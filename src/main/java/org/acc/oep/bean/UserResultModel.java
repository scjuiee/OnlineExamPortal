package org.accenture.oep.bean;

public class UserResultModel {
	
	private static String userAnswers;
	private static String userScore;
	
	public static String getUserAnswers() {
		return userAnswers;
	}
	public static void setUserAnswers(String userAnswers) {
		UserResultModel.userAnswers = userAnswers;
	}
	public static String getUserScore() {
		return userScore;
	}
	public static void setUserScore(String userScore) {
		UserResultModel.userScore = userScore;
	}
	
}
