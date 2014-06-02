package com.qat.samples.complexmo.model;

import com.qat.framework.model.QATModel;

@SuppressWarnings("serial")
public class Country extends QATModel
{
	private String code;
	private String description;

	public Country()
	{
	}

	public Country(String code)
	{
		this.code = code;
	}

	public Country(String code, String description)
	{
		this.code = code;
		this.description = description;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@Override
	public String toString()
	{
		return "Country [getCode()=" + getCode() + ", getDescription()=" + getDescription() + ", getModelAction()="
				+ getModelAction() + "]";
	}

}
