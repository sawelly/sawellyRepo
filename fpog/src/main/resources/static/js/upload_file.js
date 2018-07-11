function initUplad(isMulti,sourceId,callBack) {
	$("#uploadify").uploadify({
		// 指定swf文件
		'swf' : sawellyContentPath + "/static/system/plugins/uploadify/uploadify.swf",
		// 后台处理的页面
		'uploader' : sawellyContentPath + "/uploadAttachment",
		// 按钮显示的文字
		'buttonText' : '上传附件',
		// 显示的高度和宽度，默认 height 30；width 120
		// 'height': 15,
		// 'width': 80,
		// 上传文件的类型 默认为所有文件 'All Files' ; '*.*'
		// 在浏览窗口底部的文件类型下拉菜单中显示的文本
		
		// 允许上传的文件后缀
		
		// 发送给后台的其他参数通过formData指定
		'formData': { 'source': sourceId},
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
			console.debug(imgId);
			console.debug(imgPath);
			if(!isMulti){
				$("#uploadBtn").hide();
				$(".showUploadFile").append("<div class='ace-file-input ace-file-multiple'>"
				+"<label class='file-label hide-placeholder selected'>"
				+"<span data-title='"+imgPath+"' class='file-name'>"
				+"</span>"
				+"</label>"
				+"<a href='JavaScript:void(0);' onclick='deleteFile(this)' class='remove'>"
				+"<i class='icon-remove'></i>"
				+"</a>"
				+"</div>"); 
			}else if(!callBack){
				 
			}else{
				callBack(file, data, response);
			}
		}
	});
}
function deleteFile(self) {
	 $.confirm({
		 	theme: 'black',
		    title: '确认提示',
		    content: '是否确认删除文件?',
		    confirmButtonClass: 'btn-danger',
		    cancelButtonClass: 'btn-info',
		    confirmButton: '确认',
		    cancelButton: '取消',
		    confirm: function(){
		    	$.ajax({
			   		url:"/delFile",
			    	type:"POST",
			    	data:{"id":$("#fileName").val()},
			    	success:function(data){
						if(data=="success"){
							$("#fileName").val("");
							$("#fileName").blur();
							$("#uploadBtn").show();
							$(self).parent().remove();
						}else{
							message("删除失败");
						}
			    	}
				});	
			}
		});
}
 