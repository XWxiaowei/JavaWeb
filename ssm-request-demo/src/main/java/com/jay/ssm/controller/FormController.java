package com.jay.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.jay.ssm.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiang.wei on 2018/6/3
 * Controller中加参数
 *
 * @author xiang.wei
 */
@Controller
@RequestMapping(produces = {"text/plain;charset=utf-8"}, value = "/form")
public class FormController {


    /**
     * HttpServletRequest 接收表单数据
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getParmByReq.do", method = RequestMethod.POST)
    @ResponseBody
    public String getParmByReq(HttpServletRequest request) {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        System.out.println("接收到的参数,name=" + name + ";age=" + age);
        return "接收到的参数,name=" + name + ";age=" + age;
    }


    /**
     * @param name
     * @param age
     * @return
     * @RequestParam 接收表单数据
     */
    @RequestMapping(value = "/requestParam.do", method = RequestMethod.POST)
    @ResponseBody
    public String getBarBySimplePathWithRequestParam(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "age") Integer age
    ) {
        System.out.println("接收到的参数,name=" + name + ";age=" + age);
        return "接收到的参数,name=" + name + ";age=" + age;
    }


    /**
     * @ModelAttribute 用于实体类参数接收
     * @param user
     * @return
     */
    @RequestMapping(value = "/modelAttribute.do", method = RequestMethod.POST)
    @ResponseBody
    public String getModelAttribute(@ModelAttribute() User user) {
        System.out.println("接收到的参数,name=" + user.getName() + ";age=" + user.getAge());
        return "接收到的参数,name=" + user.getName() + ";age=" + user.getAge();
    }

    @RequestMapping(value = "/getNotStream.do")
    @ResponseBody
    public String getNotStream(String order) {
        JSONObject jsonObject = JSONObject.parseObject(order);

        System.out.println("接收到的参数,name=" + jsonObject.getString("name") + ";age=" + jsonObject.getString("age"));
        return "接收到的参数,name=" + jsonObject.getString("name") + ";age=" + jsonObject.getString("age");
    }

    /**
     * @param fooid
     * @param barid
     * @return
     */
    @RequestMapping(value = "/ex/foos/{fooid}/bar/{barid}", method = RequestMethod.GET)
    @ResponseBody
    public String getFoosBySimplePathWithPathVariables
            (@PathVariable long fooid, @PathVariable long barid) {

        return "Get a specific Bar with id=" + barid +
                " from a Foo with id=" + fooid;
    }


}
