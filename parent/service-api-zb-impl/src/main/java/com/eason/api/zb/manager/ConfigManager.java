package com.eason.api.zb.manager;

import com.eason.api.zb.dao.RoomAttributeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConfigManager {
    private static Logger logger = LoggerFactory.getLogger(ConfigManager.class);

    @Autowired
    private RoomAttributeDao roomAttributeDao;
    private Map<String,Integer> time;

    //    public static final Map<String,Integer> time=new HashMap<String,Integer>(){
//        {
//            put("VIP1",3*60);
//            put("VIP2",8*60);
//            put("VIP3",15*60);
//            put("VIP4",25*60);
//            put("VIP5",40*60);
//            put("VIP6",60*60);
//        }
//    };
    public Map<String,Integer> createMap(){
        time=new HashMap<>();
        time.put("VIP1", Integer.parseInt(roomAttributeDao.findByAttributeKey("VIP1").getAttributeValue()));
        time.put("VIP2",Integer.parseInt(roomAttributeDao.findByAttributeKey("VIP2").getAttributeValue()));
        time.put("VIP3",Integer.parseInt(roomAttributeDao.findByAttributeKey("VIP3").getAttributeValue()));
        time.put("VIP4",Integer.parseInt(roomAttributeDao.findByAttributeKey("VIP4").getAttributeValue()));
        time.put("VIP5",Integer.parseInt(roomAttributeDao.findByAttributeKey("VIP5").getAttributeValue()));
        time.put("VIP6",Integer.parseInt(roomAttributeDao.findByAttributeKey("VIP6").getAttributeValue()));
        time.put("VIP7",Integer.parseInt(roomAttributeDao.findByAttributeKey("VIP7").getAttributeValue()));
        return time;
    }

    public Integer getTreeTime(short level){
        if (level<=0){
            return 0;
        }
        if (level>=7){
            level=7;
        }
        return Integer.parseInt(roomAttributeDao.findByAttributeKey("VIP"+level).getAttributeValue());
//        return createMap().get("VIP"+level);
    }

}
