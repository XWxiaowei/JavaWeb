package com.jay.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xiang.wei on 2018/6/3
 *
 * @author xiang.wei
 */
@Controller
public class TestController4 extends BaseController {
    // 存储已有参数，用于判断参数value是否重复，从而判断线程是否安全

    @RequestMapping("/test4.do")
    public void test() throws InterruptedException {
        String value = request.getParameter("key");
        // 判断线程安全
        if (TestController3.set.contains(value)) {
            System.out.println(value + "\t重复出现，request并发不安全！");
        } else {
            System.out.println(value);
            TestController3.set.add(value);
        }
        // 模拟程序执行了一段时间
        Thread.sleep(1000);
    }
}
