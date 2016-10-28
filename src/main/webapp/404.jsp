<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%--
  Created by IntelliJ IDEA.
  User: velievvm
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet"/>
    <link type="text/css" href="<c:url value='/css/sportgym.css'/>" rel="stylesheet"/>
    <script type="text/javascript" src="<c:url value='/js/jquery-3.1.1.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/bootstrap.min.js'/>"></script>
    <style>
        body {
            background-color: #F3F3F3;
            background-repeat: no-repeat;
            background-position: center;
        }

        .center-block {
            margin-top:15%;
            cursor:pointer;
        }

    </style>
</head>


<body>
<div id="wrap">
    <div class="container">

        <img class="center-block" src="<c:url value="/img/404_end.png"/>" alt="Ошибка 404"
             onclick="window.history.back();">


    </div>
    <jsp:include page="footer.jsp"/>
