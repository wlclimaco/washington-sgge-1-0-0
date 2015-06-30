package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.List;

import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class NFStatusDACImpl extends SqlSessionDaoSupport implements INFStatusDAC
{

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(NFStatusDACImpl.class);
	private static final String CONTACT_STMT_INSERT = null;
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = null;
	private static final String CONTACT_STMT_UPDATE = null;
	private static final String EMPRESA_STMT_FETCH_COUNT = null;
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = null;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertNFStatus(com.prosperitasglobal.cbof.
	 * model.NFStatus,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertNFStatus(NFStatus nFStatus, String statementName,
			InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root nFStatus data
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, nFStatus, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
						.doInsert(getSqlSession(), statementName, nFStatus, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.INFStatusDAC#deleteBusinessNFStatus(com.prosperitasglobal
	 * .cbof.model.NFStatus,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteNFStatus(NFStatus nFStatus,
			InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT,
				nFStatus, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateNFStatus(com.prosperitasglobal.cbof.
	 * model.NFStatus,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateNFStatus(NFStatus nFStatus,
			InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(nFStatus.getModelAction())
				&& (nFStatus.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper
							.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, nFStatus, response);

			if (updateCount == 1)
			{
				nFStatus.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchNFStatusByParent(java.lang.Integer,
	 * com.prosperitasglobal.sendsolv.model.BusinessTypeEnum)
	 */
	@Override
	public InternalResultsResponse<NFStatus> fetchNFStatusByRequest(
			PagedInquiryRequest request)
	{
		InternalResultsResponse<NFStatus> response =
				new InternalResultsResponse<NFStatus>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequestNFStatus(getSqlSession(), request,
				EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchNFStatusById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<NFStatus> fetchNFStatusById(Integer id)
	{
		InternalResultsResponse<NFStatus> response =
				new InternalResultsResponse<NFStatus>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), CONTACT_STMT_FETCH_BY_ID, id, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.INFStatusDAC#maintainNFStatusAssociations(java.util
	 * .List, java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer maintainNFStatusAssociations(List<NFStatus> nFStatusList,
			Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain NFStatuss
		if (ValidationUtil.isNullOrEmpty(nFStatusList))
		{
			return count;
		}
		// For Each NFStatus...
		for (NFStatus nFStatus : nFStatusList)
		{
			// Make sure we set the parent key
			nFStatus.setParentId(parentId);

			if (ValidationUtil.isNull(nFStatus.getModelAction()))
			{
				continue;
			}
			switch (nFStatus.getModelAction())
			{
				case INSERT:
					count += insertNFStatus(nFStatus, associateStatement, response);
					break;
				case UPDATE:
					count += updateNFStatus(nFStatus, response);
					break;
				case DELETE:
					count += deleteNFStatus(nFStatus, response);
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
