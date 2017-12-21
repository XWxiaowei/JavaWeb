package com.jay.validator.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xiang.wei
 * @create 2017/11/8 17:27
 */
public class Author {
    @NotBlank(message = "作者名称不能为空")
    private String username;
    @NotBlank
    @Size(min = 6, max = 20,message = "密码必须在6和20之间")
    private String password;
    @NotBlank(message = "邮箱不能为空")
    @Email
    private String email;
    @Min(0)
    private int age;
    @NotBlank(message = "地址不能为空")
    @Size(max = 500)
    private String address;
    @Past
    @NotNull(message = "出生日期不能为空")
    private Date birthday;
    @Valid
    @NotNull
    private List<Article> articles = new ArrayList<>();


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
