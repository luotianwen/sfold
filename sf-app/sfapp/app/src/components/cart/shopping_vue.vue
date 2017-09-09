<template>
    <div id="shopping-cart">
        <div class="shopping-header">
            <img class="back" v-show="" @click="back" src="../../assets/image/set/Back_btn_img.png">
            <div class="empty"></div>
            <span class="title">购物车11</span>
            <span class="edit" @click="editGoods" v-show="cartList.length>0">{{isEdit?'完成':'编辑'}}</span>
            <img class="menu-msg" src="../../assets/image/cart/icon_msg.png">
        </div>
        <!--商品列表-->
        <div class="content-wrapper" v-if="cartList.length>0">
            <div class="content-wrapper" v-show="cartList.length>0">
                <ul>
                    <li class="goods-group-wrapper" v-for="(item, index) in cartList">
                        <div class="title-wrapper">
                            <img class="img-shop-option" @click="clickGroupImg(index)" v-if="!item.isGroupSelected"
                                 src="../../assets/image/set/opt_img_2.png">
                            <img class="img-shop-option" @click="clickGroupImg(index)" v-if="item.isGroupSelected"
                                 src="../../assets/image/cart/goods_selected.png">
                            <span class="shop-name">{{item.name}}</span>
                            <span class="get-coupon">领券</span>
                        </div>
                        <div class="shop-coupon-wrapper" v-if="true">
                            <span class="shop-coupon-txt">店铺优惠</span>
                            <span class="shop-coupon-content">满200减10元，有赠品</span>
                        </div>
                        <ul>
                            <li v-for="(goods, i) in item.goodsList">
                                <swipeout>
                                    <swipeout-item @on-close="handleEvents('on-close')"
                                                   @on-open="handleEvents('on-open')"
                                                   transition-mode="follow">
                                        <div class="swipe-button-wrapper" slot="right-menu">
                                            <swipeout-button class="fav" @click.native="onButtonClick('fav')">移入<br>收藏
                                            </swipeout-button>
                                            <swipeout-button class="delete" @click.native="onButtonClick('delete')">删除
                                            </swipeout-button>
                                        </div>
                                        <div class="goods-content-wrapper" slot="content">
                                            <img class="img-goods-option" @click="clickGoodsImg(index, i)"
                                                 v-if="!goods.isGoodsSelected"
                                                 src="../../assets/image/set/opt_img_2.png">
                                            <img class="img-goods-option" @click="clickGoodsImg(index, i)"
                                                 v-if="goods.isGoodsSelected"
                                                 src="../../assets/image/cart/goods_selected.png">
                                            <div class="goods-wrapper">
                                                <img class="goods-img" src="" width="100" height="100">
                                                <div class="goods-desc">
                                                    <p class="goods-title">{{goods.title}}</p>
                                                    <span class="info">颜色:{{goods.color}},规格:{{goods.spec}}</span>
                                                    <div class="price-change-wrapper" v-show="true">
                                                        <span>已降</span><span class="rmb">¥</span><span
                                                            class="price">30</span>
                                                    </div>
                                                    <div class="price-wrapper">
                                                        <span class="rmb">¥</span><span
                                                            class="price">{{goods.price}}</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="cart-control-wrapper">
                                                <cartcontrol :count="goods.num"></cartcontrol>
                                            </div>
                                        </div>
                                    </swipeout-item>
                                </swipeout>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <!--结账-->
        <div class="checkout-wrapper" v-if="!isEdit && cartList.length>0">
            <img class="img-selected-all" @click="selectedAll" v-if="!isSelectedAll"
                 src="../../assets/image/set/opt_img_2.png">
            <img class="img-selected-all" @click="selectedAll" v-if="isSelectedAll"
                 src="../../assets/image/cart/goods_selected.png">
            <span class="txt-selected-all">全选</span>
            <div class="total-wrapper">
                <div class="total-money-wrapper">
                    <span class="txt-total">合计:</span>
                    <span class="rmb-total">¥</span><span class="total-money">{{totalMoney}}</span>
                </div>
                <div class="coupon-wrapper">
                    <span class="txt-coupon">已优惠:</span>
                    <span class="rmb">¥</span><span class="coupon-money">5</span>
                </div>
            </div>
            <div class="btn-checkout-wrapper" :class="{'selected':count>0}">
                <span class="btn-checkout">去结算</span><span class="selected-count">({{count}})</span>
            </div>
        </div>
        <!--编辑状态 菜单展示-->
        <div class="edit-menu-wrapper" v-if="isEdit && cartList.length>0">
            <img class="img-selected-all" @click="deleteAll" v-if="!isDeleteAll"
                 src="../../assets/image/set/opt_img_2.png">
            <img class="img-selected-all" @click="deleteAll" v-if="isDeleteAll"
                 src="../../assets/image/cart/goods_selected.png">
            <span class="txt-selected-all">全选</span>
            <span class="share">分享</span>
            <span class="favorite">移入收藏</span>
            <span class="delete">删除</span>
        </div>
        <!--购物车为空时的页面展示-->
        <div v-show="cartList.length==0" class="empty-tip-wrapper">
            <div class="tip-wrapper">
                <img src="../../assets/image/cart/icon_empty_cart.png">
                <span class="txt">您的购物车是空的</span>
                <span class="stroll-again">再去逛逛</span>
            </div>
        </div>
    </div>
</template>

<script type="text/ecmascript-6">
    import {Swipeout, SwipeoutItem, SwipeoutButton} from 'vux'
    import cartcontrol from './cartcontrol.vue'
    export default{
        data(){
            return {
                //标记是否全选购买
                isSelectedAll: false,
                //标记是否全选删除
                isDeleteAll: false,
                //购物车选中商品个数
                count: 0,
                //购物车选中商品总金额
                totalMoney: 0,
                //标记是否为编辑状态
                isEdit: false,
                cartList: [
                    {
                        name: '世峰旗舰店',
                        coupon: '满100减10元',
                        isGroupSelected: false,
                        goodsList: [
                            {
                                title: '户外运动衣透气性好户外运动衣透气性好户外运动衣透气性好',
                                color: '天蓝色',
                                spec: 'XL',
                                price: 158.00,
                                isGoodsSelected: false,
                                num: 7
                            },
                            {
                                title: '鞋子穿着舒适',
                                color: '藏青色',
                                spec: 'XL',
                                price: 159.00,
                                isGoodsSelected: false,
                                num: 3
                            },
                            {
                                title: '户外运动衣透气性好户外运动衣透气性好户外运动衣透气性好',
                                color: '天蓝色',
                                spec: 'XL',
                                price: 1580.00,
                                isGoodsSelected: false,
                                num: 1
                            },
                            {
                                title: '鞋子穿着舒适',
                                color: '藏青色',
                                spec: 'XL',
                                price: 139.00,
                                isGoodsSelected: false,
                                num: 3
                            }
                        ]
                    },
                    {
                        name: '世峰旗舰店2',
                        coupon: '满100减8元',
                        isGroupSelected: false,
                        goodsList: [
                            {
                                title: '户外运动衣',
                                color: '天青色',
                                spec: 'L',
                                price: 358.00,
                                isGoodsSelected: false,
                                num: 8
                            }
                        ]
                    },
                    {
                        name: '世峰旗舰店3',
                        coupon: '满100减6元',
                        isGroupSelected: false,
                        goodsList: [
                            {
                                title: '户外运动衣防风防雨防雾霾',
                                color: '红色',
                                spec: 'XL',
                                price: 258.00,
                                isGoodsSelected: false,
                                num: 2
                            }
                        ]
                    },
                    {
                        name: '世峰旗舰店444',
                        coupon: '满100减60元',
                        isGroupSelected: false,
                        goodsList: [
                            {
                                title: '户外运动衣防风防雨防雾霾',
                                color: '红色',
                                spec: 'XL',
                                price: 2558.00,
                                isGoodsSelected: false,
                                num: 2
                            }
                        ]
                    }
                ]
            }
        },
        methods: {
            back(){
                console.log('click back img button');
            },
            editGoods(){
                console.log('click edit button');
                this.isEdit = !this.isEdit;
                this.isDeleteAll = false;
            },
            //点击品牌店的img
            clickGroupImg(index){
                this.cartList[index].isGroupSelected = !this.cartList[index].isGroupSelected;
            },
            //点击商品选择img
            clickGoodsImg(index, goodsIndex){
                console.log(this.cartList[index].goodsList[goodsIndex].isGoodsSelected);
                this.cartList[index].goodsList[goodsIndex].isGoodsSelected = !this.cartList[index].goodsList[goodsIndex].isGoodsSelected;
            },
            //全选
            selectedAll(){
                this.isSelectedAll = !this.isSelectedAll;
                //test start
                if (this.isSelectedAll) {
                    this.totalMoney = 156.03;
                    this.count = 1;
                } else {
                    this.totalMoney = 0;
                    this.count = 0;
                }
                //test end
            },
            //全选删除
            deleteAll(){
                this.isDeleteAll = !this.isDeleteAll;
            },
            onButtonClick (type) {
                console.log('on button click ' + type)
            },
            handleEvents (type) {
                console.log('event: ', type)
            }
        },
        components: {
            Swipeout,
            SwipeoutItem,
            SwipeoutButton,
            cartcontrol
        }
    }
</script>

<style lang="less" rel="stylesheet/less">
    @font-size-base: 75;

    #shopping-cart {
        width: 100%;
        height: 100%;
        /*头部*/
        .shopping-header {
            display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
            display: -moz-box; /* 老版本语法: Firefox (buggy) */
            display: -ms-flexbox; /* 混合版本语法: IE 10 */
            display: -webkit-flex; /* 新版本语法: Chrome 21+ */
            display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
            width: 100%;
            height: 88rem/@font-size-base;
            border-bottom: 1px solid #b0b0b0;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 999999;
            background-color: #ffffff;
            .back {
                flex: 0 0 auto;
                width: 21rem/@font-size-base;
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
                left: 46%;
                color: #161616;
                font-size: 32rem/@font-size-base;
                height: 88rem/@font-size-base;
                line-height: 88rem/@font-size-base;
                margin: 0 auto;
            }
            .edit {
                flex: 0 0 auto;
                display: inline-block;
                text-align: center;
                color: #363636;
                font-size: 28rem/@font-size-base;
                height: 88rem/@font-size-base;
                line-height: 88rem/@font-size-base;
                margin-right: 32rem/@font-size-base;
            }
            .menu-msg {
                flex: 0 0 auto;
                width: 36rem/@font-size-base;
                height: 38rem/@font-size-base;
                margin-top: 25rem/@font-size-base;
                margin-right: 28rem/@font-size-base;
            }

        }
        /*主体内容*/
        .content-wrapper {
            position: relative;
            width: 100%;
            top: 44rem/@font-size-base;
            overflow: hidden;
            .content-wrapper {
                position: relative;
                width: 100%;
                margin-bottom: 239rem/@font-size-base;
                overflow: hidden;
                background-color: #ededed;
                .goods-group-wrapper {
                    width: 100%;
                    border-top: 18rem/@font-size-base solid #ededed;
                    .title-wrapper {
                        display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
                        display: -moz-box; /* 老版本语法: Firefox (buggy) */
                        display: -ms-flexbox; /* 混合版本语法: IE 10 */
                        display: -webkit-flex; /* 新版本语法: Chrome 21+ */
                        display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
                        width: 100%;
                        height: 88rem/@font-size-base;
                        background-color: #ffffff;
                        .img-shop-option {
                            flex: 0 0 auto;
                            display: inline-block;
                            width: 30rem/@font-size-base;
                            height: 30rem/@font-size-base;
                            margin: auto 22rem/@font-size-base auto;
                        }
                        .shop-name {
                            flex: 1;
                            display: inline-block;
                            font-size: 24rem/@font-size-base;
                            color: #3d4245;
                            height: 88rem/@font-size-base;
                            line-height: 92rem/@font-size-base;
                        }
                        .get-coupon {
                            flex: 0 0 auto;
                            font-size: 24rem/@font-size-base;
                            color: #3d4245;
                            padding: 10rem/@font-size-base 0;
                            line-height: 72rem/@font-size-base;
                            margin-right: 24rem/@font-size-base;
                        }
                    }
                    .shop-coupon-wrapper {
                        background-color: #FFFFFF;
                        padding: 16rem/@font-size-base 20rem/@font-size-base 30rem/@font-size-base 74rem/@font-size-base;
                        .shop-coupon-txt {
                            display: inline-block;
                            border-radius: 2px;
                            color: #06b639;
                            font-size: 20rem/@font-size-base;
                            border: 1px solid #06b639;
                            margin-right: 12rem/@font-size-base;
                        }
                        .shop-coupon-content {
                            color: #999999;
                            font-size: 20rem/@font-size-base;
                        }
                    }
                    .swipe-button-wrapper {
                        position: relative;
                        font-size: 30rem/@font-size-base;
                        .fav {
                            position: absolute;
                            right: 80px;
                            background-color: #c9c9c9;
                            color: #5f646e;
                        }
                        .delete {
                            background-color: #ff5b28;
                            color: #ffffff;
                        }
                    }
                    .goods-content-wrapper {
                        display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
                        display: -moz-box; /* 老版本语法: Firefox (buggy) */
                        display: -ms-flexbox; /* 混合版本语法: IE 10 */
                        display: -webkit-flex; /* 新版本语法: Chrome 21+ */
                        display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
                        width: 100%;
                        .img-goods-option {
                            flex: 0 0 auto;
                            width: 30rem/@font-size-base;
                            height: 30rem/@font-size-base;
                            margin: 100rem/@font-size-base 22rem/@font-size-base;
                        }
                        .goods-wrapper {
                            flex: 1;
                            position: relative;
                            display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
                            display: -moz-box; /* 老版本语法: Firefox (buggy) */
                            display: -ms-flexbox; /* 混合版本语法: IE 10 */
                            display: -webkit-flex; /* 新版本语法: Chrome 21+ */
                            display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
                            background-color: #ffffff;
                            border-top: 1px solid #e0e0e0;
                            padding: 16rem/@font-size-base 0;
                            .goods-img {
                                flex: 0 0 auto;
                                width: 200rem/@font-size-base;
                                height: 200rem/@font-size-base;
                                margin-right: 24rem/@font-size-base;
                            }
                            .goods-desc {
                                position: relative;
                                background-color: #ffffff;
                                flex: 1;
                                .goods-title {
                                    font-size: 24rem/@font-size-base;
                                    color: #3d4245;
                                    line-height: 32rem/@font-size-base;
                                    margin-right: 24rem/@font-size-base;
                                }
                                .info {
                                    display: block;
                                    font-size: 22rem/@font-size-base;
                                    margin-top: 8rem/@font-size-base;
                                    color: #999999;
                                }
                                .price-change-wrapper {
                                    position: absolute;
                                    bottom: 42rem/@font-size-base;
                                    left: 0;
                                    font-size: 22rem/@font-size-base;
                                    color: #06b639;
                                    .rmb {
                                        margin: 0 3px;
                                    }
                                }
                                .price-wrapper {
                                    position: absolute;
                                    bottom: 6rem/@font-size-base;
                                    left: 0;
                                    .rmb {
                                        display: inline-block;
                                        margin-right: 10rem/@font-size-base;
                                        color: #ff5b28;
                                        font-size: 20rem/@font-size-base;
                                    }
                                    .price {
                                        color: #ff5b28;
                                        font-size: 28rem/@font-size-base;
                                    }
                                }
                            }
                        }
                        .cart-control-wrapper {
                            position: absolute;
                            right: 20rem/@font-size-base;
                            bottom: 16rem/@font-size-base;
                        }
                    }
                }
            }
        }

        /* 去结算*/
        .checkout-wrapper {
            position: fixed;
            bottom: 98rem/@font-size-base;
            display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
            display: -moz-box; /* 老版本语法: Firefox (buggy) */
            display: -ms-flexbox; /* 混合版本语法: IE 10 */
            display: -webkit-flex; /* 新版本语法: Chrome 21+ */
            display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
            background-color: #ffffff;
            width: 100%;
            height: 97rem/@font-size-base;
            border-top: 1px solid #e0e0e0;
            .img-selected-all {
                flex: 0 0 auto;
                width: 30rem/@font-size-base;
                height: 30rem/@font-size-base;
                margin: auto 22rem/@font-size-base;
            }
            .txt-selected-all {
                flex: 1;
                display: inline-block;
                font-size: 24rem/@font-size-base;
                color: #3d4245;
                line-height: 98rem/@font-size-base;
            }
            .total-wrapper {
                flex: 0 0 auto;
                margin: auto 14rem/@font-size-base;
                .total-money-wrapper {
                    margin-bottom: 14rem/@font-size-base;
                    .txt-total {
                        font-size: 20rem/@font-size-base;
                        color: #3d4245;
                    }
                    .rmb-total {
                        display: inline-block;
                        margin: 0 7rem/@font-size-base 0 10rem/@font-size-base;
                        font-size: 20rem/@font-size-base;
                        color: #ff5b28;
                    }
                    .total-money {
                        font-size: 32rem/@font-size-base;
                        color: #ff5b28;
                    }
                }
                .coupon-wrapper {
                    text-align: right;
                    font-size: 20rem/@font-size-base;
                    color: #999999;
                    .rmb {
                        display: inline-block;
                        margin: 0 7rem/@font-size-base;
                    }
                }
            }
            .btn-checkout-wrapper {
                width: 218rem/@font-size-base;
                height: 98rem/@font-size-base;
                text-align: center;
                background-color: #c9c9c9;
                color: #ffffff;
                font-size: 28rem/@font-size-base;
                line-height: 98rem/@font-size-base;
                &.selected {
                    background-color: #06b639;
                    &:active {
                        background-color: #069639;
                    }
                }
            }
        }

        .edit-menu-wrapper {
            position: fixed;
            bottom: 98rem/@font-size-base;
            display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
            display: -moz-box; /* 老版本语法: Firefox (buggy) */
            display: -ms-flexbox; /* 混合版本语法: IE 10 */
            display: -webkit-flex; /* 新版本语法: Chrome 21+ */
            display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
            background-color: #ffffff;
            width: 100%;
            height: 97rem/@font-size-base;
            border-top: 1px solid #e0e0e0;
            .img-selected-all {
                flex: 0 0 auto;
                width: 30rem/@font-size-base;
                height: 30rem/@font-size-base;
                margin: auto 22rem/@font-size-base;
            }
            .txt-selected-all {
                flex: 1;
                display: inline-block;
                font-size: 24rem/@font-size-base;
                color: #3d4245;
                line-height: 98rem/@font-size-base;
            }
            .share, .favorite, .delete {
                display: block;
                width: 128rem/@font-size-base;
                height: 54rem/@font-size-base;
                font-size: 24rem/@font-size-base;
                border-radius: 2px;
                margin: auto 16rem/@font-size-base auto 0;
                background-color: #FFFFFF;
                text-align: center;
                line-height: 54rem/@font-size-base;
                &:active {
                    background-color: #e0e0e0;
                }
            }
            .share, .favorite {
                border: 1px solid #999999;
                color: #3d4245;
            }
            .delete {
                border: 1px solid #ff5b28;
                color: #ff5b28;
            }
        }

        /*空提示*/
        .empty-tip-wrapper {
            position: absolute;
            width: 100%;
            height: 100%;
            overflow: hidden;
            background-color: #ededed;
            text-align: center;
            .tip-wrapper {
                position: relative;
                margin-top: 50%;
                img {
                    width: 180rem/@font-size-base;
                    height: 180rem/@font-size-base;
                }
                .txt {
                    display: block;
                    font-size: 28rem/@font-size-base;
                    color: #707070;
                    margin: 24rem/@font-size-base 0 34rem/@font-size-base;
                }
                .stroll-again {
                    display: block;
                    text-align: center;
                    margin: 0 auto;
                    font-size: 30rem/@font-size-base;
                    color: #06b639;
                    background-color: #ffffff;
                    width: 252rem/@font-size-base;
                    height: 86rem/@font-size-base;
                    line-height: 86rem/@font-size-base;
                    border: 1px solid #06b639;
                    border-radius: 10rem/@font-size-base;
                    &:active {
                        background-color: #e0e0e0;
                    }
                }

            }
        }

    }
</style>
