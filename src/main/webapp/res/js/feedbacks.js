$("a[href='feedbacks.jsp']").attr("href", "#").attr("class", "active");
var myapp = angular.module("myApp", []);
var sugids = $("#sugIdProvider").html().split("|");
var custnames = $("#custNameProvider").html().split("|");
var descs = $("#descProvider").html().replace("&amp;","&").split("|");
var answers = $("#isAnsweredProvider").html().split("|");
$("#sugIdProvider,#custNameProvider,#descProvider,#isAnsweredProvider")
		.remove();
function emptyHandler() {
	if (sugids[0] == ""||sugids.length==0) {
		$(".bodyholder").css("display", "none");
		$(".messageholder").css("display", "block");
	}else{
		$(".bodyholder").css("display", "block");
		$(".messageholder").css("display", "none");
		$("body").css({"background-image": "","background-size":""});
	}
}
emptyHandler();
myapp.controller("searchCtrl", searchcontroller);
myapp.controller("mainCtrl", function($scope) {
	$scope.suggestions = new Array();
	for ( var i = 0; i < sugids.length; i++) {
		if (answers[i] == "y")
			answers[i] = "1";
		if (answers[i] == "n")
			answers[i] = "2";
		$scope.suggestions[i] = {
			id : sugids[i],
			custname : custnames[i],
			desc : descs[i],
			answer : answers[i]
		};
	}
});
$("body").on("click", ".suglist li", function() {
	var custname = $(this).children("h4").children(".custname").html();
	var sugid = $(this).children(".sugid").html();
	$(".replysugid").val(sugid);
	$(".replybox").attr("placeholder", "Reply to " + custname);
});
$(".filterOption").change(function() {
	if ($(this).val() == "Answered") {
		$(".filterBox").val("1");
		$(".filterBox").trigger("input");
	} else if ($(this).val() == "Unanswered") {
		$(".filterBox").val("2");
		$(".filterBox").trigger("input");
	} else {
		$(".filterBox").val("");
		$(".filterBox").trigger("input");
	}
});
$("body").on("click", ".deletebutton", function() {
	var sugid = $(this).parent().siblings(".sugid").html();
	var target=$(this).parent().parent();
	callAjax({
		method : "suggetionDelete",
		suggetionId : sugid
	}, function(data) {
		if(data=="1"){
			showToast("Feedback Removed Successfully");
			target.remove();
			$(".replysugid").val("");
			$(".replybox").attr("placeholder", "Reply here");
			var index=sugids.indexOf(sugid);
			sugids.splice(index,1);
			custnames.splice(index,1);
			descs.splice(index,1);
			answers.splice(index,1);
			emptyHandler();
		}
		else showToast("Can't Remove Feedback Now");
	});
});