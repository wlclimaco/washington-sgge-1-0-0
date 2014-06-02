package com.qat.samples.complexmo.model.request;

import com.qat.framework.model.request.InquiryRequest;
import com.qat.framework.query.SearchString;

public class AnimalFancyRequest extends InquiryRequest
{

	private Integer id;
	private SearchString Name;

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public SearchString getName()
	{
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(SearchString name)
	{
		Name = name;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AnimalFancyRequest [id=" + id + ", Name=" + Name + ", toString()=" + super.toString() + "]";
	}

}
