package com.sensus.mlc.gestao.bcf;

import com.sensus.mlc.empresa.model.request.EmpresaRequest;
import com.sensus.mlc.empresa.model.request.InquiryEmpresaRequest;
import com.sensus.mlc.empresa.model.response.EmpresaResponse;
import com.sensus.mlc.empresa.model.response.InquiryEmpresaResponse;


/**
 * The Interface IEmpresaBCF.
 *
 * @author Washington.Costa
 */
public interface IEmpresaBCF
{

	/**
	 * Insert empresa.
	 *
	 * @param empresaRequest the empresa request
	 * @return the empresa response
	 */
	public EmpresaResponse insertEmpresa(EmpresaRequest empresaRequest);

	/**
	 * Update empresa.
	 *
	 * @param empresaRequest the empresa request
	 * @return the empresa response
	 */
	public EmpresaResponse updateEmpresa(EmpresaRequest empresaRequest);

	/**
	 * Delete empresa.
	 *
	 * @param empresaRequest the empresa request
	 * @return the empresa response
	 */
	public EmpresaResponse deleteEmpresa(EmpresaRequest empresaRequest);

	/**
	 * Fetch all empresa.
	 *
	 * @param inquiryEmpresaRequest the inquiryEmpresa request
	 * @return the inquiry empresa response
	 */
	public InquiryEmpresaResponse fetchAllEmpresa(InquiryEmpresaRequest inquiryEmpresaRequest);

	/**
	 * Fetch empresa by id.
	 *
	 * @param EmpresaRequest the empresa request
	 * @return the empresa response
	 */
	public EmpresaResponse fetchEmpresaById(EmpresaRequest empresaRequest);

}
