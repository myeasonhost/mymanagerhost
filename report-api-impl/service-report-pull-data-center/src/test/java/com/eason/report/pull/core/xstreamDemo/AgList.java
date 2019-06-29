package com.eason.report.pull.core.xstreamDemo;

import com.eason.report.pull.core.utils.Md5Util;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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

    public static void main(String[] args){
        RestTemplate restTemplate=new RestTemplate();
        String cagent="CS2";
        String startdate="2019-06-28 23:50:00";
        String enddate="2019-06-28 23:59:00";
        String pidtoken="691E87938EB3A6BD774CA98D5497B081";
//        String gametype="YMFR";
        String key= Md5Util.makeMd5Sum((cagent+startdate+enddate+pidtoken).getBytes());
        System.out.println(key);
        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("cagent",cagent);
        request.add("startdate",startdate);
        request.add("enddate",enddate);
        request.add("key",key);
//        String url="http://ctjjs2.gdcapi.com:3333/getyoplayorders_ex.xml?cagent=" +cagent+
//                "&startdate="+startdate+"&enddate="+enddate+"&key="+key;

        String url="http://ctjjs2.gdcapi.com:3333/getorders.xml?cagent=" +cagent+
                "&startdate="+startdate+"&enddate="+enddate+"&key="+key;

        String str=restTemplate.getForObject(url,String.class);
        System.out.println("str="+str);
    }
}
