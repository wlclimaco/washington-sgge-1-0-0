package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.IOrdemServicoDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.OrdemServico;
import com.prosperitasglobal.sendsolv.model.request.OrdemServicoInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class OrdemServicoDACImpl.
 */
public class OrdemServicoDACImpl extends SqlSessionDaoSupport implements IOrdemServicoDAC
{

	/** The Constant ORDEMSERVICO_NAMESPACE. */
	private static final String ORDEMSERVICO_NAMESPACE = "OrdemServicoMap.";

	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant ORDEMSERVICO_STMT_FETCH_COUNT. */
	private static final String ORDEMSERVICO_STMT_FETCH_COUNT = ORDEMSERVICO_NAMESPACE + "fetchOrdemServicoRowCount";

	/** The Constant ORDEMSERVICO_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String ORDEMSERVICO_STMT_FETCH_ALL_BY_REQUEST = ORDEMSERVICO_NAMESPACE
			+ "fetchAllOrdemServicosByRequest";

	/** The Constant ORDEMSERVICO_STMT_FETCH_BY_ID. */
	private static final String ORDEMSERVICO_STMT_FETCH_BY_ID = ORDEMSERVICO_NAMESPACE + "fetchOrdemServicoById";

	/** The Constant ORDEMSERVICO_STMT_INSERT. */
	private static final String ORDEMSERVICO_STMT_INSERT = ORDEMSERVICO_NAMESPACE + "insertOrdemServico";

	/** The Constant ORDEMSERVICO_STMT_UPDATE. */
	private static final String ORDEMSERVICO_STMT_UPDATE = ORDEMSERVICO_NAMESPACE + "updateOrdemServico";

	/** The Constant ORDEMSERVICO_STMT_DELETE. */
	private static final String ORDEMSERVICO_STMT_DELETE = ORDEMSERVICO_NAMESPACE + "deleteOrdemServicoById";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(OrdemServicoDACImpl.class);

	/** The ordemServico dac. */
	private IOrdemServicoDAC ordemServicoDAC;

	/** The valid sort fields for an ordemServico inquiry. Will be injected by Spring. */
	private Map<String, String> ordemServicoInquiryValidSortFields;

	/**
	 * Gets the ordemServico dac.
	 *
	 * @return the ordemServico dac
	 */
	public IOrdemServicoDAC getOrdemServicoDAC()
	{
		return ordemServicoDAC;
	}

	/**
	 * Sets the ordemServico dac.
	 *
	 * @param ordemServicoDAC the ordemServico dac
	 */
	public void setOrdemServicoDAC(IOrdemServicoDAC ordemServicoDAC)
	{
		this.ordemServicoDAC = ordemServicoDAC;
	}

	/**
	 * Get the valid sort fields for the ordemServico inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the ordemServico inquiry.
	 */
	public Map<String, String> getOrdemServicoInquiryValidSortFields()
	{
		return ordemServicoInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the ordemServico inquiry. Attribute injected by Spring.
	 *
	 * @param ordemServicoInquiryValidSortFields The valid sort fields for the ordemServico inquiry to set.
	 */
	public void setOrdemServicoInquiryValidSortFields(Map<String, String> ordemServicoInquiryValidSortFields)
	{
		this.ordemServicoInquiryValidSortFields = ordemServicoInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrdemServicoDAC#insertOrdemServico(com.prosperitasglobal.sendsolv.model
	 * .OrdemServico)
	 */
	@Override
	public InternalResultsResponse<OrdemServico> insertOrdemServico(OrdemServico ordemServico)
	{
		Integer insertCount = 0;
		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), ORDEMSERVICO_STMT_INSERT, ordemServico, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount += maintainOrdemServicoAssociations(ordemServico, response);

		// Finally, if something was inserted then add the OrdemServico to the result.
		if (insertCount > 0)
		{
			response.addResult(ordemServico);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrdemServicoDAC#updateOrdemServico(com.prosperitasglobal.sendsolv.model
	 * .OrdemServico)
	 */
	@Override
	public InternalResultsResponse<OrdemServico> updateOrdemServico(OrdemServico ordemServico)
	{
		Integer updateCount = 0;
		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(ordemServico.getModelAction())
				&& (ordemServico.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), ORDEMSERVICO_STMT_UPDATE, ordemServico, response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount += maintainOrdemServicoAssociations(ordemServico, response);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(ordemServico);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrdemServicoDAC#deleteOrdemServico(com.prosperitasglobal.sendsolv.model
	 * .OrdemServico)
	 */
	@Override
	public InternalResponse deleteOrdemServico(OrdemServico ordemServico)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), ORDEMSERVICO_STMT_DELETE, ordemServico, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IOrdemServicoDAC#fetchOrdemServicoById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<OrdemServico> fetchOrdemServicoById(FetchByIdRequest request)
	{
		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), ORDEMSERVICO_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IOrdemServicoDAC#fetchOrdemServicoByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<OrdemServico> fetchOrdemServicoByRequest(OrdemServicoInquiryRequest request)
	{
		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getOrdemServicoInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, ORDEMSERVICO_STMT_FETCH_COUNT,
				ORDEMSERVICO_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/**
	 * Maintain ordemServico associations.
	 *
	 * @param ordemServico the ordemServico
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainOrdemServicoAssociations(OrdemServico ordemServico,
			InternalResultsResponse<OrdemServico> response)
	{
		Integer count = 0;
		// First Maintain OrdemServicos
		// if (ValidationUtil.isNullOrEmpty(ordemServico.getOrdemServicoList()))
		// {
		// return count;
		// }
		// // For Each OrdemServico...
		// for (OrdemServico ordemServico : ordemServico.getOrdemServicoList())
		// {
		// // Make sure we set the parent key
		// ordemServico.setParentKey(ordemServico.getId());
		//
		// if (ValidationUtil.isNull(ordemServico.getModelAction()))
		// {
		// continue;
		// }
		// switch (ordemServico.getModelAction())
		// {
		// case INSERT:
		// count = getOrdemServicoDAC().insertOrdemServico(ordemServico,
		// ORDEMSERVICO_STMT_ASSOC_ORG_TO_CONTACT, response);
		// break;
		// case UPDATE:
		// count = getOrdemServicoDAC().updateOrdemServico(ordemServico, response);
		// break;
		// case DELETE:
		// count = getOrdemServicoDAC().deleteBusinessOrdemServico(ordemServico, response);
		// break;
		// default:
		// if (LOG.isDebugEnabled())
		// {
		// LOG.debug("ModelAction for OrdemServico missing!");
		// }
		// break;
		// }
		// }
		return count;
	}

	@Override
	public InternalResultsResponse<OrdemServico> fetchAllOrdemServicos()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
