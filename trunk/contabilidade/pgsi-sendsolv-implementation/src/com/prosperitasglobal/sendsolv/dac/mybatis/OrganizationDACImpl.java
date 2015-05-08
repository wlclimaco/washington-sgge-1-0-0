package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.dac.IContactDAC;
import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.IOrganizationDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.RiskDACD;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.Risk;
import com.prosperitasglobal.sendsolv.model.request.DocumentMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RiskMaintenanceRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class OrganizationDACImpl.
 */
public class OrganizationDACImpl extends SqlSessionDaoSupport implements IOrganizationDAC
{

	/** The Constant ORGANIZATION_NAMESPACE. */
	private static final String ORGANIZATION_NAMESPACE = "OrganizationMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant ORGANIZATION_STMT_FETCH_COUNT. */
	private static final String ORGANIZATION_STMT_FETCH_COUNT = ORGANIZATION_NAMESPACE + "fetchOrganizationRowCount";

	/** The Constant ORGANIZATION_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String ORGANIZATION_STMT_FETCH_ALL_BY_REQUEST = ORGANIZATION_NAMESPACE
			+ "fetchAllOrganizationsByRequest";

	/** The Constant ORGANIZATION_STMT_FETCH_BY_ID. */
	private static final String ORGANIZATION_STMT_FETCH_BY_ID = ORGANIZATION_NAMESPACE + "fetchOrganizationById";

	/** The Constant ORGANIZATION_STMT_INSERT. */
	private static final String ORGANIZATION_STMT_INSERT = ORGANIZATION_NAMESPACE + "insertOrganization";

	/** The Constant ORGANIZATION_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String ORGANIZATION_STMT_ASSOC_ORG_TO_CONTACT = ORGANIZATION_NAMESPACE
			+ "associateOrganizationWithContact";

	/** The Constant ORGANIZATION_STMT_UPDATE. */
	private static final String ORGANIZATION_STMT_UPDATE = ORGANIZATION_NAMESPACE + "updateOrganization";

	/** The Constant ORGANIZATION_STMT_DELETE. */
	private static final String ORGANIZATION_STMT_DELETE = ORGANIZATION_NAMESPACE + "deleteOrganizationById";

	/** The Constant ORGANIZATION_DOCUMENT_STMT_UPDATE. */
	private static final String ORGANIZATION_DOCUMENT_STMT_UPDATE = ORGANIZATION_NAMESPACE
			+ "updateOrganizationDocument";

	/** The Constant ORGANIZATION_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String ORGANIZATION_STMT_ASSOC_ORG_TO_DOCUMENT = ORGANIZATION_NAMESPACE
			+ "associateOrganizationWithDocument";

	/** The Constant ORGANIZATION_STMT_DELETE_DOCUMENT. */
	private static final String ORGANIZATION_STMT_DELETE_DOCUMENT = ORGANIZATION_NAMESPACE
			+ "deleteOrganizationDocument";

	/** The Constant STMT_VERSION. */
	private static final String ORGANIZATION_STMT_VERSION = ORGANIZATION_NAMESPACE + "fetchVersionNumber";

	/** The Constant ORGANIZATION_STMT_UPDATE_ORGANIZATION_STATUS. */
	private static final String ORGANIZATION_STMT_UPDATE_ORGANIZATION_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(OrganizationDACImpl.class);

	/** The contact dac. */
	private IContactDAC contactDAC;

	/** The valid sort fields for an organization inquiry. Will be injected by Spring. */
	private Map<String, String> organizationInquiryValidSortFields;

	/**
	 * Gets the contact dac.
	 *
	 * @return the contact dac
	 */
	public IContactDAC getContactDAC()
	{
		return contactDAC;
	}

	/**
	 * Sets the contact dac.
	 *
	 * @param contactDAC the contact dac
	 */
	public void setContactDAC(IContactDAC contactDAC)
	{
		this.contactDAC = contactDAC;
	}

	/**
	 * Get the valid sort fields for the organization inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the organization inquiry.
	 */
	public Map<String, String> getOrganizationInquiryValidSortFields()
	{
		return organizationInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the organization inquiry. Attribute injected by Spring.
	 *
	 * @param organizationInquiryValidSortFields The valid sort fields for the organization inquiry to set.
	 */
	public void setOrganizationInquiryValidSortFields(Map<String, String> organizationInquiryValidSortFields)
	{
		this.organizationInquiryValidSortFields = organizationInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrganizationDAC#insertOrganization(com.prosperitasglobal.sendsolv.model
	 * .Organization)
	 */
	@Override
	public InternalResultsResponse<Organization> insertOrganization(Organization organization)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Organization> response = new InternalResultsResponse<Organization>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), ORGANIZATION_STMT_INSERT, organization, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount += maintainOrganizationAssociations(organization, response);

		// Finally, if something was inserted then add the Organization to the result.
		if (insertCount > 0)
		{
			response.addResult(organization);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrganizationDAC#insertDocument(com.prosperitasglobal.sendsolv.model.request
	 * .DocumentMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Document> insertDocument(DocumentMaintenanceRequest request)
	{
		Integer insertCount = 0;

		InternalResultsResponse<Document> response = new InternalResultsResponse<Document>();
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), ORGANIZATION_STMT_ASSOC_ORG_TO_DOCUMENT,
						request.getDocument(), response);

		// Finally, if something was inserted then add the Organization to the result.
		if (insertCount > 0)
		{
			response.addResult(request.getDocument());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrganizationDAC#updateOrganization(com.prosperitasglobal.sendsolv.model
	 * .Organization)
	 */
	@Override
	public InternalResultsResponse<Organization> updateOrganization(Organization organization)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Organization> response = new InternalResultsResponse<Organization>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(organization.getModelAction())
				&& (organization.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdateOL(getSqlSession(), ORGANIZATION_STMT_UPDATE, organization,
							ORGANIZATION_STMT_VERSION, response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount += maintainOrganizationAssociations(organization, response);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(organization);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrganizationDAC#updateDocument(com.prosperitasglobal.sendsolv.model.request
	 * .DocumentMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Document> updateDocument(DocumentMaintenanceRequest request)
	{
		InternalResultsResponse<Document> response = new InternalResultsResponse<Document>();
		QATMyBatisDacHelper.doUpdate(getSqlSession(), ORGANIZATION_DOCUMENT_STMT_UPDATE, request.getDocument(),
				response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrganizationDAC#deleteOrganization(com.prosperitasglobal.sendsolv.model
	 * .Organization)
	 */
	@Override
	public InternalResponse deleteOrganization(Organization organization)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), ORGANIZATION_STMT_DELETE, organization, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrganizationDAC#deleteDocument(com.prosperitasglobal.sendsolv.model.request
	 * .DocumentMaintenanceRequest)
	 */
	@Override
	public InternalResponse deleteDocument(DocumentMaintenanceRequest request)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), ORGANIZATION_STMT_DELETE_DOCUMENT, request.getDocument(),
				response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IOrganizationDAC#fetchOrganizationById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Organization> fetchOrganizationById(FetchByIdRequest request)
	{
		InternalResultsResponse<Organization> response = new InternalResultsResponse<Organization>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), ORGANIZATION_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrganizationDAC#fetchOrganizationByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Organization> fetchOrganizationByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Organization> response = new InternalResultsResponse<Organization>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getOrganizationInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, ORGANIZATION_STMT_FETCH_COUNT,
				ORGANIZATION_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IOrganizationDAC#updateRisk(com.prosperitasglobal.sendsolv.model.request.
	 * RiskMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Risk> updateRisk(RiskMaintenanceRequest request)
	{
		return RiskDACD.updateRisk(getSqlSession(), request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrganizationDAC#applyOrganizationStatus(com.prosperitasglobal.sendsolv.model
	 * .Organization)
	 */
	@Override
	public InternalResponse applyOrganizationStatus(Organization organization)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doUpdate(getSqlSession(), ORGANIZATION_STMT_UPDATE_ORGANIZATION_STATUS, organization,
				response);

		return response;
	}

	/**
	 * Maintain organization associations.
	 *
	 * @param organization the organization
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainOrganizationAssociations(Organization organization,
			InternalResultsResponse<Organization> response)
	{
		Integer count = 0;
		// First Maintain Contacts
		if (ValidationUtil.isNullOrEmpty(organization.getContactList()))
		{
			return count;
		}
		// For Each Contact...
		for (Contact contact : organization.getContactList())
		{
			// Make sure we set the parent key
			contact.setParentKey(organization.getId());

			if (ValidationUtil.isNull(contact.getModelAction()))
			{
				continue;
			}
			switch (contact.getModelAction())
			{
				case INSERT:
					count = getContactDAC().insertContact(contact,
							ORGANIZATION_STMT_ASSOC_ORG_TO_CONTACT, response);
					break;
				case UPDATE:
					count = getContactDAC().updateContact(contact, response);
					break;
				case DELETE:
					count = getContactDAC().deleteBusinessContact(contact, response);
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("ModelAction for Organization missing!");
					}
					break;
			}
		}
		return count;
	}
}
