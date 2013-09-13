package com.sensus.dm.common.process.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.dm.common.action.model.DMAction;

/**
 * The Class Process.
 * 
 * @author QAT Global
 */
@SuppressWarnings("serial")
public class Process extends SensusModel
{
	/** The id. */
	private Integer id;

	/** The process items. */
	private List<ProcessItem> processItems;

	/** The description. */
	private String description;

	/** The start time. */
	private Date startTime;

	/** The end time. */
	private Date endTime;

	/** The parent process. */
	private Process parentProcess;

	/** The estimated seconds to complete. */
	private Integer estimatedSecondsToComplete;

	/** The is monitored instance. */
	private Boolean monitoredInstance;

	/** The is dashboard monitored. */
	private Boolean dashboardMonitored;

	/** The is process complete. */
	private Boolean processComplete;

	/** The action. */
	private DMAction action;

	/** The process status enum. */
	private ProcessStatusEnum processStatusEnum;

	/** The total smartpoints. */
	private Integer totalSmartpoints;

	/** The failed smartpoints. */
	private Integer failedSmartpoints;

	/** The unreachable device ids. */
	private List<String> unreachableDeviceIds;

	/** The process type. */
	private ProcessType processType;

	/** The rni event id. */
	private Integer rniEventId;

	/**
	 * Instantiates a new process.
	 */
	public Process()
	{
	}

	/**
	 * Instantiates a new process.
	 * 
	 * @param processTypeParam the process type param
	 */
	public Process(ProcessType processTypeParam)
	{
		setProcessType(processTypeParam);
	}

	/**
	 * Instantiates a new process.
	 * 
	 * @param actionParam the action param
	 */
	public Process(DMAction actionParam)
	{
		setAction(actionParam);
	}

	/**
	 * Instantiates a new process.
	 * 
	 * @param idParam the id
	 */
	public Process(Integer idParam)
	{
		setId(idParam);
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
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
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
	 * Gets the first process item.
	 * 
	 * @return the first process item
	 */
	public ProcessItem getFirstProcessItem()
	{
		if ((getProcessItems() != null) && !getProcessItems().isEmpty())
		{
			return getProcessItems().get(0);
		}

		return null;
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
	 * Adds the process item.
	 * 
	 * @param processItem the process item
	 */
	public void addProcessItem(ProcessItem processItem)
	{
		if (getProcessItems() == null)
		{
			setProcessItems(new ArrayList<ProcessItem>());
		}

		getProcessItems().add(processItem);
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description the new description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the start time.
	 * 
	 * @return the start time
	 */
	public Date getStartTime()
	{
		return startTime;
	}

	/**
	 * Sets the start time.
	 * 
	 * @param startTime the new start time
	 */
	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	/**
	 * Gets the end time.
	 * 
	 * @return the end time
	 */
	public Date getEndTime()
	{
		return endTime;
	}

	/**
	 * Sets the end time.
	 * 
	 * @param endTime the new end time
	 */
	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	/**
	 * Gets the parent process.
	 * 
	 * @return the parent process
	 */
	public Process getParentProcess()
	{
		return parentProcess;
	}

	/**
	 * Sets the parent process.
	 * 
	 * @param parentProcess the new parent process
	 */
	public void setParentProcess(Process parentProcess)
	{
		this.parentProcess = parentProcess;
	}

	/**
	 * Gets the estimated seconds to complete.
	 * 
	 * @return the estimated seconds to complete
	 */
	public Integer getEstimatedSecondsToComplete()
	{
		return estimatedSecondsToComplete;
	}

	/**
	 * Sets the estimated seconds to complete.
	 * 
	 * @param estimatedSecondsToComplete the new estimated seconds to complete
	 */
	public void setEstimatedSecondsToComplete(Integer estimatedSecondsToComplete)
	{
		this.estimatedSecondsToComplete = estimatedSecondsToComplete;
	}

	/**
	 * Gets the checks if is monitored instance.
	 * 
	 * @return the checks if is monitored instance
	 */
	public Boolean getIsMonitoredInstance()
	{
		return monitoredInstance;
	}

	/**
	 * Checks if is monitored instance.
	 * 
	 * @return the boolean
	 */
	public Boolean isMonitoredInstance()
	{
		return getIsMonitoredInstance();
	}

	/**
	 * Sets the checks if is monitored instance.
	 * 
	 * @param isMonitoredInstance the new checks if is monitored instance
	 */
	public void setIsMonitoredInstance(Boolean isMonitoredInstance)
	{
		monitoredInstance = isMonitoredInstance;
	}

	/**
	 * Gets the checks if is dashboard monitored.
	 * 
	 * @return the checks if is dashboard monitored
	 */
	public Boolean getIsDashboardMonitored()
	{
		return dashboardMonitored;
	}

	/**
	 * Checks if is dashboard monitored.
	 * 
	 * @return the boolean
	 */
	public Boolean isDashboardMonitored()
	{
		return getIsDashboardMonitored();
	}

	/**
	 * Sets the checks if is dashboard monitored.
	 * 
	 * @param isDashboardMonitored the new checks if is dashboard monitored
	 */
	public void setIsDashboardMonitored(Boolean isDashboardMonitored)
	{
		dashboardMonitored = isDashboardMonitored;
	}

	/**
	 * Gets the checks if is process complete.
	 * 
	 * @return the checks if is process complete
	 */
	public Boolean getIsProcessComplete()
	{
		return processComplete;
	}

	/**
	 * Checks if is process complete.
	 * 
	 * @return the boolean
	 */
	public Boolean isProcessComplete()
	{
		return getIsProcessComplete();
	}

	/**
	 * Sets the checks if is process complete.
	 * 
	 * @param isProcessComplete the new checks if is process complete
	 */
	public void setIsProcessComplete(Boolean isProcessComplete)
	{
		processComplete = isProcessComplete;
	}

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
	 * Gets the process status enum.
	 * 
	 * @return the process status enum
	 */
	public ProcessStatusEnum getProcessStatusEnum()
	{
		return processStatusEnum;
	}

	/**
	 * Sets the process status enum.
	 * 
	 * @param processStatusEnum the new process status enum
	 */
	public void setProcessStatusEnum(ProcessStatusEnum processStatusEnum)
	{
		this.processStatusEnum = processStatusEnum;
	}

	/**
	 * Gets the process status enum value.
	 * 
	 * @return the process status enum value
	 */
	public Integer getProcessStatusEnumValue()
	{
		if (getProcessStatusEnum() != null)
		{
			return getProcessStatusEnum().getValue();
		}

		return null;
	}

	/**
	 * Sets the process status enum value.
	 * 
	 * @param processStatusEnumValue the new process status enum value
	 */
	public void setProcessStatusEnumValue(Integer processStatusEnumValue)
	{
		setProcessStatusEnum(ProcessStatusEnum.enumForValue(processStatusEnumValue));
	}

	/**
	 * Gets the total smartpoints.
	 * 
	 * @return the total smartpoints
	 */
	public Integer getTotalSmartpoints()
	{
		return totalSmartpoints;
	}

	/**
	 * Sets the total smartpoints.
	 * 
	 * @param totalSmartpoints the new total smartpoints
	 */
	public void setTotalSmartpoints(Integer totalSmartpoints)
	{
		this.totalSmartpoints = totalSmartpoints;
	}

	/**
	 * Gets the failed smartpoints.
	 * 
	 * @return the failed smartpoints
	 */
	public Integer getFailedSmartpoints()
	{
		return failedSmartpoints;
	}

	/**
	 * Sets the failed smartpoints.
	 * 
	 * @param failedSmartpoints the new failed smartpoints
	 */
	public void setFailedSmartpoints(Integer failedSmartpoints)
	{
		this.failedSmartpoints = failedSmartpoints;
	}

	/**
	 * Gets the unreachable device ids.
	 * 
	 * @return the unreachable device ids
	 */
	public List<String> getUnreachableDeviceIds()
	{
		return unreachableDeviceIds;
	}

	/**
	 * Sets the unreachable device ids.
	 * 
	 * @param unreachableDeviceIds the new unreachable device ids
	 */
	public void setUnreachableDeviceIds(List<String> unreachableDeviceIds)
	{
		this.unreachableDeviceIds = unreachableDeviceIds;
	}

	/**
	 * Gets the process type.
	 * 
	 * @return the process type
	 */
	public ProcessType getProcessType()
	{
		return processType;
	}

	/**
	 * Sets the process type.
	 * 
	 * @param processType the new process type
	 */
	public void setProcessType(ProcessType processType)
	{
		this.processType = processType;
	}

	/**
	 * Gets the rni event id.
	 * 
	 * @return the rni event id
	 */
	public Integer getRniEventId()
	{
		return rniEventId;
	}

	/**
	 * Sets the rni event id.
	 * 
	 * @param rniEventId the new rni event id
	 */
	public void setRniEventId(Integer rniEventId)
	{
		this.rniEventId = rniEventId;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Process [getId()=" + getId() + ", getProcessItems()=" + getProcessItems() + ", getFirstProcessItem()="
				+ getFirstProcessItem() + ", getDescription()=" + getDescription() + ", getStartTime()="
				+ getStartTime() + ", getEndTime()=" + getEndTime() + ", getParentProcess()=" + getParentProcess()
				+ ", getEstimatedSecondsToComplete()=" + getEstimatedSecondsToComplete()
				+ ", getIsMonitoredInstance()=" + getIsMonitoredInstance() + ", isMonitoredInstance()="
				+ isMonitoredInstance() + ", getIsDashboardMonitored()=" + getIsDashboardMonitored()
				+ ", isDashboardMonitored()=" + isDashboardMonitored() + ", getIsProcessComplete()="
				+ getIsProcessComplete() + ", isProcessComplete()=" + isProcessComplete() + ", getAction()="
				+ getAction() + ", getProcessStatusEnum()=" + getProcessStatusEnum()
				+ ", getProcessStatusEnumValue()=" + getProcessStatusEnumValue() + ", getTotalSmartpoints()="
				+ getTotalSmartpoints() + ", getFailedSmartpoints()=" + getFailedSmartpoints()
				+ ", getUnreachableDeviceIds()=" + getUnreachableDeviceIds() + ", getProcessType()=" + getProcessType()
				+ ", getRniEventId()=" + getRniEventId() + ", toString()=" + super.toString() + "]";
	}

}
