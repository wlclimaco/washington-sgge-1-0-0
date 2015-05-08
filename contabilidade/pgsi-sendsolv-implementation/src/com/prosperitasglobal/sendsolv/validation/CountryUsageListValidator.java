package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import com.prosperitasglobal.cbof.dac.ICountryDAC;
import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.model.CountryUsage;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMessageUtil;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CountryUsageListValidator. Validates a {@link List} of {@link CountryUsage} objects for validity.
 */
public class CountryUsageListValidator extends ChangeControlValidator implements IValidator
{
	/** The implementation of the {@link ICountryDAC} interface. Injected by Spring. */
	private ICountryDAC countryDAC;

	/** The implementation of the {@link IPersonDAC} interface. Injected by Spring. */
	private IPersonDAC personDAC;

	/** The Constant COUNTRY_USAGE_LIST_KEY. */
	protected static final String COUNTRY_USAGE_LIST_KEY = "CountryUsageList";

	/** The error code used when a country is not found in the SendSolv system. */
	public static final String COUNTRY_NOT_FOUND =
			"sendsolv.base.countryusagevalidator.country.not.found";

	/** The error code used when the country is missing. */
	public static final String COUNTRY_REQUIRED =
			"sendsolv.base.countryusagevalidator.country.required";

	/** The error code used when the country usage for Birth is not present. */
	public static final String USAGE_BIRTH_REQUIRED =
			"sendsolv.base.countryusagevalidator.country.usage.birth.required";

	/** The error code used when the country usage for Citizenship is not present. */
	public static final String USAGE_CITIZENSHIP_REQUIRED =
			"sendsolv.base.countryusagevalidator.country.usage.citizenship.required";

	/** The error code used when the country usage list is missing from the member. */
	public static final String COUNTRY_USAGE_LIST_REQUIRED =
			"sendsolv.base.countryusagevalidator.country.usage.list.required";

	/** The error code used when the country usage enumeration is not present. */
	public static final String USAGE_REQUIRED =
			"sendsolv.base.countryusagevalidator.country.usage.required";

	/** The error code used when the country usage for Residence is not present. */
	public static final String USAGE_RESIDENCE_REQUIRED =
			"sendsolv.base.countryusagevalidator.country.usage.residence.required";

	/** The error code used when the id of the country usage object is not found. */
	public static final String ID_NOT_FOUND =
			"sendsolv.base.countryusagevalidator.id.not.found";

	/** The error code used when the id of the country usage object is missing. */
	public static final String ID_REQUIRED =
			"sendsolv.base.countryusagevalidator.id.required";

	/**
	 * Fetch the {@link Country} object using the code of the {@link Country} attribute of the {@link CountryUsage}
	 * object being validated.
	 *
	 * @param countryUsage The {@link CountryUsage} object being validated.
	 * @return The {@link Country} object that contains the code. If not found or errors fetching, <code>null</code>
	 *         will be returned.
	 */
	private Country fetchCountryByCode(CountryUsage countryUsage)
	{
		InternalResultsResponse<Country> countryResponse =
				getCountryDAC().fetchCountryByCode(countryUsage.getCountry().getCode());

		if (countryResponse.isInError())
		{
			return null;
		}

		return countryResponse.getFirstResult();
	}

	/**
	 * Fetch the {@link CountryUsage} object using the id of the {@link CountryUsage} object being validated.
	 *
	 * @param countryUsage The {@link CountryUsage} object being validated.
	 * @return The {@link CountryUsage} object that contains the id. If not found or errors fetching, <code>null</code>
	 *         will be returned.
	 */
	private CountryUsage fetchCountryUsageById(CountryUsage countryUsage)
	{
		InternalResultsResponse<CountryUsage> countryUsageResponse =
				getPersonDAC().fetchCountryUsageById(countryUsage.getId());

		if (countryUsageResponse.isInError())
		{
			return null;
		}

		return countryUsageResponse.getFirstResult();
	}

	/**
	 * Validation that applies to all actions.
	 *
	 * @param messages The {@link List} of {@link MessageInfo}'s. Used to convey errors.
	 * @param countryUsage The {@link CountryUsage} object to validate.
	 */
	private void validateAll(List<MessageInfo> messages, CountryUsage countryUsage)
	{
		validateCountry(messages, countryUsage);
		validateCountryUsage(messages, countryUsage);
	}

	/**
	 * Validate that the country is present, and that it is a valid Country in the SendSolv system.
	 *
	 * @param messages The {@link List} of {@link MessageInfo}'s. Used to convey errors.
	 * @param countryUsage The {@link CountryUsage} object to validate.
	 */
	private void validateCountry(List<MessageInfo> messages, CountryUsage countryUsage)
	{
		// if country is missing, we have an error because it is required.
		if (ValidationUtil.isNull(countryUsage.getCountry()))
		{
			String countryUsageTag = QATMessageUtil.getMessage(countryUsage.getUsage().getLabelKey());
			messages.add(new MessageInfo(COUNTRY_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field, new Object[] {countryUsageTag}));
		}
		// if country is not found in SendSolv, we have an error.
		else if (ValidationUtil.isNull(fetchCountryByCode(countryUsage)))
		{
			String countryName = countryUsage.getCountry().getDescription();
			String countryUsageTag = QATMessageUtil.getMessage(countryUsage.getUsage().getLabelKey());
			if (ValidationUtil.isNull(countryName))
			{
				countryName = "None Specified";
			}

			messages.add(new MessageInfo(COUNTRY_NOT_FOUND,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field, new Object[] {countryUsageTag, countryName}));
		}
	}

	/**
	 * Validate the {@link CountryUsage} object.
	 *
	 * @param countryUsage The {@link CountryUsage} object to be validated.
	 * @param context The validation context object.
	 */
	private void validateCountryUsage(CountryUsage countryUsage, ValidationContext context)
	{
		switch (context.getValidationContextIndicator())
		{
			case INSERT:
			case FETCH_BY_ID:
				validateAll(context.getMessages(), countryUsage);
				break;
			case UPDATE:
				validateAll(context.getMessages(), countryUsage);
				validateId(context.getMessages(), countryUsage);
				break;
			default:
				break;
		}
	}

	/**
	 * Validate that the country usage is present. Required for processing.
	 *
	 * @param messages The {@link List} of {@link MessageInfo}'s. Used to convey errors.
	 * @param countryUsage The {@link CountryUsage} object to validate.
	 */
	private void validateCountryUsage(List<MessageInfo> messages, CountryUsage countryUsage)
	{
		// if the country usage enumeration value is missing, we have an error.
		if (ValidationUtil.isNull(countryUsage.getUsage()))
		{
			messages.add(new MessageInfo(USAGE_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Validate that an instance of BIRTH, CITIZENSHIP, and RESIDENCE exists in the {@link List} of {@link CountryUsage}
	 * objects.
	 *
	 * @param messages The {@link List} of {@link MessageInfo}'s. Used to convey errors.
	 * @param countryUsage The {@link CountryUsage} object to validate.
	 */
	private void validateCountryUsageList(List<MessageInfo> messages, List<CountryUsage> countryUsageList)
	{
		// Need to validate that a BIRTH, CITENSHIP, and RESIDENCE country usage exists in the List.
		boolean birthFound = false;
		boolean citizenshipFound = false;
		boolean residenceFound = false;
		for (CountryUsage countryUsage : countryUsageList)
		{
			if (!ValidationUtil.isNull(countryUsage.getUsage()))
			{
				switch (countryUsage.getUsage())
				{
					case BIRTH:
						birthFound = true;
						break;
					case RESIDENCE:
						residenceFound = true;
						break;
					case CITIZENSHIP:
						citizenshipFound = true;
						break;
					default:
						break;
				}
			}
		}

		// if a country usage wasn't found for Birth, we have an error.
		if (!birthFound)
		{
			messages.add(new MessageInfo(USAGE_BIRTH_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}

		// if a country usage wasn't found for Residence, we have an error.
		if (!residenceFound)
		{
			messages.add(new MessageInfo(USAGE_RESIDENCE_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}

		// if a country usage wasn't found for Citizenship, we have an error.
		if (!citizenshipFound)
		{
			messages.add(new MessageInfo(USAGE_CITIZENSHIP_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Validate that the id of the {@link CountryUsage} object is present, and that it exists in the SendSolv system.
	 *
	 * @param messages The {@link List} of {@link MessageInfo}'s. Used to convey errors.
	 * @param countryUsage The {@link CountryUsage} object to validate.
	 */
	private void validateId(List<MessageInfo> messages, CountryUsage countryUsage)
	{
		// if the id of the country usage is missing, we have an error.
		if (ValidationUtil.isNull(countryUsage.getId()))
		{
			messages.add(new MessageInfo(ID_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
			return;
		}
		// if the id of the country usage object is not found in SendSolv, we have an error.
		else if (ValidationUtil.isNull(fetchCountryUsageById(countryUsage)))
		{
			messages.add(new MessageInfo(ID_NOT_FOUND,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field, new Object[] {countryUsage.getId()}));
		}
	}

	/**
	 * Get the implementation of the {@link ICountryDAC} interface. Injected by Spring.
	 *
	 * @return The implementation of the {@link ICountryDAC} interface.
	 */
	public ICountryDAC getCountryDAC()
	{
		return countryDAC;
	}

	/**
	 * Get the implementation of the {@link IPersonDAC} interface. Injected by Spring.
	 *
	 * @return The implementation of the {@link IPersonDAC} interface.
	 */
	public IPersonDAC getPersonDAC()
	{
		return personDAC;
	}

	/**
	 * Set the implementation of the {@link ICountryDAC} interface. Injected by Spring.
	 *
	 * @param countryDAC The implementation of the {@link ICountryDAC} interface to set.
	 */
	public void setCountryDAC(ICountryDAC countryDAC)
	{
		this.countryDAC = countryDAC;
	}

	/**
	 * Set the implementation of the {@link IPersonDAC} interface. Injected by Spring.
	 *
	 * @param personDAC The implementation of the {@link IPersonDAC} interface to set.
	 */
	public void setPersonDAC(IPersonDAC personDAC)
	{
		this.personDAC = personDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.validation.IValidator#validate(com.qat.framework.validation.ValidationContext)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void validate(ValidationContext validationContext)
	{
		List<CountryUsage> countryUsageList =
				(List<CountryUsage>)validationContext.getObjectToBeValidated(COUNTRY_USAGE_LIST_KEY);

		if (ValidationUtil.isNullOrEmpty(countryUsageList))
		{
			return;
		}

		for (CountryUsage countryUsage : countryUsageList)
		{
			if (!ValidationContextIndicator.DELETE.equals(validationContext.getValidationContextIndicator()))
			{
				validateChangeControlFields(validationContext.getMessages(), countryUsage, validationContext);
			}

			validateCountryUsage(countryUsage, validationContext);
		}

		validateCountryUsageList(validationContext.getMessages(), countryUsageList);
	}
}
