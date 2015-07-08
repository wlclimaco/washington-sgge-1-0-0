package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.dac.INoteDAC;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.IBancoDAC;
import com.prosperitasglobal.sendsolv.dac.ICfopDAC;
import com.prosperitasglobal.sendsolv.dac.ICnaeDAC;
import com.prosperitasglobal.sendsolv.dac.IContatoDAC;
import com.prosperitasglobal.sendsolv.dac.IConvenioDAC;
import com.prosperitasglobal.sendsolv.dac.IDocumentoDAC;
import com.prosperitasglobal.sendsolv.dac.IEmailDAC;
import com.prosperitasglobal.sendsolv.dac.IEnderecoDAC;
import com.prosperitasglobal.sendsolv.dac.IFormaPagamentoDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.IPessoaDAC;
import com.prosperitasglobal.sendsolv.dac.IProfissaoDAC;
import com.prosperitasglobal.sendsolv.dac.ISociosDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.dac.ITelefoneDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.BancoDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.CfopDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.ContatoDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.ConvenioDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.DocumentosDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.EmailDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.EnderecoDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.FormaPagamentoDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.NotesDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.ProfissaoDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.StatusDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.TelefoneDACD;
import com.prosperitasglobal.sendsolv.model.AcaoEnum;
import com.prosperitasglobal.sendsolv.model.Cliente;
import com.prosperitasglobal.sendsolv.model.Fornecedor;
import com.prosperitasglobal.sendsolv.model.Process;
import com.prosperitasglobal.sendsolv.model.Status;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.TabelaEnum;
import com.prosperitasglobal.sendsolv.model.Transportador;
import com.prosperitasglobal.sendsolv.model.request.ClienteInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FornecedorInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TransportadorInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class PessoaDACImpl.
 */
public class PessoaDACImpl extends SqlSessionDaoSupport implements IPessoaDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "PessoaMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchPessoaRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllPessoasByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchPessoaById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertPessoa";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_CONTACT = EMPRESA_NAMESPACE
			+ "associatePessoaWithContact";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updatePessoa";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deletePessoaById";

	/** The Constant EMPRESA_DOCUMENT_STMT_UPDATE. */
	private static final String EMPRESA_DOCUMENT_STMT_UPDATE = EMPRESA_NAMESPACE
			+ "updatePessoaDocument";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT = EMPRESA_NAMESPACE
			+ "associatePessoaWithDocument";

	/** The Constant EMPRESA_STMT_DELETE_DOCUMENT. */
	private static final String EMPRESA_STMT_DELETE_DOCUMENT = EMPRESA_NAMESPACE
			+ "deletePessoaDocument";

	/** The Constant STMT_VERSION. */
	private static final String EMPRESA_STMT_VERSION = EMPRESA_NAMESPACE + "fetchVersionNumber";

	/** The Constant EMPRESA_STMT_UPDATE_EMPRESA_STATUS. */
	private static final String EMPRESA_STMT_UPDATE_EMPRESA_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PessoaDACImpl.class);

	/** The valid sort fields for an pessoa inquiry. Will be injected by Spring. */
	private Map<String, String> pessoaInquiryValidSortFields;

	IEnderecoDAC enderecoDAC;
	ITelefoneDAC telefoneDAC;
	IEmailDAC emailDAC;
	ISociosDAC socioDAC;
	ICnaeDAC cnaeDAC;
	IDocumentoDAC documentoDAC;
	IHistoricoDAC historicoDAC;
	IStatusDAC statusDAC;

	IProfissaoDAC profisaoDAC;
	IConvenioDAC convenioDAC;
	IBancoDAC bancosDAC;
	IContatoDAC contatoDAC;
	INoteDAC noteDAC;
	ICfopDAC cfopDAC;
	IFormaPagamentoDAC formaPagamentoDAC;

	public ICfopDAC getCfopDAC()
	{
		return cfopDAC;
	}

	public void setCfopDAC(ICfopDAC cfopDAC)
	{
		this.cfopDAC = cfopDAC;
	}

	public IFormaPagamentoDAC getFormaPagamentoDAC()
	{
		return formaPagamentoDAC;
	}

	public void setFormaPagamentoDAC(IFormaPagamentoDAC formaPagamentoDAC)
	{
		this.formaPagamentoDAC = formaPagamentoDAC;
	}

	/**
	 * @return the enderecoDAC
	 */
	public IEnderecoDAC getEnderecoDAC()
	{
		return enderecoDAC;
	}

	/**
	 * @param enderecoDAC the enderecoDAC to set
	 */
	public void setEnderecoDAC(IEnderecoDAC enderecoDAC)
	{
		this.enderecoDAC = enderecoDAC;
	}

	/**
	 * @return the telefoneDAC
	 */
	public ITelefoneDAC getTelefoneDAC()
	{
		return telefoneDAC;
	}

	/**
	 * @param telefoneDAC the telefoneDAC to set
	 */
	public void setTelefoneDAC(ITelefoneDAC telefoneDAC)
	{
		this.telefoneDAC = telefoneDAC;
	}

	/**
	 * @return the emailDAC
	 */
	public IEmailDAC getEmailDAC()
	{
		return emailDAC;
	}

	/**
	 * @param emailDAC the emailDAC to set
	 */
	public void setEmailDAC(IEmailDAC emailDAC)
	{
		this.emailDAC = emailDAC;
	}

	/**
	 * @return the socioDAC
	 */
	public ISociosDAC getSocioDAC()
	{
		return socioDAC;
	}

	/**
	 * @param socioDAC the socioDAC to set
	 */
	public void setSocioDAC(ISociosDAC socioDAC)
	{
		this.socioDAC = socioDAC;
	}

	/**
	 * @return the cnaeDAC
	 */
	public ICnaeDAC getCnaeDAC()
	{
		return cnaeDAC;
	}

	/**
	 * @param cnaeDAC the cnaeDAC to set
	 */
	public void setCnaeDAC(ICnaeDAC cnaeDAC)
	{
		this.cnaeDAC = cnaeDAC;
	}

	/**
	 * @return the documentoDAC
	 */
	public IDocumentoDAC getDocumentoDAC()
	{
		return documentoDAC;
	}

	/**
	 * @param documentoDAC the documentoDAC to set
	 */
	public void setDocumentoDAC(IDocumentoDAC documentoDAC)
	{
		this.documentoDAC = documentoDAC;
	}

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
	 * Get the valid sort fields for the pessoa inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the pessoa inquiry.
	 */
	public Map<String, String> getPessoaInquiryValidSortFields()
	{
		return pessoaInquiryValidSortFields;
	}

	public IProfissaoDAC getProfisaoDAC()
	{
		return profisaoDAC;
	}

	public void setProfisaoDAC(IProfissaoDAC profisaoDAC)
	{
		this.profisaoDAC = profisaoDAC;
	}

	public IConvenioDAC getConvenioDAC()
	{
		return convenioDAC;
	}

	public void setConvenioDAC(IConvenioDAC convenioDAC)
	{
		this.convenioDAC = convenioDAC;
	}

	public IBancoDAC getBancosDAC()
	{
		return bancosDAC;
	}

	public void setBancosDAC(IBancoDAC bancosDAC)
	{
		this.bancosDAC = bancosDAC;
	}

	public IContatoDAC getContatoDAC()
	{
		return contatoDAC;
	}

	public void setContatoDAC(IContatoDAC contatoDAC)
	{
		this.contatoDAC = contatoDAC;
	}

	public INoteDAC getNoteDAC()
	{
		return noteDAC;
	}

	public void setNoteDAC(INoteDAC noteDAC)
	{
		this.noteDAC = noteDAC;
	}

	/**
	 * Set the valid sort fields for the pessoa inquiry. Attribute injected by Spring.
	 *
	 * @param pessoaInquiryValidSortFields The valid sort fields for the pessoa inquiry to set.
	 */
	public void setPessoaInquiryValidSortFields(Map<String, String> pessoaInquiryValidSortFields)
	{
		this.pessoaInquiryValidSortFields = pessoaInquiryValidSortFields;
	}

	@Override
	public InternalResultsResponse<Cliente> insertCliente(Cliente pessoa)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Cliente> response = new InternalResultsResponse<Cliente>();

		Process process = new Process();
		process.setEmprId(pessoa.getEmprId());
		process.setTabelaEnum(TabelaEnum.EMPRESA);
		process.setUserId(pessoa.getUserId());
		process.setAcaoType(AcaoEnum.INSERT);

		Integer processId = 0;

		processId = (Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), "ProcessMap.insertProcess", process);

		pessoa.setProcessId(processId);

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "ClienteMap.insertCliente", pessoa, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount +=
				EnderecoDACD.maintainEnderecoAssociations(pessoa.getEnderecos(), response, insertCount, null, null,
						null, getEnderecoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				EmailDACD.maintainEmailAssociations(pessoa.getEmails(), response, insertCount, null, null,
						null, getEmailDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				TelefoneDACD.maintainTelefoneAssociations(pessoa.getTelefones(), response, insertCount, null, null,
						null, getTelefoneDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				DocumentosDACD.maintainDocumentoAssociations(pessoa.getDocumentos(), response, insertCount, null,
						null,
						null, getDocumentoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				ProfissaoDACD.maintainProfissaoAssociations(pessoa.getProfissao(), response, insertCount, null,
						null,
						null, getProfisaoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				ConvenioDACD.maintainConvenioAssociations(pessoa.getConvenioList(), response, insertCount, null,
						null,
						null, getConvenioDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				BancoDACD.maintainBancoAssociations(pessoa.getBancos(), response, insertCount, null,
						null,
						null, getBancosDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				ContatoDACD.maintainContatoAssociations(pessoa.getContatoList(), response, insertCount, null,
						null,
						null, getContatoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				NotesDACD.maintainNoteAssociations(pessoa.getNotes(), response, insertCount, null,
						null,
						null, getNoteDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new ArrayList<Status>();
			insertCount =
					StatusDACD.maintainStatusAssociations(statusList, response, pessoa.getId(), null, AcaoEnum.INSERT,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.EMPRESA, getStatusDAC(),
							getHistoricoDAC(), processId);

		}

		// Finally, if something was inserted then add the Cliente to the result.
		if (insertCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Cliente> updateCliente(Cliente pessoa)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Cliente> response = new InternalResultsResponse<Cliente>();

		Process process = new Process();
		process.setEmprId(pessoa.getEmprId());
		process.setTabelaEnum(TabelaEnum.CLIENTE);
		process.setUserId(pessoa.getUserId());
		process.setAcaoType(AcaoEnum.UPDATE);

		Integer processId = 0;

		processId = (Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), "ProcessMap.insertProcess", process);

		pessoa.setProcessId(processId);

		// First update the root if necessary.
		if (!ValidationUtil.isNull(pessoa.getModelAction())
				&& (pessoa.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, pessoa,
							response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount +=
				EnderecoDACD.maintainEnderecoAssociations(pessoa.getEnderecos(), response, pessoa.getId(), null, null,
						null, getEnderecoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				EmailDACD.maintainEmailAssociations(pessoa.getEmails(), response, pessoa.getId(), null, null,
						null, getEmailDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				TelefoneDACD.maintainTelefoneAssociations(pessoa.getTelefones(), response, pessoa.getId(), null, null,
						null, getTelefoneDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				DocumentosDACD.maintainDocumentoAssociations(pessoa.getDocumentos(), response, pessoa.getId(), null,
						null,
						null, getDocumentoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);
		updateCount +=
				ProfissaoDACD.maintainProfissaoAssociations(pessoa.getProfissao(), response, pessoa.getId(), null,
						null,
						null, getProfisaoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				ConvenioDACD.maintainConvenioAssociations(pessoa.getConvenioList(), response, pessoa.getId(), null,
						null,
						null, getConvenioDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				BancoDACD.maintainBancoAssociations(pessoa.getBancos(), response, pessoa.getId(), null,
						null,
						null, getBancosDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				ContatoDACD.maintainContatoAssociations(pessoa.getContatoList(), response, pessoa.getId(), null,
						null,
						null, getContatoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				NotesDACD.maintainNoteAssociations(pessoa.getNotes(), response, pessoa.getId(), null,
						null,
						null, getNoteDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		if (updateCount > 0)
		{
			updateCount =
					StatusDACD.maintainStatusAssociations(pessoa.getStatusList(), response, pessoa.getId(), null,
							AcaoEnum.UPDATE,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.CLIENTE, getStatusDAC(),
							getHistoricoDAC(), processId);

		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;
	}

	@Override
	public InternalResponse deleteCliente(Cliente pessoa)
	{
		Integer updateCount = 0;
		InternalResponse response = new InternalResponse();

		Process process = new Process();
		process.setEmprId(pessoa.getEmprId());
		process.setTabelaEnum(TabelaEnum.CLIENTE);
		process.setUserId(pessoa.getUserId());
		process.setAcaoType(AcaoEnum.UPDATE);

		Integer processId = 0;

		processId = (Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), "ProcessMap.insertProcess", process);

		pessoa.setProcessId(processId);

		Status status = new Status();
		status.setStatus(StatusEnum.INACTIVE);
		List<Status> statusList = new ArrayList<Status>();
		updateCount =
				StatusDACD
						.maintainStatusAssociations(statusList, (InternalResultsResponse<?>)response, pessoa.getId(),
								null, AcaoEnum.DELETE,
								pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.CLIENTE, getStatusDAC(),
								getHistoricoDAC(), processId);
		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			((InternalResultsResponse<Cliente>)response).addResult(pessoa);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IClienteDAC#fetchClienteById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Cliente> fetchClienteById(FetchByIdRequest request)
	{
		InternalResultsResponse<Cliente> response = new InternalResultsResponse<Cliente>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Cliente> fetchClienteByRequest(ClienteInquiryRequest request)
	{
		InternalResultsResponse<Cliente> response = new InternalResultsResponse<Cliente>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getClienteInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "ClienteMap.fetchClienteRowCount",
				"ClienteMap.fetchAllClientesByRequest", response);
		return response;
	}

	// ========================Fornecedor=======================
	@Override
	public InternalResultsResponse<Fornecedor> insertFornecedor(Fornecedor pessoa)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Fornecedor> response = new InternalResultsResponse<Fornecedor>();

		Process process = new Process();
		process.setEmprId(pessoa.getEmprId());
		process.setTabelaEnum(TabelaEnum.FORNECEDOR);
		process.setUserId(pessoa.getUserId());
		process.setAcaoType(AcaoEnum.UPDATE);

		Integer processId = 0;

		processId = (Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), "ProcessMap.insertProcess", process);

		pessoa.setProcessId(processId);

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, pessoa, response);

		if (response.isInError())
		{
			return response;
		}
		insertCount +=
				EnderecoDACD.maintainEnderecoAssociations(pessoa.getEnderecos(), response, insertCount, null, null,
						null, getEnderecoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				EmailDACD.maintainEmailAssociations(pessoa.getEmails(), response, insertCount, null, null,
						null, getEmailDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				TelefoneDACD.maintainTelefoneAssociations(pessoa.getTelefones(), response, insertCount, null, null,
						null, getTelefoneDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				DocumentosDACD.maintainDocumentoAssociations(pessoa.getDocumentos(), response, insertCount, null,
						null,
						null, getDocumentoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				BancoDACD.maintainBancoAssociations(pessoa.getBancos(), response, insertCount, null,
						null,
						null, getBancosDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				ContatoDACD.maintainContatoAssociations(pessoa.getContatoList(), response, insertCount, null,
						null,
						null, getContatoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				NotesDACD.maintainNoteAssociations(pessoa.getNotes(), response, insertCount, null,
						null,
						null, getNoteDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				FormaPagamentoDACD.maintainFormaPgAssociations(pessoa.getFormaPagamentoList(), response, insertCount,
						null,
						null,
						null, getFormaPagamentoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				CfopDACD.maintainCfopAssociations(pessoa.getListCfops(), response, pessoa.getId(), null,
						null,
						null, getCfopDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new ArrayList<Status>();
			insertCount =
					StatusDACD.maintainStatusAssociations(statusList, response, pessoa.getId(), null, AcaoEnum.INSERT,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.FORNECEDOR, getStatusDAC(),
							getHistoricoDAC(), processId);

		}
		// Finally, if something was inserted then add the Fornecedor to the result.
		if (insertCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Fornecedor> updateFornecedor(Fornecedor pessoa)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Fornecedor> response = new InternalResultsResponse<Fornecedor>();

		Process process = new Process();
		process.setEmprId(pessoa.getEmprId());
		process.setTabelaEnum(TabelaEnum.FORNECEDOR);
		process.setUserId(pessoa.getUserId());
		process.setAcaoType(AcaoEnum.UPDATE);

		Integer processId = 0;

		processId = (Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), "ProcessMap.insertProcess", process);

		pessoa.setProcessId(processId);

		// First update the root if necessary.
		if (!ValidationUtil.isNull(pessoa.getModelAction())
				&& (pessoa.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, pessoa,
							response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations

		updateCount +=
				EnderecoDACD.maintainEnderecoAssociations(pessoa.getEnderecos(), response, pessoa.getId(), null, null,
						null, getEnderecoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				EmailDACD.maintainEmailAssociations(pessoa.getEmails(), response, pessoa.getId(), null, null,
						null, getEmailDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				TelefoneDACD.maintainTelefoneAssociations(pessoa.getTelefones(), response, pessoa.getId(), null, null,
						null, getTelefoneDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				DocumentosDACD.maintainDocumentoAssociations(pessoa.getDocumentos(), response, pessoa.getId(), null,
						null,
						null, getDocumentoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				BancoDACD.maintainBancoAssociations(pessoa.getBancos(), response, pessoa.getId(), null,
						null,
						null, getBancosDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				ContatoDACD.maintainContatoAssociations(pessoa.getContatoList(), response, pessoa.getId(), null,
						null,
						null, getContatoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				NotesDACD.maintainNoteAssociations(pessoa.getNotes(), response, pessoa.getId(), null,
						null,
						null, getNoteDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				FormaPagamentoDACD.maintainFormaPgAssociations(pessoa.getFormaPagamentoList(), response,
						pessoa.getId(),
						null,
						null,
						null, getFormaPagamentoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				CfopDACD.maintainCfopAssociations(pessoa.getListCfops(), response, pessoa.getId(), null,
						null,
						null, getCfopDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		if (updateCount > 0)
		{

			updateCount =
					StatusDACD.maintainStatusAssociations(pessoa.getStatusList(), response, pessoa.getId(), null,
							AcaoEnum.INSERT,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.FORNECEDOR, getStatusDAC(),
							getHistoricoDAC(), processId);

		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;
	}

	@Override
	public InternalResponse deleteFornecedor(Fornecedor pessoa)
	{
		Integer updateCount = 0;
		InternalResponse response = new InternalResponse();

		Process process = new Process();
		process.setEmprId(pessoa.getEmprId());
		process.setTabelaEnum(TabelaEnum.FORNECEDOR);
		process.setUserId(pessoa.getUserId());
		process.setAcaoType(AcaoEnum.DELETE);

		Integer processId = 0;

		processId = (Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), "ProcessMap.insertProcess", process);

		pessoa.setProcessId(processId);

		Status status = new Status();
		status.setStatus(StatusEnum.INACTIVE);
		List<Status> statusList = new ArrayList<Status>();
		updateCount =
				StatusDACD
						.maintainStatusAssociations(statusList, (InternalResultsResponse<?>)response, pessoa.getId(),
								null, AcaoEnum.DELETE,
								pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.FORNECEDOR, getStatusDAC(),
								getHistoricoDAC(), processId);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.isInError();
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IFornecedorDAC#fetchFornecedorById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Fornecedor> fetchFornecedorById(FetchByIdRequest request)
	{
		InternalResultsResponse<Fornecedor> response = new InternalResultsResponse<Fornecedor>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Fornecedor> fetchFornecedorByRequest(FornecedorInquiryRequest request)
	{
		InternalResultsResponse<Fornecedor> response = new InternalResultsResponse<Fornecedor>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getFornecedorInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "FornecedorMap.fetchFornecedorRowCount",
				"FornecedorMap.fetchAllFornecedorByRequest", response);
		return response;
	}

	// ==============================Transportador

	@Override
	public InternalResultsResponse<Transportador> insertTransportador(Transportador pessoa)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Transportador> response = new InternalResultsResponse<Transportador>();

		Process process = new Process();
		process.setEmprId(pessoa.getEmprId());
		process.setTabelaEnum(TabelaEnum.TRANSPORTADOR);
		process.setUserId(pessoa.getUserId());
		process.setAcaoType(AcaoEnum.UPDATE);

		Integer processId = 0;

		processId = (Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), "ProcessMap.insertProcess", process);

		pessoa.setProcessId(processId);

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, pessoa, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount +=
				EnderecoDACD.maintainEnderecoAssociations(pessoa.getEnderecos(), response, insertCount, null, null,
						null, getEnderecoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				EmailDACD.maintainEmailAssociations(pessoa.getEmails(), response, insertCount, null, null,
						null, getEmailDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				TelefoneDACD.maintainTelefoneAssociations(pessoa.getTelefones(), response, insertCount, null, null,
						null, getTelefoneDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				DocumentosDACD.maintainDocumentoAssociations(pessoa.getDocumentos(), response, insertCount, null,
						null,
						null, getDocumentoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				BancoDACD.maintainBancoAssociations(pessoa.getBancos(), response, insertCount, null,
						null,
						null, getBancosDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				ContatoDACD.maintainContatoAssociations(pessoa.getContatoList(), response, insertCount, null,
						null,
						null, getContatoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				NotesDACD.maintainNoteAssociations(pessoa.getNotes(), response, insertCount, null,
						null,
						null, getNoteDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		insertCount +=
				FormaPagamentoDACD.maintainFormaPgAssociations(pessoa.getFormaPagamentoList(), response, insertCount,
						null,
						null,
						null, getFormaPagamentoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new ArrayList<Status>();
			insertCount =
					StatusDACD.maintainStatusAssociations(statusList, response, pessoa.getId(), null, AcaoEnum.INSERT,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.TRANSPORTADOR, getStatusDAC(),
							getHistoricoDAC(), processId);

		}

		// Finally, if something was inserted then add the Transportador to the result.
		if (insertCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Transportador> updateTransportador(Transportador pessoa)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Transportador> response = new InternalResultsResponse<Transportador>();

		Process process = new Process();
		process.setEmprId(pessoa.getEmprId());
		process.setTabelaEnum(TabelaEnum.CLIENTE);
		process.setUserId(pessoa.getUserId());
		process.setAcaoType(AcaoEnum.UPDATE);

		Integer processId = 0;

		processId = (Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), "ProcessMap.insertProcess", process);

		pessoa.setProcessId(processId);

		// First update the root if necessary.
		if (!ValidationUtil.isNull(pessoa.getModelAction())
				&& (pessoa.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, pessoa,
							response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount +=
				EnderecoDACD.maintainEnderecoAssociations(pessoa.getEnderecos(), response, pessoa.getId(), null, null,
						null, getEnderecoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				EmailDACD.maintainEmailAssociations(pessoa.getEmails(), response, pessoa.getId(), null, null,
						null, getEmailDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				TelefoneDACD.maintainTelefoneAssociations(pessoa.getTelefones(), response, pessoa.getId(), null, null,
						null, getTelefoneDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				DocumentosDACD.maintainDocumentoAssociations(pessoa.getDocumentos(), response, pessoa.getId(), null,
						null,
						null, getDocumentoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				BancoDACD.maintainBancoAssociations(pessoa.getBancos(), response, pessoa.getId(), null,
						null,
						null, getBancosDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				ContatoDACD.maintainContatoAssociations(pessoa.getContatoList(), response, pessoa.getId(), null,
						null,
						null, getContatoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				NotesDACD.maintainNoteAssociations(pessoa.getNotes(), response, pessoa.getId(), null,
						null,
						null, getNoteDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		updateCount +=
				FormaPagamentoDACD.maintainFormaPgAssociations(pessoa.getFormaPagamentoList(), response,
						pessoa.getId(),
						null,
						null,
						null, getFormaPagamentoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId);

		if (updateCount > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new ArrayList<Status>();
			updateCount =
					StatusDACD.maintainStatusAssociations(statusList, response, pessoa.getId(), null, AcaoEnum.INSERT,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.EMPRESA, getStatusDAC(),
							getHistoricoDAC(), processId);

		}
		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;
	}

	@Override
	public InternalResponse deleteTransportador(Transportador pessoa)
	{
		Integer updateCount = 0;
		InternalResponse response = new InternalResponse();

		Process process = new Process();
		process.setEmprId(pessoa.getEmprId());
		process.setTabelaEnum(TabelaEnum.CLIENTE);
		process.setUserId(pessoa.getUserId());
		process.setAcaoType(AcaoEnum.UPDATE);

		Integer processId = 0;

		processId = (Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), "ProcessMap.insertProcess", process);

		pessoa.setProcessId(processId);

		Status status = new Status();
		status.setStatus(StatusEnum.INACTIVE);
		List<Status> statusList = new ArrayList<Status>();
		updateCount =
				StatusDACD
						.maintainStatusAssociations(statusList, (InternalResultsResponse<?>)response, pessoa.getId(),
								null, AcaoEnum.DELETE,
								pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.TRANSPORTADOR, getStatusDAC(),
								getHistoricoDAC(), processId);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.ITransportadorDAC#fetchTransportadorById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Transportador> fetchTransportadorById(FetchByIdRequest request)
	{
		InternalResultsResponse<Transportador> response = new InternalResultsResponse<Transportador>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Transportador> fetchTransportadorByRequest(TransportadorInquiryRequest request)
	{
		InternalResultsResponse<Transportador> response = new InternalResultsResponse<Transportador>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getTransportadorInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "TransportadorMap.fetchTransportadorRowCount",
				"TransportadorMap.fetchTransportadorRowCount", response);

		return response;
	}

}
