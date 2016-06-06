/** create by system gera-java version 1.0.0 01/05/2016 18:42 : 57*/
package com.qat.samples.sysmgmt.bar.mybatis.Empresa;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.advocacia.Advocacia;
import com.qat.samples.sysmgmt.advocacia.request.AdvocaciaInquiryRequest;
import com.qat.samples.sysmgmt.bar.Cadastros.ICadastrosBAR;
import com.qat.samples.sysmgmt.bar.Documentos.IDocumentoBAR;
import com.qat.samples.sysmgmt.bar.Email.IEmailBAR;
import com.qat.samples.sysmgmt.bar.Empresa.IEmpresaBAR;
import com.qat.samples.sysmgmt.bar.Endereco.IEnderecoBAR;
import com.qat.samples.sysmgmt.bar.Financeiro.IFinanceiroBAR;
import com.qat.samples.sysmgmt.bar.Fiscal.IFiscalBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Notes.INotesBAR;
import com.qat.samples.sysmgmt.bar.Site.ISiteBAR;
import com.qat.samples.sysmgmt.bar.Socios.ISociosBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.bar.Telefone.ITelefoneBAR;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.BaseBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.InsertHistBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.PlanoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.SociosBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.UsuarioBARD;
import com.qat.samples.sysmgmt.clinica.model.Clinica;
import com.qat.samples.sysmgmt.clinica.model.request.ClinicaInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.Condominio;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.Deposito;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.entidade.model.Filial;
import com.qat.samples.sysmgmt.entidade.model.Usuario;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialInquiryRequest;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioInquiryRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class EmpresaBARImpl extends SqlSessionDaoSupport implements IEmpresaBAR {

	/** The Constant ZERO. */
	private static final int ZERO = 0;
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_ENTIDADE = "EntidadeMap.";

	/** The Constant STMT_INSERT_EMPRESA. */
	private static final String STMT_INSERT_ENTIDADE = NAMESPACE_ENTIDADE + "insertEntidade";

	/** The Constant STMT_UPDATE_EMPRESA. */
	private static final String STMT_UPDATE_ENTIDADE = NAMESPACE_ENTIDADE + "updateEntidade";

	/** The Constant STMT_DELETE_EMPRESA. */
	private static final String STMT_DELETE_ENTIDADE = NAMESPACE_ENTIDADE + "deleteEntidadeById";

	/** The Constant STMT_DELETE_EMPRESA_ALL. */
	private static final String STMT_DELETE_ENTIDADE_ALL = NAMESPACE_ENTIDADE + "deleteAllEntidades";

	/// ===================================### EMPRESA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_EMPRESA = "EmpresaMap.";

	/** The Constant STMT_FETCH_EMPRESA. */
	private static final String STMT_FETCH_EMPRESA = NAMESPACE_EMPRESA + "fetchEmpresaById";

	/** The Constant STMT_FETCH_EMPRESA_ALL. */
	private static final String STMT_FETCH_EMPRESA_ALL = NAMESPACE_EMPRESA + "fetchAllEmpresas";

	/** The Constant STMT_FETCH_EMPRESA_COUNT. */
	private static final String STMT_FETCH_EMPRESA_COUNT = NAMESPACE_EMPRESA + "fetchEmpresaRowCount";

	/** The Constant STMT_FETCH_EMPRESA_ALL_REQUEST. */
	private static final String STMT_FETCH_EMPRESA_ALL_REQUEST = NAMESPACE_EMPRESA + "fetchAllEmpresasByRequest";

	/// ===================================### FILIAL
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_FILIAL = "FilialMap.";

	/** The Constant STMT_FETCH_FILIAL. */
	private static final String STMT_FETCH_FILIAL = NAMESPACE_FILIAL + "fetchFilialById";

	/** The Constant STMT_FETCH_FILIAL_ALL. */
	private static final String STMT_FETCH_FILIAL_ALL = NAMESPACE_FILIAL + "fetchAllFilials";

	/** The Constant STMT_FETCH_FILIAL_COUNT. */
	private static final String STMT_FETCH_FILIAL_COUNT = NAMESPACE_FILIAL + "fetchFilialRowCount";

	/** The Constant STMT_FETCH_FILIAL_ALL_REQUEST. */
	private static final String STMT_FETCH_FILIAL_ALL_REQUEST = NAMESPACE_FILIAL + "fetchAllFilialsByRequest";

	/// ===================================### DEPOSITO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_DEPOSITO = "DepositoMap.";

	/** The Constant STMT_FETCH_DEPOSITO. */
	private static final String STMT_FETCH_DEPOSITO = NAMESPACE_DEPOSITO + "fetchDepositoById";

	/** The Constant STMT_FETCH_DEPOSITO_ALL. */
	private static final String STMT_FETCH_DEPOSITO_ALL = NAMESPACE_DEPOSITO + "fetchAllDepositos";

	/** The Constant STMT_FETCH_DEPOSITO_COUNT. */
	private static final String STMT_FETCH_DEPOSITO_COUNT = NAMESPACE_DEPOSITO + "fetchDepositoRowCount";

	/** The Constant STMT_FETCH_DEPOSITO_ALL_REQUEST. */
	private static final String STMT_FETCH_DEPOSITO_ALL_REQUEST = NAMESPACE_DEPOSITO + "fetchAllDepositosByRequest";

	/// ===================================### USUARIO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_USUARIO = "UsuarioMap.";

	/** The Constant STMT_INSERT_USUARIO. */
	private static final String STMT_INSERT_USUARIO = NAMESPACE_USUARIO + "insertUsuario";

	/** The Constant STMT_UPDATE_USUARIO. */
	private static final String STMT_UPDATE_USUARIO = NAMESPACE_USUARIO + "updateUsuario";

	/** The Constant STMT_DELETE_USUARIO. */
	private static final String STMT_DELETE_USUARIO = NAMESPACE_USUARIO + "deleteUsuarioById";

	/** The Constant STMT_DELETE_USUARIO_ALL. */
	private static final String STMT_DELETE_USUARIO_ALL = NAMESPACE_USUARIO + "deleteAllUsuarios";

	/** The Constant STMT_FETCH_USUARIO. */
	private static final String STMT_FETCH_USUARIO = NAMESPACE_USUARIO + "fetchUsuarioById";

	/** The Constant STMT_FETCH_USUARIO_ALL. */
	private static final String STMT_FETCH_USUARIO_ALL = NAMESPACE_USUARIO + "fetchAllUsuarios";

	/** The Constant STMT_FETCH_USUARIO_COUNT. */
	private static final String STMT_FETCH_USUARIO_COUNT = NAMESPACE_USUARIO + "fetchUsuarioRowCount";

	/** The Constant STMT_FETCH_USUARIO_ALL_REQUEST. */
	private static final String STMT_FETCH_USUARIO_ALL_REQUEST = NAMESPACE_USUARIO + "fetchAllUsuariosRequest";

	/// ===================================### CONDOMINIO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_CONDOMINIO = "CondominioMap.";

	/** The Constant STMT_FETCH_CONDOMINIO. */
	private static final String STMT_FETCH_CONDOMINIO = NAMESPACE_CONDOMINIO + "fetchCondominioById";

	/** The Constant STMT_FETCH_CONDOMINIO_ALL. */
	private static final String STMT_FETCH_CONDOMINIO_ALL = NAMESPACE_CONDOMINIO + "fetchAllCondominios";

	/** The Constant STMT_FETCH_CONDOMINIO_COUNT. */
	private static final String STMT_FETCH_CONDOMINIO_COUNT = NAMESPACE_CONDOMINIO + "fetchCondominioRowCount";

	/** The Constant STMT_FETCH_CONDOMINIO_ALL_REQUEST. */
	private static final String STMT_FETCH_CONDOMINIO_ALL_REQUEST = NAMESPACE_CONDOMINIO + "fetchAllCondominiosRequest";

	/// ===================================### CLINICA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_CLINICA = "ClinicaMap.";

	/** The Constant STMT_FETCH_CLINICA. */
	private static final String STMT_FETCH_CLINICA = NAMESPACE_CLINICA + "fetchClinicaById";

	/** The Constant STMT_FETCH_CLINICA_ALL. */
	private static final String STMT_FETCH_CLINICA_ALL = NAMESPACE_CLINICA + "fetchAllClinicas";

	/** The Constant STMT_FETCH_CLINICA_COUNT. */
	private static final String STMT_FETCH_CLINICA_COUNT = NAMESPACE_CLINICA + "fetchClinicaRowCount";

	/** The Constant STMT_FETCH_CLINICA_ALL_REQUEST. */
	private static final String STMT_FETCH_CLINICA_ALL_REQUEST = NAMESPACE_CLINICA + "fetchAllClinicasRequest";

	/// ===================================### ADVOCACIA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_ADVOCACIA = "AdvocaciaMap.";

	/** The Constant STMT_FETCH_ADVOCACIA. */
	private static final String STMT_FETCH_ADVOCACIA = NAMESPACE_ADVOCACIA + "fetchAdvocaciaById";

	/** The Constant STMT_FETCH_ADVOCACIA_ALL. */
	private static final String STMT_FETCH_ADVOCACIA_ALL = NAMESPACE_ADVOCACIA + "fetchAllAdvocacias";

	/** The Constant STMT_FETCH_ADVOCACIA_COUNT. */
	private static final String STMT_FETCH_ADVOCACIA_COUNT = NAMESPACE_ADVOCACIA + "fetchAdvocaciaRowCount";

	/** The Constant STMT_FETCH_ADVOCACIA_ALL_REQUEST. */
	private static final String STMT_FETCH_ADVOCACIA_ALL_REQUEST = NAMESPACE_ADVOCACIA + "fetchAllAdvocaciasRequest";

	// ===================================### EMPRESA
	// ####======================================
	IEnderecoBAR enderecoBAR;

	IStatusBAR statusBAR;

	IHistoricoBAR historicoBAR;

	ICadastrosBAR cadastrosBAR;

	IFiscalBAR fiscalBAR;

	ITelefoneBAR telefoneBAR;

	IEmailBAR emailBAR;

	IDocumentoBAR documentoBAR;

	ISociosBAR sociosBAR;

	ISiteBAR siteBAR;

	IFinanceiroBAR financeiroBAR;

	INotesBAR notesBAR;

	IEmpresaBAR empresaBAR;



	public ISociosBAR getSociosBAR() {
		return sociosBAR;
	}

	public void setSociosBAR(ISociosBAR sociosBAR) {
		this.sociosBAR = sociosBAR;
	}

	public ISiteBAR getSiteBAR() {
		return siteBAR;
	}

	public void setSiteBAR(ISiteBAR siteBAR) {
		this.siteBAR = siteBAR;
	}

	public IFinanceiroBAR getFinanceiroBAR() {
		return financeiroBAR;
	}

	public void setFinanceiroBAR(IFinanceiroBAR financeiroBAR) {
		this.financeiroBAR = financeiroBAR;
	}

	public IDocumentoBAR getDocumentoBAR() {
		return documentoBAR;
	}

	public IHistoricoBAR getHistoricoBAR() {
		return historicoBAR;
	}

	public void setHistoricoBAR(IHistoricoBAR historicoBAR) {
		this.historicoBAR = historicoBAR;
	}

	public IStatusBAR getStatusBAR() {
		return statusBAR;
	}

	public void setStatusBAR(IStatusBAR statusBAR) {
		this.statusBAR = statusBAR;
	}

	public IEnderecoBAR getEnderecoBAR() {
		return enderecoBAR;
	}

	// @Resource
	public void setEnderecoBAR(IEnderecoBAR enderecoBAR) {
		this.enderecoBAR = enderecoBAR;
	}

	public ICadastrosBAR getCadastrosBAR() {
		return cadastrosBAR;
	}

	public void setCadastrosBAR(ICadastrosBAR cadastrosBAR) {
		this.cadastrosBAR = cadastrosBAR;
	}

	public IFiscalBAR getFiscalBAR() {
		return fiscalBAR;
	}

	public void setFiscalBAR(IFiscalBAR fiscalBAR) {
		this.fiscalBAR = fiscalBAR;
	}

	public ITelefoneBAR getTelefoneBAR() {
		return telefoneBAR;
	}

	public void setTelefoneBAR(ITelefoneBAR telefoneBAR) {
		this.telefoneBAR = telefoneBAR;
	}

	public IEmailBAR getEmailBAR() {
		return emailBAR;
	}

	public void setEmailBAR(IEmailBAR emailBAR) {
		this.emailBAR = emailBAR;
	}

	public IDocumentoBAR getDocumentosBAR() {
		return documentoBAR;
	}

	public void setDocumentoBAR(IDocumentoBAR documentoBAR) {
		this.documentoBAR = documentoBAR;
	}

	public INotesBAR getNotesBAR() {
		return notesBAR;
	}

	public void setNotesBAR(INotesBAR notesBAR) {
		this.notesBAR = notesBAR;
	}

	public IEmpresaBAR getEmpresaBAR() {
		return empresaBAR;
	}

	public void setEmpresaBAR(IEmpresaBAR empresaBAR) {
		this.empresaBAR = empresaBAR;
	}

	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IEmpresaBAR#insertEmpresa(com.qat.samples.sysmgmt.base.model.Empresa)
	 */
	@Override
	public InternalResponse insertEmpresa(Empresa empresa) {
		InternalResponse response = new InternalResponse();
		Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.EMPRESA, getHistoricoBAR(), response);
		Integer processId = 1;
		empresa.setProcessId(historicoId);
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ENTIDADE, empresa, response);

		BaseBARD.maintainInsertBase(empresa, historicoId, processId, TabelaEnum.EMPRESA, getEnderecoBAR(),
				getStatusBAR(), getHistoricoBAR(), getCadastrosBAR(), getFiscalBAR(), getTelefoneBAR(), getEmailBAR(),
				getDocumentosBAR(), getNotesBAR(), new InternalResultsResponse<Empresa>());

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.EMPRESA, AcaoEnum.INSERT, historicoId,
				getHistoricoBAR(), response, empresa.getId());

		if (!ValidationUtil.isNullOrEmpty(empresa.getSocios()))
		{
			a +=
					SociosBARD.maintainSocioAssociations(empresa.getSocios(), response, empresa.getId(), null, null,
							TabelaEnum.EMPRESA, getSociosBAR(), getStatusBAR(), getHistoricoBAR(), empresa.getId(),
							empresa.getCreateUser(), historicoId, historicoId, getDocumentosBAR());

		}
		if (!ValidationUtil.isNullOrEmpty(empresa.getPlanoList()))
		{
			a +=
					PlanoBARD.maintainPlanoAssociations(empresa.getPlanoList(), response, empresa.getId(), null, null,
							TabelaEnum.EMPRESA, getSiteBAR(), getStatusBAR(), getHistoricoBAR(), empresa.getId(),
							empresa.getCreateUser(), historicoId, historicoId);
		}

		if (!ValidationUtil.isNullOrEmpty(empresa.getUsuarios()))
		{
			a +=
					UsuarioBARD.maintainUsuarioAssociations(empresa.getUsuarios(), response, empresa.getId(), null,
							null,
							TabelaEnum.EMPRESA, getEmpresaBAR(), getStatusBAR(), getHistoricoBAR(), empresa.getId(),
							empresa.getCreateUser(), historicoId, historicoId);
		}

		if (!ValidationUtil.isNullOrEmpty(empresa.getContaCorrenteList()))
		{
//			a +=
//					UsuarioBARD.maintainUsuarioAssociations(empresa.getUsuarioList(), response, empresa.getId(), null,
//							null,
//							TabelaEnum.EMPRESA, getFinanceiroBAR(), getStatusBAR(), getHistoricoBAR(), empresa.getId(),
//							empresa.getCreateUser(), historicoId, historicoId);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IEmpresaBAR#updateEmpresa(com.qat.
	 * samples.sysmgmt.base.model.Empresa)
	 */
	@Override
	public InternalResponse updateEmpresa(Empresa empresa) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ENTIDADE, empresa, response);

		Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.EMPRESA, getHistoricoBAR(), response);
		Integer processId = 1;

		BaseBARD.maintainInsertBase(empresa, historicoId, processId, TabelaEnum.EMPRESA, getEnderecoBAR(),
				getStatusBAR(), getHistoricoBAR(), getCadastrosBAR(), getFiscalBAR(), getTelefoneBAR(), getEmailBAR(),
				getDocumentosBAR(), getNotesBAR(), new InternalResultsResponse<Empresa>());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IEmpresaBAR#deleteEmpresa(com.qat.
	 * samples.sysmgmt.base.model.Empresa)
	 */
	@Override
	public InternalResponse deleteEmpresaById(Empresa empresa) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE, empresa, response);

		Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.EMPRESA, getHistoricoBAR(), response);
		Integer processId = historicoId;

		BaseBARD.maintainInsertBase(empresa, historicoId, processId, TabelaEnum.EMPRESA, getEnderecoBAR(),
				getStatusBAR(), getHistoricoBAR(), getCadastrosBAR(), getFiscalBAR(), getTelefoneBAR(), getEmailBAR(),
				getDocumentosBAR(), getNotesBAR(), new InternalResultsResponse<Empresa>());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IEmpresaBAR#deleteAllEmpresas()
	 */
	@Override
	public InternalResponse deleteAllEmpresas() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IEmpresaBAR#fetchEmpresaById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Empresa fetchEmpresaById(FetchByIdRequest request) {
		return (Empresa) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_EMPRESA, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IEmpresaBAR#fetchAllEmpresasCache()
	 */
	@Override
	public InternalResultsResponse<Empresa> fetchAllEmpresas(Empresa empresa) {
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_EMPRESA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IEmpresaBAR#fetchEmpresasByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> fetchEmpresasByRequest(EmpresaInquiryRequest request) {
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();
		fetchEmpresasByRequest(getSqlSession(), request, STMT_FETCH_EMPRESA_COUNT, STMT_FETCH_EMPRESA_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchEmpresasByRequest
	// ####======================================

	public static void fetchEmpresasByRequest(SqlSession sqlSession, EmpresaInquiryRequest request,
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

	// ===================================### FILIAL
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IFilialBAR#insertFilial(com.qat.samples.sysmgmt.base.model.Filial)
	 */
	@Override
	public InternalResponse insertFilial(Filial filial) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ENTIDADE, filial, response);

		Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.EMPRESA, getHistoricoBAR(), response);
		Integer processId = historicoId;

		BaseBARD.maintainInsertBase(filial, historicoId, processId, TabelaEnum.EMPRESA, getEnderecoBAR(),
				getStatusBAR(), getHistoricoBAR(), getCadastrosBAR(), getFiscalBAR(), getTelefoneBAR(), getEmailBAR(),
				getDocumentosBAR(), getNotesBAR(), new InternalResultsResponse<Empresa>());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IFilialBAR#updateFilial(com.qat.samples.
	 * sysmgmt.base.model.Filial)
	 */
	@Override
	public InternalResponse updateFilial(Filial filial) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ENTIDADE, filial, response);

		Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.EMPRESA, getHistoricoBAR(), response);
		Integer processId = historicoId;

		BaseBARD.maintainInsertBase(filial, historicoId, processId, TabelaEnum.EMPRESA, getEnderecoBAR(),
				getStatusBAR(), getHistoricoBAR(), getCadastrosBAR(), getFiscalBAR(), getTelefoneBAR(), getEmailBAR(),
				getDocumentosBAR(), getNotesBAR(), new InternalResultsResponse<Empresa>());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IFilialBAR#deleteFilial(com.qat.samples.
	 * sysmgmt.base.model.Filial)
	 */
	@Override
	public InternalResponse deleteFilialById(Filial filial) {
		InternalResponse response = new InternalResponse();

		Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.EMPRESA, getHistoricoBAR(), response);
		Integer processId = historicoId;

		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE, filial, response);

		BaseBARD.maintainInsertBase(filial, historicoId, processId, TabelaEnum.FILIAL, getEnderecoBAR(), getStatusBAR(),
				getHistoricoBAR(), getCadastrosBAR(), getFiscalBAR(), getTelefoneBAR(), getEmailBAR(),
				getDocumentosBAR(), getNotesBAR(), new InternalResultsResponse<Empresa>());
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IFilialBAR#deleteAllFilials()
	 */
	@Override
	public InternalResponse deleteAllFilials() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IFilialBAR#fetchFilialById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Filial fetchFilialById(FetchByIdRequest request) {
		return (Filial) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_FILIAL, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IFilialBAR#fetchAllFilialsCache()
	 */
	@Override
	public InternalResultsResponse<Filial> fetchAllFilials(Filial filial) {
		InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_FILIAL_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IFilialBAR#fetchFilialsByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Filial> fetchFilialsByRequest(FilialInquiryRequest request) {
		InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();
		fetchFilialsByRequest(getSqlSession(), request, STMT_FETCH_FILIAL_COUNT, STMT_FETCH_FILIAL_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchFilialsByRequest
	// ####======================================

	public static void fetchFilialsByRequest(SqlSession sqlSession, FilialInquiryRequest request, String countStatement,
			String fetchPagedStatement, InternalResultsResponse<?> response) {

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

	// ===================================### DEPOSITO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IDepositoBAR#insertDeposito(com.qat.samples.sysmgmt.base.model.Deposito)
	 */
	@Override
	public InternalResponse insertDeposito(Deposito deposito) {
		InternalResponse response = new InternalResponse();

		Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.EMPRESA, getHistoricoBAR(), response);
		Integer processId = historicoId;

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ENTIDADE, deposito, response);

		BaseBARD.maintainInsertBase(deposito, historicoId, processId, TabelaEnum.EMPRESA, getEnderecoBAR(),
				getStatusBAR(), getHistoricoBAR(), getCadastrosBAR(), getFiscalBAR(), getTelefoneBAR(), getEmailBAR(),
				getDocumentosBAR(), getNotesBAR(), new InternalResultsResponse<Empresa>());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IDepositoBAR#updateDeposito(com.qat.
	 * samples.sysmgmt.base.model.Deposito)
	 */
	@Override
	public InternalResponse updateDeposito(Deposito deposito) {
		InternalResponse response = new InternalResponse();

		Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.EMPRESA, getHistoricoBAR(), response);
		Integer processId = historicoId;

		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ENTIDADE, deposito, response);

		BaseBARD.maintainInsertBase(deposito, historicoId, processId, TabelaEnum.EMPRESA, getEnderecoBAR(),
				getStatusBAR(), getHistoricoBAR(), getCadastrosBAR(), getFiscalBAR(), getTelefoneBAR(), getEmailBAR(),
				getDocumentosBAR(), getNotesBAR(), new InternalResultsResponse<Empresa>());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IDepositoBAR#deleteDeposito(com.qat.
	 * samples.sysmgmt.base.model.Deposito)
	 */
	@Override
	public InternalResponse deleteDepositoById(Deposito deposito) {
		InternalResponse response = new InternalResponse();

		Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.EMPRESA, getHistoricoBAR(), response);
		Integer processId = historicoId;
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE, deposito, response);

		BaseBARD.maintainInsertBase(deposito, historicoId, processId, TabelaEnum.EMPRESA, getEnderecoBAR(),
				getStatusBAR(), getHistoricoBAR(), getCadastrosBAR(), getFiscalBAR(), getTelefoneBAR(), getEmailBAR(),
				getDocumentosBAR(), getNotesBAR(), new InternalResultsResponse<Empresa>());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IDepositoBAR#deleteAllDepositos()
	 */
	@Override
	public InternalResponse deleteAllDepositos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IDepositoBAR#fetchDepositoById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Deposito fetchDepositoById(FetchByIdRequest request) {
		return (Deposito) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_DEPOSITO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IDepositoBAR#fetchAllDepositosCache()
	 */
	@Override
	public InternalResultsResponse<Deposito> fetchAllDepositos(Deposito deposito) {
		InternalResultsResponse<Deposito> response = new InternalResultsResponse<Deposito>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_DEPOSITO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IDepositoBAR#fetchDepositosByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Deposito> fetchDepositosByRequest(DepositoInquiryRequest request) {
		InternalResultsResponse<Deposito> response = new InternalResultsResponse<Deposito>();
		fetchDepositosByRequest(getSqlSession(), request, STMT_FETCH_DEPOSITO_COUNT, STMT_FETCH_DEPOSITO_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchDepositosByRequest
	// ####======================================

	public static void fetchDepositosByRequest(SqlSession sqlSession, DepositoInquiryRequest request,
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

	// ===================================### USUARIO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IUsuarioBAR#insertUsuario(com.qat.samples.sysmgmt.base.model.Usuario)
	 */
	@Override
	public InternalResponse insertUsuario(Usuario usuario) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_USUARIO, usuario, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IUsuarioBAR#updateUsuario(com.qat.
	 * samples.sysmgmt.base.model.Usuario)
	 */
	@Override
	public InternalResponse updateUsuario(Usuario usuario) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_USUARIO, usuario, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IUsuarioBAR#deleteUsuario(com.qat.
	 * samples.sysmgmt.base.model.Usuario)
	 */
	@Override
	public InternalResponse deleteUsuarioById(Usuario usuario) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_USUARIO, usuario, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IUsuarioBAR#deleteAllUsuarios()
	 */
	@Override
	public InternalResponse deleteAllUsuarios() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_USUARIO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IUsuarioBAR#fetchUsuarioById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Usuario fetchUsuarioById(FetchByIdRequest request) {
		return (Usuario) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_USUARIO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IUsuarioBAR#fetchAllUsuariosCache()
	 */
	@Override
	public InternalResultsResponse<Usuario> fetchAllUsuarios(Usuario usuario) {
		InternalResultsResponse<Usuario> response = new InternalResultsResponse<Usuario>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_USUARIO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IUsuarioBAR#fetchUsuariosByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Usuario> fetchUsuariosByRequest(UsuarioInquiryRequest request) {
		InternalResultsResponse<Usuario> response = new InternalResultsResponse<Usuario>();
		fetchUsuariosByRequest(getSqlSession(), request, STMT_FETCH_USUARIO_COUNT, STMT_FETCH_USUARIO_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchUsuariosByRequest
	// ####======================================

	public static void fetchUsuariosByRequest(SqlSession sqlSession, UsuarioInquiryRequest request,
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

	// ===================================### CONDOMINIO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.ICondominioBAR#insertCondominio(com.qat.samples.sysmgmt.base.model.Condominio)
	 */
	@Override
	public InternalResponse insertCondominio(Condominio condominio) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ENTIDADE, condominio, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICondominioBAR#updateCondominio(com.qat.
	 * samples.sysmgmt.base.model.Condominio)
	 */
	@Override
	public InternalResponse updateCondominio(Condominio condominio) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ENTIDADE, condominio, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICondominioBAR#deleteCondominio(com.qat.
	 * samples.sysmgmt.base.model.Condominio)
	 */
	@Override
	public InternalResponse deleteCondominioById(Condominio condominio) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE, condominio, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICondominioBAR#deleteAllCondominios()
	 */
	@Override
	public InternalResponse deleteAllCondominios() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.ICondominioBAR#fetchCondominioById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Condominio fetchCondominioById(FetchByIdRequest request) {
		return (Condominio) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CONDOMINIO,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICondominioBAR#fetchAllCondominiosCache(
	 * )
	 */
	@Override
	public InternalResultsResponse<Condominio> fetchAllCondominios(Condominio condominio) {
		InternalResultsResponse<Condominio> response = new InternalResultsResponse<Condominio>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CONDOMINIO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.ICondominioBAR#fetchCondominiosByRequest(com.
	 * qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Condominio> fetchCondominiosByRequest(CondominioInquiryRequest request) {
		InternalResultsResponse<Condominio> response = new InternalResultsResponse<Condominio>();
		fetchCondominiosByRequest(getSqlSession(), request, STMT_FETCH_CONDOMINIO_COUNT,
				STMT_FETCH_CONDOMINIO_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchCondominiosByRequest
	// ####======================================

	public static void fetchCondominiosByRequest(SqlSession sqlSession, CondominioInquiryRequest request,
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

	// ===================================### CLINICA
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IClinicaBAR#insertClinica(com.qat.samples.sysmgmt.base.model.Clinica)
	 */
	@Override
	public InternalResponse insertClinica(Clinica clinicas) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ENTIDADE, clinicas, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IClinicaBAR#updateClinica(com.qat.
	 * samples.sysmgmt.base.model.Clinica)
	 */
	@Override
	public InternalResponse updateClinica(Clinica clinica) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ENTIDADE, clinica, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IClinicaBAR#deleteClinica(com.qat.
	 * samples.sysmgmt.base.model.Clinica)
	 */
	@Override
	public InternalResponse deleteClinicaById(Clinica clinica) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE, clinica, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IClinicaBAR#deleteAllClinicas()
	 */
	@Override
	public InternalResponse deleteAllClinicas() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IClinicaBAR#fetchClinicaById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Clinica fetchClinicaById(FetchByIdRequest request) {
		return (Clinica) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CLINICA, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IClinicaBAR#fetchAllClinicasCache()
	 */
	@Override
	public InternalResultsResponse<Clinica> fetchAllClinicas(Clinica clinica) {
		InternalResultsResponse<Clinica> response = new InternalResultsResponse<Clinica>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CLINICA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IClinicaBAR#fetchClinicasByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Clinica> fetchClinicasByRequest(ClinicaInquiryRequest request) {
		InternalResultsResponse<Clinica> response = new InternalResultsResponse<Clinica>();
		fetchClinicasByRequest(getSqlSession(), request, STMT_FETCH_CLINICA_COUNT, STMT_FETCH_CLINICA_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchClinicasByRequest
	// ####======================================

	public static void fetchClinicasByRequest(SqlSession sqlSession, ClinicaInquiryRequest request,
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

	// ===================================### ADVOCACIA
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.IPessoaBAR.bar.IAdvocaciaBAR#insertAdvocacia(com.qat.samples.sysmgmt.base.model.Advocacia)
	 */
	@Override
	public InternalResponse insertAdvocacia(Advocacia advocacia) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ENTIDADE, advocacia, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IAdvocaciaBAR#updateAdvocacia(com.qat.
	 * samples.sysmgmt.base.model.Advocacia)
	 */
	@Override
	public InternalResponse updateAdvocacia(Advocacia advocacia) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ENTIDADE, advocacia, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IAdvocaciaBAR#deleteAdvocacia(com.qat.
	 * samples.sysmgmt.base.model.Advocacia)
	 */
	@Override
	public InternalResponse deleteAdvocaciaById(Advocacia advocacia) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE, advocacia, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IAdvocaciaBAR#deleteAllAdvocacias()
	 */
	@Override
	public InternalResponse deleteAllAdvocacias() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IAdvocaciaBAR#fetchAdvocaciaById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Advocacia fetchAdvocaciaById(FetchByIdRequest request) {
		return (Advocacia) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_ADVOCACIA,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IAdvocaciaBAR#fetchAllAdvocaciasCache()
	 */
	@Override
	public InternalResultsResponse<Advocacia> fetchAllAdvocacias(Advocacia advocacia) {
		InternalResultsResponse<Advocacia> response = new InternalResultsResponse<Advocacia>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_ADVOCACIA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IAdvocaciaBAR#fetchAdvocaciasByRequest(com.
	 * qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Advocacia> fetchAdvocaciasByRequest(AdvocaciaInquiryRequest request) {
		InternalResultsResponse<Advocacia> response = new InternalResultsResponse<Advocacia>();
		fetchAdvocaciasByRequest(getSqlSession(), request, STMT_FETCH_ADVOCACIA_COUNT, STMT_FETCH_ADVOCACIA_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchAdvocaciasByRequest
	// ####======================================

	public static void fetchAdvocaciasByRequest(SqlSession sqlSession, AdvocaciaInquiryRequest request,
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

}
