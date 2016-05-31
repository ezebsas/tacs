angular.module('tacsthree.common', []);
angular.module('tacsthree.user', ['ngResource', 'ui.bootstrap', 'tacsthree.common']);
angular.module('tacsthree.characters', ['ngResource', 'ui.bootstrap', 'tacsthree.common']);
angular.module('tacsthree.char', ['ngResource']);
angular.module('tacsthree.abm_user', ['ngResource']);
angular.module('tacsthree.abm_group', ['ngResource']);

var tacsthreeApp = angular.module('tacsthree', [
  'tacsthree.common',
  'tacsthree.user',
  'tacsthree.abm_user',
  'tacsthree.characters',
  'tacsthree.char',
  'tacsthree.abm_group',
  'ngResource',
  'ngRoute',
  'ui.bootstrap'
]);

tacsthreeApp.config(
  ['$httpProvider',
  function($httpProvider) {
    $httpProvider.defaults.headers.common.Accept = 'application/json, text/plain, */*';
  }
]);

tacsthreeApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider
    .when('/users', {
      templateUrl: 'templates/users.html',
      controller: 'UsersController'
    })
    .when('/users/new', {
      templateUrl: 'templates/create_user.html',
      controller: 'AbmCtrl'
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
    .when('/groups', {
      templateUrl: 'templates/groups.html',
      controller: 'GroupsControllers'
    })
    .when('/login',{
    	templateUrl: 'templates/login.html',
    	controller: 'LoginController'
    })
    .otherwise({
      redirectTo: '/'
    });
}]);
