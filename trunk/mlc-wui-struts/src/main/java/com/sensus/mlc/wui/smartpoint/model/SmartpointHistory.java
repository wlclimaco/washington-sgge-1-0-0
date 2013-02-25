package com.sensus.mlc.wui.smartpoint.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

/**
 * The Class SmartpointHistory is view helper class.
 * 
 * @author Alexandre Tiveron
 * 
 */

@SuppressWarnings("serial")
public class SmartpointHistory implements Serializable, Comparator<SmartpointHistory>
{

	/** The date. */
	private Date date;

	/** The time. */
	private String time;

	/** The name. */
	private String name;

	/** The description. */
	private String description;

	/** The create user. */
	private String createUser;

	/** The process id. */
	private String processId;

	/** The process completed in. */
	private String processCompletedIn;

	/** The process completed. */
	private String processCompleted;

	/**
	 * Gets the date.
	 * 
	 * @return the date
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date the new date
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}

	/**
	 * Gets the time.
	 * 
	 * @return the time
	 */
	public String getTime()
	{
		return time;
	}

	/**
	 * Sets the time.
	 * 
	 * @param time the new time
	 */
	public void setTime(String time)
	{
		this.time = time;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name the new name
	 */
	public void setName(String name)
	{
		this.name = name;
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
	 * Gets the creates the user.
	 * 
	 * @return the creates the user
	 */
	public String getCreateUser()
	{
		return createUser;
	}

	/**
	 * Sets the creates the user.
	 * 
	 * @param createUser the new creates the user
	 */
	public void setCreateUser(String createUser)
	{
		this.createUser = createUser;
	}

	/**
	 * Gets the process id.
	 * 
	 * @return the process id
	 */
	public String getProcessId()
	{
		return processId;
	}

	/**
	 * Sets the process id.
	 * 
	 * @param processId the new process id
	 */
	public void setProcessId(String processId)
	{
		this.processId = processId;
	}

	/**
	 * Gets the process completed in.
	 * 
	 * @return the process completed in
	 */
	public String getProcessCompletedIn()
	{
		return processCompletedIn;
	}

	/**
	 * Sets the process completed in.
	 * 
	 * @param processCompletedIn the new process completed in
	 */
	public void setProcessCompletedIn(String processCompletedIn)
	{
		this.processCompletedIn = processCompletedIn;
	}

	/**
	 * Gets the process completed.
	 * 
	 * @return the process completed
	 */
	public String getProcessCompleted()
	{
		return processCompleted;
	}

	/**
	 * Sets the process completed.
	 * 
	 * @param processCompleted the new process completed
	 */
	public void setProcessCompleted(String processCompleted)
	{
		this.processCompleted = processCompleted;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(SmartpointHistory o1, SmartpointHistory o2)
	{
		return o2.getDate().compareTo(o1.getDate());
	}
}
