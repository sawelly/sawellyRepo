function initUplad(isMulti,callBack) {
	$("#uploadify").uploadify({
		// 指定swf文件
		'swf' : sawellyContentPath + "/static/system/plugins/uploadify/uploadify.swf",
		// 后台处理的页面
		'uploader' : sawellyContentPath + "/uploadFile",
		// 按钮显示的文字
		'buttonText' : '上传图片',
		// 显示的高度和宽度，默认 height 30；width 120
		// 'height': 15,
		// 'width': 80,
		// 上传文件的类型 默认为所有文件 'All Files' ; '*.*'
		// 在浏览窗口底部的文件类型下拉菜单中显示的文本
		'fileTypeDesc' : 'Image Files',
		// 允许上传的文件后缀
		'fileTypeExts' : '*.gif; *.jpg; *.png',
		// 发送给后台的其他参数通过formData指定
		// 'formData': { 'someKey': 'someValue', 'someOtherKey': 1 },
		// 上传文件页面中，你想要用来作为文件队列的元素的id, 默认为false 自动生成, 不带#
		// 'queueID': 'fileQueue',
		// 选择文件后自动上传
		'auto' : true,
		// 设置为true将允许多文件上传
		'multi' : false,
		'onUploadSuccess' : function(file, data, response) {
			var dataArray = data.split(",");
			 var imgId = dataArray[0];
			 var imgPath = dataArray[1];
			 if(imgId == "failure"){
				 message(imgPath);
				 return;
			 }
			 $("#fileName").val(imgId);
			$("#fileName").blur();
			if(!isMulti){
				$("#uploadBtn").hide();
				$(".showUploadPhoto").append("<li>"
				+"<a href='"+productImg+imgPath+"' data-rel='colorbox'>"
				+"<img alt='150x150' width='150px' src='"+productImg+imgPath+"' />"
				+" </a>"
				+" <div class='tools tools-bottom'>"
				+" <a href='JavaScript:void(0);' onclick='deleteImg(this)'>"
				+" 	<i class='icon-remove red'></i>"
				+" </a>"
				+" </div>"
				+" </li>"); 
				$('.showUploadPhoto [data-rel="colorbox"]').colorbox(colorbox_params);
			}else if(!callBack){
				var count=$(".fileFile").length+1;
				$(".showUploadPhoto").append("<li id='filePanel_"+count+"'>"
						+"<a href='"+imgPath+data+"' data-rel='colorbox'>"
						
						+"<input type='hidden' name='filePaths' value='"+data+"' />"
						+"<img alt='150x150' width='150px' src='"+imgPath+data+"' />"
						+" </a>"
						+" <div class='tools tools-bottom'>"
						+" <a href='JavaScript:void(0);' onclick='deleteImg(this)'>"
						+" 	<i class='icon-remove red'></i>"
						+" </a>"
						+" </div>"
						+" </li>"); 
				$('.showUploadPhoto [data-rel="colorbox"]').colorbox(colorbox_params);
			}else{
				callBack(file, data, response);
			}
		}
	});
}
function deleteImg(self) {
	 $.confirm({
		 	theme: 'black',
		    title: '确认提示',
		    content: '是否确认删除图片?',
		    confirmButtonClass: 'btn-danger',
		    cancelButtonClass: 'btn-info',
		    confirmButton: '确认',
		    cancelButton: '取消',
		    confirm: function(){
		    	$.ajax({
			   		url:sawellyContentPath+"/delImg",
			    	type:"POST",
			    	data:{"id":$("#fileName").val()},
			    	success:function(data){
						if(data=="success"){
							$("#fileName").val("");
							$("#fileName").blur();
							$("#uploadBtn").show();
							$(self).parent().parent().remove();
						}else{
							message("删除失败");
						}
			    	}
				});	
			}
		});
}
var colorbox_params = {
		reposition:true,
		scalePhotos:true,
		scrolling:false,
		previous:'<i class="icon-arrow-left"></i>',
		next:'<i class="icon-arrow-right"></i>',
		close:'&times;',
		current:'{current} of {total}',
		maxWidth:'100%',
		maxHeight:'100%',
		onOpen:function(){
			document.body.style.overflow = 'hidden';
		},
		onClosed:function(){
			document.body.style.overflow = 'auto';
		},
		onComplete:function(){
			$.colorbox.resize();
		}
	};
$(function(){
		$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
})