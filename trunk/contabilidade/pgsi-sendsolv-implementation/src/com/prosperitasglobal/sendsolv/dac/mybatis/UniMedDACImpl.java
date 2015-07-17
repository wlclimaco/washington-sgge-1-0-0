package com.prosperitasglobal.sendsolv.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.dac.IUniMedDAC;
import com.prosperitasglobal.sendsolv.model.UniMed;
import com.prosperitasglobal.sendsolv.model.UniMedProd;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class UniMedDACImpl extends SqlSessionDaoSupport implements IUniMedDAC
{
	/** The Constant UNIMED_NAMESPACE. */
	private static final String UNIMED_NAMESPACE = "UniMedMap.";

	/** The Constant UNIMED_STMT_UPDATE. */
	private static final String UNIMED_STMT_UPDATE = UNIMED_NAMESPACE + "updateUniMed";

	/** The Constant UNIMED_STMT_DELETE_PERSON_UNIMED. */
	private static final String UNIMED_STMT_DELETE = UNIMED_NAMESPACE + "deletePersonUniMed";

	/** The Constant UNIMED_STMT_INSERT. */
	private static final String UNIMED_STMT_INSERT = UNIMED_NAMESPACE + "insertUniMed";

	/** The Constant UNIMED_STMT_UPDATE. */
	private static final String UNIMED_PROD_STMT_UPDATE = UNIMED_NAMESPACE + "updateUniMedProd";

	/** The Constant UNIMED_STMT_DELETE_PERSON_UNIMED. */
	private static final String UNIMED_PROD_STMT_DELETE = UNIMED_NAMESPACE + "deletePersonUniMedProd";

	/** The Constant UNIMED_STMT_INSERT. */
	private static final String UNIMED_PROD_STMT_INSERT = UNIMED_NAMESPACE + "insertUniMedProd";

	/** The Constant UNIMED_STMT_FETCH_BY_ID. */
	private static final String UNIMED_STMT_FETCH_BY_ID = UNIMED_NAMESPACE + "fetchUniMedsById";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(UniMedDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertUniMed(com.prosperitasglobal.cbof.model.UniMed,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertUniMed(UniMed uniMed, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root uniMed data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), UNIMED_STMT_INSERT, uniMed, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
						.doInsert(getSqlSession(), statementName, uniMed, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IUniMedDAC#deleteBusinessUniMed(com.prosperitasglobal.cbof.model.UniMed,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteUniMed(UniMed uniMed, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), UNIMED_STMT_DELETE, uniMed, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateUniMed(com.prosperitasglobal.cbof.model.UniMed,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateUniMed(UniMed uniMed, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(uniMed.getModelAction())
				&& (uniMed.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), UNIMED_STMT_UPDATE, uniMed, response);

			if (updateCount == 1)
			{
				uniMed.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchUniMedById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<UniMed> fetchUniMedById(Integer id)
	{
		InternalResultsResponse<UniMed> response = new InternalResultsResponse<UniMed>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), UNIMED_STMT_FETCH_BY_ID, id, response);

		return response;
	}

	@Override
	public Integer deleteUniMedProd(UniMedProd uniMed, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), UNIMED_PROD_STMT_DELETE, uniMed, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateUniMed(com.prosperitasglobal.cbof.model.UniMed,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateUniMedProd(UniMedProd uniMed, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(uniMed.getModelAction())
				&& (uniMed.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), UNIMED_PROD_STMT_UPDATE, uniMed, response);

			if (updateCount == 1)
			{
				uniMed.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	@Override
	public Integer insertUniMedProd(UniMedProd uniMed, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root uniMed data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), UNIMED_PROD_STMT_INSERT, uniMed, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
						.doInsert(getSqlSession(), statementName, uniMed, response);

		return insertCount;
	}

	@Override
	public InternalResultsResponse<UniMed> fetchUniMedByRequest(PagedInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
