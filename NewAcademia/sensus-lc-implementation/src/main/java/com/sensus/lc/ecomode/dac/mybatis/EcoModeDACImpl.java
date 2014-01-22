package com.sensus.lc.ecomode.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doInsert;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;
import static com.sensus.common.util.SensusMyBatisDacHelper.doUpdate;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.ecomode.dac.IEcoModeDAC;
import com.sensus.lc.ecomode.model.request.EcoModeRequest;
import com.sensus.lc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.lc.light.model.Consumption;
import com.sensus.lc.light.model.Light;

/**
 * The Class EcoModeDACImpl.
 */
public class EcoModeDACImpl extends SqlSessionDaoSupport implements IEcoModeDAC
{

	/** The Constant ECO_MODE_NAMESPACE. */
	private static final String ECO_MODE_NAMESPACE = "EcoMode.";

	/** The Constant UPSERT_ECOMODE. */
	private static final String UPSERT_ECOMODE = ECO_MODE_NAMESPACE + "upsertEcomode";

	/** The Constant UPSERT_LIGHT_CONSUMPTION. */
	private static final String UPDATE_LIGHT_CONSUMPTION = ECO_MODE_NAMESPACE + "updateLightConsumption";

	/** The Constant UPDATE_CALCULATION_RETROACTIVE_ECOMODE. */
	private static final String UPDATE_CALCULATION_RETROACTIVE_ECOMODE = ECO_MODE_NAMESPACE
			+ "updateCalculationRetroactiveEcomode";

	/** The Constant INSERT_LIGHT_CONSUMPTION. */
	private static final String INSERT_LIGHT_CONSUMPTION = ECO_MODE_NAMESPACE + "insertLightConsumption";

	/** The Constant FETCH_ECO_MODE_BY_LIGHT. */
	private static final String FETCH_ECO_MODE_BY_LIGHT = ECO_MODE_NAMESPACE + "fetchEcoModeByLight";

	/** The Constant FETCH_LIGHT_CONSUMPTIONS_TO_CHART. */
	private static final String FETCH_LIGHT_CONSUMPTIONS_TO_CHART = ECO_MODE_NAMESPACE
			+ "fetchLightConsumptionsToChart";

	/** The Constant FETCH_LIGHT_CONSUMPTIONS_BY_LIGHT_ID. */
	private static final String FETCH_LIGHT_CONSUMPTIONS_BY_LIGHT_ID = ECO_MODE_NAMESPACE
			+ "fetchLightConsumptionsByLightId";

	/** The Constant FETCH_ALL_LIGHT_CONSUMPTIONS_BY_LIGHT_ID. */
	private static final String FETCH_ALL_LIGHT_CONSUMPTIONS_BY_LIGHT_ID = ECO_MODE_NAMESPACE
			+ "fetchAllLightConsumptionsByLightId";

	/** The Constant FETCH_COUNT_LIGHT_CONSUMPTIONS_BY_LIGHT_ID. */
	private static final String FETCH_COUNT_LIGHT_CONSUMPTIONS_BY_LIGHT_ID = ECO_MODE_NAMESPACE
			+ "fetchCountLightConsumptionsByLightId";

	private static final String FETCH_ALL_LIGHTS_TO_CALCULATE_ECO_MODE = ECO_MODE_NAMESPACE
			+ "fetchAllLightsToCalculateEcoMode";

	private static final String FETCH_COUNT_ALL_LIGHTS_TO_CALCULATE_ECO_MODE = ECO_MODE_NAMESPACE
			+ "fetchCountAllLightsToCalculateEcoMode";

	/** The Constant UPDATE_ANALYTICS_DATA. */
	private static final String UPDATE_ANALYTICS_DATA = ECO_MODE_NAMESPACE + "updateAnalyticsData";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.ecomode.dac.IEcoModeDAC#upsertEcoMode(com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest)
	 */
	@Override
	public InternalResultsResponse<Light> upsertEcoMode(EcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		Integer rows = (Integer)doQueryForObject(getSqlSession(), UPSERT_ECOMODE, ecoModeRequest);

		if (rows < 0)
		{
			response.setStatus(Status.NoRowsUpdatedError);
			return response;
		}

		response.addResult(ecoModeRequest.getFirstLight());
		response.getResultsSetInfo().setTotalRowsAvailable(rows);
		return response;
	}

	@Override
	public InternalResponse updateLightConsumption(EcoModeRequest ecoModeRequest)
	{
		InternalResponse response = new InternalResponse();
		doUpdate(getSqlSession(), UPDATE_LIGHT_CONSUMPTION, ecoModeRequest.getFirstLight(), response);
		return response;
	}

	@Override
	public InternalResponse updateCalculationRetroactiveEcomode(EcoModeRequest ecoModeRequest)
	{
		InternalResponse response = new InternalResponse();
		doQueryForObject(getSqlSession(), UPDATE_CALCULATION_RETROACTIVE_ECOMODE, ecoModeRequest);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.ecomode.bcl.IEcoModeBCL#insertLightConsumption(com.sensus.mlc.ecomode.model.request.
	 * InquiryEcoModeRequest)
	 */
	@Override
	public InternalResultsResponse<Consumption> insertLightConsumption(EcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Consumption> response = new InternalResultsResponse<Consumption>();
		Light light = ecoModeRequest.getFirstLight();
		doInsert(getSqlSession(), INSERT_LIGHT_CONSUMPTION, light, response);
		response.addResult(light.getLastConsumption());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.ecomode.dac.IEcoModeDAC#fetchEcoModeByLightId(com.sensus.mlc.ecomode.model.request.
	 * InquiryEcoModeRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchEcoModeByLight(InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		doQueryForList(getSqlSession(), FETCH_ECO_MODE_BY_LIGHT, ecoModeRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.ecomode.dac.IEcoModeDAC#fetchLightConsumptionsByLightId(com.sensus.mlc.ecomode.model.request.
	 * InquiryEcoModeRequest)
	 */
	@Override
	public InternalResultsResponse<Consumption> fetchLightConsumptionsByLightId(
			InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Consumption> response = new InternalResultsResponse<Consumption>();
		doQueryForList(getSqlSession(), FETCH_LIGHT_CONSUMPTIONS_BY_LIGHT_ID, ecoModeRequest, response);

		Integer totalRows =
				(Integer)doQueryForObject(getSqlSession(), FETCH_COUNT_LIGHT_CONSUMPTIONS_BY_LIGHT_ID,
						ecoModeRequest);
		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	@Override
	public InternalResultsResponse<Consumption> fetchAllLightConsumptionsByLightId(
			InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Consumption> response = new InternalResultsResponse<Consumption>();
		doQueryForList(getSqlSession(), FETCH_ALL_LIGHT_CONSUMPTIONS_BY_LIGHT_ID, ecoModeRequest, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Consumption> fetchLightConsumptionsToChart(
			InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Consumption> response = new InternalResultsResponse<Consumption>();
		doQueryForList(getSqlSession(), FETCH_LIGHT_CONSUMPTIONS_TO_CHART, ecoModeRequest, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Light> fetchAllLightsToCalculateEcoMode(
			InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		doQueryForList(getSqlSession(), FETCH_ALL_LIGHTS_TO_CALCULATE_ECO_MODE, ecoModeRequest, response);
		return response;
	}

	@Override
	public Integer fetchCountAllLightsToCalculateEcoMode(InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Integer> response = new InternalResultsResponse<Integer>();
		doQueryForList(getSqlSession(), FETCH_COUNT_ALL_LIGHTS_TO_CALCULATE_ECO_MODE, ecoModeRequest, response);
		return response.getFirstResult();
	}

	@Override
	public void updateAnalyticsData()
	{
		doQueryForObject(getSqlSession(), UPDATE_ANALYTICS_DATA);
	}
}
