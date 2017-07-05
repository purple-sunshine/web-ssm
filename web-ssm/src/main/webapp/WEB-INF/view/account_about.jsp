<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>注册新用户</title>
    <script type="text/javascript" src="${contextPath}/resources/js/jquery/1.9.1/jquery.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/js/layer/layer.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/js/login/login.js"></script>
    <script type="text/javascript">
        var basePath = "${contextPath}";
    </script>
</head>
<body>
<div>Make You Happy welcome you to be our family</div>
<div>
    <form id="useInfo">
        *帐号  ：<input type="text" name="loginName"><br/>
        *密码  ：<input type="password" name="password"><br/>
        *昵称  ：<input type="text" name="userName"><br/>
        *性别  ：<input type="text" name="sex"><br/>
        *手机号:<input type="text" name="phone"><br/>
        <input type="button" onclick="saveAccount()" value="保存">
        <input type="reset" value="重置">
    </form>
</div>
</body>
</html>
