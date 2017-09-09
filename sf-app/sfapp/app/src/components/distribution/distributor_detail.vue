<template>
    <div id="distributor-detail">
        <!--header-->
        <div class="distributor-header">
            <img class="back" v-show="true" @click="back" src="../../assets/image/distribution/icon_back_white.png">
            <div class="empty"></div>
            <span class="header-edit" @click="edit">编辑</span>
        </div>
        <!--content-->
        <div class="distributor-detail-wrapper">
            <!--distributor describe-->
            <div class="distributor-desc-wrapper">
                <div class="bg-green"></div>
                <div class="desc-wrapper">
                    <div class="avatar-wrapper">
                        <img src="../../assets/image/distribution/icon_avatar_default.png">
                        <div class="name">张三</div>
                    </div>
                    <div class="phone-wrapper">
                        <span class="txt">手机</span><span class="phone">13652145896</span>
                    </div>
                    <div class="remark-wrapper">
                        <span class="txt">备注</span><span
                            class="remark">这是分销商备注这是分销商备注这是分销商备注这是分销商备注这是分销商备注这是分销商备注</span>
                    </div>
                </div>
            </div>
            <!--order total count-->
            <div class="order-count" v-if="orderList.length>0">订单总数(09)</div>
            <ul>
                <li class="order-item-wrapper" v-for="order in orderList">
                    <div class="time-wrapper">
                        <span class="txt-time">付款时间:</span><span class="time">{{order.time}}</span>
                    </div>
                    <div class="money-wrapper">
                        <span class="txt">订单总额</span><span class="money"><span>¥</span>{{order.totalMoney}}</span>
                    </div>
                    <div class="commission-wrapper">
                        <span class="txt">分销佣金</span><span class="commission"><span class="plus">+</span><span
                            class="rmb">¥</span>{{order.commission}}<span
                            class="price-small">.00</span></span>
                    </div>
                </li>
            </ul>
        </div>
        <!--空背景-->
        <div style="width:100%;height:100%;position: fixed;background-color: #ededed;top: 44px;z-index: 50;"></div>
    </div>
</template>

<script type="text/ecmascript-6">
    export default{
        data(){
            return {
                orderList: [
                    {
                        time: '2017-04-21 16:06:36',
                        totalMoney: 23.93,
                        commission: 3.02
                    },
                    {
                        time: '2017-04-21 19:25:36',
                        totalMoney: 293.93,
                        commission: 14.26
                    }
                ]
            }
        },
        methods: {
            back(){
                history.go(-1);
            },
            edit(){
                this.$router.push({name: 'DistributorEdit'});
            }
        }
    }
</script>

<style lang="less" rel="stylesheet/less">
    @import "../../assets/css/base.less";

    #distributor-detail {
        width: 100%;
        height: 100%;
        /*header*/
        .distributor-header {
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
            z-index: 1000;
            background-color: @main-color;
            .back {
                flex: 0 0 auto;
                width: 18rem/@font-size-base;
                height: 32rem/@font-size-base;
                display: block;
                margin: auto 0 auto 34rem/@font-size-base;
            }
            .empty {
                flex: 1;
            }
            .header-edit {
                flex: 0 0 auto;
                display: inline-block;
                text-align: right;
                font-size: 30rem/@font-size-base;
                color: #ffffff;
                padding: 10rem/@font-size-base;
                line-height: 68rem/@font-size-base;
                margin-right: 18rem/@font-size-base;
                &:active {
                    color: #e5e6e7;
                }

            }
        }
        /*content*/
        .distributor-detail-wrapper {
            position: relative;
            margin-top: 88rem/@font-size-base;
            background-color: #ffffff;
            z-index: 88;
            /*distributor describe*/
            .distributor-desc-wrapper {
                .bg-green {
                    width: 100%;
                    height: 88rem/@font-size-base;
                    background-color: @main-color;
                    z-index: 50;
                }
                .desc-wrapper {
                    margin-top: -52rem/@font-size-base;
                    .avatar-wrapper {
                        text-align: center;
                        img {
                            width: 100rem/@font-size-base;
                            height: 100rem/@font-size-base;
                        }
                        .name {
                            margin-top: 24rem/@font-size-base;
                            margin-bottom: 70rem/@font-size-base;
                            font-size: 32rem/@font-size-base;
                            color: @normal-font-gray;
                        }
                    }
                    .phone-wrapper {
                        margin: 0 22rem/@font-size-base;
                        padding-bottom: 32rem/@font-size-base;
                        border-bottom: 1px solid #e0e0e0;
                        font-size: 28rem/@font-size-base;
                        .txt {
                            display: inline-block;
                            color: @normal-font-gray;
                            margin-right: 24rem/@font-size-base;
                        }
                        .phone {
                            display: inline-block;
                            color: #757575;
                        }
                    }
                    .remark-wrapper {
                        display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
                        display: -moz-box; /* 老版本语法: Firefox (buggy) */
                        display: -ms-flexbox; /* 混合版本语法: IE 10 */
                        display: -webkit-flex; /* 新版本语法: Chrome 21+ */
                        display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
                        margin: 24rem/@font-size-base 22rem/@font-size-base 42rem/@font-size-base;
                        font-size: 28rem/@font-size-base;
                        .txt {
                            flex: 0 0 auto;
                            color: @normal-font-gray;
                            line-height: 44rem/@font-size-base;
                            margin-right: 24rem/@font-size-base;
                        }
                        .remark {
                            flex: 1;
                            color: #757575;
                            line-height: 44rem/@font-size-base;
                        }
                    }
                }
            }
            /*订单总数*/
            .order-count {
                height: 60rem/@font-size-base;
                font-size: 24rem/@font-size-base;
                color: #06b639;
                line-height: 60rem/@font-size-base;
                background-color: #ededed;
                padding-left: 22rem/@font-size-base;
            }
            .order-item-wrapper {
                padding: 0 22rem/@font-size-base;
                background-color: #ffffff;
                border-bottom: 18rem/@font-size-base solid #ededed;
                .time-wrapper {
                    height: 88rem/@font-size-base;
                    font-size: 24rem/@font-size-base;
                    color: @normal-font-gray;
                    line-height: 88rem/@font-size-base;
                    border-bottom: 1px solid #ededed;
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
                        .plus {
                            display: inline-block;
                            font-size: 32rem/@font-size-base;
                            margin-right: 4rem/@font-size-base;
                        }
                        .rmb, .price-small {
                            display: inline-block;
                            font-size: 24rem/@font-size-base;
                        }
                        .rmb {
                            margin-right: 8rem/@font-size-base;
                        }
                    }
                }
            }
        }
    }
</style>
