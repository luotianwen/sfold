/**
 * Created by pangxie on 2017/3/6.
 * 请求服务器公共方法
 */
import Vue from 'vue'
import {md5} from 'vux'
import VueResource from 'vue-resource';
import store from '../../store'
import {$storage_key, $system_key} from '../../common/public-variable'
import {$removeStorage, $setStorage, $getStorage} from '../../common/js/storage'
Vue.use(VueResource);
Vue.http.options.emulateJSON = true;
import {LoadingPlugin} from 'vux'
Vue.use(LoadingPlugin);


/*获取ticket，存本地缓存，每次获取ticket判断缓存中是否过期，如果过期重新拉取，未过期直接获取*/

export function post(url, para, callback) {

  Vue.$vux.loading.show({text:'正在加载...'});
  let ticket = $getStorage($storage_key.TICKET_STORAGE);
  if (!ticket) {
    const device = store.state.system.currentDevice;
    console.log('服务器重新拉取ticket')
    let paras = {
      secret: device.secret,
      version: $system_key.version,
      type: device.deviceType,
      sign: md5(device.secret + $system_key.version + device.deviceType)
    }

    Vue.http.post($system_key.serverPath + 'getTicket', paras).then(response => {
      if (response.status === 200) {
        let data = response.body;
        console.log('ticket拉取结果。。。。。')
        console.log(data)
        if (data.code == 0) {
          let today = new Date();
          today.setHours(23);
          today.setMinutes(59)
          today.setSeconds(40);//ticket缓存到当天23:59:40
          let ticketVal = {
            ticket: data.data,
            timeout: today
          };
          $setStorage($storage_key.TICKET_STORAGE, ticketVal);
          postServer(ticketVal.ticket, url, para, callback)
        }

      } else {
        alert("系统错误");
      }
    }, response => {
      // error callback
      Vue.$vux.toast.show({text:'   网络故障   ',type:'warn',width:'100px'});
      Vue.$vux.loading.hide();
    });
  } else {
    if (new Date(ticket.timeout) > new Date()) {
      console.log('获取缓存中的tiket:' + ticket.ticket)
      postServer(ticket.ticket, url, para, callback);
    }
    else {
      console.log('缓存中ticket过期，重新拉取服务器')
      $removeStorage($storage_key.TICKET_STORAGE);
      post(url, para, callback);
    }
  }
}
//secret+version+type
/*url--服务器接口方法名
 * params-参数数组，严格按照接口参数顺序，否则验证过不去
 * callback-回调函数
 * */
export function postServer(ticket, url, params, callback) {

  try {
    let para = {};
    if (params && params.token) {
      para = {
        version: $system_key.version
      };
    }
    else {
      para = {
        version: $system_key.version,
        ticket: ticket
      };
    }
    if (params) {
      for (var p in params) {
        para[p] = params[p];
      }
    }
    let signString = '';
    for (var p in para) {
      let isArray = para[p] instanceof Array;//判断参数是否有数组类型，数组不计入签名
      if (!isArray) {
        signString += para[p];
      }
    }
    para['sign'] = md5(signString);//生成sign

 /*   console.log('------------请求地址----------------')
    console.log($system_key.serverPath + url)
    console.log('------------请求参数----------------')
    console.log(para)*/

    Vue.http.post($system_key.serverPath + url, para).then((response) => {
      if (response.status === 200) {
        let data = response.body;
        console.log('------------请求结果----------------')
        console.log(data)
        if (data) {
          if (data.code == 0) {
            callback(data.data, true)
          }
          else if(data.code==10004){

            callback(data.code,false);
          }
          else {
            callback(data.msg ? data.msg : '网络故障', false);
          }
          Vue.$vux.loading.hide();
        }
      }
    }, response => {
      Vue.$vux.loading.hide();
      callback(response.statusText ? response.state : '网络故障', false)
    });
  }
  catch (e) {
    throw e;
  }
}


export default {
  install (Vue) {
    Vue.prototype.$post = post
    Vue.post = post
  },
  $post: post
}

export const $post = post



