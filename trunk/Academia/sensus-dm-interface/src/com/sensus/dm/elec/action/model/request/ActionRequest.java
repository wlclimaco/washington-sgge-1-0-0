package com.sensus.dm.elec.action.model.request;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class ActionRequest.
 * 
 * @author QAT Global
 */
public class ActionRequest extends TenantRequest
{
	/** The action. */
	private DMAction action;

	/** The parent retry. */
	private Integer parentRetry;

	/** The preset date. */
	private Date presetDate;

	/** The han devices file. */
	private File hanDevicesFile;

	/** The tags. */
	private List<Tag> tags;

	/** The ids file. */
	private File idsFile;

	/** The action type descriptions. */
	private List<String> actionTypeDescriptions;

	/** The schedule name. Used in the Event Description on Event History Tab and Today Tab */
	private String scheduleName;

	// -------------------------------------------------------------------------
	// Constructors:

	/**
	 * Instantiates a new action request.
	 */
	public ActionRequest()
	{
	}

	/**
	 * Instantiates a new action request.
	 * 
	 * @param userContext the user context
	 */
	public ActionRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new action request.
	 * 
	 * @param dmAction the action
	 */
	public ActionRequest(DMAction dmAction)
	{
		setAction(dmAction);
	}

	/**
	 * Instantiates a new action request.
	 * 
	 * @param actionParam the action param
	 * @param userContextParam the user context param
	 * @param unreachableIdsParam the unreachable ids param
	 * @param idFileTypeParam the id file type param
	 * @param serviceTypeEnumParam the service type enum param
	 * @param processIdParam the process id param
	 * @param tenantParam the tenant param
	 */
	public ActionRequest(DMAction actionParam, UserContext userContextParam,
			List<String> unreachableIdsParam, PropertyEnum idFileTypeParam, ServiceTypeEnum serviceTypeEnumParam,
			Integer processIdParam, DMTenant tenantParam)
	{
		setAction(actionParam);
		setUserContext(userContextParam);
		setUnreachableIds(unreachableIdsParam);
		setIdFileType(idFileTypeParam);
		setServiceTypeEnum(serviceTypeEnumParam);
		setProcessId(processIdParam);
		setTenant(tenantParam);
	}

	/**
	 * Instantiates a new action request.
	 * 
	 * @param actionParam the action param
	 * @param userContextParam the user context param
	 * @param unreachableIdsParam the unreachable ids param
	 * @param idFileTypeParam the id file type param
	 * @param serviceTypeEnumParam the service type enum param
	 * @param processIdParam the process id param
	 * @param tenantParam the tenant param
	 * @param fileName the file name
	 * @param scheduleName the schedule name
	 */
	public ActionRequest(DMAction actionParam, UserContext userContextParam,
			List<String> unreachableIdsParam, PropertyEnum idFileTypeParam, ServiceTypeEnum serviceTypeEnumParam,
			Integer processIdParam, DMTenant tenantParam, String fileName, String scheduleName)
	{
		this(actionParam, userContextParam, unreachableIdsParam, idFileTypeParam, serviceTypeEnumParam, processIdParam,
				tenantParam);
		setFileName(fileName);
		setScheduleName(scheduleName);
	}

	/**
	 * Instantiates a new action request.
	 * 
	 * @param actionParam the action param
	 * @param userContextParam the user context param
	 * @param processIdParam the process id param
	 */
	public ActionRequest(DMAction actionParam, UserContext userContextParam, Integer processIdParam)
	{
		setAction(actionParam);
		setUserContext(userContextParam);
		setProcessId(processIdParam);
	}

	/**
	 * Instantiates a new action request.
	 * 
	 * @param userContextParam the user context param
	 * @param processIdParam the process id param
	 */
	public ActionRequest(UserContext userContextParam, Integer processIdParam)
	{
		setUserContext(userContextParam);
		setProcessId(processIdParam);
	}

	/**
	 * Instantiates a new action request.
	 * 
	 * @param actionParam the action param
	 * @param userContextParam the user context param
	 */
	public ActionRequest(DMAction actionParam, UserContext userContextParam)
	{
		setAction(actionParam);
		setUserContext(userContextParam);
	}

	/**
	 * Instantiates a new action request.
	 * 
	 * @param actionParam the action param
	 * @param userContextParam the user context param
	 * @param tenantParam the tenant param
	 */
	public ActionRequest(DMAction actionParam, UserContext userContextParam, DMTenant tenantParam)
	{
		this(actionParam, userContextParam);
		setTenant(tenantParam);
	}

	/**
	 * Instantiates a new action request.
	 * 
	 * @param actionParam the action param
	 * @param userContextParam the user context param
	 * @param idsFileParam the ids file param
	 * @param idFileTypeParam the id file type param
	 * @param uploadIds the upload ids
	 */
	public ActionRequest(DMAction actionParam, UserContext userContextParam, File idsFileParam,
			PropertyEnum idFileTypeParam,
			String uploadIds)
	{
		this(actionParam, userContextParam);
		setIdsFile(idsFileParam);
		setIdFileType(idFileTypeParam);
		setUploadIds(uploadIds);
	}

	// -------------------------------------------------------------------------
	// Getters and setters:

	/**
	 * Gets the action.
	 * 
	 * @return the action
	 */
	public DMAction getAction()
	{
		return action;
	}

	/**
	 * Sets the action.
	 * 
	 * @param action the new action
	 */
	public void setAction(DMAction action)
	{
		this.action = action;
	}

	/**
	 * Gets the parent retry.
	 * 
	 * @return the parent retry
	 */
	public Integer getParentRetry()
	{
		return parentRetry;
	}

	/**
	 * Sets the parent retry.
	 * 
	 * @param parentRetry the new parent retry
	 */
	public void setParentRetry(Integer parentRetry)
	{
		this.parentRetry = parentRetry;
	}

	/**
	 * Gets the preset date.
	 * 
	 * @return the preset date
	 */
	public Date getPresetDate()
	{
		return presetDate;
	}

	/**
	 * Sets the preset date.
	 * 
	 * @param presetDate the new preset date
	 */
	public void setPresetDate(Date presetDate)
	{
		this.presetDate = presetDate;
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
	 * Gets the tags.
	 * 
	 * @return the tags
	 */
	public List<Tag> getTags()
	{
		return tags;
	}

	/**
	 * Sets the tags.
	 * 
	 * @param tags the new tags
	 */
	public void setTags(List<Tag> tags)
	{
		this.tags = tags;
	}

	/**
	 * Gets the ids file.
	 * 
	 * @return the ids file
	 */
	public File getIdsFile()
	{
		return idsFile;
	}

	/**
	 * Sets the ids file.
	 * 
	 * @param idsFile the new ids file
	 */
	public void setIdsFile(File idsFile)
	{
		this.idsFile = idsFile;
	}

	/**
	 * Gets the action type descriptions.
	 * 
	 * @return the action type descriptions
	 */
	public List<String> getActionTypeDescriptions()
	{
		return actionTypeDescriptions;
	}

	/**
	 * Sets the action type descriptions.
	 * 
	 * @param actionTypeDescriptions the new action type descriptions
	 */
	public void setActionTypeDescriptions(List<String> actionTypeDescriptions)
	{
		this.actionTypeDescriptions = actionTypeDescriptions;
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
	 * Adds the action type description.
	 * 
	 * @param actionTypeDescription the action type description
	 */
	public void addActionTypeDescription(String actionTypeDescription)
	{
		if (getActionTypeDescriptions() == null)
		{
			setActionTypeDescriptions(new ArrayList<String>());
		}

		getActionTypeDescriptions().add(actionTypeDescription);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ActionRequest [getAction()=" + getAction() + ", getParentRetry()=" + getParentRetry()
				+ ", getPresetDate()=" + getPresetDate() + ", getHanDevicesFile()=" + getHanDevicesFile()
				+ ", getTags()=" + getTags() + ", getIdsFile()="
				+ getIdsFile() + ", getActionTypeDescriptions()=" + getActionTypeDescriptions()
				+ ", getScheduleName()=" + getScheduleName() + ", getIsMonitored()=" + getIsMonitored()
				+ ", getRecentRequestMonitored()=" + getRecentRequestMonitored() + ", getTimeZone()=" + getTimeZone()
				+ ", getDateFormat()=" + getDateFormat() + ", getFileName()=" + getFileName() + ", getProcessId()="
				+ getProcessId() + ", getUnreachableIds()=" + getUnreachableIds() + ", getIdFileType()="
				+ getIdFileType() + ", getUploadIds()=" + getUploadIds() + ", getServiceTypeEnum()="
				+ getServiceTypeEnum() + ", getServiceTypeEnumValue()=" + getServiceTypeEnumValue()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}
