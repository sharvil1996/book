package com.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.util.DBConnection;
import com.util.GenrateMathodsUtils;

public class WishlistDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	boolean result = false;
	private String ajaxResult = null;

	public String ajaxWishlistInsert(String productId, String customerId) {

		conn = DBConnection.getConnection();

		if (conn != null) {
			String wishlistId = GenrateMathodsUtils.getRandomString(15);
			String insertSQL = "insert into wishlist(productId,customerId,wishlistId) values(?,?,?)";

			try {
				pstmt = conn.prepareStatement(insertSQL);

				pstmt.setString(1, productId);
				pstmt.setString(2, customerId);
				pstmt.setString(3, wishlistId);

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

	public String ajaxWishlistDelete(String productId, String customerId) {

		conn = DBConnection.getConnection();

		if (conn != null) {
			String deleteSQL = "delete from wishlist where productId=? and customerId=?";

			try {
				pstmt = conn.prepareStatement(deleteSQL);

				pstmt.setString(1, productId);
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

	public String ajaxWishlistCheck(String productId, String customerId) {

		conn = DBConnection.getConnection();
		ajaxResult = "0";
		if (conn != null) {
			String selectSQL = "select * from wishlist where productId=? and customerId=?";

			try {
				pstmt = conn.prepareStatement(selectSQL);

				pstmt.setString(1, productId);
				pstmt.setString(2, customerId);

				rs = pstmt.executeQuery();
				while (rs.next())
					ajaxResult = (Integer.parseInt(ajaxResult) + 1) + "";
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

	public String listOfWishlistProductId() {

		conn = DBConnection.getConnection();
		String productId = new String();
		if (conn != null) {

			String selectSQL = "select * from wishlist,product where wishlist.productId=product.productId";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					productId += rs.getString("productId") + "|";

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
		if (!productId.isEmpty())
			return productId.substring(0, productId.length() - 1);
		return productId;
	}

	public String listOfWishlistProductId(String customerId) {

		conn = DBConnection.getConnection();
		String productId = new String();
		if (conn != null) {

			String selectSQL = "select * from wishlist,product where wishlist.productId=product.productId and customerId=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, customerId);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					productId += rs.getString("productId") + "|";

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
		if (!productId.isEmpty())
			return productId.substring(0, productId.length() - 1);
		return productId;
	}

	public String listOfWishlistProductName() {

		conn = DBConnection.getConnection();
		String productName = new String();
		if (conn != null) {

			String selectSQL = "select * from wishlist,product where wishlist.productId=product.productId";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					productName += rs.getString("productName") + "|";

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
		if (!productName.isEmpty())
			return productName.substring(0, productName.length() - 1);
		return productName;
	}

	public String listOfWishlistIsProductImage() {

		conn = DBConnection.getConnection();
		String isProductImage = new String();
		if (conn != null) {

			String selectSQL = "select * from wishlist,product where wishlist.productId=product.productId";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					isProductImage += rs.getString("productImage") + "|";

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
		if (!isProductImage.isEmpty())
			return isProductImage.substring(0, isProductImage.length() - 1);
		return isProductImage;
	}

	public String listOfWishlistId() {

		conn = DBConnection.getConnection();
		String wishlistId = new String();
		if (conn != null) {

			String selectSQL = "select * from wishlist,product where wishlist.productId=product.productId";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					wishlistId += rs.getString("wishlistId") + "|";

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
		if (!wishlistId.isEmpty())
			return wishlistId.substring(0, wishlistId.length() - 1);
		return wishlistId;
	}

	public String listOfWishlistProductName(String customerId) {

		conn = DBConnection.getConnection();
		String productName = new String();
		if (conn != null) {

			String selectSQL = "select * from wishlist,product where wishlist.productId=product.productId and customerId=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, customerId);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					productName += rs.getString("productName") + "|";

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
		if (!productName.isEmpty())
			return productName.substring(0, productName.length() - 1);
		return productName;
	}

	public String listOfWishlistIsProductImage(String customerId) {

		conn = DBConnection.getConnection();
		String isProductImage = new String();
		if (conn != null) {

			String selectSQL = "select * from wishlist,product where wishlist.productId=product.productId and customerId=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, customerId);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					isProductImage += rs.getString("productImage") + "|";

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
		if (!isProductImage.isEmpty())
			return isProductImage.substring(0, isProductImage.length() - 1);
		return isProductImage;
	}

	public String listOfWishlistId(String customerId) {

		conn = DBConnection.getConnection();
		String wishlistId = new String();
		if (conn != null) {

			String selectSQL = "select * from wishlist,product where wishlist.productId=product.productId and customerId=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, customerId);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					wishlistId += rs.getString("wishlistId") + "|";

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
		if (!wishlistId.isEmpty())
			return wishlistId.substring(0, wishlistId.length() - 1);
		return wishlistId;
	}

}
