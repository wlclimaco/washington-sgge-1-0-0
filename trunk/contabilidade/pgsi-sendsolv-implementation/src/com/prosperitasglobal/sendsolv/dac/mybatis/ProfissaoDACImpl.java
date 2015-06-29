package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.dac.IProfissaoDAC;
import com.prosperitasglobal.sendsolv.model.Profissao;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class ProfissaoDACImpl extends SqlSessionDaoSupport implements IProfissaoDAC
{
	/** The Constant CONTACT_NAMESPACE. */
	private static final String CONTACT_NAMESPACE = "ProfissaoMap.";

	/** The Constant CONTACT_STMT_UPDATE. */
	private static final String CONTACT_STMT_UPDATE = CONTACT_NAMESPACE + "updateProfissao";

	/** The Constant CONTACT_STMT_UPDATE_PHONE. */
	private static final String CONTACT_STMT_UPDATE_PHONE = CONTACT_NAMESPACE + "updatePhone";

	/** The Constant CONTACT_STMT_DELETE_BUSINESS_CONTACT. */
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = CONTACT_NAMESPACE + "deleteBusinessProfissao";

	/** The Constant CONTACT_STMT_INSERT. */
	private static final String CONTACT_STMT_INSERT = CONTACT_NAMESPACE + "insertProfissao";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ProfissaoDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertProfissao(com.prosperitasglobal.cbof.model.Profissao
	 * ,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertProfissao(Profissao profissao, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root profissao data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, profissao, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IProfissaoDAC#deleteBusinessProfissao(com.prosperitasglobal.cbof.model.Profissao,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteProfissao(Profissao profissao, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT, profissao, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateProfissao(com.prosperitasglobal.cbof.model.Profissao
	 * ,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateProfissao(Profissao profissao, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(profissao.getModelAction())
				&& (profissao.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, profissao, response);

			if (updateCount == 1)
			{
				profissao.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		updateCount +=
				QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE_PHONE, profissao, response);

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.IProfissaoDAC#maintainProfissaoAssociations(java.util.List,
	 * java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	public Integer maintainProfissaoAssociations(List<Profissao> profissaoList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain Profissaos
		if (ValidationUtil.isNullOrEmpty(profissaoList))
		{
			return count;
		}
		// For Each Profissao...
		for (Profissao profissao : profissaoList)
		{
			// Make sure we set the parent key
			profissao.setParentId(parentId);

			if (ValidationUtil.isNull(profissao.getModelAction()))
			{
				continue;
			}
			switch (profissao.getModelAction())
			{
				case INSERT:
					count += insertProfissao(profissao, associateStatement, response);
					break;
				case UPDATE:
					count += updateProfissao(profissao, response);
					break;
				case DELETE:
					count += deleteProfissao(profissao, response);
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
	public InternalResultsResponse<Profissao> fetchProfissaoById(Integer id)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
