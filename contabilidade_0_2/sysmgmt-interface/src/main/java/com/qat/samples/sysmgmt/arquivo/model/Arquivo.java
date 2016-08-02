package com.qat.samples.sysmgmt.arquivo.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class Arquivo extends ModelCosmeDamiao
{

	/** Attributes. */
	private Integer id;

	/** The parent key. */
	private Integer parentKey;

	/**
	 * The parent key type. *
	 * 
	 * /** The keyword text.
	 */
	private String keywordText;

	/** The is action required. */
	private Boolean isActionRequired;

	/** The note text. */
	private String noteText;

	/** The expiration date. */
	private Long expirationDate;

	/** The value. */
	private String value;

	/**
	 * The Constructor.
	 */
	public Arquivo()
	{

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentKey() {
		return parentKey;
	}

	public void setParentKey(Integer parentKey) {
		this.parentKey = parentKey;
	}

	public String getKeywordText() {
		return keywordText;
	}

	public void setKeywordText(String keywordText) {
		this.keywordText = keywordText;
	}

	public Boolean getIsActionRequired() {
		return isActionRequired;
	}

	public void setIsActionRequired(Boolean isActionRequired) {
		this.isActionRequired = isActionRequired;
	}

	public String getNoteText() {
		return noteText;
	}

	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}

	public Long getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Long expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Arquivo [getId()=" + getId() + ", getParentKey()=" + getParentKey() + ", getKeywordText()="
				+ getKeywordText() + ", getIsActionRequired()=" + getIsActionRequired() + ", getNoteText()="
				+ getNoteText() + ", getExpirationDate()=" + getExpirationDate() + ", getValue()=" + getValue()
				+ ", toString()=" + super.toString() + "]";
	}

}