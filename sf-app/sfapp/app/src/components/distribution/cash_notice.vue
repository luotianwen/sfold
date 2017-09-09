<template>
    <div id="cash-notice">
        <!--header-->
        <div id="cash-header">
            <x-header :left-options="{showBack:true,backText:''}" :right-options="{showMore: true}">提现须知</x-header>
        </div>
        <!--content-->
        <div class="cash-notice-content">
            <div class="question-wrapper">
                <div class="ask">Q：什么是已收入佣金?</div>
                <div class="answer">A：即所有通过分销已提现的佣金总和。</div>
            </div>
            <div class="question-wrapper">
                <div class="ask">Q：什么是可提现佣金?</div>
                <div class="answer">A：“我的”分销商购买商品分得佣金。可提现佣金中，不包含已支付，但未过7天无理由退货的订单。</div>
            </div>
            <div class="question-wrapper">
                <div class="ask">Q：什么是待收益佣金?</div>
                <div class="answer">A：“我的”分销商购买商品，分得佣金。待收益佣金指的是，用户已下单并支付，但未过7天无理由退货的订单。过了7天无理由退货后，转变为可提现佣金。</div>
            </div>
        </div>
    </div>
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

    #cash-notice {
        width: 100%;
        height: 100%;
        position: absolute;
        background-color: #ffffff;
        /*header*/
        #cash-header {
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
        .cash-notice-content {
            position: relative;
            background-color: #ffffff;
            padding-top: 14rem/@font-size-base;
            border-top: 18rem/@font-size-base solid #ededed;
            .question-wrapper {
                font-size: 28rem/@font-size-base;
                margin: 40rem/@font-size-base 24rem/@font-size-base 0;
                .ask {
                    color: #1e6c8e;
                    margin-bottom: 10rem/@font-size-base;
                }
                .answer {
                    color: #4c5154;
                    line-height: 48rem/@font-size-base;
                }
            }
        }
    }
</style>
