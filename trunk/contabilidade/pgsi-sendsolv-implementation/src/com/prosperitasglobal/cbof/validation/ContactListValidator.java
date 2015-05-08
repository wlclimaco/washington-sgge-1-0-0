package com.prosperitasglobal.cbof.validation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.Address;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.cbof.model.Email;
import com.prosperitasglobal.cbof.model.Phone;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.ILocationDAC;
import com.prosperitasglobal.sendsolv.dac.IOrganizationDAC;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.validation.ChangeControlValidator;
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
public class ContactListValidator extends ChangeControlValidator implements IValidator
{

	/** The Constant CONTACT_LIST_KEY. */
	private static final String CONTACT_LIST_KEY = "ContactList";

	/** The Constant EMAIL_LIST_KEY. */
	private static final String EMAIL_LIST_KEY = "EmailList";

	/** The Constant ADDRESS_LIST_KEY. */
	private static final String ADDRESS_LIST_KEY = "AddressList";

	/** The Constant PHONE_LIST_KEY. */
	private static final String PHONE_LIST_KEY = "PhoneList";

	/** The Constant SPACE. */
	private static final String SPACE = " ";

	/** The Constant SENDSOLV_BASE_CONTACTLISTVALIDATOR_EMAIL_REQUIRED. */
	private static final String SENDSOLV_BASE_CONTACTLISTVALIDATOR_EMAIL_REQUIRED =
			"sendsolv.base.contactlistvalidator.email.required";

	/** The Constant SENDSOLV_BASE_CONTACTLISTVALIDATOR_PHONE_REQUIRED. */
	private static final String SENDSOLV_BASE_CONTACTLISTVALIDATOR_PHONE_REQUIRED =
			"sendsolv.base.contactlistvalidator.phone.required";

	/** The Constant SENDSOLV_BASE_CONTACTLISTVALIDATOR_ADDRESS_REQUIRED. */
	private static final String SENDSOLV_BASE_CONTACTLISTVALIDATOR_ADDRESS_REQUIRED =
			"sendsolv.base.contactlistvalidator.address.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ContactListValidator.class);

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
		ArrayList<Phone> phoneList = null;
		ArrayList<Email> emailList = null;
		ArrayList<Address> addressList = null;

		List<Contact> contactList =
				(List<Contact>)validationContext.getObjectToBeValidated(CONTACT_LIST_KEY);

		if (ValidationUtil.isNullOrEmpty(contactList))
		{
			return;
		}

		for (Contact contact : contactList)
		{
			switch (contact.getContactType())
			{
				case EMAIL_PERSONAL:
				case EMAIL_WORK:
					if (ValidationUtil.isNull(emailList))
					{
						emailList = new ArrayList<Email>();
					}
					emailList.add((Email)contact);
					break;
				case MOBILE:
				case OTHER:
				case PHONE_WORK:
					if (ValidationUtil.isNull(phoneList))
					{
						phoneList = new ArrayList<Phone>();
					}
					phoneList.add((Phone)contact);
					break;
				case POSTAL_HOME:
				case POSTAL_WORK:
					if (ValidationUtil.isNull(addressList))
					{
						addressList = new ArrayList<Address>();
					}
					addressList.add((Address)contact);
					break;
				case UNKNOWN:
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("contactType for Contact Validator missing!");
					}
					break;
			}

			if (!ValidationContextIndicator.DELETE.equals(validationContext.getValidationContextIndicator()))
			{
				validateChangeControlFields(validationContext.getMessages(), contact, validationContext);
			}
		}

		if (ValidationContextIndicator.FETCH_BY_ID.equals(validationContext.getValidationContextIndicator()) &&
				(BusinessTypeEnum.MEMBER.equals(contactList.get(0).getParentKeyType()) ||
						BusinessTypeEnum.RECIPIENT.equals(contactList.get(0).getParentKeyType())))
		{

			if (ValidationUtil.isNullOrEmpty(emailList))
			{
				validationContext.getMessages().add(
						new MessageInfo(SENDSOLV_BASE_CONTACTLISTVALIDATOR_EMAIL_REQUIRED,
								Message.MessageSeverity.Error,
								Message.MessageLevel.Field,
								new Object[] {validateValueToBeReturn(contactList.get(0).getParentKeyType(),
										contactList.get(0).getParentKey())}));
			}
			else
			{
				validationContext.getObjectsToBeValidated().put(EMAIL_LIST_KEY, emailList);
			}

			if (ValidationUtil.isNullOrEmpty(phoneList))
			{
				validationContext.getMessages().add(
						new MessageInfo(SENDSOLV_BASE_CONTACTLISTVALIDATOR_PHONE_REQUIRED,
								Message.MessageSeverity.Error,
								Message.MessageLevel.Field,
								new Object[] {validateValueToBeReturn(contactList.get(0).getParentKeyType(),
										contactList.get(0).getParentKey())}));
			}
			else
			{
				validationContext.getObjectsToBeValidated().put(PHONE_LIST_KEY, phoneList);
			}

			if (ValidationUtil.isNullOrEmpty(addressList))
			{
				validationContext.getMessages().add(
						new MessageInfo(SENDSOLV_BASE_CONTACTLISTVALIDATOR_ADDRESS_REQUIRED,
								Message.MessageSeverity.Error,
								Message.MessageLevel.Field,
								new Object[] {validateValueToBeReturn(contactList.get(0).getParentKeyType(),
										contactList.get(0).getParentKey())}));
			}
			else
			{
				validationContext.getObjectsToBeValidated().put(ADDRESS_LIST_KEY, addressList);
			}
		}
		else
		{
			if (!ValidationUtil.isNullOrEmpty(emailList) &&
					(!BusinessTypeEnum.ORGANIZATION.equals(contactList.get(0).getParentKeyType()) ||
							!BusinessTypeEnum.LOCATION.equals(contactList.get(0).getParentKeyType())))
			{
				validationContext.getObjectsToBeValidated().put(EMAIL_LIST_KEY, emailList);
			}

			if (!ValidationUtil.isNullOrEmpty(phoneList))
			{
				validationContext.getObjectsToBeValidated().put(PHONE_LIST_KEY, phoneList);
			}

			if (!ValidationUtil.isNullOrEmpty(addressList))
			{
				validationContext.getObjectsToBeValidated().put(ADDRESS_LIST_KEY, addressList);
			}
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
	 * Fetch recipient by id.
	 *
	 * @param request the request
	 * @return the recipient
	 */
	private Recipient fetchRecipientById(FetchByIdRequest request)
	{
		InternalResultsResponse<Recipient> response = getPersonDAC().fetchRecipientById(request);

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
			case RECIPIENT:
				Recipient recipient = fetchRecipientById(request);
				if (!ValidationUtil.isNull(recipient))
				{
					return String.valueOf(parentKeyType).toLowerCase() + SPACE + recipient.getParticipantId();
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
