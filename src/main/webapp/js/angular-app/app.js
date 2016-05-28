angular.module('tacsthree.common', []);
angular.module('tacsthree.user', ['ngResource', 'ui.bootstrap', 'tacsthree.common']);
angular.module('tacsthree.characters', ['ngResource', 'ui.bootstrap', 'tacsthree.common']);
angular.module('tacsthree.char', ['ngResource']);

var tacsthreeApp = angular.module('tacsthree', [
  'tacsthree.common',
  'tacsthree.user',
  'tacsthree.characters',
  'tacsthree.char',
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
      controller: 'CharactersControllers'
    })
    .when('/characters/:idMarvel', {
      templateUrl: 'templates/characters_detail.html',
      controller: 'charactCtrl'
    })
    .otherwise({
      redirectTo: '/'
    });
}]);
