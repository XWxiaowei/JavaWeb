package com.jay.ssm;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by xiang.wei on 2018/6/3
 *
 * @author xiang.wei
 */
public class TestController2Test {
    public static void main(String[] args) throws Exception {
        String prefix = UUID.randomUUID().toString().replaceAll("-", "") + "::";
        for (int i = 0; i < 1000; i++) {
            final String value = prefix + i;
            new Thread() {
                @Override
                public void run() {
                    try {
                        CloseableHttpClient httpClient = HttpClients.createDefault();
                        HttpGet httpGet = new HttpGet("http://localhost:8080/test2.do?key=" + value);
                        httpClient.execute(httpGet);
                        httpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

}
