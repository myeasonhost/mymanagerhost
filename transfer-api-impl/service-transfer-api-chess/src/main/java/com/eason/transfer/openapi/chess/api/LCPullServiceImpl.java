package com.eason.transfer.openapi.chess.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eason.transfer.openapi.chess.api.service.IPullService;
import com.eason.transfer.openapi.chess.dao.entity.TChessGameConfigPo;
import com.eason.transfer.openapi.chess.dao.entity.TChessGamePo;
import com.eason.transfer.openapi.chess.dao.mapper.TChessGamePoMapper;
import com.eason.transfer.openapi.chess.utils.Encrypt;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class LCPullServiceImpl implements IPullService<TChessGamePo, TChessGameConfigPo> {

    @Autowired
    private TChessGamePoMapper chessGamePoMapper;

    @Override
    public TChessGamePo extAttr(TChessGamePo po, TChessGameConfigPo configPo) {
        Map<String,String> map=new HashMap<>();
        String[] ary=configPo.getSiteId().split(",");
        for (String s:ary){ //_1020
            String[] i=s.split("_");
            map.put(i[1],configPo.getAgentId()+"_"+i[0]);
        }
        for(Map.Entry<String,String> site:map.entrySet()){
            if(po.getAccount().startsWith(site.getValue())){
                po.setAccount(po.getAccount().substring(site.getValue().length()));
                po.setSiteId(site.getKey());
            }
        }
        //判断输赢
//        if(po.getProfit().doubleValue() >0){
//            po.setWinLossType(Byte.valueOf("2"));//赢
//        }else if(po.getProfit().doubleValue() < 0){
//            po.setWinLossType(Byte.valueOf("1"));//输
//        }else if(po.getProfit().doubleValue() == 0){
//            po.setWinLossType(Byte.valueOf("3"));//和
//        }
        po.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return po;
    }

    @Override
    public List<TChessGamePo> pullBet(Long startId,Long endId,TChessGameConfigPo configPo) throws Exception{
        try {
            Long timestamp = System.currentTimeMillis();
            Long startTime=startId;
            Long endTime=endId;
            Map<String, Object> request= new HashMap<>();
            request.put("agent", configPo.getAgentId());
            request.put("timestamp",timestamp);
            request.put("param", getParam(startTime, endTime,configPo.getDeskey()));
            request.put("key", getKey(configPo.getAgentId(),timestamp,configPo.getMd5key()));

            String url=String.format(configPo.getPullurl()+"?agent=%s&timestamp=%s&param=%s&key=%s",
                    request.get("agent"),
                    request.get("timestamp"),
                    request.get("param"),
                    request.get("key"));
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
            URI uri = builder.build(true).toUri();

            log.info(configPo.getAgentId()+"拉取请求="+uri.toString());
            log.info(configPo.getAgentId()+"请求参数="+request);
            RestTemplate restTemplate=new RestTemplate();
            String body=restTemplate.getForObject(uri,String.class);
            JSONObject result= JSONObject.parseObject(body);
            log.info(configPo.getAgentId()+"拉取返回结果="+result.toJSONString());
            if(result.getJSONObject("d")!=null && "16".equals(result.getJSONObject("d").getString("code"))){
                return new ArrayList<>();
            }

            if(result.getJSONObject("d")!=null && "0".equals(result.getJSONObject("d").getString("code"))){
                JSONArray jsonArray=convertList(result.getJSONObject("d").getJSONObject("list"));
                return jsonArray.toJavaList(TChessGamePo.class);
            }
            return new ArrayList<>();
        } catch (Exception e) {
            if(e instanceof HttpClientErrorException){
                log.error(configPo.getAgentId()+"拉取请求次数频繁，错误信息="+e.getMessage());
            }
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void saveAndUpdate(List<TChessGamePo> listPo, TChessGameConfigPo configPo) {
        for(TChessGamePo po:listPo){
            TChessGamePo chessGamePo=chessGamePoMapper.selectByPrimaryKey(po.getGameid());
            if(chessGamePo==null){
                po=this.extAttr(po,configPo);
                chessGamePoMapper.insert(po);
            }else{
                po=this.extAttr(po,configPo);
                po.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                chessGamePoMapper.updateByPrimaryKey(po);
            }
        }
    }

    @Override
    public Long getMaxId(TChessGameConfigPo configPo){
        Timestamp maxTime=chessGamePoMapper.getMaxTime(configPo.getAgentId());
        if(maxTime==null){
            return configPo.getInitstartid().getTime();
        }
        return maxTime.getTime();
    }

    @Override
    public Long getNextId(TChessGameConfigPo configPo) {
        Date startId= DateUtils.addSeconds(new Date(getMaxId(configPo)),1);
        return startId.getTime();
    }

    private String getParam(long startTime, long endTime,String deskey) {
        StringBuffer sb = new StringBuffer();
        sb.append("s=6&startTime=").append(startTime).append("&endTime=").append(endTime);
        try {
            return Encrypt.AESEncrypt(sb.toString(), deskey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getKey(String agent, long timestamp,String md5key) {
        StringBuffer sb = new StringBuffer();
        sb.append(agent).append(timestamp).append(md5key);
        return Encrypt.MD5(sb.toString());
    }

    private JSONArray convertList(JSONObject listObject) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            JSONArray jsonArray=new JSONArray();
            JSONArray gameIdArray = listObject.getJSONArray("GameID");
            JSONArray accountArray = listObject.getJSONArray("Accounts");
            JSONArray serverArray = listObject.getJSONArray("ServerID");
            JSONArray kindIdArray = listObject.getJSONArray("KindID");
            JSONArray tabelIdArray = listObject.getJSONArray("TableID");
            JSONArray chariIdArray = listObject.getJSONArray("ChairID");
            JSONArray userCountArray = listObject.getJSONArray("UserCount");
            JSONArray allBetArray = listObject.getJSONArray("AllBet");
            JSONArray profitArray = listObject.getJSONArray("Profit");
            JSONArray revenueArray = listObject.getJSONArray("Revenue");
            JSONArray gameStartTimeArray = listObject.getJSONArray("GameStartTime");
            JSONArray gameEndTimeArray = listObject.getJSONArray("GameEndTime");
            JSONArray channelIDArray = listObject.getJSONArray("ChannelID");
            JSONArray cardValueArray = listObject.getJSONArray("CardValue");
            JSONArray cellScoreArray = listObject.getJSONArray("CellScore");
            JSONArray lineCodeArray = listObject.getJSONArray("LineCode");

            for(int i=0;i<gameIdArray.size();i++){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("GameID",gameIdArray.get(i));
                jsonObject.put("Account",accountArray.get(i));  //去掉多余的s Accounts改成Account
                jsonObject.put("ServerID",serverArray.get(i));
                jsonObject.put("KindID",kindIdArray.get(i));
                jsonObject.put("TableID",tabelIdArray.get(i));
                jsonObject.put("ChairID",chariIdArray.get(i));
                jsonObject.put("UserCount",userCountArray.get(i));
                jsonObject.put("AllBet",allBetArray.get(i));
                jsonObject.put("Profit",profitArray.get(i));
                jsonObject.put("Revenue",revenueArray.get(i));
                jsonObject.put("GameStartTime", new Timestamp(sdf.parse((String)gameStartTimeArray.get(i)).getTime()));
                jsonObject.put("GameEndTime",new Timestamp(sdf.parse((String)gameEndTimeArray.get(i)).getTime()));
                jsonObject.put("ChannelID",channelIDArray.get(i));
                jsonObject.put("CardValue",cardValueArray.get(i));
                jsonObject.put("CellScore",cellScoreArray.get(i));
                jsonObject.put("LineCode",lineCodeArray.get(i));
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
