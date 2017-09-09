export const getCartInfo = state => {
  let sel_count=0;
  let sel_money=0;
  let isCheckAll=true;
  if(state.cart&&state.cart.cartInfo&&state.cart.cartInfo.length>0){
    state.cart.cartInfo.forEach(function (item,index) {
      item.wareList.forEach(function (item,index) {
        if(item.selected) {
          sel_count += item.number;
          sel_money+=parseFloat(item.price)*parseInt(item.number);
        }
        else{
          isCheckAll=false;
        }
      })
    })
  }

  return {cartInfo:state.cart.cartInfo,count:sel_count,money:sel_money,isCheckAll:isCheckAll}

}

export const getCartCount =state =>{
   let count=0;
   if(state.cart&&state.cart.cartInfo&&state.cart.cartInfo.length>0){
      state.cart.cartInfo.forEach(function (item,index) {
        item.wareList.forEach(function (item,index) {
          count+=item.number;
        })
      })
   }
   return count;
}
