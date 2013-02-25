/**
 * @namespace sensus.pages.schedule
 * @description The creat-main namespace for the Schedule Page.
 */

/**
 * @fileoverview Defines the core functionality of the Create Schedule page.
 * @author Anke Doerfel-Parker
 */

/**
 * The main schedule namespace containing schedule-related functions.
 */
sensus.pages.schedule = {

	event : {

		/** The is Updated Monitored */
		isUpdateMonitored: false,

		/** The changed */
		changed: false,

		/**
		 * Add a new event section to the form on the screen. This will track the
		 * counts internally and request the html to be added from a Struts Action
		 * via Ajax.
		 */
		addEvent : function() {
			/*
			 * Increase the event count since we're starting at 0 which is not
			 * user-friendly
			 */
			sensus.pages.schedule.eventCount++;
			sensus.pages.schedule.eventCountTitle++;
			/*
			 * Obtain the event html with the new count inserted in all the correct
			 * places
			 */
			$.ajax( {
				url     : "schedule/include.event.action?count=" + sensus.pages.schedule.eventCount,
				cache   : false,
				async   : false,
				// Success handler for Ajax load of event template.
				success : function(html) {

					// Add the html to the main page
					$('#event-add').append(html);
					$('#event1 .remove').removeClass('remove');

					// Add the actual datas if contain
					$(".list-events").each(function(i) {
						i += 1;
						$(this).removeClass("list-events").addClass("list-events" + i);
					});

					var eventTime = $(".list-events" + sensus.pages.schedule.eventCount).find(".event-time").text();

					$("#event" + sensus.pages.schedule.eventCount).find("#time" + sensus.pages.schedule.eventCount).val(eventTime);

					$(".list-events" + sensus.pages.schedule.eventCount + " .event-day").each(function() {
						var currentDay = $(this).text();
						$("#event" + sensus.pages.schedule.eventCount).find('input[value="'+ currentDay +'"]').attr('checked', true);
					});

					//Sliders
					var $dimLayer = $("#event" + sensus.pages.schedule.eventCount).find('select#dimmer');
					if ($dimLayer.length){

						var dimm_slider = $dimLayer.attr("id", "dimmer" + sensus.pages.schedule.eventCount).selectToUISlider({
							labels: 5,
							labelSrc: 'text'
						});
					}

					var eventStatus = $(".list-events" + sensus.pages.schedule.eventCount).find(".event-status").text() == "" ? eventStatus = null : eventStatus = $(".list-events" + sensus.pages.schedule.eventCount).find(".event-status").text();
					if (!(eventStatus == null)){$("#dimmer" + sensus.pages.schedule.eventCount, "#event" + sensus.pages.schedule.eventCount).find('option[value="'+ eventStatus +'"]').prop("selected", true).trigger("click");}

					/*End actual datas*/
					/* Add callbacks to new buttons */
					sensus.pages.schedule.addCallbacks('#event' + sensus.pages.schedule.eventCount);

					//		sensus.locale.get("schedulecreate.form.event.when.descr", $.format($.parseDate("8:45 pm", "h:mm tt"), "t")));
					/*
					 * Stop progressbar/show form (only needed at first page load
					 * but will not cause problems later).
					 */
					sensus.util.page.showContent("#yui-main");
					sensus.util.page.stopProgressBar();

					if ($(".list-events" + (sensus.pages.schedule.eventCount + 1)).length > 0){

						sensus.pages.schedule.event.addEvent();

					}

					//Slide the content inner at DIM and BLINK
					$(".control span:contains(" + sensus.locale.get('schedulecreate.page.dim') + ")").mouseenter(function(){
						$(this).find('.control_schedule').fadeIn('fast');
					}).click(function(){
						$(this).parent().parent().find('.label-desc:eq(1)').text('');
					}).mouseleave(function(){
						$(this).find('.control_schedule').fadeOut('fast');
					});

					$(".control span:contains(" + sensus.locale.get('schedulecreate.page.blink') + ")").mouseenter(function(){
						$(this).find('.control_schedule').fadeIn('fast');
					}).click(function(){
						$(this).parent().parent().find('.label-desc:eq(0)').text('');
					}).mouseleave(function(){
						$(this).find('.control_schedule').fadeOut('fast');
					});
					
					$(".control span:contains(" + sensus.locale.get('schedulecreate.page.on') + "), .control span:contains(" + sensus.locale.get('schedulecreate.page.off') + ")").click(function(){
					   $(this).parent().parent().find('.label-desc').text('');
					});

					//Show the Value of DIM and BLINK options
					$("#dim_options"  + sensus.pages.schedule.eventCount + " a, #blink_options" + sensus.pages.schedule.eventCount + " a").mouseenter(function() {
						$(this).addClass('ui-state-hover');
					}).click(function(e) {

						e.preventDefault();

						$('#radio_light' + sensus.pages.schedule.eventCount + ' input').prop('checked', false);

						var sActiveControl = $(this).closest('label');
						var sValue = $(this);

						sActiveControl.prev('input').prop('checked', 'checked');

						$('#radio_light' + sensus.pages.schedule.eventCount + ' .label-desc').text('');
						$('#radio_light' + sensus.pages.schedule.eventCount + ' label').removeClass('ui-state-active');

						sActiveControl.addClass('ui-state-active');
						sActiveControl.find('.label-desc').html('<strong>' + ' ' + sValue.text() + '</strong>');

					}).mouseleave(function() {
						$(this).removeClass('ui-state-hover');
					});

					//Set Calendrical Time
					$('#time' + sensus.pages.schedule.eventCount + '').calendricalTime();
					
					if ($('.event-container').length > 3) {
					
						$('#event-add-link').hide();
					
					}

				}

			});

		},

		createSchedule : function() {

		sensus.pages.schedule.event.ajaxCall(sensus.settings.createUrl);
		sensus.util.page.stopProgressBar();

		},

		/**
		* Ajax call tha will submit the request ajax
		*/
		ajaxCall : function (urlAdress) {
		
			var scheduleName = $("#schedule-name").val();
			var scheduleType = $("#schedule-type").val();
			var scheduleId = $("#schedule-id").val() == "" ? scheduleId = null : $("#schedule-id").val();
			var sunsetIntensity = $("#sunset-intensity").find(":selected").val();
			var sDescription = $('#schedule-description').val();
			var message = "";
			var aEvents = sensus.pages.schedule.getEvents();
			var iCount = 1;
			var bValidEvent = true;

			if (!sunsetIntensity) {

				sunsetIntensity = 0;

			}
			
			for (u in aEvents) {
				if (isNaN(aEvents[u].intensity) || aEvents[u].lightBlinkEnum == '') {
					$("#radio3" + iCount + "").validationEngine('showPrompt', sensus.locale.get("validation.multipleFields"), 'red', '', true);
					bValidEvent = false;
				}
				iCount++;
			} 

			if (bValidEvent) {
			
				sensus.util.page.startProgressBar(null,true);

				var oEventSchedule = new eventSchedule(scheduleId, sDescription, scheduleName, 'EVENT', aEvents);
				var eventScheduleRequest = new scheduleRequest(null,null,null,null,null,null,oEventSchedule,'eventSchedule');

				$.ajax( {
					url : urlAdress,
					type : 'POST',
					contentType : 'application/json',
					data : $.toJSON({'scheduleRequest' : eventScheduleRequest}),
					
					/**
					 * The success handler. This will be invoked if the HTTP request
					 * returned correctly. The status of the reuested operation
					 * needs to be determined from the response object.
					 */
					success : function(data) {

						/* If successful, load the main schedule page */
						if (data.operationSuccess == true) {
							
							if (scheduleId == null) {
								message = sensus.locale.get("action.addschedule.success", [ scheduleName, sensus.settings.smartpointUrl ]);
							} else {
								message = sensus.locale.get("action.updateschedule.success", [ scheduleName, sensus.settings.smartpointUrl ]);
							}

							sensus.commons.lib.ajax.do_load($.extend({}, sensus.commons.lib.ajax.param, {
								sUrl : 'schedule/ajax.list.action',
								message : {
									bMessage : true,
									sMessage : message
								}
							}));

						} else {
							/* Show error message */
							if (!data.messageList) {

								if (scheduleId == null) {
									message = sensus.locale.get("action.addschedule.error");
								} else {
									message = sensus.locale.get("action.updateschedule.error");
								}

								sensus.util.page.showMessage("messaging-main", data.messageList[0].text, "error");

							} else {

								sensus.util.page.showMessage("messaging-main", ' ' + data.messageList[0].text, "error");

							}

							sensus.util.page.stopProgressBar(null,true);

						}
					}
				/*
				 * At this point there is no error handler. We should consider
				 * entering one. It would take effect if the provided url is not
				 * available or the request returns any other HTTP error.
				 */
				});
			}
		}

	},

	validateWeekday : function()  {
		if ($(".event-day-custom").length > 0) {
			var evtVal = $(".event-day-custom").attr("id");
			var evtCount = evtVal.substring(evtVal.length - 1);
			if (evtVal.length == 14) {
				evtCount = evtVal.substring(evtVal.length - 2);
			} else if (evtVal.length == 15) {
				evtCount = evtVal.substring(evtVal.length - 3);
			}
			var checked = 0;
			var weekdays = [ "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" ];
			for ( var i in weekdays) {
				if (weekdays.hasOwnProperty(i)) {
					if ($("[name=day" + evtCount + weekdays[i] + "]").is(":checked") && checked == 0) {
						checked++;
						return true;
					}
				}
			}

			return false;
		} else {
			return true;
		}
	},

	validateSunriseSunset : function(element) {

		if ($(element).length) {

			var value = $(element).val();

			if (value % 5 == 0){
				return true;
			} else {
				$(element).validationEngine('showPrompt', sensus.locale.get("schedulecreate.form.timeSunsetSunrise"), 'red', '', true);
				return false;
			}

		} else {
			return true;
		}
	},

	validateSunriseSunsetSwitch : function(element) {

		var name = $(element).attr('name');
		var timeField = $(element).closest('li').find('input[name='+ name +'Time]').val();

		if (timeField == "") {
			return true;
		} else {

			if ($(element).parent().find("[aria-pressed='true']").length > 0) {
				return true;
			}else {
				return false;
			}

		}

		$(element).validationEngine('showPrompt', sensus.locale.get("schedulecreate.form.lightsunset.required"), 'red', '', true);

		return false;
	},

	/**
	 * Contains query templates for HTTP requests.
	 */
	query : {
		/**
		 * Template for GET request returning to main schedule page. The
		 * schedule parameter will cause a message to be displayed.
		 */
		schedule : "{0}?schedule={1}&scheduleId={2}",
		/**
		 * Json template for create schedule Ajax call. While most of the
		 * parameters will contain numeric or string values, the events property
		 * will be set to another Json string (an array of event objects) that
		 * is constructed separately.
		 */
		create : "{'name':'{0}','description':'{1}','sunriseTime':{2},'sunsetTime':{3},'sunrise':{4},'sunset':{5},'events':{6},'scheduleType':'{7}','id':{8},'isMonitored':{9},'sunsetIntensity':{10}}",
		/**
		 * Json template for a single schedule event. If multiple days are
		 * filled in, the provided value must already contain the commas and
		 * single quotes between the values.
		 */
		event : "{'time':'{0}','state':'{1}','days':['{2}'],'eventNumber':'{3}', 'blink':'{4}'}"

	},

	updateSchedule : function() {

		if (sensus.pages.schedule.event.changed == true) {

			sensus.pages.longrunningprocess.actionUrlAdress = "updateSchedule";
			sensus.pages.longrunningprocess.callPromptMonitorAction(false);

		} else {
			sensus.pages.schedule.event.ajaxCall(sensus.settings.updateScheduleUrl);
		}

	},

	/**
	 * Counter for the number of events displayed on the page. This counter will
	 * always count up, even if events get subsequently deleted again. This is a
	 * simple and robust solution; reusing numbers of deleted events would
	 * require a lot more complicated logic.
	 */
	eventCount : 0,
	/**
	 * Counter for the event number displayed in the event title. Currently,
	 * this will be the same number as the eventCount value.
	 */
	eventCountTitle : 0,

	/**
	 * Adds button events to the newly rendered event template.
	 *
	 * @param eles
	 *            the main event container
	 */
	addCallbacks : function(eles) {
		$(eles).buttonset();
		$(eles + " .remove").click(function() {
			sensus.pages.schedule.removeEvent($(eles + " .remove"));
		});
	},
	/**
	 * Remove an event from the form.
	 *
	 * @return always false to stop event propagation
	 */
	removeEvent : function(obj) {
	
		$('.formError').remove();
		
		$(obj).parent().fadeOut("100", function() {
			$(this).remove();
		});

		if ($('.event-container').length <= 4) {
			
			$('#event-add-link').show();
		}
		
		return false;
	},
	/**
	 * Check whether an event exists for this event count.
	 */
	hasEvent : function(index) {
		return $("#event" + index).length > 0;
	},

	/**
	 * Check whether a data exist inside an event.
	 */

	hasEventData : function (d) {
		return $(d).length > 0;
	},

	/**
	 * Get the data for one event as object.
	 *
	 * @param index
	 *            the count of the event to retrieve
	 * @return the event object or null if it doesn't exist or all fields are
	 *         emtpy
	 */
	getEvent : function(index) {

		var event = {
			state 		   : null,
			time 		   : null,
			days 		   : [],
			eventNumber    : null,
			lightBlinkEnum : 'NONE'
		};

		var val;
		var days = $("#event-repeat" + index + " :input:checked");
		var state = 100;
		var sTypeControl = $('#radio_light' + index + ' label.ui-state-active').find('.label-desc');
		var time = $("#time" + index).val();
		var blink = $('#radio3'+index).is(':checked') ? 'FAST' : 'NONE';
		var dDateWhen = new Date();
		var aHour = time.split(":");
		var sHour = time.toLowerCase().contains("am") ? (aHour[0]=='12') ? '00' : aHour[0] : (aHour[0]=='12') ? aHour[0] : parseInt(aHour[0])+12;
		var sMin = aHour[1].substring(0,2);//split(" ")[0];

		dDateWhen.setHours(sHour);
		dDateWhen.setMinutes(sMin);
		dDateWhen.setSeconds(0);

		// Make convertion of the date to UTC
		var time = $.date.fnTimeToUTC(dDateWhen);

		//Pick the type of 'Control'
		if (sTypeControl.length) {

			if ($("#radio_light" + index + " label.ui-state-active span:contains(" + sensus.locale.get('schedulecreate.page.dim') + ")").length) {
				state = parseInt(sTypeControl.text().trim().split('%')[0]);
			} else {
				blink = sTypeControl.text().trim().toUpperCase();
			}

		} else {

			if ($('#radio_light' + index + ' label.ui-state-active').text() == sensus.locale.get('smartpoint.actions.controlLights.off')) {
				state = 0;
			}
		}

		event.time	 		  = time;
		event.intensity       = state;
		event.lightBlinkEnum  = blink;

		for ( var i = 0; i < days.length; i++) {

			event.days.push($(days[i]).val());

		}

		return event;

	},
	/**
	 * Get the data for all events on the form. If any given event was deleted
	 * or not filled out at all, it will be skipped.
	 *
	 * @return an array of event objects for each filled in event on the form
	 */
	getEvents : function() {
		var events = [];
		var obj;
		for ( var i = 1; i <= this.eventCount; i++) {
			if (sensus.pages.schedule.hasEvent(i)) {
				obj = sensus.pages.schedule.getEvent(i);
				if (obj != null) {
					events.push(obj);
				}
			}
		}
		return events;
	},
	/**
	 * Convert the event data array into Json.
	 *
	 * @param events
	 *            the event array to convert
	 * @return the Json string representing the event array
	 */
	getEventJSON : function(events) {

		var json = [];
		for ( var i in events) {
			if (events.hasOwnProperty(i) ) {
				obj = events[i];

				json.push({'time' : events[i].time, 'intensity' : events[i].state, 'days' : events[i].days.join("','")});
				i++;
			}
		}

		return "[" + json.join(",") + "]";
	}
};
