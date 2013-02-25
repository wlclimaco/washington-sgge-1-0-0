package com.sensus.mlc.ecomode.bcl;

import static com.sensus.mlc.base.util.LCActionUtil.createMessageInfoNone;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.LightConsumption;
import com.sensus.mlc.ecomode.model.LightEcoModeTypeEnum;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.mlc.ecomode.model.response.InquiryEcoModeResponse;
import com.sensus.mlc.smartpoint.model.Light;

/**
 * The Class MockEcoModeBCL.
 */
public class MockEcoModeBCL extends AbstractMockBase implements IEcoModeBCL
{
	/** The Constant ARBITRARY_AMOUNT_10. */
	private static final Integer ARBITRARY_AMOUNT_10 = 10;

	/** The Constant TEST_EXCEPTION. */
	private static final String TEST_EXCEPTION = "Test Exception";

	/** The Constant SUCCESS_UPD_ECOMODE_BY_POLE_ID. */
	protected static final String SUCCESS_UPD_ECOMODE_BY_POLE_ID =
			"sensus.mlc.mlc_action.success.upd_ecomode_by_poleid";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.ecomode.bcl.IEcoModeBCL#upsertEcoMode(com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest)
	 */
	@Override
	public InternalResultsResponse<EcoModeBaseline> upsertEcoMode(InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<EcoModeBaseline> response = this.getResultResponseDefault();
		response = upsertEcoModeFromEcoMode(ecoModeRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.ecomode.bcl.IEcoModeBCL#generateFileCSV(com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest
	 * )
	 */
	@Override
	public InquiryEcoModeResponse generateFileCSV(InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<LightConsumption> internalResponse = this.getResultResponseDefault(ecoModeRequest);
		internalResponse = fetchLightConsumptionsFromEcoMode(ecoModeRequest, internalResponse);

		InquiryEcoModeResponse response = new InquiryEcoModeResponse();
		LCHelp.treatReturnFromBCL(response, internalResponse, ERROR_CODE);
		response.setLightConsumptions(internalResponse.getResultsList());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.ecomode.bcl.IEcoModeBCL#fetchLightConsumptionsByLightId(com.sensus.mlc.ecomode.model.request.
	 * InquiryEcoModeRequest)
	 */
	@Override
	public InternalResultsResponse<LightConsumption> fetchLightConsumptionsByLightId(
			InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<LightConsumption> response = this.getResultResponseDefault(ecoModeRequest);
		response = fetchLightConsumptionsFromEcoMode(ecoModeRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.ecomode.bcl.IEcoModeBCL#importEcoModeBaselineFromFileCSV(com.sensus.mlc.ecomode.model.request.
	 * InquiryEcoModeRequest)
	 */
	@Override
	public InternalResponse importEcoModeBaselineFromFileCSV(InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResponse response = getInternalResponseDefault();
		response = importEcoModeBaselineFromFileCSVFromEcoMode(ecoModeRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.ecomode.bcl.IEcoModeBCL#fetchLightConsumptionsToChart(com.sensus.mlc.ecomode.model.request.
	 * InquiryEcoModeRequest)
	 */
	@Override
	public InternalResultsResponse<LightConsumption> fetchLightConsumptionsToChart(InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<LightConsumption> response = this.getResultResponseDefault(ecoModeRequest);
		response = fetchLightConsumptionsFromEcoMode(ecoModeRequest, response);
		return response;
	}

	/**
	 * Gets the result response default.
	 * 
	 * @return the result response default
	 */
	private InternalResultsResponse<EcoModeBaseline> getResultResponseDefault()
	{
		int amount = ARBITRARY_AMOUNT_10;
		InternalResultsResponse<EcoModeBaseline> response = new InternalResultsResponse<EcoModeBaseline>();
		for (int i = 0; i < amount; i++)
		{
			response.addResult(TestBaseUtil.createEcoModeBaseline());
		}
		response.getResultsSetInfo().setTotalRowsAvailable(amount);
		return response;
	}

	/**
	 * Gets the result response default.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @return the result response default
	 */
	private InternalResultsResponse<LightConsumption> getResultResponseDefault(InquiryEcoModeRequest ecoModeRequest)
	{
		int amount = ARBITRARY_AMOUNT_10;
		InternalResultsResponse<LightConsumption> response = new InternalResultsResponse<LightConsumption>();
		Light light = TestBaseUtil.createLight();
		light.setId(ecoModeRequest.getSelectionPaginationIds().get(0));
		for (int i = 0; i < amount; i++)
		{
			response.addResult(TestBaseUtil.createLightConsumption(light, i));
		}
		response.getResultsSetInfo().setTotalRowsAvailable(amount);
		return response;
	}

	/**
	 * Upsert eco mode from eco mode.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @param response the response
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<EcoModeBaseline> upsertEcoModeFromEcoMode(InquiryEcoModeRequest ecoModeRequest,
			InternalResultsResponse<EcoModeBaseline> response)
	{
		if (getAreaEnum() != LCAreaEnum.ECOMODE)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			EcoModeBaseline baseline = ecoModeRequest.getFirstEcoModeBaseline();
			LightEcoModeTypeEnum lampType = baseline.getReplacedType();
			Double wattage = baseline.getReplacedWattage();

			response = this.getResultResponseDefault();
			response.getMessageInfoList().add(
					createMessageInfoNone(
							SUCCESS_UPD_ECOMODE_BY_POLE_ID,
							lampType,
							wattage).getMessageInfo());
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
	}

	/**
	 * Fetch light consumptions from eco mode.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @param response the response
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<LightConsumption> fetchLightConsumptionsFromEcoMode(
			InquiryEcoModeRequest ecoModeRequest, InternalResultsResponse<LightConsumption> response)
	{
		if (getAreaEnum() != LCAreaEnum.ECOMODE)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return this.getResultResponseDefault(ecoModeRequest);
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
	}

	/**
	 * Import eco mode baseline from file csv from eco mode.
	 * 
	 * @param ecoModeRequest the eco mode request
	 * @param response the response
	 * @return the internal response
	 */
	private InternalResponse importEcoModeBaselineFromFileCSVFromEcoMode(
			InquiryEcoModeRequest ecoModeRequest, InternalResponse response)
	{
		if (getAreaEnum() != LCAreaEnum.ECOMODE)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getInternalResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.ecomode.bcl.IEcoModeBCL#fetchCountAllLightsToCalculateEcoMode(com.sensus.mlc.ecomode.model.request
	 * .InquiryEcoModeRequest)
	 */
	@Override
	public Integer fetchCountAllLightsToCalculateEcoMode(InquiryEcoModeRequest ecoModeRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.ecomode.bcl.IEcoModeBCL#updateAnalyticsData()
	 */
	@Override
	public void updateAnalyticsData()
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.ecomode.bcl.IEcoModeBCL#fetchAllLightsToCalculateEcoMode(com.sensus.mlc.ecomode.model.request.
	 * InquiryEcoModeRequest)
	 */
	@Override
	public InternalResultsResponse<EcoModeBaseline> fetchAllLightsToCalculateEcoMode(
			InquiryEcoModeRequest ecoModeRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
