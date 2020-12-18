let userName_correct
let chrName_correct
let email_correct
let province_correct
let city_correct
let password_correct
let passwordOk_correct

$(document).ready(function () {

    //刚进入页面时从数据库中加载出省份信息
    $.ajax({
        type: "post",
        url: "provinceCity.do",
        data: {province: "getProvince"},
        data_Type: "json",
        success: function (response) {
            let list = JSON.parse(response);
            $("#province").empty();
            $("#province").append($("<option>").val("").text("请选择省份"));
            for (let index = 0; index < list.length; index++) {
                let option = $("<option>").val(list[index].id)
                    .text(list[index].province);
                $("#province").append(option);
            }
        }
    })


    $("#province").change(function () {
        if($(this).val() == ""){
            $("#provinceError").text("请选择省份");
            $("#provinceOk").text("");
            province_correct = false;
            return;
        }
        $("#provinceError").text("");
        $("#provinceOk").text("√");
        province_correct = true;
        $("#city").empty();
        $("#city").append($("<option>").val("").text("请选择城市"));

        let getCity = $("#province").val();
        $.ajax({
            type: "post",
            url: "provinceCity.do",
            data: {province: getCity},
            data_Type: "json",
            success:function (response) {
                let list = JSON.parse(response);
                $("#city").empty();
                $("#city").append($("<option>").val("").text("请选择城市"));
                for (let index = 0; index < list.length; index++) {
                    let option = $("<option>").val(list[index].id)
                        .text(list[index].city);
                    $("#city").append(option);
                }
            }
        })

    })

    //表单验证
    $("#userName").blur(function () {
        if($(this).val() == ""){
            $("#userNameError").text("用户名不能为空");
            $("#userNameOk").text("");
            userName_correct = false;
            return;
        }
        if(/^[a-zA-Z][a-zA-Z\d]{3,15}$/.test(this.value) == false){
            $("#userNameError").text("用户名只能以字母开头，只能含有数字字母，长度为4-16");
            $("#userNameOk").text("");
            userName_correct = false;
            return;
        }
        $.ajax({
            type:"post",
            url:"checkExist.do",
            data:{userName:$(this).val()},
            data_Type:"json",
            success:function (response) {
                let json = JSON.parse(response);
                if(json.code == 0){
                    $("#userNameOk").text("√");
                    $("#userNameError").text("");
                    userName_correct = true;
                }else {
                    $("#userNameOk").text("");
                    $("#userNameError").text("用户名已存在");
                    userName_correct = false;
                }
            }
        })
    })

    $("#chrName").blur(function () {
        if($(this).val() == ""){
            $("#chrNameError").text("真实姓名不能为空");
            $("#chrNameOk").text("");
            chrName_correct = false;
            return;
        }
        if(/^[\u4e00-\u9fa5]{2,4}$/.test(this.value) == false){
            $("#chrNameError").text("真实姓名必须为汉字，长度2-4");
            chrName_correct = false;
        }else {
            chrName_correct = true;
            $("#chrNameOk").text("√");
            $("#chrNameError").text("");
        }
    })

    $("#email").blur(function () {
        if($(this).val() == ""){
            $("#emailError").text("邮箱不能为空");
            $("#emailOk").text("");
            email_correct = false;
            return
        }
        if(/^([a-zA-Z\d_-]+\.)*[a-zA-Z\d_-]+@[a-zA-Z\d-_]+(\.[a-zA-Z\d-_]+)+$/.test(this.value) ==false){
            $("#emailError").text("邮箱格式不对");
            email_correct = false;
        }else {
            email_correct = true;
            $("#emailOk").text("√");
            $("#emailError").text("");
        }
    })

    $("#province").blur(function () {
        if($(this).val() == ""){
            $("#provinceError").text("请选择省份");
            $("#provinceOk").text("");
            province_correct = false;
        }else {
            $("#provinceError").text("");
            $("#provinceOk").text("√");
            province_correct = true;
        }
    })

    $("#city").blur(function () {
        if($(this).val() == ""){
            $("#cityError").text("请选择城市");
            $("#cityOk").text("");
            city_correct = false;
        }else {
            $("#cityError").text("");
            $("#cityOk").text("√");
            city_correct = true;
        }
    })

    $("#password").blur(function (){
        if($(this).val() == ""){
            $("#passwordError").text("密码不能为空");
            $("#password_ok").text("");
            password_correct = false;
            return;
        }
        if(/^[_a-zA-Z\d]{6,16}$/.test(this.value) == false){
            $("#passwordError").text("密码必须为数字字母下划线，长度6-16");
            password_correct = false;
        }else {
            password_correct = true
            $("#passwordError").text("");
            $("#password_ok").text("√");
        }
    })

    $("#passwordOk").blur(function () {
        if($(this).val() == $("#password").val() && $(this).val().length >= 6 ){
            $("#passwordOkError").text("");
            $("#password_sure").text("√")
            passwordOk_correct = true;
        }else {
            $("#passwordOkError").text("密码或长度不一致");
            $("#password_sure").text("");
            passwordOk_correct = false;
        }
    })

    $("#register").click(function () {
        let data = {
            userName:$("#userName").val(),
            chrName:$("#chrName").val(),
            email:$("#email").val(),
            province:$("#province").text(),
            city:$("#city").text(),
            password:$("#password").val(),
            passwordOk:$("#passwordOk").val()
        };
        if(userName_correct && chrName_correct && email_correct && province_correct && city_correct && province_correct && passwordOk_correct){
            $.ajax({
                type:"post",
                url:"register.do",
                data:data,
                data_Type:"json",
                success:function (response) {
                    let json = JSON.parse(response);
                    if(json.code == 0){
                        alert("注册成功");
                        window.location.href = "login.html";
                    }
                }
            })
        }else {
            $("#userName").blur();
            $("#chrName").blur();
            $("#email").blur();
            $("#province").blur();
            $("#city").blur();
            $("#password").blur();
            $("#passwordOk").blur();
        }
    })


})

