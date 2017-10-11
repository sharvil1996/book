package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.util.DBConnection;

public class LogDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	boolean result = false;

	public long isAlreadyExists() {

		conn = DBConnection.getConnection();

		long resultDate = 0;

		if (conn != null) {

			String checkSQL = "select * from log order by logDate desc";

			try {
				pstmt = conn.prepareStatement(checkSQL);

				rs = pstmt.executeQuery();

				if (rs.next()) {

					resultDate = rs.getLong("logDate");

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

		return resultDate;
	}

	public boolean insertLogFirstTime(long date) {

		conn = DBConnection.getConnection();

		if (conn != null) {

			String checkSQL = "insert into log(logDate,logUserCnt) values(?,?)";

			try {
				pstmt = conn.prepareStatement(checkSQL);

				pstmt.setLong(1, date);
				pstmt.setString(2, "1");

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					result = true;

				} else {
					result = false;

				}

			} catch (SQLException e) {
				result=false;
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

	public boolean insertLogAfterFirstTime(long date) {

		conn = DBConnection.getConnection();
		String userCnt = "0";
		if (conn != null) {

			String checkSQL = "select * from log where logDate=?";

			try {
				pstmt = conn.prepareStatement(checkSQL);
				pstmt.setLong(1, date);
				rs = pstmt.executeQuery();

				if (rs.next()) {

					userCnt = rs.getString("logUserCnt");

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			int cnt = Integer.parseInt(userCnt);
			cnt++;
			String checkSQL1 = "update log set logUserCnt=? where logDate=?";

			try {
				pstmt = conn.prepareStatement(checkSQL1);

				pstmt.setString(1, cnt + "");
				pstmt.setLong(2, date);

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

	public String listOfLogDates() {
		conn = DBConnection.getConnection();

		String returnDate = new String();
		if (conn != null) {

			String selectSQL = "select * from log order by logDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					returnDate += rs.getString("logDate") + "|";

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
		if (!returnDate.isEmpty())
			return returnDate.substring(0, returnDate.length() - 1);
		return returnDate;
	}

	public String listOfUserCnt() {

		conn = DBConnection.getConnection();
		String userCnt = new String();

		if (conn != null) {

			String selectSQL = "select * from log order by logDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					userCnt += rs.getString("logUserCnt") + "|";

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
		return userCnt.substring(0, userCnt.length() - 1);
	}

	public String noOfLog() {

		conn = DBConnection.getConnection();
		int cnt = 0;

		if (conn != null) {

			String selectSQL = "select * from log";
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
