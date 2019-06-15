$(document).ready(function () {
    var isBuyState = true;
    var vipCardId;
    getVIP();
    getCoupon();

    //getCoupon();

    function getVIP() {
        getRequest(
            '/vip/' + sessionStorage.getItem('id') + '/get',
            function (res) {
                if (res.success) {
                    // 是会员
                    $("#member-card").css("visibility", "visible");
                    $("#member-card").css("display", "");
                    $("#nonmember-card").css("display", "none");
                    var vipCard = res.content;
                    vipCardId = vipCard.id;
                    $("#member-id").text(res.content.id);
                    $("#member-balance").text("¥" + res.content.balance.toFixed(2));
                    $("#member-joinDate").text(res.content.joinDate.substring(0, 10));
                    getRequest(
                        '/vip/getVIPInfoById/' + res.content.vipInfoId,
                        function (res) {
                            if (res.success) {
                                $("#member-cardName").text(res.content.name);
                                $("#member-discount").text("充值优惠：满" + res.content.minimumCharge + "赠" + res.content.extraCharge);
                                $("#member-description").text(res.content.description);
                            } else {
                                alert("没有会员卡信息");
                            }

                        },
                        function (error) {
                            alert("cuowu");
                        });
                } else {
                    // 非会员
                    //放css样式
                    $("#member-card").css("display", "none");
                    $("#nonmember-card").css("display", "");
                    //获取所有vipInfo
                    getRequest(
                        '/vip/getAllVIPInfo',
                        function (res) {
                            if (res.success) {
                                var vipInfoList = res.content;
                                $('#vipInfo-input').append("<option value=" + -1 + ">无</option>");
                                vipInfoList.forEach(function (vipForm) {
                                    $('#vipInfo-input').append("<option value=" + vipForm.id + ">" + vipForm.name + "</option>");
                                });
                            } else {
                                alert(res.content);
                            }

                        },
                        function (error) {
                            alert(error);
                        });
                }
            },
            function (error) {
                alert(error);
            });
    }

//
    $('#vipInfo-input').change(function () {
        var vipFormId = $('#vipInfo-input').children("option:selected").val();
        var vipFormName = $('#vipInfo-input').children("option:selected").text();
        renderSelectedVIPInfo(vipFormId, vipFormName);
    });

    function renderSelectedVIPInfo(vipFormId, vipFormName) {
        if (vipFormId == -1) {
            $("#member-buy-price").text("");
            $("#member-buy-description").text("请选择会员卡");
            $("#member-description").text("");
        } else {
            getRequest(
                '/vip/getVIPInfoById/' + vipFormId,
                function (res) {
                    if (res.success) {
                        $("#member-buy-price").text(res.content.price);
                        $("#member-buy-description").text("充值优惠：满" + res.content.minimumCharge + "赠" + res.content.extraCharge + "。永久有效");
                        $("#member-description").text(res.content.description);
                    } else {
                        alert(res.content);
                    }

                },
                function (error) {
                    alert(error);
                });
        }

    }

    $("#buyVIP").click(function () {
        if ($('#vipInfo-input').children("option:selected").val() == -1) {
            alert("请选择会员卡");
        } else {
            clearForm();
            $('#buyModal').modal('show');
            $("#userMember-amount-group").css("display", "none");
            isBuyState = true;
        }
    });

    $("#vip-recharge").click(function () {
        clearForm();
        $('#buyModal').modal('show')
        $("#userMember-amount-group").css("display", "");
        isBuyState = false;
    });

    function clearForm() {
        $('#userMember-form input').val("");
        $('#userMember-form .form-group').removeClass("has-error");
        $('#userMember-form p').css("visibility", "hidden");
    }

    $("#complete-buy-vip").click(function () {
        if (validateForm()) {
            if ($('#userMember-cardNum').val() === "123123123" && $('#userMember-cardPwd').val() === "123123") {
                if (isBuyState) {
                    postRequest(
                        '/vip/add/' + sessionStorage.getItem("id") + "/" + $('#vipInfo-input').children("option:selected").val(),
                        null,
                        function (res) {
                            $('#buyModal').modal('hide');
                            alert("购买会员卡成功");
                            getVIP();
                        },
                        function (error) {
                            alert(error);
                        });
                } else {
                    postRequest(
                        '/vip/charge',
                        {vipId: vipCardId, amount: parseInt($('#userMember-amount').val())},
                        function (res) {
                            $('#buyModal').modal('hide');
                            alert("充值成功");
                            window.location.href="/user/member";
                        },
                        function (error) {
                            alert(error);
                        });
                }
            } else {
                alert("银行卡号或密码错误");
            }
        }
    });

    function validateForm() {
        var isValidate = true;
        if (!$('#userMember-cardNum').val()) {
            isValidate = false;
            $('#userMember-cardNum').parent('.form-group').addClass('has-error');
            $('#userMember-cardNum-error').css("visibility", "visible");
        }
        if (!$('#userMember-cardPwd').val()) {
            isValidate = false;
            $('#userMember-cardPwd').parent('.form-group').addClass('has-error');
            $('#userMember-cardPwd-error').css("visibility", "visible");
        }
        if (!isBuyState && (!$('#userMember-amount').val() || parseInt($('#userMember-amount').val()) <= 0)) {
            isValidate = false;
            $('#userMember-amount').parent('.form-group').addClass('has-error');
            $('#userMember-amount-error').css("visibility", "visible");
        }
        return isValidate;
    }

    function getCoupon() {
        getRequest(
            '/coupon/' + sessionStorage.getItem('id') + '/get',
            function (res) {
                if (res.success) {
                    var couponList = res.content;
                    var couponListContent = '';
                    couponList.foreach(function (coupon) {
                            couponListContent += '<div class="col-md-6 coupon-wrapper"><div class="coupon"><div class="content">' +
                                '<div class="col-md-8 left">' +
                                '<div class="name">' +
                                coupon.name +
                                '</div>' +
                                '<div class="description">' +
                                coupon.description +
                                '</div>' +
                                '<div class="price">' +
                                '满' + coupon.targetAmount + '减' + coupon.discountAmount +
                                '</div>' +
                                '</div>' +
                                '<div class="col-md-4 right">' +
                                '<div>有效日期：</div>' +
                                '<div>' + formatDate(coupon.startTime) + ' ~ ' + formatDate(coupon.endTime) + '</div>' +
                                '</div></div></div></div>'
                        }
                    );
                    $('#coupon-list').html(couponListContent);

                }
            },
            function (error) {
                alert(error);
            });
    }

    function formatDate(date) {
        return date.substring(5, 10).replace("-", ".");
    }
});