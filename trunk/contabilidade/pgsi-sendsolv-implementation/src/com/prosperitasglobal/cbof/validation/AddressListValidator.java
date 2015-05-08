package com.prosperitasglobal.cbof.validation;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.Address;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.ILocationDAC;
import com.prosperitasglobal.sendsolv.dac.IOrganizationDAC;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationUtil;

/**
 * This class contains methods used to validate the required fields of the contact.
 */
public class AddressListValidator implements IValidator
{

	/** The Constant SPACE. */
	private static final String SPACE = " ";

	/** The Constant PROSPERITASGLOBAL_BASE_ADDRESSVALIDATOR_COUNTRY_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_ADDRESSVALIDATOR_COUNTRY_REQUIRED =
			"sendsolv.base.addressvalidator.country.required";

	/** The Constant PROSPERITASGLOBAL_BASE_ADDRESSVALIDATOR_STATE_PROVINCE_REGION_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_ADDRESSVALIDATOR_STATE_PROVINCE_REGION_REQUIRED =
			"sendsolv.base.addressvalidator.state.province.region.required";

	/** The Constant PROSPERITASGLOBAL_BASE_ADDRESSVALIDATOR_CITY_NAME_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_ADDRESSVALIDATOR_CITY_NAME_REQUIRED =
			"sendsolv.base.addressvalidator.city.name.required";

	/** The Constant PROSPERITASGLOBAL_BASE_ADDRESSVALIDATOR_ADDRESS_LINE1_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_ADDRESSVALIDATOR_ADDRESS_LINE1_REQUIRED =
			"sendsolv.base.addressvalidator.address.line1.required";

	/** The Constant ADDRESSVALIDATOR_ADDRESS_TOO_BIG. */
	private static final String ADDRESSVALIDATOR_ADDRESS_TOO_BIG = "sendsolv.base.addressvalidator.address.too.big";

	/** The Constant ADDRESSVALIDATOR_CITY_NAME_TOO_BIG. */
	private static final String ADDRESSVALIDATOR_CITY_NAME_TOO_BIG = "sendsolv.base.addressvalidator.city.name.too.big";

	/** The Constant ONE. */
	private static final Integer ONE = 1;

	/** The Constant TWO. */
	private static final Integer TWO = 2;

	/** The Constant THREE. */
	private static final Integer THREE = 3;

	/** The Constant FOUR. */
	private static final Integer FOUR = 4;

	/** The Constant ADDRESS_LINE4. */
	private static final String ADDRESS_LINE4 = "addressLine4";

	/** The Constant ADDRESS_LINE3. */
	private static final String ADDRESS_LINE3 = "addressLine3";

	/** The Constant ADDRESS_LINE2. */
	private static final String ADDRESS_LINE2 = "addressLine2";

	/** The Constant ADDRESS_LINE1. */
	private static final String ADDRESS_LINE1 = "addressLine1";

	/** The Constant CITY_NAME. */
	private static final String CITY_NAME = "cityName";

	/** The Constant CBOF_BASE_CONTACTVALIDATOR_ID_REQUIRED. */
	private static final String CBOF_BASE_CONTACTVALIDATOR_ID_REQUIRED =
			"cbof.base.contactvalidator.id.required";

	/** The Constant CBOF_BASE_CONTACTVALIDATOR_POSTAL_CODE_INVALID. */
	private static final String CBOF_BASE_CONTACTVALIDATOR_POSTAL_CODE_INVALID =
			"cbof.base.contactvalidator.postal.code.invalid";

	/** The Constant POSTAL_CODE. */
	private static final String POSTAL_CODE = "\\d{5}([ \\-]\\d{4})?";

	/** The Constant ADDRESS_LIST_KEY. */
	private static final String ADDRESS_LIST_KEY = "AddressList";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AddressListValidator.class);

	/** The contact valid length. */
	private Map<String, Integer> contactValidLength;

	/** The person dac. */
	private IPersonDAC personDAC;

	/** The organization dac. */
	private IOrganizationDAC organizationDAC;

	/** The location dac. */
	private ILocationDAC locationDAC;

	/**
	 * Gets the organization dac.
	 *
	 * @return the organizationDAC
	 */
	public IOrganizationDAC getOrganizationDAC()
	{
		return organizationDAC;
	}

	/**
	 * Sets the organization dac.
	 *
	 * @param organizationDAC the organizationDAC to set
	 */
	public void setOrganizationDAC(IOrganizationDAC organizationDAC)
	{
		this.organizationDAC = organizationDAC;
	}

	/**
	 * Gets the location dac.
	 *
	 * @return the locationDAC
	 */
	public ILocationDAC getLocationDAC()
	{
		return locationDAC;
	}

	/**
	 * Sets the location dac.
	 *
	 * @param locationDAC the locationDAC to set
	 */
	public void setLocationDAC(ILocationDAC locationDAC)
	{
		this.locationDAC = locationDAC;
	}

	/**
	 * Gets the person dac.
	 *
	 * @return the personDAC
	 */
	public IPersonDAC getPersonDAC()
	{
		return personDAC;
	}

	/**
	 * Sets the person dac.
	 *
	 * @param personDAC the personDAC to set
	 */
	public void setPersonDAC(IPersonDAC personDAC)
	{
		this.personDAC = personDAC;
	}

	/**
	 * Gets the contact valid length.
	 *
	 * @return the contact valid length
	 */
	public Map<String, Integer> getContactValidLength()
	{
		return contactValidLength;
	}

	/**
	 * Sets the contact valid length.
	 *
	 * @param contactValidLength the contact valid length
	 */
	public void setContactValidLength(Map<String, Integer> contactValidLength)
	{
		this.contactValidLength = contactValidLength;
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
		List<Address> addressList =
				(List<Address>)validationContext.getObjectToBeValidated(ADDRESS_LIST_KEY);

		// Make sure I have a an emailList, because it is optional in some cases.
		if (ValidationUtil.isNullOrEmpty(addressList))
		{
			return;
		}

		for (Address address : addressList)
		{
			validateAddress(address, validationContext);
		}

	}

	/**
	 * Validate address.
	 *
	 * @param address the address
	 * @param context the context
	 */
	private void validateAddress(Address address, ValidationContext context)
	{
		switch (address.getModelAction())
		{
			case INSERT:
				validateAll(context.getMessages(), address);
				break;
			case UPDATE:
				validateId(context.getMessages(), address);
				validateAll(context.getMessages(), address);
				break;
			case DELETE:
				validateId(context.getMessages(), address);
				break;
			default:
				// Context indicator equals FETCH_BY_ID means that is updating the status
				if (ValidationContextIndicator.FETCH_BY_ID.equals(context.getValidationContextIndicator()))
				{
					validateAll(context.getMessages(), address);
				}
				else
				{
					if (LOG.isDebugEnabled())
					{
						LOG.debug("Model action for Address Validator missing!");
					}
				}
				break;
		}
	}

	/**
	 * Validate all.
	 *
	 * @param list the list
	 * @param address the address
	 */
	private void validateAll(List<MessageInfo> list, Address address)
	{
		validatePostal(list, address);
		validateCityName(list, address);
		validateAddressLine1(list, address);
		validateAddressLine2(list, address);
		validateAddressLine3(list, address);
		validateAddressLine4(list, address);
		validateStateProvinceRegion(list, address);
		validateAddressCountry(list, address);
	}

	/**
	 * Checks if the Id is null, if true then sets an id required message.
	 *
	 * @param list the list
	 * @param contact the contact
	 */
	private void validateId(List<MessageInfo> list, Contact contact)
	{
		ValidationUtil.isNullOrZero(contact.getId(), CBOF_BASE_CONTACTVALIDATOR_ID_REQUIRED, list);
	}

	/**
	 * Validate postal.
	 *
	 * @param list the list
	 * @param address the address
	 */
	private void validatePostal(List<MessageInfo> list, Address address)
	{
		Pattern pattern = Pattern.compile(POSTAL_CODE);
		Matcher matcher = pattern.matcher(address.getPostalCode());

		if (!matcher.matches())
		{
			list.add(new MessageInfo(CBOF_BASE_CONTACTVALIDATOR_POSTAL_CODE_INVALID,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(address.getParentKeyType(), address.getParentKey())}));
		}
	}

	/**
	 * Validate city name.
	 *
	 * @param list the list
	 * @param address the address
	 */
	private void validateCityName(List<MessageInfo> list, Address address)
	{
		if (ValidationUtil.isNull(address.getCityName()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_ADDRESSVALIDATOR_CITY_NAME_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(address.getParentKeyType(), address.getParentKey())}));
		}

		if (address.getCityName().length() > getContactValidLength().get(CITY_NAME))
		{
			list.add(new MessageInfo(ADDRESSVALIDATOR_CITY_NAME_TOO_BIG,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(address.getParentKeyType(), address.getParentKey())}));
		}
	}

	/**
	 * Validate address line1.
	 *
	 * @param list the list
	 * @param address the address
	 */
	private void validateAddressLine1(List<MessageInfo> list, Address address)
	{
		if (ValidationUtil.isNull(address.getAddressLine1()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_ADDRESSVALIDATOR_ADDRESS_LINE1_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field, new Object[] {ONE}));
		}

		if (address.getAddressLine1().length() > getContactValidLength().get(ADDRESS_LINE1))
		{
			list.add(new MessageInfo(ADDRESSVALIDATOR_ADDRESS_TOO_BIG,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field, new Object[] {ONE,
					validateValueToBeReturn(address.getParentKeyType(), address.getParentKey())}));
		}
	}

	/**
	 * Validate address line2.
	 *
	 * @param list the list
	 * @param address the address
	 */
	private void validateAddressLine2(List<MessageInfo> list, Address address)
	{
		if (ValidationUtil.isNull(address.getAddressLine2()))
		{
			return;
		}

		if (address.getAddressLine2().length() > getContactValidLength().get(ADDRESS_LINE2))
		{
			list.add(new MessageInfo(ADDRESSVALIDATOR_ADDRESS_TOO_BIG,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field, new Object[] {TWO,
					validateValueToBeReturn(address.getParentKeyType(), address.getParentKey())}));
		}
	}

	/**
	 * Validate address line3.
	 *
	 * @param list the list
	 * @param address the address
	 */
	private void validateAddressLine3(List<MessageInfo> list, Address address)
	{
		if (ValidationUtil.isNull(address.getAddressLine3()))
		{
			return;
		}

		if (address.getAddressLine3().length() > getContactValidLength().get(ADDRESS_LINE3))
		{
			list.add(new MessageInfo(ADDRESSVALIDATOR_ADDRESS_TOO_BIG,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field, new Object[] {THREE,
					validateValueToBeReturn(address.getParentKeyType(), address.getParentKey())}));
		}
	}

	/**
	 * Validate address line4.
	 *
	 * @param list the list
	 * @param address the address
	 */
	private void validateAddressLine4(List<MessageInfo> list, Address address)
	{
		if (ValidationUtil.isNull(address.getAddressLine4()))
		{
			return;
		}

		if (address.getAddressLine4().length() > getContactValidLength().get(ADDRESS_LINE4))
		{
			list.add(new MessageInfo(ADDRESSVALIDATOR_ADDRESS_TOO_BIG,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field, new Object[] {FOUR,
					validateValueToBeReturn(address.getParentKeyType(), address.getParentKey())}));
		}
	}

	/**
	 * Validate state province region.
	 *
	 * @param list the list
	 * @param address the address
	 */
	private void validateStateProvinceRegion(List<MessageInfo> list, Address address)
	{
		if (ValidationUtil.isNull(address.getStateProvinceRegion()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_ADDRESSVALIDATOR_STATE_PROVINCE_REGION_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field, new Object[] {validateValueToBeReturn(address.getParentKeyType(),
							address.getParentKey())}));
		}
	}

	/**
	 * Validate address country.
	 *
	 * @param list the list
	 * @param address the address
	 */
	private void validateAddressCountry(List<MessageInfo> list, Address address)
	{
		if (ValidationUtil.isNull(address.getCountry()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_ADDRESSVALIDATOR_COUNTRY_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field, new Object[] {validateValueToBeReturn(address.getParentKeyType(),
							address.getParentKey())}));
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
