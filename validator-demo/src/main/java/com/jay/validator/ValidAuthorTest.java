package com.jay.validator;

import com.jay.validator.model.Article;
import com.jay.validator.model.ArticleChecks;
import com.jay.validator.model.Author;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author xiang.wei
 * @create 2017/11/8 17:18
 */
@Component
public class ValidAuthorTest {
    private static ValidatorFactory factory;
    private static Validator validator;

    @Autowired
    private Validator validator1;

    public static void main(String[] args) {
        //方式一
//        factory = Validation.buildDefaultValidatorFactory();
//        validator = factory.getValidator();
        //方式二
        validator = Validation.byProvider( HibernateValidator.class )
                .configure()
                .failFast(true)   //快速返回
                .buildValidatorFactory()
                .getValidator();
//        testKindsOfErrors();
//        testCheckCase();

        testValidGroup();
//        ValidAuthorTest validAuthorTest = new ValidAuthorTest();
//        validAuthorTest.testCheckCase2();
    }

    public static void testKindsOfErrors() {
        Author author = new Author();
        author.setUsername("123");
        author.setPassword("1234");
        author.setAddress("");
        author.setEmail("");
        author.setAge(-20);

        Article article = new Article();
        article.setHeadName("");
        author.getArticles().add(article);
        Set<ConstraintViolation<Author>> set = validator.validate(author);
        for (ConstraintViolation<Author> c : set) {
            System.out.println(c.getMessage());
        }
    }
    public static void testCheckCase() {
        Article article = new Article();
        article.setHeadName("zhangsan");
        Set<ConstraintViolation<Article>> validate = validator.validate(article);
        for (ConstraintViolation<Article> articleC : validate) {
            System.out.println(articleC.getMessage());
        }
    }

    public  void testCheckCase2() {
        Article article = new Article();
        article.setHeadName("zhangsan");
        Set<ConstraintViolation<Article>> validate = validator1.validate(article);
        for (ConstraintViolation<Article> articleC : validate) {
            System.out.println(articleC.getMessage());
        }
    }
    public static void  testValidGroup(){
        Article article = new Article();
        article.setHeadName("121");
        article.setHeadType("1");
        Set<ConstraintViolation<Article>> validate = validator.validate(article, ArticleChecks.class);
        for (ConstraintViolation<Article> articleC : validate) {
            System.out.println(articleC.getMessage());
        }
    }
}

