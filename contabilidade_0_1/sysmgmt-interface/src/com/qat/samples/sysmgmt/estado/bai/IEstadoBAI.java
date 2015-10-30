package com.qat.samples.sysmgmt.estado.bai;

import com.qat.samples.sysmgmt.estado.model.request.EstadoInquiryRequest;
import com.qat.samples.sysmgmt.estado.model.request.EstadoMaintenanceRequest;
import com.qat.samples.sysmgmt.estado.model.response.EstadoResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Interface IEstadoBAI.
 */
public interface IEstadoBAI
{

	public EstadoResponse insertEstado(EstadoMaintenanceRequest request);

	public EstadoResponse updateEstado(EstadoMaintenanceRequest request);

	public EstadoResponse deleteEstado(EstadoMaintenanceRequest request);

	public EstadoResponse fetchEstadoById(FetchByIdRequest request);

	public EstadoResponse fetchEstadoByRequest(EstadoInquiryRequest request);

}