package com.sensus.dm.common.academia.dac.mybatis;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.academia.dac.IAcademiaDAC;
import com.sensus.dm.common.academia.model.Academia;
import com.sensus.dm.common.academia.model.request.AcademiaRequest;
import com.sensus.dm.common.academia.model.request.InquiryAcademiaRequest;

/**
 * The Class AcademiaDACImpl.
 * 
 * @author Washington
 */
public class AcademiaDACImpl extends SqlSessionDaoSupport implements IAcademiaDAC
{
	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant ACADEMIA_TYPE_VALUE. */
	private static final Integer ACADEMIA_TYPE_VALUE = 1;

	/** The Constant PARAMSIZE2. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE3. */
	private static final Integer PARAMSIZE3 = 3;

	/** The Constant PARAMSIZE4. */
	private static final Integer PARAMSIZE4 = 4;

	/** The Constant PARAMSIZE5. */
	private static final int PARAMSIZE5 = 5;

	/** The Constant PARAMSIZE6. */
	private static final Integer PARAMSIZE6 = 6;

	/** The Constant PARAMSIZE10. */
	private static final Integer PARAMSIZE10 = 10;

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant CDACAD. */
	private static final String CDACAD = "cdacad";
	/** The Constant ACADEM. */
	private static final String ACADEM = "academ";
	/** The Constant LOGRAD. */
	private static final String LOGRAD = "lograd";
	/** The Constant NUMEN. */
	private static final String NUMEN = "numen";
	/** The Constant BAIRR. */
	private static final String BAIRR = "bairr";
	/** The Constant CIDADE. */
	private static final String CIDADE = "cidade";
	/** The Constant CEP. */
	private static final String CEP = "cep";
	/** The Constant TELEF. */
	private static final String TELEF = "telef";
	/** The Constant LATLOG. */
	private static final String LATLOG = "latlog";

	// -------------------------------------------------------------------------
	// MyBatis mapping IDs.

	/** The Constant ACADEMIA_MAP. */
	private static final String ACADEMIA_MAP = "AcademiaMap.";

	/** The Constant DELETE_ACADEMIA. */
	private static final String DELETE_ACADEMIA = ACADEMIA_MAP + "deleteAcademia";

	/** The Constant FETCH_ALL_ACADEMIAS. */
	private static final String FETCH_ALL_ACADEMIAS = ACADEMIA_MAP + "fetchAllAcademias";

	/** The Constant FETCH_DEVICES_BY_ACADEMIAS. */
	private static final String FETCH_DEVICES_BY_ACADEMIAS = ACADEMIA_MAP + "fetchDevicesByName";

	/** The Constant INSERT_ACADEMIA. */
	private static final String INSERT_ACADEMIA = ACADEMIA_MAP + "insertAcademia";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = ACADEMIA_MAP + "paginationTotalRows";

	/** The Constant UPDATE_ACADEMIA. */
	private static final String UPDATE_ACADEMIA = ACADEMIA_MAP + "updateAcademia";

	/** The Constant FETCH_SCHEDULE_BY_ID. */
	private static final String FETCH_SCHEDULE_BY_ID = ACADEMIA_MAP + "fetchDevicesByAcademias";

	private static final String CUSTOMER_ID = null;

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.academia.dac.IAcademiaDAC#deleteAcademia(com.sensus.dm.common.academia.model.request.
	 * AcademiaRequest)
	 */
	@Override
	public InternalResponse deleteAcademia(AcademiaRequest academiaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(CDACAD, academiaRequest.getFirstAcademia().getCdacad());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_ACADEMIA, paramMap);

		InternalResponse response = new InternalResponse();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.setStatus(InternalResponse.Status.NoRowsRemovedError);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.academia.dac.IAcademiaDAC#insertAcademia(com.sensus.dm.common.academia.model.request.
	 * AcademiaRequest)
	 */
	@Override
	public InternalResultsResponse<Academia> insertAcademia(AcademiaRequest academiaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE10);

		paramMap.put(CDACAD, academiaRequest.getFirstAcademia().getCdacad());
		paramMap.put(ACADEM, academiaRequest.getFirstAcademia().getAcadem());
		paramMap.put(LOGRAD, academiaRequest.getFirstAcademia().getLograd());
		paramMap.put(NUMEN, academiaRequest.getFirstAcademia().getNumen());
		paramMap.put(BAIRR, academiaRequest.getFirstAcademia().getBairr());
		paramMap.put(CIDADE, academiaRequest.getFirstAcademia().getCidade());
		paramMap.put(CEP, academiaRequest.getFirstAcademia().getCep());
		paramMap.put(TELEF, academiaRequest.getFirstAcademia().getTelef());
		paramMap.put(LATLOG, academiaRequest.getFirstAcademia().getLatlog());
		paramMap.put(CUSTOMER_ID, academiaRequest.getTenant().getKey());

		Integer academiaId =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_ACADEMIA, paramMap);

		InternalResultsResponse<Academia> response = new InternalResultsResponse<Academia>();

		if (!ValidationUtil.isNull(academiaId))
		{
			academiaRequest.getFirstAcademia().setCdacad(academiaId);
			response.addResult(academiaRequest.getFirstAcademia());
			return response;
		}

		response.setStatus(Status.NoRowsInsertedError);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.academia.dac.IAcademiaDAC#updateAcademia(com.sensus.dm.common.academia.model.request.
	 * AcademiaRequest)
	 */
	@Override
	public InternalResultsResponse<Academia> updateAcademia(AcademiaRequest academiaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		paramMap.put(CDACAD, academiaRequest.getFirstAcademia().getCdacad());
		paramMap.put(ACADEM, academiaRequest.getFirstAcademia().getAcadem());
		paramMap.put(LOGRAD, academiaRequest.getFirstAcademia().getLograd());
		paramMap.put(NUMEN, academiaRequest.getFirstAcademia().getNumen());
		paramMap.put(BAIRR, academiaRequest.getFirstAcademia().getBairr());
		paramMap.put(CIDADE, academiaRequest.getFirstAcademia().getCidade());
		paramMap.put(CEP, academiaRequest.getFirstAcademia().getCep());
		paramMap.put(TELEF, academiaRequest.getFirstAcademia().getTelef());
		paramMap.put(LATLOG, academiaRequest.getFirstAcademia().getLatlog());
		paramMap.put(CUSTOMER_ID, academiaRequest.getTenant().getKey());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), UPDATE_ACADEMIA, paramMap);

		InternalResultsResponse<Academia> response = new InternalResultsResponse<Academia>();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.setStatus(Status.NoRowsUpdatedError);
		}
		else
		{
			response.getResultsList().add(academiaRequest.getFirstAcademia());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.academia.dac.IAcademiaDAC#fetchAllAcademias(com.sensus.dm.common.academia.model.request.
	 * InquiryAcademiaRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Academia> fetchAllAcademias(InquiryAcademiaRequest inquiryAcademiaRequest)
	{
		InternalResultsResponse<Academia> response = new InternalResultsResponse<Academia>();

		if (inquiryAcademiaRequest.isPreQueryCount())
		{
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)SensusMyBatisDacHelper.doQueryForObject(
							getSqlSession(), PAGINATION_TOTAL_ROWS, inquiryAcademiaRequest));

			if (ValidationUtil.isNullOrZero(response.getResultsSetInfo().getTotalRowsAvailable()))
			{
				return response;
			}
		}

		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_ACADEMIAS, inquiryAcademiaRequest));

		return response;
	}

	@Override
	public InternalResultsResponse<Academia> fetchAcademiasByName(AcademiaRequest academiaRequest)
	{
		InternalResultsResponse<Academia> response = new InternalResultsResponse<Academia>();
		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_DEVICES_BY_ACADEMIAS, academiaRequest));

		return response;
	}

	@Override
	public InternalResultsResponse<Academia> fetchAcademiaById(InquiryAcademiaRequest inquiryAcademiaRequest)
	{
		InternalResultsResponse<Academia> response = new InternalResultsResponse<Academia>();
		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_SCHEDULE_BY_ID, inquiryAcademiaRequest));

		return response;
	}

}
