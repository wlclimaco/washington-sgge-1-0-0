/** create by system gera-java version 1.0.0 02/05/2016 21:44 : 15*/
package com.qat.samples.sysmgmt.bar.mybatis.Pessoa;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.advocacia.Advogado;
import com.qat.samples.sysmgmt.advocacia.request.AdvogadoInquiryRequest;
import com.qat.samples.sysmgmt.bar.Advogado.IAdvocaciaBAR;
import com.qat.samples.sysmgmt.bar.Clinica.IClinicaBAR;
import com.qat.samples.sysmgmt.bar.Documentos.IDocumentoBAR;
import com.qat.samples.sysmgmt.bar.Dp.IDpBAR;
import com.qat.samples.sysmgmt.bar.Email.IEmailBAR;
import com.qat.samples.sysmgmt.bar.Endereco.IEnderecoBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Notes.INotesBAR;
import com.qat.samples.sysmgmt.bar.Pessoa.IPessoaBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.bar.Telefone.ITelefoneBAR;
import com.qat.samples.sysmgmt.bar.Util.IDoisValorBAR;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.ConsultaBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.DiasHorasBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.DocumentosBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.DoisValoresParentBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.EmailBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.EnderecoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.EspecialidadeBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.HorarioFuncBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.InsertHistBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.NotesBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.PessoaTipoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.StatusBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.TelefoneBARD;
import com.qat.samples.sysmgmt.clinica.model.request.MedicoInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.PacienteInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.Inquilino;
import com.qat.samples.sysmgmt.condominio.model.Sindico;
import com.qat.samples.sysmgmt.condominio.model.request.InquilinoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.SindicoInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.pessoa.model.Fornecedor;
import com.qat.samples.sysmgmt.pessoa.model.Funcionario;
import com.qat.samples.sysmgmt.pessoa.model.Medico;
import com.qat.samples.sysmgmt.pessoa.model.Paciente;
import com.qat.samples.sysmgmt.pessoa.model.Pessoa;
import com.qat.samples.sysmgmt.pessoa.model.PessoaTipo;
import com.qat.samples.sysmgmt.pessoa.model.Transportador;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorInquiryRequest;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class PessoaBARImpl extends SqlSessionDaoSupport implements IPessoaBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;
	///===================================### PESSOA ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_PESSOA = "PessoaMap.";

	/** The Constant STMT_INSERT_ADVOGADO. */
	private static final String STMT_INSERT_PESSOA = NAMESPACE_PESSOA + "insertPessoa";

	/** The Constant STMT_UPDATE_ADVOGADO. */
	private static final String STMT_UPDATE_PESSOA = NAMESPACE_PESSOA + "updatePessoa";

	/** The Constant STMT_DELETE_ADVOGADO. */
	private static final String STMT_DELETE_PESSOA = NAMESPACE_PESSOA + "deletePessoaById";

		/** The Constant STMT_DELETE_ADVOGADO_ALL. */
		private static final String STMT_DELETE_PESSOA_ALL = NAMESPACE_PESSOA + "deleteAllPessoas";


///===================================### ADVOGADO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_ADVOGADO = "AdvogadoMap.";

	/** The Constant STMT_FETCH_ADVOGADO. */
	private static final String STMT_FETCH_ADVOGADO = NAMESPACE_ADVOGADO + "fetchAdvogadoById";

	/** The Constant STMT_FETCH_ADVOGADO_ALL. */
	private static final String STMT_FETCH_ADVOGADO_ALL = NAMESPACE_ADVOGADO + "fetchAllAdvogados";

	/** The Constant STMT_FETCH_ADVOGADO_COUNT. */
	private static final String STMT_FETCH_ADVOGADO_COUNT = NAMESPACE_ADVOGADO + "fetchAdvogadoRowCount";

	/** The Constant STMT_FETCH_ADVOGADO_ALL_REQUEST. */
	private static final String STMT_FETCH_ADVOGADO_ALL_REQUEST = NAMESPACE_ADVOGADO + "fetchAllAdvogadosRequest";

	/** The Constant STMT_INSERT_ADVOGADO. */
	private static final String STMT_INSERT_ADVOGADO = NAMESPACE_ADVOGADO + "insertAdvogado";

	/** The Constant STMT_UPDATE_ADVOGADO. */
	private static final String STMT_UPDATE_ADVOGADO = NAMESPACE_ADVOGADO + "updateAdvogado";

	/** The Constant STMT_DELETE_ADVOGADO. */
	private static final String STMT_DELETE_ADVOGADO = NAMESPACE_ADVOGADO + "deleteAdvogadoById";

		/** The Constant STMT_DELETE_ADVOGADO_ALL. */
		private static final String STMT_DELETE_ADVOGADO_ALL = NAMESPACE_ADVOGADO + "deleteAllAdvogados";

///===================================### CLIENTE ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_CLIENTE = "ClienteMap.";

	/** The Constant STMT_FETCH_CLIENTE. */
	private static final String STMT_FETCH_CLIENTE = NAMESPACE_CLIENTE + "fetchClienteById";

	/** The Constant STMT_FETCH_CLIENTE_ALL. */
	private static final String STMT_FETCH_CLIENTE_ALL = NAMESPACE_CLIENTE + "fetchAllClientes";

	/** The Constant STMT_FETCH_CLIENTE_COUNT. */
	private static final String STMT_FETCH_CLIENTE_COUNT = NAMESPACE_CLIENTE + "fetchClienteRowCount";

	/** The Constant STMT_FETCH_CLIENTE_ALL_REQUEST. */
	private static final String STMT_FETCH_CLIENTE_ALL_REQUEST = NAMESPACE_CLIENTE + "fetchAllClientesRequest";

///===================================### FORNECEDOR ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_FORNECEDOR = "FornecedorMap.";

	/** The Constant STMT_FETCH_FORNECEDOR. */
	private static final String STMT_FETCH_FORNECEDOR = NAMESPACE_FORNECEDOR + "fetchFornecedorById";

	/** The Constant STMT_FETCH_FORNECEDOR_ALL. */
	private static final String STMT_FETCH_FORNECEDOR_ALL = NAMESPACE_FORNECEDOR + "fetchAllFornecedors";

	/** The Constant STMT_FETCH_FORNECEDOR_COUNT. */
	private static final String STMT_FETCH_FORNECEDOR_COUNT = NAMESPACE_FORNECEDOR + "fetchFornecedorRowCount";

	/** The Constant STMT_FETCH_FORNECEDOR_ALL_REQUEST. */
	private static final String STMT_FETCH_FORNECEDOR_ALL_REQUEST = NAMESPACE_FORNECEDOR + "fetchAllFornecedorsRequest";

///===================================### TRANSPORTADOR ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_TRANSPORTADOR = "TransportadorMap.";

	/** The Constant STMT_FETCH_TRANSPORTADOR. */
	private static final String STMT_FETCH_TRANSPORTADOR = NAMESPACE_TRANSPORTADOR + "fetchTransportadorById";

	/** The Constant STMT_FETCH_TRANSPORTADOR_ALL. */
	private static final String STMT_FETCH_TRANSPORTADOR_ALL = NAMESPACE_TRANSPORTADOR + "fetchAllTransportadors";

	/** The Constant STMT_FETCH_TRANSPORTADOR_COUNT. */
	private static final String STMT_FETCH_TRANSPORTADOR_COUNT = NAMESPACE_TRANSPORTADOR + "fetchTransportadorRowCount";

	/** The Constant STMT_FETCH_TRANSPORTADOR_ALL_REQUEST. */
	private static final String STMT_FETCH_TRANSPORTADOR_ALL_REQUEST = NAMESPACE_TRANSPORTADOR + "fetchAllTransportadorsRequest";

///===================================### MEDICO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_MEDICO = "MedicoMap.";

	/** The Constant STMT_FETCH_MEDICO. */
	private static final String STMT_FETCH_MEDICO = NAMESPACE_MEDICO + "fetchMedicoById";

	/** The Constant STMT_FETCH_MEDICO_ALL. */
	private static final String STMT_FETCH_MEDICO_ALL = NAMESPACE_MEDICO + "fetchAllMedicos";

	/** The Constant STMT_FETCH_MEDICO_COUNT. */
	private static final String STMT_FETCH_MEDICO_COUNT = NAMESPACE_MEDICO + "fetchMedicoRowCount";

	/** The Constant STMT_FETCH_MEDICO_ALL_REQUEST. */
	private static final String STMT_FETCH_MEDICO_ALL_REQUEST = NAMESPACE_MEDICO + "fetchAllMedicosRequest";

///===================================### PACIENTE ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_PACIENTE = "PacienteMap.";

	/** The Constant STMT_FETCH_PACIENTE. */
	private static final String STMT_FETCH_PACIENTE = NAMESPACE_PACIENTE + "fetchPacienteById";

	/** The Constant STMT_FETCH_PACIENTE_ALL. */
	private static final String STMT_FETCH_PACIENTE_ALL = NAMESPACE_PACIENTE + "fetchAllPacientes";

	/** The Constant STMT_FETCH_PACIENTE_COUNT. */
	private static final String STMT_FETCH_PACIENTE_COUNT = NAMESPACE_PACIENTE + "fetchPacienteRowCount";

	/** The Constant STMT_FETCH_PACIENTE_ALL_REQUEST. */
	private static final String STMT_FETCH_PACIENTE_ALL_REQUEST = NAMESPACE_PACIENTE + "fetchAllPacientesRequest";

///===================================### SINDICO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_SINDICO = "SindicoMap.";

	/** The Constant STMT_FETCH_SINDICO. */
	private static final String STMT_FETCH_SINDICO = NAMESPACE_SINDICO + "fetchSindicoById";

	/** The Constant STMT_FETCH_SINDICO_ALL. */
	private static final String STMT_FETCH_SINDICO_ALL = NAMESPACE_SINDICO + "fetchAllSindicos";

	/** The Constant STMT_FETCH_SINDICO_COUNT. */
	private static final String STMT_FETCH_SINDICO_COUNT = NAMESPACE_SINDICO + "fetchSindicoRowCount";

	/** The Constant STMT_FETCH_SINDICO_ALL_REQUEST. */
	private static final String STMT_FETCH_SINDICO_ALL_REQUEST = NAMESPACE_SINDICO + "fetchAllSindicosRequest";

///===================================### INQUILINO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_INQUILINO = "InquilinoMap.";

	/** The Constant STMT_FETCH_INQUILINO. */
	private static final String STMT_FETCH_INQUILINO = NAMESPACE_INQUILINO + "fetchInquilinoById";

	/** The Constant STMT_FETCH_INQUILINO_ALL. */
	private static final String STMT_FETCH_INQUILINO_ALL = NAMESPACE_INQUILINO + "fetchAllInquilinos";

	/** The Constant STMT_FETCH_INQUILINO_COUNT. */
	private static final String STMT_FETCH_INQUILINO_COUNT = NAMESPACE_INQUILINO + "fetchInquilinoRowCount";

	/** The Constant STMT_FETCH_INQUILINO_ALL_REQUEST. */
	private static final String STMT_FETCH_INQUILINO_ALL_REQUEST = NAMESPACE_INQUILINO + "fetchAllInquilinosRequest";

///===================================### FUNCIONARIO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_FUNCIONARIO = "FuncionarioMap.";

	/** The Constant STMT_FETCH_FUNCIONARIO. */
	private static final String STMT_FETCH_FUNCIONARIO = NAMESPACE_FUNCIONARIO + "fetchFuncionarioById";

	/** The Constant STMT_FETCH_FUNCIONARIO_ALL. */
	private static final String STMT_FETCH_FUNCIONARIO_ALL = NAMESPACE_FUNCIONARIO + "fetchAllFuncionarios";

	/** The Constant STMT_FETCH_FUNCIONARIO_COUNT. */
	private static final String STMT_FETCH_FUNCIONARIO_COUNT = NAMESPACE_FUNCIONARIO + "fetchFuncionarioRowCount";

	/** The Constant STMT_FETCH_FUNCIONARIO_ALL_REQUEST. */
	private static final String STMT_FETCH_FUNCIONARIO_ALL_REQUEST = NAMESPACE_FUNCIONARIO + "fetchAllFuncionariosRequest";

//===================================### ADVOGADO ####======================================

	IAdvocaciaBAR advocaciaBAR;
	IDoisValorBAR doisValorBAR;

	IEnderecoBAR enderecoBAR;

	IStatusBAR statusBAR;

	IHistoricoBAR historicoBAR;

	IEmailBAR emailBAR;
	IDocumentoBAR documentoBAR;
	ITelefoneBAR telefoneBAR;
	INotesBAR notesBAR;
	IPessoaBAR pessoaBAR;
	IClinicaBAR clinicaBAR;
	IDpBAR dpBAR;



	public IDocumentoBAR getDocumentoBAR() {
		return documentoBAR;
	}

	public void setDocumentoBAR(IDocumentoBAR documentoBAR) {
		this.documentoBAR = documentoBAR;
	}

	public IEnderecoBAR getEnderecoBAR() {
		return enderecoBAR;
	}

	public void setEnderecoBAR(IEnderecoBAR enderecoBAR) {
		this.enderecoBAR = enderecoBAR;
	}

	public IStatusBAR getStatusBAR() {
		return statusBAR;
	}

	public void setStatusBAR(IStatusBAR statusBAR) {
		this.statusBAR = statusBAR;
	}

	public IHistoricoBAR getHistoricoBAR() {
		return historicoBAR;
	}

	public void setHistoricoBAR(IHistoricoBAR historicoBAR) {
		this.historicoBAR = historicoBAR;
	}

	public IEmailBAR getEmailBAR() {
		return emailBAR;
	}

	public void setEmailBAR(IEmailBAR emailBAR) {
		this.emailBAR = emailBAR;
	}


	public ITelefoneBAR getTelefoneBAR() {
		return telefoneBAR;
	}

	public void setTelefoneBAR(ITelefoneBAR telefoneBAR) {
		this.telefoneBAR = telefoneBAR;
	}

	public INotesBAR getNotesBAR() {
		return notesBAR;
	}

	public void setNotesBAR(INotesBAR notesBAR) {
		this.notesBAR = notesBAR;
	}


	public IPessoaBAR getPessoaBAR() {
		return pessoaBAR;
	}

	public void setPessoaBAR(IPessoaBAR pessoaBAR) {
		this.pessoaBAR = pessoaBAR;
	}



	public IClinicaBAR getClinicaBAR() {
		return clinicaBAR;
	}

	public void setClinicaBAR(IClinicaBAR clinicaBAR) {
		this.clinicaBAR = clinicaBAR;
	}

	public IDpBAR getDpBAR() {
		return dpBAR;
	}

	public void setDpBAR(IDpBAR dpBAR) {
		this.dpBAR = dpBAR;
	}

	public IAdvocaciaBAR getAdvocaciaBAR() {
		return advocaciaBAR;
	}

	public void setAdvocaciaBAR(IAdvocaciaBAR advocaciaBAR) {
		this.advocaciaBAR = advocaciaBAR;
	}

	public IDoisValorBAR getDoisValorBAR() {
		return doisValorBAR;
	}

	public void setDoisValorBAR(IDoisValorBAR doisValorBAR) {
		this.doisValorBAR = doisValorBAR;
	}

	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IAdvogadoBAR#insertAdvogado(com.qat.samples.sysmgmt.base.model.Advogado)
 */
@Override
public InternalResponse insertAdvogado(Advogado advogado)
{

	InternalResponse response = new InternalResponse();

	Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.ADVOGADO, getHistoricoBAR(), response);
	Integer count = 0;
	advogado.setProcessId(historicoId);

	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ADVOGADO, advogado, response);

	Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ADVOGADO, AcaoEnum.INSERT, historicoId,
			getHistoricoBAR(), response, advogado.getId(),advogado.getUserId());

	insertPessoa(advogado, response, TabelaEnum.ADVOGADO, historicoId);

	if (!ValidationUtil.isNullOrEmpty(advogado.getHorasTrabalhos()))
	{
		count +=
				DiasHorasBARD.maintainDiasHorasAssociations(advogado.getHorasTrabalhos(), response, advogado.getId(), null,
						null,
						TabelaEnum.ADVOGADO, getAdvocaciaBAR(), getStatusBAR(), getHistoricoBAR(), advogado.getId(),
						advogado.getCreateUser(), historicoId, historicoId);
	}

	if (!ValidationUtil.isNullOrEmpty(advogado.getEspecialidades()))
	{
		count +=
				DoisValoresParentBARD.maintainDoisValoresParentAssociations(advogado.getEspecialidades(), response, advogado.getId(), null,
						null,
						TabelaEnum.ESPECIALIDADE, getDoisValorBAR(), getStatusBAR(), getHistoricoBAR(), advogado.getId(),
						advogado.getCreateUser(), historicoId, historicoId);
	}

	if (!ValidationUtil.isNullOrEmpty(advogado.getGrupoTrabalho()))
	{
		count +=
				DoisValoresParentBARD.maintainDoisValoresParentAssociations(advogado.getGrupoTrabalho(), response, advogado.getId(), null,
						null,
						TabelaEnum.GRUPOTRABALHO, getDoisValorBAR(), getStatusBAR(), getHistoricoBAR(), advogado.getId(),
						advogado.getCreateUser(), historicoId, historicoId);
	}

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IAdvogadoBAR#updateAdvogado(com.qat.samples.sysmgmt.base.model.Advogado)
 */
@Override
public InternalResponse updateAdvogado(Advogado advogado)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ADVOGADO, advogado, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IAdvogadoBAR#deleteAdvogado(com.qat.samples.sysmgmt.base.model.Advogado)
 */
@Override
public InternalResponse deleteAdvogadoById(Advogado advogado)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ADVOGADO, advogado, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IAdvogadoBAR#deleteAllAdvogados()
 */
@Override
public InternalResponse deleteAllAdvogados()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ADVOGADO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IAdvogadoBAR#fetchAdvogadoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Advogado fetchAdvogadoById(FetchByIdRequest request)
{
return (Advogado)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_ADVOGADO, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IAdvogadoBAR#fetchAllAdvogadosCache()
 */
@Override
public InternalResultsResponse<Advogado> fetchAllAdvogados(Advogado advogado)
{
	InternalResultsResponse<Advogado> response = new InternalResultsResponse<Advogado>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_ADVOGADO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IAdvogadoBAR#fetchAdvogadosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Advogado> fetchAdvogadosByRequest(AdvogadoInquiryRequest request)
{
	InternalResultsResponse<Advogado> response = new InternalResultsResponse<Advogado>();
	fetchAdvogadosByRequest(getSqlSession(), request, STMT_FETCH_ADVOGADO_COUNT, STMT_FETCH_ADVOGADO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchAdvogadosByRequest ####======================================

public static void fetchAdvogadosByRequest(SqlSession sqlSession, AdvogadoInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}


//===================================### CLIENTE ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IClienteBAR#insertCliente(com.qat.samples.sysmgmt.base.model.Cliente)
 */
@Override
public InternalResponse insertCliente(Cliente cliente)
{
	InternalResponse response = new InternalResponse();

	Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.CLIENTE, getHistoricoBAR(), response);

	cliente.setProcessId(historicoId);

	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PESSOA, cliente, response);

	Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.CLIENTE, AcaoEnum.INSERT, historicoId,
			getHistoricoBAR(), response, cliente.getId(),cliente.getUserId());

	insertPessoa(cliente, response, TabelaEnum.CLIENTE, historicoId);

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IClienteBAR#updateCliente(com.qat.samples.sysmgmt.base.model.Cliente)
 */
@Override
public InternalResponse updateCliente(Cliente cliente)
{

	InternalResponse response = new InternalResponse();

	Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.CLIENTE, getHistoricoBAR(), response);

	cliente.setProcessId(historicoId);

	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PESSOA, cliente, response);

	Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.CLIENTE, AcaoEnum.UPDATE, historicoId,
			getHistoricoBAR(), response, cliente.getId(),cliente.getUserId());

	insertPessoa(cliente, response, TabelaEnum.CLIENTE, historicoId);

	return response;

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IClienteBAR#deleteCliente(com.qat.samples.sysmgmt.base.model.Cliente)
 */
@Override
public InternalResponse deleteClienteById(Cliente cliente)
{

	InternalResponse response = new InternalResponse();

	Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.CLIENTE, getHistoricoBAR(), response);

	cliente.setProcessId(historicoId);

	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PESSOA, cliente, response);

	Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.CLIENTE, AcaoEnum.DELETE, historicoId,
			getHistoricoBAR(), response, cliente.getId(),cliente.getUserId());

	insertPessoa(cliente, response, TabelaEnum.CLIENTE, historicoId);

	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IClienteBAR#deleteAllClientes()
 */
@Override
public InternalResponse deleteAllClientes()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PESSOA_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IClienteBAR#fetchClienteById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Cliente fetchClienteById(FetchByIdRequest request)
{
return (Cliente)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CLIENTE, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IClienteBAR#fetchAllClientesCache()
 */
@Override
public InternalResultsResponse<Cliente> fetchAllClientes(Cliente cliente)
{
	InternalResultsResponse<Cliente> response = new InternalResultsResponse<Cliente>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CLIENTE_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IClienteBAR#fetchClientesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Cliente> fetchClientesByRequest(ClienteInquiryRequest request)
{
	InternalResultsResponse<Cliente> response = new InternalResultsResponse<Cliente>();
	if((!ValidationUtil.isNull(request.getSelect())) && (request.getSelect()))
	{
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), "UtilMap.fetchAllCliente"));
	}
	else
	{
		fetchClientesByRequest(getSqlSession(), request, STMT_FETCH_CLIENTE_COUNT, STMT_FETCH_CLIENTE_ALL_REQUEST,
				response);
	}
	return response;
}

//===================================### fetchClientesByRequest ####======================================

public static void fetchClientesByRequest(SqlSession sqlSession, ClienteInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}


//===================================### FORNECEDOR ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFornecedorBAR#insertFornecedor(com.qat.samples.sysmgmt.base.model.Fornecedor)
 */
@Override
public InternalResponse insertFornecedor(Fornecedor fornecedor)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PESSOA, fornecedor, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFornecedorBAR#updateFornecedor(com.qat.samples.sysmgmt.base.model.Fornecedor)
 */
@Override
public InternalResponse updateFornecedor(Fornecedor fornecedor)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PESSOA, fornecedor, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFornecedorBAR#deleteFornecedor(com.qat.samples.sysmgmt.base.model.Fornecedor)
 */
@Override
public InternalResponse deleteFornecedorById(Fornecedor fornecedor)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PESSOA, fornecedor, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFornecedorBAR#deleteAllFornecedors()
 */
@Override
public InternalResponse deleteAllFornecedors()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PESSOA_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IFornecedorBAR#fetchFornecedorById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Fornecedor fetchFornecedorById(FetchByIdRequest request)
{
return (Fornecedor)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_FORNECEDOR, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFornecedorBAR#fetchAllFornecedorsCache()
 */
@Override
public InternalResultsResponse<Fornecedor> fetchAllFornecedors(Fornecedor fornecedor)
{
	InternalResultsResponse<Fornecedor> response = new InternalResultsResponse<Fornecedor>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_FORNECEDOR_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IFornecedorBAR#fetchFornecedorsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Fornecedor> fetchFornecedorsByRequest(FornecedorInquiryRequest request)
{
	InternalResultsResponse<Fornecedor> response = new InternalResultsResponse<Fornecedor>();
	fetchFornecedorsByRequest(getSqlSession(), request, STMT_FETCH_FORNECEDOR_COUNT, STMT_FETCH_FORNECEDOR_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchFornecedorsByRequest ####======================================

public static void fetchFornecedorsByRequest(SqlSession sqlSession, FornecedorInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}


//===================================### TRANSPORTADOR ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITransportadorBAR#insertTransportador(com.qat.samples.sysmgmt.base.model.Transportador)
 */
@Override
public InternalResponse insertTransportador(Transportador transportador)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PESSOA, transportador, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITransportadorBAR#updateTransportador(com.qat.samples.sysmgmt.base.model.Transportador)
 */
@Override
public InternalResponse updateTransportador(Transportador transportador)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PESSOA, transportador, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITransportadorBAR#deleteTransportador(com.qat.samples.sysmgmt.base.model.Transportador)
 */
@Override
public InternalResponse deleteTransportadorById(Transportador transportador)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PESSOA, transportador, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITransportadorBAR#deleteAllTransportadors()
 */
@Override
public InternalResponse deleteAllTransportadors()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PESSOA_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ITransportadorBAR#fetchTransportadorById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Transportador fetchTransportadorById(FetchByIdRequest request)
{
return (Transportador)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_TRANSPORTADOR, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ITransportadorBAR#fetchAllTransportadorsCache()
 */
@Override
public InternalResultsResponse<Transportador> fetchAllTransportadors(Transportador transportador)
{
	InternalResultsResponse<Transportador> response = new InternalResultsResponse<Transportador>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_TRANSPORTADOR_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.ITransportadorBAR#fetchTransportadorsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Transportador> fetchTransportadorsByRequest(TransportadorInquiryRequest request)
{
	InternalResultsResponse<Transportador> response = new InternalResultsResponse<Transportador>();
	fetchTransportadorsByRequest(getSqlSession(), request, STMT_FETCH_TRANSPORTADOR_COUNT, STMT_FETCH_TRANSPORTADOR_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchTransportadorsByRequest ####======================================

public static void fetchTransportadorsByRequest(SqlSession sqlSession, TransportadorInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}


//===================================### MEDICO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMedicoBAR#insertMedico(com.qat.samples.sysmgmt.base.model.Medico)
 */
@Override
public InternalResponse insertMedico(Medico medico)
{
	InternalResponse response = new InternalResponse();

	Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.MEDICO, getHistoricoBAR(), response);

	medico.setProcessId(historicoId);

	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PESSOA, medico, response);

	Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.MEDICO, AcaoEnum.INSERT, historicoId,
			getHistoricoBAR(), response, medico.getId(),medico.getUserId());

	insertPessoa(medico, response, TabelaEnum.MEDICO, historicoId);

	if (!ValidationUtil.isNullOrEmpty(medico.getConsultaList()))
	{
		a +=
				ConsultaBARD.maintainConsultaAssociations(medico.getConsultaList(), response, medico.getId(), null,
						null,
						TabelaEnum.MEDICO, clinicaBAR, statusBAR, historicoBAR, medico.getId(),
						medico.getCreateUser(), historicoId, historicoId);
	}

	if (!ValidationUtil.isNullOrEmpty(medico.getEspecialidadeList()))
	{
		a +=
				DoisValoresParentBARD.maintainDoisValoresParentAssociations(medico.getEspecialidadeList(), response, medico.getId(), null,
						null,
						TabelaEnum.MEDICO, getDoisValorBAR(), getStatusBAR(), getHistoricoBAR(), medico.getId(),
						medico.getCreateUser(), historicoId, historicoId);
	}

	if (!ValidationUtil.isNullOrEmpty(medico.getHorarioList()))
	{
		a +=
				HorarioFuncBARD.maintainHorarioFuncAssociations(medico.getHorarioList(), response, medico.getId(), null,
						null,
						TabelaEnum.MEDICO, dpBAR, statusBAR, historicoBAR, medico.getId(),
						medico.getCreateUser(), historicoId, historicoId);
	}

	return response;

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMedicoBAR#updateMedico(com.qat.samples.sysmgmt.base.model.Medico)
 */
@Override
public InternalResponse updateMedico(Medico medico)
{

	InternalResponse response = new InternalResponse();

	Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.MEDICO, getHistoricoBAR(), response);

	medico.setProcessId(historicoId);

	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PESSOA, medico, response);

	Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.MEDICO, AcaoEnum.UPDATE, historicoId,
			getHistoricoBAR(), response, medico.getId(),medico.getUserId());

	insertPessoa(medico, response, TabelaEnum.MEDICO, historicoId);

	if (!ValidationUtil.isNullOrEmpty(medico.getConsultaList()))
	{
		a +=
				ConsultaBARD.maintainConsultaAssociations(medico.getConsultaList(), response, medico.getId(), null,
						null,
						TabelaEnum.MEDICO, clinicaBAR, statusBAR, historicoBAR, medico.getId(),
						medico.getCreateUser(), historicoId, historicoId);
	}

	if (!ValidationUtil.isNullOrEmpty(medico.getEspecialidadeList()))
	{
		a +=
				DoisValoresParentBARD.maintainDoisValoresParentAssociations(medico.getEspecialidadeList(), response, medico.getId(), null,
						null,
						TabelaEnum.MEDICO, getDoisValorBAR(), getStatusBAR(), getHistoricoBAR(), medico.getId(),
						medico.getCreateUser(), historicoId, historicoId);
	}

	if (!ValidationUtil.isNullOrEmpty(medico.getHorarioList()))
	{
		a +=
				HorarioFuncBARD.maintainHorarioFuncAssociations(medico.getHorarioList(), response, medico.getId(), null,
						null,
						TabelaEnum.MEDICO, dpBAR, statusBAR, historicoBAR, medico.getId(),
						medico.getCreateUser(), historicoId, historicoId);
	}

	return response;

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMedicoBAR#deleteMedico(com.qat.samples.sysmgmt.base.model.Medico)
 */
@Override
public InternalResponse deleteMedicoById(Medico medico)
{

	InternalResponse response = new InternalResponse();

	Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.MEDICO, getHistoricoBAR(), response);

	medico.setProcessId(historicoId);

	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PESSOA, medico, response);

	Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.MEDICO, AcaoEnum.DELETE, historicoId,
			getHistoricoBAR(), response, medico.getId(),medico.getUserId());

	insertPessoa(medico, response, TabelaEnum.MEDICO, historicoId);

//	if (!ValidationUtil.isNullOrEmpty(medico.getConsultaList()))
//	{
//		a +=
//				ConsultaBARD.maintainConsultaAssociations(medico.getConsultaList(), response, medico.getId(), null,
//						null,
//						TabelaEnum.MEDICO, clinicaBAR, statusBAR, historicoBAR, medico.getId(),
//						medico.getCreateUser(), historicoId, historicoId);
//	}
//
//	if (!ValidationUtil.isNullOrEmpty(medico.getEspecialidadeList()))
//	{
//		a +=
//				EspecialidadeBARD.maintainEspecialidadeAssociations(medico.getEspecialidadeList(), response, medico.getId(), null,
//						null,
//						TabelaEnum.MEDICO, clinicaBAR, statusBAR, historicoBAR, medico.getId(),
//						medico.getCreateUser(), historicoId, historicoId);
//	}
//
//	if (!ValidationUtil.isNullOrEmpty(medico.getHorarioList()))
//	{
//		a +=
//				HorarioFuncBARD.maintainHorarioFuncAssociations(medico.getHorarioList(), response, medico.getId(), null,
//						null,
//						TabelaEnum.MEDICO, dpBAR, statusBAR, historicoBAR, medico.getId(),
//						medico.getCreateUser(), historicoId, historicoId);
//	}

	return response;

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMedicoBAR#deleteAllMedicos()
 */
@Override
public InternalResponse deleteAllMedicos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PESSOA_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IMedicoBAR#fetchMedicoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Medico fetchMedicoById(FetchByIdRequest request)
{
return (Medico)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_MEDICO, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMedicoBAR#fetchAllMedicosCache()
 */
@Override
public InternalResultsResponse<Medico> fetchAllMedicos(Medico medico)
{
	InternalResultsResponse<Medico> response = new InternalResultsResponse<Medico>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_MEDICO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IMedicoBAR#fetchMedicosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Medico> fetchMedicosByRequest(MedicoInquiryRequest request)
{
	InternalResultsResponse<Medico> response = new InternalResultsResponse<Medico>();
	fetchMedicosByRequest(getSqlSession(), request, STMT_FETCH_MEDICO_COUNT, STMT_FETCH_MEDICO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchMedicosByRequest ####======================================

public static void fetchMedicosByRequest(SqlSession sqlSession, MedicoInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}


//===================================### PACIENTE ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPacienteBAR#insertPaciente(com.qat.samples.sysmgmt.base.model.Paciente)
 */
@Override
public InternalResponse insertPaciente(Paciente paciente)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PESSOA, paciente, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPacienteBAR#updatePaciente(com.qat.samples.sysmgmt.base.model.Paciente)
 */
@Override
public InternalResponse updatePaciente(Paciente paciente)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PESSOA, paciente, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPacienteBAR#deletePaciente(com.qat.samples.sysmgmt.base.model.Paciente)
 */
@Override
public InternalResponse deletePacienteById(Paciente paciente)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PESSOA, paciente, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPacienteBAR#deleteAllPacientes()
 */
@Override
public InternalResponse deleteAllPacientes()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PESSOA_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IPacienteBAR#fetchPacienteById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Paciente fetchPacienteById(FetchByIdRequest request)
{
return (Paciente)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_PACIENTE, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPacienteBAR#fetchAllPacientesCache()
 */
@Override
public InternalResultsResponse<Paciente> fetchAllPacientes(Paciente paciente)
{
	InternalResultsResponse<Paciente> response = new InternalResultsResponse<Paciente>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_PACIENTE_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IPacienteBAR#fetchPacientesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Paciente> fetchPacientesByRequest(PacienteInquiryRequest request)
{
	InternalResultsResponse<Paciente> response = new InternalResultsResponse<Paciente>();
	fetchPacientesByRequest(getSqlSession(), request, STMT_FETCH_PACIENTE_COUNT, STMT_FETCH_PACIENTE_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchPacientesByRequest ####======================================

public static void fetchPacientesByRequest(SqlSession sqlSession, PacienteInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}


//===================================### SINDICO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISindicoBAR#insertSindico(com.qat.samples.sysmgmt.base.model.Sindico)
 */
@Override
public InternalResponse insertSindico(Sindico sindico)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PESSOA, sindico, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISindicoBAR#updateSindico(com.qat.samples.sysmgmt.base.model.Sindico)
 */
@Override
public InternalResponse updateSindico(Sindico sindico)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PESSOA, sindico, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISindicoBAR#deleteSindico(com.qat.samples.sysmgmt.base.model.Sindico)
 */
@Override
public InternalResponse deleteSindicoById(Sindico sindico)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PESSOA, sindico, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISindicoBAR#deleteAllSindicos()
 */
@Override
public InternalResponse deleteAllSindicos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PESSOA_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.ISindicoBAR#fetchSindicoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Sindico fetchSindicoById(FetchByIdRequest request)
{
return (Sindico)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_SINDICO, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.ISindicoBAR#fetchAllSindicosCache()
 */
@Override
public InternalResultsResponse<Sindico> fetchAllSindicos(Sindico sindico)
{
	InternalResultsResponse<Sindico> response = new InternalResultsResponse<Sindico>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_SINDICO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.ISindicoBAR#fetchSindicosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Sindico> fetchSindicosByRequest(SindicoInquiryRequest request)
{
	InternalResultsResponse<Sindico> response = new InternalResultsResponse<Sindico>();
	fetchSindicosByRequest(getSqlSession(), request, STMT_FETCH_SINDICO_COUNT, STMT_FETCH_SINDICO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchSindicosByRequest ####======================================

public static void fetchSindicosByRequest(SqlSession sqlSession, SindicoInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}


//===================================### INQUILINO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IInquilinoBAR#insertInquilino(com.qat.samples.sysmgmt.base.model.Inquilino)
 */
@Override
public InternalResponse insertInquilino(Inquilino inquilino)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PESSOA, inquilino, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IInquilinoBAR#updateInquilino(com.qat.samples.sysmgmt.base.model.Inquilino)
 */
@Override
public InternalResponse updateInquilino(Inquilino inquilino)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PESSOA, inquilino, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IInquilinoBAR#deleteInquilino(com.qat.samples.sysmgmt.base.model.Inquilino)
 */
@Override
public InternalResponse deleteInquilinoById(Inquilino inquilino)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PESSOA, inquilino, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IInquilinoBAR#deleteAllInquilinos()
 */
@Override
public InternalResponse deleteAllInquilinos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PESSOA_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IInquilinoBAR#fetchInquilinoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Inquilino fetchInquilinoById(FetchByIdRequest request)
{
return (Inquilino)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_INQUILINO, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IInquilinoBAR#fetchAllInquilinosCache()
 */
@Override
public InternalResultsResponse<Inquilino> fetchAllInquilinos(Inquilino inquilino)
{
	InternalResultsResponse<Inquilino> response = new InternalResultsResponse<Inquilino>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_INQUILINO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IInquilinoBAR#fetchInquilinosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Inquilino> fetchInquilinosByRequest(InquilinoInquiryRequest request)
{
	InternalResultsResponse<Inquilino> response = new InternalResultsResponse<Inquilino>();
	fetchInquilinosByRequest(getSqlSession(), request, STMT_FETCH_INQUILINO_COUNT, STMT_FETCH_INQUILINO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchInquilinosByRequest ####======================================

public static void fetchInquilinosByRequest(SqlSession sqlSession, InquilinoInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}


//===================================### FUNCIONARIO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFuncionarioBAR#insertFuncionario(com.qat.samples.sysmgmt.base.model.Funcionario)
 */
@Override
public InternalResponse insertFuncionario(Funcionario funcionario)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PESSOA, funcionario, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFuncionarioBAR#updateFuncionario(com.qat.samples.sysmgmt.base.model.Funcionario)
 */
@Override
public InternalResponse updateFuncionario(Funcionario funcionario)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PESSOA, funcionario, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFuncionarioBAR#deleteFuncionario(com.qat.samples.sysmgmt.base.model.Funcionario)
 */
@Override
public InternalResponse deleteFuncionarioById(Funcionario funcionario)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PESSOA, funcionario, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFuncionarioBAR#deleteAllFuncionarios()
 */
@Override
public InternalResponse deleteAllFuncionarios()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PESSOA_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IFuncionarioBAR#fetchFuncionarioById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Funcionario fetchFuncionarioById(FetchByIdRequest request)
{
return (Funcionario)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_FUNCIONARIO, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFuncionarioBAR#fetchAllFuncionariosCache()
 */
@Override
public InternalResultsResponse<Funcionario> fetchAllFuncionarios(Funcionario funcionario)
{
	InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_FUNCIONARIO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IFuncionarioBAR#fetchFuncionariosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Funcionario> fetchFuncionariosByRequest(FuncionarioInquiryRequest request)
{
	InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();
	fetchFuncionariosByRequest(getSqlSession(), request, STMT_FETCH_FUNCIONARIO_COUNT, STMT_FETCH_FUNCIONARIO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchFuncionariosByRequest ####======================================

public static void fetchFuncionariosByRequest(SqlSession sqlSession, FuncionarioInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	public boolean insertPessoa(Pessoa pessoa, InternalResponse response ,TabelaEnum tabela,Integer historicoId){

		Integer count = 0;

		if (!ValidationUtil.isNullOrEmpty(pessoa.getEnderecos()))
		{
			count +=
					EnderecoBARD.maintainEnderecoAssociations(pessoa.getEnderecos(), response, pessoa.getId(), null,
							null,
							tabela, enderecoBAR, statusBAR, historicoBAR, pessoa.getId(),
							pessoa.getCreateUser(), historicoId, historicoId);
		}

		if (!ValidationUtil.isNullOrEmpty(pessoa.getEmails()))
		{
			count +=
					EmailBARD.maintainEmailAssociations(pessoa.getEmails(), response, pessoa.getId(), null, null,
							tabela, emailBAR, statusBAR, historicoBAR, pessoa.getId(),
							pessoa.getCreateUser(), historicoId, historicoId);
		}
		if (!ValidationUtil.isNullOrEmpty(pessoa.getTelefones()))
		{
			count +=
					TelefoneBARD.maintainTelefoneAssociations(pessoa.getTelefones(), response, pessoa.getId(), null,
							null,
							tabela, telefoneBAR, statusBAR, historicoBAR, pessoa.getId(),
							pessoa.getCreateUser(), historicoId, historicoId);
		}
		if (!ValidationUtil.isNullOrEmpty(pessoa.getDocumentos()))
		{
			count +=
					DocumentosBARD.maintainDocumentoAssociations(pessoa.getDocumentos(), response, pessoa.getId(),
							null,
							null,
							tabela, documentoBAR, statusBAR, historicoBAR, pessoa.getId(),
							pessoa.getCreateUser(), historicoId, historicoId);
		}
		if (!ValidationUtil.isNullOrEmpty(pessoa.getNotes()))
		{
			count +=
					NotesBARD.maintainNoteAssociations(pessoa.getNotes(), response, pessoa.getId(), null,
							null,
							tabela, getNotesBAR(), statusBAR, historicoBAR, pessoa.getEmprId(),
							pessoa.getCreateUser(), historicoId, historicoId);
		}

		if (!ValidationUtil.isNullOrEmpty(pessoa.getPessoaTipo()))
		{
			count +=
					PessoaTipoBARD.maintainPessoaTipoAssociations(pessoa.getPessoaTipo(), response, pessoa.getId(), null,
							null,
							tabela, getPessoaBAR(), statusBAR, historicoBAR, pessoa.getEmprId(),
							pessoa.getCreateUser(), historicoId, historicoId);
		}


		if (count > 0)
		{
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ANALISANDO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count = Boolean.hashCode(
					StatusBARD.maintainStatusAssociations(statusList, response, pessoa.getId(), null, AcaoEnum.INSERT,
							pessoa.getCreateUser(), pessoa.getId(), tabela, statusBAR,
							historicoBAR, historicoId, historicoId));

		}

		return true;
	}

	//===================================### TIPO PESSOA ####======================================
		/**
	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IFuncionarioBAR#insertFuncionario(com.qat.samples.sysmgmt.base.model.Funcionario)
	 */
	@Override
	public InternalResponse insertTipoPessoa(PessoaTipo funcionario)
	{
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), "PessoaMap.insertPessoaTipo", funcionario, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IFuncionarioBAR#updateFuncionario(com.qat.samples.sysmgmt.base.model.Funcionario)
	 */
	@Override
	public InternalResponse updateTipoPessoa(PessoaTipo funcionario)
	{
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), "PessoaMap.updatePessoaTipo", funcionario, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IFuncionarioBAR#deleteFuncionario(com.qat.samples.sysmgmt.base.model.Funcionario)
	 */
	@Override
	public InternalResponse deleteTipoPessoaById(PessoaTipo funcionario)
	{
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), "PessoaMap.deletePessoaTipoById", funcionario, response);
		return response;
	}
}
