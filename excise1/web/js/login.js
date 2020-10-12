function changeCode() {
    const codeImg = document.getElementById("img");
    codeImg.src = "ImageServlet.do?t="+Math.random();
}
