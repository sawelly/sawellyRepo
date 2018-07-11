<@override name="baseSeo">
<title>产品分类管理--sawelly</title>
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
    <a href="${request.contextPath}/product/type/index">
        产品分类列表
    </a>
</li>
<li class="active">产品分类编辑</li>
</@override>
<@override name="baseBody">
<div class="row">
    <div class="col-xs-12">
        <form class="form-horizontal" role="form" id="formid" action="${request.contextPath}/product/type/save">
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right">名称:</label>
                <div class="col-sm-4">
                    <input type="hidden" name="id"  value="${productType.id}"/>
                    <input type="text" name="typeName" class="form-control col-xs-10 col-sm-5" value="${productType.typeName}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right">简介:</label>
                <div class="col-sm-4">
                    <input type="hidden" name="remark" id="remarkhide"  value="${productType.remark}"/>
                    <textarea class="form-control col-xs-10 col-sm-5" id="remark" >${productType.remark}</textarea>
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
<script type="text/javascript">
    function save() {
        $("#remarkhide").val( $("#remark").val());
        $("#formid").submit();
    }
    
</script>

</@override>
<@extends name="/common/_base.ftl"/>