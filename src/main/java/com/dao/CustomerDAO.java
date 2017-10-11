package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.CustomerBean;
import com.bean.SubCategoryBean;
import com.util.DBConnection;

public class CustomerDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String ajaxResult = null;

	private boolean result = false;

	public boolean insert(CustomerBean customerBean) {

		conn = DBConnection.getConnection();

		if (conn != null) {
			String insertSQL = "INSERT INTO customer(customerFname,"
					+ " customerLname,customerAddress,customerMobileNo,customerMobileNo1,customerEmail,"
					+ "customerPwd,customerPincode,customerId)"
					+ " values(?,?,?,?,?,?,?,?,?)";

			try {
				pstmt = conn.prepareStatement(insertSQL);

				pstmt.setString(1, customerBean.getCustomerFname());
				pstmt.setString(2, customerBean.getCustomerLname());
				pstmt.setString(3, customerBean.getCustomerAddress());
				pstmt.setString(4, customerBean.getCustomerMobileNo());
				pstmt.setString(5, customerBean.getCustomerMobileNo1());
				pstmt.setString(6, customerBean.getCustomeEmail());
				pstmt.setString(7, customerBean.getCustomerPwd());
				pstmt.setString(8, customerBean.getCustomerPincode());
				pstmt.setString(9, customerBean.getCustomerId());

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


	public boolean update(CustomerBean customerBean) {
		conn = DBConnection.getConnection();
		if (conn != null) {
			String updateSQL = "UPDATE customer set customerFname=?,"
					+ "customerLname=?,customerAddress=?,customerMobileNo=?,customerMobileNo1=?,customerEmail=?,"
					+ "customerPincode=? where customerId=?";

			try {
				pstmt = conn.prepareStatement(updateSQL);
				pstmt.setString(1, customerBean.getCustomerFname());
				pstmt.setString(2, customerBean.getCustomerLname());
				pstmt.setString(3, customerBean.getCustomerAddress());
				pstmt.setString(4, customerBean.getCustomerMobileNo());
				pstmt.setString(5, customerBean.getCustomerMobileNo1());
				pstmt.setString(6, customerBean.getCustomeEmail());
				pstmt.setString(7, customerBean.getCustomerPincode());
				pstmt.setString(8, customerBean.getCustomerId());

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

	public boolean checkUserExists(String customerEmail) {

		conn = DBConnection.getConnection();

		if (conn != null) {
			String selectSQL = "Select * from customer where customerEmail=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				pstmt.setString(1, customerEmail);

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

	public String getcategory(String cateId) {
		List<SubCategoryBean> listOFSubCategorys = new ArrayList<SubCategoryBean>();
		conn = DBConnection.getConnection();

		if (conn != null) {
			String selectSQL = "Select * from subcategory where categoryId=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);
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
		output = output.substring(0, output.length() - 1);
		output += "~";
		for (int i = 0; i < listOFSubCategorys.size(); i++)
			output += listOFSubCategorys.get(i).getSubCategoryId() + "|";
		output = output.substring(0, output.length() - 1);
		return output;
	}

	public String ajaxCustomerDelete(String customerId) {

		conn = DBConnection.getConnection();

		if (conn != null) {

			try {
				pstmt = conn
						.prepareStatement("delete from suggestion where customerId=?");
				pstmt.setString(1, customerId);
				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {
					ajaxResult = "1";

				} else {
					ajaxResult = "0";
				}

				pstmt = conn
						.prepareStatement("delete from cart where customerId=?");
				pstmt.setString(1, customerId);
				int rowsAffectedCart = pstmt.executeUpdate();
				if (rowsAffectedCart > 0) {
					ajaxResult = "0";

				} else {
					ajaxResult = "0";
				}

				pstmt = conn
						.prepareStatement("delete from wishlist where customerId=?");
				pstmt.setString(1, customerId);
				int rowsAffectedWishlist = pstmt.executeUpdate();
				if (rowsAffectedWishlist > 0) {
					ajaxResult = "1";

				} else {
					ajaxResult = "0";
				}

				pstmt = conn
						.prepareStatement("delete from orders where customerId=?");
				pstmt.setString(1, customerId);
				int rowsAffectedOrders = pstmt.executeUpdate();
				if (rowsAffectedOrders > 0) {
					ajaxResult = "1";

				} else {
					ajaxResult = "0";
				}

				pstmt = conn
						.prepareStatement("delete from customer where customerId=?");
				pstmt.setString(1, customerId);
				int rowsAffectedCustomer = pstmt.executeUpdate();
				if (rowsAffectedCustomer > 0) {
					ajaxResult = "1";

				} else {
					ajaxResult = "0";
				}

			} catch (SQLException e) {
				ajaxResult = "0";
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

	public String ajaxCustomerUpdate(String customerId, String isAdmin) {
		conn = DBConnection.getConnection();
		if (conn != null) {
			String updateSQL = "UPDATE customer set customerIsAdmin=? where customerId=?";

			try {
				pstmt = conn.prepareStatement(updateSQL);

				pstmt.setString(1, isAdmin);
				pstmt.setString(2, customerId);

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

	public String listOfCustomerId() {

		conn = DBConnection.getConnection();
		String customerId = new String();
		if (conn != null) {

			String selectSQL = "select * from customer";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					customerId += rs.getString("customerId") + "|";

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
		return customerId.substring(0, customerId.length() - 1);
	}

	public String listOfCustomerName() {

		conn = DBConnection.getConnection();
		String customerName = new String();
		if (conn != null) {

			String selectSQL = "select * from customer";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					customerName += rs.getString("customerFname") + " "
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
		return customerName.substring(0, customerName.length() - 1);
	}

	public String listOfCustomerEmail() {

		conn = DBConnection.getConnection();
		String customerEmail = new String();
		if (conn != null) {

			String selectSQL = "select * from customer";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					customerEmail += rs.getString("customerEmail") + "|";

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
		return customerEmail.substring(0, customerEmail.length() - 1);
	}

	public String listOfCustomerMobileNo1() {

		conn = DBConnection.getConnection();
		String customerMobileNo1 = new String();
		if (conn != null) {

			String selectSQL = "select * from customer";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					customerMobileNo1 += rs.getString("customerMobileNo") + "|";

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
		return customerMobileNo1.substring(0, customerMobileNo1.length() - 1);
	}

	public String listOfCustomerMobileNo2() {

		conn = DBConnection.getConnection();
		String customerMobileNo2 = new String();
		if (conn != null) {

			String selectSQL = "select * from customer";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					customerMobileNo2 += rs.getString("customerMobileNo1")
							+ "|";

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
		return customerMobileNo2.substring(0, customerMobileNo2.length() - 1);
	}

	public String listOfCustomerAddress() {

		conn = DBConnection.getConnection();
		String customerAddress = new String();
		if (conn != null) {

			String selectSQL = "select * from customer";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					customerAddress += rs.getString("customerAddress") + "|";

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
		return customerAddress.substring(0, customerAddress.length() - 1);
	}

	public String listOfCustomerPinCode() {

		conn = DBConnection.getConnection();
		String customerPinCode = new String();
		if (conn != null) {

			String selectSQL = "select * from customer,pincode where customer.customerPincode=pincode.pincodeId";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					customerPinCode += rs.getString("pincodeNumber") + "|";

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
		return customerPinCode.substring(0, customerPinCode.length() - 1);
	}

	public String listOfCustomerIsAdmin() {

		conn = DBConnection.getConnection();
		String customerPinCode = new String();
		if (conn != null) {

			String selectSQL = "select * from customer";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					customerPinCode += rs.getString("customerIsAdmin") + "|";

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
		return customerPinCode.substring(0, customerPinCode.length() - 1);
	}

	public String noOfCustomer() {

		conn = DBConnection.getConnection();
		int cnt = 0;
		if (conn != null) {

			String selectSQL = "select * from customer";
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
	
	public String getCustomerId(String customerEmailId) {

		conn = DBConnection.getConnection();
		if (conn != null) {

			String selectSQL = "select * from customer where customerEmail=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, customerEmailId);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					return (rs.getString("customerId"));
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

	public boolean changePassword(String password,String customerId) {

		conn = DBConnection.getConnection();

		if (conn != null) {
			String insertSQL = "update customer set customerPwd=? where customerId=?";

			try {
				pstmt = conn.prepareStatement(insertSQL);

				pstmt.setString(1,password);
				pstmt.setString(2,customerId);

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
	public String getPassword(String customerId) {

		conn = DBConnection.getConnection();
		if (conn != null) {

			String selectSQL = "select * from customer where customerId=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, customerId);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					return (rs.getString("customerPwd"));
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

	public boolean updatePassword(String password, String customerId) {
		conn = DBConnection.getConnection();
		if (conn != null) {
			String updateSQL = "UPDATE customer set CustomerPwd=?"
					+ " where customerId=?";

			try {
				pstmt = conn.prepareStatement(updateSQL);
				pstmt.setString(1, password);
				pstmt.setString(2, customerId);

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					result = true;
				} else {
					result = false;
				}
			} catch (SQLException e) {
				result = false;
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

}