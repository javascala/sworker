'use strict';

function ListController($scope, $location, Service) {
    $scope.lists = Service.query();
    console.log($scope.lists);

    $scope.gotoNewPage = function () {
        $location.path("/swoker/new");
    };
}

function NewController($scope, $location, Service) {

    $scope.gotoListPage = function () {
        $location.path("/swoker/list");
    };

    $scope.submit = function () {
        Service.save($scope.entity, function (entity) {
            debugger;
            $location.path('/swoker/list');
        });
    };
}
