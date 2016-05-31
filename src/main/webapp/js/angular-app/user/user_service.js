angular.module('tacsthree.user').factory('UsersService',
  ['$resource', 'User', 'CommonService', '$http', function($resource, User, CommonService, $http) {
  'use strict';
  
  this.api = $resource('/tacsthree/api/v1/users/:id', {
    id: '@id', format: 'json'
  }, {
    update: { method: 'PUT' },
    'delete': { method: 'DELETE' }
  });

  this.buildElement = function(attributes) {
    var user = new User(attributes);
    return user;
  }

  this.getParams = function(params){
    return {user: params};
  }

  function login(params) {
    return $http(
      {
        method:'POST',
        url: '/tacsthree/api/v1/login',
        data: params,
        headers: {'Content-Type' : 'application/x-www-form-urlencoded'}
      });
  }
  
  return {
    query: angular.bind(this, CommonService.query),
    create : angular.bind(this, CommonService.create),
    update : angular.bind(this, CommonService.update),
    destroy : angular.bind(this, CommonService.destroy),
    login: login
  }
}]);
