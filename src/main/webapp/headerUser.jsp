
<%@page import="com.bean.CustomerBean"%>
<%
	CustomerBean customerBeanHeader = (CustomerBean) session
			.getAttribute("customerBean");

	if (customerBeanHeader != null
			&& customerBeanHeader.getCustomerIsAdmin().equals("n")) {
%>

<div class="header">
	<div class="navpan">
		<div class="nav-content">
			<a href="index.jsp" class="headerlogo"> <img
				src="res/imgs/berry.svg">
				<div class="texts">
					<span class="darktext">book</span><br> <span class="lighttext">berries</span>
				</div>
			</a>
			<div class="right-content permitted">
				<i class="material-icons searchicon tooltip" toolmsg="Search">search</i> <i
					class="material-icons carticon tooltip" toolmsg="Cart" style="position: relative;">shopping_cart
					<span class="cartcount">0</span>
				</i> <i class="material-icons trayicon tooltip" toolmsg="More">more_vert</i>
			</div>
		</div>
		<div class="navtray">
			<a href="books.jsp">Books</a> <a href="myorders.jsp">My Orders</a> <a
				href="wishlist.jsp">Wishlist</a> <a href="feedback.jsp">Feedback</a>
			<a href="profile.jsp" class="profilebutton">Profile</a> <a
				href="LogoutServlet">Logout</a>
		</div>
		<div class="searchtray">
			<div class="searchparent">
				<i class="material-icons">search</i> <input type="text"
					class="searchbox" placeholder="Enter book name to search" ng-model="searchbook"/>
				<ul ng-controller="searchCtrl" class="sugbox">
					<li ng-repeat="b in searchbooks | filter:searchbook">{{b.name}}<span class="bookid" style="display:none;">{{b.id}}</span></li>
				</ul>
			</div>
		</div>
	</div>
	<div
		style="position: fixed; width: 320px; top: 15px; background-color: white; box-shadow: 0 10px 30px rgba(0, 0, 0, 0.7); padding: 15px; z-index: 3; border-radius: 5px; left: 50%; margin-left: -160px; box-sizing: border-box;display:none;"
		class="cart">
		<p style="font-size: 24px;color:#e74c3c; display: inline-block; margin: 0;">Cart</p>
		<i class="material-icons cartcloser"
			style="float: right; position: relative; top: 7px; cursor: pointer;color:#e74c3c;">close</i>
		<div style="height: 1px; background-color: #e74c3c; margin: 10px 0;"></div>
		<div style="height:275px;overflow: auto;margin-bottom:10px;" class="cartbooks"></div>
		<span>Book(s) Price : </span>
		<span style="color:grey;" class="cartbookprice">Rs. 22500</span><bR>
		<span style="font-size:14px;">[+] Delivery Charges : </span>
		<span style="font-size:14px;color:grey;" class="cartdelcharge">Rs. 250</span><bR>
		<span style="font-size:14px;">[-] Discount : </span>
		<span style="font-size:14px;color:grey;" class="cartdiscount">Rs. 0</span><bR>
		<div style="height: 1px; background-color: grey; margin: 5px 0;"></div>	
		<span style="font-size:18px;">Total Payable : </span>
		<span style="font-size:18px;color:grey;" class="cartpayable">Rs. 32540</span><bR>
		<a class="mybutton" href="order.jsp" style="display:block;width:100%;box-sizing:border-box;text-align: center;margin-top:10px;">Place Order</a>
	</div>
</div>
<%
	} else {
		response.sendRedirect("login.jsp");

	}
%>