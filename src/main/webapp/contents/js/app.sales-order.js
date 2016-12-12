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
            url: 'http://localhost:8080/sales-weather/webservice/sales-order/1', 
            headers: {'Access-Control-Allow-Origin': '*',                                            
                      'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept, Client-Offset, Authorization',
                      'Access-Control-Allow-Credentials':'true',
                      'Authorization': "55d5927329415b000100003b63a9e1b480b64a1040a902a26da862d1"}

        });
        response.success(function (data) {            
            $scope.sales = data;
            console.log("[main] # of items: " + data.length);
            angular.forEach(data, function (element) {
                if (element.itemSituation === 'Fechado') {
                    element.itemSituation = 'A Faturar';
                } else if (element.itemSituation === 'Baixado') {
                    element.itemSituation = 'Faturado';
                }
                if (element.idDeliverySituation === 'Aguardando Entrega') {
                    element.idDeliverySituation = 'Entrega Pendente';
                }
                if (element.idMontageSituation === 'A Executar') {
                    element.idMontageSituation = 'Montagem Pendente';
                }                
                console.log("[main] sale: " + element.itemSituation);
            });

        });
        response.error(function (status) {
            console.log("AJAX failed to get data, status=" + status);
        });
    }, 10000);

});


