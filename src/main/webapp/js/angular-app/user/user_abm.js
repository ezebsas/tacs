// Invocar modo JavaScript 'strict'
'use strict';

// Crear el controller 'articles'
angular.module('tacsthree.abm_user').controller('AbmCtrl', ['$scope', '$routeParams', 'AbmService',
    function($scope, $routeParams, UsersService) {

 // Crear un nuevo método controller para crear nuevos articles
        $scope.create = function() {
            // Usar los campos form para crear un nuevo objeto $resource article
            var user = new UsersService({
                // titulo: this.titulo,
                // contenido: this.contenido
              //
              
                id: "5709b8799a96331925075308",
                name: "Carlos",
                characters: [],
                groups: [],
                groupsHistoric: 0
             
              //
            });

            // Usar el método '$save' de article para enviar una petición POST apropiada
            user.$save(function(response) {
            }, function(errorResponse) {
                // En otro caso, presentar al usuario el mensaje de error
                $scope.error = errorResponse.data.message;
            });
        };



// Crear un nuevo método controller para borrar un único artículo

    }
]);

