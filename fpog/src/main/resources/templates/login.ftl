<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>登录系统</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${request.contextPath}/plugin/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${request.contextPath}/plugin/validator/css/bootstrapValidator.min.css">
    <link rel="stylesheet" href="${request.contextPath}/css/jquery.gritter.css" />
    <link rel="stylesheet" href="${request.contextPath}/css/AdminLTE.css">
    <link rel="stylesheet" href="${request.contextPath}/css/ace/ace.min.css">
    <link rel="stylesheet" href="${request.contextPath}/css/main.css">

      <script src="${request.contextPath}/plugin/html5shiv.js"></script>
      <script src="${request.contextPath}/plugin/respond.min.js"></script>
      <style>
          body{
              background: url(${request.contextPath}/img/loginbg.png)  no-repeat center;
              background-size: cover;
          }

      </style>

  </head>
  <body class="hold-transition">
    <div class="login-box">
      <div class="login-logo" style="color: #fff;">
         sawelly后台管理系统
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">登录</p>
        <form method="post" class="registerForm" id="registerForm">
          <div class="form-group has-feedback">
            <input type="text" name="userName" class="form-control" placeholder="用户名" value="">
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" name="pwd" class="form-control" placeholder="密码" value="">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-8">

            </div><!-- /.col -->
            <div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
            </div><!-- /.col -->
          </div>
        </form>

      </div><!-- /.login-box-body -->

    </div><!-- /.login-box -->
    <div style="position: relative;width:100%;height: calc(100% - 466px);min-height: 250px;">
        <div style="width:100%;position: absolute;bottom: 50px;">
            <div style="width:225px;margin:0px auto;text-align: center;">
                <span style="color: #ffffff; font-size: 14px;">京ICP备18032903号</span>
            </div>
        </div>
    </div>



    <script src="${request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${request.contextPath}/plugin/bootstrap/js/bootstrap.min.js"></script>
    <script src="${request.contextPath}/plugin/validator/js/bootstrapValidator.min.js"></script>
    <script src="${request.contextPath}/plugin/jquery.gritter.min.js"></script>
    <script src="${request.contextPath}/js/main.js"></script>

    <script>
		   $(document).ready(function() {
		    $('#registerForm').bootstrapValidator({
		        fields: {
		            userName: {
		                message: 'The username is not valid',
		                validators: {
		                    notEmpty: {
		                        message: '用户名不能为空!'
		                    }
		                }
		            },
		            pwd: {
		                validators: {
		                    notEmpty: {
		                        message: '密码不能为空!'
		                    }
		                }
		            }
		        }
		    }) .on('success.form.bv', function(e) {
		            e.preventDefault();
		            var $form = $(e.target);
		            var bv = $form.data('bootstrapValidator');
		         	$.ajax({
						url : "${request.contextPath}/doLogin",
						type : "POST",
						data : $form.serialize(),
						success : function(data) {
							if (data == "-1") {
								message("用户名或密码错误!","error");
								return false;
							}else{
                                if("${backUrl}" == ""){
                                    location.href = "${request.contextPath}/";
                                }else{
                                    location.href = "${backUrl}";
                                }
							}
						}

					})
		        });
		});
  </script>
  </body>
</html>
