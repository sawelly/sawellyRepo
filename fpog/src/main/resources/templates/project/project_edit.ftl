<@override name="baseSeo">
<title>项目管理--sawelly</title>
<meta content="" name="description" />
<meta content="" name="author" />

</@override>

<@override name="css">
<link rel="stylesheet" href="${request.contextPath}/plugin/validator/css/bootstrapValidator.min.css">
<link rel="stylesheet" href="${request.contextPath}/plugin/colorbox/colorbox.css">
</@override>
<@override name="js"> 
<script src="${request.contextPath}/plugin/validator/js/bootstrapValidator.min.js"></script>
<script src="${request.contextPath}/plugin/validator/js/language/zh_CN.js"></script>
<script src="${request.contextPath}/plugin/date-time/bootstrap-datepicker.min.js"></script>
</@override>

<@override name="pageTitle">
<li class="active">
    <i class="icon-gift home-icon"></i>
    <a href="${request.contextPath}/project/index">
			项目列表
    </a>
</li>
<li class="active">项目编辑</li>
</@override>
<@override name="baseBody">
<div class="row">
    <div class="col-xs-12">
        <form class="form-horizontal" role="form" action="${request.contextPath}/project/save">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">项目名:</label>
                <div class="col-sm-4">
                    <input type="hidden" name="id"  value="${projectIdId}"/>
                    <input type="text" name="projectName" class="form-control col-xs-10 col-sm-5" value="${project.projectName}"/>
                </div>
            </div>
            <#if _user.id == 1>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">编码:</label>
                <div class="col-sm-4">
                    <input type="text" name="projectCode" class="form-control col-xs-10 col-sm-5" value="${project.projectCode}"/>
                </div>
            </div>
            </#if>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">邮箱:</label>
                <div class="col-sm-4">
                    <input type="text" name="email" class="form-control col-xs-10 col-sm-5" value="${project.email}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">手机号:</label>

                <div class="col-sm-4">
                    <input type="text" name="mobile" class="form-control col-xs-10 col-sm-5" value="${project.mobile}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">qq:</label>

                <div class="col-sm-4">
                    <input type="text" required name="qq" class="form-control col-xs-10 col-sm-5" value="${project.qq}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">地址:</label>

                <div class="col-sm-4">
                    <input type="text" name="address" class="form-control col-xs-10 col-sm-5" value="${project.address}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">备注:</label>

                <div class="col-sm-4">
					<textarea name="remark" class="form-control col-xs-10 col-sm-5" >${project.remark}</textarea>
                </div>
            </div>

            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button type="submit" class="btn btn-info">
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
<script>
    $(function(){
        $('form').bootstrapValidator({
            fields: {
                email: {
                    validators: {
                        emailAddress: {
                            message: '邮箱地址格式有误'
                        }
                    }
                }
            }
        });
        $('.date-picker').datepicker({autoclose: true}).on('changeDate', function(ev){
            $(this).blur();
        });
    });

</script>
</@override>
<@extends name="/common/_base.ftl"/>