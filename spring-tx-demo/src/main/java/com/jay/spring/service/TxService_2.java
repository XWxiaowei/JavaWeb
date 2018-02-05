package com.jay.spring.service;

import com.jay.spring.model.CalabashBoy;
import com.jay.spring.model.User;

import java.util.List;

/**
 * @author xiang.wei
 * @create 2018/2/5 16:06
 */
public interface TxService_2 {
    /**
     * @param calabashBoyList
     * @param userList
     * @return
     */
    public int saveBoyBatch(List<CalabashBoy> calabashBoyList, List<User> userList);

}
