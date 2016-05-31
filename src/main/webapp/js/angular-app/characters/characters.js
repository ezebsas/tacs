angular.module('tacsthree.characters').factory('Characters', function(){

  'use strict';

  var INSTANCE_PROPERTIES = ['idMarvel', 'name', 'description', 'thumbnailUrl','urls'
        ,'comics', 'stories', 'events', 'series'];

  function Characters(attributes) {
    _.merge(this, _.pick(attributes, INSTANCE_PROPERTIES));
    this.fullname = this.firstname +' '+ this.lastname;
  }

  Characters.prototype.initialize = function() {
  };

  Characters.prototype.removeExtraParams = function() {
    return _.pick(this, INSTANCE_PROPERTIES);
  }

  return Characters;
});
