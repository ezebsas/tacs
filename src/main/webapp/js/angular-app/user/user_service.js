angular.module('tacsthree.user').factory('UsersService',
  ['$resource', 'User', 'CommonService', function($resource, User, CommonService) {
  'use strict';
  
  this.api = $resource('/tacsthree/api/v1/users/:id', {
    id: '@id', format: 'json'
  }, {
    update: { method: 'PUT' },
    'delete': {method: 'DELETE'}    
  });

  this.buildElement = function(attributes) {
    var user = new User(attributes);
    return user;
  }

  this.getParams = function(params){
    return {user: params};
  }
  
  return {
    query: angular.bind(this, CommonService.query),
    create : angular.bind(this, CommonService.create),
    update : angular.bind(this, CommonService.update),
    destroy : angular.bind(this, CommonService.destroy)
  }
}]);
