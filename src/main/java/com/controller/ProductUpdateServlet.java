package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bean.ProductBean;
import com.dao.ProductDAO;
import com.util.ValidationUtils;

public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ProductBean productBean = new ProductBean();
		boolean isError = false;
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = null;
		String fieldName = null;
		String fieldValue = null;
		File file = null;

		ServletContext context = getServletContext();

		String productId = "";
		String productName = "";
	//	String productImage = "";
		String productAuthor = "";
		String productPublisher = "";
		String productDesc = "";
		String productStock = "";
		String productPrice = "";
		String productPurchases = "";
		String productDiscount = "";
		String subCategoryId = "";
		String categoryId = "";

		try {
			items = upload.parseRequest(request);


			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				if (item.isFormField()) {
					fieldName = item.getFieldName();
					fieldValue = item.getString();

					if (fieldName.equals("txtProductName"))
						productName = fieldValue;
					else if (fieldName.equals("txtProductAuthor")) {
						productAuthor = fieldValue;
					} else if (fieldName.equals("txtProductPublisher")) {
						productPublisher = fieldValue;
					} else if (fieldName.equals("txtProductStock")) {
						productStock = fieldValue;
					} else if (fieldName.equals("txtProductPrice")) {
						productPrice = fieldValue;
					} else if (fieldName.equals("selSubCategoryName")) {
						subCategoryId = fieldValue;
					} else if (fieldName.equals("txtProductDesc")) {
						productDesc = fieldValue;
					} else if (fieldName.equals("productId")) {
						productId = fieldValue;
					} else if (fieldName.equals("productPurchases")) {
						productPurchases = fieldValue;
					} else if (fieldName.equals("selCategoryName")) {
						categoryId = fieldValue;
					} else if (fieldName.equals("txtProductDiscount")) {
						productDiscount = fieldValue;
					}
					
				} else {
					fieldName = item.getFieldName();
				/*	productImage = item.getName();
					String absoluteDiskPath = "C:\\Inetpub\\vhosts\\bookberries.co.in\\httpdocs\\Images\\temp";
					file = new File(absoluteDiskPath + File.separator
							+ productId + ".png");

					if (productImage.isEmpty()) {
						// isError=true;
						// request.setAttribute("file1","<font color=red>* Photo is Required........</font>");
						productImage = "n";
					} else {
						if (context.getMimeType(productImage).equals(
								"image/gif")
								|| context.getMimeType(productImage).equals(
										"image/jpeg")
								|| context.getMimeType(productImage).equals(
										"image/png"))
							try {
								item.write(file);
								productImage = "y";
								productBean.setProductImage(productImage);
							} catch (Exception e) {
								e.printStackTrace();
							}
						else
							request.setAttribute(
									"file",
									"<font color=red>*Please upload files that end in types .png,.jpeg only.</font>");

					}*/
				}
			}
			if (ValidationUtils.isEmpty(productName)) {
				isError = true;
				request.setAttribute("productName",
						"<font color=red>* Product Name is Required.......</font>");
			} else {
				request.setAttribute("txtProductName", productName);

			}
			productBean.setProductName(productName);
			if (ValidationUtils.isEmpty(productAuthor)) {
				isError = true;
				request.setAttribute("productAuthor",
						"<font color=red>* Product Author is Required.......</font>");
			} else {
				request.setAttribute("txtProductAuthor", productAuthor);

			}
			productBean.setProductAuthor(productAuthor);
			if (ValidationUtils.isEmpty(productPublisher)) {
				isError = true;
				request.setAttribute("productPublisher",
						"<font color=red>* Product Author is Required.......</font>");
			} else {
				request.setAttribute("txtProductPublisher", productPublisher);

			}
			productBean.setProductPublisher(productPublisher);
			if (ValidationUtils.isEmpty(productDesc)) {
				isError = true;
				request.setAttribute("productDesc",
						"<font color=red>* Product Author is Required.......</font>");
			} else {
				request.setAttribute("txtProductDesc", productDesc);

			}
			productBean.setProductDesc(productDesc);
			if (ValidationUtils.isEmpty(productStock)) {
				isError = true;
				request.setAttribute("productStock",
						"<font color=red>* Product Author is Required.......</font>");
			} else {
				request.setAttribute("txtProductStock", productStock);

			}
			productBean.setProductStock(productStock);
			if (ValidationUtils.isEmpty(productPrice)) {
				isError = true;
				request.setAttribute("productPrice",
						"<font color=red>* Product Author is Required.......</font>");
			} else {
				request.setAttribute("txtProductPrice", productPrice);

			}
			productBean.setProductPrice(productPrice);
			if (subCategoryId.equals("0")) {
				isError = true;
				request.setAttribute("subCategory",
						"<font color=red>* SubCategory is Required.......</font>");
			} else {
				request.setAttribute("", subCategoryId);

			}
			if (categoryId.equals("0")) {
				isError = true;
				request.setAttribute("category",
						"<font color=red>* Category is Required.......</font>");
			} else {
				request.setAttribute("", categoryId);

			}
			
			if (ValidationUtils.isEmpty(productDiscount)) {
				isError = true;
				request.setAttribute("productDiscount",
						"<script>showError('discountError','Please Enter Discount')</script>");
			} else {
				request.setAttribute("txtProductDiscount", productDiscount);
				productBean.setProductDiscount(productDiscount);
			}
			
			if (!ValidationUtils.validateNumber(productDiscount)) {
				isError = true;
				request.setAttribute("productDiscount",
						"<script>showError('discountError',' Enter valid discount')</script>");
			}
			
			productBean.setSubCategoryId(subCategoryId);
			productBean.setCategoryId(categoryId);
			productBean.setProductId(productId);
			productBean.setProductDiscount(productDiscount);
			/*productBean.setProductImage(productImage);*/
			productBean.setProductPurchases(productPurchases);
			if (isError) {

				request.setAttribute("productBean", productBean);
				request.getRequestDispatcher("productEdit.jsp").forward(
						request, response);
			} else {
				if (new ProductDAO().update(productBean)) {
					request.setAttribute("msgproduct",
							"product successfully updated");
					request.getRequestDispatcher("products.jsp").forward(
							request, response);
				} else {
					request.setAttribute("msgproduct", " failed to add product");
					request.getRequestDispatcher("productEdit.jsp").forward(
							request, response);
				}

			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
