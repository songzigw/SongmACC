<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入</title>
<meta name="viewport"
    content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<link rel="shortcut icon" href="${pageContext.request.contextPath }/assets/imgs/favicon.png" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/css/sog.css" />
<script src="${pageContext.request.contextPath }/assets/js/jquery.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/TweenMax.min.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/joinable.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/sog.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- [if lt IE 9] >
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<! [endif]      -->
<style type="text/css">
@media (min-width: 768px) {
  .login-width {
      width: 430px;
  }
}
.page-body {
    background: #fff;
}
.login-title {
    color: #b1b1b1;
    margin: 50px 7px 39px 7px;
    font-size: 18px;
    border-bottom: 1px solid #ccc;
}
.login-title span {
    position: relative;
    top: 10px;
    padding: 0 30px;
    background: #fff;
}
</style>
</head>
<body class="page-body">
    <div class="page-container">
        
        <div class="text-center center-block login-width">
        <h1 class="text-danger">松美</h1>
        <h5 class="text-danger">账号注册或登入</h5>
        </div>
        
        <div class="text-center login-title">
        <span><b>登入</b> <b>·</b> <a href="register">注册</a></span>
        </div>
        
        <div class="panel panel-default center-block login-width">
        <div class="panel-body">
        <form id="login_form" role="form">
        <div class="form-group">
        <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
        <input type="text" class="form-control" placeholder="账号"/>
        </div></div>
        <div class="form-group">
        <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
        <input type="password" class="form-control" placeholder="密码"/>
        </div></div>
        <div class="form-group">
        <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-screenshot"></i></span>
        <input type="text" class="form-control" placeholder="验证码"/>
        <span id="login_vcode" class="input-group-addon" style="padding: 0; cursor: pointer;"><img alt="vcode" src="vcode" /> </span>
        </div></div>
        <div class="form-group">
            <label>
                <input type="checkbox" class="cbr" checked />
                同意协议
            </label>
            <a>查看协议</a>
        </div>
        <div class="alert alert-danger">
            <button type="button" class="close" data-dismiss="alert">
                <span aria-hidden="true">×</span> <span class="sr-only">Close</span>
            </button>
            账号格式错误
        </div>
        <div class="form-group">
        <button class="btn btn-primary btn-block">登入</button>
        </div>
        </form>
        </div>
        
        </div>
    </div>
</body>
<!-- JavaScripts initializations and stuff -->
<script type="text/javascript">
    $(document).ready(function() {
        $('#login_vcode').on('click', function(ev) {
            ev.preventDefault();
            var $t = $(this);
            $t.html('<i>加载中...</i>');
            $t.html('<img alt="vcode" src="vcode?' + (new Date()).getTime() + '" />');
        });
    });
</script>
</html>