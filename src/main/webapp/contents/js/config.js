(function() {
  'use strict';

  angular.module('sales-app')
    .config(function($routeProvider, $locationProvider, $httpProvider) {
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
    $httpProvider.defaults.headers.common["Accept"] = "application/json";
    $httpProvider.defaults.headers.common["Content-Type"] = "application/json";        
         
      $routeProvider
        .when('/', {
          title : 'Lojão Rio do Peixe',
          templateUrl: 'user/home.html',
          controller: 'UserLoginController'
        })      
        .when('/vendas-tabelas', {
          title : 'Lojão Rio do Peixe - Termômetro de Vendas',  
          templateUrl: 'user/sales.html',
          controller: 'SalesWeatherTableController'
        })
        .when('/vendas-graficos', {
          title : 'Lojão Rio do Peixe - Termômetro de Vendas',  
          templateUrl: 'user/sales-chart.html',
          controller: 'SalesWeatherChartsController'
        })        
        .when('/animais/:id', {
          templateUrl: 'detalhe.html',
          controller: 'AnimalDetalheController'
        }).otherwise({
          redirectTo: '/'
        });
    });
})();