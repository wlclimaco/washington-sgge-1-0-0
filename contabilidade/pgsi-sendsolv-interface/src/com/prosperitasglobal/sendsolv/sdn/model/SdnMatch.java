package com.prosperitasglobal.sendsolv.sdn.model;

import java.util.List;

import com.qat.framework.model.QATModel;

/**
 * The Class SdnMatch.
 */
@SuppressWarnings("serial")
public class SdnMatch extends QATModel
{
	/** The match type. */
	private SdnMatchTypeEnum matchType;

	/** The person id. */
	private Integer parentKey;

	/** The first. */
	private String firstName;

	/** The middle. */
	private String middleName;

	/** The last. */
	private String lastName;

	/** The last. */
	private String mothersMaidenName;

	private List<String> otherNamesList;

	/** The city. */
	private String cityName;

	/** The country. */
	private String addressCountryName;

	/** The country. */
	private String citizenshipCountryName;

	/** The year. */
	private String yearOfBirth;

	/** The id. */
	private List<String> documentIdList;

	/**
	 * Gets the match type.
	 *
	 * @return the match type
	 */
	public SdnMatchTypeEnum getMatchType()
	{
		return matchType;
	}

	/**
	 * Sets the match type.
	 *
	 * @param matchType the new match type
	 */
	public void setMatchType(SdnMatchTypeEnum matchType)
	{
		this.matchType = matchType;
	}

	/**
	 * Gets the match type value.
	 *
	 * @return the match type value
	 */
	public Integer getMatchTypeValue()
	{
		if (getMatchType() == null)
		{
			return null;
		}
		return getMatchType().getValue();
	}

	/**
	 * Sets the match type value.
	 *
	 * @param matchTypeValue the new match type value
	 */
	public void setMatchTypeValue(Integer matchTypeValue)
	{
		setMatchType((SdnMatchTypeEnum.enumForValue(matchTypeValue)));
	}

	/**
	 * Gets the person id.
	 *
	 * @return the person id
	 */
	public Integer getParentKey()
	{
		return parentKey;
	}

	/**
	 * Sets the person id.
	 *
	 * @param parentKey the new person id
	 */
	public void setParentKey(Integer parentKey)
	{
		this.parentKey = parentKey;
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
	 * @param firstName the new first name
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
	 * @param middleName the new middle name
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
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * Gets the city name.
	 *
	 * @return the city name
	 */
	public String getCityName()
	{
		return cityName;
	}

	/**
	 * Sets the city name.
	 *
	 * @param cityName the new city name
	 */
	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}

	/**
	 * Gets the country name.
	 *
	 * @return the country name
	 */
	public String getAddressCountryName()
	{
		return addressCountryName;
	}

	/**
	 * Sets the country name.
	 *
	 * @param countryName the new country name
	 */
	public void setAddressCountryName(String countryName)
	{
		addressCountryName = countryName;
	}

	public String getCitizenshipCountryName()
	{
		return citizenshipCountryName;
	}

	public void setCitizenshipCountryName(String citizenshipCountryName)
	{
		this.citizenshipCountryName = citizenshipCountryName;
	}

	/**
	 * Gets the year of birth.
	 *
	 * @return the year of birth
	 */
	public String getYearOfBirth()
	{
		return yearOfBirth;
	}

	/**
	 * Sets the year of birth.
	 *
	 * @param yearOfBirth the new year of birth
	 */
	public void setYearOfBirth(String yearOfBirth)
	{
		this.yearOfBirth = yearOfBirth;
	}

	/**
	 * Gets the document id.
	 *
	 * @return the document id
	 */
	public List<String> getDocumentIdList()
	{
		return documentIdList;
	}

	/**
	 * Sets the document id.
	 *
	 * @param documentId the new document id
	 */
	public void setDocumentIdList(List<String> documentIdList)
	{
		this.documentIdList = documentIdList;
	}

	public List<String> getOtherNamesList()
	{
		return otherNamesList;
	}

	public void setOtherNamesList(List<String> otherNamesList)
	{
		this.otherNamesList = otherNamesList;
	}

	public String getMothersMaidenName()
	{
		return mothersMaidenName;
	}

	public void setMothersMaidenName(String mothersMaidenName)
	{
		this.mothersMaidenName = mothersMaidenName;
	}

	@Override
	public String toString()
	{
		return "SdnMatch [getMatchType()=" + getMatchType() + ", getMatchTypeValue()=" + getMatchTypeValue()
				+ ", getParentKey()=" + getParentKey() + ", getFirstName()="
				+ getFirstName() + ", getMiddleName()=" + getMiddleName() + ", getLastName()=" + getLastName()
				+ ", getCityName()=" + getCityName() + ", getAddressCountryName()=" + getAddressCountryName()
				+ ", getCitizenshipCountryName()=" + getCitizenshipCountryName() + ", getYearOfBirth()="
				+ getYearOfBirth() + ", getDocumentIdList()=" + getDocumentIdList() + ", getOtherNamesList()="
				+ getOtherNamesList() + ", getMothersMaidenName()=" + getMothersMaidenName() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()="
				+ getCreateDateUTC() + ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()="
				+ getModifyDateUTC() + "]";
	}
}
