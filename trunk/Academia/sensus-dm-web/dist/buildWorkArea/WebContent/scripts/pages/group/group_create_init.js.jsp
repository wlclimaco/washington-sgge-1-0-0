<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
	$(document).ready(function() {

		//Receives preloaded data
		<c:choose>
		    <c:when test="${empty group}">
		    	var oPreLoadResponse = null;
		    </c:when>
		    <c:otherwise>
		    	var oPreLoadResponse = ${group};
		    </c:otherwise>
		</c:choose>

		var iGroupID = $.address.parameter("id"),
			oDesc = $(".desc"),
			oGroupCreateTitle= $("#group-create-title"),
			oGroupCreateDescription = $("#group-create-description"),
			oGroupForm = $("#group-form"),
			oLegendDevice = $("#legend-device"),
			oSubmitForm = $(".submit-form"),
			oForm = $("#group-form"),
			oGroupCreateMeterType = $("#meterTypeContent");

		// Initialize Messages
		sensus.util.page.initMessaging();

		// Initialize Group Type
		sensus.pages.group.fnInitGroupType(oGroupCreateMeterType);

		// Style buttons
		$("a.button").button();

		// Function for objects manipulation in common at create and edit page
		function loadCreateGroup(oHeadDescription, sHeadDescription, oLegend, sLegend, oShow, oGroupForm, sAction) {

			oHeadDescription.html(sensus.locale.get(sHeadDescription)
					+ " <span class='required'>" + sensus.locale.get("commons.pages.required")
					+ "</span> " + sensus.locale.get("systemintelligence.create.action.paragraphDescriptionInformation"));

			oLegend.text(sensus.locale.get(sLegend));

			oShow.show();

			oGroupForm.attr("action", sAction);
		}

		// Loading the create page, if the URL have ID the page is loaded for the group edit.
		if (!iGroupID) {

			loadCreateGroup(oGroupCreateDescription, "groupcreate.page.requiredCreate", oLegendDevice,
					"groupcreate.page.addsmartpoints", oDesc, oGroupForm, sensus.settings.insertGroupSubmit);

			oGroupCreateTitle.text(sensus.locale.get("groupcreate.page.creategrouptitle"));

			$("#group-devices-create").show();

		} else {

			// Function for to fill the edit group page
			var fnCallback = function (oGroupResponse) {

				var response = oGroupResponse.groups[0],
					oGroupId = $("#group-id"),
					oTotalDevice = $("#total-smartpoint, #total-devices"),
					oGroupName = $("#group-name-create"),
					oOldName = $("#group-old-name"),
					oGroupDescription = $("#group-description"),
					oGroupType = $("#type"),
					oDeviceType = $("#meterType"),
					oAddReplaceDevices = $("#add-replace-devices"),
					oSelectShorter = $("select.shorter"),
					aLink;

				oGroupCreateTitle.text(sensus.locale.get("groupcreate.page.editgroup") + ' "' + response.name + '"');
				oTotalDevice.text(response.devicesCount);
				oGroupId.val(response.id);
				oGroupName.val(response.name);
				oOldName.val(response.name);
				oGroupDescription.val(response.description);

				if (!response.description) {

					oGroupDescription.text(response.description);
				}

				oGroupType.val(response.groupTypeEnumValue).prop("selected", true)

				if (response.deviceType) {

					oDeviceType.val(response.hanDeviceType || response.deviceType).prop("selected", true).prop("disabled", true); // Disable device Type select on edit

					aLink = ["?device_type=", response.deviceType, "|&group=", iGroupID, "|"];

					if (response.hanDeviceType) {

						aLink.push("&device_subtype=", response.hanDeviceType, "|");
					}

				} else {

					aLink = ["?group=", iGroupID, "|"];
				}

				// Add link of View in List
				$(".read-only a").attr("href", "/" + sensus.util.page.fnFormatURLService() + aLink.join(""));
			};

			// Load the edit group page populated for AJAX
			var fetchRequest = new FetchRequest({id : iGroupID, type: "editGroup"});
			fnCallback(oPreLoadResponse);
	// 		$.ajaxValidator.fnDoCall("api/group/fetch", fetchRequest, false, fnCallback);

			loadCreateGroup(oGroupCreateDescription, "groupcreate.page.requiredEdit", oLegendDevice,
					"groupcreate.page.editSmartpoint", $("#group-devices, #add-replace-devices"),
					oGroupForm, sensus.settings.updateGroupSubmit);

			oSubmitForm.find("span").text(sensus.locale.get("groupcreate.page.buttoneditgroup"));
		}

		// Validation Engine
		$("#group-form").validationEngine("attach", {

			onValidationComplete: function(form, status) {

				if (!status) {

					sensus.util.page.stopProgressBar();

				} else {

			         form.validationEngine("detach");
			         form.submit();
				}
			}
		});

		// Toggle Group
		$("#toggleGroup").toggle("blind", null, 500);

		$("#click").click(function(e) {

			e.preventDefault();

			$("#toggleGroup").toggle("blind", null, 500);

			if ($("span", this).hasClass("ui-icon-triangle-1-e")) {

				$("span", this).switchClass("ui-icon-triangle-1-e", "ui-icon-triangle-1-s", 500);

			} else {

				$("span", this).switchClass("ui-icon-triangle-1-s", "ui-icon-triangle-1-e", 500);
			}
		});

		// Check Error
		if ($.address.parameter("check")) {

			if ($.address.parameter("check") == "error") {

				sensus.util.page.stopProgressBar();

				sensus.util.page.showMessage("messaging-main", sensus.locale.get("action.addgroup.error"), "error");
			}

			$.fn.pageLoader.parameter("check", "");
		}

		$("#back-group, #ajax-button").click(sensus.pages.group.fnBackToListGroupEvent);

		// Upload Field Change Event
		$("#upload").change(function() {

			var $uploadList = $("#upload-list");

			sensus.pages.group.isSubmit = true;

			if ($(this).val().length) {

				$uploadList.attr("disabled", true);

			} else {

				$uploadList.attr("disabled", false);
			}
		});

		// Upload List Field Blur Event
		$("#upload-list").bind({

			blur :  function() {

				$upload = $("#upload");

				if ($(this).val().length) {

					$upload.attr("disabled", true);

				} else {

					$upload.attr("disabled", false);
				}
			}
		});

		// Submit (Save or Edit) Button
		$("#create-group-button").click(function(e) {

			sensus.util.page.startGlobalProgressBar();

			var sGroupName = $("#group-name-create").val().trim();

			var bValidGroup = sensus.pages.group.groupValidator(sGroupName);

			var fnCallback = function(bMonitored) {
				$("#b-monitored").val(bMonitored);
				sensus.pages.group.checkGroupName($("#group-form"), sGroupName);
			}

			e.preventDefault();

			if (bValidGroup) {

				var uploadVal = $("#upload").val();
				var uploadListVal = $("#upload-list").val();

				if (($.sc.isNullOrUndefined(uploadVal) || uploadVal.length == 0)
						&& ($.sc.isNullOrUndefined(uploadListVal) || uploadListVal.length == 0)) {

					fnCallback(false);

				} else {

					sensus.pages.longrunningprocess.monitorUpload(fnCallback);
				}

			} else {
				sensus.util.page.stopGlobalProgressBar();
			}
		});

		$(".smartpoint-count").text($.convertionNumber($("#total-smartpoint").text(), false, 0).dvalueConverter);

		$("body").delegate(".uploadformError", "click", function(e) {

			$upload = $("#upload");

			if ($.browser.msie) {

				$upload.replaceWith($("#upload").clone());

			} else {

				$upload.val("");
			}
		});

		sensus.util.page.stopGlobalProgressBar();
	});
	</script>

</sec:authorize>