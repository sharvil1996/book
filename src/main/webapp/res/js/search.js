var bookids = $("#bookIdProvider").html().split("|");
var booknames = $("#bookNameProvider").html().split("|");
$("#bookIdProvider,#bookNameProvider").remove();
function searchcontroller($scope) {
	$scope.searchbooks=new Array();
	for(var i=0;i<bookids.length;i++){
		$scope.searchbooks[i]={
			id:bookids[i],
			name:booknames[i]
		};
	}
}
$("body").on("click",".sugbox li",function(){
	window.open("BookDetailsServlet?productId="
			+ $(this).children(".bookid").html(), "_self");
});