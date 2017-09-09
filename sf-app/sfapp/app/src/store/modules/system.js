/**
 * Created by shen on 2017/3/15.
 */
/*全局设置loading状态，起始值为false，使用路由beforeEach设置为true，faterEach设置为false*/
import * as types from '../mutation-types'
import {devices, version} from '../../common/public-variable.js'

// initial state
const state = {
  isLoading: false,//loading状态
  currentDevice: {},//当前浏览设备信息
  currentRouter: {},//当前访问路由信息
  direction: 'forward'//判断当前操作是前进还是后退
}
//getters
const getters = {
  isLoading: state => state.isLoading,
  currentDevice: state => state.currentDevice,
  currentRouter: state => state.currentRouter,
  getDirection:state=>state.direction
}

const actions = {
  updateLoading({commit}, value){
    commit(types.UPDATELOADINGSTATUS, {value});
  },
  updateDirection({commit}, value){
    commit(types.UPDATEDIRECTION, {value});
  },
  currentDevice({commit}, device){
    commit(types.CURRENT_ACCESS_DEVICE, {device});
  },
  currentRouter({commit}, router){
    commit(types.CURRENT_ROUTER, {router});
  }
}
//mutations
const mutations = {
  [types.UPDATELOADINGSTATUS](state, {value}){
    state.isLoading = value;
  },
  [types.UPDATEDIRECTION](state, {value}){
    state.direction = value;
  },
  [types.CURRENT_ACCESS_DEVICE](state, {device}){
    devices.forEach(function (item, i) {
      if (item.deviceName == device) {
        state.currentDevice = item;
      }
    })
  },
  [types.CURRENT_ROUTER](state, {router}){
    if (router) {
      state.currentRouter = router;
    }
  }
}
export default {
  state,
  getters,
  actions,
  mutations
}
