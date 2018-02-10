package com.jay.hessian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianExporter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * Created by xiang.wei on 2018/2/10
 *
 * @author xiang.wei
 */
@Controller
public class HessianServer {
    @Autowired
    private UserService userService;

    @Bean(name = "/userService1.hs")
    public HessianExporter getUserService() {
        HessianExporter exporter = new HessianExporter();
        //Hessian服务的接口
        exporter.setServiceInterface(UserService.class);
        //Hessian服务的接口Impl
        exporter.setService(userService);
        return exporter;
    }
}
