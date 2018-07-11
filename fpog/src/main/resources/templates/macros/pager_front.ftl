<#import "/macros/sawelly_macros.ftl" as sawelly>
<#macro pager page link_count=5>
<#--前一个参数是总记录数，后一个参数是页面记录数-->  
<@pagination curr_index=page.currentPage pageCount=page.pages link_count=link_count totalCount=page.totalSize/> 
</#macro>

<#--link_count:显示的链接数 -->
<#macro pagination curr_index=1 pageCount=1 link_count=5  totalCount=1>  
    <#--  使用三目更简洁-->
    <#local pageCount =sawelly.max(1,pageCount)>
    <#local _p_tmp=(curr_index-((link_count/2)?int))>
    <#local _p_start = sawelly.max(_p_tmp,1)>
    <#local _p_tmp=(_p_start + link_count - 1)>
    <#local _p_end=sawelly.min(pageCount,_p_tmp)>
    <#local _p_tmp=(_p_end - link_count + 1)>
    <#local _p_start=sawelly.max(1,_p_tmp)>
    <#local _queryString= "${request.queryString!'1=1'} " >
    
    <div class="modal-footer no-margin-top">
    	<ul class="pagination pull-right no-margin">
     <#local _prev=(curr_index>1)?string('${curr_index-1}','1')>
    <li class="disabled">
	        <a href="#">
                    共${pageCount}页 共${totalCount}条记录
       </a>
	   </li>
       <#if curr_index == 1>

	       <li class="disabled">
	       	<a href="javascript:;">首页</a>
	       </li>
	       <li class="disabled">
	       	<a href="javascript:;">上一页</a>
	       </li>
       <#else>
         <li>
         	<a href="${request.requestUri+"?"+sawelly.addParam('${_queryString}','currentPage','1')}" >首页</a>
         </li>
         <li>
         	<a href="${request.requestUri+"?"+sawelly.addParam('${_queryString}','currentPage','${_prev}')} ">上一页</a>
         </li>
       </#if>


       		<li><#local _next=(curr_index<pageCount)?string('${curr_index+1}','${pageCount}')></li>

        <#list _p_start.._p_end as idx >
              <li<#if idx==curr_index> class="active" </#if>>
              	<a href="${request.requestUri+"?"+sawelly.addParam('${_queryString}','currentPage','${idx}')}"  >${idx}</a>
              </li>
        </#list>
        <#if curr_index == pageCount>
	       <li class="disabled">
	       	<a href="javascript:;">下一页</a>
	       </li>
	       <li class="disabled">
	        <a href="javascript:;">末页</a>
	       </li>
        <#else>
        <li>
           <a href="${request.requestUri+"?"+sawelly.addParam('${_queryString}','currentPage','${_next}')}">下一页 </a>
        </li>
        <li>
        <a href="${request.requestUri+"?"+sawelly.addParam('${_queryString}','currentPage','${pageCount}')}" >末页</a>
        </li>
       </#if>


        </ul>
    </div>
</#macro> 



 		