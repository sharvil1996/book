$("a[href='dashBoardAdmin.jsp']").attr("href", "#").attr("class", "active");
var myapp = angular.module("myApp", []);
myapp.controller("searchCtrl", searchcontroller);