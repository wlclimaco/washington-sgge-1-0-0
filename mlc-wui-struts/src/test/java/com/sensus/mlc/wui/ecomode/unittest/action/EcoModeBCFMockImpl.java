package com.sensus.mlc.wui.ecomode.unittest.action;

import java.util.ArrayList;
import java.util.Date;

import com.sensus.common.model.ResultsSetInfo;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.ecomode.bcf.IEcoModeBCF;
import com.sensus.mlc.ecomode.model.LightConsumption;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.mlc.ecomode.model.response.InquiryEcoModeResponse;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;

public class EcoModeBCFMockImpl extends BaseMockImpl implements IEcoModeBCF
{

	@Override
	public InquiryEcoModeResponse upsertEcoMode(InquiryEcoModeRequest ecoModeRequest)
	{
		InquiryEcoModeResponse response = new InquiryEcoModeResponse();
		response.setOperationSuccess(true);
		return response;
	}

	@Override
	public InquiryEcoModeResponse fetchLightConsumptionsByLightId(InquiryEcoModeRequest ecoModeRequest)
	{

		InquiryEcoModeResponse response = new InquiryEcoModeResponse();

		response.setLightConsumptions(new ArrayList<LightConsumption>());
		for (int i = 0; i < 30; i++)
		{

			Light light = new Light();
			LightConsumption lightConsumption = new LightConsumption();
			lightConsumption.setCreateDate(new Date());
			lightConsumption.setCreateUser("Rod");
			lightConsumption.setEcomodeBaseline(200.00);
			lightConsumption.setConsumption(110.00);
			lightConsumption.setDay(new Date());
			light.setEcoMode(200.00);
			lightConsumption.setLight(light);

			response.getLightConsumptions().add(lightConsumption);

		}
		ResultsSetInfo resultsSetInfo = new ResultsSetInfo();
		resultsSetInfo.setTotalRowsAvailable(30);

		response.setOperationSuccess(true);
		response.setResultsSetInfo(resultsSetInfo);
		return response;
	}

	@Override
	public InquiryEcoModeResponse generateFileCSV(InquiryEcoModeRequest ecoModeRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessResponse insertCSVProcess(LightSelectionRequest lightSelectionRequest)
	{
		ProcessResponse response = new ProcessResponse();
		response.setProcesses(new ArrayList<Process>());
		Process process = new Process();
		process.setId(100);
		response.getProcesses().add(process);
		return response;
	}

	@Override
	public InquiryEcoModeResponse importEcoModeBaselineFromFileCSV(InquiryEcoModeRequest ecoModeRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InquiryEcoModeResponse fetchLightConsumptionsToChart(InquiryEcoModeRequest ecoModeRequest)
	{

		InquiryEcoModeResponse response = new InquiryEcoModeResponse();
		response.setLightConsumptions(new ArrayList<LightConsumption>());
		for (int i = 0; i < 30; i++)
		{

			LightConsumption lightConsumption = new LightConsumption();
			lightConsumption.setCreateDate(new Date());
			lightConsumption.setCreateUser("Rod");
			lightConsumption.setEcomodeBaseline(200.00);
			lightConsumption.setConsumption(110.00);
			lightConsumption.setDay(new Date());

			response.getLightConsumptions().add(lightConsumption);

		}
		response.setOperationSuccess(true);

		return response;
	}

}
