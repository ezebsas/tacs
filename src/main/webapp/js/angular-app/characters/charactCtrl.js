angular.module('tacsthree.char')
    .controller("charactCtrl", 
      function ($scope,$resource, $routeParams) {
        Post = $resource('/tacsthree/api/v1/characters/:idMarvel', { idMarvel: '@idMarvel',format: 'json' },
         { 
            delete_user: {
                method: 'DELETE'
            }
         });

        $scope.persona = Post.get({idMarvel: $routeParams.idMarvel});
      }
  );
