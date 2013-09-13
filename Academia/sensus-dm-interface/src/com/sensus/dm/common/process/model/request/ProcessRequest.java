package com.sensus.dm.common.process.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.ProcessSearch;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class ProcessRequest.
 * 
 * @author QAT Brazil.
 */
public class ProcessRequest extends TenantRequest
{

	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The process list. */
	private List<DMProcess> processList;

	/** The process filter. */
	private ProcessSearch processSearch;

	/** The retry parent process. */
	private Integer retryParent;

	/** The update status. */
	private ProcessItemStatusEnum processItemStatusEnum;

	/**
	 * Instantiates a new process request.
	 */
	public ProcessRequest()
	{
	}

	/**
	 * Instantiates a new process request.
	 * 
	 * @param userContext the user context
	 */
	public ProcessRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new process request.
	 * 
	 * @param processParam the process param
	 */
	public ProcessRequest(DMProcess processParam)
	{
		addProcessAsFirstElement(processParam);
	}

	/**
	 * Instantiates a new process request.
	 * 
	 * @param processParam the process param
	 * @param tenantParam the DMTenant param
	 */
	public ProcessRequest(DMProcess processParam, DMTenant tenantParam)
	{
		addProcessAsFirstElement(processParam);
		setTenant(tenantParam);
	}

	/**
	 * Instantiates a new process request.
	 * 
	 * @param processParam the process param
	 * @param userContext the user context
	 */
	public ProcessRequest(DMProcess processParam, UserContext userContext)
	{
		addProcessAsFirstElement(processParam);
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new process request.
	 * 
	 * @param processParam the process param
	 * @param userContext the user context
	 * @param processItemStatusEnumParam the process item status enum param
	 */
	public ProcessRequest(DMProcess processParam, UserContext userContext,
			ProcessItemStatusEnum processItemStatusEnumParam)
	{
		addProcessAsFirstElement(processParam);
		setUserContext(userContext);
		setProcessItemStatusEnum(processItemStatusEnumParam);
	}

	/**
	 * Instantiates a new process request.
	 * 
	 * @param processParam the process param
	 * @param userContext the user context
	 * @param serviceTypeEnum the service type enum
	 */
	public ProcessRequest(DMProcess processParam, UserContext userContext, ServiceTypeEnum serviceTypeEnum)
	{
		addProcessAsFirstElement(processParam);
		setUserContext(userContext);
		setServiceTypeEnum(serviceTypeEnum);
	}

	/**
	 * Instantiates a new process request.
	 * 
	 * @param processParam the process param
	 * @param userContext the user context
	 * @param serviceTypeEnum the service type enum
	 * @param tenantParam the DMTenant
	 */
	public ProcessRequest(DMProcess processParam, UserContext userContext, ServiceTypeEnum serviceTypeEnum,
			DMTenant tenantParam)
	{
		addProcessAsFirstElement(processParam);
		setUserContext(userContext);
		setServiceTypeEnum(serviceTypeEnum);
		setTenant(tenantParam);
	}

	/**
	 * Instantiates a new process request.
	 * 
	 * @param processParam the process param
	 * @param fileName the file name
	 */
	public ProcessRequest(DMProcess processParam, String fileName)
	{
		this(processParam);
		setFileName(fileName);
	}

	/**
	 * Instantiates a new process request.
	 * 
	 * @param processParam the process param
	 * @param userContext the user context
	 * @param fileName the file name
	 */
	public ProcessRequest(DMProcess processParam, UserContext userContext, String fileName)
	{
		this(processParam, userContext);
		setFileName(fileName);
	}

	/**
	 * Instantiates a new process request.
	 * 
	 * @param processListParam the process list param
	 * @param userContext the user context
	 */
	public ProcessRequest(List<DMProcess> processListParam, UserContext userContext)
	{
		setProcessList(processListParam);
		setUserContext(userContext);
	}

	/**
	 * Gets the process list.
	 * 
	 * @return the process list
	 */
	public List<DMProcess> getProcessList()
	{
		return processList;
	}

	/**
	 * Gets the process.
	 * 
	 * @return the process
	 */
	public DMProcess getFirstProcess()
	{
		List<DMProcess> processes = getProcessList();

		if ((processes != null) && !processes.isEmpty())
		{
			return processes.get(FIRST);
		}

		return null;
	}

	/**
	 * Sets the process list.
	 * 
	 * @param processListParam the new process list
	 */
	public void setProcessList(List<DMProcess> processListParam)
	{
		processList = processListParam;
	}

	/**
	 * Sets the process.
	 * 
	 * @param processParam the new process
	 */
	public void addProcessAsFirstElement(DMProcess processParam)
	{
		if (getProcessList() == null)
		{
			setProcessList(new ArrayList<DMProcess>());
			getProcessList().add(processParam);
		}
		else
		{
			getProcessList().set(FIRST, processParam);
		}
	}

	/**
	 * Gets the process filter.
	 * 
	 * @return the process filter
	 */
	public ProcessSearch getProcessSearch()
	{
		return processSearch;
	}

	/**
	 * Sets the process filter.
	 * 
	 * @param processSearch the new process filter
	 */
	public void setProcessSearch(ProcessSearch processSearch)
	{
		this.processSearch = processSearch;
	}

	/**
	 * Gets the retry parent.
	 * 
	 * @return the retryParent
	 */
	public Integer getRetryParent()
	{
		return retryParent;
	}

	/**
	 * Sets the retry parent.
	 * 
	 * @param retryParent the retryParent to set
	 */
	public void setRetryParent(Integer retryParent)
	{
		this.retryParent = retryParent;
	}

	/**
	 * Gets the process item status enum.
	 * 
	 * @return the process item status enum
	 */
	public ProcessItemStatusEnum getProcessItemStatusEnum()
	{
		return processItemStatusEnum;
	}

	/**
	 * Sets the process item status enum.
	 * 
	 * @param processItemStatusEnum the new process item status enum
	 */
	public void setProcessItemStatusEnum(ProcessItemStatusEnum processItemStatusEnum)
	{
		this.processItemStatusEnum = processItemStatusEnum;
	}

	/**
	 * Gets the process item status enum value.
	 * 
	 * @return the process item status enum value
	 */
	public Integer getProcessItemStatusEnumValue()
	{

		if (getProcessItemStatusEnum() != null)
		{
			return getProcessItemStatusEnum().getValue();
		}

		return null;
	}

	/**
	 * Sets the process item status enum value.
	 * 
	 * @param processItemStatusEnumParam the new process item status enum value
	 */
	public void setProcessItemStatusEnumValue(Integer processItemStatusEnumParam)
	{
		setProcessItemStatusEnum(ProcessItemStatusEnum.enumForValue(processItemStatusEnumParam));
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tenant.model.request.TenantRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "ProcessRequest [getProcessList()=" + getProcessList() + ", getFirstProcess()=" + getFirstProcess()
				+ ", getProcessSearch()=" + getProcessSearch() + ", getRetryParent()=" + getRetryParent()
				+ ", getProcessItemStatusEnum()=" + getProcessItemStatusEnum() + ", getProcessItemStatusEnumValue()="
				+ getProcessItemStatusEnumValue() + "]";
	}
}
