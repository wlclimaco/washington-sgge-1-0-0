package com.sensus.common.scheduler.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.SensusModel;

/**
 * * The Model Object Repeat.
 * 
 * @author QAT Brazil
 */
@SuppressWarnings("serial")
public class Frequency extends SensusModel
{

	/** The id. */
	private Integer id;

	/** The frequency enum. */
	private FrequencyTypeEnum frequencyTypeEnum;

	/** The time to repeat. */
	private Integer timeToRepeat;

	/** The start on date. */
	private Date startOnDate;

	/** The never ends. */
	private Boolean neverEnds;

	/** The dayOfMonth. */
	private Boolean dayOfMonth;

	/** The day of Week. */
	private Boolean dayOfWeek;

	/** The day of Week. */
	private List<Date> repeatsDate;

	/** The ends after occurrences. */
	private Integer endsAfterOccurrences;

	/** The end date. */
	private Date endDate;

	/** The days of weeks. */
	private List<DaysOfWeekEnum> daysOfWeeks;

	/** The next execution. */
	private Date nextExecution;

	/** The execute occurrences. */
	private Integer executedOccurrences;

	/**
	 * Instantiates a new repeat.
	 */
	public Frequency()
	{

	}

	/**
	 * Instantiates a new repeat.
	 * 
	 * @param frequencyTypeEnumValue the frequency type enum value
	 * @param valueTimeToRepeat the time to repeat
	 * @param startOnDateRepeat the start on date
	 * @param neverEnd the never ends
	 * @param endAfterOccurrences the end after occurrences
	 * @param endDateRepeat the end date
	 * @param listDaysOfWeeks the days of weeks
	 */
	public Frequency(FrequencyTypeEnum frequencyTypeEnumValue, Integer valueTimeToRepeat, Date startOnDateRepeat,
			Boolean neverEnd, Integer endAfterOccurrences, Date endDateRepeat,
			List<Integer> listDaysOfWeeks)
	{
		setFrequencyTypeEnum(frequencyTypeEnumValue);
		setTimeToRepeat(valueTimeToRepeat);
		setStartOnDate(startOnDateRepeat);
		setNeverEnds(neverEnd);
		setEndsAfterOccurrences(endAfterOccurrences);
		setEndDate(endDateRepeat);
		setDaysOfWeeks(listDaysOfWeeks);
	}

	/**
	 * Gets the frequency enum.
	 * 
	 * @return the frequency enum
	 */
	public FrequencyTypeEnum getFrequencyTypeEnum()
	{
		return frequencyTypeEnum;
	}

	/**
	 * Sets the frequency enum.
	 * 
	 * @param frequencyTypeEnum the new frequency type enum
	 */
	public void setFrequencyTypeEnum(FrequencyTypeEnum frequencyTypeEnum)
	{
		this.frequencyTypeEnum = frequencyTypeEnum;
	}

	/**
	 * Sets the frequency enum value.
	 * 
	 * @param frequenceValue the new frequency enum value
	 */
	public void setFrequencyEnumValue(String frequenceValue)
	{

		frequencyTypeEnum = FrequencyTypeEnum.enumForValue(frequenceValue);

	}

	/**
	 * Gets the time to repeat.
	 * 
	 * @return the time to repeat
	 */
	public Integer getTimeToRepeat()
	{
		return timeToRepeat;
	}

	/**
	 * Sets the time to repeat.
	 * 
	 * @param timeToRepeat the new time to repeat
	 */
	public void setTimeToRepeat(Integer timeToRepeat)
	{
		this.timeToRepeat = timeToRepeat;
	}

	/**
	 * Gets the start on date.
	 * 
	 * @return the start on date
	 */
	public Date getStartOnDate()
	{
		return startOnDate;
	}

	/**
	 * Sets the start on date.
	 * 
	 * @param startOnDate the new start on date
	 */
	public void setStartOnDate(Date startOnDate)
	{
		this.startOnDate = startOnDate;
	}

	/**
	 * Gets the never ends.
	 * 
	 * @return the never ends
	 */
	public Boolean getNeverEnds()
	{
		return neverEnds;
	}

	/**
	 * Sets the never ends.
	 * 
	 * @param neverEnds the new never ends
	 */
	public void setNeverEnds(Boolean neverEnds)
	{
		this.neverEnds = neverEnds;
	}

	/**
	 * Gets the ends after ocurrences.
	 * 
	 * @return the ends after ocurrences
	 */
	public Integer getEndsAfterOccurrences()
	{
		return endsAfterOccurrences;
	}

	/**
	 * Sets the ends after ocurrences.
	 * 
	 * @param endsAfterOccurrences the new ends after occurrences
	 */
	public void setEndsAfterOccurrences(Integer endsAfterOccurrences)
	{
		this.endsAfterOccurrences = endsAfterOccurrences;
	}

	/**
	 * Gets the end date.
	 * 
	 * @return the end date
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * Gets the days of weeks.
	 * 
	 * @return the days of weeks
	 */
	public List<DaysOfWeekEnum> getDaysOfWeeks()
	{
		return daysOfWeeks;
	}

	/**
	 * Sets the days of weeks enum.
	 * 
	 * @param listDaysOfWeeks the new days of weeks enum
	 */
	public void setDaysOfWeeksEnum(List<DaysOfWeekEnum> listDaysOfWeeks)
	{
		daysOfWeeks = listDaysOfWeeks;
	}

	/**
	 * Sets the days of weeks.
	 * 
	 * @param daysOfWeeks the new days of weeks
	 */
	public void setDaysOfWeeks(List<Integer> daysOfWeeks)
	{
		List<DaysOfWeekEnum> daysOfWeeksEnum = new ArrayList<DaysOfWeekEnum>();
		for (Integer i : daysOfWeeks)
		{
			daysOfWeeksEnum.add(DaysOfWeekEnum.enumForValue(i));
		}
		this.daysOfWeeks = daysOfWeeksEnum;
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
	 * Gets the day of month.
	 * 
	 * @return the day of month
	 */
	public Boolean getDayOfMonth()
	{
		return dayOfMonth;
	}

	/**
	 * Sets the day of month.
	 * 
	 * @param dayOfMonth the new day of month
	 */
	public void setDayOfMonth(Boolean dayOfMonth)
	{
		this.dayOfMonth = dayOfMonth;
	}

	/**
	 * Gets the day of week.
	 * 
	 * @return the day of week
	 */
	public Boolean getDayOfWeek()
	{
		return dayOfWeek;
	}

	/**
	 * Sets the day of week.
	 * 
	 * @param dayOfWeek the new day of week
	 */
	public void setDayOfWeek(Boolean dayOfWeek)
	{
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * Gets the repeats date.
	 * 
	 * @return the repeats date
	 */
	public List<Date> getRepeatsDate()
	{
		return repeatsDate;
	}

	/**
	 * Sets the repeats date.
	 * 
	 * @param repeatsDate the new repeats date
	 */
	public void setRepeatsDate(List<Date> repeatsDate)
	{
		this.repeatsDate = repeatsDate;
	}

	/**
	 * Gets the next execution.
	 * 
	 * @return the nextExecution
	 */
	public Date getNextExecution()
	{
		return nextExecution;
	}

	/**
	 * Sets the next execution.
	 * 
	 * @param nextExecution the nextExecution to set
	 */
	public void setNextExecution(Date nextExecution)
	{
		this.nextExecution = nextExecution;
	}

	/**
	 * Gets the execute occurrences.
	 * 
	 * @return the execute occurrences
	 */
	public Integer getExecutedOccurrences()
	{
		return executedOccurrences;
	}

	/**
	 * Sets the execute occurrences.
	 * 
	 * @param executeOccurrences the new execute occurrences
	 */
	public void setExecutedOccurrences(Integer executeOccurrences)
	{
		executedOccurrences = executeOccurrences;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Frequency [getFrequencyTypeEnum()=" + getFrequencyTypeEnum() + ", getTimeToRepeat()="
				+ getTimeToRepeat() + ", getStartOnDate()=" + getStartOnDate() + ", getNeverEnds()=" + getNeverEnds()
				+ ", getEndsAfterOccurrences()=" + getEndsAfterOccurrences() + ", getEndDate()=" + getEndDate()
				+ ", getExecutedOccurrences()=" + getExecutedOccurrences()
				+ ", getDaysOfWeeks()=" + getDaysOfWeeks() + ", getId()=" + getId() + ", getDayOfMonth()="
				+ getDayOfMonth() + ", getDayOfWeek()=" + getDayOfWeek() + ", getRepeatsDate()=" + getRepeatsDate()
				+ ", getNextExecution()=" + getNextExecution() + ", getModelAction()=" + getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}

}
