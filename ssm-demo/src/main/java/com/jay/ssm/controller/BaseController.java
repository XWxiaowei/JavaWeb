package com.jay.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiang.wei on 2018/6/3
 *
 * @author xiang.wei
 */
@Controller
public class BaseController {
    @Autowired
    protected HttpServletRequest request;
}
