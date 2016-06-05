angular.module('tacsthree.user').controller('LoginController', 
['$scope', 'UsersService', '$http',
function($scope, UsersService, $http) {
  'use strict';

  $scope.username = "" ;
  $scope.password = "" ;

  $scope.loginForm = function(){
    console.log($scope.username);
    console.log($scope.password);
    var encodedString = 'username=' +
            encodeURIComponent($scope.username) +
            '&password=' +
            encodeURIComponent($scope.password);
    UsersService.login(encodedString)
      .success(
        function(authToken){
          $http.defaults.headers.common['Authorization'] = 'Bearer ' + authToken.token;
          alert('Login successful');
        })
      .error(function(response){
          alert('Login Error');
          $scope.password = "" ;
       });
  };
}]);