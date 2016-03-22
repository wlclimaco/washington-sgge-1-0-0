package com.qat.samples.sysmgmt.pessoa.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.clinica.Consulta;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IConsultaDAC;

/**
 * The Class ConsultaDACImpl.
 */
public class ConsultaDACImpl extends SqlSessionDaoSupport implements IConsultaDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "ConsultaMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchConsultaRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllConsultasByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchConsultaById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertConsulta";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateConsulta";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteConsultaById";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ConsultaDACImpl.class);

	/** The valid sort fields for an consulta inquiry. Will be injected by Spring. */
	private Map<String, String> consultaInquiryValidSortFields;

	/**
	 * Get the valid sort fields for the consulta inquiry. Attribute injected by Spring.
	 * 
	 * @return The valid sort fields for the consulta inquiry.
	 */
	public Map<String, String> getConsultaInquiryValidSortFields()
	{
		return consultaInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the consulta inquiry. Attribute injected by Spring.
	 * 
	 * @param consultaInquiryValidSortFields The valid sort fields for the consulta inquiry to set.
	 */
	public void setConsultaInquiryValidSortFields(Map<String, String> consultaInquiryValidSortFields)
	{
		this.consultaInquiryValidSortFields = consultaInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IConsultaDAC#insertConsulta(com.prosperitasglobal.sendsolv.model
	 * .Consulta)
	 */
	@Override
	public Integer insertConsulta(Consulta consulta, String string, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		response = new InternalResultsResponse<Consulta>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, consulta, response);

		if (response.isInError())
		{
			return null;
		}
		// response.addResults((List<?>)consulta);
		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IConsultaDAC#updateConsulta(com.prosperitasglobal.sendsolv.model
	 * .Consulta)
	 */
	@Override
	public Integer updateConsulta(Consulta consulta, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;
		response = new InternalResultsResponse<Consulta>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(consulta.getModelAction())
				&& (consulta.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, consulta,
							response);
		}

		if (response.isInError())
		{
			return null;
		}

		// Finally, if something was updated then add the Person to the result.
		// if (updateCount > 0)
		// {
		// response.addResult(consulta);
		// }

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IConsultaDAC#deleteConsulta(com.prosperitasglobal.sendsolv.model
	 * .Consulta)
	 */
	@Override
	public Integer deleteConsulta(Consulta consulta, InternalResultsResponse<?> response)
	{

		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, consulta, response);
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
	 * @see com.prosperitasglobal.sendsolv.dac.IConsultaDAC#fetchConsultaById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Consulta> fetchConsultaById(FetchByIdRequest request)
	{
		InternalResultsResponse<Consulta> response = new InternalResultsResponse<Consulta>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IConsultaDAC#fetchConsultaByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Consulta> fetchConsultaByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Consulta> response = new InternalResultsResponse<Consulta>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getConsultaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Consulta> fetchAllConsultas()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
