// Este controller está pensado para ser incluido en otros controllers. Los controllers en los que se incluya el mismo deben poseer como
// mínimo los siguientes atributos en su scope:
// - elements: Lista donde se guardarán los elementos controlados por el common controller.
// - model: Modelo al cual pertenecen esos elementos.
// - service: Service asociado al modelo de ese controller.
// - currentPage: Nombre de la página central que maneja el controller.

// El common controller se encarga de:
// - Manejar los forms con los cuales se van a modificar o crear elementos.
// - Comunicarse con el service asociado para cargar los elementos y para eliminar un elemento.
// - Inicializar la página principal que maneja el controller, cargando los elementos o redirigiendo a otra página si el usuario no tiene
//    permisos para acceder.

// Para incluirlo en un controller, se debe agregar lo siguiente:
// $controller('CommonController', {$scope: $scope, elements: $scope.elements});
angular.module('tacsthree.common').controller('CommonController',
['$scope', 'CommonService', 'elements', function($scope, CommonService, elements) {
  'use strict';

  $scope.query = function(service, elements){
    service.query().then(
      function onSuccess(_elements){
        elements.push.apply(elements, _elements);
      },
      function onFail(){
      }
    );
  }

  $scope.destroy = function(element){
    $scope.service.destroy(element.id).then(
      function onSuccess() {
        elements.splice(elements.indexOf(element), 1);
      },
      function onFail() {
      }
    );
  }

  $scope.query($scope.service, elements);
}]);
