package com.qat.samples.sysmgmt.util.model;

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

	public Note(String noteText)
	{
		super();
		this.noteText = noteText;
	}

	public Note(Integer id, String noteText)
	{
		super();
		this.id = id;
		this.noteText = noteText;
	}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the noteText
	 */
	public String getNoteText()
	{
		return noteText;
	}

	/**
	 * @param noteText the noteText to set
	 */
	public void setNoteText(String noteText)
	{
		this.noteText = noteText;
	}

	@Override
	public String toString()
	{
		return "Note [getId()=" + getId() + ", getNoteText()=" + getNoteText() + ", toString()=" + super.toString()
				+ "]";
	}

}