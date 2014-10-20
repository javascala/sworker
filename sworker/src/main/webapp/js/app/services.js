'use strict';

angular.module('swokerService', ['ngResource']).
        factory('Service', function ($resource) {
            return $resource('/activity/:id', {}, {
            });
        });
