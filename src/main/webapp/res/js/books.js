$("a[href='books.jsp']").attr("href", "#").attr("class", "active");
var myapp = angular.module("myApp", []);
var ids = $("#idProvider").html().split("|");
var names = $("#nameProvider").html().replace(/&amp;/g, "&").split("|");
var authors = $("#authorProvider").html().replace(/&amp;/g, "&").split("|");
var publishers = $("#publisherProvider").html().replace(/&amp;/g, "&").split(
		"|");
var prices = $("#priceProvider").html().split("|");
var discounts = $("#discountProvider").html().split("|");
var purchaseses = $("#purchasesProvider").html().split("|");
var images = $("#imageProvider").html().split("|");
var categorys = $("#categoryProvider").html().replace(/&amp;/g, "&").split("|");
var subcategorys = $("#subCategoryProvider").html().replace(/&amp;/g, "&")
		.split("|");
var links = $("#linkProvider").html().split("|");
var logstate = $("#logState").html();
var custid = $("#custId").html();
if (custid != undefined)
	refreshCart(custid);
$(
		"#idProvider,#nameProvider,#authorProvider,#publisherProvider,#priceProvider,#purchasesProvider,#imageProvider,#categoryProvider,#subCategoryProvider,#logState,#custId,#linkProvider")
		.remove();
var uncat = new Array;
$.each(categorys, function(i, el) {
	if ($.inArray(el, uncat) === -1)
		uncat.push(el);
});
for ( var i = 0; i < uncat.length; i++)
	$(".catlist").append(
			"<option value='" + uncat[i] + "'>" + uncat[i] + "</option>");
refreshSelect($(".catselect"));
var catselect = 0;
myapp.controller("searchCtrl", searchcontroller);
myapp.controller("mainCtrl", function($scope) {
	$scope.books = new Array;
	for ( var i = 0; i < ids.length; i++) {
		if ("y" == images[i])
			images[i] = links[i];
		if ("n" == images[i])
			images[i] = "http://www.book-berries.com/imgs/noimage.png";
		$scope.books[i] = {
			id : ids[i],
			name : names[i],
			author : authors[i],
			publisher : publishers[i],
			price : Math.ceil(parseInt(prices[i])
					- ((parseInt(prices[i]) * parseInt(discounts[i])) / 100)),
			purchases : parseInt(purchaseses[i]),
			image : images[i],
			category : categorys[i],
			subcatgory : subcategorys[i]
		}
	}
});
$("body").on(
		"click",
		".wishbook",
		function() {
			window.open("BookDetailsServlet?productId="
					+ $(this).children().children(".prodid").html(), "_self")
		});
var filterpoint=0;
$(".filter").click(function(){
	filterpoint=1;
});
$(".filterbutton").click(function() {
	if ($(".filter").hasClass("open")) {
		$(".filter").css("display", "none");
		$(".filter").removeClass("open");
		filterpoint=0;
	} else {
		$(".filter").css("display", "inline-block");
		$(".filter").addClass("open");
		filterpoint=1;
	}
});
$(".filtercloser").click(function() {
	$(".filter").css("display", "none");
	$(".filter").removeClass("open");
	filterpoint=0;
});
$(document).click(function(){
	if(filterpoint==1){
		filterpoint=2;
		return;
	}
	if(filterpoint==2){
		filterpoint=0;
		$(".filter").css("display", "none");
		$(".filter").removeClass("open");
	}
});
myapp.filter("bookfilter", function() {
	return function(item, minval, maxval, cat) {
		var temp, retval = new Array();
		maxval = parseInt(maxval);
		minval = parseInt(minval);
		for ( var i = 0; i < item.length; i++) {
			if (isNaN(minval) || isNaN(maxval))
				retval.push(item[i]);
			if (parseInt(item[i].price) <= parseInt(maxval)
					&& parseInt(item[i].price) >= parseInt(minval))
				retval.push(item[i]);
		}
		temp = retval;
		retval = new Array();
		for ( var i = 0; i < temp.length; i++) {
			if (cat == undefined || "All" == cat)
				retval.push(temp[i]);
			else if (temp[i].category == cat)
				retval.push(temp[i]);
		}
		return retval;
	}
});
$(".catlist").change(function() {
	$(".cathandle").val($(this).val());
	$(".cathandle").trigger("input");
});