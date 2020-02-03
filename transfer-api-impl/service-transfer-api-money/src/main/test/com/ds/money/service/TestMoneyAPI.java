package com.ds.money.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ds.money.vo.GetMoneyParam;
import com.ds.money.vo.TransMoneyParam;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestMoneyAPI {

    //转账和查余额接口
    @Test
    public void transferAndQueryBalance() throws Exception {
        String siteId = "3000";  //站点
        String username = "candy"; //会员名
        String money = "10000";    //转账金额
        System.out.println("money="+money);
        String fromKey = "ds_money_key";
        String transactionId = Long.toString(System.currentTimeMillis());//随机订单号，不能重复
        String transType = "IN";
        String fromKeyType = "10021";
        String memo = "测试";
        StringBuffer param = new StringBuffer();
        param.append("username=").append(username);
        param.append("&siteId=").append(siteId);
        param.append("&fromKey=").append(fromKey);
        param.append("&fromKeyType=").append(fromKeyType);
        param.append("&remitno=").append(transactionId);
        param.append("&transType=").append(transType);
        param.append("&remit=").append(money);
        param.append("&memo=").append(memo);
        param.append("&wagerCancel=").append(1);
        String key = "12345" + TestMoneyAPIUtils.toMD5(fromKey + username + transactionId) +"123456";
        System.out.println(key);
        param.append("&key=").append(key);
        String result = null;
        String url = "http://10.200.201.31:8094/ds-money-api/transMoney";
        System.out.println(param.toString());
        result = TestMoneyAPIUtils.sendPost(url, param.toString());
        System.out.println(result);
    }
    //批量转账
    @Test
    public void transMoneyBatch() throws Exception{

        List<TransMoneyParam> list = new ArrayList();

        TransMoneyParam yefeng = new TransMoneyParam();
        String fromKey = "ds_money_key";
        String username = "suki";
        String transactionId = "833456789";
        yefeng.setFromKey(fromKey);
        yefeng.setFromKeyType("2000");
        yefeng.setSiteId("3000");
        yefeng.setUsername(username);
        yefeng.setRemitno(transactionId);
        yefeng.setRemit("100");
        String key = "12345"+ TestMoneyAPIUtils.toMD5(fromKey+username+transactionId)+"123456";
        yefeng.setKey(key);
        yefeng.setTransType("in");
        yefeng.setMemo("memo");
        list.add(yefeng);

        TransMoneyParam yefeng2 = new TransMoneyParam();
        String fromKey2 = "ds_money_key";
        String username2 = "suki2";
        String transactionId2 = "933456789";
        yefeng2.setFromKey(fromKey2);
        yefeng2.setFromKeyType("2000");
        yefeng2.setSiteId("3000");
        yefeng2.setUsername(username2);
        yefeng2.setRemitno(transactionId2);
        yefeng2.setRemit("1");
        String key2 = "12345"+ TestMoneyAPIUtils.toMD5(fromKey2+username2+transactionId2)+"123456";
        yefeng2.setKey(key2);
        yefeng2.setTransType("in");
        yefeng2.setMemo("memo");
        list.add(yefeng2);
        System.out.println(JSON.toJSONString(list));
        String url = "http://10.200.201.31:8094/ds-money-api/transMoneyBatch";
        String result = TestMoneyAPIUtils.sendPost(url, JSON.toJSONString(list));
        System.out.println(result);
    }

    //批量查询用户金额接口
    @Test
    public void getMoneyBatch()  throws Exception{
        List<GetMoneyParam> list = new ArrayList();
        GetMoneyParam ddaili1 = new GetMoneyParam();
        ddaili1.setFromKey("ds_money_key");
        ddaili1.setKey("566373e8a6e62eb841d76d8fb690f4c1cf315997683");
        ddaili1.setSiteId("1");
        ddaili1.setUsername("ddaili1");

        GetMoneyParam d3000 = new GetMoneyParam();
        d3000.setFromKey("ds_money_key");
        d3000.setKey("1124175d9a6bac2c56dfe33eef8422ad2d326915100");
        d3000.setSiteId("3000");
        d3000.setUsername("d3000");

        String url = "http://10.200.201.31:8094/ds-money-api/getMoneyBatch";
        list.add(ddaili1);
        list.add(d3000);
        String result = TestMoneyAPIUtils.sendPost(url, JSON.toJSONString(list));
        System.out.println(JSON.toJSONString(list));
        System.out.println(result);
    }

    //查询用户代理现金流记录
    @Test
    public void memberMoneyLog() throws Exception{
        String url = "http://10.200.201.31:8094/ds-money-api/memberMoneyLog";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fromKey","ds_money_key");
        jsonObject.put("username","suki3");
        jsonObject.put("key","04d6f1870ac1684cdd2529fb37b7c86e4c1f859d953");
        jsonObject.put("agentLevel","world");
        jsonObject.put("agentName","suki");
        jsonObject.put("siteId","3000");
        jsonObject.put("fromKeyTypeIsTotal","0");
        jsonObject.put("userInfoIsDetail","1");
        jsonObject.put("beginTime","2018-10-01 10:30:00");
        jsonObject.put("endTime","2018-10-31 10:30:00");
        jsonObject.put("fromKeyType","10005");
        jsonObject.put("page","1");
        jsonObject.put("pageSize","200");

        //System.out.println(jsonObject);
        String result = TestMoneyAPIUtils.sendPost(url, JSON.toJSONString(jsonObject));
        System.out.println(result);
    }

    public static void main(String[] args) {
        String fromKey = "ds_money_key";
        String username = "";
        String transactionId = "833456789";
        String key = "12345"+ TestMoneyAPIUtils.toMD5(fromKey+username+transactionId)+"123456";
        System.out.println(key);
    }

    //查询用户现金流记录
    @Test
    public void memberMoneyLogByLevel() throws Exception{
        String url = "http://10.200.201.31:8094/ds-money-api/memberMoneyLogByLevel";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","suki");
        jsonObject.put("beginTime","2018-10-01 12:30:00");
        jsonObject.put("endTime","2018-10-02 12:30:00");
        jsonObject.put("page","1");
        jsonObject.put("pageSize","200");
        jsonObject.put("fromKey","ds_money_key");
        jsonObject.put("key","11111111");
        jsonObject.put("siteId","3000");
        jsonObject.put("fromKeyType","2000");
        jsonObject.put("fromKeyTypeIsTotal","0");
        jsonObject.put("userInfoIsDetail","1");
        jsonObject.put("hashCode",null);
        String result = TestMoneyAPIUtils.sendPost(url, JSON.toJSONString(jsonObject));
        System.out.println(result);
    }
    //按天统计现金流日志
    @Test
    public void moneyLogByDate() throws Exception{
        String url = "http://10.200.201.31:8094/ds-money-api/moneyLogByDate";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","suki");
        jsonObject.put("siteId",3000);
        jsonObject.put("fromKeyType","2000");
        jsonObject.put("beginTime","2018-10-01");
        jsonObject.put("endTime","2018-10-26");
        String result = TestMoneyAPIUtils.sendPost(url, JSON.toJSONString(jsonObject));
        System.out.println(result);
    }
    //修改现金流备注接口
    @Test
    public void updateMoneyLog() throws Exception{
        String url = "http://10.200.201.31:8094/ds-money-api/updateMoneyLog";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","suki");
        jsonObject.put("siteId","3000");
        jsonObject.put("remitno","1234567890");
        jsonObject.put("memo","今天转了一块钱！");
        String result = TestMoneyAPIUtils.sendPost(url, JSON.toJSONString(jsonObject));
        System.out.println(result);
    }

    //创建用户或修改接口
    @Test
    public void setMemberData() throws Exception{
        String url = "http://127.0.0.1:8080/setMemberData";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","eason002");
        jsonObject.put("siteId","5000");
        jsonObject.put("agents","eason002");
        jsonObject.put("world","parker");
        jsonObject.put("corprator","bparker");
        jsonObject.put("superior","1");
        jsonObject.put("company","1102932216");
        String result = TestMoneyAPIUtils.sendPost(url, JSON.toJSONString(jsonObject));
        System.out.println(result);
    }

    @Test
    public void getTotalBalance() throws Exception{
        String url = "http://10.200.201.31:8094/ds-money-api/getTotalBalance";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("siteId","3000");
        String result = TestMoneyAPIUtils.sendPost(url, JSON.toJSONString(jsonObject));
        System.out.println(result);

    }
    //变更代理金额（转入转出）
    @Test
    public void changeAgentMoney() throws Exception{
        String url = "http://10.200.201.31:8094/ds-money-api/changeAgentMoney";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","suki");
        jsonObject.put("siteId",3000);
        jsonObject.put("agentLevel","agents");
        jsonObject.put("agentName","agents");
        jsonObject.put("remitno","123456896");
        jsonObject.put("remit","1");
        jsonObject.put("userLevel","username");
        jsonObject.put("agentTransType","in");
        jsonObject.put("fromKeyTypeOut","60001");
        jsonObject.put("fromKeyTypeIn","60000");


        String result = TestMoneyAPIUtils.sendPost(url, JSON.toJSONString(jsonObject));
        System.out.println(result);
    }
}


