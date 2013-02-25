package com.sensus.mlc.wui.smartpoint.model;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class ViewFilterLightTypes implements Serializable
{

	private List<String[]> lamptype;

	private List<String[]> wattage;

	private List<String[]> housing;

	private List<String[]> dimmable;

	/**
	 * @return the lamptype
	 */
	public List<String[]> getLamptype()
	{
		return lamptype;
	}

	/**
	 * @param lamptype the lamptype to set
	 */
	public void setLamptype(List<String[]> lamptype)
	{
		this.lamptype = lamptype;
	}

	/**
	 * @return the walttage
	 */
	public List<String[]> getWattage()
	{
		return wattage;
	}

	/**
	 * @param walttage the walttage to set
	 */
	public void setWattage(List<String[]> wattage)
	{
		this.wattage = wattage;
	}

	/**
	 * @return the housing
	 */
	public List<String[]> getHousing()
	{
		return housing;
	}

	/**
	 * @param housing the housing to set
	 */
	public void setHousing(List<String[]> housing)
	{
		this.housing = housing;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ViewFilterLightTypes [getLamptype()=" + getLamptype()
				+ ", getWattage()=" + getWattage() + ", getHousing()="
				+ getHousing() + "]";
	}

	/**
	 * @return the dimmable
	 */
	public List<String[]> getDimmable()
	{
		return dimmable;
	}

	/**
	 * @param dimmable the dimmable to set
	 */
	public void setDimmable(List<String[]> dimmable)
	{
		this.dimmable = dimmable;
	}

	// private LampTypeView lampType;
	//
	// /**
	// * @return the lampType
	// */
	// public LampTypeView getLampType() {
	// return lampType;
	// }
	//
	// /**
	// * @param lampType the lampType to set
	// */
	// public void setLampType(LampTypeView lampType) {
	// this.lampType = lampType;
	// }
	//
	// /* (non-Javadoc)
	// * @see java.lang.Object#toString()
	// */
	// @Override
	// public String toString() {
	// return "ViewFilterLightTypes [getLampType()=" + getLampType() + "]";
	// }

}
