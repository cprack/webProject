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
<% //未登录禁止访问
    if((String)request.getSession().getAttribute("chrName") == null){
        String errorMsg = "抱歉，您必须先登录才能访问资源！";
        session.setAttribute("errorMsg", errorMsg);
        request.getRequestDispatcher("/error.jsp").forward(request, response);
}%>
<form action="/excise1/download.do" method="post">
    <div id="main_top">
        <a></a>
        <div id="main_topLeft"><img src="images/main_left.png"></div>
        <div id="main_topRight1">欢迎你：${sessionScope.get("chrName")}<a href="quit.do?id=quit" >【安全退出】 </a ></div>
        <div id="main_topRight2">
            <div id="main_div1"><a href="downloadRequest.do?id=jump_main" class="a2">首页</a></div>
            <div id="main_div2"><a href="downloadRequest.do?id=jump_download" class="a2">下载</a></div>
            <div id="main_div3">设计</div>
            <div id="main_div4">相册</div>
            <div id="main_div5">论坛</div>
            <div id="main_div6">关于</div>
        </div>
    </div>
    <div id="download_center">
        <div class="download_div">
            <div class="download_div2"><img src="${sessionScope.downloads.get(0).getImage()}"></div>
            <div class="download_div6"></div>
            <div class="download_div3">${sessionScope.downloads.get(0).getName()}</div>
            <div class="download_input"><a href="/excise1/download.do?fileName=学习心得.rar">立即下载</a></div>
            <div class="download_div4">大小:${sessionScope.downloads.get(0).getSize()} &nbsp;时间:2020-9-22 &nbsp;星级：<img src="images/星等.png"></div>
            <div class="download_div7">${sessionScope.downloads.get(0).getDescription()}</div>
        </div>
        <div class="download_div">
            <div class="download_div2"><img src="${sessionScope.downloads.get(1).getImage()}"></div>
            <div class="download_div6"></div>
            <div class="download_div3">${sessionScope.downloads.get(1).getName()}</div>
            <div class="download_input"><a href="/excise1/download.do?fileName=课堂笔记.rar">立即下载</a></div>
            <div class="download_div4">大小:${sessionScope.downloads.get(1).getSize()} &nbsp;时间:2020-9-22  星级：<img src="images/星等.png"></div>
            <div class="download_div7">${sessionScope.downloads.get(1).getDescription()}</div>
        </div>
        <div class="download_div">
            <div class="download_div2"><img src="${sessionScope.downloads.get(2).getImage()}"></div>
            <div class="download_div6"></div>
            <div class="download_div3">${sessionScope.downloads.get(2).getName()}</div>
            <div class="download_input"><a href="/excise1/download.do?fileName=idea插件.jar">立即下载</a></div>
            <div class="download_div4">大小:${sessionScope.downloads.get(2).getSize()} &nbsp;时间:2020-9-22  星级：<img src="images/星等.png"></div>
            <div class="download_div7">${sessionScope.downloads.get(2).getDescription()}</div>
        </div>
        <div class="download_div">
            <div class="download_div2"><img src="${sessionScope.downloads.get(3).getImage()}"></div>
            <div class="download_div6"></div>
            <div class="download_div3">${sessionScope.downloads.get(3).getName()}</div>
            <div class="download_input"><a href="/excise1/download.do?fileName=论文.doc">立即下载</a></div>
            <div class="download_div4">大小:${sessionScope.downloads.get(3).getSize()} &nbsp;时间:2020-9-22  星级：<img src="images/星等.png"></div>
            <div class="download_div7">${sessionScope.downloads.get(3).getDescription()}</div>
        </div>
        <div class="download_div">
            <div class="download_div2"><img src="${sessionScope.downloads.get(4).getImage()}"></div>
            <div class="download_div6"></div>
            <div class="download_div3">${sessionScope.downloads.get(4).getName()}</div>
            <div class="download_input"><a href="/excise1/download.do?fileName=音乐.mp3">立即下载</a></div>
            <div class="download_div4">大小:${sessionScope.downloads.get(4).getSize()} &nbsp;时间:2020-9-22  星级：<img src="images/星等.png"></div>
            <div class="download_div7">${sessionScope.downloads.get(4).getDescription()}</div>
        </div>
        <div class="download_div">
            <div class="download_div2"><img src="${sessionScope.downloads.get(5).getImage()}"></div>
            <div class="download_div6"></div>
            <div class="download_div3">${sessionScope.downloads.get(5).getName()}</div>
            <div class="download_input"><a href="/excise1/download.do?fileName=电影.mp4">立即下载</a></div>
            <div class="download_div4">大小:${sessionScope.downloads.get(5).getSize()} &nbsp;时间:2020-9-22  星级：<img src="images/星等.png"></div>
            <div class="download_div7">${sessionScope.downloads.get(5).getDescription()}</div>
        </div>
    </div>
</form>
</body>
</html>
