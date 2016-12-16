(function () {
    'use strict';

    angular.module('sales-app')
            .controller('SalesWeatherChartsController', SalesWeatherChartsController);

    SalesWeatherChartsController.$inject = ['$scope', '$http'];

    function SalesWeatherChartsController($scope, $http) {

        var response = $http({
            method: 'GET',
            url: 'http://localhost:8080/sales-weather/webservice/sales/',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept, Client-Offset, Authorization',
                'Access-Control-Allow-Credentials': 'true'
                    }
        });
        response.success(function (data) {
            $scope.sales = data;
            $scope.length = data.length;
            console.log("[main] # of items: " + data.length);
            angular.forEach(data, function (element) {
                console.log("[main] sale: " + element.branchNumber);
                $scope.addValues(element.balanceTotal);
            });
        });

        $scope.myJson = {
            type: "vbar3d",
            "scale-y": {
                "min-value": "0",
                "max-value": "39999",
                "format": "R$ %v",
                "negation": "currency",
                "line-width": "400px"
            },
            plot: {
                "alpha": 0.7,
                "border-width": 2,
                "border-color": "black",
                "border-radius-top-left": 5,
                "border-radius-top-right": 5,
                "aspect": "cylinder",
                hoverState: {
                    "backgroundColor": "#909090"
                },
                "animation": {
                    delay: 5,
                    effect: 4,
                    method: 0,
                    sequence: 1,
                    speed: 4000

                },

            },
            "scale-x": {
                "label": {/* Scale Title */
                    "text": "Filiais",
                },
                "labels": ["F01", "F03", "F04", "F05", "F07", "F08", "F09", "F10", "F11", "F12", "F13", "F14", "F15", "F16", "F17", "F18", "F19", "F20", "F21", "F24"],
            },
            animation: {
                "delay": 500,
                "effect": "ANIMATION_SLIDE_BOTTOM"
            },
            title: {
                backgroundColor: "transparent",
                fontColor: "black",
                text: ""
            },
            backgroundColor: "white",
            series: [
                {
                    values: [],
                    backgroundColor: "#1E90FF"
                }
            ],
            source: {
                text: "Fonte: T.I. Lojão Rio do Peixe"
            },
            noData: {
                text: "Carregando dados...",
                backgroundColor: "#1E90FF",
                fontSize: 18,
                textAlpha: .9,
                alpha: .6,
                bold: true
            },
        };

        $scope.addValues = function (value) {
            var val = value;
            console.log(val);
            $scope.myJson.series[0].values.push(val);

        };


    }
    ;


})();