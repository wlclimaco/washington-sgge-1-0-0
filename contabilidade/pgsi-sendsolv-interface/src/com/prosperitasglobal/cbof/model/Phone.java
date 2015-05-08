package com.prosperitasglobal.cbof.model;

/**
 * The Class Phone.
 */
@SuppressWarnings("serial")
public class Phone extends Contact
{

	/** The country. */
	private Country country;

	/** The number. */
	private String number;

	/** The extension. */
	private String extension;

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public Country getCountry()
	{
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the country
	 */
	public void setCountry(Country country)
	{
		this.country = country;
	}

	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public String getNumber()
	{
		return number;
	}

	/**
	 * Sets the number.
	 *
	 * @param number the number
	 */
	public void setNumber(String number)
	{
		this.number = number;
	}

	/**
	 * Gets the extension.
	 *
	 * @return the extension
	 */
	public String getExtension()
	{
		return extension;
	}

	/**
	 * Sets the extension.
	 *
	 * @param extension the extension
	 */
	public void setExtension(String extension)
	{
		this.extension = extension;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Phone [getCountry()=" + getCountry() + ", getNumber()=" + getNumber() + ", getExtension()="
				+ getExtension() + ", toString()=" + super.toString() + "]";
	}

}
