package com.prosperitasglobal.sendsolv.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.dac.IHistoricoNFDAC;
import com.prosperitasglobal.sendsolv.model.HistoricoNF;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class HistoricoNFDACImpl extends SqlSessionDaoSupport implements IHistoricoNFDAC
{

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(HistoricoNFDACImpl.class);
	private static final String CONTACT_STMT_INSERT = "HistoricoNFMap.insertHistoricoNF";
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = null;
	private static final String CONTACT_STMT_UPDATE = null;
	private static final String EMPRESA_STMT_FETCH_COUNT = null;
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = null;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertHistoricoNF(com.prosperitasglobal.cbof.
	 * model.HistoricoNF,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertHistoricoNF(HistoricoNF HistoricoNF, String statementName,
			InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root HistoricoNF data
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, HistoricoNF, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IHistoricoNFDAC#deleteBusinessHistoricoNF(com.prosperitasglobal
	 * .cbof.model.HistoricoNF,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteHistoricoNF(HistoricoNF HistoricoNF,
			InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT,
				HistoricoNF, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateHistoricoNF(com.prosperitasglobal.cbof.
	 * model.HistoricoNF,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateHistoricoNF(HistoricoNF HistoricoNF,
			InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(HistoricoNF.getModelAction())
				&& (HistoricoNF.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper
							.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, HistoricoNF, response);

			if (updateCount == 1)
			{
				HistoricoNF.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

}
