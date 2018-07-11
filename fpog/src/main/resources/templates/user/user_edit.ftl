<@override name="baseSeo">
<title>用户管理--sawelly</title>
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
    <a href="${request.contextPath}/user/index">
        用户列表
    </a>
</li>
<li class="active">用户编辑</li>
</@override>
<@override name="baseBody">
<div class="row">
    <div class="col-xs-12">
        <form class="form-horizontal" role="form" action="${request.contextPath}/user/save">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">用户名:</label>
                <div class="col-sm-4">
                    <#if  user.id??&&""!=user.id >
                        <input type="hidden" name="id" class="form-control col-xs-10 col-sm-5" value="${user.id}"/>
                        <input type="text" disabled="disabled" name="userName" class="form-control col-xs-10 col-sm-5" value="${user.userName}"/>
                    </#if>
                    <#if !(user.id??&&""!=user.id) >
                        <input type="text" name="userName" class="form-control col-xs-10 col-sm-5" value="${user.userName}"/>
                    </#if>

                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">密码:</label>
                <div class="col-sm-4">
                    <input type="text" name="pwd" class="form-control col-xs-10 col-sm-5" value="${user.pwd}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">真实姓名:</label>
                <div class="col-sm-4">
                    <input type="text" name="realName" class="form-control col-xs-10 col-sm-5" value="${user.realName}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">邮箱:</label>
                <div class="col-sm-4">
                    <input type="text" name="email" class="form-control col-xs-10 col-sm-5" value="${user.email}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">手机号:</label>

                <div class="col-sm-4">
                    <input type="text" name="mobile" class="form-control col-xs-10 col-sm-5" value="${user.mobile}"/>
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
    });

</script>
</@override>
<@extends name="/common/_base.ftl"/>