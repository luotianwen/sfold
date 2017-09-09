<template>
  <div class="feedback">
    <div class="feed_header">
      <x-header :left-options="{showBack:true,backText:''}">意见反馈</x-header>
    </div>
    <div class="feed_content">
      <group>
        <x-textarea :max="500" placeholder="请填写问题我们会尽快处理您的反馈" v-model="content"></x-textarea>
      </group>
      <div class="uploading" v-if="isShowPic">

        <span class="feed_pic_btn" @click="changeFile(0)"><i></i></span>
        <span class="feed_pic_btn" style="display: none" @click="changeFile(1)"><i></i></span>
        <span class="feed_pic_btn" style="display: none" @click="changeFile(2)"><i></i></span>

      </div>
      <!--未输入值时禁止点击，边框字体颜色为#99d9fa,获取焦点后颜色为#009ff2-->
      <div class="feed_btn" style="margin-top:20px">
        <x-button type="primary" :class="{'weui-btn_plain-primary_active':isOkClick}" plain @click.native="save">下一步
        </x-button>
      </div>
    </div>
  </div>
</template>
<script>
  import {XHeader, Group, XTextarea, XButton} from 'vux'
  import {mapGetters} from 'vuex'
  var imageList = [];
  var currentClickIndex = 0;

  export default {
    computed:{
      ...mapGetters({
        device:'currentDevice'
      })
      },
    components: {
      XHeader,
      Group,
      XTextarea,
      XButton
    },
    data () {
      return {
        content: '',
        isOkClick: false,
        userInfo: {},
        isShowPic:false
      }
    },
    created(){
      this.userInfo = this.$getStorage(this.$storage_key.USERINFO_STORAGE);
      if(this.device.deviceName=='android'||this.device.deviceName=='ios'){
          this.isShowPic=true;
      }
      else{
          this.isShowPic=false;
      }
    },
    methods: {
      save(){
        if (!this.isOkClick)return;
        let arr = {}
        var that = this;

        let para = {};

        if (that.userInfo && that.userInfo.token) {
          para['token'] = this.userInfo.token;
        }
        para['type'] = 1;
        para['content'] = that.content;
        para['imgs'] = ['123', '456', '789'];
        that.$post(that.$interface_url.user.feedback, para, function (data, success) {
          if (success) {
            that.$vux.toast.show({text: '提交成功！', width: '16em'});
          }
          else {
            that.$vux.toast.show({text: data, type: 'warn', width: '16em'});
          }
        });
      },
      changeFile(index){
        currentClickIndex = index;
        navigator.camera.getPicture(takeSuccess, takeFail, {
          quality: 50,
          destinationType: Camera.DestinationType.DATA_URL,
          sourceType: Camera.PictureSourceType.PHOTOLIBRARY,
          allowEdit: true,
          encodingType: Camera.EncodingType.JPEG,
          targetWidth: 100,
          targetHeight: 100
        });
      }
    },
    watch: {
      'content': {
        handler: function (val, oldval) {
          if (val.length >= 10) {
            this.isOkClick = true;
          }
          else {
            this.isOkClick = false;
          }
        }
      }
    }
  }
  function takeSuccess(imageURI) {
    let imageDom = document.getElementsByClassName('feed_pic_btn');
    let width = imageDom[currentClickIndex].offsetWidth - 2;
    let height = imageDom[currentClickIndex].offsetHeight - 2;
    if (currentClickIndex < 2) {
      imageDom[currentClickIndex + 1].setAttribute('style', 'display:block');
    }
    imageDom[currentClickIndex].innerHTML = '<img src="data:image/jpeg;base64,' + imageURI + '" width=' + width + ' height=' + height + '/>'
    imageList[currentClickIndex] = imageURI;

  }
  function takeFail(message) {
    alert(message)
  }
</script>

<style rel="stylesheet/less" lang="less">
  @import "../../../assets/css/feedback.less";

  i {
    font-style: normal;
  }
</style>
