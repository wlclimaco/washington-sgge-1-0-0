package com.prosperitasglobal.sendsolv.model;

/**
 * The Enum PayrollTypeEnum details the different options a payroll can have inside of SendSolv
 *
 * @author abarros
 * @version 1.0
 * @created 21-Jul-2014 10:00:07 AM
 */
public enum CfopTypeEnum implements IIntegerEnum, II18nEnum
{

	/** The centralized. */
	ENTRADA(1, "com.prosperitasglobal.sendsolv.model.payrolltype.centralized"),

	/** The not centralized. */
	SAIDA(2, "com.prosperitasglobal.sendsolv.model.payrolltype.not_centralized"),

	/** The centralized. */
	SERVICO(1, "com.prosperitasglobal.sendsolv.model.payrolltype.centralized"),

	/** The not centralized. */
	EXPORTACAO(2, "com.prosperitasglobal.sendsolv.model.payrolltype.not_centralized");

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
	private CfopTypeEnum(Integer value, String labelKeyParam)
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
	public static CfopTypeEnum enumForValue(Integer value)
	{
		for (CfopTypeEnum e : values())
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

		for (CfopTypeEnum i : CfopTypeEnum.class.getEnumConstants())
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