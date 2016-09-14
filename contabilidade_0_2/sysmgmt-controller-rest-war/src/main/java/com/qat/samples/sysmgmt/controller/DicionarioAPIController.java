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
import com.qat.samples.sysmgmt.bac.Dicionario.IDicionarioBAC;
import com.qat.samples.sysmgmt.dicionario.Classes;
import com.qat.samples.sysmgmt.dicionario.Interface;
import com.qat.samples.sysmgmt.dicionario.request.ClassesInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.ClassesMaintenanceRequest;
import com.qat.samples.sysmgmt.dicionario.request.FieldInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.InterfaceInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.InterfaceMaintenanceRequest;
import com.qat.samples.sysmgmt.dicionario.response.ClassesResponse;
import com.qat.samples.sysmgmt.dicionario.response.FieldResponse;
import com.qat.samples.sysmgmt.dicionario.response.InterfaceResponse;
import com.qat.samples.sysmgmt.entidade.model.Field;
import com.qat.samples.sysmgmt.util.model.request.FieldMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Class DicionarioAPIController.
 */
@Controller
@RequestMapping("/dicionario/api")
public class DicionarioAPIController extends BaseController {
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.dicionariocontrollerrest.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DicionarioAPIController.class);

	/** The dicionario bac. */
	private IDicionarioBAC dicionarioBAC; // injected by @Resource

	/**
	 * Gets the dicionario bac.
	 *
	 * @return the dicionario bac
	 */
	public IDicionarioBAC getDicionarioBAC() {
		return dicionarioBAC;
	}

	/**
	 * Sets the dicionario bac.
	 *
	 * @param dicionarioBAC
	 *            the new dicionario bac
	 */
	@Resource
	public void setDicionarioBAC(IDicionarioBAC dicionarioBAC) {
		this.dicionarioBAC = dicionarioBAC;
	}

	// ===================================### CLASSES
	// ####======================================
	/**
	 * Refresh classess.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the classes response
	 */
	@RequestMapping(value = "/classes/refresh", method = RequestMethod.GET)
	@ResponseBody
	public ClassesResponse refreshClassess(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		ClassesResponse classesResponse = new ClassesResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Classes> internalResponse = getDicionarioBAC().refreshClassess(request);
			ResponseHandler.handleOperationStatusAndMessages(classesResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, classesResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return classesResponse;

	}

	/**
	 * Fetch classes paged.
	 *
	 * @param request
	 *            the request
	 * @return the classes response
	 */
	@RequestMapping(value = "/classes/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public ClassesResponse fetchClassesPaged(@RequestBody ClassesInquiryRequest request) {
		ClassesResponse classesResponse = new ClassesResponse();
		try {
			InternalResultsResponse<Classes> internalResponse = getDicionarioBAC().fetchClassessByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(classesResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, classesResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return classesResponse;
	}

	/**
	 * Insert classes.
	 *
	 * @param request
	 *            the request
	 * @return the classes response
	 */
	@RequestMapping(value = "/classes/insert", method = RequestMethod.POST)
	@ResponseBody
	public ClassesResponse insertClasses(@RequestBody ClassesMaintenanceRequest request) {
		ClassesResponse classesResponse = new ClassesResponse();
		try {
			InternalResultsResponse<Classes> internalResponse = getDicionarioBAC().insertClasses(request);
			ResponseHandler.handleOperationStatusAndMessages(classesResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, classesResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return classesResponse;
	}

	/**
	 * Update classes.
	 *
	 * @param request
	 *            the request
	 * @return the classes response
	 */
	@RequestMapping(value = "/classes/update", method = RequestMethod.POST)
	@ResponseBody
	public ClassesResponse updateClasses(@RequestBody ClassesMaintenanceRequest request) {
		ClassesResponse classesResponse = new ClassesResponse();
		try {
			InternalResultsResponse<Classes> internalResponse = getDicionarioBAC().updateClasses(request);
			ResponseHandler.handleOperationStatusAndMessages(classesResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, classesResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return classesResponse;
	}

	/**
	 * Delete classes.
	 *
	 * @param request
	 *            the request
	 * @return the classes response
	 */
	@RequestMapping(value = "/classes/delete", method = RequestMethod.POST)
	@ResponseBody
	public ClassesResponse deleteClasses(@RequestBody ClassesMaintenanceRequest request) {
		ClassesResponse classesResponse = new ClassesResponse();

		try {
			InternalResultsResponse<Classes> internalResponse = getDicionarioBAC().deleteClasses(request);
			ResponseHandler.handleOperationStatusAndMessages(classesResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, classesResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return classesResponse;

	}

	// ===================================### INTERFACE
	// ####======================================
	/**
	 * Refresh interfaces.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the interface response
	 */
	@RequestMapping(value = "/interface/refresh", method = RequestMethod.GET)
	@ResponseBody
	public InterfaceResponse refreshInterfaces(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		InterfaceResponse interfaceResponse = new InterfaceResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Interface> internalResponse = getDicionarioBAC().refreshInterfaces(request);
			ResponseHandler.handleOperationStatusAndMessages(interfaceResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, interfaceResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return interfaceResponse;

	}

	/**
	 * Fetch interface paged.
	 *
	 * @param request
	 *            the request
	 * @return the interface response
	 */
	@RequestMapping(value = "/interface/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public InterfaceResponse fetchInterfacePaged(@RequestBody InterfaceInquiryRequest request) {
		InterfaceResponse interfaceResponse = new InterfaceResponse();
		try {
			InternalResultsResponse<Interface> internalResponse = getDicionarioBAC().fetchInterfacesByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(interfaceResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, interfaceResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return interfaceResponse;
	}

	/**
	 * Insert interface.
	 *
	 * @param request
	 *            the request
	 * @return the interface response
	 */
	@RequestMapping(value = "/interface/insert", method = RequestMethod.POST)
	@ResponseBody
	public InterfaceResponse insertInterface(@RequestBody InterfaceMaintenanceRequest request) {
		InterfaceResponse interfaceResponse = new InterfaceResponse();
		try {
			InternalResultsResponse<Interface> internalResponse = getDicionarioBAC().insertInterface(request);
			ResponseHandler.handleOperationStatusAndMessages(interfaceResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, interfaceResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return interfaceResponse;
	}

	/**
	 * Update interface.
	 *
	 * @param request
	 *            the request
	 * @return the interface response
	 */
	@RequestMapping(value = "/interface/update", method = RequestMethod.POST)
	@ResponseBody
	public InterfaceResponse updateInterface(@RequestBody InterfaceMaintenanceRequest request) {
		InterfaceResponse interfaceResponse = new InterfaceResponse();
		try {
			InternalResultsResponse<Interface> internalResponse = getDicionarioBAC().updateInterface(request);
			ResponseHandler.handleOperationStatusAndMessages(interfaceResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, interfaceResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return interfaceResponse;
	}

	/**
	 * Delete interface.
	 *
	 * @param request
	 *            the request
	 * @return the interface response
	 */
	@RequestMapping(value = "/interface/delete", method = RequestMethod.POST)
	@ResponseBody
	public InterfaceResponse deleteInterface(@RequestBody InterfaceMaintenanceRequest request) {
		InterfaceResponse interfaceResponse = new InterfaceResponse();

		try {
			InternalResultsResponse<Interface> internalResponse = getDicionarioBAC().deleteInterface(request);
			ResponseHandler.handleOperationStatusAndMessages(interfaceResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, interfaceResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return interfaceResponse;

	}

	// ===================================### FIELD
	// ####======================================
	/**
	 * Refresh fields.
	 *
	 * @param refreshInt
	 *            the refresh int
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the field response
	 */
	@RequestMapping(value = "/field/refresh", method = RequestMethod.GET)
	@ResponseBody
	public FieldResponse refreshFields(@RequestParam("refreshInt") Integer refreshInt,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		FieldResponse fieldResponse = new FieldResponse();

		try {
			RefreshRequest request = new RefreshRequest(refreshInt, retList, retPaged);
			InternalResultsResponse<Field> internalResponse = getDicionarioBAC().refreshFields(request);
			ResponseHandler.handleOperationStatusAndMessages(fieldResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, fieldResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return fieldResponse;

	}

	/**
	 * Fetch field paged.
	 *
	 * @param request
	 *            the request
	 * @return the field response
	 */
	@RequestMapping(value = "/field/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public FieldResponse fetchFieldPaged(@RequestBody FieldInquiryRequest request) {
		FieldResponse fieldResponse = new FieldResponse();
		try {
			InternalResultsResponse<Field> internalResponse = getDicionarioBAC().fetchFieldsByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(fieldResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, fieldResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return fieldResponse;
	}

	/**
	 * Insert field.
	 *
	 * @param request
	 *            the request
	 * @return the field response
	 */
	@RequestMapping(value = "/field/insert", method = RequestMethod.POST)
	@ResponseBody
	public FieldResponse insertField(@RequestBody FieldMaintenanceRequest request) {
		FieldResponse fieldResponse = new FieldResponse();
		try {
			InternalResultsResponse<Field> internalResponse = getDicionarioBAC().insertField(request);
			ResponseHandler.handleOperationStatusAndMessages(fieldResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, fieldResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return fieldResponse;
	}

	/**
	 * Update field.
	 *
	 * @param request
	 *            the request
	 * @return the field response
	 */
	@RequestMapping(value = "/field/update", method = RequestMethod.POST)
	@ResponseBody
	public FieldResponse updateField(@RequestBody FieldMaintenanceRequest request) {
		FieldResponse fieldResponse = new FieldResponse();
		try {
			InternalResultsResponse<Field> internalResponse = getDicionarioBAC().updateField(request);
			ResponseHandler.handleOperationStatusAndMessages(fieldResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, fieldResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return fieldResponse;
	}

	/**
	 * Delete field.
	 *
	 * @param request
	 *            the request
	 * @return the field response
	 */
	@RequestMapping(value = "/field/delete", method = RequestMethod.POST)
	@ResponseBody
	public FieldResponse deleteField(@RequestBody FieldMaintenanceRequest request) {
		FieldResponse fieldResponse = new FieldResponse();

		try {
			InternalResultsResponse<Field> internalResponse = getDicionarioBAC().deleteField(request);
			ResponseHandler.handleOperationStatusAndMessages(fieldResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, fieldResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return fieldResponse;

	}

}
