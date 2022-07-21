let baseURI = 'http://localhost:8080/';

function isNotEmptyString(str){
    if(
        str == undefined ||
        str == null ||
        $.trim(str) == ''){

        return false;
    }
    return true;
}

$(document).ready(function () {

    $("#btnGetUser").click(function (e) {
        e.preventDefault();

        if (isNotEmptyString($('#txtId').val()) == false) {
            window.alert('id can not be empty.')
            return;
        }

        $.get(baseURI + "users/" + $('#txtId').val(),
            function (user, textStatus, jqXHR) {
                $('#txtName').val(user.name);
                $("#message").val("user " + user.id + " existed.");
            },
            "json"
        );

    });

    $('#btnCreateUser').click(function (e) {
        e.preventDefault();

        if (isNotEmptyString($('#txtName').val()) == false) {
            window.alert('name can not be empty.')
            return;
        }

        var newUser = {
            "name": $("#txtName").val(),
            "department": baseURI + "departments/1"
        }

        $.ajax({
            type: "POST",
            url: baseURI + "users",
            data: JSON.stringify(newUser),
            contentType: 'application/json',
            success: function (user) {

                $('#txtId').val(user.id);
                $("#message").val("user " + user.id + " created.");

            }
        });
    });

    $('#btnModifyUser').click(function (e) {
        e.preventDefault();

        if (isNotEmptyString($('#txtId').val()) == false) {
            window.alert('id can not be empty.')
            return;
        }

        $.get(baseURI + "users/" + $('#txtId').val(),
            function (data) {

                var modifedUser = {
                    "name": $("#txtName").val()
                }

                $.ajax({
                    type: "PUT",
                    url: baseURI + "users/" + $('#txtId').val(),
                    data: JSON.stringify(modifedUser),
                    contentType: 'application/json',
                    success: function (user) {
                        $("#message").val("user " + user.id + " modified.");
                    }
                });
            }
        )
    });

    $('#btnRemoveUser').click(function (e) {
        e.preventDefault();

        $.ajax({
            type: "DELETE",
            url: baseURI + "users/" + $('#txtId').val(),
            success: function (response) {
                $("#message").val("user " + $('#txtId').val() + " removed.");
                $('#txtId').val('');
                $('#txtName').val('');
            }
        });
    });

});