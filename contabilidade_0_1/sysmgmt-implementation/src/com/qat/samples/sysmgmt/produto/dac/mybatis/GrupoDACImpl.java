package com.qat.samples.sysmgmt.produto.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.produto.dac.IGrupoDAC;
import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.produto.model.GrupoProd;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class GrupoDACImpl extends SqlSessionDaoSupport implements IGrupoDAC
{
	/** The Constant UNIMED_NAMESPACE. */
	private static final String UNIMED_NAMESPACE = "GrupoMap.";

	/** The Constant UNIMED_STMT_UPDATE. */
	private static final String UNIMED_STMT_UPDATE = UNIMED_NAMESPACE + "updateGrupo";

	/** The Constant UNIMED_STMT_DELETE_PERSON_UNIMED. */
	private static final String UNIMED_STMT_DELETE = UNIMED_NAMESPACE + "deletePersonGrupo";

	/** The Constant UNIMED_STMT_INSERT. */
	private static final String UNIMED_STMT_INSERT = UNIMED_NAMESPACE + "insertGrupo";

	/** The Constant UNIMED_STMT_UPDATE. */
	private static final String UNIMED_PROD_STMT_UPDATE = UNIMED_NAMESPACE + "updateGrupoProd";

	/** The Constant UNIMED_STMT_DELETE_PERSON_UNIMED. */
	private static final String UNIMED_PROD_STMT_DELETE = UNIMED_NAMESPACE + "deletePersonGrupoProd";

	/** The Constant UNIMED_STMT_INSERT. */
	private static final String UNIMED_PROD_STMT_INSERT = UNIMED_NAMESPACE + "insertGrupoProd";

	/** The Constant UNIMED_STMT_FETCH_BY_ID. */
	private static final String UNIMED_STMT_FETCH_BY_ID = UNIMED_NAMESPACE + "fetchGruposById";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(GrupoDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertGrupo(com.prosperitasglobal.cbof.model.Grupo,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertGrupo(Grupo grupo, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root grupo data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), UNIMED_STMT_INSERT, grupo, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
						.doInsert(getSqlSession(), statementName, grupo, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IGrupoDAC#deleteBusinessGrupo(com.prosperitasglobal.cbof.model.Grupo,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteGrupo(Grupo grupo, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), UNIMED_STMT_DELETE, grupo, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateGrupo(com.prosperitasglobal.cbof.model.Grupo,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateGrupo(Grupo grupo, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(grupo.getModelAction())
				&& (grupo.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), UNIMED_STMT_UPDATE, grupo, response);

			if (updateCount == 1)
			{
				grupo.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchGrupoById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<Grupo> fetchGrupoById(Integer id)
	{
		InternalResultsResponse<Grupo> response = new InternalResultsResponse<Grupo>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), UNIMED_STMT_FETCH_BY_ID, id, response);

		return response;
	}

	@Override
	public Integer deleteGrupoProd(GrupoProd grupo, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), UNIMED_PROD_STMT_DELETE, grupo, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateGrupo(com.prosperitasglobal.cbof.model.Grupo,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateGrupoProd(GrupoProd grupo, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(grupo.getModelAction())
				&& (grupo.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), UNIMED_PROD_STMT_UPDATE, grupo, response);

			if (updateCount == 1)
			{
				grupo.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	@Override
	public Integer insertGrupoProd(GrupoProd grupo, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root grupo data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), UNIMED_PROD_STMT_INSERT, grupo, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
						.doInsert(getSqlSession(), statementName, grupo, response);

		return insertCount;
	}

	@Override
	public InternalResultsResponse<Grupo> fetchGrupoByRequest(PagedInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
