package com.bean;

public class SuggestionBean 
{
	private String customerId;
	private String suggestionId;
	private String suggestionDesc;
	private String suggestionIsAnswered;
	private String customerFname;
	private String customerLname;	
	private String customerEmail;
	
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
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
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getSuggestionId() {
		return suggestionId;
	}
	public void setSuggestionId(String suggestionId) {
		this.suggestionId = suggestionId;
	}
	public String getSuggestionDesc() {
		return suggestionDesc;
	}
	public void setSuggestionDesc(String suggestionDesc) {
		this.suggestionDesc = suggestionDesc;
	}
	public String getSuggestionIsAnswered() {
		return suggestionIsAnswered;
	}
	public void setSuggestionIsAnswered(String suggestionIsAnswered) {
		this.suggestionIsAnswered = suggestionIsAnswered;
	}
	
	
	
}
