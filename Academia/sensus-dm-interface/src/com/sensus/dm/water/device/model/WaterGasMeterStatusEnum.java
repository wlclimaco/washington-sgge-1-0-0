package com.sensus.dm.water.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum WaterGasMeterStatusEnum.
 * 
 * @author QATEmployee
 */
public enum WaterGasMeterStatusEnum implements IIntegerEnum
{

	/** The unknown. */
	UNKNOWN(-1),

	/** The idle. */
	IDLE(0),

	/** The walk by drive by. */
	WALK_BY_DRIVE_BY(1),

	/** The fixed base lat. */
	FIXED_BASE_LAT(2),

	/** The fixed base mom. */
	FIXED_BASE_MOM(3);

	/** The water gas meter status enum. */
	private Integer waterGasMeterStatusEnum;

	/**
	 * Instantiates a new water gas meter status enum.
	 * 
	 * @param paramType the param type
	 */
	private WaterGasMeterStatusEnum(Integer paramType)
	{
		waterGasMeterStatusEnum = paramType;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return waterGasMeterStatusEnum;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the water gas meter status enum
	 */
	public static WaterGasMeterStatusEnum enumForValue(Integer value)
	{
		for (WaterGasMeterStatusEnum e : values())
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
		WaterGasMeterStatusEnum[] enums = WaterGasMeterStatusEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (WaterGasMeterStatusEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
