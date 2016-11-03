<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册</title>
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
  .regist-width {
      width: 430px;
  }
}
.page-body {
    background: #fff;
}
.regist-title {
    color: #b1b1b1;
    margin: 50px 7px 39px 7px;
    font-size: 18px;
    border-bottom: 1px solid #ccc;
}
.regist-title span {
    position: relative;
    top: 10px;
    padding: 0 30px;
    background: #fff;
}
</style>
</head>
<body class="page-body">
    <div class="page-container">
        
        <div class="text-center center-block regist-width">
        <h1 class="text-danger">松美</h1>
        <h5 class="text-danger">账号注册或登入</h5>
        </div>
        
        <div class="text-center regist-title">
        <span><a href="login">登入</a> <b>·</b> <b>注册</b></span>
        </div>
        
        <div class="panel panel-default center-block regist-width">
        <div class="panel-body">
        <form id="regist_form" role="form">
        <div class="form-group">
        <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
        <input type="text" class="form-control" placeholder="账号" name="account"/>
        </div></div>
        <div class="form-group">
        <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
        <input type="text" class="form-control" placeholder="昵称" name="nick"/>
        </div></div>
        <div class="form-group">
        <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
        <input type="password" class="form-control" placeholder="密码" name="password"/>
        </div></div>
        <div class="form-group">
        <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-screenshot"></i></span>
        <input type="text" class="form-control" placeholder="验证码" name="vcode"/>
        <span id="regist_vcode" class="input-group-addon" style="padding: 0 10px; cursor: pointer;"> </span>
        </div></div>
        <div class="form-group">
            <label>
                <input type="checkbox" class="cbr" name="agreement" value="1" checked="checked"/>
                同意协议
            </label>
            <a>查看协议</a>
        </div>
        <div id="regist_error"></div>
        <div class="form-group">
        <button class="btn btn-primary btn-block btn-regist">注册</button>
        </div>
        </form>
        </div>
        
        </div>
    </div>
</body>
<!-- JavaScripts initializations and stuff -->
<script type="text/javascript">
    $(document).ready(function() {
        loadVcode();
        $('#regist_vcode').on('click', function(ev) {
            ev.preventDefault();
            loadVcode();
        });
        $('#regist_form').on('submit', function(ev) {
            ev.preventDefault();
            var $form = $(this);
            if ($form.data('runing')) {
                return;
            }
            
            $form.data('runing', true);
            var acc = $('[name=account]', $form).val();
            var nic = $('[name=nick]', $form).val();
            var pwd = $('[name=password]', $form).val();
            var vco = $('[name=vcode]', $form).val();
            var agr = $('[name=agreement]:checked', $form).val();
            if (!agr) {
                showError('必须同意协议');
                return;
            }
            var $btn = $('.btn-regist', $form).text('注册中...');
            $.ajax({
                url: 'registry',
                method: 'post',
                data: {account: acc, password: pwd, nick: nic, vcode: vco},
                dataType: 'json',
                success: function(ret) {
                    if (!ret.succeed) {
                        showError('注册失败');
                    } else {
                        alert("注册成功 Success!!!");
                    }
                    $btn.text('注册');
                    $form.data('runing', false);
                },
                error: function() {
                    showError('注册失败');
                    $btn.text('注册');
                    $form.data('runing', false);
                }
            });
        });
    });
    function showError(info) {
        var $error = $('#regist_error');
        $error.html('<div class="alert alert-danger">\
                        <button type="button" class="close" data-dismiss="alert">\
                            <span aria-hidden="true">×</span> <span class="sr-only">Close</span>\
                        </button>\
                        <span class="text">'+ info +'</span>\
                    </div>');
    }
    function loadVcode() {
        var $t = $('#regist_vcode');
        $t.html('<i>加载中...</i>');
        $t.html('<img alt="vcode" src="vcode?' + (new Date()).getTime() + '" />');
    }
</script>
</html>