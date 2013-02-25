package com.sensus.mlc.ecomode.bcf;

import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.mlc.ecomode.model.response.InquiryEcoModeResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;

public class MockEcoModeBCF extends AbstractMockBase implements IEcoModeBCF
{

	@Override
	public InquiryEcoModeResponse upsertEcoMode(InquiryEcoModeRequest ecoModeRequest)
	{
		return new InquiryEcoModeResponse();
	}

	@Override
	public InquiryEcoModeResponse generateFileCSV(InquiryEcoModeRequest ecoModeRequest)
	{
		return new InquiryEcoModeResponse();
	}

	@Override
	public ProcessResponse insertCSVProcess(LightSelectionRequest lightSelectionRequest)
	{
		return new ProcessResponse();
	}

	@Override
	public InquiryEcoModeResponse fetchLightConsumptionsByLightId(InquiryEcoModeRequest ecoModeRequest)
	{
		return new InquiryEcoModeResponse();
	}

	@Override
	public InquiryEcoModeResponse importEcoModeBaselineFromFileCSV(InquiryEcoModeRequest ecoModeRequest)
	{
		return new InquiryEcoModeResponse();
	}

	@Override
	public InquiryEcoModeResponse fetchLightConsumptionsToChart(InquiryEcoModeRequest ecoModeRequest)
	{
		return new InquiryEcoModeResponse();
	}

}
