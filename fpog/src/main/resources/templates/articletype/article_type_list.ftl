<@override name="baseSeo">
<title>文章分类管理--sawelly</title>
<meta content="" name="description" />
<meta content="" name="author" />

</@override>

<@override name="pageTitle">
<li class="active">
    <i class="icon-gift home-icon"></i>
    文章分类列表
</li>
</@override>

<@override name="baseBody">
<div class="row">

    <div class="col-xs-12">

        <h3 class="header lighter">
            <form method="get" class="form-inline" action="${request.contextPath}/article/type/index">
                <div class="form-group">
                    <label>名称：</label>
                    <input type="text" name="typeName" value="${articleType.typeName}"/>
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
                    <th>名称</th>
                    <th>简介</th>
                    <th>创建人</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </thead>

                <tbody>
						 <#list page.results as item >
                         <tr>
                             <td>${item_index + 1}</td>
                             <td>${item.typeName}</td>
                             <td>${item.remark}</td>
                             <td>
                                 <@user_info id="${item.creatorId}">${user.realName}</@user_info>
                             </td>
                             <td>${item.createDate?string('yyyy-MM-dd hh:ss:mm')}</td>
                             <td>
                                 <div class="visible-md visible-lg hidden-md hidden-xs action-buttons">
                                     <a class="btn btn-sm btn-info" href="${request.contextPath}/article/type/edit?id=${item.id}">
                                         编辑
                                     </a>
                                     <a class="btn btn-sm  btn-danger btnConfirm" href="${request.contextPath}/article/type/del/${item.id}">
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
        location.href = "${request.contextPath}/article/type/edit";
    }
</script>
</@override>
<#include "/macros/pager_front.ftl">
<@extends name="/common/_base.ftl"/>