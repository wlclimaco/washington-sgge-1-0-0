package com.sensus.lc.dieta.dac.mybatis;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.dieta.dac.IDietaDAC;
import com.sensus.lc.dieta.model.Dieta;
import com.sensus.lc.dieta.model.request.DietaRequest;
import com.sensus.lc.dieta.model.request.InquiryDietaRequest;

/**
 * The Class DietaDACImpl.
 * 
 * @author Washington
 */
public class DietaDACImpl extends SqlSessionDaoSupport implements IDietaDAC
{
	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant DIETA_TYPE_VALUE. */
	private static final Integer DIETA_TYPE_VALUE = 1;

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

	/** The Constant CDDIET. */
	private static final String CDDIET = "cddiet";
	/** The Constant DTINIC. */
	private static final String DTINIC = "dtinic";
	/** The Constant DTFINA. */
	private static final String DTFINA = "dtfina";
	/** The Constant DSOBJ. */
	private static final String DSOBJ = "dsobj";
	/** The Constant DIETA. */
	private static final String DIETA = "dieta";

	// -------------------------------------------------------------------------
	// MyBatis mapping IDs.

	/** The Constant DIETA_MAP. */
	private static final String DIETA_MAP = "DietaMap.";

	/** The Constant DELETE_DIETA. */
	private static final String DELETE_DIETA = DIETA_MAP + "deleteDieta";

	/** The Constant FETCH_ALL_DIETAS. */
	private static final String FETCH_ALL_DIETAS = DIETA_MAP + "fetchAllDietas";

	/** The Constant FETCH_DEVICES_BY_DIETAS. */
	private static final String FETCH_DEVICES_BY_DIETAS = DIETA_MAP + "fetchDevicesByName";

	/** The Constant INSERT_DIETA. */
	private static final String INSERT_DIETA = DIETA_MAP + "insertDieta";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = DIETA_MAP + "paginationTotalRows";

	/** The Constant UPDATE_DIETA. */
	private static final String UPDATE_DIETA = DIETA_MAP + "updateDieta";

	/** The Constant FETCH_SCHEDULE_BY_ID. */
	private static final String FETCH_SCHEDULE_BY_ID = DIETA_MAP + "fetchDevicesByDietas";

	private static final String CUSTOMER_ID = null;

	private static final String MODIFY_USER = null;

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.dieta.dac.IDietaDAC#deleteDieta(com.sensus.dm.common.dieta.model.request.DietaRequest)
	 */
	@Override
	public InternalResponse deleteDieta(DietaRequest dietaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(CDDIET, dietaRequest.getFirstDieta().getCddiet());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_DIETA, paramMap);

		InternalResponse response = new InternalResponse();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.setStatus(InternalResponse.Status.NoRowsRemovedError);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.dieta.dac.IDietaDAC#insertDieta(com.sensus.dm.common.dieta.model.request.DietaRequest)
	 */
	@Override
	public InternalResultsResponse<Dieta> insertDieta(DietaRequest dietaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE10);

		paramMap.put(CDDIET, dietaRequest.getFirstDieta().getCddiet());
		paramMap.put(DTINIC, dietaRequest.getFirstDieta().getDtinic());
		paramMap.put(DTFINA, dietaRequest.getFirstDieta().getDtfina());
		paramMap.put(DSOBJ, dietaRequest.getFirstDieta().getDsobj());
		paramMap.put(DIETA, dietaRequest.getFirstDieta().getDieta());
		// paramMap.put(CUSTOMER_ID, dietaRequest.getTenant().getKey());

		Integer dietaId =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_DIETA, paramMap);

		InternalResultsResponse<Dieta> response = new InternalResultsResponse<Dieta>();

		if (!ValidationUtil.isNull(dietaId))
		{
			dietaRequest.getFirstDieta().setCddiet(dietaId);
			response.addResult(dietaRequest.getFirstDieta());
			return response;
		}

		response.setStatus(Status.NoRowsInsertedError);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.dieta.dac.IDietaDAC#updateDieta(com.sensus.dm.common.dieta.model.request.DietaRequest)
	 */
	@Override
	public InternalResultsResponse<Dieta> updateDieta(DietaRequest dietaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		paramMap.put(CDDIET, dietaRequest.getFirstDieta().getCddiet());
		paramMap.put(DTINIC, dietaRequest.getFirstDieta().getDtinic());
		paramMap.put(DTFINA, dietaRequest.getFirstDieta().getDtfina());
		paramMap.put(DSOBJ, dietaRequest.getFirstDieta().getDsobj());
		paramMap.put(DIETA, dietaRequest.getFirstDieta().getDieta());
		paramMap.put(MODIFY_USER, dietaRequest.getUserContext().getUserId());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), UPDATE_DIETA, paramMap);

		InternalResultsResponse<Dieta> response = new InternalResultsResponse<Dieta>();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.setStatus(Status.NoRowsUpdatedError);
		}
		else
		{
			response.getResultsList().add(dietaRequest.getFirstDieta());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.dieta.dac.IDietaDAC#fetchAllDietas(com.sensus.dm.common.dieta.model.request.InquiryDietaRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Dieta> fetchAllDietas(InquiryDietaRequest inquiryDietaRequest)
	{
		InternalResultsResponse<Dieta> response = new InternalResultsResponse<Dieta>();

		if (inquiryDietaRequest.isPreQueryCount())
		{
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)SensusMyBatisDacHelper.doQueryForObject(
							getSqlSession(), PAGINATION_TOTAL_ROWS, inquiryDietaRequest));

			if (ValidationUtil.isNullOrZero(response.getResultsSetInfo().getTotalRowsAvailable()))
			{
				return response;
			}
		}

		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_DIETAS, inquiryDietaRequest));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.dieta.dac.IDietaDAC#fetchDevicesByDieta(com.sensus.dm.common.dieta.model.request.DietaRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Dieta> fetchDevicesByName(DietaRequest dietaRequest)
	{
		InternalResultsResponse<Dieta> response = new InternalResultsResponse<Dieta>();

		response.getResultsList().addAll(SensusMyBatisDacHelper.doQueryForList(getSqlSession(),
				FETCH_DEVICES_BY_DIETAS, dietaRequest));

		return response;

	}

	@Override
	public InternalResultsResponse<Dieta> fetchDevicesById(DietaRequest dietaRequest)
	{
		InternalResultsResponse<Dieta> response = new InternalResultsResponse<Dieta>();

		SensusMyBatisDacHelper.doQueryForList(
				getSqlSession(), FETCH_SCHEDULE_BY_ID, dietaRequest.getFirstDieta(), response);

		// DMConvertUtil.checkResult(response);

		return response;
	}

}
