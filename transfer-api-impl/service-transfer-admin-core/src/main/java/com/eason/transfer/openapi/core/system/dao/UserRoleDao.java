package com.eason.transfer.openapi.core.system.dao;

import com.eason.transfer.openapi.core.system.entity.po.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleDao extends JpaRepository<UserRole, String> {
    List<UserRole> findAllByUserId(String userId);
}
