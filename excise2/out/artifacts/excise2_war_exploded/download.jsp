<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vo.Download" %><%--
  Created by IntelliJ IDEA.
  User: 13281
  Date: 2020/9/20
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>下载</title>
    <link href="./css/download.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div id="main_top">
        <div class="main_topLeft"><img src="images/main_left.png"></div>
        <div class="main_topRight1">欢迎你：${sessionScope.get("role")}<a class="a1" href="quit.do?id=quit" >【安全退出】 </a ></div>
        <div class="main_topRight2">
            <div class="main_class2"></div>
            <div class="main_class1"><a href="downloadRequest.do?id=jump_main" class="a2">首页</a></div>
            <div class="main_class1"><a href="downloadRequest.do?id=jump_download" class="a2">资源下载</a></div>
            <div class="main_class1"><a href="userManage.do" class="a2">用户管理</a></div>
            <div class="main_class1"><a href="resourcesManage.do" class="a2">资源管理</a></div>
            <div class="main_class1"><a href="personalCenter.do" class="a2">个人中心</a></div>
        </div>
    </div>
    <div id="download_center">
        <c:forEach var="downloadList" items="${sessionScope.downloads}">
            <div class="download_div">
                <div class="download_div2"><img src="${downloadList.getImage()}"> </div>
                <div class="download_div6"></div>
                <div class="download_div3">${downloadList.getName()}</div>
                <div class="download_input"><a href="/excise1/download.do?fileName=${fn:substring(downloadList.getPath(),fn:length(downloadList.getPath())-fn:length(downloadList.getName())-4 ,fn:length(downloadList.getPath()) )}">立即下载</a></div>
                <div class="download_div4">大小:${downloadList.getSize()} &nbsp;时间:2020-10-1 <br/>&nbsp;星级：<img src="images/${downloadList.getStar()}star.jpg"></div>
                <div class="download_div7">${downloadList.getDescription()}</div>
            </div>
        </c:forEach>
    </div>
</body>
</html>
