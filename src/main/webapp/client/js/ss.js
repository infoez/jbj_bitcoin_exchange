define(function () {
    var ws = {connected: false};

    function onerror(e) {

        if (ws.error_listeners && ws.error_listeners.length) {
            for (var i in ws.error_listeners) {
                if (ws.error_listeners.hasOwnProperty(i) && typeof ws.error_listeners[i] === 'function') {
                    ws.error_listeners[i](e);
                }
            }
        }

        if (e.type && e.type === 'close') {
            var log_message = 'WS';
            if (e.type) log_message += ' ' + e.type;
            if (e.code) log_message += ' with code ' + e.code;
            if (e.reason) log_message += ' with reason ' + e.reason;
            console.info(log_message, (e.type && e.type === 'close') ? 'https://tools.ietf.org/html/rfc6455' : '');
        }

        this.onerror = null;
        this.onclose = null;
        ws.connected = false;
        setTimeout(function () {
            setup_ws();
        }, 1000);

    }

    function onopen() {
        ws.connected = true;
    }

    function ready() {
        var int = setInterval(function () {
            if (WS.readyState === 1) {
                clearInterval(int);
                subscribe();
            }
        }, 500);
    }

    function setup_ws() {
        var listeners = ws.listeners || [];
        var on_open = ws.onopen || onopen;
        if (ws.connected) {
            ws.close();
        }
        ws = new WebSocket(ws_path);
        ws.onerror = onerror;
        ws.onclose = onerror;
        ws.onopen = on_open;
        ws.listeners = listeners;
        ws.error_listeners = ws.error_listeners || [];
        Object.defineProperty(ws, 'ready', {
            configurable: false, set: function (func) {
                if (typeof func === 'function') {
                    if (ws.readyState === 1) {
                        func();
                    }
                    if (typeof ws.onopen === 'function') {
                        var f = ws.onopen;
                        ws.onopen = function () {
                            func();
                            f();
                        }
                    } else {
                        ws.onopen = func;
                    }
                }
            }
        });
        Object.defineProperty(ws, 'events', {
            configurable: false, set: function (func) {
                if (typeof func === 'function')
                    ws.listeners.push(func);
            }
        });
        ws.onmessage = function () {
            var self = this;
            var args = Array.prototype.slice.call(arguments);
            var data = JSON.parse(args[0].data);
            var l = ws.listeners.length;
            for (var i = 0; i < l; i++) {
                ws.listeners[i].apply(self, [data]);
            }
        };
        window.WS = ws;
    }

    var transportListenerOnce = false;
    window.setup_ws = function (transportListener) {
        if (!window.WS) {
            console.log('Run ws');
            setup_ws();
        }
        if (!transportListenerOnce && typeof transportListener === 'function') {
            transportListenerOnce = true;
            console.log('Run ws, setup transport listener.');
            window.WS.events = transportListener;
        }
    };
    return ws;
});