package org.accenture.oep.dao;

import java.util.List;

import org.accenture.oep.bean.CandidateScoreProfile;
import org.accenture.oep.bean.RegistrationModel;
import org.accenture.oep.bean.UserResultModel;

public interface RegistrationDAO {

	/**
	 * @param model
	 * @return
	 */
	public int registerUser(RegistrationModel model);

	/**
	 * @param voucher
	 * @param skill
	 * @return
	 */
	public boolean validateVoucher(String voucher, String skill);
	
	
	/**
	 * @param empId
	 * @param skill
	 * @param model
	 * @param voucherId
	 * @return
	 */
	public int saveUserResult(String empId,String skill,UserResultModel model,String voucherId);
	
	/**
	 * @param empId
	 * @param password
	 * @return
	 */
	public boolean validateUser(String empId,String password);
	
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
