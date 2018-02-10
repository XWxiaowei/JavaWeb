package com.jay;

import com.jay.hessian.User;
import com.jay.hessian.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by xiang.wei on 2018/2/10
 *
 * @author xiang.wei
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    public String sayMyName(User user) {
        System.out.println("接收到的参数是=" + user.toString());
        return user.getName();
    }
}
