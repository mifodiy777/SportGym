<%--
  Created by IntelliJ IDEA.
  User: velievvm
  Date: 16.07.15
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html>
<head>
    <title>Регистрация</title>
    <link type="text/css" href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet"/>
    <link type="text/css" href="<c:url value='/css/sportgym.css'/>" rel="stylesheet"/>
    <script type="text/javascript" src="<c:url value='/js/jquery-3.1.1.min.js'/>"></script>
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
<div class="container">
    <div class="row vertical-offset-100">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Регистрация</h3>
                </div>

                <form action="saveProfile" method="post">
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="control-label">Фамилия: </label>
                            <input type="text" name="surname" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Имя: </label>
                            <input type="text" name="name" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Отчество: </label>
                            <input type="text" name="patronymic" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="control-label">E-mail: </label>
                            <input type="email" name="email" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Телефон: </label>
                            <input type="tel" name="phone" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Пароль: </label>
                            <input type="password" name="password" class="form-control">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-success">Зарегистрироваться</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>