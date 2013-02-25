package com.sensus.mlc.almoxarifado.bcf;

import com.sensus.mlc.almoxarifado.model.request.AlmoxarifadoRequest;
import com.sensus.mlc.almoxarifado.model.request.InquiryAlmoxarifadoRequest;
import com.sensus.mlc.almoxarifado.model.response.AlmoxarifadoResponse;
import com.sensus.mlc.almoxarifado.model.response.InquiryAlmoxarifadoResponse;
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
public interface IAlmoxarifadoBCF
{

	/**
	 * Insert filial.
	 *
	 * @param filialRequest the filial request
	 * @return the filial response
	 */
	public AlmoxarifadoResponse insertAlmoxarifado(AlmoxarifadoRequest almoxarifadoRequest);

	/**
	 * Update filial.
	 *
	 * @param filialRequest the filial request
	 * @return the filial response
	 */
	public AlmoxarifadoResponse updateAlmoxarifado(AlmoxarifadoRequest almoxarifadoRequest);

	/**
	 * Delete filial.
	 *
	 * @param filialRequest the filial request
	 * @return the filial response
	 */
	public AlmoxarifadoResponse deleteAlmoxarifado(AlmoxarifadoRequest almoxarifadoRequest);

	/**
	 * Fetch all filial.
	 *
	 * @param inquiryfilialRequest the inquiryfilial request
	 * @return the inquiry filial response
	 */
	public InquiryAlmoxarifadoResponse fetchAllAlmoxarifado(InquiryAlmoxarifadoRequest inquiryAlmoxarifadoRequest);

	/**
	 * Fetch filial by id.
	 *
	 * @param filialRequest the filial request
	 * @return the filial response
	 */
	public AlmoxarifadoResponse fetchAlmoxarifadoById(AlmoxarifadoRequest almoxarifadoRequest);

	/**
	 * Generate file csv.
	 *
	 * @param inquiryFilialRequest the inquiry filial request
	 * @return the inquiry filial response
	 */
	public InquiryAlmoxarifadoResponse generateFileCSV(InquiryAlmoxarifadoRequest inquiryAlmoxarifadoRequest);

	/**
	 * Insert csv process.
	 *
	 * @param filialRequest the filial request
	 * @return the process response
	 */
	public ProcessResponse insertCSVProcess(InquiryAlmoxarifadoRequest almoxarifadoRequest);

	/**
	 * Fetch all filial types.
	 *
	 * @param request the request
	 * @return the filial response
	 */
	public AlmoxarifadoResponse fetchAllAlmoxarifadoTypes(InquiryAlmoxarifadoRequest request);

	/**
	 * Fetch all filial empresa.
	 *
	 * @param request the request
	 * @return the filial response
	 */
	public InquiryAlmoxarifadoResponse fetchAllAlmoxarifadoEmpresa(InquiryAlmoxarifadoRequest inquiryAlmoxarifadoRequest);
}
