package com.prosperitasglobal.sendsolv.integration.twilio.model;

import java.util.List;

import com.qat.framework.model.QATModel;

/**
 * The Class CallRecord stores details about a {@link Member} call to the IVR
 */
@SuppressWarnings("serial")
public class CallRecord extends QATModel
{

	/** The id. */
	private Integer id;

	/** The call sid. */
	private String callSid;

	/** The parent person id. */
	private Integer parentPersonId;

	/** The originating phone. */
	private String originatingPhone;

	/** The id. */
	private Integer currentIvrOption;

	/** The start date time utc. */
	private Long startDateTimeUTC;

	/** The stop date time utc. */
	private Long stopDateTimeUTC;

	private Long currentSearchDate;

	/** The call duration seconds. */
	private Integer callDurationSeconds;

	/** The language. */
	private String language;

	/** The call record context list. */
	private List<CallRecordContext> callRecordContextList;

	/**
	 * Gets the call sid.
	 *
	 * @return the call sid
	 */
	public String getCallSid()
	{
		return callSid;
	}

	/**
	 * Sets the call sid.
	 *
	 * @param callSid the new call sid
	 */
	public void setCallSid(String callSid)
	{
		this.callSid = callSid;
	}

	/**
	 * Gets the originating phone.
	 *
	 * @return the originating phone
	 */
	public String getOriginatingPhone()
	{
		return originatingPhone;
	}

	/**
	 * Sets the originating phone.
	 *
	 * @param originatingPhone the new originating phone
	 */
	public void setOriginatingPhone(String originatingPhone)
	{
		this.originatingPhone = originatingPhone;
	}

	/**
	 * Gets the call duration seconds.
	 *
	 * @return the call duration seconds
	 */
	public Integer getCallDurationSeconds()
	{
		return callDurationSeconds;
	}

	/**
	 * Sets the call duration seconds.
	 *
	 * @param callDurationSeconds the new call duration seconds
	 */
	public void setCallDurationSeconds(Integer callDurationSeconds)
	{
		this.callDurationSeconds = callDurationSeconds;
	}

	/**
	 * Gets the language.
	 *
	 * @return the language
	 */
	public String getLanguage()
	{
		return language;
	}

	/**
	 * Sets the language.
	 *
	 * @param language the new language
	 */
	public void setLanguage(String language)
	{
		this.language = language;
	}

	/**
	 * Gets the parent person id.
	 *
	 * @return the parent person id
	 */
	public Integer getParentPersonId()
	{
		return parentPersonId;
	}

	/**
	 * Sets the parent person id.
	 *
	 * @param parentPersonId the new parent person id
	 */
	public void setParentPersonId(Integer parentPersonId)
	{
		this.parentPersonId = parentPersonId;
	}

	/**
	 * Gets the start date time utc.
	 *
	 * @return the start date time utc
	 */
	public Long getStartDateTimeUTC()
	{
		return startDateTimeUTC;
	}

	/**
	 * Sets the start date time utc.
	 *
	 * @param startDateTimeUTC the new start date time utc
	 */
	public void setStartDateTimeUTC(Long startDateTimeUTC)
	{
		this.startDateTimeUTC = startDateTimeUTC;
	}

	/**
	 * Gets the stop date time utc.
	 *
	 * @return the stop date time utc
	 */
	public Long getStopDateTimeUTC()
	{
		return stopDateTimeUTC;
	}

	/**
	 * Sets the stop date time utc.
	 *
	 * @param stopDateTimeUTC the new stop date time utc
	 */
	public void setStopDateTimeUTC(Long stopDateTimeUTC)
	{
		this.stopDateTimeUTC = stopDateTimeUTC;
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
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the call record context list.
	 *
	 * @return the call record context list
	 */
	public List<CallRecordContext> getCallRecordContextList()
	{
		return callRecordContextList;
	}

	/**
	 * Sets the call record context list.
	 *
	 * @param callRecordContextList the new call record context list
	 */
	public void setCallRecordContextList(List<CallRecordContext> callRecordContextList)
	{
		this.callRecordContextList = callRecordContextList;
	}

	public Long getCurrentSearchDate()
	{
		return currentSearchDate;
	}

	public void setCurrentSearchDate(Long currentSearchDate)
	{
		this.currentSearchDate = currentSearchDate;
	}

	public Integer getCurrentIvrOption()
	{
		return currentIvrOption;
	}

	public void setCurrentIvrOption(Integer currentIvrOption)
	{
		this.currentIvrOption = currentIvrOption;
	}

	@Override
	public String toString()
	{
		return "CallRecord [getCallSid()=" + getCallSid() + ", getOriginatingPhone()=" + getOriginatingPhone()
				+ ", getCallDurationSeconds()=" + getCallDurationSeconds() + ", getLanguage()=" + getLanguage()
				+ ", getParentPersonId()=" + getParentPersonId() + ", getStartDateTimeUTC()=" + getStartDateTimeUTC()
				+ ", getStopDateTimeUTC()=" + getStopDateTimeUTC() + ", getId()=" + getId()
				+ ", getCallRecordContextList()=" + getCallRecordContextList() + ", getCurrentSearchDate()="
				+ getCurrentSearchDate() + ", getCurrentIvrOption()=" + getCurrentIvrOption() + ", toString()="
				+ super.toString() + "]";
	}
}
