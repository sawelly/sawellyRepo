<script>
/** 
HTML5已原生支持json的解析，window.JSON.parse()将json格式字符串转换为json对象，window.JSON.stringify()将json对象转换为json格式字符串。
*/"{'statusCode':200,'result':'000000'}"
$(document).ready(function(){
	if($.cookie){
		//系统提示
		if($.cookie("tp_sys_message")){
			var val= $.cookie("tp_sys_message");
		 	$.cookie("tp_sys_message", null, {path: "/"});
            var obj = JSON.parse(val); //由JSON字符串转换为JSON对象
			// var tipInfo=JSON.parse(obj);
			// var tipInfo=JSON.parse(tipInfo);
			message(obj.message,obj.status);
		}
	}
	$("button[type=reset]").bind("click",function(){
	     history.go(-1);
	});
	
	 $(".btnConfirm").each(function(i,n){
			 $(n).confirm({
				  theme: 'black',
				  title: '确认提示',
				  content: $(n).attr("confirmContent")||'是否确认删除该数据!',
				  confirmButtonClass: 'btn-danger',
				  cancelButtonClass: 'btn-info',
				  confirmButton: '确认',
				  cancelButton: '取消'
				 });
	 });
})
 function search(){
	 $("#searchBtn").click();
 }
</script>