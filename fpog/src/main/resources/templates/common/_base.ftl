<#compress><#--用来压缩空白空间和空白的行-->
<!DOCTYPE html>
<!--[if IE 8]> <html lang="cn" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="cn" class="ie9"> <![endif]-->
<html lang="cn">
<!--<![endif]-->
<head>
    <!-- Meta -->
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="content-style-type" content="text/css"/>
    <meta http-equiv="content-script-type" content="text/javascript"/>
    <meta name="version" content="水肚儿 v1.0"/>
    <meta content="width=device-width, initial-scale=1.0,user-scalable=no" name="viewport"/>
    <@block name="baseSeo"></@block>
    <!-- basic styles -->
    <link rel="stylesheet" href="${request.contextPath}/plugin/bootstrap/css/bootstrap.min.css">
    <!-- validator -->
    <link rel="stylesheet" href="${request.contextPath}/plugin/validator/css/bootstrapValidator.min.css">
    <link rel="stylesheet" href="${request.contextPath}/plugin/confirm/jquery-confirm.min.css">
    <link rel="stylesheet" href="${request.contextPath}/css/ace/font-awesome.min.css"/>
    <link rel="stylesheet" href="${request.contextPath}/css/jquery.gritter.css"/>
    <link rel="stylesheet" href="${request.contextPath}/plugin/confirm/jquery-confirm.min.css"/>
    <!--[if IE 7]>
    <link rel="stylesheet" href="${request.contextPath}/css/ace/font-awesome-ie7.min.css"/>
    <![endif]-->
    <!-- page specific plugin styles -->
    <!-- fonts -->
    <!-- ace styles -->
    <link rel="stylesheet" href="${request.contextPath}/css/ace/ace.min.css"/>
    <link rel="stylesheet" href="${request.contextPath}/css/ace/ace-rtl.min.css"/>
    <link rel="stylesheet" href="${request.contextPath}/css/ace/ace-skins.min.css"/>
    <link rel="stylesheet" href="${request.contextPath}/css/main.css"/>
    <link rel="stylesheet" href="${request.contextPath}/plugin/kindeditor/themes/default/default.css"/>
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="${request.contextPath}/css/ace/ace-ie.min.css"/>
    <![endif]-->
    <!-- inline styles related to this page -->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${request.contextPath}/plugin/html5shiv.js"></script>
    <script src="${request.contextPath}/plugin/respond.min.js"></script>
    <![endif]-->
    <script src="${request.contextPath}/plugin/jQuery-2.1.4.min.js"></script>
    <script src="${request.contextPath}/plugin/bootstrap/js/bootstrap.min.js"></script>
    <!-- ace scripts -->
    <script src="${request.contextPath}/plugin/ace/ace-elements.min.js"></script>
    <script src="${request.contextPath}/plugin/ace/ace.min.js"></script>
    <script src="${request.contextPath}/plugin/ace/ace-extra.min.js"></script>
    <script src="${request.contextPath}/plugin/jquery.gritter.min.js"></script>
    <script src="${request.contextPath}/plugin/confirm/jquery-confirm.min.js"></script>
    <script src="${request.contextPath}/js/main.js"></script>
    <script src="${request.contextPath}/plugin/jquery.cookie.js"></script>
    <script src="${request.contextPath}/plugin/kindeditor/kindeditor-all-min.js"></script>
    <script src="${request.contextPath}/js/bootbox.min.js"></script>

    <script>
        var ctx = "${request.contextPath}";
    </script>
    <@block name="css"></@block>
    <@block name="js"></@block>

</head>

<body>
    <@block name="bodyClass"></@block>
    <#include "/common/_header.ftl"/>
    <div class="main-container" id="main-container">
        <div class="main-container-inner">
            <a class="menu-toggler" id="menu-toggler" href="#">
                <span class="menu-text"></span>
            </a>
            <div class="sidebar" id="sidebar">
                <script type="text/javascript">
                    try {
                        ace.settings.check('sidebar', 'collapsed')
                    } catch (e) {
                    }
                </script>
                <#include "/common/_menu.ftl"/>
            </div>
            <div class="main-content">
                <div class="breadcrumbs" id="breadcrumbs">
                    <script type="text/javascript">
                        try {
                            ace.settings.check('breadcrumbs', 'fixed')
                        } catch (e) {
                        }
                    </script>

                    <ul class="breadcrumb">
                        <@block name="pageTitle"></@block>
                    </ul><!-- .breadcrumb -->
                </div>

                <div class="page-content">
                    <@block name="baseBody"></@block>
                </div>
                <!-- /.page-content -->
            </div><!-- /.main-content -->
        </div><!-- /.main-container-inner -->
        <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
            <i class="icon-double-angle-up icon-only bigger-110"></i>
        </a>
    </div><!-- /.main-container -->
    <#include "/common/_footer.ftl"/>

</body>
</html>
</#compress>