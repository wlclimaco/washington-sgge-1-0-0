package com.prosperitasglobal.sendsolv.model;

import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.cbof.model.Language;
import com.prosperitasglobal.sendsolv.callingcard.model.CallingCardInfo;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class Member carries information about a {@link Person} that uses a PGSi service to send money to a
 * {@link Recipient}.
 *
 * @author abarros
 * @version 1.0
 * @created 05-Sep-2014 11:54:49 AM
 */
@SuppressWarnings("serial")
public class Member extends TransferParticipant
{

	/** The pin number. */
	private String pinNumber;

	/** The employment info list. */
	private List<EmploymentInfo> employmentInfoList;

	/** The list of country usages. */
	private List<CountryUsage> countryUsageList;

	/** The preferred language. */
	private Language preferredLanguage;

	/** The best time to call. */
	private String bestTimeToCall;

	/** The security answer list. */
	private List<PersonSecurityAnswer> securityAnswerList;

	/** The calling card info. */
	private CallingCardInfo callingCardInfo;

	/**
	 * The Constructor.
	 */
	public Member()
	{
		super();
	}

	/**
	 * One parameter constructor.
	 *
	 * @param memberId The member id of the member.
	 */
	public Member(Integer memberId)
	{
		setId(memberId);
	}

	/**
	 * Gets the employment info list.
	 *
	 * @return the employment info list
	 */
	public List<EmploymentInfo> getEmploymentInfoList()
	{
		if (ValidationUtil.isNull(employmentInfoList))
		{
			setEmploymentInfoList(new ArrayList<EmploymentInfo>());
		}

		return employmentInfoList;
	}

	/**
	 * Sets the employment info list.
	 *
	 * @param employmentInfoList the employment info list
	 */
	public void setEmploymentInfoList(List<EmploymentInfo> employmentInfoList)
	{
		this.employmentInfoList = employmentInfoList;
	}

	/**
	 * Gets the pin number.
	 *
	 * @return the pin number
	 */
	public String getPinNumber()
	{
		return pinNumber;
	}

	/**
	 * Sets the pin number.
	 *
	 * @param pinNumber the pin number
	 */
	public void setPinNumber(String pinNumber)
	{
		this.pinNumber = pinNumber;
	}

	/**
	 * Get the country usage list.
	 *
	 * @return The country usage list.
	 */
	public List<CountryUsage> getCountryUsageList()
	{
		if (ValidationUtil.isNull(countryUsageList))
		{
			setCountryUsageList(new ArrayList<CountryUsage>());
		}

		return countryUsageList;
	}

	/**
	 * Set the country usage list.
	 *
	 * @param countryUsageList The country usage list to set.
	 */
	public void setCountryUsageList(List<CountryUsage> countryUsageList)
	{
		this.countryUsageList = countryUsageList;
	}

	/**
	 * Gets the preferred language.
	 *
	 * @return the preferred language
	 */
	public Language getPreferredLanguage()
	{
		return preferredLanguage;
	}

	/**
	 * Sets the preferred language.
	 *
	 * @param preferredLanguage the preferred language
	 */
	public void setPreferredLanguage(Language preferredLanguage)
	{
		this.preferredLanguage = preferredLanguage;
	}

	/**
	 * Gets the best time to call.
	 *
	 * @return the best time to call
	 */
	public String getBestTimeToCall()
	{
		return bestTimeToCall;
	}

	/**
	 * Sets the best time to call.
	 *
	 * @param bestTimeToCall the best time to call
	 */
	public void setBestTimeToCall(String bestTimeToCall)
	{
		this.bestTimeToCall = bestTimeToCall;
	}

	/**
	 * Gets the security answer list.
	 *
	 * @return the securityAnswerList
	 */
	public List<PersonSecurityAnswer> getSecurityAnswerList()
	{
		return securityAnswerList;
	}

	/**
	 * Sets the security answer list.
	 *
	 * @param securityAnswerList the securityAnswerList to set
	 */
	public void setSecurityAnswerList(List<PersonSecurityAnswer> securityAnswerList)
	{
		this.securityAnswerList = securityAnswerList;
	}

	/**
	 * @return the callingCardInfo
	 */
	public CallingCardInfo getCallingCardInfo()
	{
		return callingCardInfo;
	}

	/**
	 * @param callingCardInfo the callingCardInfo to set
	 */
	public void setCallingCardInfo(CallingCardInfo callingCardInfo)
	{
		this.callingCardInfo = callingCardInfo;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Member [getEmploymentInfoList()=" + getEmploymentInfoList() + ", getPinNumber()=" + getPinNumber()
				+ ", getCountryUsageList()=" + getCountryUsageList() + ", getPreferredLanguage()="
				+ getPreferredLanguage() + ", getBestTimeToCall()=" + getBestTimeToCall()
				+ ", getSecurityAnswerList()=" + getSecurityAnswerList() + ", getCallingCardInfo()="
				+ getCallingCardInfo() + "]";
	}
}