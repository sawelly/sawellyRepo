<ul class="nav nav-list">
    <#if _user.id ==1 >
    <li <#if _request.requestURI?index_of("/user/")!=-1> class="active open dropdown-toggle"</#if> >
        <a href="#" class="dropdown-toggle">
            <i class="icon-credit-card"></i>
            <span class="menu-text">用户管理</span>
            <b class="arrow icon-angle-down"></b>
        </a>
        <ul class="submenu">
            <li <#if _request.requestURI?index_of("/user/index")!=-1>class="active"</#if>>
                <a href="${request.contextPath}/user/index">
                    <i class="icon-double-angle-right"></i>
                    用户
                </a>
            </li>
        </ul>
    </li>
    </#if>

    <li <#if _request.requestURI?index_of("/project/")!=-1> class="active"</#if> >
        <a href="${request.contextPath}/project/index" class="dropdown-toggle">
            <i class="icon-credit-card"></i>
            <span class="menu-text">项目管理</span>
        </a>
    </li>

    <li <#if _request.requestURI?index_of("/article/")!=-1> class="active open dropdown-toggle"</#if> >
        <a href="#" class="dropdown-toggle">
            <i class="icon-credit-card"></i>
            <span class="menu-text">文章管理</span>
            <b class="arrow icon-angle-down"></b>
        </a>
        <ul class="submenu">
            <li <#if _request.requestURI?index_of("/article/type/index")!=-1>class="active"</#if>>
                <a href="${request.contextPath}/article/type/index">
                    <i class="icon-double-angle-right"></i>
                    文章分类
                </a>
            </li>
            <li <#if _request.requestURI?index_of("/article/index")!=-1>class="active"</#if>>
                <a href="${request.contextPath}/article/index">
                    <i class="icon-double-angle-right"></i>
                    文章管理
                </a>
            </li>
        </ul>
    </li>
    <li <#if _request.requestURI?index_of("/product/")!=-1> class="active open dropdown-toggle"</#if> >
        <a href="#" class="dropdown-toggle">
            <i class="icon-credit-card"></i>
            <span class="menu-text">产品管理</span>
            <b class="arrow icon-angle-down"></b>
        </a>
        <ul class="submenu">
            <li <#if _request.requestURI?index_of("/product/type/")!=-1>class="active"</#if>>
                <a href="${request.contextPath}/product/type/index">
                    <i class="icon-double-angle-right"></i>
                    产品分类
                </a>
            </li>
            <li <#if _request.requestURI?index_of("/product/index")!=-1>class="active"</#if>>
                <a href="${request.contextPath}/product/index">
                    <i class="icon-double-angle-right"></i>
                    产品管理
                </a>
            </li>
        </ul>
    </li>


</ul><!-- /.nav-list -->

<div class="sidebar-collapse" id="sidebar-collapse">
    <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
</div>

				 
				 