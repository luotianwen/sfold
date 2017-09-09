<template>
  <transition enter-active-class="animated fadeInRight" leave-active-class="animated fadeOutRight">
    <div class="pay">
      <div class="p_header">
        <x-header :left-options="{showBack:true}">世峰户外收银台</x-header>
      </div>
      <div class="p_content">
        <div class="pc_title">
          <div class="order_amount">订单金额</div>
          <div class="oa_num"><i>￥</i><span class="integer">169</span>.<span class="decimals">00</span></div>
        </div>
        <group>
          <cell title="微信支付" is-link @click.native="weChatPay">
            <img slot="icon" width="20" style="display:block;margin-right:5px;" src="../../assets/image/cart/wx.png">
          </cell>
          <cell title="支付宝支付" is-link @click.native="alipay">
            <img slot="icon" width="20" style="display:block;margin-right:5px;" src="../../assets/image/cart/zfb.png">
          </cell>
        </group>
      </div>
    </div>
  </transition>
</template>
<script>
  import {XHeader, Group, Cell} from 'vux'
  import {mapGetters} from 'vuex'
  export default {
    computed: {
      ...mapGetters({
        currentDevice: 'currentDevice'
      })
    },

    components: {
      XHeader,
      Group,
      Cell
    },
    data() {
      return {}
    },
    methods: {
      weChatPay(){
        if (this.currentDevice.deviceName == 'chat') {//微信浏览器，调用h5支付

        } else if (this.currentDevice.deviceName == 'android' || this.currentDevice.deviceName == 'ios') {

        }
        else if (this.currentDevice.deviceName == 'wap') {
          alert('当前浏览器不支持该付款方式');
        }
      },
      alipay(){
        if (this.currentDevice.deviceName == 'chat') {//微信浏览器，调用h5支付
          alert('微信浏览器请点击右上角在浏览器中打开进行支付');
        } else if (this.currentDevice.deviceName == 'android' || this.currentDevice.deviceName == 'ios') {
          window.alipay.pay({
            tradeNo: new Date().getTime(),
            subject: "世峰商城",
            body: "世峰商城商品",
            price: 0.02,
            notifyUrl: "http://your.server.notify.url"
          }, function (successResults) {
            alert(successResults)
          }, function (errorResults) {
            alert(errorResults)
          });
        }
        else if (this.currentDevice.deviceName == 'wap') {
          alert('当前浏览器不支持该付款方式');
        }
      }
    },
    mounted(){
      console.log(this.currentDevice.deviceName)
    }
  }
</script>

<style rel="stylesheet/less" lang="less">
  @import "../../assets/css/pay.less";

  i, em {
    font-style: normal;
  }
</style>
