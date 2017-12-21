package com.jay.validator.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

/**
 * @author xiang.wei
 * @create 2017/11/8 16:45
 */
@Component("ValidUserBean")
public class ValidUserBean {
    @NotEmpty
    private String account;

    @NotEmpty
    @Length(min=6,max=16)
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
