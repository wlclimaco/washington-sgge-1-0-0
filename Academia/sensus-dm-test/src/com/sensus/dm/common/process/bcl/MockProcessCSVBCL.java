package com.sensus.dm.common.process.bcl;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.process.model.CSVProcess;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;

/**
 * The Class MockProcessCSVBCL.
 * 
 * @author QAT Global.
 */
public class MockProcessCSVBCL extends AbstractMockBase implements IProcessCSVBCL
{

	/** The Constant TEST_EXCEPTION. */
	private static final String TEST_EXCEPTION = "Test Exception";

	/** The Constant ONE. */
	private static final int ONE = 1;

	/** The Constant FILE_NAME. */
	private static final String FILE_NAME = "FileTest";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessCSVBCL#generateFileCSVSummary(com.sensus.dm.common.process.model.request
	 * .InquiryProcessRequest)
	 */
	@Override
	public InternalResponse generateFileCSVSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		return verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessCSVBCL#insertCSVProcess(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<CSVProcess> insertCSVProcess(InquiryProcessRequest inquiryProcessRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		InternalResultsResponse<CSVProcess> internalResponse = new InternalResultsResponse<CSVProcess>();

		List<DMProcess> processList = new ArrayList<DMProcess>();
		processList.add(new DMProcess(ONE));

		CSVProcess csvProcess = new CSVProcess();
		csvProcess.setFileName(FILE_NAME);
		csvProcess.setProcessList(processList);

		internalResponse.addResult(csvProcess);

		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessCSVBCL#generateFileCSVCommunicationSummary(com.sensus.dm.common.process.
	 * model.request
	 * .InquiryProcessRequest)
	 */
	@Override
	public InternalResponse generateFileCSVCommunicationSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		return verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessCSVBCL#generateFileCSVDemandResponseSummary(com.sensus.dm.common.process.
	 * model.request
	 * .InquiryProcessRequest)
	 */
	@Override
	public InternalResponse generateFileCSVDemandResponseSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		return verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessCSVBCL#generateFileCSVImportHanSummary(com.sensus.dm.common.process.model
	 * .request
	 * .InquiryProcessRequest)
	 */
	@Override
	public InternalResponse generateFileCSVImportHanSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		return verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessCSVBCL#generateFileCSVToday(com.sensus.dm.common.process.model.request
	 * .InquiryProcessRequest)
	 */
	@Override
	public InternalResponse generateFileCSVToday(InquiryProcessRequest inquiryProcessRequest)
	{
		return verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessCSVBCL#generateFileCSVEventHistory(com.sensus.dm.common.process.model
	 * .request.InquiryProcessRequest)
	 */
	@Override
	public InternalResponse generateFileCSVEventHistory(InquiryProcessRequest inquiryProcessRequest)
	{
		return verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessCSVBCL#generateFileCSVDeviceHistory(com.sensus.dm.common.process.model
	 * .request.InquiryProcessRequest)
	 */
	@Override
	public InternalResponse generateFileCSVDeviceHistory(InquiryProcessRequest inquiryProcessRequest)
	{
		return verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessCSVBCL#generateFileCSVDemandReadSummary(com.sensus.dm.common.process
	 * .model.request.InquiryProcessRequest)
	 */
	@Override
	public InternalResponse generateFileCSVDemandReadSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessCSVBCL#generateFileCSVTamperDetectSummary(com.sensus.dm.common.process
	 * .model.request.InquiryProcessRequest)
	 */
	@Override
	public InternalResponse generateFileCSVTamperDetectSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessCSVBCL#generateFileCSVDemandResponseSetupSummary(com.sensus.dm.common
	 * .process.model.request.InquiryProcessRequest)
	 */
	@Override
	public InternalResponse generateFileCSVDemandResponseSetupSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		return new InternalResponse();
	}

}
