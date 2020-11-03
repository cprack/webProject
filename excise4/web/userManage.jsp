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
    <link href="./css/userManage.css" rel="stylesheet"/>
    <script src="js/jquery-3.5.1.min.js"></script>
    <script src="js/userManage.js"></script>
</head>
<body onload="init()">
<div id="fade" class="black_overlay" onclick="CloseDiv('MyDiv','fade')"></div>
<div id="MyDiv" class="white_content">
    <div style="height : 20px;">
        <div style="font-size: 17px;padding-left: 95%;width: 20px;height: 20px;color: #dcd9d9; cursor:pointer;"><span  title="点击关闭" onclick="CloseDiv('MyDiv','fade')">x</span></div>
        <span class="register" id="op">用户添加</span>
        <form id="registerForm">
            <p>
                <input type="text" id="user_Name" placeholder="请输入账号" autocomplete="off">
                <span class="sure" id="userName_Ok"></span>
                <span class="error" id="userName_Error"></span>
            </p>
            <p>
                <input type="text" id="chr_Name" placeholder="请输入真实姓名" autocomplete="off">
                <span class="sure" id="chrName_Ok"></span>
                <span class="error" id="chrName_Error"></span>
            </p>
            <p>
                <input type="text" id="email_" placeholder="请输入邮箱" autocomplete="off">
                <span class="sure" id="emailOk_"></span>
                <span class="error" id="emailError_"></span>
            </p>
            <p>
                <select id="province_" autocomplete="off">
                    <option value>请选择省份</option>
                </select>
                <span class="sure" id="provinceOk"></span>
                <span class="error" id="provinceError"></span>
            </p>
            <p>
                <select id="city" autocomplete="off">
                    <option value>请选择城市</option>
                </select>
                <span class="sure" id="cityOk"></span>
                <span class="error" id="cityError"></span>
            </p>
            <p>
                <input type="password" id="password" placeholder="请输入密码" autocomplete="off">
                <span class="sure" id="password_ok"></span>
                <span class="error" id="passwordError"></span>
            </p>
            <p>
                <input type="password" id="passwordOk" placeholder="请再次输入密码" autocomplete="off">
                <span class="sure" id="password_sure"></span>
                <span class="error" id="passwordOkError"></span>
            </p>
            <p><input type="button" value="确        定" id="ok"></p>
        </form>
    </div>
</div>
<div id="main_top">
    <div id="main_topLeft"><img src="images/main_left.png"></div>
    <div id="main_topRight1">欢迎你：<span id="role">${sessionScope.get("role")}</span><a class="a1" href="quit.do?id=quit">【安全退出】 </a ></div>
    <div id="main_topRight2">
        <div class="main_class2"></div>
        <div class="main_class1"><a href="main.jsp" class="a2">首页</a></div>
        <div class="main_class1"><a href="downloadRequest.do?id=jump_download" class="a2">资源下载</a></div>
        <div class="main_class1"><a href="userManage.jsp" class="a2">用户管理</a></div>
        <div class="main_class1"><a href="resourcesManage.jsp" class="a2">资源管理</a></div>
        <div class="main_class1"><a href="personal.jsp" class="a2">个人中心</a></div>
    </div>
</div>
<div class="main_center">
    <div class="crud">
        <form id="searchInput">
            <input type="text" id="userName" placeholder="输入用户名" autocomplete="off">
            <input type="text" id="chrName" placeholder="输入姓名" autocomplete="off">
            <input type="text" id="email" placeholder="输入邮箱" autocomplete="off">
            <input type="text" id="province" placeholder="输入省份" autocomplete="off">
            <input type="text" id="cities" placeholder="输入城市" autocomplete="off">
        </form>
        <div id="Button">
            <a href="#" id="searchBt">查找</a>
            <a href="#" id="clearBt">清空</a>
            <a href="#" id="addBt">增加</a>
            <a href="#" id="deleteBt">删除</a>
            <a href="#" id="updateBt">修改</a>
        </div>
    </div>
    <div id="table">
        <table>
            <input id="action" name="action" type="text" hidden>
            <thead>
                <tr>
                    <th><input type="checkbox" id="checkAll" class="checkAll" title="选择"/></th>
                    <th width="310px" id="sortByUserName" data="userName">用户名</th>
                    <th width="220px">中文名</th>
                    <th width="320px">邮箱</th>
                    <th width="140px" id="sortByProvince" data="province">省份</th>
                    <th width="140px">城市</th>
                    <th width="220px">操作</th>
                </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>
    <div id="bm">
        <div class="paging">
            <div class="pageSize">每页
                <select id="pageSize">
                    <option>1</option>
                    <option>5</option>
                    <option>10</option>
                    <option>20</option>
                </select>，共
                <span id="total"></span>条数据&emsp;
                <span id="pageNumber">1</span>页/<span id="pageCount"></span>页
            </div>
        </div>
        <div id="pageNav" class="pageNav">

        </div>
    </div>
</div>

</body>
</html>
