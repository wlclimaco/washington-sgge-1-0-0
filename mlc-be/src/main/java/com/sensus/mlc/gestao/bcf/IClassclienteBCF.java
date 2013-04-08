package com.sensus.mlc.gestao.bcf;

import com.sensus.mlc.gestao.model.request.ClassclienteRequest;
import com.sensus.mlc.gestao.model.request.InquiryClassclienteRequest;
import com.sensus.mlc.gestao.model.response.ClassclienteResponse;
import com.sensus.mlc.gestao.model.response.InquiryClassclienteResponse;


/**
 * The Interface IClassclienteBCF.
 *
 * @author Washington.Costa
 */
public interface IClassclienteBCF
{

	/**
	 * Insert classcliente.
	 *
	 * @param classclienteRequest the classcliente request
	 * @return the classcliente response
	 */
	public ClassclienteResponse insertClasscliente(ClassclienteRequest classclienteRequest);

	/**
	 * Update classcliente.
	 *
	 * @param classclienteRequest the classcliente request
	 * @return the classcliente response
	 */
	public ClassclienteResponse updateClasscliente(ClassclienteRequest classclienteRequest);

	/**
	 * Delete classcliente.
	 *
	 * @param classclienteRequest the classcliente request
	 * @return the classcliente response
	 */
	public ClassclienteResponse deleteClasscliente(ClassclienteRequest classclienteRequest);

	/**
	 * Fetch all classcliente.
	 *
	 * @param inquiryClassclienteRequest the inquiryClasscliente request
	 * @return the inquiry classcliente response
	 */
	public InquiryClassclienteResponse fetchAllClasscliente(InquiryClassclienteRequest inquiryClassclienteRequest);

	/**
	 * Fetch classcliente by id.
	 *
	 * @param ClassclienteRequest the classcliente request
	 * @return the classcliente response
	 */
	public ClassclienteResponse fetchClassclienteById(ClassclienteRequest classclienteRequest);

}
