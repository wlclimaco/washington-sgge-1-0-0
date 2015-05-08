<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

pgsi.pages.note = {

		form :{

			/**
			 * Validate the fields
			 */
			validator : $("#create-note-form").validate({
				ignore : "",
				invalidHandler : function(form, validator) {
					$.each(validator.errorList, function(index, value) {
						if (value.element.nodeName.toLowerCase() == 'select') {
							$(value.element).next('span').addClass("error");
						}
						else {
							$(value.element).addClass("error");
						}

					});
				}
			}),

			/**
			 * Validate the fields required at Contact Form
			 */
			validator :	$( "#create-note-form" ).validate(),

			fnInitForm : function() {
				pgsi.util.page.form.fnInitTolltip("#note.error");
			},

			fillRequest : function(sModelAction) {

				var oNote = new Note;
				oNote.modelAction = sModelAction;
				oNote.noteText = $("#add-notes-template").find("textarea").val();

				return oNote;
			},

			fnAjaxCallFetchAll : function(fnCallBackFetch,sUrl){

				var fnCallBackFetch = function(oResponseFetch) {
					if (oResponseFetch.operationSuccess == true) {
						if (parseInt($('#business-type').val()) == 1) {
							pgsi.pages.note.view.fill(oResponseFetch.organizationList[0].noteList, oResponseFetch.organizationList[0]);
						}
						else if(parseInt($('#business-type').val()) == 2){
							pgsi.pages.note.view.fill(oResponseFetch.locationList[0].noteList, oResponseFetch.locationList[0]);
						} else if(parseInt($('#business-type').val()) == 3){
							pgsi.pages.note.view.fill(oResponseFetch.memberList[0].noteList, oResponseFetch.memberList[0]);
						} else if (parseInt($('#business-type').val()) == 5){
							pgsi.pages.note.view.fill(oResponseFetch.recipientList[0].noteList, oResponseFetch.recipientList[0]);
						}

						$("#action-dialog").dialog('close');
					}
					else{
						pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponseFetch,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
					}
					$.pgsi.progressBar.stop();
				}

				if(parseInt($('#business-type').val()) == 1){
					sPurl = "api/organization/fetch";
					iId   = parseInt($('#business-id').val(),10);
				}
				else if(parseInt($('#business-type').val()) == 2){
					sPurl = "api/location/fetch";
					iId   = parseInt($('#business-id').val(),10);
				}
				else if(parseInt($('#business-type').val()) == 3){
					sPurl = "api/member/fetch";
					iId   = parseInt($('#member-id').val(),10);
				}
				else if(parseInt($('#business-type').val()) == 5){
					sPurl = "api/recipient/fetch";
					iId   = parseInt($('#recipient-id').val(),10);
				}

				$.pgsi.ajax.post({
					 sUrl       : sPurl,
					 oRequest   : {id:iId},
					 fnCallback : fnCallBackFetch
				});
			},

			fnAjaxCallInsertUpdateNote : function(oRequest,sUrl,fnCallBack){

				var bValidForm = pgsi.pages.note.form.validator.form();
				if(bValidForm){
					var fnCallBack = function(oResponse) {

						if (oResponse.operationSuccess == true) {

							pgsi.pages.note.form.fnAjaxCallFetchAll(fnCallBack,sUrl);
							$("#action-dialog").dialog('close');
						}

						else {
							pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
						}
					}
					oRequest.note.noteText = $('#note').val();
					$.pgsi.ajax.post({
						 sUrl 		: sUrl,
						 oRequest 	: oRequest,
						 fnCallback : fnCallBack
					});
				}
			}
		},
		view :{
			fill : function(oNoteList, oBusinessParent) {

				var oNote = null;
				var sNoteList = "";
				var sDelUpdLinks = "";

				var sDate;
				var sUser;
				var sNoteText;

				var $container = $("section.notes").find("div.container");

				$("section.notes").find(".col-title").find('a').unbind("click");

				for (var i=0; i < oNoteList.length; i++) {
					oNote = oNoteList[i];

					sUser = oNote.createUser;

					if (!$.pgsi.isNullOrUndefined(oNote.modifyDateUTC)) {
						sDate = $.pgsi.date.format(new Date(oNote.modifyDateUTC), "mm/dd/yy h:i A", true);
					}

					else {
						sDate = $.pgsi.date.format(new Date(oNote.createDateUTC), "mm/dd/yy h:i A", true);
					}

					sNoteText = oNote.noteText;
					iNoteId = oNote.id;

					if (!pgsi.util.page.fnIsSDNFlagged(oBusinessParent.sdnstatus)) {
						<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
						sDelUpdLinks = "<div class='small-box'><div class='links viewNote'><a href='"+iNoteId+"'  class='ui-subtitle edit' title='" + $.pgsi.locale.get('commons.pages.edit') + "'> <span class='icon-small-button icon-nav icon-pencil edit'></span> <span>" + $.pgsi.locale.get('commons.pages.edit') +"</span></a><a href='"+iNoteId+"'  class='ui-subtitle delete' title='" + $.pgsi.locale.get('commons.pages.delete') + "'> <span class='icon-small-button icon-nav icon-trash-bin delete'></span> <span>"+$.pgsi.locale.get('commons.pages.delete')+"</span></a></div></div>";
						</sec:authorize>
					}

					sNoteList = sNoteList + "<div class='outer-box'><div class='box note'>" + sDelUpdLinks + "<span class='bold'>" + sUser + "</span><span class='date'>" + sDate +"</span><p class='full-text hide'>" + sNoteText + "</p><p></p><div class='text_here'><span class='ellipsis_text'>" + sNoteText + "</span></div></div></div>";
				}

				// Removes content from the session
				$container.empty();

				if (sNoteList.length > 0) {
					$container.append(sNoteList);
					// Limiting content
					// Configuring the initial settings to use
					$container.find('.text_here').ThreeDots({max_rows:4});
				}

				else {
					$("section.notes").find('div.container').append("<p class='empty'>" + $.pgsi.locale.get("page.business.view.note.empty") + "</p>");
				}

				// Attach add/edit/delete events
				$("section.notes.view  a").click(function(event)
				{
					event.preventDefault();
					var sNote  = $(this).parents('.box');
					var oNote = new Note({
								parentKey      : parseInt($('#business-id').val(),10),
								parentKeyValue : parseInt($('#business-type').val()),
								parentKeyType  : parseInt($('#business-type').val()),
								id             : parseInt($(this).attr("href")) ,
								noteText       : sNote.find('p').text(),
								modelAction    : "INSERT"});
					var oRequest = new NoteMaintenanceRequest({note : oNote});

					if (!$(this).hasClass('delete')) {
						if($(this).hasClass('edit')) {
							oNote.modelAction = "UPDATE";
							var sUrl = "api/note/edit",
							sTitle   = $.pgsi.locale.get("pages.business.dialog.note.title.edit",$('#business-name').val());
						}else{
							oNote.modelAction = "INSERT";
							var sUrl = "api/note/insert",
							sTitle   = $.pgsi.locale.get("pages.business.dialog.note.title.add",$('#business-name').val());
						}

						pgsi.util.actiondialog.launchActionDialog("insUpdNote", pgsi.pages.business.dialogSettings.insUpdNote($('#business-id').val(),sTitle,sUrl,new NoteMaintenanceRequest({note : oNote}),null));
					}

					else {

						pgsi.pages.note.fnDelete(oNote);
					}
				});
			}

		},
		fnDelete : function(oNote) {

			var fnCallBack = function(oResponse) {
				pgsi.pages.note.form.fnAjaxCallFetchAll();
			}

			oNote.modelAction = "DELETE";
			sTitle = $.pgsi.locale.get("pages.business.dialog.note.title.delete",$('#business-name').val());
			pgsi.util.actiondialog.launchActionDialog("deleteDialog", pgsi.pages.business.dialogSettings.deleteDialog("api/note/delete", new NoteMaintenanceRequest({note : oNote}),sTitle,fnCallBack,$.pgsi.locale.get("commons.pages.erroView","note")));
		},

		//Set up Required Selects
	initRequiredSelects : function() {
		$( "span.ui-selectmenu-text" ).each(
			function(index) {
				if (!$(this).text().trim())
				{
					$(this).parent( "span.ui-selectmenu-button" ).addClass( "required" );
				}
				else
				{
					$(this).parent( "span.ui-selectmenu-button" ).removeClass( "required" );
				}
			});
	}
}

</script>

</sec:authorize>