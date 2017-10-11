package com.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.ProductBean;
import com.util.DBConnection;

public class ProductDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private boolean result = false;
	private String ajaxResult = null;

	public boolean insert(ProductBean productBean) {

		conn = DBConnection.getConnection();

		if (conn != null) {
			String insertSQL = "insert into product(productId,productImage,productName,productAuthor,productPublisher,productDesc,productStock,productPrice,productPurchases,subCategoryId,productDiscount) values(?,?,?,?,?,?,?,?,?,?,?)";

			try {
				pstmt = conn.prepareStatement(insertSQL);

				pstmt.setString(1, productBean.getProductId());
				pstmt.setString(2, productBean.getProductImage());
				pstmt.setString(3, productBean.getProductName());
				pstmt.setString(4, productBean.getProductAuthor());
				pstmt.setString(5, productBean.getProductPublisher());
				pstmt.setString(6, productBean.getProductDesc());
				pstmt.setString(7, productBean.getProductStock());
				pstmt.setString(8, productBean.getProductPrice());
				pstmt.setString(9, productBean.getProductPurchases());
				pstmt.setString(10, productBean.getSubCategoryId());
				pstmt.setString(11, productBean.getProductDiscount());

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

	public ProductBean getByPK(String productId) {

		conn = DBConnection.getConnection();
		ProductBean product = new ProductBean();
		if (conn != null) {

			String selectSQL = "select * from product,subcategory,category "
					+ " where product.subCategoryId=subCategory.subCategoryId "
					+ " and subCategory.categoryId=category.categoryId and productId=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				pstmt.setString(1, productId);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					product.setProductAuthor(rs.getString("productAuthor"));
					product.setProductDesc(rs.getString("productDesc"));
					product.setProductId(rs.getString("productId"));
					product.setProductImage(rs.getString("productImage"));
					product.setProductName(rs.getString("productName"));
					product.setProductPrice(rs.getString("productPrice"));
					product.setProductPublisher(rs
							.getString("productPublisher"));
					product.setProductPurchases(rs
							.getString("productPurchases"));
					product.setProductStock(rs.getString("productStock"));
					product.setSubCategoryId(rs.getString("subCategoryId"));
					product.setSubCategoryName(rs.getString("subCategoryName"));
					product.setCategoryId(rs.getString("categoryId"));
					product.setCategoryName(rs.getString("categoryName"));
					product.setProductDiscount(rs.getString("productDiscount"));
					product.setLink(rs.getString("link"));				
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
		return product;
	}

	public boolean update(ProductBean product) {

		conn = DBConnection.getConnection();
		if (conn != null) {
			String updateSQL = "update product set productName=?, productAuthor=?,productPublisher=?,productDesc=?,productStock=?,productPrice=?,productPurchases=?,subCategoryId=?,productDiscount=? where productId=?";

			try {
				pstmt = conn.prepareStatement(updateSQL);

				// pstmt.setString(1, product.getProductImage());
				pstmt.setString(1, product.getProductName());
				pstmt.setString(2, product.getProductAuthor());
				pstmt.setString(3, product.getProductPublisher());
				pstmt.setString(4, product.getProductDesc());
				pstmt.setString(5, product.getProductStock());
				pstmt.setString(6, product.getProductPrice());
				pstmt.setString(7, product.getProductPurchases());
				pstmt.setString(8, product.getSubCategoryId());
				pstmt.setString(9, product.getProductDiscount());
				pstmt.setString(10, product.getProductId());

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

	public String ajaxProductDelete(String productId, File file) {

		conn = DBConnection.getConnection();

		if (conn != null) {
			String deleteSQL = "delete from product where productId=?";
			try {
				pstmt = conn.prepareStatement(deleteSQL);
				pstmt.setString(1, productId);
				int rowsAffected = pstmt.executeUpdate();
				result = file.delete();
				if (rowsAffected > 0) {
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

	public String listOfProductId() {

		conn = DBConnection.getConnection();
		String productId = new String();
		if (conn != null) {

			String selectSQL = "select * from product";
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
		return productId.substring(0, productId.length() - 1);
	}

	public String listOfProductName() {

		conn = DBConnection.getConnection();
		String productName = new String();
		if (conn != null) {

			String selectSQL = "select * from product";
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
		return productName.substring(0, productName.length() - 1);
	}

	public String listOfProductDiscount() {

		conn = DBConnection.getConnection();
		String productDiscount = new String();
		if (conn != null) {

			String selectSQL = "select * from product";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					productDiscount += rs.getString("productDiscount") + "|";

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
		return productDiscount.substring(0, productDiscount.length() - 1);
	}

	public String listOfProductAuthor() {

		conn = DBConnection.getConnection();
		String productAuthor = new String();
		if (conn != null) {

			String selectSQL = "select * from product";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					productAuthor += rs.getString("productAuthor") + "|";

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
		return productAuthor.substring(0, productAuthor.length() - 1);
	}

	public String listOfProductPublisher() {

		conn = DBConnection.getConnection();
		String productPublisher = new String();
		if (conn != null) {

			String selectSQL = "select * from product";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					productPublisher += rs.getString("productPublisher") + "|";

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
		return productPublisher.substring(0, productPublisher.length() - 1);
	}

	public String listOfProductDesc() {

		conn = DBConnection.getConnection();
		String productDesc = new String();
		if (conn != null) {

			String selectSQL = "select * from product";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					productDesc += rs.getString("productDesc") + "|";

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
		return productDesc.substring(0, productDesc.length() - 1);
	}

	public String listOfProductStock() {

		conn = DBConnection.getConnection();
		String productStock = new String();
		if (conn != null) {

			String selectSQL = "select * from product";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					productStock += rs.getString("productStock") + "|";

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
		return productStock.substring(0, productStock.length() - 1);
	}

	public String listOfProductPrice() {

		conn = DBConnection.getConnection();
		String productPrice = new String();
		if (conn != null) {

			String selectSQL = "select * from product";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					productPrice += rs.getString("productPrice") + "|";

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
		return productPrice.substring(0, productPrice.length() - 1);
	}

	public String listOfProductPurchases() {

		conn = DBConnection.getConnection();
		String productPurchases = new String();
		if (conn != null) {

			String selectSQL = "select * from product";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					productPurchases += rs.getString("productPurchases") + "|";

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
		return productPurchases.substring(0, productPurchases.length() - 1);
	}

	public String listOfProductImage() {

		conn = DBConnection.getConnection();
		String productImage = new String();
		if (conn != null) {

			String selectSQL = "select * from product";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					productImage += rs.getString("productImage") + "|";

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
		return productImage.substring(0, productImage.length() - 1);
	}

	public String listOfProductSubCategoryName() {

		conn = DBConnection.getConnection();
		String ProductSubCategoryName = new String();
		if (conn != null) {

			String selectSQL = "select * from product,subcategory where product.subCategoryId = subcategory.subCategoryId";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					ProductSubCategoryName += rs.getString("subCategoryName")
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
		return ProductSubCategoryName.substring(0,
				ProductSubCategoryName.length() - 1);
	}

	public String listOfLinks() {

		conn = DBConnection.getConnection();
		String ProductSubCategoryName = new String();
		if (conn != null) {

			String selectSQL = "select * from product";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					ProductSubCategoryName += rs.getString("link")
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
		return ProductSubCategoryName.substring(0,
				ProductSubCategoryName.length() - 1);
	}
	
	public String listOfProductCategoryName() {

		conn = DBConnection.getConnection();
		String ProductCategoryName = new String();
		if (conn != null) {

			String selectSQL = "select * from product,subcategory,category where product.subCategoryId = subcategory.subCategoryId and subcategory.categoryId=category.categoryId";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					ProductCategoryName += rs.getString("categoryName") + "|";

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
		return ProductCategoryName.substring(0,
				ProductCategoryName.length() - 1);
	}

	public String noOfProduct() {

		conn = DBConnection.getConnection();
		int cnt = 0;
		if (conn != null) {

			String selectSQL = "select * from product";
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

	public ProductBean bookDetails(String productId) {

		conn = DBConnection.getConnection();
		ProductBean product = new ProductBean();
		if (conn != null) {

			String selectSQL = "select * from product where productId=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				pstmt.setString(1, productId);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					product.setProductAuthor(rs.getString("productAuthor"));
					product.setProductDesc(rs.getString("productDesc"));
					product.setProductId(rs.getString("productId"));
					product.setProductImage(rs.getString("productImage"));
					product.setProductName(rs.getString("productName"));
					product.setProductPrice(rs.getString("productPrice"));
					product.setProductPublisher(rs
							.getString("productPublisher"));
					product.setProductPurchases(rs
							.getString("productPurchases"));
					product.setProductStock(rs.getString("productStock"));
					product.setProductDiscount(rs.getString("productDiscount"));
					product.setLink(rs.getString("link"));
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
		return product;
	}

	public boolean isExistsProductId(String productId) {

		conn = DBConnection.getConnection();
		if (conn != null) {

			String selectSQL = "select * from product where productId=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, productId);
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

	public String listOfProductDiscount(String productId) {

		conn = DBConnection.getConnection();
		String productDiscount = new String();
		if (conn != null) {

			String selectSQL = "select * from product where productId=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, productId);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					productDiscount += rs.getString("productDiscount") + "|";

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
		return productDiscount.substring(0, productDiscount.length() - 1);
	}

	public Integer totalPrice(String productId) {

		conn = DBConnection.getConnection();
		int cnt = 0;
		if (conn != null) {

			String selectSQL = "select * from product where productId=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, productId);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					cnt = rs.getInt("productPrice");

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
		return cnt;
	}

	public String ajaxProductDetail(String productId) {

		conn = DBConnection.getConnection();
		String productIds = "1";
		if (conn != null) {

			String selectSQL = "select * from product where productId=?";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, productId);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					productIds = rs.getString("productId") + "|"
							+ rs.getString("productName") + "|"
							+ rs.getString("productPrice") + "|"
							+ rs.getString("productDiscount");

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
		return productIds;
	}

}
