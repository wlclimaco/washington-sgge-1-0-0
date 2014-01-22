<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
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

	var bubble2 = $('.bubble2');

	/** bubbles */
	bubble2.CreateBubblePopup();

	/**
	 * set customized mouseover event for each button
	 */
	bubble2.mouseover(function()
	{

		/** remove title */
		$this = $(this);
		$.data(this, 'title', $this.attr('title'));
		$this.removeAttr('title');

		/** show the bubble popup with new options */
		$(this).ShowBubblePopup({
			width         : '200px',
			innerHtml     : $.data(this, 'title'),
			themeName     : $(this).attr('rel'),
			themePath     : 'images/jquerybubblepopup-theme/',
			themeMargins  :
			{
				total        : '5px',
				difference 	 : '5px'
			},
			innerHtmlStyle   :
			{
				color        : ($(this).attr('id')!='azure' ? '#000000' : '#333333'),
				'text-align' : 'center'
			}
	    });

	});

	bubble2.mouseout(function()
	{
		/** add title back */
		$this = $(this);
		$this.attr('title', $.data(this, 'title'));
	});

	/**
	 *jQuery dataTable setup
	 */
	sensus.pages.systemsettings.tag.tagTable = $('#tag-table').dataTable(sensus.widgets.datatable.setTable({

		id          : "tag-table",
		bPreLoad : true,
		<c:choose>
		    <c:when test="${empty tags}">
		    	oPreLoadResponse : null,
		    </c:when>
		    <c:otherwise>
		    	oPreLoadResponse : ${tags},
		    </c:otherwise>
		</c:choose>
		sAjaxSource : "api/tag/fetch",
		aColumns    : [
			{sId : "Id",                  sWidth : "5%", bVisible  : false},
			{sId : "tagName",             sWidth : "5%", bSortable : true},
			{sId : "autoGroup",           sWidth : "5%"},
			{sId : "tagSmartPointCount",  sWidth : "5%", bSortable : true},
			{sId : "tagDateAdded",        sWidth : "5%", bSortable : true}
		],
 		oSettings   :
 		{
			oRequest      : inquiryTagRequest,
			aColumns      : ['id','name','autoGroup','totalLights','date',''],
			sResponseObj  : 'tags',
			iDefaultCol   : 1,
			fnRequest     : function()
			{
				return null;
			},

			<sec:authorize ifAllGranted="ROLE_Role.Admin">

				remove :
				{
						url   : "api/tag/delete",
						text  : function(data, i)
						{
							return $.sc.locale("action.deletetag.tagName", data[i.tagName]);
						},
						oRequest   : 'tagRequest',
						fnRequest  : function(data, i)
						{
							var aIds = [];
							aIds.push(data[i.Id]);
							var request = new tagRequest(data[i.Id], null, null, null, null, null);
							return request;
						},
						success : function(data, i)
						{
							$('#tag-add-select option').each(function()
							{
								if($(this).text() == data[1])
								{
									$(this).remove();
								}
							});


							$.address.parameter("iDisplayStart", 0);
							sensus.widgets.datatable.reloadTable(sensus.pages.systemsettings.tag.tagTable,0);
							return $.sc.locale("action.deletetag.success");
					    }
				},

			</sec:authorize>

			smartpointFilter  :
			{
				aCols   : ["tagSmartPointCount"],
				filter  : "tags"
			}
		},

		fnRowCallback : function(nRow, aData, iDisplayIndex, col)
		{

			/** Format date */
			$("td:eq(" +col.tagDateAdded+ ")", nRow).text($.sc.dateFormat(aData[col.tagDateAdded], sensus.settings.dateFormatMask));

			/** Format number */
			$("td:eq("+ col.tagSmartPointCount +")", nRow).text($.convertionNumber(aData[col.tagSmartPointCount],false,0).dvalueConverter);

			/** Put strong in names */
			$("td:eq("+ col.tagName  +")", nRow).html("<strong>"+ aData[col.tagName] +"</strong>");

			$('td:eq(' + col.tagSmartPointCount + ')', nRow).addClass("num");

			/** Add AutoGroup CheckBox */
			var sChecked = "";

			if (aData[col.autoGroup])
			{
				sChecked = "checked";
			}
			else
			{
				sChecked = "";
			}


			/** Add link to smartpoint list */
			if (parseInt(aData[col.tagSmartPointCount]) > 0)
			{

				sensus.pages.systemsettings.tag.filterTagId = aData[col.Id];
				$('td:eq(' + col.tagSmartPointCount + ')', nRow).html("<a class='afilter' href='light?tags="+ sensus.pages.systemsettings.tag.filterTagId +"|'>"+ $.convertionNumber(aData[col.tagSmartPointCount],false,0).dvalueConverter +"</a>");

			}
			else
			{

				$('td:eq(' + col.tagSmartPointCount + ')', nRow).html('<span class="ui-state-disabled">'+$.convertionNumber(aData[col.tagSmartPointCount],false,0).dvalueConverter+'</span>');

			}

			var td = $('td:eq(' + col.autoGroup + ')', nRow);

			var sAutoGroupHtml = '<input id="add-to-group-check-' +  aData[col.Id]+ '" type="checkbox" ' + sChecked +'/>' + " <small class='autoGroupTbody'>" + $.sc.locale("tag.table.autoGroupTable") + "</small>";

			if (sensus.settings.userContext.userRole != 'ROLE_Role.Admin')
			{
				sAutoGroupHtml = '<input disabled id="add-to-group-check-' +  aData[col.Id]+ '" type="checkbox" ' + sChecked +'/>' + " <small class='autoGroupTbody'>" + $.sc.locale("tag.table.autoGroupTable") + "</small>";
			}


			td.html(sAutoGroupHtml);

			$(td).find("input").click(function()
			{

				if ($(this).is(":checked"))
				{

					var json = $.sc.getJson("api/tag/verifyautogroup", {tagName: aData[col.tagName]}, false, null, null);

					if (json.groups.length)
					{

						sensus.pages.systemsettings.tag.tagId = aData[col.Id];
						sensus.pages.systemsettings.tag.tagName = aData[col.tagName];
						sensus.pages.systemsettings.tag.tagDescription = json.groups[0].description;
						sensus.pages.systemsettings.tag.smartpointCount = aData[col.tagSmartPointCount];
						$.sc.launchActionDialog("autoExistingGroupInclude", sensus.pages.systemsettings.tag.dialogSettings["autoExistingGroupInclude"]);

					}
					else if (json.groups == "FAIL")
					{

						$.sc.showMessage("messaging-main", sensus.pages.systemsettings.tag.dialogSettings["autoExistingGroupInclude"], "error");

					}
					else
					{

						sensus.pages.systemsettings.tag.tagId = aData[col.Id];
						sensus.pages.systemsettings.tag.tagName = aData[col.tagName];
						sensus.pages.systemsettings.tag.smartpointCount = aData[col.tagSmartPointCount];
						$.sc.launchActionDialog("autoNoGroupInclude", sensus.pages.systemsettings.tag.dialogSettings["autoNoGroupInclude"]);

					}

				}
				else
				{

					sensus.pages.systemsettings.tag.tagId = aData[col.Id];
					sensus.pages.systemsettings.tag.tagName = aData[col.tagName];
					$.sc.launchActionDialog("suspendAutoGroupInclude", sensus.pages.systemsettings.tag.dialogSettings["suspendAutoGroupInclude"]);

				}

			});
		}
	}));

	/** Init checkboxes */
	$('#tag-add-select').checkbox({
		zIndex : 3999
	});

	var oTtagAddInput = $("#tag-add-select_input");
	var oActionContainer = $(".create-action-container");

	oTtagAddInput.val($.sc.locale("tag.page.addTag"));
	oTtagAddInput.focus(function()
	{

		if ($(this).val() == $.sc.locale("tag.page.addTag"))
		{

			$(this).val("");

		}

	});
	oTtagAddInput.blur(function()
	{
		if ($(this).val() == "")
		{

			$(this).val($.sc.locale("tag.page.addTag"));

		}
	});

	oTtagAddInput.next().attr("title", $.sc.locale("tag.page.addTag"));

	oActionContainer.find('button').click(function()
	{

		sensus.pages.tag.createTag();

	});

	oActionContainer.on("keypress",'#tag-add-select_input',  function(key)
	{

		if(key.which == 13)
		{

			sensus.pages.tag.createTag();

		}
	});

});

</script>
</sec:authorize>