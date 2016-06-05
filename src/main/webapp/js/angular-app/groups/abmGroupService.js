// Invocar modo JavaScript 'strict'
'use strict';

// Crear el service 'articles'
angular.module('tacsthree.abm_group').factory('abmGroupService', ['$resource', function($resource) {
	// Usar el service '$resource' para devolver un objeto '$resource' article
    return $resource('/tacsthree/api/v1/groups/:id', {
        articleId: '@_id'
    }, {
        update: {
            method: 'PUT'
        }
    });
}]);

