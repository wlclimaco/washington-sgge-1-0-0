package com.sensus.mlc.gestao.bcl;


import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.gestao.model.Classcliente;
import com.sensus.mlc.gestao.model.request.ClassclienteRequest;
import com.sensus.mlc.gestao.model.request.InquiryClassclienteRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IActionBCL.
 *
 * @author - Washington
 *
 */
public interface IClassclienteBCL
{

	/**
	 * Insert classcliente.
	 *
	 * @param classclienteRequest the classcliente request
	 * @return the classcliente response
	 */
	public InternalResultsResponse<Classcliente> insertClasscliente(ClassclienteRequest classclienteRequest);

	/**
	 * Update classcliente.
	 *
	 * @param classclienteRequest the classcliente request
	 * @return the classcliente response
	 */
	public InternalResultsResponse<Classcliente> updateClasscliente(ClassclienteRequest classclienteRequest);

	/**
	 * Delete classcliente.
	 *
	 * @param classclienteRequest the classcliente request
	 * @return the classcliente response
	 */
	public InternalResponse deleteClasscliente(ClassclienteRequest classclienteRequest);

	/**
	 * Fetch all classcliente.
	 *
	 * @param inquiryclassclienteRequest the inquiryclasscliente request
	 * @return the inquiry classcliente response
	 */
	public InternalResultsResponse<Classcliente> fetchAllClasscliente(InquiryClassclienteRequest inquiryclassclienteRequest);

	/**
	 * Fetch classcliente by id.
	 *
	 * @param inquiryclassclienteRequest the inquiryclasscliente request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Classcliente> fetchClassclienteById(ClassclienteRequest classclienteRequest);

	/**
	 * Fetch all classcliente types.
	 *
	 * @param request the request
	 * @return the classcliente response
	 */
	public InternalResultsResponse<Classcliente> fetchAllClassclienteTypes(InquiryClassclienteRequest inquiryclassclienteRequest);

	/**
	 * Fetch all classcliente filial.
	 *
	 * @param request the request
	 * @return the classcliente response
	 */
	public InternalResultsResponse<Classcliente> fetchAllClassclienteFilial(ClassclienteRequest classclienteRequest);

}
