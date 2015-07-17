package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.ITributacaoDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.Tributacao;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class TributacaoDACImpl.
 */
public class TributacaoDACImpl extends SqlSessionDaoSupport implements ITributacaoDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "TributacaoMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchTributacaoRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllTributacaosByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchTributacaoById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertTributacao";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateTributacao";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteTributacaoById";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(TributacaoDACImpl.class);

	/** The valid sort fields for an tributacao inquiry. Will be injected by Spring. */
	private Map<String, String> tributacaoInquiryValidSortFields;

	/**
	 * Get the valid sort fields for the tributacao inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the tributacao inquiry.
	 */
	public Map<String, String> getTributacaoInquiryValidSortFields()
	{
		return tributacaoInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the tributacao inquiry. Attribute injected by Spring.
	 *
	 * @param tributacaoInquiryValidSortFields The valid sort fields for the tributacao inquiry to set.
	 */
	public void setTributacaoInquiryValidSortFields(Map<String, String> tributacaoInquiryValidSortFields)
	{
		this.tributacaoInquiryValidSortFields = tributacaoInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ITributacaoDAC#insertTributacao(com.prosperitasglobal.sendsolv.model
	 * .Tributacao)
	 */
	@Override
	public Integer insertTributacao(Tributacao tributacao)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Tributacao> response = new InternalResultsResponse<Tributacao>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, tributacao, response);

		if (response.isInError())
		{
			return null;
		}

		// Finally, if something was inserted then add the Tributacao to the result.
		if (insertCount > 0)
		{
			response.addResult(tributacao);
		}

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ITributacaoDAC#updateTributacao(com.prosperitasglobal.sendsolv.model
	 * .Tributacao)
	 */
	@Override
	public Integer updateTributacao(Tributacao tributacao)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Tributacao> response = new InternalResultsResponse<Tributacao>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(tributacao.getModelAction())
				&& (tributacao.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, tributacao,
							response);
		}

		if (response.isInError())
		{
			return null;
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(tributacao);
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ITributacaoDAC#deleteTributacao(com.prosperitasglobal.sendsolv.model
	 * .Tributacao)
	 */
	@Override
	public Integer deleteTributacao(Tributacao tributacao)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, tributacao, response);
		if (response.isInError())
		{
			return null;
		}
		else
		{
			return 1;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.ITributacaoDAC#fetchTributacaoById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Tributacao> fetchTributacaoById(FetchByIdRequest request)
	{
		InternalResultsResponse<Tributacao> response = new InternalResultsResponse<Tributacao>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ITributacaoDAC#fetchTributacaoByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Tributacao> fetchTributacaoByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Tributacao> response = new InternalResultsResponse<Tributacao>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getTributacaoInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Tributacao> fetchAllTributacaos()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
