package com.jay.spring.service;

import com.jay.spring.model.CalabashBoy;
import com.jay.spring.model.User;

import java.util.List;

/**
 * @author xiang.wei
 * @create 2018/2/5 11:01
 */
public interface TxService {
    /**
     * @param calabashBoyList
     * @param userList
     * @return
     */
    public int saveBoyBatch(List<CalabashBoy> calabashBoyList,List<User> userList);

    /**
     * @return
     */
    public int getBoyBatch();
}
