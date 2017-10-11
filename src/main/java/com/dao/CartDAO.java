package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DBConnection;
import com.util.GenrateMathodsUtils;

public class CartDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	boolean result = false;
	private String ajaxResult = null;

	public String ajaxCartInsert(String customerId, String productId,
			String quantity) {

		conn = DBConnection.getConnection();

		if (conn != null) {
			String productQty = null;
			ajaxResult = "0";
			String selectSQL = "Select * from cart where customerId=? and productId=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				pstmt.setString(1, customerId);
				pstmt.setString(2, productId);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					productQty = rs.getString("quantity");
					String cartId = rs.getString("cartId");
					int qty = Integer.parseInt(productQty)
							+ Integer.parseInt(quantity);
					pstmt = conn
							.prepareStatement("update cart set quantity=? where cartId=? and productId=? and customerId=?");

					pstmt.setString(1, qty + "");
					pstmt.setString(2, cartId);
					pstmt.setString(3, productId);
					pstmt.setString(4, customerId);

					int rowsAffected = pstmt.executeUpdate();

					if (rowsAffected > 0) {
						ajaxResult = "1";

					} else {
						ajaxResult = "0";

					}

				}

				if (ajaxResult.equals("0")) {

					String insertSQL = "insert into cart(cartId,productId,customerId,quantity) values(?,?,?,?)";
					String cartId = GenrateMathodsUtils.getRandomString(15);

					pstmt = conn.prepareStatement(insertSQL);

					pstmt.setString(1, cartId);
					pstmt.setString(2, productId);
					pstmt.setString(3, customerId);
					pstmt.setString(4, quantity);

					int rowsAffected = pstmt.executeUpdate();

					if (rowsAffected > 0) {
						ajaxResult = "1";

					} else {
						ajaxResult = "0";

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

		return ajaxResult;
	}

	public String ajaxCartDelete(String cartId) {

		conn = DBConnection.getConnection();

		if (conn != null) {
			String deleteSQL = "delete from cart where cartId=?";

			try {
				pstmt = conn.prepareStatement(deleteSQL);

				pstmt.setString(1, cartId);

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

	public String ajaxCartUpdate(String productId, String customerId,
			String quantity) {

		conn = DBConnection.getConnection();
		if (conn != null) {
			String updateSQL = "update cart set quantity=? where productId=? and customerId=?";

			try {
				pstmt = conn.prepareStatement(updateSQL);

				pstmt.setString(2, productId);
				pstmt.setString(3, customerId);
				pstmt.setString(1, quantity);

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

	public String cartList(String customerId) {

		conn = DBConnection.getConnection();
		String productName = new String();
		String productId = new String();
		String productQty = new String();
		String productPrice = new String();
		String cartId = new String();
		String finalOutput = "0";
		String prodDiscount = new String();
		if (conn != null) {

			String selectSQL = "Select * from cart,product WHERE customerId=? and product.productId=cart.productId";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				pstmt.setString(1, customerId);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					productId += rs.getString("cart.productId") + "|";
					productQty += rs.getString("quantity") + "|";
					productPrice += rs.getString("productPrice") + "|";
					productName += rs.getString("productName") + "|";
					cartId += rs.getString("cartId") + "|";
					prodDiscount += rs.getString("productDiscount") + "|";
				}

				if (!productId.isEmpty()) {
					finalOutput = productId
							.substring(0, productId.length() - 1) + "~";
					finalOutput += productQty.substring(0,
							productQty.length() - 1) + "~";
					finalOutput += productPrice.substring(0,
							productPrice.length() - 1)
							+ "~";
					finalOutput += productName.substring(0,
							productName.length() - 1)
							+ "~";
					finalOutput += cartId.substring(0, cartId.length() - 1)
							+ "~";
					finalOutput += prodDiscount.substring(0,
							prodDiscount.length() - 1);
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
		return finalOutput;
	}
}
