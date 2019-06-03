$(document).ready(function () {
    var userIdSave;
    getAllUser();

    function getAllUser() {
        getRequest(
            "/staff/get/all",
            function (res) {
                var userList = res.content;
                userList.forEach(function (user) {
                    $("#user-list").append(renderUserInfo(user));
                });
            },
            function (error) {
                alert(error)
            }
        );
    }

    $("#getOneUser").click(function () {
        var key = $("#user-searchInfo-input").val();
        if (key == "") {
            getAllUser();
        } else {
            getRequest(
                "/staff/get/username/" + key,
                function (res) {
                    var user = res.content;
                    $('#user-list li').remove();
                    $('#user-list').append(renderUserInfo(user));
                },
                function (error) {
                    alert(error);
                }
            );
        }
    });
    $("#add-btn").click(function () {
        var username = $("#user-username-input").val();
        var password = $("#user-password-input").val();
        var level = $("#user-level-input").children("option:selected").val();
        var userVO = {"id":0,"username":username,"password":password,"level":level};
        postRequest(
            "/staff/add/user",
            userVO,
            function (res) {
                alert("成功");
                $('#user-list li').remove();
                getAllUser();
            },
            function (error) {
                alert('添加错误');
            }
        );
        $("#userModal").modal("hide");

    });
    $("#modify-btn").click(function () {
        var username = $("#userUpdate-id-input");
        var password = $("#userUpdate-password-input");
        var level = $("#userUpdate-level-input");
        var userVO = {"id":userIdSave,"username":username,"password":password,"level":level}
        postRequest(
            "/staff/update",
            userVO,
            function (res) {
                alert("成功");
                $('#user-list li').remove();
                getAllUser();
            },
            function (error) {
                alert(error);
            }
        );
    });
    $("#delete-btn").click(function () {
        if (confirm("确认删除吗？")){
            postRequest(
                "/staff",
                userIdSave,
                function (res) {
                    alert("成功")
                    $('#user-list li').remove();
                    getAllUser();
                }
            )
        }

    });

    function renderUserInfo(user) {
        var userInfo = "";
        userInfo +=
            "<li class=\"card\" style=\"margin:20px 0;padding:20px;\">" +
            "<div>" +
            "<span>用户名：" + user.username + "</span>" +
            "<span style='margin-left: 20px'>密码：" + user.password + "</span>";
        if (user.level == 0) {
            userInfo += "<span style='margin-left: 20px'>权限：用户</span>";
        } else if (user.level == 1) {
            userInfo += "<span style='margin-left: 20px'>权限：影院员工</span>";
        } else {
            userInfo += "<span style='margin-left: 20px'>权限：管理员</span>";
        }
        userInfo += "</div>";
        userInfo +=
            "<button class='modifyBtn btn btn-primary' date-toggle='modal' data-target='#userUpdateModal' value=" + userInfo.id + ">修改"
            + "</button>"
            + "<button class='deleteBtn btn btn-danger' style='margin-left: 20px' value=" + userInfo.id + ">删除"
            + "</button>"
            + "</li>";
        return userInfo;
    }

    $(".modify-Btn").click(function () {
        //check
        alert(this.val());
        userIdSave = this.val();
    });
    $(".deleteBtn").click(function () {
        //check
        alert("????;");
        alert(this.val());
        userIdSave = this.val();
    });


});