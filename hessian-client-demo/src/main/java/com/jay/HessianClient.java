package com.jay;

import com.caucho.hessian.client.HessianProxyFactory;
import com.jay.hessian.User;
import com.jay.hessian.UserService;

import java.net.MalformedURLException;


/**
 * Created by xiang.wei on 2018/2/10
 *
 * @author xiang.wei
 */
public class HessianClient {
    public static void main(String[] args) {
        String url = "http://localhost:8080/hessian-server-demo/userService.hs";
        HessianProxyFactory factory = new HessianProxyFactory();
        try {
            UserService userService = (UserService) factory.create(
                        UserService.class, url);
            User user = new User();
            user.setName("张三");
            user.setAge(11);
            System.out.println(userService.sayMyName(user));;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
