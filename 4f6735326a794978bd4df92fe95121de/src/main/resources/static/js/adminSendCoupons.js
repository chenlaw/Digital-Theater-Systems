$(document).ready(function () {
    var userIdSave=[];
    var couponIdSave=[];
    getAllUser();

    function getAllUser() {
        getRequest(
            "/consumption/all",
            function (res) {
                var userList = res.content;
                userList.forEach(function (consumption) {
                    $("#user-list").append(renderUserInfo(consumption));
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
        } else if(key<=0){
            alert("请输入大于0的数据");
        }
        else{
            getRequest(
                "/consumption/money/" + key,
                function (res) {
                    var userList = res.content;
                    $("#user-list").empty();
                    userList.forEach(function (consumption) {
                        $("#user-list").append(renderUserInfo(consumption));
                    });
                },
                function (error) {
                    alert(error)
                }
            );
        }
    });
    $("#select-coupon").click(function () {
        getRequest(
            '/activity/get',
            function (res) {
                var activities = res.content;
                renderActivities(activities);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    });
$("#put-on").click(function () {
    var items = document.getElementsByName("coupon");
    for (let i = 0; i < items.length; i++) {
        if (items[i].checked) {
            couponIdSave.push(items[i].id);
        }
    }
    items=document.getElementsByName("user");
    for (let i = 0; i < items.length; i++) {
        if (items[i].checked) {
            userIdSave.push(items[i].id);
        }
    }

    postRequest(
        "/coupon/sendCoupons",
        {
            "usersId":userIdSave,
            "couponsId":couponIdSave
        },
        function (res) {
            alert("成功");
        },
        function (error) {
            alert(error);
        }
    );
    $('#myModal').modal("hide");
})


    function renderUserInfo(user) {
        var userInfo = "";
        userInfo +=

           " <tr><td><input type='checkbox' name='user' id="+user.userId+" </td><td>"+user.username+"</td><td>" + user.balance + "</td></tr>"
             ;


        return userInfo;
    }
    function renderActivities(activities) {
        $(".modal-body").empty();
        var activitiesDomStr = "";

        activities.forEach(function (activity) {
            var movieDomStr="";
            if(activity.movieList.length){
                activity.movieList.forEach(function (movie) {
                    movieDomStr += "<li class='activity-movie primary-text'>"+movie.name+"</li>";
                });
            }else{
                movieDomStr = "<li class='activity-movie primary-text'>所有热映电影</li>";
            }
            activitiesDomStr+=
                "<div class='activity-container'>" +
                "    <div class='activity-card card'>" +
                "       <div class='activity-line'>" +
                "           <span class='title'>"+activity.name+"</span>" +
                "           <span class='gray-text'>"+activity.description+"</span>" +
                "       </div>" +
                "       <div class='activity-line'>" +
                "           <span>活动时间："+formatDate(new Date(activity.startTime))+"至"+formatDate(new Date(activity.endTime))+"</span>" +
                "       </div>" +
                "       <div class='activity-line'>" +
                "           <span>参与电影：</span>" +
                "               <ul>"+movieDomStr+"</ul>" +
                "       </div>" +
                "    </div>" +
                "    <div class='activity-coupon primary-bg'>" +
                "        <span class='title'>优惠券："+activity.coupon.name+"</span>" +
                "        <span class='title'>满"+activity.coupon.targetAmount+"减<span class='error-text title'>"+activity.coupon.discountAmount+"</span></span>" +
                "<input type='checkbox' name='coupon' id="+activity.coupon.id+" />"+
                "    </div>" +
                "</div>";
        });
        $(".modal-body").append(activitiesDomStr);
    }

});