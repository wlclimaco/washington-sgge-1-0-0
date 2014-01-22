package com.sensus.lc.controller.util.enums;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum ModeEnum.
 */
public enum TestMessageEnum implements IStringEnum
{
	MESSAGE_ERROR("error"),

	MESSAGE_WARN("warn"),

	MESSAGE_INFO("info"),

	DEFAULT_EXCEPTION_MSG("sensus.mlc.defaultexception");

	private String value;

	private TestMessageEnum(String value)
	{
		this.value = value;
	}

	@Override
	public String getValue()
	{
		return value;
	}

}
