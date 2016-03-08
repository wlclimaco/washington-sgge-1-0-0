package com.qat.samples.sysmgmt.pessoa.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.agencia.Agencia;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IAgenciaDAC;

/**
 * The Class AgenciaDACImpl.
 */
public class AgenciaDACImpl extends SqlSessionDaoSupport implements IAgenciaDAC
{

	/** The Constant AGENCIA_NAMESPACE. */
	private static final String AGENCIA_NAMESPACE = "AgenciaMap.";

	/** The Constant AGENCIA_STMT_FETCH_COUNT. */
	private static final String AGENCIA_STMT_FETCH_COUNT = AGENCIA_NAMESPACE + "fetchAgenciaRowCount";

	/** The Constant AGENCIA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String AGENCIA_STMT_FETCH_ALL_BY_REQUEST = AGENCIA_NAMESPACE
			+ "fetchAllAgenciasByRequest";

	/** The Constant AGENCIA_STMT_FETCH_BY_ID. */
	private static final String AGENCIA_STMT_FETCH_BY_ID = AGENCIA_NAMESPACE + "fetchAgenciaById";

	/** The Constant AGENCIA_STMT_INSERT. */
	private static final String AGENCIA_STMT_INSERT = AGENCIA_NAMESPACE + "insertAgencia";

	/** The Constant AGENCIA_STMT_UPDATE. */
	private static final String AGENCIA_STMT_UPDATE = AGENCIA_NAMESPACE + "updateAgencia";

	/** The Constant AGENCIA_STMT_DELETE. */
	private static final String AGENCIA_STMT_DELETE = AGENCIA_NAMESPACE + "deleteAgenciaById";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AgenciaDACImpl.class);

	/** The valid sort fields for an agencia inquiry. Will be injected by Spring. */
	private Map<String, String> agenciaInquiryValidSortFields;

	/**
	 * Get the valid sort fields for the agencia inquiry. Attribute injected by Spring.
	 * 
	 * @return The valid sort fields for the agencia inquiry.
	 */
	public Map<String, String> getAgenciaInquiryValidSortFields()
	{
		return agenciaInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the agencia inquiry. Attribute injected by Spring.
	 * 
	 * @param agenciaInquiryValidSortFields The valid sort fields for the agencia inquiry to set.
	 */
	public void setAgenciaInquiryValidSortFields(Map<String, String> agenciaInquiryValidSortFields)
	{
		this.agenciaInquiryValidSortFields = agenciaInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IAgenciaDAC#insertAgencia(com.prosperitasglobal.sendsolv.model
	 * .Agencia)
	 */
	@Override
	public Integer insertAgencia(Agencia agencia)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Agencia> response = new InternalResultsResponse<Agencia>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), AGENCIA_STMT_INSERT, agencia, response);

		if (response.isInError())
		{
			return null;
		}

		// Finally, if something was inserted then add the Agencia to the result.
		if (insertCount > 0)
		{
			response.addResult(agencia);
		}

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IAgenciaDAC#updateAgencia(com.prosperitasglobal.sendsolv.model
	 * .Agencia)
	 */
	@Override
	public Integer updateAgencia(Agencia agencia)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Agencia> response = new InternalResultsResponse<Agencia>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(agencia.getModelAction())
				&& (agencia.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), AGENCIA_STMT_UPDATE, agencia,
							response);
		}

		if (response.isInError())
		{
			return null;
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(agencia);
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IAgenciaDAC#deleteAgencia(com.prosperitasglobal.sendsolv.model
	 * .Agencia)
	 */
	@Override
	public Integer deleteAgencia(Agencia agencia)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), AGENCIA_STMT_DELETE, agencia, response);
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
	 * @see com.prosperitasglobal.sendsolv.dac.IAgenciaDAC#fetchAgenciaById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Agencia> fetchAgenciaById(FetchByIdRequest request)
	{
		InternalResultsResponse<Agencia> response = new InternalResultsResponse<Agencia>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), AGENCIA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IAgenciaDAC#fetchAgenciaByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Agencia> fetchAgenciaByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Agencia> response = new InternalResultsResponse<Agencia>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getAgenciaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, AGENCIA_STMT_FETCH_COUNT,
				AGENCIA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Agencia> fetchAllAgencias()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
