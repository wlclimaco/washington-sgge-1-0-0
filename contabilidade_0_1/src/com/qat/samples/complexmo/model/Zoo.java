package com.qat.samples.complexmo.model;

import java.util.List;

import com.qat.framework.model.QATModelOL;

// TODO: Auto-generated Javadoc
/**
 * The Class Zoo.
 */
public class Zoo extends QATModelOL
{

	/** The id. */
	private Integer id;

	/** The code. */
	private String code;

	/** The animal list. */
	private List<Animal> animalList;

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
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Gets the animal list.
	 * 
	 * @return the animalList
	 */
	public List<Animal> getAnimalList()
	{
		return animalList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Zoo [id=" + id + ", code=" + code + ", animalList=" + animalList + ", toString()=" + super.toString()
				+ "]";
	}

}
