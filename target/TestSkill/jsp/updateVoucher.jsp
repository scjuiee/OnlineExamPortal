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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	function noBack() {
		window.history.forward();
	}

	function activate() {
		//alert("Hello");
		var evId = $('#examVoucherId').val();
		var skill = $('#technology').val();

		if (validateValues(evId, skill)) {
			document.getElementById("searchForm").action = "getVoucherActivate?activate=Y"
			document.getElementById("searchForm").method = "POST";
			document.getElementById("searchForm").submit();
		}
	}
	
	function deactivate() {
		//alert("Hello");
		var evId = $('#examVoucherId').val();
		var skill = $('#technology').val();

		if (validateValues(evId, skill)) {
			document.getElementById("searchForm").action = "getVoucherActivate?deactivate=Y"
			document.getElementById("searchForm").method = "POST";
			document.getElementById("searchForm").submit();
		}
	}
	
	function goToSearch() {
		document.getElementById("searchForm").action = "search"
		document.getElementById("searchForm").method = "GET";
		document.getElementById("searchForm").submit();
	}
	
	function getVoucherActivationPage() {
		document.getElementById("searchForm").action = "getVoucherActivate"
		document.getElementById("searchForm").method = "GET";
		document.getElementById("searchForm").submit();
	}
	function validateValues(evId, skill) {
		if (evId != null || skill != null) {
			return true;
		} else {
			alert("Please select either exam voucher code or proper skill");
			return false;
		}
	}
	function logout() {
		document.getElementById("searchForm").action = "login";
		document.getElementById("searchForm").method = "GET";
		document.getElementById("searchForm").submit();
	}
</script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();">
	<div align="right" style="padding-right: 20px">

		<a id="logout" onclick="logout()" href="javascript:void(0);">logout</a>
	</div>
	<div class="main-container" align="center" id="all">
		<div class="content-container">
			<div class="maincontent-container">
				<div class="topborder"></div>
				<div class="border-pxl">
					<div class="form-container" align="left">
						<form id="searchForm">
							<span class="register-form-title p-b-32"> </span>
							<table>
								<tr>
									<th width="50%"><span> Examination Voucher
											ID:&nbsp;&nbsp; </span></th>
									<th width="50%">
										<div class="wrap-input100">
											<input class="input100" type="text" name="examVoucherId"
												id="examVoucherId"> <span class="focus-input100"></span>
										</div>
									</th>
								</tr>
								<tr>
									<th width="50%"></th>
									<th width="50%"></th>
								</tr>
								<tr>
									<th width="50%">&nbsp;&nbsp;</th>
									<th width="50%">&nbsp;&nbsp;</th>
								</tr>
								<tr>
									<th width="50%"><span> Skill: &nbsp;&nbsp; </span></th>
									<th width="50%">
										<div class="wrap-input100 validate-input m-b-36"
											data-validate="Select a technology">
											<select class="input100" name="technology" id="technology">
												<option value="Java">Java</option>
												<option value="Sterling">Sterling</option>
												<option value="Javascript">Javascript</option>
												<option value="Oracle">Oracle</option>
											</select><span class="focus-input100"></span>
										</div>
									</th>
								</tr>
								<tr>
									<th width="50%"></th>
									<th width="50%">&nbsp;&nbsp;&nbsp;</th>
								</tr>
							</table>
							<div class="container-login100-form-btn" align="center">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="login100-form-btn" onclick="activate()">Activate</button>
								&nbsp;&nbsp;
								<button class="login100-form-btn" onclick="deactivate()">Deactivate</button>
								&nbsp;&nbsp;
								<button class="login100-form-btn" onclick="goToSearch()">Back</button>
							</div>
						</form>
						<span>&nbsp;&nbsp;</span> <span>&nbsp;&nbsp;</span>
						<div class="panel-body">
							<c:if test="${not empty responseStr}">
								${responseStr}
						</c:if>

						</div>
					</div>
				</div>
				<div class="bottomborder"></div>
				<div class="footer">Copyrights 2018 - All Rights Reserved</div>
			</div>
		</div>
	</div>
</body>
</html>