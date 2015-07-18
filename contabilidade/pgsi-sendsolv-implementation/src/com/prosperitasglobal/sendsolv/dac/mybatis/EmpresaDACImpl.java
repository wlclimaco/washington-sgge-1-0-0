package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
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
import com.prosperitasglobal.sendsolv.model.AcaoEnum;
import com.prosperitasglobal.sendsolv.model.CdStatusTypeEnum;
import com.prosperitasglobal.sendsolv.model.Empresa;
import com.prosperitasglobal.sendsolv.model.Historico;
import com.prosperitasglobal.sendsolv.model.HistoricoItens;
import com.prosperitasglobal.sendsolv.model.Process;
import com.prosperitasglobal.sendsolv.model.Status;
import com.prosperitasglobal.sendsolv.model.TabelaEnum;
import com.prosperitasglobal.sendsolv.model.request.EmpresaInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class EmpresaDACImpl.
 */
public class EmpresaDACImpl extends SqlSessionDaoSupport implements IEmpresaDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "EmpresaMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchEmpresaRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllEmpresasByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchEmpresaById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertEmpresa";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateEmpresa";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteEmpresaById";

	/** The endereco dac. */
	IEnderecoDAC enderecoDAC;

	/** The telefone dac. */
	ITelefoneDAC telefoneDAC;

	/** The email dac. */
	IEmailDAC emailDAC;

	/** The socio dac. */
	ISociosDAC socioDAC;

	/** The cnae dac. */
	ICnaeDAC cnaeDAC;

	/** The documento dac. */
	IDocumentoDAC documentoDAC;

	/** The historico dac. */
	IHistoricoDAC historicoDAC;

	/** The status dac. */
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
	/**
	 * Gets the endereco dac.
	 *
	 * @return the endereco dac
	 */
	public IEnderecoDAC getEnderecoDAC()
	{
		return enderecoDAC;
	}

	/**
	 * Sets the endereco dac.
	 *
	 * @param enderecoDAC the new endereco dac
	 */
	public void setEnderecoDAC(IEnderecoDAC enderecoDAC)
	{
		this.enderecoDAC = enderecoDAC;
	}

	/**
	 * Gets the telefone dac.
	 *
	 * @return the telefone dac
	 */
	public ITelefoneDAC getTelefoneDAC()
	{
		return telefoneDAC;
	}

	/**
	 * Sets the telefone dac.
	 *
	 * @param telefoneDAC the new telefone dac
	 */
	public void setTelefoneDAC(ITelefoneDAC telefoneDAC)
	{
		this.telefoneDAC = telefoneDAC;
	}

	/**
	 * Gets the email dac.
	 *
	 * @return the email dac
	 */
	public IEmailDAC getEmailDAC()
	{
		return emailDAC;
	}

	/**
	 * Sets the email dac.
	 *
	 * @param emailDAC the new email dac
	 */
	public void setEmailDAC(IEmailDAC emailDAC)
	{
		this.emailDAC = emailDAC;
	}

	/**
	 * Gets the socio dac.
	 *
	 * @return the socio dac
	 */
	public ISociosDAC getSocioDAC()
	{
		return socioDAC;
	}

	/**
	 * Sets the socio dac.
	 *
	 * @param socioDAC the new socio dac
	 */
	public void setSocioDAC(ISociosDAC socioDAC)
	{
		this.socioDAC = socioDAC;
	}

	/**
	 * Gets the cnae dac.
	 *
	 * @return the cnae dac
	 */
	public ICnaeDAC getCnaeDAC()
	{
		return cnaeDAC;
	}

	/**
	 * Sets the cnae dac.
	 *
	 * @param cnaeDAC the new cnae dac
	 */
	public void setCnaeDAC(ICnaeDAC cnaeDAC)
	{
		this.cnaeDAC = cnaeDAC;
	}

	/**
	 * Gets the documento dac.
	 *
	 * @return the documento dac
	 */
	public IDocumentoDAC getDocumentoDAC()
	{
		return documentoDAC;
	}

	/**
	 * Sets the documento dac.
	 *
	 * @param documentoDAC the new documento dac
	 */
	public void setDocumentoDAC(IDocumentoDAC documentoDAC)
	{
		this.documentoDAC = documentoDAC;
	}

	/**
	 * Gets the historico dac.
	 *
	 * @return the historico dac
	 */
	public IHistoricoDAC getHistoricoDAC()
	{
		return historicoDAC;
	}

	/**
	 * Sets the historico dac.
	 *
	 * @param historicoDAC the new historico dac
	 */
	public void setHistoricoDAC(IHistoricoDAC historicoDAC)
	{
		this.historicoDAC = historicoDAC;
	}

	/**
	 * Gets the status dac.
	 *
	 * @return the status dac
	 */
	public IStatusDAC getStatusDAC()
	{
		return statusDAC;
	}

	/**
	 * Sets the status dac.
	 *
	 * @param statusDAC the new status dac
	 */
	public void setStatusDAC(IStatusDAC statusDAC)
	{
		this.statusDAC = statusDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IEmpresaDAC#insertEmpresa(com.prosperitasglobal.sendsolv.model.Empresa)
	 */
	@Override
	public InternalResultsResponse<Empresa> insertEmpresa(Empresa empresa)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();
		Historico historico = new Historico();
		historico.setEmprId(empresa.getId());
		historico.setUserId(empresa.getUserId());
		historico.setProcessId(0);
		Date a = new Date();
		historico.setData(a.getTime());

		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistorico", historico, response);

		Integer historicoId = historico.getId();

		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(historicoId);
		historicoItens.setProcessId(0);
		historicoItens.setParentId(empresa.getId());
		historicoItens.setTabelaEnum(TabelaEnum.EMPRESA);
		historicoItens.setAcaoType(AcaoEnum.INSERT);

		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistoricoItens", historicoItens,
						response);

		Integer processId = historicoId;

		empresa.setProcessId(processId);
		empresa.setModifyDateUTC(a.getTime());

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, empresa, response);

		historicoId = historico.getId();

		if (response.isInError())
		{
			return response;
		}

		insertCount +=
				EnderecoDACD.maintainEnderecoAssociations(empresa.getEnderecos(), response, empresa.getId(), null,
						null,
						TabelaEnum.EMPRESA, getEnderecoDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getCreateUser(), processId, historicoId);

		insertCount +=
				CnaeDACD.maintainCnaeAssociations(empresa.getCnaes(), response, empresa.getId(), null, null,
						TabelaEnum.EMPRESA, getCnaeDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getCreateUser(), processId, historicoId);

		insertCount +=
				EmailDACD.maintainEmailAssociations(empresa.getEmails(), response, empresa.getId(), null, null,
						TabelaEnum.EMPRESA, getEmailDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getCreateUser(), processId, historicoId);

		insertCount +=
				TelefoneDACD.maintainTelefoneAssociations(empresa.getTelefones(), response, empresa.getId(), null,
						null,
						TabelaEnum.EMPRESA, getTelefoneDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getCreateUser(), processId, historicoId);

		insertCount +=
				DocumentosDACD.maintainDocumentoAssociations(empresa.getDocumentos(), response, empresa.getId(), null,
						null,
						TabelaEnum.EMPRESA, getDocumentoDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getCreateUser(), processId, historicoId);

		insertCount +=
				SociosDACD.maintainSocioAssociations(empresa.getSocios(), response, empresa.getId(), null, null,
						TabelaEnum.EMPRESA, getSocioDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getCreateUser(), processId, historicoId);

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ANALISANDO);
			List<Status> statusList = new ArrayList<Status>();
			insertCount =
					StatusDACD.maintainStatusAssociations(statusList, response, empresa.getId(), null, AcaoEnum.INSERT,
							empresa.getCreateUser(), empresa.getId(), TabelaEnum.EMPRESA, getStatusDAC(),
							getHistoricoDAC(), processId, historicoId);

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
		process.setEmprId(empresa.getId());
		process.setTabelaEnum(TabelaEnum.EMPRESA);
		process.setUserId(empresa.getUserId());
		process.setAcaoType(AcaoEnum.UPDATE);

		Integer processId = 0;

		processId = (Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), "ProcessMap.insertProcess", process);

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
						empresa.getModifyUser(), processId, null);

		updateCount +=
				CnaeDACD.maintainCnaeAssociations(empresa.getCnaes(), response, updateCount, null, null,
						null, getCnaeDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getModifyUser(), processId, null);

		updateCount +=
				EmailDACD.maintainEmailAssociations(empresa.getEmails(), response, updateCount, null, null,
						null, getEmailDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getModifyUser(), processId, null);

		updateCount +=
				TelefoneDACD.maintainTelefoneAssociations(empresa.getTelefones(), response, updateCount, null, null,
						null, getTelefoneDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getModifyUser(), processId, null);

		updateCount +=
				DocumentosDACD.maintainDocumentoAssociations(empresa.getDocumentos(), response, updateCount, null,
						null,
						null, getDocumentoDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getModifyUser(), processId, null);

		updateCount +=
				SociosDACD.maintainSocioAssociations(empresa.getSocios(), response, updateCount, null, null,
						null, getSocioDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getModifyUser(), processId, null);

		if (updateCount > 0)
		{
			updateCount =
					StatusDACD.maintainStatusAssociations(empresa.getStatusList(), response, empresa.getId(), null,
							AcaoEnum.INSERT, empresa.getModifyUser(), empresa.getId(), TabelaEnum.EMPRESA,
							getStatusDAC(), getHistoricoDAC(), processId, null);

		}

		// Finally,processId,nullocessId,nulls updated then add the Person to the result.
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

		Process process = new Process();
		process.setEmprId(empresa.getId());
		process.setTabelaEnum(TabelaEnum.EMPRESA);
		process.setUserId(empresa.getUserId());
		process.setAcaoType(AcaoEnum.UPDATE);

		Integer processId = 0;

		processId = (Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), "ProcessMap.insertProcess", process);

		empresa.setProcessId(processId);

		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, empresa, response);
		Integer updateCount;

		Status status = new Status();
		status.setStatus(CdStatusTypeEnum.DELETADO);
		List<Status> statusList = new ArrayList<Status>();
		updateCount =
				StatusDACD
						.maintainStatusAssociations(statusList, (InternalResultsResponse<?>)response, empresa.getId(),
								null, AcaoEnum.DELETE,
								empresa.getCreateUser(), empresa.getId(), TabelaEnum.EMPRESA, getStatusDAC(),
								getHistoricoDAC(), processId, null);

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
