package com.prosperitasglobal.cbof.validation;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.Phone;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.ILocationDAC;
import com.prosperitasglobal.sendsolv.dac.IOrganizationDAC;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.PriorityEnum;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * This class contains methods used to validate the required fields of the contact.
 */
public class PhoneListValidator implements IValidator
{

	/** The Constant SPACE. */
	private static final String SPACE = " ";

	/** The Constant CBOF_BASE_CONTACTVALIDATOR_PHONE_NUMBER_REQUIRED. */
	private static final String CBOF_BASE_CONTACTVALIDATOR_PHONE_NUMBER_REQUIRED =
			"cbof.base.contactvalidator.phone.number.required";

	/** The Constant SENDSOLV_BASE_PAYERVALIDATOR_COUNTRYCODE_REQUIRED. */
	private static final String SENDSOLV_BASE_PAYERVALIDATOR_COUNTRYCODE_REQUIRED =
			"sendsolv.base.payervalidator.countrycode.required";

	/** The Constant CBOF_BASE_CONTACTVALIDATOR_PHONE_TYPE_REQUIRED. */
	private static final String CBOF_BASE_CONTACTVALIDATOR_PHONE_TYPE_REQUIRED =
			"cbof.base.contactvalidator.phone.type.required";

	/** The Constant PHONE. */
	private static final String PHONE = "^[0-9]{7,15}$";

	/** The Constant PHONE_EXTENSION. */
	private static final String PHONE_EXTENSION = "^[0-9]{1,10}$";

	/** The Constant CBOF_BASE_CONTACTVALIDATOR_ID_REQUIRED. */
	private static final String CBOF_BASE_CONTACTVALIDATOR_ID_REQUIRED =
			"cbof.base.contactvalidator.id.required";

	/** The Constant CBOF_BASE_CONTACTVALIDATOR_PHONE_NUMBER_INVALID. */
	private static final String CBOF_BASE_CONTACTVALIDATOR_PHONE_NUMBER_INVALID =
			"cbof.base.contactvalidator.phone.number.invalid";

	private static final String CBOF_BASE_CONTACTVALIDATOR_PHONE_EXTENSION_INVALID =
			"cbof.base.contactvalidator.phone.extension.invalid";

	/** The Constant PHONE_LIST_KEY. */
	private static final String PHONE_LIST_KEY = "PhoneList";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ContactListValidator.class);

	/** The Constant CBOF_BASE_CONTACTVALIDATOR_NEED_PRIMARY_PHONE_NUMBER. */
	private static final String CBOF_BASE_CONTACTVALIDATOR_NEED_PRIMARY_PHONE_NUMBER =
			"cbof.base.contactvalidator.phone.primary.required";

	/** The Constant CBOF_BASE_CONTACTVALIDATOR_NEED_ONLY_ONE_PRIMARY_PHONE_NUMBER. */
	private static final String CBOF_BASE_CONTACTVALIDATOR_NEED_ONLY_ONE_PRIMARY_PHONE_NUMBER =
			"cbof.base.contactvalidator.phone.primary.only.one.required";

	/** The person dac. */
	private IPersonDAC personDAC;

	/** The organization dac. */
	private IOrganizationDAC organizationDAC;

	/** The location dac. */
	private ILocationDAC locationDAC;

	/**
	 * @return the organizationDAC
	 */
	public IOrganizationDAC getOrganizationDAC()
	{
		return organizationDAC;
	}

	/**
	 * @param organizationDAC the organizationDAC to set
	 */
	public void setOrganizationDAC(IOrganizationDAC organizationDAC)
	{
		this.organizationDAC = organizationDAC;
	}

	/**
	 * @return the locationDAC
	 */
	public ILocationDAC getLocationDAC()
	{
		return locationDAC;
	}

	/**
	 * @param locationDAC the locationDAC to set
	 */
	public void setLocationDAC(ILocationDAC locationDAC)
	{
		this.locationDAC = locationDAC;
	}

	/**
	 * @return the personDAC
	 */
	public IPersonDAC getPersonDAC()
	{
		return personDAC;
	}

	/**
	 * @param personDAC the personDAC to set
	 */
	public void setPersonDAC(IPersonDAC personDAC)
	{
		this.personDAC = personDAC;
	}

	/**
	 * Switches validations depending on the validation context indicator.
	 *
	 * @param validationContext the validation context
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void validate(ValidationContext validationContext)
	{
		List<Phone> phoneList =
				(List<Phone>)validationContext.getObjectToBeValidated(PHONE_LIST_KEY);

		// Make sure I have a an emailList, because it is optional in some cases.
		if (ValidationUtil.isNullOrEmpty(phoneList))
		{
			return;
		}

		// Make sure at least one of the phones is marked 'primary'
		int primaryCount = 0;

		for (Phone phone : phoneList)
		{
			validatePhone(phone, validationContext);

			if (!PersistanceActionEnum.DELETE.equals(phone.getModelAction()))
			{
				if (PriorityEnum.PRIMARY.equals(phone.getPriority()))
				{
					primaryCount++;
				}
			}
		}
		if (primaryCount == 0)
		{
			validationContext.getMessages().add(
					new MessageInfo(CBOF_BASE_CONTACTVALIDATOR_NEED_PRIMARY_PHONE_NUMBER,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
		}
		else if (primaryCount > 1)
		{
			validationContext.getMessages().add(
					new MessageInfo(CBOF_BASE_CONTACTVALIDATOR_NEED_ONLY_ONE_PRIMARY_PHONE_NUMBER,
							Message.MessageSeverity.Error,
							Message.MessageLevel.Field));
		}
	}

	/**
	 * Validate phone.
	 *
	 * @param phone the phone
	 * @param context the context
	 */
	private void validatePhone(Phone phone, ValidationContext context)
	{
		switch (phone.getModelAction())
		{
			case INSERT:
				validateAll(context.getMessages(), phone);
				break;
			case UPDATE:
				validateId(context.getMessages(), phone);
				validateAll(context.getMessages(), phone);
				break;
			case DELETE:
				validateId(context.getMessages(), phone);
				break;
			default:
				// Context indicator equals FETCH_BY_ID means that is updating the status
				if (ValidationContextIndicator.FETCH_BY_ID.equals(context.getValidationContextIndicator()))
				{
					validateAll(context.getMessages(), phone);
				}
				else
				{
					if (LOG.isDebugEnabled())
					{
						LOG.debug("Model action for Phone Validator missing!");
					}
				}
				break;
		}
	}

	/**
	 * Validate all.
	 *
	 * @param list the list
	 * @param phone the phone
	 */
	private void validateAll(List<MessageInfo> list, Phone phone)
	{
		validatePhoneNumber(list, phone);
		validatePhoneExtension(list, phone);
		validatePhoneCountryCode(list, phone);
		validatePhoneType(list, phone);
	}

	/**
	 * Checks if the Id is null, if true then sets an id required message.
	 *
	 * @param list the list
	 * @param phone the phone
	 */
	private void validateId(List<MessageInfo> list, Phone phone)
	{
		ValidationUtil.isNullOrZero(phone.getId(), CBOF_BASE_CONTACTVALIDATOR_ID_REQUIRED, list);
	}

	/**
	 * Validate phone number.
	 *
	 * @param list the list
	 * @param phone the phone
	 */
	private void validatePhoneNumber(List<MessageInfo> list, Phone phone)
	{
		if (ValidationUtil.isNullOrEmpty(phone.getNumber()))
		{
			list.add(new MessageInfo(CBOF_BASE_CONTACTVALIDATOR_PHONE_NUMBER_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {phone.getParentKeyType() + SPACE + phone.getParentKey()}));
		}
		Pattern pattern = Pattern.compile(PHONE);
		Matcher matcher = pattern.matcher(phone.getNumber());

		if (!matcher.matches())
		{
			list.add(new MessageInfo(CBOF_BASE_CONTACTVALIDATOR_PHONE_NUMBER_INVALID,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field));
		}
	}

	/**
	 * Validate phone extension.
	 *
	 * @param list the list
	 * @param phone the phone
	 */
	private void validatePhoneExtension(List<MessageInfo> list, Phone phone)
	{
		if (ValidationUtil.isNullOrEmpty(phone.getExtension()))
		{
			return;
		}

		Pattern pattern = Pattern.compile(PHONE_EXTENSION);
		Matcher matcher = pattern.matcher(phone.getExtension());

		if (!matcher.matches())
		{
			list.add(new MessageInfo(CBOF_BASE_CONTACTVALIDATOR_PHONE_EXTENSION_INVALID,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(phone.getParentKeyType(), phone.getParentKey())}));
		}
	}

	/**
	 * Validate phone country code.
	 *
	 * @param list the list
	 * @param phone the phone
	 */
	private void validatePhoneCountryCode(List<MessageInfo> list, Phone phone)
	{
		if (ValidationUtil.isNull(phone.getCountry()) || ValidationUtil.isNullOrEmpty(phone.getCountry().getCode()))
		{
			list.add(new MessageInfo(SENDSOLV_BASE_PAYERVALIDATOR_COUNTRYCODE_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(phone.getParentKeyType(), phone.getParentKey())}));
		}

	}

	/**
	 * Validate phone type.
	 *
	 * @param list the list
	 * @param phone the phone
	 */
	private void validatePhoneType(List<MessageInfo> list, Phone phone)
	{
		if (ValidationUtil.isNull(phone.getContactType()))
		{
			list.add(new MessageInfo(CBOF_BASE_CONTACTVALIDATOR_PHONE_TYPE_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(phone.getParentKeyType(), phone.getParentKey())}));
		}
	}

	/**
	 * Fetch member by id.
	 *
	 * @param request the request
	 * @return the member
	 */
	private Member fetchMemberById(FetchByIdRequest request)
	{
		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberById(request);

		if (!response.isInError())
		{
			return response.getFirstResult();
		}
		return null;
	}

	/**
	 * Fetch location by id.
	 *
	 * @param request the request
	 * @return the location
	 */
	private Location fetchLocationById(FetchByIdRequest request)
	{
		InternalResultsResponse<Location> response = getLocationDAC().fetchLocationById(request);

		if (!response.isInError())
		{
			return response.getFirstResult();
		}
		return null;
	}

	/**
	 * Fetch organization by id.
	 *
	 * @param request the request
	 * @return the organization
	 */
	private Organization fetchOrganizationById(FetchByIdRequest request)
	{
		InternalResultsResponse<Organization> response = getOrganizationDAC().fetchOrganizationById(request);

		if (!response.isInError())
		{
			return response.getFirstResult();
		}
		return null;
	}

	/**
	 * Validate value to be return.
	 *
	 * @param parentKeyType the parent key type
	 * @param parentKey the parent key
	 * @return the string
	 */
	private String validateValueToBeReturn(BusinessTypeEnum parentKeyType, Integer parentKey)
	{
		FetchByIdRequest request = new FetchByIdRequest(parentKey);
		switch (parentKeyType)
		{
			case MEMBER:
				Member member = fetchMemberById(request);
				if (!ValidationUtil.isNull(member))
				{
					return String.valueOf(parentKeyType).toLowerCase() + SPACE + member.getParticipantId();
				}
				break;
			case ORGANIZATION:
				Organization organization = fetchOrganizationById(request);
				if (!ValidationUtil.isNull(organization))
				{
					return String.valueOf(parentKeyType).toLowerCase() + SPACE + organization.getName();
				}
				break;
			case LOCATION:
				Location location = fetchLocationById(request);
				if (!ValidationUtil.isNull(location))
				{
					return String.valueOf(parentKeyType).toLowerCase() + SPACE + location.getName();
				}
				break;
			default:
				break;
		}
		return String.valueOf(parentKeyType).toLowerCase();
	}

}
