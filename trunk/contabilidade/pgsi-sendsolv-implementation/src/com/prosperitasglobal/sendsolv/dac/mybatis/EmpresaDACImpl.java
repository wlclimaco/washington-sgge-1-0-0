package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.prosperitasglobal.sendsolv.dac.ICnaeDAC;
import com.prosperitasglobal.sendsolv.dac.IDocumentoDAC;
import com.prosperitasglobal.sendsolv.dac.IEmailDAC;
import com.prosperitasglobal.sendsolv.dac.IEmpresaDAC;
import com.prosperitasglobal.sendsolv.dac.IEnderecoDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.ISociosDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.dac.ITelefoneDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.CnaeDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.DocumentosDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.EmailDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.EnderecoDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.SociosDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.StatusDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.TelefoneDACD;

/**
 * The Class EmpresaDACImpl.
 */
public class EmpresaDACImpl extends SqlSessionDaoSupport implements IEmpresaDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "EmpresaMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchEmpresaRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllEmpresasByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchEmpresaById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertEmpresa";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_CONTACT = EMPRESA_NAMESPACE
			+ "associateEmpresaWithContact";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateEmpresa";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteEmpresaById";

	/** The Constant EMPRESA_DOCUMENT_STMT_UPDATE. */
	private static final String EMPRESA_DOCUMENT_STMT_UPDATE = EMPRESA_NAMESPACE
			+ "updateEmpresaDocument";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT = EMPRESA_NAMESPACE
			+ "associateEmpresaWithDocument";

	/** The Constant EMPRESA_STMT_DELETE_DOCUMENT. */
	private static final String EMPRESA_STMT_DELETE_DOCUMENT = EMPRESA_NAMESPACE
			+ "deleteEmpresaDocument";

	/** The Constant STMT_VERSION. */
	private static final String EMPRESA_STMT_VERSION = EMPRESA_NAMESPACE + "fetchVersionNumber";

	/** The Constant EMPRESA_STMT_UPDATE_EMPRESA_STATUS. */
	private static final String EMPRESA_STMT_UPDATE_EMPRESA_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	IEnderecoDAC enderecoDAC;
	ITelefoneDAC telefoneDAC;
	IEmailDAC emailDAC;
	ISociosDAC socioDAC;
	ICnaeDAC cnaeDAC;
	IDocumentoDAC documentoDAC;
	IHistoricoDAC historicoDAC;
	IStatusDAC statusDAC;

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EmpresaDACImpl.class);

	/** The valid sort fields for an empresa inquiry. Will be injected by Spring. */
	private Map<String, String> empresaInquiryValidSortFields;

	/**
	 * Get the valid sort fields for the empresa inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the empresa inquiry.
	 */
	public Map<String, String> getEmpresaInquiryValidSortFields()
	{
		return empresaInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the empresa inquiry. Attribute injected by Spring.
	 *
	 * @param empresaInquiryValidSortFields The valid sort fields for the empresa inquiry to set.
	 */
	public void setEmpresaInquiryValidSortFields(Map<String, String> empresaInquiryValidSortFields)
	{
		this.empresaInquiryValidSortFields = empresaInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IEmpresaDAC#insertEmpresa(com.prosperitasglobal.sendsolv.model
	 * .Empresa)
	 */
	public IEnderecoDAC getEnderecoDAC()
	{
		return enderecoDAC;
	}

	public void setEnderecoDAC(IEnderecoDAC enderecoDAC)
	{
		this.enderecoDAC = enderecoDAC;
	}

	public ITelefoneDAC getTelefoneDAC()
	{
		return telefoneDAC;
	}

	public void setTelefoneDAC(ITelefoneDAC telefoneDAC)
	{
		this.telefoneDAC = telefoneDAC;
	}

	public IEmailDAC getEmailDAC()
	{
		return emailDAC;
	}

	public void setEmailDAC(IEmailDAC emailDAC)
	{
		this.emailDAC = emailDAC;
	}

	public ISociosDAC getSocioDAC()
	{
		return socioDAC;
	}

	public void setSocioDAC(ISociosDAC socioDAC)
	{
		this.socioDAC = socioDAC;
	}

	public ICnaeDAC getCnaeDAC()
	{
		return cnaeDAC;
	}

	public void setCnaeDAC(ICnaeDAC cnaeDAC)
	{
		this.cnaeDAC = cnaeDAC;
	}

	public IDocumentoDAC getDocumentoDAC()
	{
		return documentoDAC;
	}

	public void setDocumentoDAC(IDocumentoDAC documentoDAC)
	{
		this.documentoDAC = documentoDAC;
	}

	public IHistoricoDAC getHistoricoDAC()
	{
		return historicoDAC;
	}

	public void setHistoricoDAC(IHistoricoDAC historicoDAC)
	{
		this.historicoDAC = historicoDAC;
	}

	public IStatusDAC getStatusDAC()
	{
		return statusDAC;
	}

	public void setStatusDAC(IStatusDAC statusDAC)
	{
		this.statusDAC = statusDAC;
	}

	@Override
	public InternalResultsResponse<Empresa> insertEmpresa(Empresa empresa)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();

		Process process = new Process();
		process.setEmpId(empresa.getEmpId());
		process.setTabela(TabelaTypeEnum.EMPRESA);
		process.setUserId(empresa.getUserId());
		process.setAcaoType(AcaoEnum.INSERT);

		Integer processId = 0;

		processId = (Integer)doQueryForObject(getSqlSession(), "ProcessMap.insertProcess", process);

		empresa.setProcessId(processId);

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, empresa, response);

		if (response.isInError())
		{
			return response;
		}

		insertCount +=
				EnderecoDACD.maintainEnderecoAssociations(empresa.getEnderecos(), response, insertCount, null, null,
						null, getEnderecoDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getCreateUser(), processId);

		insertCount +=
				CnaeDACD.maintainCnaeAssociations(empresa.getCnaes(), response, insertCount, null, null,
						null, getCnaeDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getCreateUser(), processId);

		insertCount +=
				EmailDACD.maintainEmailAssociations(empresa.getEmails(), response, insertCount, null, null,
						null, getEmailDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getCreateUser(), processId);

		insertCount +=
				TelefoneDACD.maintainTelefoneAssociations(empresa.getTelefones(), response, insertCount, null, null,
						null, getTelefoneDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getCreateUser(), processId);

		insertCount +=
				DocumentosDACD.maintainDocumentoAssociations(empresa.getDocumentos(), response, insertCount, null,
						null,
						null, getDocumentoDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getCreateUser(), processId);

		insertCount +=
				SociosDACD.maintainSocioAssociations(empresa.getSocios(), response, insertCount, null, null,
						null, getSocioDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getCreateUser(), processId);

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new ArrayList<Status>();
			insertCount =
					StatusDACD.maintainStatusAssociations(statusList, response, empresa.getId(), null, AcaoEnum.INSERT,
							empresa.getCreateUser(), empresa.getId(), TabelaEnum.EMPRESA, getStatusDAC(),
							getHistoricoDAC(), processId);

		}

		// Finally, if something was inserted then add the Empresa to the result.
		if (insertCount > 0)
		{
			response.addResult(empresa);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IEmpresaDAC#updateEmpresa(com.prosperitasglobal.sendsolv.model
	 * .Empresa)
	 */
	@Override
	public InternalResultsResponse<Empresa> updateEmpresa(Empresa empresa)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();

		Process process = new Process();
		process.setEmpId(empresa.getEmpId());
		process.setTabela(TabelaTypeEnum.EMPRESA);
		process.setUserId(empresa.getUserId());
		process.setAcaoType(AcaoEnum.INSERT);

		Integer processId = 0;

		processId = (Integer)doQueryForObject(getSqlSession(), "ProcessMap.insertProcess", process);

		empresa.setProcessId(processId);

		// First update the root if necessary.
		if (!ValidationUtil.isNull(empresa.getModelAction())
				&& (empresa.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, empresa,
							response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount +=
				EnderecoDACD.maintainEnderecoAssociations(empresa.getEnderecos(), response, updateCount, null, null,
						null, getEnderecoDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getModifyUser(), processId);

		updateCount +=
				CnaeDACD.maintainCnaeAssociations(empresa.getCnaes(), response, updateCount, null, null,
						null, getCnaeDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getModifyUser(), processId);

		updateCount +=
				EmailDACD.maintainEmailAssociations(empresa.getEmails(), response, updateCount, null, null,
						null, getEmailDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getModifyUser(), processId);

		updateCount +=
				TelefoneDACD.maintainTelefoneAssociations(empresa.getTelefones(), response, updateCount, null, null,
						null, getTelefoneDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getModifyUser(), processId);

		updateCount +=
				DocumentosDACD.maintainDocumentoAssociations(empresa.getDocumentos(), response, updateCount, null,
						null,
						null, getDocumentoDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getModifyUser(), processId);

		updateCount +=
				SociosDACD.maintainSocioAssociations(empresa.getSocios(), response, updateCount, null, null,
						null, getSocioDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getModifyUser(), processId);

		if (updateCount > 0)
		{
			updateCount =
					StatusDACD.maintainStatusAssociations(empresa.getStatusList(), response, empresa.getId(), null,
							AcaoEnum.INSERT, empresa.getModifyUser(), empresa.getId(), TabelaEnum.EMPRESA,
							getStatusDAC(), getHistoricoDAC(), processId);

		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(empresa);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IEmpresaDAC#deleteEmpresa(com.prosperitasglobal.sendsolv.model
	 * .Empresa)
	 */
	@Override
	public InternalResponse deleteEmpresa(Empresa empresa)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, empresa, response);
		Integer updateCount;
		// updateCount +=
		// EnderecoDACD.maintainHistoricoAssociations(empresa.getEStatus, response, insertCount, null, null,
		// null);

		Status status = new Status();
		status.setStatus(StatusEnum.INACTIVE);
		List<Status> statusList = new ArrayList<Status>();
		updateCount =
				StatusDACD
						.maintainStatusAssociations(statusList, (InternalResultsResponse<?>)response, empresa.getId(),
								null, AcaoEnum.DELETE,
								empresa.getCreateUser(), empresa.getId(), TabelaEnum.EMPRESA, getStatusDAC(),
								getHistoricoDAC());

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			((InternalResultsResponse<Empresa>)response).addResult(empresa);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IEmpresaDAC#fetchEmpresaById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> fetchEmpresaById(FetchByIdRequest request)
	{
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IEmpresaDAC#fetchEmpresaByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> fetchEmpresaByRequest(EmpresaInquiryRequest request)
	{
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequestEmpresa(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

}
