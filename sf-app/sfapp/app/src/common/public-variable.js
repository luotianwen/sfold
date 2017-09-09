/**
 * Created by shen on 2017/3/6.
 * 公共变量类
 */
export const SYSTEM_KEY= {
  version : '1.0.0',//版本号
//export const serverPath='http://localhost:8080/sf-webapi/'
  serverPath : 'http://192.168.1.177:7777/sf-webapi/',
  domain:'http://localhost:8080/#/'
}


/*本地缓存key*/
export const STORAGE_KEY=
  {
    WELCOME_STORAGE: 'WELCOME_STORAGE',
    USERINFO_STORAGE: 'USERINFO_STORAGE',//userinfo用户实体
    USERACCOUNT_STORAGE: 'USERACCOUNT_STORAGE',//用户登录账号
    TICKET_STORAGE: 'TICKET_STORAGE',//ticket
    CARTINFO_STORAGE: 'CARTINFO_STORAGE',//cart
    ROUTER_STORAGE:'ROUTER_STORAGE'
  }




/*本地缓存key*/

/*根据设备类型选择对应的secret*/
export const devices=[
  {
    deviceName:'wap',
    secret:'d6febb02cbd34a98b486767146c96379',
    deviceType:1
  },
  {
    deviceName:'chat',
    secret:'657bbd1df4c7461cb4dde06b216cce40',
    deviceType:2
  },
  {
    deviceName:'android',
    secret:'f99e653b1abe4828a355047e44e72533',
    deviceType:3
  },
  {
    deviceName:'ios',
    secret:'e518a8d0bda94b948cc6696cf0200b6b',
    deviceType:4
  }
]
/*系统提示*/
export const TIP_MESSAGE={
  network_err_message:'网络故障',
  sys_err_message:'系统错误',
  err_message:'系统错误',
  update_success:'更新成功',
  delete_success:'删除成功',
  add_success:'添加成功',
  auto_login_err:'您的密码已经在别的设备修改！请重新登录',
  login_success:'登录成功！',
  regiter_success:'注册成功！',
  change_pwd_success:'密码修改成功',
  addCart_msg:'加入购物车成功！',
  addCart_Err:'加入购物车失败',
  changePhoto_Err:'当前模式不支持修改头像，请下载App进行修改！',
  send_smscode_success:'短信发送成功，请注意查收.',
  send_smscode_err:'短信发送失败',
}

export const INTERFACE_URL={//接口地址
  'login':{//登录相关
    login:'login',//登录
  },
  'regiter':{//注册相关
    sendRegiterCode:'reg/notifyuser/mobileCode',//注册发送验证码
    checkMobileCode:'reg/checkMobileCode',//注册校验验证码
    checkPhoneExists:'reg/checkPhoneExists',//注册校验手机号是否存在
  },
  'safe':{//账户安全相关
    sendFindPwdCode:'safe/notifyuser/mobileCode',//找回密码发送验证码
    checkMobileCode:'safe/checkMobileCode',//找回密码校验验证码
    findPwd:'safe/findPwd',//找回密码
    checkPhoneExists:'safe/checkPhoneExists',//监测手机号是否存在
  },
  'user':{
    getAddressList:'usercenter/address/getAddressList',//获取用户地址
    setAddressAllDefaultById:'usercenter/address/setAddressAllDefaultById',//设置默认地址
    deleteAddress:'usercenter/address/deleteAddress',//删除用户地址
    updateAddress:'usercenter/address/updateAddress',//更新地址信息
    addAddress:'usercenter/address/addAddress',//添加地址信息
    modifyInfo: 'usercenter/modifyInfo',//修改用户昵称
    modifyHeadImg:'usercenter/modifyHeadImg',//修改用户头像
    feedback:'surveys/feedback',//意见反馈
    followWareList:'my/follow/followWareList',//用户商品收藏列表
    deleteFollowWare:'my/follow/deleteFollowWare',//删除用户收藏商品
    followWare:'my/follow/followWare',//添加用户关注商品
    followVenderList:'my/follow/followVenderList',//获取用户关注店铺列表
    followVender:'my/follow/followVender',//添加用户关注店铺
    deleteFollowVender:'/my/follow/deleteFollowVender',//删除用户关注店铺
    isFollowWare:'my/follow/isFollowWare',//是否关注商品
    isFollowVender:'my/follow/isFollowVender',//是否关注店铺
  },
  'product':{
    detail:'ware/detail',//商品详情
    skuList:'ware/skuList',//获取商品颜色和规格详情
    wareComments:'comment/wareComments',//获取商品评论列表
    getCoupons:'coupon/getCoupons',//获取可用优惠券信息
  },
  'order':{
    settlement:'order/settlement',//订单结算
    preview:'order/confirm_order',//订单结算页获取订单商品明细
  },
  'query':{
    homePage:'homepage/init.json',//首页加载
  },
  'cart':{
    cartWareList:'cart/cartWareList',//获取购物车商品列表
    addCartWare:'cart/addCartWare',//添加购物车商品
    deleteCartWare:'cart/deleteCartWare',//删除购物车商品
    editCartWare:'cart/editCartWare',//修改购物车商品
  },
  'shop':{
    hotWare:'shop/hotWare',//店铺热销
  }
}

export const ERROR_CODE={

}


export default {
  install (Vue) {
    Vue.prototype.$interface_url = INTERFACE_URL
    Vue.prototype.$tip_message = TIP_MESSAGE
    Vue.prototype.$storage_key = STORAGE_KEY
    Vue.prototype.$system_key = SYSTEM_KEY
  },
  $interface_url: INTERFACE_URL,
  $tip_message:TIP_MESSAGE,
  $storage_key:STORAGE_KEY,
  $system_key:SYSTEM_KEY
}

export const $interface_url = INTERFACE_URL
export const $tip_message=TIP_MESSAGE
export const $storage_key=STORAGE_KEY
export const $system_key=SYSTEM_KEY
