<@override name="baseSeo">
<title>产品管理--sawelly</title>
<meta content="" name="description" />
<meta content="" name="author" />

</@override>

<@override name="css">
<link rel="stylesheet" href="${request.contextPath}/plugin/validator/css/bootstrapValidator.min.css">
<link rel="stylesheet" href="${request.contextPath}/plugin/colorbox/colorbox.css">
<link rel="stylesheet" href="${request.contextPath}/plugin/bootstrap-fileinput/css/fileinput.css" type="text/css"/>
<style>
    .attachment {
        /*padding-left: 45px;*/
        /*padding-top: 5px;*/
    }

    div[class="layui-m-layercont"] {
        line-height: 1.5em;
        overflow: auto;
    }
    .clearfix{
        margin-top: 0.3em;

    }
    img{
        width: 8em;
        height: 5em;
        z-index:88;
    }
    .btn-danger{
        background:#cc3939 !important;
    }
    .content .main-content .main-content-item{
        margin-bottom: 0em !important;
    }
    .layui-m-layerbtn {
        justify-content: center;
    }
    .main-content-item1{
        margin-top:0px !important;
    }
    .custom-textarea{
        width: 93%;
        margin-left: 1em;
        margin-bottom:0.4em;
    }
    .yu-font-size{
        margin-bottom:0.6em;
    }
    #uploadImg{
        cursor: pointer;
    }
    .kv-upload-progress{
        display:none !important;
    }
    /*.progress-bar{*/
        /*display:none !important;*/
    /*}*/
    .btn-file{
        display:none !important;
    }

    .fileinput-upload-button{
        display:none !important;
    }
</style>
</@override>
<@override name="js"> 
<script src="${request.contextPath}/plugin/validator/js/bootstrapValidator.min.js"></script>
<script src="${request.contextPath}/plugin/validator/js/language/zh_CN.js"></script>
<script src="${request.contextPath}/plugin/date-time/bootstrap-datepicker.min.js"></script>
</@override>

<@override name="pageTitle">
<li class="active">
    <i class="icon-gift home-icon"></i>
    <a href="${request.contextPath}/product/index">
        产品列表
    </a>
</li>
<li class="active">产品编辑</li>
</@override>
<@override name="baseBody">
<div class="row">
    <div class="col-xs-12">
        <form class="form-horizontal" role="form" id="formid" method="post" action="${request.contextPath}/product/save">
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right">名称:</label>
                <div class="col-sm-4">
                    <input type="hidden" name="id"  value="${product.id}"/>
                    <input type="text" name="productName" class="form-control col-xs-10 col-sm-5" value="${product.productName}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right">所属类别:</label>
                <div class="col-sm-4">
                    <select name="typeCode" id="typeid" class="form-control col-xs-10 col-sm-5" >
                        <option value="">请选择</option>
                        <#list typeList as item>
                            <option value="${item.typeCode}" <#if item.typeCode == product.typeCode> selected="selected" </#if>  >${item.typeName}</option>
                        </#list>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right">上传产品:</label>
                <div class="col-sm-8">
                    <div class="attachment" style="position:relative">
                        <div>
                            <#list fileList as file >
                                <input type="hidden" name="fileurl" value="${request.contextPath}/file/imgshow/${product.projectCode}/${file.savename}"/>
                                <input type="hidden" name="filename" value="${file.filename}" title="${file.id}"/>
                                <input type ="hidden" name="fileids"  value="${file.id}" />
                            </#list>
                            <img  id = "uploadImg" src="${request.contextPath}/img/upload-img1.png"/   >
                        </div>
                        <input id="attachmentid" placeholder="上传图片" type="file"  multiple/>
                    </div>

                    <#--<input id="attachmentid" placeholder="上传图片" type="file"  multiple/>-->

                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right">简介:</label>
                <div class="col-sm-4">
                    <textarea name="remark" style="height: 100px;" class="form-control col-xs-10 col-sm-5 " >${product.remark}</textarea>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right">内容:</label>
                <div class="col-sm-4" style="width: 70%;">
                    <!-- 加载编辑器的容器 -->
                    <script id="container" name="content" type="text/plain">

                    </script>
                </div>
            </div>

            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn btn-info" onclick="save()" >
                        <i class="icon-ok bigger-110"></i>
                        保存
                    </button>

                    &nbsp; &nbsp; &nbsp;
                    <button type="reset" class="btn">
                        <i class="icon-undo bigger-110"></i>
                        取消
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="${request.contextPath}/plugin/bootstrap-fileinput/js/fileinput.js"></script>
<script type="text/javascript" src="${request.contextPath}/plugin/bootstrap-fileinput/js/locales/zh.js"></script>

<script type="text/javascript" src="${request.contextPath}/plugin/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="${request.contextPath}/plugin/ueditor/ueditor.all.js"></script>
<!-- 实例化编辑器 -->
<script type="text/javascript">
    // var ue = UE.getEditor('container');
    var ue = UE.getEditor('container', {
        // http://fex.baidu.com/ueditor/#start-toolbar
        toolbars: [
            ['FullScreen', 'Source', 'Undo', 'Redo','Bold','test',
                'simpleupload'
            ]
        ]
    });
    //对编辑器的操作最好在编辑器ready之后再做
    ue.ready(function() {
        ue.setHeight(300)
        //设置编辑器的内容
        ue.setContent(' ${product.content}');
        //获取html内容，返回: <p>hello</p>
        var html = ue.getContent();
    });
    $(document).on('ready', function () {

        initFile('attachmentid');
        $("#uploadImg").click(function(){
            $("#attachmentid").click();
        })

    });

    function initFileUrls() {
        var inputs = $('input[name="fileurl"]');
        var initialPreviewUrl = new Array(inputs.length);
        var i = 0;
        $(inputs).each(function () {
            initialPreviewUrl[i] = $(this).val();
            i++;
        });
        return initialPreviewUrl;
    }

    function initFileNames() {
        var inputs = $('input[name="filename"]');
        var initialPreviewName = new Array(inputs.length);
        var i = 0;
        $(inputs).each(function () {
            initialPreviewName[i] = {caption: $(this).val(), url: '${request.contextPath}/file/delFile', extra: {id: $(this).attr("title")}};
            i++;
        });
        return initialPreviewName;
    }


    function initFile(elementId) {
        var obj = $('#' + elementId);
        obj.fileinput({
            initialPreview: initFileUrls(),
            initialPreviewAsData: true,
            initialPreviewShowDelete: true,
            initialPreviewConfig: initFileNames(),
            overwriteInitial: false,
            language: 'zh', //设置语言
            showCaption: false,
            showRemove: false,
            uploadUrl: "${request.contextPath}/file/upload", //上传的地址
            dropZoneEnabled: false,//是否显示拖拽区域
            maxFileSize: 10240,//单位为kb，如果为0表示不限制文件大小
            allowedFileExtensions: ["jpg","png","bmp"],
            fileActionSettings: {
                showRemove: true,
                showUpload: true,
                showZoom: true,
                removeIcon: '<i class="glyphicon glyphicon-trash text-danger"></i>',
                removeClass: 'btn btn-xs btn-default',
                removeTitle: 'Remove file',
                uploadIcon: '<i class="glyphicon glyphicon-upload text-info"></i>',
                uploadClass: 'btn btn-xs btn-default',
                uploadTitle: 'Upload file',
                zoomIcon: '<i class="glyphicon glyphicon-zoom-in"></i>',
                zoomClass: 'btn btn-xs btn-default',
                zoomTitle: 'View Details',
                dragIcon: '<i class="glyphicon glyphicon-menu-hamburger"></i>',
                dragClass: 'text-info',
                dragTitle: 'Move / Rearrange',
                dragSettings: {},
                indicatorNewTitle: 'Not uploaded yet',
                indicatorSuccessTitle: 'Uploaded',
                indicatorErrorTitle: 'Upload Error',
                indicatorLoadingTitle: 'Uploading ...'
            },
        }).on("filebatchselected", function (event, files) {
            $(this).fileinput("upload");
            // $(this).find(".kv-upload-progress").addClass("display","block");
        }).on('filepreupload', function (event, data, previewId, index) {     //上传中
            var form = data.form, sponse, reader = data.reader;
            console.log('文件正在上传');
        }).on("fileuploaded", function (event, data, previewId, index) {    //一个文件上传成功
            var result = data.response; //后台返回的json
            if (result != 'failure') {

                console.log("result==" + result);
                var fidinput = "<input type ='hidden' name='fileids' value='" + result + "'/>"
                $("#" + previewId).append(fidinput);
            } else {
                console.log('文件上 传失败！');
            }
        }).on('fileerror', function (event, data, msg) {  //一个文件上传失败
            console.log('文件上传失败！' + data.id);
        }).on('filesuccessremove', function (event, id) {
            console.log('删除成功！========================'+id);
            console.log('删除成功！========================'+event);
            console.log('删除成功！');
        })
    }


    function save() {
        var v = $("#typeid").val();
        if (v == "") {
            bootbox.alert("请选择类别");
            return;
        }
        $("#formid").submit();
    }

</script>

</@override>
<@extends name="/common/_base.ftl"/>