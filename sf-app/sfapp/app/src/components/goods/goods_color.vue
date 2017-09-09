<template>
  <popup  v-model="isShow" style="height: auto !important;" @on-hide="updateChooseColor">
    <div ref="goodColor" >
      <div>
        <img width="80" height="80" :src="nowColor.colorPic">
      </div>
      <div>{{name}}</div>

      <div style="float:left;width:100%;border: 1px solid #1aad19;height: 100%;">
        <div style="height: 30px;line-height: 30px;padding-left:30px">颜色:</div>
        <div style="height:100%;">
          <div style="float:left;width:20%;height: 30px;line-height: 30px;margin: 10px;background-color:#ddd"
          v-for="(color,$index) in colors" :key="color.sku" :class="{'backgroud':color.colorid==nowColor.colorid}" @click="changeColor(color.colorid)">{{color.colorName}}</div>
        </div>
      </div>
      <div style="float:left;width:100%;border: 1px solid #1aad19;height: 100%;">
        <div style="height: 30px;line-height: 30px;padding-left:30px">颜色:</div>
        <div style="height:100%;">
          <div style="float:left;width:20%;height: 30px;line-height: 30px;margin: 10px;background-color:#ddd"
               v-for="(spec,$index) in specs" :key="spec.specid" :class="{'backgroud':spec.specid==nowColor.specid}" @click="changespec(spec.specid)">{{spec.specName}}</div>
        </div>
      </div>

      <div style="float:left;width:100%;border: 1px solid #1aad19;height: 100%;">
        <div style="float:left;height: 30px;line-height: 30px;padding-left:30px">购买数量:</div>
        <div style="float:left"><input type="text" style="height:30px"></div>
      </div>

      <div style="float:left;width:100%;height: 100%;">
        <div style="float:left;height: 30px;line-height: 30px;width:50%;background-color: yellow" @click="addInCart">加入购物车:</div>
        <div style="float:left;height: 30px;line-height: 30px;width:50%;background-color: blueviolet">立即购买:</div>
      </div>


    </div>
  </popup>
</template>

<script type="text/ecmascript-6">
  import {Popup} from 'vux'
  export default{
    props: {
      pid: '',
      sku:'',
      name:'',
      state:''
    },
    data(){
      return {
        isShow: false,
        colors: [],//颜色集合
        specs:[],//规格集合
        nowColor:{},//当前选择对象
        dataColor:[],//查询所有的集合

      }
    },
    components: {
      Popup
    },
    methods: {
      show(){
        this.isShow = true;
      },
      hide(){
        this.isShow = false;
      },
      initData(){
        var that = this;
        that.$post(that.$interface_url.product.skuList, {pid: that.pid}, function (data, success) {
          if (success && data) {
              console.log(data)
            data.forEach(function (item,index) {
              let obj=that.colors.find(c=>c.colorid==item.colorid);
              if(!obj){//填充非重复颜色集合
                  that.colors.push(item)
              }
              let spec_obj=that.specs.find(c=>c.specid==item.specid);
              if(!spec_obj){//填充非重复规格集合
                that.specs.push(item)
              }
            })
            that.nowColor=data.find(d=>d.sku==that.sku);//当前选择对象
            that.dataColor=data;
          }
          else {
            that.$vux.toast.show({text: '数据加载失败', type: 'warn', width: '15em'});
          }
        });
      },
      changeColor(id){//改变颜色时对应改变当前选择对象集合
          let obj=this.dataColor.find(d=>d.colorid==id&&d.specid==this.nowColor.specid);
        this.nowColor=obj;
      },
      changespec(id){//改变规格时对应改变当前选择对象集合
        let obj=this.dataColor.find(d=>d.colorid==this.nowColor.colorid&&d.specid==id);
        this.nowColor=obj;
      },
      updateChooseColor(){//当关闭当前选择窗体时，更新父级当前选择的颜色和规格（由于当前嵌套了2层，稍微麻烦，后期修改）
          this.$emit('child-update',this.nowColor)
      },
      addInCart(){
        console.log(this.state)
        if(this.state==1&&this.nowColor.stocks>0) {
          console.log(this.nowColor)
          var good = {
            shopid: this.nowColor.shopid,
            shopName: this.nowColor.shopName,
            sku: this.nowColor.sku,
            price: this.nowColor.price,
            pName: this.nowColor.pName,
            colorName: this.nowColor.colorName,
            specName: this.nowColor.specName,
            colorPic: this.nowColor.imgs?this.nowColor.imgs[0]:'',
            number:1

          };

          this.$store.dispatch('addToCart', good);
          this.hide();
        }
        else if(this.state!=1){
          alert('下架');
        }
        else if(this.nowColor.stocks<=0){
          alert('缺货');
        }
      }
    },
    computed: {

    },
    created(){

    },
    mounted(){
      this.initData();
    }

  }
</script>

<style lang="less">
.backgroud{
  background-color: red !important;
}
</style>
