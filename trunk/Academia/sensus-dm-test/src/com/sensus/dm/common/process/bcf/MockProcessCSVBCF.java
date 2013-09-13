package com.sensus.dm.common.process.bcf;

import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.common.util.AbstractMockBase;

/**
 * The Class MockProcessCSVBCF.
 * 
 * @author QAT Global.
 */
public class MockProcessCSVBCF extends AbstractMockBase implements IProcessCSVBCF
{

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
		return new ProcessResponse();
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
		return new ProcessResponse();
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
		return new ProcessResponse();
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
		return new ProcessResponse();
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
		return new ProcessResponse();
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
		return new ProcessResponse();
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
		return new ProcessResponse();
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
		return new ProcessResponse();
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
		return new ProcessResponse();
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
		return new ProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessCSVBCF#generateFileCSVTamperDetectSummary(com.sensus.dm.common.process
	 * .model.request.InquiryProcessRequest)
	 */
	@Override
	public ProcessResponse generateFileCSVTamperDetectSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		return new ProcessResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessCSVBCF#generateFileCSVDemandResponseSetupSummary(com.sensus.dm.common
	 * .process.model.request.InquiryProcessRequest)
	 */
	@Override
	public ProcessResponse generateFileCSVDemandResponseSetupSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		return new ProcessResponse();
	}
}
