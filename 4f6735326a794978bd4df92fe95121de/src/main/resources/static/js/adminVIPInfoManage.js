/**
 * @author wph
 */
$(document).ready(function () {
    getAllVIPInfo();

    /**
     * 点击发布会员卡模态框确定按钮
     */
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

    /**
     * 点击会员卡种类"修改"按钮
     */
    $("#vipInfoUpdateModal").on("show.bs.modal",function (e) {
        var test = $(e.relatedTarget).data("vipinfoid");
        //存储会员卡种类id到全局供使用
        window.sessionStorage.setItem("vipinfoid",test);
    });

    /**
     * 点击“修改会员卡模态框”的取消按钮
     */
    $("#vipInfoUpdate-form-btn").on("hidden.bs.modal", function() {
        $(this).removeData("bs.modal");
    });

    /**
     * 点击“发布会员卡模态框”的取消按钮
     */
    $("#vipInfo-form-btn").on("hidden.bs.modal", function() {
        $(this).removeData("bs.modal");
    });

    /**
     * 点击“修改会员卡模态框”的确定按钮
     */
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

    /**
     * 验证会员卡信息是否为空
     */
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

    /**
     * 获取 “发布会员卡模态框”的输入信息
     * @returns {{price: (*|jQuery|string|undefined), name: (*|jQuery|string|undefined), minimumCharge: (*|jQuery|string|undefined), description: (*|jQuery|string|undefined), extraCharge: (*|jQuery|string|undefined)}}
     */
    function getVIPInfoForm() {
        return{
            price:$('#vipInfo-price-input').val(),
            name:$('#vipInfo-name-input').val(),
            minimumCharge:$('#vipInfo-minimumCharge-input').val(),
            extraCharge:$('#vipInfo-extraCharge-input').val(),
            description:$('#vipInfo-description-input').val()
        };
    }

    /**
     * 获取 “修改会员卡模态框”的输入信息
     * @param id
     * @returns {{price: (*|jQuery|string|undefined), name: (*|jQuery|string|undefined), minimumCharge: (*|jQuery|string|undefined), description: (*|jQuery|string|undefined), id: *, extraCharge: (*|jQuery|string|undefined)}}
     */
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

    /**
     * 获取所有VIP卡种类信息
     */
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

    /**
     * 渲染会员卡种类信息表
     * @param list
     */
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