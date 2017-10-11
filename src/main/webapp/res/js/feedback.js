$("a[href='feedback.jsp']").attr("href", "#").attr("class", "active");
var myapp = angular.module("myApp", []);
myapp.controller("searchCtrl", searchcontroller);
$("#sugbox").keyup(function() {
	hideError("feedbackError");
	var len = $(this).val().length;
	enteredText = $(this).val();
	numberOfLineBreaks = (enteredText.match(/\n/g) || []).length;
	$(".remainchars").html(len + numberOfLineBreaks);
});
$(".carticon").remove();
$("#mainform").submit(function(){
	var erflag=true;
	if($("#sugbox").val().length==0){
		showError("feedbackError","Please Enter Any Feedback Message");
		erflag=false;
	}
	if($("#sugbox").val().length<10&&$("#sugbox").val().length!=0){
		showError("feedbackError","Please Enter Minimum 10 Charactes in Message");
		erflag=false;
	}
	return erflag;
});