package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.prosperitasglobal.sendsolv.dac.ICfopDAC;
import com.prosperitasglobal.sendsolv.dac.IPessoaDAC;
import com.prosperitasglobal.sendsolv.dac.IProdutoDAC;
import com.prosperitasglobal.sendsolv.dac.IUniMedDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.NotesDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.StatusDACD;

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
			+ "associateProdutoWithProduto";

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
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ProdutoDACImpl.class);

	/** The produto dac. */
	private IProdutoDAC produtoDAC;

	private IClassificacaoDAC classificacaoDAC;

	private IUniMedDAC uniMedDAC;

	private IGrupoDAC grupoDAC;

	private ISubGrupoDAC subGrupoDAC;

	private IMarcaDAC marcaDAC;

	private ITributacaoDAC tributacaoDAC;

	private IEstoqueDAC estoqueDAC;

	private ITabPrecoDAC tabPrecoDAC;

	private ICustoDAC custoDAC;

	private IPorcaoDAC porcaoDAC;

	private IRentabilidadeDAC rentabilidadeDAC;

	private ICfopDAC cfopDAC;

	private IPessoaDAC fornecedorDAC;

	/** The valid sort fields for an produto inquiry. Will be injected by Spring. */
	private Map<String, String> produtoInquiryValidSortFields;

	/**
	 * Gets the produto dac.
	 *
	 * @return the produto dac
	 */
	public IProdutoDAC getProdutoDAC()
	{
		return produtoDAC;
	}

	/**
	 * Sets the produto dac.
	 *
	 * @param produtoDAC the produto dac
	 */
	public void setProdutoDAC(IProdutoDAC produtoDAC)
	{
		this.produtoDAC = produtoDAC;
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
		insertCount +=
				NotesDACD.maintainNoteAssociations(pessoa.getNotes(), response, insertCount, null,
						null,
						null, getNoteDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser());

		insertCount +=
				NotesDACD.maintainNoteAssociations(pessoa.getNotes(), response, insertCount, null,
						null,
						null, getNoteDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser());

		insertCount +=
				NotesDACD.maintainNoteAssociations(pessoa.getNotes(), response, insertCount, null,
						null,
						null, getNoteDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser());

		insertCount +=
				NotesDACD.maintainNoteAssociations(pessoa.getNotes(), response, insertCount, null,
						null,
						null, getNoteDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser());

		insertCount +=
				NotesDACD.maintainNoteAssociations(pessoa.getNotes(), response, insertCount, null,
						null,
						null, getNoteDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser());

		insertCount +=
				NotesDACD.maintainNoteAssociations(pessoa.getNotes(), response, insertCount, null,
						null,
						null, getNoteDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser());

		insertCount +=
				NotesDACD.maintainNoteAssociations(pessoa.getNotes(), response, insertCount, null,
						null,
						null, getNoteDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser());

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new ArrayList<Status>();
			insertCount =
					StatusDACD.maintainStatusAssociations(statusList, response, pessoa.getId(), null, AcaoEnum.INSERT,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.EMPRESA, getStatusDAC(),
							getHistoricoDAC());

		}

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
					QATMyBatisDacHelper.doUpdate(getSqlSession(), PRODUTO_STMT_UPDATE, produto, response);
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
	 * @see com.prosperitasglobal.sendsolv.dac.IProdutoDAC#fetchProdutoById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Produto> fetchProdutoById(FetchByIdRequest request)
	{
		InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), PRODUTO_STMT_FETCH_BY_ID, request, response);
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
		// First Maintain Produtos
		// if (ValidationUtil.isNullOrEmpty(produto.getProdutoList()))
		// {
		// return count;
		// }
		// // For Each Produto...
		// for (Produto produto : produto.getProdutoList())
		// {
		// // Make sure we set the parent key
		// produto.setParentKey(produto.getId());
		//
		// if (ValidationUtil.isNull(produto.getModelAction()))
		// {
		// continue;
		// }
		// switch (produto.getModelAction())
		// {
		// case INSERT:
		// count = getProdutoDAC().insertProduto(produto,
		// PRODUTO_STMT_ASSOC_ORG_TO_CONTACT, response);
		// break;
		// case UPDATE:
		// count = getProdutoDAC().updateProduto(produto, response);
		// break;
		// case DELETE:
		// count = getProdutoDAC().deleteBusinessProduto(produto, response);
		// break;
		// default:
		// if (LOG.isDebugEnabled())
		// {
		// LOG.debug("ModelAction for Produto missing!");
		// }
		// break;
		// }
		// }
		return count;
	}

	@Override
	public InternalResultsResponse<Produto> fetchAllProdutos()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Produto> fetchProdutoByRequest(ProdutoInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
