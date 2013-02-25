package com.sensus.mlc.process.model;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class Process.
 */
@SuppressWarnings("serial")
public class Process extends SensusModel
{
	/** The id. */
	private Integer id;

	/** The process result list. */
	private List<ProcessItem> processItems;

	/** The description label key. */
	private String description;

	/** The start time. */
	private Date startTime;

	/** The end time. */
	private Date endTime;

	/** The rni correlation id. */
	private String rniCorrelationId;

	/** The parent process. */
	private Process parentProcess;

	/** The estimated seconds to complete. */
	private Integer estimatedSecondsToComplete;

	/** The monitored instance. */
	private Boolean isMonitoredInstance;

	/** The process complete. */
	private Boolean isProcessComplete;

	/** The lc action. */
	private LCAction lcAction;

	/** The is parent. */
	private Boolean isParent;

	/** The is submitted. */
	private Boolean isSubmitted;

	/** The Tenant that created the process. */
	private Tenant tenant;

	/**
	 * Creates a new process.
	 */
	public Process()
	{
	}

	/**
	 * Creates a new process with the given ID.
	 *
	 * @param processId the process id
	 */
	public Process(final Integer processId)
	{
		setId(processId);
	}

	/**
	 * Creates a new process with the given arguments described below:.
	 *
	 * @param processId the process id
	 * @param processItemsValue the process items value
	 * @param descriptionValue the description value
	 * @param rniCorrelationIdValue the rni correlation id value
	 * @param parentProcessValue the parent process value
	 * @param lcActionValue the lc action value
	 * @param tenantValue the tenant value
	 */
	public Process(
			final Integer processId, final List<ProcessItem> processItemsValue, final String descriptionValue,
			final String rniCorrelationIdValue, final Process parentProcessValue, final LCAction lcActionValue,
			final Tenant tenantValue)
	{
		this(processId);
		setProcessItems(processItemsValue);
		setDescription(descriptionValue);
		setRniCorrelationId(rniCorrelationIdValue);
		setParentProcess(parentProcessValue);
		setLcAction(lcActionValue);
		setTenant(tenantValue);
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
	 * Gets the description label key.
	 *
	 * @return the description label key
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets the description label key.
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
	@JSON(format = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	public Date getStartTime()
	{
		return startTime;
	}

	/**
	 * Sets the start time.
	 *
	 * @param startTime the new start time
	 */
	@JSON(format = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	@JSON(format = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	public Date getEndTime()
	{
		return endTime;
	}

	/**
	 * Sets the end time.
	 *
	 * @param endTime the new end time
	 */
	@JSON(format = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	/**
	 * Gets the rni correlation id.
	 *
	 * @return the rni correlation id
	 */
	public String getRniCorrelationId()
	{
		return rniCorrelationId;
	}

	/**
	 * Sets the rni correlation id.
	 *
	 * @param rniCorrelationId the new rni correlation id
	 */
	public void setRniCorrelationId(String rniCorrelationId)
	{
		this.rniCorrelationId = rniCorrelationId;
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
	 * Gets the monitored instance.
	 *
	 * @return the monitored instance
	 */
	public Boolean getIsMonitoredInstance()
	{
		return isMonitoredInstance;
	}

	/**
	 * Sets the monitored instance.
	 *
	 * @param isMonitoredInstance the new checks if is monitored instance
	 */
	public void setIsMonitoredInstance(Boolean isMonitoredInstance)
	{
		this.isMonitoredInstance = isMonitoredInstance;
	}

	/**
	 * Gets the process complete.
	 *
	 * @return the process complete
	 */
	public Boolean getIsProcessComplete()
	{
		return isProcessComplete;
	}

	/**
	 * Sets the process complete.
	 *
	 * @param isProcessComplete the new checks if is process complete
	 */
	public void setIsProcessComplete(Boolean isProcessComplete)
	{
		this.isProcessComplete = isProcessComplete;
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
	 * Gets the checks if is parent.
	 *
	 * @return the checks if is parent
	 */
	public Boolean getIsParent()
	{
		return isParent;
	}

	/**
	 * Sets the checks if is parent.
	 *
	 * @param isParent the new checks if is parent
	 */
	public void setIsParent(Boolean isParent)
	{
		this.isParent = isParent;
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
	 * Gets the lc action.
	 *
	 * @return the lc action
	 */
	public LCAction getLcAction()
	{
		return lcAction;
	}

	/**
	 * Sets the lc action.
	 *
	 * @param lcAction the new lc action
	 */
	public void setLcAction(LCAction lcAction)
	{
		this.lcAction = lcAction;
	}

	/**
	 * Gets the checks if is submitted.
	 *
	 * @return the checks if is submitted
	 */
	public Boolean getIsSubmitted()
	{
		return isSubmitted;
	}

	/**
	 * Sets the checks if is submitted.
	 *
	 * @param isSubmitted the new checks if is submitted
	 */
	public void setIsSubmitted(Boolean isSubmitted)
	{
		this.isSubmitted = isSubmitted;
	}

	/**
	 * Sets the tenant.
	 *
	 * @param tenant the new tenant
	 */
	public void setTenant(Tenant tenant)
	{
		this.tenant = tenant;
	}

	/**
	 * Gets the tenant.
	 *
	 * @return the tenant
	 */
	public Tenant getTenant()
	{
		return tenant;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Process [getId()=" + getId() + ", getDescription()=" + getDescription() + ", getStartTime()="
				+ getStartTime() + ", getEndTime()=" + getEndTime() + ", getRniCorrelationId()="
				+ getRniCorrelationId() + ", getEstimatedSecondsToComplete()=" + getEstimatedSecondsToComplete()
				+ ", getIsMonitoredInstance()=" + getIsMonitoredInstance() + ", getIsProcessComplete()="
				+ getIsProcessComplete() + ", getParentProcess()=" + getParentProcess() + ", getIsParent()="
				+ getIsParent() + ", getProcessItems()=" + getProcessItems() + ", getLcAction()=" + getLcAction()
				+ ", getIsSubmitted()=" + getIsSubmitted() + ", getTenant()=" + getTenant() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}
}
