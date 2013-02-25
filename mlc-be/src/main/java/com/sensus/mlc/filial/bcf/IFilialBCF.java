package com.sensus.mlc.filial.bcf;


import com.sensus.common.model.request.Request;
import com.sensus.mlc.filial.model.request.FilialRequest;
import com.sensus.mlc.filial.model.request.InquiryFilialRequest;
import com.sensus.mlc.filial.model.response.FilialResponse;
import com.sensus.mlc.filial.model.response.InquiryFilialResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/**
 * The Interface IActionBCF.
 *
 * @author QAT Brazil.
 */
public interface IFilialBCF
{

	/**
	 * Insert filial.
	 *
	 * @param filialRequest the filial request
	 * @return the filial response
	 */
	public FilialResponse insertFilial(FilialRequest filialRequest);

	/**
	 * Update filial.
	 *
	 * @param filialRequest the filial request
	 * @return the filial response
	 */
	public FilialResponse updateFilial(FilialRequest filialRequest);

	/**
	 * Delete filial.
	 *
	 * @param filialRequest the filial request
	 * @return the filial response
	 */
	public FilialResponse deleteFilial(FilialRequest filialRequest);

	/**
	 * Fetch all filial.
	 *
	 * @param inquiryfilialRequest the inquiryfilial request
	 * @return the inquiry filial response
	 */
	public InquiryFilialResponse fetchAllFilial(InquiryFilialRequest inquiryfilialRequest);

	/**
	 * Fetch filial by id.
	 *
	 * @param filialRequest the filial request
	 * @return the filial response
	 */
	public FilialResponse fetchFilialById(FilialRequest filialRequest);

	/**
	 * Generate file csv.
	 *
	 * @param inquiryFilialRequest the inquiry filial request
	 * @return the inquiry filial response
	 */
	public InquiryFilialResponse generateFileCSV(InquiryFilialRequest inquiryFilialRequest);

	/**
	 * Insert csv process.
	 *
	 * @param filialRequest the filial request
	 * @return the process response
	 */
	public ProcessResponse insertCSVProcess(InquiryFilialRequest filialRequest);

	/**
	 * Fetch all filial types.
	 *
	 * @param request the request
	 * @return the filial response
	 */
	public FilialResponse fetchAllFilialTypes(Request request);

	/**
	 * Fetch all filial empresa.
	 *
	 * @param request the request
	 * @return the filial response
	 */
	public InquiryFilialResponse fetchAllFilialEmpresa(InquiryFilialRequest inquiryFilialRequest);
}
