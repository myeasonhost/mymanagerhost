package com.eason.report.pull.core;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceReportPullDsApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
//        String url="https://record.ky013.com:190/getRecordHandle?agent={agent}&timestamp={timestamp}&param={param}&key={key}";
        String url="https://record.ky013.com:190/getRecordHandle?agent=62164&timestamp=1561098720211&param=ojklHvb4TGpzzIxJcwLxtZ8RxEQofdRZwy%2FbqaVGwBG44zajMPRwVvHpuWRlW274d%2Bolrh7SxvuSXNZ2jMj6aw%3D%3D&key=d24321d5270db8e79afdd05cf8e81fff";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        URI uri = builder.build(true).toUri();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Accept", MediaType.TEXT_PLAIN_VALUE);

        Map<String, Object> request= new HashMap<>();
        request.put("agent", "62164");
        request.put("timestamp",1561098720211L);
        request.put("param", "ojklHvb4TGpzzIxJcwLxtZ8RxEQofdRZwy%2FbqaVGwBG44zajMPRwVvHpuWRlW274d%2Bolrh7SxvuSXNZ2jMj6aw%3D%3D");
        request.put("key", "d24321d5270db8e79afdd05cf8e81fff");
        String result=restTemplate.getForObject(uri,String.class);
//        String result=restTemplate.exchange(uri, HttpMethod.GET,new HttpEntity<>(requestHeaders),String.class).getBody();
//        String result=restTemplate.getForEntity(url,String.class,request).getBody();
        JSONObject body=JSONObject.parseObject(result);
        System.out.println(result);
        System.out.println(body.getJSONObject("d").getString("code"));


    }

    @Test
    public void test(){
        String url = "https://record.ky013.com:190/getRecordHandle?agent=62164&timestamp=1561098720211&param=ojklHvb4TGpzzIxJcwLxtZ8RxEQofdRZwy%2FbqaVGwBG44zajMPRwVvHpuWRlW274d%2Bolrh7SxvuSXNZ2jMj6aw%3D%3D&key=d24321d5270db8e79afdd05cf8e81fff";
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);
        URI uri = uriComponentsBuilder.build(true).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.TEXT_PLAIN_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        System.out.println(response);
    }

    private static final String pidtoken="691E87938EB3A6BD774CA98D5497B081"; //明码

    protected String getKey(String params){
        try {
            // 确定计算方法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            Base64.Encoder base64Encoder = Base64.getEncoder();
            // 加密字符串
            String key = base64Encoder.encodeToString(md5.digest((params+pidtoken).getBytes("utf-8")));
            return key;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
