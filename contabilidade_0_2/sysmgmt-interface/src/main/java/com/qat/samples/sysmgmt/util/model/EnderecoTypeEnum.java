package com.qat.samples.sysmgmt.util.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * The Enum PayrollTypeEnum details the different options a payroll can have inside of SendSolv
 * 
 * @author abarros
 * @version 1.0
 * @created 21-Jul-2014 10:00:07 AM
 * 
 */
public enum EnderecoTypeEnum implements IIntegerEnum, II18nEnum
{

	/** The centralized. */
	RESIDENCIAL(1, "com.prosperitasglobal.sendsolv.model.payrolltype.centralized"),

	/** The not centralized. */
	ENTREGA(2, "com.prosperitasglobal.sendsolv.model.payrolltype.not_centralized"),

	/** The mixed. */
	COBRANCA(3, "com.prosperitasglobal.sendsolv.model.payrolltype.mixed"),

	PRINCIPAL(4, "com.prosperitasglobal.sendsolv.model.payrolltype.mixed");

	/** The code. */
	private Integer code;

	/** The label key. */
	private String labelKey;

	/**
	 * The Constructor.
	 * 
	 * @param value the value
	 * @param labelKeyParam the label key param
	 */
	private EnderecoTypeEnum(Integer value, String labelKeyParam)
	{
		code = value;
		labelKey = labelKeyParam;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public Integer getValue()
	{
		return code;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the process status enum
	 */
	public static EnderecoTypeEnum enumForValue(Integer value)
	{
		for (EnderecoTypeEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Gets the valid values.
	 * 
	 * @return the valid values
	 */
	public static String getValidValues()
	{
		String comma = "";
		StringBuilder enumValue = new StringBuilder();

		for (EnderecoTypeEnum i : EnderecoTypeEnum.class.getEnumConstants())
		{
			enumValue.append(comma).append(i.getValue());
			comma = ", ";
		}

		return enumValue.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.II18nEnum#getLabelKey()
	 */
	@Override
	public String getLabelKey()
	{
		return labelKey;
	}
}