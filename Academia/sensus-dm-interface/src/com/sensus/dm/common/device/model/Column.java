package com.sensus.dm.common.device.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class Column.
 */
@SuppressWarnings("serial")
public class Column extends SensusModel
{
	/** The column enum. */
	private DeviceColumnEnum columnEnum;

	/** The field name. */
	private String fieldName;

	/** The label key. */
	private String labelKey;

	/** The ordered. */
	private boolean ordered;

	/**
	 * Instantiates a new column.
	 */
	public Column()
	{
	}

	/**
	 * Instantiates a new column.
	 * 
	 * @param meterColumnEnum the meter column enum
	 */
	public Column(DeviceColumnEnum meterColumnEnum)
	{
		setColumnEnum(meterColumnEnum);
	}

	/**
	 * Gets the column enum.
	 * 
	 * @return the column enum
	 */
	public DeviceColumnEnum getColumnEnum()
	{
		return columnEnum;
	}

	/**
	 * Sets the column enum.
	 * 
	 * @param columnEnum the new column enum
	 */
	public void setColumnEnum(DeviceColumnEnum columnEnum)
	{
		this.columnEnum = columnEnum;
	}

	/**
	 * Gets the field name.
	 * 
	 * @return the field name
	 */
	public String getFieldName()
	{
		return fieldName;
	}

	/**
	 * Sets the field name.
	 * 
	 * @param fieldName the new field name
	 */
	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}

	/**
	 * Gets the label key.
	 * 
	 * @return the label key
	 */
	public String getLabelKey()
	{
		return labelKey;
	}

	/**
	 * Sets the label key.
	 * 
	 * @param labelKey the new label key
	 */
	public void setLabelKey(String labelKey)
	{
		this.labelKey = labelKey;
	}

	/**
	 * Checks if is ordered.
	 * 
	 * @return true, if is ordered
	 */
	public boolean isOrdered()
	{
		return ordered;
	}

	/**
	 * Sets the ordered.
	 * 
	 * @param ordered the new ordered
	 */
	public void setOrdered(boolean ordered)
	{
		this.ordered = ordered;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Column [getColumnEnum()=" + getColumnEnum() + ", getFieldName()=" + getFieldName() + ", getLabelKey()="
				+ getLabelKey() + ", isOrdered()=" + isOrdered() + ", getModelAction()=" + getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}
}
