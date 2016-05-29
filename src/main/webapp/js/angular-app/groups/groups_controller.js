angular.module('tacsthree.abm_group').controller('GroupsControllers',
['$scope', 'GroupsService', '$controller', 'Groups',
function($scope, GroupsService, $controller, Groups) {
  'use strict';
  $scope.service = GroupsService;
  $scope.currentPage = 'groups';
  $scope.groups = [];
  $scope.model = Groups;

  $controller('CommonController', {$scope: $scope, elements: $scope.groups});

}]); 
