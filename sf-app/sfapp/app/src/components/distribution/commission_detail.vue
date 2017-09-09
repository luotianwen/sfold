<!--佣金明细-->
<template>
    <div id="commission-detail">
        <!--header-->
        <div class="commission-detail-header">
            <x-header :left-options="{showBack:true,backText:''}" :right-options="{showMore: true}">明细列表</x-header>
        </div>
        <!--tab 收入、兑换-->
        <div class="tab-wrapper">
            <div class="tab-income-wrapper" @click="showIncome">
                <span class="income" :class="{'selected':showIncomeTab}">收入</span>
            </div>
            <span class="line"></span>
            <div class="tab-exchange-wrapper" @click="showExchange">
                <span class="exchange" :class="{'selected':!showIncomeTab}">兑换</span>
            </div>
        </div>
        <!--content-->
        <div class="commission-detail-content">
            <ul v-if="showIncomeTab">
                <li class="commission-item" v-for="income in incomeList">
                    <img src="../../assets/image/distribution/icon_income.png">
                    <div class="desc-wrapper">
                        <div class="txt">银币收入</div>
                        <div class="time">{{income.time}}</div>
                    </div>
                    <div class="count">{{income.count}}个</div>
                </li>
            </ul>
            <ul v-if="!showIncomeTab">
                <li class="commission-item" v-for="exchange in exchangeList">
                    <img src="../../assets/image/distribution/icon_exchange.png">
                    <div class="desc-wrapper">
                        <div class="txt">兑换到E卡</div>
                        <div class="time">{{exchange.time}}</div>
                    </div>
                    <div class="count"><span>¥</span>{{exchange.price}}</div>
                </li>
            </ul>
            <!--empty tip-->
            <div class="empty-wrapper"
                 v-if="(showIncomeTab && incomeList.length==0) || (!showIncomeTab && exchangeList.length==0)">
                <img src="../../assets/image/distribution/empty_order_tip.png">
                <div class="txt-empty-tip">您暂无相关记录</div>
            </div>
        </div>
        <!--空背景-->
        <div style="width:100%;height:100%;position: fixed;background-color: #ededed;top: 88px;z-index: 50;"></div>
    </div>
</template>

<script type="text/ecmascript-6">
    import {XHeader} from 'vux';
    export default{
        data(){
            return {
                showIncomeTab: true,
                exchangeList: [
                    {
                        time: '2017-04-21 17:22',
                        price: 2.38
                    }
                ],
                incomeList: [
                    {
                        time: '2017-04-20 15:32',
                        count: 132
                    },
                    {
                        time: '2017-04-20 15:32',
                        count: 190
                    },
                    {
                        time: '2017-04-20 15:32',
                        count: 100
                    },
                    {
                        time: '2017-04-20 15:32',
                        count: 100
                    },
                    {
                        time: '2017-04-20 15:32',
                        count: 100
                    },
                    {
                        time: '2017-04-20 15:32',
                        count: 100
                    },
                    {
                        time: '2017-04-20 15:32',
                        count: 100
                    },
                    {
                        time: '2017-04-20 15:32',
                        count: 100
                    },
                    {
                        time: '2017-04-20 15:32',
                        count: 100
                    },
                    {
                        time: '2017-04-20 15:32',
                        count: 100
                    },
                    {
                        time: '2017-04-20 15:32',
                        count: 100
                    },
                    {
                        time: '2017-04-20 15:32',
                        count: 100
                    },
                    {
                        time: '2017-04-20 15:32',
                        count: 100
                    },
                    {
                        time: '2017-04-20 15:32',
                        count: 100
                    },
                    {
                        time: '2017-04-20 15:32',
                        count: 100
                    },
                    {
                        time: '2017-04-20 15:32',
                        count: 100
                    }
                ]
            }
        },
        methods: {
            showIncome(){
                this.showIncomeTab = true;
            },
            showExchange(){
                this.showIncomeTab = false;
            }
        },
        components: {
            XHeader
        }
    }
</script>

<style lang="less" rel="stylesheet/less">
    @import "../../assets/css/base.less";

    #commission-detail {
        width: 100%;
        height: 100%;
        position: relative;
        .commission-detail-header {
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
        .tab-wrapper {
            display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
            display: -moz-box; /* 老版本语法: Firefox (buggy) */
            display: -ms-flexbox; /* 混合版本语法: IE 10 */
            display: -webkit-flex; /* 新版本语法: Chrome 21+ */
            display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
            position: fixed;
            width: 100%;
            height: 89rem/@font-size-base;
            text-align: center;
            margin-top: 1px;
            border-bottom: 1px solid #e0e0e0;
            background-color: #ffffff;
            top: 88rem/@font-size-base;
            z-index: 100;
            .tab-income-wrapper, .tab-exchange-wrapper {
                flex: 1;
                margin: auto;
                background-color: #ffffff;
                .income, .exchange {
                    display: inline-block;
                    width: 70rem/@font-size-base;
                    height: 84rem/@font-size-base;
                    text-align: center;
                    line-height: 80rem/@font-size-base;
                    font-size: 28rem/@font-size-base;
                    color: @normal-font-gray;
                    border-bottom: 4rem/@font-size-base solid #fff;
                    &.selected {
                        color: @main-color;
                        border-bottom: 2px solid @main-color;
                    }
                }
            }
            .line {
                flex: 0 0 auto;
                width: 1px;
                height: 40rem/@font-size-base;
                background-color: #e0e0e0;
                margin: 22rem/@font-size-base 0;
            }
        }
        .commission-detail-content {
            position: relative;
            width: 100%;
            height: 100%;
            margin-top: 177rem/@font-size-base;
            z-index: 90;
            background-color: #ffffff;
            .commission-item {
                display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
                display: -moz-box; /* 老版本语法: Firefox (buggy) */
                display: -ms-flexbox; /* 混合版本语法: IE 10 */
                display: -webkit-flex; /* 新版本语法: Chrome 21+ */
                display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
                height: 114rem/@font-size-base;
                margin: 0 22rem/@font-size-base;
                background-color: #ffffff;
                border-bottom: 1px solid #e0e0e0;
                img {
                    flex: 0 0 auto;
                    width: 66rem/@font-size-base;
                    height: 66rem/@font-size-base;
                    display: block;
                    margin: auto 24rem/@font-size-base auto 0;
                }
                .desc-wrapper {
                    flex: 1;
                    margin: auto;
                    .txt {
                        font-size: 28rem/@font-size-base;
                        color: @normal-font-gray;
                        margin-bottom: 12rem/@font-size-base;
                    }
                    .time {
                        font-size: 24rem/@font-size-base;
                        color: #999999;
                    }
                }
                .count {
                    flex: 0 0 auto;
                    font-size: 28rem/@font-size-base;
                    color: @normal-font-gray;
                    line-height: 114rem/@font-size-base;
                    span {
                        display: inline-block;
                        margin-right: 8rem/@font-size-base;
                    }
                }
            }
            .empty-wrapper {
                position: fixed;
                width: 100%;
                height: 100%;
                background-color: #ededed;
                img {
                    display: block;
                    margin: 330rem/@font-size-base auto 0;
                    width: 180rem/@font-size-base;
                    height: 180rem/@font-size-base;
                }
                .txt-empty-tip {
                    display: inline-block;
                    width: 100%;
                    text-align: center;
                    font-size: 28rem/@font-size-base;
                    color: #707070;
                    margin-top: 24rem/@font-size-base;
                }
            }
        }
    }

</style>
