package com.sensus.lc.process.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.process.model.Process;

/**
 * The Class ProcessResponse.
 */
public class ProcessResponse extends InquiryResponse
{

	/** The processes. */
	@XmlElement(nillable = true)
	private List<Process> processes = new ArrayList<Process>();

	/** The fileName. */
	private String fileName;

	/** The count monitored process. */
	private Map<String, Integer> countMonitoredProcess;

	/** The rni online. */
	private Boolean rniOnline;

	/**
	 * Gets the first process.
	 * 
	 * @return the first process
	 */
	public Process getFirstProcess()
	{
		if (getProcesses().isEmpty())
		{
			return null;
		}
		return getProcesses().get(0);
	}

	/**
	 * Adds the process.
	 * 
	 * @param process the process
	 */
	public void addProcess(Process process)
	{
		if (!getProcesses().isEmpty())
		{
			getProcesses().add(process);
		}
	}

	/**
	 * Sets the processes.
	 * 
	 * @param processesParam the new processes
	 */
	public void setProcesses(List<Process> processesParam)
	{
		processes = processesParam;
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
	 * Gets the processes.
	 * 
	 * @return the processes
	 */
	public List<Process> getProcesses()
	{
		return processes;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setProcesses(new ArrayList<Process>(coll));
	}

	/**
	 * Gets the count monitored process.
	 * 
	 * @return the count monitored process
	 */
	public Map<String, Integer> getCountMonitoredProcess()
	{
		return countMonitoredProcess;
	}

	/**
	 * Sets the count monitored process.
	 * 
	 * @param countMonitoredProcess the count monitored process
	 */
	public void setCountMonitoredProcess(Map<String, Integer> countMonitoredProcess)
	{
		this.countMonitoredProcess = countMonitoredProcess;
	}

	/**
	 * Checks if is rni online.
	 * 
	 * @return the boolean
	 */
	public Boolean isRniOnline()
	{
		return rniOnline;
	}

	/**
	 * Sets the rni online.
	 * 
	 * @param rniOnline the new rni online
	 */
	public void setRniOnline(Boolean rniOnline)
	{
		this.rniOnline = rniOnline;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProcessResponse [getFirstProcess()=" + getFirstProcess() + ", getFileName()=" + getFileName()
				+ ", getProcesses()=" + getProcesses() + ", getCountMonitoredProcess()=" + getCountMonitoredProcess()
				+ ", isRniOnline()=" + isRniOnline() + ", toString()=" + super.toString() + "]";
	}

}
