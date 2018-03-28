<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册</title>
<meta name="viewport"
    content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<jsp:include page="common_resources.jsp"></jsp:include>
<script src="${pageContext.request.contextPath }/assets/custom/js/register.js"></script>
<!-- JavaScripts initializations and stuff -->
<script type="text/javascript">
    $(document).ready(function() {
        register.init();
    });    
</script>
</head>
<body class="page-body songm">
    <div class="page-container regist">
        <div class="main-content" id="main_content">
        <div class="text-center">
        <h1 class="text-danger">松美</h1>
        <h5 class="text-danger">账号注册或登入</h5>
        </div>
        
        <div class="text-center regist-title">
        <span><a href="login">登入</a> <b>·</b> <b>注册</b></span>
        </div>
        
        <div class="panel panel-default panel-border panel-shadow center-block min-width-430">
        <div class="panel-body">
        <form id="regist_form" role="form" v-on:submit.prevent="checkIn">
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
        <span id="regist_vcode" class="input-group-addon" style="padding: 0; cursor: pointer;"> </span>
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
        <button type="submit" class="btn btn-primary btn-block btn-regist">注册</button>
        </div>
        </form>
        </div>
        </div>
        
        <songm-footer/>
        </div>
    </div>
</body>
</html>