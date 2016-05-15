angular.module('tacsthree.user').factory('User', function(){

  'use strict';

  var INSTANCE_PROPERTIES = ['id', 'name'];

  function User(attributes) {
    _.merge(this, _.pick(attributes, INSTANCE_PROPERTIES));
    this.fullname = this.firstname +' '+ this.lastname;
  }

  User.prototype.initialize = function() {
  };

  User.prototype.removeExtraParams = function() {
    return _.pick(this, INSTANCE_PROPERTIES);
  }

  return User;
});