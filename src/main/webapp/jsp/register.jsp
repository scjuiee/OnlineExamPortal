<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
</head>
<body>
	<div class="main-container" align="center" id="all">

		<div class="content-container">
			<div class="maincontent-container">
				<div class="topborder"></div>
				<div class="border-pxl">
					<div class="form-container" align="left">
						<form class="login100-form validate-form flex-sb flex-w"
							method="POST" action="doregister">
							<span class="register-form-title p-b-32"> <big><b>Register
										Yours!!!</b></big>

							</span>
							<c:if test="${not empty errorMsg}">
								<h4 style="width: 800px"><font color="red">${errorMsg}</font></h4>
							</c:if>
							<span> FirstName<font color="red">*</font>
							</span>
							<div class="wrap-input100 validate-input m-b-36"
								data-validate="Firstname is required">
								<input class="input100" type="text" name="firstName"> <span
									class="focus-input100"></span>
							</div>


							<span> LastName<font color="red">*</font>
							</span>
							<div class="wrap-input100 validate-input m-b-36"
								data-validate="Lastname is required">
								<input class="input100" type="text" name="lastName"> <span
									class="focus-input100"></span>
							</div>

							<span> Employee ID<font color="red">*</font>
							</span>
							<div class="wrap-input100 validate-input m-b-36"
								data-validate="EmployeeId is required">
								<input class="input100" type="text" name="empId"> <span
									class="focus-input100"></span>
							</div>

							<span> Skill<font color="red">*</font>
							</span>
							<div class="wrap-input100 validate-input m-b-36"
								data-validate="Select a technology">
								<select class="input100" name="technology">
									<option value="Java">Java</option>
									<option value="Sterling">Sterling</option>
									<option value="Javascript">Javascript</option>
									<option value="Oracle">Oracle</option>
								</select><span class="focus-input100"></span>
							</div>
							<div>
								<button class="login100-form-btn"
									onclick="window.location.href='index.html'">Register</button>
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