$("a[href='customers.jsp']").attr("href", "#").attr("class", "active");
var ids = $("#idProvider").html().split("|");
var names = $("#nameProvider").html().split("|");
var emails = $("#emailProvider").html().split("|");
var mobiles = $("#mobileProvider").html().split("|");
var mobile2s = $("#mobile2Provider").html().split("|");
var addresses = $("#addressProvider").html().split("|");
var zipcodes = $("#pincodeProvider").html().split("|");
var admins = $("#adminProvider").html().split("|");
$(
		"#idProvider,#nameProvider,#emailProvider,#mobileProvider,#mobile2Provider,#addressProvider,#pincodeProvider,#adminProvider")
		.remove();
var myapp = angular.module("myApp", []);
myapp.controller("searchCtrl", searchcontroller);
myapp.controller("mainCtrl", function($scope) {
	$scope.customers = new Array();
	for ( var i = 0; i < ids.length; i++) {
		if (admins[i] == "y")
			admins[i] = "done";
		if (admins[i] == "n")
			admins[i] = "close";
		if (mobile2s[i] == "")
			mobile2s[i] = "N/A";
		$scope.customers[i] = {
			id : ids[i],
			name : names[i],
			email : emails[i],
			mobile : mobiles[i],
			mobile2 : mobile2s[i],
			address : addresses[i],
			zipcode : zipcodes[i],
			admin : admins[i]
		};
	}
});
$("body")
		.on(
				"click",
				".deletebutton",
				function() {
					hideError("custError");
					var id = $(this).parent().siblings("td:first-child")
							.children(".custid").html();
					var target = $(this).parent().parent();
					callAjax(
							{
								method : "customerDelete",
								customerId : id
							},
							function(data) {
								if (data == "1") {
									target.remove();
									var index = jQuery.inArray(id, ids);
									ids.splice(index, 1);
									names.splice(index, 1);
									emails.splice(index, 1);
									addresses.splice(index, 1);
									mobiles.splice(index, 1);
									mobile2s.splice(index, 1);
									zipcodes.splice(index, 1);
									admins.splice(index, 1);
								} else
									showError("custError",
											"Customer can't be deleted right now, try again later");
							});
				});
var adminid,adminadflag,admintarget;
$("body")
		.on(
				"click",
				".adminbutton",
				function() {
					callAjax(
							{
								method : "customerUpdate",
								customerId : adminid,
								isAdmin : adminadflag
							},
							function(data) {
								if (data == "1") {
									admintarget.html((adminadflag == "y") ? "done"
											: "close");
								} else
									showError("custError",
											"Customer can't be deleted right now, try again later");
							});
					$(this).siblings(".modelcloser").click();
				});
$(".searchCriteria").change(function(){
	var index=$(this).children("option:selected").index();
	var outputvalue=["name","email","address","mobile","mobile2","zipcode"];
	$("#"+outputvalue[index]+"Search").val($("#searchTrigger").val());
	$("#"+outputvalue[index]+"Search").trigger("input");
});
$("body").on("keyup","#searchTrigger",function(){
	var index=$(".searchCriteria").children("option:selected").index();
	var outputvalue=["name","email","address","mobile","mobile2","zipcode"];
	$("#"+outputvalue[index]+"Search").val($("#searchTrigger").val());
	$("#"+outputvalue[index]+"Search").trigger("input");
});
$("body").on("click",".adminmodelbutton",function(){
	hideError("custError");
	adminid = $(this).parent().siblings("td:first-child")
			.children(".custid").html();
	adminadflag = ($(this).html() == "close") ? "y" : "n";
	admintarget = $(this);
});