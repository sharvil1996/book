<%@page import="com.bean.CustomerBean"%>
<%
	CustomerBean customerBeanHeader = (CustomerBean) session
			.getAttribute("customerBean");

	if (customerBeanHeader != null
			&& customerBeanHeader.getCustomerIsAdmin().equals("n"))
		response.sendRedirect("books.jsp");
	else if (customerBeanHeader != null
			&& customerBeanHeader.getCustomerIsAdmin().equals("y"))
		response.sendRedirect("dashBoardAdmin.jsp");
	else {
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
				<div class="right-content permitted" >
					<i class="material-icons searchicon tooltip" toolmsg="Search">search</i>
					<i class="tooltip" toolmsg="Books"><a href="books.jsp"><img src="res/imgs/book.svg" style="height:42px;width:42px;"/></a></i>
					<i class="material-icons tooltip" toolmsg="Login"><a href="login.jsp" style="color:white;">person</a></i>
					<i class="material-icons tooltip" toolmsg="Register"><a href="register.jsp"><img src="res/imgs/register.svg" style="height:42px;width:42px;"/></a></i>
				</div>
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
	}
%>