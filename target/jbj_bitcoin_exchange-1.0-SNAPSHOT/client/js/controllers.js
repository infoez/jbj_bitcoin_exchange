angular
    .module('inspinia')
    .service('$util', function () {
        this.isNull = function (o) {
            return (o === null || o === undefined)
        }

        this.isNotNull = function (o) {
            return !this.isNull(o);
        }

        this.ifNull = function (a, b) {
            if (this.isNull(a)) {
                return b;
            } else {
                return a;
            }
        }


    })
    .service('$server', function ($http, $ngToast, $q) {
        this.post = function (o) {
            var deferred = $q.defer();
            $http({
                method: 'POST',
                url: o.url,
                data: o.params,
                header: {'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'}
            }).success(function (response) {
                if (response.result_YN) {
                    deferred.resolve(response.params)
                } else {
                    $ngToast.pop({
                        strong: response.errorCode,
                        message: response.errorMessage,
                        className: 'warning'
                    })
                }
            }).catch(function (ex) {
                deferred.reject(ex)
            })
            return deferred.promise;
        }
    })
    .service('$main', function ($http, $state, $q) {

    })
    .service('$panel', function () {

        this.orders = {
            asks: null,
            bids: null
        }

        this.getOrders = function () {
            return this.orders;
        }

        this.setOrders = function (object) {
            var keys = Object.keys(object);
            for (var i = 0; i < keys.length; i++) {
                this.orders[keys[i]] = object[keys[i]];
            }
        }

    })
    .service('$socket', function ($rootScope, $interval, $panel, $ngToast, $q, $timeout) {

        var $socket = this;
        $socket.client = {};

        $socket.subscribeOrders = function () {
            var deferred = $q.defer();
            $socket.client.oSubs = $socket.client.subscribe('/push/orders', function (message) {
                var json = JSON.parse(message.body);
                var orders = json['bitcoin'];
                $panel.setOrders(orders);
                $rootScope.$digest();
            });
        }

        $socket.subscribeTransaction = function (account) {
            if (account.token !== undefined && account.email !== undefined) {
                $socket.client.tSubs = this.client.subscribe('/push/transaction/' + account.email,
                    function (message) { // success
                        /*var json = JSON.parse(message.body);
                        $ngToast.pop({
                            strong: '체결',
                            message: json.message,
                            className: 'success'
                        })
                        self.setAccount(json.account);*/
                        console.log(message);
                    }, {'token': account.token});
            }
        }

        $socket.unsubscribeTransaction = function () {
            if ($socket.client.connected && $socket.client.tSubs !== undefined) {
                $socket.client.tSubs.unsubscribe();
            }
        }

        $socket.sockjs = function () {
            var deferred = $q.defer();
            $socket.client = Stomp.over(new SockJS('/endpoint'));
            $socket.client.connect({}, deferred.resolve, deferred.reject);
            return deferred.promise
        }

        $socket.connect = function (account) {
            $socket.sockjs().then(function () {
                $socket.subscribeOrders();
                if (account.signed)
                    $socket.subscribeTransaction(account);
            }, function () {
                $timeout(function () {
                    $socket.connect();
                }, 500);
            });
        }

        /*var socket = this;
        socket.coinType = 'bitcoin';

        socket.ws = new WebSocket("ws://localhost:8080/orders.do?");

        socket.ws.onopen = function () {
            _send(socket.coinType);
        }

        socket.ws.onmessage = function (MessageEvent) {
            var json = JSON.parse(MessageEvent.data);
            var orders = json[socket.coinType];
            $panel.setOrders(orders);
            $rootScope.$digest();
        }

        $interval(function () {
            _send(socket.coinType);
        }, 1500)

        var _send = function (msg) {
            socket.ws.send(msg);
        }

        var _wsSetup = function () {
            socket = new WebSocket("ws://localhost:8080/push.do?email=" + account.email + "&" + "token=" + account.token);
            socket.onopen = function () {
                socket.connect = true;
            };
            socket.onmessage = function (e) {
                var json = JSON.parse(e.data);
                $ngToast.pop({
                    strong: account.email,
                    message: json.message,
                    className: 'success'
                })
                self.setAccount(json.account);
            };

        }

        var _wsClose = function () {
            if (socket.connect === true) {
                socket.close();
                socket.connect = false;
            }
        }

        _wsSetup()*/

    })
    .service('$transaction', function ($server, $ngToast, $account) {

        this.bid = function (t) {
            $server.post({
                url: "/bitcoin/bid.do",
                params: t
            }).then(function (response) {
                console.log(response);
                $account.setAccount(response.account);
            });
        }

        this.ask = function (t) {
            $server.post({
                url: "/bitcoin/ask.do",
                params: t
            }).then(function (response) {
                $account.setAccount(response.account);
            });
        }

    })
    .service('$number', function () {
        this.decimalPointCoordinator = function (n, p) {
            var self = this;
            var number = n;
            if (self.isNumber(number) && String(number).indexOf('.') !== -1 && String(number).indexOf('e-') === -1) {
                var arr = String(number).split('.');
                return String(arr[1]).length <= p ? n : arr[0] + '.' + arr[1].slice(0, p);
            } else {
                return n;
            }
        }

        this.isNumber = function (s) {
            s += ''; // 문자열로 변환
            s = s.replace(/^\s*|\s*$/g, ''); // 좌우 공백 제거
            if (s === '' || isNaN(s)) return false;
            return true;
        }
    })
    .service('$account', function ($q, $server, $ngToast, $location, $cookieStore, $socket) {

        this.account = {
            token: undefined,
            signed: false
        };

        this.getAccount = function () {
            return this.account;
        };

        this.setAccount = function (account) {
            var keys = Object.keys(account);
            for (var i = 0; i < keys.length; i++) {
                this.account[keys[i]] = account[keys[i]];
            }
            $cookieStore.put("account", this.account);
        }

        this.doLogin = function (account) {
            var $account = this;

            $server.post({
                url: "/account/login.do",
                params: account
            }).then(function (response) {
                var account = response.account;
                if (account.token !== undefined && account.token !== null) {
                    $cookieStore.put("account", account);
                    $account.setAccount(account);
                    $socket.subscribeTransaction(account);
                    $location.url('/main');
                    $ngToast.pop({
                        strong: account.email,
                        message: ' 님 로그인 하셨습니다.',
                        className: 'success'
                    })
                } else {
                    $ngToast.pop({
                        strong: '계정정보 불일치',
                        message: ' 아이디 및 패스워드를 확인 하세요.',
                        className: 'success'
                    })
                }
            }).catch(function () {
                $ngToast.pop({
                    strong: "통신 불가",
                    message: ' 인터넷 연결 상태를 확인 하세요',
                    className: 'success'
                })
            });
        }

        this.doLogout = function () {
            this.setDefaultAccountState();
            $socket.unsubscribeTransaction();
            $ngToast.pop({
                strong: '로그아웃',
                message: '세션 정보가 제거 되었습니다.',
                className: 'warning'
            })
        }

        this.isSigned = function () {
            return this.account.signed !== false;
        }

        this.setDefaultAccountState = function () {
            var keys = Object.keys(this.account);
            for (var i = 0; i < keys.length; i++) {
                this.account[keys[i]] = "";
            }
            this.account.signed = false;
            this.account.token = undefined;
            $cookieStore.remove("account");
        }

    })
    .service('$ngToast', function (ngToast) {
        this.pop = function (o) {
            ngToast.create({
                content: "<span style='cursor : pointer'><strong>" + o.strong + "</strong> : " + o.message + "</span>",
                className: o.className,
                dismissOnTimeout: true,
                timeout: 5000,
                dismissButton: true,
                dismissOnClick: true,
                newestOnTop: true
            });
        }
    })
    .service('$preResolve', function ($q, $cookieStore, $server, $account, $util) {

        this.tokenPreProcessing = function () {
            var deferred = $q.defer();
            var account = $cookieStore.get("account");
            $util.isNotNull(account) ? $account.setAccount(account) : null;
            if ($account.getAccount().signed) {
                deferred.resolve();
            } else {
                deferred.reject();
            }
            return deferred.promise;
        }

    })

angular
    .module('inspinia')
    .controller('welcomePageController', function ($ngToast, $number, $filter, $scope, $server, $panel, $account, $transaction) {

        $scope.transaction = {
            price: 7000000,
            quantity: 0,
            krwPrice: 0,
            flag: true
        }
        $scope.orders = $panel.getOrders();
        $scope.account = $account.getAccount();

        $scope.krwPriceOnChange = function () {
            var price = $scope.transaction.price;
            var krwPrice = $scope.transaction.krwPrice * 1;
            $scope.transaction.krwPrice = krwPrice.toFixed(0);
            var s = krwPrice / price;
            if (String(s).indexOf('e-') === -1)
                $scope.transaction.quantity = $number.decimalPointCoordinator(krwPrice / price, 8);
        }

        $scope.quantityOnChange = function () {
            var price = $scope.transaction.price;
            var quantity = $number.decimalPointCoordinator($scope.transaction.quantity, 8);
            var krwPrice = price * quantity;
            $scope.transaction.krwPrice = krwPrice.toFixed(0);
            $scope.transaction.quantity = quantity;
        }

        $scope.$watch('transaction.price', function (n, o) {
            var price = n;
            var quantity = $number.decimalPointCoordinator($scope.transaction.quantity, 8);
            var krwPrice = price * quantity;
            $scope.transaction.krwPrice = krwPrice.toFixed(0);
        }, false);

        $scope.setPrice = function (price) {
            $scope.transaction.price = price;
        }

        $scope.bid = function () {
            var account = $account.getAccount();
            $scope.transaction.email = account.email;
            $scope.transaction.token = account.token;
            $scope.transaction.coinAmount = $scope.transaction.quantity;
            $scope.transaction.coinId = 'btc';
            $transaction.bid($scope.transaction);
        }

        $scope.ask = function () {
            var account = $account.getAccount();
            $scope.transaction.email = account.email;
            $scope.transaction.token = account.token;
            $scope.transaction.coinAmount = $scope.transaction.quantity;
            $scope.transaction.coinId = 'btc';
            $transaction.ask($scope.transaction);
        }

    })
    .controller('mainController', function ($q, $scope, $http, $state, $account, $socket, $timeout) {
        var main = this;
    })
    .controller('headerController', function ($scope, $account) {
        $scope.account = $account.getAccount();
        $scope.doLogout = function () {
            $account.doLogout();
        }
    })
    .controller('registrationController', function ($scope, $http, $server) {
        $scope.account = {};
        $scope.submit = function () {
            var service = this;
            var o = {
                url: "/account/registration.do",
                params: this.account
            }
            $server.post(o).then(function (response) {
                if (response.ok) {
                    $scope.account = {};
                    alert("가입 되었습니다.");
                    location.href = "/#/main"
                } else {
                    service.account.email = "";
                    alert("이미 존재하는 아이디 입니다.");
                }
            });
        }
    })
    .controller('loginController', function ($scope, $account) {
        $scope.account = {};
        $scope.doLogin = function () {
            console.log($scope.account);
            $account.doLogin($scope.account);
            $scope.account = {};
        }
    })
    .controller('calculator', function ($scope) {
        $scope.calculated = [];
        $scope.calculate = {};

        $scope.doCalculate = function () {
            var cex = $scope.calculate.cex;
            var upbit = $scope.calculate.upbit;
            var coinName = $scope.calculate.coinName;

            var marginGap = (( upbit - ( cex * 1086.10) ) / ( cex * 1086.10)) * 100;

            var result = {};
            result.marginGap = marginGap;
            result.coinName = coinName;

            $scope.calculated.push(result);

        }
    })



