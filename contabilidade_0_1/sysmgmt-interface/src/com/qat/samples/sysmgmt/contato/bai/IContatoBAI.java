package com.qat.samples.sysmgmt.contato.bai;

import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.contato.model.request.ContatoMaintenanceRequest;
import com.qat.samples.sysmgmt.contato.model.response.ContatoResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Interface IContatoBAI.
 */
public interface IContatoBAI
{

	public ContatoResponse insertContato(ContatoMaintenanceRequest request);

	public ContatoResponse updateContato(ContatoMaintenanceRequest request);

	public ContatoResponse deleteContato(ContatoMaintenanceRequest request);

	public ContatoResponse fetchContatoById(FetchByIdRequest request);

	public ContatoResponse fetchContatoByRequest(ContatoInquiryRequest request);

}