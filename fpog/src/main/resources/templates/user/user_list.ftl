<@override name="baseSeo">
<title>用户管理--sawelly</title>
<meta content="" name="description" />
<meta content="" name="author" />

</@override>

<@override name="pageTitle">
<li class="active">
    <i class="icon-gift home-icon"></i>
    用户管理列表
</li>
</@override>

<@override name="baseBody">
<div class="row">

    <div class="col-xs-12">

        <h3 class="header lighter">
            <form method="get" class="form-inline" action="${request.contextPath}/user/index">
                <div class="form-group">
                    <label>名称：</label>
                    <input type="text" name="userName" value="${user.userName}"/>
                </div>


                <button type="submit" id="searchBtn" class="btn btn-sm btn-primary">
                    查询
                </button>
                <button type="button" class="btn btn-sm btn-primary" style="float:right;margin-right:10px;" onclick="add()">新建</button>
            </form>
        </h3>
        <div class="table-responsive">
            <table id="sample-table-2" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>用户名</th>
                    <th>密码</th>
                    <th>真实姓名</th>
                    <th>邮箱</th>
                    <th>手机</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </thead>

                <tbody>
						 <#list page.results as item >
                         <tr>
                             <td>${item_index + 1}</td>
                             <td>${item.userName}</td>
                             <td>${item.pwd}</td>
                             <td>${item.realName}</td>
                             <td>${item.email}</td>
                             <td>${item.mobile}</td>
                             <td>${item.createDate?string('yyyy-MM-dd hh:ss:mm')}</td>
                             <td>
                                 <div class="visible-md visible-lg hidden-md hidden-xs action-buttons">
                                     <a class="btn btn-sm btn-info" href="${request.contextPath}/user/edit?id=${item.id}">
                                         编辑
                                     </a>
                                     <a class="btn btn-sm  btn-danger btnConfirm" href="${request.contextPath}/user/del/${item.id}">
                                         删除
                                     </a>
                                 </div>
                             </td>
                         </tr>
                         </#list>

                </tbody>
            </table>
			<@pager page />
        </div>
    </div>
</div>
<script>
    function add() {
        location.href = "${request.contextPath}/user/edit";
    }
</script>
</@override>
<#include "/macros/pager_front.ftl">
<@extends name="/common/_base.ftl"/>