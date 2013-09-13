package com.sensus.cbof.model;

import java.math.BigInteger;

import com.sensus.common.model.SensusModel;

/**
 * The Class Note.
 *
 */
@SuppressWarnings("serial")
public class Note extends SensusModel
{

	/** The id. */
	private Integer id;

	/** The text. */
	private String text;

	/** The flex net id. */
	private BigInteger flexNetId;

	// -------------------------------------------------------------------------
	// Constructors:

	/**
	 * Instantiates a new note.
	 */
	public Note()
	{

	}

	/**
	 * Instantiates a new note.
	 *
	 * @param flexNetIdParam the flex net id param
	 */
	public Note(BigInteger flexNetIdParam)
	{
		setFlexNetId(flexNetIdParam);
	}

	/**
	 * Instantiates a new note.
	 *
	 * @param flexNetIdParam the flex net id param
	 * @param textParam the text param
	 */
	public Note(BigInteger flexNetIdParam, String textParam)
	{
		setFlexNetId(flexNetIdParam);
		setText(textParam);
	}

	/**
	 * Instantiates a new note.
	 *
	 * @param idParam the id param
	 */
	public Note(Integer idParam)
	{
		setId(idParam);
	}

	/**
	 * Instantiates a new note.
	 *
	 * @param idParam the id param
	 * @param textParam the text param
	 */
	public Note(Integer idParam, String textParam)
	{
		setId(idParam);
		setText(textParam);
	}

	// -------------------------------------------------------------------------
	// Getters and setters:

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
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText()
	{
		return text;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(String text)
	{
		this.text = text;
	}

	/**
	 * Gets the flex net id.
	 *
	 * @return the flex net id
	 */
	public BigInteger getFlexNetId()
	{
		return flexNetId;
	}

	/**
	 * Sets the flex net id.
	 *
	 * @param flexNetId the new flex net id
	 */
	public void setFlexNetId(BigInteger flexNetId)
	{
		this.flexNetId = flexNetId;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Note [getId()=" + getId() + ", getText()=" + getText() + ", getFlexNetId()=" + getFlexNetId()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}

}
