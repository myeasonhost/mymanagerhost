package com.eason.transfer.openapi.core.mgr;


import com.eason.transfer.openapi.core.mongo.po.BasePo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ApiMethodMgr {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Transactional
  public <T extends BasePo> void saveAndUpdate(T po,Class<T> tClass){
//    Optional<T> result =mongoTemplate.update(tClass)
//            .matching(query(where("id").is(po.getId())))
//            .replaceWith(po)
//            .withOptions(FindAndReplaceOptions.options().upsert())
//            .findAndReplace();
//    if(result.isPresent()){
//      log.info("API方法覆盖更新成功po="+po);
//    }
  }



}
