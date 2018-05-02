package org.accenture.oep.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
public class RegistrationController {

	private static final Logger logger = LogManager.getLogger(RegistrationController.class);

	@Autowired
	RegistrationModel registrationModel;

	@Autowired
	RegistrationService regService;

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/launchregister", method = RequestMethod.POST)
	public ModelAndView showRegisterPage(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("inside login controller");
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("errorMsg", "");
		return mav;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doregister", method = RequestMethod.POST)
	public ModelAndView doRegister(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("inside registratioon controller doRegister method");
		ModelAndView mav = null;
		HttpSession session = request.getSession(false);
		try {
			int result = 0;

			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String empId = request.getParameter("empId");
			String technology = request.getParameter("technology");

			if ((firstName == null || firstName.isEmpty()) || (lastName == null || lastName.isEmpty())
					|| (empId == null || empId.isEmpty()) || (technology == null || technology.isEmpty())) {
				System.out.println("fields are empty");
				mav = new ModelAndView("register");
				mav.addObject("errorMsg", "Please enter all the * marked fields");
				return mav;
			}

			registrationModel.setEmpId(empId);
			registrationModel.setFirstName(firstName);
			registrationModel.setLastName(lastName);
			registrationModel.setTechnology(technology);

			result = regService.save(registrationModel);
			if (result == 0) {
				mav = new ModelAndView("register");
				mav.addObject("errorMsg", "User Registration Failed. System is under maintenance. Please try later");
			} else {
				session.setAttribute("empId", empId);
				session.setAttribute("technology", technology);
				mav = new ModelAndView("examVoucher");
			}

		} catch (Exception ex) {
			logger.error("Exception occurred while doing registration..." + ex);
		}
		return mav;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public ModelAndView voucherValidate(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("inside registratioon controller voucherValidate method");
		ModelAndView mav = null;
		HttpSession session = request.getSession(false);
		try {
			String voucherId = request.getParameter("voucherId");
			String skill = (String) session.getAttribute("technology");

			logger.debug("technology..." + skill);
			logger.debug("voucherId..." + voucherId);

			if (null != voucherId && !voucherId.isEmpty()) {
				boolean flag = regService.validate(voucherId, skill);
				if (flag) {
					session.setAttribute("voucherId", voucherId);
					logger.debug("voucherValidate successful");
					mav = new ModelAndView("helpBeforeTest");
				} else {
					logger.debug("voucherValidate failure");
					mav = new ModelAndView("examVoucher");
					mav.addObject("errorMsg", "No Exam is created with this voucher ID");
				}
			} else {
				logger.debug("voucher id is null");
				mav = new ModelAndView("examVoucher");
				mav.addObject("errorMsg", "Please Enter Proper Voucher Id");
			}
		} catch (Exception ex) {
			logger.error("Exception occurred while validating voucherID ..." + ex);
		}
		return mav;
	}

}
