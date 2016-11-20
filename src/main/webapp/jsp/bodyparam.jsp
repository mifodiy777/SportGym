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

        $("#addBodyParamForm").validate({
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    success: function (html) {
                        $("#bodyParamTable").DataTable().ajax.reload(null, false);
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

        $("#addBodyParamForm").submit(function (e) {
            e.preventDefault();
            $(this).valid();
            return false;
        });

    })
</script>
<div class="panel panel-default">
    <div class="panel-heading">
        <p class="panel-title">Добавление физиологические параметров</p>
    </div>
    <div class="panel-body">
        <div class="col-md-8">
            <form:form modelAttribute="bodyParam" id="addBodyParamForm" method="post" action="saveBodyParam">
                <form:hidden path="id"/>
                <form:hidden path="user"/>
                <form:hidden path="measurementDate"/>
                <form:hidden path="version"/>
                <div class="form-group">
                    <label class="control-label">Вес: </label>
                    <form:input path="weight" id="weight" cssClass="required form-control"/>
                    <span class="help-block"></span>
                </div>
                <div class="form-group">
                    <label class="control-label">Рост: </label>
                    <form:input path="height" id="height" cssClass="required form-control"/>
                    <span class="help-block"></span>
                </div>
                <div class="form-group">
                    <label class="control-label">Окружность груди /см: </label>
                    <form:input path="body" id="body" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <label class="control-label">Окружность бедра /см: </label>
                    <form:input path="haunch" id="haunch" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <label class="control-label">Окружность предплечья / см: </label>
                    <form:input path="forearm" id="forearm" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-success">Сохранить</button>
                    <button type="button" onclick="closeForm()" class="btn btn-default">Отмена</button>
                </div>
            </form:form>
        </div>
    </div>
</div>
