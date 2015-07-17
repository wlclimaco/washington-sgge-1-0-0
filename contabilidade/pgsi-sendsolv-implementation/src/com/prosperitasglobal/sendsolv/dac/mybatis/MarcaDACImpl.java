package com.prosperitasglobal.sendsolv.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.dac.IMarcaDAC;
import com.prosperitasglobal.sendsolv.model.Marca;
import com.prosperitasglobal.sendsolv.model.MarcaProd;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class MarcaDACImpl extends SqlSessionDaoSupport implements IMarcaDAC
{
	/** The Constant UNIMED_NAMESPACE. */
	private static final String UNIMED_NAMESPACE = "MarcaMap.";

	/** The Constant UNIMED_STMT_UPDATE. */
	private static final String UNIMED_STMT_UPDATE = UNIMED_NAMESPACE + "updateMarca";

	/** The Constant UNIMED_STMT_DELETE_PERSON_UNIMED. */
	private static final String UNIMED_STMT_DELETE = UNIMED_NAMESPACE + "deletePersonMarca";

	/** The Constant UNIMED_STMT_INSERT. */
	private static final String UNIMED_STMT_INSERT = UNIMED_NAMESPACE + "insertMarca";

	/** The Constant UNIMED_STMT_UPDATE. */
	private static final String UNIMED_PROD_STMT_UPDATE = UNIMED_NAMESPACE + "updateMarcaProd";

	/** The Constant UNIMED_STMT_DELETE_PERSON_UNIMED. */
	private static final String UNIMED_PROD_STMT_DELETE = UNIMED_NAMESPACE + "deletePersonMarcaProd";

	/** The Constant UNIMED_STMT_INSERT. */
	private static final String UNIMED_PROD_STMT_INSERT = UNIMED_NAMESPACE + "insertMarcaProd";

	/** The Constant UNIMED_STMT_FETCH_BY_ID. */
	private static final String UNIMED_STMT_FETCH_BY_ID = UNIMED_NAMESPACE + "fetchMarcasById";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MarcaDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertMarca(com.prosperitasglobal.cbof.model.Marca,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertMarca(Marca marca, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root marca data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), UNIMED_STMT_INSERT, marca, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
				.doInsert(getSqlSession(), statementName, marca, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IMarcaDAC#deleteBusinessMarca(com.prosperitasglobal.cbof.model.Marca,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteMarca(Marca marca, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), UNIMED_STMT_DELETE, marca, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateMarca(com.prosperitasglobal.cbof.model.Marca,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateMarca(Marca marca, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(marca.getModelAction())
				&& (marca.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), UNIMED_STMT_UPDATE, marca, response);

			if (updateCount == 1)
			{
				marca.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchMarcaById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<Marca> fetchMarcaById(Integer id)
	{
		InternalResultsResponse<Marca> response = new InternalResultsResponse<Marca>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), UNIMED_STMT_FETCH_BY_ID, id, response);

		return response;
	}

	@Override
	public Integer deleteMarcaProd(MarcaProd marca, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), UNIMED_PROD_STMT_DELETE, marca, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateMarca(com.prosperitasglobal.cbof.model.Marca,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateMarcaProd(MarcaProd marca, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(marca.getModelAction())
				&& (marca.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), UNIMED_PROD_STMT_UPDATE, marca, response);

			if (updateCount == 1)
			{
				marca.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	@Override
	public Integer insertMarcaProd(MarcaProd marca, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root marca data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), UNIMED_PROD_STMT_INSERT, marca, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
				.doInsert(getSqlSession(), statementName, marca, response);

		return insertCount;
	}

	@Override
	public InternalResultsResponse<Marca> fetchMarcaByRequest(PagedInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
