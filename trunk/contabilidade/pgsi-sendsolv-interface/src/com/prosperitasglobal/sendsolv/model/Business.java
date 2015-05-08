package com.prosperitasglobal.sendsolv.model;

import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.CodeValue;
import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.cbof.model.Note;
import com.prosperitasglobal.cbof.model.Range;
import com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum;
import com.qat.framework.model.QATModelOL;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class Business. This represents a business entity that can be a PGSi customer (either {@link Organization} or
 * {@link Location})
 */
@SuppressWarnings("serial")
public class Business extends QATModelOL
{
	/** Attributes. */
	private Integer id;

	/** The unique key of the business. */
	private String key;

	/** The name of the business. */
	private String name;

	/** The type of business. */
	private BusinessTypeEnum businessType = BusinessTypeEnum.UNKNOWN;

	/** The employer identification number. */
	private String employerIdentificationNumber;

	/** The standard industrial classification code. */
	private CodeValue standardIndustrialClassificationCode;

	/** The North American Industry Classification System Code. */
	private CodeValue northAmericanIndustryClassificationSystemCode;

	/** The country of the business. */
	private Country country;

	/** The number of employees the business has working for it. */
	private Range numberOfEmployees;

	/** The number of migrant workers the business has working for it. */
	private Range numberOfMigrantWorkers;

	/** The risk of the business. */
	private Risk risk;

	/** The status of the business. */
	private StatusEnum status = StatusEnum.SETUP;

	/** The SDN status of the business. */
	private SDNStatusEnum sdnStatus = SDNStatusEnum.UNKNOWN;

	/** The list of notes the business might contain. */
	private List<Note> noteList;

	/** The list of contacts for the business. */
	private List<Contact> contactList;

	/** The suspicious activity id list. */
	private List<Integer> suspiciousActivityIdList;

	/**
	 * The Constructor.
	 */
	public Business()
	{
		// initialize Risk
		setRisk(new Risk());
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
	 * Gets the key.
	 *
	 * @return the key.
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * Sets the key.
	 *
	 * @param key the key
	 */
	public void setKey(String key)
	{
		this.key = key;
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
	 * @param name the name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Gets the employer identification number.
	 *
	 * @return the employer identification number
	 */
	public String getEmployerIdentificationNumber()
	{
		return employerIdentificationNumber;
	}

	/**
	 * Sets the employer identification number.
	 *
	 * @param eIN the employer identification number
	 */
	public void setEmployerIdentificationNumber(String eIN)
	{
		employerIdentificationNumber = eIN;
	}

	/**
	 * Gets the standard industrial classification code.
	 *
	 * @return the standard industrial classification code
	 */
	public CodeValue getStandardIndustrialClassificationCode()
	{
		return standardIndustrialClassificationCode;
	}

	/**
	 * Sets the standard industrial classification code.
	 *
	 * @param sICCode the standard industrial classification code
	 */
	public void setStandardIndustrialClassificationCode(CodeValue sICCode)
	{
		standardIndustrialClassificationCode = sICCode;
	}

	/**
	 * Gets the north american industry classification system code.
	 *
	 * @return the north american industry classification system code
	 */
	public CodeValue getNorthAmericanIndustryClassificationSystemCode()
	{
		return northAmericanIndustryClassificationSystemCode;
	}

	/**
	 * Sets the north american industry classification system code.
	 *
	 * @param nAICSCode the north american industry classification system code
	 */
	public void setNorthAmericanIndustryClassificationSystemCode(CodeValue nAICSCode)
	{
		northAmericanIndustryClassificationSystemCode = nAICSCode;
	}

	/**
	 * Gets the number of employees.
	 *
	 * @return the number of employees
	 */
	public Range getNumberOfEmployees()
	{
		return numberOfEmployees;
	}

	/**
	 * Sets the number of employees.
	 *
	 * @param numberOfEmployees the number of employees
	 */
	public void setNumberOfEmployees(Range numberOfEmployees)
	{
		this.numberOfEmployees = numberOfEmployees;
	}

	/**
	 * Gets the number of migrant workers.
	 *
	 * @return the number of migrant workers
	 */
	public Range getNumberOfMigrantWorkers()
	{
		return numberOfMigrantWorkers;
	}

	/**
	 * Sets the number of migrant workers.
	 *
	 * @param numberOfMigrantWorkers the number of migrant workers
	 */
	public void setNumberOfMigrantWorkers(Range numberOfMigrantWorkers)
	{
		this.numberOfMigrantWorkers = numberOfMigrantWorkers;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public StatusEnum getStatus()
	{
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the status
	 */
	public void setStatus(StatusEnum status)
	{
		this.status = status;
	}

	/**
	 * Gets the status value.
	 *
	 * @return the status value
	 */
	public Integer getStatusValue()
	{
		if (status == null)
		{
			return null;
		}

		return status.getValue();
	}

	/**
	 * Sets the status value.
	 *
	 * @param statusValue the status value
	 */
	public void setStatusValue(Integer statusValue)
	{
		status = StatusEnum.enumForValue(statusValue);
	}

	/**
	 * Gets the note list.
	 *
	 * @return the note list
	 */
	public List<Note> getNoteList()
	{
		if (ValidationUtil.isNull(noteList))
		{
			setNoteList(new ArrayList<Note>());
		}

		return noteList;
	}

	/**
	 * Sets the note list.
	 *
	 * @param noteList the note list
	 */
	public void setNoteList(List<Note> noteList)
	{
		this.noteList = noteList;
	}

	/**
	 * Gets the contact list.
	 *
	 * @return the contact list
	 */
	public List<Contact> getContactList()
	{
		if (ValidationUtil.isNull(contactList))
		{
			setContactList(new ArrayList<Contact>());
		}

		return contactList;
	}

	/**
	 * Sets the contact list.
	 *
	 * @param contactList the contact list
	 */
	public void setContactList(List<Contact> contactList)
	{
		this.contactList = contactList;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public Country getCountry()
	{
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the country
	 */
	public void setCountry(Country country)
	{
		this.country = country;
	}

	/**
	 * Gets the SDN status.
	 *
	 * @return the SDN status
	 */
	public SDNStatusEnum getSDNStatus()
	{
		return sdnStatus;
	}

	/**
	 * Sets the SDN status.
	 *
	 * @param sdnStatus the new SDN status
	 */
	public void setSDNStatus(SDNStatusEnum sdnStatus)
	{
		this.sdnStatus = sdnStatus;
	}

	/**
	 * Gets the SDN status value.
	 *
	 * @return the SDN status value
	 */
	public Integer getSDNStatusValue()
	{
		if (sdnStatus == null)
		{
			return null;
		}

		return sdnStatus.getValue();
	}

	/**
	 * Sets the SDN status value.
	 *
	 * @param sdnStatusValue the new SDN status value
	 */
	public void setSDNStatusValue(Integer sdnStatusValue)
	{
		sdnStatus = SDNStatusEnum.enumForValue(sdnStatusValue);
	}

	/**
	 * Gets the business type.
	 *
	 * @return the business type
	 */
	public BusinessTypeEnum getBusinessType()
	{
		return businessType;
	}

	/**
	 * Sets the business type.
	 *
	 * @param businessType the business type
	 */
	public void setBusinessType(BusinessTypeEnum businessType)
	{
		this.businessType = businessType;
	}

	/**
	 * Gets the business type value.
	 *
	 * @return the business type value
	 */
	public Integer getBusinessTypeValue()
	{
		if (businessType == null)
		{
			return null;
		}

		return businessType.getValue();
	}

	/**
	 * Methods that follow the naming pattern set.....Value(argValue) provide convenience for assigning the primitive
	 * value of an enum. For example, database mapping of an database column to an enum could make use of this method.
	 *
	 * @param businessValue the business type value
	 */
	public void setBusinessTypeValue(Integer businessValue)
	{
		businessType = BusinessTypeEnum.enumForValue(businessValue);
	}

	/**
	 * Gets the risk.
	 *
	 * @return the risk
	 */
	public Risk getRisk()
	{
		return risk;
	}

	/**
	 * Sets the risk.
	 *
	 * @param risk the risk
	 */
	public void setRisk(Risk risk)
	{
		this.risk = risk;
	}

	/**
	 * @return the suspiciousActivityIdList
	 */
	public List<Integer> getSuspiciousActivityIdList()
	{
		return suspiciousActivityIdList;
	}

	/**
	 * @param suspiciousActivityIdList the suspiciousActivityIdList to set
	 */
	public void setSuspiciousActivityIdList(List<Integer> suspiciousActivityIdList)
	{
		this.suspiciousActivityIdList = suspiciousActivityIdList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Business [getId()=" + getId() + ", getKey()=" + getKey() + ", getName()=" + getName()
				+ ", getEmployerIdentificationNumber()=" + getEmployerIdentificationNumber()
				+ ", getStandardIndustrialClassificationCode()=" + getStandardIndustrialClassificationCode()
				+ ", getNorthAmericanIndustryClassificationSystemCode()="
				+ getNorthAmericanIndustryClassificationSystemCode() + ", getNumberOfEmployees()="
				+ getNumberOfEmployees() + ", getNumberOfMigrantWorkers()=" + getNumberOfMigrantWorkers()
				+ ", getStatus()=" + getStatus() + ", getStatusValue()=" + getStatusValue() + ", getNoteList()="
				+ getNoteList() + ", getContactList()=" + getContactList() + ", getCountry()=" + getCountry()
				+ ", getSDNStatus()=" + getSDNStatus() + ", getSDNStatusValue()=" + getSDNStatusValue()
				+ ", getBusinessType()=" + getBusinessType() + ", getBusinessTypeValue()=" + getBusinessTypeValue()
				+ ", getRisk()=" + getRisk() + ", getSuspiciousActivityIdList()=" + getSuspiciousActivityIdList() + "]";
	}

}