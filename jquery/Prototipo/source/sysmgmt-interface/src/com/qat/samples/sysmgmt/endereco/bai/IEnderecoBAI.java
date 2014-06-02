package com.qat.samples.sysmgmt.endereco.bai;

import com.qat.samples.sysmgmt.endereco.model.request.EnderecoMaintenanceRequest;
import com.qat.samples.sysmgmt.endereco.model.response.EnderecoResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * The Interface IEnderecoBAI. (Business Area Interface - BAI)
 */
public interface IEnderecoBAI
{

	/**
	 * Insert endereco.
	 * 
	 * @param request the request
	 * @return the endereco paged response
	 */
	public EnderecoResponse insertEndereco(EnderecoMaintenanceRequest request);

	/**
	 * Update endereco.
	 * 
	 * @param request the request
	 * @return the endereco paged response
	 */
	public EnderecoResponse updateEndereco(EnderecoMaintenanceRequest request);

	/**
	 * Delete endereco.
	 * 
	 * @param request the request
	 * @return the endereco paged response
	 */
	public EnderecoResponse deleteEndereco(EnderecoMaintenanceRequest request);

	/**
	 * Fetch all enderecos.
	 * 
	 * @param request the request
	 * @return the endereco response
	 */
	public EnderecoResponse fetchAllEnderecos(FetchAllRequest request);

	/**
	 * Refresh enderecos.
	 * 
	 * @param request the request
	 * @return the endereco paged response
	 */
	public EnderecoResponse refreshEnderecos(RefreshRequest request);

	/**
	 * Fetch endereco by id.
	 * 
	 * @param request the request
	 * @return the endereco response
	 */
	public EnderecoResponse fetchEnderecoById(FetchByIdRequest request);

	/**
	 * Fetch enderecos by request.
	 * 
	 * @param request the request
	 * @return the endereco paged response
	 */
	public EnderecoResponse fetchEnderecosByRequest(PagedInquiryRequest request);

}
