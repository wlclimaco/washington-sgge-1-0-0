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
	<!--	<div class="header-left">
		</div>
		<div class="header-center">
			<div id="appname" class="appname"></div>
		</div>
		<div class="header-right">
			<div class="img"><img src="../images/listbox.topnav.gif" alt="" border="0"/>&nbsp;<a id="header-logout" href="<c:url value='/j_spring_security_logout'/>"></a></div>
			<div id="time" class="time"></div>
			<div id="user" class="uname"></div>
		</div>
	-->
	        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">SB Admin v2.0</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-envelope fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-messages">
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>Read All Messages</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-messages -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-tasks fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-tasks">
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 1</strong>
                                        <span class="pull-right text-muted">40% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                            <span class="sr-only">40% Complete (success)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 2</strong>
                                        <span class="pull-right text-muted">20% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                            <span class="sr-only">20% Complete</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 3</strong>
                                        <span class="pull-right text-muted">60% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                            <span class="sr-only">60% Complete (warning)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 4</strong>
                                        <span class="pull-right text-muted">80% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                            <span class="sr-only">80% Complete (danger)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Tasks</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-tasks -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-bell fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-alerts">
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-comment fa-fw"></i> New Comment
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                    <span class="pull-right text-muted small">12 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-envelope fa-fw"></i> Message Sent
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-tasks fa-fw"></i> New Task
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Alerts</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-alerts -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->


            <!-- /.navbar-static-side -->
        </nav>
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
						<li><a href="https://github.com/mde/timezone-js" target="_blank">TimezoneJS Date (Read)</a></li>
						<li><a href="http://jquery.com/" target="_blank">jQuery Core</a></li>
						<li><a href="http://jqueryui.com/" target="_blank">jQuery UI</a></li>
						<li><a href="http://layout.jquery-dev.net/" target="_blank">Layout (jQuery Plug-in)</a></li>
						<li><a href="http://code.google.com/p/dynatree/" target="_blank">Dynatree (jQuery Plug-in)</a></li>
						<li><a href="http://github.com/mleibman/SlickGrid" target="_blank">SlickGrid (jQuery Plug-in)</a></li>
						<li><a href="http://datatables.net/" target="_blank">DataTables (jQuery Plug-in)</a></li>
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
		<div id="StatusBar" style="height: 8px;"></div><br/>
		<div id="content" class="ui-layout-content"><img src="../images/WebDaptiveHLA.png" alt="" border="0"/></div>
	</div>

	<div class="ui-layout-south">
		<div id="footer" class="ui-layout-south-content"></div>
	</div>
</div>
<script type="text/javascript" src="../thirdparty/head.min.js"></script>
<script>



   head.js("../thirdparty/components/jquery/dist/jquery.min.js",
		   "../thirdparty/jquery/styles/tree/ui.dynatree.css",
		   "../thirdparty/jquery/styles/jnotify/jquery.jnotify.css",
		   "../thirdparty/jquery/styles/grid/slick.grid.css",
		   "../styles/wd_main.css",
		   "../thirdparty/jquery/styles/grid/slick.columnpicker.css",
		   "../thirdparty/components/bootstrap/dist/css/bootstrap.min.css",
		   "../thirdparty/components/metisMenu/dist/metisMenu.min.css",
		   "../thirdparty/dist/css/timeline.css",
		   "../thirdparty/dist/css/sb-admin-2.css",
		   "../thirdparty/components/morrisjs/morris.css",
		   "../thirdparty/components/font-awesome/css/font-awesome.min.css",

"../styles/jquery-ui.min.css",
"../styles/bootstrap.css",
"../styles/bootstrap-responsive.min.css",
"../styles/dataTables.bootstrap.css",
"../styles/dataTables.responsive.css",
"http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600",
"../styles/bootstrap-select.css",
"../styles/formValidation.css",
"../styles/select2.css",
"http://cdnjs.cloudflare.com/ajax/libs/select2/3.4.8/select2.css",

		   "../commons/scripts/util/namespace_init.js",
		   "../commons/scripts/util/wd_log.js",
		   "../scripts/config/wd_config.js",


		       <!-- jQuery -->
			//"../thirdparty/components/jquery/dist/jquery.min.js",
			"../thirdparty/jquery/jquery-1.10.2.min.js",
			"../thirdparty/jquery/jquery.address-1.6.min.js",
		   "../thirdparty/jquery/jquery.layout-latest.js",
		   "../thirdparty/jquery/custom/jquery.i18n.properties.js",
		   "../thirdparty/jquery/custom/jquery.format-1.2.js",
		   "../thirdparty/jquery/jquery.cookie.js",
		//   "../thirdparty/jquery/jquery.dynatree.min.js",
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

		   <!--teste-->

	  		"../commons/scripts/util/qat_commons_1.3.js",
	  		"../commons/scripts/util/qat_commons_table_1.0.js",
		    "../thirdparty/formvalidation-master/dist/js/select2.min.js",
			"../thirdparty/formvalidation-master/dist/js/formValidation.js",
			"../thirdparty/jquery/jquery.inputmask.js",
			"../thirdparty/jquery/jquery.inputmask.date.extensions.js",
			"../thirdparty/jquery/jquery.inputmask.extensions.js",
			"../thirdparty/jquery/jquery.inputmask.numeric.extensions.js",
			"../thirdparty/jquery/jquery.inputmask.regex.extensions.js",
			"../thirdparty/jquery/jstorage.min.js",
			"../thirdparty/timezone-js/date.custom.js",
			"../thirdparty/jquery/jquery.ThreeDots.min.js",
			"../thirdparty/jquery/custom/jquery.dataTables.custom.js",
			"../thirdparty/jquery/jquery.datatable.fnReloadAjax.js",
			"../thirdparty/jquery/jquery.datatable.fnStartingRedraw.js",
			"../scripts/excanvas.min.js",
			"../scripts/chart.min.js",
			"../thirdparty/jquery/custom/tableExport.js",
			"../thirdparty/bootstrap-table/src/bootstrap-table.js",
		   <!--teste-->
		   "../commons/scripts/util/wd_yahoo_finance_stock_data.js",
		   "../thirdparty/jquery/custom/jquery.bubblepopup.v2.3.1.min.js",
		   "../thirdparty/jquery/custom/jquery.cookie.js",
		   "../thirdparty/jquery/custom/jquery.ez-pinned-footer.js",
		   "../thirdparty/jquery/custom/jquery.menu.js",
		   "../thirdparty/jquery/custom/jquery.validate.min.js",
		   "../scripts/util/base.js",
		   "../commons/scripts/util/wd_core_3.0.0.js",
		   "../commons/scripts/model/user_context.js",
           "../commons/scripts/model/request_objects.js",
		   "../commons/scripts/model/domain_objects.js",
           "../commons/scripts/widget/slick.pager.js",
		  "../thirdparty/components/jquery/dist/jquery.min.js",
		   "../thirdparty/components/bootstrap/dist/js/bootstrap.min.js",
			"../thirdparty/components/metisMenu/dist/metisMenu.min.js",
			"../thirdparty/components/raphael/raphael-min.js",
			"../thirdparty/components/morrisjs/morris.min.js",
			//"../thirdparty/components/js/morris-data.js",
			"../thirdparty/dist/js/sb-admin-2.js",
			"../thirdparty/jquery/jquery-ui-1.10.3.custom.min.js",
			"../thirdparty/jquery/jquery-ui-layout-resize-accordions.js",
		   "../thirdparty/jquery/jquery.ui.autocomplete.js",
		   "../thirdparty/jquery/jquery.dynatree.min.js",
		   "../scripts/util/base.js"






	);
</script>
<jsp:include page="../scripts/pages/wd_main_main.js.jsp" flush="true"/>
<jsp:include page="../scripts/pages/wd_main_init.js.jsp" flush="true"/>
<jsp:include page="user_info.jsp" flush="true"/>
<jsp:include page="../commons/scripts/util/app_reuse_functions.js.jsp" flush="true"/>
</BODY>
</HTML>