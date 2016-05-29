angular.module('tacsthree.abm_group').factory('Groups', function(){

  'use strict';

  var INSTANCE_PROPERTIES = ['name', 'characters'];

  function Groups(attributes) {
    _.merge(this, _.pick(attributes, INSTANCE_PROPERTIES));
  }

  Groups.prototype.initialize = function() {
  };

  Groups.prototype.removeExtraParams = function() {
    return _.pick(this, INSTANCE_PROPERTIES);
  }

  return Groups;
});
