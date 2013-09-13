package com.sensus.dm.common.process.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.process.model.CSVProcess;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;

/**
 * The Interface IProcessBCL.
 * 
 * @author QAT Global
 */
public interface IProcessCSVBCL
{

	/**
	 * Insert csv process.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the internal results response
	 */
	InternalResultsResponse<CSVProcess> insertCSVProcess(InquiryProcessRequest inquiryProcessRequest);

	/**
	 * Generate file csv summary.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the internal response
	 */
	InternalResponse generateFileCSVSummary(InquiryProcessRequest inquiryProcessRequest);

	/**
	 * Generate file csv communication summary.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the internal response
	 */
	InternalResponse generateFileCSVCommunicationSummary(InquiryProcessRequest inquiryProcessRequest);

	/**
	 * Generate file csv demand response summary.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the internal response
	 */
	InternalResponse generateFileCSVDemandResponseSummary(InquiryProcessRequest inquiryProcessRequest);

	/**
	 * Generate file csv demand read summary.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the internal response
	 */
	InternalResponse generateFileCSVDemandReadSummary(InquiryProcessRequest inquiryProcessRequest);

	/**
	 * Generate file csv import han summary.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the internal response
	 */
	InternalResponse generateFileCSVImportHanSummary(InquiryProcessRequest inquiryProcessRequest);

	/**
	 * Generate file csv today.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the internal response
	 */
	InternalResponse generateFileCSVToday(InquiryProcessRequest inquiryProcessRequest);

	/**
	 * Generate file csv event history.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the internal response
	 */
	InternalResponse generateFileCSVEventHistory(InquiryProcessRequest inquiryProcessRequest);

	/**
	 * Generate file csv device history.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the internal response
	 */
	InternalResponse generateFileCSVDeviceHistory(InquiryProcessRequest inquiryProcessRequest);

	/**
	 * Generate file csv tamper detect summary.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the internal response
	 */
	InternalResponse generateFileCSVTamperDetectSummary(InquiryProcessRequest inquiryProcessRequest);

	/**
	 * Generate file csv demand response setup summary.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the internal response
	 */
	InternalResponse generateFileCSVDemandResponseSetupSummary(InquiryProcessRequest inquiryProcessRequest);

}
