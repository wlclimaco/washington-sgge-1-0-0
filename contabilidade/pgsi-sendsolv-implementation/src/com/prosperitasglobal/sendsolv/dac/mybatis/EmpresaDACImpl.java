package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.ArrayList;
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
import com.prosperitasglobal.sendsolv.dac.IPlanoDAC;
import com.prosperitasglobal.sendsolv.dac.ISociosDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.dac.ITelefoneDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.CnaeDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.DocumentosDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.EmailDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.EnderecoDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.HistoricoDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PlanoDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.SociosDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.StatusDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.TelefoneDACD;
import com.prosperitasglobal.sendsolv.model.AcaoEnum;
import com.prosperitasglobal.sendsolv.model.CdStatusTypeEnum;
import com.prosperitasglobal.sendsolv.model.Cidade;
import com.prosperitasglobal.sendsolv.model.Classificacao;
import com.prosperitasglobal.sendsolv.model.Cnae;
import com.prosperitasglobal.sendsolv.model.Deposito;
import com.prosperitasglobal.sendsolv.model.Empresa;
import com.prosperitasglobal.sendsolv.model.Entidade;
import com.prosperitasglobal.sendsolv.model.Filial;
import com.prosperitasglobal.sendsolv.model.Plano;
import com.prosperitasglobal.sendsolv.model.Regime;
import com.prosperitasglobal.sendsolv.model.Status;
import com.prosperitasglobal.sendsolv.model.TabelaEnum;
import com.prosperitasglobal.sendsolv.model.request.CidadeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ClassificacaoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CnaeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.DepositoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.EmpresaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FilialInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.PlanoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RegimeInquiryRequest;
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

	private static final String FILIAL_NAMESPACE = "FilialMap.";

	private static final String ENTIDADE_NAMESPACE = "EntidadeMap.";

	private static final String DEPOSITO_NAMESPACE = "DepositoMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchEmpresaRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllEmpresasByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchEmpresaById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = ENTIDADE_NAMESPACE + "insertEntidade";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = ENTIDADE_NAMESPACE + "updateEmpresa";

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

	IPlanoDAC planoDAC;

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EmpresaDACImpl.class);

	private static final String FILIAL_STMT_INSERT = ENTIDADE_NAMESPACE + "insertEntidade";

	private static final String FILIAL_STMT_UPDATE = ENTIDADE_NAMESPACE + "updateEntidade";

	private static final String FILIAL_STMT_FETCH_BY_ID = FILIAL_NAMESPACE + "fetchFilialById";

	private static final String DEPOSITO_STMT_FETCH_BY_ID = DEPOSITO_NAMESPACE + "fetchFilialById";

	private static final String FILIAL_STMT_FETCH_COUNT = FILIAL_NAMESPACE + "fetchFilialRowCount";

	private static final String FILIAL_STMT_FETCH_ALL_BY_REQUEST = FILIAL_NAMESPACE + "fetchAllFilialsByRequest";

	private static final String DEPOSITO_STMT_INSERT = ENTIDADE_NAMESPACE + "insertEntidade";

	private static final String DEPOSITO_STMT_UPDATE = ENTIDADE_NAMESPACE + "updateEntidade";

	private static final String DEPOSITO_STMT_FETCH_COUNT = DEPOSITO_NAMESPACE + "fetchDepositoRowCount";

	private static final String DEPOSITO_STMT_FETCH_ALL_BY_REQUEST = DEPOSITO_NAMESPACE + "fetchAllDepositosByRequest";

	private static final String CNAE_STMT_FETCH_COUNT = "CnaeMap.fetchCnaeRowCount";

	private static final String CNAE_STMT_FETCH_ALL_BY_REQUEST = "CnaeMap.fetchAllCnaesByRequest";

	private static final String REGIME_STMT_FETCH_COUNT = "RegimeMap.fetchRegimeRowCount";;

	private static final String REGIME_STMT_FETCH_ALL_BY_REQUEST = "RegimeMap.fetchAllRegimesByRequest";

	private static final String CIDADE_STMT_FETCH_COUNT = "CidadeMap.fetchCidadeRowCount";;

	private static final String CIDADE_STMT_FETCH_ALL_BY_REQUEST = "CidadeMap.fetchAllCidadesByRequest";

	private static final String PLANO_STMT_FETCH_COUNT = "PlanoMap.fetchPlanoRowCount";;

	private static final String PLANO_STMT_FETCH_ALL_BY_REQUEST = "PlanoMap.fetchAllPlanosByRequest";

	private static final String CLASSIFICACAO_STMT_FETCH_COUNT = "ClassificacaoMap.fetchClassificacaoRowCount";;

	private static final String CLASSIFICACAO_STMT_FETCH_ALL_BY_REQUEST =
			"ClassificacaoMap.fetchAllClassificacaosByRequest";

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
	 * @return the planoDAC
	 */
	public IPlanoDAC getPlanoDAC()
	{
		return planoDAC;
	}

	/**
	 * @param planoDAC the planoDAC to set
	 */
	public void setPlanoDAC(IPlanoDAC planoDAC)
	{
		this.planoDAC = planoDAC;
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

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, empresa, response);

		Integer historicoId =
				HistoricoDACD.inserthistorico(empresa.getId(), empresa.getId(), empresa.getUserId(), response,
						TabelaEnum.EMPRESA, AcaoEnum.INSERT, historicoDAC);

		insertCount +=
				SociosDACD.maintainSocioAssociations(empresa.getSocios(), response, empresa.getId(), null, null,
						TabelaEnum.EMPRESA, getSocioDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getCreateUser(), historicoId, historicoId);

		insertCount +=
				PlanoDACD.maintainPlanoAssociations(empresa.getPlanoList(), response, empresa.getId(), null, null,
						TabelaEnum.EMPRESA, getPlanoDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getCreateUser(), historicoId, historicoId);

		insertCount += insertAssociations(empresa, historicoId, historicoId, TabelaEnum.EMPRESA, response);

		if (response.isInError())
		{
			return response;
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
		Integer historicoId = 0;
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(empresa.getModelAction())
				&& (empresa.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, empresa,
							response);
			historicoId = empresa.getProcessId();
		}
		else
		{

			historicoId =
					HistoricoDACD.inserthistorico(empresa.getId(), empresa.getId(), empresa.getUserId(), response,
							TabelaEnum.EMPRESA, AcaoEnum.INSERT, historicoDAC);
		}
		updateCount +=
				SociosDACD.maintainSocioAssociations(empresa.getSocios(), response, empresa.getId(), null, null,
						TabelaEnum.EMPRESA, getSocioDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getCreateUser(), historicoId, historicoId);

		updateCount +=
				PlanoDACD.maintainPlanoAssociations(empresa.getPlanoList(), response, empresa.getId(), null, null,
						TabelaEnum.EMPRESA, getPlanoDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getCreateUser(), historicoId, historicoId);

		updateCount += insertAssociations(empresa, historicoId, historicoId, TabelaEnum.EMPRESA, response);

		if (response.isInError())
		{
			return response;
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

		Integer updateCount;

		Status status = new Status();
		status.setStatus(CdStatusTypeEnum.DELETADO);
		List<Status> statusList = new ArrayList<Status>();
		updateCount =
				StatusDACD
						.maintainStatusAssociations(statusList, (InternalResultsResponse<?>)response, empresa.getId(),
								null, AcaoEnum.DELETE,
								empresa.getCreateUser(), empresa.getId(), TabelaEnum.EMPRESA, getStatusDAC(),
								getHistoricoDAC(), empresa.getProcessId(), null);

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

	@Override
	public InternalResultsResponse<Filial> insertFilial(Filial filial)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), FILIAL_STMT_INSERT, filial, response);

		Integer historicoId =
				HistoricoDACD.inserthistorico(filial.getId(), filial.getEmprId(), filial.getUserId(), response,
						TabelaEnum.FILIAL, AcaoEnum.INSERT, historicoDAC);

		insertCount += insertAssociations(filial, historicoId, historicoId, TabelaEnum.FILIAL, response);

		if (response.isInError())
		{
			return response;
		}

		// Finally, if something was inserted then add the Empresa to the result.
		if (insertCount > 0)
		{
			response.addResult(filial);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Filial> updateFilial(Filial filial)
	{
		Integer updateCount = 0;
		Integer historicoId = 0;
		InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(filial.getModelAction())
				&& (filial.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), FILIAL_STMT_UPDATE, filial,
							response);
			historicoId = filial.getProcessId();
		}
		else
		{

			historicoId =
					HistoricoDACD.inserthistorico(filial.getId(), filial.getEmprId(), filial.getUserId(), response,
							TabelaEnum.FILIAL, AcaoEnum.INSERT, historicoDAC);
		}

		updateCount += insertAssociations(filial, historicoId, historicoId, TabelaEnum.FILIAL, response);

		if (response.isInError())
		{
			return response;
		}

		// Finally,processId,nullocessId,nulls updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(filial);
		}

		return response;
	}

	@Override
	public InternalResponse deleteFilial(Filial filial)
	{
		InternalResponse response = new InternalResponse();

		Integer updateCount;

		Status status = new Status();
		status.setStatus(CdStatusTypeEnum.DELETADO);
		List<Status> statusList = new ArrayList<Status>();
		updateCount =
				StatusDACD
						.maintainStatusAssociations(statusList, (InternalResultsResponse<?>)response, filial.getId(),
								null, AcaoEnum.DELETE,
								filial.getCreateUser(), filial.getEmprId(), TabelaEnum.EMPRESA, getStatusDAC(),
								getHistoricoDAC(), filial.getProcessId(), null);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			((InternalResultsResponse<Filial>)response).addResult(filial);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Filial> fetchFilialById(FetchByIdRequest request)
	{
		InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), FILIAL_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Filial> fetchFilialByRequest(FilialInquiryRequest request)
	{
		InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, FILIAL_STMT_FETCH_COUNT,
				FILIAL_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Deposito> insertDeposito(Deposito deposito)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Deposito> response = new InternalResultsResponse<Deposito>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), DEPOSITO_STMT_INSERT, deposito, response);

		Integer historicoId =
				HistoricoDACD.inserthistorico(deposito.getId(), deposito.getEmprId(), deposito.getUserId(), response,
						TabelaEnum.DEPOSITO, AcaoEnum.INSERT, historicoDAC);

		insertCount += insertAssociations(deposito, historicoId, historicoId, TabelaEnum.DEPOSITO, response);

		if (response.isInError())
		{
			return response;
		}

		// Finally, if something was inserted then add the Empresa to the result.
		if (insertCount > 0)
		{
			response.addResult(deposito);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Deposito> updateDeposito(Deposito deposito)
	{
		Integer updateCount = 0;
		Integer historicoId = 0;
		InternalResultsResponse<Deposito> response = new InternalResultsResponse<Deposito>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(deposito.getModelAction())
				&& (deposito.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), DEPOSITO_STMT_UPDATE, deposito,
							response);
			historicoId = deposito.getProcessId();
		}
		else
		{

			historicoId =
					HistoricoDACD.inserthistorico(deposito.getId(), deposito.getEmprId(), deposito.getUserId(),
							response,
							TabelaEnum.DEPOSITO, AcaoEnum.INSERT, historicoDAC);
		}

		updateCount += insertAssociations(deposito, historicoId, historicoId, TabelaEnum.DEPOSITO, response);

		if (response.isInError())
		{
			return response;
		}

		// Finally,processId,nullocessId,nulls updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(deposito);
		}

		return response;
	}

	@Override
	public InternalResponse deleteDeposito(Deposito deposito)
	{
		InternalResponse response = new InternalResponse();

		Integer updateCount;

		Status status = new Status();
		status.setStatus(CdStatusTypeEnum.DELETADO);
		List<Status> statusList = new ArrayList<Status>();
		updateCount =
				StatusDACD
						.maintainStatusAssociations(statusList, (InternalResultsResponse<?>)response, deposito.getId(),
								null, AcaoEnum.DELETE,
								deposito.getCreateUser(), deposito.getEmprId(), TabelaEnum.DEPOSITO, getStatusDAC(),
								getHistoricoDAC(), deposito.getProcessId(), null);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			((InternalResultsResponse<Deposito>)response).addResult(deposito);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Deposito> fetchDepositoById(FetchByIdRequest request)
	{
		InternalResultsResponse<Deposito> response = new InternalResultsResponse<Deposito>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), DEPOSITO_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Deposito> fetchDepositoByRequest(DepositoInquiryRequest request)
	{
		InternalResultsResponse<Deposito> response = new InternalResultsResponse<Deposito>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, DEPOSITO_STMT_FETCH_COUNT,
				DEPOSITO_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Cnae> fetchCnaeByRequest(CnaeInquiryRequest cnae)
	{
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), cnae, CNAE_STMT_FETCH_COUNT,
				CNAE_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Regime> fetchRegimeByRequest(RegimeInquiryRequest regime)
	{
		InternalResultsResponse<Regime> response = new InternalResultsResponse<Regime>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), regime, REGIME_STMT_FETCH_COUNT,
				REGIME_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Cidade> fetchCidadeByRequest(CidadeInquiryRequest cidade)
	{
		InternalResultsResponse<Cidade> response = new InternalResultsResponse<Cidade>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), cidade, CIDADE_STMT_FETCH_COUNT,
				CIDADE_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Plano> fetchPlanoByRequest(PlanoInquiryRequest plano)
	{
		InternalResultsResponse<Plano> response = new InternalResultsResponse<Plano>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), plano, PLANO_STMT_FETCH_COUNT,
				PLANO_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Classificacao> fetchClassificacaoByRequest(ClassificacaoInquiryRequest classificacao)
	{
		InternalResultsResponse<Classificacao> response = new InternalResultsResponse<Classificacao>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), classificacao, CLASSIFICACAO_STMT_FETCH_COUNT,
				CLASSIFICACAO_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	public Integer insertAssociations(Entidade empresa, Integer processId, Integer historicoId, TabelaEnum tabela,
			InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		insertCount +=
				EnderecoDACD.maintainEnderecoAssociations(empresa.getEnderecos(), response, empresa.getId(), null,
						null,
						tabela, getEnderecoDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getCreateUser(), processId, historicoId);

		insertCount +=
				CnaeDACD.maintainCnaeAssociations(empresa.getCnaes(), response, empresa.getId(), null, null,
						tabela, getCnaeDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getCreateUser(), processId, historicoId);

		insertCount +=
				EmailDACD.maintainEmailAssociations(empresa.getEmails(), response, empresa.getId(), null, null,
						tabela, getEmailDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getCreateUser(), processId, historicoId);

		insertCount +=
				TelefoneDACD.maintainTelefoneAssociations(empresa.getTelefones(), response, empresa.getId(), null,
						null,
						tabela, getTelefoneDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getCreateUser(), processId, historicoId);

		insertCount +=
				DocumentosDACD.maintainDocumentoAssociations(empresa.getDocumentos(), response, empresa.getId(), null,
						null,
						tabela, getDocumentoDAC(), getStatusDAC(), getHistoricoDAC(), empresa.getId(),
						empresa.getCreateUser(), processId, historicoId);

		if (insertCount > 0)
		{
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ANALISANDO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			insertCount =
					StatusDACD.maintainStatusAssociations(statusList, response, empresa.getId(), null, AcaoEnum.INSERT,
							empresa.getCreateUser(), empresa.getId(), tabela, getStatusDAC(),
							getHistoricoDAC(), processId, historicoId);

		}

		return insertCount;
	}

}
