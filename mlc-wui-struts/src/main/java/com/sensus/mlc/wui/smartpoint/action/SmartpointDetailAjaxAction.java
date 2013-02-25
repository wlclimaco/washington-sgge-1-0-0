package com.sensus.mlc.wui.smartpoint.action;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.Response;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.ecomode.bcf.IEcoModeBCF;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.mlc.ecomode.model.response.InquiryEcoModeResponse;
import com.sensus.mlc.process.bcf.IProcessBCF;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.schedule.bcf.IScheduleBCF;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.schedule.model.response.ScheduleResponse;
import com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF;
import com.sensus.mlc.smartpoint.bcf.ISmartPointProcessorBCF;
import com.sensus.mlc.smartpoint.bcf.ISmartPointUpdaterBCF;
import com.sensus.mlc.smartpoint.model.SearchLight;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.response.CurrentAlarmWarningMessageResponse;
import com.sensus.mlc.smartpoint.model.response.LightResponse;
import com.sensus.mlc.smartpoint.model.response.StatusMessageResponse;
import com.sensus.mlc.tag.bcf.ITagBCF;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.TagRequest;
import com.sensus.mlc.tag.model.response.TagResponse;
import com.sensus.mlc.wui.base.action.ActionBase;
import com.sensus.mlc.wui.base.model.SearchJsonResult;
import com.sensus.mlc.wui.base.util.Constants;
import com.sensus.mlc.wui.base.util.ResultUtil;

/**
 * Struts action for SmartPointDetail-related Ajax callbacks. This action primarily supports the actions available from
 * the
 * Action menu list.
 * 
 * @author Alexandre Tiveron
 * 
 */

@SuppressWarnings("serial")
public class SmartpointDetailAjaxAction extends ActionBase
{

	/** CONSTANTS *. */

	/** The Constant EVENT. */
	public static final String EVENT = "EVENT";

	/** The Constant OFFSET. */
	public static final String OFFSET = "OFFSET";

	/** The Constant ERROR_UPDATING_LIGHT. */
	private static final String ERROR_UPDATING_LIGHT = "Error updating light property";

	/** The Constant OK. */
	private static final String OK = "OK";

	/** The update light status result. */
	private SearchJsonResult smartpointDetailResult;

	/** The id. */
	private Integer id;

	/** The Long Running Process created when the user force light status. */
	private Integer forceLightStatusLRPId;

	/** The is monitored. */
	private Boolean isMonitored = false;

	/** The schedule id. */
	private Integer scheduleId;

	/** The schedule type. */
	private String scheduleType;

	/** The light protected. */
	private Boolean lightProtected;

	/** The status exception id. */
	private Integer statusExceptionTypeEnumValue;

	/** The status message id. */
	private Integer statusMessageId;

	/** The tag id. */
	private Integer tagId;

	/** The tag name. */
	private String tagName;

	/** The latitude. */
	private String latitude;

	/** The longitude. */
	private String longitude;

	/** The pole id. */
	private String poleId;

	/** The is location. */
	private Boolean isLocation;

	/** The smart point accessor bcf. */
	private ISmartPointAccessorBCF smartPointAccessorBCF;

	/** The smart point processor bcf. */
	private ISmartPointProcessorBCF smartPointProcessorBCF;

	/** The smart point updater bcf. */
	private ISmartPointUpdaterBCF smartPointUpdaterBCF;

	/** The eco mode bcf. */
	private IEcoModeBCF ecoModeBCF;

	/** The schedule bcf. */
	private IScheduleBCF scheduleBCF;

	/** The tag bcf. */
	private ITagBCF tagBCF;

	/** The process bcf. */
	private IProcessBCF processBCF;

	/** The light request. */
	private LightRequest lightRequest;

	/** The schedule request. */
	private ScheduleRequest scheduleRequest;

	/** The process request. */
	private ProcessRequest processRequest;

	/** The light selection request. */
	private LightSelectionRequest lightSelectionRequest;

	/** The inquiry eco mode request. */
	private InquiryEcoModeRequest inquiryEcoModeRequest;

	/** The response. */
	private Response response;

	/** The status type id. */
	private Integer statusTypeId;

	/** The status message subtype. */
	private Integer statusMessageSubtype;

	/**
	 * Update status light.
	 * 
	 * @return the string
	 */
	public String forceLightStatus()
	{

		try
		{

			ProcessResponse processResp = getSmartPointProcessorBCF().initiateGetLightStatus(getLightRequest());
			setResponse(processResp);
		}
		catch (Exception e)
		{
			LOG.error("Error updating Light status", e);
			getSmartpointDetailResult().setResult(Constants.JSON_FAIL);
		}

		return SUCCESS;
	}

	/**
	 * Update base line.
	 * 
	 * @return the string
	 */
	public String updateBaseLine()
	{

		try
		{

			InquiryEcoModeResponse ecoModeResponse = getEcoModeBCF().upsertEcoMode(getInquiryEcoModeRequest());
			setResponse(ecoModeResponse);
		}
		catch (Exception e)
		{
			LOG.error("Error updating Light status", e);
			getSmartpointDetailResult().setResult(Constants.JSON_FAIL);
		}

		return SUCCESS;
	}

	/**
	 * Fetch eco mode.
	 * 
	 * @return the string
	 */
	public String fetchEcoMode()
	{

		try
		{

			InquiryEcoModeResponse ecoModeResponse =
					getEcoModeBCF().fetchLightConsumptionsByLightId(getInquiryEcoModeRequest());
			setResponse(ecoModeResponse);
		}
		catch (Exception e)
		{
			LOG.error("Error updating Light status", e);
			// getSmartpointDetailResult().setResult(Constants.JSON_FAIL);
		}

		return SUCCESS;
	}

	/**
	 * Fetch eco mode chart.
	 * 
	 * @return the string
	 */
	public String fetchEcoModeChart()
	{

		try
		{

			InquiryEcoModeResponse ecoModeResponse =
					getEcoModeBCF().fetchLightConsumptionsToChart(getInquiryEcoModeRequest());
			setResponse(ecoModeResponse);
		}
		catch (Exception e)
		{
			LOG.error("Error updating Light status", e);
			getSmartpointDetailResult().setResult(Constants.JSON_FAIL);
		}

		return SUCCESS;
	}

	/**
	 * Check updated light status.
	 * 
	 * @return the string
	 */
	public String checkUpdatedLightStatus()
	{

		try
		{

			LightResponse resp = getSmartPointAccessorBCF().fetchUpdateLightStatus(getProcessRequest());
			setResponse(resp);

		}
		catch (Exception e)
		{
			LOG.error("Error checking update Light status", e);
			getSmartpointDetailResult().setResult(Constants.JSON_FAIL);
		}

		return SUCCESS;
	}

	/**
	 * Removes the schedule.
	 * 
	 * @return the string
	 */
	public String removeSchedule()
	{

		try
		{

			ScheduleResponse resp = getScheduleBCF().initiateDeleteSmartpointFromSchedule(getScheduleRequest());
			setResponse(resp);

		}
		catch (Exception e)
		{
			LOG.error("Error remove schedule", e);
			getSmartpointDetailResult().setResult(Constants.JSON_FAIL);
		}

		return SUCCESS;
	}

	/**
	 * Apply schedule.
	 * 
	 * @return the string
	 */
	public String applySchedule()
	{

		try
		{

			ScheduleResponse resp = getScheduleBCF().initiateApplySmartPointToSchedule(getScheduleRequest());
			setResponse(resp);
		}
		catch (Exception e)
		{
			LOG.error("Error apply schedule", e);
			getSmartpointDetailResult().setResult(Constants.JSON_FAIL);
		}

		return SUCCESS;
	}

	/**
	 * Lock and unlock light.
	 * 
	 * @return the string
	 */
	public String updateLightProtected()
	{

		LightResponse lightResponse = getSmartPointUpdaterBCF().updateLightProtected(getLightRequest());
		setResponse(lightResponse);

		return SUCCESS;
	}

	/**
	 * Fetch status message.
	 * 
	 * @return the string
	 */
	public String fetchStatusMessage()
	{
		StatusMessageResponse response = getSmartPointAccessorBCF().fetchStatusMessageById(getLightRequest());
		setResponse(response);

		return SUCCESS;
	}

	/**
	 * Reset values.
	 * 
	 * @return the string
	 */
	public String resetValues()
	{

		LightResponse lightResponse = getSmartPointUpdaterBCF().resetMinMaxValue(getLightRequest());
		setResponse(lightResponse);

		return SUCCESS;
	}

	/**
	 * Clear status.
	 * 
	 * @return the string
	 */
	public String clearStatus()
	{

		ProcessResponse processResponse = getSmartPointProcessorBCF().initiateDeleteAlert(getLightRequest());
		setResponse(processResponse);

		return SUCCESS;
	}

	/**
	 * Insert smartpoint to tag.
	 * 
	 * @return the string
	 */
	public String insertSmartpointToTag()
	{
		setSmartpointDetailResult(new SearchJsonResult());

		TagRequest tagRequest = new TagRequest(getUserContext());

		Tag tag = new Tag();
		if (!ValidationUtil.isNullOrZero(getTagId()))
		{
			tag.setId(getTagId());
		}
		else if (!ValidationUtil.isNullOrEmpty(getTagName()))
		{
			tag.setName(getTagName());
		}
		tagRequest.setTag(tag);
		tagRequest.setTags(new ArrayList<Tag>());
		tagRequest.getTags().add(tag);

		List<Integer> lightIds = new ArrayList<Integer>();
		lightIds.add(getId());
		tagRequest.setSelectionPaginationIds(lightIds);
		tagRequest.setPaginationAllSelected(false);
		tagRequest.setSearchLight(new SearchLight());

		TagResponse tagResponse = getTagBCF().insertSmartPointToTag(tagRequest);

		if (tagResponse.isOperationSuccess())
		{
			getSmartpointDetailResult().setResult(OK);
		}
		else
		{
			getSmartpointDetailResult().setResult(Constants.JSON_FAIL);
		}

		ResultUtil.setMessages(getSmartpointDetailResult(), tagResponse);

		return SUCCESS;
	}

	/**
	 * Removes the smartpoint to tag.
	 * 
	 * @return the string
	 */
	public String removeSmartpointToTag()
	{
		setSmartpointDetailResult(new SearchJsonResult());

		TagRequest tagRequest = new TagRequest(getUserContext());

		Tag tag = new Tag();
		tag.setId(getTagId());
		tagRequest.setTag(tag);

		List<Integer> lightIds = new ArrayList<Integer>();
		lightIds.add(getId());
		tagRequest.setSelectionPaginationIds(lightIds);
		tagRequest.setPaginationAllSelected(false);
		tagRequest.setSearchLight(new SearchLight());
		tagRequest.setTags(new ArrayList<Tag>());
		tagRequest.getTags().add(tag);

		TagResponse tagResponse = getTagBCF().deleteSmartPointFromTag(tagRequest);

		if (tagResponse.isOperationSuccess())
		{
			getSmartpointDetailResult().setResult(OK);
		}
		else
		{
			getSmartpointDetailResult().setResult(Constants.JSON_FAIL);
		}

		ResultUtil.setMessages(getSmartpointDetailResult(), tagResponse);

		return SUCCESS;
	}

	/**
	 * Update light property.
	 * 
	 * @return the string
	 */
	public String updateLightProperty()
	{

		try
		{

			ProcessResponse processResp = getSmartPointProcessorBCF().initiateUpsertLightProperty(getLightRequest());
			setResponse(processResp);

		}
		catch (Exception e)
		{
			LOG.error(ERROR_UPDATING_LIGHT, e);
		}

		return SUCCESS;

	}

	/**
	 * Current alarm status.
	 * 
	 * @return the string
	 */
	public String currentAlarmStatus()
	{
		try
		{
			CurrentAlarmWarningMessageResponse currentMsgResponse =
					getSmartPointAccessorBCF().fetchCurrentAlarmStatusMessagesByLight(getLightRequest());
			setResponse(currentMsgResponse);
		}
		catch (Exception e)
		{
			LOG.error("Error updating light property", e);
		}

		return SUCCESS;
	}

	/**
	 * Insert process.
	 * 
	 * @return the string
	 */
	public String insertProcessEcoMode()
	{

		try
		{

			ProcessResponse lightResp = getEcoModeBCF().insertCSVProcess(getLightSelectionRequest());
			setResponse(lightResp);

		}
		catch (Exception e)
		{

		}

		return SUCCESS;
	}

	/**
	 * Generate file csv.
	 * 
	 * @return the string
	 */
	public String generateFileCSVEcoMode()
	{

		try
		{

			InquiryEcoModeResponse lightResp = getEcoModeBCF().generateFileCSV(getInquiryEcoModeRequest());
			setResponse(lightResp);
		}
		catch (Exception e)
		{
			getSmartpointDetailResult().setResult(Constants.JSON_FAIL);
		}

		return SUCCESS;
	}

	/**
	 * Sets the smartpoint detail result.
	 * 
	 * @param smartpointDetailResult the smartpointDetailResult to set
	 */
	public void setSmartpointDetailResult(SearchJsonResult smartpointDetailResult)
	{
		this.smartpointDetailResult = smartpointDetailResult;
	}

	/**
	 * Gets the smartpoint detail result.
	 * 
	 * @return the smartpointDetailResult
	 */
	public SearchJsonResult getSmartpointDetailResult()
	{
		return smartpointDetailResult;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Gets the smart point accessor bcf.
	 * 
	 * @return the smart point accessor bcf
	 */
	public ISmartPointAccessorBCF getSmartPointAccessorBCF()
	{
		return smartPointAccessorBCF;
	}

	/**
	 * Sets the smart point accessor bcf.
	 * 
	 * @param smartPointAccessorBCF the new smart point accessor bcf
	 */
	public void setSmartPointAccessorBCF(ISmartPointAccessorBCF smartPointAccessorBCF)
	{
		this.smartPointAccessorBCF = smartPointAccessorBCF;
	}

	/**
	 * Gets the smart point processor bcf.
	 * 
	 * @return the smart point processor bcf
	 */
	public ISmartPointProcessorBCF getSmartPointProcessorBCF()
	{
		return smartPointProcessorBCF;
	}

	/**
	 * Sets the smart point processor bcf.
	 * 
	 * @param smartPointProcessorBCF the new smart point processor bcf
	 */
	public void setSmartPointProcessorBCF(ISmartPointProcessorBCF smartPointProcessorBCF)
	{
		this.smartPointProcessorBCF = smartPointProcessorBCF;
	}

	/**
	 * Gets the smart point updater bcf.
	 * 
	 * @return the smart point updater bcf
	 */
	public ISmartPointUpdaterBCF getSmartPointUpdaterBCF()
	{
		return smartPointUpdaterBCF;
	}

	/**
	 * Sets the smart point updater bcf.
	 * 
	 * @param smartPointUpdaterBCF the new smart point updater bcf
	 */
	public void setSmartPointUpdaterBCF(ISmartPointUpdaterBCF smartPointUpdaterBCF)
	{
		this.smartPointUpdaterBCF = smartPointUpdaterBCF;
	}

	/**
	 * Sets the schedule bcf.
	 * 
	 * @param scheduleBCF the scheduleBCF to set
	 */
	public void setScheduleBCF(IScheduleBCF scheduleBCF)
	{
		this.scheduleBCF = scheduleBCF;
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
	 * Sets the tag bcf.
	 * 
	 * @param tagBCF the tagBCF to set
	 */
	public void setTagBCF(ITagBCF tagBCF)
	{
		this.tagBCF = tagBCF;
	}

	/**
	 * Gets the tag bcf.
	 * 
	 * @return the tagBCF
	 */
	public ITagBCF getTagBCF()
	{
		return tagBCF;
	}

	/**
	 * @return the ecoModeBCF
	 */
	public IEcoModeBCF getEcoModeBCF()
	{
		return ecoModeBCF;
	}

	/**
	 * @param ecoModeBCF the ecoModeBCF to set
	 */
	public void setEcoModeBCF(IEcoModeBCF ecoModeBCF)
	{
		this.ecoModeBCF = ecoModeBCF;
	}

	/**
	 * Sets the process bcf.
	 * 
	 * @param processBCF the processBCF to set
	 */
	public void setProcessBCF(IProcessBCF processBCF)
	{
		this.processBCF = processBCF;
	}

	/**
	 * Gets the process bcf.
	 * 
	 * @return the processBCF
	 */
	public IProcessBCF getProcessBCF()
	{
		return processBCF;
	}

	/**
	 * Sets the checks if is monitored.
	 * 
	 * @param isMonitored the isMonitored to set
	 */
	public void setIsMonitored(Boolean isMonitored)
	{
		this.isMonitored = isMonitored;
	}

	/**
	 * Gets the checks if is monitored.
	 * 
	 * @return the isMonitored
	 */
	public Boolean getIsMonitored()
	{
		return isMonitored;
	}

	/**
	 * Sets the force light status lrp id.
	 * 
	 * @param forceLightStatusLRPId the forceLightStatusLRPId to set
	 */
	public void setForceLightStatusLRPId(Integer forceLightStatusLRPId)
	{
		this.forceLightStatusLRPId = forceLightStatusLRPId;
	}

	/**
	 * Gets the force light status lrp id.
	 * 
	 * @return the forceLightStatusLRPId
	 */
	public Integer getForceLightStatusLRPId()
	{
		return forceLightStatusLRPId;
	}

	/**
	 * Sets the schedule type.
	 * 
	 * @param scheduleType the scheduleType to set
	 */
	public void setScheduleType(String scheduleType)
	{
		this.scheduleType = scheduleType;
	}

	/**
	 * Gets the schedule type.
	 * 
	 * @return the scheduleType
	 */
	public String getScheduleType()
	{
		return scheduleType;
	}

	/**
	 * Sets the schedule id.
	 * 
	 * @param scheduleId the scheduleId to set
	 */
	public void setScheduleId(Integer scheduleId)
	{
		this.scheduleId = scheduleId;
	}

	/**
	 * Gets the schedule id.
	 * 
	 * @return the scheduleId
	 */
	public Integer getScheduleId()
	{
		return scheduleId;
	}

	/**
	 * Sets the light protected.
	 * 
	 * @param lightProtected the lightProtected to set
	 */
	public void setLightProtected(Boolean lightProtected)
	{
		this.lightProtected = lightProtected;
	}

	/**
	 * Gets the light protected.
	 * 
	 * @return the lightProtected
	 */
	public Boolean getLightProtected()
	{
		return lightProtected;
	}

	/**
	 * Sets the tag id.
	 * 
	 * @param tagId the tagId to set
	 */
	public void setTagId(Integer tagId)
	{
		this.tagId = tagId;
	}

	/**
	 * Gets the tag id.
	 * 
	 * @return the tagId
	 */
	public Integer getTagId()
	{
		return tagId;
	}

	/**
	 * Sets the status exception type enum value.
	 * 
	 * @param statusExceptionTypeEnumValue the statusExceptionTypeEnumValue to set
	 */
	public void setStatusExceptionTypeEnumValue(Integer statusExceptionTypeEnumValue)
	{
		this.statusExceptionTypeEnumValue = statusExceptionTypeEnumValue;
	}

	/**
	 * Gets the status exception type enum value.
	 * 
	 * @return the statusExceptionTypeEnumValue
	 */
	public Integer getStatusExceptionTypeEnumValue()
	{
		return statusExceptionTypeEnumValue;
	}

	/**
	 * Gets the status type id.
	 * 
	 * @return the statusTypeId
	 */
	public Integer getStatusTypeId()
	{
		return statusTypeId;
	}

	/**
	 * Sets the status type id.
	 * 
	 * @param statusTypeId the statusTypeId to set
	 */
	public void setStatusTypeId(Integer statusTypeId)
	{
		this.statusTypeId = statusTypeId;
	}

	/**
	 * Gets the status message subtype.
	 * 
	 * @return the statusMessageSubtype
	 */
	public Integer getStatusMessageSubtype()
	{
		return statusMessageSubtype;
	}

	/**
	 * Sets the status message subtype.
	 * 
	 * @param statusMessageSubtype the statusMessageSubtype to set
	 */
	public void setStatusMessageSubtype(Integer statusMessageSubtype)
	{
		this.statusMessageSubtype = statusMessageSubtype;
	}

	/**
	 * Gets the status message id.
	 * 
	 * @return the statusMessageId
	 */
	public Integer getStatusMessageId()
	{
		return statusMessageId;
	}

	/**
	 * Sets the status message id.
	 * 
	 * @param statusMessageId the statusMessageId to set
	 */
	public void setStatusMessageId(Integer statusMessageId)
	{
		this.statusMessageId = statusMessageId;
	}

	/**
	 * Sets the tag name.
	 * 
	 * @param tagName the new tag name
	 */
	public void setTagName(String tagName)
	{
		this.tagName = tagName;
	}

	/**
	 * Gets the tag name.
	 * 
	 * @return the tag name
	 */
	public String getTagName()
	{
		return tagName;
	}

	/**
	 * Sets the latitude.
	 * 
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}

	/**
	 * Gets the latitude.
	 * 
	 * @return the latitude
	 */
	public String getLatitude()
	{
		return latitude;
	}

	/**
	 * Sets the longitude.
	 * 
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}

	/**
	 * Gets the longitude.
	 * 
	 * @return the longitude
	 */
	public String getLongitude()
	{
		return longitude;
	}

	/**
	 * Sets the pole id.
	 * 
	 * @param poleId the poleId to set
	 */
	public void setPoleId(String poleId)
	{
		this.poleId = poleId;
	}

	/**
	 * Gets the pole id.
	 * 
	 * @return the poleId
	 */
	public String getPoleId()
	{
		return poleId;
	}

	/**
	 * Sets the checks if is location.
	 * 
	 * @param isLocation the isLocation to set
	 */
	public void setIsLocation(Boolean isLocation)
	{
		this.isLocation = isLocation;
	}

	/**
	 * Gets the checks if is location.
	 * 
	 * @return the isLocation
	 */
	public Boolean getIsLocation()
	{
		return isLocation;
	}

	/**
	 * Gets the light request.
	 * 
	 * @return the lightRequest
	 */
	public LightRequest getLightRequest()
	{
		if (!ValidationUtil.isNull(lightRequest))
		{
			lightRequest.setUserContext(getUserContext());
		}
		return lightRequest;
	}

	/**
	 * Sets the light request.
	 * 
	 * @param lightRequest the lightRequest to set
	 */
	public void setLightRequest(LightRequest lightRequest)
	{
		this.lightRequest = lightRequest;
	}

	/**
	 * Gets the response.
	 * 
	 * @return the response
	 */
	public Response getResponse()
	{
		return response;
	}

	/**
	 * Sets the response.
	 * 
	 * @param response the response to set
	 */
	public void setResponse(Response response)
	{
		this.response = response;
	}

	/**
	 * @return the scheduleRequest
	 */
	public ScheduleRequest getScheduleRequest()
	{
		scheduleRequest.setUserContext(getUserContext());
		return scheduleRequest;
	}

	/**
	 * @param scheduleRequest the scheduleRequest to set
	 */
	public void setScheduleRequest(ScheduleRequest scheduleRequest)
	{
		this.scheduleRequest = scheduleRequest;
	}

	/**
	 * @return the processRequest
	 */
	public ProcessRequest getProcessRequest()
	{
		processRequest.setUserContext(getUserContext());
		return processRequest;
	}

	/**
	 * @param processRequest the processRequest to set
	 */
	public void setProcessRequest(ProcessRequest processRequest)
	{
		this.processRequest = processRequest;
	}

	/**
	 * @return the inquiryEcoModeRequest
	 */
	public InquiryEcoModeRequest getInquiryEcoModeRequest()
	{
		inquiryEcoModeRequest.setUserContext(getUserContext());
		return inquiryEcoModeRequest;
	}

	/**
	 * @param inquiryEcoModeRequest the inquiryEcoModeRequest to set
	 */
	public void setInquiryEcoModeRequest(InquiryEcoModeRequest inquiryEcoModeRequest)
	{
		this.inquiryEcoModeRequest = inquiryEcoModeRequest;
	}

	/**
	 * @return the lightSelectionRequest
	 */
	public LightSelectionRequest getLightSelectionRequest()
	{
		lightSelectionRequest.setUserContext(getUserContext());
		return lightSelectionRequest;
	}

	/**
	 * @param lightSelectionRequest the lightSelectionRequest to set
	 */
	public void setLightSelectionRequest(LightSelectionRequest lightSelectionRequest)
	{
		this.lightSelectionRequest = lightSelectionRequest;
	}

}
