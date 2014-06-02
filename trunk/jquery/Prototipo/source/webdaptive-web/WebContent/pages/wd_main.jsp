<%@ page session="true"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%
	response.setContentType("text/html; charset=iso-8859-1");
	response.setHeader("Cache-Control", "no-cache, private, must-revalidate, max-stale=0, post-check=0, pre-check=0 no-store"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0); // Proxies.
%>
<!DOCTYPE html>
<HTML>
<HEAD>
<TITLE></TITLE>
<style type="text/css">
.preload {
	width:100px;
    height: 100px;
    position: fixed;
    top: 30%;
    left: 40%;
	FONT-FAMILY:	Arial, Helvetica;
	FONT-SIZE: 		100%;
}
</style>
</HEAD>
<BODY>
<div class="preload"><img src="../images/loading.gif">Loading....</div>
<div id="wdcontent" class="wdcontent">

	<div id="dialog" title="Your session is about to expire!">
		<p>
			<span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 50px 0;"></span>
			You will be logged off in <span id="dialog-countdown" style="font-weight:bold"></span> seconds.
		</p>

		<p>Do you want to continue your session?</p>
	</div>

	<div class="ui-layout-north">
		<div class="header-left"><img src="../images/qat-global-logo.png" alt="QAT Global" border="0" align="left" />
		</div>
		<div class="header-center">
			<div id="appname" class="appname"></div>
		</div>
		<div class="header-right">
			<div class="img"><img src="../images/listbox.topnav.gif" alt="" border="0"/>&nbsp;<a id="header-logout" href="<c:url value='/j_spring_security_logout'/>"></a></div>
			<div id="time" class="time"></div>
			<div id="user" class="uname"></div>
		</div>
	</div>

	<div class="ui-layout-west">
			<div id="west-accordion">
				<h3><a href="#SampleApp" rel="address:#SampleApp">Sample Apps</a></h3>
				<div id="tree"></div>

				<h3><a href="#Doco" rel="address:#Doco">Documentation</a></h3>
				<div>
					<p>WebDaptive is based on the open source jQuery Javascript Library. The following are the links to the open source components:</p>
					<ul>
						<li><a href="http://headjs.com/" target="_blank">Script Loader (Read)</a></li>
						<li><a href="http://jquery.com/" target="_blank">jQuery Core</a></li>
						<li><a href="http://jqueryui.com/" target="_blank">jQuery UI</a></li>
						<li><a href="http://layout.jquery-dev.net/" target="_blank">Layout (jQuery Plug-in)</a></li>
						<li><a href="http://code.google.com/p/dynatree/" target="_blank">Dynatree (jQuery Plug-in)</a></li>
						<li><a href="http://github.com/mleibman/SlickGrid" target="_blank">SlickGrid (jQuery Plug-in)</a></li>
						<li><a href="http://jnotify.codeplex.com/" target="_blank">jNotify (jQuery Plug-in)</a></li>
						<li><a href="http://malsup.com/jquery/block/" target="_blank">BlockUI (jQuery Plug-in)</a></li>
						<li><a href="http://www.asual.com/jquery/format/" target="_blank">Format (jQuery Plug-in)</a></li>
						<li><a href="http://www.asual.com/jquery/address/" target="_blank">Address (jQuery Plug-in)</a></li>
						<li><a href="https://github.com/ehynds/jquery-idle-timeout" target="_blank">Idle Timeout (jQuery Plug-in)</a></li>
						<li><a href="http://bassistance.de/jquery-plugins/jquery-plugin-validation/" target="_blank">Validation (jQuery Plug-in)</a></li>
						<li><a href="http://code.google.com/p/jquery-json/" target="_blank">JSON (jQuery Plug-in)</a></li>
					</ul>
				</div>

				<h3><a href="#Training" rel="address:#Training">Training</a></h3>
				<div>
					<p>Javascript & jQuery Training, including exercises and solutions.</p>
					<ul>
						<li><a href="../training/jqfundamentals/book/jquery-fundamentals-book.pdf" target="_blank">jQuery Fundamentals</a></li>
						<li><a href="https://secure.qat.com/share/page/site/java-knowledgebase/documentlibrary" target="_blank">WebDaptive Overview & Standards</a></li>
						<li><a href="#" onclick="$('#content').load('wd_arch.jsp');">WebDaptive Architecture</a></li>
					</ul>
				</div>

				<h3><a href="#Maps" rel="address:#Maps">QAT Global Facilities</a></h3>
				<div>
					<p>Maps  &amp; Directions to QAT Global Facilities.</p>
					<ul>
						<li><a href="http://goo.gl/maps/afLzb" target="_blank">QAT Global Headquarters</a></li>
						<li><a href="http://goo.gl/maps/WfXD8" target="_blank">QAT Global Brazil Office</a></li>
					</ul>
				</div>

				<h3><a href="#Async" rel="address:#Async">Live Messages</a></h3>
				<div>
					<p>Live Messages pushed from Back-end</p>
					<table>
						<tr>
							<td colspan="2">
								<textarea cols="27" rows="10" id="txtaMessages"></textarea>&nbsp;
							</td>
						</tr>
						<tr>
							<td>Send Msg:</td>
							<td><input type="text" id="txtMessage"/></td>
						</tr>
						<tr>
							<td colspan="2"><button id="send_async" value="Send"/></td>
						</tr>
					</table>
				</div>
			</div>
	</div>

	<div class="ui-layout-center">
		<div id="StatusBar" style="height: 20px;"></div><br/>
		<div id="content" class="ui-layout-content"><img src="../images/WebDaptiveHLA.png" alt="" border="0"/></div>
	</div>

	<div class="ui-layout-south">
		<div id="footer" class="ui-layout-south-content"></div>
	</div>
</div>
<script type="text/javascript" src="../thirdparty/head.min.js"></script>
<script>
// if desktop
// 		Load all files in parallel but executes in order & does this non-blocking
// else
// 		Go to mobile site
if (head.desktop)
{
   head.js("../thirdparty/jquery/styles/redmond/jquery-ui-1.10.3.custom.css",
		   "../thirdparty/jquery/styles/tree/ui.dynatree.css",
		   "../thirdparty/jquery/styles/jnotify/jquery.jnotify.css",
		   "../thirdparty/jquery/styles/grid/slick.grid.css",
		   "../styles/wd_main.css",
		   "../commons/scripts/util/namespace_init.js",
		   "../commons/scripts/util/wd_log.js",
		   "../scripts/config/wd_config.js",
		   "../thirdparty/jquery/jquery-1.10.2.min.js",
		   "../thirdparty/jquery/jquery.address-1.6.min.js",
		   "../thirdparty/jquery/jquery-ui-1.10.3.custom.min.js",
		   "../thirdparty/jquery/jquery.layout-latest.js",
		   "../thirdparty/jquery/jquery-ui-layout-resize-accordions.js",
		   "../thirdparty/jquery/custom/jquery.i18n.properties.js",
		   "../thirdparty/jquery/custom/jquery.format-1.2.js",
		   "../thirdparty/jquery/jquery.cookie.js",
		   "../thirdparty/jquery/jquery.dynatree.min.js",
		   "../thirdparty/jquery/jquery.equalheights.js",
		   "../thirdparty/jquery/jquery.jnotify.js",
		   "../thirdparty/jquery/jquery.blockUI.js",
		   "../thirdparty/jquery/jquery.validate.min.js",
		   "../thirdparty/jquery/jquery.idle-timer.js" ,
		   "../thirdparty/jquery/jquery.idletimeout.js",
		   "../thirdparty/jquery/jquery.json-2.4.min.js",
		   "../thirdparty/jquery/jquery.event.drag-2.2.js",
		   "../thirdparty/jquery/slick.core.js",
		   "../thirdparty/jquery/slick.rowselectionmodel.js",
		   "../thirdparty/jquery/slick.cellrangedecorator.js",
		   "../thirdparty/jquery/slick.cellrangeselector.js",
		   "../thirdparty/jquery/slick.cellselectionmodel.js",
		   "../thirdparty/jquery/slick.formatters.js",
		   "../thirdparty/jquery/slick.editors.js",
		   "../thirdparty/jquery/slick.grid.js",
		   "../thirdparty/jquery/jquery.dataTables.min.js",
		   "../commons/scripts/util/wd_yahoo_finance_stock_data.js",
		   "../commons/scripts/util/wd_core_3.0.0.js"
	);
}
else
{
	window.location.replace("mobile/wd_main.m.jsp");
}
</script>
<jsp:include page="../scripts/pages/wd_main_main.js.jsp" flush="true"/>
<jsp:include page="../scripts/pages/wd_main_init.js.jsp" flush="true"/>
<jsp:include page="user_info.jsp" flush="true"/>
</BODY>
</HTML>