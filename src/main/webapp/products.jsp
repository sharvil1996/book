<%@page import="com.dao.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products | BookBerries</title>
<%@include file="linker.jsp"%>
</head>
<body style="background-color: rgba(0, 0, 0, 0.04);">
	<%@include file="headerAdmin.jsp"%>
	<div class="model" id="deletemodel">
		<p>Are you sure, you want to delete product?</p>
		<i class="material-icons deletefinalbutton tooltip" toolmsg="Yes">done</i>&nbsp;&nbsp; <i
			class="material-icons modelcloser tooltip" toolmsg="No">close</i>
	</div>
	<div class="model" id="errormodel">
		<p>Product can't be deleted right now, try again later</p>
		<i class="material-icons modelcloser tooltip" toolmsg="Ok">done</i>
	</div>
	<a href="addproduct.jsp"
		style="background-color: #e74c3c; color: white; padding: 15px; position: fixed; bottom: 35px; right: 35px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.5); border-radius: 100px; z-index: 1;"><i
		class="material-icons tooltip" toolmsg="Add">add</i></a>
	<div class="container" ng-controller="mainCtrl">
		<div
			style="box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3); border-radius: 5px; margin: 0 0 15px 0; background-color: white;padding:10px 0;text-align:center;">
			<input type="text" id="searchTrigger" class="myinput"
				placeholder="Search Product" style="width: 40%;" /> <input
				type="text" ng-model="search.name" id="nameSearch"
				style="display: none;" /> <input type="text"
				ng-model="search.author" id="authorSearch" style="display: none;" />
			<input type="text" ng-model="search.publisher" id="publisherSearch"
				style="display: none;" /> <input type="text"
				ng-model="search.category" id="categorySearch"
				style="display: none;" /> <input type="text"
				ng-model="search.subcategory" id="subcategorySearch"
				style="display: none;" />
			<div class="select" style="width: 135px; text-align: left;">
				<span class="selected-option"></span> <select class="searchCriteria">
					<option>by name</option>
					<option>by author</option>
					<option>by publisher</option>
					<option>by category</option>
					<option>by subcategory</option>
				</select>
				<div class="options" style="bottom: auto; top: 100%; z-index: 1;"></div>
				<i class="material-icons">arrow_drop_down</i>
			</div>
		</div>
		<div ng-repeat="product in products | filter:search" class="row"
			style="box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3); border-radius: 5px; margin: 0 0 30px 0; background-color: white;">
			<div class="cl3"
				style="display: inline-block; padding: 20px; text-align: center; overflow: hidden;">
				<img ng-src="http://www.bookberries.co.in/Images/{{product.image}}" height="300"
					style="box-shadow: 0 2px 9px rgba(0, 0, 0, 0.5); border-radius: 3px;" />
			</div>
			<div class="cl9"
				style="display: inline-block; padding: 20px; position: relative;">
				<div
					style="position: absolute; right: 20px; top: 15px; display: inline-block;">
					<a href="ProductEditServlet?productId={{product.id}}"
						style="color: rgba(0, 0, 0, 0.87);"><i class="material-icons tooltip" toolmsg="Edit"
						style="cursor: pointer;">edit</i></a>&nbsp;&nbsp; <i
						class="material-icons deletemodelbutton modelbutton tooltip" toolmsg="Remove"
						target="deletemodel" style="cursor: pointer;">delete</i>
				</div>
				<span style="display: none;" class="prodid">{{product.id}}</span>
				<h2 style="margin: 0;">
					<b>{{product.name}}</b>
				</h2>
				<span style="color: grey;">by {{product.author}},
					{{product.publisher}}</span><br> <br>
				<h3 style="margin: 0;">
					<b>Description</b>
				</h3>
				<span style="white-space: normal; color: grey;">{{product.desc}}</span>
				<br>
				<div style="display: inline-block;">
					<br>
					<h3 style="margin: 0; display: inline-block;">
						<b>Stock</b>
					</h3>
					<br> <span style="color: grey;">{{product.stock}}
						available</span>
				</div>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<div style="display: inline-block;">
					<h3 style="margin: 0; display: inline-block;">
						<b>Price</b>
					</h3>
					<br> <span style="color: grey;">Rs.{{product.price}}</span>
				</div>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<div style="display: inline-block;">
					<h3 style="margin: 0; display: inline-block;">
						<b>Discount</b>
					</h3>
					<br> <span style="color: grey;">{{product.dis}}%</span>
				</div>
				<br>
				<div style="display: inline-block;">
					<br>
					<h3 style="margin: 0; display: inline-block;">
						<b>Purchases</b>
					</h3>
					<br> <span style="color: grey;">{{product.purchases}}
						times</span>
				</div>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<div style="display: inline-block;">
					<h3 style="margin: 0; display: inline-block;">
						<b>Category</b>
					</h3>
					<br> <span style="color: grey;">{{product.category}}</span>
				</div>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<div style="display: inline-block;">
					<h3 style="margin: 0; display: inline-block;">
						<b>Subcategory</b>
					</h3>
					<br> <span style="color: grey;">{{product.subcategory}}</span>
				</div>
			</div>
		</div>
	</div>
	<span id="idProvider" style="display: none;"><%=new ProductDAO().listOfProductId()%></span>
	<span id="nameProvider" style="display: none;"><%=new ProductDAO().listOfProductName()%></span>
	<span id="authorProvider" style="display: none;"><%=new ProductDAO().listOfProductAuthor()%></span>
	<span id="publisherProvider" style="display: none;"><%=new ProductDAO().listOfProductPublisher()%></span>
	<span id="descProvider" style="display: none;"><%=new ProductDAO().listOfProductDesc()%></span>
	<span id="stockProvider" style="display: none;"><%=new ProductDAO().listOfProductStock()%></span>
	<span id="priceProvider" style="display: none;"><%=new ProductDAO().listOfProductPrice()%></span>
	<span id="discountProvider" style="display: none;"><%=new ProductDAO().listOfProductDiscount()%></span>
	<span id="purchasesProvider" style="display: none;"><%=new ProductDAO().listOfProductPurchases()%></span>
	<span id="imageProvider" style="display: none;"><%=new ProductDAO().listOfProductImage()%></span>
	<span id="categoryProvider" style="display: none;"><%=new ProductDAO().listOfProductCategoryName()%></span>
	<span id="subCategoryProvider" style="display: none;"><%=new ProductDAO().listOfProductSubCategoryName()%></span>
	<%@include file="footer.jsp"%>
	<script src="res/js/products.js"></script>
</body>
</html>