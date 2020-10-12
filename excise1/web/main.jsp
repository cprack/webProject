<%--
  Created by IntelliJ IDEA.
  User: 13281
  Date: 2020/9/20
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <link href="./css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%//未登录禁止访问
    if((String)request.getSession().getAttribute("chrName") == null){
        String errorMsg = "抱歉，您必须先登录才能访问资源！";
        session.setAttribute("errorMsg", errorMsg);
        request.getRequestDispatcher("/error.jsp").forward(request, response);
}%>
<div id="main_top">
    <div id="main_topLeft"><img src="images/main_left.png"></div>
    <div id="main_topRight1">欢迎你：${sessionScope.get("chrName")}<a href="quit.do?id=quit">【安全退出】 </a ></div>
    <div id="main_topRight2">
        <div id="main_div1"><a href="downloadRequest.do?id=jump_main" class="a2">首页</a></div>
        <div id="main_div2"><a href="downloadRequest.do?id=jump_download" class="a2">下载</a></div>
        <div id="main_div3">设计</div>
        <div id="main_div4">相册</div>
        <div id="main_div5">论坛</div>
        <div id="main_div6">关于</div>
    </div>
</div>
<div id="main_center"><img src="images/main_center.jpg"></div>

</body>
</html>
