package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.ICnaeDAC;
import com.prosperitasglobal.sendsolv.dac.IEmailDAC;
import com.prosperitasglobal.sendsolv.dac.IEnderecoDAC;
import com.prosperitasglobal.sendsolv.dac.IEventoDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.ISociosDAC;
import com.prosperitasglobal.sendsolv.dac.ITelefoneDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.Historico;
import com.prosperitasglobal.sendsolv.model.request.HistoricoInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class HistoricoDACImpl.
 */
public class HistoricoDACImpl extends SqlSessionDaoSupport implements IHistoricoDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "HistoricoMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchHistoricoRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllHistoricosByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchHistoricoById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertHistorico";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_CONTACT = EMPRESA_NAMESPACE
			+ "associateHistoricoWithContact";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateHistorico";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteHistoricoById";

	/** The Constant EMPRESA_DOCUMENT_STMT_UPDATE. */
	private static final String EMPRESA_DOCUMENT_STMT_UPDATE = EMPRESA_NAMESPACE
			+ "updateHistoricoDocument";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT = EMPRESA_NAMESPACE
			+ "associateHistoricoWithDocument";

	/** The Constant EMPRESA_STMT_DELETE_DOCUMENT. */
	private static final String EMPRESA_STMT_DELETE_DOCUMENT = EMPRESA_NAMESPACE
			+ "deleteHistoricoDocument";

	/** The Constant STMT_VERSION. */
	private static final String EMPRESA_STMT_VERSION = EMPRESA_NAMESPACE + "fetchVersionNumber";

	/** The Constant EMPRESA_STMT_UPDATE_EMPRESA_STATUS. */
	private static final String EMPRESA_STMT_UPDATE_EMPRESA_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(HistoricoDACImpl.class);

	/** The valid sort fields for an historico inquiry. Will be injected by Spring. */
	private Map<String, String> historicoInquiryValidSortFields;

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
	 * Get the valid sort fields for the historico inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the historico inquiry.
	 */
	public Map<String, String> getHistoricoInquiryValidSortFields()
	{
		return historicoInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the historico inquiry. Attribute injected by Spring.
	 *
	 * @param historicoInquiryValidSortFields The valid sort fields for the historico inquiry to set.
	 */
	public void setHistoricoInquiryValidSortFields(Map<String, String> historicoInquiryValidSortFields)
	{
		this.historicoInquiryValidSortFields = historicoInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IHistoricoDAC#insertHistorico(com.prosperitasglobal.sendsolv.model
	 * .Historico)
	 */
	@Override
	public InternalResultsResponse<Historico> insertHistorico(Historico historico)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Historico> response = new InternalResultsResponse<Historico>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, historico, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount += maintainHistoricoAssociations(historico, response);

		// Finally, if something was inserted then add the Historico to the result.
		if (insertCount > 0)
		{
			response.addResult(historico);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IHistoricoDAC#updateHistorico(com.prosperitasglobal.sendsolv.model
	 * .Historico)
	 */
	@Override
	public InternalResultsResponse<Historico> updateHistorico(Historico historico)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Historico> response = new InternalResultsResponse<Historico>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(historico.getModelAction())
				&& (historico.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, historico,
							response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount += maintainHistoricoAssociations(historico, response);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(historico);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IHistoricoDAC#deleteHistorico(com.prosperitasglobal.sendsolv.model
	 * .Historico)
	 */
	@Override
	public InternalResponse deleteHistorico(Historico historico)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, historico, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IHistoricoDAC#fetchHistoricoById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Historico> fetchHistoricoById(FetchByIdRequest request)
	{
		InternalResultsResponse<Historico> response = new InternalResultsResponse<Historico>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IHistoricoDAC#fetchHistoricoByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Historico> fetchHistoricoByRequest(HistoricoInquiryRequest request)
	{
		InternalResultsResponse<Historico> response = new InternalResultsResponse<Historico>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getHistoricoInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/**
	 * Maintain historico associations.
	 *
	 * @param historico the historico
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainHistoricoAssociations(Historico historico,
			InternalResultsResponse<Historico> response)
	{
		Integer count = 0;

		return count;
	}

	@Override
	public InternalResultsResponse<Historico> fetchAllHistoricos()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
