// 与native交互实现
function init_native() {
    var toString = function (v) {
            return Object.prototype.toString.call(v);
        },
        isFunction = function (v) {
            return toString(v) === '[object Function]';
        },
        athene = {},
        CALLBACK_PREFIX = 'callback__',
        callbacks = {},
        callbackCount = 0,
        CallbackStatus = {
            OK: 1
        };
    athene.exec = function (success, fail, service, action, params) {
        var callbackId = CALLBACK_PREFIX + callbackCount++;
        callbacks[callbackId] = {
            success: success,
            fail: fail
        };
        params = Array.prototype.concat.call([], callbackId, params || []);
        action.apply(service, params);
    };
    athene.complete = function () {
        if (arguments.length < 2) {
            throw new Error('Missing essential arguments');
        }
        var callbackId = arguments[0],
            status = arguments[1],
            callback = callbacks[callbackId],
            params = Array.prototype.slice
                .call(arguments, 2),
            success,
            fail;
        for (var i = 0; i < params.length; i++) {
            params[i] = decodeURIComponent(params[i].replace(/\+/g, '%20'));
            //  params[i] = JSON.parse(params[i]);
            // console.log(params[i].url);
        }
        delete callbacks[callbackId];
        if (!callback) {
            return;
        }
        success = callback.success;
        fail = callback.fail;
        if (status == CallbackStatus.OK && isFunction(success)) {
            success.apply(null, params);
            //console.log('success params= '+ params[0]);
        } else if (isFunction(fail)) {
            fail.apply(null, params);
        }
    }
    window['athene'] = athene;
};

// ground 接口
function android_init() {
    init_native();
    var ground = {
        show: function (message) {
            huanqian.show(message);
            return false;
        },
        copy: function (message) {
            huanqian.copy(message);
            return false;
        },
        share: function (title, url, img, des, channel) {
            var args = "{\"title\":\"" + title + "\",\"url\":\"" + url + "\",\"img\":" + img + "\",\"des\":" + des + "}"
            var call = "{\"method\":\"share:\",\"arg\":" + args + "}";
            console.log(call);
            huanqian.share(title, url, img, des, channel);
            return false;
        },
        getApiToken: function (success, fail) {
            athene.exec(success, fail, huanqian, huanqian.getApiToken, []);
            return false;
        },
        callPhone: function () {
            huanqian.callPhone();
            return false;
        },
        showImage: function (urls, titles, index) {
            huanqian.showImage(urls, titles, index);
            return false;
        },
        jump: function (url, params) {
            huanqian.jump(url, params);
            return false;
        },
        reload: function () {
            huanqian.reload();
            return false;
        },
        close: function () {
            huanqian.close();
            return false;
        },
        forward: function (urls) {
            huanqian.forward(urls);
            return false;
        },
        closeAll: function () {
            huanqian.closeAll();
            return false;
        },
        dontRefresh: function () {
            huanqian.dontRefresh();
            return false;
        },
        callCustomPhone: function (cell) {
            huanqian.callCustomPhone(cell);
            return false;
        },
        hideHeader: function () {
            huanqian.hideHeader();
            return false;
        },
        goBack: function () {
            huanqian.goBack();
            return false;
        },
        event0: function (eventId) {
            huanqian.event(eventId, 0, '', '', '');
            return false;
        },
        event1: function (eventId, label) {
            huanqian.event(eventId, 1, label, '', '');
            return false;
        },
        event2: function (eventId, attributes) {
            huanqian.event(eventId, 2, '', attributes, '');
            return false;
        },
        event3: function (eventId, attributes, number) {
            huanqian.event(eventId, 3, '', attributes, number);
            return false;
        },
        event: function (eventId, type, label, attributes, number) {
            huanqian.event(eventId, type, label, attributes, number);
            return false;
        },
        vote: function (title, subTitle, voteId, productId, voteCount, votePrice) {
            huanqian.vote(title, subTitle, voteId, productId, voteCount, votePrice);
            return false;
        },
        hideHeaderAndTranslucentStatusBar: function () {
            huanqian.hideHeaderAndTranslucentStatusBar();
            return false;
        },
        setStatusBarColor: function (colorInt) {
            huanqian.setStatusBarColor(colorInt);
            return false;
        },
        reasonDialog: function (reason) {
            huanqian.reasonDialog(reason);
            return false;
        },
        spiderCrawler: function (type, siteId) {
            huanqian.spiderCrawler(type, siteId);
            return false;
        }

    };
    window['ground'] = ground;
};

function ios_init(obj) {

    if (!obj.ground) {
        obj.ground = {}

    }
    var _HuoqiuJSBridge_callbackId = 0;
    var _HuoqiuJSBridge_callbackArray = {};
    var _HuoqiuJSBridge_objArray = {};
    var _HuoqiuBridgeId = 0;
    var _HuoqiuJSBridge_shakecack = 0;

    function baseCall(jsonString) {
        _HuoqiuJSBridge_objArray[_HuoqiuBridgeId] = jsonString;
        window.location.href = "http://callobjc_" + _HuoqiuBridgeId;
        _HuoqiuBridgeId++;
    }

    function getArgs(bridgeId) {
        var args = _HuoqiuJSBridge_objArray[bridgeId];
        delete _HuoqiuJSBridge_objArray[bridgeId];
        return args;
    }

    window['ground']['getArgs'] = getArgs;
    function addCallback(functionObj) {
        var cid = _HuoqiuJSBridge_callbackId;
        _HuoqiuJSBridge_callbackArray[cid] = functionObj;
        _HuoqiuJSBridge_callbackId++;

        return cid;
    }

    function callback(cid, msg, flag, alsoDeleteId) {
        _HuoqiuJSBridge_callbackArray[cid](msg);
        //    if(1==flag){
        //      _IfengJSBridge_callbackArray[cid]["success"](msg);
        //    }else if(2==flag){
        //      _IfengJSBridge_callbackArray[cid]["error"](msg);
        //    }
        delete _HuoqiuJSBridge_callbackArray[cid];
        delete _HuoqiuJSBridge_callbackArray[alsoDeleteId];
    }

    window['ground']['callback'] = callback;

    function deleteCallBack(cid) {
        delete _IfengJSBridge_callbackArray[cid];
    }

    window['ground']['deleteCallBack'] = callback;

    function show(message) {
        //alert("back2Application..");
        var args = "{\"message\":\"" + message + "\"}"
        var call = "{\"method\":\"show:\",\"arg\":" + args + "}";
        baseCall(call);
    }

    window['ground']['show'] = show;

    function copy(message) {
        //alert("back2Application..");
        var args = "{\"message\":\"" + message + "\"}";
        var call = "{\"method\":\"copy:\",\"arg\":" + args + "}";
        baseCall(call);
    }

    window['ground']['copy'] = copy;

    function share(title, url, img, des, channel) {
        //alert("back2Application..");
        var args = "{\"title\":\"" + title + "\",\"url\":\"" + url + "\",\"img\":\"" + img + "\",\"des\":\"" + des + "\",\"channel\":\"" + channel + "\"}"
        var call = "{\"method\":\"share:\",\"arg\":" + args + "}";
        baseCall(call);
    }

    window['ground']['share'] = share;

    function getApiToken(success, fail) {
        var successId = addCallback(success);
        var failId = addCallback(fail);

        var args = "{\"success\":\"" + successId + "\",\"fail\":\"" + failId + "\"}";

        var call = "{\"method\":\"getApiToken:\",\"arg\":" + args + "}";
        baseCall(call);
    }

    window['ground']['getApiToken'] = getApiToken;

    function callPhone() {
        //alert("back2Application..");
        var args = "{\"num\":\"0\"}";
        var call = "{\"method\":\"callPhone\",\"arg\":" + args + "}";
        baseCall(call);
    }

    window['ground']['callPhone'] = callPhone;
    function showImage(urls, titles, index) {
        //alert("back2Application..");
        var args = "{\"urls\":\"" + urls + "\",\"titles\":\"" + titles + "\",\"index\":" + index + "}";
        var call = "{\"method\":\"showImage:\",\"arg\":" + args + "}";
        baseCall(call);
    }

    window['ground']['showImage'] = showImage;
    function jump(url, params) {
        //alert("back2Application..");
        var args = "{\"urls\":\"" + url + "\",\"params\":\"" + params + "\"}";
        var call = "{\"method\":\"jump:\",\"arg\":" + args + "}";
        baseCall(call);
    }

    window['ground']['jump'] = jump;

    function reload() {
        var call = "{\"method\":\"reload\"}";
        baseCall(call);
    }

    window['ground']['reload'] = reload;

    function close() {
        var call = "{\"method\":\"close\"}";
        baseCall(call);
    }

    window['ground']['close'] = close;

    function forward(urls) {
        var args = "{\"urls\":\"" + urls + "\"}";
        var call = "{\"method\":\"forward:\",\"arg\":" + args + "}";
        baseCall(call);
    }

    window['ground']['forward'] = forward;

    function closeAll() {
        var call = "{\"method\":\"closeAll\"}";
        baseCall(call);
    }

    window['ground']['closeAll'] = closeAll;

    function dontRefresh() {
        var call = "{\"method\":\"dontRefresh\"}";
        baseCall(call);
    }

    window['ground']['dontRefresh'] = dontRefresh;

    function callCustomPhone(cell) {
        var args = "{\"cell\":\"" + cell + "\"}";
        var call = "{\"method\":\"callCustomPhone:\",\"arg\":" + args + "}";
        baseCall(call);
    }

    window['ground']['callCustomPhone'] = callCustomPhone;

    function hideHeader() {
        var call = "{\"method\":\"hideHeader\"}";
        baseCall(call);
    }

    window['ground']['hideHeader'] = hideHeader;

    function goBack() {
        var call = "{\"method\":\"goBack\"}";
        baseCall(call);
    }

    window['ground']['goBack'] = goBack;

    function event0(eventId) {
        var args = "{\"eventId\":\"" + eventId + "\",\"type\":\"" + 0 + "\"}";
        var call = "{\"method\":\"event:\",\"arg\":" + args + "}";
        baseCall(call);
    }

    window['ground']['event0'] = event0;

    function event1(eventId, label) {
        var args = "{\"eventId\":\"" + eventId + "\",\"type\":\"" + 1 + "\",\"label\":\"" + label + "\"}";
        var call = "{\"method\":\"event:\",\"arg\":" + args + "}";
        baseCall(call);
    }

    window['ground']['event1'] = event1;

    function event2(eventId, attributes) {
        var args = "{\"eventId\":\"" + eventId + "\",\"type\":\"" + 2 + "\",\"attributes\":\"" + attributes + "\"}";
        var call = "{\"method\":\"event:\",\"arg\":" + args + "}";
        baseCall(call);
    }

    window['ground']['event2'] = event2;

    function event3(eventId, attributes, number) {
        var args = "{\"eventId\":\"" + eventId + "\",\"type\":\"" + 3 + "\",\"attributes\":\"" + attributes + "\",\"number\":\"" + number + "\"}";
        var call = "{\"method\":\"event:\",\"arg\":" + args + "}";
        baseCall(call);
    }

    window['ground']['event3'] = event3;

    function event(eventId, type, label, attributes, number) {
        var args = "{\"eventId\":\"" + eventId + "\",\"type\":\"" + type + "\",\"label\":\"" + label + "\",\"attributes\":\"" + attributes + "\",\"number\":\"" + number + "\"}";
        var call = "{\"method\":\"event:\",\"arg\":" + args + "}";
        baseCall(call);
    }

    window['ground']['event'] = event;

    function json2str(o) {
        var arr = [];
        var fmt = function (s) {
            if (typeof s == 'object' && s != null) return json2str(s);
            return /^(string|number)$/.test(typeof s) ? "\"" + s + "\"" : s;
        }
        for (var i in o) arr.push("\"" + i + "\":" + fmt(o[i]));
        return '{' + arr.join(',') + '}';
    }

    function vote(title, subTitle, voteId, productId, voteCount, votePrice) {
        var args = "{\"title\":\"" + title + "\",\"subTitle\":\"" + subTitle + "\",\"voteId\":\"" + voteId + "\",\"productId\":\"" + productId + "\",\"voteCount\":\"" + voteCount + "\",\"votePrice\":\"" + votePrice + "\"}"
        var call = "{\"method\":\"vote:\",\"arg\":" + args + "}";
        baseCall(call);
    }

    window['ground']['vote'] = vote;

    function reasonDialog(reason) {
        var args = "{\"reason\":" + reason + "}";
        var call = "{\"method\":\"reasonDialog:\",\"arg\":" + args + "}";
        baseCall(call);
    }

    window['ground']['reasonDialog'] = reasonDialog;

    function spiderCrawler(type, siteId) {
        var args = "{\"type\":\""+type+"\",\"siteId\":\""+siteId+"\"}";
        var call = "{\"method\":\"spiderCrawler:\",\"arg\":" + args + "}";
        baseCall(call);
    }
    window['ground']['spiderCrawler'] = spiderCrawler;
};

(function () {
    var isAndroid = (/android/gi).test(navigator.appVersion),
        isIDevice = (/iphone|ipad/gi).test(navigator.appVersion);
    if (isAndroid) {
        android_init();
    } else if (isIDevice) {
        ios_init(window);
    }
})();

