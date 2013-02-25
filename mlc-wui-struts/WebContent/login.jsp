<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Sensus Lighting Control</title>

<style type="text/css">
/*
Copyright (c) 2009, Yahoo! Inc. All rights reserved.
Code licensed under the BSD License:
http://developer.yahoo.net/yui/license.txt
version: 2.7.0
*/
html{color:#000;background:#FFF;}body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,code,form,fieldset,legend,input,button,textarea,p,blockquote,th,td{margin:0;padding:0;}table{border-collapse:collapse;border-spacing:0;}fieldset,img{border:0;}address,caption,cite,code,dfn,em,strong,th,var,optgroup{font-style:inherit;font-weight:inherit;}del,ins{text-decoration:none;}li{list-style:none;}caption,th{text-align:left;}h1,h2,h3,h4,h5,h6{font-weight:normal;}q:before,q:after{content:'';}abbr,acronym{border:0;font-variant:normal;}sup{vertical-align:baseline;}sub{vertical-align:baseline;}legend{color:#000;}input,button,textarea,select,optgroup,option{font-family:inherit;font-size:inherit;font-style:inherit;font-weight:inherit;}input,button,textarea,select{*font-size:100%;}body{font:13px/1.231 arial,helvetica,clean,sans-serif;*font-size:small;*font:x-small;}select,input,button,textarea,button{font:99% arial,helvetica,clean,sans-serif;}table{font-size:inherit;font:100%;}pre,code,kbd,samp,tt{font-family:monospace;*font-size:108%;line-height:100%;}body{text-align:center;}#doc,#doc2,#doc3,#doc4,.yui-t1,.yui-t2,.yui-t3,.yui-t4,.yui-t5,.yui-t6,.yui-t7{margin:auto;text-align:left;width:57.69em;*width:56.25em;}#doc2{width:73.076em;*width:71.25em;}#doc3{margin:auto 10px;width:auto;}#doc4{width:74.923em;*width:73.05em;}.yui-b{position:relative;}.yui-b{_position:static;}#yui-main .yui-b{position:static;}#yui-main,.yui-g .yui-u .yui-g{width:100%;}.yui-t1 #yui-main,.yui-t2 #yui-main,.yui-t3 #yui-main{float:right;margin-left:-25em;}.yui-t4 #yui-main,.yui-t5 #yui-main,.yui-t6 #yui-main{float:left;margin-right:-25em;}.yui-t1 .yui-b{float:left;width:12.30769em;*width:12.00em;}.yui-t1 #yui-main .yui-b{margin-left:13.30769em;*margin-left:13.05em;}.yui-t2 .yui-b{float:left;width:13.8461em;*width:13.50em;}.yui-t2 #yui-main .yui-b{margin-left:14.8461em;*margin-left:14.55em;}.yui-t3 .yui-b{float:left;width:23.0769em;*width:22.50em;}.yui-t3 #yui-main .yui-b{margin-left:24.0769em;*margin-left:23.62em;}.yui-t4 .yui-b{float:right;width:13.8456em;*width:13.50em;}.yui-t4 #yui-main .yui-b{margin-right:14.8456em;*margin-right:14.55em;}.yui-t5 .yui-b{float:right;width:18.4615em;*width:18.00em;}.yui-t5 #yui-main .yui-b{margin-right:19.4615em;*margin-right:19.125em;}.yui-t6 .yui-b{float:right;width:23.0769em;*width:22.50em;}.yui-t6 #yui-main .yui-b{margin-right:24.0769em;*margin-right:23.62em;}.yui-t7 #yui-main .yui-b{display:block;margin:0 0 1em 0;}#yui-main .yui-b{float:none;width:auto;}.yui-gb .yui-u,.yui-g .yui-gb .yui-u,.yui-gb .yui-g,.yui-gb .yui-gb,.yui-gb .yui-gc,.yui-gb .yui-gd,.yui-gb .yui-ge,.yui-gb .yui-gf,.yui-gc .yui-u,.yui-gc .yui-g,.yui-gd .yui-u{float:left;}.yui-g .yui-u,.yui-g .yui-g,.yui-g .yui-gb,.yui-g .yui-gc,.yui-g .yui-gd,.yui-g .yui-ge,.yui-g .yui-gf,.yui-gc .yui-u,.yui-gd .yui-g,.yui-g .yui-gc .yui-u,.yui-ge .yui-u,.yui-ge .yui-g,.yui-gf .yui-g,.yui-gf .yui-u{float:right;}.yui-g div.first,.yui-gb div.first,.yui-gc div.first,.yui-gd div.first,.yui-ge div.first,.yui-gf div.first,.yui-g .yui-gc div.first,.yui-g .yui-ge div.first,.yui-gc div.first div.first{float:left;}.yui-g .yui-u,.yui-g .yui-g,.yui-g .yui-gb,.yui-g .yui-gc,.yui-g .yui-gd,.yui-g .yui-ge,.yui-g .yui-gf{width:49.1%;}.yui-gb .yui-u,.yui-g .yui-gb .yui-u,.yui-gb .yui-g,.yui-gb .yui-gb,.yui-gb .yui-gc,.yui-gb .yui-gd,.yui-gb .yui-ge,.yui-gb .yui-gf,.yui-gc .yui-u,.yui-gc .yui-g,.yui-gd .yui-u{width:32%;margin-left:1.99%;}.yui-gb .yui-u{*margin-left:1.9%;*width:31.9%;}.yui-gc div.first,.yui-gd .yui-u{width:66%;}.yui-gd div.first{width:32%;}.yui-ge div.first,.yui-gf .yui-u{width:74.2%;}.yui-ge .yui-u,.yui-gf div.first{width:24%;}.yui-g .yui-gb div.first,.yui-gb div.first,.yui-gc div.first,.yui-gd div.first{margin-left:0;}.yui-g .yui-g .yui-u,.yui-gb .yui-g .yui-u,.yui-gc .yui-g .yui-u,.yui-gd .yui-g .yui-u,.yui-ge .yui-g .yui-u,.yui-gf .yui-g .yui-u{width:49%;*width:48.1%;*margin-left:0;}.yui-g .yui-g .yui-u{width:48.1%;}.yui-g .yui-gb div.first,.yui-gb .yui-gb div.first{*margin-right:0;*width:32%;_width:31.7%;}.yui-g .yui-gc div.first,.yui-gd .yui-g{width:66%;}.yui-gb .yui-g div.first{*margin-right:4%;_margin-right:1.3%;}.yui-gb .yui-gc div.first,.yui-gb .yui-gd div.first{*margin-right:0;}.yui-gb .yui-gb .yui-u,.yui-gb .yui-gc .yui-u{*margin-left:1.8%;_margin-left:4%;}.yui-g .yui-gb .yui-u{_margin-left:1.0%;}.yui-gb .yui-gd .yui-u{*width:66%;_width:61.2%;}.yui-gb .yui-gd div.first{*width:31%;_width:29.5%;}.yui-g .yui-gc .yui-u,.yui-gb .yui-gc .yui-u{width:32%;_float:right;margin-right:0;_margin-left:0;}.yui-gb .yui-gc div.first{width:66%;*float:left;*margin-left:0;}.yui-gb .yui-ge .yui-u,.yui-gb .yui-gf .yui-u{margin:0;}.yui-gb .yui-gb .yui-u{_margin-left:.7%;}.yui-gb .yui-g div.first,.yui-gb .yui-gb div.first{*margin-left:0;}.yui-gc .yui-g .yui-u,.yui-gd .yui-g .yui-u{*width:48.1%;*margin-left:0;}.yui-gb .yui-gd div.first{width:32%;}.yui-g .yui-gd div.first{_width:29.9%;}.yui-ge .yui-g{width:24%;}.yui-gf .yui-g{width:74.2%;}.yui-gb .yui-ge div.yui-u,.yui-gb .yui-gf div.yui-u{float:right;}.yui-gb .yui-ge div.first,.yui-gb .yui-gf div.first{float:left;}.yui-gb .yui-ge .yui-u,.yui-gb .yui-gf div.first{*width:24%;_width:20%;}.yui-gb .yui-ge div.first,.yui-gb .yui-gf .yui-u{*width:73.5%;_width:65.5%;}.yui-ge div.first .yui-gd .yui-u{width:65%;}.yui-ge div.first .yui-gd div.first{width:32%;}#hd:after,#bd:after,#ft:after,.yui-g:after,.yui-gb:after,.yui-gc:after,.yui-gd:after,.yui-ge:after,.yui-gf:after{content:".";display:block;height:0;clear:both;visibility:hidden;}#hd,#bd,#ft,.yui-g,.yui-gb,.yui-gc,.yui-gd,.yui-ge,.yui-gf{zoom:1;}

BODY {
	 font-family: Helvetica,Arial,sans-serif;
	 font: 13px/1.231 arial,helvetica,clean,sans-serif;
	 text-align: center;
}
.hide {
	display: none;
}
.login {
	POSITION: relative; PADDING-BOTTOM: 10px; PADDING-LEFT: 10px; PADDING-RIGHT: 10px; OVERFLOW: hidden; PADDING-TOP: 100px;
}
.login-error-msg{
	FONT-WEIGHT: bold; FONT-SIZE: 14pt; FONT-FAMILY: Arial, Helvetica; COLOR: red; TEXT-ALIGN: center; PADDING-TOP: 5px;
}
.login-data{
	FONT-SIZE: 9pt; FONT-FAMILY: Arial, Helvetica; PADDING-TOP: 5px;
}
.login-label{
	FONT-SIZE: 9pt; FONT-WEIGHT: bold;  FONT-FAMILY: Arial, Helvetica;  float: left; text-align: right; PADDING-RIGHT: 7px; PADDING-TOP: 9px;
}
.login-msg{
	FONT-SIZE: 11pt; FONT-FAMILY: Arial, Helvetica; text-align: center;
}
.login-buttons {
	margin-left: 47%; PADDING-TOP: 5px;
}

/**START*/

h1, h2, h3, h4, h5, h6, strong {
    font-weight: bold;
}
.app-version {
    color: #666666;
    font-style: italic;
    padding: 1em 0;
}

.app-version {
    font-size: 85%;
}
#sign-in-container {
    background: url("images/bg-body-signin.gif") repeat-x scroll 0 0 #F0F0F0;
    padding: 1em;
}

#hd {
    margin: 15px 0;
}

#sign-in-container .logo {
    background: url("images/logo-sensus-sign-in.gif") no-repeat scroll center center transparent;
    min-height: 80px;
    width: 100%;
}

.logo h3 {
    color: #9ADB91;
    font-weight: normal;
    letter-spacing: 0.036em;
    margin: 2.5em 0 0;
}

#doc2 {
    width: 73.076em;
}
.logo h1, .logo h2 {
    margin: 0.5em;
    text-indent: -5000em;
}

#doc, #doc2, #doc3, #doc4, .yui-t1, .yui-t2, .yui-t3, .yui-t4, .yui-t5, .yui-t6, .yui-t7 {
    margin: auto;
    text-align: left;
}

#hd-user {
    color: #FFFFFF;
    font-size: 93%;
    padding: 0.3em 0.5em;
}

#sign-in-container #hd {
    text-align: center;
}

.sign-in {
    background: none repeat scroll 0 0 #FFFFFF;
    border: 3px solid #000000;
    margin: 3em auto;
    padding: 2em 3em;
    width: 25em;
}

.box_shadow {
    -moz-box-shadow: 0 0 1em #000000;
}

.rounded {
    -moz-border-radius: 0.4em 0.4em 0.4em 0.4em;
}

fieldset.two-line legend {
    color: #02254D;
    font-size: 167%;
    letter-spacing: -0.036em;
    padding: 0.25em;
}

.ui-state-error, .ui-state-success {
    padding: 1em;
}

.ui-corner-all {
    -moz-border-radius: 4px 4px 4px 4px;
}

.ui-state-success {
	background: url("images/ui-bg_inset-soft_95_fef1ec_1x100.png") repeat-x scroll 50% bottom #FFFFEE;
    border: 1px solid #FCEFA1;
    color: #333333;
}

.ui-state-error, .ui-widget-content .ui-state-error, .ui-widget-header .ui-state-error {
    background: url("images/ui-bg_inset-soft_95_fef1ec_1x100.png") repeat-x scroll 50% bottom #FEF1EC;
    border: 1px solid #CD0A0A;
    color: #CD0A0A;
}

.login-data {
    font-family: Arial,Helvetica;
    font-size: 9pt;
    padding-top: 5px;
}

.fancy input[type="text"], .fancy input[type="password"] {
    -moz-box-shadow: 0 0 4px #999999;
    background: none repeat scroll 0 0 #FFFFFF;
    border: 1px solid #999999;
    margin: 0.35em 0;
    padding: 0.5em;
}
fieldset.two-line ul li input {
    padding: 0.2em;
    width: 90%;
}

fieldset ul .radio input, fieldset ul .checkbox input {
    width: auto !important;
}

fieldset.two-line ul li label {
    float: none;
    width: auto;
}

fieldset.two-line ul li.checkbox input {
    margin: 0 0 0 0.5em;
    width: auto;
}

fieldset ul .radio label, fieldset ul .checkbox label {
    float: none;
    margin-left: 0.25em;
    width: auto;
}
fieldset ul li.submit-row, fieldset ul li.submit-row-pad {
    border-top: 1px dotted #CCCCCC;
    margin: 0.8em 0;
    padding: 1em;
}

fieldset ul li label {
    float: left;
    line-height: 1.1;
    padding: 0.65em 0.2em;
    width: 11em;
}

fieldset.two-line ul li.checkbox {
    margin: 0 0 0 0.5em;
    width: auto;
}

.fancy input[type="submit"] {
    -moz-border-radius: 0.5em 0.5em 0.5em 0.5em;
    -moz-box-shadow: 0 0 4px #999999;
    background: url("images/bg-button-fancy.gif") repeat-x scroll 0 0 #001A35;
    border: 1px solid #011830;
    color: #FFFFFF;
    font-size: 108%;
    font-weight: bold;
    padding: 0.5em;
    width: auto;
}

.login-label {
    float: left;
    font-family: Arial,Helvetica;
    font-size: 9pt;
    font-weight: bold;
    padding-right: 7px;
    padding-top: 9px;
    text-align: right;
}

.login-buttons {
    margin-left: 47%;
    padding-top: 5px;
}

fieldset legend {
    background: none repeat scroll 0 0 #F0F0F0;
    font-weight: bold;
    margin: 0 0 0.5em;
    width: 100%;
}

.vcard {
    font-size: 93%;
    padding: 1.5em;
    text-align: left;
}

.vcard .adr {
	color: #666666;
}

#ft .footer-links {
    text-align: right;
}

#ft .link-list {
    font-size: 93%;
    padding: 1.5em;
}

ul.link-list li {
    background: url("images/bg-sep-text-list.gif") no-repeat scroll right center transparent;
    display: inline;
    list-style: none outside none;
    padding: 0 0.6em 0 0.3em;
}

ul.link-list li a {
    color: #333333;
    text-decoration: none;
}

#ft {
    background: none repeat scroll 0 0 #ECECEC;
    height: 8em;
    margin: 1.5em 0;
}

fieldset ul li {
    clear: both;
    overflow: hidden;
    padding: 0.5em;
}

fieldset ul {
    margin: 0.6em 0.2em;
}

</style>
<script type="text/javascript">
function init()
{

	var dToday = new Date();
	document.getElementById("current-year").innerHTML = dToday.getFullYear();
	
	document.loginForm.j_username.focus();
	
	var msg;
	
	if (window.location.href.indexOf('?') == -1 || window.location.href.indexOf("error=true") != -1) {

		msg = document.getElementById("error-messages").innerHTML;

	} else {

		msg = window.location.href.slice(window.location.href.indexOf('?') + 1).replace(/%20{1}/gi," ");

	}

	var elementMsg = document.getElementById('login-message'); 	

	if (msg.indexOf("notenant") != -1) {

		elementMsg.setAttribute("class", "ui-corner-all ui-state-error");
		elementMsg.setAttribute("className", "ui-corner-all ui-state-error");
		elementMsg.innerHTML = "You don´t have access to this tenant.";

	}
	
	
	if (msg.indexOf("error") != -1 
			|| msg.indexOf("failed") != -1 
			|| msg.indexOf("Invalid") != -1 
			|| msg.indexOf("User is already logged in on another computer") != -1
			|| msg.indexOf("User Name and Password do not match our entry") != -1 
			|| msg.indexOf("User does not exist!") != -1 
			|| msg.indexOf("No group defined to this user!") != -1 
			|| msg.indexOf("Bad Password for this tenant!") != -1) {
			
		msg = msg.indexOf("Bad Password for this tenant!") != -1 ? "Invalid password" : msg

		elementMsg.setAttribute("class", "ui-corner-all ui-state-error");
		elementMsg.setAttribute("className", "ui-corner-all ui-state-error");
		elementMsg.innerHTML = msg;

	} else if (msg.indexOf("Bad credentials") != -1) {

		elementMsg.setAttribute("class", "ui-corner-all ui-state-error");
		elementMsg.setAttribute("className", "ui-corner-all ui-state-error");
		elementMsg.innerHTML = "User Name and Password do not match our entry";

	}else if (msg.indexOf("Session timed out") != -1 || msg.indexOf("Successful Logout") != -1) {

		elementMsg.setAttribute("class", "ui-corner-all ui-state-success");
		elementMsg.setAttribute("className", "ui-corner-all ui-state-success");
		elementMsg.innerHTML = msg;

	}
	
}	
</script>

</head>

<body onload='init();' id="sign-in-container">
<div id="doc2" class="yui-t">
<div id="hd-user"></div>
<div id="hd" class="yui-gf">
	<div class="logo">

		<h1><a href="/"><s:text name="flexnet.portal.sensuslink" /></a></h1>
		<h3><s:text name="flexnet.portal.mission" /></h3>

	</div>
</div>
<div id="bd">
<div class="sign-in rounded box_shadow"><!-- <form id="username-password-form" name="username-password-form" method="post" action="/josso/signon/usernamePasswordLogin.do;jsessionid=E070843BF968089209ECE93EDD4E8DC9">  -->
<form id="loginForm" name="loginForm" action="j_spring_security_check" method="post">
	<input type="hidden" name="josso_cmd" value="login"> 
	<input type="hidden" name="josso_back_to" value="http://localhost:8080/sensus-site/josso_security_check">
	<div class="content">
		<fieldset class="two-line fancy">

			<div id="error-messages" class="hide">
				<c:if test="${param.error != null}">
					<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
				</c:if>	
			</div>

			<div id="login-message">
				
			</div>
			<ul>
				<li>
					<label for="josso_username">Enter your <strong>User Name</strong></label><br />
					<input id="j_username" name="j_username" type="text" size=17 maxlength=25 tabindex="1" value=""/>
				</li>
				<li>
					<label for="josso_password">Enter your <strong>Password</strong></label> 
					<input id="j_password" name="j_password" type="password" size=17 maxlength=25 tabindex="2" value="">
				</li>
				<li class="submit-row">
					<input type="submit" id="jasso_signin" tabindex="3" value="Log in to Lighting Control">
				</li>
			</ul>
		</fieldset>
	</div>
</form>
</div>
</div>

<%-- START Footer --%>
<div id="ft" class="yui-g">
<div class="yui-u first"><%-- Footer address section --%>
<div class="vcard">
<div class="fn org">&copy;<span id="current-year"></span>
<s:text name="company.org" /></div>
</div>
</div>
<%-- Footer Links --%>
<div class="yui-u footer-links">

</div>
<%-- END Footer --%>

</div>
</div>

</body>
</html>
