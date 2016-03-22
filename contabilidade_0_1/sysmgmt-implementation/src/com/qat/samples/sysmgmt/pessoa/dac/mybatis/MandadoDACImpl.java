package com.qat.samples.sysmgmt.pessoa.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.dp.Profissao;
import com.qat.samples.sysmgmt.pessoa.dac.IProfissaoDAC;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class MandadoDACImpl extends SqlSessionDaoSupport implements IMandadoDAC
{
	/** The Constant CONTACT_NAMESPACE. */
	private static final String MANDADO_NAMESPACE = "MandadoMap.";

	/** The Constant MANDADO_STMT_UPDATE. */
	private static final String MANDADO_STMT_UPDATE = MANDADO_NAMESPACE + "updateMandado";

	/** The Constant MANDADO_STMT_DELETE_BUSINESS_MANDADO. */
	private static final String MANDADO_STMT_DELETE_MANDADO = MANDADO_NAMESPACE + "deleteMandado";

	/** The Constant MANDADO_STMT_INSERT. */
	private static final String MANDADO_STMT_INSERT = MANDADO_NAMESPACE + "insertMandado";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MandadoDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertMandado(com.prosperitasglobal.cbof.model.Mandado
	 * ,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertMandado(Mandado mandado, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root mandado data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), MANDADO_STMT_INSERT, mandado, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IMandadoDAC#deleteBusinessMandado(com.prosperitasglobal.cbof.model.Mandado,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteMandado(Mandado mandado, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), MANDADO_STMT_DELETE_MANDADO, mandado, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateMandado(com.prosperitasglobal.cbof.model.Mandado
	 * ,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateMandado(Mandado mandado, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(mandado.getModelAction())
				&& (mandado.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), MANDADO_STMT_UPDATE, mandado, response);

			if (updateCount == 1)
			{
				mandado.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.IMandadoDAC#maintainMandadoAssociations(java.util.List,
	 * java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	public Integer maintainMandadoAssociations(List<Mandado> mandadoList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain Mandados
		if (ValidationUtil.isNullOrEmpty(mandadoList))
		{
			return count;
		}
		// For Each Mandado...
		for (Mandado mandado : mandadoList)
		{
			// Make sure we set the parent key
			mandado.setParentId(parentId);

			if (ValidationUtil.isNull(mandado.getModelAction()))
			{
				continue;
			}
			switch (mandado.getModelAction())
			{
				case INSERT:
					count += insertMandado(mandado, associateStatement, response);
					break;
				case UPDATE:
					count += updateMandado(mandado, response);
					break;
				case DELETE:
					count += deleteMandado(mandado, response);
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
	public InternalResultsResponse<Mandado> fetchMandadoById(Integer id)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
