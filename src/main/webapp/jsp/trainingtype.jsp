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

        $("#addTrainigTypeForm").validate({
            submitHandler: function (form) {
                if (checkTypeParam()) {
                    $(form).ajaxSubmit({
                        success: function (html) {
                            $("#trainingTypeTable").DataTable().ajax.reload(null, false);
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
                }

            },
            errorPlacement: function (error, element) {
                validPlaceError(error, element);
            },
            success: function (label, element) {
                validPlaceSuccess(label, element);
            }
        });

        $(".radio-group").on("click", function () {
            checkTypeParam();
        });

        $("#addTrainigTypeForm").submit(function (e) {
            e.preventDefault();
            $(this).valid();
            return false;
        });

    })
</script>
<div class="panel panel-default">
    <div class="panel-heading">
        <p class="panel-title">Добавление типов тренировок</p>
    </div>
    <div class="panel-body">
        <div class="col-md-8">
            <form:form modelAttribute="trainigType" id="addTrainigTypeForm" method="post" action="saveTrainigType">
                <form:hidden path="id"/>
                <form:hidden path="user"/>
                <form:hidden path="version"/>
                <div class="form-group">
                    <label class="control-label">Название: </label>
                    <form:input path="name" id="name" cssClass="required form-control"/>
                    <span class="help-block"></span>
                </div>
                <hr>
                <h4>Типы измерений:</h4>
                <hr>

                <p id="msg-error" class="text-danger" style="display: none"><strong>*Необходимо выделить хотя бы один
                    параметр</strong></p>
                <div class="form-group input-group">
                    <form:checkbox path="distance" id="distance"
                                   cssClass="radio-group form-control"
                                   name="fancy-checkbox-success" autocomplete="off"/>
                    <div class="[ btn-group ]">
                        <label for="distance" class="[ btn btn-default ]">
                            <span class="[ glyphicon glyphicon-ok ]"></span>
                            <span> </span>
                        </label>
                        <label for="distance" class="[ btn btn-default ]">
                            Дистанция
                        </label>
                    </div>
                </div>


                <div class="form-group input-group">
                    <form:checkbox path="time" id="time"
                                   cssClass="radio-group form-control"
                                   name="fancy-checkbox-success" autocomplete="off"/>
                    <div class="[ btn-group ]">
                        <label for="time" class="[ btn btn-default ]">
                            <span class="[ glyphicon glyphicon-ok ]"></span>
                            <span> </span>
                        </label>
                        <label for="time" class="[ btn btn-default ]">
                            Время
                        </label>
                    </div>
                </div>


                <div class="form-group input-group">
                    <form:checkbox path="count" id="count"
                                   cssClass="radio-group form-control"
                                   name="fancy-checkbox-success" autocomplete="off"/>
                    <div class="[ btn-group ]">
                        <label for="count" class="[ btn btn-default ]">
                            <span class="[ glyphicon glyphicon-ok ]"></span>
                            <span> </span>
                        </label>
                        <label for="count" class="[ btn btn-default ]">
                            Количество
                        </label>
                    </div>
                </div>


                <div class="form-group input-group">
                    <form:checkbox path="attempt" id="attempt"
                                   cssClass="radio-group form-control"
                                   name="fancy-checkbox-success" autocomplete="off"/>
                    <div class="[ btn-group ]">
                        <label for="attempt" class="[ btn btn-default ]">
                            <span class="[ glyphicon glyphicon-ok ]"></span>
                            <span> </span>
                        </label>
                        <label for="attempt" class="[ btn btn-default ]">
                            Попытки
                        </label>
                    </div>
                </div>


                <div class="form-group input-group">
                    <form:checkbox path="weight" id="weight"
                                   cssClass="radio-group form-control"
                                   name="fancy-checkbox-success" autocomplete="off"/>
                    <div class="[ btn-group ]">
                        <label for="weight" class="[ btn btn-default ]">
                            <span class="[ glyphicon glyphicon-ok ]"></span>
                            <span> </span>
                        </label>
                        <label for="weight" class="[ btn btn-default ]">
                            Вес
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
