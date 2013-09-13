package com.sensus.dm.common.device.validation;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.cbof.model.Location;

/**
 * The Class LocationValidator.
 * 
 * @author QAT Global.
 */
public class LocationValidator implements IValidator
{

	/** The Constant LOCATION_REQUIRED. */
	private static final String LOCATION_REQUIRED = "sensus.epm.locationvalidator.location.required";

	/** The Constant ADDRESS_INVALID. */
	private static final String ADDRESS_INVALID = "sensus.epm.devicevalidator.address.invalid";

	/** The Constant CITY_INVALID. */
	private static final String CITY_INVALID = "sensus.epm.devicevalidator.city.invalid";

	/** The Constant ZIP_INVALID. */
	private static final String ZIP_INVALID = "sensus.epm.devicevalidator.zip.invalid";

	/** The address length. */
	private int addressLength;

	/** The city length. */
	private int cityLength;

	/** The zip length. */
	private int zipLength;

	/**
	 * Gets the address length.
	 * 
	 * @return the address length
	 */
	public int getAddressLength()
	{
		return addressLength;
	}

	/**
	 * Sets the address length.
	 * 
	 * @param addressLength the new address length
	 */
	public void setAddressLength(int addressLength)
	{
		this.addressLength = addressLength;
	}

	/**
	 * Gets the city length.
	 * 
	 * @return the city length
	 */
	public int getCityLength()
	{
		return cityLength;
	}

	/**
	 * Sets the city length.
	 * 
	 * @param cityLength the new city length
	 */
	public void setCityLength(int cityLength)
	{
		this.cityLength = cityLength;
	}

	/**
	 * Gets the zip length.
	 * 
	 * @return the zip length
	 */
	public int getZipLength()
	{
		return zipLength;
	}

	/**
	 * Sets the zip length.
	 * 
	 * @param zipLength the new zip length
	 */
	public void setZipLength(int zipLength)
	{
		this.zipLength = zipLength;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		DMPersistanceActionEnum action =
				(DMPersistanceActionEnum)validationContext.getValidationArguments().get(
						DMPersistanceActionEnum.getDefaultName());

		Location location = (Location)validationContext.getObjectToBeValidated(Location.class.getSimpleName());

		if (ValidationUtil.isNull(action) || ValidationUtil.isNull(location))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(LOCATION_REQUIRED));
			return;
		}

		switch (action)
		{
			case FETCH_ALL:
				validateAddress(validationContext.getMessages(), location);
				validateCity(validationContext.getMessages(), location);
				validateZip(validationContext.getMessages(), location);
				break;

			default:
				break;
		}
	}

	/**
	 * Validate address.
	 * 
	 * @param list the list
	 * @param location the location
	 */
	private void validateAddress(List<MessageInfo> list, Location location)
	{
		if (!ValidationUtil.isNull(location.getAddress())
				&& (location.getAddress().length() > getAddressLength()))
		{
			list.add(MessageInfo.createFieldValidationError(ADDRESS_INVALID, getAddressLength()));
		}
	}

	/**
	 * Validate city.
	 * 
	 * @param list the list
	 * @param location the location
	 */
	private void validateCity(List<MessageInfo> list, Location location)
	{
		if (!ValidationUtil.isNull(location.getCity())
				&& (location.getCity().length() > getCityLength()))
		{
			list.add(MessageInfo.createFieldValidationError(CITY_INVALID, getCityLength()));
		}
	}

	/**
	 * Validate zip.
	 * 
	 * @param list the list
	 * @param location the location
	 */
	private void validateZip(List<MessageInfo> list, Location location)
	{
		if (!ValidationUtil.isNull(location.getZip()) && (location.getZip().length() > getZipLength()))
		{
			list.add(MessageInfo.createFieldValidationError(ZIP_INVALID, getZipLength()));
		}
	}

}
