package com.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.SuggestionBean;
import com.util.DBConnection;

public class SuggestionDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private boolean result = false;
	private String ajaxResult=null;
	
	public boolean insert(SuggestionBean suggestionBean) {
		conn = DBConnection.getConnection();

		if (conn != null) {
			String insertSQL = "insert into suggestion(suggestionDesc,suggestionId,customerId) values(?,?,?)";

			try {
				pstmt = conn.prepareStatement(insertSQL);

				pstmt.setString(1, suggestionBean.getSuggestionDesc());
				pstmt.setString(2, suggestionBean.getSuggestionId());
				pstmt.setString(3, suggestionBean.getCustomerId());

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

	public String ajaxSuggetionDelete(String suggestionId) {

		conn = DBConnection.getConnection();

		if (conn != null) {
			String deleteSQL = "delete from suggestion where suggestionId=? and suggestionIsAnswered='y'";

			try {
				pstmt = conn.prepareStatement(deleteSQL);

				pstmt.setString(1, suggestionId);

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					ajaxResult = "1";

				} else {
					ajaxResult = "0";
				}
			} catch (SQLException e) {
				e.printStackTrace();
				ajaxResult = "0";
			} finally {

				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return ajaxResult;
	}

	public boolean update(SuggestionBean suggestion) {

		conn = DBConnection.getConnection();
		if (conn != null) {
			String updateSQL = "update suggestion set suggestionDesc=?,customerId=? where suggestionId=?";

			try {
				pstmt = conn.prepareStatement(updateSQL);

				pstmt.setString(1, suggestion.getSuggestionDesc());
				pstmt.setString(2, suggestion.getCustomerId());
				pstmt.setString(3, suggestion.getSuggestionId());

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

	public String getSuggestion(String customerId) {
		List<SuggestionBean> listOfSuggestion = new ArrayList<SuggestionBean>();
		conn = DBConnection.getConnection();

		if (conn != null) {
			String selectSQL = "Select * from suggestion where CustomerId=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, customerId);
				rs = pstmt.executeQuery();
				SuggestionBean suggestionBean = null;
				while (rs.next()) {
					suggestionBean = new SuggestionBean();

					suggestionBean.setCustomerId(rs.getString("customerId"));
					suggestionBean
							.setSuggestionId(rs.getString("suggestionId"));
					suggestionBean.setSuggestionDesc(rs
							.getString("suggestionDesc"));
					suggestionBean.setSuggestionIsAnswered(rs
							.getString("suggestionIsAnswered"));

					listOfSuggestion.add(suggestionBean);
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
		String output = "";
		for (int i = 0; i < listOfSuggestion.size(); i++)
			output += listOfSuggestion.get(i).getSuggestionId() + "|";
		output = output.substring(0, output.length() - 1);
		output += "~";
		for (int i = 0; i < listOfSuggestion.size(); i++)
			output += listOfSuggestion.get(i).getSuggestionIsAnswered() + "|";
		output = output.substring(0, output.length() - 1);
		output += "~";
		for (int i = 0; i < listOfSuggestion.size(); i++)
			output += listOfSuggestion.get(i).getSuggestionDesc() + "|";
		output = output.substring(0, output.length() - 1);
		return output;
	}

	public String listOfCustomerName() {

		conn = DBConnection.getConnection();
		String customerId = new String();
		if (conn != null) {

			String selectSQL = "select * from suggestion,customer where customer.customerId=suggestion.customerId";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					customerId += rs.getString("customerFname") + " "
							+ rs.getString("customerLname") + "|";

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
		if (!customerId.isEmpty())
			return customerId.substring(0, customerId.length() - 1);
		return customerId;
	}

	public String listOfSuggestionId() {

		conn = DBConnection.getConnection();
		String suggestionId = new String();
		if (conn != null) {

			String selectSQL = "select * from suggestion";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					suggestionId += rs.getString("suggestionId") + "|";

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
		if (!suggestionId.isEmpty())
			return suggestionId.substring(0, suggestionId.length() - 1);
		return suggestionId;
	}

	public String listOfSuggestionDesc() {

		conn = DBConnection.getConnection();
		String suggestionDesc = new String();
		if (conn != null) {

			String selectSQL = "select * from suggestion";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					suggestionDesc += rs.getString("suggestionDesc") + "|";

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
		if (!suggestionDesc.isEmpty())
			return suggestionDesc.substring(0, suggestionDesc.length() - 1);
		return suggestionDesc;
	}

	public String listOfsuggestionIsAnswered() {

		conn = DBConnection.getConnection();
		String suggestionIsAnswered = new String();
		if (conn != null) {

			String selectSQL = "select * from suggestion";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					suggestionIsAnswered += rs
							.getString("suggestionIsAnswered") + "|";

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
		if (!suggestionIsAnswered.isEmpty())
			return suggestionIsAnswered.substring(0,
					suggestionIsAnswered.length() - 1);
		return suggestionIsAnswered;
	}

	public boolean updateIsAnswered(String suggestionId) {

		conn = DBConnection.getConnection();
		if (conn != null) {
			String updateSQL = "update suggestion set suggestionIsAnswered='y' where suggestionId=?";

			try {
				pstmt = conn.prepareStatement(updateSQL);
				pstmt.setString(1, suggestionId);

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

	public String getCustomerEmailId(String suggestionId) {

		conn = DBConnection.getConnection();
		String emailId = new String();
		if (conn != null) {

			String selectSQL = "select customerEmail from suggestion,customer where suggestion.customerId=customer.customerId and suggestionId=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				pstmt.setString(1, suggestionId);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					emailId = rs.getString("customerEmail");
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
		return emailId;
	}

	public String noOfSuggestionNotAnswered() {

		conn = DBConnection.getConnection();
		int cnt = 0;
		if (conn != null) {

			String selectSQL = "select * from suggestion where suggestionIsAnswered='n'";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					cnt++;

				}

			} catch (SQLException e) {
				e.printStackTrace();
				cnt = 0;
			} finally {

				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return cnt + "";
	}

	public String noOfSuggestion() {

		conn = DBConnection.getConnection();
		int cnt = 0;
		if (conn != null) {

			String selectSQL = "select * from suggestion";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					cnt++;

				}

			} catch (SQLException e) {
				e.printStackTrace();
				cnt = 0;
			} finally {

				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return cnt + "";
	}

}
