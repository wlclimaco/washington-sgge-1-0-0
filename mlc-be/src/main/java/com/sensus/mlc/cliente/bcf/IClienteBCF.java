package com.sensus.mlc.cliente.bcf;

import com.sensus.mlc.cliente.model.reponse.ClienteResponse;
import com.sensus.mlc.cliente.model.reponse.InquiryClienteResponse;
import com.sensus.mlc.cliente.model.request.ClienteRequest;
import com.sensus.mlc.cliente.model.request.InquiryClienteRequest;
import com.sensus.mlc.empresa.model.response.EmpresaResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;


// TODO: Auto-generated Javadoc
/**
 * The Interface IEmpresaBCF.
 *
 * @author QAT Brazil.
 */
public interface IClienteBCF
{

	/**
	 * Insert empresa.
	 *
	 * @param clienteRequest the cliente request
	 * @return the empresa response
	 */
	public EmpresaResponse insertCliente(ClienteRequest clienteRequest);

	/**
	 * Update empresa.
	 *
	 * @param clienteRequest the cliente request
	 * @return the empresa response
	 */
	public EmpresaResponse updateCliente(ClienteRequest clienteRequest);

	/**
	 * Delete empresa.
	 *
	 * @param clienteRequest the cliente request
	 * @return the empresa response
	 */
	public EmpresaResponse deleteCliente(ClienteRequest clienteRequest);

	/**
	 * Fetch all empresa.
	 *
	 * @param inquiryClienteRequest the inquiry cliente request
	 * @return the inquiry empresa response
	 */
	public InquiryClienteResponse fetchAllCliente(InquiryClienteRequest inquiryClienteRequest);

	/**
	 * Fetch empresa by id.
	 *
	 * @param clienteRequest the cliente request
	 * @return the empresa response
	 */
	public ClienteResponse fetchClienteById(ClienteRequest clienteRequest);

	/**
	 * Generate file csv.
	 *
	 * @param inquiryClienteRequest the inquiry cliente request
	 * @return the internal response
	 */
	public ProcessResponse generateFileCSV(InquiryClienteRequest inquiryClienteRequest);

	/**
	 * Insert csv process.
	 *
	 * @param clienteRequest the cliente request
	 * @return the process response
	 */
	public ProcessResponse insertCSVProcess(InquiryClienteRequest clienteRequest);

	/**
	 * Fetch all empresa types.
	 *
	 * @param request the request
	 * @return the empresa response
	 */
	public ClienteResponse fetchAllClienteTypes(InquiryClienteRequest request);


	/**
	 * Fetch all column filters.
	 *
	 * @param columnFilterRequest the column filter request
	 * @return the column filter response
	 */
	public ColumnFilterResponse fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest);


}
