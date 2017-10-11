package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.CustomerBean;
import com.util.DBConnection;

public class LoginDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public Object checkAuthentication(String password, String emailId) {
		conn = DBConnection.getConnection();

		if (conn != null) {
			try {
				String userSearchSQL = "select * from customer where customerEmail=?";
				pstmt = conn.prepareStatement(userSearchSQL);
				pstmt.setString(1, emailId);
				rs = pstmt.executeQuery();
				if (rs.next() && rs.getString("customerPwd").equals(password)
						&& rs.getString("customerIsAdmin").equals("n")) {

					return "customer";

				} else {

					String adminSearchSQL = "select * from customer where customerEmail=?";
					pstmt = conn.prepareStatement(adminSearchSQL);
					pstmt.setString(1, emailId);
					rs = pstmt.executeQuery();

					if (rs.next()
							&& rs.getString("customerPwd").equals(password)
							&& rs.getString("customerIsAdmin").equals("y")) {

						return "admin";
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return null;
	}

	public CustomerBean setCustomerBean(String password, String emailId) {
		conn = DBConnection.getConnection();
		CustomerBean customerbean = new CustomerBean();
		if (conn != null) {
			try {
				String userSearchSQL = "select * from customer,pincode where customerEmail=? and customer.customerPincode=pincode.pincodeId";
				pstmt = conn.prepareStatement(userSearchSQL);
				pstmt.setString(1, emailId);
				rs = pstmt.executeQuery();
				if (rs.next() && rs.getString("customerPwd").equals(password)
						&& rs.getString("customerIsAdmin").equals("n")) {
					customerbean = new CustomerBean();

					customerbean.setCustomerPinnumber(rs
							.getString("pincodeNumber"));
					customerbean.setCustomerId(rs.getString("customerId"));
					customerbean.setCustomeEmail(rs.getString("customerEmail"));
					customerbean.setCustomerAddress(rs
							.getString("customerAddress"));
					customerbean
							.setCustomerFname(rs.getString("customerFname"));
					customerbean
							.setCustomerLname(rs.getString("customerLname"));
					customerbean.setCustomerPwd(rs.getString("customerPwd"));
					customerbean.setCustomerMobileNo(rs
							.getString("customerMobileNo"));
					customerbean.setCustomerMobileNo1(rs
							.getString("customerMobileNo1"));
					customerbean.setCustomerPincode(rs
							.getString("customerPincode"));
					customerbean.setCustomerIsAdmin(rs
							.getString("customerIsAdmin"));


					return customerbean;

				} else {
					String adminSearchSQL = "select * from customer,pincode where customerEmail=? and customer.customerPincode=pincode.pincodeId";
					pstmt = conn.prepareStatement(adminSearchSQL);
					pstmt.setString(1, emailId);
					rs = pstmt.executeQuery();

					while (rs.next()
							&& rs.getString("customerPwd").equals(password)
							&& rs.getString("customerIsAdmin").equals("y")) {

						customerbean = new CustomerBean();
						customerbean.setCustomerPinnumber(rs
								.getString("pincodeNumber"));
						customerbean.setCustomerId(rs.getString("customerId"));
						customerbean.setCustomeEmail(rs
								.getString("customerEmail"));
						customerbean.setCustomerAddress(rs
								.getString("customerAddress"));
						customerbean.setCustomerFname(rs
								.getString("customerFname"));
						customerbean.setCustomerLname(rs
								.getString("customerLname"));
						customerbean
								.setCustomerPwd(rs.getString("customerPwd"));
						customerbean.setCustomerMobileNo(rs
								.getString("customerMobileNo"));
						customerbean.setCustomerMobileNo1(rs
								.getString("customerMobileNo1"));
						customerbean.setCustomerPincode(rs
								.getString("customerPincode"));
						customerbean.setCustomerIsAdmin(rs
								.getString("customerIsAdmin"));

						return customerbean;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return customerbean;
	}

}
