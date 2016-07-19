var app = angular.module('refresh_div', []);

app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
        $httpProvider.defaults.headers.common["Accept"] = "application/json";
        $httpProvider.defaults.headers.common["Content-Type"] = "application/json";
    }
]);

app.controller('refresh_control', function ($scope, $interval, $http) {
    $interval(function () {
        var response = $http.get('http://localhost:8080/sales-weather/rest/vendas/');
        response.success(function (data) {
            $scope.sales = data;
            console.log("[main] # of items: " + data.length);
            angular.forEach(data, function (element) {
                console.log("[main] sale: " + element.numeroFilial);
            });
        });
        response.error(function (status) {
            alert("AJAX failed to get data, status=" + status);
        });
    }, 1000);
});
