/**
 * Created by Кирилл on 29.10.2016.
 */
function validPlaceError(error, element) {
    $(element).parent().addClass("has-error");
    $(element).parent().children(".help-block").text($(error).text());
}

function validPlaceSuccess(label, element) {
    $(element).parent().removeClass("has-error");
    $(element).parent().children(".help-block").empty();
}

function showSuccessMessage(html) {
    $("#messages").removeClass("alert-danger");
    $("#messages").addClass("alert-info");
    $("#messages").html(html).show(800).delay(4000).hide(1000);
}

function showErrorMessage(html) {
    $("#messages").removeClass("alert-info");
    $("#messages").addClass("alert-danger");
    $("#messages").html(html).show(800).delay(4000).hide(1000);
}

function editUser(id) {
    $.get("editUserPage/" + id, function (html) {
        $("#profileForm").html(html);
    }).fail(function (xhr) {
        if (xhr.status == 409) {
            showErrorMessage(xhr.responseText);
        }
    })
}

function addBodyParam() {
    $.get("bodyParamForm", function (html) {
        $("#formPanel").html(html).show();
        $(".addBtn").hide();
        closeCharts();
    })
}

function addTrainigType() {
    $.get("trainigTypeForm", function (html) {
        $("#formPanel").html(html).show();
        $(".addBtn").hide();
    })
}

function addTraining(type) {
    $.get("trainigForm", {type: type}, function (html) {
        $("#formPanel").html(html).show();
        $(".addBtn").hide();
    }).fail(function (xhr) {
        if (xhr.status == 409) {
            showErrorMessage(xhr.responseText);
        }
    })
}

function editParam(id) {
    $.get("bodyParamForm/" + id, function (html) {
        $("#formPanel").html(html).show();
        $(".addBtn").hide();
    }).fail(function (xhr) {
        if (xhr.status == 409) {
            showErrorMessage(xhr.responseText);
        }
    })
}

function editTraining(id) {
    $.get("editTraining/" + id, function (html) {
        $("#formPanel").html(html).show();
        $(".addBtn").hide();
    }).fail(function (xhr) {
        if (xhr.status == 409) {
            showErrorMessage(xhr.responseText);
        }
    })
}

function deleteParam(id) {
    $.ajax({
        url: "deleteBodyParam/" + id,
        type: "post",
        success: function (html) {
            showSuccessMessage(html);
            $('#bodyParamTable').DataTable().ajax.reload(null, false);
            closeCharts();
        },
        error: function (xhr) {
            if (xhr.status == 409) {
                showErrorMessage(xhr.responseText);
            }
        }
    });
}

function deleteTrainingType(id) {
    $.ajax({
        url: "deleteTrainingType/" + id,
        type: "post",
        success: function (html) {
            showSuccessMessage(html);
            $('#trainingTypeTable').DataTable().ajax.reload(null, false);
        },
        error: function (xhr) {
            if (xhr.status == 409) {
                showErrorMessage(xhr.responseText);
            }
        }
    });
}

function deleteTraining(id) {
    $.ajax({
        url: "deleteTraining/" + id,
        type: "post",
        success: function (html) {
            showSuccessMessage(html);
            $('#trainingTable').DataTable().ajax.reload(null, false);
        },
        error: function (xhr) {
            if (xhr.status == 409) {
                showErrorMessage(xhr.responseText);
            }
        }
    });
}

function completeTraning(id) {
    $.get("trainigComplete/" + id, function (html) {
        $("#formPanel").html(html).show();
        $(".addBtn").hide();
    }).fail(function (xhr) {
        if (xhr.status == 409) {
            showErrorMessage(xhr.responseText);
        }
    })
}

function closeForm() {
    $("#formPanel").hide();
    $(".addBtn").show();
}

function closeCharts() {
    $('#line-graf').empty();
    $("#chartWell").hide();
}

function checkTypeParam() {
    if ($(".radio-group:checked").size() != 0) {
        $("#msg-error").hide();
        return true;
    } else {
        $("#msg-error").show();
        return false;
    }

}