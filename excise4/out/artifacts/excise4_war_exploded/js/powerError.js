let x = 10;
function run(){
    if(x == 0){
        location.href = "main.jsp";
    }
    // 获取到的是span标签的对象
    const span = document.getElementById("time");
    // 获取span标签中间的文本
    span.innerHTML = x;
    x--;
    // 再让run方法执行呢，一秒钟执行一次
    window.setTimeout("run()", 1000);
}
