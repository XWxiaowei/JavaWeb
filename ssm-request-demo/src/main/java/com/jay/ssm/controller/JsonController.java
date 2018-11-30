package com.jay.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.jay.ssm.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiang.wei on 2018/11/30
 *
 * @author xiang.wei
 */
@Controller
@RequestMapping(produces = {"application/json;charset=utf-8"}, value = "/json")
public class JsonController {


    /**
     * @param userStr
     * @return
     */
    @RequestMapping(value = "getJson2.do",produces = "application/json")
    @ResponseBody
    public String getJson2(@RequestBody String userStr) {
        User user = JSON.parseObject(userStr, User.class);
        System.out.println("接收到的参数,name=" + user.getName() + ";age=" + user.getAge());
        return JSON.toJSONString("接收到的参数,name=" + user.getName() + ";age=" + user.getAge());
    }
}
