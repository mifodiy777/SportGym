<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: velievvm
  Date: 31.07.15
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet"/>
    <link type="text/css" href="<c:url value='/css/sportgym.css'/>" rel="stylesheet"/>
    <script type="text/javascript" src="<c:url value='/js/jquery.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/bootstrap.min.js'/>"></script>

    <style>
        body {
            background-color: #eee;
            background-size: 100%;
        }

        .vertical-and-horizontal-offset {
            padding-top: 20%;
        }
    </style>
</head>


<div class="container">
    <div class="vertical-and-horizontal-offset">
        <h1 style="text-align: center;">Доступ запрещен!</h1>
        <p style="color: #f3a02c; text-align: center">К сожалению, у Вас нет прав доступа к запрашиваемой странице</p>
        <p style="text-align: center;"><a onclick="window.history.back();">Вернуться на предыдущую страницу</a></p>
    </div>
</div>




