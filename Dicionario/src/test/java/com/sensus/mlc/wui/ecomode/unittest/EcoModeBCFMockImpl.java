package com.sensus.mlc.wui.ecomode.unittest;

import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.ecomode.bcf.IEcoModeBCF;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.mlc.ecomode.model.response.InquiryEcoModeResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.wui.util.BaseMockImpl;

/**
 * The Class AnalyticsBCFMockImpl.
 */
public class EcoModeBCFMockImpl extends BaseMockImpl implements IEcoModeBCF
{

	@Override
	public InquiryEcoModeResponse upsertEcoMode(InquiryEcoModeRequest ecoModeRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InquiryEcoModeResponse fetchLightConsumptionsByLightId(InquiryEcoModeRequest ecoModeRequest)
	{
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}
}
