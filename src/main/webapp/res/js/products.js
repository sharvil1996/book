$("a[href='products.jsp']").attr("href", "#").attr("class", "active");
var myapp = angular.module("myApp", []);
var ids = $("#idProvider").html().split("|");
var names = $("#nameProvider").html().split("|");
var authors = $("#authorProvider").html().replace("&amp;", "&").split("|");
var publishers = $("#publisherProvider").html().replace("&amp;", "&")
		.split("|");
var descs = $("#descProvider").html().replace("&amp;", "&").split("|");
var stocks = $("#stockProvider").html().split("|");
var prices = $("#priceProvider").html().split("|");
var diss = $("#discountProvider").html().split("|");
var purchaseses = $("#purchasesProvider").html().split("|");
var images = $("#imageProvider").html().split("|");
var categorys = $("#categoryProvider").html().replace("&amp;", "&").split("|");
var subcategorys = $("#subCategoryProvider").html().replace("&amp;", "&")
		.split("|");
$(
		"#idProvider,#nameProvider,#authorProvider,#publisherProvider,#descProvider,#stockProvider,#priceProvider,#purchasesProvider,#imageProvider,#categoryProvider,#subCategoryProvider")
		.remove();
myapp.controller("searchCtrl", searchcontroller);
myapp.controller("mainCtrl", function($scope) {
	$scope.products = new Array();
	for ( var i = 0; i < ids.length; i++) {
		if(images[i]=="y")images[i]=ids[i]+".png";
		if(images[i]=="n")images[i]="noimage.png";
		$scope.products[i] = {
			id : ids[i],
			author : authors[i],
			name : names[i],
			publisher : publishers[i],
			desc : descs[i],
			stock : stocks[i],
			price : prices[i],
		    dis : diss[i],
			purchases : purchaseses[i],
			image : images[i],
			category : categorys[i],
			subcategory : subcategorys[i]
		};
	}
});
var prodid, prodtarget;
$("body").on("click", ".deletemodelbutton", function() {
	prodid = $(this).parent().siblings(".prodid").html();
	prodtarget = $(this).parent().parent().parent();
});
$("body")
		.on(
				"click",
				".deletefinalbutton",
				function() {
					var target = $(this).parent().parent();
					callAjax(
							{
								method : "productDelete",
								productId : prodid
							},
							function(data) {
								if (data == "1") {
									target.remove();
									var index = jQuery.inArray(prodid, ids);
									ids.splice(index, 1);
									names.splice(index, 1);
									authors.splice(index, 1);
									publishers.splice(index, 1);
									prices.splice(index, 1);
									stocks.splice(index, 1);
									images.splice(index, 1);
									purchaseses.splice(index, 1);
									descs.splice(index, 1);
									categorys.splice(index, 1);
									subcategorys.splice(index, 1);
									location.reload();
								} else
									$("#errormodel").addClass("open");
							});
				});
$(".searchCriteria").change(function(){
	var index=$(this).children("option:selected").index();
	var outputvalue=["name","author","publisher","category","subcategory"];
	$("#"+outputvalue[index]+"Search").val($("#searchTrigger").val());
	$("#"+outputvalue[index]+"Search").trigger("input");
});
$("body").on("keyup","#searchTrigger",function(){
	var index=$(".searchCriteria").children("option:selected").index();
	var outputvalue=["name","author","publisher","category","subcategory"];
	$("#"+outputvalue[index]+"Search").val($("#searchTrigger").val());
	$("#"+outputvalue[index]+"Search").trigger("input");
});