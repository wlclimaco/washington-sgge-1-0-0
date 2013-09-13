package com.sensus.dm.common.medida.bcl;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.medida.model.Medida;
import com.sensus.dm.common.medida.model.request.MedidaRequest;
import com.sensus.dm.common.medida.model.request.InquiryMedidaRequest;

/**
 * The Interface IMedidaBCL.
 * 
 * @author Washington.
 * 
 */
public interface IMedidaBCL
{

	/**
	 * Fetch all medidas.
	 * 
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the internal results response
	 */
	InternalResultsResponse<Medida> fetchAllMedidas(InquiryMedidaRequest inquiryPaginationRequest);

	/**
	 * Delete medida.
	 * 
	 * @param medidaRequest the medida request
	 * @return the internal response
	 */
	InternalResponse deleteMedida(MedidaRequest medidaRequest);

	/**
	 * Update medida.
	 * 
	 * @param medidaRequest the medida request
	 * @return the internal response
	 */
	InternalResultsResponse<Medida> updateMedida(MedidaRequest medidaRequest);

	/**
	 * Fetch medidas by device.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Medida> fetchMedidasById(InquiryMedidaRequest InquiryMedidaRequest);

	/**
	 * Insert medida.
	 * 
	 * @param medidaRequest the medida request
	 * @return the internal results response
	 */
	InternalResultsResponse<Medida> insertMedida(MedidaRequest medidaRequest);

	/**
	 * Fetch devices by medida.
	 * 
	 * @param medidaRequest the medida request
	 * @return the internal results response
	 */
	InternalResultsResponse<Medida> fetchDevicesByName(MedidaRequest medidaRequest);

}

