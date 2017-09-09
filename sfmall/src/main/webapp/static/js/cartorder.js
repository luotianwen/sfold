$(function(){


    loadcartorderproduct("");
    $(".address-more").click(function(){
        $(".address-list .item").show();
        $(".address-more").hide();
    });

    $(".btn-add").click(function () {
        $(".form-address").toggle();
    });
    $(".btn-area .btn-cancel").click(function () {
        $(".form-address").toggle();
    })
    loadaddress();
    $("#addressbtn").click(function () {
        if($("#addressform").valid()){
          $.post("/my/addaddress",$("#addressform").serialize(),function(data){
              if(data.code==0){
                  $("#city").val("");
                  $("#area").val("");
                  $("#address").val("");
                  $("#contacts").val("");
                  $("#phone").val("");
                  loadaddress();
                  $(".address-more").show();
              }
              else{
                layer.alert("保存失败！");
              }
          });
        }
    })
    $("#province").on("change",function(){
          var _province=$(this).val();
        $.post("/getAllCityByPid",{pid:_province},function (data) {
            var _html='<option value="">请选择</option>';
            for(var i=0,len=data.length;i<len;i++){
                    _html += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
            }
            $("#city").html(_html);
        })
        $("#area").html('<option value="">请选择</option>');
    });
    $("#city").on("change",function(){
        var _cid=$(this).val();
        $.post("/getAllAreaByCid",{cid:_cid},function (data) {
            var _html='<option value="">请选择</option>';
            for(var i=0,len=data.length;i<len;i++){
                _html += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
            }
            $("#area").html(_html);
        })
    });
    $("#addressform").validate({
        onfocusout : false,
        onkeyup : false,
        onclick : false,
        showErrors: function(errorMap, errorList) {
            var msg = "";
            var a;
            $.each(errorList,function(i,v){
                msg = v.message+"\r\n";
                a = v.element;
                if(msg!=''){
                    layer.tips(msg, a, {
                        tips : [ 1, '#019F95' ],
                        time : 1500,
                        tipsMore: true
                    });
                }
                if(i==0){
                    $("html, body").animate({scrollTop: $('#'+a.id).offset().top});
                }
            });
        },
        onfocusout: false
    });
  $(".link-edit").click(function(){
   $(".pop-box").show();
  });
    $(".closessss").click(function(){
        $(".pop-box").hide();
    });
    $(".btn-pay").click(function(){
        //地址
      var addressid=$(".current").attr("id");
        //发票
       //var addressid=$(".item current").attr("id");
        //备注
        var a = [];
        $("input[name^='remark']").each(function(i, o){
            a[i] = $(o).val();
        });
        alert(addressid);
        alert(a);
       //window.location.href="/my/joinorder";
    });


});
function loadaddress() {
    $.post("/my/addressList","",function (data) {
        $(".address-list").html(data);
    })
}

function loadcartorderproduct(addressid) {
    $.post("/my/cartorderproduct",{addressid:addressid},function (data) {
        $(".goods-list").html(data);
    })
}