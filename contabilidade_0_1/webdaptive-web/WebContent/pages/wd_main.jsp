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



	  <header class="main-header">

        <!-- Logo -->
        <a href="index2.html" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><b>A</b>LT</span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><b>Admin</b>LTE</span>
        </a>

        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <!-- Navbar Right Menu -->
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <!-- Messages: style can be found in dropdown.less-->
              <li class="dropdown messages-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-envelope-o"></i>
                  <span class="label label-success">4</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">You have 4 messages</li>
                  <li>
                    <!-- inner menu: contains the actual data -->
                    <ul class="menu">
                      <li><!-- start message -->
                        <a href="#">
                          <div class="pull-left">
                            <img src="../thirdparty/AdminLTE-2.3.0/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                          </div>
                          <h4>
                            Support Team
                            <small><i class="fa fa-clock-o"></i> 5 mins</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li><!-- end message -->
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="../thirdparty/AdminLTE-2.3.0/dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
                          </div>
                          <h4>
                            AdminLTE Design Team
                            <small><i class="fa fa-clock-o"></i> 2 hours</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="../thirdparty/AdminLTE-2.3.0/dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
                          </div>
                          <h4>
                            Developers
                            <small><i class="fa fa-clock-o"></i> Today</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="../thirdparty/AdminLTE-2.3.0/dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
                          </div>
                          <h4>
                            Sales Department
                            <small><i class="fa fa-clock-o"></i> Yesterday</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="../thirdparty/AdminLTE-2.3.0/dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
                          </div>
                          <h4>
                            Reviewers
                            <small><i class="fa fa-clock-o"></i> 2 days</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>
                    </ul>
                  </li>
                  <li class="footer"><a href="#">See All Messages</a></li>
                </ul>
              </li>
              <!-- Notifications: style can be found in dropdown.less -->
              <li class="dropdown notifications-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-bell-o"></i>
                  <span class="label label-warning">10</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">You have 10 notifications</li>
                  <li>
                    <!-- inner menu: contains the actual data -->
                    <ul class="menu">
                      <li>
                        <a href="#">
                          <i class="fa fa-users text-aqua"></i> 5 new members joined today
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <i class="fa fa-warning text-yellow"></i> Very long description here that may not fit into the page and may cause design problems
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <i class="fa fa-users text-red"></i> 5 new members joined
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <i class="fa fa-shopping-cart text-green"></i> 25 sales made
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <i class="fa fa-user text-red"></i> You changed your username
                        </a>
                      </li>
                    </ul>
                  </li>
                  <li class="footer"><a href="#">View all</a></li>
                </ul>
              </li>
              <!-- Tasks: style can be found in dropdown.less -->
              <li class="dropdown tasks-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-flag-o"></i>
                  <span class="label label-danger">9</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">You have 9 tasks</li>
                  <li>
                    <!-- inner menu: contains the actual data -->
                    <ul class="menu">
                      <li><!-- Task item -->
                        <a href="#">
                          <h3>
                            Design some buttons
                            <small class="pull-right">20%</small>
                          </h3>
                          <div class="progress xs">
                            <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                              <span class="sr-only">20% Complete</span>
                            </div>
                          </div>
                        </a>
                      </li><!-- end task item -->
                      <li><!-- Task item -->
                        <a href="#">
                          <h3>
                            Create a nice theme
                            <small class="pull-right">40%</small>
                          </h3>
                          <div class="progress xs">
                            <div class="progress-bar progress-bar-green" style="width: 40%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                              <span class="sr-only">40% Complete</span>
                            </div>
                          </div>
                        </a>
                      </li><!-- end task item -->
                      <li><!-- Task item -->
                        <a href="#">
                          <h3>
                            Some task I need to do
                            <small class="pull-right">60%</small>
                          </h3>
                          <div class="progress xs">
                            <div class="progress-bar progress-bar-red" style="width: 60%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                              <span class="sr-only">60% Complete</span>
                            </div>
                          </div>
                        </a>
                      </li><!-- end task item -->
                      <li><!-- Task item -->
                        <a href="#">
                          <h3>
                            Make beautiful transitions
                            <small class="pull-right">80%</small>
                          </h3>
                          <div class="progress xs">
                            <div class="progress-bar progress-bar-yellow" style="width: 80%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                              <span class="sr-only">80% Complete</span>
                            </div>
                          </div>
                        </a>
                      </li><!-- end task item -->
                    </ul>
                  </li>
                  <li class="footer">
                    <a href="#">View all tasks</a>
                  </li>
                </ul>
              </li>
              <!-- User Account: style can be found in dropdown.less -->
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="../thirdparty/AdminLTE-2.3.0/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                  <span class="hidden-xs">Alexander Pierce</span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header">
                    <img src="../thirdparty/AdminLTE-2.3.0/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                    <p>
                      Alexander Pierce - Web Developer
                      <small>Member since Nov. 2012</small>
                    </p>
                  </li>
                  <!-- Menu Body -->
                  <li class="user-body">
                    <div class="col-xs-4 text-center">
                      <a href="#">Followers</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">Sales</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">Friends</a>
                    </div>
                  </li>
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="#" class="btn btn-default btn-flat">Profile</a>
                    </div>
                    <div class="pull-right">
                      <a href="#" class="btn btn-default btn-flat">Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>
              <!-- Control Sidebar Toggle Button -->
              <li>
                <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
              </li>
            </ul>
          </div>

        </nav>
      </header>

 <!-- Control Sidebar -->
      <aside class="control-sidebar control-sidebar-dark">
        <!-- Create the tabs -->
        <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
          <li><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
          <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
        </ul>
        <!-- Tab panes -->
        <div class="tab-content">
          <!-- Home tab content -->
          <div class="tab-pane" id="control-sidebar-home-tab">
            <h3 class="control-sidebar-heading">Recent Activity</h3>
            <ul class="control-sidebar-menu">
              <li>
                <a href="javascript::;">
                  <i class="menu-icon fa fa-birthday-cake bg-red"></i>
                  <div class="menu-info">
                    <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>
                    <p>Will be 23 on April 24th</p>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript::;">
                  <i class="menu-icon fa fa-user bg-yellow"></i>
                  <div class="menu-info">
                    <h4 class="control-sidebar-subheading">Frodo Updated His Profile</h4>
                    <p>New phone +1(800)555-1234</p>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript::;">
                  <i class="menu-icon fa fa-envelope-o bg-light-blue"></i>
                  <div class="menu-info">
                    <h4 class="control-sidebar-subheading">Nora Joined Mailing List</h4>
                    <p>nora@example.com</p>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript::;">
                  <i class="menu-icon fa fa-file-code-o bg-green"></i>
                  <div class="menu-info">
                    <h4 class="control-sidebar-subheading">Cron Job 254 Executed</h4>
                    <p>Execution time 5 seconds</p>
                  </div>
                </a>
              </li>
            </ul><!-- /.control-sidebar-menu -->

            <h3 class="control-sidebar-heading">Tasks Progress</h3>
            <ul class="control-sidebar-menu">
              <li>
                <a href="javascript::;">
                  <h4 class="control-sidebar-subheading">
                    Custom Template Design
                    <span class="label label-danger pull-right">70%</span>
                  </h4>
                  <div class="progress progress-xxs">
                    <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript::;">
                  <h4 class="control-sidebar-subheading">
                    Update Resume
                    <span class="label label-success pull-right">95%</span>
                  </h4>
                  <div class="progress progress-xxs">
                    <div class="progress-bar progress-bar-success" style="width: 95%"></div>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript::;">
                  <h4 class="control-sidebar-subheading">
                    Laravel Integration
                    <span class="label label-warning pull-right">50%</span>
                  </h4>
                  <div class="progress progress-xxs">
                    <div class="progress-bar progress-bar-warning" style="width: 50%"></div>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript::;">
                  <h4 class="control-sidebar-subheading">
                    Back End Framework
                    <span class="label label-primary pull-right">68%</span>
                  </h4>
                  <div class="progress progress-xxs">
                    <div class="progress-bar progress-bar-primary" style="width: 68%"></div>
                  </div>
                </a>
              </li>
            </ul><!-- /.control-sidebar-menu -->

          </div><!-- /.tab-pane -->

          <!-- Settings tab content -->
          <div class="tab-pane" id="control-sidebar-settings-tab">
            <form method="post">
              <h3 class="control-sidebar-heading">General Settings</h3>
              <div class="form-group">
                <label class="control-sidebar-subheading">
                  Report panel usage
                  <input type="checkbox" class="pull-right" checked>
                </label>
                <p>
                  Some information about this general settings option
                </p>
              </div><!-- /.form-group -->

              <div class="form-group">
                <label class="control-sidebar-subheading">
                  Allow mail redirect
                  <input type="checkbox" class="pull-right" checked>
                </label>
                <p>
                  Other sets of options are available
                </p>
              </div><!-- /.form-group -->

              <div class="form-group">
                <label class="control-sidebar-subheading">
                  Expose author name in posts
                  <input type="checkbox" class="pull-right" checked>
                </label>
                <p>
                  Allow the user to show his name in blog posts
                </p>
              </div><!-- /.form-group -->

              <h3 class="control-sidebar-heading">Chat Settings</h3>

              <div class="form-group">
                <label class="control-sidebar-subheading">
                  Show me as online
                  <input type="checkbox" class="pull-right" checked>
                </label>
              </div><!-- /.form-group -->

              <div class="form-group">
                <label class="control-sidebar-subheading">
                  Turn off notifications
                  <input type="checkbox" class="pull-right">
                </label>
              </div><!-- /.form-group -->

              <div class="form-group">
                <label class="control-sidebar-subheading">
                  Delete chat history
                  <a href="javascript::;" class="text-red pull-right"><i class="fa fa-trash-o"></i></a>
                </label>
              </div><!-- /.form-group -->
            </form>
          </div><!-- /.tab-pane -->
        </div>
      </aside><!-- /.control-sidebar -->
      <!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
      <div class="control-sidebar-bg"></div>

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



   head.js(
"../thirdparty/components/jquery/dist/jquery.min.js",
		   "../thirdparty/jquery/styles/tree/ui.dynatree.css",
		   "../thirdparty/jquery/styles/jnotify/jquery.jnotify.css",
		   "../thirdparty/jquery/styles/grid/slick.grid.css",
		   "../styles/wd_main.css",
		   "../thirdparty/jquery/styles/grid/slick.columnpicker.css",
		   "../thirdparty/dist/css/timeline.css",
		   "../thirdparty/components/morrisjs/morris.css",
		   "../thirdparty/components/font-awesome/css/font-awesome.min.css",

"../thirdparty/AdminLTE-2.3.0/bootstrap/css/bootstrap.min.css",
"https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css",
"https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css",
"../thirdparty/AdminLTE-2.3.0/plugins/jvectormap/jquery-jvectormap-1.2.2.css",
"../thirdparty/AdminLTE-2.3.0/dist/css/AdminLTE.min.css",
"../thirdparty/AdminLTE-2.3.0/dist/css/skins/_all-skins.min.css",
"../thirdparty/components/morrisjs/morris.css",


		   "../commons/scripts/util/namespace_init.js",
		   "../commons/scripts/util/wd_log.js",
		   "../scripts/config/wd_config.js",

"../thirdparty/AdminLTE-2.3.0/plugins/jQuery/jQuery-2.1.4.min.js",
"https://code.jquery.com/ui/1.11.4/jquery-ui.min.js",
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->

"../thirdparty/AdminLTE-2.3.0/bootstrap/js/bootstrap.min.js",
"https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js",
"../thirdparty/AdminLTE-2.3.0/plugins/morris/morris.min.js",
"../thirdparty/AdminLTE-2.3.0/plugins/sparkline/jquery.sparkline.min.js",
"../thirdparty/AdminLTE-2.3.0/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js",
"../thirdparty/AdminLTE-2.3.0/plugins/jvectormap/jquery-jvectormap-world-mill-en.js",
"../thirdparty/AdminLTE-2.3.0/plugins/knob/jquery.knob.js",
"https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js",
"../thirdparty/AdminLTE-2.3.0/plugins/daterangepicker/daterangepicker.js",
"../thirdparty/AdminLTE-2.3.0/plugins/datepicker/bootstrap-datepicker.js",
"../thirdparty/AdminLTE-2.3.0/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js",
"../thirdparty/AdminLTE-2.3.0/plugins/slimScroll/jquery.slimscroll.min.js",
"../thirdparty/AdminLTE-2.3.0/plugins/fastclick/fastclick.min.js",
"../thirdparty/AdminLTE-2.3.0/dist/js/app.min.js",

"../thirdparty/AdminLTE-2.3.0/dist/js/demo.js",



		       <!-- jQuery -->
			//"../thirdparty/components/jquery/dist/jquery.min.js",
			//"../thirdparty/jquery/jquery-1.10.2.min.js",
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
			"../thirdparty/knockout/knockout-3.3.0.js",

		   <!--teste-->

		   "../thirdparty/jquery/jquery-ui-1.10.3.custom.min.js",
		   "../commons/scripts/util/wd_core_3.0.0.js",
		   "../commons/scripts/model/user_context.js",
           "../commons/scripts/model/request_objects.js",


		   "../commons/scripts/model/domain_objects.js",
           "../commons/scripts/widget/slick.pager.js",
"../thirdparty/jquery/jquery.dynatree.min.js",
"../scripts/util/actiondialog.js",
"../scripts/pages/empresa/empresa.js",
"../scripts/pages/regime/regime.js",
"../scripts/pages/documento/documento.js",
"../scripts/pages/endereco/endereco.js",
"../scripts/pages/email/email.js",
"../scripts/pages/phone/telefone.js",
"../scripts/pages/cnae/cnae.js",
"../scripts/pages/status/status.js",
"../scripts/pages/note/note.js",
"../scripts/pages/socios/socios.js",
"../scripts/pages/plano/plano.js",
"../scripts/pages/filial/filial.js",
"../scripts/pages/deposito/deposito.js"

		//   "../scripts/util/base.js"
	);
</script>
<jsp:include page="../scripts/pages/wd_main_main.js.jsp" flush="true"/>
<jsp:include page="../scripts/pages/wd_main_init.js.jsp" flush="true"/>
<jsp:include page="user_info.jsp" flush="true"/>
<jsp:include page="../commons/scripts/util/app_reuse_functions.js.jsp" flush="true"/>
</BODY>
</HTML>