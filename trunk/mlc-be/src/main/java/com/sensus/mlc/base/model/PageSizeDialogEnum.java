package com.sensus.mlc.base.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum PageSizeDialogEnum.
 */
public enum PageSizeDialogEnum implements IIntegerEnum
{

	// Always save in user settings
	/** The change default. */
	CHANGE_DEFAULT(1),

	// No save in user settings
	/** The no change default. */
	NO_CHANGE_DEFAULT(2),

	// Prompt me in each change
	/** The PROMPT. */
	PROMPT(3);

	/** The pageSizeDialogEnum . */
	private Integer pageSizeDialogEnum;

	/**
	 * Instantiates a new page size dialog enum.
	 * 
	 * @param pageSizeDialog the page size dialog enum
	 */
	private PageSizeDialogEnum(Integer pageSizeDialog)
	{
		this.pageSizeDialogEnum = pageSizeDialog;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the page size dialog enum
	 */
	public static PageSizeDialogEnum enumForValue(Integer value)
	{
		for (PageSizeDialogEnum e : values())
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
		PageSizeDialogEnum[] enums = PageSizeDialogEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (PageSizeDialogEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return this.pageSizeDialogEnum;
	}

}
