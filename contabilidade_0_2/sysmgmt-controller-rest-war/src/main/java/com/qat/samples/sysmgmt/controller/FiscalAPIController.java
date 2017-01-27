/** create by system gera-java version 1.0.0 09/05/2016 16:51 : 47*/

package com.qat.samples.sysmgmt.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.Fiscal.IFiscalBAC;
import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.cfop.model.request.CfopMaintenanceRequest;
import com.qat.samples.sysmgmt.cnae.model.Cnae;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeInquiryRequest;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeMaintenanceRequest;
import com.qat.samples.sysmgmt.cnae.model.response.CnaeResponse;
import com.qat.samples.sysmgmt.fiscal.model.Regime;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeMaintenanceRequest;
import com.qat.samples.sysmgmt.fiscal.model.response.RegimeResponse;
import com.qat.samples.sysmgmt.produto.model.response.CfopResponse;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Class FiscalAPIController.
 */
@Controller
@RequestMapping("/fiscal/api")
public class FiscalAPIController extends BaseController {
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.fiscalcontrollerrest.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FiscalAPIController.class);

	/** The fiscal bac. */
	private IFiscalBAC fiscalBAC; // injected by @Resource

	/**
	 * Gets the fiscal bac.
	 *
	 * @return the fiscal bac
	 */
	public IFiscalBAC getFiscalBAC() {
		return fiscalBAC;
	}

	/**
	 * Sets the fiscal bac.
	 *
	 * @param fiscalBAC
	 *            the new fiscal bac
	 */
	@Resource
	public void setFiscalBAC(IFiscalBAC fiscalBAC) {
		this.fiscalBAC = fiscalBAC;
	}

	// ===================================### REGIME
	// ####======================================
	/**
	 * Refresh regimes.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the regime response
	 */
	@RequestMapping(value = "/regime/refresh", method = RequestMethod.GET)
	@ResponseBody
	public RegimeResponse refreshRegimes(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		RegimeResponse regimeResponse = new RegimeResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Regime> internalResponse = getFiscalBAC().refreshRegimes(request);
			ResponseHandler.handleOperationStatusAndMessages(regimeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, regimeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return regimeResponse;

	}

	/**
	 * Fetch regime paged.
	 *
	 * @param request
	 *            the request
	 * @return the regime response
	 */
	@RequestMapping(value = "/regime/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public RegimeResponse fetchRegimePaged(@RequestBody RegimeInquiryRequest request) {
		RegimeResponse regimeResponse = new RegimeResponse();
		try {
			InternalResultsResponse<Regime> internalResponse = getFiscalBAC().fetchRegimesByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(regimeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, regimeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return regimeResponse;
	}

	/**
	 * Insert regime.
	 *
	 * @param request
	 *            the request
	 * @return the regime response
	 */
	@RequestMapping(value = "/regime/insert", method = RequestMethod.POST)
	@ResponseBody
	public RegimeResponse insertRegime(@RequestBody RegimeMaintenanceRequest request) {
		RegimeResponse regimeResponse = new RegimeResponse();
		try {
			InternalResultsResponse<Regime> internalResponse = getFiscalBAC().insertRegime(request);
			ResponseHandler.handleOperationStatusAndMessages(regimeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, regimeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return regimeResponse;
	}

	/**
	 * Update regime.
	 *
	 * @param request
	 *            the request
	 * @return the regime response
	 */
	@RequestMapping(value = "/regime/update", method = RequestMethod.POST)
	@ResponseBody
	public RegimeResponse updateRegime(@RequestBody RegimeMaintenanceRequest request) {
		RegimeResponse regimeResponse = new RegimeResponse();
		try {
			InternalResultsResponse<Regime> internalResponse = getFiscalBAC().updateRegime(request);
			ResponseHandler.handleOperationStatusAndMessages(regimeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, regimeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return regimeResponse;
	}

	/**
	 * Delete regime.
	 *
	 * @param request
	 *            the request
	 * @return the regime response
	 */
	@RequestMapping(value = "/regime/delete", method = RequestMethod.POST)
	@ResponseBody
	public RegimeResponse deleteRegime(@RequestBody RegimeMaintenanceRequest request) {
		RegimeResponse regimeResponse = new RegimeResponse();

		try {
			InternalResultsResponse<Regime> internalResponse = getFiscalBAC().deleteRegime(request);
			ResponseHandler.handleOperationStatusAndMessages(regimeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, regimeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return regimeResponse;

	}

	// ===================================### CFOP
	// ####======================================
	/**
	 * Refresh cfops.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the cfop response
	 */
	@RequestMapping(value = "/cfop/refresh", method = RequestMethod.GET)
	@ResponseBody
	public CfopResponse refreshCfops(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		CfopResponse cfopResponse = new CfopResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Cfop> internalResponse = getFiscalBAC().refreshCfops(request);
			ResponseHandler.handleOperationStatusAndMessages(cfopResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, cfopResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return cfopResponse;

	}

	/**
	 * Fetch cfop paged.
	 *
	 * @param request
	 *            the request
	 * @return the cfop response
	 */
	@RequestMapping(value = "/cfop/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public CfopResponse fetchCfopPaged(@RequestBody CfopInquiryRequest request) {
		CfopResponse cfopResponse = new CfopResponse();
		try {
			InternalResultsResponse<Cfop> internalResponse = getFiscalBAC().fetchCfopsByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(cfopResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, cfopResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return cfopResponse;
	}

	/**
	 * Insert cfop.
	 *
	 * @param request
	 *            the request
	 * @return the cfop response
	 */
	@RequestMapping(value = "/cfop/insert", method = RequestMethod.POST)
	@ResponseBody
	public CfopResponse insertCfop(@RequestBody CfopMaintenanceRequest request) {
		CfopResponse cfopResponse = new CfopResponse();
		try {
			InternalResultsResponse<Cfop> internalResponse = getFiscalBAC().insertCfop(request);
			ResponseHandler.handleOperationStatusAndMessages(cfopResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, cfopResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return cfopResponse;
	}

	/**
	 * Update cfop.
	 *
	 * @param request
	 *            the request
	 * @return the cfop response
	 */
	@RequestMapping(value = "/cfop/update", method = RequestMethod.POST)
	@ResponseBody
	public CfopResponse updateCfop(@RequestBody CfopMaintenanceRequest request) {
		CfopResponse cfopResponse = new CfopResponse();
		try {
			InternalResultsResponse<Cfop> internalResponse = getFiscalBAC().updateCfop(request);
			ResponseHandler.handleOperationStatusAndMessages(cfopResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, cfopResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return cfopResponse;
	}

	/**
	 * Delete cfop.
	 *
	 * @param request
	 *            the request
	 * @return the cfop response
	 */
	@RequestMapping(value = "/cfop/delete", method = RequestMethod.POST)
	@ResponseBody
	public CfopResponse deleteCfop(@RequestBody CfopMaintenanceRequest request) {
		CfopResponse cfopResponse = new CfopResponse();

		try {
			InternalResultsResponse<Cfop> internalResponse = getFiscalBAC().deleteCfop(request);
			ResponseHandler.handleOperationStatusAndMessages(cfopResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, cfopResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return cfopResponse;

	}

	// ===================================### CNAE
	// ####======================================
	/**
	 * Refresh cnaes.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the cnae response
	 */
	@RequestMapping(value = "/cnae/refresh", method = RequestMethod.GET)
	@ResponseBody
	public CnaeResponse refreshCnaes(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		CnaeResponse cnaeResponse = new CnaeResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Cnae> internalResponse = getFiscalBAC().refreshCnaes(request);
			ResponseHandler.handleOperationStatusAndMessages(cnaeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, cnaeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return cnaeResponse;

	}

	/**
	 * Fetch cnae paged.
	 *
	 * @param request
	 *            the request
	 * @return the cnae response
	 */
	@RequestMapping(value = "/cnae/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public CnaeResponse fetchCnaePaged(@RequestBody CnaeInquiryRequest request) {
		CnaeResponse cnaeResponse = new CnaeResponse();
		try {
			InternalResultsResponse<Cnae> internalResponse = getFiscalBAC().fetchCnaesByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(cnaeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, cnaeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return cnaeResponse;
	}

	/**
	 * Insert cnae.
	 *
	 * @param request
	 *            the request
	 * @return the cnae response
	 */
	@RequestMapping(value = "/cnae/insert", method = RequestMethod.POST)
	@ResponseBody
	public CnaeResponse insertCnae(@RequestBody CnaeMaintenanceRequest request) {
		CnaeResponse cnaeResponse = new CnaeResponse();
		try {
			InternalResultsResponse<Cnae> internalResponse = getFiscalBAC().insertCnae(request);
			ResponseHandler.handleOperationStatusAndMessages(cnaeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, cnaeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return cnaeResponse;
	}

	/**
	 * Update cnae.
	 *
	 * @param request
	 *            the request
	 * @return the cnae response
	 */
	@RequestMapping(value = "/cnae/update", method = RequestMethod.POST)
	@ResponseBody
	public CnaeResponse updateCnae(@RequestBody CnaeMaintenanceRequest request) {
		CnaeResponse cnaeResponse = new CnaeResponse();
		try {
			InternalResultsResponse<Cnae> internalResponse = getFiscalBAC().updateCnae(request);
			ResponseHandler.handleOperationStatusAndMessages(cnaeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, cnaeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return cnaeResponse;
	}

	/**
	 * Delete cnae.
	 *
	 * @param request
	 *            the request
	 * @return the cnae response
	 */
	@RequestMapping(value = "/cnae/delete", method = RequestMethod.POST)
	@ResponseBody
	public CnaeResponse deleteCnae(@RequestBody CnaeMaintenanceRequest request) {
		CnaeResponse cnaeResponse = new CnaeResponse();

		try {
			InternalResultsResponse<Cnae> internalResponse = getFiscalBAC().deleteCnae(request);
			ResponseHandler.handleOperationStatusAndMessages(cnaeResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, cnaeResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return cnaeResponse;

	}

	// // ===================================### CNAEEMPRESA
	// // ####======================================
	// /**
	// * Refresh cnaeempresas.
	// *
	// * @param refreshInt
	// * the refresh int
	// * @param retList
	// * the ret list
	// * @param retPaged
	// * the ret paged
	// * @return the cnaeempresa response
	// */
	// @RequestMapping(value = "/refresh", method = RequestMethod.GET)
	// @ResponseBody
	// public CnaeEmpresaResponse
	// refreshCnaeEmpresas(@RequestParam("refreshInt") Integer refreshInt,
	// @RequestParam("retList") Boolean retList, @RequestParam("retPaged")
	// Boolean retPaged) {
	// CnaeEmpresaResponse cnaeempresaResponse = new CnaeEmpresaResponse();
	//
	// try {
	// RefreshRequest request = new RefreshRequest(refreshInt, retList,
	// retPaged);
	// InternalResultsResponse<CnaeEmpresa> internalResponse =
	// getFiscalBAC().refreshCnaeEmpresas(request);
	// ResponseHandler.handleOperationStatusAndMessages(cnaeempresaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, cnaeempresaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return cnaeempresaResponse;
	//
	// }
	//
	// /**
	// * Fetch cnaeempresa paged.
	// *
	// * @param request
	// * the request
	// * @return the cnaeempresa response
	// */
	// @RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	// @ResponseBody
	// public CnaeEmpresaResponse fetchCnaeEmpresaPaged(@RequestBody
	// CnaeEmpresaInquiryRequest request) {
	// CnaeEmpresaResponse cnaeempresaResponse = new CnaeEmpresaResponse();
	// try {
	// InternalResultsResponse<CnaeEmpresa> internalResponse =
	// getFiscalBAC().fetchCnaeEmpresasByRequest(request);
	// ResponseHandler.handleOperationStatusAndMessages(cnaeempresaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, cnaeempresaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return cnaeempresaResponse;
	// }
	//
	// /**
	// * Insert cnaeempresa.
	// *
	// * @param request
	// * the request
	// * @return the cnaeempresa response
	// */
	// @RequestMapping(value = "/insert", method = RequestMethod.POST)
	// @ResponseBody
	// public CnaeEmpresaResponse insertCnaeEmpresa(@RequestBody
	// CnaeEmpresaMaintenanceRequest request) {
	// CnaeEmpresaResponse cnaeempresaResponse = new CnaeEmpresaResponse();
	// try {
	// InternalResultsResponse<CnaeEmpresa> internalResponse =
	// getFiscalBAC().insertCnaeEmpresa(request);
	// ResponseHandler.handleOperationStatusAndMessages(cnaeempresaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, cnaeempresaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return cnaeempresaResponse;
	// }
	//
	// /**
	// * Update cnaeempresa.
	// *
	// * @param request
	// * the request
	// * @return the cnaeempresa response
	// */
	// @RequestMapping(value = "/update", method = RequestMethod.POST)
	// @ResponseBody
	// public CnaeEmpresaResponse updateCnaeEmpresa(@RequestBody
	// CnaeEmpresaMaintenanceRequest request) {
	// CnaeEmpresaResponse cnaeempresaResponse = new CnaeEmpresaResponse();
	// try {
	// InternalResultsResponse<CnaeEmpresa> internalResponse =
	// getFiscalBAC().updateCnaeEmpresa(request);
	// ResponseHandler.handleOperationStatusAndMessages(cnaeempresaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, cnaeempresaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return cnaeempresaResponse;
	// }
	//
	// /**
	// * Delete cnaeempresa.
	// *
	// * @param request
	// * the request
	// * @return the cnaeempresa response
	// */
	// @RequestMapping(value = "/delete", method = RequestMethod.POST)
	// @ResponseBody
	// public CnaeEmpresaResponse deleteCnaeEmpresa(@RequestBody
	// CnaeEmpresaMaintenanceRequest request) {
	// CnaeEmpresaResponse cnaeempresaResponse = new CnaeEmpresaResponse();
	//
	// try {
	// InternalResultsResponse<CnaeEmpresa> internalResponse =
	// getFiscalBAC().deleteCnaeEmpresa(request);
	// ResponseHandler.handleOperationStatusAndMessages(cnaeempresaResponse,
	// internalResponse, true);
	// } catch (Exception ex) {
	// ResponseHandler.handleException(LOG, cnaeempresaResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] { ex.toString() });
	// }
	// return cnaeempresaResponse;
	//
	// }

}
