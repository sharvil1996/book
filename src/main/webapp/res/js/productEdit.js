var myapp = angular.module("myApp", []);
myapp.controller("searchCtrl", searchcontroller);
$("#cats")
		.change(
				function() {
					var val = $(this).val();
					$
							.ajax({
								url : "AjaxServlet",
								data : {
									method : "getSubCategory",
									categoryId : val
								},
								success : function(data) {
									$("#subcats").children("option").remove();
									$("#subcats")
											.append(
													"<option value='def'>Select Sub Category</option>");
									var temp = data.split("~")[0].split("|");
									var temp1 = data.split("~")[1].split("|");
									for ( var i = 0; i < temp.length; i++) {
										$("#subcats").append(
												"<option value='" + temp1[i]
														+ "'>" + temp[i]
														+ "</option>");
									}
									refreshSelect($(".subcatselect"));
								}
							});
				});
$(".cancelform").click(function() {
	window.open("products.jsp", "_self");
});
$(".photobutton").click(function() {
	$(".photoselector").click();
});
$(".photoselector").change(function() {
	var cols = $(this).val().split("\\");
	var name = cols.pop();
	$(".imagename").html(name);
});
$(".mainform").submit(function() {
	var erflag = true;
	var name = $("#namebox").val();
	var author = $("#authorbox").val();
	var publisher = $("#publisherbox").val();
	var desc = $("#descbox").val();
	var stock = $("#stockbox").val();
	var price = $("#pricebox").val();
	var cat = $("#cats").val();
	var subcat = $("#subcats").val();
	var dis=$("#disbox").val();
	if (name == "") {
		showError("nameError", "Please Enter Book Name");
		erflag = false;
	}
	if (author == "") {
		showError("authorError", "Please Enter Author Name");
		erflag = false;
	}
	if (publisher == "") {
		showError("publisherError", "Please Enter Publisher Name");
		erflag = false;
	}
	if (desc == "") {
		showError("descError", "Please Enter Book Description");
		erflag = false;
	}
	if (stock == "") {
		showError("stockError", "Please Enter Stock Value");
		erflag = false;
	}
	if (dis== "") {
		showError("discountError", "Please Enter Discount Value");
		erflag = false;
	}
	if (price == "") {
		showError("priceError", "Please Enter Price Value");
		erflag = false;
	}
	if (cat == "def") {
		showError("categoryError", "Please Select Book Category");
		erflag = false;
	}
	if (subcat == "def") {
		showError("subcategoryError", "Please Select Book Subcategory");
		erflag = false;
	}
	return erflag;
});
$("#namebox").keydown(function() {
	hideError("nameError");
});
$("#authorbox").keydown(function() {
	hideError("authorError");
});
$("#publisherbox").keydown(function() {
	hideError("publisherError");
});
$("#descbox").keydown(function() {
	hideError("descError");
});
$("#stockbox").keydown(function() {
	hideError("stockError");
}).focusout(function() {
	var stock = $(this).val();
	if (stock != "")
		callAjax({
			method : "validateNumber",
			number : stock
		}, function(data) {
			if (data == "1")
				showError("stockError", "Please Enter Valid Stock Value");
			else
				hideError("stockError");
		});
});
$("#disbox").keydown(function() {
	hideError("discountError");
}).focusout(function() {
	var dis = $(this).val();
	if (dis != "")
		callAjax({
			method : "validateNumber",
			number : dis
		}, function(data) {
			if (data == "1")
				showError("discountError", "Please Enter Valid Discount Value");
			else
				hideError("discountError");
		});
});
$("#pricebox").keydown(function() {
	hideError("priceError");
}).focusout(function() {
	var price = $(this).val();
	if (price != "")
		callAjax({
			method : "validateNumber",
			number : price
		}, function(data) {
			if (data == "1")
				showError("priceError", "Please Enter Valid Price Value");
			else
				hideError("priceError");
		});
});
$(".catslist").click(function(){
	hideError("categoryError");
});
$(".subcatselect").click(function(){
	hideError("subcategoryError");
});
$("#namebox,#authorbox,#publisherbox,#descbox").focusout(function() {
	var text = $(this).val();
	$(this).val(titleCaseSentence(text));
});