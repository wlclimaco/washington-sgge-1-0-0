package com.sensus.lc.api.service.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sensus.common.model.UserContext;
import com.sensus.common.util.SensusMessageUtil;
import com.sensus.lc.api.service.model.APIException;
import com.sensus.lc.light.bcf.ILightBCF;
import com.sensus.lc.settings.bcf.ISettingsBCF;
import com.sensus.lc.tenant.bcf.ITenantBCF;
import com.sensus.lc.tenant.model.request.TenantRequest;
import com.sensus.lc.tenant.model.response.ApiControlResponse;

/**
 * The Class BaseAPIController.
 */
public class BaseAPIController
{

	/** The Constant SENSUS_LC_APIVALIDATOR_ACCESS_EXCEEDED. */
	private static final String SENSUS_LC_APIVALIDATOR_ACCESS_EXCEEDED = "sensus.lc.apivalidator.access.exceeded";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	public static final String DEFAULT_EXCEPTION_MSG = "sensus.mlc.defaultexception";

	/** The light accessor bcf. */
	private ILightBCF lightBCF;

	/** The tenant bcf. */
	private ITenantBCF tenantBCF;

	/** Settings BCF. */
	private ISettingsBCF settingsBCF;

	/**
	 * Gets the light bcf.
	 * 
	 * @return the light bcf
	 */
	public ILightBCF getLightBCF()
	{
		return lightBCF;
	}

	/**
	 * Sets the light bcf.
	 * 
	 * @param lightBCF the new light bcf
	 */
	@Resource
	public void setLightBCF(ILightBCF lightBCF)
	{
		this.lightBCF = lightBCF;
	}

	/**
	 * Gets the tenant bcf.
	 * 
	 * @return the tenant bcf
	 */
	public ITenantBCF getTenantBCF()
	{
		return tenantBCF;
	}

	/**
	 * Sets the tenant bcf.
	 * 
	 * @param tenantBCF the new tenant bcf
	 */
	@Resource
	public void setTenantBCF(ITenantBCF tenantBCF)
	{
		this.tenantBCF = tenantBCF;
	}

	/**
	 * Gets the settings bcf.
	 * 
	 * @return the settings bcf
	 */
	public ISettingsBCF getSettingsBCF()
	{
		return settingsBCF;
	}

	/**
	 * Sets the settings bcf.
	 * 
	 * @param settingsBCF the new settings bcf
	 */
	@Resource
	public void setSettingsBCF(ISettingsBCF settingsBCF)
	{
		this.settingsBCF = settingsBCF;
	}

	/**
	 * Verify access control.
	 * 
	 * @param request the request
	 * @throws APIException
	 */
	protected UserContext verifyAccessControl(HttpServletRequest request) throws APIException
	{
		TenantRequest tenantRequest = new TenantRequest();
		tenantRequest.setServerName(request.getServerName());

		// Fetch tenant
		tenantRequest.setTenant(getSettingsBCF().fetchTenantByServerName(tenantRequest).getTenant());

		// Verify access
		ApiControlResponse apiControlResponse = getTenantBCF().verifyAPIAccess(tenantRequest);
		if (!apiControlResponse.getAllowAPIAccess())
		{
			throw new APIException(SensusMessageUtil.getMessage(SENSUS_LC_APIVALIDATOR_ACCESS_EXCEEDED),
					HttpStatus.TOO_MANY_REQUESTS);
		}

		UserContext userContext = new UserContext();
		userContext.setTenant(tenantRequest.getTenant());
		userContext.setUserId(request.getUserPrincipal().getName());

		return userContext;
	}

	/**
	 * Handle default exception.
	 * 
	 * @param log the log
	 * @param e the exception
	 * @param controllerExceptionMsg the controller exception message
	 * @throws APIException the aPI exception
	 */
	public void handleDefaultException(Logger log, Exception e, String controllerExceptionMsg) throws APIException
	{
		if (log.isErrorEnabled())
		{
			log.error(DEFAULT_EXCEPTION_MSG + " " + controllerExceptionMsg);
		}
		throw new APIException(SensusMessageUtil.getMessage(DEFAULT_EXCEPTION_MSG), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handle
	 * 
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler({APIException.class})
	public ResponseEntity<String> handleApiException(APIException ex)
	{
		return new ResponseEntity<String>(ex.getMessage(), ex.getHttpStatus());
	}

}
