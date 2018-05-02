package org.accenture.oep.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.accenture.oep.bean.CandidateScoreProfile;
import org.accenture.oep.bean.RegistrationModel;
import org.accenture.oep.service.RegistrationService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	private static final Logger logger = LogManager.getLogger(LoginController.class);

	@Autowired
	RegistrationService regService;

	@Autowired
	RegistrationModel regModel;

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLoginPage(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("inside login controller");
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("errorMsg", "");
		return mav;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView showSearchPage(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("inside login controller");
		ModelAndView mav = new ModelAndView("search");
		mav.addObject("errorMsg", "");
		return mav;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView validateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {

		logger.debug("inside login");
		ModelAndView mav = null;
		// String userId = userDetails.getUser();
		// String pwd = userDetails.getPassword();
		boolean flag = false;

		String userId = request.getParameter("username");
		String pwd = request.getParameter("pass");

		if ((userId == null || userId.isEmpty()) || (pwd == null || pwd.isEmpty())) {
			mav = new ModelAndView("login");
			mav.addObject("errorMsg", "Please enter all the * marked fields");
			return mav;
		} else {
			flag = regService.validateUser(userId, pwd);
			if (flag) {
				mav = new ModelAndView("search");
				return mav;
			} else {
				mav = new ModelAndView("login");
				mav.addObject("errorMsg", "UserId/Password is invalid");
				return mav;
			}
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doduplicateregister", method = RequestMethod.POST)
	public ModelAndView registerUser(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav;
		HttpSession session = request.getSession(false);
		logger.debug("inside reisterUser");

		String fName = request.getParameter("firstName");
		String lName = request.getParameter("lastName");
		String empId = request.getParameter("empId");
		String skill = request.getParameter("technology");

		regModel.setFirstName(fName);
		regModel.setLastName(lName);
		regModel.setEmpId(empId);
		regModel.setTechnology(skill);

		int result = regService.save(regModel);

		logger.debug("result.." + result);

		if (result != 0) {
			session.setAttribute("user", empId);
			mav = new ModelAndView("examVoucher");

		} else {
			mav = new ModelAndView("register");
			mav.addObject("errorMsg", "User Registration Failed. System is under maintenance. Please try later");
		}

		return mav;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/fetchCandidateScore", method = RequestMethod.POST)
	public ModelAndView fetchUserResults(HttpServletRequest request, HttpServletResponse response) throws SQLException {

		logger.debug("inside fetchUserResults");
		ModelAndView mav = null;
		List<CandidateScoreProfile> scoreList;
		String responseStr = "";
		String examVoucherId = request.getParameter("examVoucherId");
		String empId = request.getParameter("empId");

		if ((empId == null || empId.isEmpty()) && (examVoucherId == null || examVoucherId.isEmpty())) {
			responseStr = "0 results found";
		} else {
			scoreList = regService.fetchUserResults(examVoucherId, empId);
			if (scoreList != null && scoreList.size() > 0) {
				responseStr = "<table class='table table-striped table-bordered table-hover'><thead>"
						+ "<tr><th>Candidate Name</th><th>Score</th></tr></thead><tbody>";
				String respBody = "";
				for (int index = 0; index < scoreList.size(); index++) {
					CandidateScoreProfile scoreProfile = (CandidateScoreProfile) scoreList.get(index);
					String name = scoreProfile.getName();
					System.out.println("Name obtained..." + name);
					String score = scoreProfile.getUserScore();
					if (respBody.length() == 0) {
						respBody = "<tr><td>" + name + "</td><td>" + score + "</td></tr>";
					} else {
						respBody = respBody + "<tr><td>" + name + "</td><td>" + score + "</td></tr>";
					}
				}
				responseStr = responseStr + respBody + "</tbody></table>";

			}
		}
		System.out.println("responseStr obtained..." + responseStr);
		mav = new ModelAndView("search");
		mav.addObject("responseStr", responseStr);
		return mav;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getVoucherActivate", method = RequestMethod.GET)
	public ModelAndView showVoucherActivation(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("inside showVoucherActivation");
		ModelAndView mav = new ModelAndView("updateVoucher");
		mav.addObject("errorMsg", "");
		return mav;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/getVoucherActivate", method = RequestMethod.POST)
	public ModelAndView updateVoucher(HttpServletRequest request, HttpServletResponse response) throws SQLException {

		logger.debug("inside updateVoucher");
		ModelAndView mav = null;

		String responseStr = "";
		String examVoucherId = request.getParameter("examVoucherId");
		String skill = request.getParameter("technology");
		
		String isActivate = request.getParameter("activate");
		String isDeactivate = request.getParameter("deactivate");
		
		System.out.println("isActivate..."+isActivate+"isDeactivate..."+isDeactivate);
		int result = 0;

		if ((skill == null || skill.isEmpty()) && (examVoucherId == null || examVoucherId.isEmpty())) {
			responseStr = "unable to update voucher";
		} else {
			if ("Y".equalsIgnoreCase(isActivate)) {
				// scoreList = regService.fetchUserResults(examVoucherId,empId);
				result = regService.updateVoucher(examVoucherId, skill, "Y");
			} else if ("Y".equalsIgnoreCase(isDeactivate)) {
				result = regService.updateVoucher(examVoucherId, skill, "N");
			}
			
			System.out.println("result obtained while updating voucher..getClass()."+ result);
			
			if(result == 1){
			responseStr =  "The Voucher Updated Successfully";
			}else{
				responseStr = "Unable to update voucher";
			}

		}
		System.out.println("responseStr obtained..." + responseStr);
		mav = new ModelAndView("updateVoucher");
		mav.addObject("responseStr", responseStr);
		return mav;
	}

}
