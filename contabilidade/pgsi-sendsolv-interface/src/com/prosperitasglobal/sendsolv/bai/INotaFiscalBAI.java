package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.NotaFiscalResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface INotaFiscalBAI.
 */
public interface INotaFiscalBAI
{

	/**
	 * Insert nota fiscal.
	 *
	 * @param request the request
	 * @return the nota fiscal response
	 */
	public NotaFiscalResponse insertNotaFiscal(NotaFiscalMaintenanceRequest request);

	/**
	 * Update nota fiscal.
	 *
	 * @param request the request
	 * @return the nota fiscal response
	 */
	public NotaFiscalResponse updateNotaFiscal(NotaFiscalMaintenanceRequest request);

	/**
	 * Delete nota fiscal.
	 *
	 * @param request the request
	 * @return the nota fiscal response
	 */
	public NotaFiscalResponse deleteNotaFiscal(NotaFiscalMaintenanceRequest request);

	/**
	 * Fetch nota fiscal by id.
	 *
	 * @param request the request
	 * @return the nota fiscal response
	 */
	public NotaFiscalResponse fetchNotaFiscalById(FetchByIdRequest request);

	/**
	 * Fetch nota fiscal by request.
	 *
	 * @param request the request
	 * @return the nota fiscal response
	 */
	public NotaFiscalResponse fetchNotaFiscalByRequest(NotaFiscalInquiryRequest request);

}