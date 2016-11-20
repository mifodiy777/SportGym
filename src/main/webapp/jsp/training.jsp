<%--
  Created by IntelliJ IDEA.
  User: Кирилл
  Date: 19.11.2016
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
    $(document).ready(function () {

        $('.datetimepicker').datetimepicker({
            locale: 'ru'
        });

        $("#addTrainigForm").validate({
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    success: function (html) {
                        $("#trainingTable").DataTable().ajax.reload(null, false);
                        showSuccessMessage(html);
                        closeForm();
                        return false;
                    },
                    error: function (xhr) {
                        if (xhr.status == 409) {
                            showErrorMessage(xhr.responseText);
                        }
                    }
                });
            },
            errorPlacement: function (error, element) {
                validPlaceError(error, element);
            },
            success: function (label, element) {
                validPlaceSuccess(label, element);
            }
        });

        $("#addTrainigForm").submit(function (e) {
            e.preventDefault();
            $(this).valid();
            return false;
        });

    })
</script>
<div class="panel panel-default">
    <div class="panel-heading">
        <p class="panel-title">Добавление тренировки</p>
    </div>
    <div class="panel-body">
        <div class="col-md-8">
            <form:form modelAttribute="trainig" id="addTrainigForm" method="post" action="saveTrainig">
                <form:hidden path="id"/>
                <form:hidden path="user"/>
                <form:hidden path="version"/>
                <div class="form-group">
                    <label class="control-label">Тип: </label>
                    <form:select path="type" items="${trainigType}" itemLabel="name" cssClass="required form-control"/>
                    <span class="help-block"></span>
                </div>
                <div class="form-group">
                    <label class="control-label">Дата/Время тренировки: </label>
                    <div class='input-group date datetimepicker'>
                        <form:input path="targetDate" id="targetDate" cssClass="form-control"/>
                        <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label">Дистанция/м: </label>
                    <form:input path="distance" id="distance" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <label class="control-label">Время/м: </label>
                    <form:input path="time" id="time" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <label class="control-label">Количество/ед: </label>
                    <form:input path="count" id="count" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <label class="control-label">Подходы/шт: </label>
                    <form:input path="attempt" id="attempt" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <label class="control-label">Вес/кг: </label>
                    <form:input path="weight" id="weight" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <label class="control-label">Дата/Время тренировки: </label>
                    <div class='input-group date datetimepicker' id='datetimepicker'>
                        <form:input path="notificate" id="notificate" cssClass="form-control"/>
                        <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-success">Сохранить</button>
                    <button type="button" onclick="closeForm()" class="btn btn-default">Отмена</button>
                </div>
            </form:form>
        </div>
    </div>
</div>
