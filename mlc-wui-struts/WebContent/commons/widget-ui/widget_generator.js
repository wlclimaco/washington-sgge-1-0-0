	// plugin definition

$summary = null;
/**
 * @param sId
 * 			string, id of device
 * @param sType
 * 			string, type of summary
 * @return date
 * 			date java script format
 */

$.fn.summary = function(sId, sType,bHideTabs) {

	var oDialogContent = $(this);
	
	var sProcessId = sId;
	
	var sdialog = "";
	
	var content = {
			
			tabs : {
				
				/**
				 * Clear All Tabs
				 */
				fnClearTabs : function(){
					
					var oTabs = $('#tabs', oDialogContent);
					
					oTabsNav = $('ul.ui-tabs-nav li', oTabs);
					oTabsNav.removeClass('ui-tabs-selected ui-state-active');
					
				},
				
				/**
				 * Ajax Content of tab
				 */
				fnAjaxContent : function(sPage) {
					
					var oResponse;					
					$.ajax({
						  url: sPage,
						  async : false,
						  success: function(data) {
							  oResponse = data;
						  }
					});
					
					return oResponse;

				},
				
				/**
				 * Change the tab
				 * 
				 * @param {Object} obj
				 */
				fnChangeTab : function(oSelected){
					
					var sContent = $(oSelected).attr('id'),
						oContent = $('#content', oDialogContent),
						sUrl = $(oSelected).find('a').attr('href');
					
					/**
					 * Clear the Tabs
					 */
					content.tabs.fnClearTabs();
					
					$(oSelected).addClass('ui-tabs-selected ui-state-active');
					
					/**
					 * Load Content of tab
					 */
					var sContentHtml = content.tabs.fnAjaxContent(sUrl);
					
					
					/**
					 * Displays the contents of tab
					 */
					$(oContent).empty().append(sContentHtml);
					
					/**
					 * Performs functions java script
					 */
					content[sContent].init();
					
				},
				
			init : function(){
				bHideTabs = true;
				// Style for the plugin, either tabs or box  
				if (bHideTabs) {
					var oSummaryContainer = $("#summary-container", oDialogContent);
					oSummaryContainer.removeClass("hide");
					
					var sContentHtml = content.tabs.fnAjaxContent("commons/pages/summary/communication_summary.jsp");
					$(oSummaryContainer).append(sContentHtml);
					content["createDisplay"].init();

				} else {
					var oTabs = $('#tabs', oDialogContent);
					oTabs.removeClass("hide");
					
					var oDemandResponseSummary =  $("#demandResponseSummary",oTabs);
					var oImportHanDevices = $("#importHanDevices",oTabs);
					var oCommunicationSummary = $("#communicationSummary",oTabs);
					sType = 'Import HAN Devices';
					if(sType === 'Import HAN Devices'){
						oDemandResponseSummary.hide();
						content.tabs.fnChangeTab(oImportHanDevices);
					} else if(sType === 'Demand Response'){
						oImportHanDevices.hide();
						oCommunicationSummary.hide();
						content.tabs.fnChangeTab(oDemandResponseSummary);
						
					} else {
						var oFirstTab =  $(".ui-tabs-nav li:first",oTabs);
						oImportHanDevices.hide();
						oDemandResponseSummary.hide();
						content.tabs.fnChangeTab(oFirstTab);
						
					}	



					$('ul.ui-tabs-nav li', oTabs).click(function(e){
						e.preventDefault();
						content.tabs.fnChangeTab(this);
					});
				}
				
			}

				
		},
			
			util : {
				
				fnValidation : function(sField){
					if(sField == null || sField == 'null' || sField == '[null]'){
						return '';
					} else {
						return  sField;
					}
				},
				
				
				createData : function(dataReq){
					
					var data = [[],[{
						UNDEFINED : 0,
						FAILED: 0,
						RUNNING: 0,
						CANCELLED: 0,
						TIMED_OUT: 0,
						COMPLETED: 0,
						NOT_STARTED: 0,
						INTERRUPTED: 0,
						PAUSED: 0,
						ABORTED: 0,
						UNREACHABLE: 0,
						TOO_MANY_ATTEMPTS: 0,
						DEVICE_DOES_NOT_ACCEPT_FUTURE_TIME: 0,
						REPEATED: 0,
						INVALID_TARGET: 0,
						INVALID_ID: 0
						
					}],[{
						CONNECTED : 0,
						DISCONNECTED: 0
					}]];
					for(var x= 0;dataReq.length > x ;x++){

						if(dataReq[x].device.connectionStatus == "CONNECTED"){
							data[2][0].CONNECTED = data[2][0].CONNECTED + 1;
						} else if(dataReq[x].device.connectionStatus == "DISCONNECTED" || dataReq[x].device.connectionStatus == null){
							data[2][0].DISCONNECTED = data[2][0].DISCONNECTED + 1;
						}
						if(data[1][0].hasOwnProperty(dataReq[x].processItemStatusEnum)){
							data[1][0][dataReq[x].processItemStatusEnum] = data[1][0][dataReq[x].processItemStatusEnum]+1; 
						}
						
						if(dataReq[x].processItemStatusEnum != 'COMPLETED'){
							data[0].push({"device_id":content.util.fnValidation(dataReq[x].device.clientEndPointId),"Network_Address":content.util.fnValidation(dataReq[x].device.macAddress),"Premise_ID":content.util.fnValidation(dataReq[x].device.premiseId),"Address":content.util.fnValidation(dataReq[x].device.address),"State":sensus.locale.get("summary.text.processStatus."+dataReq[x].processItemStatusEnum),"Error":content.util.fnValidation(sensus.locale.get(dataReq[x].message))});
						}
					}
					return data;
				},
				createDataDemandResponse : function(dataReq){
					
					var data = [[],[{
						fullParticipation : 0,
						partialParticipation: 0,
						optout: 0
					}]];
					
					
					for(var x= 0;dataReq.length > x ;x++){
							if(dataReq[x].participation == "FullParticipation"){
								data[1][0].fullParticipation = data[1][0].fullParticipation+1; 
								data[0].push({"device_id":content.util.fnValidation(dataReq[x].device.clientEndPointId),"Network_Address":content.util.fnValidation(dataReq[x].device.macAddress),"ParentDeviceID":content.util.fnValidation(dataReq[x].device.baseFlexNetId),"Premise_ID":content.util.fnValidation(dataReq[x].device.premiseId),"NetworkStatus":content.util.fnValidation(dataReq[x].device.connectionStatus),"FullParticipation":"Yes","PartialParticipation":"No","OptOut":"No"});
							} else if(dataReq[x].participation == "PartialParticipation"){
								data[1][0].partialParticipation = data[1][0].partialParticipation+1;
								data[0].push({"device_id":content.util.fnValidation(dataReq[x].device.clientEndPointId),"Network_Address":content.util.fnValidation(dataReq[x].device.macAddress),"ParentDeviceID":content.util.fnValidation(dataReq[x].device.baseFlexNetId),"Premise_ID":content.util.fnValidation(dataReq[x].device.premiseId),"NetworkStatus":content.util.fnValidation(dataReq[x].device.connectionStatus),"FullParticipation":"No","PartialParticipation":"Yes","OptOut":"No"});
							}else if(dataReq[x].participation == "OptOut"){
								data[1][0].optout = data[1][0].optout+1;
								data[0].push({"device_id":content.util.fnValidation(dataReq[x].device.clientEndPointId),"Network_Address":content.util.fnValidation(dataReq[x].device.macAddress),"ParentDeviceID":content.util.fnValidation(dataReq[x].device.baseFlexNetId),"Premise_ID":content.util.fnValidation(dataReq[x].device.premiseId),"NetworkStatus":content.util.fnValidation(dataReq[x].device.connectionStatus),"FullParticipation":"No","PartialParticipation":"No","OptOut":"Yes"});
							}else if(dataReq[x].participation == "PartialParticipationAndOptOut"){
								data[1][0].optout = data[1][0].optout+1;
								data[0].push({"device_id":content.util.fnValidation(dataReq[x].device.clientEndPointId),"Network_Address":content.util.fnValidation(dataReq[x].device.macAddress),"ParentDeviceID":content.util.fnValidation(dataReq[x].device.baseFlexNetId),"Premise_ID":content.util.fnValidation(dataReq[x].device.premiseId),"NetworkStatus":content.util.fnValidation(dataReq[x].device.connectionStatus),"FullParticipation":"No","PartialParticipation":"Yes","OptOut":"Yes"});
							} else {
								data[0].push({"device_id":content.util.fnValidation(dataReq[x].device.clientEndPointId),"Network_Address":content.util.fnValidation(dataReq[x].device.macAddress),"ParentDeviceID":content.util.fnValidation(dataReq[x].device.baseFlexNetId),"Premise_ID":content.util.fnValidation(dataReq[x].device.premiseId),"NetworkStatus":content.util.fnValidation(dataReq[x].device.connectionStatus),"FullParticipation":"No","PartialParticipation":"No","OptOut":"No"});
							}
					}
					return data;
				},
				
			 
				createTableHeader : function(data, seletor) {
					var oSmtBody = $("#" + seletor + " > thead", oDialogContent),
						aux = [],
			  	  		sTableLines = "";
					
					sTableLines += '<tr>';
					
					for(x = 0; data.length >= x; x++)
					{
					    for(z in data[x] ) {
					    	// Check if item is not in array, and add it 
					    	var bInArray = $.inArray(z, aux); 
							if(bInArray === -1) {
								aux.push('' + z);
							}
					    }
					}

					for(var x = 0;aux.length > x ; x++ ){
						sTableLines += '<th>'+ sensus.locale.get("summary.text.headerTable."+aux[x]) + '</th>';
					}
					sTableLines += '</tr>';
					oSmtBody.append(sTableLines);

					
				},
				
				createDisplay : function(data){
					var oSmtBody,
					sTableLines = "";
					for(var x= 0;oData.length > x ;x++){
						sTableLines +='<input type="text" id="Editbox2" style="position:absolute;left:146px;top:72px;width:418px;height:19px;line-height:19px;z-index:65;" name="Editbox2" value="${razemp}">';
					}
					oSmtBody.append(sTableLines);
				},
							
				
				createInfoDemandResponse : function(enrollmentCode,offset,dutyCycleRate,loadAdjustment,criticalityLevel,randomize,seletor){
					var oSmtBody = $("." + seletor, oDialogContent),			  	  	
						sTableLines = "";
					
					sTableLines = '<dt>Enrollment Code</dt><dd>'+content.util.fnValidation(enrollmentCode)+'</dd><dt>Offset</dt><dd>'+content.util.fnValidation(offset)+'</dd><dt>Duty Cycle Rate</dt><dd>'+content.util.fnValidation(dutyCycleRate)+'%</dd><dt>Load Adjustment</dt><dd>'+content.util.fnValidation(loadAdjustment)+'%</dd><dt>Criticality Level</dt><dd>'+content.util.fnValidation(criticalityLevel)+'</dd><dt>Randomize</dt><dd>'+content.util.fnValidation(randomize)+'</dd>';
					oSmtBody.append(sTableLines);
				},

				createInformation : function(title,value,subHead,seletor) {
					var oSmtBody = $("#"+seletor+" > tbody >tr", oDialogContent),
			  	  		sTableLines = "";
					
					if(title === "Start") {
						dates = $.date.dateFormat(value,"h:i A").split(" ");
						sTableLines += "<td title=''><span class='title'>"+title+"</span> <strong class='value'>"+dates[0]+"<small>"+dates[1]+"</small></strong><small class='sub-head'>"+$.date.dateFormat(value, sensus.settings.dateFormatMask)+"</small></td>";
					} else {
						sTableLines += "<td title=''><span class='title'>"+title+"</span> <strong class='value'>"+value+"</strong><small class='sub-head'>"+subHead+"</small></td>";
					}
					
					oSmtBody.append(sTableLines);
				},
				

				
				createTableHeaderInformation : function(seletor,sPage,oProcessStatus) {

					var oSmtBody = $("#"+seletor+" > tbody >tr", oDialogContent),
			  	  	sTableLines = "";
					
					var fnCheckPlural = function(amount, key){
						if(amount > 1){
							return key+'.plural.';
						} else{
							return key+'.single.';
						}
					}

					sTableLines += '<td class="last fail" title="">';
					sTableLines += '<ul>';
					for(var status in oProcessStatus[0]){	
										
						if(status != 'COMPLETED' && oProcessStatus[0][status] > 0){
							if(sPage === 'importHanDevice' && status === 'FAILED'){
								if(oProcessStatus[0].FAILED > 0 ){
									sTableLines += '<li class="fail"><strong>'+oProcessStatus[0][status]+'</strong> Devices were unreachable <a href="#" id="unreachable" class="button small ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">Retry Connect</a></li> ';
								}
							}else {
								sTableLines += '<li class="fail">'+sensus.locale.get(fnCheckPlural(oProcessStatus[0][status],"summary.text.processStatusMessage")+status, ''+oProcessStatus[0][status]+'')+'</li>';
							}
						}
					}

					sTableLines += '</ul>';
					sTableLines += '</td>';
						
					oSmtBody.append(sTableLines);
					
				},
				listDeviceString : function(data, sKey) {
					var unreachableIds="";
					for(var x= 0;data.length > x ;x++){
						if( x == 0){
							unreachableIds = "&"+sKey+"="+data[x].device.flexNetId;
						}else{
							unreachableIds = unreachableIds + "&"+sKey+"=" + data[x].device.flexNetId;
						}					
					}
					return unreachableIds;
				},
				listMacAdresstring : function(data, sKey) {
					var unreachableIds="";
					for(var x= 0;data.length > x ;x++){
						if(data[x].processItemStatusEnum === "FAILED"){
							if( x == 0){
								unreachableIds = "&"+sKey+"="+data[x].device.macAddress
							}else{
								unreachableIds = unreachableIds + "&"+sKey+"=" + data[x].device.macAddress;
							}	
						}
					}
					return unreachableIds;
				},
				createTableLine : function(data,seletor) {
					
					var oSmtBody = $('#'+seletor+' > tbody:last', oDialogContent),
			  	  		sTableLines = "",
			  	  		z = "",
			  	  		sDecodeFailed = "",
			  	  		sData="",
			  	  		sTypeFailed = "",
			  	  		sMessage = "sensus.epm.process.nc.process.";
					
					for (var x = 0; data.length > x; x++) {
						
						sTableLines += '<tr>';
						
						for (z in data[x]) {
							
							sData = data[x][z];
							
							if (sData.contains(sMessage)) {
								
								if (sData.contains("decode.failed")) {
									sDecodeFailed = sData.substring(sData.indexOf(".failed") + 7, sData.length).split(" ")[0];
									sDecodeFailed = sDecodeFailed.replace(/\]/g, "");
									sTableLines += '<td>'+ sensus.locale.get("sensus.epm.process.nc.process.decode.failed") + " " + sDecodeFailed + '</td>';
								} else if (sData.contains("sensus.epm.process.nc.process.invalid")) {
									sDecodeFailed = sData.substring(sData.indexOf(".invalid") + 8, sData.length).split(" ")[0];
									sDecodeFailed = sDecodeFailed.replace(/\]/g, "");
									sTableLines += '<td>'+ sensus.locale.get("sensus.epm.process.nc.process.invalid") + " <strong>" + sDecodeFailed + '</strong></td>';
								} else {
									sTypeFailed = sData.substring(sData.indexOf(".nc") + 12, sData.length).split(" ")[0];
									sTypeFailed = sTypeFailed.replace(/\]/g, "");
									sTableLines += '<td>'+ sensus.locale.get(sMessage+sTypeFailed) + '</td>';
								}
								
							} else {
								sData = content.util.replaceAll(sData, "[", "");
								sData = content.util.replaceAll(sData, "]", "");
								sTableLines += '<td>'+ sData + '</td>';
							}
						}
						sTableLines += '</tr>';
					}
					oSmtBody.append(sTableLines);
				},
				createTableLineImportHan : function(data,seletor) {
					
					var oSmtBody = $('#'+seletor+' > tbody:last', oDialogContent),
			  	  		sTableLines = "",
			  	  		z = "",
			  	  		sDecodeFailed = "",
			  	  		sData="",
			  	  		sTypeFailed = "",
			  	  		sMessage = "sensus.epm.process.nc.process.";
					
					for (var x = 0; data.length > x; x++) {
						
						sTableLines += '<tr>';
						
						for (z in data[x]) {
							
							sData = data[x][z];
							if (sData.contains(sMessage)) {
								
								if (sData.contains("decode.failed")) {
									sDecodeFailed = sData.substring(sData.indexOf(".failed") + 7, sData.length).split(" ")[0];
									sDecodeFailed = sDecodeFailed.replace(/\]/g, "");
									sTableLines += '<td>'+ sensus.locale.get("sensus.epm.process.nc.process.decode.failed") + " " + sDecodeFailed + '</td>';
								} else {
									sTypeFailed = sData.substring(sData.indexOf(".nc") + 12, sData.length).split(" ")[0];
									sTypeFailed = sTypeFailed.replace(/\]/g, "");
									sTableLines += '<td>'+ sensus.locale.get(sMessage+sTypeFailed) + '</td>';
								}
								
							} else {
								sData = content.util.replaceAll(sData, "[", "");
								sData = content.util.replaceAll(sData, "]", "");
								sTableLines += '<td>'+ sData + '</td>';
							}
						}
						sTableLines += '</tr>';
					}
					oSmtBody.append(sTableLines);
				},
				
				/**
				 * Format text of time spent
				 * @param  - [String] pHours time spent in hours
				 * 			 [String] pMinutes time spent in minutes
				 * 			 [String] pSeconds time spent in seconds
				 * 			 [Integer] iAborted
				 * @return - [String]
				 */
				
				fnGetTime : function(pHours, pMinutes,pSeconds, iAborted) {
					
					if (iAborted === 1) {

						return sensus.locale.get("summary.text.wasAborted");

					} else {

						var iHours = parseInt(pHours),
							iMinutes = parseInt(pMinutes),
							iSeconds = parseInt(pSeconds)
							sHours = "",
							sMinutes = "",
							sSeconds = "";
						
						if (iHours == 1) {
							sHours = iHours +" "+sensus.locale.get("commons.pages.hour");
						} else if (iHours > 1) {
							sHours = iHours +" "+sensus.locale.get("commons.pages.hours");
						}

						if (iMinutes == 1) {
							sMinutes = iMinutes +" "+sensus.locale.get("commons.pages.minute");
						} else if (iMinutes > 1) {
							sMinutes = iMinutes +" "+sensus.locale.get("commons.pages.minutes");
						} 

						if (iSeconds == 1) {
							sSeconds = iSeconds +" "+sensus.locale.get("commons.pages.second");
						} else if (iSeconds > 1) {
							sSeconds = iSeconds +" "+sensus.locale.get("commons.pages.seconds");
						} else {
							sSeconds = "1 "+sensus.locale.get("commons.pages.second");
						}

						return ' '+((iHours != 0 &&iMinutes > 0) ? sHours + ' '+sensus.locale.get("commons.pages.and")+' '  : sHours) + sMinutes + ((iMinutes == 0 && iHours == 0 ) ? sSeconds:'');
					}
				},
				
				replaceAll : function(message, token, newToken) {
					while (message.indexOf(token) != -1) {
						message = message.replace(token, newToken);
					}
					return message;
				},
				
				createInfoFailed : function(sum,seletor) {
					var oSmtBody = $('.'+seletor, oDialogContent),
			  	  	sTableLines = "";
					sTableLines = '<strong>'+sum+'</strong> Failed Devices Listed Below';
					oSmtBody.append(sTableLines);
				},
				percentage : function(sum,per) {
					if(sum == 0){
						return 0;
					} else {
						var avg = 0;
						avg = (per * 100)/sum;
						return Math.floor(avg);						
					}
				},
				createDemandResponse : function(data) {
					var enrollmentCode,
						offset,
						dutyCycleRate,
						loadAdjustment,
						criticalityLevel,
						randomize;
				
					enrollmentCode = data.enrollmentCode;
					dutyCycleRate  = data.dutyCycleRate;
					loadAdjustment = data.loadAdjustment;
					criticalityLevel=data.criticalityLevel;
					if(data.randomize){
						randomize = "Start";
					}else{
						randomize = "End";
					}
					if(data.cooling){
						offset = "Cooling "+data.cooling+" degrees F°";
					}else{
						offset = "Heating "+data.cooling+" degrees F°";	
					}
				
				
					content.util.createInfoDemandResponse(enrollmentCode,offset,dutyCycleRate,loadAdjustment,criticalityLevel,randomize,"sui-pad1");
				},
				
				fnGetProcessItems : function(oData){
					var processItems = [],
						y = 0;
					
					for(var x= 0;oData.length > x ;x++){
						
						if(oData[x].processItemStatusEnum != 'COMPLETED'){
							processItems[y] = oData[x];
							y++;
						}

					}
					return processItems;
				}

				
					
			},
			
			fnCommunicationSummary : function() {
				var	oCommunicationSummary = $('.communication-summary', oDialogContent),
    			oExport = $('.csv',oCommunicationSummary);
			
				$.ajax({
			        url : "process/generateSummaryCommunication.action",
			        data: "processId="+sId+"&iProcId="+sId,
			        success: function( resp ) {
			        	if(resp.processes[0].failedSmartpoints != undefined){
			        		var iFailedSmartpoints = resp.processes[0].failedSmartpoints;
			        	}else{
			        		var iFailedSmartpoints = 0;
			        	}
			        	
			        	
			        	/** Fills description of the process */
			        	content.fnFillDescriptionProcess(resp);
			        	
			        	
						content.util.createInfoFailed(resp.processes[0].failedSmartpoints,"fail");
						content.util.createInformation("Success",content.util.percentage(resp.processes[0].totalSmartpoints,resp.processes[0].totalSmartpoints-resp.processes[0].failedSmartpoints)+"%",resp.processes[0].totalSmartpoints-resp.processes[0].failedSmartpoints+" Devices","informationTable");
						content.util.createInformation("Failed",content.util.percentage(resp.processes[0].totalSmartpoints,resp.processes[0].failedSmartpoints)+"%",resp.processes[0].failedSmartpoints +" Devices","informationTable");
						
						
						if(iFailedSmartpoints > 0){
							var aData = content.util.createData(resp.processes[0].processItems);
								oSize = $('.size'),
								oTitleFail = $('h4.fail'),
								oTable = $('div.selected-points');
								
							oSize.text(iFailedSmartpoints);
							content.util.createTableHeader(aData[0], "tableCommunicationSummary");					
							content.util.createTableLine(aData[0], "tableCommunicationSummary");
							content.util.createTableHeaderInformation("informationTable","communicationSummary",aData[1]);
							
							oTitleFail.show();
							oTable.show();
						}
						
			        }
			    });
				
				/**
				 * Export Communication Summary
				 */
				oExport.click(function(e){
					
					e.preventDefault();
					sensus.util.page.startProgressBar();
					e.preventDefault();
					/** set process id to url **/
					$.address.parameter("process_id", sId);
					/** set status item to url **/
					//$.address.parameter("status_item", 10);
					/** generate csv file **/
					sensus.util.exportcsv.generateCSV("process/ajax.insertCSVProcess.action", "process/exportCommunicationSummary.action");
			});
			
			
			
			},
			
			fnDemandResponseSummary : function() {
				
				var	oDemandResponseSummary = $('.demand-response-sumary', oDialogContent),
				oExport = $('.csv',oDemandResponseSummary);
			
				var oResponse = null;
				
				var oGetDemandResponseEventStatus = $('#tabs .button');
				
				var aProcessItems = null;
				
				
				//$('.spindown-child').toggle('blind', null, 500);
				$('.spindown').click(function(e) {
					e.preventDefault();
					$('.spindown-child').toggle('blind', null, 500);
				});
	
				$.ajax({
			        url : "process/generateSummaryDemandResponse.action",
			        data: "processId="+sId+"&iProcId="+sId,
			        success: function( resp ) {
			        	oResponse = resp;
	
			        	
			        	var aData = content.util.createDataDemandResponse(resp.processes[0].processItems),
			        		oProcess = resp.processes[0],
			        		oSize = $('.size'),
			        		iOptOut = aData[1][0].optout,
		        			iPartialParticipation = aData[1][0].partialParticipation,
		        			iFullParticipation = aData[1][0].fullParticipation,
		        			iReceivedStart = iOptOut+iPartialParticipation+iFullParticipation,
		        			iTotal = oResponse.processes[0].totalSmartpoints,
		        			oTabs = $('#tabs', oDialogContent),
		        			oCommunicationSummary = $("#communicationSummary",oTabs);
			        	
			        	oSize.text(iTotal);
			        	
			        	if(oProcess.processStatusEnum == 'COMPLETED'){
			        		oCommunicationSummary.show();
			        	}
	
			        	
			        	aProcessItems = content.util.fnGetProcessItems(resp.processes[0].processItems)
			        	iSize = aProcessItems.length;
			        	if(iSize){
			        		var oStatError = $('#content .ui-state-error'); 
			        		oStatError.find('strong').empty().text(iSize);
			        		oStatError.show();
			        	}
	
			        	content.util.createTableHeader(aData[0], "tableDemandResponse");					
						content.util.createTableLine(aData[0], "tableDemandResponse");						
						content.util.createDemandResponse(resp.processes[0].actionView);
						
						content.util.createInformation("Start",resp.processes[0].startTime,"","summaryDemandResponse");
						
						content.util.createInformation("Duration",content.util.fnValidation(resp.processes[0].actionView.demandResponseDuration),"Minutes","summaryDemandResponse");
	
						
						content.util.createInformation("Participation",content.util.percentage(iReceivedStart,iFullParticipation)+"%",iFullParticipation+" of "+iReceivedStart,"summaryDemandResponse");							
	
						content.util.createInformation("Received Start",iReceivedStart,"","summaryDemandResponse");
						
						content.util.createInformation("Full Participation",iFullParticipation,"","summaryDemandResponse");							
	
						content.util.createInformation("Partial Participation",iPartialParticipation,"","summaryDemandResponse");
						
						content.util.createInformation("Opt-out",iOptOut,"","summaryDemandResponse");
						
	
						
			        }
			    });
				
				
				oGetDemandResponseEventStatus.button().click(function(e){
					
					e.preventDefault();
					
					$('#content .ui-state-error').slideToggle();
					
					if(oResponse){
						var smartpointIds = content.util.listDeviceString(oResponse.processes[0].processItems, 'smartpointIds');
						var actionName= "getDemandResponseEventStatus";
						
						$.ajax({
					        url : "device/ajax.applyActions.action",
					        data: "actionName="+actionName+ smartpointIds+"&processId="+sId,
	//				        async : false,
					        success: function(data) {
					        	
					        	$('#messaging-summary .remove').click(function(e){
					        		e.preventDefault();
					        		$(this).parent().slideToggle();
					        	});
					        	
					        	if(data.operationSuccess){
					        		sensus.util.page.showMessage('messaging-summary', sensus.locale.get("commons.pages.getDemandResponseEventStatusSucess"), "confirm");
					        	} else {
					        		sensus.util.page.showMessage('messaging-summary', sensus.locale.get("commons.pages.getDemandResponseEventStatusSucess"), "error");
					        	}
					        }
					    });	
						
					}
					
				});
				
				/**
				 * Export Communication Summary
				 */
				oExport.click(function(e){
					
					e.preventDefault();
					sensus.util.page.startProgressBar();
					e.preventDefault();
					/** set process id to url **/
					$.address.parameter("process_id", sId);
					/** set status item to url **/
					//$.address.parameter("status_item", 10);
					/** generate csv file **/
					sensus.util.exportcsv.generateCSV("process/ajax.insertCSVProcess.action", "process/exportDemandResponseSummary.action");
				});
			}, 
			

			
			fnCreateDisplay : function() {
				var	oCommunicationSummary = $('.communication-summary', oDialogContent),
    			oExport = $('.csv',oCommunicationSummary);
			
				$.ajax({
			        url : "dicionario/fetchTelaById.action",
			        oRequest   : 'dicionarioRequest',
					text       : function(data, i) {
						return sensus.locale.get("groupdelete.warning.nosmartpointsongroup", data[i.groupName]);
					},
					fnRequest  : function(data, i) {
						aIds = 1;
						return new dicionarioRequest (aIds,null, null, null, null, null, null, null, null, null, null)
					},
			        success: function( resp ) {
			        	console.log(resp)
			        }
			    });
				
			},
			
			/**
			 * Fill Description of Process
			 * @param  - [Object] oResponse
			 * @return - [void]
			 */
			fnFillDescriptionProcess: function(oResponse){
				
				var oHighlight = $('.highlight', oDialogContent);
				
				/** Calculates time spent in Hours, Minutes and Seconds */
				var startDate = new Date(oResponse.processes[0].startTime).getTime();
				var endDate = new Date(oResponse.processes[0].endTime).getTime();

				var iDifference = (endDate-startDate),
					iSeconds = 0,
					iMinutes = 0,
					iHours = 0;
				
				if ((iDifference != 0) && (iDifference != null) && (iDifference > 0) && ((iDifference / 1000) > 59)){
					
					iDifference = iDifference / (60 * 1000);
					
					if (iDifference >= 60)
					{
						var diffHour = parseInt(iDifference / 60);
						iHours = diffHour;
						
						iMinutes = iDifference - (iHours*60)
					} else {
						iMinutes = iDifference;
					}
					
				} else {
					iSeconds = iDifference/1000;
				}
				
				var sDescription = oResponse.processes[0].description;

				sDescription = sDescription.substring(sDescription.indexOf(']') + 1, sDescription.length).replace(/\,/g, "").replace(/\./g, "")
				sDescription = sDescription.replace("]","").replace("[","");

				if (sDescription.contains("Network Address")) {
					
					var aDeviceIdType = [];
					
					sFlexNetId = sDescription.substring(sDescription.indexOf("Network Address") + 16, sDescription.length).split(" ")[0];
					
					sFlexNetId = sFlexNetId.replace(/\,/g, "").replace(/\./g, "");
					
					aDeviceIdType = sFlexNetId.split("|");
					
					if (aDeviceIdType[1]) {
						
						sFlexNet = aDeviceIdType[1];
					
					} else {
						
						sFlexNet = "";
					
					}
					sDescription = sDescription.replace(sFlexNetId, sFlexNet);
					sDescription = sDescription.replace("Network Address", sensus.locale.get("commons.pages.device"));
				}

				sDescription = sDescription.replace("[","");
				sDescription = sDescription.replace("]","");

				$('.action-type',oHighlight).empty().text(sensus.locale.get(oResponse.processes[0].actionView.actionType.actionCategory.name)+' - '+sensus.locale.get(oResponse.processes[0].actionView.actionType.description));
				$('.process-id',oHighlight).empty().text(oResponse.processes[0].id);
				$('.request-by',oHighlight).empty().text(sDescription);
				$('.time',oHighlight).empty().text(content.util.fnGetTime(iHours, iMinutes, iSeconds));
				$('.initiate-by',oHighlight).empty().text(sDescription);
				
				oHighlight.show();
			},

/* 			communicationSummary : {
				
				init : function(){
					sdialog = "communicationSummary";
					content.fnCommunicationSummary();
				}
			},	
			
			demandResponseSummary : {

				init : function(){
					sdialog = "demandResponseSummary";
					content.fnDemandResponseSummary();
				}
				
			},
			
			importHanDevices : {
				
				init : function(){
					sdialog = "importHanDevices";
					content.fnImportHanDevices();
				}
			}, */
			createDisplay : {
				
				init : function(){
					sdialog = "createDisplay";
					content.fnCreateDisplay();
				}
			}
		}	
	
	
	

	/**
	 * Ajax HTML Summary Main
	 * 
	 * @returns The ajax Object
	 */
	var fnLoadSummaryMain = function(){
		
		var oResponse;
		
		$.ajax({
			  url: "commons/pages/summary/summary_main.jsp",
			  async : false,
			  success: function(data) {
				  oResponse = data;
			  }
		});
		
		return oResponse;

	}
	
	/**
	 * Load content of Summary Main
	 */
	var sHtmlSummary = fnLoadSummaryMain();
	
	/**
	 * Displays the contents of Summary Main
	 */
	$(oDialogContent).empty().append(sHtmlSummary);
	
	content.tabs.init();
	
	$(".ui-tabs").delegate(".refresh", "click", function(e) {
		e.preventDefault();
		if(sdialog ==  "communicationSummary"){
			$('#informationTable tbody').find('tr').empty();
			$('#tableCommunicationSummary thead').empty();
			$('#tableCommunicationSummary tbody').empty();
			$('.fail').empty();
			content.fnCommunicationSummary();	
		}else if(sdialog ==  "demandResponseSummary"){
			$('#informationTable tbody').find('tr').empty();
			$('#tableDemandResponse thead').empty();
			$('#tableDemandResponse tbody').empty();
			$('.fail').empty();
			content.fnDemandResponseSummary();
		}else if(sdialog ==  "importHanDevices"){
			$('#informationTable tbody').find('tr').empty();
			$('#tableImportHanDevice thead').empty();
			$('#tableImportHanDevice tbody').empty();
			$('.fail').empty();
			content.fnImportHanDevices();
		}
	});
	

};