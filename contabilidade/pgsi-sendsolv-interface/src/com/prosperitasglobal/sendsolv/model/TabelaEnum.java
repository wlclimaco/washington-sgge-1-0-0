package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

// TODO: Auto-generated Javadoc
/**
 * The Enum RiskLevelEnum.
 *
 * @author abarros
 * @version 1.0
 * @created 21-Jul-2014 10:00:07 AM
 */
public enum TabelaEnum implements IIntegerEnum, II18nEnum
{

	/**
	 * The high.
	 *
	 * REGIME
	 * EMPRESA
	 * DOCUMENTO
	 * EMAIL
	 * ENDERECO
	 * TELEFONE
	 * SOCIO
	 * HISTORICO
	 * CNAE
	 * CSOSN
	 * NCM
	 * CIDADE
	 * CNAEPORRELACIONAMENTO
	 * ESTADO
	 * UNIMED
	 * CFOP
	 * FUNCIONARIO
	 * SALARIO
	 */

	REGIME(0, "sendsolv.base.model.risklevelenum.high"),

	/** The low. */
	EMPRESA(1, "sendsolv.base.model.risklevelenum.low"),

	/** The medium. */
	DOCUMENTO(2, "sendsolv.base.model.risklevelenum.medium"),

	/** The unknown. */
	EMAIL(3, "sendsolv.base.model.risklevelenum.unknown"),

	/** The endereco. */
	ENDERECO(0, "sendsolv.base.model.risklevelenum.high"),

	/** The low. */
	TELEFONE(1, "sendsolv.base.model.risklevelenum.low"),

	/** The medium. */
	SOCIO(2, "sendsolv.base.model.risklevelenum.medium"),

	/** The unknown. */
	HISTORICO(3, "sendsolv.base.model.risklevelenum.unknown"),

	/** The cnae. */
	CNAE(0, "sendsolv.base.model.risklevelenum.high"),

	/** The low. */
	CSOSN(1, "sendsolv.base.model.risklevelenum.low"),

	/** The medium. */
	NCM(2, "sendsolv.base.model.risklevelenum.medium"),

	/** The unknown. */
	CIDADE(3, "sendsolv.base.model.risklevelenum.unknown"),

	/** The cnaeporrelacionamento. */
	CNAEPORRELACIONAMENTO(0, "sendsolv.base.model.risklevelenum.high"),

	/** The estado. */
	ESTADO(3, "sendsolv.base.model.risklevelenum.unknown"),

	/** The unimed. */
	UNIMED(0, "sendsolv.base.model.risklevelenum.high"),

	/** The low. */
	CFOP(1, "sendsolv.base.model.risklevelenum.low"),

	/** The medium. */
	FUNCIONARIO(2, "sendsolv.base.model.risklevelenum.medium"),

	CLIENTE(0, "sendsolv.base.model.risklevelenum.high"),

	/** The low. */
	FORNECEDOR(1, "sendsolv.base.model.risklevelenum.low"),

	/** The medium. */
	TRANSPORTADOR(2, "sendsolv.base.model.risklevelenum.medium"),

	/** The unknown. */
	SALARIO(3, "sendsolv.base.model.risklevelenum.unknown");

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
	private TabelaEnum(Integer value, String labelKeyParam)
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
	public static TabelaEnum enumForValue(Integer value)
	{
		for (TabelaEnum e : values())
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

		for (TabelaEnum i : TabelaEnum.class.getEnumConstants())
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
	/**
	 * Gets the label key.
	 *
	 * @return the label key
	 */
	@Override
	public String getLabelKey()
	{
		return labelKey;
	}
}