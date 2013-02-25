package com.sensus.mlc.empresa.bcl;


import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.empresa.model.Empresa;
import com.sensus.mlc.empresa.model.request.EmpresaRequest;
import com.sensus.mlc.empresa.model.request.InquiryEmpresaRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IActionBCL.
 *
 * @author - QAT Brazil.
 *
 */
public interface IEmpresaBCL
{

	/**
	 * Insert empresa.
	 *
	 * @param empresaRequest the empresa request
	 * @return the empresa response
	 */
	public InternalResultsResponse<Empresa> insertEmpresa(EmpresaRequest empresaRequest);

	/**
	 * Update empresa.
	 *
	 * @param empresaRequest the empresa request
	 * @return the empresa response
	 */
	public InternalResultsResponse<Empresa> updateEmpresa(EmpresaRequest empresaRequest);

	/**
	 * Delete empresa.
	 *
	 * @param empresaRequest the empresa request
	 * @return the empresa response
	 */
	public InternalResponse deleteEmpresa(EmpresaRequest empresaRequest);

	/**
	 * Fetch all empresa.
	 *
	 * @param inquiryempresaRequest the inquiryempresa request
	 * @return the inquiry empresa response
	 */
	public InternalResultsResponse<Empresa> fetchAllEmpresa(InquiryEmpresaRequest inquiryempresaRequest);

	/**
	 * Fetch empresa by id.
	 *
	 * @param inquiryempresaRequest the inquiryempresa request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Empresa> fetchEmpresaById(EmpresaRequest empresaRequest);

	/**
	 * Fetch all empresa types.
	 *
	 * @param request the request
	 * @return the empresa response
	 */
	public InternalResultsResponse<Empresa> fetchAllEmpresaTypes(InquiryEmpresaRequest inquiryempresaRequest);

	/**
	 * Fetch all empresa filial.
	 *
	 * @param request the request
	 * @return the empresa response
	 */
	public InternalResultsResponse<Empresa> fetchAllEmpresaFilial(EmpresaRequest empresaRequest);

}
