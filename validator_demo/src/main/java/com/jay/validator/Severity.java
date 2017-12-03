package com.jay.validator;

import javax.validation.Payload;

/**
 * Serverity用来指定错误级别，
 * 可以根据ConstraintViolation.getConstraintDescriptor()来得到之前指定的错误级别。
 * @author xiang.wei
 * @create 2017/11/29 10:23
 */
public class Severity {
    public static class Info implements Payload {};
    public static class Error implements Payload{};
}
