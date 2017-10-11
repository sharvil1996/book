package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.ForgotPasswordBean;
import com.util.DBConnection;

public class ForgotPasswordDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	boolean result = false;

	public boolean insert(ForgotPasswordBean forgotPasswordBean) {

		conn = DBConnection.getConnection();

		if (conn != null) {
			String insertSQL = "insert into forgotPassword(forgotPasswordId,customerId) values(?,?)";

			try {
				pstmt = conn.prepareStatement(insertSQL);

				pstmt.setString(1, forgotPasswordBean.getForgotPasswordId());
				pstmt.setString(2, forgotPasswordBean.getCustomerId());

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					result = true;

				} else {

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

		return result;
	}

	public List<ForgotPasswordBean> list() {

		List<ForgotPasswordBean> listOfForgotPassword = new ArrayList<ForgotPasswordBean>();
		conn = DBConnection.getConnection();

		if (conn != null) {

			String selectSQL = "select * from forgotPassword order by forgotPasswordId";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();
				ForgotPasswordBean forgotPasswordBean = null;

				while (rs.next()) {

					forgotPasswordBean = new ForgotPasswordBean();
					forgotPasswordBean.setForgotPasswordId(rs.getString("forgotPasswordId"));
					forgotPasswordBean.setCustomerFname(rs.getString("customerFname"));
					forgotPasswordBean.setCustomerLname(rs.getString("customerLname"));
					listOfForgotPassword.add(forgotPasswordBean);

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
		return listOfForgotPassword;
	}

	public boolean delete(String forgotPasswordId) {

		conn = DBConnection.getConnection();

		if (conn != null) {
			String deleteSQL = "delete from forgotPassword where forgotPasswordId=?";

			try {
				pstmt = conn.prepareStatement(deleteSQL);

				pstmt.setString(1, forgotPasswordId);

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					result = true;

				} else {
					result = false;
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
		return result;
	}

	public boolean checkUser(String customerId) {

		conn = DBConnection.getConnection();

		if (conn != null) {

			String selectSQL = "select * from forgotPassword where customerId=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				pstmt.setString(1, customerId);
				
				rs = pstmt.executeQuery();

				while (rs.next()) {

					return true;
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
		return false;
	}


	public boolean updateForgotPasswordId(String forgotPasswordId,String customerId) {

		conn = DBConnection.getConnection();

		if (conn != null) {

			String updateSQL = "update forgotPassword set forgotPasswordId=? where customerId=?";
			try {
				pstmt = conn.prepareStatement(updateSQL);

				pstmt.setString(1,forgotPasswordId);
				pstmt.setString(2, customerId);
				

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					result = true;

				} else {
					result = false;
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
		return result;
	}

	
	
	public boolean checkAuthentication(String forgotPasswordId,String customerId) {

		conn = DBConnection.getConnection();

		if (conn != null) {

			String selectSQL = "select * from forgotPassword where forgotPasswordId=? and customerId=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				pstmt.setString(1, forgotPasswordId);
				pstmt.setString(2, customerId);
				
				rs = pstmt.executeQuery();

				while (rs.next()) {

					return true;
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
		return false;
	}
	

}
