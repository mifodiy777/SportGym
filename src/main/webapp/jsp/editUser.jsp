<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
    $(document).ready(function () {

        $('#modalFormProfile').modal('show');

        $(".datepicker").datepicker({
            autoclose: true,
            format: "dd.mm.yyyy",
            language: 'ru',
            todayBtn: true,
            endDate:"0d"
        }).on('changeDate', function (ev) {
            $(this).valid();
        });

        $("#editUserForm").validate({
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    success: function (html) {
                        showSuccessMessage(html);
                        $('#modalFormProfile').modal('hide');
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
<div id="modalFormProfile" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Редактирование профиля</h4>
            </div>
            <form:form modelAttribute="user" id="editUserForm" method="post" action="editProfile">
                <div class="modal-body">
                    <form:hidden path="id"/>
                    <form:hidden path="role.id"/>
                    <form:hidden path="active"/>
                    <form:hidden path="version"/>
                    <div class="form-group">
                        <label class="control-label">Фамилия: </label>
                        <form:input path="surname" id="surname" cssClass="required form-control"/>
                        <span class="help-block"></span>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Имя: </label>
                        <form:input path="name" id="name" cssClass="required form-control"/>
                        <span class="help-block"></span>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Отчество: </label>
                        <form:input path="patronymic" id="patronymic" cssClass="form-control"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Пол: </label>
                        <form:select path="gender" cssClass="form-control">
                            <form:option value="M" label="Мужской"/>
                            <form:option value="W" label="Женский"/>
                        </form:select>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Дата рождения: </label>
                        <form:input path="birthday" id="birthday" cssClass="required datepicker dateRU form-control"/>
                        <span class="help-block"></span>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Телефон: </label>
                        <form:input path="phone" id="phone" cssClass="form-control"/>
                    </div>
                </div>

                <div class="modal-footer" align="center">
                    <div class="form-group">
                        <button type="submit" class="btn btn-success">Сохранить</button>
                        <button type="button" data-dismiss="modal" class="btn btn-default">Отмена</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

