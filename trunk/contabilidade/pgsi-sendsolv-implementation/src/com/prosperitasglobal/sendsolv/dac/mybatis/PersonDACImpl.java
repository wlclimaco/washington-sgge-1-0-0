package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.dac.IContactDAC;
import com.prosperitasglobal.cbof.dac.INoteDAC;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.IDocumentTypeDAC;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.dac.ISecurityQuestionDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.RiskDACD;
import com.prosperitasglobal.sendsolv.model.CountryUsage;
import com.prosperitasglobal.sendsolv.model.CustomFee;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.EmploymentInfo;
import com.prosperitasglobal.sendsolv.model.Liaison;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.Person;
import com.prosperitasglobal.sendsolv.model.PersonName;
import com.prosperitasglobal.sendsolv.model.PersonSecurityAnswer;
import com.prosperitasglobal.sendsolv.model.PersonTypeEnum;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.model.Risk;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.prosperitasglobal.sendsolv.model.request.DocumentMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MemberInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RecipientInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RiskMaintenanceRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class PersonDACImpl.
 */
public class PersonDACImpl extends SqlSessionDaoSupport implements IPersonDAC
{

	/** The Constant PERSON_NAMESPACE. */
	private static final String PERSON_NAMESPACE = "PersonMap.";

	/** The Constant FETCH_VERSION_NUMBER. */
	private static final String FETCH_VERSION_NUMBER = "fetchVersionNumber";

	/* **************************************************************
	 * Liaison statements - no need for delete, we use the common deletePerson *
	 * **************************************************************
	 */

	/** The Constant PERSON_STMT_INSERT_LIAISON. */
	private static final String PERSON_STMT_INSERT_LIAISON = PERSON_NAMESPACE + "insertLiaison";

	/** The Constant PERSON_STMT_UPDATE_LIAISON. */
	private static final String PERSON_STMT_UPDATE_LIAISON = PERSON_NAMESPACE + "updateLiaison";

	/** The Constant PERSON_STMT_FETCH_LIAISON_BY_ID. */
	private static final String PERSON_STMT_FETCH_LIAISON_BY_ID = PERSON_NAMESPACE + "fetchLiaisonById";
	// Support for fetch with paging for Recipient
	/** The Constant PERSON_STMT_FETCH_RECIPIENT_COUNT. */
	private static final String PERSON_STMT_FETCH_RECIPIENT_ROW_COUNT = PERSON_NAMESPACE + "fetchRecipientRowCount";

	/** The Constant PERSON_STMT_FETCH_ALL_RECIPIENT_BY_REQUEST. */
	private static final String PERSON_STMT_FETCH_ALL_RECIPIENT_BY_REQUEST = PERSON_NAMESPACE
			+ "fetchRecipientByRequest";

	// Recipient - no need for delete, we use the common deletePerson
	/** The Constant PERSON_STMT_INSERT_RECIPIENT. */
	private static final String PERSON_STMT_INSERT_RECIPIENT = PERSON_NAMESPACE + "insertRecipient";

	/** The Constant PERSON_STMT_UPDATE_RECIPIENT. */
	private static final String PERSON_STMT_UPDATE_RECIPIENT = PERSON_NAMESPACE + "updateRecipient";

	/** The Constant PERSON_STMT_FETCH_RECIPIENT_BY_ID. */
	private static final String PERSON_STMT_FETCH_RECIPIENT_BY_ID = PERSON_NAMESPACE + "fetchRecipientById";

	// Support for fetch with paging for Liaison
	/** The Constant PERSON_STMT_FETCH_LIAISON_COUNT. */
	private static final String PERSON_STMT_FETCH_LIAISON_ROW_COUNT = PERSON_NAMESPACE + "fetchLiaisonRowCount";

	/** The Constant PERSON_STMT_FETCH_ALL_LIAISON_BY_REQUEST. */
	private static final String PERSON_STMT_FETCH_ALL_LIAISON_BY_REQUEST = PERSON_NAMESPACE
			+ "fetchLiaisonByRequest";

	// Member - no need for delete, we use the common deletePerson
	/** The Constant PERSON_STMT_INSERT_MEMBER. */
	private static final String PERSON_STMT_INSERT_MEMBER = PERSON_NAMESPACE + "insertMember";

	/** The Constant PERSON_STMT_UPDATE_MEMBER. */
	private static final String PERSON_STMT_UPDATE_MEMBER = PERSON_NAMESPACE + "updateMember";

	/** The Constant PERSON_STMT_FETCH_MEMBER_BY_ID. */
	private static final String PERSON_STMT_FETCH_MEMBER_BY_ID = PERSON_NAMESPACE + "fetchMemberById";

	/** The Constant PERSON_STMT_FETCH_ALL_MEMBER_BY_REQUEST. */
	private static final String PERSON_STMT_FETCH_ALL_MEMBER_BY_REQUEST = PERSON_NAMESPACE
			+ "fetchMemberByRequest";

	/** The Constant PERSON_STMT_FETCH_MEMBER_ROW_COUNT. */
	private static final String PERSON_STMT_FETCH_MEMBER_ROW_COUNT = PERSON_NAMESPACE + "fetchMemberRowCount";

	// The common delete statement
	/** The Constant PERSON_STMT_DELETE. */
	private static final String PERSON_STMT_DELETE = PERSON_NAMESPACE + "deletePersonById";

	// Different associations Person has
	/** The Constant PERSON_STMT_ASSOC_PERSON_WITH_CONTACT. */
	private static final String PERSON_STMT_ASSOC_PERSON_WITH_CONTACT = PERSON_NAMESPACE
			+ "associatePersonWithContact";

	/** The Constant PERSON_STMT_ASSOC_PERSON_WITH_NOTE. */
	private static final String PERSON_STMT_ASSOC_PERSON_WITH_NOTE = "associatePersonWithNote";

	/** The Constant PERSON_STMT_ASSOC_TRANSFER_SETTING_WITH_NOTE. */
	private static final String PERSON_STMT_ASSOC_TRANSFER_SETTING_WITH_NOTE = "associateTransferSettingWithNote";

	/** The Constant PERSON_STMT_ASSOC_LIAISON_WITH_LOCATION. */
	private static final String PERSON_STMT_ASSOC_LIAISON_WITH_LOCATION = PERSON_NAMESPACE
			+ "associateLiaisonWithLocation";

	// Support for PersonName
	/** The Constant PERSON_NAME_STMT_INSERT. */
	private static final String PERSON_NAME_STMT_INSERT = PERSON_NAMESPACE + "insertPersonName";

	/** The Constant PERSON_NAME_STMT_UPDATE. */
	private static final String PERSON_NAME_STMT_UPDATE = PERSON_NAMESPACE + "updatePersonName";

	/** The Constant PERSON_NAME_STMT_DELETE. */
	private static final String PERSON_NAME_STMT_DELETE = PERSON_NAMESPACE + "deletePersonName";

	/** The Constant PERSON_STMT_FETCH_PERSON_NAME_BY_ID. */
	private static final String PERSON_STMT_FETCH_PERSON_NAME_BY_ID = PERSON_NAMESPACE + "fetchPersonNameById";

	// Support for PersonDocument
	/** The Constant PERSON_STMT_ASSOC_PERSON_WITH_DOCUMENT. */
	private static final String PERSON_STMT_ASSOC_PERSON_WITH_DOCUMENT = PERSON_NAMESPACE
			+ "associatePersonWithDocument";

	/** The Constant PERSON_STMT_UPDATE_PERSON_DOCUMENT. */
	private static final String PERSON_STMT_UPDATE_PERSON_DOCUMENT = PERSON_NAMESPACE
			+ "updatePersonDocument";

	/** The Constant PERSON_STMT_UPDATE_PERSON_STATUS. */
	private static final String PERSON_STMT_UPDATE_PERSON_STATUS = PERSON_NAMESPACE
			+ "updatePersonStatus";

	/** The Constant PERSON_STMT_DELETE_PERSON_DOCUMENT. */
	private static final String PERSON_STMT_DELETE_PERSON_DOCUMENT = PERSON_NAMESPACE
			+ "deletePersonDocument";

	/** The Constant PERSON_STMT_FETCH_DOCUMENT_BY_PERSON_ID. */
	private static final String PERSON_STMT_FETCH_DOCUMENT_BY_PERSON_ID = "fetchDocumentByPersonId";

	// Support for OL
	/** The Constant PERSON_STMT_VERSION. */
	private static final String PERSON_STMT_VERSION = PERSON_NAMESPACE + FETCH_VERSION_NUMBER;

	/*
	 * Support for Namespace CountryUsage: no need for delete, will be deleted when Member is deleted.
	 */
	/** The Constant COUNTRY_USAGE_NAMESPACE. */
	private static final String COUNTRY_USAGE_NAMESPACE = "CountryUsageMap.";

	/** The Constant EMPLOYMENT_INFO_NAMESPACE. */
	private static final String EMPLOYMENT_INFO_NAMESPACE = "EmploymentInfoMap.";

	/** The Constant TRANSFER_SETTING_INFO_NAMESPACE. */
	private static final String TRANSFER_SETTING_INFO_NAMESPACE = "TransferSettingMap.";

	/** The Constant COUNTRY_USAGE_STMT_INSERT. */
	private static final String COUNTRY_USAGE_STMT_INSERT = COUNTRY_USAGE_NAMESPACE + "insertCountryUsage";

	/** The Constant COUNTRY_USAGE_STMT_UPDATE. */
	private static final String COUNTRY_USAGE_STMT_UPDATE = COUNTRY_USAGE_NAMESPACE + "updateCountryUsage";

	/** The Constant EMPLOYMENT_INFO_STMT_INSERT. */
	private static final String EMPLOYMENT_INFO_STMT_INSERT = EMPLOYMENT_INFO_NAMESPACE + "insertEmploymentInfo";

	/** The Constant EMPLOYMENT_INFO_STMT_UPDATE. */
	private static final String EMPLOYMENT_INFO_STMT_UPDATE = EMPLOYMENT_INFO_NAMESPACE + "updateEmploymentInfo";

	/** The Constant EMPLOYMENT_INFO_STMT_DELETE. */
	private static final String EMPLOYMENT_INFO_STMT_DELETE = EMPLOYMENT_INFO_NAMESPACE + "deleteEmploymentInfo";

	/** The Constant EMPLOYMENT_INFO_STMT_FETCH. */
	private static final String EMPLOYMENT_INFO_STMT_FETCH = EMPLOYMENT_INFO_NAMESPACE
			+ "fetchEmploymentInfoByBusinessAndPersonId";

	/** The Constant EMPLOYMENT_INFO_STMT_FETCH_BY_LOCATION_ID. */
	private static final String EMPLOYMENT_INFO_STMT_FETCH_BY_LOCATION_ID = EMPLOYMENT_INFO_NAMESPACE
			+ "fetchEmploymentInfoByLocationId";

	/** The Constant EMPLOYMENT_INFO_STMT_UPDATE_STATUS. */
	private static final String EMPLOYMENT_INFO_STMT_UPDATE_STATUS = EMPLOYMENT_INFO_NAMESPACE
			+ "updateEmploymentInfoStatus";

	/** The Constant TRANSFER_SETTING_INFO_STMT_INSERT. */
	private static final String TRANSFER_SETTING_INFO_STMT_INSERT = TRANSFER_SETTING_INFO_NAMESPACE
			+ "insertTransferSetting";

	/** The Constant TRANSFER_SETTING_INFO_STMT_UPDATE. */
	private static final String TRANSFER_SETTING_INFO_STMT_UPDATE = TRANSFER_SETTING_INFO_NAMESPACE
			+ "updateTransferSetting";

	/** The Constant TRANSFER_SETTING_INFO_STMT_DELETE. */
	private static final String TRANSFER_SETTING_INFO_STMT_DELETE = TRANSFER_SETTING_INFO_NAMESPACE
			+ "deleteTransferSetting";

	/** The Constant TRANSFER_SETTING_INFO_STMT_UPDATE_STATUS. */
	private static final String TRANSFER_SETTING_INFO_STMT_UPDATE_STATUS = TRANSFER_SETTING_INFO_NAMESPACE
			+ "updateTransferSettingStatus";

	/** The Constant COUNTRY_USAGE_STMT_FETCH_BY_ID. */
	private static final String COUNTRY_USAGE_STMT_FETCH_BY_ID = COUNTRY_USAGE_NAMESPACE + "fetchCountryUsageById";

	/** The Constant CUSTOM_FEE_STMT_INSERT. */
	private static final String CUSTOM_FEE_STMT_INSERT = TRANSFER_SETTING_INFO_NAMESPACE
			+ "insertCustomFee";

	/** The Constant CUSTOM_FEE_STMT_UPDATE. */
	private static final String CUSTOM_FEE_STMT_UPDATE = TRANSFER_SETTING_INFO_NAMESPACE
			+ "updateCustomFee";

	/** The Constant CUSTOM_FEE_STMT_DELETE. */
	private static final String CUSTOM_FEE_STMT_DELETE = TRANSFER_SETTING_INFO_NAMESPACE
			+ "deleteCustomFee";

	/** The Constant COUNTRY_USAGE_STMT_VERSION. */
	private static final String COUNTRY_USAGE_STMT_VERSION = COUNTRY_USAGE_NAMESPACE + FETCH_VERSION_NUMBER;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PersonDACImpl.class);

	/** The common business objects dac. */
	private IContactDAC contactDAC;

	/** The note dac. */
	private INoteDAC noteDAC;

	/** The document type dac. */
	private IDocumentTypeDAC documentTypeDAC;

	/** The security question dac. */
	private ISecurityQuestionDAC securityQuestionDAC;

	/** The valid sort fields for a member inquiry. Will be injected by Spring. */
	private Map<String, String> memberInquiryValidSortFields;

	/** The valid sort fields for a recipient inquiry. Will be injected by Spring. */
	private Map<String, String> recipientInquiryValidSortFields;

	/**
	 * Gets the security question dac.
	 *
	 * @return the securityQuestionDAC
	 */
	public ISecurityQuestionDAC getSecurityQuestionDAC()
	{
		return securityQuestionDAC;
	}

	/**
	 * Sets the security question dac.
	 *
	 * @param securityQuestionDAC the securityQuestionDAC to set
	 */
	public void setSecurityQuestionDAC(ISecurityQuestionDAC securityQuestionDAC)
	{
		this.securityQuestionDAC = securityQuestionDAC;
	}

	/**
	 * Gets the document type dac.
	 *
	 * @return the document type dac
	 */
	public IDocumentTypeDAC getDocumentTypeDAC()
	{
		return documentTypeDAC;
	}

	/**
	 * Sets the document type dac.
	 *
	 * @param documentTypeDAC the document type dac
	 */
	public void setDocumentTypeDAC(IDocumentTypeDAC documentTypeDAC)
	{
		this.documentTypeDAC = documentTypeDAC;
	}

	/**
	 * Gets the note dac.
	 *
	 * @return the note dac
	 */
	public INoteDAC getNoteDAC()
	{
		return noteDAC;
	}

	/**
	 * Sets the note dac.
	 *
	 * @param noteDAC the note dac
	 */
	public void setNoteDAC(INoteDAC noteDAC)
	{
		this.noteDAC = noteDAC;
	}

	/**
	 * Gets the common business objects dac.
	 *
	 * @return the common business objects dac
	 */
	public IContactDAC getContactDAC()
	{
		return contactDAC;
	}

	/**
	 * Sets the common business objects dac.
	 *
	 * @param contactDAC the common business objects dac
	 */
	public void setContactDAC(IContactDAC contactDAC)
	{
		this.contactDAC = contactDAC;
	}

	/**
	 * Get the valid sort fields for the member inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the member inquiry.
	 */
	public Map<String, String> getMemberInquiryValidSortFields()
	{
		return memberInquiryValidSortFields;
	}

	/**
	 * Get the valid sort fields for the recipient inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the recipient inquiry.
	 */
	public Map<String, String> getRecipientInquiryValidSortFields()
	{
		return recipientInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the member inquiry. Attribute injected by Spring.
	 *
	 * @param memberInquiryValidSortFields The valid sort fields for the member inquiry to set.
	 */
	public void setMemberInquiryValidSortFields(Map<String, String> memberInquiryValidSortFields)
	{
		this.memberInquiryValidSortFields = memberInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the recipient inquiry. Attribute injected by Spring.
	 *
	 * @param recipientInquiryValidSortFields The valid sort fields for the recipient inquiry to set.
	 */
	public void setRecipientInquiryValidSortFields(Map<String, String> recipientInquiryValidSortFields)
	{
		this.recipientInquiryValidSortFields = recipientInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrganizationDAC#insertOrganization(com.prosperitasglobal.sendsolv.model
	 * .Organization)
	 */
	/**
	 * Insert liaison.
	 *
	 * @param liaison an instance of {@link Liaison}
	 * @return the internal results response< liaison>
	 */
	@Override
	public InternalResultsResponse<Liaison> insertLiaison(Liaison liaison)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Liaison> response = new InternalResultsResponse<Liaison>();

		// First insert the root
		// If successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), PERSON_STMT_INSERT_LIAISON, liaison, response);

		if (!response.isInError())
		{
			insertCount += associateLiaisonWithLocation(liaison, response);

			if (response.isInError())
			{
				return response;
			}

			insertCount += maintainPersonAssociations(liaison, response);

			if (!response.isInError() && (insertCount > 0))
			{
				response.addResult(liaison);
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IPersonDAC#insertRecipient(com.prosperitasglobal.sendsolv.model.Recipient)
	 */
	/**
	 * Insert recipient.
	 *
	 * @param recipient an instance of {@link Recipient}
	 * @return the internal results response< recipient>
	 */
	@Override
	public InternalResultsResponse<Recipient> insertRecipient(Recipient recipient)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Recipient> response = new InternalResultsResponse<Recipient>();

		// First insert the root
		// If successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), PERSON_STMT_INSERT_RECIPIENT, recipient, response);

		if (response.isInError())
		{
			return response;
		}

		insertCount += maintainPersonAssociations(recipient, response);

		if (!response.isInError() && (insertCount > 0))
		{
			response.addResult(recipient);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrganizationDAC#updateOrganization(com.prosperitasglobal.sendsolv.model
	 * .Organization)
	 */
	/**
	 * Update liaison.
	 *
	 * @param liaison an instance of {@link Liaison}
	 * @return the internal results response< liaison>
	 */
	@Override
	public InternalResultsResponse<Liaison> updateLiaison(Liaison liaison)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Liaison> response = new InternalResultsResponse<Liaison>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(liaison.getModelAction())
				&& (liaison.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdateOL(getSqlSession(), PERSON_STMT_UPDATE_LIAISON, liaison,
							PERSON_STMT_VERSION, response);
		}

		if (response.isInError())
		{
			return response;
		}

		updateCount += maintainPersonAssociations(liaison, response);

		// Finally, if something was updated then add the Person to the result.
		if (!response.isInError() && (updateCount > 0))
		{
			response.addResult(liaison);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IPersonDAC#updateRecipient(com.prosperitasglobal.sendsolv.model.Recipient)
	 */
	/**
	 * Update recipient.
	 *
	 * @param recipient an instance of {@link Recipient}
	 * @return the internal results response< recipient>
	 */
	@Override
	public InternalResultsResponse<Recipient> updateRecipient(Recipient recipient)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Recipient> response = new InternalResultsResponse<Recipient>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(recipient.getModelAction())
				&& (recipient.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdateOL(getSqlSession(), PERSON_STMT_UPDATE_RECIPIENT, recipient,
							PERSON_STMT_VERSION, response);
		}

		if (response.isInError())
		{
			return response;
		}

		updateCount = maintainPersonAssociations(recipient, response);

		if (response.isInError())
		{
			return response;
		}

		updateCount += maintainTransferSettingAssociations(recipient, response);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(recipient);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrganizationDAC#deleteOrganization(com.prosperitasglobal.sendsolv.model
	 * .Organization)
	 */
	/**
	 * Delete person.
	 *
	 * @param person an instance of {@link Person}
	 * @return the internal response
	 */
	@Override
	public InternalResponse deletePerson(Person person)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), PERSON_STMT_DELETE, person, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IPersonDAC#insertPersonDocument(com.prosperitasglobal.sendsolv.model.request
	 * .DocumentMaintenanceRequest)
	 */
	/**
	 * Attaches an instance of {@link Document} to an instance of {@link Person}.
	 *
	 * @param request the request
	 * @return the internal results response< document>
	 */
	@Override
	public InternalResultsResponse<Document> insertPersonDocument(DocumentMaintenanceRequest request)
	{
		InternalResultsResponse<Document> response = new InternalResultsResponse<Document>();
		QATMyBatisDacHelper.doInsert(getSqlSession(), PERSON_STMT_ASSOC_PERSON_WITH_DOCUMENT, request.getDocument(),
				response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IPersonDAC#updatePersonDocument(com.prosperitasglobal.sendsolv.model.request
	 * .DocumentMaintenanceRequest)
	 */
	/**
	 * Update person document.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	@Override
	public InternalResponse updatePersonDocument(DocumentMaintenanceRequest request)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doUpdate(getSqlSession(), PERSON_STMT_UPDATE_PERSON_DOCUMENT, request.getDocument(),
				response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IPersonDAC#deletePersonDocument(com.prosperitasglobal.sendsolv.model.request
	 * .DocumentMaintenanceRequest)
	 */
	/**
	 * Delete person document.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	@Override
	public InternalResponse deletePersonDocument(DocumentMaintenanceRequest request)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), PERSON_STMT_DELETE_PERSON_DOCUMENT, request.getDocument(),
				response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IPersonDAC#fetchLiaisonById(com.prosperitasglobal.cbof.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Liaison> fetchLiaisonById(FetchByIdRequest request)
	{
		InternalResultsResponse<Liaison> response = new InternalResultsResponse<Liaison>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), PERSON_STMT_FETCH_LIAISON_BY_ID, request, response);

		if (!response.isInError() && (response.getResultsList().size() == 1))
		{
			setPersonSsn(response.getFirstResult());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IPersonDAC#fetchRecipientById(com.prosperitasglobal.cbof.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Recipient> fetchRecipientById(FetchByIdRequest request)
	{
		InternalResultsResponse<Recipient> response = new InternalResultsResponse<Recipient>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), PERSON_STMT_FETCH_RECIPIENT_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IPersonDAC#fetchCountryUsageById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<CountryUsage> fetchCountryUsageById(Integer id)
	{
		InternalResultsResponse<CountryUsage> response = new InternalResultsResponse<CountryUsage>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), COUNTRY_USAGE_STMT_FETCH_BY_ID, id, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IPersonDAC#fetchDocumentByPersonId(java.lang.Integer)
	 */
	/**
	 * Fetch all {@link Document} linked to given a {@link Person} id.
	 *
	 * @param id the id
	 * @return the internal results response< document>
	 */
	@Override
	public InternalResultsResponse<Document> fetchDocumentByPersonId(Integer id)
	{
		InternalResultsResponse<Document> response = new InternalResultsResponse<Document>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), PERSON_STMT_FETCH_DOCUMENT_BY_PERSON_ID, id, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IPersonDAC#fetchPersonNameById(java.lang.Integer)
	 */
	/**
	 * Fetch all {@link PersonName} linked to given a {@link Person} id.
	 *
	 * @param id the id
	 * @return the internal results response< person name>
	 */
	@Override
	public InternalResultsResponse<PersonName> fetchPersonNameById(Integer id)
	{
		InternalResultsResponse<PersonName> response = new InternalResultsResponse<PersonName>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), PERSON_STMT_FETCH_PERSON_NAME_BY_ID, id, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrganizationDAC#fetchOrganizationByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	/**
	 * Fetch a list of {@link Liaison} by request using pagination parameters.
	 *
	 * @param request the request
	 * @return the internal results response< liaison>
	 */
	@Override
	public InternalResultsResponse<Liaison> fetchLiaisonByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Liaison> response = new InternalResultsResponse<Liaison>();
		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, PERSON_STMT_FETCH_LIAISON_ROW_COUNT,
				PERSON_STMT_FETCH_ALL_LIAISON_BY_REQUEST, response);

		for (Liaison liaison : response.getResultsList())
		{
			setPersonSsn(liaison);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IPersonDAC#fetchRecipientByRequest
	 * (com.prosperitasglobal.sendsolv.model.request.RecipientInquiryRequest)
	 */
	/**
	 * Fetch a list of {@link Recipient} by request using pagination parameters.
	 *
	 * @param request the request
	 * @return the internal results response< recipient>
	 */
	@Override
	public InternalResultsResponse<Recipient> fetchRecipientByRequest(RecipientInquiryRequest request)
	{
		InternalResultsResponse<Recipient> response = new InternalResultsResponse<Recipient>();

		/*
		 * Helper method to translation from the "user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getRecipientInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, PERSON_STMT_FETCH_RECIPIENT_ROW_COUNT,
				PERSON_STMT_FETCH_ALL_RECIPIENT_BY_REQUEST, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IPersonDAC#updateRisk(com.prosperitasglobal.sendsolv.model.request.
	 * RiskMaintenanceRequest)
	 */
	/**
	 * Update risk.
	 *
	 * @param request the request
	 * @return the internal results response< risk>
	 */
	@Override
	public InternalResultsResponse<Risk> updateRisk(RiskMaintenanceRequest request)
	{
		return RiskDACD.updateRisk(getSqlSession(), request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IPersonDAC#deletePersonName(com.prosperitasglobal.sendsolv.model.PersonName,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	/**
	 * Delete person name.
	 *
	 * @param name the name
	 * @param response the response
	 * @return the integer
	 */
	@Override
	public Integer deletePersonName(PersonName name, InternalResultsResponse<?> response)
	{
		Integer deleteCount = 0;

		deleteCount = QATMyBatisDacHelper.doRemove(getSqlSession(), PERSON_NAME_STMT_DELETE, name,
				response);

		return deleteCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IPersonDAC#insertMember(com.prosperitasglobal.sendsolv.model.Member)
	 */
	/**
	 * Insert member.
	 *
	 * @param member an instance of {@link Member}
	 * @return the internal results response< member>
	 */
	@Override
	public InternalResultsResponse<Member> insertMember(Member member)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Member> response = new InternalResultsResponse<Member>();

		// First insert the root
		// If successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), PERSON_STMT_INSERT_MEMBER, member, response);

		if (response.isInError())
		{
			return response;
		}

		insertCount += maintainPersonAssociations(member, response);

		if (response.isInError())
		{
			return response;
		}

		insertCount += maintainMemberAssociations(member, response);

		if (!response.isInError() && (insertCount > 0))
		{
			response.addResult(member);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IPersonDAC#updateMember(com.prosperitasglobal.sendsolv.model.Member)
	 */
	/**
	 * Update member.
	 *
	 * @param member an instance of {@link Member}
	 * @return the internal results response<Member>
	 */
	@Override
	public InternalResultsResponse<Member> updateMember(Member member)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Member> response = new InternalResultsResponse<Member>();

		// First update the root if necessary.
		if ((!ValidationUtil.isNull(member.getModelAction())
				&& (member.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
				|| (!ValidationUtil.isNull(member.getRisk().getModelAction())
				&& QATModel.PersistanceActionEnum.UPDATE.equals(member.getRisk().getModelAction())))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdateOL(getSqlSession(), PERSON_STMT_UPDATE_MEMBER, member,
							PERSON_STMT_VERSION, response);
		}

		if (response.isInError())
		{
			return response;
		}

		updateCount += maintainPersonAssociations(member, response);

		if (response.isInError())
		{
			return response;
		}

		updateCount += maintainMemberAssociations(member, response);

		if (response.isInError())
		{
			return response;
		}

		updateCount += maintainTransferSettingAssociations(member, response);

		// Finally, if something was updated then add the Person to the result.
		if (!response.isInError() && (updateCount > 0))
		{
			response.addResult(member);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IPersonDAC#fetchMemberById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Member> fetchMemberById(FetchByIdRequest request)
	{
		InternalResultsResponse<Member> response = new InternalResultsResponse<Member>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), PERSON_STMT_FETCH_MEMBER_BY_ID, request, response);

		if (!response.isInError() && (response.getResultsList().size() == 1))
		{
			setPersonSsn(response.getFirstResult());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IPersonDAC#fetchMemberByRequest(com.prosperitasglobal.sendsolv.model.request
	 * .MemberInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Member> fetchMemberByRequest(MemberInquiryRequest request)
	{
		InternalResultsResponse<Member> response = new InternalResultsResponse<Member>();

		/*
		 * Helper method to translation from the "user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getMemberInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, PERSON_STMT_FETCH_MEMBER_ROW_COUNT,
				PERSON_STMT_FETCH_ALL_MEMBER_BY_REQUEST, response);

		if (!response.isInError() && response.hasResults())
		{
			for (Member member : response.getResultsList())
			{
				setPersonSsn(member);
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IPersonDAC#applyStatus(com.prosperitasglobal.sendsolv.model.Member)
	 */
	@Override
	public InternalResponse applyPersonStatus(Person person)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doUpdate(getSqlSession(), PERSON_STMT_UPDATE_PERSON_STATUS, person,
				response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IPersonDAC#applyEmploymentInfoStatus(com.prosperitasglobal.sendsolv.model.
	 * EmploymentInfo)
	 */
	@Override
	public InternalResponse applyEmploymentInfoStatus(EmploymentInfo employmentInfo)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPLOYMENT_INFO_STMT_UPDATE_STATUS, employmentInfo,
				response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IPersonDAC#applyTransferSettingStatus(com.prosperitasglobal.sendsolv.model
	 * .TransferSetting)
	 */
	@Override
	public InternalResponse applyTransferSettingStatus(TransferSetting transferSetting)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doUpdate(getSqlSession(), TRANSFER_SETTING_INFO_STMT_UPDATE_STATUS, transferSetting,
				response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IPersonDAC#fetchEmploymentInfoByMemberId(com.prosperitasglobal.sendsolv.model
	 * .EmploymentInfo)
	 */
	@Override
	public InternalResultsResponse<EmploymentInfo> fetchEmploymentInfoByMemberId(EmploymentInfo employmentInfo)
	{
		InternalResultsResponse<EmploymentInfo> response = new InternalResultsResponse<EmploymentInfo>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPLOYMENT_INFO_STMT_FETCH, employmentInfo, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IPersonDAC#fetchEmploymentInfoByMemberId(com.prosperitasglobal.sendsolv.model
	 * .EmploymentInfo)
	 */
	@Override
	public InternalResultsResponse<EmploymentInfo> fetchEmploymentInfoByLocationId(Integer employmentInfoId)
	{
		InternalResultsResponse<EmploymentInfo> response = new InternalResultsResponse<EmploymentInfo>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPLOYMENT_INFO_STMT_FETCH_BY_LOCATION_ID,
				employmentInfoId, response);
		return response;
	}

	/* *************************
	 * Private utility methods *
	 * *************************
	 */
	/**
	 * Maintain member associations.
	 *
	 * @param member the member
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainMemberAssociations(Member member, InternalResponse response)
	{
		Integer count = 0;

		// Country Usage
		count += maintainCountryUsageAssociations(member, response);

		if (response.isInError())
		{
			return count;
		}

		// Employment Information
		count += maintainEmploymentInfoAssociations(member, response);

		if (response.isInError())
		{
			return count;
		}

		// Security Question & Answer
		count += maintainSecurityAnswerAssociations(member, response);

		if (response.isInError())
		{
			return count;
		}

		return count;
	}

	/**
	 * Maintain employment info associations.
	 *
	 * @param member the member
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainEmploymentInfoAssociations(Member member, InternalResponse response)
	{
		Integer count = 0;

		if (ValidationUtil.isNullOrEmpty(member.getEmploymentInfoList()))
		{
			return count;
		}
		// For Each EmploymentInfo...
		for (EmploymentInfo employmentInfo : member.getEmploymentInfoList())
		{
			employmentInfo.setMemberId(member.getId());

			if (ValidationUtil.isNull(employmentInfo.getModelAction()))
			{
				continue;
			}

			switch (employmentInfo.getModelAction())
			{
				case INSERT:
					count += insertEmploymentInfo(employmentInfo, response);
					break;
				case UPDATE:
					count += updateEmploymentInfo(employmentInfo, response);
					break;
				case DELETE:
					count += deleteEmploymentInfo(employmentInfo, response);
					break;
				default:
					break;
			}
		}

		return count;
	}

	/**
	 * Maintain transfer setting associations.
	 *
	 * @param recipient the recipient
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainTransferSettingAssociations(Recipient recipient, InternalResultsResponse<?> response)
	{
		if (ValidationUtil.isNullOrEmpty(recipient.getTransferSettingList()))
		{
			return 0;
		}
		return maintainTransferSettingAssociations(response, recipient.getTransferSettingList());
	}

	/**
	 * Maintain transfer setting associations.
	 *
	 * @param member the member
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainTransferSettingAssociations(Member member, InternalResultsResponse<?> response)
	{
		if (ValidationUtil.isNullOrEmpty(member.getTransferSettingList()))
		{
			return 0;
		}
		return maintainTransferSettingAssociations(response, member.getTransferSettingList());
	}

	/**
	 * Maintain transfer setting associations.
	 *
	 * @param response the response
	 * @param transferSettingList the transfer setting list
	 * @return the integer
	 */
	private Integer maintainTransferSettingAssociations(InternalResultsResponse<?> response,
			List<TransferSetting> transferSettingList)
	{
		Integer count = 0;

		// For Each EmploymentInfo...
		for (TransferSetting transferSetting : transferSettingList)
		{
			if (ValidationUtil.isNull(transferSetting.getModelAction()))
			{
				continue;
			}

			switch (transferSetting.getModelAction())
			{
				case INSERT:
					count += insertTransferSetting(transferSetting, response);
					break;
				case UPDATE:
				case NONE:
					count += updateTransferSetting(transferSetting, response);
					break;
				case DELETE:
					count += deleteTransferSetting(transferSetting, response);
					break;
				default:
					break;
			}

		}

		return count;
	}

	/**
	 * Maintain custom fee associations.
	 *
	 * @param transferSetting the transfer setting
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainCustomFeeAssociations(TransferSetting transferSetting, InternalResponse response)
	{
		Integer count = 0;

		if (ValidationUtil.isNullOrEmpty(transferSetting.getCustomFeeList()))
		{
			return count;
		}

		for (CustomFee customFee : transferSetting.getCustomFeeList())
		{
			if (ValidationUtil.isNull(customFee.getModelAction()))
			{
				continue;
			}

			customFee.setTransferSettingId(transferSetting.getId());

			switch (customFee.getModelAction())
			{
				case INSERT:
					count += insertCustomFee(customFee, response);
					break;
				case UPDATE:
					count += updateCustomFee(customFee, response);
					break;
				case DELETE:
					count += deleteCustomFee(customFee, response);
					break;
				default:
					break;
			}
		}

		return count;
	}

	/**
	 * Maintain country usage associations.
	 *
	 * @param member the member
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainCountryUsageAssociations(Member member, InternalResponse response)
	{
		Integer count = 0;

		// First Maintain CountryUsage
		if (!ValidationUtil.isNullOrEmpty(member.getCountryUsageList()))
		{
			// For Each CountryUsage...
			for (CountryUsage countryUsage : member.getCountryUsageList())
			{
				// Make sure we set the parent key
				countryUsage.setPersonId(member.getId());

				if (!ValidationUtil.isNull(countryUsage.getModelAction()))
				{
					switch (countryUsage.getModelAction())
					{
						case UPDATE:
							count += updateCountryUsage(countryUsage, response);
							break;
						case INSERT:
							count += insertCountryUsage(countryUsage, response);
							break;
						default:
							break;
					}
				}
			}
		}
		return count;
	}

	/**
	 * Maintain questions & answers associations.
	 *
	 * @param member the member
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainSecurityAnswerAssociations(Member member, InternalResponse response)
	{
		Integer count = 0;

		// First Maintain Security Questions & Answer
		if (!ValidationUtil.isNullOrEmpty(member.getSecurityAnswerList()))
		{
			// For Each Question...
			for (PersonSecurityAnswer personSecurityAnswer : member.getSecurityAnswerList())
			{
				// Make sure we set the parent key
				personSecurityAnswer.setParentKey(member.getId());

				if (ValidationUtil.isNull(personSecurityAnswer.getModelAction()))
				{
					continue;
				}

				InternalResultsResponse<PersonSecurityAnswer> securityAnswerResponse =
						new InternalResultsResponse<PersonSecurityAnswer>();

				switch (personSecurityAnswer.getModelAction())
				{
					case UPDATE:
						securityAnswerResponse =
						getSecurityQuestionDAC().updatePersonSecurityAnswer(personSecurityAnswer);
						if (securityAnswerResponse.isInError())
						{
							response.merge(securityAnswerResponse);
							break;
						}

						count += 1;
						break;
					case INSERT:
						securityAnswerResponse =
						getSecurityQuestionDAC().insertPersonSecurityAnswer(personSecurityAnswer);
						if (securityAnswerResponse.isInError())
						{
							response.merge(securityAnswerResponse);
							break;
						}

						count += 1;
						break;
					case DELETE:
						InternalResponse deleteResponse =
						getSecurityQuestionDAC().deletePersonSecurityAnswer(personSecurityAnswer);
						if (deleteResponse.isInError())
						{
							response.merge(deleteResponse);
							break;
						}
						count += 1;
						break;
					default:
						break;
				}
			}
		}
		return count;
	}

	/**
	 * Maintain person associations.
	 *
	 * @param person an instance of {@link Person}
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainPersonAssociations(Person person, InternalResultsResponse<?> response)
	{
		Integer theCount = 0;

		// Names
		theCount +=
				maintainNameAssociations(person.getNameList(), person.getId(),
						PERSON_NAME_STMT_INSERT, response);

		if (response.isInError())
		{
			return theCount;
		}
		// Contacts
		theCount +=
				getContactDAC().maintainContactAssociations(person.getContactList(),
						person.getId(), PERSON_STMT_ASSOC_PERSON_WITH_CONTACT, response);

		if (response.isInError())
		{
			return theCount;
		}
		// Notes
		theCount +=
				getNoteDAC().maintainNoteAssociations(person.getNoteList(), person.getId(),
						PERSON_STMT_ASSOC_PERSON_WITH_NOTE, response);

		if (response.isInError())
		{
			return theCount;
		}

		if (!PersonTypeEnum.RECIPIENT.equals(person.getPersonType()))
		{
			// Documents
			theCount += maintainDocumentAssociation(person.getDocumentList(), person.getId(), response);
		}
		return theCount;

	}

	/**
	 * Maintain document association.
	 *
	 * @param documentList the document list
	 * @param parentId the parent id
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainDocumentAssociation(List<Document> documentList, Integer parentId,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;

		// Then create the request to add the Document
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();

		if (ValidationUtil.isNullOrEmpty(documentList))
		{
			return count;
		}
		for (Document document : documentList)
		{
			if (!ValidationUtil.isNull(document.getModelAction()))
			{
				document.setParentKey(parentId);
				request.setDocument(document);
				switch (document.getModelAction())
				{
					case INSERT:
						InternalResultsResponse<Document> insertResult = insertPersonDocument(request);
						if (!insertResult.isInError())
						{
							count++;
							continue;
						}
						response.merge(insertResult);
						break;
					case UPDATE:
						InternalResponse updateResult = updatePersonDocument(request);
						if (!updateResult.isInError())
						{
							count++;
							continue;
						}
						response.merge(updateResult);
						break;
					case DELETE:
						InternalResponse deleteResult = deletePersonDocument(request);
						if (!deleteResult.isInError())
						{
							count++;
							continue;
						}
						response.merge(deleteResult);
						break;
					default:
						if (LOG.isDebugEnabled())
						{
							LOG.debug("ModelAction for Person is missing!");
						}
						break;
				}
			}
		}

		return count;
	}

	/**
	 * Associate liaison with location.
	 *
	 * @param liaison an instance of {@link Liaison}
	 * @param response the response
	 * @return the integer
	 */
	private Integer associateLiaisonWithLocation(Liaison liaison, InternalResultsResponse<Liaison> response)
	{
		Integer insertCount = 0;

		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), PERSON_STMT_ASSOC_LIAISON_WITH_LOCATION, liaison,
						response);

		return insertCount;
	}

	/**
	 * Maintain name associations.
	 *
	 * @param nameList the name list
	 * @param parentId the parent id
	 * @param personStmtAssocPersonToName the person stmt assoc person to name
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainNameAssociations(List<PersonName> nameList, Integer parentId,
			String personStmtAssocPersonToName, InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain Contacts
		if (ValidationUtil.isNullOrEmpty(nameList))
		{
			return count;
		}
		// For Each Contact...
		for (PersonName name : nameList)
		{
			// Make sure we set the parent key
			name.setPersonId(parentId);

			if (ValidationUtil.isNull(name.getModelAction()))
			{
				continue;
			}
			switch (name.getModelAction())
			{
				case UPDATE:
					count = updatePersonName(name, response);
					break;
				case INSERT:
					count = insertPersonName(name, personStmtAssocPersonToName, response);
					break;
				case DELETE:
					count = deletePersonName(name, response);
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("ModelAction for PersonName missing!");
					}
					break;
			}
		}
		return count;
	}

	/**
	 * Insert person name.
	 *
	 * @param name the name
	 * @param personStmtAssocPersonToName the person stmt assoc person to name
	 * @param response the response
	 * @return the integer
	 */
	private Integer insertPersonName(PersonName name, String personStmtAssocPersonToName,
			InternalResultsResponse<?> response)
	{

		Integer insertCount = 0;

		// First insert the root personName data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), PERSON_NAME_STMT_INSERT, name, response);

		return insertCount;
	}

	/**
	 * Update person name.
	 *
	 * @param name the name
	 * @param response the response
	 * @return the integer
	 */
	private Integer updatePersonName(PersonName name, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		if (!ValidationUtil.isNull(name.getModelAction())
				&& (name.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), PERSON_NAME_STMT_UPDATE, name, response);

			if (updateCount == 1)
			{
				name.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}
		return updateCount;
	}

	/**
	 * Sets the person ssn.
	 *
	 * @param person the person ssn
	 */
	private void setPersonSsn(Person person)
	{
		// Now, retrieve the Docs associated. Should only have one, the SSN
		InternalResultsResponse<Document> docResponse =
				fetchDocumentByPersonId(person.getId());

		if (!docResponse.isInError() && (docResponse.getResultsList().size() == 1))
		{
			// Set the document list in the Person object
			person.setDocumentList(docResponse.getResultsList());
		}
	}

	/**
	 * Insert the Country Usage.
	 *
	 * @param countryUsage The country usage object to insert.
	 * @param response The response of the insert.
	 * @return The number of objects inserted.
	 */
	private Integer insertCountryUsage(CountryUsage countryUsage, InternalResponse response)
	{
		Integer insertCount = 0;

		if (countryUsage.getModelAction() == QATModel.PersistanceActionEnum.INSERT)
		{
			insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), COUNTRY_USAGE_STMT_INSERT, countryUsage, response);

			if (insertCount == 1)
			{
				countryUsage.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return insertCount;
	}

	/**
	 * Update the Country Usage.
	 *
	 * @param countryUsage The country usage object to update.
	 * @param response The response of the update.
	 * @return The number of objects updated.
	 */
	private Integer updateCountryUsage(CountryUsage countryUsage, InternalResponse response)
	{
		Integer updateCount = 0;

		if (countryUsage.getModelAction() == QATModel.PersistanceActionEnum.UPDATE)
		{
			updateCount =
					QATMyBatisDacHelper.doUpdateOL(getSqlSession(), COUNTRY_USAGE_STMT_UPDATE, countryUsage,
							COUNTRY_USAGE_STMT_VERSION, response);

			if (updateCount == 1)
			{
				countryUsage.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	/**
	 * Insert employment info.
	 *
	 * @param employmentInfo the employment info
	 * @param response the response
	 * @return the integer
	 */
	private Integer insertEmploymentInfo(EmploymentInfo employmentInfo, InternalResponse response)
	{
		Integer insertCount = 0;

		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), EMPLOYMENT_INFO_STMT_INSERT, employmentInfo, response);

		return insertCount;
	}

	/**
	 * Update employment info.
	 *
	 * @param employmentInfo the employment info
	 * @param response the response
	 * @return the integer
	 */
	private Integer updateEmploymentInfo(EmploymentInfo employmentInfo, InternalResponse response)
	{
		Integer updateCount = 0;

		updateCount =
				QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPLOYMENT_INFO_STMT_UPDATE, employmentInfo, response);

		return updateCount;
	}

	/**
	 * Delete employment info.
	 *
	 * @param employmentInfo the employment info
	 * @param response the response
	 * @return the integer
	 */
	private Integer deleteEmploymentInfo(EmploymentInfo employmentInfo, InternalResponse response)
	{
		Integer deleteCount = 0;

		deleteCount =
				QATMyBatisDacHelper.doRemove(getSqlSession(), EMPLOYMENT_INFO_STMT_DELETE, employmentInfo, response);

		return deleteCount;
	}

	/**
	 * Insert transfer setting.
	 *
	 * @param transferSetting the transfer setting
	 * @param response the response
	 * @return the integer
	 */
	private Integer insertTransferSetting(TransferSetting transferSetting, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;

		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), TRANSFER_SETTING_INFO_STMT_INSERT, transferSetting,
						response);

		if (!response.isInError())
		{
			insertCount += maintainCustomFeeAssociations(transferSetting, response);

			if (response.isInError())
			{
				return insertCount;
			}

			// Notes
			insertCount +=
					getNoteDAC().maintainNoteAssociations(transferSetting.getNoteList(), transferSetting.getId(),
							PERSON_STMT_ASSOC_TRANSFER_SETTING_WITH_NOTE, response);
		}

		return insertCount;
	}

	/**
	 * Update transfer setting.
	 *
	 * @param transferSetting the transfer setting
	 * @param response the response
	 * @return the integer
	 */
	private Integer updateTransferSetting(TransferSetting transferSetting, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		updateCount =
				QATMyBatisDacHelper.doUpdate(getSqlSession(), TRANSFER_SETTING_INFO_STMT_UPDATE, transferSetting,
						response);

		if (!response.isInError())
		{
			updateCount += maintainCustomFeeAssociations(transferSetting, response);

			if (response.isInError())
			{
				return updateCount;
			}

			// Notes
			updateCount +=
					getNoteDAC().maintainNoteAssociations(transferSetting.getNoteList(), transferSetting.getId(),
							PERSON_STMT_ASSOC_TRANSFER_SETTING_WITH_NOTE, response);
		}

		return updateCount;
	}

	/**
	 * Delete transfer setting.
	 *
	 * @param transferSetting the transfer setting
	 * @param response the response
	 * @return the integer
	 */
	private Integer deleteTransferSetting(TransferSetting transferSetting, InternalResultsResponse<?> response)
	{
		Integer deleteCount = 0;

		deleteCount =
				QATMyBatisDacHelper.doRemove(getSqlSession(), TRANSFER_SETTING_INFO_STMT_DELETE, transferSetting,
						response);

		return deleteCount;
	}

	/**
	 * Insert custom fee.
	 *
	 * @param customFee the custom fee
	 * @param response the response
	 * @return the integer
	 */
	private Integer insertCustomFee(CustomFee customFee, InternalResponse response)
	{
		Integer insertCount = 0;

		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), CUSTOM_FEE_STMT_INSERT, customFee,
						response);

		return insertCount;

	}

	/**
	 * Update custom fee.
	 *
	 * @param customFee the custom fee
	 * @param response the response
	 * @return the integer
	 */
	private Integer updateCustomFee(CustomFee customFee, InternalResponse response)
	{
		Integer updateCount = 0;

		updateCount =
				QATMyBatisDacHelper.doUpdate(getSqlSession(), CUSTOM_FEE_STMT_UPDATE, customFee,
						response);

		return updateCount;

	}

	/**
	 * Delete custom fee.
	 *
	 * @param customFee the custom fee
	 * @param response the response
	 * @return the integer
	 */
	private Integer deleteCustomFee(CustomFee customFee, InternalResponse response)
	{
		Integer deleteCount = 0;

		deleteCount =
				QATMyBatisDacHelper.doRemove(getSqlSession(), CUSTOM_FEE_STMT_DELETE, customFee,
						response);

		return deleteCount;

	}
}
