<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8" session="false" isELIgnored="false"%>
<link rel="shortcut icon" href="${pageContext.request.contextPath }/assets/sog/imgs/favicon.png" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/sog/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/sog/js/toastr/toastr.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/sog/js/dropzone/css/dropzone.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/sog/js/cropper/cropper.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/sog/css/sog.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/custom/css/songm.css" />
<script src="${pageContext.request.contextPath }/assets/sog/js/vue.js"></script>
<script src="${pageContext.request.contextPath }/assets/sog/js/jquery.js"></script>
<script src="${pageContext.request.contextPath }/assets/sog/js/jquery-validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath }/assets/sog/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath }/assets/sog/js/toastr/toastr.min.js"></script>
<script src="${pageContext.request.contextPath }/assets/sog/js/TweenMax.min.js"></script>
<script src="${pageContext.request.contextPath }/assets/sog/js/joinable.js"></script>
<script src="${pageContext.request.contextPath }/assets/sog/js/dropzone/dropzone.min.js"></script>
<script src="${pageContext.request.contextPath }/assets/sog/js/cropper/cropper.min.js"></script>
<script src="${pageContext.request.contextPath }/assets/sog/js/sog.js"></script>
<script src="${pageContext.request.contextPath }/assets/custom/js/frame.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- [if lt IE 9] >
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<! [endif]      -->

<script type="text/javascript">
frame.init({
    contextPath: '${pageContext.request.contextPath}',
    userAvatarDefault: '${userAvatarDefault}',
    userAvatarUpload: '${userAvatarUpload}'
});
</script>