angular.module('tacsthree.user').controller('LoginController', 
['$scope', 'UsersService', '$controller',
function($scope, UsersService, $controller) {
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
          debugger;
          angular.module('tacsthree').config(
          ['$httpProvider', function($httpProvider) { 
            $httpProvider.defaults.headers.common.Authorize = 'Bearer ' + authToken;
          }]);
          console.log('Logged with token: ' + authToken.token);
        })
      .error(function(response){ debugger; });
  };
}]);