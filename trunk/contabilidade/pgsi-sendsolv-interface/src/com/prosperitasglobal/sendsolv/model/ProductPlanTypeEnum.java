package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * Enumeration specifying the values defined for a product plan in the SendSolv system.
 */
public enum ProductPlanTypeEnum implements IIntegerEnum, II18nEnum
{
	/** A business. */
	BUSINESS(1, "com.prosperitasglobal.sendsolv.model.productplantypeenum.business"),

	/** A Template. */
	TEMPLATE(2, "com.prosperitasglobal.sendsolv.model.productplantypeenum.template");

	/** The code. */
	private Integer code;

	/** The label key. */
	private String labelKey;

	/**
	 * The Constructor.
	 *
	 * @param value The value of the product plan type enumeration.
	 * @param labelKeyParam The label key parameter.
	 */
	private ProductPlanTypeEnum(Integer value, String labelKeyParam)
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
	 * Enumeration for value.
	 *
	 * @param value The value of the product plan type enumeration.
	 * @return The product plan type enumeration.
	 */
	public static ProductPlanTypeEnum enumForValue(Integer value)
	{
		for (ProductPlanTypeEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Gets the valid values for the product plan type's.
	 *
	 * @return The valid values.
	 */
	public static String getValidValues()
	{
		String comma = "";
		StringBuilder enumValue = new StringBuilder();

		for (ProductPlanTypeEnum i : ProductPlanTypeEnum.class.getEnumConstants())
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
