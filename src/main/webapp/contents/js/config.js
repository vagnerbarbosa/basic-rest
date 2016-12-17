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
          resolve: {  
          $title : function() { return 'Lojão Rio do Peixe'; }
              },
          templateUrl: 'user/home.html',
          controller: 'UserLoginController'
        })      
        .when('/vendas-tabelas', {
          resolve:  {  
          $title : function() { return 'Lojão Rio do Peixe - Termômetro de Vendas'; }
              },
          templateUrl: 'user/sales.html',
          controller: 'SalesWeatherTableController'
        })
        .when('/vendas-graficos', {
          resolve: {            
          $title : function() { return 'Lojão Rio do Peixe - Termômetro de Vendas'; } 
              },          
          templateUrl: 'user/sales-chart.html',
          controller: 'SalesWeatherChartsController'
        })        
        .when('/acompanhamento-vendas', {
          resolve: {            
          $title : function() { return 'Lojão Rio do Peixe - Acompanhamento de Vendas'; }
              },            
          templateUrl: 'user/sales-order.html',
          controller: 'SalesOrderController'
        }).otherwise({
          redirectTo: '/'
        });
    });
})();