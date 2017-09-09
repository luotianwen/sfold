<template>
    <div id="login-wrapper">
        <div class="login-title-wrapper">
            <span class="login-title">登录</span>
        </div>

        <div class="phone-wrapper">
            <x-input class="phone" title="手机号" :max="11" type="tel" v-model="phone" placeholder="请输入手机号"></x-input>
        </div>

        <div class="btn-wrapper">
            <x-button plain class="btn-next" :disabled="check" @click.native="next"
                      :class="{'disabled': isDisabled}">下一步
            </x-button>
        </div>
    </div>
</template>

<script type="text/ecmascript-6">
    import {XInput, XButton} from 'vux';

    export default{
        data() {
            return {
                phone: '',
                isDisabled: true
            }
        },
        methods: {
            next(){
                //push：add in history, replace: replace current router path
                this.$router.push({name: 'registerYZM'});
            }
        },
        computed: {
            check: function () {
                return this.isDisabled = (this.phone.length != 11);
            }
        },
        components: {
            XInput,
            XButton
        }
    }
</script>

<style lang="less" rel="stylesheet/less">
    #login-wrapper {
        width: 100%;
        height: 100%;
        .login-title-wrapper {
            text-align: center;
            width: 100%;
            height: 44px;
            .login-title {
                font-size: 16px;
                color: #161616 !important;
                height: 44px;
                line-height: 44px;
                margin: 0 auto;
            }
        }
        .phone-wrapper {
            width: 100%;
            height: 100%;
            padding-top: 54px;
            .phone {
                font-size: 14px;
                color: #3d4245;
                padding-bottom: 14px !important;
                padding-left: 0 !important;
                padding-right: 0 !important;
                text-indent: 3px;
                margin: 0 11px;
                border-bottom: 1px solid #e0e0e0;
            }
        }
        .btn-wrapper {
            padding: 27px 18px;
            .btn-next {
                height: 44px !important;
                border-radius: 5px !important;
                border-color: #009ff2 !important;
                color: #009ff2;
                font-size: 15px;
                &:active {
                    color: #FFFFFF;
                    background-color: #009ff2;
                }
                &.disabled {
                    color: #99d9fa;
                    border-color: #99d9fa !important;
                    background-color: #FFFFFF;
                }
            }
        }
    }
</style>
