function titleCaseSentence(str) {
	var capflag = true;
	var final = "";
	if (str.length > 0) {
		for (i = 0; i < str.length; i++) {
			if (capflag) {
				final += str[i].toUpperCase();
				capflag = false;
			} else
				final += str[i].toLowerCase();
			if (str[i] == ' ' || str[i] == '.')
				capflag = true;
		}
	}
	return final;
}
function getParameterByName(name, url) {
	if (!url)
		url = window.location.href;
	name = name.replace(/[\[\]]/g, "\\$&");
	var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"), results = regex
			.exec(url);
	if (!results)
		return null;
	if (!results[2])
		return '';
	return decodeURIComponent(results[2].replace(/\+/g, " "));
}
var cartpoint = 0;
$(".carticon").click(function() {
	if ($(".cartcount").html() != "0") {
		$(".cart").show();
		cartpoint = 1;
	}
});
$(".cart").click(function() {
	cartpoint = 1;
});
$(document).click(function() {
	if (cartpoint == 1) {
		cartpoint = 2;
		return;
	}
	if (cartpoint == 2) {
		cartpoint = 0;
		$(".cart").hide();
	}
});
var cart = new Array();
var cartqty = 0, cartprice = 0, cartdelcharge = 0;
$(".cartcloser").click(function() {
	$(".cart").hide();
	cartpoint = 0;
});
$("body").on("click", ".qtyplus", function() {
	var value = parseInt($(this).siblings(".qty").html());
	if (value != 999) {
		var prodid = $(this).siblings(".prodid").html();
		callAjax({
			method : "cartUpdate",
			productId : prodid,
			customerId : custid,
			quantity : value + 1
		}, function(data) {
			if (data == "1")
				refreshCart(custid);
		});
	}
});
$("body").on("click", ".qtyminus", function() {
	var value = parseInt($(this).siblings(".qty").html());
	if (value != 1) {
		var prodid = $(this).siblings(".prodid").html();
		callAjax({
			method : "cartUpdate",
			productId : prodid,
			customerId : custid,
			quantity : value - 1
		}, function(data) {
			if (data == "1")
				refreshCart(custid);
		});
	}
});
$("body").on("click", ".cartdelete", function() {
	var cid = $(this).siblings(".cartid").html();
	callAjax({
		method : "cartDelete",
		cartId : cid,
	}, function(data) {
		if (data == "1") {
			if ($(".cartpiece").length == 1) {
				$(".cart").hide();
				cartpoint = 0;
			}
			refreshCart(custid);
		}
	});
});
function refreshCart(id) {
	callAjax(
			{
				method : "cartList",
				customerId : id
			},
			function(data) {
				$(".cartpiece").remove();
				cartprice = 0;
				cartqty = 0;
				cartdelcharge = 0;
				cartdiscount = 0;
				$(".cartbookprice").html("Rs. 0");
				$(".cartdelcharge").html("Rs. 0");
				$(".cartpayable").html("Rs. 0");
				$(".cartcount").html("0");
				$(".cartdiscount").html("0");
				if (data != "0") {
					cart = new Array();
					var prodids = data.split("~")[0].split("|");
					var qtys = data.split("~")[1].split("|");
					var prices = data.split("~")[2].split("|");
					var books = data.split("~")[3].split("|");
					var ids = data.split("~")[4].split("|");
					var discs = data.split("~")[5].split("|");
					for ( var i = 0; i < ids.length; i++) {
						cart[i] = {
							id : ids[i],
							prodid : prodids[i],
							qty : qtys[i],
							price : parseInt(prices[i]),
							book : books[i],
							disc : discs[i]
						}
						$(".cartbooks")
								.append(
										"<div class='cartpiece' style='padding: 5px 0;position: relative;'>"
												+ "<span style='display:none;' class='cartid'>"
												+ cart[i].id
												+ "</span>"
												+ "<span style='display:none;' class='prodid'>"
												+ cart[i].prodid
												+ "</span><span style='font-weight: 700;'>"
												+ cart[i].book
												+ "</span><br> <span"
												+ " style='color: grey;'>Rs. "
												+ cart[i].price
												+ "</span>&nbsp;&nbsp;&nbsp; Qty : <span "
												+ " style=' cursor: pointer;background-color: #e74c3c;color:white;padding:0 7px;' "
												+ " class='qtyminus'>-</span>&nbsp;<span class='qty'>"
												+ cart[i].qty
												+ "</span>&nbsp;<span "
												+ " style='cursor: pointer; background-color: #e74c3c;color:white;padding:0 6px;' "
												+ "class='qtyplus'>+</span>"
												+ "<i class='material-icons cartdelete' style='position: absolute;right:10px;top:25px;cursor:pointer;'>close</i>"
												+ "</div>");
						cartprice += parseInt(cart[i].price)
								* parseInt(cart[i].qty);
						cartqty += parseInt(cart[i].qty);
						cartdiscount += Math
								.floor(((parseInt(prices[i]) * parseInt(discs[i])) / 100)
										* parseInt(cart[i].qty));
					}
					$(".cartbookprice").html("Rs. " + cartprice);
					cartdelcharge = cartqty * 10;
					cartprice += cartdelcharge - cartdiscount;
					$(".cartdelcharge").html("Rs. " + cartdelcharge);
					$(".cartpayable").html("Rs. " + cartprice);
					$(".cartcount").html(ids.length);
					$(".cartdiscount").html("Rs. " + cartdiscount);
					try {
						refreshPayment();
					} catch (e) {

					}
				}
			});
}
/*
 * $(window).scroll(function() { var top = parseInt($(window).scrollTop()); if
 * (top != 0) $(".navpan").addClass("shrink"); else
 * $(".navpan").removeClass("shrink"); });
 */
var navtrayid = 0;
$(".trayicon").click(function() {
	if ($(".navtray").hasClass("open")) {
		$(".navtray").removeClass("open");
		navtrayid = 0;
	} else {
		$(".navtray").addClass("open");
		navtrayid = 0;
	}
});
$(".searchicon").click(function() {
	$(".searchtray").addClass("open");
	$(".searchbox").focus();
});
$(".searchbox").focusout(function() {
	setTimeout(function() {
		$(".searchtray").removeClass("open");
		$(".sugbox").css("height", "0");
	}, 200);
});
var sugselect = 1;
$(".searchbox").keyup(function(e) {
	if ($(this).val().length > 0)
		$(".sugbox").css("height", "150px");
	else
		$(".sugbox").css("height", "0");
	var items=$(".sugbox").children("li").length;
	if (e.keyCode == 38) {
		sugselect--;
		if(sugselect==0)sugselect=items;
		$(".sugbox").children("li").removeClass("active");
		$(".sugbox").children("li:nth-child(" + sugselect + ")").addClass("active");
		var off=$(".sugbox").children("li:nth-child(" + sugselect + ")").position().top;
		$(".sugbox").scrollTop(off);
	}
	if (e.keyCode == 40) {
		sugselect++;
		if(sugselect==items+1)sugselect=1;
		$(".sugbox").children("li").removeClass("active");
		$(".sugbox").children("li:nth-child(" + sugselect + ")").addClass("active");
		var off=$(".sugbox").children("li:nth-child(" + sugselect + ")").position().top;
		$(".sugbox").scrollTop(off);
	}
	if (e.keyCode == 13) {
		$(".sugbox").children("li:nth-child(" + sugselect + ")").click();
	}
});
$(document).click(function() {
	navtrayCloser();
	selectCloser();
});
function navtrayCloser() {
	if (navtrayid == 0) {
		navtrayid++;
		return;
	}
	if (navtrayid == 1) {
		if ($(".navtray").hasClass("open"))
			$(".navtray").removeClass("open");
		navtrayid = 0;
	}
}
var modelid = 0;
function modelCloser() {
	if (modelid == 0) {
		modelid++;
		return;
	}
	if (modelid == 1) {
		$(".model.open").removeClass("open");
		modelid = 0;
	}
}
function selectCloser() {
	if (selectid == 0) {
		selectid++;
		return;
	}
	if (selectid == 1) {
		$(".select").children(".options").removeClass("open");
		selectid = 0;
	}
}
function showError(target, msg) {
	$("#" + target).html(msg).slideDown().css("display", "block");
}
function hideError(target) {
	$("#" + target).slideUp();
}
function callAjax(param, executer) {
	$.ajax({
		url : "AjaxServlet",
		data : param,
		success : function(data) {
			executer(data);
		}
	});
}
if ($(".select")[0]) {
	$(".select").each(function() {
		refreshSelect($(this));
	});
}
var selectid = 0;
$(".select").click(function() {
	if ($(this).children(".options").hasClass("open")) {
		$(this).children(".options").removeClass("open");
		selectid = 0;
	} else {
		selectCloser();
		$(this).children(".options").addClass("open");
		selectid = 0;
	}
});
$("body").on(
		"click",
		".select .options li",
		function() {
			var selectbox = $(this).parent().parent();
			var index = $(this).index() + 1;
			$(this).siblings("li").each(function() {
				$(this).removeClass("active");
			});
			$(this).addClass("active");
			selectbox.children(".selected-option").text($(this).text());
			selectbox.children("select").val(
					selectbox.children("select").children(
							"option:nth-child(" + index + ")").val());
			selectbox.children("select").trigger('change');
		});
$("body").on("click", ".modelbutton", function() {
	var target = $("#" + $(this).attr("target"));
	if (target.hasClass("open")) {
		modelid = 1;
		modelCloser();
	} else {
		target.addClass("open");
		modelid = 0;
	}

});
$("body").on("click", ".modelcloser", function() {
	modelid = 1;
	modelCloser();
});
$("body").on("click", ".list .content li", function() {
	$(this).siblings("li").removeClass("active");
	$(this).addClass("active");
});
function refreshSelect(target) {
	target.children(".options").children().remove();
	target.children(".selected-option").html("");
	for ( var i = 0; i < target.children("select").children("option").length; i++)
		target.children(".options").append(
				"<li>"
						+ target.children("select").children(
								"option:nth-child(" + (i + 1) + ")").html()
						+ "</li>");
	target.children(".selected-option").append(
			target.children("select").find(":selected").text());
	target.children(".options").children(
			"li:nth-child("
					+ (target.children("select").find(":selected").index() + 1)
					+ ")").attr("class", "active");
}
var galleries = new Array(), galtop = 0;
if ($(".gallery")[0]) {
	$(".gallery").each(
			function() {
				var activeimg = 1, counts = $(this).children(".images")
						.children().length;
				for (i = 1; i <= counts; i++) {
					if ($(this).children(".images").children(
							"div:nth-child(" + i + ")").hasClass("active")) {
						activeimg = i;
						break;
					}
				}
				for (i = 1; i <= counts; i++) {
					if ($(this).children(".pointers")[0] && i == activeimg)
						$(this).children(".pointers").append(
								"<span class='active'></span>");
					else if ($(this).children(".pointers")[0])
						$(this).children(".pointers").append("<span></span>");
				}
				var interval = 5000;
				if ($(this).attr("interval") != undefined)
					interval = parseInt($(this).attr("interval"));
				galleries[galtop] = {
					target : $(this),
					curimg : activeimg,
					totalimg : counts,
					timerobj : undefined,
					timer : interval
				};
				startGalleryTimer($(this), galtop++);
			});
}
$("body").on("click", ".gallery .pointers span", function() {
	var galleryparent = $(this).parent().parent();
	for (i = 0; i < galleries.length; i++) {
		if (galleries[i].target.is(galleryparent)) {
			var curimg = galleries[i].curimg;
			var totalimg = galleries[i].totalimg;
			changeGalleryImage(i, curimg, $(this).index() + 1);
			clearTimeout(galleries[i].timerobj)
			startGalleryLoop(galleryparent, i);
			break;
		}
	}
});
$("body").on("click", ".gallery .left,.gallery .right", function() {
	var galleryparent = $(this).parent();
	for (i = 0; i < galleries.length; i++) {
		if (galleries[i].target.is(galleryparent)) {
			var curimg = galleries[i].curimg;
			var totalimg = galleries[i].totalimg;
			if ($(this).hasClass("left")) {
				if (curimg - 1 < 1)
					changeGalleryImage(i, curimg, totalimg);
				else
					changeGalleryImage(i, curimg, curimg - 1);
			} else if ($(this).hasClass("right")) {
				if (curimg + 1 > totalimg)
					changeGalleryImage(i, curimg, 1);
				else
					changeGalleryImage(i, curimg, curimg + 1);
			}
			clearTimeout(galleries[i].timerobj)
			startGalleryLoop(galleryparent, i);
			break;
		}
	}
});
function startGalleryTimer(obj, arindex) {
	var imagespath = galleries[arindex].target.children(".images");
	var curimg = galleries[arindex].curimg;
	var totalimg = galleries[arindex].totalimg;
	var imgh = imagespath.children("div:nth-child(" + curimg + ")").children(
			"img,div").height();
	imagespath.parent().css("height", imgh);
	startGalleryLoop(obj, arindex);
}
function startGalleryLoop(obj, arindex) {
	var imagespath = galleries[arindex].target.children(".images");
	var curimg = galleries[arindex].curimg;
	var totalimg = galleries[arindex].totalimg;
	var imgh = imagespath.children("div:nth-child(" + curimg + ")").children(
			"img,div").height();
	imagespath.parent().css("height", imgh);
	galleries[arindex].timerobj = setTimeout(function() {
		var curimg = galleries[arindex].curimg;
		var totalimg = galleries[arindex].totalimg;
		if (curimg + 1 > totalimg)
			changeGalleryImage(arindex, curimg, 1);
		else
			changeGalleryImage(arindex, curimg, curimg + 1);
		startGalleryLoop(obj, arindex);
	}, galleries[arindex].timer);
}
function changeGalleryImage(arindex, current, next) {
	var imagespath = galleries[arindex].target.children(".images");
	var pointerpath;
	if (imagespath.parent().children(".pointers")[0]) {
		pointerpath = imagespath.parent().children(".pointers");
		pointerpath.children("span:nth-child(" + current + ")").removeAttr(
				"class");
	}
	imagespath.children("div:nth-child(" + current + ")").removeClass("active")
			.addClass("prev");
	imagespath.children("div:nth-child(" + next + ")").addClass("active");
	if (imagespath.parent().children(".pointers")[0])
		pointerpath.children("span:nth-child(" + next + ")").addClass("active");
	var imgh = imagespath.children("div:nth-child(" + next + ")").children(
			"img,div").height();
	imagespath.parent().css("height", imgh);
	setTimeout(function() {
		imagespath.children("div:nth-child(" + current + ")").css("opacity",
				"0");
		imagespath.children("div:nth-child(" + current + ")").removeClass(
				"prev");
		setTimeout(function() {
			imagespath.children("div:nth-child(" + current + ")").css(
					"opacity", "");
		}, parseFloat(imagespath.children("div:nth-child(" + current + ")")
				.css("transition-duration").replace("s", "")) * 1000);
	}, parseFloat(imagespath.children("div:nth-child(" + current + ")").css(
			"transition-duration").replace("s", "")) * 1000);
	galleries[arindex].curimg = next;
}
var toastid = 0;
function showToast(tip, type, dura) {
	var ttype = "default";
	var duration = 5000;
	if (type == "safe" || type == "danger" || type == "warning")
		ttype = type;
	if (dura != undefined)
		duration = parseInt(dura);
	if ($(".toast")[0]) {
		$(".toast").each(function() {
			var curtop = parseInt($(this).css("top"));
			var curheight = parseInt($(this).outerHeight());
			$(this).css("top", curtop + curheight + 10);
		});
	}
	$("body").append(
			"<div id='toast" + toastid + "' class='toast " + ttype + "'>" + tip
					+ "</div>");
	var curtoast = $("#toast" + (toastid++));
	var twidth = curtoast.outerWidth();
	var docwidth = $(document).outerWidth();
	var ft = 110;
	var fl = parseInt((docwidth / 2.0) - (twidth / 2.0));
	if ($(".navbar")[0])
		ft += 60;
	curtoast.css({
		"left" : fl,
		"top" : ft,
		"opacity" : "1",
		"transform" : "scale(1.1)"
	});
	setTimeout(function() {
		curtoast.css({
			"transform" : "scale(1)"
		});
	}, 300);
	setTimeout(function() {
		curtoast.css({
			"transform" : "scale(1.1)"
		});
		setTimeout(function() {
			curtoast.css({
				"transform" : "scale(0)",
				"opacity" : "0"
			});
			setTimeout(function() {
				curtoast.remove();
			}, 300);
		}, 300);
	}, duration + 300);
}
function validateDate(input) {
	var parts = input.split("/");
	if (parts.length == 3) {
		var d = parts[0];
		var m = parts[1];
		var y = parts[2];
		if (y.length != 4)
			return false;
		else {
			if (m.length != 2 && m.length != 1)
				return false;
			else {
				if (d.length != 2 && d.length != 1)
					return false;
				else {
					y = parseInt(y);
					m = parseInt(m);
					d = parseInt(d);
					if (isNaN(y) || isNaN(m) || isNaN(d))
						return false;
					else {
						switch (m) {
						case 1:
							if (d < 1 || d > 31)
								return false;
							break;
						case 2:
							if (y % 4 == 0 && (d < 1 || d > 29))
								return false;
							if (y % 4 != 0 && (d < 1 || d > 28))
								return false;
							break;
						case 3:
							if (d < 1 || d > 31)
								return false;
							break;
						case 4:
							if (d < 1 || d > 30)
								return false;
							break;
						case 5:
							if (d < 1 || d > 31)
								return false;
							break;
						case 6:
							if (d < 1 || d > 30)
								return false;
							break;
						case 7:
							if (d < 1 || d > 31)
								return false;
							break;
						case 8:
							if (d < 1 || d > 31)
								return false;
							break;
						case 9:
							if (d < 1 || d > 30)
								return false;
							break;
						case 10:
							if (d < 1 || d > 31)
								return false;
							break;
						case 11:
							if (d < 1 || d > 30)
								return false;
							break;
						case 12:
							if (d < 1 || d > 31)
								return false;
							break;
						default:
							return false;
						}
						return new Date(y, m - 1, d);
					}
				}
			}
		}
	} else
		return false;
}
$("body").on(
		"mouseenter",
		".tooltip",
		function(e) {
			var toolmsg = $(this).attr("toolmsg");
			$("body").append(
					"<div class='tooltipbody' style='top:" + (e.clientY + 40)
							+ "px;left:" + e.clientX + "px;'>" + toolmsg
							+ "</div>");
			var h = parseInt($(".tooltipbody").outerHeight());
			var w = parseInt($(".tooltipbody").outerWidth());
			$(".tooltipbody").css({
				"left" : (e.clientX - (w / 2)) + "px"
			});
		}).on("mouseleave", ".tooltip", function(e) {
	$(".tooltipbody").remove();
});
