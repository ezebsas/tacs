// Invocar modo JavaScript 'strict'
'use strict';

// Crear el controller 'articles'
angular.module('tacsthree.abm_group').controller('abmGroupCtrl', ['$scope', '$routeParams', 'abmGroupService',
    function($scope, $routeParams, GroupsService) {

 // Crear un nuevo método controller para crear nuevos articles
        $scope.create = function() {
            // Usar los campos form para crear un nuevo objeto $resource article
            var group = new GroupsService({
            	
            	id: "5709b8799a96331925075398",
                name: this.name,
                characters: [{"id":this.personaje.id,"idMarvel":this.personaje.idMarvel,"name":this.personaje.name,"description":this.personaje.description,"modified":this.personaje.modified,"resourceURI":this.personaje.resourceURI,"urls":this.personaje.urls,"thumbnailUrl":this.personaje.thumbnailUrl,"comics":this.personaje.comics,"stories":this.personaje.stories,"events":this.personaje.events,"series":this.personaje.series}]
             
              //
            });

            // Usar el método '$save' de article para enviar una petición POST apropiada
            group.$save(function(response) {
            }, function(errorResponse) {
                // En otro caso, presentar al usuario el mensaje de error
                $scope.error = errorResponse.data.message;
            });
        };



// Crear un nuevo método controller para borrar un único artículo

    }
]);

