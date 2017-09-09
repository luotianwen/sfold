<template>
    <div id="silver">
        <!--header-->
        <div class="silver-header">
            <x-header :left-options="{showBack:true,backText:''}" :right-options="{showMore: true}">我的银币</x-header>
        </div>
        <!--content-->
        <div class="silver-content-wrapper">
            <div class="silver-info-wrapper">
                <div class="txt">银币资产(个)</div>
                <div class="silver-count">2568</div>
            </div>
            <div class="btn-exchange" @click="clickExchange">兑换到世峰E卡</div>
            <div class="other-wrapper">
                <span class="silver-notice" @click="silverNotice">兑换须知</span>
                <span class="commission-detail" @click="commissionDetail">佣金明细</span>
            </div>
        </div>
    </div>
</template>

<script type="text/ecmascript-6">
    import {XHeader} from 'vux';

    export default{
        data(){
            return {}
        },
        methods: {
            //兑换按钮
            clickExchange(){
                this.showDialog = true;
                console.log("..........");
            },
            //兑换须知
            silverNotice(){
                this.$router.push({name: 'SilverNotice'});
            },
            //佣金明细
            commissionDetail(){
                this.$router.push({name:'CommissionDetail'});
            }
        },
        components: {
            XHeader
        }
    }
</script>

<style lang="less" rel="stylesheet/less">
    @import "../../assets/css/base.less";

    #silver {
        width: 100%;
        height: 100%;
        position: relative;
        .silver-header {
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
        .silver-content-wrapper {
            position: relative;
            margin-top: 88rem/@font-size-base;
            background-color: #ffffff;
            .silver-info-wrapper {
                width: 100%;
                height: 200rem/@font-size-base;
                text-align: center;
                background-color: @main-color;
                .txt {
                    margin-bottom: 30rem/@font-size-base;
                    padding-top: 54rem/@font-size-base;
                    font-size: 22rem/@font-size-base;
                    color: #a5f9ad;
                }
                .silver-count {
                    font-size: 40rem/@font-size-base;
                    color: #ffffff;
                }
            }
            //兑换按钮
            .btn-exchange {
                margin: 54rem/@font-size-base 36rem/@font-size-base;
                border: 1px solid @main-color;
                border-radius: 10rem/@font-size-base;
                color: @main-color;
                text-align: center;
                height: 86rem/@font-size-base;
                line-height: 86rem/@font-size-base;
                font-size: 30rem/@font-size-base;
                &:active {
                    background-color: #e0e0e0;
                }
            }
            .other-wrapper {
                margin: auto 36rem/@font-size-base;
                font-size: 26rem/@font-size-base;
                color: #707070;
                /*兑换须知*/
                .silver-notice, .commission-detail {
                    display: inline-block;
                    &:active {
                        color: #a0a0a0;
                    }
                }
                .silver-notice {
                    float: left;
                }
                //佣金明细
                .commission-detail {
                    float: right;
                }
            }
        }
    }
</style>
