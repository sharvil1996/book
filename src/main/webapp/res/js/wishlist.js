$("a[href='wishlist.jsp']").attr("href", "#").attr("class", "active");
var myapp = angular.module("myApp", []);
var custid = $("#custId").html();
var ids = $("#idProvider").html().split("|");
var productnames = $("#productNameProvider").html().split("|");
var images = $("#imageProvider").html().split("|");
var productids = $("#productIdProvider").html().replace(/&amp;/g, "&").split(
		"|");
$(
		"#customerId,#idProvider,#productIdProvider,#imageProvider,#productNameProvider")
		.remove();
function emptyHandler() {
	if (ids[0] == ""||ids.length==0) {
		$(".bodyholder").css("display", "none");
		$(".messageholder").css("display", "inline-block");
	}else{
		$(".bodyholder").css("display", "inline-block");
		$(".messageholder").css("display", "none");
	}
}
emptyHandler();
if(custid!=undefined)refreshCart(custid);
myapp.controller("searchCtrl", searchcontroller);
myapp.controller("mainCtrl", function($scope) {
	$scope.wishlist = new Array();
	for ( var i = 0; i < ids.length; i++) {
		if (images[i] == "n")
			images[i] = "noimage.png";
		else
			images[i] = productids[i] + ".png";
		$scope.wishlist[i] = {
			id : ids[i],
			productname : productnames[i],
			image : images[i],
			productid : productids[i],
		}
	}
});
var delflag = 0;
$("body").on(
		"click",
		".wishbook",
		function() {
			if (delflag == 1) {
				delflag = 0;
				return;
			}
			window.open("BookDetailsServlet?productId="
					+ $(this).children().children(".prodid").html(), "_self");
		});
$("body")
		.on(
				"click",
				".deletebutton",
				function() {
					hideError("wishlistError");
					var prodid = $(this).siblings(".prodid").html();
					var target = $(this).parent().parent();
					callAjax(
							{
								method : "wishlistDelete",
								productId : prodid,
								customerId : custid
							},
							function(data) {
								if (data == "1") {
									target.remove();
									var index=productids.indexOf(prodid);
									ids.splice(index,1);
									productids.splice(index,1);
									productnames.splice(index,1);
									images.splice(index,1);
									emptyHandler();
									
								} else
									showError("wishlistError",
											"Book can't be deleted right now, try again later");
							});
					delflag = 1;
				});