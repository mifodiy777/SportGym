<%--
  Created by IntelliJ IDEA.
  User: Кирилл
  Date: 29.10.2016
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(document).ready(function () {
        $("#editUserForm").validate({
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    success: function (html) {
                        showSuccessMessage(html);
                        $("#content").empty();
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

        $("#editUserForm").submit(function (e) {
            e.preventDefault();
            $(this).valid();
            return false;
        });

    })
</script>
<div class="panel panel-default">
    <div class="panel-heading">
        <p class="panel-title">Редактирование профиля</p>
    </div>
    <div class="panel-body">
        <div class="col-md-8">
            <form id="editUserForm" action="editProfile" method="post">
                <p class="text-danger"><strong>${errorMsg}</strong></p>
                <input type="hidden" name="id" value="${user.id}">
                <div class="form-group">
                    <label class="control-label">Фамилия: </label>
                    <input type="text" name="surname" value="${user.surname}" class="form-control required">
                    <span class="help-block"></span>
                </div>
                <div class="form-group">
                    <label class="control-label">Имя: </label>
                    <input type="text" name="name" value="${user.name}" class="form-control required">
                    <span class="help-block"></span>
                </div>
                <div class="form-group">
                    <label class="control-label">Отчество: </label>
                    <input type="text" name="patronymic" value="${user.patronymic}" class="form-control">
                </div>
                <div class="form-group">
                    <label class="control-label">Телефон: </label>
                    <input type="tel" name="phone" value="${user.phone}" class="form-control">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-success">Сохранить</button>
                    <button type="button" onclick="$('#content').empty();" class="btn btn-default">Отмена</button>
                </div>
            </form>
        </div>
    </div>
</div>

