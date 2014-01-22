package com.sensus.lc.controller.ecomode.unittest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.controller.base.unittest.util.BaseMockImpl;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.ecomode.bcf.IEcoModeBCF;
import com.sensus.lc.ecomode.model.EcoModeBaseline;
import com.sensus.lc.ecomode.model.LightEcoModeTypeEnum;
import com.sensus.lc.ecomode.model.request.EcoModeCSVRequest;
import com.sensus.lc.ecomode.model.request.EcoModeRequest;
import com.sensus.lc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.lc.ecomode.model.response.EcoModeResponse;
import com.sensus.lc.ecomode.model.response.InquiryEcoModeResponse;
import com.sensus.lc.light.model.Consumption;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.response.CSVResponse;
import com.sensus.lc.process.model.response.ProcessResponse;

public class EcoModeBCFMockImpl extends BaseMockImpl implements IEcoModeBCF
{

	/** The Constant SUCCESS_IMPORT_ECOMODE. */
	private static final String SUCCESS_IMPORT_ECOMODE = "sensus.mlc.mlc_action.success.import_ecomode";

	private static final String FILE_NAME = "/opt/flexnet-slc/csvtem...23_12_42_06_757_BRT.csv";

	@Override
	public InquiryEcoModeResponse fetchLightConsumptionsByLightId(InquiryEcoModeRequest ecoModeRequest)
	{
		InquiryEcoModeResponse response = new InquiryEcoModeResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			response.setLightConsumptions(generatelightConsumptions());
			return response;
		}

		return (InquiryEcoModeResponse)testOtherDefaultModes(response);
	}

	@Override
	public InquiryEcoModeResponse fetchLightConsumptionsToChart(InquiryEcoModeRequest ecoModeRequest)
	{
		InquiryEcoModeResponse response = new InquiryEcoModeResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			response.setLightConsumptions(generatelightConsumptions());
			return response;
		}

		return (InquiryEcoModeResponse)testOtherDefaultModes(response);
	}

	private List<EcoModeBaseline> generateBaseline()
	{
		List<EcoModeBaseline> baselines = new ArrayList<EcoModeBaseline>();
		EcoModeBaseline baseline = new EcoModeBaseline();
		baseline.setCreateDate(new Date());
		// baseline.setPoleId("000-111-222");
		baseline.setReplacedType(LightEcoModeTypeEnum.HPS);
		baseline.setReplacedTypeValue(10);
		baseline.setReplacedWattage(new Double(12.5));

		baselines.add(baseline);

		return baselines;
	}

	private List<Consumption> generatelightConsumptions()
	{
		List<Consumption> lightConsumptions = new ArrayList<Consumption>();
		Consumption lightConsumption = new Consumption();
		lightConsumption.setConsumption(100.45);
		lightConsumption.setCreateDate(new Date());
		lightConsumption.setEcoMode(20.5);
		lightConsumptions.add(lightConsumption);

		return lightConsumptions;
	}

	@Override
	public EcoModeResponse importEcoModeBaselineFromFileCSV(EcoModeRequest ecoModeRequest)
	{
		EcoModeResponse response = new EcoModeResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{

			if (!ValidationUtil.isNull(ecoModeRequest.getEcoModeCSVImport()))
			{
				response.setOperationSuccess(true);
				ArrayList<Message> msgList = new ArrayList<Message>();
				msgList.add(Message.internalMessage(SUCCESS_IMPORT_ECOMODE, MessageSeverity.None, null));
				response.setMessageList(msgList);
			}
		}
		return (EcoModeResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse insertCSVProcess(InquiryPaginationRequest lightSelectionRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public EcoModeResponse upsertEcoMode(EcoModeRequest ecoModeRequest)
	{
		EcoModeResponse response = new EcoModeResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			response.setLights(new ArrayList<Light>());
			Light light = new Light();
			light.setEcoModeBaseline(generateBaseline().get(0));
			response.getLights().add(light);
			return response;
		}

		return (EcoModeResponse)testOtherDefaultModes(response);
	}

	@Override
	public CSVResponse generateFileCSV(EcoModeCSVRequest ecoModeRequest)
	{
		CSVResponse response = new CSVResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setFileName(FILE_NAME);
			response.setOperationSuccess(true);
			return response;
		}

		return (CSVResponse)testOtherDefaultModes(response);
	}
}
