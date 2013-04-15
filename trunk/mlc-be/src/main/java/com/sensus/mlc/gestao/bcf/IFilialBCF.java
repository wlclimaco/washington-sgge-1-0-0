package com.sensus.mlc.gestao.bcf;

import com.sensus.mlc.gestao.model.request.FilialRequest;
import com.sensus.mlc.gestao.model.request.InquiryFilialRequest;
import com.sensus.mlc.gestao.model.response.FilialResponse;
import com.sensus.mlc.gestao.model.response.InquiryFilialResponse;


/**
 * The Interface IFilialBCF.
 *
 * @author Washington.Costa
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
	 * @param inquiryFilialRequest the inquiryFilial request
	 * @return the inquiry filial response
	 */
	public InquiryFilialResponse fetchAllFilial(InquiryFilialRequest inquiryFilialRequest);

	/**
	 * Fetch filial by id.
	 *
	 * @param FilialRequest the filial request
	 * @return the filial response
	 */
	public FilialResponse fetchFilialById(FilialRequest filialRequest);

}
