package com.prosperitasglobal.sendsolv.model;

import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.cbof.model.CodeValue;
import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.cbof.model.GenderEnum;
import com.prosperitasglobal.cbof.model.Note;
import com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum;
import com.qat.framework.model.QATModelOL;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class Person is a high level class used to carry common information about individuals in the context of the
 * SendSolv system.
 *
 * @author abarros
 * @version 1.0
 * @created 05-Sep-2014 11:16:12 AM
 */
@SuppressWarnings("serial")
public class Csosn extends QATModelOL
{

	/** The id. */
	private Integer id;

	/** The prefix. */
	private CodeValue prefix;

	/** The first name. */
	private String firstName;

	/** The middle name. */
	private String middleName;

	/** The last name. */
	private String lastName;

	/** The mothers maiden name. */
	private String mothersMaidenName;

	/** The suffix. */
	private CodeValue suffix;

	/** The gender. */
	private GenderEnum gender;

	/** The date of birth. */
	private Long dateOfBirth;

	/** The pep status. */
	private PoliticallyExposedPersonEnum pepStatus = PoliticallyExposedPersonEnum.UNKNOWN;

	/** The person type. */
	private PersonTypeEnum personType = PersonTypeEnum.OTHER;

	/** The person status. */
	private StatusEnum personStatus = StatusEnum.SETUP;

	/** The SDN status. */
	private SDNStatusEnum sdnStatus = SDNStatusEnum.UNKNOWN;

	/** The contact list. */
	private List<Contact> contactList;

	/** The name list. */
	private List<PersonName> nameList;

	/** The note list. */
	private List<Note> noteList;

	/** The risk. */
	private Risk risk;

	/** The document list. */
	private List<Document> documentList;

	/** The participant id. */
	private String participantId;

	/** The social security number. */
	private String socialSecurityNumber;

	/** The suspicious activity id list. */
	private List<Integer> suspiciousActivityIdList;

	/**
	 * The Constructor.
	 */
	public Csosn()
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
	 * Gets the date of birth.
	 *
	 * @return the date of birth
	 */
	public Long getDateOfBirth()
	{
		return dateOfBirth;
	}

	/**
	 * Sets the date of birth.
	 *
	 * @param dateOfBirth the date of birth
	 */
	public void setDateOfBirth(Long dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
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
	 * Gets the name list.
	 *
	 * @return the name list
	 */
	public List<PersonName> getNameList()
	{
		if (ValidationUtil.isNull(nameList))
		{
			setNameList(new ArrayList<PersonName>());
		}

		return nameList;
	}

	/**
	 * Sets the name list.
	 *
	 * @param nameList the name list
	 */
	public void setNameList(List<PersonName> nameList)
	{
		this.nameList = nameList;
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
	 * Sets the participant id.
	 *
	 * @param participantId the participant id
	 */
	public void setParticipantId(String participantId)
	{
		this.participantId = participantId;
	}

	/**
	 * Gets the pep status.
	 *
	 * @return the pep status
	 */
	public PoliticallyExposedPersonEnum getPepStatus()
	{
		return pepStatus;
	}

	/**
	 * Sets the pep status.
	 *
	 * @param pepStatus the pep status
	 */
	public void setPepStatus(PoliticallyExposedPersonEnum pepStatus)
	{
		this.pepStatus = pepStatus;
	}

	/**
	 * Gets the pep status value.
	 *
	 * @return the pep status value
	 */
	public Integer getPepStatusValue()
	{
		if (pepStatus == null)
		{
			return null;
		}

		return pepStatus.getValue();
	}

	/**
	 * Sets the status value.
	 *
	 * @param statusValue the status value
	 */
	public void setPepStatusValue(Integer statusValue)
	{
		pepStatus = PoliticallyExposedPersonEnum.enumForValue(statusValue);
	}

	/**
	 * Gets the person status.
	 *
	 * @return the person status
	 */
	public StatusEnum getPersonStatus()
	{
		return personStatus;
	}

	/**
	 * Sets the person status.
	 *
	 * @param personStatus the person status
	 */
	public void setPersonStatus(StatusEnum personStatus)
	{
		this.personStatus = personStatus;
	}

	/**
	 * Gets the person status value.
	 *
	 * @return the person status value
	 */
	public Integer getPersonStatusValue()
	{
		if (personStatus == null)
		{
			return null;
		}

		return personStatus.getValue();
	}

	/**
	 * Sets the status value.
	 *
	 * @param statusValue the status value
	 */
	public void setPersonStatusValue(Integer statusValue)
	{
		personStatus = StatusEnum.enumForValue(statusValue);
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
	 * Gets the sdn status value.
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
	 * Sets the sdn status value.
	 *
	 * @param sdnStatusValue the SDN status value
	 */
	public void setSDNStatusValue(Integer sdnStatusValue)
	{
		sdnStatus = SDNStatusEnum.enumForValue(sdnStatusValue);
	}

	/**
	 * Gets the person type.
	 *
	 * @return the person type
	 */
	public PersonTypeEnum getPersonType()
	{
		return personType;
	}

	/**
	 * Sets the person type.
	 *
	 * @param personType the person type
	 */
	public void setPersonType(PersonTypeEnum personType)
	{
		this.personType = personType;
	}

	/**
	 * Gets the person type value.
	 *
	 * @return the person type value
	 */
	public Integer getPersonTypeValue()
	{
		if (personType == null)
		{
			return null;
		}

		return personType.getValue();
	}

	/**
	 * Sets the status value.
	 *
	 * @param statusValue the status value
	 */
	public void setPersonTypeValue(Integer statusValue)
	{
		personType = PersonTypeEnum.enumForValue(statusValue);
	}

	/**
	 * Gets the prefix.
	 *
	 * @return the prefix
	 */
	public CodeValue getPrefix()
	{
		return prefix;
	}

	/**
	 * Sets the prefix.
	 *
	 * @param prefix the prefix
	 */
	public void setPrefix(CodeValue prefix)
	{
		this.prefix = prefix;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the first name
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * Gets the middle name.
	 *
	 * @return the middle name
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * Sets the middle name.
	 *
	 * @param middleName the middle name
	 */
	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the last name
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * Gets the suffix.
	 *
	 * @return the suffix
	 */
	public CodeValue getSuffix()
	{
		return suffix;
	}

	/**
	 * Sets the suffix.
	 *
	 * @param suffix the suffix
	 */
	public void setSuffix(CodeValue suffix)
	{
		this.suffix = suffix;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public GenderEnum getGender()
	{
		return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the gender
	 */
	public void setGender(GenderEnum gender)
	{
		this.gender = gender;
	}

	/**
	 * Gets the gender value.
	 *
	 * @return the gender value
	 */
	public Integer getGenderValue()
	{
		if (gender == null)
		{
			return null;
		}

		return gender.getValue();
	}

	/**
	 * Sets the gender value.
	 *
	 * @param genderValue the gender value
	 */
	public void setGenderValue(Integer genderValue)
	{
		gender = GenderEnum.enumForValue(genderValue);
	}

	/**
	 * Gets the document list.
	 *
	 * @return the document list
	 */
	public List<Document> getDocumentList()
	{
		if (ValidationUtil.isNull(documentList))
		{
			setDocumentList(new ArrayList<Document>());
		}

		return documentList;
	}

	/**
	 * Gets the participant id.
	 *
	 * @return the participant id
	 */
	public String getParticipantId()
	{
		return participantId;
	}

	/**
	 * Gets the social security number.
	 *
	 * @return The social security number.
	 */
	public String getSocialSecurityNumber()
	{
		return socialSecurityNumber;
	}

	/**
	 * Sets the social security number.
	 *
	 * @param socialSecurityNumber The social security number to set.
	 */
	public void setSocialSecurityNumber(String socialSecurityNumber)
	{
		this.socialSecurityNumber = socialSecurityNumber;
	}

	/**
	 * Sets the document list.
	 *
	 * @param documentList the document list
	 */
	public void setDocumentList(List<Document> documentList)
	{
		this.documentList = documentList;
	}

	/**
	 * Get the mothers maiden name.
	 *
	 * @return The name.
	 */
	public String getMothersMaidenName()
	{
		return mothersMaidenName;
	}

	/**
	 * Set the mothers maiden name
	 *
	 * @param mothersMaidenName The name to set.
	 */
	public void setMothersMaidenName(String mothersMaidenName)
	{
		this.mothersMaidenName = mothersMaidenName;
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
		return "Person [getId()=" + getId() + ", getDateOfBirth()=" + getDateOfBirth() + ", getContactList()="
				+ getContactList() + ", getNameList()=" + getNameList() + ", getNoteList()=" + getNoteList()
				+ ", getRisk()=" + getRisk() + ", getPepStatus()=" + getPepStatus() + ", getPepStatusValue()="
				+ getPepStatusValue() + ", getPersonStatus()=" + getPersonStatus() + ", getPersonStatusValue()="
				+ getPersonStatusValue() + ", getSDNStatus()=" + getSDNStatus() + ", getSDNStatusValue()="
				+ getSDNStatusValue() + ", getPersonType()=" + getPersonType() + ", getPersonTypeValue()="
				+ getPersonTypeValue() + ", getPrefix()=" + getPrefix() + ", getFirstName()=" + getFirstName()
				+ ", getMiddleName()=" + getMiddleName() + ", getLastName()=" + getLastName() + ", getSuffix()="
				+ getSuffix() + ", getGender()=" + getGender() + ", getGenderValue()=" + getGenderValue()
				+ ", getDocumentList()=" + getDocumentList() + ", getParticipantId()=" + getParticipantId()
				+ ", getSocialSecurityNumber()=" + getSocialSecurityNumber() + ", getMothersMaidenName()="
				+ getMothersMaidenName() + ", getSuspiciousActivityIdList()=" + getSuspiciousActivityIdList() + "]";
	}

}