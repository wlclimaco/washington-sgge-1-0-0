package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.prosperitasglobal.sendsolv.dac.ICfopDAC;
import com.prosperitasglobal.sendsolv.dac.IClassificacaoDAC;
import com.prosperitasglobal.sendsolv.dac.ICustoDAC;
import com.prosperitasglobal.sendsolv.dac.IEstoqueDAC;
import com.prosperitasglobal.sendsolv.dac.IGrupoDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.IMarcaDAC;
import com.prosperitasglobal.sendsolv.dac.IPessoaDAC;
import com.prosperitasglobal.sendsolv.dac.IPorcaoDAC;
import com.prosperitasglobal.sendsolv.dac.IProdutoDAC;
import com.prosperitasglobal.sendsolv.dac.IRentabilidadeDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.dac.ISubGrupoDAC;
import com.prosperitasglobal.sendsolv.dac.ITabPrecoDAC;
import com.prosperitasglobal.sendsolv.dac.ITributacaoDAC;
import com.prosperitasglobal.sendsolv.dac.IUniMedDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.CfopDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.ClassificacaoDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.CustoDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.EstoqueDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.FornecedorDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.GrupoDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.MarcaDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PorcaoDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PrecoDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.RentabilidadeDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.StatusDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.SubGrupoDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.TributacaoDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.UniMedDACD;

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

	private IHistoricoDAC historicoDAC;
	private IStatusDAC statusDAC;

	/** The valid sort fields for an produto inquiry. Will be injected by Spring. */
	private Map<String, String> produtoInquiryValidSortFields;

	/**
	 * @return the historicoDAC
	 */
	public IHistoricoDAC getHistoricoDAC()
	{
		return historicoDAC;
	}

	/**
	 * @param historicoDAC the historicoDAC to set
	 */
	public void setHistoricoDAC(IHistoricoDAC historicoDAC)
	{
		this.historicoDAC = historicoDAC;
	}

	/**
	 * @return the statusDAC
	 */
	public IStatusDAC getStatusDAC()
	{
		return statusDAC;
	}

	/**
	 * @param statusDAC the statusDAC to set
	 */
	public void setStatusDAC(IStatusDAC statusDAC)
	{
		this.statusDAC = statusDAC;
	}

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
	 * @return the classificacaoDAC
	 */
	public IClassificacaoDAC getClassificacaoDAC()
	{
		return classificacaoDAC;
	}

	/**
	 * @param classificacaoDAC the classificacaoDAC to set
	 */
	public void setClassificacaoDAC(IClassificacaoDAC classificacaoDAC)
	{
		this.classificacaoDAC = classificacaoDAC;
	}

	/**
	 * @return the uniMedDAC
	 */
	public IUniMedDAC getUniMedDAC()
	{
		return uniMedDAC;
	}

	/**
	 * @param uniMedDAC the uniMedDAC to set
	 */
	public void setUniMedDAC(IUniMedDAC uniMedDAC)
	{
		this.uniMedDAC = uniMedDAC;
	}

	/**
	 * @return the grupoDAC
	 */
	public IGrupoDAC getGrupoDAC()
	{
		return grupoDAC;
	}

	/**
	 * @param grupoDAC the grupoDAC to set
	 */
	public void setGrupoDAC(IGrupoDAC grupoDAC)
	{
		this.grupoDAC = grupoDAC;
	}

	/**
	 * @return the subGrupoDAC
	 */
	public ISubGrupoDAC getSubGrupoDAC()
	{
		return subGrupoDAC;
	}

	/**
	 * @param subGrupoDAC the subGrupoDAC to set
	 */
	public void setSubGrupoDAC(ISubGrupoDAC subGrupoDAC)
	{
		this.subGrupoDAC = subGrupoDAC;
	}

	/**
	 * @return the marcaDAC
	 */
	public IMarcaDAC getMarcaDAC()
	{
		return marcaDAC;
	}

	/**
	 * @param marcaDAC the marcaDAC to set
	 */
	public void setMarcaDAC(IMarcaDAC marcaDAC)
	{
		this.marcaDAC = marcaDAC;
	}

	/**
	 * @return the tributacaoDAC
	 */
	public ITributacaoDAC getTributacaoDAC()
	{
		return tributacaoDAC;
	}

	/**
	 * @param tributacaoDAC the tributacaoDAC to set
	 */
	public void setTributacaoDAC(ITributacaoDAC tributacaoDAC)
	{
		this.tributacaoDAC = tributacaoDAC;
	}

	/**
	 * @return the estoqueDAC
	 */
	public IEstoqueDAC getEstoqueDAC()
	{
		return estoqueDAC;
	}

	/**
	 * @param estoqueDAC the estoqueDAC to set
	 */
	public void setEstoqueDAC(IEstoqueDAC estoqueDAC)
	{
		this.estoqueDAC = estoqueDAC;
	}

	/**
	 * @return the tabPrecoDAC
	 */
	public ITabPrecoDAC getTabPrecoDAC()
	{
		return tabPrecoDAC;
	}

	/**
	 * @param tabPrecoDAC the tabPrecoDAC to set
	 */
	public void setTabPrecoDAC(ITabPrecoDAC tabPrecoDAC)
	{
		this.tabPrecoDAC = tabPrecoDAC;
	}

	/**
	 * @return the custoDAC
	 */
	public ICustoDAC getCustoDAC()
	{
		return custoDAC;
	}

	/**
	 * @param custoDAC the custoDAC to set
	 */
	public void setCustoDAC(ICustoDAC custoDAC)
	{
		this.custoDAC = custoDAC;
	}

	/**
	 * @return the porcaoDAC
	 */
	public IPorcaoDAC getPorcaoDAC()
	{
		return porcaoDAC;
	}

	/**
	 * @param porcaoDAC the porcaoDAC to set
	 */
	public void setPorcaoDAC(IPorcaoDAC porcaoDAC)
	{
		this.porcaoDAC = porcaoDAC;
	}

	/**
	 * @return the rentabilidadeDAC
	 */
	public IRentabilidadeDAC getRentabilidadeDAC()
	{
		return rentabilidadeDAC;
	}

	/**
	 * @param rentabilidadeDAC the rentabilidadeDAC to set
	 */
	public void setRentabilidadeDAC(IRentabilidadeDAC rentabilidadeDAC)
	{
		this.rentabilidadeDAC = rentabilidadeDAC;
	}

	/**
	 * @return the cfopDAC
	 */
	public ICfopDAC getCfopDAC()
	{
		return cfopDAC;
	}

	/**
	 * @param cfopDAC the cfopDAC to set
	 */
	public void setCfopDAC(ICfopDAC cfopDAC)
	{
		this.cfopDAC = cfopDAC;
	}

	/**
	 * @return the fornecedorDAC
	 */
	public IPessoaDAC getFornecedorDAC()
	{
		return fornecedorDAC;
	}

	/**
	 * @param fornecedorDAC the fornecedorDAC to set
	 */
	public void setFornecedorDAC(IPessoaDAC fornecedorDAC)
	{
		this.fornecedorDAC = fornecedorDAC;
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
				ClassificacaoDACD.maintainClassificacaoAssociations(produto.getClassificacao(), response, insertCount,
						null,
						null,
						null, getClassificacaoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());

		insertCount +=
				UniMedDACD.maintainUniMedAssociations(produto.getUniMed(), response, insertCount, null,
						null,
						null, getUniMedDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());

		insertCount +=
				GrupoDACD.maintainGrupoAssociations(produto.getGrupo(), response, insertCount, null,
						null,
						null, getGrupoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());

		insertCount +=
				SubGrupoDACD.maintainSubGrupoAssociations(produto.getSubGrupo(), response, insertCount, null,
						null,
						null, getSubGrupoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());

		insertCount +=
				MarcaDACD.maintainMarcaAssociations(produto.getMarca(), response, insertCount, null,
						null,
						null, getMarcaDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());

		insertCount +=
				TributacaoDACD.maintainTributacaoAssociations(produto.getTributacao(), response, insertCount, null,
						null,
						null, getTributacaoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());

		insertCount +=
				EstoqueDACD.maintainEstoqueAssociations(produto.getEstoqueList(), response, insertCount, null,
						null,
						null, getEstoqueDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());

		insertCount +=
				PrecoDACD.maintainTabPrecoAssociations(produto.getPrecoList(), response, insertCount, null,
						null,
						null, getTabPrecoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());
		insertCount +=
				CustoDACD.maintainCustoAssociations(produto.getCustoList(), response, insertCount, null,
						null,
						null, getCustoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());
		insertCount +=
				PorcaoDACD.maintainPorcaoAssociations(produto.getPorcaoList(), response, insertCount, null,
						null,
						null, getPorcaoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());
		insertCount +=
				RentabilidadeDACD.maintainRentabilidadeAssociations(produto.getRentabilidadeList(), response,
						insertCount, null,
						null,
						null, getRentabilidadeDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());

		insertCount +=
				CfopDACD.maintainCfopAssociations(produto.getCfopList(), response,
						insertCount, null,
						null,
						null, getCfopDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());

		insertCount +=
				FornecedorDACD.maintainFornecedorAssociations(produto.getFornecedorList(), response,
						insertCount, null,
						null,
						null, getFornecedorDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new ArrayList<Status>();
			insertCount =
					StatusDACD.maintainStatusAssociations(statusList, response, produto.getId(), null, AcaoEnum.INSERT,
							produto.getCreateUser(), produto.getEmprId(), TabelaEnum.EMPRESA, getStatusDAC(),
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

		updateCount +=
				ClassificacaoDACD.maintainClassificacaoAssociations(produto.getClassificacao(), response,
						produto.getProduto(),
						null,
						null,
						null, getClassificacaoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());

		updateCount +=
				UniMedDACD.maintainUniMedAssociations(produto.getUniMed(), response, produto.getProduto(), null,
						null,
						null, getUniMedDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());

		updateCount +=
				GrupoDACD.maintainGrupoAssociations(produto.getGrupo(), response, produto.getProduto(), null,
						null,
						null, getGrupoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());

		updateCount +=
				SubGrupoDACD.maintainSubGrupoAssociations(produto.getSubGrupo(), response, produto.getProduto(), null,
						null,
						null, getSubGrupoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());

		updateCount +=
				MarcaDACD.maintainMarcaAssociations(produto.getMarca(), response, produto.getProduto(), null,
						null,
						null, getMarcaDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());

		updateCount +=
				TributacaoDACD.maintainTributacaoAssociations(produto.getTributacao(), response, produto.getProduto(),
						null,
						null,
						null, getTributacaoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());

		updateCount +=
				EstoqueDACD.maintainEstoqueAssociations(produto.getEstoqueList(), response, produto.getProduto(), null,
						null,
						null, getEstoqueDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());

		updateCount +=
				PrecoDACD.maintainTabPrecoAssociations(produto.getPrecoList(), response, produto.getProduto(), null,
						null,
						null, getTabPrecoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());
		updateCount +=
				CustoDACD.maintainCustoAssociations(produto.getCustoList(), response, produto.getProduto(), null,
						null,
						null, getCustoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());
		updateCount +=
				PorcaoDACD.maintainPorcaoAssociations(produto.getPorcaoList(), response, produto.getProduto(), null,
						null,
						null, getPorcaoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());
		updateCount +=
				RentabilidadeDACD.maintainRentabilidadeAssociations(produto.getRentabilidadeList(), response,
						produto.getProduto(), null,
						null,
						null, getRentabilidadeDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());

		updateCount +=
				CfopDACD.maintainCfopAssociations(produto.getCfopList(), response,
						produto.getProduto(), null,
						null,
						null, getCfopDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());

		updateCount +=
				FornecedorDACD.maintainFornecedorAssociations(produto.getFornecedorList(), response,
						produto.getProduto(), null,
						null,
						null, getFornecedorDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser());

		if (updateCount > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new ArrayList<Status>();
			insertCount =
					StatusDACD.maintainStatusAssociations(statusList, response, produto.getId(), null, AcaoEnum.INSERT,
							produto.getCreateUser(), produto.getEmprId(), TabelaEnum.EMPRESA, getStatusDAC(),
							getHistoricoDAC());

		}

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
