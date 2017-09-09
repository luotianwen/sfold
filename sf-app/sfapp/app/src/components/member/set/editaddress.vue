<template>
  <transition enter-active-class="animated fadeInUpBig" leave-active-class="animated fadeOutDownBig">
  <div class="new_address" v-show="isShow" ref='editAddress'>
    <div class="new_header">
      <x-header :left-options="{showBack:false,backText:''}">
        <span class="back_btn" @click="hide"><i></i></span>
        管理收货地址 <span class="save" @click="saveAddress">保存</span></x-header>
    </div>
    <div class="new_content">
      <group>
        <x-input title="收货人"  placeholder="收货人姓名"  v-model="_address.contacts"></x-input>
        <x-input title="联系电话"  placeholder="联系电话" v-model="_address.phone" keyboard="number" is-type="china-mobile" ref="phoneTxt"></x-input>

        <x-address title="所在地区"
                   placeholder="请选择地址"
                   v-model="addressVal" :list="addressData">
        </x-address>
        <x-textarea  placeholder="请填写详细地址" v-model="_address.address" :max="60" ref="addressTxt"></x-textarea>
      </group>
      <group>
        <x-switch title="设为默认" v-model="isFirst"></x-switch>
      </group>
    </div>
  </div>
  </transition>
</template>
<script>
  import {XHeader, Cell, Group,XInput,XTextarea,XSwitch,XAddress,ChinaAddressData,Value2nameFilter as value2name } from 'vux'
  import {isLogin} from '../../../common/js/checkIsLogin'
  export default {
    props: {
      _address: {}
    },
    components: {
      ChinaAddressData,
      XAddress,
      XSwitch,
      XTextarea, XHeader,
      Cell,
      Group,
      XInput
    },
    data () {
      return {
        addressData: ChinaAddressData,
        isShow: false,
        isFirst: false,
        addressVal: []
      }
    },
    computed: {
      getAddressVal: function () {
        let array = new Array();
        if (this._address.province) {
          array.push('' + this._address.province + '');
          array.push('' + this._address.city + '');
          array.push('' + this._address.area + '');
          this.addressVal = array;
        }
      },
      getIsFirst:function () {
        if(this._address.first){
            if(this._address.first==1)
                this.isFirst=true;
            else
                this.isFirst=false;
        }
      }
    },
    methods: {
      show(){
        this.isShow = true;
      },
      hide(){
        this.isShow = false;
        this.$emit('chid-updateParent');//调用父组件方法更新父组件显示
      },
      saveAddress(){
        var that = this;
        if (that._address) {
          if (!that._address.contacts) {
            that.$vux.toast.show({text: '请填写收货人姓名!', type: 'text', width: '18em'});
          }
          else if (!that._address.phone) {
            that.$vux.toast.show({text: '请填写联系电话!', type: 'text', width: '18em'});
          }
          else if (!that._address.address) {
            that.$vux.toast.show({text: '请填写详细地址!', type: 'text', width: '18em'});
          }
          else if (!that.$refs.phoneTxt.valid) {
            that.$vux.toast.show({text: '手机格式有误!', type: 'text', width: '18em'});
          }
          else if (that.addressVal.length <= 2) {
            that.$vux.toast.show({text: '请选择地址!', type: 'text', width: '18em'});
          }
          else {
            if (isLogin()) {
              var userInfo = that.$getStorage(that.$storage_key.USERINFO_STORAGE);
              that._address.province = that.addressVal[0];
              that._address.city = that.addressVal[1];
              that._address.area = that.addressVal[2];
              that._address.first = that.isFirst ? 1 : 2;
              let para = {
                token: userInfo.token
              };
              let url = '';
              if (that._address.id > 0) {
                para["id"] = that._address.id;
                url = that.$interface_url.user.updateAddress;
              }
              else {
                url = that.$interface_url.user.addAddress;
              }
              para["contacts"]= that._address.contacts.trim();//	//联系人
              para["province"]= that._address.province;//省
              para["city"]= that._address.city;//市
              para["area"]= that._address.area;//区
              para["address"]= that._address.address.trim();//详细地址
              para["phone"]= that._address.phone;//联系电话
              para["first"]= that._address.first;//默认 1是2否
              that.$post(url, para, function (data, success) {
                if (success) {
                  that.isShow = false;
                  that.$emit('chid-updateParent');//调用父组件方法更新父组件显示
                  if (that._address.id > 0) {
                    that.$vux.toast.show({text: that.$tip_message.update_success, width: '18em'});
                    that.$emit('child-update', {address: that._address, isEdit: true});//调用父组件方法更新地址列表
                  } else {
                    that.$vux.toast.show({text: that.$tip_message.add_success, width: '18em'});
                    that.$emit('child-update', {address: that._address, isEdit: false});//调用父组件方法更新地址列表
                  }
                }
                else {
                  that.$vux.toast.show({text: data, type: 'text', width: '18em'});
                }
              })
            } else {
              that.$vux.toast.show({text: '当前登录信息过期!', width: '18em'});
              that.$router.push({path: '/login'});
            }
          }
        }
        else {
          that.$vux.toast.show({text: '请填写地址信息!', type: 'warn', width: '18em'});
        }
      }
    }
  }
</script>
<style rel="stylesheet/less" lang="less">
  @import "../../../assets/css/newaddress.less";
</style>
