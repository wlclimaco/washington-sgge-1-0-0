package com.sensus.dm.controller.export.unittest;

import com.sensus.dm.common.process.bcf.IProcessCSVBCF;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.controller.unittest.util.BaseMockImpl;
import com.sensus.dm.controller.unittest.util.ModeEnum;

/**
 * The Class ProcessCsvBCFMockImpl.
 */
public class ProcessCsvBCFMockImpl extends BaseMockImpl implements IProcessCSVBCF
{

	/**
	 * Gets the generate csv results.
	 * 
	 * @return the generate csv results
	 */
	private ProcessResponse getGenerateCsvResults()
	{
		ProcessResponse processResponse = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return processResponse;
		}

		return (ProcessResponse)testOtherDefaultModes(processResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessCSVBCF#updateCSVDownloaded(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public ProcessResponse updateCSVDownloaded(ProcessRequest processRequest)
	{
		return getGenerateCsvResults();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessCSVBCF#generateSummaryFileCSV(com.sensus.dm.common.process.model.request
	 * .
	 * InquiryProcessRequest)
	 */
	@Override
	public ProcessResponse generateFileCSVSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		return getGenerateCsvResults();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessCSVBCF#insertCSVProcess(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest
	 * )
	 */
	@Override
	public ProcessResponse insertCSVProcess(InquiryProcessRequest processRequest)
	{
		return getGenerateCsvResults();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessCSVBCF#generateFileCSVCommunicationSummary(com.sensus.dm.common.process.
	 * model.request
	 * .InquiryProcessRequest)
	 */
	@Override
	public ProcessResponse generateFileCSVCommunicationSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		return getGenerateCsvResults();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessCSVBCF#generateFileCSVDemandResponseSummary(com.sensus.dm.common.process.
	 * model.request
	 * .InquiryProcessRequest)
	 */
	@Override
	public ProcessResponse generateFileCSVDemandResponseSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		return getGenerateCsvResults();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessCSVBCF#generateFileCSVImportHanSummary(com.sensus.dm.common.process.model
	 * .request
	 * .InquiryProcessRequest)
	 */
	@Override
	public ProcessResponse generateFileCSVImportHanSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		return getGenerateCsvResults();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessCSVBCF#generateTodayCSV(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest
	 * )
	 */
	@Override
	public ProcessResponse generateFileCSVToday(InquiryProcessRequest inquiryProcessRequest)
	{
		return getGenerateCsvResults();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessCSVBCF#generateEventHistoryCSV(com.sensus.dm.common.process.model.request
	 * .
	 * InquiryProcessRequest)
	 */
	@Override
	public ProcessResponse generateFileCSVEventHistory(InquiryProcessRequest inquiryProcessRequest)
	{
		return getGenerateCsvResults();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessCSVBCF#generateDeviceHistoryCSV(com.sensus.dm.common.process.model.request
	 * .
	 * InquiryProcessRequest)
	 */
	@Override
	public ProcessResponse generateFileCSVDeviceHistory(InquiryProcessRequest inquiryProcessRequest)
	{
		return getGenerateCsvResults();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessCSVBCF#generateFileCSVDemandReadSummary(com.sensus.dm.common.process
	 * .model.request.InquiryProcessRequest)
	 */
	@Override
	public ProcessResponse generateFileCSVDemandReadSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		return getGenerateCsvResults();
	}

	@Override
	public ProcessResponse generateFileCSVTamperDetectSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessResponse generateFileCSVDemandResponseSetupSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
