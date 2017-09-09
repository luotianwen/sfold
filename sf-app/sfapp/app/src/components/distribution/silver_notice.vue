<template>
    <transition name="fade">
        <div id="silver-notice" transition="fade">
            <!--header-->
            <div class="silver-header">
                <x-header :left-options="{showBack:true,backText:''}" :right-options="{showMore: true}">兑换须知</x-header>
            </div>
            <!--content-->
            <div class="silver-content-wrapper">
                <h1>银币兑换须知</h1>
                <p>1、每成功邀请一好友成为分销商，即可获得10银币；<br>2、银币只可用于兑换到世峰E卡；<br>3、银币与世峰E卡兑换比例为10:1（元）。</p>
            </div>
        </div>
    </transition>
</template>

<script type="text/ecmascript-6">
    import {XHeader} from 'vux';

    export default{
        components: {
            XHeader
        }
    }
</script>

<style lang="less" rel="stylesheet/less">
    @import "../../assets/css/base.less";

    #silver-notice {
        width: 100%;
        height: 100%;
        position: relative;
        &.fade-transition {
            transform: translate3d(0, 0, 0);
        }
        &.fade-enter, &.fade-leave-active {
            transform: translate3d(100%, 0, 0);
        }
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
            border-top: 18rem/@font-size-base solid #ededed;
            h1 {
                font-weight: bold;
                color: #161616;
                font-size: 28rem/@font-size-base;
                margin: 54rem/@font-size-base 24rem/@font-size-base 10rem/@font-size-base;
            }
            p {
                margin: 0 24rem/@font-size-base;
                font-size: 28rem/@font-size-base;
                color: #4c5154;
                line-height: 48rem/@font-size-base;
            }
        }
    }
</style>
