package com.jay.spring.service.impl;

import com.jay.spring.dao.TxDao;
import com.jay.spring.model.CalabashBoy;
import com.jay.spring.model.User;
import com.jay.spring.service.TxService_2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiang.wei
 * @create 2018/2/5 16:07
 */
public class TxService_2Impl implements TxService_2 {
    @Autowired
    private TxDao txDao;
    //测试外部事务和内部事务的传播性。
    @Transactional(rollbackFor = RuntimeException.class,propagation = Propagation.REQUIRES_NEW)
    @Override
    public int saveBoyBatch(List<CalabashBoy> calabashBoyList, List<User> userList) {
        int boyResult = txDao.insertBoyBatch(calabashBoyList);
        if (boyResult != 1) {
            throw new RuntimeException();
        }
        int userResult = txDao.insertUserBatch(userList);
        if (userResult != 1) {
            throw new RuntimeException();
        }
        return 1;
    }
}
