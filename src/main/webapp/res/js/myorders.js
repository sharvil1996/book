$("a[href='myorders.jsp']").attr("href", "#").attr("class", "active");
var myapp = angular.module("myApp", []);
var ids = $("#idProvider").html().split("|");
var productNames = $("#productNameProvider").html().replace("&amp;", "&")
		.split("|");
var qtys = $("#qtyProvider").html().split("|");
var indprices = $("#individualPriceProvider").html().split("|");
var indpricenormals = $("#individualPriceNormalProvider").html().split("|");
var inddiscnormals = $("#individualDiscountProvider").html().split("|");
var prices = $("#priceProvider").html().split("|");
var dates = $("#dateProvider").html().split("|");
var cancels = $("#isCancelledProvider").html().split("|");
var dispatchs = $("#isDispatchedProvider").html().split("|");
var delievers = $("#isDeliveredProvider").html().split("|");
$(
		"#idProvider,#productNameProvider,#qtyProvider,#priceProvider,#dateProvider,#isCancelledProvider,#isDispatchedProvider,#isDeliveredProvider")
		.remove();
var custid = $("#custId").html();
if (custid != undefined)
	refreshCart(custid);
function emptyHandler() {
	if (ids[0] == "" || ids.length == 0) {
		$(".bodyholder").css("display", "none");
		$(".messageholder").css("display", "inline-block");
	} else {
		$(".bodyholder").css("display", "block");
		$(".messageholder").css("display", "none");
	}
}
emptyHandler();
myapp.controller("searchCtrl", searchcontroller);
myapp.controller("mainCtrl", function($scope) {
	$scope.orders = new Array();
	for ( var i = 0; i < ids.length; i++) {
		var tdate = new Date(parseInt(dates[i]));
		var tempdate = tdate.getDate() + "/" + (tdate.getMonth() + 1) + "/"
				+ tdate.getFullYear();
		var curstate = "";
		var statwidth = "";
		var orbutton = "";
		var orbuttonclass = "";
		var orbuttonstyle = "";
		var tempdelcharge = 0;
		var tempqtys = qtys[i].split(",");
		var tempdiscount = 0;
		var tempdiscounts = inddiscnormals[i].split("~");
		for ( var j = 0; j < tempqtys.length; j++) {
			tempdelcharge += parseInt(tempqtys[j]) * 10;
			tempdiscount += parseInt(tempdiscounts[j]) * parseInt(tempqtys[j]);
		}
		if (cancels[i] == "y") {
			curstate = "Cancelled";
			statwidth = "0%";
			orbutton = "Delete";
			orbuttonclass = "deleteorderbutton";
		} else if (delievers[i] == "y") {
			curstate = "Delievered";
			statwidth = "100%";
			orbutton = "Delete";
			orbuttonclass = "deleteorderbutton";
		} else if (dispatchs[i] == "y") {
			curstate = "Dispatched";
			statwidth = "50%";
			orbuttonstyle = "display:none;"
		} else {
			curstate = "Processing Order";
			statwidth = "5%";
			orbutton = "Cancel";
			orbuttonclass = "cancelorderbutton";
		}
		$scope.orders[i] = {
			id : ids[i],
			products : productNames[i].split("~"),
			qty : qtys[i].split(","),
			indprice : indprices[i].split("~"),
			indpricenormal : indpricenormals[i].split("~"),
			inddiscountnormal : inddiscnormals[i].split("~"),
			price : prices[i],
			date : tempdate,
			cancel : cancels[i],
			dispatch : dispatchs[i],
			deliver : delievers[i],
			status : curstate,
			statusWidth : statwidth,
			button : orbutton,
			buttonclass : orbuttonclass,
			buttonstyle : orbuttonstyle,
			delcharge : tempdelcharge,
			discount : tempdiscount
		};
	}
});
var orderid, ordertarget;
$("body").on("click", ".cancelorderbutton", function() {
	orderid = $(this).siblings(".orderid").html();
	ordertarget = $(this);
	$("#cancelmodel").addClass("open");
});
$("body").on("click", ".deleteorderbutton", function() {
	orderid = $(this).siblings(".orderid").html();
	ordertarget = $(this);
	$("#deletemodel").addClass("open");
});
$("body").on(
		"click",
		".cancelfinalbutton",
		function() {
			callAjax({
				method : "cancelOrder",
				ordersId : orderid
			}, function(data) {
				if (data == "1") {
					location.reload();
				} else {
					$("#errormodel").addClass("open");
					$("#errormodel").children("p").html(
							"Order can not be cancelled right now !");
				}
			});
		});
$("body").on(
		"click",
		".deletefinalbutton",
		function() {
			callAjax({
				method : "ordersDelete",
				ordersId : orderid
			}, function(data) {
				if (data == "1") {
					location.reload();
				} else {
					$("#errormodel").addClass("open");
					$("#errormodel").children("p").html(
							"Order can not be deleted right now !");
				}
			});
		});