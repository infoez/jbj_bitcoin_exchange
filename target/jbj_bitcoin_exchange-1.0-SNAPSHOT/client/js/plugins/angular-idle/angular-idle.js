/*** Directives and services for responding to idle users in AngularJS
 * @author Mike Grabski <me@mikegrabski.com>
 * @version v1.0.2
 * @link https://github.com/HackedByChinese/ng-idle.git
 * @license MIT
 */

!function(a,b){"use strict";b.module("ngIdle",["ngIdle.keepalive","ngIdle.idle","ngIdle.countdown","ngIdle.title","ngIdle.localStorage"]),b.module("ngIdle.keepalive",[]).provider("Keepalive",function(){var a={http:null,interval:600};this.http=function(c){if(!c)throw new Error("Argument must be a string containing a URL, or an object containing the HTTP core configuration.");b.isString(c)&&(c={url:c,method:"GET"}),c.cache=!1,a.http=c};var c=this.interval=function(b){if(b=parseInt(b),isNaN(b)||0>=b)throw new Error("Interval must be expressed in seconds and be greater than 0.");a.interval=b};this.$get=["$rootScope","$log","$interval","$http",function(d,e,f,g){function h(a,b){d.$broadcast("KeepaliveResponse",a,b)}function i(){d.$broadcast("Keepalive"),b.isObject(a.http)&&g(a.http).success(h).error(h)}var j={ping:null};return{_options:function(){return a},setInterval:c,start:function(){return f.cancel(j.ping),j.ping=f(i,1e3*a.interval),j.ping},stop:function(){f.cancel(j.ping)},ping:function(){i()}}}]}),b.module("ngIdle.idle",["ngIdle.keepalive","ngIdle.localStorage"]).provider("Idle",function(){var a={idle:1200,timeout:30,autoResume:"idle",interrupt:"mousemove keydown DOMMouseScroll mousewheel mousedown touchstart touchmove scroll",keepalive:!0},c=this.timeout=function(c){if(c===!1)a.timeout=0;else{if(!(b.isNumber(c)&&c>=0))throw new Error("Timeout must be zero or false to disable the feature, or a positive integer (in seconds) to enable it.");a.timeout=c}};this.interrupt=function(b){a.interrupt=b};var d=this.idle=function(b){if(0>=b)throw new Error("Idle must be a value in seconds, greater than 0.");a.idle=b};this.autoResume=function(b){a.autoResume=b===!0?"idle":b===!1?"off":b},this.keepalive=function(b){a.keepalive=b===!0},this.$get=["$interval","$log","$rootScope","$document","Keepalive","IdleLocalStorage","$window",function(b,e,f,g,h,i,j){function k(){a.keepalive&&(s.running&&h.ping(),h.start())}function l(){a.keepalive&&h.stop()}function m(){s.idling=!s.idling;var c=s.idling?"Start":"End";f.$broadcast("Idle"+c),s.idling?(l(),a.timeout&&(s.countdown=a.timeout,n(),s.timeout=b(n,1e3,a.timeout,!1))):k(),b.cancel(s.idle)}function n(){return s.countdown<=0?void o():(f.$broadcast("IdleWarn",s.countdown),void s.countdown--)}function o(){l(),b.cancel(s.idle),b.cancel(s.timeout),s.idling=!0,s.running=!1,s.countdown=0,f.$broadcast("IdleTimeout")}function p(a,b,c){var d=a.running();a.unwatch(),b(c),d&&a.watch()}function q(){var a=i.get("expiry");return a.time}function r(a){a?i.set("expiry",{id:t,time:a}):i.remove("expiry")}var s={idle:null,timeout:null,idling:!1,running:!1,countdown:null},t=(new Date).getTime(),u={_options:function(){return a},_getNow:function(){return new Date},setIdle:function(a){p(this,d,a)},setTimeout:function(a){p(this,c,a)},isExpired:function(){var a=q();return a&&a<=this._getNow()},running:function(){return s.running},idling:function(){return s.idling},watch:function(c){b.cancel(s.idle),b.cancel(s.timeout);var d=a.timeout?a.timeout:0;c||r(new Date((new Date).getTime()+1e3*(a.idle+d))),s.idling?m():s.running||k(),s.running=!0,s.idle=b(m,1e3*a.idle,0,!1)},unwatch:function(){b.cancel(s.idle),b.cancel(s.timeout),s.idling=!1,s.running=!1,r(null),l()},interrupt:function(b){return s.running?a.timeout&&this.isExpired()?void o():void(("idle"===a.autoResume||"notIdle"===a.autoResume&&!s.idling)&&this.watch(b)):void 0}};g.find("body").on(a.interrupt,function(){u.interrupt()});var v=function(a){if("ngIdle.expiry"===a.key&&a.newValue!==a.oldValue){var b=i.parseJson(a.newValue);if(b.id===t)return;u.interrupt(!0)}};return j.addEventListener?j.addEventListener("storage",v,!1):j.attachEvent("onstorage",v),u}]}),b.module("ngIdle.countdown",[]).directive("idleCountdown",function(){return{restrict:"A",scope:{value:"=idleCountdown"},link:function(a){a.$on("IdleWarn",function(b,c){a.$apply(function(){a.value=c})}),a.$on("IdleTimeout",function(){a.$apply(function(){a.value=0})})}}}),b.module("ngIdle.title",[]).factory("Title",["$document","$interpolate",function(a,c){function d(a,b,c){return Array(b-String(a).length+1).join(c||"0")+a}var e={original:null,idle:"{{minutes}}:{{seconds}} until your session times out!",timedout:"Your session has expired."};return{original:function(a){return b.isUndefined(a)?e.original:void(e.original=a)},store:function(a){(a||!e.original)&&(e.original=this.value())},value:function(c){return b.isUndefined(c)?a[0].title:void(a[0].title=c)},idleMessage:function(a){return b.isUndefined(a)?e.idle:void(e.idle=a)},timedOutMessage:function(a){return b.isUndefined(a)?e.timedout:void(e.timedout=a)},setAsIdle:function(a){this.store();var b={totalSeconds:a};b.minutes=Math.floor(a/60),b.seconds=d(a-60*b.minutes,2),this.value(c(this.idleMessage())(b))},setAsTimedOut:function(){this.store(),this.value(this.timedOutMessage())},restore:function(){this.original()&&this.value(this.original())}}}]).directive("title",["Title",function(a){return{restrict:"E",link:function(b,c,d){d.idleDisabled||(a.store(!0),b.$on("IdleWarn",function(b,c){a.setAsIdle(c)}),b.$on("IdleEnd",function(){a.restore()}),b.$on("IdleTimeout",function(){a.setAsTimedOut()}))}}}]),b.module("ngIdle.localStorage",[]).factory("IdleLocalStorage",["$window",function(a){function b(a){try{return JSON.parse(a,function(a,b){var c=/^(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2}(?:\.\d*)?)Z$/.exec(b);return c?new Date(b):b})}catch(b){return a}}var c=a.localStorage;return{set:function(a,b){c.setItem("ngIdle."+a,JSON.stringify(b))},get:function(a){var d=c.getItem("ngIdle."+a);return b(d)},remove:function(a){c.removeItem("ngIdle."+a)},parseJson:function(a){return b(a)}}}])}(window,window.angular);
//# sourceMappingURL=angular-idle.map