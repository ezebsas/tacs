angular.module('tacsthree.characters').controller('CharactersControllers',
['$scope', 'CharactersService', '$controller', 'Characters',
function($scope, CharactersService, $controller, Characters) {
  'use strict';
  $scope.service = CharactersService;
  $scope.currentPage = 'characters';
  $scope.characters = [];
  $scope.model = Characters;

  $controller('CommonController', {$scope: $scope, elements: $scope.characters});

}]); 
