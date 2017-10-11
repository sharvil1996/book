var myapp = angular.module("myApp", []);
myapp.controller("searchCtrl", searchcontroller);
$("#subbutton").click(function(){
	var em = $(".embox").val();
	if (em == "") {
		showError("forgotError", "Please enter email address");
		return false;
	}
	else{
		callAjax({
			method : "validateEmail",
			email : em
		}, handleEmail);
	}
});
function handleEmail(data) {
	if (data == "1") {
		showError("forgotError", "Invalid Email Address");
	} else{
		hideError("forgotError");
		$("#mainbutton").click();
	}
}
$(".embox").keydown(function(){
	hideError("forgotError");
});