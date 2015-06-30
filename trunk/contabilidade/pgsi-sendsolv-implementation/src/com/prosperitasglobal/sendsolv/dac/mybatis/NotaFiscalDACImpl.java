package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import com.prosperitasglobal.cbof.dac.INoteDAC;
import com.prosperitasglobal.sendsolv.dac.ICfopDAC;
import com.prosperitasglobal.sendsolv.dac.IContasPagarDAC;
import com.prosperitasglobal.sendsolv.dac.IContasReceberDAC;
import com.prosperitasglobal.sendsolv.dac.IFormaPagamentoDAC;
import com.prosperitasglobal.sendsolv.dac.IItensEspeciaisDAC;
import com.prosperitasglobal.sendsolv.dac.INfStatusDAC;
import com.prosperitasglobal.sendsolv.dac.INotaFiscalDAC;
import com.prosperitasglobal.sendsolv.dac.INotaFiscalItensDAC;
import com.prosperitasglobal.sendsolv.dac.IOrcamentoDAC;
import com.prosperitasglobal.sendsolv.dac.IPedidoCompraDAC;
import com.prosperitasglobal.sendsolv.dac.IServicoItensDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;

/**
 * The Class NotaFiscalDACImpl.
 */
public class NotaFiscalDACImpl extends SqlSessionDaoSupport implements INotaFiscalDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "NotaFiscalMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchNotaFiscalRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllNotaFiscalsByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchNotaFiscalById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertNotaFiscal";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_CONTACT = EMPRESA_NAMESPACE
			+ "associateNotaFiscalWithContact";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateNotaFiscal";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteNotaFiscalById";

	/** The Constant EMPRESA_DOCUMENT_STMT_UPDATE. */
	private static final String EMPRESA_DOCUMENT_STMT_UPDATE = EMPRESA_NAMESPACE
			+ "updateNotaFiscalDocument";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT = EMPRESA_NAMESPACE
			+ "associateNotaFiscalWithDocument";

	/** The Constant EMPRESA_STMT_DELETE_DOCUMENT. */
	private static final String EMPRESA_STMT_DELETE_DOCUMENT = EMPRESA_NAMESPACE
			+ "deleteNotaFiscalDocument";

	/** The Constant STMT_VERSION. */
	private static final String EMPRESA_STMT_VERSION = EMPRESA_NAMESPACE + "fetchVersionNumber";

	/** The Constant EMPRESA_STMT_UPDATE_EMPRESA_STATUS. */
	private static final String EMPRESA_STMT_UPDATE_EMPRESA_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(NotaFiscalDACImpl.class);

	/** The valid sort fields for an cnae inquiry. Will be injected by Spring. */
	private Map<String, String> cnaeInquiryValidSortFields;

	private ITransporteDac transportadorDac;
	private IConhecimentoTransporteDac conhecimentoTransporteDac;
	private ITributacaoDac tributacaoDac;
	private IFormaPagamentoDAC formapgDAC;
	private INotaFiscalItensDAC notaItensDAC;
	private INoteDAC noteDAC;
	private IContasPagarDAC contasPagarDAC;
	private IContasReceberDAC contasReceberDAC;
	private IItensEspeciaisDAC itensEspeciaisDAC;
	private ICfopDAC cfopDAC;
	private IServicoItensDAC servicoItensDAC;
	private INfStatusDAC nfStatusDAC;
	private IFornecedorDAC fornecedorDAC;
	private IPedidoCompraDAC pedidoCompraDAC;
	private IOrcamentoDAC orcamentoDAC;

	/**
	 * Get the valid sort fields for the cnae inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the cnae inquiry.
	 */
	public Map<String, String> getNotaFiscalInquiryValidSortFields()
	{
		return cnaeInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the cnae inquiry. Attribute injected by Spring.
	 *
	 * @param cnaeInquiryValidSortFields The valid sort fields for the cnae inquiry to set.
	 */
	public void setNotaFiscalInquiryValidSortFields(Map<String, String> cnaeInquiryValidSortFields)
	{
		this.cnaeInquiryValidSortFields = cnaeInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.INotaFiscalDAC#insertNotaFiscal(com.prosperitasglobal.sendsolv.model
	 * .NotaFiscal)
	 */
	@Override
	public InternalResultsResponse<NotaFiscal> insertNotaFiscal(NotaFiscal cnae)
	{
		Integer insertCount = 0;
		InternalResultsResponse<NotaFiscal> response = new InternalResultsResponse<NotaFiscal>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, cnae, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount += maintainNotaFiscalAssociations(cnae, response);

		// Finally, if something was inserted then add the NotaFiscal to the result.
		if (insertCount > 0)
		{
			response.addResult(cnae);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.INotaFiscalDAC#updateNotaFiscal(com.prosperitasglobal.sendsolv.model
	 * .NotaFiscal)
	 */
	@Override
	public InternalResultsResponse<NotaFiscal> updateNotaFiscal(NotaFiscal cnae)
	{
		Integer updateCount = 0;
		InternalResultsResponse<NotaFiscal> response = new InternalResultsResponse<NotaFiscal>();

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
		updateCount += maintainNotaFiscalAssociations(cnae, response);

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
	 * com.prosperitasglobal.sendsolv.dac.INotaFiscalDAC#deleteNotaFiscal(com.prosperitasglobal.sendsolv.model
	 * .NotaFiscal)
	 */
	@Override
	public InternalResponse deleteNotaFiscal(NotaFiscal cnae)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, cnae, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.INotaFiscalDAC#fetchNotaFiscalById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<NotaFiscal> fetchNotaFiscalById(FetchByIdRequest request)
	{
		InternalResultsResponse<NotaFiscal> response = new InternalResultsResponse<NotaFiscal>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.INotaFiscalDAC#fetchNotaFiscalByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<NotaFiscal> fetchNotaFiscalByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<NotaFiscal> response = new InternalResultsResponse<NotaFiscal>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getNotaFiscalInquiryValidSortFields());

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
	private Integer maintainNotaFiscalAssociations(NotaFiscal cnae,
			InternalResultsResponse<NotaFiscal> response)
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
		// LOG.debug("ModelAction for NotaFiscal missing!");
		// }
		// break;
		// }
		// }
		return count;
	}

	@Override
	public InternalResultsResponse<NotaFiscal> fetchAllNotaFiscals()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
