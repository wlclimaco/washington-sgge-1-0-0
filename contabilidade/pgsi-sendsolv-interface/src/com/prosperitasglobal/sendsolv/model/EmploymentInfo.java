package com.prosperitasglobal.sendsolv.model;

import java.math.BigDecimal;

import com.qat.framework.model.QATModelOL;

/**
 * This Class represents the different pieces of information involved with employment.
 */
@SuppressWarnings("serial")
public class EmploymentInfo extends QATModelOL
{
	/** The amount the employee is currently getting paid by the employer. */
	private BigDecimal currentPay;

	/** The employee's external id. This will be a value external to SendSolv. */
	private String employeeId;

	/** The status of the employees employment. */
	private StatusEnum employmentInfoStatus;

	/** The date the employee was enrolled as a member of the transfer service offering. */
	private Long enrollDate;

	/** The date the employee was hired. */
	private Long hireDate;

	/** The SendSolv id for the employment information. */
	private Integer id;

	/** The employee's job title. */
	private String jobTitle;

	/** The SendSolv id of the location that is associated with this employment. */
	private Integer locationId;

	/** The name of the location. This is provided as a convenience for the UI. */
	private String locationName;

	/** The organization id. */
	private Integer organizationId;

	/** The organization name. */
	private String organizationName;

	/** The SendSolv id of the member that is associated with this employment. */
	private Integer memberId;

	/** The amount the employee is transferring during a pay date. */
	private BigDecimal payPerPeriod;

	/** The enrollment type. */
	private EnrollmentTypeEnum enrollmentType;

	/**
	 * The Constructor.
	 */
	public EmploymentInfo()
	{
		super();
	}

	/**
	 * Gets the amount the employee is currently getting paid by the employer.
	 *
	 * @return The amount.
	 */
	public BigDecimal getCurrentPay()
	{
		return currentPay;
	}

	/**
	 * Sets the amount the employee is currently getting paid by the employer.
	 *
	 * @param currentPay The amount to set.
	 */
	public void setCurrentPay(BigDecimal currentPay)
	{
		this.currentPay = currentPay;
	}

	/**
	 * Gets the employee's external id. This will be a value external to SendSolv.
	 *
	 * @return The id
	 */
	public String getEmployeeId()
	{
		return employeeId;
	}

	/**
	 * Sets the employee's external id. This will be a value external to SendSolv.
	 *
	 * @param employeeId The id to set.
	 */
	public void setEmployeeId(String employeeId)
	{
		this.employeeId = employeeId;
	}

	/**
	 * Gets the status of the employees employment.
	 *
	 * @return The status.
	 */
	public StatusEnum getEmploymentInfoStatus()
	{
		return employmentInfoStatus;
	}

	/**
	 * Get the enumeration value for status of the employees employment.
	 *
	 * @return The value for the status.
	 */
	public Integer getEmploymentInfoStatusValue()
	{
		if (getEmploymentInfoStatus() == null)
		{
			return null;
		}
		return getEmploymentInfoStatus().getValue();
	}

	/**
	 * Sets the status of the employees employment.
	 *
	 * @param employmentInfoStatus The status to set.
	 */
	public void setEmploymentInfoStatus(StatusEnum employmentInfoStatus)
	{
		this.employmentInfoStatus = employmentInfoStatus;
	}

	/**
	 * Set the enumeration by the value of the status of the employees employment.
	 *
	 * @param statusValue The value of the status enumeration to set.
	 */
	public void setEmploymentInfoStatusValue(Integer statusValue)
	{
		setEmploymentInfoStatus(StatusEnum.enumForValue(statusValue));
	}

	/**
	 * Gets the date the employee was enrolled to the transfer service for the location. If this date has a time
	 * portion, it will be removed. This attribute is only a date.
	 *
	 * @return The date
	 */
	public Long getEnrollDate()
	{
		return enrollDate;
	}

	/**
	 * Sets the date the employee was enrolled to the transfer service for this location.
	 *
	 * @param enrollDate The enrollment date.
	 */
	public void setEnrollDate(Long enrollDate)
	{
		this.enrollDate = enrollDate;
	}

	/**
	 * Gets the enrollment type.
	 *
	 * @return The type.
	 */
	public EnrollmentTypeEnum getEnrollmentType()
	{
		return enrollmentType;
	}

	/**
	 * Get the enumeration value for enrollment type.
	 *
	 * @return The value for the enrollment.
	 */
	public Integer getEnrollmentTypeValue()
	{
		if (getEnrollmentType() == null)
		{
			return null;
		}
		return getEnrollmentType().getValue();
	}

	/**
	 * Sets the enrollment type.
	 *
	 * @param enrollmentType The type to set.
	 */
	public void setEnrollmentType(EnrollmentTypeEnum enrollmentType)
	{
		this.enrollmentType = enrollmentType;
	}

	/**
	 * Set the enumeration by the value of the enrollment type.
	 *
	 * @param enrollmentTypeValue The value of the enrollment type to set.
	 */
	public void setEnrollmentTypeValue(Integer enrollmentTypeValue)
	{
		setEnrollmentType(EnrollmentTypeEnum.enumForValue(enrollmentTypeValue));
	}

	/**
	 * Gets the date the employee was hired. If this date has a time portion, it will be removed. This attribute is only
	 * a date.
	 *
	 * @return The date
	 */
	public Long getHireDate()
	{
		return hireDate;
	}

	/**
	 * Sets the date the employee was hired.
	 *
	 * @param hireDate The date to set.
	 */
	public void setHireDate(Long hireDate)
	{
		this.hireDate = hireDate;
	}

	/**
	 * Gets the SendSolv id for the employment information.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the SendSolv id for the employment information.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the employee's job title.
	 *
	 * @return The title.
	 */
	public String getJobTitle()
	{
		return jobTitle;
	}

	/**
	 * Sets the employee's job title.
	 *
	 * @param jobTitle The title to set.
	 */
	public void setJobTitle(String jobTitle)
	{
		this.jobTitle = jobTitle;
	}

	/**
	 * Gets the SendSolv id of the location that is associated with this employment.
	 *
	 * @return The id.
	 */
	public Integer getLocationId()
	{
		return locationId;
	}

	/**
	 * Sets the SendSolv id of the location that is associated with this employment.
	 *
	 * @param locationId The id to set.
	 */
	public void setLocationId(Integer locationId)
	{
		this.locationId = locationId;
	}

	/**
	 * Gets the name of the location. This is provided as a convenience for the UI.
	 *
	 * @return The name
	 */
	public String getLocationName()
	{
		return locationName;
	}

	/**
	 * Sets the name of the location. This is provided as a convenience for the UI.
	 *
	 * @param locationName The name to set.
	 */
	public void setLocationName(String locationName)
	{
		this.locationName = locationName;
	}

	/**
	 * Gets the SendSolv id of the member that is associated with this employment.
	 *
	 * @return The id.
	 */
	public Integer getMemberId()
	{
		return memberId;
	}

	/**
	 * Sets the SendSolv id of the member that is associated with this employment.
	 *
	 * @param memberId The id to set.
	 */
	public void setMemberId(Integer memberId)
	{
		this.memberId = memberId;
	}

	/**
	 * Gets the amount the employee is transferring during a pay date.
	 *
	 * @return The amount.
	 */
	public BigDecimal getPayPerPeriod()
	{
		return payPerPeriod;
	}

	/**
	 * Sets the amount the employee is transferring during a pay date.
	 *
	 * @param payPerPeriod The amount.
	 */
	public void setPayPerPeriod(BigDecimal payPerPeriod)
	{
		this.payPerPeriod = payPerPeriod;
	}

	/**
	 * Gets the organization id.
	 *
	 * @return the organization id
	 */
	public Integer getOrganizationId()
	{
		return organizationId;
	}

	/**
	 * Sets the organization id.
	 *
	 * @param organizationId the organization id
	 */
	public void setOrganizationId(Integer organizationId)
	{
		this.organizationId = organizationId;
	}

	/**
	 * Gets the organization name.
	 *
	 * @return the organization name
	 */
	public String getOrganizationName()
	{
		return organizationName;
	}

	/**
	 * Sets the organization name.
	 *
	 * @param organizationName the organization name
	 */
	public void setOrganizationName(String organizationName)
	{
		this.organizationName = organizationName;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "EmploymentInfo [getCurrentPay()=" + getCurrentPay() + ", getEmployeeId()=" + getEmployeeId()
				+ ", getEmploymentInfoStatus()=" + getEmploymentInfoStatus() + ", getEmploymentInfoStatusValue()="
				+ getEmploymentInfoStatusValue() + ", getEnrollDate()=" + getEnrollDate() + ", getEnrollmentType()="
				+ getEnrollmentType() + ", getEnrollmentTypeValue()=" + getEnrollmentTypeValue() + ", getHireDate()="
				+ getHireDate() + ", getId()=" + getId() + ", getJobTitle()=" + getJobTitle() + ", getLocationId()="
				+ getLocationId() + ", getLocationName()=" + getLocationName() + ", getMemberId()=" + getMemberId()
				+ ", getPayPerPeriod()=" + getPayPerPeriod() + ", getOrganizationId()=" + getOrganizationId()
				+ ", getOrganizationName()=" + getOrganizationName() + ", toString()=" + super.toString() + "]";
	}

}