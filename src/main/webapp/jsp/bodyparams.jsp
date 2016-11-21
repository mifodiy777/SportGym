<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<script type="text/javascript">
    $(document).ready(function () {
        $('#bodyParamTable').on('error.dt', function (e, settings, techNote, message) {
            showErrorMessage(message);
        }).DataTable({
            "ajax": {
                "url": "allBodyParams",
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
                    data: "id", "searchable": false, "render": function (data, type, full) {
                    return '<a href=\"#\" class="btn btn-warning"  onclick=\"editParam(' + full.id + ');\">' +
                            '<span class="glyphicon glyphicon-pencil"></span></a>'
                }, 'title': 'Изменить'
                },
                {"data": "measurementDate.date", 'title': 'Дата измерения'},
                {"data": "weight", 'title': 'Вес'},
                {"data": "height", 'title': 'Рост'},
                {"data": "body", 'title': 'Окружность груди'},
                {"data": "haunch", 'title': 'Окружность бедра'},
                {"data": "forearm", 'title': 'Окружность предплечья'},
                {
                    data: "id", "searchable": false, "render": function (data, type, full) {
                    return '<a href=\"#\" class="btn btn-danger deleteButton" onclick=\"deleteParam(' + full.id + ');\">' +
                            '<span class="glyphicon glyphicon-trash"></span></a>'
                }, 'title': 'Удалить'
                }
            ]
        });

        $(window).resize(function () {
            closeCharts();
        });

        $("#graficForm").submit(function (e) {
            e.preventDefault();
            $("#graficForm").ajaxSubmit({
                success: function (html) {
                    if (html != "[]") {
                        $('#line-graf').empty();
                        $("#chartWell").show();
                        Morris.Line({
                            element: 'line-graf',
                            data: JSON.parse(html),
                            xkey: 'year',
                            ykeys: ['value'],
                            labels: ['Результат']
                        });
                    } else {
                        showErrorMessage("Не данных для построения графика")
                    }
                    return false;
                }
            });
            return false;
        });

    })
</script>


<div class="container">
    <button class="btn btn-success addBtn" onclick="addBodyParam()">
        <span class="glyphicon glyphicon-plus"></span> Добавить измерение
    </button>
    <div id="formPanel"></div>
    <br>
    <div id="chartWell" class="well" style="display: none">
        <button type="button" class="close"
                onclick="closeCharts();">&times;</button>
        <br>
        <div id="line-graf" style="height: 400px;"></div>
    </div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4>Физиологические параметры</h4>
        </div>
        <div class="panel-body">
            <h4>Построить график:</h4>
            <form action="createGraf" id="graficForm" method="POST">
                <div class="form-group input-group">
                    <label for="paramGraf" class="input-group-addon">Параметр</label>
                    <select id="paramGraf" name="param" class="form-control">
                        <option value="weight">Вес</option>
                        <option value="height">Рост</option>
                        <option value="body">Окружность груди</option>
                        <option value="haunch">Окружность бедра</option>
                        <option value="forearm">Окружность предплечья</option>
                    </select>
                    <span class="input-group-btn">
                        <button type="submit" class="btn btn-primary">
                            Построить
                        </button>
                    </span>
                </div>

            </form>
            <div class="table-responsive">
                <div class="col-md-12">
                    <table id="bodyParamTable" class="table table-striped table-bordered" cellspacing="0"
                           width="100%"></table>
                </div>

            </div>

        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
