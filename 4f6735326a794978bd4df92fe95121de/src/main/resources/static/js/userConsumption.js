$(document).ready(function () {
    getRechargeList();

    function getRechargeList() {
        getRequest(
            '/consumption/' + sessionStorage.getItem('id'),
            function (res) {
                renderConsumptionList(res.content);
            },
            function (error) {
                alert(error);
            });
    }

    // TODO:填空
    function renderConsumptionList(list) {
        var ticketsBodyStr = "";

        list.forEach(function (recharge) {
            ticketsBodyStr +=
                "<tr>" +
                "<td>" + recharge.way + "</td>" +
                "<td>" + -recharge.balance+ "</td>" +
                "<td>" + formatdate(new Date(recharge.time))  + "</td>" +
                "</tr>"
        });

        $("#consumption-body").html(ticketsBodyStr);


        function formatdate(date) {
            var month = date.getMonth() + 1 + '';
            var day = date.getDate() + '';
            month.length === 1 && (month = '0' + month)
            day.length === 1 && (day = '0' + day)
            return month + '月' + day + '日';
        }




    }}

);