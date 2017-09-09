var cid="";
var sku="";
var pcid="";
var pid="";
$(function(){
//大家都在买
    var ebUrl="/product/everybodybuy";
    var everybodybuy=$("#everybodybuy");
      cid=everybodybuy.attr("cid");
      sku=everybodybuy.attr("sku");
      pcid=everybodybuy.attr("pcid");
      pid=everybodybuy.attr("pid");

    $.post(ebUrl,{cid:cid,pcid:pcid, pid:pid},function(data){
        if(data.length>0){
            var _html="";
            for(var p in data){
                _html+='<li>';
                _html+= '<a href="/detail/'+data[p].sku+'.html"><img src="'+data[p].logo+'"/><span>￥'+data[p].price+'</span></a>';
                _html+='</li>';
            }
            $("#everybodybuy").html(_html);
        }
    });

    //guessyoulike
    var gylUrl="/product/guessyoulike";
    var guessyoulike=$("#guessyoulike");
    $.post(gylUrl,{cid:cid,pcid:pcid, pid:pid},function(data){
        if(data.length>0){
            var _html="";
            for(var p in data){
                a=data[p];
                _html+='<li  ><a href="/detail/'+a.sku+'"><img src="'+a.logo+'"/><p>'+a.pName+'</p><span>￥'+a.price+'</span></a></li>';
            }
            $("#guessyoulike").html(_html);
        }
    });

    $(".enshrine").click(function () {
        var joinskuUrl="/my/joinsku";
        $.post(joinskuUrl,{sku:sku},function(data){
            if(data.code==0){
                alert("关注成功");
            }
            else{
                alert("关注失败");
            }
        })
    });
    $("#bookmark").click(function () {
        var bookmark=$("#bookmark");
        var shopid=bookmark.attr("shopid");
        var bookmarkUrl="/my/joinbookmark";
        $.post(bookmarkUrl,{shopid:shopid},function(data){
            if(data.code==0){
                alert("关注成功");
            }
            else{
                alert("关注失败");
            }
        })
    });




    var bs_content=$("#bs_content");
    var brandid=bs_content.attr("brandid");
    var bandUrl="/product/getBrandInfoById";

    $.post(bandUrl,{brandid:brandid},function(a){
        var _html='<div class="bs_content"> <span class="logo"><img src="'+a.logo+'"/></span><div class="logo_msg">'+a.descript+'</div></div>';
        $("#bs_content").html(_html);
    });


    $.post(commentUrl,{pid:pid,pageSize:1},function(a){
        $(".tab_con").html(a);
    });
   $(".join_car").click(function () {
       var _num=$("#goodsNumberInput").val();
       if(_num>0){
         window.location.href="/my/joincart?sku="+sku+"&pcount="+_num;
       }
   });

});
var commentUrl="/product/getComment";

function commentPage(page) {
    $.post(commentUrl,{pid:pid,currentPage:page,pageSize:1},function(a){
        $(".tab_con").html(a);
    });
}
function check(sku){
    window.location.href="/detail/"+sku+".html";
}