package com.sensus.lc.ecomode.dac;

import static com.sensus.lc.base.TestBaseUtil.createLight;
import static com.sensus.lc.base.TestBaseUtil.createLightConsumption;
import static com.sensus.lc.base.TestBaseUtil.createLightWithEcoModeBaseline;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.ecomode.model.request.EcoModeRequest;
import com.sensus.lc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.lc.light.model.Consumption;
import com.sensus.lc.light.model.Light;

public class MockEcoModeDAC extends AbstractMockBase implements IEcoModeDAC
{
	/** The Constant ARBITRARY_AMOUNT_10. */
	private static final Integer ARBITRARY_AMOUNT_10 = 10;
	private static final Integer ARBITRARY_LIGHT_ID_100 = 100;

	@Override
	public InternalResultsResponse<Light> upsertEcoMode(EcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Light> response = getEcoModeResponse(ecoModeRequest);
		response = upsertEcoModeFromEcoMode(ecoModeRequest, response);
		return response;
	}

	@Override
	public InternalResponse updateLightConsumption(EcoModeRequest ecoModeRequest)
	{
		return new InternalResponse();
	}

	@Override
	public InternalResponse updateCalculationRetroactiveEcomode(EcoModeRequest ecoModeRequest)
	{
		return new InternalResponse();
	}

	@Override
	public InternalResultsResponse<Light> fetchEcoModeByLight(InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Light> response = getEcoModeResponse(ecoModeRequest);
		response = fetchEcoModeByPoleIdFromEcoMode(ecoModeRequest, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Consumption> insertLightConsumption(EcoModeRequest ecoModeRequest)
	{
		return new InternalResultsResponse<Consumption>();
	}

	@Override
	public InternalResultsResponse<Consumption> fetchLightConsumptionsByLightId(
			InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Consumption> response = getLightConsumptionResponseDefault(ecoModeRequest);
		response = fetchLightConsumptionsByLightIdFromEcoMode(ecoModeRequest, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Consumption> fetchAllLightConsumptionsByLightId(
			InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Consumption> response = getLightConsumptionResponseDefault(ecoModeRequest);
		response = fetchLightConsumptionsByLightIdFromEcoMode(ecoModeRequest, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Light> fetchAllLightsToCalculateEcoMode(
			InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<>();
		response = fetchAllLightsToCalculateEcoModeFromEcoMode(ecoModeRequest, response);
		return response;
	}

	@Override
	public Integer fetchCountAllLightsToCalculateEcoMode(InquiryEcoModeRequest ecoModeRequest)
	{
		Integer amount = getAmountDefault();
		amount = fetchCountAllLightsToCalculateEcoModeFromEcoMode(ecoModeRequest, amount);
		return amount;
	}

	@Override
	public InternalResultsResponse<Consumption> fetchLightConsumptionsToChart(InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Consumption> response = getLightConsumptionResponseDefault(ecoModeRequest);
		response = fetchLightConsumptionsByLightIdFromEcoMode(ecoModeRequest, response);
		return response;
	}

	@Override
	public void updateAnalyticsData()
	{

	}

	private Integer getAmountDefault()
	{
		return ARBITRARY_AMOUNT_10;
	}

	private InternalResultsResponse<Light> getEcoModeResponse(EcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<>();
		response.addResult(createLightWithEcoModeBaseline());
		response.getResultsSetInfo().setTotalRowsAvailable(1);
		return response;
	}

	private InternalResultsResponse<Light> getEcoModeResponse(InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<>();
		response.addResult(createLightWithEcoModeBaseline());
		response.getResultsSetInfo().setTotalRowsAvailable(1);
		return response;
	}

	private InternalResultsResponse<Consumption> getLightConsumptionResponseDefault(
			InquiryEcoModeRequest ecoModeRequest)
	{
		int amount = ARBITRARY_AMOUNT_10;
		int lightId = ARBITRARY_LIGHT_ID_100;

		Light light = createLight();
		light.setId(lightId);
		InternalResultsResponse<Consumption> response = new InternalResultsResponse<Consumption>();

		for (int i = 0; i < amount; i++)
		{
			response.addResult(createLightConsumption(i));
		}
		response.getResultsSetInfo().setTotalRowsAvailable(amount);
		return response;
	}

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
			return getEcoModeResponse(ecoModeRequest);
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return response;
	}

	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Light> fetchEcoModeByPoleIdFromEcoMode(
			InquiryEcoModeRequest ecoModeRequest,
			InternalResultsResponse<Light> response)
	{
		if (getAreaEnum() != LCAreaEnum.ECOMODE)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getEcoModeResponse(ecoModeRequest);
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.VALIDATION)
		{
			response = getEcoModeResponse(ecoModeRequest);
			response.getFirstResult().setLastConsumption(null);
			return response;
		}

		return response;
	}

	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Consumption> fetchLightConsumptionsByLightIdFromEcoMode(
			InquiryEcoModeRequest ecoModeRequest, InternalResultsResponse<Consumption> response)
	{
		if (getAreaEnum() != LCAreaEnum.ECOMODE)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getLightConsumptionResponseDefault(ecoModeRequest);
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return response;
	}

	private Integer fetchCountAllLightsToCalculateEcoModeFromEcoMode(InquiryEcoModeRequest ecoModeRequest,
			Integer amount)
	{

		if (getAreaEnum() != LCAreaEnum.ECOMODE)
		{
			return amount;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getAmountDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return 0;
		}

		return amount;
	}

	private InternalResultsResponse<Light> fetchAllLightsToCalculateEcoModeFromEcoMode(
			InquiryEcoModeRequest ecoModeRequest,
			InternalResultsResponse<Light> response)
	{
		if (getAreaEnum() != LCAreaEnum.ECOMODE)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			Integer amount = ecoModeRequest.getPageSize();
			for (int i = 0; i < amount; i++)
			{
				response.addResult(createLightWithEcoModeBaseline());
			}
		}

		return response;
	}

}
