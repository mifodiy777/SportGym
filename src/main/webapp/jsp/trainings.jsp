<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<script type="text/javascript">
    $(document).ready(function () {
        $('#trainingTable').on('error.dt', function (e, settings, techNote, message) {
            showErrorMessage(message);
        }).DataTable({
            "ajax": {
                "url": "allTrainigs/${currentType.id}",
                "type": "POST"
            },
            "fnDrawCallback": function () {
                $('a.deleteButton').off("click");
                $('a.deleteButton').popConfirm({
                    title: "Удалить?",
                    content: "",
                    placement: "bottom",
                    yesBtn: "Да",
                    noBtn: "Нет"
                });
            },
            "columns": [
                {
                    data: "complete", "searchable": false, "render": function (data, type, full) {
                    if (data) {
                        return "<a class='btn btn-success'>Да</a>";
                    } else {
                        return '<a class="btn btn-default" id="complete_' + full.id + '"  onclick=\"completeTraning(' + full.id + ')\">Нет</a>';
                    }

                }, 'title': 'Выполнено'
                },
                {"data": "targetDate.date", 'title': 'Дата тренировки'},
                <c:if test="${currentType.distance}">
                {"data": "distance", 'title': 'Дистанция'},
                </c:if>
                <c:if test="${currentType.time}">
                {"data": "time", 'title': 'Время'},
                </c:if>
                <c:if test="${currentType.count}">
                {"data": "count", 'title': 'Количество'},
                </c:if>
                <c:if test="${currentType.attempt}">
                {"data": "attempt", 'title': 'Подходы'},
                </c:if>
                <c:if test="${currentType.weight}">
                {"data": "weight", 'title': 'Вес'},
                </c:if>
                {"data": "notificate.date", 'title': 'Напоминание'},
                {
                    "searchable": false, "sorting": false, "render": function (data, type, full) {
                    var edit = '<a href="#" class="btn btn-primary" onclick=\"editTraining(' + full.id + ');\"><span class=\"glyphicon glyphicon-pencil\"/></span></a>';
                    var del = '<a href="#" class="btn btn-danger deleteButton" onclick=\"deleteTraining(' + full.id + ');\"><span class=\"glyphicon glyphicon-trash\"/></span></a>';
                    return '<div class="btn-group">' + edit + del + "</div>";

                }
                }

            ]
        });
    });
</script>

<div class="container-fluid">
    <div class="col-md-2">
        <h3 class="type-label">Типы тренировок:</h3>
        <div class="list-group">
            <c:if test="${empty trainingType}">
                <h3>Пусто</h3>
            </c:if>
            <c:forEach items="${trainingType}" var="type">
                <a href="traningPage?id=${type.id}"
                   class="list-group-item  <c:if test="${type.id eq currentType.id}">active</c:if>">
                        ${type.name}
                </a>
            </c:forEach>
        </div>
    </div>
    <br>
    <div class="col-md-10">
        <c:choose>
            <c:when test="${not empty currentType.id}">
                <button class="btn btn-success addBtn" onclick="addTraining('${currentType.id}')">
                    <span class="glyphicon glyphicon-plus"></span> Добавить тренировку
                </button>
                <div id="formPanel"></div>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Тренировки</h4>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div class="col-md-12">
                                <table id="trainingTable" class="table table-striped table-bordered" cellspacing="0"
                                       width="100%"></table>
                            </div>
                        </div>

                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Тренировки</h4>
                    </div>
                    <div class="panel-body">
                        <h2>Введите типы тренировок</h2>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <jsp:include page="footer.jsp"/>
