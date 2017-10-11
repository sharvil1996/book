<%@page import="com.bean.CustomerBean"%>
<%
	CustomerBean customerBeanHeader = (CustomerBean) session
			.getAttribute("customerBean");

	if (customerBeanHeader != null
			&& customerBeanHeader.getCustomerIsAdmin().equals("y")) {
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
			<div class="right-content">
				<i class="material-icons searchicon tooltip" toolmsg="Search">search</i> <i
					class="material-icons trayicon tooltip" toolmsg="More">more_vert</i>
			</div>
		</div>
		<div class="navtray">
			<a href="dashBoardAdmin.jsp">Dashboard</a> <a href="orders.jsp">Orders</a>
			<a href="products.jsp">Products</a> <a href="category.jsp">Category
				&amp; Subcategory</a> <a href="customers.jsp">Customers</a> <a
				href="zipcode.jsp">Zip Codes</a> <a href="feedbacks.jsp">Feedbacks</a>
			<a href="logs.jsp">Logs</a><a
				href="profile.jsp" class="profilebutton">Profile</a>
			<a href="LogoutServlet">Logout</a>
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
</div>
<%
	} else {
		response.sendRedirect("login.jsp");

	}
%>
