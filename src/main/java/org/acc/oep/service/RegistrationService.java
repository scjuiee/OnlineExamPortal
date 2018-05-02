package org.accenture.oep.service;

import java.util.List;

import org.accenture.oep.bean.CandidateScoreProfile;
import org.accenture.oep.bean.RegistrationModel;
import org.accenture.oep.bean.UserResultModel;

public interface RegistrationService {

	/**
	 * @param registrationModel
	 * @return
	 */
	public int save(RegistrationModel registrationModel);

	/**
	 * @param voucherId
	 * @param skill
	 * @return
	 */
	public boolean validate(String voucherId, String skill);
	
	
	/**
	 * @param empId
	 * @param skill
	 * @param model
	 * @param voucherId
	 * @return
	 */
	public int saveUserResult(String empId,String skill,UserResultModel model,String voucherId);
	
	/**
	 * @param user
	 * @param pwd
	 * @return
	 */
	public boolean validateUser(String user,String pwd);
	
	/**
	 * @param examVoucherId
	 * @param empId
	 * @return
	 */
	public List<CandidateScoreProfile> fetchUserResults(String examVoucherId,String empId);
	
	/**
	 * @param examVoucherId
	 * @param skill
	 * @param update
	 * @return
	 */
	public int updateVoucher(String examVoucherId, String skill, String update);

}
