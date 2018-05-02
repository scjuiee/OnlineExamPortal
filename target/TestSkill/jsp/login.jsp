<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Online Exam Portal</title>
<!--===============================================================================================-->
<link href="resources/css/styles.css" rel="stylesheet" type="text/css" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="resources/css/util.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/vendor/daterangepicker/daterangepicker.css">
<!-- -------- -->
<script type="text/javascript">
	function goToRegister() {
		//alert("Hello ");
		document.getElementById("loginForm").action = "launchregister";
		document.getElementById("loginForm").method = "POST";
		document.getElementById("loginForm").submit();
	}
	function noBack() {
		window.history.forward();
	}
</script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();">
	<div class="main-container" align="center" id="all">

		<div class="content-container">
			<div class="maincontent-container">
				<div class="topborder"></div>
				<div class="border-pxl">
					<div class="form-container" align="left">
						<form class="login100-form validate-form flex-sb flex-w"
							id="loginForm" method="POST" action="">
							<span class="register-form-title p-b-32"> <big><b>Login(for
										Admin Use Only)</b></big> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <!-- <a href='register'>Take Test</a>-->
							</span>
							<c:if test="${not empty errorMsg}">
								<h4 style="width: 800px"><font color="red">${errorMsg}</font></h4>
							</c:if>
							<span> Username<font color="red">*</font>
							</span>
							<div class="wrap-input100 validate-input m-b-36"
								data-validate="Username is required">
								<input class="input100" type="text" name="username"> <span
									class="focus-input100"></span>
							</div>

							<span> Password<font color="red">*</font>
							</span>
							<div class="wrap-input100 validate-input m-b-12"
								data-validate="Password is required">
								<span class="btn-show-pass"> <i class="fa fa-eye"></i>
								</span> <input class="input100" type="password" name="pass"> <span
									class="focus-input100"></span>
							</div>

							<div class="flex-sb-m w-full p-b-48"></div>

							<div class="container-login100-form-btn">
								<button class="login100-form-btn">Login</button>
								&nbsp;&nbsp;
								<button class="login100-form-btn" onclick="goToRegister()">Take
									Test</button>
							</div>

						</form>
					</div>
				</div>
				<div class="bottomborder"></div>
				<div class="footer">Copyrights 2018 - All Rights Reserved</div>
			</div>
		</div>
	</div>
</body>
</html>