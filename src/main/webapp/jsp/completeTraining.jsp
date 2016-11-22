<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
    $(document).ready(function () {

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
                <form:hidden path="targetDate"/>
                <form:hidden path="notificate"/>
                <form:hidden path="version"/>
                <input type="hidden" name="complete" value="true">
                <c:if test="${training.type.distance}">
                    <div class="form-group">
                        <label class="control-label">Дистанция: </label>
                        <form:input path="distance" id="distance" cssClass="required digits form-control"/>
                        <span class="help-block"></span>
                    </div>
                </c:if>
                <c:if test="${training.type.time}">
                    <div class="form-group">
                        <label class="control-label">Время: </label>
                        <form:input path="time" id="time" cssClass="required digits form-control"/>
                        <span class="help-block"></span>
                    </div>
                </c:if>
                <c:if test="${training.type.count}">
                    <div class="form-group">
                        <label class="control-label">Количество: </label>
                        <form:input path="count" id="count" cssClass="required digits form-control"/>
                        <span class="help-block"></span>
                    </div>
                </c:if>
                <c:if test="${training.type.attempt}">
                    <div class="form-group">
                        <label class="control-label">Попытки: </label>
                        <form:input path="attempt" id="attempt" cssClass="required digits form-control"/>
                        <span class="help-block"></span>
                    </div>
                </c:if>
                <c:if test="${training.type.weight}">
                    <div class="form-group">
                        <label class="control-label">Вес: </label>
                        <form:input path="weight" id="weight" cssClass="required  digits form-control"/>
                        <span class="help-block"></span>
                    </div>
                </c:if>
                <div class="form-group">
                    <button type="submit" class="btn btn-success">Сохранить</button>
                    <button type="button" onclick="closeForm()" class="btn btn-default">Отмена</button>
                </div>
            </form:form>
        </div>
    </div>
</div>
