<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>UEditor</title>
    <script type="text/javascript">
        var basePath = "<%=basePath%>";
    </script>
    <script type="text/javascript" src="../assets/jquery-1.12.0.min.js"></script>
</head>
<body>
<div>
    <input id="requestData1" type="button" value="将返回数据类型long转成String方式1">
    <input id="requestData2" type="button" value="将返回数据类型转成String方式2">
    <input id="requestData3" type="button" value="将返回数据类型String方式">
</div>
<script>
  $(function(){
     $('#requestData1').click(function(){
         $.post("<%=path%>/main/getProducts",null,
                 function(data){
             console.log("配置的方式:",data);
         });
     });
      $('#requestData2').click(function(){
          $.post("<%=path%>/main/getUser",null,
                  function(data){
              console.log("消息的方式",data);
          },'json');
      });


  });

</script>
</body>
</html>