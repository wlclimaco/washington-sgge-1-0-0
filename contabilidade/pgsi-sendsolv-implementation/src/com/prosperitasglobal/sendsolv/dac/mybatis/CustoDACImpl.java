package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.dac.ICustoDAC;
import com.prosperitasglobal.sendsolv.model.Custo;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class CustoDACImpl extends SqlSessionDaoSupport implements ICustoDAC
{
	/** The Constant CONTACT_NAMESPACE. */
	private static final String CONTACT_NAMESPACE = "CustoMap.";

	/** The Constant CONTACT_STMT_UPDATE. */
	private static final String CONTACT_STMT_UPDATE = CONTACT_NAMESPACE + "updateCusto";

	/** The Constant CONTACT_STMT_UPDATE_PHONE. */
	private static final String CONTACT_STMT_UPDATE_PHONE = CONTACT_NAMESPACE + "updatePhone";

	/** The Constant CONTACT_STMT_DELETE_BUSINESS_CONTACT. */
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = CONTACT_NAMESPACE + "deleteBusinessCusto";

	/** The Constant CONTACT_STMT_INSERT. */
	private static final String CONTACT_STMT_INSERT = CONTACT_NAMESPACE + "insertCusto";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CustoDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertCusto(com.prosperitasglobal.cbof.model.Custo
	 * ,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertCusto(Custo custo, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root custo data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, custo, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateCusto(com.prosperitasglobal.cbof.model.Custo
	 * ,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateCusto(Custo custo, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(custo.getModelAction())
				&& (custo.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, custo, response);

			if (updateCount == 1)
			{
				custo.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		updateCount +=
				QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE_PHONE, custo, response);

		return updateCount;
	}

	public Integer maintainCustoAssociations(List<Custo> custoList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain Custos
		if (ValidationUtil.isNullOrEmpty(custoList))
		{
			return count;
		}
		// For Each Custo...
		for (Custo custo : custoList)
		{
			// Make sure we set the parent key
			custo.setParentId(parentId);

			if (ValidationUtil.isNull(custo.getModelAction()))
			{
				continue;
			}
			switch (custo.getModelAction())
			{
				case INSERT:
					count += insertCusto(custo, associateStatement, response);
					break;
				case UPDATE:
					count += updateCusto(custo, response);
					break;
				case DELETE:
					count += deleteCusto(custo, response);
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
	public InternalResultsResponse<Custo> fetchCustoById(Integer id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteCusto(Custo custo, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT, custo, response);
	}
}
