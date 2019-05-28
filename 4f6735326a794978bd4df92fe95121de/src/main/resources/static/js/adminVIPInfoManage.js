$(document).ready(function () {
    getAllVIPInfo();
    $("#vipInfo-form-btn").click(function () {
        var formData = getVIPInfoForm();
        if(!validateVIPInfoForm(formData)){
            alert("输入有误");
            return;
        }
        postRequest(
            "/vip/addVIPInfo",
            formData,
            function (res) {
                getAllVIPInfo();
                $('#vipInfoModal').modal('hide');
            },
            function (error) {
                alert(error);
            }
        );
    });


    $("#vipInfoUpdateModal").on("show.bs.modal",function (e) {
        var test = $(e.relatedTarget).data("vipinfoid");
        window.sessionStorage.setItem("vipinfoid",test);
    });
    $("#vipInfoUpdate-form-btn").on("hidden.bs.modal", function() {
        $(this).removeData("bs.modal");
    });
    $("#vipInfo-form-btn").on("hidden.bs.modal", function() {
        $(this).removeData("bs.modal");
    });
    $("#vipInfoUpdate-form-btn").click(function () {
        var id = window.sessionStorage.getItem("vipinfoid");
        alert(id);
        var formData = getVIPInfoUpdateForm(id);
        if(!validateVIPInfoForm(formData)){
            alert("输入有误");
            return;
        }
        postRequest(
            "/vip/update",
            formData,
            function (res) {
                getAllVIPInfo();
                $('#vipInfoUpdateModal').modal('hide');
            },
            function (error) {
                alert(error);
            }
        );
    });
    function validateVIPInfoForm(data) {
        var isValidate = true;
        if(!data.name) {
            isValidate = false;

            $('#vipInfo-name-input').parent('.form-group').addClass('has-error');
        }
        if(!data.description) {
            isValidate = false;
            $('#vipInfo-description-input').parent('.form-group').addClass('has-error');
        }
        if(!data.price) {
            isValidate = false;
            $('#vipInfo-price-input').parent('.form-group').addClass('has-error');
        }
        if(!data.minimumCharge) {
            isValidate = false;
            $('#vipInfo-minimumCharge-input').parent('.form-group').addClass('has-error');
        }
        if(!data.extraCharge) {
            isValidate = false;
            $('#vipInfo-extraCharge-input').parent('.form-group').addClass('has-error');
        }
        return isValidate;
    }
    function getVIPInfoForm() {
        return{
            price:$('#vipInfo-price-input').val(),
            name:$('#vipInfo-name-input').val(),
            minimumCharge:$('#vipInfo-minimumCharge-input').val(),
            extraCharge:$('#vipInfo-extraCharge-input').val(),
            description:$('#vipInfo-description-input').val()
        };
    }
    function getVIPInfoUpdateForm(id) {
        return{
            id:id,
            price:$('#vipInfoUpdate-price-input').val(),
            name:$('#vipInfoUpdate-name-input').val(),
            minimumCharge:$('#vipInfoUpdate-minimumCharge-input').val(),
            extraCharge:$('#vipInfoUpdate-extraCharge-input').val(),
            description:$('#vipInfoUpdate-description-input').val()
        };
    }

    function getAllVIPInfo() {
        getRequest(
            "/vip/getAllVIPInfo",
            function (res) {
                renderVIPInfoList(res.content);
            },
            function (error) {
                alert(error);
            }
        );
    }

    function renderVIPInfoList(list) {
        $('.vipInfo-list').empty();
        var vipInfoDomStr = '';
        var vipinfoid = "vipinfoid";
        list.forEach(function (vipinfo) {
            vipInfoDomStr +=
                "<div class='vipInfo-container'>" +
                "    <div class='vipInfo-card card'>" +
                "       <div class='vipInfo-line' >" +
                "           <span class='primary-text title'>"+vipinfo.name+"</span>" +
                "       </div>" +
                "       <div class='vipInfo-line' >" +
                "           <span class='gray-text'>"+vipinfo.description+"</span>" +
                "       </div>" +
                "    </div>" +
                "    <div class='vipInfo-main primary-bg'>" +
                "        <span class='title'>VIP卡名称："+vipinfo.name+"</span>" +
                "        <span class='title'>满"+vipinfo.minimumCharge+"减<span class='error-text title'>"+vipinfo.extraCharge+"</span></span>" +
                "        <span class='gray-text'>"+vipinfo.description+"</span>" +
                "        <button data-vipinfoid ="+vipinfo.id+" class='btn btn-primary'data-backdrop='static' data-toggle='modal'data-target='#vipInfoUpdateModal'>修改</button>" +
                "    </div>" +
                "</div>"
        });

        $('.vipInfo-list').append(vipInfoDomStr);
    }

});