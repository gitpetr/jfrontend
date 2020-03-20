$(function () {
    confirmDelete = () => !(confirm('Are you sure you want to delete this user?'));
    // load all
    $('#users-table-tab').click();
    $.ajax({
        type: 'get',
        url: '/admin/user/all',
        success: function (users) {
            console.log(JSON.parse(users));
            JSON.parse(users).forEach(u => $('#user-item').append('<tr >' + oneTr(u) + '</tr>'));
        },
        error: function (request, status) {
            console.log(request.responseText);
            console.log(status);
        }
    });


        //update user
    $('#modalEditForm').on('submit', function (event) {
        event.preventDefault();
        $this = $(this);
        $.ajax({
            type: 'post',
            url: "/admin/saveUser/",
            data: $this.serialize(),
            success: function (user) {
                $updatingNode = $('td:contains(' + JSON.parse(user).id + ')').parent();
                $updatingNode.html(oneTr(JSON.parse(user)));
                $('#modal-edit-close').click();
            },

            error: function (request, status) {
                console.log(request.responseText);
                console.log(status);
            }

        });
        return false;
    });

    //new user
    $('#saveUser').on('submit', function (event) {
        event.preventDefault();
        let $form = $('#saveUser');
        $.ajax({
            type: 'post',
            url: "/admin/saveUser/",
            data: $form.serialize(),
            success: function (user) {
                $('#saveUser').trigger('reset');
                $('#users-table-tab').click();
                appendNewUser(JSON.parse(user));
            }
        })
    });

    //edit
    $('.table').on('click', '.eBtn', function (event) {
        event.preventDefault();
        let n = $(this).parent().parent();
        $('#id').val($(n).children('td:eq(0)').text());
        $('#username').val($(n).children('td:eq(1)').text());
        $('#email').val($(n).children('td:eq(2)').text());
        $('#password').val($(n).children('td:eq(3)').text());
        $('#country').val($(n).children('td:eq(4)').text());
        $('#modalEdit').modal('show');
    });

    //delete
    $('#user-item').on('click', '.dBtn', function (event) {
        event.preventDefault();
        let n = $(this).parent().parent();
        const href = $(this).attr('href');
        $.ajax({
            url: href,
            success: n.html(''),
            error: console.log('удалить не удалось')
        })
    });

    function appendNewUser(u) {
        $('#user-item').append('<tr >' + oneTr(u) + '</tr>');
    }

    function oneTr(u) {
        return '<td>' + u.id + '</td> ' +
            '<td>' + u.username + '</td> ' +
            '<td>' + u.email + '</td> ' +
            '<td>' + u.password + '</td> ' +
            '<td>' + u.country + '</td> ' +
            '<td>' + u.roles +
            '</td> ' + '<td class="d-flex justify-content-around"> ' +
            '<a class="btn btn-primary eBtn" href="">Edit</a> ' +
            '<a class="btn btn-danger dBtn" onclick="if (confirmDelete()) return false"' +
            ' href="/admin/user/delete/' + u.id + '">Delete</a></td> '
    }
});
