package com.qat.samples.complexmo.model.request;

import com.qat.framework.model.request.InquiryRequest;
import com.qat.framework.query.SearchNumberRange;
import com.qat.framework.query.SearchString;

public class PersonFancyRequest extends InquiryRequest
{

	private Integer id;
	private SearchString firstName;
	private SearchString lastName;
	private SearchNumberRange employeeNumber;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public SearchString getFirstName()
	{
		return firstName;
	}

	public void setFirstName(SearchString firstName)
	{
		this.firstName = firstName;
	}

	public SearchString getLastName()
	{
		return lastName;
	}

	public void setLastName(SearchString lastName)
	{
		this.lastName = lastName;
	}

	public SearchNumberRange getEmployeeNumber()
	{
		return employeeNumber;
	}

	public void setEmployeeNumber(SearchNumberRange employeeNumber)
	{
		this.employeeNumber = employeeNumber;
	}

	@Override
	public String toString()
	{
		return "PersonFancyRequest [getId()=" + getId() + ", getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + ", getEmployeeNumber()=" + getEmployeeNumber() + "]";
	}

}
