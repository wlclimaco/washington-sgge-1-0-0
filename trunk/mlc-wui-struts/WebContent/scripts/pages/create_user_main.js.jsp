<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">

/**
 * @namespace     sensus.pages.user
 * @author        Vinicius Scalon Ferreira
 * @description   The main namespace for the User Page.
 * @fileoverview  Defines the core functionality of the User page.
 *
 * The main namespace for the User Page.
 *
 */
sensus.pages.createUser = {

	loadIncludeLights : function(){
	
		$('.select-group, .select-tag, .operator, .summary, .overflow').show();
		$('.all-desc-container').hide();
		$('#select-groups-label').html('Groups:').addClass('short');
		$('#select-tags-label').html('Tags:').addClass('short');
	
	},
	/**
	 * Do a iterator on the groups to get the id of the lights
	 *
	 * @param aUserGroups - Array of the groups
	 * 				
	 * @returns void
	 * 			
	 */
	fnGetLightLatLongFromGroup : function(aUserGroups) {
		
		var nLat  = 0;
		var	nLng  = 0;
		var	iGroups  = 0;
		var	oMapItIcon  = $('.map-col .text_button');
		var oSelectedLights = $('.map-col strong');
		
		sensus.pages.user.aGroupsId = [];

		for (var iKeyG in aUserGroups) {

			// Check if is property of this object
			if (!aUserGroups.hasOwnProperty(iKeyG)) {
				return;
			}
			
			// Set Group IDs
			sensus.pages.user.aGroupsId.push({'id' : aUserGroups[iKeyG].id });

			// Do the average and set Latitude and Longitude on namespace
			if (aUserGroups[iKeyG].centerLatitude && aUserGroups[iKeyG].centerLongitude) {
				var fCenterLatitude = aUserGroups[iKeyG].centerLatitude;
				var fCenterLongitude = aUserGroups[iKeyG].centerLongitude;
						
				if ((fCenterLatitude != 0) || (fCenterLongitude != 0)){
					iGroups++;
				
					nLat = sensus.pages.createUser.fnGetCurrentLatOrLng(nLat, fCenterLatitude, iGroups);
					nLng = sensus.pages.createUser.fnGetCurrentLatOrLng(nLng, fCenterLongitude, iGroups);
				}
			}
			
			
			
			
			for (var iKeyLight in aUserGroups[iKeyG].lights) {

				// Check if is property of this object
				if (!aUserGroups[iKeyG].lights.hasOwnProperty(iKeyLight)) {
					return;
				}

				var iValid = 0;
				var iLightId = aUserGroups[iKeyG].lights[iKeyLight].id;	

				iValid = jQuery.inArray(iLightId, sensus.pages.user.aLightsId);
			
				if (iValid < 0) {

					sensus.pages.user.aLightsId.push(iLightId);
				}
			}
		}
		
		if (sensus.pages.user.aLightsId.length < 1) {
			
			oMapItIcon.addClass('ui-state-disabled');
			
		} else {
			
			oMapItIcon.removeClass('ui-state-disabled');
			
		}
		
		sensus.pages.user.nLat = nLat;
		sensus.pages.user.nLng = nLng;
		oSelectedLights.text(sensus.pages.user.aLightsId.length);

 	},
	
	fnGetCurrentLatOrLng : function(nLatLng, fValue, iGroups){
		
		nLatLng = (nLatLng + fValue)/iGroups;
		
		return nLatLng;
	},
	
	fnCountSmartpoints : function(aGroupIds, isAllGroups) {

		var nLat = 0;
		var nLng = 0;
		var iGroups = 0;
		var i = 0;
		
		sensus.pages.user.nLat = 0;
		sensus.pages.user.nLng = 0;
		$('.chzn-select option:selected').each(function() {

			var aData = $(this).attr('class').split('#');
			var	aLatLng = aData[1].split('|');
			var fLat = parseFloat(aLatLng[0]);
			var fLng = parseFloat(aLatLng[1]);
					
			//nLightsSelected	= nLightsSelected + parseInt(aData[0]);

			if (((fLat != 0) && (fLng != 0)) && ((!isNaN(fLng)) && (!isNaN(fLng)))){
			
				iGroups++;
				sensus.pages.user.nLat = sensus.pages.user.nLat + fLat;
				sensus.pages.user.nLng = sensus.pages.user.nLng + fLng;
				
			}
			
			i++;
			
		});
		
		sensus.pages.user.nLat = sensus.pages.user.nLat/iGroups;
		sensus.pages.user.nLng = sensus.pages.user.nLng/iGroups;
		
		var	oRequest = {'groupRequest': new groupRequest(null, null, null, null, null, null, null, null, aGroupIds)};
		
		function fnCount(data){
		
			var nLightsSelected = data.resultsSetInfo.totalRowsAvailable;
		
			if (nLightsSelected < 1) {

				$('.map-col .text_button').addClass('ui-state-disabled');

			} else {

				$('.map-col .text_button').removeClass('ui-state-disabled');

			}
		
			$('.map-col strong').text(nLightsSelected);	
		}
		
		if (aGroupIds.length) {
			$.ajaxValidator.fnDoCall('user/ajax.countLightsbyGroup.action', oRequest, false, fnCount, null);
		} else {
			$('strong', '.map-col').text('0');
		}

	},
	
	doChanges : function(oElement, oKey) {
		var aIds = [];

		// Get all ids from the elements selecteds
		$('.chzn-choices').find('.search-choice').each(function(iKey, oElem) {
		
			var sId = $(oElem).find('a').attr('id');
			
			if (oKey.deselected && oKey.deselected == sId) {
				return;
			}
			aIds.push({ id : sId});
			
		});
		
		sensus.pages.createUser.fnCountSmartpoints(aIds, false);

	},
	
	fnFillGroupsCallBack : function(data) {

		var aGroups   = data.groups;
		var sOptions  = '';
		var oSelectGroups = $('#select_groups');
		var nUserId = $.address.parameter('userId');
		var	oElementChosen  = $(".chzn-select");
	
		if (aGroups){
			var nLat = 0;
			var nLng = 0;
			
			for (var g=0; g < aGroups.length; g++) {
				
					if(aGroups[g].centerLatitude){
						nLat = aGroups[g].centerLatitude;
					}else{
						nLat = 0;
					}
					if(aGroups[g].centerLongitude){
						nLng = aGroups[g].centerLongitude;
					}else {
						nLng = 0;
					}			
					sOptions += '<option class="' + aGroups[g].smartPointCount + '#' + nLat + '|' + nLng + '" value="' + aGroups[g].id + '">'+aGroups[g].name+'</option>';
				
			}
		}

		oSelectGroups.append(sOptions);

		if (!nUserId) {
			// Get all ids from the elements selecteds
			oElementChosen.chosen().change(function(oElement, oKey) { 
				sensus.pages.createUser.doChanges(oElement, oKey);
			});
		}
	},
	
	fnFillUser : function(data) {

		var	oElementChosen  = $(".chzn-select");
		
		$('.content-header h1').html(sensus.locale.get('smartpointdetail.location.edit')+' "'+data.user.firstName+' '+data.user.lastName+'"');
		$('#user-first-create').val(data.user.firstName);
		$('#user-last-create').val(data.user.lastName);
		$('#user-email-create').val(data.user.email);
		$('#user-user').val(data.user.userName).attr('disabled', true);
		$('.description').html(sensus.locale.get("user.page.editrequired"));

		if ($('.radio').find('input:checked').val() != data.user.role) {

			$('#include-all-lights, #include-some-lights').removeAttr("disabled");

		}

		if (!data.user.allLightsAuth) {

			$('#include-all-lights').attr('checked', false);
			$('#include-some-lights').attr('checked', true);

			//enable group access control
			$('.select-group, .summary, .all-desc-container, .overflow').show();

		}

		var sRoles = data.user.role;
		
		if (sRoles) {

			$(".radio-list").find("input[value='" + sRoles + "']").attr('checked', true);

		}

		var aUserGroups = data.user.groups;
		
		sensus.pages.createUser.fnGetLightLatLongFromGroup(aUserGroups);

		for (g in aUserGroups) {

			if (aUserGroups.hasOwnProperty(g)) {

				$('#select_groups option[value="' + aUserGroups[g].id + '"]').prop('selected',true);

			}

		}
		
		// After selec a options, is call the plugin CHOSEN
		oElementChosen.chosen().change(function(oElement, oKey) { 
			sensus.pages.createUser.doChanges(oElement, oKey);
		});

	},
	
	ajaxCall   : function(sUrl, sMessage) {

		var	i = 0;
		var sRoles = [];
		var aGroups = [];
		var sPass = $("#user-password-create").val();
		var bAllLightsAuth = '';

		sRoles = $('.radio').find('input:checked').val();

		bAllLightsAuth = $("input[name='select-lights']:checked").val();

		if (bAllLightsAuth == 'false') {

			$('.chzn-choices .search-choice a').each(function() {

				aGroups[i] = { id : parseInt($(this).attr('id')) };
				i++;

			});			
		}

		if ($('#user-password-create').val() == '') {

			sPass = null;

		}

		var request = {
			'userRequest' : new userRequest(
								$.address.parameter('userId'), 
								$("#user-user").val(), 
								$("#user-first-create").val(), 
								sRoles, 
								$("#user-last-create").val(), 
								sPass, 
								$("#user-email-create").val(), 
								bAllLightsAuth, 
								aGroups, null, null
							)
		};
		
		var fnCallBack = function(data) {

			if (data && data.operationSuccess) {

				//sMessage = sensus.locale.get("action.adduser.success", $("#user-user").val());

				sensus.commons.lib.ajax.do_load($.extend({}, sensus.commons.lib.ajax.param, {
					sUrl     : 'user/ajax.list.action',
					message  : {
						bMessage : true,
						sMessage : sMessage
					}
				}));					

			} else {

				sensus.util.page.stopProgressBar(0,true);
				sensus.util.page.showMessage("messaging-main", sensus.util.page.getMessageList(data.messageList), "error");

			}
		};
		
		$.ajaxValidator.fnDoCall(sUrl, request, false, fnCallBack);

	},
	fnGroupsValidator : function (){
		if($("#include-some-lights").attr("checked")){
			if ($(".chzn-choices li.search-choice").length){
				return true;
			} else {
				var element = "#select_groups_chzn";
				$(element).validationEngine('showPrompt', sensus.locale.get("validation.fieldrequired"), 'red', '', true);

				return false;
			}
		}
		return true;
	}
 };

</script>
</sec:authorize>