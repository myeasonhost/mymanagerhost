package com.eason.report.pull.ag;

import com.eason.report.pull.ag.dao.IndexBannerDao;
import com.eason.report.pull.ag.dao.MsgNotificationDao;
import com.eason.report.pull.ag.dao.UserPersonalDao;
import com.eason.report.pull.ag.model.SaveMongoEventListener;
import com.eason.report.pull.ag.model.ZbConstant;
import com.eason.report.pull.ag.po.ZbTIndexBanner;
import com.eason.report.pull.ag.po.ZbTMsgNotification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexTests {
	@Autowired
	private IndexBannerDao indexBannerDao;
	@Autowired
	private MsgNotificationDao msgNotificationDao;
	@Autowired
	private UserPersonalDao userPersonalDao;
	@Autowired
	private SaveMongoEventListener saveMongoEventListener;

	@Test
	public void addIndexBanner() {
		ZbTIndexBanner indexBanner=new ZbTIndexBanner();
		indexBanner.setTitle("标题测试003");
		indexBanner.setUrl("http://indexBanner-url.png");
		indexBanner.setType(ZbConstant.Room.Type.ticket.name());
		indexBanner.setThumbImgUrl("http://indexBanner-iimg-url.png");
		indexBanner.setCreateUser("admin");
		indexBanner.setCreateTime(new Timestamp(System.currentTimeMillis()));
//		indexBannerDao.save(indexBanner);
	}

	@Test
	public void addMsgNotification() {
		ZbTMsgNotification msgNotification=new ZbTMsgNotification();
		msgNotification.setTitle("消息003");
		msgNotification.setUrl("http://msgNotification-url.png");
		msgNotification.setType(ZbConstant.Room.Type.ticket.name());
		msgNotification.setCreateUser("admin");
		msgNotification.setCreateTime(new Timestamp(System.currentTimeMillis()));
//		msgNotificationDao.save(msgNotification);
	}
}
