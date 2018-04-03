<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户编辑</title>
<meta name="viewport"
    content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<jsp:include page="common_resources.jsp"></jsp:include>
<jsp:include page="tem/user_edit_tem.jsp"></jsp:include>
<jsp:include page="tem/topbar_tem.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/assets/custom/js/user.edit.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    var userEdit = new UserEdit();
});
</script>
</head>
<body class="page-body songm" id="user_edit_page">

    <songm-topbar :frame="frame"></songm-topbar>

    <div class="page-container">
        <div class="main-content">
        
        <div class="account-edit">
            <div class="account-navb">
            <div>
                <div class="user-avatar">
                <img class="avatar-inner" width="160" height="160" :src="frame.currUser.avatar">
                </div>
            </div>
            <div style="width: 168px;">
            <ul class="list-unstyled nav-bar">
            <li v-for="menu in menus" v-bind:class="{active: menu.isActive}"><a :href="menu.uri">{{menu.name}}</a></li>
            </ul>
            </div>
            </div>
            
            <div class="account-content">
            
            <component v-bind:is="view">
                <songm-user-base></songm-user-base>
            </component>            
            
            </div>
        </div>
        
        </div>
    </div>
    
    <songm-footer></songm-footer>
</body>
</html>