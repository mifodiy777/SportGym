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
    $("#content").empty().load("editUserPage?idUser=" + id);
}
