angular.module('tacsthree.user').controller('UsersController', 
['$scope', 'UsersService', '$controller', 'User',
function($scope, UsersService, $controller, User) {
  'use strict';

  $scope.service = UsersService;
  $scope.currentPage = 'users';
  $scope.users = [];
  $scope.model = User;

  $controller('CommonController', {$scope: $scope, elements: $scope.users});
}]);
