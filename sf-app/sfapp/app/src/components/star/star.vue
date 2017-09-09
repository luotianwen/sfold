<template>
    <div id="star">
        <span v-for="item in starList" :class="item" class="start-item"></span>
    </div>
</template>

<script type="text/ecmascript-6">
    const LENGTH = 5;//总个数
    const CLS_ON = 'on';//点亮的星星
    const CLS_OFF = 'off';//灰色的星星

    export  default{
        props: {
            score: {//星星点亮的个数
                type: Number
            }
        },
        computed: {
            starList(){
                let result = [];
                for (let i = 0; i < this.score; i++) {
                    result.push(CLS_ON);
                }
                while(result.length < LENGTH) {
                    result.push(CLS_OFF);
                }
                return result;
            }
        }
    }
</script>

<style lang="less" rel="stylesheet/less">
    @font-size-base: 75;
    #star{
        .start-item{
            display: inline-block;
            background-repeat: no-repeat;
            width: 16rem/@font-size-base;
            height: 16rem/@font-size-base;
            margin-right: 8rem/@font-size-base;
            &.on{
                background-image: url("comment_star_selected.png");
                background-size: 16rem/@font-size-base 16rem/@font-size-base;
            }
            &.off{
                background-image: url("comment_star_normal.png");
                background-size: 16rem/@font-size-base 16rem/@font-size-base;
            }
        }
    }

</style>
