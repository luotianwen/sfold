<template>
    <div id="register-wrapper">
        <div class="register-title-wrapper">
            <img src="../../assets/image/set/Back_btn_img.png" @click="back">
            <span class="register-title">验证手机</span>
        </div>
        <div class="content-wrapper">
            <div class="phone-wrapper">
                <span class="txt">我们已经发送了校验码到您的手机： </span>
                <span class="phone">13651269663</span>
            </div>

            <div class="yzm-wrapper">
                <x-input class="yzm" title="校验码" :max="4" v-model="code" placeholder="请输入校验码"></x-input>
                <span class="tip" :class="{'sending': isStart}" @click="startTimer">{{tip}}</span>
            </div>

            <div class="btn-wrapper">
                <x-button plain class="btn-next" :disabled="check" @click.native="next"
                          :class="{'disabled': isDisabled}">下一步
                </x-button>
            </div>
            <toast v-model="showToast" type="text" style="min-width: 160px !important;">验证码有误！</toast>
        </div>
    </div>
</template>

<script type="text/ecmascript-6">
    import {XHeader, XInput, XButton, Toast} from 'vux';

    export default{
        data() {
            return {
                phone:'',
                code: '',
                isDisabled: true,
                timer: 30,       //默认倒数60秒
                isStart: false,   //默认是停止的，但界面加载之后会变成false
                Interval: null,  //setInterval的对象
                tip: '30秒后可重发',
                showToast: false
            }
        },
        methods: {
            back(){
                history.go(-1);
            },
            next(){
                console.log("***** click next button *****");
                this.showToast = !this.showToast;
            },
            update () {
                if (this.timer <= 0) {
                    this.timer = 30;
                    this.isStart = false;
                    this.tip = "重新发送";
                    clearInterval(this.Interval);
                }
                else {
                    console.log(">>> " + this.phone);
                    this.tip = this.timer + "秒后可重发";
                    this.timer--;
                }
            },
            startTimer () {
                if (this.isStart) {
                    return;
                }
                this.isStart = true;
                this.Interval = setInterval(this.update, 1000);
            }
        },
        created(){
            this.startTimer();
        },
        computed: {
            check: function () {
                this.isDisabled = (this.code.length != 4);
                return this.isDisabled;
            }
        },
        components: {
            XHeader,
            XInput,
            XButton,
            Toast
        }
    }
</script>

<style lang="less" rel="stylesheet/less">
    #register-wrapper {
        .register-title-wrapper {
            display: flex;
            width: 100%;
            height: 44px;
            border-bottom: 1px solid #b0b0b0;
            text-align: center;
            img {
                flex: 0 0 auto;
                width: 10px;
                height: 16px;
                padding: 5px;
                margin: 9px 0 9px 10px;
            }
            .register-title {
                flex: 1;
                font-size: 16px;
                height: 44px;
                margin-right: 30px;
                line-height: 44px;
                color: #161616;
            }
        }
        .content-wrapper {
            position: absolute;
            top: 45px;
            width: 100%;
            height: 100%;
            background-color: #ededed;
            overflow: hidden;
            .phone-wrapper {
                text-align: center;
                color: #161616;
                padding: 32px 0;
                .txt {
                    display: block;
                    font-size: 12px;
                    margin-bottom: 11px;
                }
                .phone {
                    display: block;
                    font-size: 20px;
                }
            }
            .yzm-wrapper {
                display: flex;
                width: 100%;
                height: 44px;
                background-color: #FFFFFF;
                .yzm {
                    flex: 1;
                    font-size: 14px;
                    color: #3d4245;
                }
                .tip {
                    flex: 0 0 auto;
                    height: 16px;
                    line-height: 16px;
                    margin-top: 14px;
                    margin-right: 14px;
                    font-size: 12px;
                    color: #009ff2;
                    padding-left: 14px;
                    border-left: 1px solid #e0e0e0;
                    &.sending {
                        color: #999;
                    }
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
                    background-color: #FFFFFF;
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
    }

</style>
