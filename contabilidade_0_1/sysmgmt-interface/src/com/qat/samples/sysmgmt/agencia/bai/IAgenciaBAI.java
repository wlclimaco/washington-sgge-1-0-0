package com.qat.samples.sysmgmt.agencia.bai;

import com.qat.samples.sysmgmt.agencia.model.request.AgenciaInquiryRequest;
import com.qat.samples.sysmgmt.agencia.model.request.AgenciaMaintenanceRequest;
import com.qat.samples.sysmgmt.agencia.model.response.AgenciaResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Interface IAgenciaBAI.
 */
public interface IAgenciaBAI
{

	public AgenciaResponse insertAgencia(AgenciaMaintenanceRequest request);

	public AgenciaResponse updateAgencia(AgenciaMaintenanceRequest request);

	public AgenciaResponse deleteAgencia(AgenciaMaintenanceRequest request);

	public AgenciaResponse fetchAgenciaById(FetchByIdRequest request);

	public AgenciaResponse fetchAgenciaByRequest(AgenciaInquiryRequest request);

}