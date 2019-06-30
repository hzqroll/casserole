package com.roll.casserole.transaction.dao.impl;

import com.roll.casserole.transaction.dao.AccountService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zongqiang.hao
 * created on 2019-04-11 21:36.
 */
public class AccountServiceImpl implements AccountService {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void inset(String customerName, String money) {
        throw new NotImplementedException();
    }

    @Override
    public Object get(int id) {
        return jdbcTemplate.queryForObject("", new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return "123";
            }
        });
    }

    @Override
    public void update(int id, String customerName, String money) {
        throw new NotImplementedException();
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
