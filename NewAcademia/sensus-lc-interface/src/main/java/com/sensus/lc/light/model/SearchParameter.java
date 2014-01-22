package com.sensus.lc.light.model;

/**
 * The Class SearchParameter.
 *
 * @author - QAT Global
 */
@SuppressWarnings("serial")
public class SearchParameter extends Parameter
{
	/** The name. */
	private String name;

	/** The validParameter. */
	private Boolean validParameter;

	/** The operator. */
	private SearchOperatorEnum operator;

	/**
	 * Instantiates a new search parameter.
	 */
	public SearchParameter()
	{
	}

	/**
	 * Instantiates a new search parameter.
	 *
	 * @param propertyEnum the property enum
	 * @param value the value
	 */
	public SearchParameter(PropertyEnum propertyEnum, String value)
	{
		setPropertyEnum(propertyEnum);
		setValue(value);
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param nameString the new name
	 */
	public void setName(String nameString)
	{
		name = nameString;
	}

	/**
	 * Gets the validParameter.
	 *
	 * @return the validParameter
	 */
	public Boolean isValidParameter()
	{
		return validParameter;
	}

	/**
	 * Sets the validParameter.
	 *
	 * @param validParameter the new validParameter
	 */
	public void setValidParameter(Boolean validParameter)
	{
		this.validParameter = validParameter;
	}

	/**
	 * Gets the operator.
	 *
	 * @return the operator
	 */
	public SearchOperatorEnum getOperator()
	{
		return operator;
	}

	/**
	 * Sets the operator.
	 *
	 * @param operator the new operator
	 */
	public void setOperator(SearchOperatorEnum operator)
	{
		this.operator = operator;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.Parameter#toString()
	 */
	@Override
	public String toString()
	{
		return "SearchParameter [getName()=" + getName() + ", isValidParameter()=" + isValidParameter()
				+ ", getOperator()=" + getOperator() + ", getId()=" + getId() + ", getPropertyEnum()="
				+ getPropertyEnum() + ", getPropertyEnumValue()=" + getPropertyEnumValue() + ", getLabelKey()="
				+ getLabelKey() + ", getValue()=" + getValue() + ", getDataTypeEnum()=" + getDataTypeEnum()
				+ ", getDataTypeEnumValue()=" + getDataTypeEnumValue() + ", getModelAction()=" + getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}
}
