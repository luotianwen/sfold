<template>
    <!--我的分销-->
    <div id="distributor">
        <!--header-->
        <div class="distributor-header">
            <img class="back" v-show="true" @click="back" src="../../assets/image/set/Back_btn_img.png">
            <div class="empty"></div>
            <span class="title">我的分销商</span>
            <img class="header-add" @click="addDistributor" src="../../assets/image/distribution/icon_add.png">
        </div>
        <!--content-->
        <div class="distributor-content-wrapper" v-if="distributorList.length>0">
            <!--search-->
            <div class="search-wrapper" @click="showSearchPager">
                <div class="search">
                    <img src="../../assets/image/distribution/icon_search.png">
                    <span class="txt">搜索分销商</span>
                </div>
            </div>
            <!--分销商列表-->
            <ul>
                <li class="distributor-item-wrapper" v-for="(distributor,index) in distributorList"
                    @click="detail(index)">
                    <div class="distributor">
                        <img src="../../assets/image/distribution/icon_avatar_default.png">
                        <div class="info">
                            <div class="name">{{distributor.name}}</div>
                            <div class="phone">{{distributor.phone}}</div>
                        </div>
                    </div>
                    <div class="remark-wrapper">
                        <span class="txt">备注</span>
                        <span class="remark">{{distributor.remark}}</span>
                    </div>
                </li>
            </ul>
        </div>

        <!--empty content-->
        <div class="empty-wrapper" v-if="distributorList.length==0">
            <img src="../../assets/image/distribution/empty_person_tip.png">
            <span class="txt-empty-tip">您暂无分销商<br/>赶快去添加吧</span>
        </div>
        <!--空背景-->
        <div style="width:100%;height:100%;position: fixed;background-color: #ededed;top: 44px;z-index: 50;"></div>
    </div>
</template>

<script type="text/ecmascript-6">
    export default{
        data(){
            return {
                distributorList: [
                    {
                        name: '张三',
                        phone: '13652369648',
                        remark: '北京的分销商'
                    },
                    {
                        name: '李四',
                        phone: '13652369669',
                        remark: '海南的分销商海南的分销商海南的分销商海南的分销商海南的分销商海南的分销商'
                    },
                    {
                        name: '王五',
                        phone: '13652369541',
                        remark: '上海的分销商'
                    }, {
                        name: '张三',
                        phone: '13652369648',
                        remark: '北京的分销商'
                    },
                    {
                        name: '李四',
                        phone: '13652369669',
                        remark: '海南的分销商海南的分销商海南的分销商海南的分销商海南的分销商海南的分销商'
                    },
                    {
                        name: '王五',
                        phone: '13652369541',
                        remark: '上海的分销商'
                    }, {
                        name: '张三',
                        phone: '13652369648',
                        remark: '北京的分销商'
                    },
                    {
                        name: '李四',
                        phone: '13652369669',
                        remark: '海南的分销商海南的分销商海南的分销商海南的分销商海南的分销商海南的分销商'
                    },
                    {
                        name: '王五',
                        phone: '13652369541',
                        remark: '上海的分销商'
                    }
                ]
            }
        },
        methods: {
            back(){
                history.go(-1);
            },
            addDistributor(){
                this.$router.push({name: 'DistributorAdd'});
            },
            detail(index){
                this.$router.push({name: 'DistributorDetail'});
            },
            showSearchPager(){
                this.$router.push({name: 'DistributorSearch'});
            }
        }
    }
</script>

<style lang="less" rel="stylesheet/less">
    @import "../../assets/css/base.less";

    #distributor {
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
            border-bottom: 1px solid @header-border-color;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 1000;
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
            .header-add {
                flex: 0 0 auto;
                display: block;
                width: 32rem/@font-size-base;
                height: 32rem/@font-size-base;
                padding: 10rem/@font-size-base;
                margin: auto 24rem/@font-size-base;
            }
        }
        /*content*/
        .distributor-content-wrapper {
            position: relative;
            z-index: 88;
            margin-top: 88rem/@font-size-base;
            /*search*/
            .search-wrapper {
                width: 100%;
                background-color: #ffffff;
                padding: 16rem/@font-size-base 0;
                .search {
                    position: relative;
                    margin: auto 22rem/@font-size-base;
                    height: 56rem/@font-size-base;
                    background-color: #e8e9eb;
                    border-radius: 10rem/@font-size-base;
                    text-align: center;
                    font-size: 0;
                    img {
                        position: relative;
                        margin: auto;
                        top: 1px;
                        width: 24rem/@font-size-base;
                        height: 24rem/@font-size-base;
                    }
                    .txt {
                        display: inline-block;
                        font-size: 24rem/@font-size-base;
                        color: #444444;
                        margin-left: 16rem/@font-size-base;
                        line-height: 56rem/@font-size-base;
                    }
                }
            }
            /*distributor list*/
            .distributor-item-wrapper {
                background-color: #ffffff;
                border-top: 18rem/@font-size-base solid #ededed;
                &:active {
                    background-color: #f0f0f0;
                }
                .distributor {
                    display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
                    display: -moz-box; /* 老版本语法: Firefox (buggy) */
                    display: -ms-flexbox; /* 混合版本语法: IE 10 */
                    display: -webkit-flex; /* 新版本语法: Chrome 21+ */
                    display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
                    border-bottom: 1px solid #e0e0e0;
                    margin: 0 22rem/@font-size-base;
                    img {
                        flex: 0 0 auto;
                        display: block;
                        width: 100rem/@font-size-base;
                        height: 100rem/@font-size-base;
                        margin: 24rem/@font-size-base 22rem/@font-size-base 24rem/@font-size-base 0;
                    }
                    .info {
                        flex: 1;
                        margin: auto;
                        .name {
                            font-size: 32rem/@font-size-base;
                            color: @normal-font-gray;
                            margin-bottom: 16rem/@font-size-base;
                        }
                        .phone {
                            font-size: 28rem/@font-size-base;
                            color: #757575;
                        }
                    }
                }
                .remark-wrapper {
                    display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
                    display: -moz-box; /* 老版本语法: Firefox (buggy) */
                    display: -ms-flexbox; /* 混合版本语法: IE 10 */
                    display: -webkit-flex; /* 新版本语法: Chrome 21+ */
                    display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
                    padding: 16rem/@font-size-base 0 42rem/@font-size-base;
                    .txt {
                        flex: 0 0 auto;
                        margin: 0 22rem/@font-size-base 0 24rem/@font-size-base;
                        font-size: 28rem/@font-size-base;
                        color: @normal-font-gray;
                        line-height: 44rem/@font-size-base;
                    }
                    .remark {
                        flex: 1;
                        display: inline-block;
                        margin-right: 22rem/@font-size-base;
                        font-size: 28rem/@font-size-base;
                        color: #757575;
                        line-height: 44rem/@font-size-base;
                    }
                }
            }
        }
        /*empty content*/
        .empty-wrapper {
            position: fixed;
            top: 88rem/@font-size-base;
            width: 100%;
            height: 100%;
            background-color: #ededed;
            img {
                display: block;
                width: 180rem/@font-size-base;
                height: 180rem/@font-size-base;
                margin: 315rem/@font-size-base auto auto;
            }
            .txt-empty-tip {
                display: inline-block;
                width: 100%;
                text-align: center;
                font-size: 28rem/@font-size-base;
                line-height: 36rem/@font-size-base;
                color: #707070;
                margin-top: 24rem/@font-size-base;
            }
        }

    }
</style>
