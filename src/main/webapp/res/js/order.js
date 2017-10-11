var myapp = angular.module("myApp", []);
myapp.controller("searchCtrl", searchcontroller);
var count = 0;
var ids = "";
var names = "";
var prices = "";
var qtys = "";
var custid = $("#custId").html();
$(".carticon").remove();
if (getParameterByName("bookId") != null) {
	callAjax({
		method : "productDetail",
		productId : getParameterByName("bookId")
	}, function(data) {
		if (data == "1")
			window.open("books.jsp", "_self");
		else {
			cart[0] = {
				id : "",
				prodid : data.split("|")[0],
				qty : "1",
				price : data.split("|")[2],
				book : data.split("|")[1],
				disc : data.split("|")[3]
			}
			refreshPayment();
		}
	});
} else {
	refreshCart(custid);
}
$("body").on("click", ".qtyplu", function() {
	var value = parseInt($(this).siblings(".qty").html());
	if (value != 999) {
		$(this).siblings(".qty").html(value + 1);
		var cartind = parseInt($(this).siblings(".cartind").html());
		cart[cartind].qty = parseInt(cart[cartind].qty) + 1;
		refreshPayment();
	}
});
$("body").on("click", ".qtymin", function() {
	var value = parseInt($(this).siblings(".qty").html());
	if (value != 1) {
		$(this).siblings(".qty").html(value - 1);
		var cartind = parseInt($(this).siblings(".cartind").html());
		cart[cartind].qty = parseInt(cart[cartind].qty) - 1;
		refreshPayment();
	}
});
$("body").on("click", ".orderdelete", function() {
	if (cart.length == 1)
		window.open("books.jsp", "_self");
	var cartind = parseInt($(this).siblings(".cartind").html());
	cart.splice(cartind, 1);
	refreshPayment();
});
var ordprice, ordqty, ordproid, orddelcharge;
function refreshPayment() {
	$(".orderpiece").remove();
	ordprice = 0;
	ordqty = "";
	ordprodid = "";
	orddelcharge = 0;
	ordiscount = 0;
	for ( var i = 0; i < cart.length; i++) {
		$(".orders")
				.append(
						"<div class='orderpiece' style='padding: 5px 0;position: relative;'>"
								+ "<div>"
								+ "<span class='cartind' style='display:none;'>"
								+ i
								+ "</span><span class='prodid' style='display:none;'>"
								+ cart[i].prodid
								+ "</span><span style='font-weight: 700; font-size: 18px;'>"
								+ cart[i].book
								+ "</span><br> <span style='color: grey;'>Rs. "
								+ cart[i].price
								+ "</span>"
								+ "&nbsp;&nbsp;&nbsp; Qty : <span "
								+ "style='cursor: pointer; background-color: #e74c3c;color:white; padding: 0 7px; ' "
								+ "class='qtymin'>-</span>&nbsp;<span class='qty'>"
								+ cart[i].qty
								+ "</span>&nbsp;<span "
								+ "style='cursor: pointer; background-color: #e74c3c;color:white; padding: 0 6px; ' "
								+ "class='qtyplu'>+</span>"
								+ "<i class='material-icons orderdelete' style='position: absolute;right:10px;top:25px;cursor:pointer;'>close</i>"
								+ "</div>" + "</div>");
		ordprice += parseInt(cart[i].price) * parseInt(cart[i].qty);
		ordiscount += Math.floor(((parseInt(cart[i].price)
				* parseInt(cart[i].disc) / 100) * parseInt(cart[i].qty)));
		orddelcharge += parseInt(cart[i].qty) * 10;
		ordqty += cart[i].qty + ",";
		ordprodid += cart[i].prodid + ",";
	}
	$(".bookprice").html("Rs. " + ordprice);
	ordprodid = ordprodid.substr(0, ordprodid.length - 1);
	ordqty = ordqty.substr(0, ordqty.length - 1);
	ordprice += orddelcharge - ordiscount;
	$("#prodids").val(ordprodid);
	$("#prodqtys").val(ordqty);
	$(".delcharge").html("Rs. " + orddelcharge);
	$(".totalprice").html("Rs. " + ordprice);
	$(".discountcharge").html("Rs. " + ordiscount);
}
$(".placeorderbutton").click(function() {
	if (getParameterByName("bookId") == null) {
		for ( var i = 0; i < cart.length; i++) {
			callAjax({
				method : "cartDelete",
				cartId : cart[i].id,
			}, function(data) {
			});
		}
	}
	if ($("#prodids,#prodqtys").val() != "")
		$("#submitbutton").click();
});