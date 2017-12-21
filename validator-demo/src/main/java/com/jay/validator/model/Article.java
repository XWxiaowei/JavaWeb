package com.jay.validator.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author xiang.wei
 * @create 2017/11/29 9:56
 */
public class Article {
    @NotBlank(message = "文章的标题不能为空")
//    @CheckCase(value = CaseMode.UPPER,message = "标题名称必须大写")
    private String headName;

    @NotBlank(message = "文章类型不能为空")
    @Length(min = 2,max = 10,message = "文章类型的长度必须在2到10之间",groups = ArticleChecks.class)
    private String headType;

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String getHeadType() {
        return headType;
    }

    public void setHeadType(String headType) {
        this.headType = headType;
    }
}
