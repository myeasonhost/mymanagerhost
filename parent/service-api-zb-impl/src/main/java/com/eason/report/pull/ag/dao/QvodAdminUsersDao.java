package com.eason.report.pull.ag.dao;

import com.eason.report.pull.ag.po.QvodAdminUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QvodAdminUsersDao extends JpaRepository<QvodAdminUsers, Integer> {
    QvodAdminUsers findByRememberToken(String rememberToken);
}
