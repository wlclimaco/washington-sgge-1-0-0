package com.sensus.mlc.gestao.bcl;


import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.filial.model.Filial;
import com.sensus.mlc.filial.model.request.FilialRequest;
import com.sensus.mlc.filial.model.request.InquiryFilialRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IActionBCL.
 *
 * @author - Washington
 *
 */
public interface IFilialBCL
{

	/**
	 * Insert filial.
	 *
	 * @param filialRequest the filial request
	 * @return the filial response
	 */
	public InternalResultsResponse<Filial> insertFilial(FilialRequest filialRequest);

	/**
	 * Update filial.
	 *
	 * @param filialRequest the filial request
	 * @return the filial response
	 */
	public InternalResultsResponse<Filial> updateFilial(FilialRequest filialRequest);

	/**
	 * Delete filial.
	 *
	 * @param filialRequest the filial request
	 * @return the filial response
	 */
	public InternalResponse deleteFilial(FilialRequest filialRequest);

	/**
	 * Fetch all filial.
	 *
	 * @param inquiryfilialRequest the inquiryfilial request
	 * @return the inquiry filial response
	 */
	public InternalResultsResponse<Filial> fetchAllFilial(InquiryFilialRequest inquiryfilialRequest);

	/**
	 * Fetch filial by id.
	 *
	 * @param inquiryfilialRequest the inquiryfilial request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Filial> fetchFilialById(FilialRequest filialRequest);

	/**
	 * Fetch all filial types.
	 *
	 * @param request the request
	 * @return the filial response
	 */
	public InternalResultsResponse<Filial> fetchAllFilialTypes(InquiryFilialRequest inquiryfilialRequest);

	/**
	 * Fetch all filial filial.
	 *
	 * @param request the request
	 * @return the filial response
	 */
	public InternalResultsResponse<Filial> fetchAllFilialFilial(FilialRequest filialRequest);

}
