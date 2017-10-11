$("a[href='logs.jsp']").attr("href", "#").attr("class", "active");
var myapp = angular.module("myApp", []);
var dates = $("#dateProvider").html().split("|");
var cnts = $("#cntProvider").html().split("|");
var tempdate = new Array();
for ( var i = 0; i < dates.length; i++) {
	var datebase = new Date(parseInt(dates[i], 10));
	tempdate[i] = datebase.getDate() + "/" + (datebase.getMonth() + 1) + "/"
			+ datebase.getFullYear();
}
$("#dateProvider,#cntProvider").remove();
myapp.controller("searchCtrl", searchcontroller);
myapp.controller("mainCtrl", function($scope) {
	$scope.logs = new Array();
	$scope.logs[0] = new Array();
	$scope.logs[1] = new Array();
	$scope.logs[2] = new Array();
	$scope.logs[3] = new Array();
	for ( var i = 0; i < dates.length; i++) {
		$scope.logs[0].push({
			date : tempdate[i],
			rawdate : parseInt(dates[i], 10),
			cnt : cnts[i]
		});
		i++;
		if (i >= dates.length)
			break;
		$scope.logs[1].push({
			date : tempdate[i],
			rawdate : parseInt(dates[i], 10),
			cnt : cnts[i]
		});
		i++;
		if (i >= dates.length)
			break;
		$scope.logs[2].push({
			date : tempdate[i],
			rawdate : parseInt(dates[i], 10),
			cnt : cnts[i]
		});
		i++;
		if (i >= dates.length)
			break;
		$scope.logs[3].push({
			date : tempdate[i],
			rawdate : parseInt(dates[i], 10),
			cnt : cnts[i]
		});
	}
});
myapp.filter("myfilter", function() {
	return function(items, fromdate, todate) {
		var retval = new Array();
		fromdate = parseInt(fromdate);
		todate = parseInt(todate);
		if (!isNaN(fromdate) && !isNaN(todate)) {
			for ( var i = 0; i < items.length; i++) {
				if (items[i].rawdate <= todate && items[i].rawdate >= fromdate)
					retval.push(items[i]);
			}
		}
		else return items;
		return retval;
	};
});
if (dates.length == 0)
	$(".log0pres").remove();
if (dates.length < 2)
	$(".log1pres").remove();
if (dates.length < 3)
	$(".log2pres").remove();
if (dates.length < 4)
	$(".log3pres").remove();
$(".fromdate,.todate").focusout(
		function() {
			var date = $(this).val();
			if (validateDate(date) == false && date != "")
				showError("dateError", "Invalid Date");
			else if ($(".fromdate").val() != "" && $(".todate").val() != ""
					&& $(".fromdate").val() != undefined
					&& $(".todate").val() != undefined) {
				hideError("dateError");
				var fromdate = $(".fromdate").val();
				var todate = $(".todate").val();
				if (validateDate(fromdate) != false
						&& validateDate(todate) != false) {
					fromdate = validateDate(fromdate).getTime();
					todate = validateDate(todate).getTime();
					$("#fromdate").val(fromdate);
					$("#todate").val(todate);
					$("#fromdate,#todate").trigger("input");
				} else
					showError("dateError", "Invalid Date");
			}
		});