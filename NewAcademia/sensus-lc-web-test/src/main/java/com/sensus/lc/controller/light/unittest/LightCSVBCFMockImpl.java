package com.sensus.lc.controller.light.unittest;

import com.sensus.lc.controller.base.unittest.util.BaseMockImpl;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.light.bcf.ILightCSVBCF;
import com.sensus.lc.light.model.request.LightCSVRequest;
import com.sensus.lc.light.model.request.LightHistoryCSVRequest;
import com.sensus.lc.light.model.request.LightSummaryCSVRequest;
import com.sensus.lc.light.model.response.CSVResponse;

public class LightCSVBCFMockImpl extends BaseMockImpl implements ILightCSVBCF
{

	private static final String FILE_NAME = "/opt/flexnet-slc/csvtem...23_12_42_06_757_BRT.csv";

	@Override
	public CSVResponse generateLightHistoryFileCSV(LightHistoryCSVRequest request)
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

	@Override
	public CSVResponse generateLightSummaryFileCSV(LightSummaryCSVRequest request)
	{
		CSVResponse response = new CSVResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (CSVResponse)testOtherDefaultModes(response);
	}

	@Override
	public CSVResponse generateLightDetailFileCSV(LightCSVRequest request)
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
