package com.sensus.lc.process.model.request;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.sensus.common.model.UserContext;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.ProcessFilter;
import com.sensus.lc.process.model.ProcessItem;
import com.sensus.lc.process.model.ProcessItemStatusEnum;
import com.sensus.lc.process.model.ProcessStatusReasonEnum;

/**
 * The Class ProcessRequest.
 */
public class ProcessRequest extends InquiryPaginationRequest
{

	/** The Constant FIRST_RESULT. */
	private static final int FIRST_RESULT = 0;

	/** The process list. */
	private List<Process> processList = new ArrayList<Process>();

	/** The process filter. */
	private ProcessFilter processFilter;

	/** The process item status enum. */
	private ProcessItemStatusEnum processItemStatusEnum;

	/** The process status reason enum. */
	private ProcessStatusReasonEnum processStatusReasonEnum;

	/** The insert by fetch. */
	private List<ProcessItem> processItemFailureList;

	/**
	 * Instantiates a new process request.
	 */
	public ProcessRequest()
	{
		setPageSize(NumberUtils.INTEGER_ZERO);
	}

	/**
	 * Create a new process request.
	 *
	 * @param userContext the user context
	 */
	public ProcessRequest(UserContext userContext)
	{
		super(userContext);
		setPageSize(NumberUtils.INTEGER_ZERO);
	}

	/**
	 * Instantiates a new process request.
	 *
	 * @param userContext the user context
	 * @param process the process
	 */
	public ProcessRequest(UserContext userContext, Process process)
	{
		super(userContext);
		setProcess(process);
		setPageSize(NumberUtils.INTEGER_ZERO);
	}

	/**
	 * Sets the process list.
	 *
	 * @param processListParam the new process list
	 */
	public void setProcessList(List<Process> processListParam)
	{
		processList = processListParam;
	}

	/**
	 * Gets the process.
	 *
	 * @return the process
	 */
	public Process getProcess()
	{
		if ((getProcessList() == null) || getProcessList().isEmpty())
		{
			return null;
		}
		return getProcessList().get(FIRST_RESULT);
	}

	/**
	 * Sets the process.
	 *
	 * @param processParam the new process
	 */
	public void setProcess(Process processParam)
	{
		getProcessList().add(processParam);
	}

	/**
	 * Gets the process list.
	 *
	 * @return the process list
	 */
	public List<Process> getProcessList()
	{
		return processList;
	}

	/**
	 * Gets the process filter.
	 *
	 * @return the process filter
	 */
	public ProcessFilter getProcessFilter()
	{
		return processFilter;
	}

	/**
	 * Sets the process filter.
	 *
	 * @param processFilter the new process filter
	 */
	public void setProcessFilter(ProcessFilter processFilter)
	{
		this.processFilter = processFilter;
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
		if (getProcessItemStatusEnum() == null)
		{
			return null;
		}
		return getProcessItemStatusEnum().getValue();
	}

	/**
	 * Gets the process status reason enum.
	 *
	 * @return the process status reason enum
	 */
	public ProcessStatusReasonEnum getProcessStatusReasonEnum()
	{
		return processStatusReasonEnum;
	}

	/**
	 * Sets the process status reason enum.
	 *
	 * @param processStatusReasonEnum the new process status reason enum
	 */
	public void setProcessStatusReasonEnum(ProcessStatusReasonEnum processStatusReasonEnum)
	{
		this.processStatusReasonEnum = processStatusReasonEnum;
	}

	/**
	 * Gets the process status reason enum value.
	 *
	 * @return the process status reason enum value
	 */
	public Integer getProcessStatusReasonEnumValue()
	{
		if (getProcessStatusReasonEnum() == null)
		{
			return null;
		}
		return getProcessStatusReasonEnum().getValue();
	}

	/**
	 * Gets the process item failure list.
	 *
	 * @return the process item failure list
	 */
	public List<ProcessItem> getProcessItemFailureList()
	{
		return processItemFailureList;
	}

	/**
	 * Sets the process item failure list.
	 *
	 * @param processItemFailureList the new process item failure list
	 */
	public void setProcessItemFailureList(List<ProcessItem> processItemFailureList)
	{
		this.processItemFailureList = processItemFailureList;
	}

	@Override
	public String toString()
	{
		return "ProcessRequest [getProcess()=" + getProcess() + ", getProcessList()=" + getProcessList()
				+ ", getProcessFilter()=" + getProcessFilter() + ", getProcessItemStatusEnum()="
				+ getProcessItemStatusEnum() + ", getProcessItemStatusEnumValue()=" + getProcessItemStatusEnumValue()
				+ ", getProcessStatusReasonEnum()=" + getProcessStatusReasonEnum()
				+ ", getProcessStatusReasonEnumValue()=" + getProcessStatusReasonEnumValue()
				+ ", getProcessItemFailureList()=" + getProcessItemFailureList() + ", toString()=" + super.toString()
				+ "]";
	}

}
