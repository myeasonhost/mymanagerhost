package com.eason.transfer.openapi.core.system.dao;

import com.eason.transfer.openapi.core.system.entity.po.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, String> {

}
