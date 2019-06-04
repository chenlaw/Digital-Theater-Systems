$(document).ready(function (){
    var hallId;
    var scheduleDate = formatDate(new Date());
    var schedules=[];
    var movieStartTime;

    initSelectAndDate();

    getAllWithdrawInfo();

    function getAllWithdrawInfo() {
        getRequest(
            "/ticket/withdraw/getAll",
            function (res) {
                renderWithdrawVOList(res.content);
            },
            function (error) {
                console.log(error);
            }
        )
    }

    function renderWithdrawVOList(list) {
        $('.withdrawInfo-list').empty();
        var withdrawInfoDomStr = '';
        list.forEach(function (withdrawVO) {
            withdrawInfoDomStr +=
                "<div id='withdrawInfo-"+withdrawVO.id+"' class='withdrawInfo-item'>" +
                "    <div class='withdrawInfo-content' data-withdrawinfo='"+JSON.stringify(withdrawVO)+"'>" +
                "        <span class='withdrawInfo-text'>退票信息编号："+withdrawVO.id+"</span>"+
                "        <span class='withdrawInfo-text'>退票信息描述："+withdrawVO.withdrawDescription+"</span>"+
                "        <span class='withdrawInfo-text'>影厅："+withdrawVO.hallName+"</span>" +
                "        <span class='withdrawInfo-text'>退票折价："+withdrawVO.discount+"</span>" +
                "        <span class='withdrawInfo-text'>退票截止时间："+withdrawVO.closeTime.slice(0,16)+"</span>" +
                "        <span class='withdrawInfo-text'>电影名称："+withdrawVO.filmName+"</span>" +
                "        <span class='withdrawInfo-text'>电影开始时间："+withdrawVO.filmStartTime.slice(0,16)+"</span>" +
                "    </div>" +
                "</div>"
        });
        $('.withdrawInfo-list').append(withdrawInfoDomStr);
    }

    function getCinemaHalls() {
        var halls = [];
        getRequest(
            '/hall/all',
            function (res) {
                halls = res.content;
                hallId = halls[0].id;
                halls.forEach(function (hall) {
                    $('#hall-select').append("<option value="+ hall.id +">"+hall.name+"</option>");
                    $('#hall-edit-select').append("<option value="+ hall.id +">"+hall.name+"</option>");
                    // $('#schedule-hall-input').append("<option value="+ hall.id +">"+hall.name+"</option>");
                    // $('#schedule-edit-hall-input').append("<option value="+ hall.id +">"+hall.name+"</option>");
                });
                getSchedules();
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    function initSelectAndDate() {
        $('#date-input').val(scheduleDate);
        getCinemaHalls();

        // 过滤条件变化后重新查询
        $('#hall-select').change (function () {
            hallId=$(this).children('option:selected').val();
            getSchedules();
        });
        $('#date-input').change(function () {
            scheduleDate = $('#date-input').val();
            getSchedules();
        });
        $('#hall-edit-select').change (function () {
            hallId=$(this).children('option:selected').val();
            getSchedules();
        });
        $('#date-edit-input').change(function () {
            scheduleDate = $('#date-edit-input').val();
            getSchedules();
        });
    }

    function getSchedules() {

        getRequest(
            '/schedule/search/today?hallId='+hallId+'&startDate='+scheduleDate.replace(/-/g,'/'),
            function (res) {
                schedules = res.content;
                renderScheduleTable(schedules);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    function renderScheduleTable(schedules){
        document.getElementById("schedule-select").innerHTML = "";
        document.getElementById("schedule-edit-select").innerHTML = "";
        schedules.forEach(function (scheduleOfDate) {
            scheduleOfDate.scheduleItemList.forEach(function (schedule) {
                $('#schedule-select').append("<option value="+ schedule.id +">"+schedule.movieName+" "+schedule.startTime.substring(11,19)+"~"+schedule.endTime.substring(11,19)+"</option>")
                $('#schedule-edit-select').append("<option value="+ schedule.id +">"+schedule.movieName+" "+schedule.startTime.substring(11,19)+"~"+schedule.endTime.substring(11,19)+"</option>")
                movieStartTime=schedule.startTime;
            });
        })
    }


    $("#withdrawInfo-form-btn").click(function () {
        var form = {
            withdrawDescription:$("#withdrawInfo-description-input").val(),
            scheduleId: $("#schedule-select").children('option:selected').val(),
            closeTime:$("#withdrawInfo-closeTime-input").val(),
            discount:$("#discount-input").val()
        };

        if(!validateWithdrawInfoForm(form)) {
            return;
        }
        postRequest(
            '/ticket/withdraw/add',
            form,
            function (res) {
                if(res.success){
                    $("#withdrawInfoModal").modal('hide');
                    alert(res.content);
                    getAllWithdrawInfo();
                } else{
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    })


    function validateWithdrawInfoForm(data) {
        var isValidate = true;
        if(!data.withdrawDescription) {
            isValidate = false;
            $('#withdrawInfo-description-input').parent('.form-group').addClass('has-error');
        }
        if(!data.scheduleId){
            isValidate = false;
            $('#schedule-select').parent('.form-group').addClass('has-error');
        }
        if(!data.closeTime){
            isValidate = false;
            $('#date-input').parent('.form-group').addClass('has-error');
        }
        if(!data.discount){
            isValidate = false;
            $('#discount-input').parent('.form-group').addClass('has-error');
        }
        return isValidate;
    }


    $(document).on('click','.withdrawInfo-item',function (e) {
        var withdrawInfo = JSON.parse(e.target.dataset.withdrawinfo);
        hallId=withdrawInfo.hallId;
        scheduleDate = formatDate(new Date(withdrawInfo.filmStartTime));
        getSchedules();
        $("#withdrawInfo-description-edit-input").val(withdrawInfo.withdrawDescription);
        $("#hall-edit-select").children('option[value='+withdrawInfo.hallId+']').attr('selected',true);
        $("#hall-edit-select").attr("disabled","disabled");
        $("#withdrawInfo-closeTime-edit-input").val(withdrawInfo.closeTime.slice(0,16));
        $("#date-edit-input").val(withdrawInfo.filmStartTime.slice(0,10));
        $("#date-edit-input").attr("readonly","readonly");
        $("#discount-edit-input").val(withdrawInfo.discount);
        $("#schedule-edit-select").children('option[value='+withdrawInfo.scheduleId+']').attr('selected',true);
        $("#schedule-edit-select").attr("disabled","disabled");
        $('#withdrawInfoEditModal').modal('show');
        console.log(withdrawInfo);
    });

    $("#withdrawInfo-form-edit-btn").click(function () {
        var form = {
            withdrawDescription:$("#withdrawInfo-description-edit-input").val(),
            scheduleId: $("#schedule-edit-select").children('option:selected').val(),
            closeTime:$("#withdrawInfo-closeTime-edit-input").val(),
            discount:$("#discount-edit-input").val()
        };

        if(!validateWithdrawInfoForm(form)) {
            return;
        }
        postRequest(
            '/ticket/withdraw/update',
            form,
            function (res) {
                if(res.success){
                    $("#withdrawInfoEditModal").modal('hide');
                    alert(res.content);
                    getAllWithdrawInfo();
                } else{
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    })

})