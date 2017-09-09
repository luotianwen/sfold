<template>
    <!--分销订单-->
    <div id="distribution-order" :class="{'empty':orderList.length<4,'full':orderList.length >4}">
        <!--header-->
        <div class="distribution-order-header">
            <img class="back" v-show="true" @click="back" src="../../assets/image/distribution/icon_back_white.png">
            <div class="empty"></div>
            <div class="title-wrapper">
                <span class="title" :class="{'show-menu':isShowPanel}" @click="showMenu">分销订单</span>
            </div>
        </div>
        <div class="distribution-order-content">
            <div class="order-total-wrapper">
                <!--订单数量-->
                <div class="order-wrapper">
                    <div class="txt">订单数量</div>
                    <div class="num">15</div>
                </div>
                <!--总订单金额-->
                <div class="order-wrapper">
                    <div class="txt">总订单金额</div>
                    <div class="num">1562</div>
                </div>
                <!--可提现金额-->
                <div class="order-wrapper">
                    <div class="txt">可提现金额</div>
                    <div class="num">175</div>
                </div>
            </div>
            <!--订单列表-->
            <ul v-if="orderList.length>0">
                <li class="order-item-wrapper" v-for="(order,index) in orderList">
                    <div class="time-wrapper">
                        <span class="txt-time">付款时间:</span><span class="time">{{order.time}}</span>
                    </div>
                    <div class="money-wrapper">
                        <span class="txt">订单总额</span><span class="money"><span>¥</span>{{order.totalMoney}}</span>
                    </div>
                    <div class="commission-wrapper">
                        <span class="txt">分销佣金</span><span class="commission"><span class="rmb">¥</span>{{order.commission}}<span
                            class="price-small">.00</span></span>
                    </div>
                    <div class="distributor-wrapper">
                        <span class="txt">我的分销商</span><span class="distributor">{{order.distributor}}</span>
                    </div>
                </li>
            </ul>
            <!--分销订单为空的页面-->
            <div class="empty-wrapper" v-if="orderList.length==0">
                <img src="../../assets/image/distribution/empty_order_tip.png">
                <span class="txt-empty-tip">您暂无分销订单</span>
            </div>
        </div>
        <!--条件筛选面板-->
        <transition name="fade">
            <div class="select-panel-wrapper" v-if="isShowPanel" @click="hide" transition="fade">
                <div class="condition-wrapper">
                    <span class="order-all" @click="selectCondition(4)">全部订单</span>
                    <span class="order-today" @click="selectCondition(1)">今日订单</span>
                    <span class="order-week" @click="selectCondition(2)">本周订单</span>
                    <span class="order-month" @click="selectCondition(3)">本月订单</span>
                </div>
            </div>
        </transition>
    </div>
</template>

<script type="text/ecmascript-6">
    export default{
        data(){
            return {
                isShowPanel: false,
                orderList: [
                    {
                        time: "2017-04-20 16:17:06",
                        totalMoney: 29.39,
                        distributor: '张三',
                        commission: 2
                    },
                    {
                        time: "2017-04-20 18:17:06",
                        totalMoney: 255.39,
                        distributor: '李四',
                        commission: 1
                    },
                    {
                        time: "2017-04-20 16:17:06",
                        totalMoney: 29.39,
                        distributor: '张三',
                        commission: 2
                    }
                ]
            }
        },
        methods: {
            back(){
                history.go(-1);
            },
            showMenu(){
                this.isShowPanel = !this.isShowPanel;
            },
            //隐藏条件筛选末班
            hide(){
                this.isShowPanel = false;
            },
            selectCondition(type){
                //进行type相对应得网络请求
            }
        }
    }
</script>

<style lang="less" rel="stylesheet/less">
    @import "../../assets/css/base.less";

    #distribution-order {
        width: 100%;
        height: 100%;
        position: absolute;
        background: #ededed;
        &.empty {
            position: fixed;
        }
        &.full {
            position: relative;
        }
        /*header*/
        .distribution-order-header {
            display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
            display: -moz-box; /* 老版本语法: Firefox (buggy) */
            display: -ms-flexbox; /* 混合版本语法: IE 10 */
            display: -webkit-flex; /* 新版本语法: Chrome 21+ */
            display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
            width: 100%;
            height: 88rem/@font-size-base;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 999999;
            background-color: #04ad20;
            .back {
                flex: 0 0 auto;
                width: 18rem/@font-size-base;
                height: 32rem/@font-size-base;
                padding: 10rem/@font-size-base;
                margin-left: 24rem/@font-size-base;
                margin-top: 16rem/@font-size-base;
            }
            .empty {
                flex: 1;
            }
            .title-wrapper {
                position: absolute;
                text-align: center;
                left: 30%;
                right: 30%;
                .title {
                    display: inline-block;
                    color: #ffffff;
                    font-size: 34rem/@font-size-base;
                    height: 88rem/@font-size-base;
                    line-height: 88rem/@font-size-base;
                    background: url('../../assets/image/distribution/arrow_down_white.png') no-repeat right;
                    -webkit-background-size: 6px 3px;
                    background-size: 6px 3px;
                    padding-right: 10px;
                    &.show-menu {
                        background: url('../../assets/image/distribution/arrow_up_white.png') no-repeat right;
                        -webkit-background-size: 6px 3px;
                        background-size: 6px 3px;
                    }
                }
            }

        }
        /*订单统计：订单数量，订单总金额，可提现金额*/
        .distribution-order-content {
            position: relative;
            width: 100%;
            height: 100%;
            background-color: @bg-color;
            margin-top: 88rem/@font-size-base;
            overflow: auto;
            /*订单统计*/
            .order-total-wrapper {
                display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
                display: -moz-box; /* 老版本语法: Firefox (buggy) */
                display: -ms-flexbox; /* 混合版本语法: IE 10 */
                display: -webkit-flex; /* 新版本语法: Chrome 21+ */
                display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
                width: 100%;
                height: 134rem/@font-size-base;
                background-color: #04ad20;
                .order-wrapper {
                    flex: 1;
                    text-align: center;
                    margin: auto;
                    .txt {
                        font-size: 22rem/@font-size-base;
                        color: #a5f9ad;
                        margin-bottom: 18rem/@font-size-base;
                    }
                    .num {
                        font-size: 32rem/@font-size-base;
                        color: #ffffff;
                    }
                }
            }
            /*订单列表*/
            .order-item-wrapper {
                padding: 0 22rem/@font-size-base;
                background-color: #ffffff;
                border-top: 18rem/@font-size-base solid #ededed;
                .time-wrapper {
                    height: 88rem/@font-size-base;
                    font-size: 24rem/@font-size-base;
                    color: @normal-font-gray;
                    line-height: 88rem/@font-size-base;
                    border-bottom: 1px solid #ededed;
                    background-color: #ffffff;
                    .txt-time {
                        display: inline-block;
                        margin-right: 10rem/@font-size-base;
                    }
                }
                .money-wrapper {
                    margin-top: 24rem/@font-size-base;
                    .txt {
                        display: inline-block;
                        font-size: 24rem/@font-size-base;
                        color: #999999;
                    }
                    .money {
                        float: right;
                        display: inline-block;
                        font-size: 24rem/@font-size-base;
                        color: #999999;
                        span {
                            display: inline-block;
                            margin-right: 8rem/@font-size-base;
                        }

                    }
                }
                .commission-wrapper {
                    margin: 20rem/@font-size-base 0;
                    .txt {
                        font-size: 24rem/@font-size-base;
                        color: @normal-font-gray;
                    }
                    .commission {
                        float: right;
                        display: inline-block;
                        font-size: 32rem/@font-size-base;
                        color: @red;
                        .rmb, .price-small {
                            display: inline-block;
                            font-size: 24rem/@font-size-base;
                        }
                        .rmb {
                            margin-right: 8rem/@font-size-base;
                        }
                    }
                }
                .distributor-wrapper {
                    padding-bottom: 24rem/@font-size-base;
                    .txt {
                        font-size: 24rem/@font-size-base;
                        color: #999999;
                    }
                    .distributor {
                        float: right;
                        font-size: 24rem/@font-size-base;
                        color: #999999;
                    }
                }
            }
            /*订单为空时的提示*/
            .empty-wrapper {
                margin-top: 245rem/@font-size-base;
                img {
                    display: block;
                    margin: auto;
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
        .select-panel-wrapper {
            display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
            display: -moz-box; /* 老版本语法: Firefox (buggy) */
            display: -ms-flexbox; /* 混合版本语法: IE 10 */
            display: -webkit-flex; /* 新版本语法: Chrome 21+ */
            display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
            position: fixed;
            width: 100%;
            height: 100%;
            left: 0;
            top: 88rem/@font-size-base;
            background-color: rgba(0, 0, 0, 0.4);
            z-index: 100;
            transition: all 0.5s;
            &.fade-transition{
                opacity: 1;
            }
            &.fade-enter, &.fade-leave-active{
                opacity: 0;
            }
            .condition-wrapper {
                width: 100%;
                background: #FFFFFF;
                display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
                display: -moz-box; /* 老版本语法: Firefox (buggy) */
                display: -ms-flexbox; /* 混合版本语法: IE 10 */
                display: -webkit-flex; /* 新版本语法: Chrome 21+ */
                display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
                height: 88rem/@font-size-base;
                .order-all, .order-today, .order-week, .order-month {
                    flex: 1;
                    font-size: 24rem/@font-size-base;
                    color: @normal-font-gray;
                    line-height: 88rem/@font-size-base;
                    text-align: center;
                    background-color: #ffffff;
                    &:active {
                        color: @main-color;
                        background-color: #f0f0f0;
                    }
                }
            }
        }
    }
</style>
