
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" import="org.accenture.oep.bean.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%
	HttpSession sess = request.getSession(false);
	if (sess == null) {
%>

<jsp:forward page="exam?action=FinishExam" />
<%
	}
%>
<%
	String duration = (String) sess.getAttribute("duration");
	HashMap answersMap = (HashMap)sess.getAttribute("answerMap");
	Integer currQnNo = (Integer)sess.getAttribute("currQ");
	if(currQnNo == null){
		currQnNo = 100;
	}
%>
<%
	int countdownTimer = 15 * 60 * 1000;
	if (duration != null) {
		countdownTimer = Integer.parseInt(duration);
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Exam Portal</title>
<style>
p {
	text-align: right;
	font-size: 20px;
	margin-top: 0px;
}
</style>
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
	// Set the date we're counting down to
	var countDownDate = new Date().getTime() +
<%=countdownTimer%>
	;

	// Update the count down every 1 second
	var x = setInterval(
			function() {

				// Get todays date and time
				var now = new Date().getTime();

				// Find the distance between now an the count down date
				var distance = countDownDate - now;

				// Time calculations for days, hours, minutes and seconds
				var days = Math.floor(distance / (1000 * 60 * 60 * 24));
				var hours = Math.floor((distance % (1000 * 60 * 60 * 24))
						/ (1000 * 60 * 60));
				var minutes = Math.floor((distance % (1000 * 60 * 60))
						/ (1000 * 60));
				var seconds = Math.floor((distance % (1000 * 60)) / 1000);

				// Output the result in an element with id="demo"
				document.getElementById("timer").innerHTML = "<img width='25' height='25' alt='timer' src='resources/images/clock.jpg' style='vertical-align: middle'/> Time Left: "
						+ minutes + "m " + seconds + "s ";

				document.getElementById("scrrenTimer").value = distance;

				// If the count down is over, write some text 
				if (distance < 0) {
					clearInterval(x);
					document.getElementById("timer").innerHTML = "EXPIRED";
					document.getElementById("examForm").action = "exam?action=FinishExam&timer="
					document.getElementById("examForm").method = "POST";
					document.getElementById("examForm").submit();
				}
			}, 1000);
</script>
<script>
	function moveToPreviousQuestion() {
		var timerCurr = document.getElementById("scrrenTimer").value
		//alert("timer..."+timer);
		document.getElementById("examForm").action = "exam?action=Previous&timer="
				+ timerCurr;
		document.getElementById("examForm").method = "POST";
		document.getElementById("examForm").submit();
	}

	function moveToNextQuestion() {
		var timerCurr = document.getElementById("scrrenTimer").value
		//alert("timer..."+timer);
		document.getElementById("examForm").action = "exam?action=Next&timer="
				+ timerCurr;
		document.getElementById("examForm").method = "POST";
		document.getElementById("examForm").submit();
	}

	function endExam() {
		var timerCurr = document.getElementById("scrrenTimer").value
		//alert("timer..."+timer);
		document.getElementById("examForm").action = "exam?action=FinishExam&timer="
				+ timerCurr;
		document.getElementById("examForm").method = "POST";
		document.getElementById("examForm").submit();
	}

	function noBack() {
		window.history.forward();
	}
</script>

</head>
<br />
<body onload="noBack();" onpageshow="if (event.persisted) noBack();">
	<div align="right" style="padding: 50px">
		<p id="timer"></p>
		<button class="login100-form-btn" align="center" onclick="endExam()">Finish
			Exam</button>
		<div id="scrrenTimer" value="" style="visibility: hidden"></div>
	</div>
	<div class="main-container" align="center" id="all">
		<div class="maincontent-container">
			<%
				int currentQuestion = ((Exam) request.getSession().getAttribute("currentExam")).getCurrentQuestion();
				System.out.println("Question Number " + currentQuestion + " retrieved ");
			%>
			<%
				if (currentQuestion > 0) {
			%>
			<img width="25" height="25" src="resources/images/bullet81.jpg"
				alt="Back" style='vertical-align: middle'
				onclick="moveToPreviousQuestion();">&nbsp;&nbsp;<big><b>Prev</b></big>
			<%
				}
			%>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <big><b>Current
					Question ${sessionScope.quest.questionNumber+1}/ 10 </b></big>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<%
				if (currentQuestion < 9) {
			%>
			<big><b>Next</b></big>&nbsp;&nbsp;<img width="25" height="25"
				src="resources/images/bullet8.jpg" alt="Next"
				style='vertical-align: middle' onclick="moveToNextQuestion();">
			<%
				}
			%>

			<form id="examForm"
				class="login100-form validate-form flex-sb flex-w" method="POST"
				action="">
				<div class="topborder"></div>
				<div class="border-pxl">
					<div class="form-container" align="left">
						<span class="login100-form-title p-b-32">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span> <span>${sessionScope.quest.question}</span><br />
						<br />

						<%int incr = 0; %>
						<c:forEach var="choice"
							items="${sessionScope.quest.questionOptions}" varStatus="counter">
							<%
							incr++;
							QuizQuestion q = (QuizQuestion)(session.getAttribute("quest")); 
							
							if(q!= null && answersMap!= null){
							System.out.println("question insider.."+q.getQuestion());
							System.out.println("answerMap Contains.."+answersMap);
							System.out.println("currQnNo.."+currQnNo);
							Integer index = (Integer)answersMap.get(currQnNo);
							System.out.println("index obtained within.."+index);
							System.out.println("incr obtained within.."+incr);
							if(index != null){
							if(index==incr){
							%>
							<input type="radio" name="answer" value="${counter.count}"
								checked="checked">${choice}  <br />
							<%}else{ %>
							<input type="radio" name="answer" value="${counter.count}">${choice}  <br />
							<%}}else{%>
							<input type="radio" name="answer" value="${counter.count}">${choice}  <br />
							<%} }else{%>
							<input type="radio" name="answer" value="${counter.count}">${choice}  <br />
							<%} %>
						</c:forEach>

						<br />


					</div>
				</div>
				<div class="bottomborder"></div>
			</form>
			<br>
			<div class="footer">Copyrights 2018 - All Rights Reserved</div>

		</div>
	</div>

</body>
</html>