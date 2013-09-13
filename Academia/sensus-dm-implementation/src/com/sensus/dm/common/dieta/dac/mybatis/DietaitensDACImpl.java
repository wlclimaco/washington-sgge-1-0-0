package com.sensus.dm.common.dieta.dac.mybatis;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.dieta.dac.IDietaitensDAC;
import com.sensus.dm.common.dieta.model.Dietaitens;
import com.sensus.dm.common.dieta.model.request.DietaitensRequest;
import com.sensus.dm.common.dieta.model.request.InquiryDietaitensRequest;

/**
 * The Class DietaitensDACImpl.
 * 
 * @author Washington
 */
public class DietaitensDACImpl extends SqlSessionDaoSupport implements IDietaitensDAC
{
	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant DIETAITENS_TYPE_VALUE. */
	private static final Integer DIETAITENS_TYPE_VALUE = 1;

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

	/** The Constant CDDIETITENS. */
	private static final String CDDIETITENS = "cddietitens";
	/** The Constant REFEICAO. */
	private static final String REFEICAO = "refeicao";
	/** The Constant OPçãO. */
	private static final String OPCAO = "opção";
	/** The Constant QNT. */
	private static final String QNT = "qnt";

	// -------------------------------------------------------------------------
	// MyBatis mapping IDs.

	/** The Constant DIETAITENS_MAP. */
	private static final String DIETAITENS_MAP = "DietaitensMap.";

	/** The Constant DELETE_DIETAITENS. */
	private static final String DELETE_DIETAITENS = DIETAITENS_MAP + "deleteDietaitens";

	/** The Constant FETCH_ALL_DIETAITENSS. */
	private static final String FETCH_ALL_DIETAITENSS = DIETAITENS_MAP + "fetchAllDietaitenss";

	/** The Constant FETCH_DEVICES_BY_DIETAITENSS. */
	private static final String FETCH_DEVICES_BY_DIETAITENSS = DIETAITENS_MAP + "fetchDevicesByName";

	/** The Constant INSERT_DIETAITENS. */
	private static final String INSERT_DIETAITENS = DIETAITENS_MAP + "insertDietaitens";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = DIETAITENS_MAP + "paginationTotalRows";

	/** The Constant UPDATE_DIETAITENS. */
	private static final String UPDATE_DIETAITENS = DIETAITENS_MAP + "updateDietaitens";

	/** The Constant FETCH_SCHEDULE_BY_ID. */
	private static final String FETCH_SCHEDULE_BY_ID = DIETAITENS_MAP + "fetchDevicesByDietaitenss";

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
	 * @see
	 * com.sensus.dm.common.dietaitens.dac.IDietaitensDAC#deleteDietaitens(com.sensus.dm.common.dietaitens.model.request
	 * .DietaitensRequest)
	 */
	@Override
	public InternalResponse deleteDietaitens(DietaitensRequest dietaitensRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(CDDIETITENS, dietaitensRequest.getFirstDietaitens().getCddietitens());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_DIETAITENS, paramMap);

		InternalResponse response = new InternalResponse();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.setStatus(InternalResponse.Status.NoRowsRemovedError);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.dietaitens.dac.IDietaitensDAC#insertDietaitens(com.sensus.dm.common.dietaitens.model.request
	 * .DietaitensRequest)
	 */
	@Override
	public InternalResultsResponse<Dietaitens> insertDietaitens(DietaitensRequest dietaitensRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE10);

		paramMap.put(CDDIETITENS, dietaitensRequest.getFirstDietaitens().getCddietitens());
		paramMap.put(REFEICAO, dietaitensRequest.getFirstDietaitens().getRefeicao());
		paramMap.put(OPCAO, dietaitensRequest.getFirstDietaitens().getOpção());
		paramMap.put(QNT, dietaitensRequest.getFirstDietaitens().getQnt());
		paramMap.put(CUSTOMER_ID, dietaitensRequest.getTenant().getKey());

		Integer dietaitensId =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_DIETAITENS, paramMap);

		InternalResultsResponse<Dietaitens> response = new InternalResultsResponse<Dietaitens>();

		if (!ValidationUtil.isNull(dietaitensId))
		{
			dietaitensRequest.getFirstDietaitens().setCddietitens(dietaitensId);
			response.addResult(dietaitensRequest.getFirstDietaitens());
			return response;
		}

		response.setStatus(Status.NoRowsInsertedError);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.dietaitens.dac.IDietaitensDAC#updateDietaitens(com.sensus.dm.common.dietaitens.model.request
	 * .DietaitensRequest)
	 */
	@Override
	public InternalResultsResponse<Dietaitens> updateDietaitens(DietaitensRequest dietaitensRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		paramMap.put(CDDIETITENS, dietaitensRequest.getFirstDietaitens().getCddietitens());
		paramMap.put(REFEICAO, dietaitensRequest.getFirstDietaitens().getRefeicao());
		paramMap.put(OPCAO, dietaitensRequest.getFirstDietaitens().getOpção());
		paramMap.put(QNT, dietaitensRequest.getFirstDietaitens().getQnt());
		paramMap.put(CUSTOMER_ID, dietaitensRequest.getTenant().getKey());
		paramMap.put(MODIFY_USER, dietaitensRequest.getUserContext().getUserId());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), UPDATE_DIETAITENS, paramMap);

		InternalResultsResponse<Dietaitens> response = new InternalResultsResponse<Dietaitens>();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.setStatus(Status.NoRowsUpdatedError);
		}
		else
		{
			response.getResultsList().add(dietaitensRequest.getFirstDietaitens());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.dietaitens.dac.IDietaitensDAC#fetchAllDietaitenss(com.sensus.dm.common.dietaitens.model.request
	 * .InquiryDietaitensRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Dietaitens> fetchAllDietaitenss(InquiryDietaitensRequest inquiryDietaitensRequest)
	{
		InternalResultsResponse<Dietaitens> response = new InternalResultsResponse<Dietaitens>();

		if (inquiryDietaitensRequest.isPreQueryCount())
		{
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)SensusMyBatisDacHelper.doQueryForObject(
							getSqlSession(), PAGINATION_TOTAL_ROWS, inquiryDietaitensRequest));

			if (ValidationUtil.isNullOrZero(response.getResultsSetInfo().getTotalRowsAvailable()))
			{
				return response;
			}
		}

		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_DIETAITENSS, inquiryDietaitensRequest));

		return response;
	}

	@Override
	public InternalResultsResponse<Dietaitens> fetchDietaitensById(InquiryDietaitensRequest dietaitensRequest)
	{
		InternalResultsResponse<Dietaitens> response = new InternalResultsResponse<Dietaitens>();

		SensusMyBatisDacHelper.doQueryForList(
				getSqlSession(), FETCH_SCHEDULE_BY_ID, dietaitensRequest.getFirstDietaitens(), response);

		DMConvertUtil.checkResult(response);

		return response;
	}

	@Override
	public InternalResultsResponse<Dietaitens> fetchDietaitensByName(InquiryDietaitensRequest dietaitensRequest)
	{
		InternalResultsResponse<Dietaitens> response = new InternalResultsResponse<Dietaitens>();

		response.getResultsList().addAll(SensusMyBatisDacHelper.doQueryForList(getSqlSession(),
				FETCH_DEVICES_BY_DIETAITENSS, dietaitensRequest));

		return response;
	}

}
