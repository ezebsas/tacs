angular.module('tacsthree.common').factory('CommonService', function() {
  'use strict';
  function query(params) {
    return this.api.query().$promise.then(angular.bind(this, buildElements));
  }

  function get(id){
    return this.api.get({id: id}).$promise.then(this.buildElement);
  }

  function create(params) {
    return this.api.save( this.getParams(params) ).$promise.then(this.buildElement);
  }

  function update(id, params) {
    return this.api.update({ id: id }, this.getParams(params) ).$promise.then(this.buildElement);
  }

  function destroy(id) {
    return this.api['delete']({ id: id }).$promise;
  }

  function buildElements(attributes) {
    return _.map(attributes, this.buildElement);
  }

  return {
    query : query,
    get : get,
    create : create,
    update : update,
    destroy : destroy
  };
});
