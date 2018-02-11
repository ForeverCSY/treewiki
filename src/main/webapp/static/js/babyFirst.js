var ie = function() {
	for (var b = 3, a = document.createElement("div"), c = a.getElementsByTagName("i"); a.innerHTML = "<\!--[if gt IE " + ++b + "]><i></i><![endif]--\>", c[0]; );
	return b > 4 ? b :
	void 0
}(), ie6 = ie === 6, path = window.location.pathname.replace(/.*\/([^\/\.]*)\..*/g, "$1"), langLib = [];
( ie = ie < 9) && document.write('<link rel="stylesheet" href="css/common_ie6.css" type="text/css">');


console.log("babyFirst.js --> path :" + path );



function chgLang(b) {
	var a = new Date;
	a.setTime(a.getTime() + 2592E6);
	document.cookie = "lang=" + escape(b) + ";expires=" + a.toGMTString();
	window.location.reload()
};
