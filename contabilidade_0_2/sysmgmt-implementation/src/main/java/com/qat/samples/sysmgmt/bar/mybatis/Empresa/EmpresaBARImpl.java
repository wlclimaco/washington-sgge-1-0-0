package com.qat.samples.sysmgmt.bar.mybatis.Empresa;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Cadastros.ICadastrosBAR;
import com.qat.samples.sysmgmt.bar.Documentos.IDocumentoBAR;
import com.qat.samples.sysmgmt.bar.Email.IEmailBAR;
import com.qat.samples.sysmgmt.bar.Empresa.IEmpresaBAR;
import com.qat.samples.sysmgmt.bar.Endereco.IEnderecoBAR;
import com.qat.samples.sysmgmt.bar.Fiscal.IFiscalBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Notes.INotesBAR;
import com.qat.samples.sysmgmt.bar.Site.ISiteBAR;
import com.qat.samples.sysmgmt.bar.Socios.ISociosBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.bar.Telefone.ITelefoneBAR;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.ContaCorrenteBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.HistoricoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.PlanoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.SociosBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.UsuarioBARD;
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
public class EmpresaBARImpl extends SqlSessionDaoSupport implements IEmpresaBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### EMPRESA ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_EMPRESA = "EmpresaMap.";

/** The Constant STMT_INSERT_EMPRESA. */
private static final String STMT_INSERT_EMPRESA = NAMESPACE_EMPRESA + "insertEmpresa";

/** The Constant STMT_UPDATE_EMPRESA. */
private static final String STMT_UPDATE_EMPRESA = NAMESPACE_EMPRESA + "updateEmpresa";

/** The Constant STMT_DELETE_EMPRESA. */
private static final String STMT_DELETE_EMPRESA = NAMESPACE_EMPRESA + "deleteEmpresaById";

	/** The Constant STMT_DELETE_EMPRESA_ALL. */
	private static final String STMT_DELETE_EMPRESA_ALL = NAMESPACE_EMPRESA + "deleteAllEmpresas";

	/** The Constant STMT_FETCH_EMPRESA. */
	private static final String STMT_FETCH_EMPRESA = NAMESPACE_EMPRESA + "fetchEmpresaById";

	/** The Constant STMT_FETCH_EMPRESA_ALL. */
	private static final String STMT_FETCH_EMPRESA_ALL = NAMESPACE_EMPRESA + "fetchAllEmpresas";

	/** The Constant STMT_FETCH_EMPRESA_COUNT. */
	private static final String STMT_FETCH_EMPRESA_COUNT = NAMESPACE_EMPRESA + "fetchEmpresaRowCount";

	/** The Constant STMT_FETCH_EMPRESA_ALL_REQUEST. */
	private static final String STMT_FETCH_EMPRESA_ALL_REQUEST = NAMESPACE_EMPRESA + "fetchAllEmpresasRequest";

///===================================### FILIAL ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_FILIAL = "FilialMap.";

/** The Constant STMT_INSERT_FILIAL. */
private static final String STMT_INSERT_FILIAL = NAMESPACE_FILIAL + "insertFilial";

/** The Constant STMT_UPDATE_FILIAL. */
private static final String STMT_UPDATE_FILIAL = NAMESPACE_FILIAL + "updateFilial";

/** The Constant STMT_DELETE_FILIAL. */
private static final String STMT_DELETE_FILIAL = NAMESPACE_FILIAL + "deleteFilialById";

	/** The Constant STMT_DELETE_FILIAL_ALL. */
	private static final String STMT_DELETE_FILIAL_ALL = NAMESPACE_FILIAL + "deleteAllFilials";

	/** The Constant STMT_FETCH_FILIAL. */
	private static final String STMT_FETCH_FILIAL = NAMESPACE_FILIAL + "fetchFilialById";

	/** The Constant STMT_FETCH_FILIAL_ALL. */
	private static final String STMT_FETCH_FILIAL_ALL = NAMESPACE_FILIAL + "fetchAllFilials";

	/** The Constant STMT_FETCH_FILIAL_COUNT. */
	private static final String STMT_FETCH_FILIAL_COUNT = NAMESPACE_FILIAL + "fetchFilialRowCount";

	/** The Constant STMT_FETCH_FILIAL_ALL_REQUEST. */
	private static final String STMT_FETCH_FILIAL_ALL_REQUEST = NAMESPACE_FILIAL + "fetchAllFilialsRequest";

///===================================### DEPOSITO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_DEPOSITO = "DepositoMap.";

/** The Constant STMT_INSERT_DEPOSITO. */
private static final String STMT_INSERT_DEPOSITO = NAMESPACE_DEPOSITO + "insertDeposito";

/** The Constant STMT_UPDATE_DEPOSITO. */
private static final String STMT_UPDATE_DEPOSITO = NAMESPACE_DEPOSITO + "updateDeposito";

/** The Constant STMT_DELETE_DEPOSITO. */
private static final String STMT_DELETE_DEPOSITO = NAMESPACE_DEPOSITO + "deleteDepositoById";

	/** The Constant STMT_DELETE_DEPOSITO_ALL. */
	private static final String STMT_DELETE_DEPOSITO_ALL = NAMESPACE_DEPOSITO + "deleteAllDepositos";

	/** The Constant STMT_FETCH_DEPOSITO. */
	private static final String STMT_FETCH_DEPOSITO = NAMESPACE_DEPOSITO + "fetchDepositoById";

	/** The Constant STMT_FETCH_DEPOSITO_ALL. */
	private static final String STMT_FETCH_DEPOSITO_ALL = NAMESPACE_DEPOSITO + "fetchAllDepositos";

	/** The Constant STMT_FETCH_DEPOSITO_COUNT. */
	private static final String STMT_FETCH_DEPOSITO_COUNT = NAMESPACE_DEPOSITO + "fetchDepositoRowCount";

	/** The Constant STMT_FETCH_DEPOSITO_ALL_REQUEST. */
	private static final String STMT_FETCH_DEPOSITO_ALL_REQUEST = NAMESPACE_DEPOSITO + "fetchAllDepositosRequest";

///===================================### USUARIO ####======================================
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


	private static final String EMPRESA_STMT_INSERT = null;

//===================================### EMPRESA ####======================================

/** The endereco dac. */
	IEnderecoBAR enderecoBAR;

	ICadastrosBAR cidadeBAR;

	/** The telefone dac. */
	ITelefoneBAR telefoneBAR;

	/** The email dac. */
	IEmailBAR emailBAR;

	/** The socio dac. */
	ISociosBAR socioBAR;

	/** The cnae dac. */
	IFiscalBAR cnaeBAR;

	/** The documento dac. */
	IDocumentoBAR documentoBAR;

	/** The historico dac. */
	IHistoricoBAR historicoBAR;

	/** The status dac. */
	IStatusBAR statusBAR;

	ISiteBAR planoBAR;

	IEmpresaBAR usuarioBAR;

	INotesBAR noteBAR;


public IEnderecoBAR getEnderecoBAR() {
		return enderecoBAR;
	}

	public void setEnderecoBAR(IEnderecoBAR enderecoBAR) {
		this.enderecoBAR = enderecoBAR;
	}

	public ICadastrosBAR getCidadeBAR() {
		return cidadeBAR;
	}

	public void setCidadeBAR(ICadastrosBAR cidadeBAR) {
		this.cidadeBAR = cidadeBAR;
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

	public ISociosBAR getSocioBAR() {
		return socioBAR;
	}

	public void setSocioBAR(ISociosBAR socioBAR) {
		this.socioBAR = socioBAR;
	}

	public IFiscalBAR getCnaeBAR() {
		return cnaeBAR;
	}

	public void setCnaeBAR(IFiscalBAR cnaeBAR) {
		this.cnaeBAR = cnaeBAR;
	}

	public IDocumentoBAR getDocumentoBAR() {
		return documentoBAR;
	}

	public void setDocumentoBAR(IDocumentoBAR documentoBAR) {
		this.documentoBAR = documentoBAR;
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

	public ISiteBAR getPlanoBAR() {
		return planoBAR;
	}

	public void setPlanoBAR(ISiteBAR planoBAR) {
		this.planoBAR = planoBAR;
	}

	public IEmpresaBAR getUsuarioBAR() {
		return usuarioBAR;
	}

	public void setUsuarioBAR(IEmpresaBAR usuarioBAR) {
		this.usuarioBAR = usuarioBAR;
	}

	public INotesBAR getNoteBAR() {
		return noteBAR;
	}

	public void setNoteBAR(INotesBAR noteBAR) {
		this.noteBAR = noteBAR;
	}



	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.IEmailBAR.bar.IEmpresaBAR#insertEmpresa(com.qat.samples.sysmgmt.base.model.Empresa)
 */
@Override
public InternalResponse insertEmpresa(Empresa empresa)
{
	Integer historicoId = 0;
	Integer insertCount = 0;
	InternalResponse response = new InternalResponse();

	if (empresa.getModelAction() == PersistenceActionEnum.INSERT)
		{
			// First insert the root
			// Is successful the unique-id will be populated back into the object.
			insertCount = MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_EMPRESA, empresa, response);

			historicoId =
					HistoricoBARD.inserthistorico(empresa.getId(), empresa.getId(), empresa.getUserId(), (InternalResultsResponse<?>) response,
							TabelaEnum.EMPRESA, AcaoEnum.INSERT, historicoBAR);

			empresa.setProcessId(historicoId);

			insertCount = MyBatisBARHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, empresa, response);

			historicoId =
					HistoricoBARD.inserthistoricoItens(empresa.getId(), empresa.getUserId(), (InternalResultsResponse<?>) response,
							TabelaEnum.EMPRESA, AcaoEnum.INSERT, historicoId, getHistoricoBAR());

			//EmailUtilBARD.maintainEmailEnviar();
			//GerarTarefaBARD.maintainGerarTarefas();
			//GerarFinanceiroBARD.maintainGerarFinanceiro();

		}
		else
		{
			historicoId = empresa.getProcessId();
		}
		if (!ValidationUtil.isNullOrEmpty(empresa.getSocios()))
		{
			insertCount +=
					SociosBARD.maintainSocioAssociations(empresa.getSocios(), (InternalResultsResponse<?>) response, empresa.getId(), null, null,
							TabelaEnum.EMPRESA, getSocioBAR(), getStatusBAR(), getHistoricoBAR(), empresa.getId(),
							empresa.getCreateUser(), historicoId, historicoId, getDocumentoBAR());

		}
		if (!ValidationUtil.isNullOrEmpty(empresa.getPlanoList()))
		{
			insertCount +=
					PlanoBARD.maintainPlanoAssociations(empresa.getPlanoList(), response, empresa.getId(), null, null,
							TabelaEnum.EMPRESA, getPlanoBAR(), getStatusBAR(), getHistoricoBAR(), empresa.getId(),
							empresa.getCreateUser(), historicoId, historicoId);
		}

		if (!ValidationUtil.isNullOrEmpty(empresa.getUsuarioList()))
		{
			insertCount +=
					UsuarioBARD.maintainUsuarioAssociations(empresa.getUsuarioList(), (InternalResultsResponse<?>) response, empresa.getId(), null,
							null,
							TabelaEnum.EMPRESA, getUsuarioBAR(), getStatusBAR(), getHistoricoBAR(), empresa.getId(),
							empresa.getCreateUser(), historicoId, historicoId);
		}

		if (!ValidationUtil.isNullOrEmpty(empresa.getContaCorrenteList()))
		{
			insertCount +=
					ContaCorrenteBARD.maintainContaCorrenteAssociationsA(empresa.getContaCorrenteList(), response, empresa.getId(), null,
							null,
							TabelaEnum.EMPRESA, getUsuarioBAR(), getStatusBAR(), getHistoricoBAR(), empresa.getId(),
							empresa.getCreateUser(), historicoId, historicoId);
		}

	//	insertCount += EmpresaBARD.maintainInsertEntidade(empresa, historicoId, historicoId, TabelaEnum.EMPRESA, response);

		if (response.isInError())
		{
			return response;
		}


	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEmpresaBAR#updateEmpresa(com.qat.samples.sysmgmt.base.model.Empresa)
 */
@Override
public InternalResponse updateEmpresa(Empresa county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_EMPRESA, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEmpresaBAR#deleteEmpresa(com.qat.samples.sysmgmt.base.model.Empresa)
 */
@Override
public InternalResponse deleteEmpresaById(Empresa county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_EMPRESA, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEmpresaBAR#deleteAllEmpresas()
 */
@Override
public InternalResponse deleteAllEmpresas()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_EMPRESA_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IEmpresaBAR#fetchEmpresaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Empresa> fetchEmpresaById(FetchByIdRequest request)
{
	InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();
	response.addResult((Empresa)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_EMPRESA,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IEmpresaBAR#fetchAllEmpresasCache()
 */
@Override
public InternalResultsResponse<Empresa> fetchAllEmpresas(Empresa empresa)
{
	InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_EMPRESA_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IEmpresaBAR#fetchEmpresasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Empresa> fetchEmpresasByRequest(EmpresaInquiryRequest request)
{
	InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();
	fetchEmpresasByRequest(getSqlSession(), request, STMT_FETCH_EMPRESA_COUNT, STMT_FETCH_EMPRESA_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchEmpresasByRequest ####======================================

public static void fetchEmpresasByRequest(SqlSession sqlSession, EmpresaInquiryRequest request, String countStatement,
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


//===================================### FILIAL ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFilialBAR#insertFilial(com.qat.samples.sysmgmt.base.model.Filial)
 */
@Override
public InternalResponse insertFilial(Filial county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_FILIAL, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFilialBAR#updateFilial(com.qat.samples.sysmgmt.base.model.Filial)
 */
@Override
public InternalResponse updateFilial(Filial county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_FILIAL, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFilialBAR#deleteFilial(com.qat.samples.sysmgmt.base.model.Filial)
 */
@Override
public InternalResponse deleteFilialById(Filial county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_FILIAL, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFilialBAR#deleteAllFilials()
 */
@Override
public InternalResponse deleteAllFilials()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_FILIAL_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IFilialBAR#fetchFilialById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Filial> fetchFilialById(FetchByIdRequest request)
{
	InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();
	response.addResult((Filial)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_FILIAL,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFilialBAR#fetchAllFilialsCache()
 */
@Override
public InternalResultsResponse<Filial> fetchAllFilials(Filial filial)
{
	InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_FILIAL_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IFilialBAR#fetchFilialsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Filial> fetchFilialsByRequest(FilialInquiryRequest request)
{
	InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();
	fetchFilialsByRequest(getSqlSession(), request, STMT_FETCH_FILIAL_COUNT, STMT_FETCH_FILIAL_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchFilialsByRequest ####======================================

public static void fetchFilialsByRequest(SqlSession sqlSession, FilialInquiryRequest request, String countStatement,
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


//===================================### DEPOSITO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IDepositoBAR#insertDeposito(com.qat.samples.sysmgmt.base.model.Deposito)
 */
@Override
public InternalResponse insertDeposito(Deposito county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_DEPOSITO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IDepositoBAR#updateDeposito(com.qat.samples.sysmgmt.base.model.Deposito)
 */
@Override
public InternalResponse updateDeposito(Deposito county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_DEPOSITO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IDepositoBAR#deleteDeposito(com.qat.samples.sysmgmt.base.model.Deposito)
 */
@Override
public InternalResponse deleteDepositoById(Deposito county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_DEPOSITO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IDepositoBAR#deleteAllDepositos()
 */
@Override
public InternalResponse deleteAllDepositos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_DEPOSITO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IDepositoBAR#fetchDepositoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Deposito> fetchDepositoById(FetchByIdRequest request)
{
	InternalResultsResponse<Deposito> response = new InternalResultsResponse<Deposito>();
	response.addResult((Deposito)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_DEPOSITO,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IDepositoBAR#fetchAllDepositosCache()
 */
@Override
public InternalResultsResponse<Deposito> fetchAllDepositos(Deposito deposito)
{
	InternalResultsResponse<Deposito> response = new InternalResultsResponse<Deposito>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_DEPOSITO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IDepositoBAR#fetchDepositosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Deposito> fetchDepositosByRequest(DepositoInquiryRequest request)
{
	InternalResultsResponse<Deposito> response = new InternalResultsResponse<Deposito>();
	fetchDepositosByRequest(getSqlSession(), request, STMT_FETCH_DEPOSITO_COUNT, STMT_FETCH_DEPOSITO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchDepositosByRequest ####======================================

public static void fetchDepositosByRequest(SqlSession sqlSession, DepositoInquiryRequest request, String countStatement,
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


//===================================### USUARIO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IUsuarioBAR#insertUsuario(com.qat.samples.sysmgmt.base.model.Usuario)
 */
@Override
public InternalResponse insertUsuario(Usuario county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_USUARIO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IUsuarioBAR#updateUsuario(com.qat.samples.sysmgmt.base.model.Usuario)
 */
@Override
public InternalResponse updateUsuario(Usuario county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_USUARIO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IUsuarioBAR#deleteUsuario(com.qat.samples.sysmgmt.base.model.Usuario)
 */
@Override
public InternalResponse deleteUsuarioById(Usuario county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_USUARIO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IUsuarioBAR#deleteAllUsuarios()
 */
@Override
public InternalResponse deleteAllUsuarios()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_USUARIO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IUsuarioBAR#fetchUsuarioById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Usuario> fetchUsuarioById(FetchByIdRequest request)
{
	InternalResultsResponse<Usuario> response = new InternalResultsResponse<Usuario>();
	response.addResult((Usuario)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_USUARIO,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IUsuarioBAR#fetchAllUsuariosCache()
 */
@Override
public InternalResultsResponse<Usuario> fetchAllUsuarios(Usuario usuario)
{
	InternalResultsResponse<Usuario> response = new InternalResultsResponse<Usuario>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_USUARIO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IUsuarioBAR#fetchUsuariosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Usuario> fetchUsuariosByRequest(UsuarioInquiryRequest request)
{
	InternalResultsResponse<Usuario> response = new InternalResultsResponse<Usuario>();
	fetchUsuariosByRequest(getSqlSession(), request, STMT_FETCH_USUARIO_COUNT, STMT_FETCH_USUARIO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchUsuariosByRequest ####======================================

public static void fetchUsuariosByRequest(SqlSession sqlSession, UsuarioInquiryRequest request, String countStatement,
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
