package com.controller;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
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
import com.util.GenrateMathodsUtils;
import com.util.ValidationUtils;

public class ProductInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static BufferedImage resizeImageWithHint(
			BufferedImage originalImage, int type) {

		int oldh = originalImage.getHeight();
		int oldw = originalImage.getWidth();
		int newh, neww;
		if (oldh < 650) {
			neww = 500;
			newh = (oldh * 500) / oldw;
		} else {
			newh = 650;
			neww = (oldw * 650) / oldh;
		}
		BufferedImage resizedImage = new BufferedImage(neww, newh, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, neww, newh, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		return resizedImage;
	}

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
		String productImage = "";
		String productAuthor = "";
		String productPublisher = "";
		String productDesc = "";
		String productStock = "";
		String productPrice = "";
		String productPurchases = "";
		String productDiscount = "";
		String subCategoryId = "";

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
					} else if (fieldName.equals("txtProductDiscount")) {
						productDiscount = fieldValue;
						System.out.println("t " + productDiscount);
					}

				} else {
					fieldName = item.getFieldName();
					productImage = item.getName();
					productId = GenrateMathodsUtils.getRandomString(15);
					String absoluteDiskPath = "C:\\Inetpub\\vhosts\\bookberries.co.in\\httpdocs\\Images\\temp";
					file = new File(absoluteDiskPath + File.separator
							+ productId + ".png");
					if (productImage.isEmpty()) {
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

								BufferedImage originalImage = ImageIO
										.read(new File(absoluteDiskPath
												+ File.separator + productId
												+ ".png"));
								int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
										: originalImage.getType();
								String newabsoluteDiskPath = "C:\\Inetpub\\vhosts\\bookberries.co.in\\httpdocs\\Images\\";
								BufferedImage resizeImageHintJpg = resizeImageWithHint(
										originalImage, type);
								ImageIO.write(resizeImageHintJpg, "jpg",
										new File(newabsoluteDiskPath
												+ File.separator + productId
												+ ".png"));
								File delfile = new File(absoluteDiskPath
										+ File.separator + productId + ".png");
								delfile.delete();

								productImage = "y";
								productBean.setProductImage(productImage);
							} catch (Exception e) {
								e.printStackTrace();
							}
						else
							request.setAttribute(
									"file",
									"<font color=red>*Please upload files that end in types .png,.jpeg only.</font>");

					}
				}
			}
			if (ValidationUtils.isEmpty(productName)) {
				isError = true;
				request.setAttribute("productName",
						"<script>showError('nameError','Please Enter First Name')</script>");
			} else {
				request.setAttribute("txtProductName", productName);
				productBean.setProductName(productName);
			}

			if (ValidationUtils.isEmpty(productAuthor)) {
				isError = true;
				request.setAttribute("productAuthor",
						"<script>showError('authorError','Please Enter Author Name')</script>");
			} else {
				request.setAttribute("txtProductAuthor", productAuthor);
				productBean.setProductAuthor(productAuthor);
			}
			if (ValidationUtils.isEmpty(productPublisher)) {
				isError = true;
				request.setAttribute("productPublisher",
						"<script>showError('publisherError','Please Enter Publisher Name')</script>");
			} else {
				request.setAttribute("txtProductPublisher", productPublisher);
				productBean.setProductPublisher(productPublisher);
			}

			if (ValidationUtils.isEmpty(productDesc)) {
				isError = true;
				request.setAttribute("productDesc",
						"<script>showError('descError','Please Enter Description')</script>");
			} else {
				request.setAttribute("txtProductDesc", productDesc);
				productBean.setProductDesc(productDesc);
			}

			if (ValidationUtils.isEmpty(productStock)) {
				isError = true;
				request.setAttribute("productStock",
						"<script>showError('stockError','Please Enter Description')</script>");
			} else {
				request.setAttribute("txtProductStock", productStock);
				productBean.setProductStock(productStock);
			}

			if (!ValidationUtils.validateNumber(productStock)) {
				isError = true;
				request.setAttribute("productStock",
						"<script>showError('stockError',' Enter valid stock')</script>");
			}
			if (!ValidationUtils.validateNumber(productPrice)) {
				isError = true;
				request.setAttribute("productPrice",
						"<script>showError('priceError','Enter valid Price')</script>");
			}

			if (ValidationUtils.isEmpty(productPrice)) {
				isError = true;
				request.setAttribute("productPrice",
						"<script>showError('priceError','Please Enter Price')</script>");
			} else {
				request.setAttribute("txtProductPrice", productPrice);
				productBean.setProductPrice(productPrice);
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
			
			
			if (subCategoryId.equals("0")) {
				isError = true;
				request.setAttribute("subCategory",
						"<script>showError('subcategoryError','Please Sub Category')</script>");
			} else {
				request.setAttribute("", subCategoryId);
				productBean.setSubCategoryId(subCategoryId);
			}

			if (isError) {

				request.setAttribute("productBean", productBean);
				request.getRequestDispatcher("addproduct.jsp").forward(request,
						response);
			} else {
				productPurchases = "0";
				productBean.setProductId(productId);
				productBean.setProductImage(productImage);
				productBean.setProductPurchases(productPurchases);

				if (new ProductDAO().insert(productBean)) {
					request.setAttribute("msgproduct",
							"product successfully added");
					request.getRequestDispatcher("products.jsp").forward(
							request, response);
				} else {
					request.setAttribute("msgproduct", " failed to add product");
					request.getRequestDispatcher("addproduct.jsp").forward(
							request, response);
				}

			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
