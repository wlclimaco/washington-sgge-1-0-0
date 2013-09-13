package com.sensus.dm.common.process.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.UserContext;
import com.sensus.dm.common.base.model.request.InquiryPaginationRequest;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.ProcessSearch;
import com.sensus.dm.common.property.model.PropertyEnum;

/**
 * The Class InquiryProcessRequest.
 * 
 * @author QAT Global.
 */
public class InquiryProcessRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The process filter. */
	private ProcessSearch processSearch;

	/** The processes. */
	private List<DMProcess> processes;

	/** The process items. */
	private List<ProcessItem> processItems;

	/** The devices. */
	private List<Device> devices;

	/** The process item status enum. */
	private ProcessItemStatusEnum processItemStatusEnum;

	/** The is today. */
	private Boolean isToday;

	/** The file ids type. */
	private PropertyEnum fileIdsType;

	/** The unreachables ids. */
	private String unreachablesIds;

	/**
	 * Instantiates a new inquiry process request.
	 */
	public InquiryProcessRequest()
	{
	}

	/**
	 * Instantiates a new inquiry process request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryProcessRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new inquiry process request.
	 * 
	 * @param userContext the user context
	 * @param processSearchParam the process filter
	 * @param sortExpressionParam the sort expression
	 */
	public InquiryProcessRequest(UserContext userContext, ProcessSearch processSearchParam,
			SortExpression sortExpressionParam)
	{
		this(userContext);
		setProcessSearch(processSearchParam);
		addSortExpressions(sortExpressionParam);
	}

	/**
	 * Instantiates a new inquiry process request.
	 * 
	 * @param userContext the user context
	 * @param items the items
	 */
	public InquiryProcessRequest(UserContext userContext, List<ProcessItem> items)
	{
		this(userContext);
		setProcessItems(items);
	}

	/**
	 * Instantiates a new inquiry process request.
	 * 
	 * @param processParam the process param
	 */
	public InquiryProcessRequest(DMProcess processParam)
	{
		addProcess(processParam);
	}

	/**
	 * Instantiates a new inquiry process request.
	 * 
	 * @param processList the process views
	 */
	public InquiryProcessRequest(List<DMProcess> processList)
	{
		setProcesses(processList);
	}

	/**
	 * Instantiates a new inquiry process request.
	 * 
	 * @param processParam the process param
	 * @param user the user
	 */
	public InquiryProcessRequest(DMProcess processParam, UserContext user)
	{
		addProcess(processParam);
		setUserContext(user);
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
	 * Gets the processes.
	 * 
	 * @return the processes
	 */
	public List<DMProcess> getProcesses()
	{
		return processes;
	}

	/**
	 * Gets the process.
	 * 
	 * @return the process
	 */
	public DMProcess getFirstProcess()
	{
		if (getProcesses() != null && !getProcesses().isEmpty())
		{
			return getProcesses().get(FIRST);
		}

		return null;
	}

	/**
	 * Sets the processes.
	 * 
	 * @param processes the new processes
	 */
	public void setProcesses(List<DMProcess> processes)
	{
		this.processes = processes;
	}

	/**
	 * Sets the process.
	 * 
	 * @param processParam the new process
	 */
	public void addProcess(DMProcess processParam)
	{
		if (getProcesses() == null)
		{
			setProcesses(new ArrayList<DMProcess>());
		}

		getProcesses().add(processParam);
	}

	/**
	 * Gets the devices.
	 * 
	 * @return the devices
	 */
	public List<Device> getDevices()
	{
		return devices;
	}

	/**
	 * Get the first device in the List.
	 * 
	 * @return the device
	 */
	public Device getFirstDevice()
	{
		if (getDevices() != null && !getDevices().isEmpty())
		{
			return getDevices().get(FIRST);
		}

		return null;
	}

	/**
	 * Sets the devices.
	 * 
	 * @param devices the new devices
	 */
	public void setDevices(List<Device> devices)
	{
		this.devices = devices;
	}

	/**
	 * Add a Device to the List.
	 * 
	 * @param deviceObject the new Device
	 */
	public void addDevice(Device deviceObject)
	{
		if (getDevices() == null)
		{
			setDevices(new ArrayList<Device>());
		}

		getDevices().add(deviceObject);
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
	 * Gets the file ids type.
	 * 
	 * @return the fileIdsType
	 */
	public PropertyEnum getFileIdsType()
	{
		return fileIdsType;
	}

	/**
	 * Sets the file ids type.
	 * 
	 * @param fileIdsType the fileIdsType to set
	 */
	public void setFileIdsType(PropertyEnum fileIdsType)
	{
		this.fileIdsType = fileIdsType;
	}

	/**
	 * Gets the unreachables ids.
	 * 
	 * @return the unreachablesIds
	 */
	public String getUnreachablesIds()
	{
		return unreachablesIds;
	}

	/**
	 * Sets the unreachables ids.
	 * 
	 * @param unreachablesIds the unreachablesIds to set
	 */
	public void setUnreachablesIds(String unreachablesIds)
	{
		this.unreachablesIds = unreachablesIds;
	}

	/**
	 * Gets the process items.
	 * 
	 * @return the process items
	 */
	public List<ProcessItem> getProcessItems()
	{
		return processItems;
	}

	/**
	 * Sets the process items.
	 * 
	 * @param processItems the new process items
	 */
	public void setProcessItems(List<ProcessItem> processItems)
	{
		this.processItems = processItems;
	}

	/**
	 * Gets the checks if is today.
	 * 
	 * @return the checks if is today
	 */
	public Boolean getIsToday()
	{
		return isToday;
	}

	/**
	 * Sets the checks if is today.
	 * 
	 * @param isToday the new checks if is today
	 */
	public void setIsToday(Boolean isToday)
	{
		this.isToday = isToday;
	}

	@Override
	public String toString()
	{
		return "InquiryProcessRequest [getProcessSearch()=" + getProcessSearch() + ", getProcesses()=" + getProcesses()
				+ ", getFirstProcess()=" + getFirstProcess() + ", getProcessItemStatusEnum()="
				+ getProcessItemStatusEnum() + ", getIsToday()=" + getIsToday()
				+ ", getFileIdsType()=" + getFileIdsType() + "getDevices()=" + getDevices()
				+ ", getUnreachablesIds()=" + getUnreachablesIds() + ", getProcessItems()=" + getProcessItems()
				+ ", toString()=" + super.toString() + "]";
	}

}
