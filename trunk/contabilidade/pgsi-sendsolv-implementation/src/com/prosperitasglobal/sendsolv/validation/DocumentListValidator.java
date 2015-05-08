package com.prosperitasglobal.sendsolv.validation;

import java.util.List;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.ILocationDAC;
import com.prosperitasglobal.sendsolv.dac.IOrganizationDAC;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.model.Document;
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
 * The Class DocumentListValidator.
 */
public class DocumentListValidator extends ChangeControlValidator implements IValidator

{

	/** The Constant SPACE. */
	private static final String SPACE = " ";

	/** The Constant PROSPERITASGLOBAL_BASE_BASE_DOCUMENTVALIDATOR_DOCUMENT_EXPIRATION_DATE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BASE_DOCUMENTVALIDATOR_DOCUMENT_EXPIRATION_DATE_REQUIRED =
			"sendsolv.base.base.documentvalidator.document.expiration.date.required";

	/** The Constant PROSPERITASGLOBAL_BASE_BASE_DOCUMENTVALIDATOR_DOCUMENT_ISSUING_COUNTRY_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BASE_DOCUMENTVALIDATOR_DOCUMENT_ISSUING_COUNTRY_REQUIRED =
			"sendsolv.base.base.documentvalidator.document.issuing.country.required";

	/** The Constant PROSPERITASGLOBAL_BASE_BASE_DOCUMENTVALIDATOR_DOCUMENT_NUMBER_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_BASE_DOCUMENTVALIDATOR_DOCUMENT_NUMBER_TOO_BIG =
			"sendsolv.base.base.documentvalidator.document.number.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_BASE_DOCUMENTVALIDATOR_DOCUMENT_NUMBER_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BASE_DOCUMENTVALIDATOR_DOCUMENT_NUMBER_REQUIRED =
			"sendsolv.base.base.documentvalidator.document.number.required";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_DOCUMENT_TYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_DOCUMENT_TYPE_REQUIRED =
			"sendsolv.base.documentvalidator.document.type.required";

	/** The Constant EIGHTY. */
	private static final Integer EIGHTY = 80;

	/** The Constant DOCUMENT_LIST_KEY. */
	private static final String DOCUMENT_LIST_KEY = "DocumentList";

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

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.validation.IValidator#validate(com.qat.framework.validation.ValidationContext)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void validate(ValidationContext validationContext)
	{
		List<Document> documentList = (List<Document>)validationContext.getObjectToBeValidated(DOCUMENT_LIST_KEY);

		if (ValidationUtil.isNullOrEmpty(documentList))
		{
			return;
		}

		for (Document document : documentList)
		{
			if (ValidationContextIndicator.FETCH_BY_ID.equals(validationContext.getValidationContextIndicator()))
			{
				validateApplyStatus(validationContext.getMessages(), document);
			}

			if (!ValidationContextIndicator.DELETE.equals(validationContext.getValidationContextIndicator()))
			{
				validateChangeControlFields(validationContext.getMessages(), document, validationContext);
			}
		}

		validationContext.getObjectsToBeValidated().put(DOCUMENT_LIST_KEY, documentList);
	}

	/**
	 * Validate apply status.
	 *
	 * @param list the list
	 * @param document the document
	 */
	private void validateApplyStatus(List<MessageInfo> list, Document document)
	{
		validateIssuingCountry(list, document);
		validateIdType(list, document);
		validateIdNumber(list, document);
		validateExpirationDate(list, document);
	}

	/**
	 * Validate id type.
	 *
	 * @param list the list
	 * @param document the document
	 */
	private void validateIdType(List<MessageInfo> list, Document document)
	{
		if (ValidationUtil.isNull(document.getDocumentType()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_DOCUMENT_TYPE_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(document.getParentKeyType(), document.getParentKey())}));
		}
	}

	/**
	 * Validate id number.
	 *
	 * @param list the list
	 * @param document the document
	 */
	private void validateIdNumber(List<MessageInfo> list, Document document)
	{
		if (ValidationUtil.isNullOrEmpty(document.getValue()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_BASE_DOCUMENTVALIDATOR_DOCUMENT_NUMBER_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(document.getParentKeyType(), document.getParentKey())}));
			return;
		}

		if (document.getValue().length() > EIGHTY)
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_BASE_DOCUMENTVALIDATOR_DOCUMENT_NUMBER_TOO_BIG,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(document.getParentKeyType(), document.getParentKey())}));
		}
	}

	/**
	 * Validate issuing country.
	 *
	 * @param list the list
	 * @param document the document
	 */
	private void validateIssuingCountry(List<MessageInfo> list, Document document)
	{
		if (ValidationUtil.isNull(document.getIssueCountry()))
		{
			list.add(new MessageInfo(PROSPERITASGLOBAL_BASE_BASE_DOCUMENTVALIDATOR_DOCUMENT_ISSUING_COUNTRY_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(document.getParentKeyType(), document.getParentKey())}));
		}
	}

	/**
	 * Validate expiration date.
	 *
	 * @param list the list
	 * @param document the document
	 */
	private void validateExpirationDate(List<MessageInfo> list, Document document)
	{
		if (ValidationUtil.isNull(document.getExpirationDate()))
		{
			list.add(new MessageInfo(
					PROSPERITASGLOBAL_BASE_BASE_DOCUMENTVALIDATOR_DOCUMENT_EXPIRATION_DATE_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.Field,
					new Object[] {validateValueToBeReturn(document.getParentKeyType(), document.getParentKey())}));
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
