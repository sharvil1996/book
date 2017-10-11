
<%@page import="com.dao.SubCategoryDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.CategoryDAO"%>
<%@page import="com.bean.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product | BookBerries</title>
<%@include file="linker.jsp"%>
</head>
<body
	style="background-image: url(res/imgs/productaddback.png); background-size: cover;">
	<%@include file="headerAdmin.jsp"%>
	<div class="container">
		<div class="row">
			<div class="cmd3" style="padding: 0;"></div>
			<div class="cmd6">
				<div class="rightpanel">
					<div style="font-size: 48px; color: #e74c3c;">Add Book</div>
					<form action="ProductInsertServlet" method="post" class="mainform"
						name="productForm" enctype="multipart/form-data">
						<input type="text" placeholder="Book Name" id="namebox"
							name="txtProductName" value="${txtProductName}" class="myinput"
							maxlength="100"><span class="ermsg" id="nameError"></span>
						<input id="authorbox" type="text" placeholder="Author Name"
							name="txtProductAuthor" value="${txtProductAuthor}"
							class="myinput" maxlength="50"><span class="ermsg"
							id="authorError"></span> <input type="text"
							placeholder="Publisher Name" id="publisherbox"
							name="txtProductPublisher" class="myinput"
							value="${txtProductPublisher }" maxlength="50"><span
							class="ermsg" id="publisherError"></span>
						<textarea rows="5" name="txtProductDesc" placeholder="Description"
							class="myinput" maxlength="255" id="descbox">${txtProductDesc}</textarea>
						<span class="ermsg" id="descError"></span> <input type="text"
							placeholder="Stock" id="stockbox" name="txtProductStock"
							value="${txtProductStock}" class="myinput" maxlength="5"><span
							class="ermsg" id="stockError"></span> <input type="text"
							placeholder="Price" id="pricebox" name="txtProductPrice"
							value="${txtProductPrice}" class="myinput" maxlength="5"><span
							class="ermsg" id="priceError"></span> <input type="text"
							placeholder="Discount" id="disbox" name="txtProductDiscount"
							value="${txtProductDiscount}" class="myinput" maxlength="2"><span
							class="ermsg" id="discountError"></span>
						<div class="select catslist">
							<span class="selected-option"></span> <select id="cats"
								name="selCategoryName">
								<option value="def">Select Category</option>
								<%
									CategoryDAO categoryDAO = new CategoryDAO();
									List<CategoryBean> categoryList = categoryDAO.list();

									for (int i = 0; i < categoryList.size(); i++) {
										String tmp = "unselected";
										String type = request.getParameter("selCategoryName");
										if (type == null)
											tmp = "unselected";
										else if (categoryList.get(i).getCategoryId().equals(type))
											tmp = "selected";
								%>

								<option value=<%=categoryList.get(i).getCategoryId()%> <%=tmp%>>
									<%=categoryList.get(i).getCategoryName()%></option>
								<%
									}
								%>
							</select>
							<div class="options" style="bottom: 100%;"></div>
							<i class="material-icons">arrow_drop_down</i>
						</div>
						<span class="ermsg" id="categoryError"></span>
						<div class="select subcatselect">
							<span class="selected-option"></span> <select id="subcats"
								name="selSubCategoryName">
								<option value="def">Select Sub Category</option>
							</select>
							<div class="options" style="bottom: 100%;"></div>
							<i class="material-icons">arrow_drop_down</i>
						</div>
						<span class="ermsg" id="subcategoryError"></span>
						<button type="button" class="mybutton photobutton">Select
							Photo</button>
						<span class="imagename" style="margin-left: 15px;">No Photo
							Chosen</span> <input type="file" name="photo" class="photoselector"
							style="display: none;">${file1} <br>
						<button type="submit" class="mybutton">
							<i class="material-icons tooltip" toolmsg="Save">save</i>
						</button>
						<button type="button" class="mybutton cancelform">
							<i class="material-icons tooltip" toolmsg="Cancel">close</i>
						</button>
					</form>
				</div>
			</div>
			<div class="cmd3" style="padding: 0;"></div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	${productName}${productAuthor}${productPublisher}${productDesc}${productStock}${productPrice}${category}${subCategory}
	<script src="res/js/addproduct.js"></script>
</body>
</html>