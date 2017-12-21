package com.jay.validator;

import com.jay.validator.model.ValidUserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author xiang.wei
 * @create 2017/11/8 16:46
 */
@Controller
public class   AdminController {
    @Autowired
    private ValidUserBean validUserBean;

    //通过url访问新增页面
    @RequestMapping(value = "/admin/add", method = RequestMethod.GET)
    public String userNew(Model model) {
        model.addAttribute("validUserBean", validUserBean);
        return "user_add";
    }

    //点击按钮提交时
    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String userAdd(
            @ModelAttribute("validUserBean") @Valid ValidUserBean validUserBean,
            BindingResult result, Model model) {
        if (!result.hasErrors()) {
            model.addAttribute("show", "ok");
        }
        return "user_add";
    }
}
