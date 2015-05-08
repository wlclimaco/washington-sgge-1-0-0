package com.prosperitasglobal.sendsolv.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.qat.framework.model.QATModel;

/**
 * This class models a suspicious activity incident for various target types: Organizations, Locations,
 * Members, Recipients or Liaisons (Contacts). The BusinessTypeEnum indicates the target type. When first
 * created the SuspiciousActivity should be in a PGSI_REVIEW status. A suspicious activity can be reported
 * by any user. A compliance officer will then look at the suspicious activity reported and either change the
 * status to FILING while writing the formal report and then to FILED when the report is filed with the
 * Federal Government. If the officer decides not to file a formal report, the SuspiciousActivity will be put in a
 * CLOSED status.
 * <p>
 * The class supports multiple Person (Member, Recipient, Liaison) and Business (Organization, Location) objects to be
 * associated with a SuspiciousActivity. For example, a Member may be associated to multiple Location objects but the
 * SuspiciousActivity is targeted at the Member of one of the Locations. For example, a Location which is the object of
 * the SuspiciousActivity may involve multiple Person objects such as a Member and a Liaison.
 *
 * @author QATEmployee
 *
 */
@SuppressWarnings("serial")
public class SuspiciousActivity extends QATModel
{
	private Integer id;
	private String businessKey;
	private BusinessTypeEnum type;
	private String summary;
	private String detail;
	private Long activityStartDateTimeUTC;
	private Long activityStopDateTimeUTC;
	private SARStatusEnum status;
	private Long statusDateTime;
	private String statusReason;
	private BigDecimal amountAssociated;
	private List<Person> personList;
	private List<Business> businessList;

	/**
	 * Get the internal system assigned ID.
	 *
	 * @return
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the ID assigned by the system when the object was created.
	 *
	 * @param id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the unique business key for the suspicious activity.
	 *
	 * @return
	 */
	public String getBusinessKey()
	{
		return businessKey;
	}

	/**
	 * Set the unique business key for the suspicious activity.
	 *
	 * @param businessKey
	 */
	public void setBusinessKey(String businessKey)
	{
		this.businessKey = businessKey;
	}

	/**
	 * Get the target type of the SuspiciousActivity. It will be one of Organization,
	 * Location, Member, Recipient, Liaison.
	 *
	 * @return
	 */
	public BusinessTypeEnum getType()
	{
		return type;
	}

	/**
	 * Set the target type of the SuspiciousActivity. It will be one of Organization,
	 * Location, Member, Recipient, Liaison.
	 *
	 * @param type
	 */
	public void setType(BusinessTypeEnum type)
	{
		this.type = type;
	}

	/**
	 * Used by components that do not understand Enum types such as MyBatis. This
	 * method returns the Integer value associated with the BusinessTypeEnum.
	 *
	 * @return
	 */
	public Integer getTypeValue()
	{
		if (type == null)
		{
			return null;
		}

		return type.getValue();
	}

	/**
	 * Used by components that do not understand Enum types such as MyBatis. This
	 * method converts the Integer value into a BusinessTypeEnum. If the value is not
	 * valid for the Enum, null will be set.
	 *
	 * @param typeValue
	 */
	public void setTypeValue(Integer typeValue)
	{
		type = BusinessTypeEnum.enumForValue(typeValue);
	}

	/**
	 * Gets a brief summary description of the SuspiciousActivity.
	 *
	 * @return
	 */
	public String getSummary()
	{
		return summary;
	}

	/**
	 * Sets a brief summary description of the SuspiciousActivity.
	 *
	 * @param summary
	 */
	public void setSummary(String summary)
	{
		this.summary = summary;
	}

	/**
	 * Gets the detailed description of the SuspiciousActivity.
	 *
	 * @return
	 */
	public String getDetail()
	{
		return detail;
	}

	/**
	 * Sets the detailed description of the SuspiciousActivity.
	 *
	 * @param detail
	 */
	public void setDetail(String detail)
	{
		this.detail = detail;
	}

	/**
	 * Gets the {@link SARStatusEnum} of the SuspiciousActivity.
	 * It will be one of PGSI_REVIEW, FILING, FILED, CLOSED.
	 *
	 * @return
	 */
	public SARStatusEnum getStatus()
	{
		return status;
	}

	/**
	 * Sets the {@link SARStatusEnum} of the SuspiciousActivity.
	 * It will be one of PGSI_REVIEW, FILING, FILED, CLOSED.
	 *
	 * @param status
	 */
	public void setStatus(SARStatusEnum status)
	{
		this.status = status;
	}

	/**
	 * Used by components that do not understand Enum types such as MyBatis. This
	 * method returns the Integer value associated with the SARStatusEnum.
	 *
	 * @return
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
	 * Used by components that do not understand Enum types such as MyBatis. This
	 * method converts the Integer value into a SARStatusEnum. If the value is not
	 * valid for the Enum, null will be set.
	 *
	 * @param typeValue
	 */
	public void setStatusValue(Integer statusValue)
	{
		status = SARStatusEnum.enumForValue(statusValue);
	}

	/**
	 * Gets the user provided date and time when setting a SARStatusEnum.
	 * Defaults to the current DateTime UTC.
	 *
	 * @return
	 */
	public Long getStatusDateTime()
	{
		return statusDateTime;
	}

	/**
	 * Sets the user provided date and time when setting a SARStatusEnum.
	 * Should default to the current DateTime UTC.
	 *
	 * @param statusDateTime
	 */
	public void setStatusDateTime(Long statusDateTime)
	{
		this.statusDateTime = statusDateTime;
	}

	/**
	 * Gets the user provided reason for the SARStatusEnum. Optional.
	 *
	 * @return
	 */
	public String getStatusReason()
	{
		return statusReason;
	}

	/**
	 * Sets the user provided reason for the SARStatusEnum. Optional.
	 *
	 * @param statusReason
	 */
	public void setStatusReason(String statusReason)
	{
		this.statusReason = statusReason;
	}

	/**
	 * Get the starting UTC DateTime recorded by the user who initiated the SuspiciousActivity report.
	 *
	 * @return
	 */
	public Long getActivityStartDateTimeUTC()
	{
		return activityStartDateTimeUTC;
	}

	/**
	 * Set the starting UTC DateTime recorded by the user who initiated the SuspiciousActivity report.
	 *
	 * @param activityStartDateTimeUTC
	 */
	public void setActivityStartDateTimeUTC(Long activityStartDateTimeUTC)
	{
		this.activityStartDateTimeUTC = activityStartDateTimeUTC;
	}

	/**
	 * Get the UTC DateTime recorded by the user when SuspiciousActivity ended.
	 *
	 * @return
	 */
	public Long getActivityStopDateTimeUTC()
	{
		return activityStopDateTimeUTC;
	}

	/**
	 * Set the UTC DateTime recorded by the user when SuspiciousActivity ended.
	 *
	 * @param activityStopDateTimeUTC
	 */
	public void setActivityStopDateTimeUTC(Long activityStopDateTimeUTC)
	{
		this.activityStopDateTimeUTC = activityStopDateTimeUTC;
	}

	/**
	 * Gets the money amount, if there was money associated with the SuspiciousActivity.
	 *
	 * @return
	 */
	public BigDecimal getAmountAssociated()
	{
		return amountAssociated;
	}

	/**
	 * Sets the money amount, if there was money associated with the SuspiciousActivity.
	 *
	 * @param amountAssociated
	 */
	public void setAmountAssociated(BigDecimal amountAssociated)
	{
		this.amountAssociated = amountAssociated;
	}

	/**
	 * Returns the List of Person objects associated with the SuspiciousActivity.
	 *
	 * @return
	 */
	public List<Person> getPersonList()
	{
		return personList;
	}

	/**
	 * Sets the List of Person objects associated with the SuspiciousActivity.
	 *
	 * @param personList
	 */
	public void setPersonList(List<Person> personList)
	{
		this.personList = personList;
	}

	/**
	 * Sets the one Person object replacing the List with a List of one.
	 *
	 * @param person
	 */
	public void setPerson(Person person)
	{
		personList = new ArrayList<Person>();
		personList.add(person);
	}

	/**
	 * Adds a Person object to the existing List if one exists.
	 * If no List exists, one will be created.
	 *
	 * @param person
	 * @return the List of Persons including the person parameter.
	 */
	public List<Person> addPerson(Person person)
	{
		if (personList == null)
		{
			personList = new ArrayList<Person>();
		}

		personList.add(person);

		return personList;
	}

	/**
	 * Returns the List of Business objects associated with the SuspiciousActivity.
	 *
	 * @return
	 */
	public List<Business> getBusinessList()
	{
		return businessList;
	}

	/**
	 * Sets the one Business object replacing the List with a List of one.
	 *
	 * @param business
	 */
	public void setBusiness(Business business)
	{
		businessList = new ArrayList<Business>();
		businessList.add(business);
	}

	/**
	 * Adds a Business object to the existing List if one exists.
	 * If no List exists, one will be created.
	 *
	 * @param business
	 * @return the List of Businesses including the business parameter.
	 */
	public List<Business> addBusiness(Business business)
	{
		if (businessList == null)
		{
			businessList = new ArrayList<Business>();
		}

		businessList.add(business);

		return businessList;
	}

	/**
	 * Sets the List of Business objects associated with the SuspiciousActivity.
	 *
	 * @param businessList
	 */
	public void setBusinessList(List<Business> businessList)
	{
		this.businessList = businessList;
	}

	@Override
	public String toString()
	{
		return "SuspiciousActivity [getId()=" + getId() + ", getBusinessKey()=" + getBusinessKey() + ", getType()="
				+ getType() + ", getSummary()=" + getSummary() + ", getDetail()=" + getDetail() + ", getStatus()="
				+ getStatus() + ", getStatusDateTime()=" + getStatusDateTime() + ", getStatusReason()="
				+ getStatusReason() + ", getActivityStartDateTimeUTC()=" + getActivityStartDateTimeUTC()
				+ ", getActivityStopDateTimeUTC()=" + getActivityStopDateTimeUTC() + ", getAmountAssociated()="
				+ getAmountAssociated() + ", getPersonList()=" + getPersonList() + ", getBusinessList()="
				+ getBusinessList() + ", toString()=" + super.toString() + "]";
	}
}
