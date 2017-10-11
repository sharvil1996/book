package com.bean;

public class ForgotPasswordBean {

	private String forgotPasswordId;
	private String customerId;

	private String customerFname;
	private String customerLname;

	public String getForgotPasswordId() {
		return forgotPasswordId;
	}

	public void setForgotPasswordId(String forgotPasswordId) {
		this.forgotPasswordId = forgotPasswordId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerFname() {
		return customerFname;
	}

	public void setCustomerFname(String customerFname) {
		this.customerFname = customerFname;
	}

	public String getCustomerLname() {
		return customerLname;
	}

	public void setCustomerLname(String customerLname) {
		this.customerLname = customerLname;
	}

}
