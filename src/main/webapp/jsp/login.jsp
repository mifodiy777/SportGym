<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html>
<head>
    <title>Авторизация</title>
    <link rel="icon" href="../img/ico-logo.ico" type="image/x-icon" />
    <link type="text/css" href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet"/>
    <link type="text/css" href="<c:url value='/css/sportgym.css'/>" rel="stylesheet"/>
    <script type="text/javascript" src="<c:url value='/js/jquery.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/bootstrap.min.js'/>"></script>
    <style>

        body {
            /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#f8ffe8+0,e3f5ab+33,b7df2d+100;Green+3D+%234 */
            background: #f8ffe8; /* Old browsers */
            background: -moz-linear-gradient(top, #f8ffe8 0%, #e3f5ab 33%, #b7df2d 100%); /* FF3.6-15 */
            background: -webkit-linear-gradient(top, #f8ffe8 0%, #e3f5ab 33%, #b7df2d 100%); /* Chrome10-25,Safari5.1-6 */
            background: linear-gradient(to bottom, #f8ffe8 0%, #e3f5ab 33%, #b7df2d 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f8ffe8', endColorstr='#b7df2d', GradientType=0); /* IE6-9 */
        }

        .vertical-offset-100 {
            padding-top: 150px;
        }

        input:-webkit-autofill {
            -webkit-box-shadow: inset 0 0 0 50px #fff !important; /* Цвет фона */
            -webkit-text-fill-color: #999 !important; /* цвет текста */
            color: #999 !important; /* цвет текста */
        }

    </style>
</head>
<body>
<div id="login-box" class="container">
    <div class="row vertical-offset-100">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Войдите</h3>
                </div>
                <div class="panel-body">
                    <form accept-charset="UTF-8" role="form" id='loginForm' name='loginForm'
                          action="<c:url value='/j_spring_security_check' />" method='POST'>
                        <fieldset>
                            <div class="form-group <c:if test="${not empty error}">has-error</c:if>">
                                <input class="form-control" placeholder="E-mail" name="username" type="email">
                            </div>
                            <div class="form-group <c:if test="${not empty error}">has-error</c:if>">
                                <input class="form-control" placeholder="Пароль" name="password" type="password"
                                       value="" required="">
                            </div>
                            <button type="submit" class="btn btn-success btn-block">Войти</button>
                            <button type="reset" class="btn btn-default btn-block">Сбросить</button>
                        </fieldset>
                    </form>
                </div>
                <c:if test="${not empty error}">
                    <div class="panel-footer">
                        <div id="loginError">${error}</div>
                    </div>
                </c:if>
                <c:if test="${not empty msg}">
                    <div class="panel-footer">
                        <div id="loginMsg">${msg}</div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>