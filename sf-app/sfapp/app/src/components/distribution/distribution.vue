<template>
    <!--我的分销-->
    <div id="distribution">
        <!--header-->
        <div class="distribution-header">
            <img class="back" v-show="true" @click="back" src="../../assets/image/set/Back_btn_img.png">
            <div class="empty"></div>
            <span class="title">我的分销</span>
            <span class="header-about" @click="aboutDistribution">关于分销</span>
        </div>
        <!--content-->
        <div class="distribution-content-wrapper">
            <!--分销订单、我的分销商、我要提现、我的钱包-->
            <div class="distribution-option-wrapper">
                <!--分销订单-->
                <div class="distribution-order-wrapper" @click="goDistributionOrder">
                    <img src="../../assets/image/distribution/icon_distribution_order.png">
                    <span class="distribution-txt">分销订单</span>
                </div>
                <!--我的分销商-->
                <div class="distributor-wrapper" @click="goDistributor">
                    <img src="../../assets/image/distribution/icon_distributor.png">
                    <span class="distribution-txt">我的分销商</span>
                </div>
                <!--我要提现-->
                <div class="get-cash-wrapper" @click="goCash">
                    <img src="../../assets/image/distribution/icon_get_cash.png">
                    <span class="distribution-txt">我要提现</span>
                </div>
                <!--我的银币-->
                <div class="wallet-wrapper" @click="goSilver">
                    <img src="../../assets/image/distribution/icon_wallet.png">
                    <span class="distribution-txt">我的银币</span>
                </div>
            </div>
            <!--今天、昨天、本周-->
            <div class="distribution-date-wrapper">
                <!--今天-->
                <div class="today-wrapper">
                    <div class="content-wrapper">
                        <img class="img-date" src="../../assets/image/distribution/icon_today.png">
                        <div class="info-wrapper">
                            <div class="time-wrapper">
                                <div class="txt">今天</div>
                                <div class="date">04-20</div>
                            </div>
                            <div class="order-wrapper">
                                <div class="price-wrapper">
                                    <span class="plus">+</span><span class="rmb">¥</span><span class="price">3</span><span
                                        class="price-small">.06</span>
                                </div>
                                <div class="count"><span>1</span>笔</div>
                            </div>
                        </div>
                        <img class="img-arrow" src="../../assets/image/goods/arrow_right.png">
                    </div>
                </div>
                <!--昨天-->
                <div class="yesterday-wrapper">
                    <div class="content-wrapper">
                        <img class="img-date" src="../../assets/image/distribution/icon_yesterday.png">
                        <div class="info-wrapper">
                            <div class="time-wrapper">
                                <div class="txt">昨天</div>
                                <div class="date">04-19</div>
                            </div>
                            <div class="order-wrapper">
                                <div class="price-wrapper">
                                    <span class="plus">+</span><span class="rmb">¥</span><span class="price">6</span><span
                                        class="price-small">.00</span>
                                </div>
                                <div class="count"><span>2</span>笔</div>
                            </div>
                        </div>
                        <img class="img-arrow" src="../../assets/image/goods/arrow_right.png">
                    </div>
                </div>
                <!--本周-->
                <div class="week-wrapper">
                    <div class="content-wrapper">
                        <img class="img-date" src="../../assets/image/distribution/icon_week.png">
                        <div class="info-wrapper">
                            <div class="time-wrapper">
                                <div class="txt">本周</div>
                                <div class="date">04-20~04-23</div>
                            </div>
                            <div class="order-wrapper">
                                <div class="price-wrapper">
                                    <span class="plus">+</span><span class="rmb">¥</span><span class="price">9</span><span
                                        class="price-small">.00</span>
                                </div>
                                <div class="count"><span>3</span>笔</div>
                            </div>
                        </div>
                        <img class="img-arrow" src="../../assets/image/goods/arrow_right.png">
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script type="text/ecmascript-6">
    import {XHeader} from 'vux'

    export default{
        methods: {
            back(){
                //history.go(-1);
            },
            //分销规则
            aboutDistribution(){
                this.$router.push({name: 'AboutDistribution'});
            },
            //分销订单
            goDistributionOrder(){
                this.$router.push({name: 'OrderDistribution'});
            },
            //我的分销商
            goDistributor(){
                this.$router.push({name:'Distributor'});
            },
            //我的提现
            goCash(){
                this.$router.push({name:'Cash'});
            },
            //我的银币
            goSilver(){
                this.$router.push({name:'Silver'});
            }
        },
        components: {
            XHeader
        }
    }
</script>

<style lang="less" rel="stylesheet/less">
    @import "../../assets/css/base.less";

    #distribution {
        /*header*/
        .distribution-header {
            display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
            display: -moz-box; /* 老版本语法: Firefox (buggy) */
            display: -ms-flexbox; /* 混合版本语法: IE 10 */
            display: -webkit-flex; /* 新版本语法: Chrome 21+ */
            display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
            width: 100%;
            height: 88rem/@font-size-base;
            border-bottom: 1px solid @header-border-color;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 999999;
            background-color: #ffffff;
            .back {
                flex: 0 0 auto;
                width: 20rem/@font-size-base;
                height: 34rem/@font-size-base;
                padding: 10rem/@font-size-base;
                margin-left: 24rem/@font-size-base;
                margin-top: 16rem/@font-size-base;
            }
            .empty {
                flex: 1;
            }
            .title {
                position: absolute;
                text-align: center;
                color: #161616;
                left: 30%;
                right: 30%;
                font-size: 32rem/@font-size-base;
                height: 88rem/@font-size-base;
                line-height: 88rem/@font-size-base;
            }
            .header-about {
                flex: 0 0 auto;
                display: inline-block;
                text-align: center;
                font-size: 30rem/@font-size-base;
                color: #5f646e;
                line-height: 88rem/@font-size-base;
                height: 88rem/@font-size-base;
                margin-right: 28rem/@font-size-base;
                &:active{
                    color: #a6a6a6;
                }
            }
        }
        /*content*/
        .distribution-content-wrapper {
            position: fixed;
            width: 100%;
            height: 100%;
            background-color: @bg-color;
            top: 88rem/@font-size-base;
            overflow: hidden;
            /*option 分销订单、我的分销商、我要提现、我的钱包*/
            .distribution-option-wrapper {
                display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
                display: -moz-box; /* 老版本语法: Firefox (buggy) */
                display: -ms-flexbox; /* 混合版本语法: IE 10 */
                display: -webkit-flex; /* 新版本语法: Chrome 21+ */
                display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
                width: 100%;
                border-top: 18rem/@font-size-base solid #ededed;
                border-bottom: 18rem/@font-size-base solid #ededed;
                .distribution-order-wrapper, .distributor-wrapper, .get-cash-wrapper, .wallet-wrapper {
                    flex: 1;
                    height: 154rem/@font-size-base;
                    text-align: center;
                    background-color: #ffffff;
                    img {
                        display: block;
                        margin: 31rem/@font-size-base auto auto;
                    }
                    .distribution-txt {
                        display: block;
                        width: 100%;
                        text-align: center;
                        margin-top: 20rem/@font-size-base;
                    }
                    &:active {
                        background-color: @color-white-to-gray;
                    }
                }
                .distribution-order-wrapper {
                    img {
                        width: 42rem/@font-size-base;
                        height: 48rem/@font-size-base;
                    }
                }
                .distributor-wrapper {
                    img {
                        width: 48rem/@font-size-base;
                        height: 48rem/@font-size-base;
                    }
                }
                .get-cash-wrapper {
                    img {
                        width: 48rem/@font-size-base;
                        height: 48rem/@font-size-base;
                    }
                }
                .wallet-wrapper {
                    img {
                        width: 48rem/@font-size-base;
                        height: 40rem/@font-size-base;
                        margin: 39rem/@font-size-base auto auto !important;
                    }
                }
            }
            /*昨天、今天、明天*/
            .distribution-date-wrapper {
                position: relative;
                width: 100%;
                .today-wrapper, .yesterday-wrapper, .week-wrapper {
                    background-color: #ffffff;
                    &:active {
                        background-color: @color-white-to-gray;
                    }
                    .content-wrapper {
                        display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
                        display: -moz-box; /* 老版本语法: Firefox (buggy) */
                        display: -ms-flexbox; /* 混合版本语法: IE 10 */
                        display: -webkit-flex; /* 新版本语法: Chrome 21+ */
                        display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
                        margin: 0 22rem/@font-size-base;
                        border-bottom: 1px solid @border-color;
                        height: 114rem/@font-size-base;
                        /*日期图标*/
                        .img-date {
                            flex: 0 0 auto;
                            width: 66rem/@font-size-base;
                            height: 66rem/@font-size-base;
                            display: block;
                            margin: auto 24rem/@font-size-base auto 0;
                        }
                        /*日期内容展示*/
                        .info-wrapper {
                            flex: 1;
                            position: relative;
                            display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
                            display: -moz-box; /* 老版本语法: Firefox (buggy) */
                            display: -ms-flexbox; /* 混合版本语法: IE 10 */
                            display: -webkit-flex; /* 新版本语法: Chrome 21+ */
                            display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
                            .time-wrapper {
                                flex: 1;
                                text-align: left;
                                margin: auto;
                                .txt {
                                    font-size: 28rem/@font-size-base;
                                    color: @normal-font-gray;
                                    margin-bottom: 12rem/@font-size-base;
                                }
                                .date {
                                    font-size: 24rem/@font-size-base;
                                    color: #bababa;
                                }
                            }
                            .order-wrapper {
                                flex: 0 0 auto;
                                margin: auto;
                                .price-wrapper {
                                    color: @red;
                                    margin-bottom: 12rem/@font-size-base;
                                    .plus{
                                        display: inline-block;
                                        font-size: 28rem/@font-size-base;
                                    }
                                    .rmb {
                                        display: inline-block;
                                        font-size: 24rem/@font-size-base;
                                        margin-left: 4rem/@font-size-base;
                                        margin-right: 8rem/@font-size-base;
                                    }
                                    .price {
                                        display: inline-block;
                                        font-size: 32rem/@font-size-base;
                                    }
                                    .price-small {
                                        display: inline-block;
                                        font-size: 24rem/@font-size-base;
                                    }
                                }
                                .count {
                                    font-size: 20rem/@font-size-base;
                                    color: #bababa;
                                    text-align: right;
                                    span {
                                        font-size: 24rem/@font-size-base;
                                    }
                                }
                            }
                        }
                        .img-arrow {
                            flex: 0 0 auto;
                            width: 14rem/@font-size-base;
                            height: 24rem/@font-size-base;
                            display: block;
                            margin: auto 0 auto 24rem/@font-size-base;
                        }
                    }
                }
                .week-wrapper {
                    .content-wrapper {
                        border-bottom: none !important;
                    }
                }
            }
        }
    }
</style>
