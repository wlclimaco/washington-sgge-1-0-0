package com.sensus.lc.ecomode.bcl;

import static com.sensus.lc.base.util.LCActionUtil.createMessageInfoNone;

import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.ecomode.model.EcoModeBaseline;
import com.sensus.lc.ecomode.model.LightEcoModeTypeEnum;
import com.sensus.lc.ecomode.model.request.EcoModeCSVRequest;
import com.sensus.lc.ecomode.model.request.EcoModeRequest;
import com.sensus.lc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.lc.light.model.Consumption;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.response.CSVInternalResponse;

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
	 * com.sensus.lc.ecomode.bcl.IEcoModeBCL#upsertEcoMode(com.sensus.lc.ecomode.model.request.InquiryEcoModeRequest)
	 */
	@Override
	public InternalResultsResponse<Light> upsertEcoMode(EcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Light> response = this.getResultResponseDefault();
		response = upsertEcoModeFromEcoMode(ecoModeRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.ecomode.bcl.IEcoModeBCL#fetchLightConsumptionsByLightId(com.sensus.lc.ecomode.model.request.
	 * InquiryEcoModeRequest)
	 */
	@Override
	public InternalResultsResponse<Consumption> fetchLightConsumptionsByLightId(
			InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Consumption> response = this.getResultResponseDefault(ecoModeRequest);
		response = fetchLightConsumptionsFromEcoMode(ecoModeRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.ecomode.bcl.IEcoModeBCL#importEcoModeBaselineFromFileCSV(com.sensus.lc.ecomode.model.request.
	 * InquiryEcoModeRequest)
	 */
	@Override
	public InternalResultsResponse<Light> importEcoModeBaselineFromFileCSV(EcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Light> response = getResultResponseLight();
		response = importEcoModeBaselineFromFileCSVFromEcoMode(ecoModeRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.ecomode.bcl.IEcoModeBCL#fetchLightConsumptionsToChart(com.sensus.lc.ecomode.model.request.
	 * InquiryEcoModeRequest)
	 */
	@Override
	public InternalResultsResponse<Consumption> fetchLightConsumptionsToChart(InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Consumption> response = this.getResultResponseDefault(ecoModeRequest);
		response = fetchLightConsumptionsFromEcoMode(ecoModeRequest, response);
		return response;
	}

	/**
	 * Gets the result response default.
	 * 
	 * @return the result response default
	 */
	private InternalResultsResponse<Light> getResultResponseDefault()
	{
		int amount = ARBITRARY_AMOUNT_10;
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		for (int i = 0; i < amount; i++)
		{
			response.addResult(TestBaseUtil.createLightWithEcoModeBaseline());
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
	private InternalResultsResponse<Consumption> getResultResponseDefault(InquiryEcoModeRequest ecoModeRequest)
	{
		int amount = ARBITRARY_AMOUNT_10;
		InternalResultsResponse<Consumption> response = new InternalResultsResponse<Consumption>();
		for (int i = 0; i < amount; i++)
		{
			response.addResult(TestBaseUtil.createLightConsumption(i));
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
	private InternalResultsResponse<Light> upsertEcoModeFromEcoMode(EcoModeRequest ecoModeRequest,
			InternalResultsResponse<Light> response)
	{
		if (getAreaEnum() != LCAreaEnum.ECOMODE)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			Light light = ecoModeRequest.getFirstLight();
			EcoModeBaseline baseline = light.getEcoModeBaseline();
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
	private InternalResultsResponse<Consumption> fetchLightConsumptionsFromEcoMode(
			InquiryEcoModeRequest ecoModeRequest, InternalResultsResponse<Consumption> response)
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
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Light> importEcoModeBaselineFromFileCSVFromEcoMode(
			EcoModeRequest ecoModeRequest, InternalResultsResponse<Light> response)
	{
		if (getAreaEnum() != LCAreaEnum.ECOMODE)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getResultResponseLight();
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

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.ecomode.bcl.IEcoModeBCL#fetchCountAllLightsToCalculateEcoMode(com.sensus.lc.ecomode.model.request
	 * .InquiryEcoModeRequest)
	 */
	@Override
	public Integer fetchCountAllLightsToCalculateEcoMode(InquiryEcoModeRequest ecoModeRequest)
	{
		return ARBITRARY_AMOUNT_10;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.ecomode.bcl.IEcoModeBCL#updateAnalyticsData()
	 */
	@Override
	public void updateAnalyticsData()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.ecomode.bcl.IEcoModeBCL#fetchAllLightsToCalculateEcoMode(com.sensus.lc.ecomode.model.request.
	 * InquiryEcoModeRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchAllLightsToCalculateEcoMode(
			InquiryEcoModeRequest ecoModeRequest)
	{
		return getResultResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.ecomode.bcl.IEcoModeBCL#generateFileCSV(com.sensus.lc.ecomode.model.request.EcoModeCSVRequest)
	 */
	@Override
	public CSVInternalResponse generateFileCSV(EcoModeCSVRequest ecoModeRequest)
	{
		CSVInternalResponse response = new CSVInternalResponse(
				System.getProperty("java.io.tmpdir")
						+ System.getProperty("line.separator")
						+ LCDateUtil.getNewDateUTC().getTime() + ".csv");

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setStatus(Status.ExceptionError);
			response.addFieldErrorMessage(ERROR_CODE);
			return response;
		}
		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(J_UNIT);
		}

		return response;
	}
}
