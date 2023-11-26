package com.example.mall.mymall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;

import io.micrometer.common.util.StringUtils;
import java.util.List;
import java.util.Map;

@RestController
public class JdbcTest{
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/insert")
    public String insert(String type, String name) {
        if (StringUtils.isEmpty(type) || StringUtils.isEmpty(name)) {
            return "param error.";
        }
        jdbcTemplate.execute("insert into jdbc_test (`ds_type`, `ds_name`) values (\"" + type + "\", \"" + name + "\")");
        return "SQL complete.";
    }

    @GetMapping("/delete")
    public String delete(int id) {
        if (id < 0) {
            return "param error.";
        }

        List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from jdbc_test where ds_id = \"" + id + "\"");

        if (CollectionUtils.isEmpty(result)) {
            return "no record.";
        }
        jdbcTemplate.execute("delete from jdbc_test where ds_id=\"" + id + "\"");
        return "SQL complete.";
    }

    @GetMapping("/update")
    public String update(int id, String type, String name) {
        if (id < 0 || StringUtils.isEmpty(type) || StringUtils.isEmpty(name)) {
            return "param error.";
        }
        List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from jdbc_test where ds_id = \"" + id + "\"");
        if (CollectionUtils.isEmpty(result)) {
            return "no record";
        }
        jdbcTemplate.execute("update jdbc_test set ds_type=\"" + type + "\", ds_name=\"" + name + "\" where ds_id=\"" + id + "\"");
        return "SQL complete.";
    }

    @GetMapping("/queryAll")
    public List<Map<String, Object>> queryAll() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from jdbc_test");
        return list;
    }
}
