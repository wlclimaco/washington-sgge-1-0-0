package com.sensus.lc.light.model;

import com.sensus.common.model.IStringEnum;

public enum DeleteLightReferenceEnum implements IStringEnum
{
	DELETE_LIGHT_REFERENCES("deleteLightReferences"), // Delete Light References in the tables: light_tag,
														// light_grouping and schedule_membership
	DELETE_ADDR_TAGS("deleteAddrTags"); // Delete Light Addr Tags

	private String statementValue;

	private DeleteLightReferenceEnum(String value)
	{
		statementValue = value;
	}

	@Override
	public String getValue()
	{
		return statementValue;
	}

}
