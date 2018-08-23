function config($stateProvider, $urlRouterProvider, $ocLazyLoadProvider, IdleProvider, ngToastProvider) {

    IdleProvider.idle(5);
    IdleProvider.timeout(120);

    $urlRouterProvider.otherwise("/welcome");

    $ocLazyLoadProvider.config({
        debug: true
    });

    $stateProvider

        .state('main', {
            abstract: true,
            url: "",
            templateUrl: "client/views/main.html",
            resolve: {
                init: function ($preResolve, $server, $account, $socket) {
                    return $preResolve.tokenPreProcessing().then(function () {
                        return $server.post({
                            url: "/account/tokenValidation.do",
                            params: $account.getAccount()
                        })
                    }).then(function (response) {
                        $account.setAccount(response.account);
                        $socket.connect($account.getAccount());
                    }).catch(function () {
                        $account.setDefaultAccountState();
                        $socket.connect($account.getAccount());
                    })
                },
                orders: function ($server, $panel) {
                    $server.post({
                        url: '/bitcoin/orders.do',
                        params: {}
                    }).then(function (response) {
                        $panel.setOrders(response.bitcoin)
                    })
                    /*.catch(function () {
                                            this.orders();
                                        })*/
                }
            }
        })
        .state('main.welcome', {
            url: "/welcome",
            templateUrl: "client/views/welcome.html"

        })
        .state('main.registration', {
            url: "/registration",
            templateUrl: "client/views/registration.html"
        })
        .state('main.login', {
            url: "/login",
            templateUrl: "client/views/login.html"
        })
        .state('main.tap', {
            url: "/tap",
            templateUrl: "client/views/taptest.html"
        })
        .state('main.calculator', {
            url: "/calculator",
            templateUrl: "client/views/calculator.html"
        })
    ngToastProvider.configure({
        animation: 'fade'
    })
}

angular
    .module('inspinia')
    .config(config)
    .run(function ($rootScope, $state) {
        $rootScope.$state = $state;
    });
