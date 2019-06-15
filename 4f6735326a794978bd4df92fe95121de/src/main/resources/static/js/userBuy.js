$(document).ready(function () {
    getMovieList();

    function getMovieList() {
        getRequest(
            '/ticket/get/' + sessionStorage.getItem('id'),
            function (res) {
                renderTicketList(res.content);
            },
            function (error) {
                alert(error);
            });
    }

    // TODO:填空
    function renderTicketList(list) {
        var ticketsBodyStr ="";

        list.forEach(function (ticket) {
            var row=ticket.rowIndex+1
            var column=ticket.columnIndex+1
            var startTime=new Date(ticket.schedule.startTime)
            var endTime=new Date(ticket.schedule.endTime)

            ticketsBodyStr +=
                "<tr>"+
                "<td>" + ticket.schedule.movieName+ "</td>" +
                "<td>" + ticket.schedule.hallName + "</td>" +
                "<td>" + row+"排"+column+"座" + "</td>" +
                "<td>" + formatdate(startTime)+" "+formatTime(startTime)+ "</td>" +
                "<td>"+ formatdate(endTime)+" "+formatTime(endTime)+"</td>"+
                "<td>"+ticket.state+"</td>"+
                "<td><button class='btn-default' onclick='withdraw(this)' "+"id='"+ticket.id+"'" + (!isValidatedlyWithdraw(ticket)?"style='color: #ac2925' disabled='disabled'>不可退票":"style='color: #1caf9a'>退票")+"</button></td>"+
                "</tr>"
        });

        $("#ticket-body").html(ticketsBodyStr);

            // ticketsDomStr+=
            //     "<div class='ticket-container'>" +
            //     "    <div class='ticket-schedule-movie'>" +
            //     "       <span class='title'>"+ticket.schedule.movieName+"</span>" +
            //     "    </div>" +
            //     "    <div class='ticket-card card'>" +
            //     "       <div class='ticket-line'>" +
            //     "        <span class='title'>"+row+" 排</span>" +
            //     "        <span class='title'>"+column+" 座</span>" +
            //     "           <span class='gray-text'>"+ticket.state+"</span>" +
            //     "       </div>" +
            //     "       <div class='ticket-line'>" +
            //     "           <span>开始时间："+(new Date(ticket.schedule.startTime)).toLocaleDateString()+(new Date(ticket.schedule.startTime)).toLocaleTimeString()+"</span>" +
            //     "           <span>结束时间："+(new Date(ticket.schedule.endTime)).toLocaleDateString()+(new Date(ticket.schedule.endTime)).toLocaleTimeString()+"</span>" +
            //     "       </div>" +
            //     "    </div>" +
            //     "</div>";
    }

    function formatdate(date){
        var month = date.getMonth()+1+'';
        var day = date.getDate()+'';
        month.length===1 && (month = '0'+month)
        day.length===1 && (day = '0'+day)
        return month+'月'+day+'日';
    }

    function isValidatedlyWithdraw(ticket) {
        if(ticket.state!=="已完成"){
            return false;
        }
        var date=new Date();
        if(date>new Date(ticket.schedule.startTime)){
            return false
        }
        return true;
    }




    withdraw = function(e) {
        var id =$(e).attr("id")
        r=confirm("您确定要退票吗？")
        if(r){
            getRequest(
                "/ticket/withdraw?ticketId="+id,
                function (res) {
                    alert(res.content)
                    location.reload()
                },
                function (err) {
                    alert("该电影场次不可退票")
                }
            )
        }
    }


});