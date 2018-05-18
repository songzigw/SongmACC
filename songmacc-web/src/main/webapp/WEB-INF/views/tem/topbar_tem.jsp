<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8" session="false" isELIgnored="false"%>
<script type="text/x-template" id="songm-topbar-template">
    <nav class="topbar" id="topbar">
        <div class="topbar-inner">
    <a href="." title="松美"><h1 class="songm-logo">松美</h1></a>
    <div class="navigation">
        <a class="nav-item active" href="/explore">书架</a>
        <a class="nav-item" href="/my">我的</a>
        <a class="nav-item" href="/my">客户端</a>
    </div>
    <div class="search-bar">
    <form action="/search" method="get" row="form">
    <div class="form-group">
        <input type="text" class="form-control" placeholder="Search..." name="keyword">
        <button type="submit" class="btn-unstyled">
            <i class="sog-icon sog-icon-search"></i>
        </button>
    </div>
    </form>
    </div>
    <div class="user-info">
    <template v-if="frame.currUser == null">
    <button class="btn btn-blue btn-sm" v-on:click="login">登入</button>
    <button class="btn btn-blue btn-sm" v-on:click="regist">注册</button>
    </template>
    <template v-else>
    <div class="profile btn-group">
    <button class="btn dropdown-toggle" data-toggle="dropdown" type="button"><img width="30" height="30" :src="frame.currUser.avatar"></button>
    <ul class="dropdown-menu list-unstyled">
    <li><a href="${pageContext.request.contextPath}/member/user/edit"><i class="sog-icon sog-icon-profile"></i>设置</a></li>
    <li><a href="${pageContext.request.contextPath}/logout"><i class="sog-icon sog-icon-lock"></i>退出</a></li>
    </ul>
    </div>
    </template>
    </div>
    </div>
    </nav>
</script>