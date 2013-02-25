package com.sensus.mlc.process.model.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.process.model.Process;

/**
 * The Class ProcessResponse.
 */
public class ProcessResponse extends Response
{

	/** The processes. */
	@XmlElement(nillable = true)
	private List<Process> processes = new ArrayList<Process>();

	/** The fileName. */
	private String fileName;

	/** The count monitored process. */
	private HashMap<String, Integer> countMonitoredProcess;

	/**
	 * Gets the first process.
	 * 
	 * @return the first process
	 */
	public Process getFirstProcess()
	{
		if (this.getProcesses().isEmpty())
		{
			return null;
		}
		return this.getProcesses().get(0);
	}

	/**
	 * Adds the process.
	 * 
	 * @param process the process
	 */
	public void addProcess(Process process)
	{
		if (!this.getProcesses().isEmpty())
		{
			this.getProcesses().add(process);
		}
	}

	/**
	 * Sets the processes.
	 * 
	 * @param processesParam the new processes
	 */
	public void setProcesses(List<Process> processesParam)
	{
		this.processes = processesParam;
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
	 * Gets the count monitored process.
	 * 
	 * @return the count monitored process
	 */
	public HashMap<String, Integer> getCountMonitoredProcess()
	{
		return this.countMonitoredProcess;
	}

	/**
	 * Sets the count monitored process.
	 * 
	 * @param countMonitoredProcess the count monitored process
	 */
	public void setCountMonitoredProcess(HashMap<String, Integer> countMonitoredProcess)
	{
		this.countMonitoredProcess = countMonitoredProcess;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProcessResponse [getFileName()=" + this.getFileName() + ", getProcesses()=" + this.getProcesses()
				+ ", getCountMonitoredProcess()=" + this.getCountMonitoredProcess() + ", getMessageIterator()="
				+ this.getMessageIterator() + ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()="
				+ this.getMessageInfoList() + ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}

}
