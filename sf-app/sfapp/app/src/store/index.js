/**
 * Created by shen on 2017/3/6.
 */
import Vue from 'vue'
import Vuex from 'vuex'
import * as actions from './actions'
import * as getters from './getters'
import  system from './modules/system'
import cart from './modules/cart'
/*import 'babel-polyfill'//解决vuex兼容问题，先安装cnpm install --save-dev babel-polyfill，在vuex文件中导入使用
import vuexI18n from 'vuex-i18n'*/
Vue.use(Vuex)

const store = new Vuex.Store({
  actions,
  getters,
  modules:{
    cart,
    system
    //i18n: vuexI18n.store
  }
})
/*store.registerModule('vux', {
  state: {
    demoScrollTop: 0,
    isLoading: false,
    direction: 'forward'
  },
  mutations: {
    updateDemoPosition (state, payload) {
      state.demoScrollTop = payload.top
    },
    updateLoadingStatus (state, payload) {
      state.isLoading = payload.isLoading
    },
    updateDirection (state, payload) {
      state.direction = payload.direction
    }
  },
  actions: {
    updateDemoPosition ({commit}, top) {
      commit({type: 'updateDemoPosition', top: top})
    }
  }
})*/

//Vue.use(vuexI18n.plugin, store)

export default store
