package org.accenture.oep.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.accenture.oep.bean.Exam;
import org.accenture.oep.bean.QuizQuestion;
import org.accenture.oep.bean.UserResultModel;
import org.accenture.oep.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Servlet implementation class ExamController
 */
@Controller
public class ExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	RegistrationService regService;
    
	@RequestMapping(value = "/exam", method = RequestMethod.POST)
	public ModelAndView renderExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ModelAndView mv = null;
		boolean finish=false;
		
		HttpSession session=request.getSession();		
		try
		{
			if(session.getAttribute("currentExam")==null)
		  {  session=request.getSession(); 	
		     String selectedExam=(String)request.getSession().getAttribute("technology"); 
		     System.out.println("Setting Exam "+selectedExam);
			 Exam newExam=new Exam(selectedExam);		  
			 session.setAttribute("currentExam",newExam);
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
				Date date = new Date();
				String started=dateFormat.format(date);
			  session.setAttribute("started",started);
		  }
		
		}catch(Exception e){e.printStackTrace();}
		
        Exam exam=(Exam)request.getSession().getAttribute("currentExam");		
        System.out.println("before loading the current question..");
        if(exam.currentQuestion==0){	
			exam.setQuestion(exam.currentQuestion);
		    QuizQuestion q=exam.questionList.get(exam.currentQuestion);	
			session.setAttribute("quest",q);
			session.setAttribute("currQ", exam.currentQuestion);
        }
			
			String action=request.getParameter("action");
			
			String radio=request.getParameter("answer");
			int selectedRadio=-1;
			if(exam.selections.containsKey(exam.currentQuestion) && radio!=null){
				exam.selections.put(exam.currentQuestion, selectedRadio);
			}
			if("1".equals(radio))
			{
				selectedRadio=1;
				exam.selections.put(exam.currentQuestion, selectedRadio);
				System.out.println("You selected "+selectedRadio);
				request.getSession().setAttribute("answerMap",exam.selections);
			}
			else if("2".equals(radio))
			{
				selectedRadio=2;
				exam.selections.put(exam.currentQuestion, selectedRadio);
				System.out.println("You selected "+selectedRadio);
				request.getSession().setAttribute("answerMap",exam.selections);
			}
			else if("3".equals(radio))
			{
				selectedRadio=3;
				exam.selections.put(exam.currentQuestion, selectedRadio);
				System.out.println("You selected "+selectedRadio);
				request.getSession().setAttribute("answerMap",exam.selections);
			}
			else if("4".equals(radio))
			{
				selectedRadio=4;
				exam.selections.put(exam.currentQuestion, selectedRadio);
				System.out.println("You selected "+selectedRadio);
				request.getSession().setAttribute("answerMap",exam.selections);
			}
			
						
			if("Next".equals(action)){
				String empId = (String)session.getAttribute("empId");
				String skill = (String)session.getAttribute("technology");
				String voucherId = (String)session.getAttribute("voucherId");
				if(exam.currentQuestion == 14){
					finish=true;				
					UserResultModel userResultModel=exam.calculateResult(exam);
					if(empId == null || skill == null){
						mv = new ModelAndView("result");
						return mv;
					}else{
						System.out.println("empId.....& technology"+empId +" "+ skill);
						regService.saveUserResult(empId,skill,userResultModel,voucherId);
						request.getSession().setAttribute("empId",null);
						request.getSession().setAttribute("technology",null);
						request.getSession().setAttribute("voucherId", null);
						request.getSession().setAttribute("duration", null);
						request.getSession().setAttribute("currentExam",null);
						//request.getRequestDispatcher("/webapp/jsp/result.jsp").forward(request,response);
						mv = new ModelAndView("result");
						}
					
				}else{
					if(empId == null || skill == null){
						mv = new ModelAndView("result");
						return mv;
					}else{
						String timerCurrent = (String)request.getParameter("timer");
						exam.currentQuestion++;
						exam.setQuestion(exam.currentQuestion);
					    QuizQuestion q=exam.questionList.get(exam.currentQuestion);	
					  	session.setAttribute("quest",q);
					  	session.setAttribute("currQ", exam.currentQuestion);
					  	session.setAttribute("duration", timerCurrent);
					  	request.getSession().setAttribute("answerMap",exam.selections);
					}
				}
			}
			else if("Previous".equals(action))
			{   
				String empId = (String)session.getAttribute("empId");
				String skill = (String)session.getAttribute("technology");
				if(empId == null || skill == null){
					mv = new ModelAndView("result");
					return mv;
				}else{
					System.out.println("You clicked Previous Button");
					String timerCurrent = (String)request.getParameter("timer");
					exam.currentQuestion--;
					exam.setQuestion(exam.currentQuestion);
				    QuizQuestion q=exam.questionList.get(exam.currentQuestion);	
					session.setAttribute("quest",q);
					session.setAttribute("currQ", exam.currentQuestion);
					session.setAttribute("duration", timerCurrent);
					request.getSession().setAttribute("answerMap",exam.selections);
				}
			}
			else if("FinishExam".equals(action))
			{   
				finish=true;				
				UserResultModel userResultModel=exam.calculateResult(exam);				
				String empId = (String)session.getAttribute("empId");
				String skill = (String)session.getAttribute("technology");
				String voucherId = (String)session.getAttribute("voucherId");
				System.out.println("empId.....& technology"+empId +" "+ skill);
				regService.saveUserResult(empId,skill,userResultModel,voucherId);
				request.getSession().setAttribute("empId",null);
				request.getSession().setAttribute("technology",null);
				request.getSession().setAttribute("duration", null);
				request.getSession().setAttribute("voucherId", null);
				request.getSession().setAttribute("currentExam",null);
				request.getSession().setAttribute("answerMap",null);
				request.getSession().setAttribute("currQ", null);
				
				//request.getRequestDispatcher("/webapp/jsp/result.jsp").forward(request,response);
				mv = new ModelAndView("result");
				
			}
						
		if(finish!=true){
			mv = new ModelAndView("exam");
			
		//request.getRequestDispatcher("/webapp/jsp/exam.jsp").forward(request,response);
		}
		return mv;
	}

}
