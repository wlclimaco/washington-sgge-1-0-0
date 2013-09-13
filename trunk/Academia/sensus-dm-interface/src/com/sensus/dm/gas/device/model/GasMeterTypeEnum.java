package com.sensus.dm.gas.device.model;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum TypeEnum.
 * 
 * @author - QAT Global.
 */
public enum GasMeterTypeEnum implements IIntegerEnum
{

	/** The FLEXNE t_1 wa y_ gas. */
	FLEXNET_1WAY_GAS(7, "Flexnet Gas"),

	/** The North_ american_2_ way_gas. */
	NORTH_AMERICAN_2_WAY_GAS(48, "North American 2-Way Gas"),

	/** The gas shutoff valve. */
	GAS_SHUTOFF_VALVE(58, "Gas Shutoff Valve"),

	/** The Na2w_3_port_gas_corrector. */
	NA2W_3_PORT_GAS_CORRECTOR(68, "NA2W 3-Port Gas Corrector"),

	/** The liberty gas meter. */
	LIBERTY_GAS_METER(72, "Liberty Gas Meter");

	/** The gas meter type. */
	private Integer gasMeterType;

	/** The gas meter type description. */
	private String gasMeterTypeDescription;

	/**
	 * Instantiates a new gas meter type enum.
	 * 
	 * @param paramType the param type
	 */
	private GasMeterTypeEnum(Integer gasMeterTypeParam, String gasMeterTypeDescriptionParam)
	{
		gasMeterType = gasMeterTypeParam;
		gasMeterTypeDescription = gasMeterTypeDescriptionParam;
	}

	/**
	 * Gets the gas meter type description.
	 * 
	 * @return the gas meter type description
	 */
	public String getGasMeterTypeDescription()
	{
		return gasMeterTypeDescription;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return gasMeterType;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the gas meter type enum
	 */
	public static GasMeterTypeEnum enumForValue(Integer value)
	{
		for (GasMeterTypeEnum e : values())
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
		GasMeterTypeEnum[] enums = GasMeterTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (GasMeterTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
