var myapp = angular.module("myApp", []);
myapp.controller("searchCtrl", searchcontroller);
$("#temppass").keyup(function() {
	var pass = $(this).val();
	var shaObj = new jsSHA("SHA-512", "TEXT");
	shaObj.update(pass);
	$("#psbox").val(shaObj.getHash("HEX"));
	hideError("passError");
	passwordstrength($(this).val());
}).focusin(function(){
	$("#str").slideDown().css("display", "block");
});
$(".showpass").click(function(){
	if($(this).siblings("input").attr("type")=="password"){
		$(this).css("opacity","1");
		$(this).siblings("input").attr("type","text");
	}
	else{
		$(this).css("opacity","0.5");
		$(this).siblings("input").attr("type","password");
	}
});
$("#mainform").submit(function(){
	var pass=$("#temppass").val();
	if(pass.length==0){
		showError("passError","Please Enter Password");
		return false;
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