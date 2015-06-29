package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.dac.IRentabilidadeDAC;
import com.prosperitasglobal.sendsolv.model.Rentabilidade;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class RentabilidadeDACImpl extends SqlSessionDaoSupport implements IRentabilidadeDAC
{
	/** The Constant CONTACT_NAMESPACE. */
	private static final String CONTACT_NAMESPACE = "RentabilidadeMap.";

	/** The Constant CONTACT_STMT_UPDATE. */
	private static final String CONTACT_STMT_UPDATE = CONTACT_NAMESPACE + "updateRentabilidade";

	/** The Constant CONTACT_STMT_UPDATE_PHONE. */
	private static final String CONTACT_STMT_UPDATE_PHONE = CONTACT_NAMESPACE + "updatePhone";

	/** The Constant CONTACT_STMT_DELETE_BUSINESS_CONTACT. */
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = CONTACT_NAMESPACE
			+ "deleteBusinessRentabilidade";

	/** The Constant CONTACT_STMT_INSERT. */
	private static final String CONTACT_STMT_INSERT = CONTACT_NAMESPACE + "insertRentabilidade";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(RentabilidadeDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertRentabilidade(com.prosperitasglobal.cbof.model.
	 * Rentabilidade
	 * ,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertRentabilidade(Rentabilidade rentabilidade,
			String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root rentabilidade data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, rentabilidade, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IRentabilidadeDAC#deleteBusinessRentabilidade(com.prosperitasglobal.cbof.model.
	 * Rentabilidade,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteRentabilidade(Rentabilidade rentabilidade, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT, rentabilidade,
				response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateRentabilidade(com.prosperitasglobal.cbof.model.
	 * Rentabilidade
	 * ,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateRentabilidade(Rentabilidade rentabilidade, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(rentabilidade.getModelAction())
				&& (rentabilidade.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, rentabilidade, response);

			if (updateCount == 1)
			{
				rentabilidade.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		updateCount +=
				QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE_PHONE, rentabilidade, response);

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.IRentabilidadeDAC#maintainRentabilidadeAssociations(java.util.List,
	 * java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	public Integer maintainRentabilidadeAssociations(List<Rentabilidade> rentabilidadeList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain Rentabilidades
		if (ValidationUtil.isNullOrEmpty(rentabilidadeList))
		{
			return count;
		}
		// For Each Rentabilidade...
		for (Rentabilidade rentabilidade : rentabilidadeList)
		{
			// Make sure we set the parent key
			rentabilidade.setParentId(parentId);

			if (ValidationUtil.isNull(rentabilidade.getModelAction()))
			{
				continue;
			}
			switch (rentabilidade.getModelAction())
			{
				case INSERT:
					count += insertRentabilidade(rentabilidade, associateStatement, response);
					break;
				case UPDATE:
					count += updateRentabilidade(rentabilidade, response);
					break;
				case DELETE:
					count += deleteRentabilidade(rentabilidade, response);
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
	public InternalResultsResponse<Rentabilidade> fetchRentabilidadeById(Integer id)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
