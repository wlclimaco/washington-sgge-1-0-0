package com.prosperitasglobal.sendsolv.model;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.cbof.model.FilingStatusEnum;
import com.prosperitasglobal.cbof.model.Note;
import com.prosperitasglobal.cbof.model.StateProvinceRegion;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class Convenio extends ModelCosmeDamiao
{

	/** Attributes. */
	private Integer id;

	private String nome;

	private Long dataini;

	private Long dataFin;

	private Double porcentagem;

	private Double valor;

	private List<Note> notes;

	private List<CondPag> listCondPag;

	private List<TipoPag> listTipoPag;

	/**
	 * The Constructor.
	 */
	public Convenio()
	{

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
	 * Gets the document type.
	 *
	 * @return the document type
	 */
	public DocumentType getDocumentType()
	{
		return documentType;
	}

	/**
	 * Sets the document type.
	 *
	 * @param documentType the document type
	 */
	public void setDocumentType(DocumentType documentType)
	{
		this.documentType = documentType;
	}

	/**
	 * Gets the keyword text.
	 *
	 * @return the keyword text
	 */
	public String getKeywordText()
	{
		return keywordText;
	}

	/**
	 * Sets the keyword text.
	 *
	 * @param keywordText the keyword text
	 */
	public void setKeywordText(String keywordText)
	{
		this.keywordText = keywordText;
	}

	/**
	 * Gets the is filed.
	 *
	 * @return the checks if is filed
	 */
	public Boolean getIsActionRequired()
	{
		return isActionRequired;
	}

	/**
	 * Sets the is filed.
	 *
	 * @param isActionRequired the checks if is action required
	 */
	public void setIsActionRequired(Boolean isActionRequired)
	{
		this.isActionRequired = isActionRequired;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public FilingStatusEnum getFilingStatus()
	{
		return filingStatus;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the status
	 */
	public void setFilingStatus(FilingStatusEnum status)
	{
		filingStatus = status;
	}

	/**
	 * Gets the status value.
	 *
	 * @return the status value
	 */
	public Integer getFilingStatusValue()
	{
		if (filingStatus == null)
		{
			return null;
		}

		return filingStatus.getValue();
	}

	/**
	 * Sets the status value.
	 *
	 * @param statusValue the status value
	 */
	public void setFilingStatusValue(Integer statusValue)
	{
		filingStatus = FilingStatusEnum.enumForValue(statusValue);
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
	 * Gets the parent key type.
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
	 * Gets the parent key value.
	 *
	 * @return the parent key value
	 */
	public Integer getParentKeyValue()
	{
		if (parentKeyType == null)
		{
			return null;
		}

		return parentKeyType.getValue();
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
	 * Gets the expiration date. If this date has a time portion, it will be removed. This attribute is only a date.
	 *
	 * @return the expiration date
	 */
	public Long getExpirationDate()
	{
		return expirationDate;
	}

	/**
	 * Sets the expiration date.
	 *
	 * @param expirationDate the expiration date
	 */
	public void setExpirationDate(Long expirationDate)
	{
		this.expirationDate = expirationDate;
	}

	/**
	 * Gets the issue country.
	 *
	 * @return the issue country
	 */
	public Country getIssueCountry()
	{
		return issueCountry;
	}

	/**
	 * Sets the issue country.
	 *
	 * @param issueCountry the issue country
	 */
	public void setIssueCountry(Country issueCountry)
	{
		this.issueCountry = issueCountry;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * Gets the issue state province region.
	 *
	 * @return the issue state province region
	 */
	public StateProvinceRegion getIssueStateProvinceRegion()
	{
		return issueStateProvinceRegion;
	}

	/**
	 * Sets the issue state province region.
	 *
	 * @param issueStateProvinceRegion the issue state province region
	 */
	public void setIssueStateProvinceRegion(StateProvinceRegion issueStateProvinceRegion)
	{
		this.issueStateProvinceRegion = issueStateProvinceRegion;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Document [getId()=" + getId() + ", getDocumentType()=" + getDocumentType() + ", getKeywordText()="
				+ getKeywordText() + ", getIsActionRequired()=" + getIsActionRequired() + ", getFilingStatus()="
				+ getFilingStatus() + ", getFilingStatusValue()=" + getFilingStatusValue() + ", getNoteText()="
				+ getNoteText() + ", getParentId()=" + getParentId() + ", getParentKeyType()=" + getParentKeyType()
				+ ", getParentKeyValue()=" + getParentKeyValue() + ", getExpirationDate()=" + getExpirationDate()
				+ ", getIssueCountry()=" + getIssueCountry() + ", getValue()=" + getValue()
				+ ", getIssueStateProvinceRegion()=" + getIssueStateProvinceRegion() + ", toString()="
				+ super.toString() + "]";
	}

}