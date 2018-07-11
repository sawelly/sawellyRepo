<!-- kindeditor 宏 ,使用方式参考 /demo/kindeditor-->
	
	
	<#macro bingParam name >
	
		<#assign status = springMacroRequestContext.getBindStatus(name)>
		<#if status.value?exists && status.value?is_boolean>
	        <#assign stringStatusValue=status.value?string>
	    <#else>
	        <#assign stringStatusValue=status.value?default("")>
	    </#if>
	</#macro>
	<!-- 简单模式 -->
	<!-- num为文本框限定字数,当需要限定文本框字数时传入默认50000 -->
	<#macro kindeditor_simple name  editId  value themeType="default" width="" num=50000>
	<textarea id="${editId}" name="${name}">${value}
	</textarea>
		<script type="text/javascript">
		var editor_${editId};
		(function(){
			
			if(editor_${editId}){
				 KindEditor.remove('textarea[name="${name}"]');
				 editor_${editId}.remove();
				 editor_${editId}=null;
				setTimeout(function(){
				  initEdit();
				},10);
			 }else{
				 $("kindeditor",function(){
					 setTimeout(function(){
						  initEdit();
						},10);
				 })
			 }
			 
			 function initEdit(){
			
		          editor_${editId}=KindEditor.create('textarea[name="${name}"]', {
		        	  	themeType:'${themeType}',
	                    allowFileManager : false,
	                    width:'${width}',
	                    uploadJson:"/itransfer/keupload",
	                    showLocal : false,
	                    items : ['fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
	                             'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
	                             'insertunorderedlist', '|', 'emoticons', 'image', 'link','fullscreen','source'],   
		                	 afterBlur: function(){
		                		//限制字数
			                	if(this.count('text') > ${num}) {
			                		alert('字数超过限制，请适当删减部分内容');
			                	 } 
		                		this.sync();
		                	 }
		                });
			 }
			 	})();
		</script>
	
	</#macro>
	
	
	<!-- 全模式  缺省的-->
	<#macro kindeditor_full name themeType="default" width="" >
	<@bingParam name />
	<textarea id="${status.expression}" name="${status.expression}" >${stringStatusValue}</textarea>
		<script type="text/javascript">
		$("kindeditor",function(){
	            //KindEditor.ready(function(K) {
	                var editor${status.expression} = KindEditor.create('textarea[name="${status.expression}"]', {
	                    themeType:'${themeType}',
	                    width:'${width}',
	                    uploadJson:"/itransfer/keupload",
	                    allowFileManager : false,
	                    afterBlur: function(){this.sync();}
	                });
	            //});
			});
		</script>
	
	</#macro>
	
	<!-- 常用模式-->
	<#macro kindeditor_normal name themeType="default" blur="">
	<@bingParam name />
	<textarea id="${status.expression}" name="${status.expression}" >${stringStatusValue}</textarea>
		<script type="text/javascript">
				$("kindeditor",function(){
				//KindEditor.ready(function(K) {
	                var editor${status.expression} = KindEditor.create('textarea[name="${status.expression}"]', {
	                    themeType:'${themeType}',
	                    uploadJson:"/itransfer/keupload",
	                    allowFileManager : false,
	                    showLocal : false,
	                    items : ['source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template','cut', 'copy', 'paste',
	                    'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
	                    'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
	                    'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', 
	                    'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
	                    'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 
	                    'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
	                    'anchor', 'link', 'unlink'
	                   ]
	                   ,afterBlur: function(){this.sync();
	                      <#if blur??&&blur!="">
	                            //if(jQuery.isFunction('eval(${blur})')){
	                            	eval("${blur}('"+this.text()+"')");
	                            //}
	                            
	                      </#if>
	                   }
	                });
	            //});
			});
		</script>
	
	</#macro>
	
	
	
	<!-- 单图片上传 解决复杂name的情况，id为简单纯洁唯一的字符串（如object.column是错误的）,name和defaultValue可以是任意复杂表达式 -->
	<#macro upload_image_recode id name defaultValue="" callback="" showRemote="false" >
			<input type="text" id="${id}" name="${name}" value="${defaultValue }" class="input">
			<input type="button" id="${id }_trigger" value="选择图片">
		<script type="text/javascript">
		$("kindeditor",function(){
				//KindEditor.ready(function(K) {
	                img_upload_editor = KindEditor.editor({
	                    allowFileManager : false,
	                    //filePostName:"imgUpload",
	                    uploadJson:"/itransfer/keupload"
	                });
	                KindEditor('#${id}_trigger').click(function() {
	                    img_upload_editor.loadPlugin('image', function() {
	                        img_upload_editor.plugin.imageDialog({
	                            <#if showRemote??&&showRemote="true">
	                                showRemote : true,
	                                <#else>
	                                showRemote : false,
	                            </#if>
	                            imageUrl : KindEditor('#${value}').val(),
	                            clickFn : function(url, title, width, height, border, align) {
	                                KindEditor('#${id}').val(url);
	                                img_upload_editor.hideDialog();
	                                <#if callback??>
	                                    if($.isFunction(${callback})){
	                                        ${callback}(url);
	                                    }
	                                </#if>
	                            }
	                        });
	                    });
	                });
	            //});
			});
		
		</script>
		
	</#macro>
	<!-- 多图片上传 -->
	<#macro upload_multi_image trigger callback="" before="">
	
	<script type="text/javascript">
	$("kindeditor",function(){
	            var multi_image_editor = KindEditor.editor({
	                allowFileManager : false,
	                uploadJson:"/itransfer/keupload"
	            });
	            KindEditor('#${trigger}').click(function() {
	            	<#if before??&&""!=before>
						if($.isFunction(${before})){
							var result = ${before}();
							if(!result){
								return;
							}
						}
	        		</#if>
	                multi_image_editor.loadPlugin('multiimage', function() {
	                    multi_image_editor.plugin.multiImageDialog({
	                        clickFn : function(urlList) {

	                            
	                            <#if callback??>
									if($.isFunction(${callback})){
										${callback}(urlList);
									}
	                            </#if>
	                            multi_image_editor.hideDialog();
	                        }
	                    });
	                });
	            });
		});
	</script>
		
	</#macro>
	
