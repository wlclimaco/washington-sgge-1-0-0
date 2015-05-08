package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;
import java.util.logging.Logger;

import com.prosperitasglobal.cbof.dac.IContactDAC;
import com.prosperitasglobal.sendsolv.dac.ITabelaDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.RiskDACD;

/**
 * The Class TabelaDACImpl.
 */
public class TabelaDACImpl extends SqlSessionDaoSupport implements ITabelaDAC
{

	/** The Constant TABELA_NAMESPACE. */
	private static final String TABELA_NAMESPACE = "TabelaMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant TABELA_STMT_FETCH_COUNT. */
	private static final String TABELA_STMT_FETCH_COUNT = TABELA_NAMESPACE + "fetchTabelaRowCount";

	/** The Constant TABELA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String TABELA_STMT_FETCH_ALL_BY_REQUEST = TABELA_NAMESPACE
			+ "fetchAllTabelasByRequest";

	/** The Constant TABELA_STMT_FETCH_BY_ID. */
	private static final String TABELA_STMT_FETCH_BY_ID = TABELA_NAMESPACE + "fetchTabelaById";

	/** The Constant TABELA_STMT_INSERT. */
	private static final String TABELA_STMT_INSERT = TABELA_NAMESPACE + "insertTabela";

	/** The Constant TABELA_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String TABELA_STMT_ASSOC_ORG_TO_CONTACT = TABELA_NAMESPACE
			+ "associateTabelaWithContact";

	/** The Constant TABELA_STMT_UPDATE. */
	private static final String TABELA_STMT_UPDATE = TABELA_NAMESPACE + "updateTabela";

	/** The Constant TABELA_STMT_DELETE. */
	private static final String TABELA_STMT_DELETE = TABELA_NAMESPACE + "deleteTabelaById";

	/** The Constant TABELA_DOCUMENT_STMT_UPDATE. */
	private static final String TABELA_DOCUMENT_STMT_UPDATE = TABELA_NAMESPACE
			+ "updateTabelaDocument";

	/** The Constant TABELA_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String TABELA_STMT_ASSOC_ORG_TO_DOCUMENT = TABELA_NAMESPACE
			+ "associateTabelaWithDocument";

	/** The Constant TABELA_STMT_DELETE_DOCUMENT. */
	private static final String TABELA_STMT_DELETE_DOCUMENT = TABELA_NAMESPACE
			+ "deleteTabelaDocument";

	/** The Constant STMT_VERSION. */
	private static final String TABELA_STMT_VERSION = TABELA_NAMESPACE + "fetchVersionNumber";

	/** The Constant TABELA_STMT_UPDATE_TABELA_STATUS. */
	private static final String TABELA_STMT_UPDATE_TABELA_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TabelaDACImpl.class);

	/** The contact dac. */
	private IContactDAC contactDAC;

	/** The valid sort fields for an tabela inquiry. Will be injected by Spring. */
	private Map<String, String> tabelaInquiryValidSortFields;

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
	 * Get the valid sort fields for the tabela inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the tabela inquiry.
	 */
	public Map<String, String> getTabelaInquiryValidSortFields()
	{
		return tabelaInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the tabela inquiry. Attribute injected by Spring.
	 *
	 * @param tabelaInquiryValidSortFields The valid sort fields for the tabela inquiry to set.
	 */
	public void setTabelaInquiryValidSortFields(Map<String, String> tabelaInquiryValidSortFields)
	{
		this.tabelaInquiryValidSortFields = tabelaInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ITabelaDAC#insertTabela(com.prosperitasglobal.sendsolv.model
	 * .Tabela)
	 */
	@Override
	public InternalResultsResponse<Tabela> insertTabela(Tabela tabela)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Tabela> response = new InternalResultsResponse<Tabela>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), TABELA_STMT_INSERT, tabela, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount += maintainTabelaAssociations(tabela, response);

		// Finally, if something was inserted then add the Tabela to the result.
		if (insertCount > 0)
		{
			response.addResult(tabela);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ITabelaDAC#insertDocument(com.prosperitasglobal.sendsolv.model.request
	 * .DocumentMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Document> insertDocument(DocumentMaintenanceRequest request)
	{
		Integer insertCount = 0;

		InternalResultsResponse<Document> response = new InternalResultsResponse<Document>();
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), TABELA_STMT_ASSOC_ORG_TO_DOCUMENT,
						request.getDocument(), response);

		// Finally, if something was inserted then add the Tabela to the result.
		if (insertCount > 0)
		{
			response.addResult(request.getDocument());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ITabelaDAC#updateTabela(com.prosperitasglobal.sendsolv.model
	 * .Tabela)
	 */
	@Override
	public InternalResultsResponse<Tabela> updateTabela(Tabela tabela)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Tabela> response = new InternalResultsResponse<Tabela>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(tabela.getModelAction())
				&& (tabela.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdateOL(getSqlSession(), TABELA_STMT_UPDATE, tabela,
							TABELA_STMT_VERSION, response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount += maintainTabelaAssociations(tabela, response);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(tabela);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ITabelaDAC#updateDocument(com.prosperitasglobal.sendsolv.model.request
	 * .DocumentMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Document> updateDocument(DocumentMaintenanceRequest request)
	{
		InternalResultsResponse<Document> response = new InternalResultsResponse<Document>();
		QATMyBatisDacHelper.doUpdate(getSqlSession(), TABELA_DOCUMENT_STMT_UPDATE, request.getDocument(),
				response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ITabelaDAC#deleteTabela(com.prosperitasglobal.sendsolv.model
	 * .Tabela)
	 */
	@Override
	public InternalResponse deleteTabela(Tabela tabela)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), TABELA_STMT_DELETE, tabela, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ITabelaDAC#deleteDocument(com.prosperitasglobal.sendsolv.model.request
	 * .DocumentMaintenanceRequest)
	 */
	@Override
	public InternalResponse deleteDocument(DocumentMaintenanceRequest request)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), TABELA_STMT_DELETE_DOCUMENT, request.getDocument(),
				response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.ITabelaDAC#fetchTabelaById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Tabela> fetchTabelaById(FetchByIdRequest request)
	{
		InternalResultsResponse<Tabela> response = new InternalResultsResponse<Tabela>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), TABELA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ITabelaDAC#fetchTabelaByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Tabela> fetchTabelaByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Tabela> response = new InternalResultsResponse<Tabela>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getTabelaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, TABELA_STMT_FETCH_COUNT,
				TABELA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.ITabelaDAC#updateRisk(com.prosperitasglobal.sendsolv.model.request.
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
	 * com.prosperitasglobal.sendsolv.dac.ITabelaDAC#applyTabelaStatus(com.prosperitasglobal.sendsolv.model
	 * .Tabela)
	 */
	@Override
	public InternalResponse applyTabelaStatus(Tabela tabela)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doUpdate(getSqlSession(), TABELA_STMT_UPDATE_TABELA_STATUS, tabela,
				response);

		return response;
	}

	/**
	 * Maintain tabela associations.
	 *
	 * @param tabela the tabela
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainTabelaAssociations(Tabela tabela,
			InternalResultsResponse<Tabela> response)
	{
		Integer count = 0;
		// First Maintain Contacts
		if (ValidationUtil.isNullOrEmpty(tabela.getContactList()))
		{
			return count;
		}
		// For Each Contact...
		for (Contact contact : tabela.getContactList())
		{
			// Make sure we set the parent key
			contact.setParentKey(tabela.getId());

			if (ValidationUtil.isNull(contact.getModelAction()))
			{
				continue;
			}
			switch (contact.getModelAction())
			{
				case INSERT:
					count = getContactDAC().insertContact(contact,
							TABELA_STMT_ASSOC_ORG_TO_CONTACT, response);
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
						LOG.debug("ModelAction for Tabela missing!");
					}
					break;
			}
		}
		return count;
	}
}
