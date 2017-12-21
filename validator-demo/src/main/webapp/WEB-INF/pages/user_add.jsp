<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>add user</title>
</head>
<body>
<form:form id="form" method="post"
           modelAttribute="validUserBean">
    添加用户
    <form:label path="account">账号：</form:label>
    <form:input path="account" />
    <form:errors path="account" />
    <form:label path="password">密码：</form:label>
    <form:input path="password" />
    <form:errors path="password" />
    <button type="submit">添加</button>
    <button type="reset">重置</button>
    <c:out value="${show}"></c:out>
</form:form>
</body>
</html>