package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.dac.ICadastroDAC;
import com.prosperitasglobal.sendsolv.dac.ICnaeDAC;
import com.prosperitasglobal.sendsolv.dac.IEmailDAC;
import com.prosperitasglobal.sendsolv.dac.IEnderecoDAC;
import com.prosperitasglobal.sendsolv.dac.IEventoDAC;
import com.prosperitasglobal.sendsolv.dac.ISociosDAC;
import com.prosperitasglobal.sendsolv.dac.ITelefoneDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.Cfop;
import com.prosperitasglobal.sendsolv.model.Cidade;
import com.prosperitasglobal.sendsolv.model.Cnae;
import com.prosperitasglobal.sendsolv.model.Csosn;
import com.prosperitasglobal.sendsolv.model.Estado;
import com.prosperitasglobal.sendsolv.model.Regime;
import com.prosperitasglobal.sendsolv.model.UniMed;
import com.prosperitasglobal.sendsolv.model.request.CfopInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CidadeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CnaeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CsosnInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.EstadoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RegimeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.UniMedInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class BancoDACImpl.
 */
public class CadastroDACImpl extends SqlSessionDaoSupport implements ICadastroDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "BancoMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchBancoRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllBancosByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchBancoById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertBanco";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_CONTACT = EMPRESA_NAMESPACE
			+ "associateBancoWithContact";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateBanco";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteBancoById";

	/** The Constant EMPRESA_DOCUMENT_STMT_UPDATE. */
	private static final String EMPRESA_DOCUMENT_STMT_UPDATE = EMPRESA_NAMESPACE
			+ "updateBancoDocument";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT = EMPRESA_NAMESPACE
			+ "associateBancoWithDocument";

	/** The Constant EMPRESA_STMT_DELETE_DOCUMENT. */
	private static final String EMPRESA_STMT_DELETE_DOCUMENT = EMPRESA_NAMESPACE
			+ "deleteBancoDocument";

	/** The Constant STMT_VERSION. */
	private static final String EMPRESA_STMT_VERSION = EMPRESA_NAMESPACE + "fetchVersionNumber";

	/** The Constant EMPRESA_STMT_UPDATE_EMPRESA_STATUS. */
	private static final String EMPRESA_STMT_UPDATE_EMPRESA_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CadastroDACImpl.class);

	/** The valid sort fields for an banco inquiry. Will be injected by Spring. */
	private Map<String, String> bancoInquiryValidSortFields;

	private IEnderecoDAC enderecoDAC;

	private IEmailDAC emailDAC;

	private ITelefoneDAC telefoneDAC;

	private ICnaeDAC cnaeDAC;

	private ISociosDAC socioDAC;

	private IEventoDAC eventosDAC;

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
	 * @return the eventosDAC
	 */
	public IEventoDAC getEventosDAC()
	{
		return eventosDAC;
	}

	/**
	 * @param eventosDAC the eventosDAC to set
	 */
	public void setEventosDAC(IEventoDAC eventosDAC)
	{
		this.eventosDAC = eventosDAC;
	}

	/**
	 * Get the valid sort fields for the banco inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the banco inquiry.
	 */
	public Map<String, String> getBancoInquiryValidSortFields()
	{
		return bancoInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the banco inquiry. Attribute injected by Spring.
	 *
	 * @param bancoInquiryValidSortFields The valid sort fields for the banco inquiry to set.
	 */
	public void setBancoInquiryValidSortFields(Map<String, String> bancoInquiryValidSortFields)
	{
		this.bancoInquiryValidSortFields = bancoInquiryValidSortFields;
	}

	@Override
	public InternalResultsResponse<Cidade> insertCidade(Cidade cidade)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Cidade> response = new InternalResultsResponse<Cidade>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), "insertCidade", cidade, response);

		if (response.isInError())
		{
			return response;
		}

		// Finally, if something was inserted then add the Empresa to the result.
		if (insertCount > 0)
		{
			response.addResult(cidade);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Cidade> updateCidade(Cidade request)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Cidade> response = new InternalResultsResponse<Cidade>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(request.getModelAction())
				&& (request.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), "updateCidade", request,
							response);
		}

		if (response.isInError())
		{
			return response;
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResponse deleteCidade(Cidade request)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), "deleteCidade", request, response);

		return response;
	}

	@Override
	public InternalResultsResponse<Cidade> fetchCidadeByRequest(CidadeInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Estado> insertEstado(Estado request)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Estado> response = new InternalResultsResponse<Estado>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), "insertEstado", request, response);

		if (response.isInError())
		{
			return response;
		}

		// Finally, if something was inserted then add the Empresa to the result.
		if (insertCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Estado> updateEstado(Estado request)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Estado> response = new InternalResultsResponse<Estado>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(request.getModelAction())
				&& (request.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), "updateEstado", request,
							response);
		}

		if (response.isInError())
		{
			return response;
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResponse deleteEstado(Estado request)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), "deleteEstado", request, response);

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
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Cnae> insertCnae(Cnae request)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, request, response);

		if (response.isInError())
		{
			return response;
		}

		// Finally, if something was inserted then add the Empresa to the result.
		if (insertCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Cnae> updateCnae(Cnae request)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(request.getModelAction())
				&& (request.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), "updateCidade", request,
							response);
		}

		if (response.isInError())
		{
			return response;
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResponse deleteCnae(Cnae request)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), "deleteCnae", request, response);

		return response;
	}

	@Override
	public InternalResultsResponse<Cnae> fetchCnaeByRequest(CnaeInquiryRequest request)
	{
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Regime> insertRegime(Regime request)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Regime> response = new InternalResultsResponse<Regime>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, request, response);

		if (response.isInError())
		{
			return response;
		}

		// Finally, if something was inserted then add the Empresa to the result.
		if (insertCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Regime> updateRegime(Regime request)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Regime> response = new InternalResultsResponse<Regime>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(request.getModelAction())
				&& (request.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), "updateCidade", request,
							response);
		}

		if (response.isInError())
		{
			return response;
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResponse deleteRegime(Regime request)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), "deleteRegime", request, response);

		return response;
	}

	@Override
	public InternalResultsResponse<Regime> fetchRegimeByRequest(RegimeInquiryRequest request)
	{
		InternalResultsResponse<Regime> response = new InternalResultsResponse<Regime>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Csosn> insertCsosn(Csosn request)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Csosn> response = new InternalResultsResponse<Csosn>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, request, response);

		if (response.isInError())
		{
			return response;
		}

		// Finally, if something was inserted then add the Empresa to the result.
		if (insertCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Csosn> updateCsosn(Csosn request)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Csosn> response = new InternalResultsResponse<Csosn>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(request.getModelAction())
				&& (request.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), "updateCidade", request,
							response);
		}

		if (response.isInError())
		{
			return response;
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResponse deleteCsosn(Csosn request)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), "deleteCsosn", request, response);

		return response;
	}

	@Override
	public InternalResultsResponse<Csosn> fetchCsosnByRequest(CsosnInquiryRequest request)
	{
		InternalResultsResponse<Csosn> response = new InternalResultsResponse<Csosn>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	@Override
	public InternalResultsResponse<UniMed> insertUniMed(UniMed request)
	{
		Integer insertCount = 0;
		InternalResultsResponse<UniMed> response = new InternalResultsResponse<UniMed>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, request, response);

		if (response.isInError())
		{
			return response;
		}

		// Finally, if something was inserted then add the Empresa to the result.
		if (insertCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<UniMed> updateUniMed(UniMed request)
	{
		Integer updateCount = 0;
		InternalResultsResponse<UniMed> response = new InternalResultsResponse<UniMed>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(request.getModelAction())
				&& (request.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), "updateCidade", request,
							response);
		}

		if (response.isInError())
		{
			return response;
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResponse deleteUniMed(UniMed request)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), "deleteUniMed", request, response);

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

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Cfop> insertCfop(Cfop request)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Cfop> response = new InternalResultsResponse<Cfop>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, request, response);

		if (response.isInError())
		{
			return response;
		}

		// Finally, if something was inserted then add the Empresa to the result.
		if (insertCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Cfop> updateCfop(Cfop request)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Cfop> response = new InternalResultsResponse<Cfop>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(request.getModelAction())
				&& (request.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), "updateCfop", request,
							response);
		}

		if (response.isInError())
		{
			return response;
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResponse deleteCfop(Cfop request)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), "deleteCfop", request, response);

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

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}
}
