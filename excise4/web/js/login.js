function changeCode() {
    const codeImg = document.getElementById("img");
    codeImg.src = "createVerifyImage.do?t="+Math.random();
}

let userName_correct = false;
let password_correct = false;
let vCode_correct = false;

let xmlHttp;
//创建xmlHttpRequest对象
function createXmlHttp() {
    if(window.XMLHttpRequest){
        xmlHttp = new XMLHttpRequest();
    }else {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
}

//使用js实现ajax登录
function ajaxCheckLogin() {
    if(!userName_correct || !password_correct || !vCode_correct){
        $("#userName").blur();
        $("#password").blur();
        $("#vCode").blur();
        return;
    }
    let userName = document.getElementById("userName").value;
    let password = document.getElementById("password").value;
    let vCode = document.getElementById("vCode").value;
    let data = "userName="+userName+"&password="+password+"&vCode="+vCode;
    if(document.getElementById("autoLogin").checked){
        data += "&autoLogin=y";
    }
    createXmlHttp();//创建XMLHttpRequest对象
    xmlHttp.open("post", "ajaxLoginCheck.do", true);
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttp.send(data);
    xmlHttp.onreadystatechange = function () {
        if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
            let response = xmlHttp.responseText;
            let json = JSON.parse(response);
            if(json.code == 0){
                //登录成功
                window.location.href = "main.jsp";
            }else{
                //登录失败,显示返回的错误信息
                document.getElementById("checkError").innerText = json.info;
            }
        }
    }
}

$(document).ready(function (){
    $("#userName").blur(function () {
        if($(this).val() == ""){
            $("#userNameError").text("用户名不能为空");
        }else {
            $("#userNameError").text("");
            userName_correct = true;
        }
    });

    $("#password").blur(function () {
        if($(this).val() == ""){
            $("#passwordError").text("密码不能为空");
        }else {
            $("#passwordError").text("");
            password_correct = true;
        }
    });

    $("#vCode").blur(function () {
        if($(this).val() == ""){
            $("#vCodeError").text("验证码不能为空");
        }else {
            $("#vCodeError").text("");
            vCode_correct = true;
        }
    });
});
