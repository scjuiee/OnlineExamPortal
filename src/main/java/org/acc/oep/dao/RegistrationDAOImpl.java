package org.accenture.oep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.accenture.oep.bean.CandidateScoreProfile;
import org.accenture.oep.bean.RegistrationModel;
import org.accenture.oep.bean.UserResultModel;
import org.accenture.oep.util.DBConnectionUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class RegistrationDAOImpl implements RegistrationDAO {

	private static final Logger logger = LogManager.getLogger(RegistrationDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.accenture.oep.dao.RegistrationDAO#registerUser(org.accenture.oep.bean
	 * .RegistrationModel)
	 */
	public int registerUser(RegistrationModel model) {

		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String INSERT_USER = "INSERT INTO OEP_CANDIDATE(USER_FIRSTNAME,USER_LASTNAME,USER_EMPID,USER_SKILL) VALUES(?,?,?,?)";

		try {
			connection = DBConnectionUtil.getDBConnection();

			// Step 2.B: Creating JDBC Statement
			preparedStatement = connection.prepareStatement(INSERT_USER);
			preparedStatement.setString(1, model.getFirstName());
			preparedStatement.setString(2, model.getLastName());
			preparedStatement.setString(3, model.getEmpId());
			preparedStatement.setString(4, model.getTechnology());

			result = preparedStatement.executeUpdate();

		} catch (Exception ex) {
			logger.error("Exception occurred while creating candidate details");
		} finally {
			// Step 3: Closing database connection
			try {
				if (null != connection) {
					// cleanup resources, once after processing
					preparedStatement.close();
					// and then finally close connection
					connection.close();
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.accenture.oep.dao.RegistrationDAO#validateVoucher(java.lang.String,
	 * java.lang.String)
	 */
	public boolean validateVoucher(String voucher, String skill) {

		boolean validateFlg = false;
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		String VALIDATE_VOUCHER = "SELECT * FROM OEP_EXAM_VOUCHER WHERE VOUCHER_CODE='" + voucher + "' AND SKILL='"
				+ skill + "' AND IS_ACTIVE='Y'";

		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			System.out.println(VALIDATE_VOUCHER);
			rs = statement.executeQuery(VALIDATE_VOUCHER);
			System.out.println();
			if (rs != null && rs.next()) {
				logger.debug("Voucher is active");
				validateFlg = true;
			} else {
				logger.debug("Voucher is not active");
			}

		} catch (Exception exception) {
			logger.error("Exception occurred while validating the voucher details" + exception);
			exception.printStackTrace();
		} finally {
			try {
				if (null != connection) {
					// cleanup resources, once after processing
					statement.close();
					// and then finally close connection
					connection.close();
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}
		return validateFlg;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.accenture.oep.dao.RegistrationDAO#saveUserResult(java.lang.String,
	 * java.lang.String, org.accenture.oep.bean.UserResultModel,
	 * java.lang.String)
	 */
	public int saveUserResult(String empId, String skill, UserResultModel model, String voucherId) {
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String UPDATE_RESULT = "UPDATE OEP_CANDIDATE SET USER_RESULT=? ,USER_ANSWERS=? ,EXAM_VOUCHER=? ,EXAM_TAKEN= ? WHERE USER_EMPID=? AND USER_SKILL=?";
		Date currentExamTakenDt = new java.util.Date();
		try {
			connection = DBConnectionUtil.getDBConnection();

			// Step 2.B: Creating JDBC Statement
			preparedStatement = connection.prepareStatement(UPDATE_RESULT);
			preparedStatement.setInt(1, Integer.parseInt(model.getUserScore()));
			preparedStatement.setString(2, model.getUserAnswers());
			preparedStatement.setString(3, voucherId);
			preparedStatement.setDate(4, new java.sql.Date(currentExamTakenDt.getTime()));
			preparedStatement.setString(5, empId);
			preparedStatement.setString(6, skill);

			result = preparedStatement.executeUpdate();
			System.out.println("Result Scored for the user " + empId + "updated wirth score " + model.getUserScore());

		} catch (Exception ex) {
			logger.error("Exception occurred while creating candidate details");
		} finally {
			// Step 3: Closing database connection
			try {
				if (null != connection) {
					// cleanup resources, once after processing
					preparedStatement.close();
					// and then finally close connection
					connection.close();
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.accenture.oep.dao.RegistrationDAO#validateUser(java.lang.String,
	 * java.lang.String)
	 */
	public boolean validateUser(String empId, String password) {
		boolean flag = false;
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		String VALIDATE_USER = "SELECT * FROM OEP_ADMIN WHERE USERNAME='" + empId + "' AND PASSWORD='" + password + "'";
		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			System.out.println(VALIDATE_USER);
			rs = statement.executeQuery(VALIDATE_USER);
			System.out.println();
			if (rs != null && rs.next()) {
				logger.debug("user id valid");
				flag = true;
			} else {
				logger.debug("user id is not valid");
			}

		} catch (Exception exception) {
			logger.error("Exception occurred while validating the voucher details" + exception);
			exception.printStackTrace();
		} finally {
			try {
				if (null != connection) {
					// cleanup resources, once after processing
					statement.close();
					// and then finally close connection
					connection.close();
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}

		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.accenture.oep.dao.RegistrationDAO#fetchUserResults(java.lang.String,
	 * java.lang.String)
	 */
	public List<CandidateScoreProfile> fetchUserResults(String examVoucherId, String empId) {
		List<CandidateScoreProfile> scoreList = new ArrayList<CandidateScoreProfile>();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		String FETCH_CANDID_SCORE_PART2="";
		if (examVoucherId != null && !examVoucherId.isEmpty()) {
			FETCH_CANDID_SCORE_PART2=" WHERE EXAM_VOUCHER='" + examVoucherId + "'";			
		}else{
			examVoucherId = "";
		}
		if (empId != null && !empId.isEmpty()) {
			if(!FETCH_CANDID_SCORE_PART2.isEmpty()){
				FETCH_CANDID_SCORE_PART2 = FETCH_CANDID_SCORE_PART2 + " AND USER_EMPID='" +empId+"'";
			}else{
				FETCH_CANDID_SCORE_PART2 = " WHERE USER_EMPID='" +empId+"'";
			}
		}
		
		String FETCH_CANDID_SCORE_PART1 = "SELECT USER_FIRSTNAME,USER_LASTNAME,USER_RESULT,USER_ANSWERS FROM OEP_CANDIDATE ";
		String FETCH_CANDID_SCORE= FETCH_CANDID_SCORE_PART1 + FETCH_CANDID_SCORE_PART2;
		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			System.out.println(FETCH_CANDID_SCORE);
			rs = statement.executeQuery(FETCH_CANDID_SCORE);
			System.out.println();
			while (rs.next()) {
				System.out.println("record exist");
				String fName = rs.getString("USER_FIRSTNAME");
				String lName = rs.getString("USER_LASTNAME");
				if (fName == null) {
					fName = "";
				}
				if (lName == null) {
					lName = "";
				}
				String name = fName + " " + lName;
				String score = rs.getString("USER_RESULT");
				String answers = rs.getString("USER_ANSWERS");
				
				System.out.println("Name:.."+name);

				CandidateScoreProfile scoreProfile = new CandidateScoreProfile();
				scoreProfile.setUserScore(score);
				scoreProfile.setEmpId(empId);
				scoreProfile.setExamVoucher(examVoucherId);
				scoreProfile.setName(name);
				scoreProfile.setUserAnswers(answers);

				scoreList.add(scoreProfile);
			}

		} catch (Exception exception) {
			logger.error("Exception occurred while validating the voucher details" + exception);
			exception.printStackTrace();
		} finally {
			try {
				if (null != connection) {
					// cleanup resources, once after processing
					statement.close();
					// and then finally close connection
					connection.close();
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}
		return scoreList;
	}

	/* (non-Javadoc)
	 * @see org.accenture.oep.dao.RegistrationDAO#updateVoucher(java.lang.String, java.lang.String, java.lang.String)
	 */
	public int updateVoucher(String examVoucherId, String skill, String update){
		
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String QUERY = "";
		if("Y".equalsIgnoreCase(update)){
		 
			QUERY = "INSERT INTO OEP_EXAM_VOUCHER(IS_ACTIVE,VOUCHER_CODE,SKILL) VALUES(?,?,?)";
		 
		}else if("N".equalsIgnoreCase(update)){
			
			QUERY = "UPDATE OEP_EXAM_VOUCHER SET IS_ACTIVE=? WHERE VOUCHER_CODE=? AND SKILL= ?";
		}
		

		try {
			connection = DBConnectionUtil.getDBConnection();
			
			// Step 2.B: Creating JDBC Statement
			preparedStatement = connection.prepareStatement(QUERY);
			
			preparedStatement.setString(1, update);
			preparedStatement.setString(2, examVoucherId);
			preparedStatement.setString(3, skill);
			

			result = preparedStatement.executeUpdate();

		} catch (Exception ex) {
			logger.error("Exception occurred while creating/updating voucher details");
		} finally {
			// Step 3: Closing database connection
			try {
				if (null != connection) {
					// cleanup resources, once after processing
					preparedStatement.close();
					// and then finally close connection
					connection.close();
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}
		return result;
		
	}
}
