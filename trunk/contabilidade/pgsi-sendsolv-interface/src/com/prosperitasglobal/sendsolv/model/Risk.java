package com.prosperitasglobal.sendsolv.model;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.qat.framework.model.QATModelOL;

/**
 * The Class Risk. It carries Risk information attached to {@link Person} or {@link Business}
 */

@SuppressWarnings("serial")
public class Risk extends QATModelOL
{
	/** The SendSolv parent id. */
	private Integer parentKey = -1;

	/** The type of parent key. */
	private BusinessTypeEnum parentKeyType = BusinessTypeEnum.UNKNOWN;

	/** The risk level, defaulted to unknown. */
	private RiskLevelEnum riskLevel = RiskLevelEnum.UNKNOWN;

	/** The risk level note. */
	private String riskLevelNote;

	/**
	 * The Constructor.
	 */
	public Risk()
	{

	}

	/**
	 * Gets the risk level.
	 *
	 * @return the risk level
	 */
	public RiskLevelEnum getRiskLevel()
	{
		return riskLevel;
	}

	/**
	 * Sets the risk level.
	 *
	 * @param riskLevel the risk level
	 */
	public void setRiskLevel(RiskLevelEnum riskLevel)
	{
		this.riskLevel = riskLevel;
	}

	/**
	 * Methods that follow the naming pattern get.....Value() provide convenience for returning the primitive value of
	 * an enum. For example, database mapping of an enum to a database column could make use of this method.
	 *
	 * @return the risk level value
	 */
	public Integer getRiskLevelValue()
	{
		if (riskLevel == null)
		{
			return null;
		}

		return riskLevel.getValue();
	}

	/**
	 * Methods that follow the naming pattern set.....Value(argValue) provide convenience for assigning the primitive
	 * value of an enum. For example, database mapping of an database column to an enum could make use of this method.
	 *
	 * @param riskLevelValue the risk level value
	 */
	public void setRiskLevelValue(Integer riskLevelValue)
	{
		riskLevel = RiskLevelEnum.enumForValue(riskLevelValue);
	}

	/**
	 * Gets the risk level note.
	 *
	 * @return the risk level note
	 */
	public String getRiskLevelNote()
	{
		return riskLevelNote;
	}

	/**
	 * Sets the risk level note.
	 *
	 * @param riskLevelNote the risk level note
	 */
	public void setRiskLevelNote(String riskLevelNote)
	{
		this.riskLevelNote = riskLevelNote;
	}

	/**
	 * Gets the parent key.
	 *
	 * @return the parent key
	 */
	public Integer getParentKey()
	{
		return parentKey;
	}

	/**
	 * Sets the parent key.
	 *
	 * @param parentKey the parent key
	 */
	public void setParentKey(Integer parentKey)
	{
		this.parentKey = parentKey;
	}

	/**
	 * Gets the parent key type.
	 *
	 * @return the parent key type
	 */
	public BusinessTypeEnum getParentKeyType()
	{
		return parentKeyType;
	}

	/**
	 * Sets the parent key type.
	 *
	 * @param parentKeyType the parent key type
	 */
	public void setParentKeyType(BusinessTypeEnum parentKeyType)
	{
		this.parentKeyType = parentKeyType;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Risk [getRiskLevel()=" + getRiskLevel() + ", getRiskLevelValue()=" + getRiskLevelValue()
				+ ", getRiskLevelNote()=" + getRiskLevelNote() + ", getParentKey()=" + getParentKey()
				+ ", getParentKeyType()=" + getParentKeyType() + ", toString()=" + super.toString() + "]";
	}

}