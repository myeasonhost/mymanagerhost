package com.eason.live.zb;

import com.eason.live.zb.service.impl.LiveUrlServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZbLiveMediaApplicationTests {

	@Autowired
	private LiveUrlServiceImpl liveUrlService;

	@Test
	public void contextLoads() {
		System.out.println(liveUrlService.getPushUrl("1"));
	}

}
