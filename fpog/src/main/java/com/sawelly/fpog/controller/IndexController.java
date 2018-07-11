package com.sawelly.fpog.controller;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class IndexController {

    private Logger log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private DruidDataSource dataSource;

    @RequestMapping("/index")
    public String index(){

        log.info(dataSource.getDriverClassName());
        log.info(dataSource.getUsername());
        log.info(dataSource.getUrl());
        String sql = "select * from sys_project;";
        String str = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = dataSource.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                str = rs.getString(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       log.info("srt===="+str);

        return str;

    }
}
