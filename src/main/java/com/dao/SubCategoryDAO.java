package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.SubCategoryBean;
import com.util.DBConnection;
import com.util.GenrateMathodsUtils;

public class SubCategoryDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String ajaxResult = null;

	public List<SubCategoryBean> list() {

		List<SubCategoryBean> listOfSubCategory = new ArrayList<SubCategoryBean>();
		conn = DBConnection.getConnection();

		if (conn != null) {

			String selectSQL = "select * from subcategory,category "
					+ " where subcategory.categoryId=category.categoryId ";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();
				SubCategoryBean subCategoryBean = null;

				while (rs.next()) {

					subCategoryBean = new SubCategoryBean();
					subCategoryBean.setCategoryId(rs.getString("categoryId"));
					subCategoryBean.setSubCategoryId(rs
							.getString("subCategoryId"));
					subCategoryBean.setSubCategoryName(rs
							.getString("subCategoryName"));
					subCategoryBean.setCategoryName(rs
							.getString("categoryName"));
					listOfSubCategory.add(subCategoryBean);

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
		return listOfSubCategory;
	}

	
	public String ajaxSubCategoryInsert(String subCategoryName,
			String categoryId) {

		conn = DBConnection.getConnection();

		if (conn != null) {

			String subCategoryId = GenrateMathodsUtils.getRandomString(15);
			String insertSQL = "insert into subcategory(subCategoryName,subCategoryId,categoryId) values(?,?,?)";

			try {
				pstmt = conn.prepareStatement(insertSQL.toLowerCase());

				pstmt.setString(1, subCategoryName);
				pstmt.setString(2, subCategoryId);
				pstmt.setString(3, categoryId);

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

	public String ajaxSubCategoryDelete(String subCategoryId) {

		conn = DBConnection.getConnection();

		if (conn != null) {
			String deleteSQL = "delete from subcategory where subCategoryId=?";

			try {
				pstmt = conn.prepareStatement(deleteSQL.toLowerCase());

				pstmt.setString(1, subCategoryId);

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

	public String ajaxSubCategoryUpdate(String subCategoryName,
			String subCategoryId) {

		conn = DBConnection.getConnection();
		if (conn != null) {
			String updateSQL = "update subcategory set subCategoryName=? where subCategoryId=?";

			try {
				pstmt = conn.prepareStatement(updateSQL.toLowerCase());

				pstmt.setString(1, subCategoryName);
				pstmt.setString(2, subCategoryId);

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

	public String getSubCategory(String cateId) {
		List<SubCategoryBean> listOFSubCategorys = new ArrayList<SubCategoryBean>();
		conn = DBConnection.getConnection();

		if (conn != null) {
			String selectSQL = "Select * from subcategory where categoryId=?";
			try {
				pstmt = conn.prepareStatement(selectSQL.toLowerCase());
				pstmt.setString(1, cateId);
				rs = pstmt.executeQuery();
				SubCategoryBean subCategorybean = null;
				while (rs.next()) {
					subCategorybean = new SubCategoryBean();

					subCategorybean.setCategoryId(rs.getString("categoryId"));
					subCategorybean.setSubCategoryId(rs
							.getString("subCategoryId"));
					subCategorybean.setSubCategoryName(rs
							.getString("subCategoryName"));

					listOFSubCategorys.add(subCategorybean);
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
		for (int i = 0; i < listOFSubCategorys.size(); i++)
			output += listOFSubCategorys.get(i).getSubCategoryName() + "|";
		if (output != "")
			output = output.substring(0, output.length() - 1);
		output += "~";
		for (int i = 0; i < listOFSubCategorys.size(); i++)
			output += listOFSubCategorys.get(i).getSubCategoryId() + "|";
		if (output != "")
			output = output.substring(0, output.length() - 1);
		return output;
	}

}
