$(document).ready(function () {
    getAllVIPInfo()
    $("#vipInfo-form-btn").click(function () {
        var formData = getVIPInfoForm();
        if(!validateVIPInfoForm(formData)){
            alert("输入有误")
            return;
        }
        postRequest(
            "/addVIPInfo",
            formData,
            function (res) {
                $('#vipInfoModal').hide()
                //todo 出现错误没进来
            },
            function (error) {
                alert(error)
            }
        )
    })
    
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

    function getAllVIPInfo() {
        getRequest(
            "/getAllVIPInfo",
            function (res) {
                renderVIPInfoList(res.content);
            },
            function (error) {
                alert(error);
            }
        )
    }

    function renderVIPInfoList(list) {
        $('.vipInfo-list').empty();
        var vipinfoDomStr = '';
        list.forEach(function (vipinfo) {
            vipinfo.description = vipinfo.description || '';
            vipinfoDomStr +=
                "<li class='vipInfo-item card'>"
            +"</li>";
        });
        $('.vipInfo-list').append(vipinfoDomStr);
    }
})