$(document).ready(function() {

<<<<<<< HEAD
=======
    var canSeeDate = 0;
    var targetHallId = 0;
>>>>>>> pqy

    getCinemaHalls();

    function getCinemaHalls() {
        var halls = [];
        getRequest(
            '/hall/all',
            function (res) {
                halls = res.content;
                renderHall(halls);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    function getCanSeeDayNum() {
        getRequest(
            '/schedule/view',
            function (res) {
                canSeeDate = res.content;
                $("#can-see-num").text(canSeeDate);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    function renderHall(halls){
        $('#hall-card').empty();
        var hallDomStr =""
        halls.forEach(function (hall) {
            var seat = "";
            for(var i =0;i<hall.row;i++){
                var temp = ""
                for(var j =0;j<hall.column;j++){
                    temp+="<div class='cinema-hall-seat'></div>";
                }
                seat+= "<div>"+temp+"</div>";
            }
            var hallDom =
                "<div class='cinema-hall'>" +
                "   <div>" +
                "       <span class='cinema-hall-name'>"+ hall.name +"</span>" +
                "       <span class='cinema-hall-size'>"+ hall.column +'*'+ hall.row +"</span>" +
                "       <button type='button' class='btn hall-item' data-backdrop='static' data-toggle='modal' data-target='#hallModify' data-hall='"+JSON.stringify(hall)+"'>修改</button>"+
                // "       <a style='margin-left: 20px;' class='primary-text' id='hall-modify-btn'>修改</a>"+
                // "       <label style='display:none' id=hall-mod-row'>行数:</label>"+
                // "       <input type='number' class='form-control' id='hall-mod-row-input' style='display: none;width: 200px;'>"+
                // "       <label style='display:none' id= 'hall-mod-column'>列数:</label>"+
                // "       <input type='number' class='form-control' id='hall-mod-column-input' style='display: none;width: 200px;'>"+
                // "       <a style='display: none' class='primary-text' id='hall-mod-confirm-btn'>确认</a>"+
                "   </div>" +
                "   <div class='cinema-seat'>" + seat +
                "   </div>" +
                "</div>";
            hallDomStr+=hallDom;
        });
        $('#hall-card').append(hallDomStr);
    }

<<<<<<< HEAD
=======
    $('#canview-modify-btn').click(function () {
       $("#canview-modify-btn").hide();
       $("#canview-set-input").val(canSeeDate);
       $("#canview-set-input").show();
       $("#canview-confirm-btn").show();
    });

    $('#canview-confirm-btn').click(function () {
        var dayNum = $("#canview-set-input").val();
        // 验证一下是否为数字
        postRequest(
            '/schedule/view/set',
            {day:dayNum},
            function (res) {
                if(res.success){
                    getCanSeeDayNum();
                    canSeeDate = dayNum;
                    $("#canview-modify-btn").show();
                    $("#canview-set-input").hide();
                    $("#canview-confirm-btn").hide();
                } else{
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    })

    $("#hall-form-btn").click(function () {
        var formData = getHallForm();
        if(!validateHallForm(formData)) {
            return;
        }
        postRequest(
            '/hall/add',
            formData,
            function (res) {
                getCinemaHalls();
                $("#hallModal").modal('hide');
            },
            function (error) {
                alert(error);
            });
    });

    function getHallForm() {
        return {
            name: $('#hall-name-input').val(),
            row: $('#hall-row-input').val(),
            column: $('#hall-column-input').val(),
        };
    }

    function validateHallForm(data) {
        var isValidate = true;
        if(!data.name) {
            isValidate = false;
            $('#hall-name-input').parent('.form-group').addClass('has-error');
        }
        if(!data.row) {
            isValidate = false;
            $('#hall-row-input').parent('.form-group').addClass('has-error');
        }
        if(!data.column) {
            isValidate = false;
            $('#hall-column-input').parent('.form-group').addClass('has-error');
        }
        return isValidate;
    }

    // function repaintHall(hall){
    //     $('#hall-name-set').val(hall.name);
    //     $('#hall-row-set').val(hall.row);
    //     $('#hall-column-set').val(hall.column);
    // }

    $(document).on('click','.hall-item',function(e){
        var hall = JSON.parse(e.target.dataset.hall);
        $("#hall-name-set").val(hall.name);
        $("#hall-row-set").val(hall.row);
        $("#hall-column-set").val(hall.column);
        targetHallId=hall.id;
    });

    $("#modify-confirm-btn").click(function (){
        var modifyData={
            id:targetHallId,
            name:$("#hall-name-set").val(),
            row:$("#hall-row-set").val(),
            column:$("#hall-column-set").val()
        }
        if(!validateHallForm(modifyData)) {
            return;
        }
        postRequest(
            '/hall/update',
            modifyData,
            function (res) {
                getCinemaHalls();
                $("#hallModify").modal('hide');
            },
            function (error) {
                alert(error);
            });
    })

    $("#hall-remove-btn").click(function () {
        var r=confirm("确认要删除该影厅吗")
        if (r) {
            postRequest(
                '/hall/delete',
                targetHallId,
                function (res) {
                    if(res.success){
                        getCinemaHalls();
                        $("#hallModify").modal('hide');
                    } else{
                        alert(res.message);
                    }
                },
                function (error) {
                    alert(JSON.stringify(error));
                }
            );
        }
    })
>>>>>>> pqy

});