package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.List;

import com.prosperitasglobal.sendsolv.dac.ITransporteDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class TransporteDACImpl extends SqlSessionDaoSupport implements ITransporteDAC
{

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(TransporteDACImpl.class);
	private static final String CONTACT_STMT_INSERT = null;
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = null;
	private static final String CONTACT_STMT_UPDATE = null;
	private static final String EMPRESA_STMT_FETCH_COUNT = null;
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = null;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertTransporte(com.prosperitasglobal.cbof.
	 * model.Transporte,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertTransporte(Transporte transporte, String statementName,
			InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root transporte data
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, transporte, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
						.doInsert(getSqlSession(), statementName, transporte, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ITransporteDAC#deleteBusinessTransporte(com.prosperitasglobal
	 * .cbof.model.Transporte,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteTransporte(Transporte transporte,
			InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT,
				transporte, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateTransporte(com.prosperitasglobal.cbof.
	 * model.Transporte,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateTransporte(Transporte transporte,
			InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(transporte.getModelAction())
				&& (transporte.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper
							.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, transporte, response);

			if (updateCount == 1)
			{
				transporte.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchTransporteByParent(java.lang.Integer,
	 * com.prosperitasglobal.sendsolv.model.BusinessTypeEnum)
	 */
	@Override
	public InternalResultsResponse<Transporte> fetchTransporteByRequest(
			PagedInquiryRequest request)
	{
		InternalResultsResponse<Transporte> response =
				new InternalResultsResponse<Transporte>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequestTransporte(getSqlSession(), request,
				EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchTransporteById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<Transporte> fetchTransporteById(Integer id)
	{
		InternalResultsResponse<Transporte> response =
				new InternalResultsResponse<Transporte>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), CONTACT_STMT_FETCH_BY_ID, id, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ITransporteDAC#maintainTransporteAssociations(java.util
	 * .List, java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer maintainTransporteAssociations(List<Transporte> transporteList,
			Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain Transportes
		if (ValidationUtil.isNullOrEmpty(transporteList))
		{
			return count;
		}
		// For Each Transporte...
		for (Transporte transporte : transporteList)
		{
			// Make sure we set the parent key
			transporte.setParentId(parentId);

			if (ValidationUtil.isNull(transporte.getModelAction()))
			{
				continue;
			}
			switch (transporte.getModelAction())
			{
				case INSERT:
					count += insertTransporte(transporte, associateStatement, response);
					break;
				case UPDATE:
					count += updateTransporte(transporte, response);
					break;
				case DELETE:
					count += deleteTransporte(transporte, response);
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
