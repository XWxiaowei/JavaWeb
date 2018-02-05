package com.jay.spring.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xiang.wei
 * @create 2018/2/5 11:04
 */
@RunWith(JUnit4.class)
public class TxServiceTest {
    private TxService txService;
    @Before
    public void setUp() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        txService = context.getBean("txService",TxService.class);
    }
    @Test
    public void testGetBoyBatch() {
        try {
            txService.getBoyBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
