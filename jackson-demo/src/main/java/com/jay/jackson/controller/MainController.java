package com.jay.jackson.controller;

import com.jay.jackson.model.ProductVo;
import com.jay.jackson.model.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xiangwei
 *
 */
@Controller
@RequestMapping(value="/main")
public class MainController {
	
	/**
	 * 跳转到首页
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String page(HttpServletRequest request, HttpServletResponse response){
		return "/index";
	}

    @RequestMapping(value = "/getProducts",method = RequestMethod.POST)
	@ResponseBody
	public List getProducts(HttpServletRequest request,HttpServletResponse response){
		List<ProductVo> productVos=new ArrayList<>();
		for (int i=1;i<=2;i++){
			ProductVo productVo= new ProductVo();
			productVo.setProductId(20170720125047233L+i);
			productVo.setProductName("测试商品"+i);
			productVos.add(productVo);
		}
		return productVos;
	}

	@RequestMapping(value = "/getUsers",method = RequestMethod.POST)
	@ResponseBody
	public List<UserVo> getUsers(){
		List<UserVo> userVos=new ArrayList<>();
		for (int i=1;i<=2;i++){
			UserVo userVo=new UserVo();
			userVo.setUserid((long)i);
			userVo.setUserName("测试用户"+i);
			userVo.setCreateTime(new Date());
			userVos.add(userVo);
		}
		return userVos;
	}



}
