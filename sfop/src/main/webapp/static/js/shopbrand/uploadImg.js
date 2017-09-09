var uploader = new plupload.Uploader({
	runtimes : 'html5,flash,silverlight,html4',
	browse_button : "file",
	container: "uploadImg",
	//multi_selection: false,
	flash_swf_url : '/static/upload/plupload-2.1.8/js/Moxie.swf',
	silverlight_xap_url : '/static/upload/plupload-2.1.8/js/Moxie.xap',
	filters: {title: "image", filetype: "jpg,jpge,gif"},
    url : '/upload/file',
	init: {
		PostInit: function() {
			$("#upload").next().css("width","82px");
			$("#upload").next().css("height","35px");
		},
		FilesAdded: function(up, files) {
			plupload.each(files, function(file,index) {
				makeThumb(file, function (imgsrc) {
					$('#logoimg').attr("src",imgsrc);
					
					$("#upload").next().css("width","82px");
					$("#upload").next().css("height","35px");
					
					$("#upload").on("click",function(){
						up.start();
					})
                })
			})
		},
		
		FileUploaded: function(uploader,file,data){
			if(data.status == "200"){
				var json =  eval("("+data.response+")");
				if(json.state=="SUCCESS"){
					if(json.url!=null&&json.url!=''){
						$('input#logo').val(json.url);
					}
				}else{
					layer.alert(json.msg, {
        				title:"错误信息",
        				icon:2
        			});
				}
			}else{
				layer.alert("图片上传异常！", {
    				title:"错误信息",
    				icon:2
    			});
			}
		},
		UploadComplete: function(){
			layer.msg("上传成功！",{icon:1,time:1*1000,shade:0.3})
		}
	}
});
	
// 生产缩略图
function makeThumb(file,callback) {// file为plupload事件监听函数参数中的file对象,callback为预览图片准备完成的回调函数
    if (!file || !/image\//.test(file.type)) return; // 确保文件是图片
    
    if (file.type == 'image/gif') {// gif使用FileReader进行预览,因为mOxie.Image只支持jpg和png
        var fr = new mOxie.FileReader();
        fr.onload = function () {
            callback(fr.result,file);
            fr.destroy();
            fr = null;
        }
        fr.readAsDataURL(file.getSource());
    } else {
        var preloader = new mOxie.Image();
        preloader.onload = function () {
            // preloader.downsize(550, 400);//先压缩一下要预览的图片,宽300，高300
            var imgsrc = preloader.type == 'image/jpeg' ? preloader.getAsDataURL('image/jpeg', 80) : preloader.getAsDataURL(); // 得到图片src,实质为一个base64编码的数据
            callback && callback(imgsrc,file); // callback传入的参数为预览图片的url
            preloader.destroy();
            preloader = null;
        };
        preloader.load(file.getSource());
    }
}

uploader.init();
