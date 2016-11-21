<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<script type="text/javascript">
    $(document).ready(function () {
        $('#trainingTypeTable').on('error.dt', function (e, settings, techNote, message) {
            showErrorMessage(message);
        }).DataTable({
            "ajax": {
                "url": "allTrainigTypes",
                "type": "POST"
            },
            "fnDrawCallback": function () {
                $('a.deleteButton').off("click");
                $('a.deleteButton').popConfirm({
                    title: "Удалить? Все связанные тренировки будут удалены!",
                    content: "",
                    placement: "bottom",
                    yesBtn: "Да",
                    noBtn: "Нет"
                });
            },
            "columns": [
                {"data": "name", 'title': 'Тип'},
                {
                    data: "distance", "searchable": false, "render": function (data, type, full) {
                    return (data) ? "Да" : "Нет";
                }, 'title': 'Дистанция'
                },
                {
                    data: "time", "searchable": false, "render": function (data, type, full) {
                    return (data) ? "Да" : "Нет";
                }, 'title': 'Время'
                },
                {
                    data: "count", "searchable": false, "render": function (data, type, full) {
                    return (data) ? "Да" : "Нет";
                }, 'title': 'Количество'
                },
                {
                    data: "attempt", "searchable": false, "render": function (data, type, full) {
                    return (data) ? "Да" : "Нет";
                }, 'title': 'Попытки'
                },
                {
                    data: "weight", "searchable": false, "render": function (data, type, full) {
                    return (data) ? "Да" : "Нет";
                }, 'title': 'Вес'
                },
                {
                    "searchable": false, "sorting": false, "render": function (data, type, full) {
                    return '<a href="#" class="btn btn-danger deleteButton" onclick=\"deleteTrainingType(' + full.id +
                            ');\"><span class=\"glyphicon glyphicon-trash\"/></span></a>';

                }
                }
            ]
        });
    });
</script>


<div class="container">
    <button class="btn btn-success addBtn" onclick="addTrainigType()">
        <span class="glyphicon glyphicon-plus"></span> Добавить тип
    </button>
    <div id="formPanel"></div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4>Типы трениировок</h4>
        </div>
        <div class="panel-body">
            <div class="table-responsive">
                <div class="col-md-12">
            <table id="trainingTypeTable" class="table table-striped table-bordered" cellspacing="0"
                   width="100%"></table>
                    </div>
                </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
