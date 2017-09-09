/**
 * 解析url参数
 * @example ?id=12345&a=b
 * @return Object {id:12345,a:b}
 */
export function urlParse() {
  let url = window.location.search;
  let obj = {};
  let reg = /[?&][^?&]+=[^?&]+/g;
  let arr = url.match(reg);
  console.log(arr);
  // ['?id=12345', '&a=b']
  if (arr) {
    arr.forEach((item) => {
      let tempArr = item.substring(1).split('=');
      let key = decodeURIComponent(tempArr[0]);
      let val = decodeURIComponent(tempArr[1]);
      obj[key] = val;
    });
  }
  return obj;
};


export function  formatCartInfo(data){
  var cartInfo=[];
  if(data){
    data.forEach(function (good,index) {
      let shopItem=cartInfo.find(c=>c.shopid==good.shopid);
      if(!shopItem) {
        shopItem = {
          shopid: good.shopid,
          shopName: good.shopName,
          selected: false,
          goods: [
            {
              sku: good.sku,
              number: good.number,
              price: good.price,
              cartPrice: good.price,
              pName: good.pName,
              colorName: good.colorName,
              specName: good.specName,
              colorPic: good.colorPic,
              selected: false
            }
          ]
        };
        cartInfo.push(shopItem)
      }
      else{
        var good = {
          sku: good.sku,
          number: good.number,
          price: good.price,
          cartPrice: good.price,
          pName: good.pName,
          colorName: good.colorName,
          specName: good.specName,
          colorPic: good.colorPic,
          selected:false
        };
        shopItem.goods.push(good)
      }
    })

  }
  return cartInfo;


}
