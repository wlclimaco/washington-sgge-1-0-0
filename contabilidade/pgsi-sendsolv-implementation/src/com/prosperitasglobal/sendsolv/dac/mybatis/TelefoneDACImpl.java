package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import com.prosperitasglobal.sendsolv.dac.ITelefoneDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;

/**
 * The Class TelefoneDACImpl.
 */
public class TelefoneDACImpl extends SqlSessionDaoSupport implements ITelefoneDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "TelefoneMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchTelefoneRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllTelefonesByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchTelefoneById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertTelefone";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_CONTACT = EMPRESA_NAMESPACE
			+ "associateTelefoneWithContact";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateTelefone";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteTelefoneById";

	/** The Constant EMPRESA_DOCUMENT_STMT_UPDATE. */
	private static final String EMPRESA_DOCUMENT_STMT_UPDATE = EMPRESA_NAMESPACE
			+ "updateTelefoneDocument";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT = EMPRESA_NAMESPACE
			+ "associateTelefoneWithDocument";

	/** The Constant EMPRESA_STMT_DELETE_DOCUMENT. */
	private static final String EMPRESA_STMT_DELETE_DOCUMENT = EMPRESA_NAMESPACE
			+ "deleteTelefoneDocument";

	/** The Constant STMT_VERSION. */
	private static final String EMPRESA_STMT_VERSION = EMPRESA_NAMESPACE + "fetchVersionNumber";

	/** The Constant EMPRESA_STMT_UPDATE_EMPRESA_STATUS. */
	private static final String EMPRESA_STMT_UPDATE_EMPRESA_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(TelefoneDACImpl.class);

	/** The valid sort fields for an telefone inquiry. Will be injected by Spring. */
	private Map<String, String> telefoneInquiryValidSortFields;

	/**
	 * Get the valid sort fields for the telefone inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the telefone inquiry.
	 */
	public Map<String, String> getTelefoneInquiryValidSortFields()
	{
		return telefoneInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the telefone inquiry. Attribute injected by Spring.
	 *
	 * @param telefoneInquiryValidSortFields The valid sort fields for the telefone inquiry to set.
	 */
	public void setTelefoneInquiryValidSortFields(Map<String, String> telefoneInquiryValidSortFields)
	{
		this.telefoneInquiryValidSortFields = telefoneInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ITelefoneDAC#insertTelefone(com.prosperitasglobal.sendsolv.model
	 * .Telefone)
	 */
	@Override
	public InternalResultsResponse<Telefone> insertTelefone(Telefone telefone)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Telefone> response = new InternalResultsResponse<Telefone>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, telefone, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount += maintainTelefoneAssociations(telefone, response);

		// Finally, if something was inserted then add the Telefone to the result.
		if (insertCount > 0)
		{
			response.addResult(telefone);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ITelefoneDAC#updateTelefone(com.prosperitasglobal.sendsolv.model
	 * .Telefone)
	 */
	@Override
	public InternalResultsResponse<Telefone> updateTelefone(Telefone telefone)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Telefone> response = new InternalResultsResponse<Telefone>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(telefone.getModelAction())
				&& (telefone.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, telefone,
							response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount += maintainTelefoneAssociations(telefone, response);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(telefone);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ITelefoneDAC#deleteTelefone(com.prosperitasglobal.sendsolv.model
	 * .Telefone)
	 */
	@Override
	public InternalResponse deleteTelefone(Telefone telefone)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, telefone, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.ITelefoneDAC#fetchTelefoneById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Telefone> fetchTelefoneById(FetchByIdRequest request)
	{
		InternalResultsResponse<Telefone> response = new InternalResultsResponse<Telefone>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ITelefoneDAC#fetchTelefoneByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Telefone> fetchTelefoneByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Telefone> response = new InternalResultsResponse<Telefone>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getTelefoneInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/**
	 * Maintain telefone associations.
	 *
	 * @param telefone the telefone
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainTelefoneAssociations(Telefone telefone,
			InternalResultsResponse<Telefone> response)
	{
		Integer count = 0;
		// First Maintain Contacts
		// if (ValidationUtil.isNullOrEmpty(telefone.getContactList()))
		// {
		// return count;
		// }
		// // For Each Contact...
		// for (Contact contact : telefone.getContactList())
		// {
		// // Make sure we set the parent key
		// contact.setParentKey(telefone.getId());
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
		// LOG.debug("ModelAction for Telefone missing!");
		// }
		// break;
		// }
		// }
		return count;
	}

	@Override
	public InternalResultsResponse<Telefone> fetchAllTelefones()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
