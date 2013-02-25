package com.sensus.mlc.empresa.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.empresa.model.request.EmpresaRequest;
import com.sensus.mlc.empresa.model.request.InquiryEmpresaRequest;
import com.sensus.mlc.empresa.model.response.EmpresaResponse;
import com.sensus.mlc.empresa.model.response.InquiryEmpresaResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/**
 * The Interface IEmpresaBCF.
 *
 * @author QAT Brazil.
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
	 * @param inquiryempresaRequest the inquiryempresa request
	 * @return the inquiry empresa response
	 */
	public InquiryEmpresaResponse fetchAllEmpresa(InquiryEmpresaRequest inquiryempresaRequest);

	/**
	 * Fetch empresa by id.
	 *
	 * @param empresaRequest the empresa request
	 * @return the empresa response
	 */
	public EmpresaResponse fetchEmpresaById(EmpresaRequest empresaRequest);

	/**
	 * Generate file csv.
	 *
	 * @param inquiryActionRequest the inquiry action request
	 * @return the internal response
	 */
	public ProcessResponse generateFileCSV(InquiryEmpresaRequest inquiryEmpresaRequest);

	/**
	 * Insert csv process.
	 *
	 * @param empresaRequest the empresa request
	 * @return the process response
	 */
	public ProcessResponse insertCSVProcess(InquiryEmpresaRequest empresaRequest);

	/**
	 * Fetch all empresa types.
	 *
	 * @param request the request
	 * @return the empresa response
	 */
	public EmpresaResponse fetchAllEmpresaTypes(EmpresaRequest empresaRequest);

	/**
	 * Fetch all empresa filial.
	 *
	 * @param request the request
	 * @return the empresa response
	 */
	public EmpresaResponse fetchAllEmpresaFilial(EmpresaRequest empresaRequest);

}
