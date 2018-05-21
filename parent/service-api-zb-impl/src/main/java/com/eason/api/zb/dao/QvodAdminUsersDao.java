package com.eason.api.zb.dao;

import com.eason.api.zb.po.QvodAdminUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QvodAdminUsersDao extends JpaRepository<QvodAdminUsers, Integer> {
    QvodAdminUsers findByRememberToken(String rememberToken);
}
