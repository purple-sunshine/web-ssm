<%@ page trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
    <title>500</title>
    <style type="text/css">
        body {
            padding: 0;
            margin: 0 auto;
            text-align: center;
            background-color: #79CFE8;
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
    <img src="${contextPath}/resources/img/500.png" ismap="ismap" width="900" height="500" usemap="#servicer" />
    <map id="servicer" name="servicer">
        <area shape="rect" coords="714,253,613,223" href="index.jsp" alt="500" />
    </map>
</div>
</body>
</html>

