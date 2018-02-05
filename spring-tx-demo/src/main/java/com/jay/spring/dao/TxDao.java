package com.jay.spring.dao;

import com.jay.spring.model.CalabashBoy;
import com.jay.spring.model.User;

import java.util.List;

/**
 * @author xiang.wei
 * @create 2018/2/5 11:09
 */

public interface TxDao {

    /**
     * 批量插入男孩表
     * @param calabashBoyList
     * @return
     */
    int insertBoyBatch(final List<CalabashBoy> calabashBoyList);

    /**
     * 批量插入用户表
     * @param userList
     * @return
     */
    int insertUserBatch(final List<User> userList);
}
