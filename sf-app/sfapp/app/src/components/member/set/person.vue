<template>
  <div>
  <div class="pge">
    <div class="pge_header">
      <x-header :left-options="{showBack:true}">{{$route.meta.title}}</x-header>
    </div>
    <div class="pge_content">
      <group>
        <div class="head_portrait" v-on:click="Picture">
          <cell title="世峰头像" is-link>
						<span class="pge_pic">
							<img :src="portrait" id="photoImg"/>
						</span>
          </cell>
        </div>
        <cell title="世峰昵称" :value="userInfo.name"  @click.native="showNickName()"></cell>
        <cell title="我的二维码名片" is-link link="/member/code">
					<span class="pge_code">
						<img src="../../../assets/image/personage/erweima.png"/>
					</span>
				</cell>
        <popup-picker title="性别" :data="sexList" v-model="sexValue"  confirm-text="确认" @on-change="saveSex" cancel-text="取消" ></popup-picker>
        <datetime v-model="birthdayVal" title="我的生日"  confirm-text="确认" cancel-text="取消"  :min-year=1940 :max-year=2017 @on-change="saveBirthday"></datetime>
        <cell title="我的收货地址" is-link link="/member/address"></cell>
      </group>
      <actionsheet v-show="fileOK" v-model="fileOK" :menus="fileMenus" @on-click-menu="changeFile" show-cancel cancel-text="取消"></actionsheet>
    </div>
  </div>
    <nickname :userInfo="userInfo" ref='nickname' v-on:child-update="updateUserInfo"></nickname>
  </div>
</template>

<script>
  import {XHeader, Cell, Group, Badge, XButton,Actionsheet,PopupPicker,Datetime} from 'vux'

  import {USERINFO_STORAGE} from '../../../common/public-variable'
  import {mapGetters} from 'vuex'
  import Nickname from './nickname.vue'

  var _that;
  export default {
    components: {
      PopupPicker,Actionsheet,XHeader,Cell,Group,XButton,Datetime,Nickname
    },
    data() {
      return {
        userInfo: {},
        fileOK: false,
        isShowNickName: false,
        fileMenus: {
          menu1: '拍照',
          menu2: '从相册选择'
        },
        sexList: [['男','女','保密']],
        sexValue: ['保密'],
        birthdayVal: '2015-11-12'
      }
    },
    computed:{
      ...mapGetters({
        currentDevice:'currentDevice'
      }),
      portrait:function() {
        if (this.userInfo && this.userInfo.portrait) {
          return this.userInfo.portrait;
        }
        else {
          return '../../static/image/touxiang.png';
        }
      }
    },
    methods: {
        updateUserInfo(userInfo){//提供给子组件调用更新的方法
            this.userInfo=userInfo;
            this.$setStorage(this.$storage_key.USERINFO_STORAGE,this.userInfo);
        },
      Picture:function () {
        if (this.currentDevice.deviceName == 'wap') {
          this.$vux.toast.show({
            text: this.$tip_message.changePhoto_Err,
            type: 'text',
            width: '22em'
          })
        } else {
          this.fileOK = true;
        }
        this.fileOK = true;
      },
      changeFile:function(key){
        if(key=='cancel')return;
          let sourceType=Camera.PictureSourceType.CAMERA;
        if(key=='menu1'){//拍照
          //拍照
          //navigator.camera就是上面我们所说的clobbers定义的东西，用来调用插件中的方法的
          //getPicture就是插件中调用摄像头拍照的方法
          sourceType=Camera.PictureSourceType.CAMERA;
        }
        else if(key=='menu2') {//从相册选择
          sourceType=Camera.PictureSourceType.PHOTOLIBRARY;
        }
        navigator.camera.getPicture(takeSuccess, takeFail, {
          quality : 50,
          destinationType : Camera.DestinationType.DATA_URL,
          sourceType : sourceType,
          allowEdit : true,
          encodingType : Camera.EncodingType.JPEG,
          targetWidth : 100,
          targetHeight : 100
        });
      },
      showNickName(){
        this.$refs.nickname.show();
      },
      saveSex(val){
        let sex=val.indexOf('男')>-1?1:val.indexOf('女')>-1?2:3;
        var that = this;
        this.userInfo.sex=sex;
        that.$post(that.$interface_url.user.modifyInfo, {
          token: this.userInfo.token,
          sex: this.userInfo.sex
        }, function (data, success) {
          if (success) {
            that.$setStorage(that.$storage_key.USERINFO_STORAGE,that.userInfo)
            that.$vux.toast.show({
              text: that.$tip_message.update_success,
              type: 'success',
              width: '18em'
            })
          }
          else {
            that.$vux.toast.show({
              text: data,
              type: 'warn',
              width: '18em'
            })
          }
        })
      },
      saveBirthday(val){
          if(val=='2017-01-01'||val==this.userInfo.birthday)return;
        var that = this;
        this.userInfo.birthday=val;
        that.$post(that.$interface_url.user.modifyInfo, {
          token: this.userInfo.token,
          birthday: this.userInfo.birthday
        }, function (data, success) {
          if (success) {
            that.$setStorage(that.$storage_key.USERINFO_STORAGE,that.userInfo)
            that.$vux.toast.show({
              text: that.$tip_message.update_success,
              type: 'success',
              width: '18em'
            })
          }
          else {
            that.$vux.toast.show({
              text: data,
              type: 'warn',
              width: '18em'
            })
          }
        })
      }
    },
    created(){
      this.userInfo=this.$getStorage(this.$storage_key.USERINFO_STORAGE);
      let sex=this.userInfo.sex;
      this.sexValue=sex==1?['男']:sex==2?['女']:['保密'];
      this.birthdayVal=this.userInfo.birthday?this.userInfo.birthday:'2017-01-01'
      _that=this;
    }
  }
  //拍照成功后回调
  function takeSuccess(imageURI) {
    document.getElementById('photoImg').setAttribute('src', 'data:image/jpeg;base64,' + imageURI)
    _that.$post(_that.$interface_url.user.modifyHeadImg, {token: _that.userInfo.token, headimg: imageURI}, function (data, success) {
      if (success) {
        _that.$vux.toast.show({
          text: _that.$tip_message.update_success,
          type: 'success',
          width: '15em'
        })
      }
      else {
        _that.$vux.toast.show({
          text: data,
          type: 'text',
          width: '15em'
        })
      }
    })

  }

  //失败后回调
  function takeFail(message) {
//   alert("拍照失败，原因：" + message);
  }
</script>

<style rel="stylesheet/less" lang="less">
  @import "../../../assets/css/Pge.less";
  i {
    font-style: normal;
  }
</style>
