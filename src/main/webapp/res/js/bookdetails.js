var myapp = angular.module("myApp", []);
var id = $("#proid").html();
var image = $("#proimg").html();
var logstate = $("#logState").html();
var custid = $("#custid").html();
var disc = parseInt($(".disc").html());
var orgprice = parseInt($(".price").html());
$(".orgprice").html("Rs. " + (Math.ceil(orgprice - (orgprice * disc) / 100)));
$("#proid,#proimg,#logState,#custid").remove();
function emptyHandler() {
	if (id == "null") {
		$(".bodyholder").css("display", "none");
		$(".messageholder").css("display", "inline-block");
	} else {
		$(".bodyholder").css("display", "block");
		$(".messageholder").css("display", "none");
		callAjax({
			method : "wishlistCheck",
			productId : id,
			customerId : custid
		}, function(data) {
			if (data == "0") {
				$(".wishlistbutton").html("bookmark_border");
				$(".wishlabel").html("Add to Wishlist");
			} else {
				$(".wishlistbutton").html("bookmark");
				$(".wishlabel").html("Remove from Wishlist");
			}
		});
	}
}
emptyHandler();
if (custid != undefined)
	refreshCart(custid);
myapp.controller("searchCtrl", searchcontroller);
myapp.controller("mainCtrl", function($scope) {
	$scope.id = id;
	if (image == "y")
		$scope.image = id + ".png";
	else
		$scope.image = "noimage.png";
});
if (logstate == "1") {
	$(".actionbuttons")
			.append(
					"<a href='order.jsp?bookId={{id}}' class='mybutton'>Buy Now</a>&nbsp;&nbsp;<a href='#' class='mybutton addcartbutton'>Add To Cart</a>");
} else {
	$(".actionbuttons")
			.append(
					"<a href='login.jsp?refer=order.jsp?bookId={{id}}' class='mybutton'>Buy Now</a>&nbsp;&nbsp;<a href='login.jsp?refer=BookDetailsServlet?productId={{id}}' class='mybutton'>Add To Cart</a>");
}
$("body").on("click", ".addcartbutton", function() {
	callAjax({
		method : "cartInsert",
		productId : id,
		customerId : custid,
		quantity : "1"
	}, function(data) {
		if (data == "1") {
			refreshCart(custid);
		} else
			$("#errorModel").addClass("open");
	});
});
$("body").on("click", ".wishlistbutton", function() {
	var stat = $(this).html();
	var target = $(this);
	if (stat == "bookmark_border") {
		callAjax({
			method : "wishlistInsert",
			productId : id,
			customerId : custid
		}, function(data) {
			if (data == "1") {
				showToast("Added to Wishlist");
				target.html("bookmark");
				$(".wishlabel").html("Remove from Wishlist");
			} else
				showToast("Can't Add to Wishlist");
		});
	} else if (stat == "bookmark") {
		callAjax({
			method : "wishlistDelete",
			productId : id,
			customerId : custid
		}, function(data) {
			if (data == "1") {
				showToast("Removed from Wishlist");
				target.html("bookmark_border");
				$(".wishlabel").html("Add to Wishlist");
			} else
				showToast("Can't Remove from Wishlist");
		});
	}
});