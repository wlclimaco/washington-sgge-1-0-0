package com.sensus.dm.common.process.model;

import java.util.List;

import com.sensus.common.model.SensusModel;

/**
 * The Class CSVProcess.
 * 
 * @author QAT Brazil.
 */
@SuppressWarnings("serial")
public class CSVProcess extends SensusModel
{
	/** The process list. */
	private List<DMProcess> processList;

	/** The file name. */
	private String fileName;

	/**
	 * Instantiates a new cSV process.
	 */
	public CSVProcess()
	{
	}

	/**
	 * Instantiates a new cSV process.
	 * 
	 * @param listProcess the list process
	 * @param valueFileName the value file name
	 */
	public CSVProcess(List<DMProcess> listProcess, String valueFileName)
	{
		setProcessList(listProcess);
		setFileName(valueFileName);
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
	 * Sets the process list.
	 * 
	 * @param processList the new process list
	 */
	public void setProcessList(List<DMProcess> processList)
	{
		this.processList = processList;
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "CSVProcess [getProcessList()=" + getProcessList() + ", getFileName()=" + getFileName()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}
}
