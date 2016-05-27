angular.module('tacsthree.characters').factory('CharactersService',
  ['$resource', 'Characters', 'CommonService', function($resource, Characters, CommonService) {
  'use strict';
  
  this.api = $resource('/tacsthree/api/v1/characters/', {
    format: 'json'
  }, {
    get: { method: 'GET' }
  });

  this.buildElement = function(attributes) {
    var characters = new Characters(attributes);
    return characters;
  }

  this.getParams = function(params){
    return {characters: params};
  }
  
  return {
    query: angular.bind(this, CommonService.query),
  }
}]);
