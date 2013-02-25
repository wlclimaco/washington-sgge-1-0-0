package com.sensus.mlc.ecomode.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doInsert;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.CSVFileGenerator;
import com.sensus.mlc.ecomode.csv.EcoModeCSVDataSource;
import com.sensus.mlc.ecomode.dac.IEcoModeDAC;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.LightConsumption;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;

/**
 * The Class EcoModeDACImpl.
 */
public class EcoModeDACImpl extends SqlSessionDaoSupport implements IEcoModeDAC
{

	/** The Constant ECO_MODE_NAMESPACE. */
	private static final String ECO_MODE_NAMESPACE = "EcoMode.";

	/** The Constant UPSERT_ECOMODE. */
	private static final String UPSERT_ECOMODE = ECO_MODE_NAMESPACE + "upsertEcomode";

	/** The Constant UPSERT_LIGHT_CONSUMPTIONS. */
	private static final String UPDATE_LIGHT_CONSUMPTIONS = ECO_MODE_NAMESPACE + "updateLightConsumptions";

	/** The Constant UPDATE_CALCULATION_RETROACTIVE_ECOMODE. */
	private static final String UPDATE_CALCULATION_RETROACTIVE_ECOMODE = ECO_MODE_NAMESPACE
			+ "updateCalculationRetroactiveEcomode";

	/** The Constant INSERT_LIGHT_CONSUMPTION. */
	private static final String INSERT_LIGHT_CONSUMPTION = ECO_MODE_NAMESPACE + "insertLightConsumption";

	/** The Constant FETCH_ECO_MODE_BY_LIGHT_ID. */
	private static final String FETCH_ECO_MODE_BY_POLE_ID = ECO_MODE_NAMESPACE + "fetchEcoModeByPoleId";

	/** The Constant FETCH_LIGHT_CONSUMPTIONS_TO_CHART. */
	private static final String FETCH_LIGHT_CONSUMPTIONS_TO_CHART = ECO_MODE_NAMESPACE
			+ "fetchLightConsumptionsToChart";

	/** The Constant FETCH_LIGHT_CONSUMPTIONS_BY_LIGHT_ID. */
	private static final String FETCH_LIGHT_CONSUMPTIONS_BY_LIGHT_ID = ECO_MODE_NAMESPACE
			+ "fetchLightConsumptionsByLightId";

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
	public InternalResultsResponse<EcoModeBaseline> upsertEcoMode(InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<EcoModeBaseline> response = new InternalResultsResponse<EcoModeBaseline>();
		EcoModeBaseline ecoModeBaseline = ecoModeRequest.getFirstEcoModeBaseline();
		Integer rows = (Integer)doQueryForObject(this.getSqlSession(), UPSERT_ECOMODE, ecoModeRequest);

		if (rows < 0)
		{
			response.setStatus(Status.NoRowsUpdatedError);
			return response;
		}

		response.addResult(ecoModeBaseline);
		response.getResultsSetInfo().setTotalRowsAvailable(rows);
		return response;
	}

	@Override
	public InternalResponse updateLightConsumptions(InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResponse response = new InternalResponse();
		doQueryForObject(this.getSqlSession(), UPDATE_LIGHT_CONSUMPTIONS, ecoModeRequest);
		return response;
	}

	@Override
	public InternalResponse updateCalculationRetroactiveEcomode(InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResponse response = new InternalResponse();
		doQueryForObject(this.getSqlSession(), UPDATE_CALCULATION_RETROACTIVE_ECOMODE, ecoModeRequest);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.ecomode.bcl.IEcoModeBCL#insertLightConsumption(com.sensus.mlc.ecomode.model.request.
	 * InquiryEcoModeRequest)
	 */
	@Override
	public InternalResultsResponse<LightConsumption> insertLightConsumption(InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<LightConsumption> response = new InternalResultsResponse<LightConsumption>();
		LightConsumption lightConsumption = ecoModeRequest.getFirstLightConsumption();
		doInsert(this.getSqlSession(), INSERT_LIGHT_CONSUMPTION, lightConsumption, response);
		response.addResult(lightConsumption);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.ecomode.dac.IEcoModeDAC#fetchEcoModeByLightId(com.sensus.mlc.ecomode.model.request.
	 * InquiryEcoModeRequest)
	 */
	@Override
	public InternalResultsResponse<EcoModeBaseline> fetchEcoModeByPoleId(InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<EcoModeBaseline> response = new InternalResultsResponse<EcoModeBaseline>();
		doQueryForList(this.getSqlSession(), FETCH_ECO_MODE_BY_POLE_ID, ecoModeRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.ecomode.dac.IEcoModeDAC#fetchLightConsumptionsByLightId(com.sensus.mlc.ecomode.model.request.
	 * InquiryEcoModeRequest)
	 */
	@Override
	public InternalResultsResponse<LightConsumption> fetchLightConsumptionsByLightId(
			InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<LightConsumption> response = new InternalResultsResponse<LightConsumption>();
		doQueryForList(this.getSqlSession(), FETCH_LIGHT_CONSUMPTIONS_BY_LIGHT_ID, ecoModeRequest, response);

		Integer totalRows =
				(Integer)doQueryForObject(this.getSqlSession(), FETCH_COUNT_LIGHT_CONSUMPTIONS_BY_LIGHT_ID,
						ecoModeRequest);
		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	@Override
	public InternalResultsResponse<LightConsumption> fetchLightConsumptionsToChart(
			InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<LightConsumption> response = new InternalResultsResponse<LightConsumption>();
		doQueryForList(this.getSqlSession(), FETCH_LIGHT_CONSUMPTIONS_TO_CHART, ecoModeRequest, response);
		return response;
	}

	@Override
	public InternalResultsResponse<EcoModeBaseline> fetchAllLightsToCalculateEcoMode(
			InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<EcoModeBaseline> response = new InternalResultsResponse<EcoModeBaseline>();
		doQueryForList(this.getSqlSession(), FETCH_ALL_LIGHTS_TO_CALCULATE_ECO_MODE, ecoModeRequest, response);
		return response;
	}

	@Override
	public Integer fetchCountAllLightsToCalculateEcoMode(InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Integer> response = new InternalResultsResponse<Integer>();
		doQueryForList(this.getSqlSession(), FETCH_COUNT_ALL_LIGHTS_TO_CALCULATE_ECO_MODE, ecoModeRequest, response);
		return response.getFirstResult();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.ecomode.dac.IEcoModeDAC#generateFileCSV(com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest
	 * )
	 */
	@Override
	public InternalResponse generateFileCSV(InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResponse response = new InternalResponse();
		CSVFileGenerator.create(ecoModeRequest.getFileName(), new EcoModeCSVDataSource(ecoModeRequest), response);
		return response;
	}

	@Override
	public void updateAnalyticsData()
	{
		doQueryForObject(this.getSqlSession(), UPDATE_ANALYTICS_DATA);
	}
}
