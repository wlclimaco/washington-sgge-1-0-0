package com.qat.samples.sysmgmt.pessoa.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.advocacia.model.Processo;
import com.qat.samples.sysmgmt.advocacia.model.request.ProcessoInquiryRequest;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IProcessoDAC;

/**
 * The Class AgenciaDACImpl.
 */
public class ProcessoDACImpl extends SqlSessionDaoSupport implements IProcessoDAC
{

	/** The Constant AGENCIA_NAMESPACE. */
	private static final String PROCESSO_NAMESPACE = "ProcessoMap.";

	/** The Constant PROCESSO_STMT_FETCH_COUNT. */
	private static final String PROCESSO_STMT_FETCH_COUNT = PROCESSO_NAMESPACE + "fetchProcessoRowCount";

	/** The Constant PROCESSO_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String PROCESSO_STMT_FETCH_ALL_BY_REQUEST = PROCESSO_NAMESPACE
			+ "fetchAllProcessosByRequest";

	/** The Constant PROCESSO_STMT_FETCH_BY_ID. */
	private static final String PROCESSO_STMT_FETCH_BY_ID = PROCESSO_NAMESPACE + "fetchProcessoById";

	/** The Constant PROCESSO_STMT_INSERT. */
	private static final String PROCESSO_STMT_INSERT = PROCESSO_NAMESPACE + "insertProcesso";

	/** The Constant PROCESSO_STMT_UPDATE. */
	private static final String PROCESSO_STMT_UPDATE = PROCESSO_NAMESPACE + "updateProcesso";

	/** The Constant PROCESSO_STMT_DELETE. */
	private static final String PROCESSO_STMT_DELETE = PROCESSO_NAMESPACE + "deleteProcessoById";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ProcessoDACImpl.class);

	/** The valid sort fields for an agencia inquiry. Will be injected by Spring. */
	private Map<String, String> agenciaInquiryValidSortFields;

	/**
	 * Get the valid sort fields for the agencia inquiry. Attribute injected by Spring.
	 * 
	 * @return The valid sort fields for the agencia inquiry.
	 */
	public Map<String, String> getProcessoInquiryValidSortFields()
	{
		return agenciaInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the agencia inquiry. Attribute injected by Spring.
	 * 
	 * @param agenciaInquiryValidSortFields The valid sort fields for the agencia inquiry to set.
	 */
	public void setProcessoInquiryValidSortFields(Map<String, String> agenciaInquiryValidSortFields)
	{
		this.agenciaInquiryValidSortFields = agenciaInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IProcessoDAC#insertProcesso(com.prosperitasglobal.sendsolv.model
	 * .Processo)
	 */
	@Override
	public Integer insertProcesso(Processo agencia)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Processo> response = new InternalResultsResponse<Processo>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), PROCESSO_STMT_INSERT, agencia, response);

		if (response.isInError())
		{
			return null;
		}

		// Finally, if something was inserted then add the Processo to the result.
		if (insertCount > 0)
		{
			response.addResult(agencia);
		}

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IProcessoDAC#updateProcesso(com.prosperitasglobal.sendsolv.model
	 * .Processo)
	 */
	@Override
	public Integer updateProcesso(Processo agencia)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Processo> response = new InternalResultsResponse<Processo>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(agencia.getModelAction())
				&& (agencia.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), PROCESSO_STMT_UPDATE, agencia,
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
	 * com.prosperitasglobal.sendsolv.dac.IProcessoDAC#deleteProcesso(com.prosperitasglobal.sendsolv.model
	 * .Processo)
	 */
	@Override
	public Integer deleteProcesso(Processo agencia)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), PROCESSO_STMT_DELETE, agencia, response);
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
	 * @see com.prosperitasglobal.sendsolv.dac.IProcessoDAC#fetchProcessoById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Processo> fetchProcessoById(FetchByIdRequest request)
	{
		InternalResultsResponse<Processo> response = new InternalResultsResponse<Processo>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), PROCESSO_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IProcessoDAC#fetchProcessoByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Processo> fetchProcessoByRequest(ProcessoInquiryRequest request)
	{
		InternalResultsResponse<Processo> response = new InternalResultsResponse<Processo>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getProcessoInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, PROCESSO_STMT_FETCH_COUNT,
				PROCESSO_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Processo> fetchAllProcessos()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
