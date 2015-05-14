package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.ICnaeDAC;
import com.prosperitasglobal.sendsolv.dac.IEmailDAC;
import com.prosperitasglobal.sendsolv.dac.IEnderecoDAC;
import com.prosperitasglobal.sendsolv.dac.IEventoDAC;
import com.prosperitasglobal.sendsolv.dac.IOrcamentoDAC;
import com.prosperitasglobal.sendsolv.dac.ISociosDAC;
import com.prosperitasglobal.sendsolv.dac.ITelefoneDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.Orcamento;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class OrcamentoDACImpl.
 */
public class OrcamentoDACImpl extends SqlSessionDaoSupport implements IOrcamentoDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "OrcamentoMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchOrcamentoRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllOrcamentosByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchOrcamentoById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertOrcamento";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_CONTACT = EMPRESA_NAMESPACE
			+ "associateOrcamentoWithContact";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateOrcamento";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteOrcamentoById";

	/** The Constant EMPRESA_DOCUMENT_STMT_UPDATE. */
	private static final String EMPRESA_DOCUMENT_STMT_UPDATE = EMPRESA_NAMESPACE
			+ "updateOrcamentoDocument";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT = EMPRESA_NAMESPACE
			+ "associateOrcamentoWithDocument";

	/** The Constant EMPRESA_STMT_DELETE_DOCUMENT. */
	private static final String EMPRESA_STMT_DELETE_DOCUMENT = EMPRESA_NAMESPACE
			+ "deleteOrcamentoDocument";

	/** The Constant STMT_VERSION. */
	private static final String EMPRESA_STMT_VERSION = EMPRESA_NAMESPACE + "fetchVersionNumber";

	/** The Constant EMPRESA_STMT_UPDATE_EMPRESA_STATUS. */
	private static final String EMPRESA_STMT_UPDATE_EMPRESA_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(OrcamentoDACImpl.class);

	/** The valid sort fields for an orcamento inquiry. Will be injected by Spring. */
	private Map<String, String> orcamentoInquiryValidSortFields;

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
	 * Get the valid sort fields for the orcamento inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the orcamento inquiry.
	 */
	public Map<String, String> getOrcamentoInquiryValidSortFields()
	{
		return orcamentoInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the orcamento inquiry. Attribute injected by Spring.
	 *
	 * @param orcamentoInquiryValidSortFields The valid sort fields for the orcamento inquiry to set.
	 */
	public void setOrcamentoInquiryValidSortFields(Map<String, String> orcamentoInquiryValidSortFields)
	{
		this.orcamentoInquiryValidSortFields = orcamentoInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrcamentoDAC#insertOrcamento(com.prosperitasglobal.sendsolv.model
	 * .Orcamento)
	 */
	@Override
	public InternalResultsResponse<Orcamento> insertOrcamento(Orcamento orcamento)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Orcamento> response = new InternalResultsResponse<Orcamento>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, orcamento, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount += maintainOrcamentoAssociations(orcamento, response);

		// Finally, if something was inserted then add the Orcamento to the result.
		if (insertCount > 0)
		{
			response.addResult(orcamento);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrcamentoDAC#updateOrcamento(com.prosperitasglobal.sendsolv.model
	 * .Orcamento)
	 */
	@Override
	public InternalResultsResponse<Orcamento> updateOrcamento(Orcamento orcamento)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Orcamento> response = new InternalResultsResponse<Orcamento>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(orcamento.getModelAction())
				&& (orcamento.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, orcamento,
							response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount += maintainOrcamentoAssociations(orcamento, response);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(orcamento);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrcamentoDAC#deleteOrcamento(com.prosperitasglobal.sendsolv.model
	 * .Orcamento)
	 */
	@Override
	public InternalResponse deleteOrcamento(Orcamento orcamento)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, orcamento, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IOrcamentoDAC#fetchOrcamentoById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Orcamento> fetchOrcamentoById(FetchByIdRequest request)
	{
		InternalResultsResponse<Orcamento> response = new InternalResultsResponse<Orcamento>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrcamentoDAC#fetchOrcamentoByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Orcamento> fetchOrcamentoByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Orcamento> response = new InternalResultsResponse<Orcamento>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getOrcamentoInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/**
	 * Maintain orcamento associations.
	 *
	 * @param orcamento the orcamento
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainOrcamentoAssociations(Orcamento orcamento,
			InternalResultsResponse<Orcamento> response)
	{
		Integer count = 0;
		// First Maintain Orcamento

		return count;
	}

	@Override
	public InternalResultsResponse<Orcamento> fetchAllOrcamentos()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
