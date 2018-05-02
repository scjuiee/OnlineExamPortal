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
<script>
	function startNewExam() {
		//alert("inside alert");
		document.getElementById("examForm").action = "exam";
		document.getElementById("examForm").method = "POST";
		document.getElementById("examForm").submit();
	}
</script>
</head>
<body>
	<div class="main-container" align="center" id="all">
		<form id="examForm" method="POST" action="">
			<div class="fullwidth">
				<div class="top-menu"></div>
			</div>
			<div class="logo-container floatleft clearboth">
				<big><b> Instructions Before you Start The Exam!!!</b></big>

			</div>
			<div class="content-container">
				<div class="maincontent-container">
					<div class="topborder"></div>
					<div class="border-pxl">
						<div class="form-container" align="left">
							<ul style="list-style-type: disc">
								<b>
									<li>i. Quiz contains 10 Multiple Choice Questions</li>
									<li>ii. Total time for the Quiz is 15 Minutes</li>
									<li>iii. You can finish the exam at any time< by clicking
										the finish button at the top right corner</li>
									<li>iv. Read the question carefully before answering</li>
									<li>v. You can change your answers before submitting</li>
									<li>vi. <img width="25" height="25"
										src="resources/images/bullet8.jpg" alt="Next"
										style='vertical-align: middle'> &nbsp; Click this link
										to move to next question.
									</li>
									<li>vii. <img width="25" height="25"
										src="resources/images/bullet81.jpg" alt="Back"
										style='vertical-align: middle'>&nbsp; Click this link to
										move to previous question.
									</li>
									<li>viii. Your answers will be auto saved once you move
										next or previous.</li>
								</b>
							</ul>
							<br> <br>
							<div align="center">
								<img src="resources/images/start.jpg" title="Start Exam"
									alt="Start Exam" onclick="startNewExam();"
									style="vertical-align: middle; cursor: pointer" />
							</div>

						</div>
					</div>
					<div class="bottomborder"></div>
					<div class="footer">
						Copyrights 2018 - All Rights Reserved <br> <img alt="logo"
							src="resources/images/online-exam-icon.jpg" class="examLogo"
							width="200" height="150" style="vertical-align: middle" />
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>