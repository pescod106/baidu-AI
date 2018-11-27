<%--
  Created by IntelliJ IDEA.
  User: pescod
  Date: 2018/11/26
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Baidu Aip</title>
</head>
<body>
<h2>Hello Word</h2>
<a href="aip/ocr/generalOcr">GeneralOcr</a>
</body>
<script type="text/javascript">
    $(function () {
        var oContinue=1;
        $.Tipmsg.r = null;
        $("#form").Validform({
            tiptype: function (msg, o, cssctl) {
                if (o.type != 1) {//1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态
                    ground.show(msg);
                }
            },
            tipSweep: true,
            callback: function (data) {
                if(oContinue){
                    oContinue=0;
                    ground.jump("${nextStep}", "{'siteKey':'${siteKey}','username':" + $('#username').val() + "}");
                }
                setTimeout(function(){
                    oContinue=1;
                },5000);
            }
        });
    })
</script>
</html>
