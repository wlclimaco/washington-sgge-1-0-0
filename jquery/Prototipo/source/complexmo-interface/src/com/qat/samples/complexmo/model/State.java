package com.qat.samples.complexmo.model;

import com.qat.framework.model.QATModel;

@SuppressWarnings("serial")
public class State extends QATModel
{
	private String code;
	private String description;

	public State()
	{
	}

	public State(String code)
	{
		this.code = code;
	}

	public State(String code, String description)
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

	public String getUniqueId()
	{
		return code;
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
		return "State [getCode()=" + getCode() + ", getDescription()=" + getDescription() + ", getUniqueId()="
				+ getUniqueId() + ", getModelAction()=" + getModelAction() + "]";
	}
}
