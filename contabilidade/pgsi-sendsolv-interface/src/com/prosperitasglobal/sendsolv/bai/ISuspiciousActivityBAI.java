package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.SarInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.SarMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.SarResponse;
import com.qat.framework.model.response.MaintenanceResponse;

/**
 *
 * Interface for access to Suspicious Activity Reporting (SAR) methods.
 *
 */
public interface ISuspiciousActivityBAI
{
	/**
	 * This method should be used when a new SuspiciousActivity event is being entered.
	 *
	 * @param request
	 * @return
	 */
	public MaintenanceResponse insertSuspiciousActivity(SarMaintenanceRequest request);

	/**
	 * This method should be used when a SuspiciousActivity is being updated. The update
	 * will actually result in an insert of a new row with the same business key since
	 * a work flow audit needs to be kept as statuses change.
	 *
	 * @param request
	 * @return
	 */
	public MaintenanceResponse updateSuspiciousActivity(SarMaintenanceRequest request);

	/**
	 * This method should be used when a SuspiciousActivity needs to be deleted.
	 *
	 * @param request
	 * @return
	 */
	public MaintenanceResponse deleteSuspiciousActivity(SarMaintenanceRequest request);

	/**
	 * This method can be used to lookup SuspiciousActivity objects by various criteria.
	 *
	 * @param request
	 * @return
	 */
	public SarResponse fetchSuspiciousActivityByRequest(SarInquiryRequest request);

	/**
	 * This method can be used to lookup SuspiciousActivity by the internal system
	 * identifier or by the business key. If the lookup is by business key, all instances
	 * with the same business key will be returned.
	 *
	 * @param request
	 * @return
	 */
	public SarResponse fetchSuspiciousActivityByIdRequest(FetchByIdRequest request);

	/**
	 * Fetch business suspicious activity by id request.
	 *
	 * @param request the request
	 * @return the sar response
	 */
	public SarResponse fetchBusinessSuspiciousActivityByIdRequest(FetchByIdRequest request);
}
