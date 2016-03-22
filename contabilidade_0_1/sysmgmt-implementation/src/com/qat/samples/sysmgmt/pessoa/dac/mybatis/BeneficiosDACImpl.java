package com.qat.samples.sysmgmt.pessoa.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.beneficios.BeneficioPessoa;
import com.qat.samples.sysmgmt.beneficios.Beneficios;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IBeneficiosDAC;

/**
 * The Class BeneficiosDACImpl.
 */
public class BeneficiosDACImpl extends SqlSessionDaoSupport implements IBeneficiosDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "BeneficiosMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchBeneficiosRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllBeneficiossByRequest";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertBeneficios";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateBeneficios";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteBeneficiosById";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(BeneficiosDACImpl.class);

	/** The valid sort fields for an beneficios inquiry. Will be injected by Spring. */
	private Map<String, String> beneficiosInquiryValidSortFields;

	/**
	 * Get the valid sort fields for the beneficios inquiry. Attribute injected by Spring.
	 * 
	 * @return The valid sort fields for the beneficios inquiry.
	 */
	public Map<String, String> getBeneficiosInquiryValidSortFields()
	{
		return beneficiosInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the beneficios inquiry. Attribute injected by Spring.
	 * 
	 * @param beneficiosInquiryValidSortFields The valid sort fields for the beneficios inquiry to set.
	 */
	public void setBeneficiosInquiryValidSortFields(Map<String, String> beneficiosInquiryValidSortFields)
	{
		this.beneficiosInquiryValidSortFields = beneficiosInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IBeneficiosDAC#insertBeneficios(com.prosperitasglobal.sendsolv.model
	 * .Beneficios)
	 */
	@Override
	public Integer insertBeneficios(Beneficios beneficios, String string, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		response = new InternalResultsResponse<Beneficios>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, beneficios, response);

		if (response.isInError())
		{
			return null;
		}

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IBeneficiosDAC#updateBeneficios(com.prosperitasglobal.sendsolv.model
	 * .Beneficios)
	 */
	@Override
	public Integer updateBeneficios(Beneficios beneficios, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;
		response = new InternalResultsResponse<Beneficios>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(beneficios.getModelAction())
				&& (beneficios.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, beneficios,
							response);
		}

		if (response.isInError())
		{
			return null;
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IBeneficiosDAC#deleteBeneficios(com.prosperitasglobal.sendsolv.model
	 * .Beneficios)
	 */
	@Override
	public Integer deleteBeneficios(Beneficios beneficios, InternalResultsResponse<?> response)
	{

		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, beneficios, response);
		if (response.isInError())
		{
			return null;
		}
		else
		{
			return 1;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IBeneficiosDAC#fetchBeneficiosByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Beneficios> fetchBeneficiosByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Beneficios> response = new InternalResultsResponse<Beneficios>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getBeneficiosInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	@Override
	public Integer updateBeneficioPessoa(BeneficioPessoa beneficios, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;
		response = new InternalResultsResponse<BeneficioPessoa>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(beneficios.getModelAction())
				&& (beneficios.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), "BeneficiosMap.updateBeneficioPessoa", beneficios,
							response);
		}

		if (response.isInError())
		{
			return null;
		}

		return updateCount;
	}

	@Override
	public Integer insertBeneficioPessoa(BeneficioPessoa beneficios, String string, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		response = new InternalResultsResponse<BeneficioPessoa>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "BeneficiosMap.insertBeneficioPerson", beneficios,
						response);

		return insertCount;
	}

	@Override
	public Integer deleteBeneficioPessoa(BeneficioPessoa beneficios, InternalResultsResponse<?> response)
	{

		QATMyBatisDacHelper.doRemove(getSqlSession(), "BeneficiosMap.deleteBeneficioPerson", beneficios, response);
		if (response.isInError())
		{
			return null;
		}
		else
		{
			return 1;
		}
	}

}
