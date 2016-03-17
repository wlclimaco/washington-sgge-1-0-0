package com.qat.samples.sysmgmt.pessoa.dac.mybatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.agencia.Agencia;
import com.qat.samples.sysmgmt.agencia.model.request.AgenciaInquiryRequest;
import com.qat.samples.sysmgmt.banco.Banco;
import com.qat.samples.sysmgmt.banco.model.request.BancoInquiryRequest;
import com.qat.samples.sysmgmt.beneficios.Beneficios;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosInquiryRequest;
import com.qat.samples.sysmgmt.clinica.Consulta;
import com.qat.samples.sysmgmt.clinica.Especialidade;
import com.qat.samples.sysmgmt.clinica.Exame;
import com.qat.samples.sysmgmt.clinica.PlanoSaude;
import com.qat.samples.sysmgmt.clinica.model.request.ConsultaInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.EspecializacaoInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.ExameInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.MedicoInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.PacienteInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.PlanoSaudeInquiryRequest;
import com.qat.samples.sysmgmt.condpag.FormaPg;
import com.qat.samples.sysmgmt.condpag.model.request.FormaPgInquiryRequest;
import com.qat.samples.sysmgmt.conta.Conta;
import com.qat.samples.sysmgmt.contato.Contato;
import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.convenio.Convenio;
import com.qat.samples.sysmgmt.dac.IEnderecoDAC;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.dp.Eventos;
import com.qat.samples.sysmgmt.dp.HorarioFunc;
import com.qat.samples.sysmgmt.dp.Profissao;
import com.qat.samples.sysmgmt.dp.model.request.EventoInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.ProfissaoInquiryRequest;
import com.qat.samples.sysmgmt.entidade.dacd.DocumentosDACD;
import com.qat.samples.sysmgmt.entidade.dacd.EmailDACD;
import com.qat.samples.sysmgmt.entidade.dacd.EnderecoDACD;
import com.qat.samples.sysmgmt.entidade.dacd.NotesDACD;
import com.qat.samples.sysmgmt.entidade.dacd.StatusDACD;
import com.qat.samples.sysmgmt.entidade.dacd.TelefoneDACD;
import com.qat.samples.sysmgmt.estado.Estado;
import com.qat.samples.sysmgmt.estado.model.request.EstadoInquiryRequest;
import com.qat.samples.sysmgmt.historico.dacd.HistoricoDACD;
import com.qat.samples.sysmgmt.historico.model.Historico;
import com.qat.samples.sysmgmt.historico.model.HistoricoItens;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.pessoa.Cliente;
import com.qat.samples.sysmgmt.pessoa.Contador;
import com.qat.samples.sysmgmt.pessoa.Fornecedor;
import com.qat.samples.sysmgmt.pessoa.Funcionario;
import com.qat.samples.sysmgmt.pessoa.Medico;
import com.qat.samples.sysmgmt.pessoa.Paciente;
import com.qat.samples.sysmgmt.pessoa.Pessoa;
import com.qat.samples.sysmgmt.pessoa.Transportador;
import com.qat.samples.sysmgmt.pessoa.dac.IAgenciaDAC;
import com.qat.samples.sysmgmt.pessoa.dac.IBancoDAC;
import com.qat.samples.sysmgmt.pessoa.dac.IBeneficiosDAC;
import com.qat.samples.sysmgmt.pessoa.dac.ICondPagDAC;
import com.qat.samples.sysmgmt.pessoa.dac.IConsultaDAC;
import com.qat.samples.sysmgmt.pessoa.dac.IContatoDAC;
import com.qat.samples.sysmgmt.pessoa.dac.IConvenioDAC;
import com.qat.samples.sysmgmt.pessoa.dac.IEspecialidadeDAC;
import com.qat.samples.sysmgmt.pessoa.dac.IEventosDAC;
import com.qat.samples.sysmgmt.pessoa.dac.IExameDAC;
import com.qat.samples.sysmgmt.pessoa.dac.IFormaPagamentoDAC;
import com.qat.samples.sysmgmt.pessoa.dac.IHoraFuncDAC;
import com.qat.samples.sysmgmt.pessoa.dac.IPessoaDAC;
import com.qat.samples.sysmgmt.pessoa.dac.IPlanoSaudeDAC;
import com.qat.samples.sysmgmt.pessoa.dac.IProfissaoDAC;
import com.qat.samples.sysmgmt.pessoa.dac.ISalariosDAC;
import com.qat.samples.sysmgmt.pessoa.dac.IUsuarioDAC;
import com.qat.samples.sysmgmt.pessoa.dacd.BancoDACD;
import com.qat.samples.sysmgmt.pessoa.dacd.BeneficiosDACD;
import com.qat.samples.sysmgmt.pessoa.dacd.CondPagDACD;
import com.qat.samples.sysmgmt.pessoa.dacd.ConsultaDACD;
import com.qat.samples.sysmgmt.pessoa.dacd.ContatoDACD;
import com.qat.samples.sysmgmt.pessoa.dacd.ConvenioDACD;
import com.qat.samples.sysmgmt.pessoa.dacd.EspecialidadeDACD;
import com.qat.samples.sysmgmt.pessoa.dacd.EventosDACD;
import com.qat.samples.sysmgmt.pessoa.dacd.FormaPagamentoDACD;
import com.qat.samples.sysmgmt.pessoa.dacd.HorarioDACD;
import com.qat.samples.sysmgmt.pessoa.dacd.PlanoSaudeDACD;
import com.qat.samples.sysmgmt.pessoa.dacd.ProfissaoDACD;
import com.qat.samples.sysmgmt.pessoa.dacd.SalarioDACD;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ContaInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ContadorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ConvenioInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorInquiryRequest;
import com.qat.samples.sysmgmt.produto.dac.ICfopDAC;
import com.qat.samples.sysmgmt.produto.dacd.mybatis.CfopDACD;
import com.qat.samples.sysmgmt.util.AcaoEnum;
import com.qat.samples.sysmgmt.util.AcaoTypeEnum;
import com.qat.samples.sysmgmt.util.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.Cidade;
import com.qat.samples.sysmgmt.util.Status;
import com.qat.samples.sysmgmt.util.TabelaEnum;
import com.qat.samples.sysmgmt.util.TypeEnum;
import com.qat.samples.sysmgmt.util.dac.IDocumentoDAC;
import com.qat.samples.sysmgmt.util.dac.IEmailDAC;
import com.qat.samples.sysmgmt.util.dac.IHistoricoDAC;
import com.qat.samples.sysmgmt.util.dac.INoteDAC;
import com.qat.samples.sysmgmt.util.dac.IStatusDAC;
import com.qat.samples.sysmgmt.util.dac.ITelefoneDAC;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;

/**
 * The Class PessoaDACImpl.
 */
public class PessoaDACImpl extends SqlSessionDaoSupport implements IPessoaDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "PessoaMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchPessoaById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertPessoa";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updatePessoa";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PessoaDACImpl.class);

	/** The valid sort fields for an pessoa inquiry. Will be injected by Spring. */
	private Map<String, String> pessoaInquiryValidSortFields;

	IEnderecoDAC enderecoDAC;
	ITelefoneDAC telefoneDAC;
	IEmailDAC emailDAC;
	IDocumentoDAC documentoDAC;
	IHistoricoDAC historicoDAC;
	IStatusDAC statusDAC;
	IConvenioDAC convenioDAC;
	IBancoDAC bancoDAC;
	IContatoDAC contatoDAC;
	INoteDAC noteDAC;
	ICfopDAC cfopDAC;
	IFormaPagamentoDAC formaPgDAC;
	ISalariosDAC salarioDAC;
	IBeneficiosDAC beneficiosDAC;
	IEventosDAC eventosDAC;
	IHoraFuncDAC horaFuncDAC;
	IProfissaoDAC profissaoDAC;
	IUsuarioDAC usuarioDAC;
	IAgenciaDAC agenciaDAC;
	ICondPagDAC condPagDAC;
	IConsultaDAC consultaDAC;
	IEspecialidadeDAC especialidadeDAC;
	IExameDAC exameDAC;
	IPlanoSaudeDAC planoSaudeDAC;

	public IPlanoSaudeDAC getPlanoSaudeDAC()
	{
		return planoSaudeDAC;
	}

	public void setPlanoSaudeDAC(IPlanoSaudeDAC planoSaudeDAC)
	{
		this.planoSaudeDAC = planoSaudeDAC;
	}

	public IConsultaDAC getConsultaDAC()
	{
		return consultaDAC;
	}

	public void setConsultaDAC(IConsultaDAC consultaDAC)
	{
		this.consultaDAC = consultaDAC;
	}

	public IEspecialidadeDAC getEspecialidadeDAC()
	{
		return especialidadeDAC;
	}

	public void setEspecialidadeDAC(IEspecialidadeDAC especialidadeDAC)
	{
		this.especialidadeDAC = especialidadeDAC;
	}

	public IExameDAC getExameDAC()
	{
		return exameDAC;
	}

	public void setExameDAC(IExameDAC exameDAC)
	{
		this.exameDAC = exameDAC;
	}

	public ICondPagDAC getCondPagDAC()
	{
		return condPagDAC;
	}

	public void setCondPagDAC(ICondPagDAC condPagDAC)
	{
		this.condPagDAC = condPagDAC;
	}

	public IUsuarioDAC getUsuarioDAC()
	{
		return usuarioDAC;
	}

	public void setUsuarioDAC(IUsuarioDAC usuarioDAC)
	{
		this.usuarioDAC = usuarioDAC;
	}

	/**
	 * @return the profissaoDAC
	 */
	public IProfissaoDAC getProfissaoDAC()
	{
		return profissaoDAC;
	}

	/**
	 * @param profissaoDAC the profissaoDAC to set
	 */
	public void setProfissaoDAC(IProfissaoDAC profissaoDAC)
	{
		this.profissaoDAC = profissaoDAC;
	}

	/**
	 * @return the horaFuncDAC
	 */
	public IHoraFuncDAC getHoraFuncDAC()
	{
		return horaFuncDAC;
	}

	/**
	 * @param horaFuncDAC the horaFuncDAC to set
	 */
	public void setHoraFuncDAC(IHoraFuncDAC horaFuncDAC)
	{
		this.horaFuncDAC = horaFuncDAC;
	}

	/**
	 * @return the salarioDAC
	 */
	public ISalariosDAC getSalarioDAC()
	{
		return salarioDAC;
	}

	/**
	 * @param salarioDAC the salarioDAC to set
	 */
	public void setSalarioDAC(ISalariosDAC salarioDAC)
	{
		this.salarioDAC = salarioDAC;
	}

	/**
	 * @return the beneficiosDAC
	 */
	public IBeneficiosDAC getBeneficiosDAC()
	{
		return beneficiosDAC;
	}

	/**
	 * @param beneficiosDAC the beneficiosDAC to set
	 */
	public void setBeneficiosDAC(IBeneficiosDAC beneficiosDAC)
	{
		this.beneficiosDAC = beneficiosDAC;
	}

	/**
	 * @return the eventosDAC
	 */
	public IEventosDAC getEventosDAC()
	{
		return eventosDAC;
	}

	/**
	 * @param eventosDAC the eventosDAC to set
	 */
	public void setEventosDAC(IEventosDAC eventosDAC)
	{
		this.eventosDAC = eventosDAC;
	}

	public ICfopDAC getCfopDAC()
	{
		return cfopDAC;
	}

	public void setCfopDAC(ICfopDAC cfopDAC)
	{
		this.cfopDAC = cfopDAC;
	}

	/**
	 * @return the formaPgDAC
	 */
	public IFormaPagamentoDAC getFormaPgDAC()
	{
		return formaPgDAC;
	}

	/**
	 * @param formaPgDAC the formaPgDAC to set
	 */
	public void setFormaPgDAC(IFormaPagamentoDAC formaPgDAC)
	{
		this.formaPgDAC = formaPgDAC;
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

	public IAgenciaDAC getAgenciaDAC()
	{
		return agenciaDAC;
	}

	public void setAgenciaDAC(IAgenciaDAC agenciaDAC)
	{
		this.agenciaDAC = agenciaDAC;
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

	public IConvenioDAC getConvenioDAC()
	{
		return convenioDAC;
	}

	public void setConvenioDAC(IConvenioDAC convenioDAC)
	{
		this.convenioDAC = convenioDAC;
	}

	/**
	 * @return the bancoDAC
	 */
	public IBancoDAC getBancoDAC()
	{
		return bancoDAC;
	}

	/**
	 * @param bancoDAC the bancoDAC to set
	 */
	public void setBancoDAC(IBancoDAC bancoDAC)
	{
		this.bancoDAC = bancoDAC;
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

		Pessoa pessoaa = new Pessoa();

		pessoaa = pessoa;
		pessoa = (Cliente)pessoaa;

		Integer historicoId = HistoricoDACD.maintainHistoricoAssociations(pessoa.getId(), pessoa.getCreateUser(), 0,
				TabelaEnum.FUNCIONARIO, getHistoricoDAC());

		Integer processId = historicoId;
		pessoaa.setProcessId(historicoId);

		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "PessoaMap.insertPessoa", pessoaa, response);

		historicoId = HistoricoDACD.maintainHistoricoItensAssociations(historicoId, pessoa.getId(), pessoa.getUserId(),
				historicoId,
				TabelaEnum.FUNCIONARIO, AcaoEnum.INSERT, getHistoricoDAC());

		if (response.isInError())
		{
			return response;
		}

		insertCount += insertPerson(pessoa, processId, historicoId, response);

		insertCount +=
				ProfissaoDACD.maintainProfissaoAssociationsList(pessoa.getProfissao(), response, insertCount, null,
						null,
						null, getProfissaoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId, historicoId);

		insertCount +=
				ConvenioDACD.maintainConvenioAssociations(pessoa.getConvenioList(), response, insertCount, null,
						null,
						null, getConvenioDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId, historicoId);

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ANALISANDO);
			List<Status> statusList = new ArrayList<Status>();
			insertCount =
					StatusDACD.maintainStatusAssociations(statusList, response, pessoa.getId(), null, AcaoEnum.INSERT,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.EMPRESA, getStatusDAC(),
							getHistoricoDAC(), processId, null);

		}

		// Next traverse the object graph and "maintain" the associations

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

		// First update the root if necessary.
		if (!ValidationUtil.isNull(pessoa.getModelAction())
				&& (pessoa.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), "PessoaMap.updatePessoa", pessoa,
							response);
		}

		Integer historicoId = pessoa.getProcessId();

		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(historicoId);
		historicoItens.setProcessId(historicoId);
		historicoItens.setParentId(pessoa.getId());
		historicoItens.setTabelaEnum(TabelaEnum.PESSOA);
		historicoItens.setAcaoType(AcaoEnum.UPDATE);

		updateCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistoricoItens", historicoItens,
						response);

		if (response.isInError())
		{
			return response;
		}

		updateCount += insertPerson(pessoa, historicoId, historicoId, response);
		// Next traverse the object graph and "maintain" the associations

		// updateCount +=
		// ProfissaoDACD.maintainProfissaoAssociations(pessoa.getProfissao(), response, pessoa.getId(), null,
		// null,
		// null, getProfissaoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
		// pessoa.getCreateUser(), historicoId, historicoId, getProfissaoDAC());

		updateCount +=
				ConvenioDACD.maintainConvenioAssociations(pessoa.getConvenioList(), response, pessoa.getId(), null,
						null,
						null, getConvenioDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), historicoId, historicoId);

		if (updateCount > 0)
		{
			updateCount =
					StatusDACD.maintainStatusAssociations(pessoa.getStatusList(), response, pessoa.getId(), null,
							AcaoEnum.UPDATE,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.CLIENTE, getStatusDAC(),
							getHistoricoDAC(), historicoId, null);

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

		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(pessoa.getProcessId());
		historicoItens.setProcessId(pessoa.getProcessId());
		historicoItens.setParentId(pessoa.getId());
		historicoItens.setTabelaEnum(TabelaEnum.PESSOA);
		historicoItens.setAcaoType(AcaoEnum.DELETE);

		updateCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistoricoItens", historicoItens,
						response);

		Status status = new Status();
		status.setStatus(CdStatusTypeEnum.DELETADO);
		List<Status> statusList = new ArrayList<Status>();
		statusList.add(status);
		updateCount +=
				StatusDACD
						.maintainStatusAssociations(statusList, null, pessoa.getId(),
								null, AcaoEnum.DELETE,
								pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.CLIENTE, getStatusDAC(),
								getHistoricoDAC(), pessoa.getProcessId(), null);

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
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), "ClienteMap.fetchClienteById", request, response);
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
	
	//=========================Advogado=========================
	
	@Override
	public InternalResultsResponse<Advogado> insertAdvogado(Advogado pessoa)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Advogado> response = new InternalResultsResponse<Advogado>();

		Pessoa pessoaa = new Pessoa();

		pessoaa = pessoa;
		pessoa = (Advogado)pessoaa;

		Integer historicoId = HistoricoDACD.maintainHistoricoAssociations(pessoa.getId(), pessoa.getCreateUser(), 0,
				TabelaEnum.FUNCIONARIO, getHistoricoDAC());

		Integer processId = historicoId;
		pessoaa.setProcessId(historicoId);

		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "PessoaMap.insertPessoa", pessoaa, response);

		historicoId = HistoricoDACD.maintainHistoricoItensAssociations(historicoId, pessoa.getId(), pessoa.getUserId(),
				historicoId,
				TabelaEnum.FUNCIONARIO, AcaoEnum.INSERT, getHistoricoDAC());

		if (response.isInError())
		{
			return response;
		}

		insertCount += insertPerson(pessoa, processId, historicoId, response);

		insertCount +=
				ProfissaoDACD.maintainProfissaoAssociationsList(pessoa.getProfissao(), response, insertCount, null,
						null,
						null, getProfissaoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId, historicoId);

		insertCount +=
				ConvenioDACD.maintainConvenioAssociations(pessoa.getConvenioList(), response, insertCount, null,
						null,
						null, getConvenioDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId, historicoId);

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ANALISANDO);
			List<Status> statusList = new ArrayList<Status>();
			insertCount =
					StatusDACD.maintainStatusAssociations(statusList, response, pessoa.getId(), null, AcaoEnum.INSERT,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.EMPRESA, getStatusDAC(),
							getHistoricoDAC(), processId, null);

		}

		// Next traverse the object graph and "maintain" the associations

		// Finally, if something was inserted then add the Advogado to the result.
		if (insertCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Advogado> updateAdvogado(Advogado pessoa)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Advogado> response = new InternalResultsResponse<Advogado>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(pessoa.getModelAction())
				&& (pessoa.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), "PessoaMap.updatePessoa", pessoa,
							response);
		}

		Integer historicoId = pessoa.getProcessId();

		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(historicoId);
		historicoItens.setProcessId(historicoId);
		historicoItens.setParentId(pessoa.getId());
		historicoItens.setTabelaEnum(TabelaEnum.PESSOA);
		historicoItens.setAcaoType(AcaoEnum.UPDATE);

		updateCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistoricoItens", historicoItens,
						response);

		if (response.isInError())
		{
			return response;
		}

		updateCount += insertPerson(pessoa, historicoId, historicoId, response);
		// Next traverse the object graph and "maintain" the associations

		// updateCount +=
		// ProfissaoDACD.maintainProfissaoAssociations(pessoa.getProfissao(), response, pessoa.getId(), null,
		// null,
		// null, getProfissaoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
		// pessoa.getCreateUser(), historicoId, historicoId, getProfissaoDAC());

		updateCount +=
				ConvenioDACD.maintainConvenioAssociations(pessoa.getConvenioList(), response, pessoa.getId(), null,
						null,
						null, getConvenioDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), historicoId, historicoId);

		if (updateCount > 0)
		{
			updateCount =
					StatusDACD.maintainStatusAssociations(pessoa.getStatusList(), response, pessoa.getId(), null,
							AcaoEnum.UPDATE,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.CLIENTE, getStatusDAC(),
							getHistoricoDAC(), historicoId, null);

		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;
	}

	@Override
	public InternalResponse deleteAdvogado(Advogado pessoa)
	{
		Integer updateCount = 0;
		InternalResponse response = new InternalResponse();

		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(pessoa.getProcessId());
		historicoItens.setProcessId(pessoa.getProcessId());
		historicoItens.setParentId(pessoa.getId());
		historicoItens.setTabelaEnum(TabelaEnum.PESSOA);
		historicoItens.setAcaoType(AcaoEnum.DELETE);

		updateCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistoricoItens", historicoItens,
						response);

		Status status = new Status();
		status.setStatus(CdStatusTypeEnum.DELETADO);
		List<Status> statusList = new ArrayList<Status>();
		statusList.add(status);
		updateCount +=
				StatusDACD
						.maintainStatusAssociations(statusList, null, pessoa.getId(),
								null, AcaoEnum.DELETE,
								pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.CLIENTE, getStatusDAC(),
								getHistoricoDAC(), pessoa.getProcessId(), null);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IAdvogadoDAC#fetchAdvogadoById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Advogado> fetchAdvogadoById(FetchByIdRequest request)
	{
		InternalResultsResponse<Advogado> response = new InternalResultsResponse<Advogado>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), "AdvogadoMap.fetchAdvogadoById", request, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Advogado> fetchAdvogadoByRequest(AdvogadoInquiryRequest request)
	{
		InternalResultsResponse<Advogado> response = new InternalResultsResponse<Advogado>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getAdvogadoInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "AdvogadoMap.fetchAdvogadoRowCount",
				"AdvogadoMap.fetchAllAdvogadosByRequest", response);
		return response;
	}


	// ========================Fornecedor=======================
	@Override
	public InternalResultsResponse<Fornecedor> insertFornecedor(Fornecedor pessoa)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Fornecedor> response = new InternalResultsResponse<Fornecedor>();

		Historico historico = new Historico();
		historico.setEmprId(pessoa.getEmprId());
		historico.setUserId(pessoa.getUserId());
		historico.setProcessId(0);
		Date a = new Date();

		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistorico", historico, response);

		Integer historicoId = historico.getId();

		pessoa.setProcessId(historicoId);

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), "PessoaMap.insertPessoa", pessoa, response);

		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(historicoId);
		historicoItens.setProcessId(historicoId);
		historicoItens.setParentId(pessoa.getId());
		historicoItens.setTabelaEnum(TabelaEnum.FORNECEDOR);
		historicoItens.setAcaoType(AcaoEnum.INSERT);

		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistoricoItens", historicoItens,
						response);

		historicoId = historico.getId();

		if (response.isInError())
		{
			return response;
		}

		insertCount += insertPerson(pessoa, historicoId, historicoId, response);

		insertCount +=
				FormaPagamentoDACD.maintainFormaPgAssociations(pessoa.getFormaPagamentoList(), response,
						pessoa.getId(),
						null,
						null,
						TabelaEnum.PESSOA, getFormaPgDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), historicoId, historicoId);

		insertCount +=
				CfopDACD.maintainCfopAssociations(pessoa.getListCfops(), response, pessoa.getId(), null,
						null,
						TabelaEnum.PESSOA, getCfopDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), historicoId, historicoId);

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ANALISANDO);
			List<Status> statusList = new ArrayList<Status>();
			insertCount +=
					StatusDACD.maintainStatusAssociations(statusList, response, pessoa.getId(), null, AcaoEnum.INSERT,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.FORNECEDOR, getStatusDAC(),
							getHistoricoDAC(), historicoId, null);

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
		Date a = new Date();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(pessoa.getModelAction())
				&& (pessoa.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), "PessoaMap.updatePessoa", pessoa,
							response);
		}

		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(pessoa.getProcessId());
		historicoItens.setProcessId(pessoa.getProcessId());
		historicoItens.setParentId(pessoa.getId());
		historicoItens.setTabelaEnum(TabelaEnum.FORNECEDOR);
		historicoItens.setAcaoType(AcaoEnum.INSERT);

		updateCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistoricoItens", historicoItens,
						response);

		if (response.isInError())
		{
			return response;
		}

		updateCount += insertPerson(pessoa, pessoa.getProcessId(), pessoa.getProcessId(), response);
		// Next traverse the object graph and "maintain" the associations

		updateCount +=
				FormaPagamentoDACD.maintainFormaPgAssociations(pessoa.getFormaPagamentoList(), response,
						pessoa.getId(),
						null,
						null,
						null, getFormaPgDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), pessoa.getProcessId(), pessoa.getProcessId());

		updateCount +=
				CfopDACD.maintainCfopAssociations(pessoa.getListCfops(), response, pessoa.getId(), null,
						null,
						null, getCfopDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), pessoa.getProcessId(), pessoa.getProcessId());

		if (updateCount > 0)
		{

			updateCount =
					StatusDACD.maintainStatusAssociations(pessoa.getStatusList(), response, pessoa.getId(), null,
							AcaoEnum.INSERT,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.FORNECEDOR, getStatusDAC(),
							getHistoricoDAC(), pessoa.getProcessId(), null);

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

		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(pessoa.getProcessId());
		historicoItens.setProcessId(pessoa.getProcessId());
		historicoItens.setParentId(pessoa.getId());
		historicoItens.setTabelaEnum(TabelaEnum.FORNECEDOR);
		historicoItens.setAcaoType(AcaoEnum.INSERT);

		updateCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistoricoItens", historicoItens,
						response);

		Status status = new Status();
		status.setStatus(CdStatusTypeEnum.DELETADO);
		List<Status> statusList = new ArrayList<Status>();
		updateCount =
				StatusDACD
						.maintainStatusAssociations(statusList, null, pessoa.getId(),
								null, AcaoEnum.DELETE,
								pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.FORNECEDOR, getStatusDAC(),
								getHistoricoDAC(), pessoa.getProcessId(), null);

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
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), "FornecedorMap.fetchFornecedorById", request, response);
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
				"FornecedorMap.fetchAllFornecedorsByRequest", response);
		return response;
	}

	// ==============================Transportador

	@Override
	public InternalResultsResponse<Transportador> insertTransportador(Transportador pessoa)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Transportador> response = new InternalResultsResponse<Transportador>();

		Historico historico = new Historico();
		historico.setEmprId(pessoa.getEmprId());
		historico.setUserId(pessoa.getUserId());
		historico.setProcessId(0);
		Date a = new Date();

		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistorico", historico, response);

		Integer historicoId = historico.getId();

		pessoa.setProcessId(historicoId);

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), "PessoaMap.insertPessoa", pessoa, response);

		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(historicoId);
		historicoItens.setProcessId(historicoId);
		historicoItens.setParentId(pessoa.getId());
		historicoItens.setTabelaEnum(TabelaEnum.FORNECEDOR);
		historicoItens.setAcaoType(AcaoEnum.INSERT);

		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistoricoItens", historicoItens,
						response);

		historicoId = historico.getId();

		if (response.isInError())
		{
			return response;
		}

		insertCount += insertPerson(pessoa, 0, historicoId, response);

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ANALISANDO);
			List<Status> statusList = new ArrayList<Status>();
			insertCount =
					StatusDACD.maintainStatusAssociations(statusList, response, pessoa.getId(), null, AcaoEnum.INSERT,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.TRANSPORTADOR, getStatusDAC(),
							getHistoricoDAC(), 0, null);

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

		Date a = new Date();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(pessoa.getModelAction())
				&& (pessoa.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), "PessoaMap.updatePessoa", pessoa,
							response);
		}

		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(pessoa.getProcessId());
		historicoItens.setProcessId(pessoa.getProcessId());
		historicoItens.setParentId(pessoa.getId());
		historicoItens.setTabelaEnum(TabelaEnum.FORNECEDOR);
		historicoItens.setAcaoType(AcaoEnum.INSERT);

		updateCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistoricoItens", historicoItens,
						response);

		if (response.isInError())
		{
			return response;
		}

		updateCount += insertPerson(pessoa, pessoa.getProcessId(), pessoa.getProcessId(), response);
		// Next traverse the object graph and "maintain" the associations

		updateCount +=
				FormaPagamentoDACD.maintainFormaPgAssociations(pessoa.getFormaPagamentoList(), response,
						pessoa.getId(),
						null,
						null,
						null, getFormaPgDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), pessoa.getProcessId(), pessoa.getProcessId());

		if (updateCount > 0)
		{

			updateCount =
					StatusDACD.maintainStatusAssociations(pessoa.getStatusList(), response, pessoa.getId(), null,
							AcaoEnum.INSERT,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.TRANSPORTADOR, getStatusDAC(),
							getHistoricoDAC(), pessoa.getProcessId(), pessoa.getProcessId());

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

		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(pessoa.getProcessId());
		historicoItens.setProcessId(pessoa.getProcessId());
		historicoItens.setParentId(pessoa.getId());
		historicoItens.setTabelaEnum(TabelaEnum.TRANSPORTADOR);
		historicoItens.setAcaoType(AcaoEnum.DELETE);

		updateCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistoricoItens", historicoItens,
						response);

		Status status = new Status();
		status.setStatus(CdStatusTypeEnum.DELETADO);
		List<Status> statusList = new ArrayList<Status>();
		updateCount =
				StatusDACD
						.maintainStatusAssociations(statusList, null, pessoa.getId(),
								null, AcaoEnum.DELETE,
								pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.TRANSPORTADOR, getStatusDAC(),
								getHistoricoDAC(), pessoa.getProcessId(), null);
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
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), "TransportadorMap.fetchTransportadorById", request,
				response);
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

	// ==============Contador
	// ==============================Contador

	@Override
	public InternalResultsResponse<Contador> insertContador(Contador pessoa)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Contador> response = new InternalResultsResponse<Contador>();

		Historico historico = new Historico();
		historico.setEmprId(pessoa.getEmprId());
		historico.setUserId(pessoa.getUserId());
		historico.setProcessId(0);
		Date a = new Date();

		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistorico", historico, response);

		Integer historicoId = historico.getId();

		pessoa.setProcessId(historicoId);

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), "PessoaMap.insertPessoa", pessoa, response);

		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(historicoId);
		historicoItens.setProcessId(historicoId);
		historicoItens.setParentId(pessoa.getId());
		historicoItens.setTabelaEnum(TabelaEnum.FORNECEDOR);
		historicoItens.setAcaoType(AcaoEnum.INSERT);

		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistoricoItens", historicoItens,
						response);

		historicoId = historico.getId();

		if (response.isInError())
		{
			return response;
		}

		insertCount += insertPerson(pessoa, 0, historicoId, response);

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ANALISANDO);
			List<Status> statusList = new ArrayList<Status>();
			insertCount =
					StatusDACD.maintainStatusAssociations(statusList, response, pessoa.getId(), null, AcaoEnum.INSERT,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.TRANSPORTADOR, getStatusDAC(),
							getHistoricoDAC(), 0, null);

		}

		// Finally, if something was inserted then add the Contador to the result.
		if (insertCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Contador> updateContador(Contador pessoa)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Contador> response = new InternalResultsResponse<Contador>();

		Date a = new Date();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(pessoa.getModelAction())
				&& (pessoa.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), "PessoaMap.updatePessoa", pessoa,
							response);
		}

		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(pessoa.getProcessId());
		historicoItens.setProcessId(pessoa.getProcessId());
		historicoItens.setParentId(pessoa.getId());
		historicoItens.setTabelaEnum(TabelaEnum.FORNECEDOR);
		historicoItens.setAcaoType(AcaoEnum.INSERT);

		updateCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistoricoItens", historicoItens,
						response);

		if (response.isInError())
		{
			return response;
		}

		updateCount += insertPerson(pessoa, pessoa.getProcessId(), pessoa.getProcessId(), response);
		// Next traverse the object graph and "maintain" the associations

		updateCount +=
				FormaPagamentoDACD.maintainFormaPgAssociations(pessoa.getFormaPagamentoList(), response,
						pessoa.getId(),
						null,
						null,
						null, getFormaPgDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), pessoa.getProcessId(), pessoa.getProcessId());

		if (updateCount > 0)
		{

			updateCount =
					StatusDACD.maintainStatusAssociations(pessoa.getStatusList(), response, pessoa.getId(), null,
							AcaoEnum.INSERT,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.TRANSPORTADOR, getStatusDAC(),
							getHistoricoDAC(), pessoa.getProcessId(), pessoa.getProcessId());

		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;
	}

	@Override
	public InternalResponse deleteContador(Contador pessoa)
	{
		Integer updateCount = 0;
		InternalResponse response = new InternalResponse();

		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(pessoa.getProcessId());
		historicoItens.setProcessId(pessoa.getProcessId());
		historicoItens.setParentId(pessoa.getId());
		historicoItens.setTabelaEnum(TabelaEnum.TRANSPORTADOR);
		historicoItens.setAcaoType(AcaoEnum.DELETE);

		updateCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistoricoItens", historicoItens,
						response);

		Status status = new Status();
		status.setStatus(CdStatusTypeEnum.DELETADO);
		List<Status> statusList = new ArrayList<Status>();
		updateCount =
				StatusDACD
						.maintainStatusAssociations(statusList, null, pessoa.getId(),
								null, AcaoEnum.DELETE,
								pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.TRANSPORTADOR, getStatusDAC(),
								getHistoricoDAC(), pessoa.getProcessId(), null);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IContadorDAC#fetchContadorById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Contador> fetchContadorById(FetchByIdRequest request)
	{
		InternalResultsResponse<Contador> response = new InternalResultsResponse<Contador>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Contador> fetchContadorByRequest(ContadorInquiryRequest request)
	{
		InternalResultsResponse<Contador> response = new InternalResultsResponse<Contador>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getContadorInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "ContadorMap.fetchContadorRowCount",
				"ContadorMap.fetchContadorRowCount", response);

		return response;
	}

	private Integer insertPerson(Pessoa pessoa, Integer processId, Integer historicoId,
			InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		insertCount +=
				EnderecoDACD.maintainEnderecoAssociations(pessoa.getEnderecos(), response, pessoa.getId(), null, null,
						TabelaEnum.PESSOA, getEnderecoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId, historicoId, null);

		insertCount +=
				EmailDACD.maintainEmailAssociations(pessoa.getEmails(), response, pessoa.getId(), null, null,
						TabelaEnum.PESSOA, getEmailDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId, historicoId);

		insertCount +=
				TelefoneDACD.maintainTelefoneAssociations(pessoa.getTelefones(), response, pessoa.getId(), null, null,
						TabelaEnum.PESSOA, getTelefoneDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId, historicoId);

		insertCount +=
				DocumentosDACD.maintainDocumentoAssociations(pessoa.getDocumentos(), response, pessoa.getId(), null,
						null,
						TabelaEnum.PESSOA, getDocumentoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId, historicoId);

		insertCount +=
				NotesDACD.maintainNoteAssociations(pessoa.getNotes(), response, pessoa.getId(), null,
						null,
						TabelaEnum.PESSOA, getNoteDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId, historicoId);

		insertCount +=
				BancoDACD.maintainBancoAssociations(pessoa.getBancos(), response, pessoa.getId(), null,
						null,
						TabelaEnum.PESSOA, getBancoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId, historicoId, getAgenciaDAC());

		insertCount +=
				ContatoDACD.maintainContatoAssociations(pessoa.getContatoList(), response, insertCount, null,
						null,
						TabelaEnum.PESSOA, getContatoDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId, historicoId);

		insertCount +=
				FormaPagamentoDACD.maintainFormaPgAssociations(pessoa.getFormaPagamentoList(), response, insertCount,
						null,
						null,
						TabelaEnum.PESSOA, getFormaPgDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId, historicoId);

		insertCount +=

				CondPagDACD.maintainCondPagAssociations(pessoa.getCondPagList(), response, insertCount, null,
						null,
						TabelaEnum.PESSOA, getCondPagDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId, historicoId);

		return insertCount;

	}

	@Override
	public InternalResultsResponse<Funcionario> updateFuncionario(Funcionario pessoa)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();
		Date a = new Date();
		// First update the root if necessary.
		if (!ValidationUtil.isNull(pessoa.getModelAction())
				&& (pessoa.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), "PessoaMap.updatePessoa", pessoa,
							response);
		}

		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(pessoa.getProcessId());
		historicoItens.setProcessId(pessoa.getProcessId());
		historicoItens.setParentId(pessoa.getId());
		historicoItens.setTabelaEnum(TabelaEnum.FORNECEDOR);
		historicoItens.setAcaoType(AcaoEnum.INSERT);

		updateCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistoricoItens", historicoItens,
						response);

		if (response.isInError())
		{
			return response;
		}

		updateCount += insertPerson(pessoa, pessoa.getProcessId(), pessoa.getProcessId(), response);
		// Next traverse the object graph and "maintain" the associations

		updateCount +=
				SalarioDACD.maintainSalarioAssociations(pessoa.getSalarios(), response, pessoa.getId(), null, null,
						null, getSalarioDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), pessoa.getProcessId(), pessoa.getProcessId(), getProfissaoDAC());

		updateCount +=
				BeneficiosDACD.maintainBeneficiosAssociations(pessoa.getBeneficios(), response, pessoa.getId(), null,
						null,
						null, getBeneficiosDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), pessoa.getProcessId(), pessoa.getProcessId());

		updateCount +=
				EventosDACD.maintainEventoPessoaAssociations(pessoa.getEventosList(), response, pessoa.getId(), null,
						null,
						null, getEventosDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), pessoa.getProcessId(), pessoa.getProcessId());

		updateCount +=
				HorarioDACD.maintainHorarioFuncAssociations(pessoa.getHorarios(), response, pessoa.getId(), null,
						null,
						null, getHoraFuncDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), pessoa.getProcessId(), pessoa.getProcessId());

		if (updateCount > 0)
		{

			updateCount =
					StatusDACD.maintainStatusAssociations(pessoa.getStatusList(), response, pessoa.getId(), null,
							AcaoEnum.UPDATE,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.FUNCIONARIO, getStatusDAC(),
							getHistoricoDAC(), pessoa.getProcessId(), null);

		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Funcionario> insertFuncionario(Funcionario pessoa)
	{
		Integer insertCount = 0;
		Integer historicoId = 0;

		Integer processId = 0;
		InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();

		if (pessoa.getModelAction() == PersistanceActionEnum.INSERT)
		{
			Historico historico = new Historico();
			historico.setEmprId(pessoa.getId());
			historico.setUserId(pessoa.getUserId());
			historico.setAcaoEnumValue(1);
			historico.setTabelaEnum(TabelaEnum.FUNCIONARIO);
			historico.setProcessId(0);
			Date a = new Date();
			historico.setData(a.getTime());

			insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistorico", historico, response);

			historicoId = historico.getId();

			processId = historicoId;

			pessoa.setProcessId(processId);

			// First insert the root
			// Is successful the unique-id will be populated back into the object.
			insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), "PessoaMap.insertPessoa", pessoa, response);

			HistoricoItens historicoItens = new HistoricoItens();
			historicoItens.setIdHist(historicoId);
			historicoItens.setProcessId(0);
			historicoItens.setParentId(pessoa.getId());
			historicoItens.setTabelaEnum(TabelaEnum.FUNCIONARIO);
			historicoItens.setAcaoType(AcaoEnum.INSERT);

			insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistoricoItens", historicoItens,
							response);

			if (response.isInError())
			{
				return response;
			}
		}

		insertCount += insertPerson(pessoa, processId, historicoId, response);
		// Next traverse the object graph and "maintain" the associations

		insertCount +=
				SalarioDACD.maintainSalarioAssociations(pessoa.getSalarios(), response, pessoa.getId(), null, null,
						null, getSalarioDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId, historicoId, getProfissaoDAC());

		insertCount +=
				BeneficiosDACD.maintainBeneficiosAssociations(pessoa.getBeneficios(), response, pessoa.getId(), null,
						null,
						null, getBeneficiosDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId, historicoId);

		insertCount +=
				EventosDACD.maintainEventoPessoaAssociations(pessoa.getEventosList(), response, pessoa.getId(), null,
						null,
						null, getEventosDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId, historicoId);

		insertCount +=
				HorarioDACD.maintainHorarioFuncAssociations(pessoa.getHorarios(), response, pessoa.getId(), null,
						null,
						null, getHoraFuncDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId, historicoId);

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ANALISANDO);
			List<Status> statusList = new ArrayList<Status>();
			insertCount +=
					StatusDACD.maintainStatusAssociations(statusList, response, pessoa.getId(), null, AcaoEnum.INSERT,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.TRANSPORTADOR, getStatusDAC(),
							getHistoricoDAC(), processId, null);

		}

		// Finally, if something was inserted then add the Contador to the result.
		if (insertCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Funcionario> fetchFuncionarioById(FetchByIdRequest request)
	{
		InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), "FuncionarioMap.fetchFuncionarioById", request, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Funcionario> fetchFuncionarioByRequest(FuncionarioInquiryRequest request)
	{
		InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getClienteInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "FuncionarioMap.fetchFuncionarioRowCount",
				"FuncionarioMap.fetchAllFuncionariosByRequest", response);
		return response;
	}

	@Override
	public InternalResponse deleteFuncionario(Funcionario funcionario)
	{
		Integer updateCount = 0;
		InternalResponse response = new InternalResponse();

		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(funcionario.getProcessId());
		historicoItens.setProcessId(funcionario.getProcessId());
		historicoItens.setParentId(funcionario.getId());
		historicoItens.setTabelaEnum(TabelaEnum.FORNECEDOR);
		historicoItens.setAcaoType(AcaoEnum.INSERT);

		updateCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistoricoItens", historicoItens,
						response);

		Status status = new Status();
		status.setStatus(CdStatusTypeEnum.DELETADO);
		List<Status> statusList = new ArrayList<Status>();
		statusList.add(status);
		updateCount =
				StatusDACD
						.maintainStatusAssociations(statusList, null,
								funcionario.getId(),
								null, AcaoEnum.DELETE,
								funcionario.getCreateUser(), funcionario.getEmprId(), TabelaEnum.TRANSPORTADOR,
								getStatusDAC(),
								getHistoricoDAC(), funcionario.getProcessId(), null);
		return response;
	}

	@Override
	public InternalResultsResponse<Profissao> fetchProfissaoByRequest(ProfissaoInquiryRequest request)
	{
		InternalResultsResponse<Profissao> response = new InternalResultsResponse<Profissao>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getClienteInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "ProfissaoMap.fetchProfissaoRowCount",
				"ProfissaoMap.fetchAllProfissaosByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Convenio> fetchConvenioByRequest(ConvenioInquiryRequest request)
	{
		InternalResultsResponse<Convenio> response = new InternalResultsResponse<Convenio>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getClienteInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "ConvenioMap.fetchConvenioRowCount",
				"ConvenioMap.fetchAllConveniosByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Contato> fetchContatoByRequest(ContatoInquiryRequest request)
	{
		InternalResultsResponse<Contato> response = new InternalResultsResponse<Contato>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getClienteInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "ContatoMap.fetchContatoRowCount",
				"ContatoMap.fetchAllContatosByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Banco> fetchBancoByRequest(BancoInquiryRequest request)
	{
		InternalResultsResponse<Banco> response = new InternalResultsResponse<Banco>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getClienteInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "BancoMap.fetchBancoRowCount",
				"BancoMap.fetchAllBancosByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<FormaPg> fetchFormaPgByRequest(FormaPgInquiryRequest request)
	{
		InternalResultsResponse<FormaPg> response = new InternalResultsResponse<FormaPg>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getClienteInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "FormaPgMap.fetchFormaPgRowCount",
				"FormaPgMap.fetchAllFormaPgsByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Agencia> fetchAgenciaByRequest(AgenciaInquiryRequest request)
	{
		InternalResultsResponse<Agencia> response = new InternalResultsResponse<Agencia>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getClienteInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "AgenciaMap.fetchAgenciaRowCount",
				"AgenciaMap.fetchAllAgenciasByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Conta> fetchContaByRequest(ContaInquiryRequest request)
	{
		InternalResultsResponse<Conta> response = new InternalResultsResponse<Conta>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getClienteInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "ContaMap.fetchContaRowCount",
				"ContaMap.fetchAllContasByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Estado> fetchEstadoByRequest(EstadoInquiryRequest request)
	{
		InternalResultsResponse<Estado> response = new InternalResultsResponse<Estado>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getClienteInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "EstadoMap.fetchEstadoRowCount",
				"EstadoMap.fetchAllEstadoByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Cidade> fetchCidadeRequest(CidadeInquiryRequest request)
	{
		InternalResultsResponse<Cidade> response = new InternalResultsResponse<Cidade>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getClienteInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "CidadeMap.fetchCidadeRowCount",
				"CidadeMap.fetchAllCidadesByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Eventos> fetchEventosRequest(EventoInquiryRequest request)
	{
		InternalResultsResponse<Eventos> response = new InternalResultsResponse<Eventos>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getClienteInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "EventoMap.fetchEventoRowCount",
				"EventoMap.fetchAllEventosByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Beneficios> fetchBeneficiosRequest(BeneficiosInquiryRequest request)
	{
		InternalResultsResponse<Beneficios> response = new InternalResultsResponse<Beneficios>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getClienteInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "BeneficiosMap.fetchBeneficiosRowCount",
				"BeneficiosMap.fetchAllBeneficiosByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<HorarioFunc> fetchHorarioFuncsRequest(HoraFuncInquiryRequest request)
	{
		InternalResultsResponse<HorarioFunc> response = new InternalResultsResponse<HorarioFunc>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getClienteInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "HoraFuncMap.fetchHoraFuncRowCount",
				"HoraFuncMap.fetchAllHoraFuncByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Medico> updateMedico(Medico pessoa)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Medico> response = new InternalResultsResponse<Medico>();

		Integer historicoId = HistoricoDACD.maintainHistoricoAssociations(pessoa.getId(), pessoa.getCreateUser(), 0,
				TabelaEnum.MEDICO, getHistoricoDAC());

		Integer processId = historicoId;
		pessoa.setProcessId(historicoId);

		updateCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "MedicoMap.updateMedico", pessoa, response);

		historicoId = HistoricoDACD.maintainHistoricoItensAssociations(historicoId, pessoa.getId(), pessoa.getUserId(),
				historicoId,
				TabelaEnum.MEDICO, AcaoEnum.UPDATE, getHistoricoDAC());

		if (response.isInError())
		{
			return response;
		}

		updateCount += insertPerson(pessoa, processId, historicoId, response);

		updateCount +=
				EspecialidadeDACD.maintainEspecialidadeAssociations(
						pessoa.getEspecialidadeList(), response, pessoa.getId(), TypeEnum.HIGH, AcaoTypeEnum.UPDATE,
						TabelaEnum.MEDICO, getEspecialidadeDAC(), getStatusDAC(), getHistoricoDAC(),
						pessoa.getEmprId(), pessoa.getUserId(), pessoa.getProcessId(), historicoId);

		updateCount +=
				ConsultaDACD.maintainConsultaAssociations(
						pessoa.getConsultaList(), response, pessoa.getId(), TypeEnum.HIGH, AcaoTypeEnum.UPDATE,
						TabelaEnum.MEDICO, getConsultaDAC(), getStatusDAC(), getHistoricoDAC(),
						pessoa.getEmprId(), pessoa.getUserId(), pessoa.getProcessId(), historicoId, getExameDAC());

		updateCount +=
				HorarioDACD.maintainHorarioFuncAssociations(pessoa.getHorarioList(), response, pessoa.getId(), null,
						null,
						null, getHoraFuncDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId, historicoId);

		if (updateCount > 0)
		{

			updateCount +=
					StatusDACD.maintainStatusAssociations(pessoa.getStatusList(), response, pessoa.getId(), null,
							AcaoEnum.UPDATE,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.FUNCIONARIO, getStatusDAC(),
							getHistoricoDAC(), pessoa.getProcessId(), null);

		}

		// Next traverse the object graph and "maintain" the associations

		// Finally, if something was inserted then add the Cliente to the result.
		if (updateCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;

	}

	@Override
	public InternalResultsResponse<Medico> insertMedico(Medico pessoa)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Medico> response = new InternalResultsResponse<Medico>();

		Integer historicoId = HistoricoDACD.maintainHistoricoAssociations(pessoa.getId(), pessoa.getCreateUser(), 0,
				TabelaEnum.MEDICO, getHistoricoDAC());

		Integer processId = historicoId;
		pessoa.setProcessId(historicoId);

		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "MedicoMap.insertMedico", pessoa, response);

		historicoId = HistoricoDACD.maintainHistoricoItensAssociations(historicoId, pessoa.getId(), pessoa.getUserId(),
				historicoId,
				TabelaEnum.MEDICO, AcaoEnum.INSERT, getHistoricoDAC());

		if (response.isInError())
		{
			return response;
		}

		insertCount += insertPerson(pessoa, processId, historicoId, response);

		insertCount +=
				EspecialidadeDACD.maintainEspecialidadeAssociations(
						pessoa.getEspecialidadeList(), response, pessoa.getId(), TypeEnum.HIGH, AcaoTypeEnum.INSERT,
						TabelaEnum.MEDICO, getEspecialidadeDAC(), getStatusDAC(), getHistoricoDAC(),
						pessoa.getEmprId(), pessoa.getUserId(), pessoa.getProcessId(), historicoId);

		insertCount +=
				ConsultaDACD.maintainConsultaAssociations(
						pessoa.getConsultaList(), response, pessoa.getId(), TypeEnum.HIGH, AcaoTypeEnum.INSERT,
						TabelaEnum.MEDICO, getConsultaDAC(), getStatusDAC(), getHistoricoDAC(),
						pessoa.getEmprId(), pessoa.getUserId(), pessoa.getProcessId(), historicoId, getExameDAC());

		insertCount +=
				HorarioDACD.maintainHorarioFuncAssociations(pessoa.getHorarioList(), response, pessoa.getId(), null,
						null,
						null, getHoraFuncDAC(), getStatusDAC(), getHistoricoDAC(), pessoa.getEmprId(),
						pessoa.getCreateUser(), processId, historicoId);

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ANALISANDO);
			List<Status> statusList = new ArrayList<Status>();
			insertCount =
					StatusDACD.maintainStatusAssociations(statusList, response, pessoa.getId(), null, AcaoEnum.INSERT,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.EMPRESA, getStatusDAC(),
							getHistoricoDAC(), processId, null);

		}

		// Next traverse the object graph and "maintain" the associations

		// Finally, if something was inserted then add the Cliente to the result.
		if (insertCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;
	}

	@Override
	public InternalResponse deleteMedico(Medico pessoa)
	{
		Integer updateCount = 0;
		InternalResponse response = new InternalResponse();

		HistoricoDACD.maintainHistoricoItensAssociations(pessoa.getProcessId(), pessoa.getId(),
				pessoa.getUserId(),
				pessoa.getProcessId(),
				TabelaEnum.MEDICO, AcaoEnum.DELETE, getHistoricoDAC());

		Status status = new Status();
		status.setStatus(CdStatusTypeEnum.DELETADO);
		List<Status> statusList = new ArrayList<Status>();
		statusList.add(status);
		updateCount +=
				StatusDACD
						.maintainStatusAssociations(statusList, null, pessoa.getId(),
								null, AcaoEnum.DELETE,
								pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.CLIENTE, getStatusDAC(),
								getHistoricoDAC(), pessoa.getProcessId(), null);

		return response;
	}

	@Override
	public InternalResultsResponse<Medico> fetchMedicoById(FetchByIdRequest request)
	{
		InternalResultsResponse<Medico> response = new InternalResultsResponse<Medico>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), "MedicoMap.fetchMedicoById", request, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Medico> fetchMedicoByRequest(MedicoInquiryRequest request)
	{
		InternalResultsResponse<Medico> response = new InternalResultsResponse<Medico>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getClienteInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "MedicoMap.fetchMedicoRowCount",
				"MedicoMap.fetchAllMedicoByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Paciente> updatePaciente(Paciente pessoa)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Paciente> response = new InternalResultsResponse<Paciente>();

		Integer historicoId = HistoricoDACD.maintainHistoricoAssociations(pessoa.getId(), pessoa.getCreateUser(), 0,
				TabelaEnum.PACIENTE, getHistoricoDAC());

		Integer processId = historicoId;
		pessoa.setProcessId(historicoId);

		updateCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "PacienteMap.updatePaciente", pessoa, response);

		historicoId = HistoricoDACD.maintainHistoricoItensAssociations(historicoId, pessoa.getId(), pessoa.getUserId(),
				historicoId,
				TabelaEnum.PACIENTE, AcaoEnum.UPDATE, getHistoricoDAC());

		if (response.isInError())
		{
			return response;
		}

		updateCount += insertPerson(pessoa, processId, historicoId, response);

		updateCount += PlanoSaudeDACD.maintainPlanoSaudeAssociations(
				pessoa.getPlanoSaudeList(), response, pessoa.getId(), TypeEnum.HIGH, AcaoTypeEnum.UPDATE,
				TabelaEnum.PACIENTE, getPlanoSaudeDAC(), getStatusDAC(), getHistoricoDAC(),
				pessoa.getEmprId(), pessoa.getUserId(), pessoa.getProcessId(), historicoId);

		updateCount +=
				ConsultaDACD.maintainConsultaAssociations(
						pessoa.getConsultaList(), response, pessoa.getId(), TypeEnum.HIGH, AcaoTypeEnum.UPDATE,
						TabelaEnum.PACIENTE, getConsultaDAC(), getStatusDAC(), getHistoricoDAC(),
						pessoa.getEmprId(), pessoa.getUserId(), pessoa.getProcessId(), historicoId, getExameDAC());

		if (updateCount > 0)
		{

			updateCount +=
					StatusDACD.maintainStatusAssociations(pessoa.getStatusList(), response, pessoa.getId(), null,
							AcaoEnum.UPDATE,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.PACIENTE, getStatusDAC(),
							getHistoricoDAC(), pessoa.getProcessId(), null);

		}

		// Next traverse the object graph and "maintain" the associations

		// Finally, if something was inserted then add the Cliente to the result.
		if (updateCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Paciente> insertPaciente(Paciente pessoa)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Paciente> response = new InternalResultsResponse<Paciente>();

		Integer historicoId = HistoricoDACD.maintainHistoricoAssociations(pessoa.getId(), pessoa.getCreateUser(), 0,
				TabelaEnum.PACIENTE, getHistoricoDAC());

		Integer processId = historicoId;
		pessoa.setProcessId(historicoId);

		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "PacienteMap.insertPaciente", pessoa, response);

		historicoId = HistoricoDACD.maintainHistoricoItensAssociations(historicoId, pessoa.getId(), pessoa.getUserId(),
				historicoId,
				TabelaEnum.PACIENTE, AcaoEnum.INSERT, getHistoricoDAC());

		if (response.isInError())
		{
			return response;
		}

		insertCount += insertPerson(pessoa, processId, historicoId, response);

		insertCount += PlanoSaudeDACD.maintainPlanoSaudeAssociations(
				pessoa.getPlanoSaudeList(), response, pessoa.getId(), TypeEnum.HIGH, AcaoTypeEnum.UPDATE,
				TabelaEnum.PACIENTE, getPlanoSaudeDAC(), getStatusDAC(), getHistoricoDAC(),
				pessoa.getEmprId(), pessoa.getUserId(), pessoa.getProcessId(), historicoId);

		insertCount +=
				ConsultaDACD.maintainConsultaAssociations(
						pessoa.getConsultaList(), response, pessoa.getId(), TypeEnum.HIGH, AcaoTypeEnum.UPDATE,
						TabelaEnum.PACIENTE, getConsultaDAC(), getStatusDAC(), getHistoricoDAC(),
						pessoa.getEmprId(), pessoa.getUserId(), pessoa.getProcessId(), historicoId, getExameDAC());

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ANALISANDO);
			List<Status> statusList = new ArrayList<Status>();
			insertCount =
					StatusDACD.maintainStatusAssociations(statusList, response, pessoa.getId(), null, AcaoEnum.INSERT,
							pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.PACIENTE, getStatusDAC(),
							getHistoricoDAC(), processId, null);

		}

		// Next traverse the object graph and "maintain" the associations

		// Finally, if something was inserted then add the Cliente to the result.
		if (insertCount > 0)
		{
			response.addResult(pessoa);
		}

		return response;
	}

	@Override
	public InternalResponse deletePaciente(Paciente pessoa)
	{
		Integer updateCount = 0;
		InternalResponse response = new InternalResponse();

		HistoricoDACD.maintainHistoricoItensAssociations(pessoa.getProcessId(), pessoa.getId(),
				pessoa.getUserId(),
				pessoa.getProcessId(),
				TabelaEnum.PACIENTE, AcaoEnum.DELETE, getHistoricoDAC());

		Status status = new Status();
		status.setStatus(CdStatusTypeEnum.DELETADO);
		List<Status> statusList = new ArrayList<Status>();
		statusList.add(status);
		updateCount +=
				StatusDACD
						.maintainStatusAssociations(statusList, null, pessoa.getId(),
								null, AcaoEnum.DELETE,
								pessoa.getCreateUser(), pessoa.getEmprId(), TabelaEnum.PACIENTE, getStatusDAC(),
								getHistoricoDAC(), pessoa.getProcessId(), null);

		return response;
	}

	@Override
	public InternalResultsResponse<Paciente> fetchPacienteById(FetchByIdRequest request)
	{
		InternalResultsResponse<Paciente> response = new InternalResultsResponse<Paciente>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), "PacienteMap.fetchPacienteById", request, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Paciente> fetchPacienteByRequest(PacienteInquiryRequest request)
	{
		InternalResultsResponse<Paciente> response = new InternalResultsResponse<Paciente>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getClienteInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "PacienteMap.fetchPacienteRowCount",
				"PacienteMap.fetchAllPacientesByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Consulta> fetchConsultaByRequest(ConsultaInquiryRequest request)
	{
		InternalResultsResponse<Consulta> response = new InternalResultsResponse<Consulta>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getClienteInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "ConsultaMap.fetchConsultaRowCount",
				"ConsultaMap.fetchAllConsultaByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Especialidade> fetchEspecialidadeByRequest(EspecializacaoInquiryRequest request)
	{
		InternalResultsResponse<Especialidade> response = new InternalResultsResponse<Especialidade>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getClienteInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "EspecialidadeMap.fetchEspecialidadeRowCount",
				"EspecialidadeMap.fetchAllEspecialidadesByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Exame> fetchExameByRequest(ExameInquiryRequest request)
	{
		InternalResultsResponse<Exame> response = new InternalResultsResponse<Exame>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getClienteInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "ExameMap.fetchExameRowCount",
				"ExameMap.fetchAllExameByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<PlanoSaude> fetchPlanoSaudeRequest(PlanoSaudeInquiryRequest request)
	{
		InternalResultsResponse<PlanoSaude> response = new InternalResultsResponse<PlanoSaude>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getClienteInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "PlanoSaudeMap.fetchPlanoSaudeRowCount",
				"PlanoSaudeMap.fetchAllPlanoSaudeByRequest", response);
		return response;
	}
}
