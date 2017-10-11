package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.bean.CategoryBean;
import com.util.DBConnection;
import com.util.GenrateMathodsUtils;

public class CategoryDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	boolean result = false;
	private String ajaxResult = null;


	public List<CategoryBean> list() {

		List<CategoryBean> listOfCategory = new ArrayList<CategoryBean>();
		conn = DBConnection.getConnection();

		if (conn != null) {

			String selectSQL = "select * from category order by categoryId";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();
				CategoryBean categoryBean = null;

				while (rs.next()) {

					categoryBean = new CategoryBean();
					categoryBean.setCategoryId(rs.getString("categoryId"));
					categoryBean.setCategoryName(rs.getString("categoryName"));
					listOfCategory.add(categoryBean);

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
		return listOfCategory;
	}

	
	public String ajaxCategoryInsert(String categoryName) {

		conn = DBConnection.getConnection();

		if (conn != null) {

			String insertSQL = "insert into category(categoryName,categoryId) values(?,?)";
			String categoryId = GenrateMathodsUtils.getRandomString(15);

			try {
				pstmt = conn.prepareStatement(insertSQL);

				pstmt.setString(1, categoryName);
				pstmt.setString(2, categoryId);

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					ajaxResult = "1";

				} else {
					ajaxResult = "0";

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

		return ajaxResult;
	}

	public String ajaxCategoryDelete(String categoryId) {

		conn = DBConnection.getConnection();

		if (conn != null) {
			String deleteSQL = "delete from category where categoryId=?";

			try {
				pstmt = conn.prepareStatement(deleteSQL);

				pstmt.setString(1, categoryId);

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					ajaxResult = "1";
				} else {
					ajaxResult = "0";
				}
			} catch (SQLException e) {
				e.printStackTrace();
				ajaxResult="0";
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

	public String ajaxCategoryUpdate(String categoryId, String categoryName) {

		conn = DBConnection.getConnection();
		if (conn != null) {
			String updateSQL = "update category set categoryName=? where categoryId=?";

			try {
				pstmt = conn.prepareStatement(updateSQL);

				pstmt.setString(1, categoryName);
				pstmt.setString(2, categoryId);

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					ajaxResult = "1";
				} else {
					ajaxResult = "0";
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
		return ajaxResult;
	}

	public String listOfSubCategoryId() {

		conn = DBConnection.getConnection();
		String subCategoryId = new String();
		if (conn != null) {

			String selectSQL = "select * from subcategory";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();
			

				while (rs.next()) {

					subCategoryId += rs.getString(1) + "|";

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
		return subCategoryId.substring(0,subCategoryId.length()-1);
	}

	
	public String listOfCategoryName() {

		conn = DBConnection.getConnection();
		String categoryName = new String();
		if (conn != null) {

			String selectSQL = "select * from category";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();
			

				while (rs.next()) {

					categoryName += rs.getString("categoryName") + "|";

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
		return categoryName.substring(0,categoryName.length()-1);
	}

	
	public String listOfCategoryId() {

		conn = DBConnection.getConnection();
		String categoryId = new String();
		if (conn != null) {

			String selectSQL = "select * from category";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();
			

				while (rs.next()) {

					categoryId += rs.getString("categoryId") + "|";

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
		return categoryId.substring(0,categoryId.length()-1);
	}

	public String listOfSubCategoryName() {

		conn = DBConnection.getConnection();
		String subCategoryName = new String();
		if (conn != null) {

			String selectSQL = "select * from subcategory,category where category.categoryId=subCategory.categoryId";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();
			

				while (rs.next()) {

					subCategoryName += rs.getString(1) + "|";

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
		return subCategoryName.substring(0,subCategoryName.length()-1);
	}

	
	public String noOfCategory() {

		conn = DBConnection.getConnection();
		int cnt = 0;
		if (conn != null) {

			String selectSQL = "select * from category";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();
			

				while (rs.next()) {

					cnt++;

				}

			} catch (SQLException e) {
				e.printStackTrace();
				cnt=0;
			} finally {

				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return cnt+"";
	}

	
}
