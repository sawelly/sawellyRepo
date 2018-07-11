<#-- 
*******************************************
    名称:上传组件
    作用:统一上传
*******************************************
-->
<#-- 
    id:触发上传的组件id
    destFileTypes:10012 转码目标类型 ,与参数transcoding同时使用,transcoding=1 才开启转码
    transcoding:是否转码,等于1时转码,其他不转码
    maxFileSize:最大文件限制eg:maxFileSize="20mb" 缺省500mb
    origin:来源 非必须
    originId:来源id 非必须
    attachment_upload_complete:所在form的data由上传组件维持,attachment_upload_complete=true,表示没有正在进行的上传
-->
<#macro upload id=""  transcoding="0" maxFileSize="500mb" origin="" originId="">
    <input type="hidden" name="${id}_${origin}_${originId}_attachment_vos">
    <input type="hidden" name="_upload_id" value="${id}_${origin}_${originId}">
    <ul id="${id}_${origin}_${originId}_file_queue" class="file-list">
    </ul>
    <script type="text/javascript">
        var loaded_${id};
        var upload_${id} = new plupload.Uploader({
             runtimes : 'flash',
             browse_button : "${id}",
             url : "${rc.contextPath}/uploadFile",
             unique_names : true,
             filters:{
                max_file_size : '${maxFileSize}'
             },
             flash_swf_url : '${rc.contextPath}/member_static/admin/pupload/Moxie.cdn.swf',
             init:{
                FilesAdded : function(up, files){
                    plupload.each(files,function(file){
                        var item = '<li class="file-item"><span  id="progress_'+file.id+'">[等待上传]</span>'+file.name+'['+plupload.formatSize(file.size)+']<a href="#" class="del">删除</a></li>';
                        $("#${id}_${origin}_${originId}_file_queue").append(item);
                    });
                    upload_${id}.start();
                    loaded_${id}=0;
                },
                FileUploaded:function(up, file,info){
                     var arry=[];
                     var has = $("input[name=${id}_${origin}_${originId}_attachment_vos]");
                     if(has.val()){ //已经存在
                          arry=JSON.parse(has.val());
                          arry.push(JSON.parse(info.response));
                      }else{
                          arry.push(JSON.parse(info.response)); 
                      }
                      has.val(JSON.stringify(arry));
                      loaded_${id}=0;//重置
                      $("#${id}_${origin}_${originId}_file_queue").parents("form").data("attachment_upload_complete",false);
                },
                UploadProgress:function(up, file){
                    if(100==file.percent){
                        $("span#progress_"+file.id).html('[上传完成]');
                    }else{
                        $("span#progress_"+file.id).html("["+file.percent+"%]");
                    }
                    var kbs=(file.loaded-loaded_${id})/1024;
                    //console.log(kbs+'kb/s');
                    loaded_${id} = file.loaded;
                },
                UploadComplete:function(up,files){
                    $("#${id}_${origin}_${originId}_file_queue").parents("form").data("attachment_upload_complete",true);
                },
                error:function(up, err){
                    if(err.code==-600){
                        alert('文件大小不符合要求(${max_file_size})');
                    }else{
                        alert("\nError #"+err.code + ": " + err.message);
                    }
                    
                }
             }
        });
        upload_${id}.init();
    </script>
</#macro>
<#-- 
id:触发上传的组件id
上传的地址将设置为id="${id}_value"的value,eg:id='abc' ,将返回的值赋给abc_value
callback:上传完成后的回调
maxFileSize:文件限制大小,缺省10mb
-->
<#macro image id="" callback="" maxFileSize="10mb">
    <script type="text/javascript">
     
        var upload_${id} = new plupload.Uploader({
             runtimes : 'flash',
             browse_button : "${id}",
             url : "${rc.contextPath}/uploadImg",
             unique_names : true,
             filters:{
                max_file_size : '${maxFileSize}',
                mime_types:[{title : "Image files", extensions : "jpg,gif,png"}]
             },
             flash_swf_url : '${rc.contextPath}/member_static/admin/pupload/Moxie.cdn.swf',
             init:{
                FilesAdded : function(up, files){
                    upload_${id}.start();
                },
                FileUploaded:function(up, file,info){
                    <#if callback!=''>
                     try{
                            if(typeof ${callback} && $.isFunction(${callback})){
                                ${callback}(JSON.parse(info.response));
                            }
                        }catch(e){
                                alert(e.message);
                        }
                      <#else>
                      $("#${id}_value").val(JSON.parse(info.response));
                    </#if>
                },
                UploadProgress:function(up, file){
             
                   
                },
                UploadComplete:function(up,files){
                   
                },
                error:function(up, err){
                     if(err.code==-600){
                        alert('文件大小不符合要求(${max_file_size})');
                    }else{
                    	
                        alert("\nError #"+err.code + ": " + err.message);
                    }
                }
             }
        });
        upload_${id}.init();
    </script>
</#macro>
<#-- 
上传视频到cc
id:内部会生成一个id=id的按钮
originId:来源id
origin:来源
btnName:上传按钮
btnClass:按钮的样式
video_upload_complete:所在form的data由上传组件维持,video_upload_complete=true,表示没有正在进行的上传
maxFileSize:最大文件,单位字节
-->
<#macro ccUpload id="" origin="" originId = ""  maxFileSize='3145728000'>
    <script type="text/javascript" >
        var pre_loaded = 0;
        <#-- 
                   调用者：flash
                   功能：选中上传文件，获取文件名函数
                   说明：用户可以加入相应逻辑
        -->
         function on_spark_selected_file(filename,filesize) {
            $("#${id }_swf").data('videoInfo',{fileName:filename,fileSize:filesize,originStr:'${origin}',originId:'${originId}'});
            if(filename.length>40){
                filename=filename.substring(0,40)+"……";
            }
            if(filesize>${maxFileSize}){
                alert(filename+'['+Teacher.formatByteSize(filesize)+']超过最大限制['+Teacher.formatByteSize(${maxFileSize})+']');;
                return ;
            }
         
            $.post("${rc.contextPath}/remote/video/getUploadParams",function(re){
                 document.getElementById("${id}swf").start_upload(re);
                 var item = "<li class='file-item'>"+filename+"["+Teacher.formatByteSize(filesize)+"]<span id='video_progress'>等待上传</span><a href='javascript:;' class='del'>删除</a></li>";
                 $("#${id}_file_queue").append(item);
                 $("#${id }_swf").parents("form").data("video_upload_complete",false);
             });
         }
         
       <#-- 
                 调用者：flash
                  功能：验证上传是否正常进行函数
                  时间：2010-12-22
                 说明：用户可以加入相应逻辑
         -->
         function on_spark_upload_validated(status, videoid) {
             if (status == "OK") {
                $("#${id }_swf").data('videoInfo').videoId=videoid;
                $("#${id }_swf").data('videoInfo',videoInfo);
             } else if (status == "NETWORK_ERROR") {
                 $('#video_progress').html('网络错误');
             } else {
                $('#video_progress').html("api错误码：" + status);
             }
         }
         <#-- 
                         调用者：flash
                         功能：通知上传进度函数
                         时间：2010-12-22
                         说明：用户可以加入相应逻辑
         -->
         function on_spark_upload_progress(progress,response,s) {
            var b =response.bytesloaded-pre_loaded;
            pre_loaded =response.bytesloaded;
            var remainBytes = response.bytesTotal - response.bytesloaded;
            if(progress==100){
                $.post("${rc.contextPath}/plugin/video/save",$("#${id }_swf").data('videoInfo'),function(resp){
                    if(resp.success){
                       var oldIds= $("#_video_ids").val();
                       $("#_video_ids").val(oldIds?oldIds:''+','+resp.value);
                       $('#video_progress').html('上传成功'); 
                     }else{
                       $('#video_progress').html('视频保存失败');
                     }
                });
                return ;
            }
            if(remainBytes&&b){
                $('#video_progress').html(progress+'% '+Teacher.formatByteSize(b)+'/s 预计:'+Teacher.remainTime(remainBytes,b));
            }else{
                $('#video_progress').html('等待上传');
            }
            
         }
    </script>
</#macro>


<#-- 
上传视频到cc

-->
<#macro ccUploadHelper id="" origin="" originId = ""  maxFileSize='3145728000' buttonId=''>
    <script type="text/javascript" >
        var pre_loaded = 0;
        <#-- 
                   调用者：flash
                   功能：选中上传文件，获取文件名函数
                   说明：用户可以加入相应逻辑
        -->
        
         function on_spark_selected_file(filename,filesize) {
            $("#${buttonId}").hide();
            $("#${id }_swf").data('videoInfo',{fileName:filename,fileSize:filesize,originStr:'${origin}',originId:'${originId}'});
            if(filename.length>40){
                filename=filename.substring(0,40)+"……";
            }
            if(filesize>${maxFileSize}){
                alert(filename+'['+Teacher.formatByteSize(filesize)+']超过最大限制['+Teacher.formatByteSize(${maxFileSize})+']');;
                return ;
            }
         
            $.post("${rc.contextPath}/remote/video/getUploadParams",function(re){
                 document.getElementById("${id}swf").start_upload(re);
                 var item = "<li class='file-item'>"+filename+"["+Teacher.formatByteSize(filesize)+"]<span id='video_progress'>等待上传</span><a href='javascript:;' class='del btn'>删除</a></li>";
                 $("#${id}_file_queue").append(item);
                 $("#${id }_swf").parents("form").data("video_upload_complete",false);
             });
         }
         
       <#-- 
                 调用者：flash
                  功能：验证上传是否正常进行函数
                  时间：2010-12-22
                 说明：用户可以加入相应逻辑
         -->
         function on_spark_upload_validated(status, videoid) {
             if (status == "OK") {
                $("#${id }_swf").data('videoInfo').videoId=videoid;
                $("#${id }_swf").data('videoInfo',videoInfo);
             } else if (status == "NETWORK_ERROR") {
                 $('#video_progress').html('网络错误');
             } else {
                $('#video_progress').html("api错误码：" + status);
             }
         }
         <#-- 
                         调用者：flash
                         功能：通知上传进度函数
                         时间：2010-12-22
                         说明：用户可以加入相应逻辑
         -->
         function on_spark_upload_progress(progress,response,s) {
            var b =response.bytesloaded-pre_loaded;
            pre_loaded =response.bytesloaded;
            var remainBytes = response.bytesTotal - response.bytesloaded;
            if(progress==100){
                $.post("${rc.contextPath}/plugin/video/save",$("#${id }_swf").data('videoInfo'),function(resp){
                    if(resp.success){
                       var oldIds= $("#_video_ids").val();
                       $("#_video_ids").val(oldIds?oldIds:''+','+resp.value);
                       $('#video_progress').html('上传成功'); 
                       $("#${buttonId}").show();
                     }else{
                       $('#video_progress').html('视频保存失败');
                     }
                });
                return ;
            }
            if(remainBytes&&b){
                $('#video_progress').html(progress+'% '+Teacher.formatByteSize(b)+'/s 预计:'+Teacher.remainTime(remainBytes,b));
            }else{
                $('#video_progress').html('等待上传');
            }
            
         }
    </script>
</#macro>



<#-- 
id:播放器容器id,可能是div
cc视频播放器
videoId视频的id,必填
width:宽度  ,整数 默认480
height:高度,整数 默认364
autostart:是否自动播放 默认false
allowFullscreen:是否允许全屏,默认允许
typeId: 主键ID
type: 类型 work tutorial cocach
-->
<#macro ccPlayer id="videoPlayerSwf" videoId="" width="480" height="364" autostart="" allowFullscreen="true" typeId="" type="" playid="" feeStaus="" hasBuy="">
    <script type="text/javascript">
        In.ready("swfobject",function(){
                var swfobj=new SWFObject('http://union.bokecc.com/flash/player.swf', '${id}_swf', '${width}', '${height}', '8');
                swfobj.addVariable( "userid" , "${CC_VIDEO_USERID}");  
                swfobj.addVariable( "playid" , "${playid}");
                swfobj.addVariable( "videoid" , "${videoId}");
                swfobj.addVariable( "playerId", "BB0CF998633416D5");    
                swfobj.addVariable( "mode" , "api");    //  mode, 注意：必须填写，否则无法播放
                swfobj.addVariable( "autostart" , "${autostart}"); //  开始自动播放，true/false
                swfobj.addVariable( "jscontrol" , "true");  //  开启js控制播放器，true/false
                swfobj.addParam('allowFullscreen','${allowFullscreen}');
                swfobj.addParam('allowScriptAccess','always');
                swfobj.addParam('wmode','transparent');
                swfobj.write('${id}');
            });
            
               function on_cc_player_init(){
				 	var config = {};
				    config.on_player_start = "onPlayStart"; //设置开始播放时回调JS函数的名称
				    var player = getSWF("${id}_swf");
				    player.setConfig(config);
				    player.onPlayStart();
  				}
				function getSWF(swfID) {
				  
				    if( navigator.appName.indexOf( "Microsoft" ) != -1 ){
				      return window[ swfID ];
				    } else {
				      return document[ swfID ];
				    }
				}
				function onPlayStart(){
				     var url =''; 
				     	 if('${type}' =='work'){
					       url ="/play/work/${typeId}";
					     }
					     if('${type}' =='tutorial'){
					     	url ="/play/tutorial/${typeId}";
					     }
					     if('${type}' == 'coach'){
					     	url ="/play/coach/${typeId}";
					     }
						$.post(url,function(data){
							});
				}
				
    </script>
</#macro>
<#--

展示类播放器
videoId:播放视频的id
playerId:展示类播放器id
id:播放器容器id,如果为空，则为document
 -->
<#macro showCCPlayer id="" videoId="" playerId="BB0CF998633416D5" width="300" height="324" autoStart="false">
	<script type="text/javascript" src="http://union.bokecc.com/player?vid=${videoId}&autoStart=${autoStart}&width=${width}&height=${height}&playerid=${playerId}">
	</script>
</#macro>
<#-- 
id:播放器容器id,可能是div
cc视频播放器
videoId视频的id,必填
width:宽度  ,整数 默认480
height:高度,整数 默认364
autostart:是否自动播放 默认false
allowFullscreen:是否允许全屏,默认允许
-->
<#macro ccManagePlayer id="videoPlayerSwf" videoId="" width="480" height="364" autostart="" allowFullscreen="true">
    <script type="text/javascript">
        In.ready("swfobject",function(){
                var swfobj=new SWFObject('http://union.bokecc.com/flash/player.swf', '${id}_swf', '${width}', '${height}', '8');
                swfobj.addVariable( "userid" , "${CC_VIDEO_USERID}");  
                swfobj.addVariable( "videoid" , "${videoId}");    
                swfobj.addVariable( "mode" , "api");    //  mode, 注意：必须填写，否则无法播放
                swfobj.addVariable( "autostart" , "${autostart}"); //  开始自动播放，true/false
                swfobj.addVariable( "jscontrol" , "true");  //  开启js控制播放器，true/false
                swfobj.addParam('allowFullscreen','${allowFullscreen}');
                swfobj.addParam('allowScriptAccess','always');
                swfobj.addParam('wmode','transparent');
                swfobj.write('${id}');
            });
    </script>
</#macro>
<#-- 
通用视频播放器通用插件,可以直接播放一个url
id:播放器的容器，即展现播放器的一个界面元素的ID，并且此元素无限制。（必选）
videoUrl:需要播放视频的URL地址。（必选）
params:设置播放器宽高并且格式必需为｛width:'宽度像素值',heigth:'高度像素值'｝（非必选）
-->
<#macro videoPlayer id="" url="" params="{width:'600px',heigth:'400px'}">
    <script type="text/javascript">
        In.ready('ckplayer',function(){
            var flashvars={
                f:'${videoUrl}',
                c:0,
                p:0,
                e:2,
                m:0,
                i:${params}.img,
                b:1
                };
            CKobject.embedSWF('<@shop.s/>/<@shop.dir/>/js/plugins/ckplayer/ckplayer.swf','${id}','ckplayer_a1',${params}.width,${params}.heigth,flashvars);
        });
         
    </script>
</#macro>
