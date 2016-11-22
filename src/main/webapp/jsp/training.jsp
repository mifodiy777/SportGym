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
            locale: 'ru',
            minDate: "moment"
        });

        $("#addTrainingForm").validate({
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

        $("#addTrainingForm").submit(function (e) {
            e.preventDefault();
            $(this).valid();
            return false;
        });

    })
</script>
<div class="panel panel-default">
    <div class="panel-heading">
        <p class="panel-title">Добавление тренировки: ${training.type.name}</p>
    </div>
    <div class="panel-body">
        <div class="col-md-8">
            <form:form modelAttribute="training" id="addTrainingForm" method="post" action="saveTraining">
                <form:hidden path="id"/>
                <form:hidden path="user"/>
                <form:hidden path="type"/>
                <form:hidden path="complete"/>
                <form:hidden path="version"/>
                <div class="form-group">
                    <label class="control-label">Дата/Время тренировки: </label>
                    <form:input path="targetDate" id="targetDate" cssClass="required form-control datetimepicker"/>
                    <span class="help-block"></span>
                </div>
                <div class="form-group">
                    <label class="control-label">Напоминание: </label>
                    <div class="btn-group" data-toggle="buttons">
                        <label class="btn btn-default active">
                            <input type="radio" name="alarm" autocomplete="off" value="h1" checked> За час
                        </label>
                        <label class="btn btn-default">
                            <input type="radio" name="alarm" autocomplete="off" value="h3"> За 3 часа
                        </label>
                        <label class="btn btn-default">
                            <input type="radio" name="alarm" autocomplete="off" value="h6"> За 6 часов
                        </label>
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
