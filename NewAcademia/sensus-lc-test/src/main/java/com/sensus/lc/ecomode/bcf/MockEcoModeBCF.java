package com.sensus.lc.ecomode.bcf;

import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.ecomode.model.request.EcoModeCSVRequest;
import com.sensus.lc.ecomode.model.request.EcoModeRequest;
import com.sensus.lc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.lc.ecomode.model.response.EcoModeResponse;
import com.sensus.lc.ecomode.model.response.InquiryEcoModeResponse;
import com.sensus.lc.light.model.response.CSVResponse;
import com.sensus.lc.process.model.response.ProcessResponse;

public class MockEcoModeBCF extends AbstractMockBase implements IEcoModeBCF
{

	@Override
	public EcoModeResponse upsertEcoMode(EcoModeRequest ecoModeRequest)
	{
		return new EcoModeResponse();
	}

	@Override
	public ProcessResponse insertCSVProcess(InquiryPaginationRequest request)
	{
		return new ProcessResponse();
	}

	@Override
	public InquiryEcoModeResponse fetchLightConsumptionsByLightId(InquiryEcoModeRequest ecoModeRequest)
	{
		return new InquiryEcoModeResponse();
	}

	@Override
	public EcoModeResponse importEcoModeBaselineFromFileCSV(EcoModeRequest ecoModeRequest)
	{
		return new EcoModeResponse();
	}

	@Override
	public InquiryEcoModeResponse fetchLightConsumptionsToChart(InquiryEcoModeRequest ecoModeRequest)
	{
		return new InquiryEcoModeResponse();
	}

	@Override
	public CSVResponse generateFileCSV(EcoModeCSVRequest ecoModeRequest)
	{
		return new CSVResponse();
	}

}
