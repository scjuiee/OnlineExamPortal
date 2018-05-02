package org.accenture.oep.bean;

import org.springframework.stereotype.Component;

@Component
public class RegistrationModel {

	private static String firstName;
	private static String lastName;
	private static String empId;
	private static String technology;
	
	
		
	public static String getFirstName() {
		return firstName;
	}
	public static void setFirstName(String firstName) {
		RegistrationModel.firstName = firstName;
	}
	public static String getLastName() {
		return lastName;
	}
	public static void setLastName(String lastName) {
		RegistrationModel.lastName = lastName;
	}
	public static String getEmpId() {
		return empId;
	}
	public static void setEmpId(String empId) {
		RegistrationModel.empId = empId;
	}
	public static String getTechnology() {
		return technology;
	}
	public static void setTechnology(String technology) {
		RegistrationModel.technology = technology;
	}
	
	
	
}
