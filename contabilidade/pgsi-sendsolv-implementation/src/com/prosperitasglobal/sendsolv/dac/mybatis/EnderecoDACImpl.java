package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import com.prosperitasglobal.sendsolv.dac.IEnderecoDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;

/**
 * The Class EnderecoDACImpl.
 */
public class EnderecoDACImpl extends SqlSessionDaoSupport implements IEnderecoDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "EnderecoMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchEnderecoRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllEnderecosByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchEnderecoById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertEndereco";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_CONTACT = EMPRESA_NAMESPACE
			+ "associateEnderecoWithContact";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateEndereco";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteEnderecoById";

	/** The Constant EMPRESA_DOCUMENT_STMT_UPDATE. */
	private static final String EMPRESA_DOCUMENT_STMT_UPDATE = EMPRESA_NAMESPACE
			+ "updateEnderecoDocument";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT = EMPRESA_NAMESPACE
			+ "associateEnderecoWithDocument";

	/** The Constant EMPRESA_STMT_DELETE_DOCUMENT. */
	private static final String EMPRESA_STMT_DELETE_DOCUMENT = EMPRESA_NAMESPACE
			+ "deleteEnderecoDocument";

	/** The Constant STMT_VERSION. */
	private static final String EMPRESA_STMT_VERSION = EMPRESA_NAMESPACE + "fetchVersionNumber";

	/** The Constant EMPRESA_STMT_UPDATE_EMPRESA_STATUS. */
	private static final String EMPRESA_STMT_UPDATE_EMPRESA_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EnderecoDACImpl.class);

	/** The valid sort fields for an endereco inquiry. Will be injected by Spring. */
	private Map<String, String> enderecoInquiryValidSortFields;

	/**
	 * Get the valid sort fields for the endereco inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the endereco inquiry.
	 */
	public Map<String, String> getEnderecoInquiryValidSortFields()
	{
		return enderecoInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the endereco inquiry. Attribute injected by Spring.
	 *
	 * @param enderecoInquiryValidSortFields The valid sort fields for the endereco inquiry to set.
	 */
	public void setEnderecoInquiryValidSortFields(Map<String, String> enderecoInquiryValidSortFields)
	{
		this.enderecoInquiryValidSortFields = enderecoInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IEnderecoDAC#insertEndereco(com.prosperitasglobal.sendsolv.model
	 * .Endereco)
	 */
	@Override
	public InternalResultsResponse<Endereco> insertEndereco(Endereco endereco)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Endereco> response = new InternalResultsResponse<Endereco>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, endereco, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount += maintainEnderecoAssociations(endereco, response);

		// Finally, if something was inserted then add the Endereco to the result.
		if (insertCount > 0)
		{
			response.addResult(endereco);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IEnderecoDAC#updateEndereco(com.prosperitasglobal.sendsolv.model
	 * .Endereco)
	 */
	@Override
	public InternalResultsResponse<Endereco> updateEndereco(Endereco endereco)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Endereco> response = new InternalResultsResponse<Endereco>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(endereco.getModelAction())
				&& (endereco.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, endereco,
							response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount += maintainEnderecoAssociations(endereco, response);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(endereco);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IEnderecoDAC#deleteEndereco(com.prosperitasglobal.sendsolv.model
	 * .Endereco)
	 */
	@Override
	public InternalResponse deleteEndereco(Endereco endereco)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, endereco, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IEnderecoDAC#fetchEnderecoById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Endereco> fetchEnderecoById(FetchByIdRequest request)
	{
		InternalResultsResponse<Endereco> response = new InternalResultsResponse<Endereco>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IEnderecoDAC#fetchEnderecoByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Endereco> fetchEnderecoByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Endereco> response = new InternalResultsResponse<Endereco>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getEnderecoInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/**
	 * Maintain endereco associations.
	 *
	 * @param endereco the endereco
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainEnderecoAssociations(Endereco endereco,
			InternalResultsResponse<Endereco> response)
	{
		Integer count = 0;
		// First Maintain Contacts
		// if (ValidationUtil.isNullOrEmpty(endereco.getContactList()))
		// {
		// return count;
		// }
		// // For Each Contact...
		// for (Contact contact : endereco.getContactList())
		// {
		// // Make sure we set the parent key
		// contact.setParentKey(endereco.getId());
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
		// LOG.debug("ModelAction for Endereco missing!");
		// }
		// break;
		// }
		// }
		return count;
	}

	@Override
	public InternalResultsResponse<Endereco> fetchAllEnderecos()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
