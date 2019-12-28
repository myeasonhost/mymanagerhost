package com.eason.transfer.openapi.core.system.dao;

import com.eason.transfer.openapi.core.system.entity.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByAccount(String account);

}
