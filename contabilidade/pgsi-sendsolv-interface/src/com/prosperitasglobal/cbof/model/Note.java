package com.prosperitasglobal.cbof.model;

import com.prosperitasglobal.sendsolv.model.ModelCosmeDamiao;

/**
 * The Class Note models any comment that could be attached to entities in the system, for instance {@link Person} or
 * {@link Business}
 *
 * @author abarros
 * @version 1.0
 * @created 21-Jul-2014 9:59:59 AM
 */
@SuppressWarnings("serial")
public class Note extends ModelCosmeDamiao
{

	/** The id. */
	private Integer id;

	/** The note. */
	private String noteText;

	/**
	 * The Constructor.
	 */
	public Note()
	{

	}

	/**
	 * Gets the note text.
	 *
	 * @return the note text
	 */
	public String getNoteText()
	{
		return noteText;
	}

	/**
	 * Sets the note text.
	 *
	 * @param noteText the note text
	 */
	public void setNoteText(String noteText)
	{
		this.noteText = noteText;
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
	 * @param id the id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the parent key.
	 *
	 * @return the parent key
	 */
	public Integer getParentKey()
	{
		return parentKey;
	}

	/**
	 * Sets the parent key.
	 *
	 * @param parentKey the parent key
	 */
	public void setParentKey(Integer parentKey)
	{
		this.parentKey = parentKey;
	}

	/**
	 * ParentKeyType get/set and support for Enum.
	 *
	 * @return the parent key type
	 */
	public BusinessTypeEnum getParentKeyType()
	{
		return parentKeyType;
	}

	/**
	 * Sets the parent key type.
	 *
	 * @param parentKeyType the parent key type
	 */
	public void setParentKeyType(BusinessTypeEnum parentKeyType)
	{
		this.parentKeyType = parentKeyType;
	}

	/**
	 * Sets the parent key value.
	 *
	 * @param parentKeyValue the parent key value
	 */
	public void setParentKeyValue(Integer parentKeyValue)
	{
		parentKeyType = BusinessTypeEnum.enumForValue(parentKeyValue);
	}

	/**
	 * Gets the parent key value.
	 *
	 * @param parentKeyValue the parent key value
	 * @return the parent key value
	 */
	public Integer getParentKeyValue(Integer parentKeyValue)
	{
		if (parentKeyType == null)
		{
			return null;
		}

		return parentKeyType.getValue();
	}

	/**
	 * Gets the sequence number.
	 *
	 * @return the sequence number
	 */
	public Integer getSequenceNumber()
	{
		return sequenceNumber;
	}

	/**
	 * Sets the sequence number.
	 *
	 * @param sequenceNumber the sequence number
	 */
	public void setSequenceNumber(Integer sequenceNumber)
	{
		this.sequenceNumber = sequenceNumber;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Note [getNoteText()=" + getNoteText() + ", getId()=" + getId() + ", getParentKey()=" + getParentKey()
				+ ", getParentKeyType()=" + getParentKeyType() + ", getSequenceNumber()=" + getSequenceNumber()
				+ ", toString()=" + super.toString() + "]";
	}
}