import Vue from 'vue'
import App from './App'
import store from './store'
import PostPlugin from './common/js/public-resource'
import StoreagePlugin from './common/js/storage'
import InterFaceUrlPlugin from './common/public-variable'

import router from './router'
import {ConfirmPlugin, ToastPlugin,WechatPlugin } from 'vux'

require('es6-promise').polyfill();

Vue.use(StoreagePlugin);
Vue.use(InterFaceUrlPlugin);
Vue.use(PostPlugin);
Vue.use(ConfirmPlugin);
Vue.use(ToastPlugin);
Vue.use(WechatPlugin);


if (window.location.protocol === 'file:' || window.location.port === '3000') {
  var cordovaScript = document.createElement('script')
  cordovaScript.setAttribute('type', 'text/javascript')
  cordovaScript.setAttribute('src', 'cordova.js')
  document.body.appendChild(cordovaScript)
}
let device = detectOS();

console.log('当前访问设备：' + device);
store.commit('CURRENT_ACCESS_DEVICE', {device: device});

function detectOS() {
  var sUserAgent = window.navigator.userAgent;

  if (sUserAgent.toLowerCase().match(/MicroMessenger/i) == 'micromessenger') {
    return "chat";
  }
  var isWin = (navigator.platform == "Win32") || (navigator.platform == "Windows");
  var isMac = (navigator.platform == "Mac68K") || (navigator.platform == "MacPPC") || (navigator.platform == "Macintosh") || (navigator.platform == "MacIntel");
  var isUnix = (navigator.platform == "X11") && !isWin && !isMac;
  if (isWin || isMac || isUnix) {
    return "wap";
  }
  var isLinux = (String(navigator.platform).indexOf("Linux") > -1);
  var bIsAndroid = sUserAgent.toLowerCase().match(/android/i) == "android";
  var bIsIos = sUserAgent.toLowerCase().match(/ios/i) == "ios";
  if (isLinux) {
    if (bIsAndroid) return "android";
    else return "wap";
  }
  if (bIsIos) {
    return "ios";
  }
  if (isWin) {
    return "wap";
  }
  return "other";
}


//解决低版本浏览器不支持es6 Array.find()
if (!Array.prototype.find) {
  Array.prototype.find = function (predicate) {
    'use strict';
    if (this == null) {
      throw new TypeError('Array.prototype.find called on null or undefined');
    }
    if (typeof predicate !== 'function') {
      throw new TypeError('predicate must be a function');
    }
    var list = Object(this);
    var length = list.length >>> 0;
    var thisArg = arguments[1];
    var value;

    for (var i = 0; i < length; i++) {
      value = list[i];
      if (predicate.call(thisArg, value, i, list)) {
        return value;
      }
    }
    return undefined;
  };
}

/* eslint-disable no-new */
new Vue({
  el: 'app',
  router,
  store,
  // components: { App }
  render: h => h(App)
})

