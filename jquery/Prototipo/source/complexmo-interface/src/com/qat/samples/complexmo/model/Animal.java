package com.qat.samples.complexmo.model;

import com.qat.framework.model.QATModel;

/**
 * The Class Animal.
 */
public class Animal extends QATModel
{

	/** The id. */
	private Integer id;

	/** The parent key. */
	private Integer parentKey = -1;

	/** The Name. */
	private String name;

	/** The animal type. */
	private AnimalTypeEnum animalType = AnimalTypeEnum.UNKNOWN;

	/** The country of origin. */
	private Country countryOfOrigin;

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
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
	 * @param parentKey the new parent key
	 */
	public void setParentKey(Integer parentKey)
	{
		this.parentKey = parentKey;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Gets the animal type.
	 * 
	 * @return the animal type
	 */
	public AnimalTypeEnum getAnimalType()
	{
		return animalType;
	}

	/**
	 * Sets the animal type.
	 * 
	 * @param animalType the new animal type
	 */
	public void setAnimalType(AnimalTypeEnum animalType)
	{
		this.animalType = animalType;
	}

	/**
	 * Gets the country of origin.
	 * 
	 * @return the country of origin
	 */
	public Country getCountryOfOrigin()
	{
		return countryOfOrigin;
	}

	/**
	 * Sets the country of origin.
	 * 
	 * @param countryOfOrigin the new country of origin
	 */
	public void setCountryOfOrigin(Country countryOfOrigin)
	{
		this.countryOfOrigin = countryOfOrigin;
	}

	/**
	 * Methods that follow the naming pattern get.....Value() provide convenience for returning the primitive value of
	 * an enum. For example, database mapping of an enum to a database column could make use of this method.
	 * 
	 * @return
	 */
	public Integer getAnimalTypeValue()
	{
		return animalType.getValue();
	}

	/**
	 * Methods that follow the naming pattern set.....Value(argValue) provide convenience for assigning the primitive
	 * value of an enum. For example, database mapping of an database column to an enum could make use of this method.
	 * 
	 * @return
	 */
	public void setAnimalTypeValue(Integer animalTypeValue)
	{
		animalType = AnimalTypeEnum.enumForValue(animalTypeValue);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Animal [getId()=" + getId() + ", getParentKey()=" + getParentKey() + ", getName()=" + getName()
				+ ", getAnimalType()=" + getAnimalType() + ", getCountryOfOrigin()=" + getCountryOfOrigin()
				+ ", toString()=" + super.toString() + "]";
	}

}
