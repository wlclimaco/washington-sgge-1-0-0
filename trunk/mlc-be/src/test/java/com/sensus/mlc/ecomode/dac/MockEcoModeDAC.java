package com.sensus.mlc.ecomode.dac;

import static com.sensus.mlc.base.TestBaseUtil.createEcoModeBaseline;
import static com.sensus.mlc.base.TestBaseUtil.createLight;
import static com.sensus.mlc.base.TestBaseUtil.createLightConsumption;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.LightConsumption;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.mlc.smartpoint.model.Light;

public class MockEcoModeDAC extends AbstractMockBase implements IEcoModeDAC
{
	/** The Constant ARBITRARY_AMOUNT_10. */
	private static final Integer ARBITRARY_AMOUNT_10 = 10;

	@Override
	public InternalResultsResponse<EcoModeBaseline> upsertEcoMode(InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<EcoModeBaseline> response = getEcoModeResponse(ecoModeRequest);
		response = upsertEcoModeFromEcoMode(ecoModeRequest, response);
		return response;
	}

	@Override
	public InternalResponse updateLightConsumptions(InquiryEcoModeRequest ecoModeRequest)
	{
		return new InternalResponse();
	}

	@Override
	public InternalResponse updateCalculationRetroactiveEcomode(InquiryEcoModeRequest ecoModeRequest)
	{
		return new InternalResponse();
	}

	@Override
	public InternalResultsResponse<EcoModeBaseline> fetchEcoModeByPoleId(InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<EcoModeBaseline> response = getEcoModeResponse(ecoModeRequest);
		response = fetchEcoModeByPoleIdFromEcoMode(ecoModeRequest, response);
		return response;
	}

	@Override
	public InternalResponse generateFileCSV(InquiryEcoModeRequest inquiryEcoModeRequest)
	{
		InternalResponse response = getEcoModeResponse(inquiryEcoModeRequest);
		response = generateFileCSVFromEcoMode(inquiryEcoModeRequest, response);
		return response;
	}

	@Override
	public InternalResultsResponse<LightConsumption> insertLightConsumption(InquiryEcoModeRequest ecoModeRequest)
	{
		return new InternalResultsResponse<LightConsumption>();
	}

	@Override
	public InternalResultsResponse<LightConsumption> fetchLightConsumptionsByLightId(
			InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<LightConsumption> response = getLightConsumptionResponseDefault(ecoModeRequest);
		response = fetchLightConsumptionsByLightIdFromEcoMode(ecoModeRequest, response);
		return response;
	}

	@Override
	public InternalResultsResponse<EcoModeBaseline> fetchAllLightsToCalculateEcoMode(
			InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<EcoModeBaseline> response = new InternalResultsResponse<>();
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
	public InternalResultsResponse<LightConsumption> fetchLightConsumptionsToChart(InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<LightConsumption> response = getLightConsumptionResponseDefault(ecoModeRequest);
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

	private InternalResultsResponse<EcoModeBaseline> getEcoModeResponse(InquiryEcoModeRequest ecoModeRequest)
	{
		InternalResultsResponse<EcoModeBaseline> response = new InternalResultsResponse<>();
		response.addResult(createEcoModeBaseline());
		response.getResultsSetInfo().setTotalRowsAvailable(1);
		return response;
	}

	private InternalResultsResponse<LightConsumption> getLightConsumptionResponseDefault(
			InquiryEcoModeRequest ecoModeRequest)
	{
		int amount = ARBITRARY_AMOUNT_10;
		int lightId = ecoModeRequest.getSelectionPaginationIds().get(0);

		Light light = createLight();
		light.setId(lightId);
		InternalResultsResponse<LightConsumption> response = new InternalResultsResponse<LightConsumption>();

		for (int i = 0; i < amount; i++)
		{
			response.addResult(createLightConsumption(light, i));
		}
		response.getResultsSetInfo().setTotalRowsAvailable(amount);
		return response;
	}

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
			return getEcoModeResponse(ecoModeRequest);
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return response;
	}

	@SuppressWarnings("unchecked")
	private InternalResultsResponse<EcoModeBaseline> fetchEcoModeByPoleIdFromEcoMode(
			InquiryEcoModeRequest ecoModeRequest,
			InternalResultsResponse<EcoModeBaseline> response)
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
			response.getFirstResult().getLightId().setLastConsumption(null);
			return response;
		}

		return response;
	}

	@SuppressWarnings("unchecked")
	private InternalResultsResponse<LightConsumption> fetchLightConsumptionsByLightIdFromEcoMode(
			InquiryEcoModeRequest ecoModeRequest, InternalResultsResponse<LightConsumption> response)
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

	private InternalResultsResponse<EcoModeBaseline> fetchAllLightsToCalculateEcoModeFromEcoMode(
			InquiryEcoModeRequest ecoModeRequest,
			InternalResultsResponse<EcoModeBaseline> response)
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
				response.addResult(createEcoModeBaseline());
			}
		}

		return response;
	}

	private InternalResponse generateFileCSVFromEcoMode(
			InquiryEcoModeRequest ecoModeRequest,
			InternalResponse response)
	{
		if (getAreaEnum() != LCAreaEnum.ECOMODE)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			String path = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator");
			ecoModeRequest.setFileName(path + "export.csv");
			return getEcoModeResponse(ecoModeRequest);
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseError();
		}

		return response;
	}
}
