package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;
import java.util.logging.Logger;

import com.prosperitasglobal.cbof.dac.IContactDAC;
import com.prosperitasglobal.sendsolv.dac.IProdutoDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.RiskDACD;

/**
 * The Class ProdutoDACImpl.
 */
public class ProdutoDACImpl extends SqlSessionDaoSupport implements IProdutoDAC
{

	/** The Constant PRODUTO_NAMESPACE. */
	private static final String PRODUTO_NAMESPACE = "ProdutoMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant PRODUTO_STMT_FETCH_COUNT. */
	private static final String PRODUTO_STMT_FETCH_COUNT = PRODUTO_NAMESPACE + "fetchProdutoRowCount";

	/** The Constant PRODUTO_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String PRODUTO_STMT_FETCH_ALL_BY_REQUEST = PRODUTO_NAMESPACE
			+ "fetchAllProdutosByRequest";

	/** The Constant PRODUTO_STMT_FETCH_BY_ID. */
	private static final String PRODUTO_STMT_FETCH_BY_ID = PRODUTO_NAMESPACE + "fetchProdutoById";

	/** The Constant PRODUTO_STMT_INSERT. */
	private static final String PRODUTO_STMT_INSERT = PRODUTO_NAMESPACE + "insertProduto";

	/** The Constant PRODUTO_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String PRODUTO_STMT_ASSOC_ORG_TO_CONTACT = PRODUTO_NAMESPACE
			+ "associateProdutoWithContact";

	/** The Constant PRODUTO_STMT_UPDATE. */
	private static final String PRODUTO_STMT_UPDATE = PRODUTO_NAMESPACE + "updateProduto";

	/** The Constant PRODUTO_STMT_DELETE. */
	private static final String PRODUTO_STMT_DELETE = PRODUTO_NAMESPACE + "deleteProdutoById";

	/** The Constant PRODUTO_DOCUMENT_STMT_UPDATE. */
	private static final String PRODUTO_DOCUMENT_STMT_UPDATE = PRODUTO_NAMESPACE
			+ "updateProdutoDocument";

	/** The Constant PRODUTO_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String PRODUTO_STMT_ASSOC_ORG_TO_DOCUMENT = PRODUTO_NAMESPACE
			+ "associateProdutoWithDocument";

	/** The Constant PRODUTO_STMT_DELETE_DOCUMENT. */
	private static final String PRODUTO_STMT_DELETE_DOCUMENT = PRODUTO_NAMESPACE
			+ "deleteProdutoDocument";

	/** The Constant STMT_VERSION. */
	private static final String PRODUTO_STMT_VERSION = PRODUTO_NAMESPACE + "fetchVersionNumber";

	/** The Constant PRODUTO_STMT_UPDATE_PRODUTO_STATUS. */
	private static final String PRODUTO_STMT_UPDATE_PRODUTO_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProdutoDACImpl.class);

	/** The contact dac. */
	private IContactDAC contactDAC;

	/** The valid sort fields for an produto inquiry. Will be injected by Spring. */
	private Map<String, String> produtoInquiryValidSortFields;

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
	 * Get the valid sort fields for the produto inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the produto inquiry.
	 */
	public Map<String, String> getProdutoInquiryValidSortFields()
	{
		return produtoInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the produto inquiry. Attribute injected by Spring.
	 *
	 * @param produtoInquiryValidSortFields The valid sort fields for the produto inquiry to set.
	 */
	public void setProdutoInquiryValidSortFields(Map<String, String> produtoInquiryValidSortFields)
	{
		this.produtoInquiryValidSortFields = produtoInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IProdutoDAC#insertProduto(com.prosperitasglobal.sendsolv.model
	 * .Produto)
	 */
	@Override
	public InternalResultsResponse<Produto> insertProduto(Produto produto)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), PRODUTO_STMT_INSERT, produto, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount += maintainProdutoAssociations(produto, response);

		// Finally, if something was inserted then add the Produto to the result.
		if (insertCount > 0)
		{
			response.addResult(produto);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IProdutoDAC#insertDocument(com.prosperitasglobal.sendsolv.model.request
	 * .DocumentMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Document> insertDocument(DocumentMaintenanceRequest request)
	{
		Integer insertCount = 0;

		InternalResultsResponse<Document> response = new InternalResultsResponse<Document>();
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), PRODUTO_STMT_ASSOC_ORG_TO_DOCUMENT,
						request.getDocument(), response);

		// Finally, if something was inserted then add the Produto to the result.
		if (insertCount > 0)
		{
			response.addResult(request.getDocument());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IProdutoDAC#updateProduto(com.prosperitasglobal.sendsolv.model
	 * .Produto)
	 */
	@Override
	public InternalResultsResponse<Produto> updateProduto(Produto produto)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(produto.getModelAction())
				&& (produto.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdateOL(getSqlSession(), PRODUTO_STMT_UPDATE, produto,
							PRODUTO_STMT_VERSION, response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount += maintainProdutoAssociations(produto, response);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(produto);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IProdutoDAC#updateDocument(com.prosperitasglobal.sendsolv.model.request
	 * .DocumentMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Document> updateDocument(DocumentMaintenanceRequest request)
	{
		InternalResultsResponse<Document> response = new InternalResultsResponse<Document>();
		QATMyBatisDacHelper.doUpdate(getSqlSession(), PRODUTO_DOCUMENT_STMT_UPDATE, request.getDocument(),
				response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IProdutoDAC#deleteProduto(com.prosperitasglobal.sendsolv.model
	 * .Produto)
	 */
	@Override
	public InternalResponse deleteProduto(Produto produto)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), PRODUTO_STMT_DELETE, produto, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IProdutoDAC#deleteDocument(com.prosperitasglobal.sendsolv.model.request
	 * .DocumentMaintenanceRequest)
	 */
	@Override
	public InternalResponse deleteDocument(DocumentMaintenanceRequest request)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), PRODUTO_STMT_DELETE_DOCUMENT, request.getDocument(),
				response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IProdutoDAC#fetchProdutoById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Produto> fetchProdutoById(FetchByIdRequest request)
	{
		InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), PRODUTO_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IProdutoDAC#fetchProdutoByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Produto> fetchProdutoByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getProdutoInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, PRODUTO_STMT_FETCH_COUNT,
				PRODUTO_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IProdutoDAC#updateRisk(com.prosperitasglobal.sendsolv.model.request.
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
	 * com.prosperitasglobal.sendsolv.dac.IProdutoDAC#applyProdutoStatus(com.prosperitasglobal.sendsolv.model
	 * .Produto)
	 */
	@Override
	public InternalResponse applyProdutoStatus(Produto produto)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doUpdate(getSqlSession(), PRODUTO_STMT_UPDATE_PRODUTO_STATUS, produto,
				response);

		return response;
	}

	/**
	 * Maintain produto associations.
	 *
	 * @param produto the produto
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainProdutoAssociations(Produto produto,
			InternalResultsResponse<Produto> response)
	{
		Integer count = 0;
		// First Maintain Contacts
		if (ValidationUtil.isNullOrEmpty(produto.getContactList()))
		{
			return count;
		}
		// For Each Contact...
		for (Contact contact : produto.getContactList())
		{
			// Make sure we set the parent key
			contact.setParentKey(produto.getId());

			if (ValidationUtil.isNull(contact.getModelAction()))
			{
				continue;
			}
			switch (contact.getModelAction())
			{
				case INSERT:
					count = getContactDAC().insertContact(contact,
							PRODUTO_STMT_ASSOC_ORG_TO_CONTACT, response);
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
						LOG.debug("ModelAction for Produto missing!");
					}
					break;
			}
		}
		return count;
	}
}
