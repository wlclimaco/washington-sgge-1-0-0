package com.prosperitasglobal.sendsolv.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.dac.ISubGrupoDAC;
import com.prosperitasglobal.sendsolv.model.SubGrupo;
import com.prosperitasglobal.sendsolv.model.SubGrupoProd;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class SubGrupoDACImpl extends SqlSessionDaoSupport implements ISubGrupoDAC
{
	/** The Constant UNIMED_NAMESPACE. */
	private static final String UNIMED_NAMESPACE = "SubGrupoMap.";

	/** The Constant UNIMED_STMT_UPDATE. */
	private static final String UNIMED_STMT_UPDATE = UNIMED_NAMESPACE + "updateSubGrupo";

	/** The Constant UNIMED_STMT_DELETE_PERSON_UNIMED. */
	private static final String UNIMED_STMT_DELETE = UNIMED_NAMESPACE + "deletePersonSubGrupo";

	/** The Constant UNIMED_STMT_INSERT. */
	private static final String UNIMED_STMT_INSERT = UNIMED_NAMESPACE + "insertSubGrupo";

	/** The Constant UNIMED_STMT_UPDATE. */
	private static final String UNIMED_PROD_STMT_UPDATE = UNIMED_NAMESPACE + "updateSubGrupoProd";

	/** The Constant UNIMED_STMT_DELETE_PERSON_UNIMED. */
	private static final String UNIMED_PROD_STMT_DELETE = UNIMED_NAMESPACE + "deletePersonSubGrupoProd";

	/** The Constant UNIMED_STMT_INSERT. */
	private static final String UNIMED_PROD_STMT_INSERT = UNIMED_NAMESPACE + "insertSubGrupoProd";

	/** The Constant UNIMED_STMT_FETCH_BY_ID. */
	private static final String UNIMED_STMT_FETCH_BY_ID = UNIMED_NAMESPACE + "fetchSubGruposById";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SubGrupoDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertSubGrupo(com.prosperitasglobal.cbof.model.SubGrupo,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertSubGrupo(SubGrupo subGrupo, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root subGrupo data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), UNIMED_STMT_INSERT, subGrupo, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
				.doInsert(getSqlSession(), statementName, subGrupo, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ISubGrupoDAC#deleteBusinessSubGrupo(com.prosperitasglobal.cbof.model.SubGrupo,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteSubGrupo(SubGrupo subGrupo, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), UNIMED_STMT_DELETE, subGrupo, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateSubGrupo(com.prosperitasglobal.cbof.model.SubGrupo,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateSubGrupo(SubGrupo subGrupo, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(subGrupo.getModelAction())
				&& (subGrupo.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), UNIMED_STMT_UPDATE, subGrupo, response);

			if (updateCount == 1)
			{
				subGrupo.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchSubGrupoById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<SubGrupo> fetchSubGrupoById(Integer id)
	{
		InternalResultsResponse<SubGrupo> response = new InternalResultsResponse<SubGrupo>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), UNIMED_STMT_FETCH_BY_ID, id, response);

		return response;
	}

	@Override
	public Integer deleteSubGrupoProd(SubGrupoProd subGrupo, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), UNIMED_PROD_STMT_DELETE, subGrupo, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateSubGrupo(com.prosperitasglobal.cbof.model.SubGrupo,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateSubGrupoProd(SubGrupoProd subGrupo, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(subGrupo.getModelAction())
				&& (subGrupo.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), UNIMED_PROD_STMT_UPDATE, subGrupo, response);

			if (updateCount == 1)
			{
				subGrupo.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	@Override
	public Integer insertSubGrupoProd(SubGrupoProd subGrupo, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root subGrupo data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), UNIMED_PROD_STMT_INSERT, subGrupo, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
				.doInsert(getSqlSession(), statementName, subGrupo, response);

		return insertCount;
	}

	@Override
	public InternalResultsResponse<SubGrupo> fetchSubGrupoByRequest(PagedInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
