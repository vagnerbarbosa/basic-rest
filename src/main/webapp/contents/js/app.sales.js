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
        var response =  $http({
            method: 'GET',
            url: 'http://192.168.18.250:8080/sales-weather/rest/sales/', 
            headers: {'Access-Control-Allow-Origin': '*',
                      'Access-Control-Allow-Methods':'GET',
                      'Access-Control-Request-Method': '*',
                      'Access-Control-Allow-Headers': 'Origin'}

        });
        response.success(function (data) {
            $scope.sales = data;
            console.log("[main] # of items: " + data.length);
            angular.forEach(data, function (element) {
                console.log("[main] sale: " + element.branchNumber);
            });

            //Totalizadores

            $scope.calcProdSaleTotal = function () {
                var total = 0;
                angular.forEach($scope.sales, function (element) {
                    total = total + (element.prodSaleTotal);
                });
                return total;
            };

            $scope.calcProdDevolutionTotal = function () {
                var total = 0;
                angular.forEach($scope.sales, function (element) {
                    total = total + (element.prodDevolutionTotal);
                });
                return total;
            };

            $scope.calcTotalservTotalSale = function () {
                var total = 0;
                angular.forEach($scope.sales, function (element) {
                    total = total + (element.servSaleTotal);
                });
                return total;
            };

            $scope.calcTotalservTotalDevolution = function () {
                var total = 0;
                angular.forEach($scope.sales, function (element) {
                    total = total + (element.servDevolutionTotal);
                });
                return total;
            };

            $scope.calcTotalTicket = function () {
                var total = 0;
                angular.forEach($scope.sales, function (element) {
                    total = total + (element.servAverageTicket + element.prodAverageTicket);
                });
                return total;
            };

            $scope.calcTotalSales = function () {
                var total = 0;
                angular.forEach($scope.sales, function (element) {
                    total = total + (element.prodBalanceTotal + element.servBalanceTotal);
                });
                return total;
            };

        });
        response.error(function (status) {
            console.log("AJAX failed to get data, status=" + status);
        });
    }, 10000);

});


