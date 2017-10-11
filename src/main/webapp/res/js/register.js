$("a[href='register.jsp']").attr("href", "#").attr("class", "active");
var myapp = angular.module("myApp", []);
myapp.controller("searchCtrl", searchcontroller);
$("#mainform").submit(function() {
	var fn = $("#fnbox").val();
	var ln = $("#lnbox").val();
	var email = $("#emailbox").val();
	var ps = $("#temppass").val();
	var address = $("#addressbox").val();
	var mob = $("#mobbox").val();
	var mob2 = $("#mob2box").val();
	var pincode = $("#selPincode").val();
	var erflag = true;
	if (fn == "") {
		showError("firstNameError", "Please Enter First Name");
		erflag = false;
	}
	if (ln == "") {
		showError("lastNameError", "Please Enter Last Name");
		erflag = false;
	}
	if (email == "") {
		showError("emailError", "Please Enter Email Address");
		erflag = false;
	}
	if (ps == "") {
		showError("passwordError", "Please Enter Password");
		erflag = false;
	}
	if (address == "") {
		showError("addressError", "Please Enter Address");
		erflag = false;
	}
	if (mob == "") {
		showError("mobile1Error", "Please Enter Mobile Number");
		erflag = false;
	}
	if (mob != "" && mob.length != 10) {
		showError("mobile1Error", "Please Enter 10-digit Mobile Number");
		erflag = false;
	}
	if (mob2 != "" && mob2.length != 10) {
		showError("mobile2Error", "Please Enter 10-digit Mobile Number");
		erflag = false;
	}
	if (pincode == "def") {
		showError("pinCodeError", "Please Select Zip Code");
		erflag = false;
	}
	return erflag;
});
$("#fnbox").keydown(function() {
	hideError("firstNameError");
});
$("#lnbox").keydown(function() {
	hideError("lastNameError");
});
$("#emailbox").keydown(function() {
	hideError("emailError");
});
$("#temppass").keydown(function() {
	hideError("passwordError");
});
$("#addressbox").keydown(function() {
	hideError("addressError");
});
$("#mobbox").keydown(function() {
	hideError("mobile1Error");
});
$("#mob2box").keydown(function() {
	hideError("mobile2Error");
});
$(".select").click(function() {
	hideError("pinCodeError");
});
$("#fnbox,#lnbox,#addressbox").focusout(function() {
	var value = $(this).val();
	$(this).val(titleCaseSentence(value));
});
$("#temppass").focusout(function() {
	var pass = $(this).val();
	var shaObj = new jsSHA("SHA-512", "TEXT");
	shaObj.update(pass);
	$("#psbox").val(shaObj.getHash("HEX"));
	$("#str").slideUp();
}).focusin(function(){
	$("#str").slideDown().css("display", "block");
}).keyup(function(){
	passwordstrength($(this).val());
});
$("#emailbox").focusout(function() {
	var em = $(this).val();
	if (em != "")
		callAjax({
			method : "validateEmail",
			email : em
		}, handleEmail);
});
$("#mobbox").focusout(function() {
	var mob = $(this).val();
	if (mob != "")
		callAjax({
			method : "validateNumber",
			number : mob
		}, function(data) {
			if (data == "1")
				showError("mobile1Error", "Please Enter Valid Mobile Number");
			else
				hideError("mobile1Error");
		});
});
$("#mob2box").focusout(function() {
	var mob = $(this).val();
	if (mob != "")
		callAjax({
			method : "validateNumber",
			number : mob
		}, function(data) {
			if (data == "1")
				showError("mobile2Error", "Please Enter Valid Mobile Number");
			else
				hideError("mobile2Error");
		});
});
$("#fnbox").focusout(function() {
	var fn = $(this).val();
	if (fn != "")
		callAjax({
			method : "validateText",
			text : fn
		}, function(data) {
			if (data == "1")
				showError("firstNameError", "Please Enter Valid Name");
			else
				hideError("firstNameError");
		});
});
$("#lnbox").focusout(function() {
	var ln = $(this).val();
	if (ln != "")
		callAjax({
			method : "validateText",
			text : ln
		}, function(data) {
			if (data == "1")
				showError("lastNameError", "Please Enter Valid Name");
			else
				hideError("lastNameError");
		});
});
function handleEmail(data) {
	if (data == "1") {
		showError("emailError", "Invalid Email Address");
	} else if (data == "2") {
		showError("emailError", "Email Address Already Exists");
	} else
		hideError("emailError");
}
$(".showpass").click(function() {
	if ($(this).siblings("input").attr("type") == "password") {
		$(this).css("opacity", "1");
		$(this).siblings("input").attr("type", "text");
	} else {
		$(this).css("opacity", "0.5");
		$(this).siblings("input").attr("type", "password");
	}
});
function passwordstrength(ps) {
	alphalow = 0, alphaup = 0, num = 0, sym = 0;
	status = 0;
	for (i = 0; i < ps.length; i++) {
		if (ps.charCodeAt(i) > 64 && ps.charCodeAt(i) < 91)
			alphaup++;
		else if (ps.charCodeAt(i) > 96 && ps.charCodeAt(i) < 123)
			alphalow++;
		else if (ps.charCodeAt(i) > 47 && ps.charCodeAt(i) < 58)
			num++;
		else
			sym++;
	}
	if (alphalow && alphaup && num && sym)
		status = 4;
	else if (alphalow && num && sym)
		status = 3;
	else if ((alphalow && num) || (alphalow && sym))
		status = 2;
	else
		status = 1;
	if (ps.length < 4)
		status = 1;
	color = "", strength = "";
	if (status == 4) {
		color = "rgba(60,118,61,1)";
		strength = "Very Strong";
	}
	if (status == 1) {
		color = "crimson";
		strength = "Very Weak";
	}
	if (status == 2) {
		color = "#ec971f";
		strength = "Weak";
	}
	if (status == 3) {
		color = "rgba(60,118,61,1)";
		strength = "Strong";
	}
	$("#str").html(strength);
	$("#str").css("color", color);
}