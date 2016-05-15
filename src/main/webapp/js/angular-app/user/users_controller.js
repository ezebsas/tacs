angular.module('tacsthree.user').controller('UsersController', 
['$scope', 'UserService', '$controller', 'User',
function($scope, UserService, $controller, User) {
  'use strict';

  $scope.service = UserService;
  $scope.currentPage = 'users';
  $scope.users = [];
  $scope.model = User;

  $controller('CommonController', {$scope: $scope, elements: $scope.users});
}]);
