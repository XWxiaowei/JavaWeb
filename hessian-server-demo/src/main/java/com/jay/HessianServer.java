package com.jay;

import com.jay.hessian.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;
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

    /**
     *
     * @return
     */
    @Bean(name = "/userService.hs")
    public HessianServiceExporter getUserService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        //Hessian服务的接口
        exporter.setServiceInterface(UserService.class);
        //Hessian服务的接口Impl
        exporter.setService(userService);
        return exporter;
    }
}
