<#global domain="http://www.liaotiantu.com">  
<#-- 运行状态  debug:开发调试模式     product:生产模式-->
<#global runStatus="product">  
<#--
  	自定义宏.
-->
<#macro contentPath>${request.contextPath}</#macro>

<#--===========================For static start============================================== -->
<#--<#macro dir>${request.contextPath}/static/system</#macro>-->
<#--<#macro productImgPath>${request.contextPath}/upload/product_img/</#macro>-->
<#--前端静态 -->
<#--<#macro fs>-->
<#--<@dir/>/front-->
<#--</#macro>-->


<#function addParams url param>
	<#assign returnValue = "" /> 
	<#if url?exists>
			<#if (url?index_of("?") > 0)>
				<#assign returnValue = url + "&" + param />;
			<#else>
				<#assign returnValue = url + "?" + param />;
			</#if>
	</#if>
	<#return returnValue>
</#function>
<#--
date:如果null 返回空串
pattern:格式化日期格式,默认yyyy-MM-dd HH:mm:ss
 -->
<#function format date='' pattern="yyyy-MM-dd HH:mm:ss">
    <#if !(date?exists)>
        <#return "">
    </#if>
    <#if date?is_date>
            <#return date?string('${pattern}')>
        <#else>
        <#return date>
    </#if>
</#function>

<#macro substr str num>
	<#if (str?length>num)>
         ${str[0..num]?default("")}...
    <#else>
         ${str?default("")}
    </#if>      
</#macro>

<#-- 后台selected用到 -->
<#macro selected map val="">
<#if map?exists><#list map?keys as key><option value="${key}" <#if val?? && val=key>selected="selected"</#if>>${map[key]}</option></#list></#if>
</#macro>

<#-- 
取最大数
-->
<#function max first two>
    <#if first gt two>
        <#return first>
     <#else>
        <#return two>
    </#if>
</#function>
<#-- 
取最小数
-->
<#function min first two>
    <#if first lt two>
            <#return first>
    <#else>
            <#return two>
    </#if>
</#function>


<#-- 
增加参数
eg:
name=leonlau&key=aaaa ===> name=leonlau&key=aaaa&key=value
-->
<#function addParam queryString key value>
    <#local result="">
    <#local flag=true>
    <#if queryString!="">
        <#list queryString?split("&") as one>
            <#local tmp=one?split("=")[0]>
            <#local tmpValue=one?split("=")[1]>
            <#local end=one_has_next?string('&','')>
            <#if tmp==key>
                <#local result=result+tmp+"="+value+end>
                <#local flag=false>
                <#else>
                <#local result=result+tmp+'='+tmpValue+end>
            </#if>
        </#list>
    </#if>

    <#if flag>
        <#if result=="">
            <#local result=key+"="+value>
        <#else>
            <#local result=result+"&"+key+"="+value>
        </#if>
    </#if>
    <#return result>
</#function>