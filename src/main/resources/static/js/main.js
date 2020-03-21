$(function () {
    // load all
    loadAllUsers();

        //update user
    $('#modalEditForm').on('submit', function (event) {
        event.preventDefault();
        $this = $(this);
        $.ajax({
            type: 'post',
            url: "/admin/saveUser/",
            data: $this.serialize(),
            success: updateUser,
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
            success: appendNewUser
        })
    });

    //edit
    $('.table').on('click', '.eBtn', function (event) {
        event.preventDefault();
        let tr = $(this).parent().parent();
        $('#id').val($(tr).children('td:eq(0)').text());
        $('#username').val($(tr).children('td:eq(1)').text());
        $('#email').val($(tr).children('td:eq(2)').text());
        $('#password').val($(tr).children('td:eq(3)').text());
        $('#country').val($(tr).children('td:eq(4)').text());
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
        $('#saveUser').trigger('reset');
        $('#users-table-tab').click();
        $('#user-item').append('<tr >' + oneTr(u) + '</tr>');
    }

    function loadUsers(users) {
        console.log(users);
        users.forEach(u => $('#user-item').append('<tr >' + oneTr(u) + '</tr>'));
    }

    function updateUser(user) {
        $updatingNode = $('td:contains(' + user.id + ')').parent();
        $updatingNode.html(oneTr(user));
        $('#modal-edit-close').click();
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
            '<a class="btn btn-danger dBtn"' +
            ' href="/admin/user/delete/' + u.id + '">Delete</a></td> '
    }

    function loadAllUsers() {
        $.ajax({
            type: 'get',
            url: '/admin/user/all',
            success: loadUsers,
            error: function (request, status) {
                console.log(request.responseText);
                console.log(status);
            }
        });
    }
});
