package com.sensus.lc.process.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.process.model.Process;

/**
 * The Class InquiryProcessResponse.
 */
public class InquiryProcessResponse extends InquiryResponse
{

	/** The processes. */
	private List<Process> processes;

	/** The file name. */
	private String fileName;

	/**
	 * Gets the processes.
	 * 
	 * @return the processes
	 */
	public List<Process> getProcesses()
	{
		return this.processes;
	}

	/**
	 * Sets the processes.
	 * 
	 * @param processes the new processes
	 */
	public void setProcesses(List<Process> processes)
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
		setProcesses(new ArrayList<Process>(coll));
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName()
	{
		return this.fileName;
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryProcessResponse [getProcesses()=" + getProcesses() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}
