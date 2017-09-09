<template>
    <!--分销规则-->
    <div id="distribution-about">
        <!--header-->
        <div class="distribution-about-header">
            <img class="back" v-show="true" @click="back" src="../../assets/image/set/Back_btn_img.png">
            <div class="empty"></div>
            <span class="title">分销规则</span>
        </div>
        <!--content-->
        <div class="distribution-about-content">
            <h1>一、分销要求</h1>
            <div class="section-wrapper">
                <i class="num">1、</i>
                <p class="content">注册成为世峰户外商城会员即为分销商（每个手机号为一个单位）；</p>
            </div>
            <div class="section-wrapper">
                <i class="num">2、</i>
                <p class="content">以年为期限，按分销商的收益总额划分分销等级，不同等级获得佣金返还不同。</p>
            </div>

            <h1>二、分销优势</h1>
            <div class="section-wrapper">
                <i class="num">1、</i>
                <p class="content">大众分销模式，无门槛参与；</p>
            </div>
            <div class="section-wrapper">
                <i class="num">2、</i>
                <p class="content">无需购买商品，免去分销压力；</p>
            </div>
            <div class="section-wrapper">
                <i class="num">3、</i>
                <p class="content">无需刻意游说，保全人际关系；</p>
            </div>
            <div class="section-wrapper">
                <i class="num">4、</i>
                <p class="content">无需分销申请，注册即为分销商；</p>
            </div>
            <div class="section-wrapper">
                <i class="num">5、</i>
                <p class="content">零风险创业，坐等佣金入账；</p>
            </div>
            <!--////////////////////////////////////////////////-->
            <h1>三、收益方式</h1>
            <h2>会员A收益方式</h2>
            <div class="section-wrapper">
                <i class="num">1、</i>
                <p class="content">用户在世峰户外商城成功注册即成为会员A；</p>
            </div>
            <div class="section-wrapper">
                <span class="num">2、</span>
                <p class="content">会员A添加B的手机号码邀请B成为会员B，邀请成功则会员A得<b>10个银币</b>（会
                    员B成功登录即表示邀请成功），邀请失败则会员A无收入；</p>
            </div>
            <div class="section-wrapper">
                <span class="num">3、</span>
                <p class="content">会员B在世峰户外商城消费，会员A可得相应的佣金，并且所得佣金永久有效；</p>
            </div>
            <div class="img-wrapper">
                <img class="align-center" width="255" height="192"
                     src="../../assets/image/distribution/about_distribution_a.png">
            </div>
            <h2>会员B收益方式</h2>
            <div class="section-wrapper">
                <i class="num">1、</i>
                <p class="content">用户B被A邀请并注册成为会员B；</p>
            </div>
            <div class="section-wrapper">
                <i class="num">2、</i>
                <p class="content">会员B填写好友C手机号码邀请好友C成为会员C，邀请成功则B得10银币（好友C登陆成功即表示邀请成功），邀请失败则无收入；</p>
            </div>
            <div class="section-wrapper">
                <i class="num">3、</i>
                <p class="content">好友C在世峰户外商城消费，会员B可得相应的佣金，并且所得佣金永久有效；</p>
            </div>
            <img class="align-center" width="255" height="192"
                 src="../../assets/image/distribution/about_distribution_b.png">

            <h1>四、操作流程</h1>
            <div class="img-wrapper-2">
                <img class="align-center-2" width="208" height="221"
                     src="../../assets/image/distribution/about_distribution_2.png">
            </div>

            <h1>五、佣金提现及使用规则</h1>
            <div class="section-wrapper">
                <i class="num">1、</i>
                <p class="content">所得收益系统将自动充入世峰E卡，世峰E卡是世峰户外商城无门槛购物卡，卡内余额可直接抵扣货款或提现；</p>
            </div>
            <div class="section-wrapper">
                <i class="num">2、</i>
                <p class="content">银币与世峰E卡兑换比例为<b>10:1</b>；</p>
            </div>
            <div class="section-wrapper">
                <i class="num">3、</i>
                <p class="content">世峰E卡与人民币兑换比例为<b>1:1</b>；</p>
            </div>
            <div class="section-wrapper">
                <i class="num">2、</i>
                <p class="content">支持提现金额每笔不得低于<b>50元</b>。</p>
            </div>

            <h1>六、注意事项</h1>
            <div class="section-wrapper">
                <i class="num">1、</i>
                <p class="content">世峰E卡余额提现至支付宝、微信、银行卡功能正在建设中；</p>
            </div>
            <div class="section-wrapper">
                <i class="num">2、</i>
                <p class="content">成功邀请用户所得银币目前只能充入世峰E卡；</p>
            </div>
            <div class="section-wrapper">
                <i class="num">3、</i>
                <p class="content">发展分销用户每天只能发展50个用户，且每个账号最多发展200个用户；</p>
            </div>
            <div class="section-wrapper">
                <i class="num">4、</i>
                <p class="content">所得佣金将由系统自动送出，商品利润不同返佣比率不同；</p>
            </div>
            <div class="section-wrapper">
                <i class="num">5、</i>
                <p class="content">部分合作商家品牌商品不参与分销范围，商品返佣最终以系统返还为准；</p>
            </div>

            <h1>七、违反规则时的处理方式</h1>
            <div class="section-wrapper" style="margin-bottom: 22px">
                <p class="content">
                    凡发现用户违反分销规则，利用未知的系统漏洞做违法乱纪引起刑事、民事等纠纷或对世峰户外商城账户及资金安全有重大威胁，将冻结其账户及账户中的余额，另永久取消分销商资格，并保留追究法律责任的权利，以上分销规则最终解释权归世峰户外商城所有。</p>
            </div>
        </div>
    </div>
</template>

<script type="text/ecmascript-6">

    export default{
        methods: {
            back(){
                history.go(-1);
            }
        }
    }
</script>

<style lang="less" rel="stylesheet/less">
    @import "../../assets/css/base.less";

    #distribution-about {
        width: 100%;
        height: 100%;
        overflow: hidden;
        /*header*/
        .distribution-about-header {
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
        }
        /*content*/
        .distribution-about-content {
            position: relative;
            background-color: #ffffff;
            overflow: auto;
            margin-top: 88rem/@font-size-base;
            padding: 0 28rem/@font-size-base;
            border-top: 18rem/@font-size-base solid @border-color;
            h1 {
                color: #161616;
                font-size: 28rem/@font-size-base;
                background-color: #ffffff;
                font-weight: bold;
                margin-bottom: 24rem/@font-size-base;
                margin-top: 54rem/@font-size-base;
            }
            h1.h1-top {
                margin-top: 22rem/@font-size-base !important;
            }
            h2 {
                font-weight: bold;
                color: #161616;
                font-size: 26rem/@font-size-base;
                margin-bottom: 24rem/@font-size-base;
                margin-top: 36rem/@font-size-base;
            }
            .section-wrapper {
                display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
                display: -moz-box; /* 老版本语法: Firefox (buggy) */
                display: -ms-flexbox; /* 混合版本语法: IE 10 */
                display: -webkit-flex; /* 新版本语法: Chrome 21+ */
                display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
                padding: 0 !important;
                font-size: 26rem/@font-size-base;
                color: #4c5154;
                line-height: 48rem/@font-size-base;
                p b {
                    font-weight: bold;
                }
                .num {
                    flex: 0 0 auto;
                    display: inline-block;
                }
                .content {
                    flex: 1;
                    margin: 0 !important;
                    display: inline-block;
                }
                .img-wrapper {
                    text-align: center !important;
                    .img {
                        width: 255px;
                        height: 192px;
                        margin-top: 24rem/@font-size-base !important;
                    }
                }
                .img-wrapper-2 {
                    text-align: center !important;
                    img {
                        width: 208px;
                        height: 221px;
                    }
                }

            }
        }
    }

    .align-center {
        display: block;
        margin: 24rem/@font-size-base auto;
    }

    .align-center-2 {
        display: block;
        margin: 36rem/@font-size-base auto;
    }

    .fold-enter-active, .fold-leave-active {
        transition: all .5s;
    }

    .fold-enter, .fold-leave-active {
        transform: translate3d(0, 0, 0);
    }
</style>
