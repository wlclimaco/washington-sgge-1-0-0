package com.prosperitasglobal.sendsolv.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.dac.ICnaeDAC;
import com.prosperitasglobal.sendsolv.model.Cnae;
import com.prosperitasglobal.sendsolv.model.CnaeEmpresa;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class CnaeDACImpl extends SqlSessionDaoSupport implements ICnaeDAC
{
	/** The Constant CONTACT_NAMESPACE. */
	private static final String CONTACT_NAMESPACE = "CnaeMap.";

	/** The Constant CONTACT_STMT_UPDATE. */
	private static final String CONTACT_STMT_UPDATE = CONTACT_NAMESPACE + "updateCnae";

	/** The Constant CONTACT_STMT_DELETE_BUSINESS_CONTACT. */
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = CONTACT_NAMESPACE + "deleteBusinessCnae";

	/** The Constant CONTACT_STMT_INSERT. */
	private static final String CONTACT_STMT_INSERT = CONTACT_NAMESPACE + "insertCnae";

	/** The Constant CONTACT_STMT_FETCH_BY_ID. */
	private static final String CONTACT_STMT_FETCH_BY_ID = CONTACT_NAMESPACE + "fetchCnaesById";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CnaeDACImpl.class);

	private static final String CNAE_EMPRESA_STMT_UPDATE = CONTACT_NAMESPACE + "updateCnaeEmpresa";

	private static final String CNAE_EMPRESA_STMT_DELETE = CONTACT_NAMESPACE + "deleteCnaeEmpresaById";

	private static final String CNAE_EMPRESA_STMT_INSERT = CONTACT_NAMESPACE + "insertCnaeEmpresa";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertCnae(com.prosperitasglobal.cbof.model.Cnae,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertCnae(Cnae cnae)
	{
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();
		Integer insertCount = 0;
		// First insert the root cnae data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, cnae, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICnaeDAC#deleteBusinessCnae(com.prosperitasglobal.cbof.model.Cnae,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteCnae(Cnae cnae)
	{
		InternalResponse response = new InternalResponse();
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT, cnae, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateCnae(com.prosperitasglobal.cbof.model.Cnae,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateCnae(Cnae cnae)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();
		// First update the root if necessary.
		if (!ValidationUtil.isNull(cnae.getModelAction())
				&& (cnae.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, cnae, response);

			if (updateCount == 1)
			{
				cnae.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchCnaeById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<Cnae> fetchCnaeById(Integer id)
	{
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), CONTACT_STMT_FETCH_BY_ID, id, response);

		return response;
	}

	@Override
	public Integer insertCnaeEmpresa(CnaeEmpresa cnae)
	{
		Integer insertCount = 0;
		InternalResultsResponse<CnaeEmpresa> response = new InternalResultsResponse<CnaeEmpresa>();
		// First insert the root cnae data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CNAE_EMPRESA_STMT_INSERT, cnae, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICnaeDAC#deleteBusinessCnae(com.prosperitasglobal.cbof.model.Cnae,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteCnaeEmpresa(CnaeEmpresa cnae)
	{
		InternalResponse response = new InternalResponse();
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CNAE_EMPRESA_STMT_DELETE, cnae, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateCnae(com.prosperitasglobal.cbof.model.Cnae,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateCnaeEmpresa(CnaeEmpresa cnae)
	{
		Integer updateCount = 0;
		InternalResultsResponse<CnaeEmpresa> response = new InternalResultsResponse<CnaeEmpresa>();
		// First update the root if necessary.
		if (!ValidationUtil.isNull(cnae.getModelAction())
				&& (cnae.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CNAE_EMPRESA_STMT_UPDATE, cnae, response);

			if (updateCount == 1)
			{
				cnae.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	@Override
	public InternalResultsResponse<Cnae> fetchCnaeByRequest(PagedInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
