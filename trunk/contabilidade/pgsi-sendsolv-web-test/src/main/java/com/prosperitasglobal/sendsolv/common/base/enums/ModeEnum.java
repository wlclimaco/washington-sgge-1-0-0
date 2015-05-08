package com.prosperitasglobal.sendsolv.common.base.enums;

import com.qat.framework.model.IStringEnum;

/**
 * The Enum ModeEnum.
 */
public enum ModeEnum implements IStringEnum
{
	MODE_SUCCESS("success"),

	MODE_EMPTY("empty"),

	MODE_FAILURE("fail"),

	MODE_EXCEPTION("exception");

	private String value;

	private ModeEnum(String value)
	{
		this.value = value;
	}

	@Override
	public String getValue()
	{
		return value;
	}

}
