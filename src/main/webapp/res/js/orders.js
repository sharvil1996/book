$("a[href='orders.jsp']").attr("href", "#").attr("class", "active");
var myapp = angular.module("myApp", []);
var ids = $("#idProvider").html().split("|");
var names = $("#nameProvider").html().split("|");
var productNames = $("#productNameProvider").html().replace("&amp;", "&")
		.split("|");
var qtys = $("#qtyProvider").html().split("|");
var prices = $("#priceProvider").html().split("|");
var dates = $("#dateProvider").html().split("|");
var cancels = $("#isCancelledProvider").html().split("|");
var dispatchs = $("#isDispatchedProvider").html().split("|");
var delievers = $("#isDeliveredProvider").html().split("|");
var emails = $("#emailProvider").html().split("|");
var addresses = $("#addressProvider").html().replace(/&amp;/g, "&").split("|");
var mobiles = $("#mobileProvider").html().split("|");
$(
		"#idProvider,#nameProvider,#addressProvider,#emailProvider,#mobileProvider,#productNameProvider,#qtyProvider,#priceProvider,#dateProvider,#isCancelledProvider,#isDispatchedProvider,#isDeliveredProvider")
		.remove();
function emptyHandler() {
	if (ids[0] == ""||ids.length==0) {
		$(".bodyholder").css("display", "none");
		$(".messageholder").css("display", "block");
		$("body").css({"background-image": "url(res/imgs/defback.png)","background-size":"cover"});
	}else{
		$(".bodyholder").css("display", "block");
		$(".messageholder").css("display", "none");
		$("body").css({"background-image": "","background-size":""});
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
		if (dispatchs[i] == "n")
			dispatchs[i] = "close";
		if (dispatchs[i] == "y")
			dispatchs[i] = "done";
		if (delievers[i] == "n")
			delievers[i] = "close";
		if (delievers[i] == "y")
			delievers[i] = "done";
		if (cancels[i] == "n")
			cancels[i] = "close";
		if (cancels[i] == "y")
			cancels[i] = "done";
		if(cancels[i]=="done"){
			dispatchs[i]="";
			delievers[i]="";
		}
		$scope.orders[i] = {
			id : ids[i],
			name : names[i],
			products : productNames[i].split("~"),
			qty : qtys[i].split(","),
			price : prices[i],
			date : tempdate,
			cancel : cancels[i],
			dispatch : dispatchs[i],
			deliver : delievers[i],
			email : emails[i],
			address : addresses[i],
			mobile : mobiles[i]
		};
	}
});
$("body")
.on(
		"click",
		".dispatchbutton",
		function() {
			var id=$(this).parent().siblings(".orderid").html();
			var dispatchflag,deliverflag;
			if($(this).html()=="done"){
				dispatchflag="n";
				deliverflag="n";
			}
			if($(this).html()=="close")dispatchflag="y";
			if($(this).parent().siblings(".delid").children().html()=="close")deliverflag="n";
			var target=$(this);
			var deltarget=$(this).parent().siblings(".delid").children();
			callAjax(
					{
						method : "ordersUpdate",
						ordersId : id,
						ordersIsDispatched : dispatchflag,
						ordersIsDelivered : deliverflag 
					},
					function(data) {
						if (data == "1") {
							location.reload();
						} else
							showError("orderError",
									"Order can't be dispatched right now, try again later");
					});
		});
$("body")
.on(
		"click",
		".deliverbutton",
		function() {
			var id=$(this).parent().siblings(".orderid").html();
			var dispatchflag,deliverflag;
			if($(this).html()=="done"){
				deliverflag="n";
				dispatchflag=($(this).parent().siblings(".delid").children().html()=="close")?"n":"y";
			}
			if($(this).html()=="close"){
				deliverflag="y";
				dispatchflag="y";
			}
			var target=$(this);
			var distarget=$(this).parent().siblings(".disid").children();
			callAjax(
					{
						method : "ordersUpdate",
						ordersId : id,
						ordersIsDispatched : dispatchflag,
						ordersIsDelivered : deliverflag 
					},
					function(data) {
						if (data == "1") {
							location.reload();
						} else
							showError("orderError",
									"Order can't be delievered right now, try again later");
					});
		});
myapp.filter("myfilter",function(){
	return function(items,can,dis,del){
		var retval=new Array();
		var temp=new Array();
		if(can!=""&&can!=undefined){
			for(var i=0;i<items.length;i++){
				if(items[i].cancel==can)retval.push(items[i]);
			}
		}
		else retval=items;
		temp=retval;
		retval=new Array();
		if(dis!=""&&dis!=undefined){
			for(var i=0;i<temp.length;i++){
				if(temp[i].dispatch==dis)retval.push(temp[i]);
			}
		}
		else retval=temp;
		temp=retval;
		retval=new Array();
		if(del!=""&&del!=undefined){
			for(var i=0;i<temp.length;i++){
				if(temp[i].deliver==del)retval.push(temp[i]);
			}
		}
		else retval=temp;
		return retval;
	}
});