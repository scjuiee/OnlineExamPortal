package org.accenture.oep.service;

import java.util.ArrayList;
import java.util.List;

import org.accenture.oep.bean.CandidateScoreProfile;
import org.accenture.oep.bean.RegistrationModel;
import org.accenture.oep.bean.UserResultModel;
import org.accenture.oep.dao.RegistrationDAO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	private static final Logger logger = LogManager.getLogger(RegistrationServiceImpl.class);

	@Autowired
	RegistrationDAO registrationDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.accenture.oep.service.RegistrationService#save(org.accenture.oep.bean
	 * .RegistrationModel)
	 */
	public int save(RegistrationModel model) {
		int result = 0;
		result = registrationDAO.registerUser(model);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.accenture.oep.service.RegistrationService#validate(java.lang.String,
	 * java.lang.String)
	 */
	public boolean validate(String voucherId, String skill) {
		boolean validateFlag = false;
		validateFlag = registrationDAO.validateVoucher(voucherId, skill);
		return validateFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.accenture.oep.service.RegistrationService#saveUserResult(java.lang.
	 * String, java.lang.String, org.accenture.oep.bean.UserResultModel,
	 * java.lang.String)
	 */
	public int saveUserResult(String empId, String skill, UserResultModel model, String voucherId) {
		int result = 0;
		result = registrationDAO.saveUserResult(empId, skill, model, voucherId);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.accenture.oep.service.RegistrationService#validateUser(java.lang.
	 * String, java.lang.String)
	 */
	public boolean validateUser(String empId, String password) {
		boolean flag = false;
		flag = registrationDAO.validateUser(empId, password);
		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.accenture.oep.service.RegistrationService#fetchUserResults(java.lang.
	 * String, java.lang.String)
	 */
	public List<CandidateScoreProfile> fetchUserResults(String examVoucherId, String empId) {
		List<CandidateScoreProfile> scoreList = new ArrayList<CandidateScoreProfile>();
		scoreList = registrationDAO.fetchUserResults(examVoucherId, empId);
		return scoreList;
	}
	
	
	/* (non-Javadoc)
	 * @see org.accenture.oep.service.RegistrationService#updateVoucher(java.lang.String, java.lang.String, java.lang.String)
	 */
	public int updateVoucher(String examVoucherId, String skill, String update){
		int result=0;
		result = registrationDAO.updateVoucher(examVoucherId, skill, update);
		return result;
	}
}
