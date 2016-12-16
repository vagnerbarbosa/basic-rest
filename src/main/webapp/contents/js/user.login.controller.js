(function() {
    'use strict';

    angular.module('sales-app')
      .controller('UserLoginController', UserLoginController);

    UserLoginController.$inject = ['$scope', 'AnimalFactory'];

    function UserLoginController($scope, AnimalFactory) {
      $scope.listar = function() {
        return AnimalFactory.listar();
      };
    }

})();