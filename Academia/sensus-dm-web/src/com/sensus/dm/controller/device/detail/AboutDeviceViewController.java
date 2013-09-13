package com.sensus.dm.controller.device.detail;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Note;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.response.Response;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.model.UiModulesPermissions;
import com.sensus.dm.common.device.bcf.INoteBCF;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.NoteRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.device.model.response.NoteResponse;
import com.sensus.dm.common.group.bcf.IGroupBCF;
import com.sensus.dm.common.process.bcf.IProcessBCF;
import com.sensus.dm.common.process.bcf.IProcessSummaryBCF;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.common.schedule.bcf.IScheduleBCF;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;
import com.sensus.dm.common.schedule.model.response.ScheduleResponse;
import com.sensus.dm.common.tag.bcf.ITagBCF;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.tag.model.request.InquiryTagRequest;
import com.sensus.dm.common.tag.model.response.TagResponse;
import com.sensus.dm.controller.base.BaseModuleController;
import com.sensus.dm.controller.util.DMConvertUtil;
import com.sensus.dm.elec.device.bcf.IElectricMeterBCF;
import com.sensus.dm.elec.device.model.HanDevice;

/**
 * The Class AboutDeviceViewController.
 */
@Controller
@RequestMapping("/device/tab/about")
public class AboutDeviceViewController extends BaseModuleController
{

	/** The Constant CONTENT_MODULES. */
	private static final String CONTENT_MODULES = "contentModules";

	/** The Constant DEMAND_RESPONSE_PROGRAM_PARTICIPATION. */
	private static final String DEMAND_RESPONSE_PROGRAM_PARTICIPATION = "demandResponseProgramParticipation";

	/** The Constant DEMAND_RESPONSE_SETUP. */
	private static final String DEMAND_RESPONSE_SETUP = "demandResponseSetup";

	/** The Constant DEMAND_RESPONSE_SETUP_LCM. */
	private static final String DEMAND_RESPONSE_SETUP_LCM = "demandResponseSetupLcm";

	/** The Constant DEVICE_TYPE. */
	private static final String DEVICE_TYPE = "deviceType";

	/** The Constant HAN_DEVICE. */
	private static final String HAN_DEVICE = "hanDevices";

	/** The Constant ID. */
	private static final String ID = "id";

	/** The Constant LCM_RELAY. */
	private static final String LCM_RELAY = "lcmRelay";

	/** The Constant LOAD. */
	private static final String LOAD = "";

	/** The Constant TYPE_ENUM. */
	private static final String TYPE_ENUM = "typeEnum";

	/** The Constant VIEW_ABOUT_DEVICE_MAIN. */
	private static final String VIEW_ABOUT_DEVICE_MAIN = "/device_detail/device_detail_about";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AboutDeviceViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "AboutDeviceViewController";

	/** The group bcf. */
	private IGroupBCF groupBCF;

	/** The tag bcf. */
	private ITagBCF tagBCF;

	/** The schedule bcf. */
	private IScheduleBCF scheduleBCF;

	/** The electric meter bcf. */
	private IElectricMeterBCF electricMeterBCF;

	/** The process summary bcf. */
	private IProcessSummaryBCF processSummaryBCF;

	/** The note bcf. */
	private INoteBCF noteBCF;

	/** The process bcf. */
	private IProcessBCF processBCF;

	/**
	 * Gets the process bcf.
	 * 
	 * @return the process bcf
	 */
	public IProcessBCF getProcessBCF()
	{
		return processBCF;
	}

	/**
	 * Sets the process bcf.
	 * 
	 * @param processBCF the new process bcf
	 */
	@Resource
	public void setProcessBCF(IProcessBCF processBCF)
	{
		this.processBCF = processBCF;
	}

	/**
	 * Gets the note bcf.
	 * 
	 * @return the note bcf
	 */
	public INoteBCF getNoteBCF()
	{
		return noteBCF;
	}

	/**
	 * Sets the note bcf.
	 * 
	 * @param noteBCF the new note bcf
	 */
	@Resource
	public void setNoteBCF(INoteBCF noteBCF)
	{
		this.noteBCF = noteBCF;
	}

	/**
	 * Gets the process summary bcf.
	 * 
	 * @return the processSummaryBCF
	 */
	public IProcessSummaryBCF getProcessSummaryBCF()
	{
		return processSummaryBCF;
	}

	/**
	 * Sets the process summary bcf.
	 * 
	 * @param processSummaryBCF the processSummaryBCF to set
	 */
	@Resource
	public void setProcessSummaryBCF(IProcessSummaryBCF processSummaryBCF)
	{
		this.processSummaryBCF = processSummaryBCF;
	}

	/**
	 * Gets the electric meter bcf.
	 * 
	 * @return the electric meter bcf
	 */
	public IElectricMeterBCF getElectricMeterBCF()
	{
		return electricMeterBCF;
	}

	/**
	 * Sets the electric meter bcf.
	 * 
	 * @param electricMeterBCF the new electric meter bcf
	 */
	@Resource
	public void setElectricMeterBCF(IElectricMeterBCF electricMeterBCF)
	{
		this.electricMeterBCF = electricMeterBCF;
	}

	/**
	 * Gets the schedule bcf.
	 * 
	 * @return the scheduleBCF
	 */
	public IScheduleBCF getScheduleBCF()
	{
		return scheduleBCF;
	}

	/**
	 * Sets the schedule bcf.
	 * 
	 * @param scheduleBCF the scheduleBCF to set
	 */
	@Resource
	public void setScheduleBCF(IScheduleBCF scheduleBCF)
	{
		this.scheduleBCF = scheduleBCF;
	}

	/**
	 * Gets the tag bcf.
	 * 
	 * @return the tag bcf
	 */
	public ITagBCF getTagBCF()
	{
		return tagBCF;
	}

	/**
	 * Sets the tag bcf.
	 * 
	 * @param tagBCF the new tag bcf
	 */
	@Resource
	public void setTagBCF(ITagBCF tagBCF)
	{
		this.tagBCF = tagBCF;
	}

	/**
	 * Gets the group bcf.
	 * 
	 * @return the group bcf
	 */
	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
	}

	/**
	 * Sets the group bcf.
	 * 
	 * @param groupBCF the new group bcf
	 */
	@Resource
	public void setGroupBCF(IGroupBCF groupBCF)
	{
		this.groupBCF = groupBCF;
	}

	/**
	 * Load.
	 * 
	 * @param flexNetId the flex net id
	 * @param deviceType the device type
	 * @param deviceSubType the device sub type
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = LOAD, method = RequestMethod.GET)
	public ModelAndView load(@RequestParam(value = ID, required = true) BigInteger flexNetId,
			@RequestParam(value = DEVICE_TYPE, required = true) DeviceTypeEnum deviceType,
			@RequestParam(value = TYPE_ENUM, required = false) String deviceSubType,
			HttpServletRequest request)
	{

		ModelAndView modelAndView = new ModelAndView(VIEW_ABOUT_DEVICE_MAIN);

		// Current Service type
		ServiceTypeEnum serviceType = ServiceTypeEnum.valueOf(getServiceType());

		// Detail Modules
		UiModulesPermissions detailUiModules = getDetailUiModules(serviceType, deviceType, deviceSubType);

		// Module: Contents
		modelAndView.addObject(CONTENT_MODULES, fillModulesResponse(detailUiModules.getContents()));

		// (Common Request) Device Request
		DeviceRequest deviceRequest = new DeviceRequest(new Device(new Radio(flexNetId), deviceType));

		// Add user context to request
		addUserContextToRequest(deviceRequest);

		// Response Map
		Map<String, Response> responseMap = new HashMap<String, Response>();

		try
		{
			// Contents array
			modelAndView.addObject("contents", getMapper().writeValueAsString(detailUiModules.getContents()));

			// Response Map
			for (String content : detailUiModules.getContents())
			{

				if ("group".equals(content))
				{
					responseMap.put("deviceGroups", getGroupBCF().fetchGroupsByDevice(deviceRequest));
				}
				else if ("tag".equals(content))
				{
					responseMap.put("deviceTags", getTagByDevice(flexNetId));
				}
				else if ("postNote".equals(content))
				{
					responseMap.put("deviceNotes", getNotes(flexNetId));
				}
				else if ("scheduledEvents".equals(content))
				{
					responseMap.put("deviceSchedules", getSchedulesByDevice(flexNetId));
				}
				else if (HAN_DEVICE.equals(content))
				{
					DeviceResponse deviceResponse = getElectricMeterBCF().fetchAllHanDevicesByMeter(deviceRequest);
					// Convert to MacAddress
					DMConvertUtil.convertMacAddress(deviceResponse);
					responseMap.put(HAN_DEVICE, deviceResponse);
				}
				else if (DEMAND_RESPONSE_PROGRAM_PARTICIPATION.equals(content))
				{
					responseMap.put(DEMAND_RESPONSE_PROGRAM_PARTICIPATION,
							getDemandResponseProgramParticipation(flexNetId));
				}
				else if (DEMAND_RESPONSE_SETUP.equals(content) || DEMAND_RESPONSE_SETUP_LCM.equals(content))
				{
					responseMap.put(DEMAND_RESPONSE_SETUP, getDemandResponseSetup(flexNetId));
				}
				else if (LCM_RELAY.equals(content))
				{
					responseMap.put(LCM_RELAY, getElectricMeterBCF().fetchLCMRelaysByDevice(deviceRequest));
				}
			}

			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(responseMap));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}

		return modelAndView;
	}

	/**
	 * Gets the notes.
	 * 
	 * @param flexNetId the flex net id
	 * @return the notes
	 */
	public NoteResponse getNotes(BigInteger flexNetId)
	{
		NoteRequest noteRequest = new NoteRequest(new Note(flexNetId));

		// Add user context to request
		addUserContextToRequest(noteRequest);

		return getNoteBCF().fetchDeviceNotes(noteRequest);
	}

	/**
	 * Gets the demand response setup.
	 * 
	 * @param flexNetId the flex net id
	 * @return the demand response setup
	 */
	public ProcessResponse getDemandResponseSetup(BigInteger flexNetId)
	{

		return getProcessSummaryBCF().fetchAllDemandResponseSetup(
				new ProcessRequest(new DMProcess(new ProcessItem(new Device(new Radio(flexNetId))))));
	}

	/**
	 * Gets the demand response program participation.
	 * 
	 * @param flexNetId the flex net id
	 * @return the demand response program participation
	 */
	public ProcessResponse getDemandResponseProgramParticipation(BigInteger flexNetId)
	{

		// Set FlexNetId into macAddress of the HanDevice object
		HanDevice hanDevice = new HanDevice();
		hanDevice.setMacAddress(flexNetId.toString());

		return getProcessSummaryBCF().fetchDemandResponseProgramParticipation(
				new ProcessRequest(new DMProcess(new ProcessItem(hanDevice))));
	}

	/**
	 * Gets the schedules by device.
	 * 
	 * @param flexNetId the flex net id
	 * @return the schedules by device
	 */
	public ScheduleResponse getSchedulesByDevice(BigInteger flexNetId)
	{

		ScheduleRequest scheduleRequest = new ScheduleRequest();
		DMAction action = new DMAction();
		DMSchedule schedule = new DMSchedule();

		action.addDevice(new Device(new Radio(flexNetId)));

		schedule.setDmAction(action);

		scheduleRequest.setSchedule(schedule);

		// ADD user context to request
		addUserContextToRequest(scheduleRequest);

		return getScheduleBCF().fetchScheduleByDevice(scheduleRequest);
	}

	/**
	 * Gets the tag by device.
	 * 
	 * @param flexNetId the flex net id
	 * @return the tag by device
	 */
	public TagResponse getTagByDevice(BigInteger flexNetId)
	{

		InquiryTagRequest inquiryTagRequest = new InquiryTagRequest(new Tag(new Device(new Radio(flexNetId))));

		// Add user context to request
		addUserContextToRequest(inquiryTagRequest);

		return getTagBCF().fetchTagsByDevice(inquiryTagRequest);
	}
}
