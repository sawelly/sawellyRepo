/**
 * 
 */

/**
 * 提醒 msg 信息 type :类型 info /error /success/warning
 */
function message(msg, type) {
	if (!type) {
		type = "info";
	}
	$.gritter.add({
		// 通知的标题
		// title : '提交信息!',
		// 里面的文字通知
		text : msg,
		// 如果你想让它淡出自己或只是在那里
		sticky : false,
		// 想要多长的时间才消失
		time : '3000',
		//
		class_name : 'gritter-center gritter-' + type
	});
}

function confrimInfo(msg,callBack) {
	$.confirm({
		theme : 'black',
		title : '确认提示',
		content : msg||'是否确认删除?',
		confirmButtonClass : 'btn-danger',
		cancelButtonClass : 'btn-info',
		confirmButton : '确认',
		cancelButton : '取消',
		confirm : function() {
			if(callBack){
				callBack();
			}
		}
	});
}