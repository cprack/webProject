
function ShowDiv(show_div,bg_div) {
    document.getElementById(show_div).style.display = "block";
    document.getElementById(bg_div).style.display = "block";
}
function CloseDiv(show_div,bg_div){
    document.getElementById(show_div).style.display = "none";
    document.getElementById(bg_div).style.display = "none";
}
//
// let checkAll = function () {
//     $("tbody input:checkbox").attr("checked",this.checked);
//
// }
// let sortRecord = function () {
//
// }
// let navigate = function () {
//
// }
let pageSize;
let pageCount;
let pageNumber;
let userName_correct;
let chrName_correct;
let email_correct;
let province_correct;
let city_correct;
let password_correct;
let passwordOk_correct;

function tbody(rows,pageSize,pageNumber,total) {
    let str;
    let start = parseInt((pageNumber-1) * pageSize);
    let end = parseInt(pageSize) + parseInt((pageNumber-1)*pageSize);
    if(total < end){
        end = total;
    }
    $("tbody").empty();
    for(let i = start ;i < end;i++){
        str = str + "<tr><td><input type='checkbox' class='checkAll' value=" + rows[i].userName+"></td>";
        str = str + '<td width="310px">' + rows[i].userName + '</td>';
        str = str + '<td width="220px">' + rows[i].chrName + '</td>';
        str = str + '<td width="320px">' + rows[i].email + '</td>';
        str = str + '<td width="140px">' + rows[i].province + '</td>';
        str = str + '<td width="140px">' + rows[i].city + '</td>';
        str = str + '<td width="220px"><a href="#" id="btnDelete" name="' + rows[i].userName + '">删除&emsp;</a>';
        str = str + '<a href="#" id="btnUpdate">修改</a></td>';
        str = str + '</tr>';
        $("tbody").append(str);
        str = "";
    }
}
function page(pageSize,pageNumber,pageCount,total) {
    let str = "";
    let i;
    let number= parseInt(pageNumber);
    let count = parseInt(pageCount);
    $("#pageNav").empty();
    if(total <= pageSize){
        str = str + "<strong>1</strong>"
    }else {
        if(number == 1){
            str = str + "<strong>"+1+"</strong>"
            for(i = 2;i <= count;i++){
                if(i > 10){
                    break;
                }
                str = str + '<a href="userManage.jsp?page=' + i +'&size='+pageSize+'">' + i +'</a>';
            }
            if(i > 10){
                str = str + '<a href="userManage.jsp?page='+count+'&size='+pageSize+'">'+'...'+count+'</a>';

            }
            if(number != count) {
                str = str + '<a href="userManage.jsp?page=' + (parseInt(number) + 1) + '&size=' + pageSize + '">' + '下一页</a>';
            }
        }else if(number > 1 && number <= 6){
            str = str + '<a href="userManage.jsp?page='+ parseInt(number - 1)+ '&size='+pageSize+'">上一页</a>';
            for(i = 1;i <= number;i++){
                if(number == i){
                    str = str + "<strong>"+i+"</strong>";
                }else {
                    str = str + '<a href="userManage.jsp?page=' + i +'&size='+pageSize+'">' + i +'</a>';
                }
            }
            for(i;i <= count;i++){
                if(i > 10){
                    break;
                }
                str = str + '<a href="userManage.jsp?page=' + i +'&size='+pageSize+'">' + i +'</a>';
            }
            if(i > 10){
                str = str + '<a href="userManage.jsp?page='+count+'&size='+pageSize+'">'+'...'+count+'</a>';

            }
            if(number != count){
                str = str + '<a href="userManage.jsp?page='+(parseInt(number) + 1)+'&size='+pageSize+'">'+'下一页</a>';
            }
        }else if(number > 6 && number <= count - 5){
            str = str + '<a href="userManage.jsp?page='+ (parseInt(number)-1)+'&size='+pageSize+'">上一页</a>';
            str = str + '<a href="userManage.jsp?page=1'+'&size='+pageSize+'">'+'1 ...</a>';
            for(i = number - 5;i < number;i++){
                str = str + '<a href="userManage.jsp?page='+i+'&size='+pageSize+'">'+i+'</a>';
            }
            str = str + "<strong>"+number+"</strong>";
            for(i = number + 1;i < number + 5;i++){
                str = str + '<a href="userManage.jsp?page='+i+'&size='+pageSize+'">'+i+'</a>';
            }
            str = str + '<a href="userManage.jsp?page='+count+'&size='+pageSize+'">'+'...'+count+'</a>';

        }else if(number > count - 5 && number < count){
            str = str + '<a href="userManage.jsp?page='+ parseInt(number - 1)+'&size='+pageSize+'">上一页</a>';
            str = str + '<a href="userManage.jsp?page=1'+'&size='+pageSize+'">'+'1 ...</a>';
            for(i = number - 4;i < number;i++){
                str = str + '<a href="userManage.jsp?page='+i+'&size='+pageSize+'">'+i+'</a>';
            }
            str = str + "<strong>"+number+"</strong>";
            for(i = number + 1;i <= count;i++){
                str = str + '<a href="userManage.jsp?page='+i+'&size='+pageSize+'">'+i+'</a>';
            }
            str = str + '<a href="userManage.jsp?page='+(parseInt(number) + 1)+'&size='+pageSize+'">'+'下一页</a>';
        }else if(number ==  count){
            str = str + '<a href="userManage.jsp?page='+ parseInt(number - 1)+'&size='+pageSize+'">上一页</a>';
            str = str + '<a href="userManage.jsp?page=1'+'&size='+pageSize+'">'+'1 ...</a>';
            for(i = number - 9;i < number;i++){
                str = str + '<a href="userManage.jsp?page='+i+'&size='+pageSize+'">'+i+'</a>';
            }
            str = str + "<strong>"+number+"</strong>";
        }
    }
    $("#pageNav").append(str);
}
function init(){
    
    $.ajax({
        type:"post",
        url:"userManage.do",
        data:{INIT:"init"},
        dataType:"json",
        success:function (response) {
            let userData = response;
            let rows = userData.rows;
            let total = userData.total;
            let url = location.search;
            let str1 = url.substr(1);
            let str2 =str1.split("&");
            if(str2 == ""){
                pageNumber = 1;
                pageSize = 10;
            }else {
                let str3 = str2[0].split("=");
                let str4 = str2[1].split("=");
                pageNumber = str3[1];
                pageSize = str4[1];
            }
            pageCount = Math.ceil(total / pageSize);
            $("#pageNumber").text(pageNumber);
            $("#pageCount").text(pageCount);
            $("#pageSize").val(pageSize);
            $("#total").text(total);
            $("tbody").empty();
            tbody(rows,pageSize,pageNumber,total);
            page(pageSize,pageNumber,pageCount,total);
            $('tbody tr:even').addClass('tr_even');
            $('tbody tr:odd').addClass('tr_odd');

        }
    })
}

$(document).ready(function () {

    $("#pageSize").blur(function () {
        $.ajax({
            type:"post",
            url:"userManage.do",
            data:{INIT:"select"},
            dataType:"json",
            success:function (response) {
                let userData = response;
                let total = userData.total;
                let url = location.search;
                pageSize = $("#pageSize").val();
                let str1 = url.substr(1);
                let str2 =str1.split("&");
                if(str2 == ""){
                    pageNumber = 1;
                }else {
                    let str3 = str2[0].split("=");
                    pageNumber = str3[1];
                }
                pageCount = Math.ceil(total / pageSize);
                location.href = "userManage.jsp?page=1"+"&size="+pageSize;
            }
        })
    })

    $("tbody").on("mouseover","tr",function () {
        $(this).addClass('tr_hover');//通过jQuery控制实现鼠标悬停上的背景色
    });
    $("tbody").on("mouseout","tr",function () {
        $(this).removeClass('tr_hover');//通过jQuery控制实现鼠标悬停上的背景色
    });
    $("#checkAll").click(function () {
        if($("#checkAll").prop('checked')){
            $("tbody tr input:checkbox").prop("checked",true);
        }else {
            $("tbody tr input:checkbox").prop("checked",false);
        }
    })
    $("table").on("click","#btnUpdate",function () {
        const userName = $(this).closest("tr").find("td").eq(1).text();//获取用户名
        const chrName = $(this).closest("tr").find("td").eq(2).text();//获取中文名
        const email = $(this).closest("tr").find("td").eq(3).text();//获取邮箱
        ShowDiv("MyDiv","fade");
        $("#op").text("用户修改");
        $("#user_Name").val(userName);
        $("#user_Name").prop("disabled","disabled");
        $("#chr_Name").val(chrName);
        $("#email_").val(email);
        $.ajax({
            type: "post",
            url: "provinceCity.do",
            data: {province: "getProvince"},
            data_Type: "json",
            success: function (response) {
                let list = JSON.parse(response);
                $("#province_").empty();
                $("#province_").append($("<option>").val("").text("请选择省份"));
                for (let index = 0; index < list.length; index++) {
                    let option = $("<option>").val(list[index].id)
                        .text(list[index].province);
                    $("#province_").append(option);
                }
            }
        })
    })
    $("#ok").click(function () {
        let url,title;
        title = $("#op").text();
        let data = {
            userName:$("#user_Name").val(),
            password: $("#password").val(),
            chrName:$("#chr_Name").val(),
            email:$("#email_").val(),
            province:$("#province_").val(),
            city:$("#city").val()
        }
        if(title == "用户添加"){
            url = "register.do";
        }else{
            url = "updateUser.do";
        }
        $.ajax({
            type: "post",
            url: url,
            data: data,
            data_Type: "json",
            success:function () {
                if(title == "用户添加"){
                    alert("添加成功!");
                }else {
                    alert("修改成功!");
                }
                location.href = "userManage.jsp";
            }
        })
    })
    $("table").on("click","#btnDelete",function () {
        let userName = $(this).attr("name");
        $.ajax({
            type:"post",
            url:"deleteUser.do",
            data:{ids:userName},
            dataType:"json",
            success:function () {
                alert("删除成功!");
                location.href = "userManage.jsp";
            }
        })
    })
    $("#clearBt").click(function () {
        $("#userName").val("");
        $("#chrName").val("");
        $("#email").val("");
        $("#province").val("");
        $("#cities").val("");
    })
    $("#addBt").click(function () {
        ShowDiv("MyDiv","fade");
        $("#op").text("用户添加");
        $("#user_Name").val("");
        $("#user_Name").prop("disabled","");
        $("#chr_Name").val("");
        $("#email_").val("");
        $.ajax({
            type: "post",
            url: "provinceCity.do",
            data: {province: "getProvince"},
            data_Type: "json",
            success: function (response) {
                let list = JSON.parse(response);
                $("#province_").empty();
                $("#province_").append($("<option>").val("").text("请选择省份"));
                for (let index = 0; index < list.length; index++) {
                    let option = $("<option>").val(list[index].id)
                        .text(list[index].province);
                    $("#province_").append(option);
                }
            }
        })
    })
    $("#deleteBt").click(function () {
        let len = $("tbody tr input:checkbox:checked").length;
        if(len == 0){
            alert("至少选择一项!");
            return;
        }
        let de = [];
        $("tbody tr input:checkbox:checked").each(function () {
            de.push($(this).val());
        })
        $.ajax({
            type:"post",
            url:"deleteUser.do",
            data:{ids:de.join(",")},
            dataType:"json",
            success:function () {
                alert("删除成功!");
                location.href = "userManage.jsp";
            }
        })
    })

    $("#province_").change(function () {
        if($(this).val() == ""){
            alert("请选择省份!");
            $("#provinceOk").text("");
            province_correct = false;
            return;
        }
        $("#provinceOk").text("√");
        province_correct = true;
        $("#city").empty();
        $("#city").append($("<option>").val("").text("请选择城市"));

        let getCity = $("#province_").val();
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
    $("#user_Name").blur(function () {
        if($(this).val() == ""){
            alert("用户名不能为空!");
            $("#userName_Ok").text("");
            userName_correct = false;
            return;
        }
        if(/^[a-zA-Z][a-zA-Z\d]{3,15}$/.test(this.value) == false){
            alert("用户名只能以字母开头，只能含有数字字母，长度为4-16!");
            $("#userName_Ok").text("");
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
                    $("#userName_Ok").text("√");
                    userName_correct = true;
                }else {
                    $("#userName_Ok").text("");
                    alert("用户名已存在!");
                    userName_correct = false;
                }
            }
        })
    })

    $("#chr_Name").blur(function () {
        if($(this).val() == ""){
            alert("真实姓名不能为空!");
            $("#chrName_Ok").text("");
            chrName_correct = false;
            return;
        }
        if(/^[\u4e00-\u9fa5]{2,4}$/.test(this.value) == false){
            alert("真实姓名必须为汉字，长度2-4!");
            chrName_correct = false;
        }else {
            chrName_correct = true;
            $("#chrName_Ok").text("√");
        }
    })

    $("#email_").blur(function () {
        if($(this).val() == ""){
            alert("邮箱不能为空!");
            $("#emailOk_").text("");
            email_correct = false;
            return
        }
        if(/^([a-zA-Z\d_-]+\.)*[a-zA-Z\d_-]+@[a-zA-Z\d-_]+(\.[a-zA-Z\d-_]+)+$/.test(this.value) ==false){
            alert("邮箱格式不对!");
            email_correct = false;
        }else {
            email_correct = true;
            $("#emailOk_").text("√");
        }
    })

    $("#province_").blur(function () {
        if($(this).val() == ""){
            alert("请选择省份!");
            $("#provinceOk").text("");
            province_correct = false;
        }else {
            $("#provinceOk").text("√");
            province_correct = true;
        }
    })

    $("#city").blur(function () {
        if($(this).val() == ""){
            alert("请选择城市!");
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
            alert("密码不能为空!");
            $("#password_ok").text("");
            password_correct = false;
            return;
        }
        if(/^[_a-zA-Z\d]{6,16}$/.test(this.value) == false){
            alert("密码必须为数字字母下划线，长度6-16!");
            password_correct = false;
        }else {
            password_correct = true
            $("#password_ok").text("√");
        }
    })

    $("#passwordOk").blur(function () {
        if($(this).val() == $("#password").val() && $(this).val().length >= 6 ){
            $("#password_sure").text("√")
            passwordOk_correct = true;
        }else {
            alert("密码或长度不一致");
            $("#password_sure").text("");
            passwordOk_correct = false;
        }
    })

    $("#register").click(function () {
        let data = {
            userName:$("#user_Name").val(),
            chrName:$("#chr_Name").val(),
            email:$("#email_").val(),
            province:$("#province_").val(),
            city:$("#city").val(),
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
                        alert("添加成功!");
                        window.location.href = "userManage.jsp";
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
    $("#searchBt").click(function () {
        let data = {
            userName:$("#userName").val(),
            chrName:$("#chrName").val(),
            email:$("#email").val(),
            province:$("#province").val(),
            city:$("#cities").val()
        }
        $.ajax({
            type:"post",
            url:"search.do",
            data:data,
            data_Type:"json",
            success:function (response) {
                let userData = JSON.parse(response);
                let rows = userData.rows;
                let total = userData.total;
                let url = location.search;
                let str1 = url.substr(1);
                let str2 =str1.split("&");
                if(str2 == ""){
                    pageNumber = 1;
                    pageSize = 10;
                }else {
                    let str3 = str2[0].split("=");
                    let str4 = str2[1].split("=");
                    pageNumber = str3[1];
                    pageSize = str4[1];
                }
                pageCount = Math.ceil(total / pageSize);
                $("#pageNumber").text(pageNumber);
                $("#pageCount").text(pageCount);
                $("#pageSize").val(pageSize);
                $("#total").text(total);
                $("tbody").empty();
                tbody(rows,pageSize,pageNumber,total);
                page(pageSize,pageNumber,pageCount,total);
                $('tbody tr:even').addClass('tr_even');
                $('tbody tr:odd').addClass('tr_odd');

            }
        })
    })
})