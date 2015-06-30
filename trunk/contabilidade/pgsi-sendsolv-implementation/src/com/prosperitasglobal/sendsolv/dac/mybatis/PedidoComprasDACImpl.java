package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.List;

import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class PedidoComprasDACImpl extends SqlSessionDaoSupport implements IPedidoComprasDAC
{

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PedidoComprasDACImpl.class);
	private static final String CONTACT_STMT_INSERT = null;
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = null;
	private static final String CONTACT_STMT_UPDATE = null;
	private static final String EMPRESA_STMT_FETCH_COUNT = null;
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = null;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertPedidoCompras(com.prosperitasglobal.cbof.
	 * model.PedidoCompras,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertPedidoCompras(PedidoCompras pedidoCompras, String statementName,
			InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root pedidoCompras data
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, pedidoCompras, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
						.doInsert(getSqlSession(), statementName, pedidoCompras, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IPedidoComprasDAC#deleteBusinessPedidoCompras(com.prosperitasglobal
	 * .cbof.model.PedidoCompras,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deletePedidoCompras(PedidoCompras pedidoCompras,
			InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT,
				pedidoCompras, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updatePedidoCompras(com.prosperitasglobal.cbof.
	 * model.PedidoCompras,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updatePedidoCompras(PedidoCompras pedidoCompras,
			InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(pedidoCompras.getModelAction())
				&& (pedidoCompras.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper
							.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, pedidoCompras, response);

			if (updateCount == 1)
			{
				pedidoCompras.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchPedidoComprasByParent(java.lang.Integer,
	 * com.prosperitasglobal.sendsolv.model.BusinessTypeEnum)
	 */
	@Override
	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasByRequest(
			PagedInquiryRequest request)
	{
		InternalResultsResponse<PedidoCompras> response =
				new InternalResultsResponse<PedidoCompras>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequestPedidoCompras(getSqlSession(), request,
				EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchPedidoComprasById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasById(Integer id)
	{
		InternalResultsResponse<PedidoCompras> response =
				new InternalResultsResponse<PedidoCompras>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), CONTACT_STMT_FETCH_BY_ID, id, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IPedidoComprasDAC#maintainPedidoComprasAssociations(java.util
	 * .List, java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer maintainPedidoComprasAssociations(List<PedidoCompras> pedidoComprasList,
			Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain PedidoComprass
		if (ValidationUtil.isNullOrEmpty(pedidoComprasList))
		{
			return count;
		}
		// For Each PedidoCompras...
		for (PedidoCompras pedidoCompras : pedidoComprasList)
		{
			// Make sure we set the parent key
			pedidoCompras.setParentId(parentId);

			if (ValidationUtil.isNull(pedidoCompras.getModelAction()))
			{
				continue;
			}
			switch (pedidoCompras.getModelAction())
			{
				case INSERT:
					count += insertPedidoCompras(pedidoCompras, associateStatement, response);
					break;
				case UPDATE:
					count += updatePedidoCompras(pedidoCompras, response);
					break;
				case DELETE:
					count += deletePedidoCompras(pedidoCompras, response);
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
