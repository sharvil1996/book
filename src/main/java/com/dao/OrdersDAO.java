package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bean.OrdersBean;
import com.util.DBConnection;

public class OrdersDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	boolean result = false;
	private String ajaxResult = null;

	public Boolean insert(OrdersBean ordersBean) {

		conn = DBConnection.getConnection();

		if (conn != null) {
			String insertSQL = "insert into orders"
					+ "(ordersId,ordersQty,ordersPrice,ordersDate,"
					+ "customerId,ordersIsDispatched,ordersIsDelivered,"
					+ "ordersIsCancelled,productId) "
					+ "values(?,?,?,?,?,?,?,?,?)";

			try {
				pstmt = conn.prepareStatement(insertSQL);

				pstmt.setString(1, ordersBean.getOrdersId());
				pstmt.setString(2, ordersBean.getOrdersQty());
				pstmt.setString(3, ordersBean.getOrdersPrice());
				pstmt.setLong(4, ordersBean.getOrdersDate());
				pstmt.setString(5, ordersBean.getCustomerId());
				pstmt.setString(6, ordersBean.getOrdersIsDispatched());
				pstmt.setString(7, ordersBean.getOrdersIsDelivered());
				pstmt.setString(8, ordersBean.getOrdersIsCancelled());
				pstmt.setString(9, ordersBean.getProductId());

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					String[] quantity = ordersBean.getOrdersQty().split(",");
					String[] pIds = ordersBean.getProductId().split(",");
					int qty;
					for (int i = 0; i < quantity.length; i++) {
						qty = Integer.parseInt(quantity[i]);
						String pId = pIds[i];
						String selectSQL = "select * from product where productId=?";
						pstmt = conn.prepareStatement(selectSQL);
						pstmt.setString(1, pId);
						rs = pstmt.executeQuery();
						int purchases = 0, stock = 0;

						while (rs.next()) {
							purchases = Integer.parseInt(rs
									.getString("productPurchases"));
							stock = Integer.parseInt(rs
									.getString("productStock"));
						}
						purchases += qty;
						stock -= qty;
						System.out.println("qty->" + qty + " stock->" + stock
								+ " purchase->" + purchases);

						if (stock < 0) {
							return false;
						} else {
							String updateSQL = "update product set productPurchases=?,productStock=? where productId=?";
							pstmt = conn.prepareStatement(updateSQL);
							pstmt.setString(1, purchases + "");
							pstmt.setString(2, stock + "");
							pstmt.setString(3, pId);

							rowsAffected = pstmt.executeUpdate();
							if (rowsAffected > 0) {
								result = true;
								System.out.println("updated");
							} else {
								result = false;
								System.out.println("not updated");
							}
						}
					}
				} else {
					result = false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				result = false;
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

	public String ajaxOrdersUpdate(String ordersId, String ordersIsDispatched,
			String ordersIsDelivered) {

		conn = DBConnection.getConnection();
		if (conn != null) {
			String updateSQL = "update orders set ordersIsDispatched=?,ordersIsDelivered=? where ordersId=?";

			try {
				pstmt = conn.prepareStatement(updateSQL);

				pstmt.setString(1, ordersIsDispatched);
				pstmt.setString(2, ordersIsDelivered);
				pstmt.setString(3, ordersId);

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

	public String ajaxCancelOrders(String ordersId) {

		conn = DBConnection.getConnection();
		if (conn != null) {
			String updateSQL = "update orders set ordersIsCancelled='y' where ordersId=?";

			try {
				pstmt = conn.prepareStatement(updateSQL);

				pstmt.setString(1, ordersId);

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

	public String ajaxOrdersDelete(String ordersId) {

		conn = DBConnection.getConnection();
		if (conn != null) {
			String deleteSQL = "delete from orders where ordersId=?";

			try {
				pstmt = conn.prepareStatement(deleteSQL);

				pstmt.setString(1, ordersId);

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

	public String listOfOrdersId() {

		conn = DBConnection.getConnection();
		String ordersId = new String();
		if (conn != null) {

			String selectSQL = "select * from orders order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					ordersId += rs.getString("ordersId") + "|";

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
		if (!ordersId.isEmpty())
			return ordersId.substring(0, ordersId.length() - 1);
		return ordersId;
	}

	public String listOfOrdersId(String customerId) {

		conn = DBConnection.getConnection();
		String ordersId = new String();
		if (conn != null) {

			String selectSQL = "select * from orders where customerId=? order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, customerId);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					ordersId += rs.getString("ordersId") + "|";

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
		if (!ordersId.isEmpty())
			return ordersId.substring(0, ordersId.length() - 1);
		return ordersId;
	}

	public String listOfOrdersQty() {

		conn = DBConnection.getConnection();
		String ordersQty = new String();
		if (conn != null) {

			String selectSQL = "select * from orders order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					ordersQty += rs.getString("ordersQty") + "|";

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
		if (!ordersQty.isEmpty())
			return ordersQty.substring(0, ordersQty.length() - 1);
		return ordersQty;
	}

	public String listOfOrdersQty(String customerId) {

		conn = DBConnection.getConnection();
		String ordersQty = new String();
		if (conn != null) {

			String selectSQL = "select * from orders where customerId=? order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, customerId);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					ordersQty += rs.getString("ordersQty") + "|";
					System.out.println(ordersQty + "<-");
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
		if (!ordersQty.isEmpty())
			return ordersQty.substring(0, ordersQty.length() - 1);
		return ordersQty;
	}

	public String listOfOrdersPrice() {

		conn = DBConnection.getConnection();
		String ordersPrice = new String();
		if (conn != null) {

			String selectSQL = "select * from orders order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					ordersPrice += rs.getString("ordersPrice") + "|";

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
		if (!ordersPrice.isEmpty())
			return ordersPrice.substring(0, ordersPrice.length() - 1);
		return ordersPrice;
	}

	public String listOfOrdersPrice(String customerId) {

		conn = DBConnection.getConnection();
		String ordersPrice = new String();
		if (conn != null) {

			String selectSQL = "select * from orders where customerId=? order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, customerId);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					ordersPrice += rs.getString("ordersPrice") + "|";

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
		if (!ordersPrice.isEmpty())
			return ordersPrice.substring(0, ordersPrice.length() - 1);
		return ordersPrice;
	}

	public String listOfOrdersDate() {

		conn = DBConnection.getConnection();
		String returnDay = new String();
		if (conn != null) {

			String selectSQL = "select * from orders order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					returnDay += rs.getString("ordersDate") + "|";
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
		if (!returnDay.isEmpty())
			return returnDay.substring(0, returnDay.length() - 1);
		return returnDay;
	}

	public String listOfOrdersDate(String customerId) {

		conn = DBConnection.getConnection();
		String returnDay = new String();
		if (conn != null) {

			String selectSQL = "select * from orders where customerId=? order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, customerId);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					returnDay += rs.getString("ordersDate") + "|";
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
		if (!returnDay.isEmpty())
			return returnDay.substring(0, returnDay.length() - 1);
		return returnDay;
	}

	public String listOfOrdersIsDispatched() {

		conn = DBConnection.getConnection();
		String ordersIsDispatched = new String();
		if (conn != null) {

			String selectSQL = "select * from orders order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					ordersIsDispatched += rs.getString("ordersIsDispatched")
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
		if (!ordersIsDispatched.isEmpty())
			return ordersIsDispatched.substring(0,
					ordersIsDispatched.length() - 1);
		return ordersIsDispatched;
	}

	public String listOfOrdersIsDispatched(String customerId) {

		conn = DBConnection.getConnection();
		String ordersIsDispatched = new String();
		if (conn != null) {

			String selectSQL = "select * from orders where customerId=? order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, customerId);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					ordersIsDispatched += rs.getString("ordersIsDispatched")
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
		if (!ordersIsDispatched.isEmpty())
			return ordersIsDispatched.substring(0,
					ordersIsDispatched.length() - 1);
		return ordersIsDispatched;
	}

	public String listOfOrdersIsDelivered() {

		conn = DBConnection.getConnection();
		String ordersIsDelivered = new String();
		if (conn != null) {

			String selectSQL = "select * from orders order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					ordersIsDelivered += rs.getString("ordersIsDelivered")
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
		if (!ordersIsDelivered.isEmpty())
			return ordersIsDelivered.substring(0,
					ordersIsDelivered.length() - 1);
		return ordersIsDelivered;
	}

	public String listOfOrdersIsDelivered(String customerId) {

		conn = DBConnection.getConnection();
		String ordersIsDelivered = new String();
		if (conn != null) {

			String selectSQL = "select * from orders where customerId=? order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, customerId);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					ordersIsDelivered += rs.getString("ordersIsDelivered")
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
		if (!ordersIsDelivered.isEmpty())
			return ordersIsDelivered.substring(0,
					ordersIsDelivered.length() - 1);
		return ordersIsDelivered;
	}

	public String listOfOrdersIsCancelled() {

		conn = DBConnection.getConnection();
		String ordersIsCancelled = new String();
		if (conn != null) {

			String selectSQL = "select * from orders order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					ordersIsCancelled += rs.getString("ordersIsCancelled")
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
		if (!ordersIsCancelled.isEmpty())
			return ordersIsCancelled.substring(0,
					ordersIsCancelled.length() - 1);
		return ordersIsCancelled;
	}

	public String listOfOrdersIsCancelled(String customerId) {

		conn = DBConnection.getConnection();
		String ordersIsCancelled = new String();
		if (conn != null) {

			String selectSQL = "select * from orders where customerId=? order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, customerId);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					ordersIsCancelled += rs.getString("ordersIsCancelled")
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
		if (!ordersIsCancelled.isEmpty())
			return ordersIsCancelled.substring(0,
					ordersIsCancelled.length() - 1);
		return ordersIsCancelled;
	}

	public String listOfOrdersProductName() {

		conn = DBConnection.getConnection();
		String productId = new String();
		String productName = new String();
		if (conn != null) {

			String selectSQL = "select * from orders order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					productId = rs.getString("productId");
					String ids[] = productId.split(",");
					for (int i = 0; i < ids.length; i++) {
						pstmt = conn
								.prepareStatement("select * from product where productId=?");
						pstmt.setString(1, ids[i]);
						ResultSet rs1 = pstmt.executeQuery();
						while (rs1.next()) {
							productName += rs1.getString("productName") + "~";
						}
					}
					productName = productName.substring(0,
							productName.length() - 1) + "|";
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

	public String listOfOrdersCustomerName() {

		conn = DBConnection.getConnection();
		String customerFname = new String();
		if (conn != null) {

			String selectSQL = "select * from orders,customer where orders.customerId=customer.customerId order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					customerFname += rs.getString("customerFname") + " "
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
		if (!customerFname.isEmpty())
			return customerFname.substring(0, customerFname.length() - 1);
		return customerFname;
	}

	public String listOfOrdersProductName(String customerId) {

		conn = DBConnection.getConnection();
		String productId = new String();
		String productName = new String();
		if (conn != null) {

			String selectSQL = "select * from orders where customerId=? order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, customerId);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					productId = rs.getString("productId");
					String ids[] = productId.split(",");
					for (int i = 0; i < ids.length; i++) {
						pstmt = conn
								.prepareStatement("select * from product where productId=?");
						pstmt.setString(1, ids[i]);
						ResultSet rs1 = pstmt.executeQuery();
						while (rs1.next()) {
							productName += rs1.getString("productName") + "~";
						}
					}
					productName = productName.substring(0,
							productName.length() - 1) + "|";
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

	public String noOfOrders() {

		conn = DBConnection.getConnection();
		int cnt = 0;
		if (conn != null) {

			String selectSQL = "select * from orders order by ordersDate desc";
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

	public String noOfOrders(String customerId) {

		conn = DBConnection.getConnection();
		int cnt = 0;
		if (conn != null) {

			String selectSQL = "select * from orders where customerId=? order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, customerId);
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

	public String noOfOrdersNotDispatched() {

		conn = DBConnection.getConnection();
		int cnt = 0;
		if (conn != null) {

			String selectSQL = "select * from orders where ordersIsDispatched='n' and ordersIsCancelled='n' order by ordersDate desc";
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

	public String noOfOrdersNotDispatched(String customerId) {

		conn = DBConnection.getConnection();
		int cnt = 0;
		if (conn != null) {

			String selectSQL = "select * from orders where ordersIsDispatched='n' and ordersIsCancelled='n' where customerId=? order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, customerId);
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

	public String noOfOrdersNotDelivered() {

		conn = DBConnection.getConnection();
		int cnt = 0;
		if (conn != null) {

			String selectSQL = "select * from orders where ordersIsDelivered='n' and ordersIsCancelled='n' order by ordersDate desc";
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

	public String noOfOrdersNotDelivered(String customerId) {

		conn = DBConnection.getConnection();
		int cnt = 0;
		if (conn != null) {

			String selectSQL = "select * from orders where ordersIsDelivered='n' and ordersIsCancelled='n' and customerId=? order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, customerId);
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

	public String listOfOrdersCustomerEmail() {

		conn = DBConnection.getConnection();
		String productName = new String();
		if (conn != null) {

			String selectSQL = "select * from orders,customer where orders.customerId = customer.customerId order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					productName += rs.getString("customerEmail") + "|";

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

	public String listOfOrdersCustomerAddress() {

		conn = DBConnection.getConnection();
		String customerAddress = new String();
		if (conn != null) {

			String selectSQL = "select * from orders,customer where orders.customerId = customer.customerId order by ordersDate desc";
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
		if (!customerAddress.isEmpty())
			return customerAddress.substring(0, customerAddress.length() - 1);
		return customerAddress;
	}

	public String listOfOrderscustomerMobileNo() {

		conn = DBConnection.getConnection();
		String customerMobileNo = new String();
		if (conn != null) {

			String selectSQL = "select * from orders,customer where orders.customerId = customer.customerId order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					customerMobileNo += rs.getString("customerMobileNo") + "|";

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
		if (!customerMobileNo.isEmpty())
			return customerMobileNo.substring(0, customerMobileNo.length() - 1);
		return customerMobileNo;
	}

	public String listOfIndividualPrice(String customerId) {

		conn = DBConnection.getConnection();
		String productId = new String();
		String productName = new String();
		if (conn != null) {

			String selectSQL = "select * from orders where customerId=? order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, customerId);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					productId = rs.getString("productId");
					int qtp = 0;
					String qtys[] = rs.getString("ordersQty").split(",");
					String ids[] = productId.split(",");
					for (int i = 0; i < ids.length; i++) {
						pstmt = conn
								.prepareStatement("select * from product where productId=?");
						pstmt.setString(1, ids[i]);
						ResultSet rs1 = pstmt.executeQuery();
						while (rs1.next()) {
							productName += (Integer.parseInt(rs1
									.getString("productPrice")) * Integer
									.parseInt(qtys[qtp++]))
									+ "~";
						}
					}
					productName = productName.substring(0,
							productName.length() - 1) + "|";
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

	public String listOfIndividualPriceNormal(String customerId) {

		conn = DBConnection.getConnection();
		String productId = new String();
		String productName = new String();
		if (conn != null) {

			String selectSQL = "select * from orders where customerId=? order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, customerId);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					productId = rs.getString("productId");
					String ids[] = productId.split(",");
					for (int i = 0; i < ids.length; i++) {
						pstmt = conn
								.prepareStatement("select * from product where productId=?");
						pstmt.setString(1, ids[i]);
						ResultSet rs1 = pstmt.executeQuery();
						while (rs1.next()) {
							productName += rs1.getString("productPrice") + "~";
						}
					}
					productName = productName.substring(0,
							productName.length() - 1) + "|";
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

	public String listOfIndividualDiscount(String customerId) {

		conn = DBConnection.getConnection();
		String productId = new String();
		String productName = new String();
		if (conn != null) {

			String selectSQL = "select * from orders where customerId=? order by ordersDate desc";
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, customerId);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					productId = rs.getString("productId");
					String ids[] = productId.split(",");
					for (int i = 0; i < ids.length; i++) {
						pstmt = conn
								.prepareStatement("select * from product where productId=?");
						pstmt.setString(1, ids[i]);
						ResultSet rs1 = pstmt.executeQuery();
						while (rs1.next()) {
							productName += ((Integer.parseInt(rs1
									.getString("productPrice")) * Integer
									.parseInt(rs1.getString("productDiscount"))) / 100)
									+ "~";
						}
					}
					productName = productName.substring(0,
							productName.length() - 1) + "|";
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
}
