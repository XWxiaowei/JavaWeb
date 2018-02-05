package com.jay.spring.service.impl;

import com.jay.spring.dao.TxDao;
import com.jay.spring.model.CalabashBoy;
import com.jay.spring.model.User;
import com.jay.spring.service.TxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiang.wei
 * @create 2018/2/5 11:02
 */
@Service("txService")
public class TxServiceImpl implements TxService {
    @Autowired
    private TxDao txDao;

    //测试外部事务和内部事务的传播性。
//    @Transactional(rollbackFor = RuntimeException.class)
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
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int getBoyBatch() {
        List<CalabashBoy> calabashBoyList = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                CalabashBoy calabashBoy = new CalabashBoy();
                calabashBoy.setName("张三" + i);
                calabashBoy.setMana(i);
                calabashBoyList.add(calabashBoy);
            }
            for (int i = 0; i < 3; i++) {
                User user = new User();
                user.setName("李四" + i);
                user.setPassword("1212" + i);
                user.setAge(i);
                userList.add(user);
            }
            saveBoyBatch(calabashBoyList, userList);
        }
        User user = new User();
        user.setName("李四1212121111");
        user.setPassword("1212");
        user.setAge(121);
        userList.add(user);
        saveBoyBatch(calabashBoyList, userList);
        return 1;
    }

}
