<template>
    <div id="distributor-edit">
        <!--header-->
        <div class="distributor-header">
            <span class="cancel" v-show="true" @click="cancel">取消</span>
            <div class="empty"></div>
            <span class="header-ok" @click="ok">完成</span>
        </div>
        <!--content-->
        <div class="distributor-edit-content">
            <div class="distributor-info-wrapper">
                <img src="../../assets/image/distribution/icon_avatar_default.png">
                <div class="distributor-info">
                    <div class="name-wrapper">
                        <span class="txt">姓名</span>
                        <input v-model="distributor.name" type="text" placeholder="请输入姓名">
                    </div>
                    <div class="phone-wrapper">
                        <span class="txt">手机</span>
                        <input v-model="distributor.phone" type="text" maxlength="11" placeholder="请输入手机号">
                    </div>
                </div>
            </div>
            <div class="remark-wrapper">
                <span class="txt">备注</span>
                <input class="remark" type="text" v-model="distributor.remark" placeholder="请输入备注">
            </div>
        </div>
        <!--delete button-->
        <div class="btn-delete" @click="deleteDistributor">删除分销商</div>
    </div>
</template>

<script type="text/ecmascript-6">
    export default{
        data(){
            return {
                distributor: {name: '张三', phone: '13652696517', remark: '天下无贼天下无贼天下无贼天下无贼天下无贼天下无贼天下无贼天下无贼'}
            }
        },
        methods:{
            cancel(){
                history.go(-1);
            },
            ok(){

            },
            deleteDistributor(){

            }
        }
    }

</script>

<style lang="less" rel="stylesheet/less">
    @import "../../assets/css/base.less";

    #distributor-edit {
        position: absolute;
        background-color: #ededed;
        width: 100%;
        height: 100%;
        .distributor-header {
            display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
            display: -moz-box; /* 老版本语法: Firefox (buggy) */
            display: -ms-flexbox; /* 混合版本语法: IE 10 */
            display: -webkit-flex; /* 新版本语法: Chrome 21+ */
            display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
            width: 100%;
            height: 88rem/@font-size-base;
            position: relative;
            z-index: 1000;
            background-color: #ffffff;
            .cancel {
                flex: 0 0 auto;
                display: inline-block;
                font-size: 30rem/@font-size-base;
                color: #5f646e;
                line-height: 68rem/@font-size-base;
                padding: 10rem/@font-size-base;
                margin-left: 18rem/@font-size-base;
            }
            .empty {
                flex: 1;
            }
            .header-ok {
                flex: 0 0 auto;
                display: inline-block;
                font-size: 30rem/@font-size-base;
                color: #5f646e;
                padding: 10rem/@font-size-base;
                line-height: 68rem/@font-size-base;
                margin-right: 18rem/@font-size-base;
                &:active {
                    color: #c9c9c9;
                }

            }
        }
        .distributor-edit-content {
            position: relative;
            background-color: #ffffff;
            .distributor-info-wrapper {
                display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
                display: -moz-box; /* 老版本语法: Firefox (buggy) */
                display: -ms-flexbox; /* 混合版本语法: IE 10 */
                display: -webkit-flex; /* 新版本语法: Chrome 21+ */
                display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
                margin-left: 22rem/@font-size-base;
                border-bottom: 1px solid #e0e0e0;
                img {
                    flex: 0 0 auto;
                    width: 100rem/@font-size-base;
                    height: 100rem/@font-size-base;
                    display: block;
                    margin: auto 24rem/@font-size-base auto 0;
                }
                .distributor-info {
                    flex: 1;
                    .name-wrapper {
                        font-size: 28rem/@font-size-base;
                        color: @normal-font-gray;
                        padding: 30rem/@font-size-base 0;
                        border-bottom: 1px solid #e0e0e0;
                        .txt {
                            display: inline-block;
                            margin-right: 24rem/@font-size-base;
                        }
                        input {
                            display: inline-block;
                        }
                    }
                    .phone-wrapper {
                        font-size: 28rem/@font-size-base;
                        color: @normal-font-gray;
                        padding: 30rem/@font-size-base 0;
                        .txt {
                            display: inline-block;
                            margin-right: 24rem/@font-size-base;
                        }
                        input {
                            display: inline-block;
                        }
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
        .btn-delete{
            height: 86rem/@font-size-base;
            margin: 54rem/@font-size-base;
            font-size: 30rem/@font-size-base;
            color: @red;
            text-align: center;
            line-height: 86rem/@font-size-base;
            border: 1px solid @red;
            border-radius: 10rem/@font-size-base;
            background-color: #ffffff;
            &:active{
                background-color: @color-white-to-gray;
            }
        }
    }
</style>
