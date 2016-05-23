angular.module('tacsthree.common', []);
angular.module('tacsthree.user', ['ngResource', 'ui.bootstrap', 'tacsthree.common']);
angular.module('tacsthree.characters', []);

var tacsthreeApp = angular.module('tacsthree', [
  'tacsthree.common',
  'tacsthree.user',
  'tacsthree.characters',
  'ngResource',
  'ngRoute',
  'ui.bootstrap'
]);

tacsthreeApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider
    .when('/users', {
      templateUrl: 'templates/users.html',
      controller: 'UsersController'
    })
    .when('/', {
      templateUrl: 'templates/home.html',
    })
    .when('', {
      templateUrl: 'templates/home.html',
    })
    .when('/characters', {
      templateUrl: 'templates/characters.html',
      controller: 'charactersCtrl'
    })
    .otherwise({
      redirectTo: '/'
    });
}]);
