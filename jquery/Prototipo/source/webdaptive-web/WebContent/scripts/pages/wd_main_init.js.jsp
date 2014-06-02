<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
//executes when head.js says all js files loaded
head(function()
{
	$(document).ready(function ()
	{
		//visual fade in effect
		$("#wdcontent").fadeIn(1000);
		$.ajaxSetup (
		{
			// Disable caching of AJAX responses
			cache: false
		});
		//long polling every 3 seconds
	    setInterval(getMessages, 3000);
	  	//adding to async data in the backend
	    $('#send_async').click(function() {
	    		$.ajax({
	    		type		: "GET",
	    		url			: WDHost + "qat-webdaptive/async/api/postMessage/"+ $("#txtMessage").val(),
	    		contentType : "text",
	         	success		: function(data,textStatus,jqXHR){
	         				$("#txtMessage").val("");
	         	},
	            error		: function (jqXHR, textStatus, errorThrown) { wd.core.process_xhr_error(jqXHR, textStatus, errorThrown); }
	    		});
	    });
		//localization plugin
		$.i18n.properties(
		{
			name: WDi18nPropFile,
			path: '../' + WDi18nPropFilePath,
			mode: 'both',
			language: WDi18nLanguage,
			callback: function()
			{
				//Session Management
				$("#dialog").dialog({
					autoOpen: false,
					modal: true,
					width: 450,
					height: 250,
					closeOnEscape: false,
					draggable: false,
					resizable: false,
					buttons: {
						'Yes, Keep Working': function(){
							$(this).dialog('close');
						},
						'No, Logoff': function(){
							// fire whatever the configured onTimeout callback is.
							// using .call(this) keeps the default behavior of "this" being the warning
							// element (the dialog in this case) inside the callback.
							$.idleTimeout.options.onTimeout.call(this);
						}
					}
				});

				// cache a reference to the countdown element so we don't have to query the DOM for it on each ping.
				var $countdown = $("#dialog-countdown");

				// start the idle timer plugin
				$.idleTimeout('#dialog', 'div.ui-dialog-buttonpane button:first', {
					idleAfter: WDIdleTimeout,
					pollingInterval: WDPollingInterval,
					keepAliveURL: 'keep_alive.jsp',
					serverResponseEquals: 'OK',
					failedRequests: 1,
					onTimeout: function(){
						window.location.href = "../timeout.jsp";
					},
					onAbort: function(){
						window.location.href = "../timeout.jsp";
					},
					onIdle: function(){
						$(this).dialog("open");
					},
					onCountdown: function(counter){
						$countdown.html(counter); // update the counter
					}
				});

				//create WebDaptive Default Layout
				wdLayout = $('#wdcontent').layout(layoutSettings_WebDaptive);
				//Set WebDaptive Title
				document.title = wdheadertitle;
				//Set WebDaptive Header Fields
				//$('#logotext').html(wdlogotext + "<br>" +  wdlogotext2);
				$('#appname').html(wdheadertitle);
				$('#header-logout').text(wdheaderlogout);
				wd.core.showTime();
				wd.session.userId = uUserName;
				wd.session.userPassword = uUserPassword;
				wd.session.authorities = uAuthorities;
				$("#send_async").html(wdmain.send.label);
				$('#user').html( wdmain.userid.label + wd.session.userId + '<br/>' + wdmain.userrole.label + wd.session.authorities);
				// ACCORDION - in the West pane
			    $("#west-accordion").accordion({heightStyle: "fill"});
				//Tree Menu plugin
				$("#tree").dynatree({
					keyboard: true,
					onActivate: function(node) {
						treeval = ($.address.parameter('id'));
						tabval = ($.address.parameter('tab'));
						// A DynaTreeNode object is passed to the activation handler
						if( node.data.url )
						{
							if (wd.core.isEmpty(tabval) ||(treeval != node.data.key))
							{
								tabval = 0;
							}
							$.address.value('/wdtree?id=' + node.data.key + "&tab=" + tabval);
							$('#content').load(node.data.url);
						}
					},
					children: [
								{title: wdtree.node1.title, isFolder: true, expand: true, tooltip: wdtree.node1.tooltip,
									children: [
										{title: wdtree.node1.child1.title, key: 'qatmvctabs', url: "qat_mvc_tabs.jsp"},
									]
								},
								{title: wdtree.node2.title, isFolder: true, expand: true, tooltip: wdtree.node2.tooltip,
									children: [
										{title: wdtree.node2.child1.title, key: 'qatbastabs', url: "qat_bas_tabs.jsp"},
									]
								},
						        <sec:authorize ifAllGranted="ROLE_DOMAIN ADMINS">
								{title: wdtree.node3.title, isFolder: true, expand: true, tooltip: wdtree.node3.tooltip,
									children: [
										{title: wdtree.node3.child1.title, key: 'wdothertabs', url: "wd_other_tabs.jsp"},
									]
								},
								</sec:authorize>
					]
					,
					imagePath: '../thirdparty/jquery/styles/tree/'
					,
					dnd: {
						onDragStart: function(node) {
							/** This function MUST be defined to enable dragging for the tree.
							*  Return false to cancel dragging of node.
							*/
							return true;
						},
						onDragStop: function(node) {
							// This function is optional.
						},
						autoExpandMS: 1000,
						preventVoidMoves: true, // Prevent dropping nodes 'before self', etc.
						onDragEnter: function(node, sourceNode) {
							/** sourceNode may be null for non-dynatree droppables.
							*  Return false to disallow dropping on node. In this case
							*  onDragOver and onDragLeave are not called.
							*  Return 'over', 'before, or 'after' to force a hitMode.
							*  Return ['before', 'after'] to restrict available hitModes.
							*  Any other return value will calc the hitMode from the cursor position.
							*/
							return true;
						},
						onDragOver: function(node, sourceNode, hitMode) {
							/** Return false to disallow dropping this node.
							*
							*/
						},
						onDrop: function(node, sourceNode, hitMode, ui, draggable) {
							/** This function MUST be defined to enable dropping of items on
							* the tree.
							*/
							sourceNode.move(node, hitMode);
							// expand the drop target
							sourceNode.expand(true);
						},
						onDragLeave: function(node, sourceNode) {
							/** Always called if onDragEnter was called.
							*/
						}
					}
				});

	            // Init and change handlers
				$.address.externalChange(function(event)
				{
					path = $.address.path();
					if (path == '/wdtree')
					{
						var nodeval = ($.address.parameter('id'));
						$("#tree").dynatree("getTree").activateKey(nodeval);
						var node = $("#tree").dynatree("getActiveNode");
						tabval = ($.address.parameter('tab'));
						if (wd.core.isEmpty(tabval))
						{
							tabval = 0;
						}
						$.address.value('/wdtree?id=' + node.data.key + "&tab=" + tabval);
						$('#content').load(node.data.url);
					}
	            });

				//Set WebDaptive Footer
				$('#footer').html("Copyright &copy; QAT Global 1995-" + (new Date().getFullYear()) + ". All rights reserved.");
			}
		});
	});
});
</script>