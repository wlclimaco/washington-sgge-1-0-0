package com.sensus.dm.water.device.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class WaterGasMeterStatusCount.
 * 
 * @author QAT Global
 */
@SuppressWarnings("serial")
public class WaterGasMeterStatusCount extends SensusModel
{

	/** The water gas meter status enum. */
	private WaterGasMeterStatusEnum waterGasMeterStatusEnum;

	/** The amount. */
	private Integer amount;

	/**
	 * Gets the water gas meter status enum.
	 * 
	 * @return the water gas meter status enum
	 */
	public WaterGasMeterStatusEnum getWaterGasMeterStatusEnum()
	{
		return waterGasMeterStatusEnum;
	}

	/**
	 * Sets the water gas meter status enum.
	 * 
	 * @param waterGasMeterStatusEnum the new water gas meter status enum
	 */
	public void setWaterGasMeterStatusEnum(WaterGasMeterStatusEnum waterGasMeterStatusEnum)
	{
		this.waterGasMeterStatusEnum = waterGasMeterStatusEnum;
	}

	/**
	 * Gets the water gas meter status enum value.
	 * 
	 * @return the water gas meter status enum value
	 */
	public Integer getWaterGasMeterStatusEnumValue()
	{
		if (getWaterGasMeterStatusEnum() != null)
		{
			return getWaterGasMeterStatusEnum().getValue();
		}

		return null;
	}

	/**
	 * Sets the water gas meter status enum value.
	 * 
	 * @param waterGasMeterStatusEnumParam the new water gas meter status enum value
	 */
	public void setWaterGasMeterStatusEnumValue(Integer waterGasMeterStatusEnumParam)
	{
		setWaterGasMeterStatusEnum(WaterGasMeterStatusEnum.enumForValue(waterGasMeterStatusEnumParam));
	}

	/**
	 * Gets the amount.
	 * 
	 * @return the amount
	 */
	public Integer getAmount()
	{
		return amount;
	}

	/**
	 * Sets the amount.
	 * 
	 * @param amount the new amount
	 */
	public void setAmount(Integer amount)
	{
		this.amount = amount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "WaterGasMeterStatus [getWaterGasMeterStatusEnum()=" + getWaterGasMeterStatusEnum()
				+ ", getWaterGasMeterStatusEnumValue()=" + getWaterGasMeterStatusEnumValue() + ", getAmount()="
				+ getAmount() + ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}

}
