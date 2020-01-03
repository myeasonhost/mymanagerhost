package com.eason.transfer.openapi.core.system.dao;

import com.eason.transfer.openapi.core.system.entity.po.Resource;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ResourceDao extends JpaRepository<Resource, String> {

    List<Resource> findAllByParentId(String parentId);
    Resource findAllById(String Id);

    @Modifying
    @Transactional
    void deleteAllByParentId(String parentId);
}
