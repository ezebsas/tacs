angular.module('tacsthree.abm_group').factory('GroupsService',
  ['$resource', 'Groups', 'CommonService', function($resource, Groups, CommonService) {
  'use strict';
  
  this.api = $resource('/tacsthree/api/v1/groups/:id', {
    id: '@id',format: 'json'
  });

  this.buildElement = function(attributes) {
    var groups = new Groups(attributes);
    return groups;
  }

  this.getParams = function(params){
    return {groups: params};
  }
  
  return {
    query: angular.bind(this, CommonService.query),
  }
}]);
