package com.jay.jackson.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * Created by maerfeifei on 2017/7/21.
 */
//将json转换器替换为fastjson实现，xml转换器替换为jackson-dataformat-xml实现
@Configuration
public class Config_web {
    public HttpMessageConverter messageConverter(){
        return null;
    }
}
