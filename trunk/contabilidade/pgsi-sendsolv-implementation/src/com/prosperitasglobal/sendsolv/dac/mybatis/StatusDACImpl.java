package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.model.Status;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class StatusDACImpl extends SqlSessionDaoSupport implements IStatusDAC
{

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(StatusDACImpl.class);
	private static final String CONTACT_STMT_INSERT = "StatusMap.insertStatus";
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = null;
	private static final String CONTACT_STMT_UPDATE = null;
	private static final String EMPRESA_STMT_FETCH_COUNT = null;
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = null;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertStatus(com.prosperitasglobal.cbof.
	 * model.Status,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertStatus(Status Status, String statementName,
			InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root Status data
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, Status, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
				.doInsert(getSqlSession(), statementName, Status, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IStatusDAC#deleteBusinessStatus(com.prosperitasglobal
	 * .cbof.model.Status,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteStatus(Status Status,
			InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT,
				Status, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateStatus(com.prosperitasglobal.cbof.
	 * model.Status,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateStatus(Status Status,
			InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(Status.getModelAction())
				&& (Status.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper
					.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, Status, response);

			if (updateCount == 1)
			{
				Status.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchStatusByParent(java.lang.Integer,
	// * com.prosperitasglobal.sendsolv.model.BusinessTypeEnum)
	// */
	// @Override
	// public InternalResultsResponse<Status> fetchStatusByRequest(
	// PagedInquiryRequest request)
	// {
	// InternalResultsResponse<Status> response =
	// new InternalResultsResponse<Status>();
	//
	// /*
	// * Helper method to translation from the user friendly" sort field names to the
	// * actual database column names.
	// */
	// // QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());
	//
	// PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request,
	// EMPRESA_STMT_FETCH_COUNT,
	// EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
	// return response;
	// }

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IStatusDAC#maintainStatusAssociations(java.util
	 * .List, java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	public Integer maintainStatusAssociations(List<Status> StatusList,
			Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain Statuss
		if (ValidationUtil.isNullOrEmpty(StatusList))
		{
			return count;
		}
		// For Each Status...
		for (Status Status : StatusList)
		{
			// Make sure we set the parent key
			Status.setParentId(parentId);

			if (ValidationUtil.isNull(Status.getModelAction()))
			{
				continue;
			}
			switch (Status.getModelAction())
			{
				case INSERT:
					count += insertStatus(Status, associateStatement, response);
					break;
				case UPDATE:
					count += updateStatus(Status, response);
					break;
				case DELETE:
					count += deleteStatus(Status, response);
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

	@Override
	public InternalResultsResponse<Status> fetchStatusById(Integer id)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
