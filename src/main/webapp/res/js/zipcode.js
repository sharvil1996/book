$("a[href='zipcode.jsp']").attr("href", "#").attr("class", "active");
var myapp = angular.module("myApp", []);
var zips = $("#numberProvider").html().split("|");
$("#numberProvider,#idProvider").remove();
myapp.controller("searchCtrl", searchcontroller);
myapp.controller("mainCtrl", function($scope) {
	$scope.zipcodes = new Array();
	for ( var i = 0; i < zips.length; i++) {
		$scope.zipcodes[i] = {
			code : zips[i]
		}
	}
});
$("#pinbox").keydown(function() {
	hideError("pinaddError");
	disableedit();
});
$("body").on("keydown", ".myinput.small", function() {
	hideError("pinaddError");
});
$("body")
		.on(
				"click",
				".deletebutton",
				function() {
					hideError("pinaddError");
					var zip = $(this).parent().siblings(".mainzip").html();
					var target = $(this).parent().parent().parent();
					callAjax(
							{
								method : "pincodeDelete",
								pincode : zip
							},
							function(data) {
								if (data == "1") {
									target.remove();
									var index = jQuery.inArray(zip, zips);
									zips.splice(index, 1);
								} else
									showError("pinaddError",
											"Zip code can't be deleted right now, try again later");
							});
				});
function disableedit() {
	$(".mainzip").css("display", "inline-block");
	$(".mainzip").siblings(".myinput").css("display", "none");
	$(".closebutton,.donebutton").css("display", "none");
	$(".editbutton,.deletebutton").css("display", "inline-block");
}
var oldzip;
$("body")
		.on(
				"click",
				".donebutton",
				function() {
					hideError("pinaddError");
					var newzip = $(this).parent().siblings(".myinput").val();
					var target = $(this).parent().siblings(".myinput");
					var target1 = $(this).parent().siblings(".mainzip");
					var focustarget = $(this).parent().siblings(".myinput");
					if (newzip.length != 6) {
						showError("pinaddError", "Please Enter Valid Zip Code");
						focustarget.focus();
					} else if (jQuery.inArray(newzip, zips) != -1) {
						showError("pinaddError", "Zip Code Already Exists");
						focustarget.focus();
					} else {
						callAjax(
								{
									method : "validateNumber",
									number : newzip
								},
								function(data) {
									if (data == "1") {
										showError("pinaddError",
												"Please Enter Valid Zip Code");
										focustarget.focus();
									} else {
										hideError("pinaddError");
										callAjax(
												{
													method : "pincodeUpdate",
													oldPincode : oldzip,
													newPincode : newzip
												},
												function(data) {
													if (data == "1") {
														disableedit();
														target.val(newzip);
														target1.html(newzip);
														var index = jQuery
																.inArray(
																		oldzip,
																		zips);
														zips[index] = newzip;
													} else {
														showError(
																"pinaddError",
																"Zip code can't be added right now, try again later");
														focustarget.focus();
													}
												});
									}
								});
					}
				});
$("body").on("click", ".closebutton", function() {
	disableedit();
	hideError("pinaddError");
});
$("body").on(
		"click",
		".editbutton",
		function() {
			hideError("pinaddError");
			disableedit();
			$(this).parent().siblings(".myinput")
					.css("display", "inline-block").focus();
			$(this).parent().siblings(".mainzip").css("display", "none");
			$(this).siblings(".donebutton,.closebutton").css("display",
					"inline-block");
			$(this).css("display", "none");
			$(this).siblings(".deletebutton").css("display", "none");
			$(this).parent().siblings(".myinput").val(
					$(this).parent().siblings(".mainzip").html());
			oldzip = $(this).parent().siblings(".myinput").val();
		});
$("#addbutton")
		.click(
				function() {
					hideError("pinaddError");
					var zip = $("#pinbox").val();
					if (zip.length != 6)
						showError("pinaddError", "Please Enter Valid Zip Code");
					else if (jQuery.inArray(zip, zips) != -1) {
						showError("pinaddError", "Zip Code Already Exists");
					} else {
						callAjax(
								{
									method : "validateNumber",
									number : zip
								},
								function(data) {
									if (data == "1")
										showError("pinaddError",
												"Please Enter Valid Zip Code");
									else {
										hideError("pinaddError");
										callAjax(
												{
													method : "pincodeInsert",
													pincode : zip
												},
												function(data) {
													if (data == "1")
														location.reload();
													else
														showError(
																"pinaddError",
																"Zip code can't be added right now, try again later");
												});
									}
								});
					}
				});