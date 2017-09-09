<template>
    <div id="get-cash">
        <!--header-->
        <div class="get-cash-header">
            <x-header :left-options="{showBack:true,backText:''}" :right-options="{showMore: true}">提现</x-header>
        </div>
        <!--content-->
        <div class="get-cash-content">
            <div class="content-wrapper">
                <div class="account-wrapper">
                    <span class="txt">到账账户</span><span class="card">世峰E卡</span>
                </div>
                <div class="txt-get-money">提现金额</div>
                <div class="cash-wrapper">
                    <span class="rmb">¥</span><input type="text" maxlength="6" placeholder="0.00" v-model="getCash"
                                                     oninput="if(value.length>6)value=value.slice(0,6)"
                                                     onkeyup="this.value=this.value.replace(/[^\d.]/g,'');
                                                    this.value=this.value.replace(/\.{2,}/g,'.');
                                                    this.value=this.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');"
                                                     onafterpaste="this.value=this.value.replace(/[^\d.]/g,'')">
                </div>
                <!-- 可提现金额-->
                <div class="can-use-cash">
                    <div v-show="true">
                        <span class="cash-txt">可提现金额¥3.25，</span><span class="get-cash-all"
                                                                       @click="getAllMoney()">全部提现</span>
                    </div>
                    <span v-show="false" class="wrong-tip">您的输入金额超过可提现佣金</span>
                </div>
                <div class="btn-get-cash" :class="{'disable':getCash ==0}" @click="clickGetCash">提现</div>
            </div>
        </div>
        <transition name="fade">
            <div id="get-cash-dialog" v-show="showDialog" transition="fade">
                <div class="dialog-wrapper">
                    <div class="txt-tip">确定将佣金提现到世峰E卡吗？</div>
                    <div class="action-wrapper">
                        <span class="cancel" @click="cancel">取消</span><span class="confirm">确定</span>
                    </div>
                </div>
            </div>
        </transition>
    </div>
</template>

<script type="text/ecmascript-6">
    import {XHeader} from 'vux';

    export default{
        data(){
            return {
                getCash: '',
                showDialog: false
            }
        },
        methods: {
            getAllMoney(){

            },
            //提现按钮
            clickGetCash(){
                this.showDialog = true;
                console.log("..........");
            },
            cancel(){
                this.showDialog = false;
            }
        },
        components: {
            XHeader
        }
    }
</script>

<style lang="less" rel="stylesheet/less">
    @import "../../assets/css/base.less";

    #get-cash {
        width: 100%;
        height: 100%;
        position: relative;
        .get-cash-header {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            z-index: 100;
            .vux-header {
                height: 88rem/@font-size-base !important;
                background-color: #ffffff !important;
                .vux-header-left {
                    .left-arrow {
                        &:before {
                            border-color: #646973 !important;
                            border-width: 1px 0 0 1px !important;
                            width: 22rem/@font-size-base !important;
                            height: 22rem/@font-size-base !important;
                            top: 34rem/@font-size-base !important;
                            left: 34rem/@font-size-base !important;
                        }
                    }
                }
                h1 {
                    font-size: 32rem/@font-size-base !important;
                    color: #161616 !important;
                }
                .vux-header-right {
                    a {
                        color: #646973 !important;
                    }
                }
            }
        }
        .get-cash-content {
            position: fixed;
            top: 88rem/@font-size-base;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: #ededed;
            .content-wrapper {
                position: relative;
                margin: 20rem/@font-size-base;
                padding: 0 30rem/@font-size-base 54rem/@font-size-base;
                border-radius: 10rem/@font-size-base;
                background-color: #ffffff;
                .account-wrapper {
                    height: 88rem/@font-size-base;
                    font-size: 28rem/@font-size-base;
                    line-height: 88rem/@font-size-base;
                    border-bottom: 1px solid #e0e0e0;
                    .txt {
                        display: inline-block;
                        color: @normal-font-gray;
                        margin-right: 28rem/@font-size-base;
                    }
                    .card {
                        display: inline-block;
                        color: #1e6c8e;
                    }
                }
                .txt-get-money {
                    font-size: 28rem/@font-size-base;
                    color: @normal-font-gray;
                    margin: 30rem/@font-size-base 0 20rem/@font-size-base;

                }
                .cash-wrapper {
                    display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
                    display: -moz-box; /* 老版本语法: Firefox (buggy) */
                    display: -ms-flexbox; /* 混合版本语法: IE 10 */
                    display: -webkit-flex; /* 新版本语法: Chrome 21+ */
                    display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
                    padding-bottom: 22rem/@font-size-base;
                    border-bottom: 1px solid #e0e0e0;
                    .rmb {
                        flex: 0 0 auto;
                        display: inline-block;
                        font-size: 30rem/@font-size-base;
                        color: #161616;
                        margin-top: 10rem/@font-size-base;
                        margin-right: 14rem/@font-size-base;
                    }
                    input {
                        flex: 1;
                        color: #161616;
                        font-size: 60rem/@font-size-base;
                        overflow: hidden;
                    }
                }
                .can-use-cash {
                    margin-top: 20rem/@font-size-base;
                    font-size: 24rem/@font-size-base;
                    .cash-txt {
                        display: inline-block;
                        color: #999999;
                    }
                    .get-cash-all {
                        display: inline-block;
                        color: @main-color;
                        &:active {
                            color: #04be20;
                        }
                    }
                    .wrong-tip {
                        display: inline-block;
                        color: @red;
                    }
                }
                .btn-get-cash {
                    margin: 53rem/@font-size-base 30rem/@font-size-base 0;
                    color: @main-color;
                    font-size: 30rem/@font-size-base;
                    border: 1px solid @main-color;
                    border-radius: 10rem/@font-size-base;
                    text-align: center;
                    height: 86rem/@font-size-base;
                    line-height: 86rem/@font-size-base;
                    background-color: #ffffff;
                    &:active {
                        background-color: #e0e0e0;
                    }
                    &.disable {
                        color: #b4dbba;
                        border: 1px solid #b4dbba;
                        &:active {
                            background-color: #FFFFFF;
                        }
                    }
                }
            }
        }
        #get-cash-dialog {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: 9999;
            background-color: rgba(0, 0, 0, 0.4);
            transition: all 0.5s;
            &.fade-transition {
                opacity: 1;
                background: rgba(0, 0, 0, 0.4);
            }
            &.fade-enter, &.fade-leave-active {
                opacity: 0;
            }
            .dialog-wrapper {
                background-color: #FFFFFF;
                border-radius: 10rem/@font-size-base;
                margin: 64% 10%;
                .txt-tip {
                    color: #161616;
                    text-align: center;
                    height: 107rem/@font-size-base;
                    line-height: 107rem/@font-size-base;
                    font-size: 30rem/@font-size-base;
                    border-bottom: 1px solid #e0e0e0;
                }
                .action-wrapper {
                    position: relative;
                    display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
                    display: -moz-box; /* 老版本语法: Firefox (buggy) */
                    display: -ms-flexbox; /* 混合版本语法: IE 10 */
                    display: -webkit-flex; /* 新版本语法: Chrome 21+ */
                    display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
                    height: 88rem/@font-size-base;
                    .cancel {
                        flex: 1;
                        text-align: center;
                        line-height: 88rem/@font-size-base;
                        color: #454545;
                        border-right: 1px solid #e0e0e0;
                        font-size: 30rem/@font-size-base;
                        &:active {
                            background-color: #e0e0e0;
                        }
                    }
                    .confirm {
                        flex: 1;
                        text-align: center;
                        line-height: 88rem/@font-size-base;
                        font-size: 30rem/@font-size-base;
                        color: @main-color;
                        &:active {
                            background-color: #e0e0e0;
                        }
                    }
                }
            }
        }
    }
</style>
