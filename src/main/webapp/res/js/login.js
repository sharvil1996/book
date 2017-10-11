$("a[href='login.jsp']").attr("href", "#").attr("class", "active");
var myapp = angular.module("myApp", []);
myapp.controller("searchCtrl", searchcontroller);
$("#embox,#psbox").keydown(function() {
	hideError("loginError");
});
$(".mainform").submit(function() {
	var em = $("#embox").val();
	var ps = $("#temppass").val();
	if (ps == "" || em == "") {
		showError("loginError", "Please enter all details");
		return false;
	}
});
$("#temppass").keyup(function() {
	var pass = $(this).val();
	var shaObj = new jsSHA("SHA-512", "TEXT");
	shaObj.update(pass);
	$("#psbox").val(shaObj.getHash("HEX"));
});

function handleEmail(data) {
	if (data == "1") {
		showError("loginError", "Invalid Email Address");
	} else{
		hideError("loginError");
	}
}