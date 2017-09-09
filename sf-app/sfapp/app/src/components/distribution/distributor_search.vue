<template>
    <div id="distributor-search-page">
        <div class="search-control-wrapper">
            <div class="search-cond-wrapper">
                <img src="../../assets/image/distribution/icon_search.png">
                <input type="text" placeholder="搜索分销商" maxlength="11">
            </div>
            <span class="cancel" @click="cancel">取消</span>
        </div>
        <div class="search-result-wrapper">
            <ul>
                <li class="distributor-item-wrapper" v-for="(distributor,index) in distributorList">
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
                    }
                ]
            }
        },
        methods: {
            cancel(){
                history.go(-1);
            }
        }
    }
</script>

<style lang="less" rel="stylesheet/less">
    @import "../../assets/css/base.less";

    /*搜索页面*/
    #distributor-search-page {
        position: relative;
        width: 100%;
        height: 100%;
        background-color: #e0e0e0;
        .search-control-wrapper {
            position: fixed;
            width: 100%;
            top:0;
            left: 0;
            display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
            display: -moz-box; /* 老版本语法: Firefox (buggy) */
            display: -ms-flexbox; /* 混合版本语法: IE 10 */
            display: -webkit-flex; /* 新版本语法: Chrome 21+ */
            display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
            height: 88rem/@font-size-base;
            background-color: #ffffff;
            z-index: 100;
            .search-cond-wrapper {
                display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
                display: -moz-box; /* 老版本语法: Firefox (buggy) */
                display: -ms-flexbox; /* 混合版本语法: IE 10 */
                display: -webkit-flex; /* 新版本语法: Chrome 21+ */
                display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
                flex: 1;
                height: 56rem/@font-size-base;
                margin: auto 40rem/@font-size-base;
                background-color: #e8e9eb;
                border-radius: 10rem/@font-size-base;
                img {
                    flex: 0 0 auto;
                    display: block;
                    margin: auto 22rem/@font-size-base;
                    width: 24rem/@font-size-base;
                    height: 24rem/@font-size-base;
                }
                input {
                    flex: 1;
                    background-color: #e8e9eb;
                    margin-right: 12rem/@font-size-base;
                    color: #444444;
                    font-size: 24rem/@font-size-base;
                }
            }
            .cancel {
                flex: 0 0 auto;
                font-size: 30rem/@font-size-base;
                color: #08a030;
                line-height: 88rem/@font-size-base;
                display: inline-block;
                margin-right: 28rem/@font-size-base;
                &:active {
                    color: #08c030;
                }
            }
        }
        .search-result-wrapper {
            position: relative;
            overflow: hidden;
            width: 100%;
            height: 100%;
            margin-top: 88rem/@font-size-base;
            z-index: 80;
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
    }
</style>
