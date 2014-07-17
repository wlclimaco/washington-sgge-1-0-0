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
		<div class="header-left">
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
				<h3><a href="#SampleApp" rel="address:#SampleApp">Menu</a></h3>
				<div id="tree"></div>


				<h3><a href="#Maps" rel="address:#Maps">Filtros</a></h3>
				<div>
					qat_mvc_tabs.jsp
				</div>
			</div>
	</div>

	<div class="ui-layout-center">
		<div id="StatusBar" style="height: 20px;"></div><br/>
		<div id="content" class="ui-layout-content"></div>
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
		   "../thirdparty/jquery/styles/grid/slick.columnpicker.css",
		   "../commons/scripts/util/namespace_init.js",
		   "../commons/scripts/util/wd_log.js",
		   "../scripts/config/wd_config.js",
		   "../thirdparty/jquery/jquery-1.10.2.min.js",
		   "../thirdparty/jquery/jquery.address-1.6.min.js",
		   "../thirdparty/jquery/jquery-ui-1.10.3.custom.min.js",
		   "../thirdparty/jquery/jquery.layout-latest.js",
		   "../thirdparty/jquery/jquery-ui-layout-resize-accordions.js",
		   "../thirdparty/jquery/jquery.ui.autocomplete.js",
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
		   "../thirdparty/jquery/slick.checkboxselectcolumn.js",
		   "../thirdparty/jquery/slick.rowselectionmodel.js",
		   "../thirdparty/jquery/slick.cellrangedecorator.js",
		   "../thirdparty/jquery/slick.cellrangeselector.js",
		   "../thirdparty/jquery/slick.cellselectionmodel.js",
		   "../thirdparty/jquery/slick.columnpicker.js",
		   "../thirdparty/jquery/slick.formatters.js",
		   "../thirdparty/jquery/slick.editors.js",
		   "../thirdparty/jquery/slick.grid.js",
		   "../thirdparty/jquery/jquery.dataTables.min.js",
		   "../commons/scripts/util/wd_yahoo_finance_stock_data.js",
		   "../thirdparty/jquery/custom/jquery.bubblepopup.v2.3.1.min.js",
		   "../thirdparty/jquery/custom/jquery.cookie.js",
		   "../thirdparty/jquery/custom/jquery.ez-pinned-footer.js",
		   "../thirdparty/jquery/custom/jquery.menu.js",
		   "../thirdparty/jquery/custom/jquery.sparkline.min.js",
		   "../thirdparty/jquery/custom/jquery.validate.min.js",
		   "../scripts/util/base.js",
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