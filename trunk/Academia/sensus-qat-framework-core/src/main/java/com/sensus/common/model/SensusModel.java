package com.sensus.common.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * This class may be used as a base or super class for a Model object.<br/>
 * This class or the {@link SensusModelOL} class should be used as the super class for ALL model objects. The difference
 * between these 2 types are as follows:
 * <ul>
 * <li>The QATModel class should be used for basic Model Objects that do not require any special support for optimistic
 * locking.
 * <li>The {@link SensusModelOL} class should be used for Model Objects that do required special support for optimistic
 * locking.
 * </ul>
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@SuppressWarnings("serial")
public class SensusModel implements java.io.Serializable
{
	// [start] private fields

	/** The model action. */
	@XmlElement(nillable = true)
	private PersistanceActionEnum modelAction;

	/** The create user. */
	private String createUser;

	/** The modify user. */
	private String modifyUser;

	/** The create date. */
	private Date createDate;

	/** The modify date. */
	private Date modifyDate;

	/**
	 * Sets the model action.
	 * 
	 * @param action the new model action
	 */
	public void setModelAction(PersistanceActionEnum action)
	{
		modelAction = action;
	}

	/**
	 * Gets the model action.
	 * 
	 * @return the model action
	 */
	public PersistanceActionEnum getModelAction()
	{
		if (modelAction == null)
		{
			modelAction = PersistanceActionEnum.NONE;
		}
		return modelAction;
	}

	/**
	 * The Enum ModelActionEnum.
	 */
	public static enum PersistanceActionEnum
	{

		/** No action should be taken. */
		NONE,

		/** The object should be added to the data repository. */
		INSERT,

		/** The object should be updated to the data repository. */
		UPDATE,

		/** The object should be removed from the data repository. */
		DELETE,

		/** The object is in error. */
		ERROR,

		/** The object is in error. */
		FETCHBYID,

		/** The object should be skipped. */
		SKIP
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
	 * Gets the creates the date.
	 * 
	 * @return the creates the date
	 */
	public Date getCreateDate()
	{
		return createDate;
	}

	/**
	 * Sets the creates the date.
	 * 
	 * @param createDate the new creates the date
	 */
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	/**
	 * Gets the modify user.
	 * 
	 * @return the modify user
	 */
	public String getModifyUser()
	{
		return modifyUser;
	}

	/**
	 * Sets the modify user.
	 * 
	 * @param modifyUser the new modify user
	 */
	public void setModifyUser(String modifyUser)
	{
		this.modifyUser = modifyUser;
	}

	/**
	 * Gets the modify date.
	 * 
	 * @return the modify date
	 */
	public Date getModifyDate()
	{
		return modifyDate;
	}

	/**
	 * Sets the modify date.
	 * 
	 * @param modifyDate the new modify date
	 */
	public void setModifyDate(Date modifyDate)
	{
		this.modifyDate = modifyDate;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "QATModel [getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreatedDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}
}
