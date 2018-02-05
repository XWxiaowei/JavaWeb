package com.jay.spring.service.impl;

import com.jay.spring.dao.TxDao;
import com.jay.spring.model.CalabashBoy;
import com.jay.spring.model.User;
import com.jay.spring.service.TxService;
import com.jay.spring.service.TxService_2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    private TxService_2  txService_2;

//    @Transactional(rollbackFor = Exception.class)
    @Override
    public int getBoyBatch() throws Exception {
        List<CalabashBoy> calabashBoyList = new ArrayList<>();
        List<User> userList = new ArrayList<>();

//        try {
            for (int j = 0; j < 2; j++) {
                for (int i = 0; i < 2; i++) {
                    CalabashBoy calabashBoy = new CalabashBoy();
                    calabashBoy.setName("张三" + i);
                    calabashBoy.setMana(i);
                    calabashBoyList.add(calabashBoy);
                }
                for (int i = 0; i < 2; i++) {
                    User user = new User();
                    user.setName("李四" + i);
                    user.setPassword("1212" + i);
                    user.setAge(i);
                    userList.add(user);
                }
                txService_2.saveBoyBatch(calabashBoyList, userList);
                calabashBoyList.clear();
                userList.clear();
            }
            for (int i = 0; i < 2; i++) {
                CalabashBoy calabashBoy = new CalabashBoy();
                calabashBoy.setName("王二" + i);
                calabashBoy.setMana(i);
                calabashBoyList.add(calabashBoy);

                User user = new User();
                user.setName("六六六1212121111");
                user.setPassword("1212");
                user.setAge(121);
                userList.add(user);

                txService_2.saveBoyBatch(calabashBoyList, userList);
            }
//        } catch (RuntimeException e) {
//            throw new Exception();
//        }
        return 1;
    }

}
