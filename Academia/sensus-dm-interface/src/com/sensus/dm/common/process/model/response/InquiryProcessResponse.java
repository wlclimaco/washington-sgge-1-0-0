package com.sensus.dm.common.process.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.dm.common.process.model.DMProcess;

/**
 * The Class InquiryProcessResponse.
 * 
 * @author QAT Brazil.
 */
public class InquiryProcessResponse extends InquiryResponse
{

	/** The processes. */
	private List<DMProcess> processes;

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
	 * Sets the processes.
	 * 
	 * @param processes the new processes
	 */
	public void setProcesses(List<DMProcess> processes)
	{
		this.processes = processes;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void addResults(Collection coll)
	{
		setProcesses(new ArrayList<DMProcess>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryProcessResponse [getProcesses()=" + getProcesses() +
				", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}
}
