<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="static/test/css/body.css">
<link rel="stylesheet" href="static/test/css/style.css">
<script type="text/javascript" src="static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="static/js/cookie.js"></script>
</head>
<body>

	<div class="container">
		<section id="content">
		<form action="login/login.do" id="form1">
			<h1>会员登录</h1>
			<div>
				<input placeholder="账号" required="" id="userAccount"
					name="userAccount" type="text">
			</div>
			<div>
				<input placeholder="密码" required="" id="userPass" name="userPass"
					type="password">
			</div>
			<div class="">
				<span class="help-block u-errormessage" id="js-server-helpinfo">&nbsp;</span>
			</div>
			<div>
				<!-- <input type="submit" value="Log in" /> -->
				<input value="登录" class="btn btn-primary" id="login"
					onclick="toLogin()" type="button"> <a href="#">忘记密码?</a> <a
					onclick="register()">Register</a>
			</div>
			<input type="hidden" value="${pd.p }" name="p">
		</form>
		</section>
		<!-- content -->
	</div>
	<!-- container -->
</body>
</html>
<script>
	function toLogin() {
		//console.log();
		$.ajax({
			//cache : "False",
			type : "post",
			//async: false, 【(A)】
			url : "${loginUrl}",
			data : $("#form1").serialize(),
			//data : {userAccount : $("#userAccount").val(),userPass : $("#userPass").val()},
			success : function(result) {
				console.log(result);
				if (200 == result.code) {
					setAllCookie(result.data.accToken);
					//console.log(result.data.accToken);
					window.open(result.data.redirectUrl, '_self');
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
			}
		});
	}
	function register() {
		window.open('${toRegister}');
	}
</script>