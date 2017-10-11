$("a[href='category.jsp']").attr("href", "#").attr("class", "active");
var myapp = angular.module("myApp", []);
var catids = $("#catIdProvider").html().split("|");
var catnames = $("#catNameProvider").html().replace("&amp;", "&").split("|");
var subcatids;
var subcatnames;
$("#catIdProvider,#catNameProvider").remove();
myapp.controller("searchCtrl", searchcontroller);
myapp.controller("mainCtrl", function($scope) {
	$scope.category = new Array();
	for ( var i = 0; i < catids.length; i++) {
		$scope.category[i] = {
			id : catids[i],
			name : catnames[i]
		}
	}
});
$("body").on("click", ".catlist li", function() {
	var catid = $(this).children(".catid").html();
	callAjax({
		method : "getSubCategory",
		categoryId : catid
	}, handleSubcategory);
});
function handleSubcategory(data) {
	if (data != "") {
		var subnames = data.split("~")[0].replace("&amp;", "&").split("|");
		var subids = data.split("~")[1].split("|");
		subcatids = subids;
		subcatnames = subnames;
		$(".subcatlist").children().remove();
		var parentid = $(".catlist").children("li.active").children(".catid")
				.html();
		for ( var i = 0; i < subnames.length; i++) {
			$(".subcatlist")
					.append(
							"<li><span class='subname'>"
									+ subnames[i]
									+ "</span><span class='subid' style='display:none;'>"
									+ subids[i]
									+ "</span><span class='catid' style='display:none;'>"
									+ parentid
									+ "</span><input type='text'"
									+ "placeholder='Subcategory Name' class='myinput small'"
									+ "style='padding: 1px 10px; margin: 0; text-align: center; width: 150px; display: none;'"
									+ "maxlength='50' /><div style='float:right;'>"
									+ "<i class='material-icons subcateditbutton tooltip' toolmsg='Edit' style='cursor:pointer;'>edit</i> <i class='material-icons subcatdeletebutton tooltip' toolmsg='Remove' style='cursor:pointer;'>delete</i><i"
									+ " class='material-icons subcatdonebutton tooltip' toolmsg='Update' style='display: none;cursor:pointer;'>done</i>"
									+ "<i class='material-icons subcatclosebutton tooltip' toolmsg='Cancel' style='display: none;cursor:pointer;'>close</i></div></li>");
		}
	} else {
		$(".subcatlist").children().remove();
	}
}
$("body")
		.on(
				"click",
				".catdeletebutton",
				function() {
					hideError("cataddError");
					var id = $(this).parent().siblings(".catid").html();
					var target = $(this).parent().parent();
					callAjax(
							{
								method : "categoryDelete",
								categoryId : id
							},
							function(data) {
								if (data == "1") {
									target.remove();
									var index = jQuery.inArray(id, catids);
									catids.splice(index, 1);
									catnames.splice(index, 1);
								} else
									showError("cataddError",
											"Category can't be deleted right now, try again later");
							});
				});
$("body")
		.on(
				"click",
				".subcatdeletebutton",
				function() {
					hideError("subcataddError");
					var id = $(this).parent().siblings(".subid").html();
					var target = $(this).parent().parent();
					callAjax(
							{
								method : "subCategoryDelete",
								subCategoryId : id
							},
							function(data) {
								if (data == "1") {
									target.remove();
									var index = jQuery.inArray(id, subcatids);
									subcatids.splice(index, 1);
									subcatnames.splice(index, 1);
								} else
									showError("subcataddError",
											"Subcategory can't be deleted right now, try again later");
							});
				});
$("#catbox").focusout(function() {
	var cat = $(this).val();
	$(this).val(titleCaseSentence(cat));
});
$("#addcatbutton")
		.click(
				function() {
					hideError("cataddError");
					var cat = $("#catbox").val();
					if (cat == "") {
						showError("cataddError", "Please Enter Category");
					} else if (jQuery.inArray(cat, catnames) != -1) {
						showError("cataddError", "Category Already Exists");
					} else {
						hideError("cataddError");
						callAjax(
								{
									method : "categoryInsert",
									categoryName : cat
								},
								function(data) {
									if (data == "1")
										location.reload();
									else
										showError("cataddError",
												"Category can't be added right now, try again later");
								});
					}
				});
$("#subcatbox").focusout(function() {
	var subcat = $(this).val();
	$(this).val(titleCaseSentence(subcat));
});
$("#addsubcatbutton")
		.click(
				function() {
					hideError("subcataddError");
					var subcat = $("#subcatbox").val();
					if ($(".catlist li.active")[0]) {
						var catid = $(".catlist li.active").children(".catid")
								.html();
						if (subcat == "") {
							showError("subcataddError",
									"Please Enter Subcategory");
						} else if (jQuery.inArray(subcat, subcatnames) != -1) {
							showError("subcataddError",
									"Subcategory Already Exists");
						} else {
							hideError("subcataddError");
							callAjax(
									{
										method : "subCategoryInsert",
										subCategoryName : subcat,
										categoryId : catid
									},
									function(data) {
										if (data == "1")
											location.reload();
										else
											showError("subcataddError",
													"Subcategory can't be added right now, try again later");
									});
						}
					} else {
						showError("subcataddError",
								"Please Select Any One of Category");
					}
				});
$("#catbox").keydown(function() {
	hideError("cataddError");
	disableedit();
});
$("#subcatbox").keydown(function() {
	hideError("subcataddError");
	disableedit();
});
function disableedit() {
	$(".cateditbutton,.catdeletebutton,.subcateditbutton,.subcatdeletebutton")
			.css("display", "inline-block");
	$(".catdonebutton,.catclosebutton,.subcatdonebutton,.subcatclosebutton")
			.css("display", "none");
	$(".myinput.small").css("display", "none");
	$(".myinput.small").siblings(".catname,.subname").css("display",
			"inline-block");
}
$("body").on(
		"click",
		".cateditbutton",
		function() {
			disableedit();
			hideError("cataddError");
			$(this).css("display", "none");
			$(this).siblings(".catdeletebutton").css("display", "none");
			$(this).siblings(".catdonebutton,.catclosebutton").css("display",
					"inline-block");
			$(this).parent().siblings(".myinput")
					.css("display", "inline-block");
			$(this).parent().siblings(".catname").css("display", "none");
			$(this).parent().siblings(".myinput").val(
					$(this).parent().siblings(".catname").html()).focus();
		});
$("body").on(
		"click",
		".subcateditbutton",
		function() {
			disableedit();
			hideError("subcataddError");
			$(this).css("display", "none");
			$(this).siblings(".subcatdeletebutton").css("display", "none");
			$(this).siblings(".subcatdonebutton,.subcatclosebutton").css(
					"display", "inline-block");
			$(this).parent().siblings(".myinput")
					.css("display", "inline-block");
			$(this).parent().siblings(".subname").css("display", "none");
			$(this).parent().siblings(".myinput").val(
					$(this).parent().siblings(".subname").html()).focus();
		});
$("body").on("click", ".catclosebutton,.subcatclosebutton", function() {
	hideError("cataddError");
	hideError("subcataddError");
	disableedit();
});
$("body")
		.on(
				"click",
				".catdonebutton",
				function() {
					hideError("cataddError");
					var cat = $(this).parent().siblings(".myinput").val();
					var catid = $(this).parent().siblings(".catid").html();
					var target = $(this).parent().siblings(".catname");
					var focusbox = $(this).parent().siblings(".myinput");
					if (cat == "") {
						showError("cataddError", "Please Enter Category");
						focusbox.focus();
					} else if (jQuery.inArray(cat, catnames) != -1) {
						showError("cataddError", "Category Already Exists");
						focusbox.focus();
					} else {
						hideError("cataddError");
						callAjax(
								{
									method : "categoryUpdate",
									categoryId : catid,
									categoryName : cat
								},
								function(data) {
									if (data == "1") {
										disableedit();
										target.html(cat);
									} else
										showError("cataddError",
												"Category can't be updated right now, try again later");
									focusbox.focus();
								});
					}
				});
$("body")
		.on(
				"click",
				".subcatdonebutton",
				function() {
					hideError("subcataddError");
					var subcat = $(this).parent().siblings(".myinput").val();
					var subcatid = $(this).parent().siblings(".subid").html();
					var target = $(this).parent().siblings(".subname");
					var focusbox = $(this).parent().siblings(".myinput");
					if (subcat == "") {
						showError("subcataddError", "Please Enter Subcategory");
						focusbox.focus();
					} else if (jQuery.inArray(subcat, subcatnames) != -1) {
						showError("subcataddError",
								"Subcategory Already Exists");
						focusbox.focus();
					} else {
						hideError("subcataddError");
						callAjax(
								{
									method : "subCategoryUpdate",
									subCategoryId : subcatid,
									subCategoryName : subcat
								},
								function(data) {
									if (data == "1") {
										disableedit();
										target.html(subcat);
									} else
										showError("subcataddError",
												"Subcategory can't be updated right now, try again later");
									focusbox.focus();
								});
					}
				});