$(document).ready(function () {
    getRechargeList();

    function getRechargeList() {
        getRequest(
            '/recharge/get/' + sessionStorage.getItem('id'),
            function (res) {
                renderTicketList(res.content);
            },
            function (error) {
                alert(error);
            });
    }

    // TODO:填空
    function renderTicketList(list) {
        var ticketsBodyStr = "";

        list.forEach(function (recharge) {
            ticketsBodyStr +=
                "<tr>" +
                "<td>" + recharge.balance + "</td>" +
                "<td>" + formatdate(new Date(recharge.time))  + "</td>" +
                "</tr>"
        });

        $("#recharge-body").html(ticketsBodyStr);


        function formatdate(date) {
            var month = date.getMonth() + 1 + '';
            var day = date.getDate() + '';
            month.length === 1 && (month = '0' + month)
            day.length === 1 && (day = '0' + day)
            return month + '月' + day + '日';
        }

        function isValidatedlyWithdraw(ticket) {
            if (ticket.state !== "已完成") {
                return false;
            }
            var date = new Date();
            if (date > new Date(ticket.schedule.startTime)) {
                return false
            }
            return true;
        }



    }}

);