package com.qat.samples.sysmgmt.produto.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.produto.dac.IServicoDAC;
import com.qat.samples.sysmgmt.produto.model.PlanoByServico;
import com.qat.samples.sysmgmt.produto.model.Servico;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class ServicoDACImpl extends SqlSessionDaoSupport implements IServicoDAC
{
	/** The Constant CONTACT_NAMESPACE. */
	private static final String CONTACT_NAMESPACE = "ServicoMap.";

	/** The Constant CONTACT_STMT_UPDATE. */
	private static final String CONTACT_STMT_UPDATE = CONTACT_NAMESPACE + "updateServico";

	/** The Constant CONTACT_STMT_UPDATE_PHONE. */
	private static final String CONTACT_STMT_UPDATE_PHONE = CONTACT_NAMESPACE + "updatePhone";

	/** The Constant CONTACT_STMT_INSERT. */
	private static final String CONTACT_STMT_INSERT = CONTACT_NAMESPACE + "insertPlanoByServico";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ServicoDACImpl.class);

	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = null;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertServico(com.prosperitasglobal.cbof.model.Servico
	 * ,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertServico(PlanoByServico servico, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root servico data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, servico, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IServicoDAC#deleteBusinessServico(com.prosperitasglobal.cbof.model.Servico,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteServico(PlanoByServico servico, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT, servico, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateServico(com.prosperitasglobal.cbof.model.Servico
	 * ,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateServico(PlanoByServico servico, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(servico.getModelAction())
				&& (servico.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, servico, response);

			if (updateCount == 1)
			{
				servico.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		updateCount +=
				QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE_PHONE, servico, response);

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.IServicoDAC#maintainServicoAssociations(java.util.List,
	 * java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	public Integer maintainServicoAssociations(List<PlanoByServico> servicoList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain Servicos
		if (ValidationUtil.isNullOrEmpty(servicoList))
		{
			return count;
		}
		// For Each Servico...
		for (PlanoByServico servico : servicoList)
		{
			// Make sure we set the parent key
			servico.setParentId(parentId);

			if (ValidationUtil.isNull(servico.getModelAction()))
			{
				continue;
			}
			switch (servico.getModelAction())
			{
				case INSERT:
					count += insertServico(servico, associateStatement, response);
					break;
				case UPDATE:
					count += updateServico(servico, response);
					break;
				case DELETE:
					count += deleteServico(servico, response);
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
	public InternalResultsResponse<Servico> fetchServicoById(Integer id)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
