package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.List;

import com.prosperitasglobal.sendsolv.dac.IConhecimentoTransporteDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class ConhecimentoTransporteDACImpl extends SqlSessionDaoSupport implements IConhecimentoTransporteDAC
{

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ConhecimentoTransporteDACImpl.class);
	private static final String CONTACT_STMT_INSERT = null;
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = null;
	private static final String CONTACT_STMT_UPDATE = null;
	private static final String EMPRESA_STMT_FETCH_COUNT = null;
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = null;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertConhecimentoTransporte(com.prosperitasglobal.cbof.
	 * model.ConhecimentoTransporte,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertConhecimentoTransporte(ConhecimentoTransporte conhecimentoTransporte, String statementName,
			InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root conhecimentoTransporte data
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, conhecimentoTransporte, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
						.doInsert(getSqlSession(), statementName, conhecimentoTransporte, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IConhecimentoTransporteDAC#deleteBusinessConhecimentoTransporte(com.prosperitasglobal
	 * .cbof.model.ConhecimentoTransporte,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteConhecimentoTransporte(ConhecimentoTransporte conhecimentoTransporte,
			InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT,
				conhecimentoTransporte, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateConhecimentoTransporte(com.prosperitasglobal.cbof.
	 * model.ConhecimentoTransporte,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateConhecimentoTransporte(ConhecimentoTransporte conhecimentoTransporte,
			InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(conhecimentoTransporte.getModelAction())
				&& (conhecimentoTransporte.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper
							.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, conhecimentoTransporte, response);

			if (updateCount == 1)
			{
				conhecimentoTransporte.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchConhecimentoTransporteByParent(java.lang.Integer,
	 * com.prosperitasglobal.sendsolv.model.BusinessTypeEnum)
	 */
	@Override
	public InternalResultsResponse<ConhecimentoTransporte> fetchConhecimentoTransporteByRequest(
			PagedInquiryRequest request)
	{
		InternalResultsResponse<ConhecimentoTransporte> response =
				new InternalResultsResponse<ConhecimentoTransporte>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequestConhecimentoTransporte(getSqlSession(), request,
				EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchConhecimentoTransporteById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<ConhecimentoTransporte> fetchConhecimentoTransporteById(Integer id)
	{
		InternalResultsResponse<ConhecimentoTransporte> response =
				new InternalResultsResponse<ConhecimentoTransporte>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), CONTACT_STMT_FETCH_BY_ID, id, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IConhecimentoTransporteDAC#maintainConhecimentoTransporteAssociations(java.util
	 * .List, java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer maintainConhecimentoTransporteAssociations(List<ConhecimentoTransporte> conhecimentoTransporteList,
			Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain ConhecimentoTransportes
		if (ValidationUtil.isNullOrEmpty(conhecimentoTransporteList))
		{
			return count;
		}
		// For Each ConhecimentoTransporte...
		for (ConhecimentoTransporte conhecimentoTransporte : conhecimentoTransporteList)
		{
			// Make sure we set the parent key
			conhecimentoTransporte.setParentId(parentId);

			if (ValidationUtil.isNull(conhecimentoTransporte.getModelAction()))
			{
				continue;
			}
			switch (conhecimentoTransporte.getModelAction())
			{
				case INSERT:
					count += insertConhecimentoTransporte(conhecimentoTransporte, associateStatement, response);
					break;
				case UPDATE:
					count += updateConhecimentoTransporte(conhecimentoTransporte, response);
					break;
				case DELETE:
					count += deleteConhecimentoTransporte(conhecimentoTransporte, response);
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("ModelAction for Organization missing!");
					}
					break;
			}
		}
		return count;
	}
}
