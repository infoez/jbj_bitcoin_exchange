angular
    .module('inspinia')
    .directive('equalTo', function ($rootScope, $timeout) {
        return {
            require: "ngModel",
            scope: {
                compareTarget: "=equalTo"
            },
            link: function (scope, element, attributes, ngModel) {

                ngModel.$validators.compareTo = function (modelValue) {
                    return modelValue == scope.compareTarget;
                };

                scope.$watch("compareTarget", function () {
                    ngModel.$validate();
                });
            }
        }
    })
    .directive('numberFormat', function($filter){
        return {
            replace: false,
            restrict: "A",
            require: "?ngModel",
            link: function(scope, element, attrs, ngModel) {
                var numberFormat;
                if (!ngModel) {
                    return;
                }
                ngModel.$render = function() {
                    return element.val(ngModel.$viewValue);
                };
                var numberFilter = $filter('number');
                return ngModel.$formatters.push(function(value) {
                    return numberFilter(value);
                });
            }
        };
    })

