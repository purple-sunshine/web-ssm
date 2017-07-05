<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%--<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">--%>
    <title>登录</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/public.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/login.css" />
     <script type="text/javascript">
        var basePath = "${contextPath}";
    </script>
</head>
<style type="text/css">
    .login-yzm{
        width: 160px;
        margin-right: 15px;
    }

    .codeA{
        width: 116px;
        height: 40px;
        display: inline-block;
    }
    .verify-code{
        display: none;
    }
    .mar-b15{margin-bottom: 7%;}
</style>

<body>
<div class="box">
    <div class="home_logo">
        <img src="${contextPath}/resources/img/logo_01.jpg" />
    </div>
    <div class="login-box">
        <div class="login-title">
            <h1>MakeYouHappy</h1>
        </div>
        <form id="loginForm" action="${contextPath}/loginHandler/loginInfo" method="post">
            <input type="hidden" value="${sessionScope.tryLoginNum}" id="tryLoginNum"/>
            <div class="form-group">
                <div class="col-xs-12 pad-lr40 mar-b15">
                    <div class="input-group">
                        <span class="input-group-addon login-icon"><span class="glyphicon glyphicon-user"></span></span>
                        <input type="text" id="loginName" name="loginName" class="form-control login-input"
                               placeholder="请输入用户名" value="" >
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12 pad-lr40 mar-b15">
                    <div class="input-group">
                        <span class="input-group-addon login-icon"><span class="glyphicon glyphicon-lock"></span></span>
                        <input type="password" id="password" name="password" class="form-control login-input"
                               placeholder="请输入密码" value="" >
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12 pad-lr40 mar-b15 verify-code" id="vcode">
                    <div class="login-yzm">
                        <input type="text" id="identifyingCode" name="identifyingCode" class="form-control bor0" placeholder="输入验证码" value="" >
                    </div>
                   <div class="code" id="checkCode" onClick="reloadCaptcha()" title="点击图片获取新的验证码" alt="加载中..."><img src="${contextPath}/captcha.jsp" id="captchaImg" style="width: 100%;height:100%;"/></div>
                   <%--<a href="javascript:reloadCaptcha();" class="codeA"><img src="${contextPath}/captcha.jsp" id="captchaImg" style="width: 100%;"/><span style="width: 100%;">看不清，换一张</span></a>--%>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12 distance">
                    <input type="submit" class="btn log-on cardioadd-submit"  value="login" id="submitBtn"/>
                    <input type="reset" class="btn log-empty" value="reset"/>
                </div>
            </div>
           <div class="login_b">
               <a href="">忘记密码</a>||<a href="${contextPath}/loginHandler/signPage" >注册成为新用户</a>;
           </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="${contextPath}/resources/js/jquery-2.2.0.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/layer/layer.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/jquery/form/jquery.form.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/login/login.js"></script>
<script type="text/javascript">
    function reloadCaptcha() {
        var imgUrl = "${contextPath}/captcha.jsp?_=" + new Date().getTime();
        $("#captchaImg").attr("src", imgUrl);
    }

    var tryLoginNum=$('#tryLoginNum').val();
    if(tryLoginNum > 2){
        $('#vcode').removeClass("verify-code");
    }
</script>
</body>
</html>
