angular.module( 'tacsthree.login', []).controller('loginController',['$scope','$http', function($scope, $http)
{
$scope.username = "" ;
$scope.password = "" ;

$scope.loginForm = function(){
    alert("login controller called");
    console.log($scope.username);
    console.log($scope.password);
    var encodedString = 'username=' +
            encodeURIComponent($scope.username) +
            '&password=' +
            encodeURIComponent($scope.password);
    $http({
        method:'POST',
        url: '/tacsthree/api/v1/login',
        data: encodedString,
        headers: {'Content-Type' : 'application/x-www-form-urlencoded'}
    });
};
}]);