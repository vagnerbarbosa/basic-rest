var app = angular.module('refresh_div', []);

app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
        $httpProvider.defaults.headers.common["Accept"] = "application/json";
        $httpProvider.defaults.headers.common["Content-Type"] = "application/json";
    }
]);

app.controller('refresh_control', function ($scope, $interval, $http, $filter) {
    $interval(function () {
        var response = $http.get('http://localhost:8080/sales-weather/rest/vendas/');
        response.success(function (data) {
            $scope.sales = data;
            console.log("[main] # of items: " + data.length);
            angular.forEach(data, function (element) {
                console.log("[main] sale: " + element.numerofilial);
            });

            //Totalizadores

            $scope.calcProdTotalVenda = function () {
                var total = 0;
                angular.forEach($scope.sales, function (element) {
                    total = total + (element.prodTotalVenda);
                });
                return total;
            };

            $scope.calcProdTotalDevolvido = function () {
                var total = 0;
                angular.forEach($scope.sales, function (element) {
                    total = total + (element.prodTotalDevolvido);
                });
                return total;
            };

            $scope.calcTotalservTotalVenda = function () {
                var total = 0;
                angular.forEach($scope.sales, function (element) {
                    total = total + (element.servTotalVenda);
                });
                return total;
            };

            $scope.calcTotalservTotalDevolvido = function () {
                var total = 0;
                angular.forEach($scope.sales, function (element) {
                    total = total + (element.servTotalDevolvido);
                });
                return total;
            };

            $scope.calcTotalTicket = function () {
                var total = 0;
                angular.forEach($scope.sales, function (element) {
                    total = total + (element.servTicketMedio + element.prodTicketMedio);
                });
                return total;
            };

            $scope.calcTotalSales = function () {
                var total = 0;
                angular.forEach($scope.sales, function (element) {
                    total = total + (element.prodSaldoTotal + element.servSaldoTotal);
                });
                return total;
            };

        });
        response.error(function (status) {
            console.log("AJAX failed to get data, status=" + status);
        });
    }, 10000);

});


