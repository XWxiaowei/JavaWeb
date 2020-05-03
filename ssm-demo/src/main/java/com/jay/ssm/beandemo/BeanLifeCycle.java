package com.jay.ssm.beandemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xiang.wei
 * @date 2020/5/2 9:27 PM
 */
public class BeanLifeCycle {
    public static void main(String[] args) {
        System.out.println("*******现在开始初始化容器!");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //得到Preson,并使用
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
        System.out.println("*******现在开始关闭容器!");
        ((ClassPathXmlApplicationContext) applicationContext).registerShutdownHook();
    }
}
