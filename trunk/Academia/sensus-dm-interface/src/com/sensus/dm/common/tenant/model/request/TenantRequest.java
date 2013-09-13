package com.sensus.dm.common.tenant.model.request;

import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.request.Request;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * The Class TenantRequest.
 * 
 * @author QAT Global.
 */
public class TenantRequest extends Request
{
	/** The is monitored. */
	private Boolean isMonitored;

	/** The recent request monitored. */
	private Boolean recentRequestMonitored;

	/** The time zone. */
	private String timeZone;

	/** The date format. */
	private String dateFormat;

	/** The file name. */
	private String fileName;

	/** The process id. */
	private Integer processId;

	/** The unreachable ids. */
	private List<String> unreachableIds;

	/** The id file type. */
	private PropertyEnum idFileType;

	/** The upload ids. */
	private String uploadIds;

	/** The param type. */
	private String paramType;

	/** The service enum. */
	private ServiceTypeEnum serviceTypeEnum;

	/** The tenant. */
	private DMTenant tenant;

	/**
	 * Instantiates a new tenant request.
	 */
	public TenantRequest()
	{
	}

	/**
	 * Instantiates a new tenant request.
	 * 
	 * @param userContext the user context
	 */
	public TenantRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new tenant request.
	 * 
	 * @param userContext the user context
	 * @param tenantParam the tenant param
	 */
	public TenantRequest(UserContext userContext, DMTenant tenantParam)
	{
		setUserContext(userContext);
		setTenant(tenantParam);
	}

	/**
	 * Gets the checks if is monitored.
	 * 
	 * @return the checks if is monitored
	 */
	public Boolean getIsMonitored()
	{
		return isMonitored;
	}

	/**
	 * Sets the checks if is monitored.
	 * 
	 * @param isMonitored the new checks if is monitored
	 */
	public void setIsMonitored(Boolean isMonitored)
	{
		this.isMonitored = isMonitored;
	}

	/**
	 * Gets the recent request monitored.
	 * 
	 * @return the recent request monitored
	 */
	public Boolean getRecentRequestMonitored()
	{
		return recentRequestMonitored;
	}

	/**
	 * Sets the recent request monitored.
	 * 
	 * @param recentRequestMonitored the new recent request monitored
	 */
	public void setRecentRequestMonitored(Boolean recentRequestMonitored)
	{
		this.recentRequestMonitored = recentRequestMonitored;
	}

	/**
	 * Gets the time zone.
	 * 
	 * @return the time zone
	 */
	public String getTimeZone()
	{
		return timeZone;
	}

	/**
	 * Sets the time zone.
	 * 
	 * @param timeZone the new time zone
	 */
	public void setTimeZone(String timeZone)
	{
		this.timeZone = timeZone;
	}

	/**
	 * Gets the date format.
	 * 
	 * @return the date format
	 */
	public String getDateFormat()
	{
		return dateFormat;
	}

	/**
	 * Sets the date format.
	 * 
	 * @param dateFormat the new date format
	 */
	public void setDateFormat(String dateFormat)
	{
		this.dateFormat = dateFormat;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * Gets the process id.
	 * 
	 * @return the process id
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * Sets the process id.
	 * 
	 * @param processId the new process id
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	/**
	 * Gets the unreachable ids.
	 * 
	 * @return the unreachable ids
	 */
	public List<String> getUnreachableIds()
	{
		return unreachableIds;
	}

	/**
	 * Sets the unreachable ids.
	 * 
	 * @param unreachableIds the new unreachable ids
	 */
	public void setUnreachableIds(List<String> unreachableIds)
	{
		this.unreachableIds = unreachableIds;
	}

	/**
	 * Gets the id file type.
	 * 
	 * @return the id file type
	 */
	public PropertyEnum getIdFileType()
	{
		return idFileType;
	}

	/**
	 * Sets the id file type.
	 * 
	 * @param idFileType the new id file type
	 */
	public void setIdFileType(PropertyEnum idFileType)
	{
		this.idFileType = idFileType;
	}

	/**
	 * Gets the upload ids.
	 * 
	 * @return the upload ids
	 */
	public String getUploadIds()
	{
		return uploadIds;
	}

	/**
	 * Sets the upload ids.
	 * 
	 * @param uploadIds the new upload ids
	 */
	public void setUploadIds(String uploadIds)
	{
		this.uploadIds = uploadIds;
	}

	/**
	 * Gets the param type.
	 * 
	 * @return the paramType
	 */
	public final String getParamType()
	{
		return paramType;
	}

	/**
	 * Sets the param type.
	 * 
	 * @param paramType the paramType to set
	 */
	public final void setParamType(String paramType)
	{
		this.paramType = paramType;
	}

	/**
	 * Gets the service type enum.
	 * 
	 * @return the service type enum
	 */
	public ServiceTypeEnum getServiceTypeEnum()
	{
		return serviceTypeEnum;
	}

	/**
	 * Sets the service type enum.
	 * 
	 * @param serviceTypeEnum the new service type enum
	 */
	public void setServiceTypeEnum(ServiceTypeEnum serviceTypeEnum)
	{
		this.serviceTypeEnum = serviceTypeEnum;
	}

	/**
	 * Gets the service type enum value.
	 * 
	 * @return the service type enum value
	 */
	public Integer getServiceTypeEnumValue()
	{
		if (getServiceTypeEnum() != null)
		{
			return getServiceTypeEnum().getValue();
		}

		return null;
	}

	/**
	 * Sets the service type enum value.
	 * 
	 * @param serviceEnumParam the new service type enum value
	 */
	public void setServiceTypeEnumValue(Integer serviceEnumParam)
	{
		setServiceTypeEnum(ServiceTypeEnum.enumForValue(serviceEnumParam));
	}

	/**
	 * Gets the tenant.
	 * 
	 * @return the tenant
	 */
	public DMTenant getTenant()
	{
		return tenant;
	}

	/**
	 * Sets the tenant.
	 * 
	 * @param tenant the new tenant
	 */
	public void setTenant(DMTenant tenant)
	{
		this.tenant = tenant;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TenantRequest [getIsMonitored()=" + getIsMonitored() + ", getRecentRequestMonitored()="
				+ getRecentRequestMonitored() + ", getTimeZone()=" + getTimeZone() + ", getDateFormat()="
				+ getDateFormat() + ", getFileName()=" + getFileName() + ", getProcessId()=" + getProcessId()
				+ ", getUnreachableIds()=" + getUnreachableIds() + ", getIdFileType()=" + getIdFileType()
				+ ", getUploadIds()=" + getUploadIds() + ", getParamType()=" + getParamType()
				+ ", getServiceTypeEnum()=" + getServiceTypeEnum() + ", getServiceTypeEnumValue()="
				+ getServiceTypeEnumValue() + ", getTenant()=" + getTenant() + ", toString()=" + super.toString() + "]";
	}
}
