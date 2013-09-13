package com.sensus.dm.elec.action.model.request;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.UserContext;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.model.request.InquiryPaginationRequest;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * The Class InquiryActionRequest.
 * 
 * @author QAT Brazil.
 */
public class InquiryActionRequest extends InquiryPaginationRequest
{

	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The device search. */
	private DeviceSearch deviceSearch;

	/** The actions. */
	private List<DMAction> actions;

	/** The han devices file. */
	private File hanDevicesFile;

	/** The upload han devices. */
	private String uploadHanDevices;

	/** The schedule name. Used in the Event Description on Event History Tab and Today Tab */
	private String scheduleName;

	/**
	 * Instantiates a new inquiry action request.
	 */
	public InquiryActionRequest()
	{
	}

	/**
	 * Instantiates a new inquiry action request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryActionRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new inquiry action request.
	 * 
	 * @param dmAction the action
	 * @param userContext the user context
	 */
	public InquiryActionRequest(DMAction dmAction, UserContext userContext)
	{
		setUserContext(userContext);
		addAction(dmAction);
	}

	/**
	 * Instantiates a new inquiry action request.
	 * 
	 * @param dmAction the action
	 * @param userContext the user context
	 * @param serviceTypeEnumParam the ServiceTypeEnum
	 */
	public InquiryActionRequest(DMAction dmAction, UserContext userContext, ServiceTypeEnum serviceTypeEnumParam)
	{
		setUserContext(userContext);
		addAction(dmAction);
		setServiceTypeEnum(serviceTypeEnumParam);
	}

	/**
	 * Instantiates a new inquiry action request.
	 * 
	 * @param dmAction the action
	 * @param userContext the user context
	 * @param serviceTypeEnumParam the service type enum param
	 * @param scheduleNameParam the schedule name
	 */
	public InquiryActionRequest(DMAction dmAction, UserContext userContext, ServiceTypeEnum serviceTypeEnumParam,
			String scheduleNameParam)
	{
		this(dmAction, userContext, serviceTypeEnumParam);
		setScheduleName(scheduleNameParam);
	}

	/**
	 * Instantiates a new inquiry action request.
	 * 
	 * @param dmAction the action
	 * @param userContext the user context
	 * @param serviceTypeEnumParam the service type enum param
	 * @param scheduleNameParam the schedule name
	 * @param tenantParam the DMTenant
	 */
	public InquiryActionRequest(DMAction dmAction, UserContext userContext, ServiceTypeEnum serviceTypeEnumParam,
			String scheduleNameParam, DMTenant tenantParam)
	{
		this(dmAction, userContext, serviceTypeEnumParam);
		setScheduleName(scheduleNameParam);
		setTenant(tenantParam);
	}

	/**
	 * Instantiates a new inquiry action request.
	 * 
	 * @param dmAction the dm action
	 * @param userContext the user context
	 * @param serviceTypeEnumParam the service type enum param
	 * @param tenantParam the tenant param
	 */
	public InquiryActionRequest(DMAction dmAction, UserContext userContext, ServiceTypeEnum serviceTypeEnumParam,
			DMTenant tenantParam)
	{
		this(dmAction, userContext, serviceTypeEnumParam);
		setTenant(tenantParam);
	}

	/**
	 * Instantiates a new inquiry action request.
	 * 
	 * @param dmAction the action
	 * @param userContext the user context
	 * @param processId the process id
	 */
	public InquiryActionRequest(DMAction dmAction, UserContext userContext, Integer processId, DMTenant tenantParam)
	{
		this(dmAction, userContext);
		setProcessId(processId);
		setTenant(tenantParam);
	}

	/**
	 * Instantiates a new inquiry action request.
	 * 
	 * @param dmAction the action
	 * @param userContext the user context
	 * @param processId the process id
	 * @param serviceTypeEnum the service type enum
	 */
	public InquiryActionRequest(DMAction dmAction, UserContext userContext, Integer processId,
			ServiceTypeEnum serviceTypeEnum)
	{
		this(dmAction, userContext);
		setProcessId(processId);
		setServiceTypeEnum(serviceTypeEnum);
	}

	/**
	 * Instantiates a new inquiry action request.
	 * 
	 * @param search the search
	 */
	public InquiryActionRequest(DeviceSearch search)
	{
		setDeviceSearch(search);
	}

	/**
	 * Instantiates a new inquiry action request.
	 * 
	 * @param search the search
	 * @param sortExpressionParam the sort expression param
	 */
	public InquiryActionRequest(DeviceSearch search, SortExpression sortExpressionParam)
	{
		setDeviceSearch(search);
		addSortExpressions(sortExpressionParam);
	}

	/**
	 * Gets the device search.
	 * 
	 * @return the device search
	 */
	public DeviceSearch getDeviceSearch()
	{
		return deviceSearch;
	}

	/**
	 * Sets the device search.
	 * 
	 * @param deviceSearch the new device search
	 */
	public void setDeviceSearch(DeviceSearch deviceSearch)
	{
		this.deviceSearch = deviceSearch;
	}

	/**
	 * Gets the actions.
	 * 
	 * @return the actions
	 */
	public List<DMAction> getActions()
	{
		return actions;
	}

	/**
	 * Sets the actions.
	 * 
	 * @param actions the new actions
	 */
	public void setActions(List<DMAction> actions)
	{
		this.actions = actions;
	}

	/**
	 * Adds the action.
	 * 
	 * @param dmAction the action
	 */
	public void addAction(DMAction dmAction)
	{
		if (getActions() == null)
		{
			setActions(new ArrayList<DMAction>());
		}
		getActions().add(dmAction);
	}

	/**
	 * Gets the han devices file.
	 * 
	 * @return the han devices file
	 */
	public File getHanDevicesFile()
	{
		return hanDevicesFile;
	}

	/**
	 * Sets the han devices file.
	 * 
	 * @param hanDevicesFile the new han devices file
	 */
	public void setHanDevicesFile(File hanDevicesFile)
	{
		this.hanDevicesFile = hanDevicesFile;
	}

	/**
	 * Gets the upload han devices.
	 * 
	 * @return the upload han devices
	 */
	public String getUploadHanDevices()
	{
		return uploadHanDevices;
	}

	/**
	 * Sets the upload han devices.
	 * 
	 * @param uploadHanDevices the new upload han devices
	 */
	public void setUploadHanDevices(String uploadHanDevices)
	{
		this.uploadHanDevices = uploadHanDevices;
	}

	/**
	 * Gets the schedule name.
	 * 
	 * @return the schedule name
	 */
	public String getScheduleName()
	{
		return scheduleName;
	}

	/**
	 * Sets the schedule name.
	 * 
	 * @param scheduleNameParam the new schedule name
	 */
	public void setScheduleName(String scheduleNameParam)
	{
		scheduleName = scheduleNameParam;
	}

	/**
	 * Gets the first action.
	 * 
	 * @return the first action
	 */
	public DMAction getFirstAction()
	{
		if (getActions() != null && !getActions().isEmpty())
		{
			return getActions().get(FIRST);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.base.model.request.InquiryPaginationRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryActionRequest [getDeviceSearch()=" + getDeviceSearch() + ", getActions()="
				+ getActions() + ", getHanDevicesFile()="
				+ getHanDevicesFile() + ", getUploadHanDevices()=" + getUploadHanDevices() + ", getFirstAction()="
				+ getFirstAction() + ", getScheduleName()=" + getScheduleName() + ", toString()=" + super.toString()
				+ "]";
	}
}
