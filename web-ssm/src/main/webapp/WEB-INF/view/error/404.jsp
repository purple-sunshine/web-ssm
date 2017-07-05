<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
    <title>404</title>
    <style type="text/css">
        body {
            padding: 0;
            margin: 0 auto;
            text-align: center;
            background-color: white;
        }

        area {
            outline: none;
        }

        .map-total {
            width: 900px;
            height: 500px;
            margin-left: -450px;
            margin-top: -250px;
            left: 50%;
            top: 40%;
            position: absolute;
        }
    </style>
</head>
<body>
<div class="map-total">
    <img src="${contextPath}/resources/img/404.png" ismap="ismap" width="900" height="500" usemap="#lose" />
    <map id="lose" name="lose">
        <area shape="rect" coords="733,308,632,278" href="index.jsp" alt="404" />
    </map>
</div>
</body>
</html>