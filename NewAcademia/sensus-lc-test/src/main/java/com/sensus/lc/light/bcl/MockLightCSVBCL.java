package com.sensus.lc.light.bcl;

import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.light.model.request.LightCSVRequest;
import com.sensus.lc.light.model.request.LightHistoryCSVRequest;
import com.sensus.lc.light.model.request.LightSummaryCSVRequest;
import com.sensus.lc.light.model.response.CSVInternalResponse;

/**
 * The Class MockLightCSVBCL.
 */
public class MockLightCSVBCL extends AbstractMockBase implements ILightCSVBCL
{

	/** The Constant FILE_NAME_CSV. */
	private static final String FILE_NAME_CSV = "file.csv";

	/** The Constant TEST_EXCEPTION. */
	private static final String TEST_EXCEPTION = "Test Exception";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcl.ILightCSVBCL#generateLightHistoryFileCSV(com.sensus.lc.light.model.request.
	 * LightHistoryCSVRequest)
	 */
	@Override
	public CSVInternalResponse generateLightHistoryFileCSV(LightHistoryCSVRequest request)
	{
		return getResponseBySituations();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcl.ILightCSVBCL#generateLightSummaryFileCSV(com.sensus.lc.light.model.request.
	 * LightSummaryCSVRequest)
	 */
	@Override
	public CSVInternalResponse generateLightSummaryFileCSV(LightSummaryCSVRequest request)
	{
		return getResponseBySituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.light.bcl.ILightCSVBCL#generateLightDetailFileCSV(com.sensus.lc.light.model.request.LightCSVRequest
	 * )
	 */
	@Override
	public CSVInternalResponse generateLightDetailFileCSV(LightCSVRequest request)
	{
		return getResponseBySituations();
	}

	/**
	 * Gets the response default.
	 * 
	 * @return the response default
	 */
	private CSVInternalResponse getResponseDefault()
	{
		return new CSVInternalResponse(FILE_NAME_CSV);
	}

	/**
	 * Gets the response by situations.
	 * 
	 * @return the response by situations
	 */
	private CSVInternalResponse getResponseBySituations()
	{
		CSVInternalResponse csvInternalResponse = new CSVInternalResponse(FILE_NAME_CSV);

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			csvInternalResponse.setStatus(Status.ExceptionError);
			csvInternalResponse.addFieldErrorMessage(ERROR_CODE);
			return csvInternalResponse;
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return csvInternalResponse;
	}

}
