package com.eason.transfer.openapi.core.system.dao;

import com.eason.transfer.openapi.core.system.entity.po.RoleResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRoleDao extends JpaRepository<RoleResource, String> {
    List<RoleResource> findAllByRoleId(String roleId);
}




