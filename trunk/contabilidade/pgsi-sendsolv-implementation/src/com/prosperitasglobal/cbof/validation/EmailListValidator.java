package com.prosperitasglobal.cbof.validation;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.Email;
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
public class EmailListValidator implements IValidator
{

	/** The Constant SPACE. */
	private static final String SPACE = " ";

	/** The Constant EMAIL_PATTERN. */
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/** The Constant CBOF_BASE_CONTACTVALIDATOR_ID_REQUIRED. */
	private static final String CBOF_BASE_CONTACTVALIDATOR_ID_REQUIRED =
			"cbof.base.contactvalidator.id.required";

	/** The Constant CBOF_BASE_CONTACTVALIDATOR_EMAIL_INVALID. */
	private static final String CBOF_BASE_CONTACTVALIDATOR_EMAIL_INVALID =
			"cbof.base.contactvalidator.email.invalid";

	/** The Constant CBOF_BASE_CONTACTVALIDATOR_EMAIL_REQUIRED. */
	private static final String CBOF_BASE_CONTACTVALIDATOR_EMAIL_REQUIRED =
			"cbof.base.contactvalidator.email.required";

	/** The Constant EMAIL_LIST_KEY. */
	private static final String EMAIL_LIST_KEY = "EmailList";

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
		List<Email> emailList =
				(List<Email>)validationContext.getObjectToBeValidated(EMAIL_LIST_KEY);

		// Make sure I have a an emailList, because it is optional in some cases.
		if (ValidationUtil.isNullOrEmpty(emailList))
		{
			return;
		}

		for (Email email : emailList)
		{
			validateEmail(email, validationContext);
		}

	}

	/**
	 * Validate email.
	 *
	 * @param email the email
	 * @param context the context
	 */
	private void validateEmail(Email email, ValidationContext context)
	{
		switch (email.getModelAction())
		{
			case INSERT:
				validateEmailAddress(context.getMessages(), email);
				break;
			case UPDATE:
				validateId(context.getMessages(), email);
				validateEmailAddress(context.getMessages(), email);
				break;
			case DELETE:
				validateId(context.getMessages(), email);
				break;
			default:
				// Context indicator equals FETCH_BY_ID means that is updating the status
				if (ValidationContextIndicator.FETCH_BY_ID.equals(context.getValidationContextIndicator()))
				{
					validateEmailAddress(context.getMessages(), email);
				}
				else
				{
					if (LOG.isDebugEnabled())
					{
						LOG.debug("Model action for Email Validator missing!");
					}
				}
				break;
		}
	}

	/**
	 * Checks if the Id is null, if true then sets an id required message.
	 *
	 * @param list the list
	 * @param email the email
	 */
	private void validateId(List<MessageInfo> list, Email email)
	{
		ValidationUtil.isNullOrZero(email.getId(), CBOF_BASE_CONTACTVALIDATOR_ID_REQUIRED, list);
	}

	/**
	 * Validate email address.
	 *
	 * @param list the list
	 * @param email the email
	 */
	private void validateEmailAddress(List<MessageInfo> list, Email email)
	{
		if (ValidationUtil.isNullOrEmpty(email.getEmailAddress()))
		{
			list.add(new MessageInfo(CBOF_BASE_CONTACTVALIDATOR_EMAIL_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(email.getParentKeyType(), email.getParentKey())}));
			return;
		}

		if (!isEmailValid(email))
		{
			list.add(new MessageInfo(CBOF_BASE_CONTACTVALIDATOR_EMAIL_INVALID,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(email.getParentKeyType(), email.getParentKey())}));
		}
	}

	/**
	 * Checks if is email valid.
	 *
	 * @param email the email
	 * @return true, if checks if is email valid
	 */
	private boolean isEmailValid(Email email)
	{
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);

		Matcher matcher = pattern.matcher(email.getEmailAddress());
		if (matcher.matches())
		{
			return true;
		}

		return false;
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
