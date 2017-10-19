/** create by system gera-java version 1.0.0 15/09/2017 11:19 : 10*/
package com.qat.samples.sysmgmt.bar.mybatis.Advogado;

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
import com.qat.samples.sysmgmt.advocacia.Advogados;
import com.qat.samples.sysmgmt.advocacia.Envolvidos;
import com.qat.samples.sysmgmt.advocacia.Processo;
import com.qat.samples.sysmgmt.advocacia.ProcessoCliente;
import com.qat.samples.sysmgmt.advocacia.ProcessoStatus;
import com.qat.samples.sysmgmt.advocacia.ProcessoUsuarios;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoInquiryRequest;
import com.qat.samples.sysmgmt.arquivo.model.Arquivo;
import com.qat.samples.sysmgmt.bar.Advogado.IAdvocaciaBAR;
import com.qat.samples.sysmgmt.bar.Email.IEmailBAR;
import com.qat.samples.sysmgmt.bar.Financeiro.IFinanceiroBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.bar.Telefone.ITelefoneBAR;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.ArquivoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.ClienteCompromissoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.CompromissoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.ContasReceberBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.EmailBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.EnvolvidosBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.InsertHistBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.ParticipanteExternoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.ProcessoClienteBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.ProcessoStatusBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.ProcessoUsuariosBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.StatusBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.TelefoneBARD;
import com.qat.samples.sysmgmt.clinica.model.Especialidade;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.ClienteCompromisso;
import com.qat.samples.sysmgmt.util.model.Compromisso;
import com.qat.samples.sysmgmt.util.model.DiasHoras;
import com.qat.samples.sysmgmt.util.model.ParticipanteExterno;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class AdvocaciaBARImpl extends SqlSessionDaoSupport implements IAdvocaciaBAR {

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/// ===================================### PROCESSOSTATUS
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_PROCESSOSTATUS = "ProcessoStatusMap.";

	/** The Constant STMT_INSERT_PROCESSOSTATUS. */
	private static final String STMT_INSERT_PROCESSOSTATUS = NAMESPACE_PROCESSOSTATUS + "insertProcessoStatus";

	/** The Constant STMT_UPDATE_PROCESSOSTATUS. */
	private static final String STMT_UPDATE_PROCESSOSTATUS = NAMESPACE_PROCESSOSTATUS + "updateProcessoStatus";

	/** The Constant STMT_DELETE_PROCESSOSTATUS. */
	private static final String STMT_DELETE_PROCESSOSTATUS = NAMESPACE_PROCESSOSTATUS + "deleteProcessoStatusById";

	/** The Constant STMT_DELETE_PROCESSOSTATUS_ALL. */
	private static final String STMT_DELETE_PROCESSOSTATUS_ALL = NAMESPACE_PROCESSOSTATUS + "deleteAllProcessoStatuss";

	/** The Constant STMT_FETCH_PROCESSOSTATUS. */
	private static final String STMT_FETCH_PROCESSOSTATUS = NAMESPACE_PROCESSOSTATUS + "fetchProcessoStatusById";

	/** The Constant STMT_FETCH_PROCESSOSTATUS_ALL. */
	private static final String STMT_FETCH_PROCESSOSTATUS_ALL = NAMESPACE_PROCESSOSTATUS + "fetchAllProcessoStatuss";

	/** The Constant STMT_FETCH_PROCESSOSTATUS_COUNT. */
	private static final String STMT_FETCH_PROCESSOSTATUS_COUNT = NAMESPACE_PROCESSOSTATUS
			+ "fetchProcessoStatusRowCount";

	/** The Constant STMT_FETCH_PROCESSOSTATUS_ALL_REQUEST. */
	private static final String STMT_FETCH_PROCESSOSTATUS_ALL_REQUEST = NAMESPACE_PROCESSOSTATUS
			+ "fetchAllProcessoStatussRequest";

	/// ===================================### DIASHORAS
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_DIASHORAS = "DiasHorasMap.";

	/** The Constant STMT_INSERT_DIASHORAS. */
	private static final String STMT_INSERT_DIASHORAS = NAMESPACE_DIASHORAS + "insertDiasHoras";

	/** The Constant STMT_UPDATE_DIASHORAS. */
	private static final String STMT_UPDATE_DIASHORAS = NAMESPACE_DIASHORAS + "updateDiasHoras";

	/** The Constant STMT_DELETE_DIASHORAS. */
	private static final String STMT_DELETE_DIASHORAS = NAMESPACE_DIASHORAS + "deleteDiasHorasById";

	/** The Constant STMT_DELETE_DIASHORAS_ALL. */
	private static final String STMT_DELETE_DIASHORAS_ALL = NAMESPACE_DIASHORAS + "deleteAllDiasHorass";

	/** The Constant STMT_FETCH_DIASHORAS. */
	private static final String STMT_FETCH_DIASHORAS = NAMESPACE_DIASHORAS + "fetchDiasHorasById";

	/** The Constant STMT_FETCH_DIASHORAS_ALL. */
	private static final String STMT_FETCH_DIASHORAS_ALL = NAMESPACE_DIASHORAS + "fetchAllDiasHorass";

	/** The Constant STMT_FETCH_DIASHORAS_COUNT. */
	private static final String STMT_FETCH_DIASHORAS_COUNT = NAMESPACE_DIASHORAS + "fetchDiasHorasRowCount";

	/** The Constant STMT_FETCH_DIASHORAS_ALL_REQUEST. */
	private static final String STMT_FETCH_DIASHORAS_ALL_REQUEST = NAMESPACE_DIASHORAS + "fetchAllDiasHorassRequest";

	/// ===================================### ESPECIALIDADE
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_ESPECIALIDADE = "EspecialidadeMap.";

	/** The Constant STMT_INSERT_ESPECIALIDADE. */
	private static final String STMT_INSERT_ESPECIALIDADE = NAMESPACE_ESPECIALIDADE + "insertEspecialidade";

	/** The Constant STMT_UPDATE_ESPECIALIDADE. */
	private static final String STMT_UPDATE_ESPECIALIDADE = NAMESPACE_ESPECIALIDADE + "updateEspecialidade";

	/** The Constant STMT_DELETE_ESPECIALIDADE. */
	private static final String STMT_DELETE_ESPECIALIDADE = NAMESPACE_ESPECIALIDADE + "deleteEspecialidadeById";

	/** The Constant STMT_DELETE_ESPECIALIDADE_ALL. */
	private static final String STMT_DELETE_ESPECIALIDADE_ALL = NAMESPACE_ESPECIALIDADE + "deleteAllEspecialidades";

	/** The Constant STMT_FETCH_ESPECIALIDADE. */
	private static final String STMT_FETCH_ESPECIALIDADE = NAMESPACE_ESPECIALIDADE + "fetchEspecialidadeById";

	/** The Constant STMT_FETCH_ESPECIALIDADE_ALL. */
	private static final String STMT_FETCH_ESPECIALIDADE_ALL = NAMESPACE_ESPECIALIDADE + "fetchAllEspecialidades";

	/** The Constant STMT_FETCH_ESPECIALIDADE_COUNT. */
	private static final String STMT_FETCH_ESPECIALIDADE_COUNT = NAMESPACE_ESPECIALIDADE + "fetchEspecialidadeRowCount";

	/** The Constant STMT_FETCH_ESPECIALIDADE_ALL_REQUEST. */
	private static final String STMT_FETCH_ESPECIALIDADE_ALL_REQUEST = NAMESPACE_ESPECIALIDADE
			+ "fetchAllEspecialidadesRequest";

	/// ===================================### COMPROMISSO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_COMPROMISSO = "CompromissoMap.";

	/** The Constant STMT_INSERT_COMPROMISSO. */
	private static final String STMT_INSERT_COMPROMISSO = NAMESPACE_COMPROMISSO + "insertCompromisso";

	/** The Constant STMT_UPDATE_COMPROMISSO. */
	private static final String STMT_UPDATE_COMPROMISSO = NAMESPACE_COMPROMISSO + "updateCompromisso";

	/** The Constant STMT_DELETE_COMPROMISSO. */
	private static final String STMT_DELETE_COMPROMISSO = NAMESPACE_COMPROMISSO + "deleteCompromissoById";

	/** The Constant STMT_DELETE_COMPROMISSO_ALL. */
	private static final String STMT_DELETE_COMPROMISSO_ALL = NAMESPACE_COMPROMISSO + "deleteAllCompromissos";

	/** The Constant STMT_FETCH_COMPROMISSO. */
	private static final String STMT_FETCH_COMPROMISSO = NAMESPACE_COMPROMISSO + "fetchCompromissoById";

	/** The Constant STMT_FETCH_COMPROMISSO_ALL. */
	private static final String STMT_FETCH_COMPROMISSO_ALL = NAMESPACE_COMPROMISSO + "fetchAllCompromissos";

	/** The Constant STMT_FETCH_COMPROMISSO_COUNT. */
	private static final String STMT_FETCH_COMPROMISSO_COUNT = NAMESPACE_COMPROMISSO + "fetchCompromissoRowCount";

	/** The Constant STMT_FETCH_COMPROMISSO_ALL_REQUEST. */
	private static final String STMT_FETCH_COMPROMISSO_ALL_REQUEST = NAMESPACE_COMPROMISSO
			+ "fetchAllCompromissosRequest";

	/// ===================================### ADVOGADOS
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_ADVOGADOS = "AdvogadosMap.";

	/** The Constant STMT_INSERT_ADVOGADOS. */
	private static final String STMT_INSERT_ADVOGADOS = NAMESPACE_ADVOGADOS + "insertAdvogados";

	/** The Constant STMT_UPDATE_ADVOGADOS. */
	private static final String STMT_UPDATE_ADVOGADOS = NAMESPACE_ADVOGADOS + "updateAdvogados";

	/** The Constant STMT_DELETE_ADVOGADOS. */
	private static final String STMT_DELETE_ADVOGADOS = NAMESPACE_ADVOGADOS + "deleteAdvogadosById";

	/** The Constant STMT_DELETE_ADVOGADOS_ALL. */
	private static final String STMT_DELETE_ADVOGADOS_ALL = NAMESPACE_ADVOGADOS + "deleteAllAdvogadoss";

	/** The Constant STMT_FETCH_ADVOGADOS. */
	private static final String STMT_FETCH_ADVOGADOS = NAMESPACE_ADVOGADOS + "fetchAdvogadosById";

	/** The Constant STMT_FETCH_ADVOGADOS_ALL. */
	private static final String STMT_FETCH_ADVOGADOS_ALL = NAMESPACE_ADVOGADOS + "fetchAllAdvogadoss";

	/** The Constant STMT_FETCH_ADVOGADOS_COUNT. */
	private static final String STMT_FETCH_ADVOGADOS_COUNT = NAMESPACE_ADVOGADOS + "fetchAdvogadosRowCount";

	/** The Constant STMT_FETCH_ADVOGADOS_ALL_REQUEST. */
	private static final String STMT_FETCH_ADVOGADOS_ALL_REQUEST = NAMESPACE_ADVOGADOS + "fetchAllAdvogadossRequest";

	/// ===================================### ENVOLVIDOS
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_ENVOLVIDOS = "EnvolvidosMap.";

	/** The Constant STMT_INSERT_ENVOLVIDOS. */
	private static final String STMT_INSERT_ENVOLVIDOS = NAMESPACE_ENVOLVIDOS + "insertEnvolvidos";

	/** The Constant STMT_UPDATE_ENVOLVIDOS. */
	private static final String STMT_UPDATE_ENVOLVIDOS = NAMESPACE_ENVOLVIDOS + "updateEnvolvidos";

	/** The Constant STMT_DELETE_ENVOLVIDOS. */
	private static final String STMT_DELETE_ENVOLVIDOS = NAMESPACE_ENVOLVIDOS + "deleteEnvolvidosById";

	/** The Constant STMT_DELETE_ENVOLVIDOS_ALL. */
	private static final String STMT_DELETE_ENVOLVIDOS_ALL = NAMESPACE_ENVOLVIDOS + "deleteAllEnvolvidoss";

	/** The Constant STMT_FETCH_ENVOLVIDOS. */
	private static final String STMT_FETCH_ENVOLVIDOS = NAMESPACE_ENVOLVIDOS + "fetchEnvolvidosById";

	/** The Constant STMT_FETCH_ENVOLVIDOS_ALL. */
	private static final String STMT_FETCH_ENVOLVIDOS_ALL = NAMESPACE_ENVOLVIDOS + "fetchAllEnvolvidoss";

	/** The Constant STMT_FETCH_ENVOLVIDOS_COUNT. */
	private static final String STMT_FETCH_ENVOLVIDOS_COUNT = NAMESPACE_ENVOLVIDOS + "fetchEnvolvidosRowCount";

	/** The Constant STMT_FETCH_ENVOLVIDOS_ALL_REQUEST. */
	private static final String STMT_FETCH_ENVOLVIDOS_ALL_REQUEST = NAMESPACE_ENVOLVIDOS + "fetchAllEnvolvidossRequest";

	/// ===================================### PARTICIPANTE
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_PARTICIPANTE = "ParticipanteExternoMap.";

	/** The Constant STMT_INSERT_PARTICIPANTE. */
	private static final String STMT_INSERT_PARTICIPANTE = NAMESPACE_PARTICIPANTE + "insertParticipanteExterno";

	/** The Constant STMT_UPDATE_PARTICIPANTE. */
	private static final String STMT_UPDATE_PARTICIPANTE = NAMESPACE_PARTICIPANTE + "updateParticipanteExterno";

	/** The Constant STMT_DELETE_PARTICIPANTE. */
	private static final String STMT_DELETE_PARTICIPANTE = NAMESPACE_PARTICIPANTE + "deleteParticipanteExternoById";

	/** The Constant STMT_DELETE_PARTICIPANTE_ALL. */
	private static final String STMT_DELETE_PARTICIPANTE_ALL = NAMESPACE_PARTICIPANTE + "deleteAllParticipanteExternos";

	/** The Constant STMT_FETCH_PARTICIPANTE. */
	private static final String STMT_FETCH_PARTICIPANTE = NAMESPACE_PARTICIPANTE + "fetchParticipanteExternoById";

	/** The Constant STMT_FETCH_PARTICIPANTE_ALL. */
	private static final String STMT_FETCH_PARTICIPANTE_ALL = NAMESPACE_PARTICIPANTE + "fetchAllParticipanteExternos";

	/** The Constant STMT_FETCH_PARTICIPANTE_COUNT. */
	private static final String STMT_FETCH_PARTICIPANTE_COUNT = NAMESPACE_PARTICIPANTE
			+ "fetchParticipanteExternoRowCount";

	/** The Constant STMT_FETCH_PARTICIPANTE_ALL_REQUEST. */
	private static final String STMT_FETCH_PARTICIPANTE_ALL_REQUEST = NAMESPACE_PARTICIPANTE
			+ "fetchAllParticipanteExternosRequest";

	/// ===================================### PROCESSO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_PROCESSO = "ProcessoMap.";

	/** The Constant STMT_INSERT_PROCESSO. */
	private static final String STMT_INSERT_PROCESSO = NAMESPACE_PROCESSO + "insertProcesso";

	/** The Constant STMT_UPDATE_PROCESSO. */
	private static final String STMT_UPDATE_PROCESSO = NAMESPACE_PROCESSO + "updateProcesso";

	/** The Constant STMT_DELETE_PROCESSO. */
	private static final String STMT_DELETE_PROCESSO = NAMESPACE_PROCESSO + "deleteProcessoById";

	/** The Constant STMT_DELETE_PROCESSO_ALL. */
	private static final String STMT_DELETE_PROCESSO_ALL = NAMESPACE_PROCESSO + "deleteAllProcessos";

	/** The Constant STMT_FETCH_PROCESSO. */
	private static final String STMT_FETCH_PROCESSO = NAMESPACE_PROCESSO + "fetchProcessoById";

	/** The Constant STMT_FETCH_PROCESSO_ALL. */
	private static final String STMT_FETCH_PROCESSO_ALL = NAMESPACE_PROCESSO + "fetchAllProcessos";

	/** The Constant STMT_FETCH_PROCESSO_COUNT. */
	private static final String STMT_FETCH_PROCESSO_COUNT = NAMESPACE_PROCESSO + "fetchProcessoRowCount";

	/** The Constant STMT_FETCH_PROCESSO_ALL_REQUEST. */
	private static final String STMT_FETCH_PROCESSO_ALL_REQUEST = NAMESPACE_PROCESSO + "fetchAllProcessosRequest";

	/// ===================================### CLIENTECOMPROMISSO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_CLIENTECOMPROMISSO = "ClienteCompromissoMap.";

	/** The Constant STMT_INSERT_CLIENTECOMPROMISSO. */
	private static final String STMT_INSERT_CLIENTECOMPROMISSO = NAMESPACE_CLIENTECOMPROMISSO
			+ "insertClienteCompromisso";

	/** The Constant STMT_UPDATE_CLIENTECOMPROMISSO. */
	private static final String STMT_UPDATE_CLIENTECOMPROMISSO = NAMESPACE_CLIENTECOMPROMISSO
			+ "updateClienteCompromisso";

	/** The Constant STMT_DELETE_CLIENTECOMPROMISSO. */
	private static final String STMT_DELETE_CLIENTECOMPROMISSO = NAMESPACE_CLIENTECOMPROMISSO
			+ "deleteClienteCompromissoById";

	/** The Constant STMT_DELETE_CLIENTECOMPROMISSO_ALL. */
	private static final String STMT_DELETE_CLIENTECOMPROMISSO_ALL = NAMESPACE_CLIENTECOMPROMISSO
			+ "deleteAllClienteCompromissos";

	/** The Constant STMT_FETCH_CLIENTECOMPROMISSO. */
	private static final String STMT_FETCH_CLIENTECOMPROMISSO = NAMESPACE_CLIENTECOMPROMISSO
			+ "fetchClienteCompromissoById";

	/** The Constant STMT_FETCH_CLIENTECOMPROMISSO_ALL. */
	private static final String STMT_FETCH_CLIENTECOMPROMISSO_ALL = NAMESPACE_CLIENTECOMPROMISSO
			+ "fetchAllClienteCompromissos";

	/** The Constant STMT_FETCH_CLIENTECOMPROMISSO_COUNT. */
	private static final String STMT_FETCH_CLIENTECOMPROMISSO_COUNT = NAMESPACE_CLIENTECOMPROMISSO
			+ "fetchClienteCompromissoRowCount";

	/** The Constant STMT_FETCH_CLIENTECOMPROMISSO_ALL_REQUEST. */
	private static final String STMT_FETCH_CLIENTECOMPROMISSO_ALL_REQUEST = NAMESPACE_CLIENTECOMPROMISSO
			+ "fetchAllClienteCompromissosRequest";

	/// ===================================### ARQUIVO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_ARQUIVO = "ArquivoMap.";

	/** The Constant STMT_INSERT_ARQUIVO. */
	private static final String STMT_INSERT_ARQUIVO = NAMESPACE_ARQUIVO + "insertArquivo";

	/** The Constant STMT_UPDATE_ARQUIVO. */
	private static final String STMT_UPDATE_ARQUIVO = NAMESPACE_ARQUIVO + "updateArquivo";

	/** The Constant STMT_DELETE_ARQUIVO. */
	private static final String STMT_DELETE_ARQUIVO = NAMESPACE_ARQUIVO + "deleteArquivoById";

	/** The Constant STMT_DELETE_ARQUIVO_ALL. */
	private static final String STMT_DELETE_ARQUIVO_ALL = NAMESPACE_ARQUIVO + "deleteAllArquivos";

	/** The Constant STMT_FETCH_ARQUIVO. */
	private static final String STMT_FETCH_ARQUIVO = NAMESPACE_ARQUIVO + "fetchArquivoById";

	/** The Constant STMT_FETCH_ARQUIVO_ALL. */
	private static final String STMT_FETCH_ARQUIVO_ALL = NAMESPACE_ARQUIVO + "fetchAllArquivos";

	/** The Constant STMT_FETCH_ARQUIVO_COUNT. */
	private static final String STMT_FETCH_ARQUIVO_COUNT = NAMESPACE_ARQUIVO + "fetchArquivoRowCount";

	/** The Constant STMT_FETCH_ARQUIVO_ALL_REQUEST. */
	private static final String STMT_FETCH_ARQUIVO_ALL_REQUEST = NAMESPACE_ARQUIVO + "fetchAllArquivosRequest";

	///===================================### PROCESSOUSUARIOS ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_PROCESSOUSUARIOS = "ProcessoUsuariosMap.";

	/** The Constant STMT_INSERT_PROCESSOUSUARIOS. */
	private static final String STMT_INSERT_PROCESSOUSUARIOS = NAMESPACE_PROCESSOUSUARIOS + "insertProcessoUsuarios";

	/** The Constant STMT_UPDATE_PROCESSOUSUARIOS. */
	private static final String STMT_UPDATE_PROCESSOUSUARIOS = NAMESPACE_PROCESSOUSUARIOS + "updateProcessoUsuarios";

	/** The Constant STMT_DELETE_PROCESSOUSUARIOS. */
	private static final String STMT_DELETE_PROCESSOUSUARIOS = NAMESPACE_PROCESSOUSUARIOS + "deleteProcessoUsuariosById";

		/** The Constant STMT_DELETE_PROCESSOUSUARIOS_ALL. */
		private static final String STMT_DELETE_PROCESSOUSUARIOS_ALL = NAMESPACE_PROCESSOUSUARIOS + "deleteAllProcessoUsuarioss";

		/** The Constant STMT_FETCH_PROCESSOUSUARIOS. */
		private static final String STMT_FETCH_PROCESSOUSUARIOS = NAMESPACE_PROCESSOUSUARIOS + "fetchProcessoUsuariosById";

		/** The Constant STMT_FETCH_PROCESSOUSUARIOS_ALL. */
		private static final String STMT_FETCH_PROCESSOUSUARIOS_ALL = NAMESPACE_PROCESSOUSUARIOS + "fetchAllProcessoUsuarioss";

		/** The Constant STMT_FETCH_PROCESSOUSUARIOS_COUNT. */
		private static final String STMT_FETCH_PROCESSOUSUARIOS_COUNT = NAMESPACE_PROCESSOUSUARIOS + "fetchProcessoUsuariosRowCount";

		/** The Constant STMT_FETCH_PROCESSOUSUARIOS_ALL_REQUEST. */
		private static final String STMT_FETCH_PROCESSOUSUARIOS_ALL_REQUEST = NAMESPACE_PROCESSOUSUARIOS + "fetchAllProcessoUsuariossRequest";


		///===================================### PROCESSOCLIENTE ####======================================
		/** The Constant NAMESPACE. */
		private static final String NAMESPACE_PROCESSOCLIENTE = "ProcessoClienteMap.";

		/** The Constant STMT_INSERT_PROCESSOCLIENTE. */
		private static final String STMT_INSERT_PROCESSOCLIENTE = NAMESPACE_PROCESSOCLIENTE + "insertProcessoCliente";

		/** The Constant STMT_UPDATE_PROCESSOCLIENTE. */
		private static final String STMT_UPDATE_PROCESSOCLIENTE = NAMESPACE_PROCESSOCLIENTE + "updateProcessoCliente";

		/** The Constant STMT_DELETE_PROCESSOCLIENTE. */
		private static final String STMT_DELETE_PROCESSOCLIENTE = NAMESPACE_PROCESSOCLIENTE + "deleteProcessoClienteById";

			/** The Constant STMT_DELETE_PROCESSOCLIENTE_ALL. */
			private static final String STMT_DELETE_PROCESSOCLIENTE_ALL = NAMESPACE_PROCESSOCLIENTE + "deleteAllProcessoClientes";

			/** The Constant STMT_FETCH_PROCESSOCLIENTE. */
			private static final String STMT_FETCH_PROCESSOCLIENTE = NAMESPACE_PROCESSOCLIENTE + "fetchProcessoClienteById";

			/** The Constant STMT_FETCH_PROCESSOCLIENTE_ALL. */
			private static final String STMT_FETCH_PROCESSOCLIENTE_ALL = NAMESPACE_PROCESSOCLIENTE + "fetchAllProcessoClientes";

			/** The Constant STMT_FETCH_PROCESSOCLIENTE_COUNT. */
			private static final String STMT_FETCH_PROCESSOCLIENTE_COUNT = NAMESPACE_PROCESSOCLIENTE + "fetchProcessoClienteRowCount";

			/** The Constant STMT_FETCH_PROCESSOCLIENTE_ALL_REQUEST. */
			private static final String STMT_FETCH_PROCESSOCLIENTE_ALL_REQUEST = NAMESPACE_PROCESSOCLIENTE + "fetchAllProcessoClientesRequest";

	/** The status BAR. */
	IStatusBAR statusBAR;

	/** The historico BAR. */
	IHistoricoBAR historicoBAR;

	/** The advocacia BAR. */
	IAdvocaciaBAR advocaciaBAR;

	/** The financeiro BAR. */
	IFinanceiroBAR financeiroBAR;

	/** The email BAR. */
	IEmailBAR emailBAR;

	/** The telefone BAR. */
	ITelefoneBAR telefoneBAR;

	/**
	 * Gets the status BAR.
	 *
	 * @return the status BAR
	 */
	public IStatusBAR getStatusBAR() {
		return statusBAR;
	}

	/**
	 * Sets the status BAR.
	 *
	 * @param statusBAR
	 *            the new status BAR
	 */
	public void setStatusBAR(IStatusBAR statusBAR) {
		this.statusBAR = statusBAR;
	}

	/**
	 * Gets the historico BAR.
	 *
	 * @return the historico BAR
	 */
	public IHistoricoBAR getHistoricoBAR() {
		return historicoBAR;
	}

	/**
	 * Sets the historico BAR.
	 *
	 * @param historicoBAR
	 *            the new historico BAR
	 */
	public void setHistoricoBAR(IHistoricoBAR historicoBAR) {
		this.historicoBAR = historicoBAR;
	}

	/**
	 * Gets the advocacia BAR.
	 *
	 * @return the advocacia BAR
	 */
	public IAdvocaciaBAR getAdvocaciaBAR() {
		return advocaciaBAR;
	}

	/**
	 * Gets the financeiro BAR.
	 *
	 * @return the financeiro BAR
	 */
	public IFinanceiroBAR getFinanceiroBAR() {
		return financeiroBAR;
	}

	/**
	 * Sets the financeiro BAR.
	 *
	 * @param financeiroBAR
	 *            the new financeiro BAR
	 */
	public void setFinanceiroBAR(IFinanceiroBAR financeiroBAR) {
		this.financeiroBAR = financeiroBAR;
	}

	/**
	 * Sets the advocacia BAR.
	 *
	 * @param advocaciaBAR
	 *            the new advocacia BAR
	 */
	public void setAdvocaciaBAR(IAdvocaciaBAR advocaciaBAR) {
		this.advocaciaBAR = advocaciaBAR;
	}

	/**
	 * Gets the email BAR.
	 *
	 * @return the email BAR
	 */
	public IEmailBAR getEmailBAR() {
		return emailBAR;
	}

	/**
	 * Sets the email BAR.
	 *
	 * @param emailBAR the new email BAR
	 */
	public void setEmailBAR(IEmailBAR emailBAR) {
		this.emailBAR = emailBAR;
	}

	/**
	 * Gets the telefone BAR.
	 *
	 * @return the telefone BAR
	 */
	public ITelefoneBAR getTelefoneBAR() {
		return telefoneBAR;
	}

	/**
	 * Sets the telefone BAR.
	 *
	 * @param telefoneBAR the new telefone BAR
	 */
	public void setTelefoneBAR(ITelefoneBAR telefoneBAR) {
		this.telefoneBAR = telefoneBAR;
	}

	// ===================================### PROCESSOSTATUS
	// ####======================================
	/**
	 * /* (non-Javadoc).
	 *
	 * @param processostatus
	 *            the processostatus
	 * @return the internal response
	 * @see com.qat.samples.sysmgmt.base.bar.IProcessoStatusBAR#insertProcessoStatus(com.qat.samples.sysmgmt.base.model.ProcessoStatus)
	 */
	@Override
	public InternalResponse insertProcessoStatus(ProcessoStatus processostatus) {
		InternalResponse response = new InternalResponse();

		processostatus.setProcessId(processostatus.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PROCESSOSTATUS, processostatus, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PROCESSOSTATUS, AcaoEnum.INSERT,
				processostatus.getTransactionId(), getHistoricoBAR(), response, processostatus.getId(),
				processostatus.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IProcessoStatusBAR#updateProcessoStatus(
	 * com.qat.samples.sysmgmt.base.model.ProcessoStatus)
	 */
	@Override
	public InternalResponse updateProcessoStatus(ProcessoStatus processostatus) {
		InternalResponse response = new InternalResponse();
		processostatus.setProcessId(processostatus.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PROCESSOSTATUS, processostatus, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PROCESSOSTATUS, AcaoEnum.UPDATE,
				processostatus.getTransactionId(), getHistoricoBAR(), response, processostatus.getId(),
				processostatus.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IProcessoStatusBAR#deleteProcessoStatus(
	 * com.qat.samples.sysmgmt.base.model.ProcessoStatus)
	 */
	@Override
	public InternalResponse deleteProcessoStatusById(ProcessoStatus processostatus) {
		InternalResponse response = new InternalResponse();
		processostatus.setProcessId(processostatus.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PROCESSOSTATUS, processostatus, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PROCESSOSTATUS, AcaoEnum.DELETE,
				processostatus.getTransactionId(), getHistoricoBAR(), response, processostatus.getId(),
				processostatus.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IProcessoStatusBAR#
	 * deleteAllProcessoStatuss()
	 */
	@Override
	public InternalResponse deleteAllProcessoStatuss() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PROCESSOSTATUS_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IProcessoStatusBAR#fetchProcessoStatusById(
	 * com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public ProcessoStatus fetchProcessoStatusById(FetchByIdRequest request) {
		return (ProcessoStatus) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_PROCESSOSTATUS,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IProcessoStatusBAR#
	 * fetchAllProcessoStatussCache()
	 */
	@Override
	public InternalResultsResponse<ProcessoStatus> fetchAllProcessoStatuss(ProcessoStatus processostatus) {
		InternalResultsResponse<ProcessoStatus> response = new InternalResultsResponse<ProcessoStatus>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_PROCESSOSTATUS_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IProcessoStatusBAR#
	 * fetchProcessoStatussByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<ProcessoStatus> fetchProcessoStatussByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<ProcessoStatus> response = new InternalResultsResponse<ProcessoStatus>();
		fetchProcessoStatussByRequest(getSqlSession(), request, STMT_FETCH_PROCESSOSTATUS_COUNT,
				STMT_FETCH_PROCESSOSTATUS_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchProcessoStatussByRequest
	// ####======================================

	/**
	 * Fetch processo statuss by request.
	 *
	 * @param sqlSession
	 *            the sql session
	 * @param request
	 *            the request
	 * @param countStatement
	 *            the count statement
	 * @param fetchPagedStatement
	 *            the fetch paged statement
	 * @param response
	 *            the response
	 */
	public static void fetchProcessoStatussByRequest(SqlSession sqlSession, PagedInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### DIASHORAS
	// ####======================================
	/**
	 * /* (non-Javadoc).
	 *
	 * @param diashoras
	 *            the diashoras
	 * @return the internal response
	 * @see com.qat.samples.sysmgmt.base.bar.IDiasHorasBAR#insertDiasHoras(com.qat.samples.sysmgmt.base.model.DiasHoras)
	 */
	@Override
	public InternalResponse insertDiasHoras(DiasHoras diashoras) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		diashoras.setProcessId(diashoras.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_DIASHORAS, diashoras, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.DIASHORAS, AcaoEnum.INSERT,
				diashoras.getTransactionId(), getHistoricoBAR(), response, diashoras.getId(), diashoras.getUserId());

		if (diashoras.getId() != 0 && diashoras.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, diashoras.getId(), null,
					AcaoEnum.INSERT, diashoras.getCreateUser(), diashoras.getId(), TabelaEnum.DIASHORAS, statusBAR,
					historicoBAR, diashoras.getTransactionId(), diashoras.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IDiasHorasBAR#updateDiasHoras(com.qat.
	 * samples.sysmgmt.base.model.DiasHoras)
	 */
	@Override
	public InternalResponse updateDiasHoras(DiasHoras diashoras) {
		InternalResponse response = new InternalResponse();
		diashoras.setProcessId(diashoras.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_DIASHORAS, diashoras, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.DIASHORAS, AcaoEnum.UPDATE,
				diashoras.getTransactionId(), getHistoricoBAR(), response, diashoras.getId(), diashoras.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IDiasHorasBAR#deleteDiasHoras(com.qat.
	 * samples.sysmgmt.base.model.DiasHoras)
	 */
	@Override
	public InternalResponse deleteDiasHorasById(DiasHoras diashoras) {
		InternalResponse response = new InternalResponse();
		diashoras.setProcessId(diashoras.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_DIASHORAS, diashoras, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.DIASHORAS, AcaoEnum.DELETE,
				diashoras.getTransactionId(), getHistoricoBAR(), response, diashoras.getId(), diashoras.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IDiasHorasBAR#deleteAllDiasHorass()
	 */
	@Override
	public InternalResponse deleteAllDiasHorass() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_DIASHORAS_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IDiasHorasBAR#fetchDiasHorasById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public DiasHoras fetchDiasHorasById(FetchByIdRequest request) {
		return (DiasHoras) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_DIASHORAS,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IDiasHorasBAR#fetchAllDiasHorassCache()
	 */
	@Override
	public InternalResultsResponse<DiasHoras> fetchAllDiasHorass(DiasHoras diashoras) {
		InternalResultsResponse<DiasHoras> response = new InternalResultsResponse<DiasHoras>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_DIASHORAS_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IDiasHorasBAR#fetchDiasHorassByRequest(com.
	 * qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<DiasHoras> fetchDiasHorassByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<DiasHoras> response = new InternalResultsResponse<DiasHoras>();
		fetchDiasHorassByRequest(getSqlSession(), request, STMT_FETCH_DIASHORAS_COUNT, STMT_FETCH_DIASHORAS_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchDiasHorassByRequest
	// ####======================================

	/**
	 * Fetch dias horass by request.
	 *
	 * @param sqlSession
	 *            the sql session
	 * @param request
	 *            the request
	 * @param countStatement
	 *            the count statement
	 * @param fetchPagedStatement
	 *            the fetch paged statement
	 * @param response
	 *            the response
	 */
	public static void fetchDiasHorassByRequest(SqlSession sqlSession, PagedInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### ESPECIALIDADE
	// ####======================================
	/**
	 * /* (non-Javadoc).
	 *
	 * @param especialidade
	 *            the especialidade
	 * @return the internal response
	 * @see com.qat.samples.sysmgmt.base.bar.IEspecialidadeBAR#insertEspecialidade(com.qat.samples.sysmgmt.base.model.Especialidade)
	 */
	@Override
	public InternalResponse insertEspecialidade(Especialidade especialidade) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		especialidade.setProcessId(especialidade.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ESPECIALIDADE, especialidade, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ESPECIALIDADE, AcaoEnum.INSERT,
				especialidade.getTransactionId(), getHistoricoBAR(), response, especialidade.getId(),
				especialidade.getUserId());

		if (especialidade.getId() != 0 && especialidade.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, especialidade.getId(), null,
					AcaoEnum.INSERT, especialidade.getCreateUser(), especialidade.getId(), TabelaEnum.ESPECIALIDADE,
					statusBAR, historicoBAR, especialidade.getTransactionId(), especialidade.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IEspecialidadeBAR#updateEspecialidade(
	 * com.qat.samples.sysmgmt.base.model.Especialidade)
	 */
	@Override
	public InternalResponse updateEspecialidade(Especialidade especialidade) {
		InternalResponse response = new InternalResponse();
		especialidade.setProcessId(especialidade.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ESPECIALIDADE, especialidade, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ESPECIALIDADE, AcaoEnum.UPDATE,
				especialidade.getTransactionId(), getHistoricoBAR(), response, especialidade.getId(),
				especialidade.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IEspecialidadeBAR#deleteEspecialidade(
	 * com.qat.samples.sysmgmt.base.model.Especialidade)
	 */
	@Override
	public InternalResponse deleteEspecialidadeById(Especialidade especialidade) {
		InternalResponse response = new InternalResponse();
		especialidade.setProcessId(especialidade.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ESPECIALIDADE, especialidade, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ESPECIALIDADE, AcaoEnum.DELETE,
				especialidade.getTransactionId(), getHistoricoBAR(), response, especialidade.getId(),
				especialidade.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IEspecialidadeBAR#
	 * deleteAllEspecialidades()
	 */
	@Override
	public InternalResponse deleteAllEspecialidades() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ESPECIALIDADE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IEspecialidadeBAR#fetchEspecialidadeById(com.
	 * qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Especialidade fetchEspecialidadeById(FetchByIdRequest request) {
		return (Especialidade) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_ESPECIALIDADE,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IEspecialidadeBAR#
	 * fetchAllEspecialidadesCache()
	 */
	@Override
	public InternalResultsResponse<Especialidade> fetchAllEspecialidades(Especialidade especialidade) {
		InternalResultsResponse<Especialidade> response = new InternalResultsResponse<Especialidade>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_ESPECIALIDADE_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IEspecialidadeBAR#
	 * fetchEspecialidadesByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Especialidade> fetchEspecialidadesByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<Especialidade> response = new InternalResultsResponse<Especialidade>();
		fetchEspecialidadesByRequest(getSqlSession(), request, STMT_FETCH_ESPECIALIDADE_COUNT,
				STMT_FETCH_ESPECIALIDADE_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchEspecialidadesByRequest
	// ####======================================

	/**
	 * Fetch especialidades by request.
	 *
	 * @param sqlSession
	 *            the sql session
	 * @param request
	 *            the request
	 * @param countStatement
	 *            the count statement
	 * @param fetchPagedStatement
	 *            the fetch paged statement
	 * @param response
	 *            the response
	 */
	public static void fetchEspecialidadesByRequest(SqlSession sqlSession, PagedInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### COMPROMISSO
	// ####======================================

	/**
	 * Insert BARD compromisso.
	 *
	 * @param compromisso the compromisso
	 * @param response the response
	 * @param historicoId the historico id
	 * @return the integer
	 */
	public Integer insertBARDCompromisso(Compromisso compromisso, InternalResponse response, Integer historicoId) {
		Integer count = 0;

		if (!ValidationUtil.isNullOrEmpty(compromisso.getParticipante())) {
			count += ClienteCompromissoBARD.maintainClienteCompromissoAssociations(compromisso.getParticipante(),
					response, compromisso.getId(), null, null, TabelaEnum.CLIENTECOMPROMISSO, getAdvocaciaBAR(),
					getStatusBAR(), getHistoricoBAR(), compromisso.getId(), compromisso.getCreateUser(), historicoId,
					historicoId);
		}

		if (!ValidationUtil.isNullOrEmpty(compromisso.getParticipanteExterno())) {
			count += ParticipanteExternoBARD.maintainParticipanteExternoAssociations(
					compromisso.getParticipanteExterno(), response, compromisso.getId(), null, null,
					TabelaEnum.CLIENTECOMPROMISSO, getAdvocaciaBAR(), getStatusBAR(), getHistoricoBAR(),
					compromisso.getId(), compromisso.getCreateUser(), historicoId, historicoId);
		}

		// if (!ValidationUtil.isNullOrEmpty(compromisso.getArquivo()))
		// {
		// count +=
		// DocumentosBARD.maintainDocumentoAssociations(compromisso.getArquivo(),
		// response, compromisso.getId(), null,
		// null,
		// TabelaEnum.CLIENTECOMPROMISSO, getDocumentoBAR(), getStatusBAR(),
		// getHistoricoBAR(), compromisso.getId(),
		// compromisso.getCreateUser(), historicoId, historicoId);
		// }

		return count;
	}

	/**
	 * /* (non-Javadoc).
	 *
	 * @param compromisso
	 *            the compromisso
	 * @return the internal response
	 * @see com.qat.samples.sysmgmt.base.bar.ICompromissoBAR#insertCompromisso(com.qat.samples.sysmgmt.base.model.Compromisso)
	 */
	@Override
	public InternalResponse insertCompromisso(Compromisso compromisso) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		compromisso.setProcessId(compromisso.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_COMPROMISSO, compromisso, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.COMPROMISSO, AcaoEnum.INSERT,
				compromisso.getTransactionId(), getHistoricoBAR(), response, compromisso.getId(),
				compromisso.getUserId());

		a = insertBARDCompromisso(compromisso, response, compromisso.getTransactionId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICompromissoBAR#updateCompromisso(com.
	 * qat.samples.sysmgmt.base.model.Compromisso)
	 */
	@Override
	public InternalResponse updateCompromisso(Compromisso compromisso) {
		InternalResponse response = new InternalResponse();
		compromisso.setProcessId(compromisso.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_COMPROMISSO, compromisso, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.COMPROMISSO, AcaoEnum.UPDATE,
				compromisso.getTransactionId(), getHistoricoBAR(), response, compromisso.getId(),
				compromisso.getUserId());

		a = insertBARDCompromisso(compromisso, response, compromisso.getTransactionId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICompromissoBAR#deleteCompromisso(com.
	 * qat.samples.sysmgmt.base.model.Compromisso)
	 */
	@Override
	public InternalResponse deleteCompromissoById(Compromisso compromisso) {
		InternalResponse response = new InternalResponse();
		compromisso.setProcessId(compromisso.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_COMPROMISSO, compromisso, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.COMPROMISSO, AcaoEnum.DELETE,
				compromisso.getTransactionId(), getHistoricoBAR(), response, compromisso.getId(),
				compromisso.getUserId());

		a = insertBARDCompromisso(compromisso, response, compromisso.getTransactionId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICompromissoBAR#deleteAllCompromissos()
	 */
	@Override
	public InternalResponse deleteAllCompromissos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_COMPROMISSO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.ICompromissoBAR#fetchCompromissoById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Compromisso fetchCompromissoById(FetchByIdRequest request) {
		return (Compromisso) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_COMPROMISSO,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.ICompromissoBAR#
	 * fetchAllCompromissosCache()
	 */
	@Override
	public InternalResultsResponse<Compromisso> fetchAllCompromissos(Compromisso compromisso) {
		InternalResultsResponse<Compromisso> response = new InternalResultsResponse<Compromisso>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_COMPROMISSO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.ICompromissoBAR#fetchCompromissosByRequest(
	 * com.qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Compromisso> fetchCompromissosByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<Compromisso> response = new InternalResultsResponse<Compromisso>();
		fetchCompromissosByRequest(getSqlSession(), request, STMT_FETCH_COMPROMISSO_COUNT,
				STMT_FETCH_COMPROMISSO_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchCompromissosByRequest
	// ####======================================

	/**
	 * Fetch compromissos by request.
	 *
	 * @param sqlSession
	 *            the sql session
	 * @param request
	 *            the request
	 * @param countStatement
	 *            the count statement
	 * @param fetchPagedStatement
	 *            the fetch paged statement
	 * @param response
	 *            the response
	 */
	public static void fetchCompromissosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### ADVOGADOS
	// ####======================================
	/**
	 * /* (non-Javadoc).
	 *
	 * @param advogados
	 *            the advogados
	 * @return the internal response
	 * @see com.qat.samples.sysmgmt.base.bar.IAdvogadosBAR#insertAdvogados(com.qat.samples.sysmgmt.base.model.Advogados)
	 */
	@Override
	public InternalResponse insertAdvogados(Advogados advogados) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		advogados.setProcessId(advogados.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ADVOGADOS, advogados, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ADVOGADOS, AcaoEnum.INSERT,
				advogados.getTransactionId(), getHistoricoBAR(), response, advogados.getId(), advogados.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IAdvogadosBAR#updateAdvogados(com.qat.
	 * samples.sysmgmt.base.model.Advogados)
	 */
	@Override
	public InternalResponse updateAdvogados(Advogados advogados) {
		InternalResponse response = new InternalResponse();
		advogados.setProcessId(advogados.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ADVOGADOS, advogados, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ADVOGADOS, AcaoEnum.UPDATE,
				advogados.getTransactionId(), getHistoricoBAR(), response, advogados.getId(), advogados.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IAdvogadosBAR#deleteAdvogados(com.qat.
	 * samples.sysmgmt.base.model.Advogados)
	 */
	@Override
	public InternalResponse deleteAdvogadosById(Advogados advogados) {
		InternalResponse response = new InternalResponse();
		advogados.setProcessId(advogados.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ADVOGADOS, advogados, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ADVOGADOS, AcaoEnum.DELETE,
				advogados.getTransactionId(), getHistoricoBAR(), response, advogados.getId(), advogados.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IAdvogadosBAR#deleteAllAdvogadoss()
	 */
	@Override
	public InternalResponse deleteAllAdvogadoss() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ADVOGADOS_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IAdvogadosBAR#fetchAdvogadosById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Advogados fetchAdvogadosById(FetchByIdRequest request) {
		return (Advogados) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_ADVOGADOS,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IAdvogadosBAR#fetchAllAdvogadossCache()
	 */
	@Override
	public InternalResultsResponse<Advogados> fetchAllAdvogadoss(Advogados advogados) {
		InternalResultsResponse<Advogados> response = new InternalResultsResponse<Advogados>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_ADVOGADOS_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IAdvogadosBAR#fetchAdvogadossByRequest(com.
	 * qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Advogados> fetchAdvogadossByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<Advogados> response = new InternalResultsResponse<Advogados>();
		fetchAdvogadossByRequest(getSqlSession(), request, STMT_FETCH_ADVOGADOS_COUNT, STMT_FETCH_ADVOGADOS_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchAdvogadossByRequest
	// ####======================================

	/**
	 * Fetch advogadoss by request.
	 *
	 * @param sqlSession
	 *            the sql session
	 * @param request
	 *            the request
	 * @param countStatement
	 *            the count statement
	 * @param fetchPagedStatement
	 *            the fetch paged statement
	 * @param response
	 *            the response
	 */
	public static void fetchAdvogadossByRequest(SqlSession sqlSession, PagedInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### ENVOLVIDOS
	// ####======================================
	/**
	 * /* (non-Javadoc).
	 *
	 * @param envolvidos
	 *            the envolvidos
	 * @return the internal response
	 * @see com.qat.samples.sysmgmt.base.bar.IEnvolvidosBAR#insertEnvolvidos(com.qat.samples.sysmgmt.base.model.Envolvidos)
	 */
	@Override
	public InternalResponse insertEnvolvidos(Envolvidos envolvidos) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		envolvidos.setProcessId(envolvidos.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ENVOLVIDOS, envolvidos, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ENVOLVIDOS, AcaoEnum.INSERT,
				envolvidos.getTransactionId(), getHistoricoBAR(), response, envolvidos.getId(), envolvidos.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IEnvolvidosBAR#updateEnvolvidos(com.qat.
	 * samples.sysmgmt.base.model.Envolvidos)
	 */
	@Override
	public InternalResponse updateEnvolvidos(Envolvidos envolvidos) {
		InternalResponse response = new InternalResponse();
		envolvidos.setProcessId(envolvidos.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ENVOLVIDOS, envolvidos, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ENVOLVIDOS, AcaoEnum.UPDATE,
				envolvidos.getTransactionId(), getHistoricoBAR(), response, envolvidos.getId(), envolvidos.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IEnvolvidosBAR#deleteEnvolvidos(com.qat.
	 * samples.sysmgmt.base.model.Envolvidos)
	 */
	@Override
	public InternalResponse deleteEnvolvidosById(Envolvidos envolvidos) {
		InternalResponse response = new InternalResponse();
		envolvidos.setProcessId(envolvidos.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENVOLVIDOS, envolvidos, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ENVOLVIDOS, AcaoEnum.DELETE,
				envolvidos.getTransactionId(), getHistoricoBAR(), response, envolvidos.getId(), envolvidos.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IEnvolvidosBAR#deleteAllEnvolvidoss()
	 */
	@Override
	public InternalResponse deleteAllEnvolvidoss() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENVOLVIDOS_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IEnvolvidosBAR#fetchEnvolvidosById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Envolvidos fetchEnvolvidosById(FetchByIdRequest request) {
		return (Envolvidos) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_ENVOLVIDOS,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IEnvolvidosBAR#fetchAllEnvolvidossCache(
	 * )
	 */
	@Override
	public InternalResultsResponse<Envolvidos> fetchAllEnvolvidoss(Envolvidos envolvidos) {
		InternalResultsResponse<Envolvidos> response = new InternalResultsResponse<Envolvidos>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_ENVOLVIDOS_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IEnvolvidosBAR#fetchEnvolvidossByRequest(com.
	 * qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Envolvidos> fetchEnvolvidossByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<Envolvidos> response = new InternalResultsResponse<Envolvidos>();
		fetchEnvolvidossByRequest(getSqlSession(), request, STMT_FETCH_ENVOLVIDOS_COUNT,
				STMT_FETCH_ENVOLVIDOS_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchEnvolvidossByRequest
	// ####======================================

	/**
	 * Fetch envolvidoss by request.
	 *
	 * @param sqlSession
	 *            the sql session
	 * @param request
	 *            the request
	 * @param countStatement
	 *            the count statement
	 * @param fetchPagedStatement
	 *            the fetch paged statement
	 * @param response
	 *            the response
	 */
	public static void fetchEnvolvidossByRequest(SqlSession sqlSession, PagedInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### PARTICIPANTE
	// ####======================================
	/**
	 * /* (non-Javadoc).
	 *
	 * @param participante
	 *            the participante
	 * @return the internal response
	 * @see com.qat.samples.sysmgmt.base.bar.IParticipanteExternoBAR#insertParticipanteExterno(com.qat.samples.sysmgmt.base.model.ParticipanteExterno)
	 */
	@Override
	public InternalResponse insertParticipanteExterno(ParticipanteExterno participante) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		participante.setProcessId(participante.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PARTICIPANTE, participante, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PARTICIPANTE, AcaoEnum.INSERT,
				participante.getTransactionId(), getHistoricoBAR(), response, participante.getId(),
				participante.getUserId());

		if (!ValidationUtil.isNullOrEmpty(participante.getEmail())) {
			count += EmailBARD.maintainEmailAssociations(participante.getEmail(), response, participante.getId(), null,
					null, TabelaEnum.PARTICIPANTE, getEmailBAR(), getStatusBAR(), getHistoricoBAR(),
					participante.getId(), participante.getCreateUser(), participante.getTransactionId(),
					participante.getTransactionId());
		}
		if (!ValidationUtil.isNullOrEmpty(participante.getTelefones())) {
			count += TelefoneBARD.maintainTelefoneAssociations(participante.getTelefones(), response,
					participante.getId(), null, null, TabelaEnum.PARTICIPANTE, getTelefoneBAR(), getStatusBAR(),
					getHistoricoBAR(), participante.getId(), participante.getCreateUser(),
					participante.getTransactionId(), participante.getTransactionId());
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IParticipanteExternoBAR#
	 * updateParticipanteExterno(com.qat.samples.sysmgmt.base.model.
	 * ParticipanteExterno)
	 */
	@Override
	public InternalResponse updateParticipanteExterno(ParticipanteExterno participante) {
		InternalResponse response = new InternalResponse();
		participante.setProcessId(participante.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PARTICIPANTE, participante, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PARTICIPANTE, AcaoEnum.UPDATE,
				participante.getTransactionId(), getHistoricoBAR(), response, participante.getId(),
				participante.getUserId());
		if (!ValidationUtil.isNullOrEmpty(participante.getEmail())) {
			a += EmailBARD.maintainEmailAssociations(participante.getEmail(), response, participante.getId(), null,
					null, TabelaEnum.PARTICIPANTE, getEmailBAR(), getStatusBAR(), getHistoricoBAR(),
					participante.getId(), participante.getCreateUser(), participante.getTransactionId(),
					participante.getTransactionId());
		}
		if (!ValidationUtil.isNullOrEmpty(participante.getTelefones())) {
			a += TelefoneBARD.maintainTelefoneAssociations(participante.getTelefones(), response, participante.getId(),
					null, null, TabelaEnum.PARTICIPANTE, getTelefoneBAR(), getStatusBAR(), getHistoricoBAR(),
					participante.getId(), participante.getCreateUser(), participante.getTransactionId(),
					participante.getTransactionId());
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IParticipanteExternoBAR#
	 * deleteParticipanteExterno(com.qat.samples.sysmgmt.base.model.
	 * ParticipanteExterno)
	 */
	@Override
	public InternalResponse deleteParticipanteExternoById(ParticipanteExterno participante) {
		InternalResponse response = new InternalResponse();
		participante.setProcessId(participante.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PARTICIPANTE, participante, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PARTICIPANTE, AcaoEnum.DELETE,
				participante.getTransactionId(), getHistoricoBAR(), response, participante.getId(),
				participante.getUserId());
		if (!ValidationUtil.isNullOrEmpty(participante.getEmail())) {
			a += EmailBARD.maintainEmailAssociations(participante.getEmail(), response, participante.getId(), null,
					null, TabelaEnum.PARTICIPANTE, getEmailBAR(), getStatusBAR(), getHistoricoBAR(),
					participante.getId(), participante.getCreateUser(), participante.getTransactionId(),
					participante.getTransactionId());
		}
		if (!ValidationUtil.isNullOrEmpty(participante.getTelefones())) {
			a += TelefoneBARD.maintainTelefoneAssociations(participante.getTelefones(), response, participante.getId(),
					null, null, TabelaEnum.PARTICIPANTE, getTelefoneBAR(), getStatusBAR(), getHistoricoBAR(),
					participante.getId(), participante.getCreateUser(), participante.getTransactionId(),
					participante.getTransactionId());
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IParticipanteExternoBAR#
	 * deleteAllParticipanteExternos()
	 */
	@Override
	public InternalResponse deleteAllParticipanteExternos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PARTICIPANTE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IParticipanteExternoBAR#
	 * fetchParticipanteExternoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public ParticipanteExterno fetchParticipanteExternoById(FetchByIdRequest request) {
		return (ParticipanteExterno) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_PARTICIPANTE,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IParticipanteExternoBAR#
	 * fetchAllParticipanteExternosCache()
	 */
	@Override
	public InternalResultsResponse<ParticipanteExterno> fetchAllParticipanteExternos(ParticipanteExterno participante) {
		InternalResultsResponse<ParticipanteExterno> response = new InternalResultsResponse<ParticipanteExterno>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_PARTICIPANTE_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IParticipanteExternoBAR#
	 * fetchParticipanteExternosByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<ParticipanteExterno> fetchParticipanteExternosByRequest(
			PagedInquiryRequest request) {
		InternalResultsResponse<ParticipanteExterno> response = new InternalResultsResponse<ParticipanteExterno>();
		fetchParticipanteExternosByRequest(getSqlSession(), request, STMT_FETCH_PARTICIPANTE_COUNT,
				STMT_FETCH_PARTICIPANTE_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchParticipanteExternosByRequest
	// ####======================================

	/**
	 * Fetch participante externos by request.
	 *
	 * @param sqlSession
	 *            the sql session
	 * @param request
	 *            the request
	 * @param countStatement
	 *            the count statement
	 * @param fetchPagedStatement
	 *            the fetch paged statement
	 * @param response
	 *            the response
	 */
	public static void fetchParticipanteExternosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### PROCESSO
	// ####======================================

	/**
	 * Insert BARD processo.
	 *
	 * @param processo the processo
	 * @param response the response
	 * @param historicoId the historico id
	 * @return the integer
	 */
	public Integer insertBARDProcesso(Processo processo, InternalResponse response, Integer historicoId) {
		Integer count = 0;

		if (!ValidationUtil.isNullOrEmpty(processo.getTituloList())) {
			count += ContasReceberBARD.maintainContasReceberAssociations(processo.getTituloList(), response,
					processo.getId(), null, null, TabelaEnum.PROCESSO, getFinanceiroBAR(), getStatusBAR(),
					getHistoricoBAR(), processo.getId(), processo.getCreateUser(), historicoId, historicoId);
		}
//		if (!ValidationUtil.isNullOrEmpty(processo.getClienteList())) {
//			count += ProcessoClienteBARD.maintainProcessoClienteAssociations(processo.getAdvogadoList(), response,
//					processo.getId(), null, null, TabelaEnum.PROCESSO, getAdvocaciaBAR(), getStatusBAR(),
//					getHistoricoBAR(), processo.getId(), processo.getCreateUser(), historicoId, historicoId);
//		}
		if (!ValidationUtil.isNullOrEmpty(processo.getAudienciaList())) {
			count += CompromissoBARD.maintainCompromissoAssociations(processo.getAudienciaList(), response,
					processo.getId(), null, null, TabelaEnum.PROCESSO, getAdvocaciaBAR(), getStatusBAR(),
					getHistoricoBAR(), processo.getId(), processo.getCreateUser(), historicoId, historicoId);
		}
		if (!ValidationUtil.isNullOrEmpty(processo.getProcessoStatusList())) {
			count += ProcessoStatusBARD.maintainProcessoStatusAssociations(processo.getProcessoStatusList(), response,
					processo.getId(), null, null, TabelaEnum.PROCESSO, getAdvocaciaBAR(), getStatusBAR(),
					getHistoricoBAR(), processo.getId(), processo.getCreateUser(), historicoId, historicoId);
		}
		if (!ValidationUtil.isNullOrEmpty(processo.getEnvolvList())) {
			count += EnvolvidosBARD.maintainEnvolvidosAssociations(processo.getEnvolvList(), response, processo.getId(),
					null, null, TabelaEnum.PROCESSO, getAdvocaciaBAR(), getStatusBAR(), getHistoricoBAR(),
					processo.getId(), processo.getCreateUser(), historicoId, historicoId);
		}
		if (!ValidationUtil.isNullOrEmpty(processo.getArquivos())) {
			count += ArquivoBARD.maintainArquivoAssociations(processo.getArquivos(), response,
					processo.getId(), null, null, TabelaEnum.PROCESSO, getAdvocaciaBAR(), getStatusBAR(),
					getHistoricoBAR(), processo.getId(), processo.getCreateUser(), historicoId, historicoId);
		}
		if (!ValidationUtil.isNullOrEmpty(processo.getEnvolvidosExterno())) {
			count += ParticipanteExternoBARD.maintainParticipanteExternoAssociations(processo.getEnvolvidosExterno(), response,
					processo.getId(), null, null, TabelaEnum.PROCESSO, getAdvocaciaBAR(), getStatusBAR(),
					getHistoricoBAR(), processo.getId(), processo.getCreateUser(), historicoId, historicoId);
		}
		if (!ValidationUtil.isNullOrEmpty(processo.getUsuariosRestricaoProc())) {
			count += ProcessoUsuariosBARD.maintainProcessoUsuariosAssociations(processo.getUsuariosRestricaoProc(), response,
					processo.getId(), null, null, TabelaEnum.PROCESSO, getAdvocaciaBAR(), getStatusBAR(),
					getHistoricoBAR(), processo.getId(), processo.getCreateUser(), historicoId, historicoId);
		}

		return count;
	}

	/**
	 * /* (non-Javadoc).
	 *
	 * @param processo
	 *            the processo
	 * @return the internal response
	 * @see com.qat.samples.sysmgmt.base.bar.IProcessoBAR#insertProcesso(com.qat.samples.sysmgmt.base.model.Processo)
	 */
	@Override
	public InternalResponse insertProcesso(Processo processo) {
		InternalResponse response = new InternalResponse();

		processo.setProcessId(processo.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PROCESSO, processo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PROCESSO, AcaoEnum.INSERT,
				processo.getTransactionId(), getHistoricoBAR(), response, processo.getId(), processo.getUserId());

		a = insertBARDProcesso(processo, response, processo.getTransactionId());
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IProcessoBAR#updateProcesso(com.qat.
	 * samples.sysmgmt.base.model.Processo)
	 */
	@Override
	public InternalResponse updateProcesso(Processo processo) {
		InternalResponse response = new InternalResponse();
		processo.setProcessId(processo.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PROCESSO, processo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PROCESSO, AcaoEnum.UPDATE,
				processo.getTransactionId(), getHistoricoBAR(), response, processo.getId(), processo.getUserId());

		a = insertBARDProcesso(processo, response, processo.getTransactionId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IProcessoBAR#deleteProcesso(com.qat.
	 * samples.sysmgmt.base.model.Processo)
	 */
	@Override
	public InternalResponse deleteProcessoById(Processo processo) {
		InternalResponse response = new InternalResponse();
		processo.setProcessId(processo.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PROCESSO, processo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PROCESSO, AcaoEnum.DELETE,
				processo.getTransactionId(), getHistoricoBAR(), response, processo.getId(), processo.getUserId());

		a = insertBARDProcesso(processo, response, processo.getTransactionId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IProcessoBAR#deleteAllProcessos()
	 */
	@Override
	public InternalResponse deleteAllProcessos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PROCESSO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IProcessoBAR#fetchProcessoById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Processo fetchProcessoById(FetchByIdRequest request) {
		return (Processo) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_PROCESSO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IProcessoBAR#fetchAllProcessosCache()
	 */
	@Override
	public InternalResultsResponse<Processo> fetchAllProcessos(Processo processo) {
		InternalResultsResponse<Processo> response = new InternalResultsResponse<Processo>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_PROCESSO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IProcessoBAR#fetchProcessosByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Processo> fetchProcessosByRequest(ProcessoInquiryRequest request) {
		InternalResultsResponse<Processo> response = new InternalResultsResponse<Processo>();
		fetchProcessosByRequest(getSqlSession(), request, STMT_FETCH_PROCESSO_COUNT, STMT_FETCH_PROCESSO_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchProcessosByRequest
	// ####======================================

	/**
	 * Fetch processos by request.
	 *
	 * @param sqlSession
	 *            the sql session
	 * @param request
	 *            the request
	 * @param countStatement
	 *            the count statement
	 * @param fetchPagedStatement
	 *            the fetch paged statement
	 * @param response
	 *            the response
	 */
	public static void fetchProcessosByRequest(SqlSession sqlSession, ProcessoInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### CLIENTECOMPROMISSO
	// ####======================================
	/**
	 * /* (non-Javadoc).
	 *
	 * @param clientecompromisso the clientecompromisso
	 * @return the internal response
	 * @see com.qat.samples.sysmgmt.base.bar.IClienteCompromissoBAR#insertClienteCompromisso(com.qat.samples.sysmgmt.base.model.ClienteCompromisso)
	 */
	@Override
	public InternalResponse insertClienteCompromisso(ClienteCompromisso clientecompromisso) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		clientecompromisso.setProcessId(clientecompromisso.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CLIENTECOMPROMISSO, clientecompromisso, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.CLIENTECOMPROMISSO, AcaoEnum.INSERT,
				clientecompromisso.getTransactionId(), getHistoricoBAR(), response, clientecompromisso.getId(),
				clientecompromisso.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IClienteCompromissoBAR#
	 * updateClienteCompromisso(com.qat.samples.sysmgmt.base.model.
	 * ClienteCompromisso)
	 */
	@Override
	public InternalResponse updateClienteCompromisso(ClienteCompromisso clientecompromisso) {
		InternalResponse response = new InternalResponse();
		clientecompromisso.setProcessId(clientecompromisso.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CLIENTECOMPROMISSO, clientecompromisso, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.CLIENTECOMPROMISSO, AcaoEnum.UPDATE,
				clientecompromisso.getTransactionId(), getHistoricoBAR(), response, clientecompromisso.getId(),
				clientecompromisso.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IClienteCompromissoBAR#
	 * deleteClienteCompromisso(com.qat.samples.sysmgmt.base.model.
	 * ClienteCompromisso)
	 */
	@Override
	public InternalResponse deleteClienteCompromissoById(ClienteCompromisso clientecompromisso) {
		InternalResponse response = new InternalResponse();
		clientecompromisso.setProcessId(clientecompromisso.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CLIENTECOMPROMISSO, clientecompromisso, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.CLIENTECOMPROMISSO, AcaoEnum.DELETE,
				clientecompromisso.getTransactionId(), getHistoricoBAR(), response, clientecompromisso.getId(),
				clientecompromisso.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IClienteCompromissoBAR#
	 * deleteAllClienteCompromissos()
	 */
	@Override
	public InternalResponse deleteAllClienteCompromissos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CLIENTECOMPROMISSO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IClienteCompromissoBAR#
	 * fetchClienteCompromissoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public ClienteCompromisso fetchClienteCompromissoById(FetchByIdRequest request) {
		return (ClienteCompromisso) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CLIENTECOMPROMISSO,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IClienteCompromissoBAR#
	 * fetchAllClienteCompromissosCache()
	 */
	@Override
	public InternalResultsResponse<ClienteCompromisso> fetchAllClienteCompromissos(
			ClienteCompromisso clientecompromisso) {
		InternalResultsResponse<ClienteCompromisso> response = new InternalResultsResponse<ClienteCompromisso>();
		response.getResultsList()
				.addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CLIENTECOMPROMISSO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IClienteCompromissoBAR#
	 * fetchClienteCompromissosByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<ClienteCompromisso> fetchClienteCompromissosByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<ClienteCompromisso> response = new InternalResultsResponse<ClienteCompromisso>();
		fetchClienteCompromissosByRequest(getSqlSession(), request, STMT_FETCH_CLIENTECOMPROMISSO_COUNT,
				STMT_FETCH_CLIENTECOMPROMISSO_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchClienteCompromissosByRequest
	// ####======================================

	/**
	 * Fetch cliente compromissos by request.
	 *
	 * @param sqlSession the sql session
	 * @param request the request
	 * @param countStatement the count statement
	 * @param fetchPagedStatement the fetch paged statement
	 * @param response the response
	 */
	public static void fetchClienteCompromissosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### ARQUIVO
	// ####======================================
	/**
	 * /* (non-Javadoc).
	 *
	 * @param arquivo the arquivo
	 * @return the internal response
	 * @see com.qat.samples.sysmgmt.base.bar.IArquivoBAR#insertArquivo(com.qat.samples.sysmgmt.base.model.Arquivo)
	 */
	@Override
	public InternalResponse insertArquivo(Arquivo arquivo) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		arquivo.setProcessId(arquivo.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ARQUIVO, arquivo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ARQUIVO, AcaoEnum.INSERT,
				arquivo.getTransactionId(), getHistoricoBAR(), response, arquivo.getId(), arquivo.getUserId());

		if (arquivo.getId() != 0 && arquivo.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, arquivo.getId(), null, AcaoEnum.INSERT,
					arquivo.getCreateUser(), arquivo.getId(), TabelaEnum.ARQUIVO, statusBAR, historicoBAR,
					arquivo.getTransactionId(), arquivo.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IArquivoBAR#updateArquivo(com.qat.
	 * samples.sysmgmt.base.model.Arquivo)
	 */
	@Override
	public InternalResponse updateArquivo(Arquivo arquivo) {
		InternalResponse response = new InternalResponse();
		arquivo.setProcessId(arquivo.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ARQUIVO, arquivo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ARQUIVO, AcaoEnum.UPDATE,
				arquivo.getTransactionId(), getHistoricoBAR(), response, arquivo.getId(), arquivo.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IArquivoBAR#deleteArquivo(com.qat.
	 * samples.sysmgmt.base.model.Arquivo)
	 */
	@Override
	public InternalResponse deleteArquivoById(Arquivo arquivo) {
		InternalResponse response = new InternalResponse();
		arquivo.setProcessId(arquivo.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ARQUIVO, arquivo, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ARQUIVO, AcaoEnum.DELETE,
				arquivo.getTransactionId(), getHistoricoBAR(), response, arquivo.getId(), arquivo.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IArquivoBAR#deleteAllArquivos()
	 */
	@Override
	public InternalResponse deleteAllArquivos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ARQUIVO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IArquivoBAR#fetchArquivoById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Arquivo fetchArquivoById(FetchByIdRequest request) {
		return (Arquivo) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_ARQUIVO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IArquivoBAR#fetchAllArquivosCache()
	 */
	@Override
	public InternalResultsResponse<Arquivo> fetchAllArquivos(Arquivo arquivo) {
		InternalResultsResponse<Arquivo> response = new InternalResultsResponse<Arquivo>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_ARQUIVO_ALL));
		return response;
	}

	/**
	 * Fetch arquivos by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IArquivoBAR#fetchArquivosByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Arquivo> fetchArquivosByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<Arquivo> response = new InternalResultsResponse<Arquivo>();
		fetchArquivosByRequest(getSqlSession(), request, STMT_FETCH_ARQUIVO_COUNT, STMT_FETCH_ARQUIVO_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchArquivosByRequest
	// ####======================================

	/**
	 * Fetch arquivos by request.
	 *
	 * @param sqlSession the sql session
	 * @param request the request
	 * @param countStatement the count statement
	 * @param fetchPagedStatement the fetch paged statement
	 * @param response the response
	 */
	public static void fetchArquivosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	//===================================### PROCESSOUSUARIOS ####======================================
		/**
	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IProcessoUsuariosBAR#insertProcessoUsuarios(com.qat.samples.sysmgmt.base.model.ProcessoUsuarios)
	 */
	@Override
	public InternalResponse insertProcessoUsuarios(ProcessoUsuarios processousuarios)
	{
		InternalResponse response = new InternalResponse();
			Integer count = 0;
			Boolean count1 = false;

	processousuarios.setProcessId(processousuarios.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PROCESSOUSUARIOS, processousuarios, response);


	Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PROCESSOUSUARIOS, AcaoEnum.INSERT, processousuarios.getTransactionId(),
				getHistoricoBAR(), response, processousuarios.getId(),processousuarios.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IProcessoUsuariosBAR#updateProcessoUsuarios(com.qat.samples.sysmgmt.base.model.ProcessoUsuarios)
	 */
	@Override
	public InternalResponse updateProcessoUsuarios(ProcessoUsuarios processousuarios)
	{
		InternalResponse response = new InternalResponse();
	 processousuarios.setProcessId(processousuarios.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PROCESSOUSUARIOS, processousuarios, response);

	Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PROCESSOUSUARIOS, AcaoEnum.UPDATE, processousuarios.getTransactionId(),
				getHistoricoBAR(), response, processousuarios.getId(),processousuarios.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IProcessoUsuariosBAR#deleteProcessoUsuarios(com.qat.samples.sysmgmt.base.model.ProcessoUsuarios)
	 */
	@Override
	public InternalResponse deleteProcessoUsuariosById(ProcessoUsuarios processousuarios)
	{
		InternalResponse response = new InternalResponse();
	processousuarios.setProcessId(processousuarios.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PROCESSOUSUARIOS, processousuarios, response);

	Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PROCESSOUSUARIOS, AcaoEnum.DELETE, processousuarios.getTransactionId(),
				getHistoricoBAR(), response, processousuarios.getId(),processousuarios.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IProcessoUsuariosBAR#deleteAllProcessoUsuarioss()
	 */
	@Override
	public InternalResponse deleteAllProcessoUsuarioss()
	{
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PROCESSOUSUARIOS_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bar.IProcessoUsuariosBAR#fetchProcessoUsuariosById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public ProcessoUsuarios fetchProcessoUsuariosById(FetchByIdRequest request)
	{
	return (ProcessoUsuarios)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_PROCESSOUSUARIOS, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IProcessoUsuariosBAR#fetchAllProcessoUsuariossCache()
	 */
	@Override
	public InternalResultsResponse<ProcessoUsuarios> fetchAllProcessoUsuarioss(ProcessoUsuarios processousuarios)
	{
		InternalResultsResponse<ProcessoUsuarios> response = new InternalResultsResponse<ProcessoUsuarios>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_PROCESSOUSUARIOS_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bar.IProcessoUsuariosBAR#fetchProcessoUsuariossByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<ProcessoUsuarios> fetchProcessoUsuariossByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<ProcessoUsuarios> response = new InternalResultsResponse<ProcessoUsuarios>();
		fetchProcessoUsuariossByRequest(getSqlSession(), request, STMT_FETCH_PROCESSOUSUARIOS_COUNT, STMT_FETCH_PROCESSOUSUARIOS_ALL_REQUEST,
				response);
		return response;
	}

	//===================================### fetchProcessoUsuariossByRequest ####======================================

	public static void fetchProcessoUsuariossByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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


	//===================================### PROCESSOCLIENTE ####======================================
		/**
	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IProcessoClienteBAR#insertProcessoCliente(com.qat.samples.sysmgmt.base.model.ProcessoCliente)
	 */
	@Override
	public InternalResponse insertProcessoCliente(ProcessoCliente processocliente)
	{
		InternalResponse response = new InternalResponse();
			Integer count = 0;
			Boolean count1 = false;

	processocliente.setProcessId(processocliente.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PROCESSOCLIENTE, processocliente, response);


	Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PROCESSOCLIENTE, AcaoEnum.INSERT, processocliente.getTransactionId(),
				getHistoricoBAR(), response, processocliente.getId(),processocliente.getUserId());


	if (processocliente.getId() !=  0 && processocliente.getId() != null)
		{
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 =
					StatusBARD.maintainStatusAssociations(statusList, response, processocliente.getId(), null, AcaoEnum.INSERT,
							processocliente.getCreateUser(), processocliente.getId(), TabelaEnum.PROCESSOCLIENTE, statusBAR,
							historicoBAR, processocliente.getTransactionId(), processocliente.getTransactionId());

		}


		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IProcessoClienteBAR#updateProcessoCliente(com.qat.samples.sysmgmt.base.model.ProcessoCliente)
	 */
	@Override
	public InternalResponse updateProcessoCliente(ProcessoCliente processocliente)
	{
		InternalResponse response = new InternalResponse();
	 processocliente.setProcessId(processocliente.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PROCESSOCLIENTE, processocliente, response);

	Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PROCESSOCLIENTE, AcaoEnum.UPDATE, processocliente.getTransactionId(),
				getHistoricoBAR(), response, processocliente.getId(),processocliente.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IProcessoClienteBAR#deleteProcessoCliente(com.qat.samples.sysmgmt.base.model.ProcessoCliente)
	 */
	@Override
	public InternalResponse deleteProcessoClienteById(ProcessoCliente processocliente)
	{
		InternalResponse response = new InternalResponse();
	processocliente.setProcessId(processocliente.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PROCESSOCLIENTE, processocliente, response);

	Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PROCESSOCLIENTE, AcaoEnum.DELETE, processocliente.getTransactionId(),
				getHistoricoBAR(), response, processocliente.getId(),processocliente.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IProcessoClienteBAR#deleteAllProcessoClientes()
	 */
	@Override
	public InternalResponse deleteAllProcessoClientes()
	{
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PROCESSOCLIENTE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bar.IProcessoClienteBAR#fetchProcessoClienteById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public ProcessoCliente fetchProcessoClienteById(FetchByIdRequest request)
	{
	return (ProcessoCliente)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_PROCESSOCLIENTE, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IProcessoClienteBAR#fetchAllProcessoClientesCache()
	 */
	@Override
	public InternalResultsResponse<ProcessoCliente> fetchAllProcessoClientes(ProcessoCliente processocliente)
	{
		InternalResultsResponse<ProcessoCliente> response = new InternalResultsResponse<ProcessoCliente>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_PROCESSOCLIENTE_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bar.IProcessoClienteBAR#fetchProcessoClientesByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<ProcessoCliente> fetchProcessoClientesByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<ProcessoCliente> response = new InternalResultsResponse<ProcessoCliente>();
		fetchProcessoClientesByRequest(getSqlSession(), request, STMT_FETCH_PROCESSOCLIENTE_COUNT, STMT_FETCH_PROCESSOCLIENTE_ALL_REQUEST,
				response);
		return response;
	}

	//===================================### fetchProcessoClientesByRequest ####======================================

	public static void fetchProcessoClientesByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
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
}
