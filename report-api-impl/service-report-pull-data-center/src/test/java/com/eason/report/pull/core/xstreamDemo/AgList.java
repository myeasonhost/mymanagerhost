package com.eason.report.pull.core.xstreamDemo;

import com.eason.report.pull.core.utils.DateUtil;
import com.eason.report.pull.core.utils.Md5Util;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("result")
public class AgList {
    @XStreamImplicit(itemFieldName="row")
    private List<AgModel> agList;
    @XStreamAlias("info")
    private String info;
    @XStreamAlias("addition")
    private String addition;

    public List<AgModel> getAgList() {
        return agList;
    }

    public void setAgList(List<AgModel> agList) {
        this.agList = agList;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        RestTemplate restTemplate=new RestTemplate();
        String cagent="CS2";
        Long startdate=DateUtil.covertTime("2019-07-05 06:50:00").getTime();
        Long enddate=DateUtil.covertTime("2019-07-05 07:00:00").getTime();
        String page="1";
        String pageLimit="100";
        String pidtoken="691E87938EB3A6BD774CA98D5497B081";
        String order="time";
//        String key= Md5Util.makeMd5Sum((cagent+startdate+enddate+pidtoken).getBytes());
        String key= Md5Util.makeMd5Sum((pidtoken+startdate+enddate+page+order+pageLimit+cagent+"superd84b062c76a87a9322Q").getBytes());
        System.out.println(key);
        MultiValueMap<String, Object> request = new LinkedMultiValueMap<>();
        request.add("cagent",cagent);
        request.add("startdate",startdate);
        request.add("enddate",enddate);
        request.add("page",page);
        request.add("pageLimit",pageLimit);
        request.add("key",key);
        String url="http://hctjjs2.gdcapi.com:7733/api?act=getgameorders&pidtoken=691E87938EB3A6BD774CA98D5497B081&productid=" +cagent+
                "&begintime="+startdate+"&endtime="+enddate+"&order="+order+"&numperpage="+page+"&page="+pageLimit+"&sessionkey="+key;

//        String url="http://ctjjs2.gdcapi.com:3333/getagsportorders_ex.xml?cagent=" +cagent+
//                "&startdate="+startdate+"&enddate="+enddate+"&page="+page+"&perpage="+pageLimit+"&key="+key;

        String str=restTemplate.getForObject(url,String.class);
        System.out.println("str="+new String(str.getBytes(),"UTF-8"));
    }
}
