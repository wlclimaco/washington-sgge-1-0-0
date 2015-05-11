package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import com.prosperitasglobal.sendsolv.dac.ICnaeDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;

/**
 * The Class CnaeDACImpl.
 */
public class CnaeDACImpl extends SqlSessionDaoSupport implements ICnaeDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "CnaeMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchCnaeRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllCnaesByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchCnaeById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertCnae";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_CONTACT = EMPRESA_NAMESPACE
			+ "associateCnaeWithContact";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateCnae";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteCnaeById";

	/** The Constant EMPRESA_DOCUMENT_STMT_UPDATE. */
	private static final String EMPRESA_DOCUMENT_STMT_UPDATE = EMPRESA_NAMESPACE
			+ "updateCnaeDocument";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT = EMPRESA_NAMESPACE
			+ "associateCnaeWithDocument";

	/** The Constant EMPRESA_STMT_DELETE_DOCUMENT. */
	private static final String EMPRESA_STMT_DELETE_DOCUMENT = EMPRESA_NAMESPACE
			+ "deleteCnaeDocument";

	/** The Constant STMT_VERSION. */
	private static final String EMPRESA_STMT_VERSION = EMPRESA_NAMESPACE + "fetchVersionNumber";

	/** The Constant EMPRESA_STMT_UPDATE_EMPRESA_STATUS. */
	private static final String EMPRESA_STMT_UPDATE_EMPRESA_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CnaeDACImpl.class);

	/** The valid sort fields for an cnae inquiry. Will be injected by Spring. */
	private Map<String, String> cnaeInquiryValidSortFields;

	/**
	 * Get the valid sort fields for the cnae inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the cnae inquiry.
	 */
	public Map<String, String> getCnaeInquiryValidSortFields()
	{
		return cnaeInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the cnae inquiry. Attribute injected by Spring.
	 *
	 * @param cnaeInquiryValidSortFields The valid sort fields for the cnae inquiry to set.
	 */
	public void setCnaeInquiryValidSortFields(Map<String, String> cnaeInquiryValidSortFields)
	{
		this.cnaeInquiryValidSortFields = cnaeInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ICnaeDAC#insertCnae(com.prosperitasglobal.sendsolv.model
	 * .Cnae)
	 */
	@Override
	public InternalResultsResponse<Cnae> insertCnae(Cnae cnae)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, cnae, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount += maintainCnaeAssociations(cnae, response);

		// Finally, if something was inserted then add the Cnae to the result.
		if (insertCount > 0)
		{
			response.addResult(cnae);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ICnaeDAC#updateCnae(com.prosperitasglobal.sendsolv.model
	 * .Cnae)
	 */
	@Override
	public InternalResultsResponse<Cnae> updateCnae(Cnae cnae)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(cnae.getModelAction())
				&& (cnae.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, cnae,
							response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount += maintainCnaeAssociations(cnae, response);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(cnae);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ICnaeDAC#deleteCnae(com.prosperitasglobal.sendsolv.model
	 * .Cnae)
	 */
	@Override
	public InternalResponse deleteCnae(Cnae cnae)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, cnae, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.ICnaeDAC#fetchCnaeById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Cnae> fetchCnaeById(FetchByIdRequest request)
	{
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ICnaeDAC#fetchCnaeByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Cnae> fetchCnaeByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getCnaeInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/**
	 * Maintain cnae associations.
	 *
	 * @param cnae the cnae
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainCnaeAssociations(Cnae cnae,
			InternalResultsResponse<Cnae> response)
	{
		Integer count = 0;
		// First Maintain Contacts
		// if (ValidationUtil.isNullOrEmpty(cnae.getContactList()))
		// {
		// return count;
		// }
		// // For Each Contact...
		// for (Contact contact : cnae.getContactList())
		// {
		// // Make sure we set the parent key
		// contact.setParentKey(cnae.getId());
		//
		// if (ValidationUtil.isNull(contact.getModelAction()))
		// {
		// continue;
		// }
		// switch (contact.getModelAction())
		// {
		// case INSERT:
		// count = getContactDAC().insertContact(contact,
		// EMPRESA_STMT_ASSOC_ORG_TO_CONTACT, response);
		// break;
		// case UPDATE:
		// count = getContactDAC().updateContact(contact, response);
		// break;
		// case DELETE:
		// count = getContactDAC().deleteBusinessContact(contact, response);
		// break;
		// default:
		// if (LOG.isDebugEnabled())
		// {
		// LOG.debug("ModelAction for Cnae missing!");
		// }
		// break;
		// }
		// }
		return count;
	}

	@Override
	public InternalResultsResponse<Cnae> fetchAllCnaes()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
