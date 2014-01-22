package com.sensus.lc.light.bcf.impl;

import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.lc.light.bcf.ILightCSVBCF;
import com.sensus.lc.light.bcl.ILightCSVBCL;
import com.sensus.lc.light.model.request.LightCSVRequest;
import com.sensus.lc.light.model.request.LightHistoryCSVRequest;
import com.sensus.lc.light.model.request.LightSummaryCSVRequest;
import com.sensus.lc.light.model.response.CSVInternalResponse;
import com.sensus.lc.light.model.response.CSVResponse;

/**
 * Support the creation of CSV files given various CSV-Request types.
 */
public class LightCSVBCFImpl implements ILightCSVBCF
{

	/** The light csvbcl. */
	private ILightCSVBCL lightCSVBCL;

	/**
	 * Gets the light csvbcl.
	 * 
	 * @return the lightCSVBCL
	 */
	public ILightCSVBCL getLightCSVBCL()
	{
		return lightCSVBCL;
	}

	/**
	 * Sets the light csvbcl.
	 * 
	 * @param lightCSVBCL the lightCSVBCL to set
	 */
	public void setLightCSVBCL(ILightCSVBCL lightCSVBCL)
	{
		this.lightCSVBCL = lightCSVBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcf.ILightCSVBCF#generateLightHistoryFileCSV(com.sensus.mlc.light.model.request.
	 * LightHistoryCSVRequest)
	 */
	@Override
	public CSVResponse generateLightHistoryFileCSV(LightHistoryCSVRequest request)
	{
		CSVResponse response = new CSVResponse();

		// Invoke the BCL method.
		CSVInternalResponse internalResponse = getLightCSVBCL().generateLightHistoryFileCSV(request);

		// Handle the response.
		SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, false);

		// Copy over the file name.
		response.setFileName(request.getFileName());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcf.ILightCSVBCF#generateLightSummaryFileCSV(com.sensus.mlc.light.model.request.
	 * LightSummaryCSVRequest)
	 */
	@Override
	public CSVResponse generateLightSummaryFileCSV(LightSummaryCSVRequest request)
	{
		CSVResponse response = new CSVResponse();

		// Invoke the BCL method.
		CSVInternalResponse internalResponse = getLightCSVBCL().generateLightSummaryFileCSV(request);

		// Handle the response.
		SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, false);

		// Copy over the file name.
		response.setFileName(request.getFileName());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcf.ILightCSVBCF#generateLightDetailFileCSV(com.sensus.mlc.light.model.request.LightCSVRequest
	 * )
	 */
	@Override
	public CSVResponse generateLightDetailFileCSV(LightCSVRequest request)
	{
		CSVResponse response = new CSVResponse();

		// Invoke the BCL method.
		CSVInternalResponse internalResponse = getLightCSVBCL().generateLightDetailFileCSV(request);

		// Handle the response.
		SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, false);

		// Copy over the file name.
		response.setFileName(request.getFileName());

		return response;
	}

}
