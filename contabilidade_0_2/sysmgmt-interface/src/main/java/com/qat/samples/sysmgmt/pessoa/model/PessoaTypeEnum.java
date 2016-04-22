package com.qat.samples.sysmgmt.pessoa.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * The Enum PayrollTypeEnum details the different options a payroll can have inside of SendSolv
 * 
 * @author abarros
 * @version 1.0
 * @created 21-Jul-2014 10:00:07 AM
 */
public enum PessoaTypeEnum implements IIntegerEnum, II18nEnum
{

	/** The centralized. */
	CLIENTE(1, "com.prosperitasglobal.sendsolv.model.payrolltype.centralized"),

	/** The not centralized. */
	FORNECEDOR(2, "com.prosperitasglobal.sendsolv.model.payrolltype.not_centralized"),

	/** The mixed. */
	FUNCIONARIO(3, "com.prosperitasglobal.sendsolv.model.payrolltype.mixed"),

	/** The unknown. */
	TRANSPORTADOR(4, "com.prosperitasglobal.sendsolv.model.payrolltype.unknown"),

	CONTADOR(5, "com.prosperitasglobal.sendsolv.model.payrolltype.unknown"),

	SOCIO(6, "com.prosperitasglobal.sendsolv.model.payrolltype.unknown"),

	MEDICO(7, "com.prosperitasglobal.sendsolv.model.payrolltype.unknown"),

	PACIENTE(8, "com.prosperitasglobal.sendsolv.model.payrolltype.unknown");

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
	private PessoaTypeEnum(Integer value, String labelKeyParam)
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
	public static PessoaTypeEnum enumForValue(Integer value)
	{
		for (PessoaTypeEnum e : values())
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

		for (PessoaTypeEnum i : PessoaTypeEnum.class.getEnumConstants())
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