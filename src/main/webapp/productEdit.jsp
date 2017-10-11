<%@page import="com.dao.SubCategoryDAO"%>
<%@page import="com.bean.SubCategoryBean"%>
<%@page import="com.dao.CategoryDAO"%>
<%@page import="com.bean.CategoryBean"%>
<%@page import="java.util.List"%>
<%@page import="com.bean.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Product | BookBerries</title>
<%@include file="linker.jsp"%>
</head>
<body
	style="background-image: url(res/imgs/productaddback.png); background-size: cover;">
	<%@include file="headerAdmin.jsp"%>
	<%
		ProductBean productBean = (ProductBean) request
				.getAttribute("productBean");
		if (productBean != null) {
	%>
	<div class="container">
		<div class="row">
			<div class="cmd3" style="padding: 0;"></div>
			<div class="cmd6">
				<div class="rightpanel">
					<div style="font-size: 48px; color: #e74c3c;">Update Book</div>
					<form action="ProductUpdateServlet" method="post" class="mainform"
						name="productForm" enctype="multipart/form-data">
						<input type="hidden" name="productId" id="productId"
							value="<%=productBean.getProductId()%>"> <input
							type="hidden" name="productPurchases" id="productPurchases"
							value="<%=productBean.getProductPurchases()%>"> <input
							type="text" placeholder="Book Name" id="namebox"
							name="txtProductName" value="<%=productBean.getProductName()%>"
							class="myinput" maxlength="100"><span class="ermsg"
							id="nameError"></span> <input id="authorbox" type="text"
							placeholder="Author Name" name="txtProductAuthor"
							value="<%=productBean.getProductAuthor()%>" class="myinput"
							maxlength="50"><span class="ermsg" id="authorError"></span>
						<input type="text" placeholder="Publisher Name" id="publisherbox"
							name="txtProductPublisher" class="myinput"
							value="<%=productBean.getProductPublisher()%>" maxlength="50"><span
							class="ermsg" id="publisherError"></span>
						<textarea rows="5" name="txtProductDesc" placeholder="Description"
							class="myinput" maxlength="255" id="descbox"><%=productBean.getProductDesc()%></textarea>
						<span class="ermsg" id="descError"></span> <input type="text"
							placeholder="Stock" id="stockbox" name="txtProductStock"
							value="<%=productBean.getProductStock()%>" class="myinput"
							maxlength="5"><span class="ermsg" id="stockError"></span>
						<input type="text" placeholder="Price" id="pricebox"
							name="txtProductPrice" value="<%=productBean.getProductPrice()%>"
							class="myinput" maxlength="5"><span class="ermsg"
							id="priceError"></span> <input type="text" placeholder="Discount"
							id="disbox" name="txtProductDiscount"
							value="<%=productBean.getProductDiscount()%>" class="myinput"
							maxlength="2"><span class="ermsg" id="discountError"></span>

						<div class="select catslist">
							<span class="selected-option"></span> <select id="cats"
								name="selCategoryName">
								<option value="def">Select Category</option>
								<%
									List<CategoryBean> categoryBeanList = new CategoryDAO().list();
										String type = productBean.getCategoryId();
										for (int i = 0; i < categoryBeanList.size(); i++) {
											String tmp = "unselected";
											if (categoryBeanList.get(i).getCategoryId().equals(type))
												tmp = "selected";
								%>


								<option value=<%=categoryBeanList.get(i).getCategoryId()%>
									<%=tmp%>><%=categoryBeanList.get(i).getCategoryName()%></option>
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
								<%
									List<SubCategoryBean> subCategoryBeanList = new SubCategoryDAO()
												.list();
										String type1 = productBean.getSubCategoryId();
										for (int i = 0; i < subCategoryBeanList.size(); i++) {
											String tmp = "unselected";
											if (subCategoryBeanList.get(i).getSubCategoryId()
													.equals(type1))
												tmp = "selected";
								%>
								<option value=<%=subCategoryBeanList.get(i).getSubCategoryId()%>
									<%=tmp%>><%=subCategoryBeanList.get(i).getSubCategoryName()%></option>
								<%
									}
								%>
							</select>
							<div class="options" style="bottom: 100%;"></div>
							<i class="material-icons">arrow_drop_down</i>
						</div>
						<span class="ermsg" id="subcategoryError"></span>
						<%-- <button type="button" class="mybutton photobutton">Select
							Photo</button>
						<span class="imagename" style="margin-left: 15px;">No Photo
							Chosen</span> <input type="file" name="photo" class="photoselector"
							style="display: none;">${file1} <br> --%>
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
	<%
		}
	%>
	<%@include file="footer.jsp"%>
	<script src="res/js/productEdit.js"></script>
</body>
</html>