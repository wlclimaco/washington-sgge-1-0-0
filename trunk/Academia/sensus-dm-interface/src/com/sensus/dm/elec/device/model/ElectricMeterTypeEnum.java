package com.sensus.dm.elec.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum ElectricMeterTypeEnum.
 */
public enum ElectricMeterTypeEnum implements IIntegerEnum
{

	/** The tx only. */
	TX_ONLY(0),

	/** The rx. */
	RX(1),

	/** The l g focus ax time of use. */
	L_G_FOCUS_AX_TIME_OF_USE(19),

	/** The icona tou integrated display. */
	ICONA_TOU_INTEGRATED_DISPLAY(23),

	/** The ELSTE r_ a3_ c_ i_ flexne t_ radio. */
	ELSTER_A3_C_I_FLEXNET_RADIO(49),

	/** The iconas tou integrated display. */
	ICONAS_TOU_INTEGRATED_DISPLAY(50),

	/** The l g focus ax flexnet radio. */
	L_G_FOCUS_AX_FLEXNET_RADIO(51),

	/** The ICON a_ ge n_4. */
	ICONA_GEN_4(83);

	/** The electric meter type. */
	private Integer electricMeterType;

	/**
	 * Instantiates a new electric meter type enum.
	 *
	 * @param electricMeterType the electric meter type
	 */
	private ElectricMeterTypeEnum(Integer electricMeterTypeParam)
	{
		this.setElectricMeterType(electricMeterTypeParam);
	}

	/**
	 * Gets the electric meter type.
	 *
	 * @return the electric meter type
	 */
	public Integer getElectricMeterType()
	{
		return electricMeterType;
	}

	/**
	 * Sets the electric meter type.
	 *
	 * @param electricMeterType the new electric meter type
	 */
	public void setElectricMeterType(Integer electricMeterType)
	{
		this.electricMeterType = electricMeterType;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return electricMeterType;
	}

	/**
	 * Enum for value.
	 *
	 * @param value the value
	 * @return the electric meter type enum
	 */
	public static ElectricMeterTypeEnum enumForValue(Integer value)
	{
		for (ElectricMeterTypeEnum e : values())
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
		ElectricMeterTypeEnum[] enums = ElectricMeterTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (ElectricMeterTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
