package com.qat.samples.sysmgmt.nf.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

public enum NotaTypeEnum implements IIntegerEnum, II18nEnum
{

	/** The centralized. */
	ENTRADA(1, "com.prosperitasglobal.sendsolv.model.payrolltype.centralized"),

	/** The not centralized. */
	SAIDA(2, "com.prosperitasglobal.sendsolv.model.payrolltype.not_centralized"),

	/** The mixed. */
	TRANSPORTE(3, "com.prosperitasglobal.sendsolv.model.payrolltype.mixed"),

	/** The unknown. */
	SERVICO(4, "com.prosperitasglobal.sendsolv.model.payrolltype.unknown"),

	ORCAMENTO(5, "com.prosperitasglobal.sendsolv.model.payrolltype.unknown"),

	PEDIDOCOMPRAS(6, "com.prosperitasglobal.sendsolv.model.payrolltype.unknown");

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
	private NotaTypeEnum(Integer value, String labelKeyParam)
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
	public static NotaTypeEnum enumForValue(Integer value)
	{
		for (NotaTypeEnum e : values())
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

		for (NotaTypeEnum i : NotaTypeEnum.class.getEnumConstants())
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