package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.dac.IBeneficiosDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.Beneficios;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class BeneficiosDACImpl.
 */
public class BeneficiosDACImpl extends SqlSessionDaoSupport implements IBeneficiosDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "BeneficiosMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchBeneficiosRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllBeneficiossByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchBeneficiosById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertBeneficios";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_CONTACT = EMPRESA_NAMESPACE
			+ "associateBeneficiosWithContact";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateBeneficios";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteBeneficiosById";

	/** The Constant EMPRESA_DOCUMENT_STMT_UPDATE. */
	private static final String EMPRESA_DOCUMENT_STMT_UPDATE = EMPRESA_NAMESPACE
			+ "updateBeneficiosDocument";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT = EMPRESA_NAMESPACE
			+ "associateBeneficiosWithDocument";

	/** The Constant EMPRESA_STMT_DELETE_DOCUMENT. */
	private static final String EMPRESA_STMT_DELETE_DOCUMENT = EMPRESA_NAMESPACE
			+ "deleteBeneficiosDocument";

	/** The Constant STMT_VERSION. */
	private static final String EMPRESA_STMT_VERSION = EMPRESA_NAMESPACE + "fetchVersionNumber";

	/** The Constant EMPRESA_STMT_UPDATE_EMPRESA_STATUS. */
	private static final String EMPRESA_STMT_UPDATE_EMPRESA_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

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
	public Integer insertBeneficios(Beneficios beneficios)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Beneficios> response = new InternalResultsResponse<Beneficios>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, beneficios, response);

		if (response.isInError())
		{
			return null;
		}

		// Finally, if something was inserted then add the Beneficios to the result.
		if (insertCount > 0)
		{
			response.addResult(beneficios);
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
	public Integer updateBeneficios(Beneficios beneficios)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Beneficios> response = new InternalResultsResponse<Beneficios>();

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

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(beneficios);
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
	public Integer deleteBeneficios(Beneficios beneficios)
	{
		InternalResponse response = new InternalResponse();
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
		QATMyBatisDacHelper.translateSortFields(request, getBeneficiosInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}
}
