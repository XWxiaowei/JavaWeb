package com.jay.jackson.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xiangwei on 2017/7/21.
 */
public class UserVo  implements Serializable{
    private Long userid;
    private String userName;
    private Date createTime;


    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
