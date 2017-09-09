<template>
  <transition enter-active-class="animated fadeInRight" leave-active-class="animated fadeOutRight">
  <div>
  <div class="mgm" v-show="isShowParent">
    <div class="mgm_header">
      <x-header :left-options="{showBack:true,backText:''}">管理收货地址</x-header>
    </div>
    <div class="mgm_content">
      <div class="address_msg" v-for="(address,$index) in addressList" v-show="addressList&&addressList.length>0" @click="chooseAddress(address)">
        <ul class="a_msg_1">
          <li class="a_msg_first">{{address.contacts}}</li>
          <li	class="a_msg_last">{{address.phone}}</li>
        </ul>
        <div class="a_msg_2">
          {{getAddressName(address)+address.address}}
        </div>

        <div class="a_msg_3 ">
					<!--切换样式选中"selected_class",未选中'default_class'-->
					<div :class="{'default_class':address.first==2,'selected_class':address.first==1}" >
			    		<i class="opt_img" @click="changeDefault(address)"></i>
					    <span class="opt_msg" @click="changeDefault(address)">
                {{address.first==1?"默认地址":"设为默认"}}
              </span>
						<div class="omit" @click="deleteAddress(address)">
							<i></i>
							<em>删除</em>
						</div>
						<div class="compile" @click="edit(address)">
							<i></i>
							<em>编辑</em>
						</div>
					</div>
				</div>
      </div>
      <!--未添加地址-->
      <div class="basic_cement" v-show="!addressList&&addressList.length>0">
        <div class="bc">
          <i>
            <img src="../../../assets/image/set/dizhi.png"/>
          </i>
          <p>您还没有收货地址呢！</p>
        </div>
      </div>
    </div>
    <div class="new_btn">
      <x-button type="primary" @click.native="showAddress">添加新地址</x-button>
    </div>
  </div>
    <edit-address :_address="selectedAddress"
                  ref='editAddress' v-on:child-update="child_updateAddress"
                  v-on:chid-updateParent="showParent"></edit-address>
  </div>
  </transition>
</template>
<script>
  import {XHeader, Cell, Group, Badge, XButton,Actionsheet,PopupPicker, Checker,Value2nameFilter as value2name, CheckerItem,ChinaAddressData} from 'vux'

  import {USERINFO_STORAGE} from '../../../common/public-variable'
  import editAddress from './editaddress.vue'
  export default {
    components: {
      PopupPicker,
      Actionsheet,
      XHeader, Cell,
      Group,
      XButton,
      Checker,
      editAddress,
      CheckerItem,
      ChinaAddressData,
      value2name
    },
    data () {
      return {
        color1: '#FFEF7D',
        colors1: ['#FF3B3B'],
        userInfo: {},
        addressList: [],
        selectedAddress: {},
        selectedIndex: 0,
        isShowParent:true,
        type:0
      }
    },
    methods: {
      showAddress(){
        this.selectedAddress = {};
        this.isShowParent=false;
        this.$refs.editAddress.show();
      },
      edit(address){
        this.selectedAddress = address;
        this.isShowParent=false;
        this.$refs.editAddress.show();

      },
      showParent(){
          this.isShowParent=true;
      },
      child_updateAddress(){
        var that = this;
        this.$post(that.$interface_url.user.getAddressList, {token: this.userInfo.token}, function (data, success) {
          if (success) {
            that.addressList = data;
          }
        })
      },
      getAddressName(address){
        let addressName = [];
        addressName.push('' + address.province + '');
        addressName.push('' + address.city + '');
        addressName.push('' + address.area + '');
        return value2name(addressName, ChinaAddressData)
      },
      changeDefault(address){
        this.addressList.forEach(function (item, index) {
          if (item == address) {
            item.first = 1;
          }
          else {
            item.first = 2;
          }
        })
        var that = this;
        this.$post(that.$interface_url.user.setAddressAllDefaultById,
          {token: this.userInfo.token, addressId: address.id}, function (data, success) {
            if (!success) {
              that.$vux.toast.show({text: data, type: 'warn', width: '17em'});
            }
          })
      },
      deleteAddress(address){
        var that = this;
        this.$vux.confirm.show({
          content:'确认删除?',
          confirmText: '确认',
          cancelText: '取消',
          // 组件除show外的属性
          onCancel () {

          },
          onConfirm () {
            that.$post(that.$interface_url.user.deleteAddress, {
              token:that.userInfo.token,
              addressId: address.id
            }, function (data, success) {
              if (success) {
                that.addressList.forEach(function (item,index) {
                  if(item==address){
                    that.addressList.splice(index, 1)
                  }
                })
                that.$vux.toast.show({text: that.$tip_message.delete_success, width: '18em'});
              }
              else {
                that.$vux.toast.show({text: data, type: 'warn', width: '18em'})
              }
            })
          }
        })

      },
      chooseAddress(address){
          if(this.type==0)return;
          else if(this.type==1){
              this.changeDefault(address)
              this.$router.go(-1);
          }
      }
    },
    created(){
      this.userInfo = this.$getStorage(this.$storage_key.USERINFO_STORAGE)
      this.type=this.$route.query.type;
      var that = this;
      this.$post(that.$interface_url.user.getAddressList, {token: this.userInfo.token}, function (data, success) {
        if (success) {
          that.addressList = data;
        }
        else if(data=='10004'){
          var returnurl=that.$route.path;
          that.$router.push({name:'login',query:{returnurl:returnurl}});
        }
        else {
          that.$vux.toast.show({text: data, type: 'warn', width: '18em'});
        }
      })
    }
  }
</script>
<style rel="stylesheet/less" lang="less">
  @import "../../../assets/css/Management.less";
  em{
    font-style: normal;
  }
</style>
