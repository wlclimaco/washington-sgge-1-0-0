package com.qat.samples.complexmo.model;

import com.qat.framework.model.QATModel;

@SuppressWarnings("serial")
public class Help extends QATModel
{
	private String topic;
	private Integer sequence;
	private String information;

	public String getTopic()
	{
		return topic;
	}

	public void setTopic(String topic)
	{
		this.topic = topic;
	}

	public Integer getSequence()
	{
		return sequence;
	}

	public void setSequence(Integer sequence)
	{
		this.sequence = sequence;
	}

	public String getInformation()
	{
		return information;
	}

	public void setInfo(String value)
	{
		information = value;
	}

	@Override
	public String toString()
	{
		return "Help [getInformation()=" + getInformation() + ", getSequence()=" + getSequence() + ", getTopic()="
				+ getTopic() + ", getModelAction()=" + getModelAction() + "]";
	}
}
