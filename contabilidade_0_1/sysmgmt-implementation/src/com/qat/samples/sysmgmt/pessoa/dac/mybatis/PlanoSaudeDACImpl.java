package com.qat.samples.sysmgmt.pessoa.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.clinica.PlanoSaude;
import com.qat.samples.sysmgmt.clinica.PlanoSaudePessoa;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IPlanoSaudeDAC;

/**
 * The Class PlanoSaudeDACImpl.
 */
public class PlanoSaudeDACImpl extends SqlSessionDaoSupport implements IPlanoSaudeDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "PlanoSaudeMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchPlanoSaudeRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllPlanoSaudesByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchPlanoSaudeById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertPlanoSaude";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updatePlanoSaude";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deletePlanoSaudeById";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PlanoSaudeDACImpl.class);

	/** The valid sort fields for an planoSaude inquiry. Will be injected by Spring. */
	private Map<String, String> planoSaudeInquiryValidSortFields;

	/**
	 * Get the valid sort fields for the planoSaude inquiry. Attribute injected by Spring.
	 * 
	 * @return The valid sort fields for the planoSaude inquiry.
	 */
	public Map<String, String> getPlanoSaudeInquiryValidSortFields()
	{
		return planoSaudeInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the planoSaude inquiry. Attribute injected by Spring.
	 * 
	 * @param planoSaudeInquiryValidSortFields The valid sort fields for the planoSaude inquiry to set.
	 */
	public void setPlanoSaudeInquiryValidSortFields(Map<String, String> planoSaudeInquiryValidSortFields)
	{
		this.planoSaudeInquiryValidSortFields = planoSaudeInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IPlanoSaudeDAC#insertPlanoSaude(com.prosperitasglobal.sendsolv.model
	 * .PlanoSaude)
	 */
	@Override
	public Integer insertPlanoSaude(PlanoSaude planoSaude)
	{
		Integer insertCount = 0;
		InternalResultsResponse<PlanoSaude> response = new InternalResultsResponse<PlanoSaude>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, planoSaude, response);

		if (response.isInError())
		{
			return null;
		}

		// Finally, if something was inserted then add the PlanoSaude to the result.
		if (insertCount > 0)
		{
			response.addResult(planoSaude);
		}

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IPlanoSaudeDAC#updatePlanoSaude(com.prosperitasglobal.sendsolv.model
	 * .PlanoSaude)
	 */
	@Override
	public Integer updatePlanoSaude(PlanoSaude planoSaude)
	{
		Integer updateCount = 0;
		InternalResultsResponse<PlanoSaude> response = new InternalResultsResponse<PlanoSaude>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(planoSaude.getModelAction())
				&& (planoSaude.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, planoSaude,
							response);
		}

		if (response.isInError())
		{
			return null;
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(planoSaude);
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IPlanoSaudeDAC#deletePlanoSaude(com.prosperitasglobal.sendsolv.model
	 * .PlanoSaude)
	 */
	@Override
	public Integer deletePlanoSaude(PlanoSaude planoSaude)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, planoSaude, response);
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
	 * @see com.prosperitasglobal.sendsolv.dac.IPlanoSaudeDAC#fetchPlanoSaudeById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<PlanoSaude> fetchPlanoSaudeById(FetchByIdRequest request)
	{
		InternalResultsResponse<PlanoSaude> response = new InternalResultsResponse<PlanoSaude>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IPlanoSaudeDAC#fetchPlanoSaudeByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<PlanoSaude> fetchPlanoSaudeByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<PlanoSaude> response = new InternalResultsResponse<PlanoSaude>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getPlanoSaudeInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/**
	 * Maintain planoSaude associations.
	 * 
	 * @param planoSaude the planoSaude
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainPlanoSaudeAssociations(PlanoSaude planoSaude,
			InternalResultsResponse<PlanoSaude> response)
	{
		Integer count = 0;

		return count;
	}

	@Override
	public InternalResultsResponse<PlanoSaude> fetchAllPlanoSaudes()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updatePlanoSaudePessoa(PlanoSaudePessoa planoSaude)
	{
		Integer updateCount = 0;
		InternalResultsResponse<PlanoSaudePessoa> response = new InternalResultsResponse<PlanoSaudePessoa>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(planoSaude.getModelAction())
				&& (planoSaude.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), "PlanoSaudeMap.updatePlanoSaudePessoa", planoSaude,
							response);
		}

		if (response.isInError())
		{
			return null;
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(planoSaude);
		}

		return updateCount;
	}

	@Override
	public Integer insertPlanoSaudePessoa(PlanoSaudePessoa planoSaude)
	{
		Integer insertCount = 0;
		InternalResultsResponse<PlanoSaudePessoa> response = new InternalResultsResponse<PlanoSaudePessoa>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "PlanoSaudeMap.insertPlanoSaude",
						planoSaude.getPlanoId(), response);

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "PlanoSaudeMap.insertPlanoSaudePessoa", planoSaude,
						response);

		if (response.isInError())
		{
			return null;
		}
		// Finally, if something was inserted then add the PlanoSaude to the result.
		if (insertCount > 0)
		{
			response.addResult(planoSaude);
		}

		return insertCount;
	}

	@Override
	public Integer deletePlanoSaudePessoa(PlanoSaudePessoa planoSaude)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), "PlanoSaudeMap.deletePlanoSaudePessoa", planoSaude, response);
		if (response.isInError())
		{
			return null;
		}
		else
		{
			return 1;
		}
	}
}
