<@override name="baseSeo">
<title>产品管理--sawelly</title>
<meta content="" name="description" />
<meta content="" name="author" />

</@override>

<@override name="pageTitle">
<li class="active">
    <i class="icon-gift home-icon"></i>
    产品管理列表
</li>
</@override>

<@override name="baseBody">
<div class="row">

    <div class="col-xs-12">

        <h3 class="header lighter">
            <form method="get" class="form-inline" action="${request.contextPath}/product/index">
                <div class="form-group">
                    <label>名称：</label>
                    <input type="text" name="productName" value="${product.productName}"/>
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
                    <th width="4%" >序号</th>
                    <th width="10%" >名称</th>
                    <th width="37%">简介</th>
                    <th width="10%" >类别</th>
                    <th width="5%" >创建人</th>
                    <th width="15%" >创建时间</th>
                    <th width="18%" >操作</th>
                </tr>
                </thead>

                <tbody>
						 <#list page.results as item >
                         <tr>
                             <td>${item_index + 1}</td>
                             <td>${item.productName}</td>
                             <td>${item.remark}</td>
                             <td><@product_type typeCode="${item.typeCode}">${productType.typeName}</@product_type></td>
                             <td><@user_info id="${item.creatorId}">${user.realName}</@user_info></td>
                             <td>${item.createDate?string('yyyy-MM-dd hh:ss:mm')}</td>
                             <td>
                                 <div class="visible-md visible-lg hidden-md hidden-xs action-buttons">
                                     <a class="btn btn-sm btn-info" href="${request.contextPath}/product/edit?id=${item.id}">
                                         编辑
                                     </a>
                                     <a class="btn btn-sm  btn-danger btnConfirm" href="${request.contextPath}/product/del/${item.id}">
                                         删除
                                     </a>
                                     <a class="btn btn-sm btn-info" href="${request.contextPath}/product/toTop/${item.id}">
                                         <#if item.topTime?? >
                                             取消置顶
                                         <#else>
                                             置顶
                                         </#if>
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
        location.href = "${request.contextPath}/product/edit";
    }
</script>
</@override>
<#include "/macros/pager_front.ftl">
<@extends name="/common/_base.ftl"/>