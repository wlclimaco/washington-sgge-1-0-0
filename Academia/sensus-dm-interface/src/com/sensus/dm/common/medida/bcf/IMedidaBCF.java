package com.sensus.dm.common.medida.bcf;

import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.medida.model.request.MedidaRequest;
import com.sensus.dm.common.medida.model.request.InquiryMedidaRequest;
import com.sensus.dm.common.medida.model.response.MedidaResponse;
import com.sensus.dm.common.medida.model.response.InquiryMedidaResponse;

/**
 * The Interface IMedidaBCF.
 * 
 * @author Washington.
 * 
 */
public interface IMedidaBCF
{

	/**
	 * Fetch all medidas.
	 * 
	 * @param inquiryMedidaRequest the inquiry medida request
	 * @return the inquiry medida response
	 */
	InquiryMedidaResponse fetchAllMedidas(InquiryMedidaRequest inquiryMedidaRequest);

	/**
	 * Fetch medida by id.
	 * 
	 * @param inquiryMedidaRequest the inquiry medida request
	 * @return the medida response
	 */
	MedidaResponse fetchMedidaById(InquiryMedidaRequest inquiryMedidaRequest);

	/**
	 * Fetch medida by name.
	 * 
	 * @param inquiryMedidaRequest the inquiry medida request
	 * @return the medida response
	 */
	MedidaResponse fetchMedidaByName(InquiryMedidaRequest inquiryMedidaRequest);

	/**
	 * Insert medida.
	 * 
	 * @param medidaRequest the medida request
	 * @return the medida response
	 */
	MedidaResponse insertMedida(MedidaRequest medidaRequest);

	/**
	 * Update medida.
	 * 
	 * @param medidaRequest the medida request
	 * @return the medida response
	 */
	MedidaResponse updateMedida(MedidaRequest medidaRequest);

	/**
	 * Delete medida.
	 * 
	 * @param medidaRequest the medida request
	 * @return the medida response
	 */
	MedidaResponse deleteMedida(MedidaRequest medidaRequest);

	/**
	 * Fetch devices by medida.
	 * 
	 * @param medidaRequest the medida request
	 * @return the device response
	 */
	DeviceResponse fetchDevicesByMedida(MedidaRequest medidaRequest);

}

