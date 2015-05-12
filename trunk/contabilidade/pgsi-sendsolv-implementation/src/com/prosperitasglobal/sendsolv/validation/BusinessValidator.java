package com.prosperitasglobal.sendsolv.validation;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.prosperitasglobal.cbof.dac.IIndustryClassificationDAC;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.CodeValue;
import com.prosperitasglobal.cbof.model.CodeValueEnum;
import com.prosperitasglobal.cbof.model.request.CodeValueRequest;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.ILocationDAC;
import com.prosperitasglobal.sendsolv.dac.IOrganizationDAC;
import com.prosperitasglobal.sendsolv.model.Business;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * OrganizationValidator is the class that handles all validation for the organization. An organization object requires
 * a number of
 * fields before it can be successfully created. These fields include:
 * <ul>
 * <li>Id
 * <li>Name
 * <li>EmployeeIdentificationNumber
 * <li>NAICSC or SICC
 * <li>Country
 * <li>NumberOfEmployees
 * <li>NumberOfMigrantWorkers
 * <li>RiskLevel
 * <li>Status
 * </ul>
 * This class focuses on validating these fields by checking for Null and NullOrEmpty. The class utilizes the
 * ValidationUtil tool of the QAT framework to perform these checks. If any one of these fields is null or nullOrEmpty
 * an error message is set informing the user that the field is required.
 */
public class BusinessValidator extends ChangeControlValidator implements IValidator
{
	private static final String EIN = "^[0-9]{9}$";

	/** The Constant TWO_HUNDRED_FIFTY_FIVE. */
	private static final Integer TWO_HUNDRED_FIFTY_FIVE = 255;

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_ID_REQUIRED =
			"sendsolv.base.businessvalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NAME_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NAME_REQUIRED =
			"sendsolv.base.businessvalidator.name.required";

	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_EMPLOYERIDENTIFICATIONNUMBER_INVALID =
			"sendsolv.base.businessvalidator.employeridentificationnumber.invalid";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_INVALID_NAICS. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_INVALID_NAICS =
			"sendsolv.base.businessvalidator.invalid.naics";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_INVALID_SIC. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_INVALID_SIC =
			"sendsolv.base.businessvalidator.invalid.sic";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NAICSC_OR_SICC_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NAICSC_OR_SICC_REQUIRED =
			"sendsolv.base.businessvalidator.naicsc.or.sicc.required";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_COUNTRY_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_COUNTRY_REQUIRED =
			"sendsolv.base.businessvalidator.country.required";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NUMBEROFEMPLOYEES_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NUMBEROFEMPLOYEES_REQUIRED =
			"sendsolv.base.businessvalidator.numberofemployees.required";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NUMBEROFMIGRANTWORKERS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NUMBEROFMIGRANTWORKERS_REQUIRED =
			"sendsolv.base.businessvalidator.numberofmigrantworkers.required";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_STATUS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_STATUS_REQUIRED =
			"sendsolv.base.businessvalidator.status.required";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_BUSINESS_TYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_BUSINESS_TYPE_REQUIRED =
			"sendsolv.base.businessvalidator.businesstype.required";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_BUSINESS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_BUSINESS_REQUIRED =
			"sendsolv.base.businessvalidator.business.required";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NAME_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NAME_TOO_BIG =
			"sendsolv.base.businessvalidator.name.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_LOCATION_PARENT_INACTIVE. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_LOCATION_PARENT_NOT_ACTIVE =
			"sendsolv.base.businessvalidator.location.parent.not.active";

	/** The Constant CONTACT_LIST_KEY. */
	protected static final String CONTACT_LIST_KEY = "ContactList";

	/** The industry classification dac. */
	private IIndustryClassificationDAC industryClassificationDAC;

	/** The organization dac. */
	private IOrganizationDAC organizationDAC;

	/** The location dac. */
	private ILocationDAC locationDAC;

	/**
	 * Gets the industry classification dac.
	 *
	 * @return the industry classification dac
	 */
	public IIndustryClassificationDAC getIndustryClassificationDAC()
	{
		return industryClassificationDAC;
	}

	/**
	 * Sets the industry classification dac.
	 *
	 * @param industryClassificationDAC the industry classification dac
	 */
	public void setIndustryClassificationDAC(IIndustryClassificationDAC industryClassificationDAC)
	{
		this.industryClassificationDAC = industryClassificationDAC;
	}

	/**
	 * Gets the organization dac.
	 *
	 * @return the organization dac
	 */
	public IOrganizationDAC getOrganizationDAC()
	{
		return organizationDAC;
	}

	/**
	 * Sets the organization dac.
	 *
	 * @param organizationDAC the organization dac
	 */
	public void setOrganizationDAC(IOrganizationDAC organizationDAC)
	{
		this.organizationDAC = organizationDAC;
	}

	/**
	 * Gets the location dac.
	 *
	 * @return the location dac
	 */
	public ILocationDAC getLocationDAC()
	{
		return locationDAC;
	}

	/**
	 * Sets the location dac.
	 *
	 * @param locationDAC the location dac
	 */
	public void setLocationDAC(ILocationDAC locationDAC)
	{
		this.locationDAC = locationDAC;
	}

	/**
	 * Switches validations depending on the validation context indicator.
	 *
	 * @param validationContext the validation context
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		Business business =
				(Business)validationContext.getObjectToBeValidated(Business.class.getSimpleName());

		// Do the basic check first: do I even have an Object to validate ?
		if (ValidationUtil.isNull(business))
		{
			validationContext.getMessages().add(
					new MessageInfo(PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_BUSINESS_REQUIRED,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
			return;
		}
		performValidation(validationContext, business);

	}

	/**
	 * Perform validation.
	 *
	 * @param validationContext the validation context
	 * @param business the business
	 */
	protected void performValidation(ValidationContext validationContext, E business)
	{
		validateBusinessType(validationContext.getMessages(), business);

		switch (validationContext.getValidationContextIndicator())
		{
			case DELETE:
				validateId(validationContext.getMessages(), business);
				break;
			case INSERT:
				validateAll(validationContext.getMessages(), business);
				break;
			case UPDATE:
				validateId(validationContext.getMessages(), business);
				validateAll(validationContext.getMessages(), business);
				break;
			case FETCH_BY_ID:
				validateBusinessStatus(validationContext.getMessages(), business);
				validateParentStatus(validationContext.getMessages(), business);
				break;
			default:
				validateAll(validationContext.getMessages(), business);
				break;
		}

		if (ValidationContextIndicator.DELETE.equals(validationContext.getValidationContextIndicator()))
		{
			validationContext.setStopProcessing(true);
			return;
		}

		validateChangeControlFields(validationContext.getMessages(), business, validationContext);

		if (!ValidationUtil.isNullOrEmpty(business.getContactList()))
		{
			validationContext.getObjectsToBeValidated().put(CONTACT_LIST_KEY,
					business.getContactList());
		}

	}

	/**
	 * Process additional validations.
	 *
	 * @param validationContext the validation context
	 * @param business the business
	 */
	protected void processAdditionalValidations(ValidationContext validationContext, Business business)
	{

	}

	/**
	 * Validate business type.
	 *
	 * @param list the list
	 * @param business the business
	 */
	private void validateBusinessType(List<MessageInfo> list, Business business)
	{
		ValidationUtil.isNullOrZero(business.getBusinessTypeValue(),
				PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_BUSINESS_TYPE_REQUIRED, list);
	}

	/**
	 * Validate all.
	 *
	 * @param list the list
	 * @param business the business
	 */
	private void validateAll(List<MessageInfo> list, Business business)
	{
		validateName(list, business);
		validateEmployerIdentificationNumber(list, business);
		validateNAICSCAndSICC(list, business);
		validateCountry(list, business);
		validateNumberOfEmployees(list, business);
		validateNumberOfMigrantWorkers(list, business);
		validateBusinessStatus(list, business);
	}

	/**
	 * Validates that either the NAICSC or SICC are not null. Also performs checks for null on the codes of the NAICS
	 * and SIC using the isNaicscValid and isSiccValid methods. If both base's or codes are null, sets an error
	 * message.
	 *
	 * @param list the list
	 * @param business the business
	 */
	private void validateNAICSCAndSICC(List<MessageInfo> list, Business business)
	{

		// If both are null, set message and get out
		if ((ValidationUtil.isNull(business.getNorthAmericanIndustryClassificationSystemCode()) || ValidationUtil
				.isNullOrEmpty(business.getNorthAmericanIndustryClassificationSystemCode().getCode()))
				&& (ValidationUtil.isNull(business.getStandardIndustrialClassificationCode()) || ValidationUtil
						.isNullOrEmpty(business.getStandardIndustrialClassificationCode().getCode())))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NAICSC_OR_SICC_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
			return;
		}

		// Now try to retrieve Ids for filled fields
		// NAICS
		if (!ValidationUtil.isNull(business.getNorthAmericanIndustryClassificationSystemCode())
				&& (!ValidationUtil.isNullOrEmpty(business.getNorthAmericanIndustryClassificationSystemCode()
						.getCode())))
		{
			if (!isNAICSValid(business))
			{
				list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_INVALID_NAICS,
						Message.MessageSeverity.Error,
						Message.MessageLevel.Field));
			}
		}

		// SIC
		if (!ValidationUtil.isNull(business.getStandardIndustrialClassificationCode())
				&& !ValidationUtil.isNullOrEmpty(business.getStandardIndustrialClassificationCode().getCode()))
		{
			if (!isSICValid(business))
			{
				list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_INVALID_SIC,
						Message.MessageSeverity.Error,
						Message.MessageLevel.Field));
			}
		}

	}

	/**
	 * Checks if is naics valid.
	 *
	 * @param business the business
	 * @return boolean
	 *         Checks if either the NAICSC base or code is null. If the base or the code is null, return false,
	 *         otherwise return true.
	 */
	private boolean isNAICSValid(Business business)
	{
		CodeValueRequest request = new CodeValueRequest();
		request.setCodeValueType(CodeValueEnum.NAICS);
		request.setCode(business.getNorthAmericanIndustryClassificationSystemCode().getCode());
		InternalResultsResponse<CodeValue> result = getIndustryClassificationDAC().fetchCodeValueByCode(request);
		if (!ValidationUtil.isNull(result.getFirstResult()))
		{
			business.getNorthAmericanIndustryClassificationSystemCode().setId(result.getFirstResult().getId());
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Checks if is sic valid.
	 *
	 * @param business the business
	 * @return boolean
	 *         Checks if either the SICC base or code is null. If the base or the code is null, return false, otherwise
	 *         return true.
	 */
	private boolean isSICValid(Business business)
	{
		CodeValueRequest request = new CodeValueRequest();
		request.setCodeValueType(CodeValueEnum.SIC);
		request.setCode(business.getStandardIndustrialClassificationCode().getCode());
		InternalResultsResponse<CodeValue> result = getIndustryClassificationDAC().fetchCodeValueByCode(request);
		if (!ValidationUtil.isNull(result.getFirstResult()))
		{
			business.getStandardIndustrialClassificationCode().setId(result.getFirstResult().getId());
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Checks if the Id is null, if true then sets an id required message.
	 *
	 * @param list the list
	 * @param business the business
	 */
	private void validateId(List<MessageInfo> list, Business business)
	{
		ValidationUtil.isNullOrZero(business.getId(),
				PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_ID_REQUIRED, list);
	}

	/**
	 * Checks if the Name is null, if true then sets a Name required message.
	 *
	 * @param list the list
	 * @param business the business
	 */
	private void validateName(List<MessageInfo> list, Business business)
	{
		if (ValidationUtil.isNull(business.getName()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NAME_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
			return;
		}

		if (business.getName().length() > TWO_HUNDRED_FIFTY_FIVE)
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NAME_TOO_BIG,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Checks if the EmployerIdentificationNumber is null, if true then sets an EmployerIdentificationNumber required
	 * message.
	 *
	 * @param list the list
	 * @param business the business
	 */
	private void validateEmployerIdentificationNumber(List<MessageInfo> list, Business business)
	{
		if (!ValidationUtil.isNullOrEmpty(business.getEmployerIdentificationNumber()))
		{
			Pattern pattern = Pattern.compile(EIN);
			Matcher matcher = pattern.matcher(business.getEmployerIdentificationNumber());

			if (!matcher.matches())
			{
				list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_EMPLOYERIDENTIFICATIONNUMBER_INVALID,
						Message.MessageSeverity.Error,
						Message.MessageLevel.Field));
			}
		}
	}

	/**
	 * Checks if the Country is null, if true then sets a Country required message.
	 *
	 * @param list the list
	 * @param business the business
	 */
	private void validateCountry(List<MessageInfo> list, Business business)
	{
		ValidationUtil.isNull(business.getCountry(),
				PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_COUNTRY_REQUIRED, list);
	}

	/**
	 * Checks if the NumberOfEmployees is null, if true then sets a NumberOfEmployees required message.
	 *
	 * @param list the list
	 * @param business the business
	 */
	private void validateNumberOfEmployees(List<MessageInfo> list, Business business)
	{
		ValidationUtil.isNull(business.getNumberOfEmployees(),
				PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NUMBEROFEMPLOYEES_REQUIRED, list);
	}

	/**
	 * Checks if the NumberOfMigrantWorkers is null, if true then sets a NumberOfMigrantWorkers required message.
	 *
	 * @param list the list
	 * @param business the business
	 */
	private void validateNumberOfMigrantWorkers(List<MessageInfo> list, Business business)
	{
		ValidationUtil.isNull(business.getNumberOfMigrantWorkers(),
				PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NUMBEROFMIGRANTWORKERS_REQUIRED, list);
	}

	/**
	 * Validate business status.
	 *
	 * @param list the list
	 * @param business the business
	 */
	private void validateBusinessStatus(List<MessageInfo> list, Business business)
	{
		if (ValidationUtil.isNull(business.getStatus()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_STATUS_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(business)}));
		}
	}

	/**
	 * Validate parent status.
	 *
	 * @param list the list
	 * @param business the business
	 */
	private void validateParentStatus(List<MessageInfo> list, Business business)
	{
		if (ValidationUtil.isNull(business.getBusinessType())
				|| !BusinessTypeEnum.LOCATION.equals(business.getBusinessType()))
		{
			return;
		}
		FetchByIdRequest request = new FetchByIdRequest(business.getId());
		InternalResultsResponse<Location> locResponse = getLocationDAC().fetchLocationById(request);
		if (locResponse.isInError())
		{
			return;
		}

		request.setId(locResponse.getFirstResult().getParentOrganizationId());
		InternalResultsResponse<Organization> orgResponse =
				getOrganizationDAC().fetchOrganizationById(request);
		if (orgResponse.isInError())
		{
			return;
		}

		if (!StatusEnum.ACTIVE.equals(orgResponse.getFirstResult().getStatus())
				&& !StatusEnum.INACTIVE.equals(business.getStatus()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_LOCATION_PARENT_NOT_ACTIVE,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(locResponse.getFirstResult())}));
		}

	}

	private String validateValueToBeReturn(Business business)
	{
		if (!ValidationUtil.isNullOrEmpty(business.getKey()))
		{
			return business.getKey();
		}

		if (!ValidationUtil.isNullOrEmpty(business.getName()))
		{
			return business.getName();
		}

		if (!ValidationUtil.isNullOrZero(business.getId()))
		{
			return String.valueOf(business.getId());
		}
		return null;
	}
}
