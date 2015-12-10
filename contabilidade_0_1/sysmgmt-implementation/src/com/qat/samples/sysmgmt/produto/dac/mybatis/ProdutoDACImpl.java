package com.qat.samples.sysmgmt.produto.dac.mybatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.cfop.Cfop;
import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.contabilidade.Plano;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.entidade.dacd.ClassificacaoDACD;
import com.qat.samples.sysmgmt.entidade.dacd.StatusDACD;
import com.qat.samples.sysmgmt.entidade.dacd.TributacaoDACD;
import com.qat.samples.sysmgmt.fiscal.Classificacao;
import com.qat.samples.sysmgmt.fiscal.Tributacao;
import com.qat.samples.sysmgmt.fiscal.model.request.ClassificacaoInquiryRequest;
import com.qat.samples.sysmgmt.historico.model.Historico;
import com.qat.samples.sysmgmt.historico.model.HistoricoItens;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.produto.dac.ICfopDAC;
import com.qat.samples.sysmgmt.produto.dac.ICustoDAC;
import com.qat.samples.sysmgmt.produto.dac.IEstoqueDAC;
import com.qat.samples.sysmgmt.produto.dac.IGrupoDAC;
import com.qat.samples.sysmgmt.produto.dac.IMarcaDAC;
import com.qat.samples.sysmgmt.produto.dac.IPorcaoDAC;
import com.qat.samples.sysmgmt.produto.dac.IProdutoDAC;
import com.qat.samples.sysmgmt.produto.dac.IRentabilidadeDAC;
import com.qat.samples.sysmgmt.produto.dac.ISubGrupoDAC;
import com.qat.samples.sysmgmt.produto.dac.ITabPrecoDAC;
import com.qat.samples.sysmgmt.produto.dac.IUniMedDAC;
import com.qat.samples.sysmgmt.produto.dacd.mybatis.CfopDACD;
import com.qat.samples.sysmgmt.produto.dacd.mybatis.CustoDACD;
import com.qat.samples.sysmgmt.produto.dacd.mybatis.EstoqueDACD;
import com.qat.samples.sysmgmt.produto.dacd.mybatis.GrupoDACD;
import com.qat.samples.sysmgmt.produto.dacd.mybatis.MarcaDACD;
import com.qat.samples.sysmgmt.produto.dacd.mybatis.PorcaoDACD;
import com.qat.samples.sysmgmt.produto.dacd.mybatis.PrecoDACD;
import com.qat.samples.sysmgmt.produto.dacd.mybatis.RentabilidadeDACD;
import com.qat.samples.sysmgmt.produto.dacd.mybatis.SubGrupoDACD;
import com.qat.samples.sysmgmt.produto.dacd.mybatis.UniMedDACD;
import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.Servico;
import com.qat.samples.sysmgmt.produto.model.SubGrupo;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.produto.model.request.GrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ServicoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.TributacaoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedInquiryRequest;
import com.qat.samples.sysmgmt.util.AcaoEnum;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.Status;
import com.qat.samples.sysmgmt.util.TabelaEnum;
import com.qat.samples.sysmgmt.util.dac.IClassificacaoDAC;
import com.qat.samples.sysmgmt.util.dac.IHistoricoDAC;
import com.qat.samples.sysmgmt.util.dac.IStatusDAC;
import com.qat.samples.sysmgmt.util.dac.ITributacaoDAC;

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

	// private IPessoaDAC fornecedorDAC;

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
		historicoDAC = historicoDAC;
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
		statusDAC = statusDAC;
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
		produtoDAC = produtoDAC;
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
		classificacaoDAC = classificacaoDAC;
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
		uniMedDAC = uniMedDAC;
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
		grupoDAC = grupoDAC;
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
		subGrupoDAC = subGrupoDAC;
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
		marcaDAC = marcaDAC;
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
		tributacaoDAC = tributacaoDAC;
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
		estoqueDAC = estoqueDAC;
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
		tabPrecoDAC = tabPrecoDAC;
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
		custoDAC = custoDAC;
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
		porcaoDAC = porcaoDAC;
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
		rentabilidadeDAC = rentabilidadeDAC;
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
		cfopDAC = cfopDAC;
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
		produtoInquiryValidSortFields = produtoInquiryValidSortFields;
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
		Historico historico = new Historico();
		historico.setEmprId(produto.getEmprId());
		historico.setUserId(produto.getUserId());
		historico.setProcessId(0);
		Date a = new Date();
		historico.setData(a.getTime());

		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistorico", historico, response);

		Integer historicoId = historico.getId();

		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(historicoId);
		historicoItens.setProcessId(0);
		historicoItens.setParentId(produto.getId());
		historicoItens.setTabelaEnum(TabelaEnum.EMPRESA);
		historicoItens.setAcaoType(AcaoEnum.INSERT);

		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistoricoItens", historicoItens,
						response);

		Integer processId = historicoId;

		produto.setProcessId(processId);

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
						produto.getCreateUser(), processId);

		insertCount +=
				UniMedDACD.maintainUniMedAssociations(produto.getUniMed(), response, insertCount, null,
						null,
						null, getUniMedDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId);

		insertCount +=
				GrupoDACD.maintainGrupoAssociations(produto.getGrupo(), response, insertCount, null,
						null,
						null, getGrupoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId);

		insertCount +=
				SubGrupoDACD.maintainSubGrupoAssociations(produto.getSubGrupo(), response, insertCount, null,
						null,
						null, getSubGrupoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId);

		insertCount +=
				MarcaDACD.maintainMarcaAssociations(produto.getMarca(), response, insertCount, null,
						null,
						null, getMarcaDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId);

		insertCount +=
				TributacaoDACD.maintainTributacaoAssociations(produto.getTributacao(), response, insertCount, null,
						null,
						null, getTributacaoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId);

		insertCount +=
				EstoqueDACD.maintainEstoqueAssociations(produto.getEstoqueList(), response, insertCount, null,
						null,
						null, getEstoqueDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId);

		insertCount +=
				PrecoDACD.maintainTabPrecoAssociations(produto.getPrecoList(), response, insertCount, null,
						null,
						null, getTabPrecoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId);
		insertCount +=
				CustoDACD.maintainCustoAssociations(produto.getCustoList(), response, insertCount, null,
						null,
						null, getCustoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId);
		insertCount +=
				PorcaoDACD.maintainPorcaoAssociations(produto.getPorcaoList(), response, insertCount, null,
						null,
						null, getPorcaoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId);
		insertCount +=
				RentabilidadeDACD.maintainRentabilidadeAssociations(produto.getRentabilidadeList(), response,
						insertCount, null,
						null,
						null, getRentabilidadeDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId);

		insertCount +=
				CfopDACD.maintainCfopAssociations(produto.getCfopList(), response,
						insertCount, null,
						null,
						null, getCfopDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId, null);

		// insertCount +=
		// FornecedorDACD.maintainFornecedorAssociations(produto.getFornecedorList(), response,
		// insertCount, null,
		// null,
		// null, getFornecedorDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
		// produto.getCreateUser(), processId);

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ANALISANDO);
			List<Status> statusList = new ArrayList<Status>();
			insertCount =
					StatusDACD.maintainStatusAssociations(statusList, response, produto.getId(), null, AcaoEnum.INSERT,
							produto.getCreateUser(), produto.getEmprId(), TabelaEnum.EMPRESA, getStatusDAC(),
							getHistoricoDAC(), processId, null);

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

		// Process process = new Process();
		// process.setEmprId(produto.getEmprId());
		// process.setTabelaEnum(TabelaEnum.CLIENTE);
		// process.setUserId(produto.getUserId());
		// process.setAcaoType(AcaoEnum.UPDATE);

		Integer processId = 0;

		// processId = (Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), "ProcessMap.insertProcess",
		// process);

		produto.setProcessId(processId);

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
						produto.getId(),
						null,
						null,
						null, getClassificacaoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId);

		updateCount +=
				UniMedDACD.maintainUniMedAssociations(produto.getUniMed(), response, produto.getId(), null,
						null,
						null, getUniMedDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId);

		updateCount +=
				GrupoDACD.maintainGrupoAssociations(produto.getGrupo(), response, produto.getId(), null,
						null,
						null, getGrupoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId);

		updateCount +=
				SubGrupoDACD.maintainSubGrupoAssociations(produto.getSubGrupo(), response, produto.getId(), null,
						null,
						null, getSubGrupoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId);

		updateCount +=
				MarcaDACD.maintainMarcaAssociations(produto.getMarca(), response, produto.getId(), null,
						null,
						null, getMarcaDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId);

		updateCount +=
				TributacaoDACD.maintainTributacaoAssociations(produto.getTributacao(), response, produto.getId(),
						null,
						null,
						null, getTributacaoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId);

		updateCount +=
				EstoqueDACD.maintainEstoqueAssociations(produto.getEstoqueList(), response, produto.getId(), null,
						null,
						null, getEstoqueDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId);

		updateCount +=
				PrecoDACD.maintainTabPrecoAssociations(produto.getPrecoList(), response, produto.getId(), null,
						null,
						null, getTabPrecoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId);
		updateCount +=
				CustoDACD.maintainCustoAssociations(produto.getCustoList(), response, produto.getId(), null,
						null,
						null, getCustoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId);
		updateCount +=
				PorcaoDACD.maintainPorcaoAssociations(produto.getPorcaoList(), response, produto.getId(), null,
						null,
						null, getPorcaoDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId);
		updateCount +=
				RentabilidadeDACD.maintainRentabilidadeAssociations(produto.getRentabilidadeList(), response,
						produto.getId(), null,
						null,
						null, getRentabilidadeDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId);

		updateCount +=
				CfopDACD.maintainCfopAssociations(produto.getCfopList(), response,
						produto.getId(), null,
						null,
						null, getCfopDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
						produto.getCreateUser(), processId, null);

		// updateCount +=
		// FornecedorDACD.maintainFornecedorAssociations(produto.getFornecedorList(), response,
		// produto.getId(), null,
		// null,
		// null, getFornecedorDAC(), getStatusDAC(), getHistoricoDAC(), produto.getId(),
		// produto.getCreateUser(), processId);

		if (updateCount > 0)
		{
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ANALISANDO);
			List<Status> statusList = new ArrayList<Status>();
			updateCount =
					StatusDACD.maintainStatusAssociations(statusList, response, produto.getId(), null, AcaoEnum.INSERT,
							produto.getCreateUser(), produto.getEmprId(), TabelaEnum.EMPRESA, getStatusDAC(),
							getHistoricoDAC(), processId, null);

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
		InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, PRODUTO_STMT_FETCH_COUNT,
				PRODUTO_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	@Override
	public InternalResultsResponse<UniMed> fetchUniMedByRequest(UniMedInquiryRequest request)
	{
		InternalResultsResponse<UniMed> response = new InternalResultsResponse<UniMed>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "UniMedMap.fetchUniMedRowCount",
				"UniMedMap.fetchAllUniMedsByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Grupo> fetchGrupoByRequest(GrupoInquiryRequest request)
	{
		InternalResultsResponse<Grupo> response = new InternalResultsResponse<Grupo>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "GrupoMap.fetchGrupoRowCount",
				"GrupoMap.fetchAllGruposByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<SubGrupo> fetchSubGrupoByRequest(SubGrupoInquiryRequest request)
	{
		InternalResultsResponse<SubGrupo> response = new InternalResultsResponse<SubGrupo>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "SubGrupoMap.fetchSubGrupoRowCount",
				"SubGrupoMap.fetchAllSubGruposByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Marca> fetchMarcaByRequest(MarcaInquiryRequest request)
	{
		InternalResultsResponse<Marca> response = new InternalResultsResponse<Marca>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "MarcaMap.fetchMarcaRowCount",
				"MarcaMap.fetchAllMarcasByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Tributacao> fetchTributacaoByRequest(TributacaoInquiryRequest request)
	{
		InternalResultsResponse<Tributacao> response = new InternalResultsResponse<Tributacao>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "TributacaoMap.fetchTributacaoRowCount",
				"TributacaoMap.fetchAllTributacaosByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Cfop> fetchCfopByRequest(CfopInquiryRequest request)
	{
		InternalResultsResponse<Cfop> response = new InternalResultsResponse<Cfop>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "CfopMap.fetchCfopRowCount",
				"CfopMap.fetchAllCfopsByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Classificacao> fetchClassificacaoByRequest(ClassificacaoInquiryRequest request)
	{
		InternalResultsResponse<Classificacao> response = new InternalResultsResponse<Classificacao>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "ClassificacaoMap.fetchClassificacaoRowCount",
				"ClassificacaoMap.fetchAllClassificacaosByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Servico> insertServico(ServicoMaintenanceRequest request)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Servico> response = new InternalResultsResponse<Servico>();
		Historico historico = new Historico();
		historico.setEmprId(request.getServico().getEmprId());
		historico.setUserId(request.getServico().getUserId());
		historico.setProcessId(0);
		Date a = new Date();
		historico.setData(a.getTime());
		historico.setTabelaEnum(TabelaEnum.SERVICO);
		historico.setAcaoType(AcaoEnum.INSERT);

		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistorico", historico, response);

		Integer historicoId = historico.getId();

		request.getServico().setProcessId(historicoId);

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "ServicoMap.insertServico", request.getServico(),
						response);

		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(historicoId);
		historicoItens.setProcessId(0);
		historicoItens.setParentId(response.getFirstResult().getId());
		historicoItens.setTabelaEnum(TabelaEnum.SERVICO);
		historicoItens.setAcaoType(AcaoEnum.INSERT);

		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistoricoItens", historicoItens,
						response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations

		insertCount +=
				PrecoDACD.maintainTabPrecoAssociations(request.getServico().getPreco(), response, insertCount,
						null,
						null,
						null, getTabPrecoDAC(), getStatusDAC(), getHistoricoDAC(), response.getFirstResult().getId(),
						response.getFirstResult().getCreateUser(), historicoId);

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			insertCount =
					StatusDACD.maintainStatusAssociations(statusList, response, response.getFirstResult().getId(),
							null, AcaoEnum.INSERT,
							request.getServico().getCreateUser(), request.getServico().getEmprId(), TabelaEnum.SERVICO,
							getStatusDAC(),
							getHistoricoDAC(), historicoId, historicoId);

		}

		// Finally, if something was inserted then add the Produto to the result.
		if (insertCount > 0)
		{
			response.addResult(response.getFirstResult());
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Servico> updateServico(ServicoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deleteServico(ServicoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Servico> fetchServicoById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Servico> fetchServicoByRequest(ServicoInquiryRequest request)
	{
		InternalResultsResponse<Servico> response = new InternalResultsResponse<Servico>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "ServicoMap.fetchServicoRowCount",
				"ServicoMap.fetchAllServicosByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Plano> insertPlano(PlanoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Plano> updatePlano(PlanoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deletePlano(PlanoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Plano> fetchPlanoById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Plano> fetchPlanoByRequest(PlanoInquiryRequest request)
	{
		InternalResultsResponse<Plano> response = new InternalResultsResponse<Plano>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "PlanoMap.fetchPlanoRowCount",
				"PlanoMap.fetchAllPlanosByRequest", response);
		return response;
	}
}
