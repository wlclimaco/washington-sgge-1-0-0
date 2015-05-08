package com.prosperitasglobal.sendsolv.model;

import java.util.ArrayList;
import java.util.List;

import com.qat.framework.validation.ValidationUtil;

/**
 * The Class Organization represents a main {@link Business} that is signed up to use PGSi services. It owns one or more
 * {@link Location}
 */
@SuppressWarnings("serial")
public class Organization extends Business
{
	/** The number of locations this organization contains. */
	private Integer numberOfLocations;

	/** The flag that indicates if the payroll is centralized. */
	private PayrollTypeEnum isPayrollCentralized;

	/** The dba name. */
	private String dbaName;

	/** The list of documents this organization contains. */
	private List<Document> documentList;

	/** The list of locations this organization contains. */
	private List<Location> locationList;

	/**
	 * The Constructor.
	 */
	public Organization()
	{

	}

	/**
	 * Gets the number of locations.
	 *
	 * @return the number of locations
	 */
	public Integer getNumberOfLocations()
	{
		return numberOfLocations;
	}

	/**
	 * Sets the number of locations.
	 *
	 * @param numberOfLocations the number of locations
	 */
	public void setNumberOfLocations(Integer numberOfLocations)
	{
		this.numberOfLocations = numberOfLocations;
	}

	/**
	 * Gets the is payroll centralized.
	 *
	 * @return the checks if is payroll centralized
	 */
	public PayrollTypeEnum getIsPayrollCentralized()
	{
		return isPayrollCentralized;
	}

	/**
	 * Sets the is payroll centralized.
	 *
	 * @param isPayrollCentralized the checks if is payroll centralized
	 */
	public void setIsPayrollCentralized(PayrollTypeEnum isPayrollCentralized)
	{
		this.isPayrollCentralized = isPayrollCentralized;
	}

	/**
	 * Gets the is payroll centralized value.
	 *
	 * @return the checks if is payroll centralized value
	 */
	public Integer getIsPayrollCentralizedValue()
	{
		if (isPayrollCentralized == null)
		{
			return null;
		}

		return isPayrollCentralized.getValue();
	}

	/**
	 * Sets the is payroll centralized value.
	 *
	 * @param isPayrollCentralized the checks if is payroll centralized value
	 */
	public void setIsPayrollCentralizedValue(Integer isPayrollCentralized)
	{
		this.isPayrollCentralized = PayrollTypeEnum.enumForValue(isPayrollCentralized);
	}

	/**
	 * Gets the dba name.
	 *
	 * @return the dba name
	 */
	public String getDbaName()
	{
		return dbaName;
	}

	/**
	 * Sets the dba name.
	 *
	 * @param dbaName the dba name
	 */
	public void setDbaName(String dbaName)
	{
		this.dbaName = dbaName;
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
	 * Sets the document list.
	 *
	 * @param documentList the document list
	 */
	public void setDocumentList(List<Document> documentList)
	{
		this.documentList = documentList;
	}

	/**
	 * Gets the location list.
	 *
	 * @return the location list
	 */
	public List<Location> getLocationList()
	{
		if (ValidationUtil.isNull(locationList))
		{
			setLocationList(new ArrayList<Location>());
		}

		return locationList;
	}

	/**
	 * Sets the location list.
	 *
	 * @param locationList the location list
	 */
	public void setLocationList(List<Location> locationList)
	{
		this.locationList = locationList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Organization [getNumberOfLocations()=" + getNumberOfLocations() + ", getIsPayrollCentralized()="
				+ getIsPayrollCentralized() + ", getIsPayrollCentralizedValue()=" + getIsPayrollCentralizedValue()
				+ ", getDbaName()=" + getDbaName() + ", getDocumentList()=" + getDocumentList()
				+ ", getLocationList()=" + getLocationList() + ", toString()=" + super.toString() + "]";
	}

}