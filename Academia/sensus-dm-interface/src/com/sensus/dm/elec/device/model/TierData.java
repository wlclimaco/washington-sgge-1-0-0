package com.sensus.dm.elec.device.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;

/**
 * The Class TierData.
 * 
 * @author QAT Brazil
 */
@SuppressWarnings("serial")
public class TierData extends SensusModel
{
	// -------------------------------------------------------------------------
	// Fields:

	/** The tier id. */
	private String tierId;

	/** The unit of measure totals. */
	private List<UnitOfMeasureTotal> unitOfMeasureTotals;

	// -------------------------------------------------------------------------
	// Constructors:

	/**
	 * Instantiates a new tier data.
	 */
	public TierData()
	{
	}

	/**
	 * Instantiates a new tier data.
	 * 
	 * @param tierIdParam the tier id
	 */
	public TierData(String tierIdParam)
	{
		setTierId(tierIdParam);
	}

	/**
	 * Instantiates a new tier data.
	 * 
	 * @param tierIdParam the tier id
	 * @param uomTotalParam the unit of measure totals
	 */
	public TierData(String tierIdParam, List<UnitOfMeasureTotal> uomTotalParam)
	{
		setTierId(tierIdParam);
		setUnitOfMeasureTotals(uomTotalParam);
	}

	// -------------------------------------------------------------------------
	// Getters and setters:

	/**
	 * Gets the tier id.
	 * 
	 * @return the tier id
	 */
	public String getTierId()
	{
		return tierId;
	}

	/**
	 * Sets the tier id.
	 * 
	 * @param tierId the new tier id
	 */
	public void setTierId(String tierId)
	{
		this.tierId = tierId;
	}

	/**
	 * Gets the unit of measure totals.
	 * 
	 * @return the unit of measure totals
	 */
	public List<UnitOfMeasureTotal> getUnitOfMeasureTotals()
	{
		return unitOfMeasureTotals;
	}

	/**
	 * Gets the unit of measure total.
	 * 
	 * @param channelName the channel name
	 * @return the unit of measure total
	 */
	public UnitOfMeasureTotal getUnitOfMeasureTotal(String channelName)
	{
		List<UnitOfMeasureTotal> theUnitOfMeasureTotals = getUnitOfMeasureTotals();

		if (theUnitOfMeasureTotals != null)
		{
			int index = theUnitOfMeasureTotals.indexOf(new UnitOfMeasureTotal(channelName));

			if (index != -1)
			{
				return theUnitOfMeasureTotals.get(index);
			}
		}

		return null;
	}

	/**
	 * Sets the unit of measure totals.
	 * 
	 * @param unitOfMeasureTotals the new unit of measure totals
	 */
	public void setUnitOfMeasureTotals(List<UnitOfMeasureTotal> unitOfMeasureTotals)
	{
		this.unitOfMeasureTotals = unitOfMeasureTotals;
	}

	/**
	 * Adds the unit of measure total.
	 * 
	 * @param unitOfMeasureTotal the unit of measure total
	 */
	public void addUnitOfMeasureTotal(UnitOfMeasureTotal unitOfMeasureTotal)
	{
		if (getUnitOfMeasureTotals() == null)
		{
			setUnitOfMeasureTotals(new ArrayList<UnitOfMeasureTotal>());
		}

		getUnitOfMeasureTotals().add(unitOfMeasureTotal);
	}

	// -------------------------------------------------------------------------
	// java.lang.Object overrides:

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TierData [getTierId()=" + getTierId() + ", getUnitOfMeasureTotals()=" + getUnitOfMeasureTotals() + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		int i = 0;

		if (tierId != null)
		{
			i = tierId.hashCode();
		}

		result = (prime * result) + i;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		TierData other = (TierData)obj;
		if (tierId == null)
		{
			if (other.tierId != null)
			{
				return false;
			}
		}
		else if (!tierId.equals(other.tierId))
		{
			return false;
		}
		return true;
	}

	// -------------------------------------------------------------------------
}
