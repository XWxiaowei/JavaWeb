package com.jay.spring.dao.impl;

import com.jay.spring.dao.TxDao;
import com.jay.spring.model.CalabashBoy;
import com.jay.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author xiang.wei
 * @create 2018/2/5 11:09
 */
@Repository(value = "txDao")
public class TxDaoImpl implements TxDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insertBoyBatch(final List<CalabashBoy> calabashBoyList) {
        String sql = "insert into calabash_boy(name,mana) values (?,?)";
        try {
            int[] updateCounts = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1, calabashBoyList.get(i).getName());
                    ps.setInt(2, calabashBoyList.get(i).getMana());
                }
                @Override
                public int getBatchSize() {
                    return calabashBoyList.size();
                }
            });
            if (updateCounts.length == calabashBoyList.size()) {
                return 1;
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insertUserBatch(final List<User> userList) {
        String sql = "insert into `user`(name,password,age) values (?,?,?)";
        try {
            int[] updateCounts = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1, userList.get(i).getName());
                    ps.setString(2, userList.get(i).getPassword());
                    ps.setInt(3, userList.get(i).getAge());
                }

                @Override
                public int getBatchSize() {
                    return userList.size();
                }
            });
            if (updateCounts.length == userList.size()) {
                return 1;
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
