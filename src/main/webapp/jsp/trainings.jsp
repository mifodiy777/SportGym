<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<script type="text/javascript">
    $(document).ready(function () {
        $('#trainingTable').on('error.dt', function (e, settings, techNote, message) {
            showErrorMessage(message);
        }).DataTable({
            "ajax": {
                "url": "allTrainigs",
                "type": "POST"
            },
            "columns": [
                {"data": "type.name", 'title': 'Тип тренировки'},
                {"data": "targetDate.date", 'title': 'Дата тренировки'},
                {"data": "distance", 'title': 'Дистанция'},
                {"data": "time", 'title': 'Время'},
                {"data": "count", 'title': 'Количество'},
                {"data": "attempt", 'title': 'Подходы'},
                {"data": "weight", 'title': 'Вес'},
                {
                    data: "complete", "searchable": false, "render": function (data, type, full) {
                    return (data) ? "Да" : "Нет";
                }, 'title': 'Выполненно?'
                },
                {"data": "notificate.date", 'title': 'Напоминание'}

            ]
        });
    });
</script>


<div class="container">
    <div class="col-md-3">
        <ul class="nav nav-pills nav-stacked">
            <c:forEach items="${trainingType}" var="type">
                <li role="presentation"><a href="#">${type.name}</a></li>
            </c:forEach>
        </ul>
    </div>
    <div class="col-md-9">
        <button class="btn btn-success addBtn" onclick="addTraining()">
            <span class="glyphicon glyphicon-plus"></span> Добавить тренировку
        </button>
        <div id="formPanel"></div>
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h4>Тренировки</h4>
            </div>
            <div class="panel-body">
                <table id="trainingTable" class="table table-striped table-bordered" cellspacing="0"
                       width="100%"></table>
            </div>
        </div>
    </div>

</div>
<jsp:include page="footer.jsp"/>
