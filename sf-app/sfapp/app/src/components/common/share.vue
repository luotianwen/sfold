<template>
  <div ref="share">
    <popup v-model="isShow">
      <div class="ditch_msg">
        <h1>分享</h1>
        <div class="ditch_classify">
          <div class="ditch_item1 wxin" @click="wechatShare">
            <img src="../../assets/image/set/weixin_img.png"/>
            <span>微信好友</span>
          </div>
          <div class="ditch_item1 friends" @click="wechatFriendShare">
            <img src="../../assets/image/set/friends_img.png"/>
            <span>朋友圈</span>
          </div>
          <div class="ditch_item1 qq" @click="qqShare">
            <img src="../../assets/image/set/qq_img.png"/>
            <span>QQ空间</span>
          </div>
          <div class="ditch_item1 sina">
            <img src="../../assets/image/set/sina_img.png"/>
            <span>新浪微博</span>
          </div>
          <div class="ditch_item2 copylink" @click="copy">
            <img src="../../assets/image/set/copy_img.png"/>
            <span>复制链接</span>
          </div>
        </div>
      </div>
      <div class="off_btn" @click="hide">
        <button>取消</button>
      </div>
    </popup>

  </div>
</template>

<script type="text/ecmascript-6">
  import {Popup} from 'vux'
  import {mapGetters} from 'vuex'
  export default{
    props: {
      url: '',//分享链接
      title: '',//分享标题
      description:'',//分享描述
      imgSrc: ''//分享图片地址
    },
    data(){
      return {
        isShow: false
      }
    },
    components: {
      Popup
    },
    methods: {
      show(){
        var device=this.currentDevice.deviceName;
        if(device!='wechat'&&device!='android'&&device!='ios'){
          this.$vux.toast.show({text:'当前浏览模式不支持分享功能',type:'text'});return;
        }

        this.isShow = true;
      },
      hide(){
        this.isShow = false;
      },
      wechatShare(){
        var that = this;
        var device=this.currentDevice.deviceName;
        if(device=='wechat'){

        }else {
          Wechat.share({
            text: "This is just a plain string",
            scene: Wechat.Scene.TIMELINE   // share to Timeline
          }, function () {
            alert("Success");
          }, function (reason) {
            alert("Failed: " + reason);
          });
          //微信分享
          //Wechat.Scene.TIMELINE 表示分享到朋友圈
          //Wechat.Scene.SESSION 表示分享给好友
/*          Wechat.share({
            message: {
              title: "Hi, there",
              description: "This is description.",
              thumb: "www/img/thumbnail.png",
              media: {
                type: Wechat.Type.LINK,
                image: that.url
              }
            },
            scene: Wechat.Scene.SESSION   // share to Timeline
          }, function () {
            alert("Success");
          }, function (reason) {
            alert("Failed: " + reason);
          });*/
        }
      },
      wechatFriendShare(){
        var that = this;
        Wechat.share({
          message: {
            title: "Hi, there",
            description: "This is description.",
            thumb: "www/img/thumbnail.png",
            mediaTagName: "TEST-TAG-001",
            messageExt: "这是第三方带的测试字段",
            messageAction: "<action>dotalist</action>",
            media: {
              type: Wechat.Type.IMAGE,
              image: that.img
            }
          },
          scene: Wechat.Scene.TIMELINE   // share to Timeline
        }, function () {
          alert("Success");
        }, function (reason) {
          alert("Failed: " + reason);
        });
      },
      qqShare(){
          var that=this;
        var args = {};
        args.scene = QQSDK.Scene.QQ;//QQSDK.Scene.QQZone,QQSDK.Scene.Favorite
        args.title = that.title;
        args.description = that.description;
        args.image = that.img;
        QQSDK.shareImage(function () {
            that.$vux.toast.show({text:'分享成功',type:'text'});

        }, function (failReason) {
          that.$vux.toast.show({text:'取消分享',type:'text'});
        }, args);
      },
      sinaShare(){

      },
      copy(){
        window.clipboardData.setData("Text",clipBoardContent);
        this.$vux.toast.show({text:'复制成功'});
      }
    },
    computed: {
      ...mapGetters({
        currentDevice:'currentDevice'
      })
    },
    created(){


    }
  }
</script>

<style lang="less">

</style>
