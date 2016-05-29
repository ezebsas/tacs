// Invocar modo JavaScript 'strict'
'use strict';

// Crear el service 'articles'
angular.module('tacsthree.abm_user').factory('AbmService', ['$resource', function($resource) {
	// Usar el service '$resource' para devolver un objeto '$resource' article
    return $resource('/tacsthree/api/v1/users/:id', {
        articleId: '@_id'
    }, {
        update: {
            method: 'PUT'
        }
    });
}]);

