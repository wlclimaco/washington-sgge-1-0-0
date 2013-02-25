package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class Column.
 */
@SuppressWarnings("serial")
public class Column extends SensusModel
{
	/** The property enum. */
	private SmartPointColumnEnum columnEnum;

	/** The field name. */
	private String fieldName;

	/** The label key. */
	private String labelKey;

	/** The ordered. */
	private boolean ordered;

	/** The display order. */
	private Integer displayOrder;

	/**
	 * Instantiates a new column.
	 */
	public Column()
	{
	}

	/**
	 * Instantiates a new column.
	 * 
	 * @param fieldName the field name
	 */
	public Column(String fieldNameValue)
	{
		fieldName = fieldNameValue;
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

	/**
	 * Gets the column enum.
	 * 
	 * @return the column enum
	 */
	public SmartPointColumnEnum getColumnEnum()
	{
		return columnEnum;
	}

	/**
	 * Sets the column enum.
	 * 
	 * @param columnEnum the new column enum
	 */
	public void setColumnEnum(SmartPointColumnEnum columnEnum)
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
	 * Gets the display order.
	 * 
	 * @return the display order
	 */
	public Integer getDisplayOrder()
	{
		return displayOrder;
	}

	/**
	 * Sets the display order.
	 * 
	 * @param displayOrder the new display order
	 */
	public void setDisplayOrder(Integer displayOrder)
	{
		this.displayOrder = displayOrder;
	}

	/**
	 * Sets the column enum value.
	 * 
	 * @param valueParam the new column enum value
	 */
	public void setColumnEnumValue(String valueParam)
	{
		columnEnum = SmartPointColumnEnum.enumForValue(valueParam);
	}

	/**
	 * Gets the column enum value.
	 * 
	 * @return the column enum value
	 */
	public String getColumnEnumValue()
	{
		return columnEnum.getValue();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Column [isOrdered()=" + isOrdered() + ", getColumnEnum()=" + getColumnEnum() + ", getFieldName()="
				+ getFieldName() + ", getLabelKey()=" + getLabelKey() + ", getDisplayOrder()=" + getDisplayOrder()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}

}
