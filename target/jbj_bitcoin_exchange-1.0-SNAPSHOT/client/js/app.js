/**
 * INSPINIA - Responsive Admin Theme
 *
 */
(function () {
    angular.module('inspinia', [
        'ui.router',                    // Routing
        'oc.lazyLoad',                  // ocLazyLoad
        'ui.bootstrap',                 // Ui Bootstrap
        'ngIdle',                       // Idle timer
        'ngSanitize',                    // ngSanitize
        'ngAnimate',
        'ngCookies',
        'ngToast',
/*        'ngMaterial'*/
    ])
})();

// Other libraries are loaded dynamically in the config.js file using the library ocLazyLoad