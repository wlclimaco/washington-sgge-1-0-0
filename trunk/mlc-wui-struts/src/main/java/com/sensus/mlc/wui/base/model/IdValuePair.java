package com.sensus.mlc.wui.base.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

import com.sensus.common.validation.ValidationUtil;

/**
 * Model Object for key-value pairs, for example to back droplists on the screen.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
@SuppressWarnings("serial")
public class IdValuePair implements Serializable, Comparator<IdValuePair>
{

	/** The Constant INT_FORMAT. */
	private static final String INT_FORMAT = "%d";

	/**
	 * The id or key.
	 */
	private String id;

	/**
	 * The value.
	 */
	private String value;

	/** The int value. */
	private Integer intValue;

	private List<IdValuePair> idValuePairs;

	/**
	 * Constructs an empty IdValuePair object.
	 */
	public IdValuePair()
	{
	}

	/**
	 * Constructs and IdValuePair object from a string id and a value.
	 * 
	 * @param idIn the id or key
	 * @param valueIn the value
	 */
	public IdValuePair(String idIn, String valueIn)
	{
		setId(idIn);
		setValue(valueIn);
	}

	public IdValuePair(String idIn, List<IdValuePair> idValuePairsIn)
	{
		setId(idIn);
		setIdValuePairs(idValuePairsIn);
	}

	/**
	 * Constructs and IdValuePair object from an integer id and a value. The id will be automatically converted to a
	 * String.
	 * 
	 * @param idIn the id or key
	 * @param valueIn the value
	 */
	public IdValuePair(Integer idIn, String valueIn)
	{
		setId(String.format(INT_FORMAT, idIn));
		setValue(valueIn);
	}

	/**
	 * Instantiates a new id value pair.
	 * 
	 * @param idIn the id in
	 * @param valueIn the value in
	 */
	public IdValuePair(Integer idIn, Integer valueIn)
	{
		setId(String.format(INT_FORMAT, idIn));
		setIntValue(valueIn);
	}

	/**
	 * Get the id or key.
	 * 
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Set the id or key.
	 * 
	 * @param idIn the id
	 */
	public void setId(String idIn)
	{
		id = idIn;
	}

	/**
	 * Get the value.
	 * 
	 * @return the value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Set the value.
	 * 
	 * @param valueIn the value
	 */
	public void setValue(String valueIn)
	{
		value = valueIn;
	}

	/**
	 * Gets the int value.
	 * 
	 * @return the int value
	 */
	public Integer getIntValue()
	{
		return intValue;
	}

	/**
	 * Sets the int value.
	 * 
	 * @param intValue the new int value
	 */
	public void setIntValue(Integer intValue)
	{
		this.intValue = intValue;
	}

	/**
	 * @return the idValuePair
	 */
	public List<IdValuePair> getIdValuePairs()
	{
		return idValuePairs;
	}

	/**
	 * @param idValuePair the idValuePair to set
	 */
	public void setIdValuePairs(List<IdValuePair> idValuePairs)
	{
		this.idValuePairs = idValuePairs;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "IdValuePair [getId()=" + getId() + ", getValue()=" + getValue() + ", getIntValue()=" + getIntValue()
				+ ", getIdValuePair()=" + getIdValuePairs() + "]";
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(IdValuePair o1, IdValuePair o2)
	{
		if (!ValidationUtil.isNull(o1.getIntValue()))
		{
			return o1.getIntValue().compareTo(o2.getIntValue());
		}
		return o1.getValue().compareTo(o2.getValue());
	}

}
