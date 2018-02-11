window.___gcfg = {
	lang : "zh-CN",
	parsetags : "onload"
};
var body, ad_right, ad_left = {
	isStart : !0,
	list : [],
	div : null,
	cur : 0,
	animate : 0,
	sleep : 1E4
};
$(document).ready(function() {
	body = $("body");
	var a = document.createElement("script");
	a.type = "text/javascript";
	a.async = !0;
	//a.src = "https://apis.google.com/js/plusone.js";
	body.append(a);
	ad_right = $("#ad_120_600_right");
	ad_right.sleep = 9500;
	a = $(".adLeft");
	ad_left.div = $("#ad_120_600_left");
	for (var b = 0, c = a.length; b < c; b++)
		ad_left.list[b] = $(a.get(b));
	setAd();
	$("#footer_siteMap").hover(function() {
		$("#footer_siteMap_ul").fadeIn("normal")
	}, function() {
		$("#footer_siteMap_ul").fadeOut("normal")
	});
	$("#footer_contact").hover(function() {
		$("#footer_contact_ul").fadeIn("normal")
	}, function() {
		$("#footer_contact_ul").fadeOut("normal")
	});
	$("#footer_download").hover(function() {
		$("#footer_download_ul").fadeIn("normal")
	}, function() {
		$("#footer_download_ul").fadeOut("normal")
	});
	//demoContent._init()
	path == "main" ? mainContent._init() : path == "demo" ? demoContent._init() : path == "api" ? apiContent._init() : path == "faq" ? faqContent._init() : path == "donate" && donateContent._init();
	$(window).bind("resize", function() {
		setAd()
	})
});
function setAd() {
	var a = $("#header");
	ad_left.headOffset = a.offset();
	ad_left.left = ad_left.headOffset.left - 120 - 20;
	clearTimeout(ad_left.timer);
	clearTimeout(ad_left.resizeTimer);
	ad_left.left < 0 ? leftAdSwitch() : ad_left.resizeTimer = setTimeout(leftAdSwitch, 200);
	ad_right.css({
		top : ad_left.headOffset.top + 50,
		left : ad_left.headOffset.left + a.width() + 20
	}).show();
	a = ad_right.children();
	if ( a = a[Math.round(Math.random() * 1E3) % a.length])
		a.style.display = "block";
	if (!ad_right.ads)
		ad_right.timer = setTimeout(function() {
			rightAdSwitch()
		}, ad_right.sleep)
}

function rightAdSwitch() {
	var a;
	if (ad_right.ads) {
		if (ad_right.ads.length > 0) {
			ad_right.ads[ad_right.curIndex].hide();
			ad_right.curIndex++;
			if (ad_right.curIndex == ad_right.ads.length)
				ad_right.curIndex = 0;
			ad_right.ads[ad_right.curIndex].show(0)
		}
	} else {
		ad_right.ads = ad_right.children();
		for ( a = ad_right.curIndex = 0; a < ad_right.ads.length; a++) {
			if (ad_right.ads[a].style.display != "none")
				ad_right.curIndex = a;
			ad_right.ads[a] = $(ad_right.ads[a])
		}
	}
	clearTimeout(ad_right.timer);
	ad_right.timer = setTimeout(function() {
		rightAdSwitch()
	}, ad_right.sleep)
}

function leftAdSwitch() {
	ad_left.left > 0 ? (ad_left.div.css({
		top : ad_left.headOffset.top + 50,
		left : ad_left.left
	}).show(), ie ? ( curObj = getLeftCur(), curObj.last && curObj.last.css({
		left : "-120px"
	}), curObj.cur.css({
		left : 0
	}), ad_left.timer = setTimeout(leftAdSwitch, ad_left.sleep)) : ad_left.animate == 0 && ( curObj = getLeftCur(), curObj.last && curObj.last.animate({
		left : -120
	}, {
		duration : 1500,
		easing : "swing",
		complete : function() {
			ad_left.animate ^=2;
			if (ad_left.animate == 0 && !ad_left.div.is(":hidden"))
				ad_left.timer = setTimeout(leftAdSwitch, ad_left.sleep)
		}
	}), curObj.cur.css({
		left : 120
	}).show(), curObj.cur.animate({
		left : 0
	}, {
		duration : 1500,
		easing : "swing",
		complete : function() {
			if (!(ad_left.list.length <= 1) && (ad_left.animate ^=1, ad_left.animate == 0 && !ad_left.div.is(":hidden")))
				ad_left.timer = setTimeout(leftAdSwitch, ad_left.sleep)
		}
	}))) : ad_left.div.hide()
}

function getLeftCur() {
	ad_left.animate = ad_left.isStart ? 1 : 3;
	ad_left.last = ad_left.isStart ? null : ad_left.cur;
	ad_left.cur = ad_left.isStart ? ad_left.list.length - 1 : ad_left.cur == ad_left.list.length - 1 ? 0 : ad_left.cur + 1;
	ad_left.isStart = !1;
	return {
		last : ad_left.list[ad_left.last],
		cur : ad_left.list[ad_left.cur]
	}
};
