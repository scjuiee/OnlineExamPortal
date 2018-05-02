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
<script language="JavaScript" src="resources/js/ajax_download.js"></script>
<body onload="checkStarted();">
	<div class="main-container" align="center" id="all">
		<div class="fullwidth">
			<div class="top-menu"></div>
		</div>
		<div class="content-container">
			<form method="post" action="validate">
				<div class="maincontent-container">
					<div class="topborder"></div>
					<div class="border-pxl">
						<div id="control" class="form-container" align="center"
							style="visibility: hidden">
							<a onclick="submitExam();" title="Pause Exam"
								style="cursor: pointer"><img alt="submit"
								src="resources/images/submit.gif" /> Submit Exam</a> &nbsp;&nbsp; <a
								onclick="pauseExam();" title="Pause Exam" id="pauseLink"
								name="pauseLink" style="cursor: pointer"> <img alt="pause"
								src="resources/images/button-pause.png" /> Pause Exam
							</a> &nbsp;&nbsp;
						</div>
						<div id="timer" class="form-container" align="center"
							style="visibility: hidden">
							<div id="time">
								00 <img src='resources/images/clock.jpg'
									style='vertical-align: middle' /> 00
							</div>
						</div>
						<div id="start" class="form-container" align="left"
							style="visibility: hidden">
							<span><c:if test="${not empty errorMsg}">
									<h4 style="width: 800px"><font color="red">${errorMsg}</font></h4>
							</c:if></span>
							<div class="form-row">
								<img alt="Voucher" src="resources/images/login.jpg"
									style="vertical-align: middle" width="60" height="40" /> <big><b>
										Examination Voucher ID</b></big>
								<div class="wrap-input100 validate-input m-b-36"
									data-validate="Username is required">
									<input class="input100" type="text" name="voucherId"> <span
										class="focus-input100"></span>
								</div>
							</div>
							<div style="vertical-align: middle" align="center">
								<img id="loading" name="loading"
									src="resources/images/loading.gif" alt="Loading"
									style="visibility: hidden">
								<div align="center">
									<button class="examId-form-btn" align="center">Next</button>
								</div>
							</div>
						</div>
						<div style="vertical-align: middle" align="center">
							<a id="back" name="back" href="#top"
								onclick="pageIndex--;refreshExamDisplay();"
								style="visibility: hidden; cursor: pointer"><img
								src="resources/images/bullet81.jpg" alt="Back"></a> <a
								id="next" name="next" href="#top"
								onclick="pageIndex++;refreshExamDisplay();"
								style="visibility: hidden; cursor: pointer"><img
								src="resources/images/bullet8.jpg" alt="Next"></a> <br> <a
								id="main" name="main" onclick="refreshExamsMainMenu();"
								style="visibility: hidden; cursor: pointer"><img
								src="resources/images/mainmenu.gif" title="Main Exam Menu"
								alt="Main Exam Menu" /></a> <a id="next_exam" name="next_exam"
								href="#top"
								onclick="pageIndex=0;examIndex++;refreshExamDisplay();"
								style="visibility: hidden; cursor: pointer"><img
								src="resources/images/next.gif" title="Next Exam"
								alt="Next Exam" /></a>
						</div>
					</div>
					<div class="bottomborder"></div>
					<div class="footer">Copyrights 2018 - All Rights Reserved</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>