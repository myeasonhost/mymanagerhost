package com.eason.transfer.openapi.core.system.dao;

import com.eason.transfer.openapi.core.system.entity.po.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceDao extends JpaRepository<Resource, String> {

    List<Resource> findAllByParentId(String parentId);

}
