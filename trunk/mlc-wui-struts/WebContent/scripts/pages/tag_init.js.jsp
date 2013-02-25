<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">
/**
 * @namespace sensus.pages.systemsettings.tag
 * @description The init namespace for the Tag Page.
 * @fileoverview Initializes the group page.
 * @author Raphael Constantino
 *
 */
 $(document).ready(function() {
 
	sensus.util.page.initMessaging();

	var bubble2 = $('.bubble2'); 
	
	//bubbles		
	bubble2.CreateBubblePopup();
	
	//set customized mouseover event for each button
	bubble2.mouseover(function() {

		//remove title 
		$this = $(this);
		$.data(this, 'title', $this.attr('title'));
		$this.removeAttr('title');			

		//show the bubble popup with new options
		$(this).ShowBubblePopup({
			width         : '200px',
			innerHtml     : $.data(this, 'title'),
			themeName     : $(this).attr('rel'),
			themePath     : 'images/jquerybubblepopup-theme',								 
			themeMargins  : {
				total      : '5px',
				difference : '5px'
			},
			innerHtmlStyle : {
				color        : ($(this).attr('id')!='azure' ? '#000000' : '#333333'), 
				'text-align' : 'center'
			}								
	  });

	});
	
	bubble2.mouseout(function() {
		// add title back
		$this = $(this);
		$this.attr('title', $.data(this, 'title'));					
	});

	/** * jQuery dataTable setup ** */
	sensus.pages.systemsettings.tag.tagTable = $('#tag-table').dataTable(sensus.widgets.datatable.setTable({

		id          : "tag-table",
		sAjaxSource : sensus.settings.searchTagUrl,
		aColumns    : [
			{sId : "Id",                  sWidth : "5%", bVisible : false},
			{sId : "tagName",             sWidth : "5%", bSortable : true},
			{sId : "autoGroup",           sWidth : "5%"},
			{sId : "tagSmartPointCount",  sWidth : "5%", bSortable : true},
			{sId : "tagDateAdded",        sWidth : "5%", bSortable : true}
		],
 		oSettings   : {
			oRequest      : inquiryTagRequest,
			aColumns      : ['id','name','autoGroup','totalSmartpoints','date',''],
			sResponseObj  : 'tags',				
			iDefaultCol   : 1,
			fnRequest     : function() {

				var aSearchParameters  = [],
					search             = new SearchMeter(aSearchParameters);

				return search;

			}, 
			<sec:authorize ifAllGranted="ROLE_Role.Admin">
			remove : {
					url   : sensus.settings.deleteTagUrl,
					text  : function(data, i) {
						return sensus.locale.get("action.deletetag.tagName", data[i.tagName]);
					},
					oRequest   : 'tagRequest',
					fnRequest  : function(data, i) {

						var aIds = [];
						aIds.push(data[i.Id]);
						var request = new tagRequest(data[i.Id], null, null, null, null, null);
						return request;

					},					
					success : function(data, i) {

						$('#tag-add-select option').each(function() {

							if($(this).text() == data[1]) {

								$(this).remove();

							}

						});						
						
						
						$.address.parameter("iDisplayStart", 0);
						sensus.widgets.datatable.reloadTable(sensus.pages.systemsettings.tag.tagTable,0);
						return sensus.locale.get("action.deletetag.success");
					}
			},
			</sec:authorize>
			smartpointFilter  : {						
				aCols   : ["tagSmartPointCount"],
				filter  : "tags"
			}
		},
				
		fnRowCallback : function(nRow, aData, iDisplayIndex, col) {

			//Format date
			$("td:eq(" +col.tagDateAdded+ ")", nRow).text($.datepicker.formatDate(sensus.settings.dateFormatMask, new Date(aData[col.tagDateAdded].replace('T',' ').replace(/-/g,'/').split('.')[0])));

			//Format number
			$("td:eq("+ col.tagSmartPointCount +")", nRow).text($.convertionNumber(aData[col.tagSmartPointCount],false,0).dvalueConverter);
					
			// Put strong in names
			$("td:eq("+ col.tagName  +")", nRow).html("<strong>"+ aData[col.tagName] +"</strong>");

			$('td:eq(' + col.tagSmartPointCount + ')', nRow).addClass("num");
					
			// Add AutoGroup CheckBox
			var sChecked = "";

			if (aData[col.autoGroup] == true) {

				sChecked = "checked";

			} else if (aData[col.autoGroup] == false) {

				sChecked = "";

			}

			// Add link to smartpoint list
			if (parseInt(aData[col.tagSmartPointCount]) > 0) {	

				sensus.pages.systemsettings.tag.filterTagId = aData[col.Id];
				$('td:eq(' + col.tagSmartPointCount + ')', nRow).html("<a class='afilter' href='smartpoint/ajax.list.action?tags="+ sensus.pages.systemsettings.tag.filterTagId +"|'>"+ $.convertionNumber(aData[col.tagSmartPointCount],false,0).dvalueConverter +"</a>");

			} else {

				$('td:eq(' + col.tagSmartPointCount + ')', nRow).html('<span class="ui-state-disabled">'+$.convertionNumber(aData[col.tagSmartPointCount],false,0).dvalueConverter+'</span>');
			
			}
			
			var td = $('td:eq(' + col.autoGroup + ')', nRow);
			
		//	if (aData[col.autoGroup]) {
		
			var sAutoGroupHtml = '<input id="add-to-group-check-' +  aData[col.Id]+ '" type="checkbox" ' + sChecked +'/>' + " <small class='autoGroupTbody'>" + sensus.locale.get("tag.table.autoGroupTable") + "</small>";
		
			if (sensus.settings.userContext.userRole != 'ROLE_Role.Admin') {
				sAutoGroupHtml = '<input disabled id="add-to-group-check-' +  aData[col.Id]+ '" type="checkbox" ' + sChecked +'/>' + " <small class='autoGroupTbody'>" + sensus.locale.get("tag.table.autoGroupTable") + "</small>";
			}
				
		//	} else {
		//		var sAutoGroupHtml = '<input id="add-to-group-check-' +  aData[col.Id]+ '" type="checkbox" title="' + sensus.locale.get('tag.table.autogrouptooltips') + ' ' + aData[col.tagName] + '"' + sChecked +'/>' +
	    //		" <small class='autoGroupTbody'>" + sensus.locale.get("tag.table.autoGroupTable") + "</small>";
		//	}

			td.html(sAutoGroupHtml);
			
			$(td).find("input").click(function() {
				
				if ($(this).is(":checked")) {
					
					$.ajax({ 
						dataType    : 'json',
						url      : sensus.settings.existGroupWithTagName,
						type     : 'POST',
						data         : $.toJSON({'groupRequest' : new groupRequest(aData[col.tagName])}),
						async    : false,
						contentType : "application/json; charset=utf-8",
						success  : function(data) {
							if (data.groups.length) {

								sensus.pages.systemsettings.tag.tagId = aData[col.Id];
								sensus.pages.systemsettings.tag.tagName = aData[col.tagName];
								sensus.pages.systemsettings.tag.tagDescription = data.groups[0].description;
								sensus.pages.systemsettings.tag.smartpointCount = aData[col.tagSmartPointCount];
								sensus.util.actiondialog.launchActionDialog("autoExistingGroupInclude", sensus.pages.systemsettings.tag.dialogSettings["autoExistingGroupInclude"]);

							} else if (data.groups == "FAIL") {

								sensus.util.page.showMessage("messaging-main", sensus.pages.systemsettings.tag.dialogSettings["autoExistingGroupInclude"], "error");

							} else {

								sensus.pages.systemsettings.tag.tagId = aData[col.Id];
								sensus.pages.systemsettings.tag.tagName = aData[col.tagName];
								sensus.pages.systemsettings.tag.smartpointCount = aData[col.tagSmartPointCount];
								sensus.util.actiondialog.launchActionDialog("autoNoGroupInclude", sensus.pages.systemsettings.tag.dialogSettings["autoNoGroupInclude"]);

							}
						}
					});

				} else {
					
					sensus.pages.systemsettings.tag.tagId = aData[col.Id];
					sensus.pages.systemsettings.tag.tagName = aData[col.tagName];
					sensus.util.actiondialog.launchActionDialog("suspendAutoGroupInclude", sensus.pages.systemsettings.tag.dialogSettings["suspendAutoGroupInclude"]);
				
				}
				
			});
			
		}
		
	}));

	//Init checkboxes
	$('#tag-add-select').checkbox( {
		zIndex : 3999
	});
	
	var oTtagAddInput = $("#tag-add-select_input");
	
	oTtagAddInput.val(sensus.locale.get("tag.page.addTag"));
	oTtagAddInput.focus(function() {

		if ($(this).val() == sensus.locale.get("tag.page.addTag")) {

			$(this).val("");

		}	
	});

	oTtagAddInput.next().attr("title", sensus.locale.get("tag.page.addTag"));

	$(".create-action-container").find('button').click(function() {

		var sValIpt = $(this).prev().val().trim();
		var iCount = 0;

		$("#tag-add-select option").each(function() {

			if ($(this).text().toLowerCase() == sValIpt.toLowerCase()) {
				iCount ++;
			}

		});

		// BEGIN Validation
		if (sValIpt == sensus.locale.get('widgets.combobox.prompt')) {

			$('#tag-add-select_input').validationEngine('showPrompt', sensus.locale.get("tag.page.error.validValue"), 'red', '', true);
			return;

		}

		if (sValIpt.length > 100) {

			$('#tag-add-select_input').validationEngine('showPrompt', sensus.locale.get("tag.page.error.maxlength"), 'red', '', true);
			return;

		}

		if ((sValIpt == sensus.locale.get('tag.page.addTag')) || (!sValIpt.length)) {
			
			$('#tag-add-select_input').validationEngine('showPrompt', sensus.locale.get("tag.page.error.required"), 'red', '', true);
			return;
			
		} 

		if (iCount > 0) {

			$('#tag-add-select_input').validationEngine('showPrompt', sensus.locale.get("tag.page.error.exist"), 'red', '', true);
			return;

		}
		// END Validation
				
		var sTags = $('#tag-add-select_input').val();

		$('.formError').remove();

		//Send ajax action
		sensus.util.ajaxaction.actionUrlAdress = sensus.settings.addTag;
		sensus.util.ajaxaction.data = { 
			'tagRequest': new tagRequest(null, sTags, null, sensus.widgets.datatable.isAllRows, null, null) 
		};

		sensus.util.ajaxaction.sendActionDefault(sensus.locale.get("action.inserttag.success", [sTags, sensus.settings.smartpointUrl]));

		if (sensus.util.ajaxaction.actionResult) {

			$('#tag-add-select_input').val(sensus.locale.get('tag.page.addTag'));
			$('#tag-add-select').append('<option>'+sValIpt+'</option>');
			sensus.widgets.datatable.reloadTable(sensus.pages.systemsettings.tag.tagTable);

			sensus.util.ajaxaction.actionResult = false;

		}

	});	

});

</script>
</sec:authorize>