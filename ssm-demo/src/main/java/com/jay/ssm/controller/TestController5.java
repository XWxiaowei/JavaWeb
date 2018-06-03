package com.jay.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xiang.wei on 2018/6/3
 *  Controller中加参数
 * @author xiang.wei
 */
@Controller
public class TestController5 {

    public static Set<String> set = new HashSet<>();

    @RequestMapping("/test5.do")
    public void  test() throws InterruptedException {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
//        判断线程安全
        String value = request.getParameter("key");
        if (set.contains(value)) {
            System.out.println(value + "\t重复出现，request不安全");
        } else {
            System.out.println(value);
            set.add(value);
        }

        Thread.sleep(1000);
    }
}
