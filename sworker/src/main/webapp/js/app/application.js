'use strict';

angular.module('sworker', ['swokerService']).
        config(['$routeProvider', function ($routeProvider) {
    $routeProvider.
            when('/sworker/list', {templateUrl:'list.html', controller:ListController}).
            when('/swoker/new', {templateUrl:'new.html', controller:NewController}).
            otherwise({redirectTo:'/sworker/list'});
}]);
