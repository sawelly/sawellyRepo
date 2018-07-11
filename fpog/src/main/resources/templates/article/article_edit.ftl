<@override name="baseSeo">
<title>文章管理--sawelly</title>
<meta content="" name="description" />
<meta content="" name="author" />

</@override>

<@override name="css">
<link rel="stylesheet" href="${request.contextPath}/plugin/validator/css/bootstrapValidator.min.css">
<link rel="stylesheet" href="${request.contextPath}/plugin/colorbox/colorbox.css">
<link rel="stylesheet" href="${request.contextPath}/plugin/bootstrap-fileinput/css/fileinput.css" type="text/css"/>
<style>
    /*.uecontent{*/
        /*width:100%;*/
    /*}*/
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
    <a href="${request.contextPath}/article/index">
        文章列表
    </a>
</li>
<li class="active">文章编辑</li>
</@override>
<@override name="baseBody">
<div class="row">
    <div class="col-xs-12">
        <form class="form-horizontal" role="form" id="formid" method="post" action="${request.contextPath}/article/save">
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right">标题:</label>
                <div class="col-sm-4">
                    <input type="hidden" name="id"  value="${article.id}"/>
                    <input type="text" name="title" class="form-control col-xs-10 col-sm-5" value="${article.title}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right">作者:</label>
                <div class="col-sm-4">
                    <input type="text" name="author" class="form-control col-xs-10 col-sm-5" value="${article.author}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right">所属类别:</label>
                <div class="col-sm-4">
                    <select name="typeCode" id="typeid" class="form-control col-xs-10 col-sm-5" >
                        <option value="">请选择</option>
                        <#list typeList as item>
                            <option value="${item.typeCode}" <#if item.typeCode == article.typeCode> selected="selected" </#if>  >${item.typeName}</option>
                        </#list>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right">简介:</label>
                <div class="col-sm-4">
                    <textarea name="remark" style="height: 100px;" class="form-control col-xs-10 col-sm-5 " >${article.remark}</textarea>
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
        ue.setContent(' ${article.content}');
        //获取html内容，返回: <p>hello</p>
        var html = ue.getContent();
    });
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