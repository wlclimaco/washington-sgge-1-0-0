package com.qat.samples.sysmgmt.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.ResponseHandler;
import com.qat.samples.sysmgmt.bac.ICountyBAC;
import com.qat.samples.sysmgmt.controller.model.FileBucket;
import com.qat.samples.sysmgmt.controller.model.UserDocument;
import com.qat.samples.sysmgmt.model.County;
import com.qat.samples.sysmgmt.model.request.CountyMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.CountyResponse;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class CountyAPIController.
 */
@Controller
@RequestMapping("/county/api")
public class CountyAPIController extends BaseController {
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.countycontrollerrest.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CountyAPIController.class);

	/** The county bac. */
	private ICountyBAC countyBAC; // injected by @Resource

	/**
	 * Gets the county bac.
	 *
	 * @return the county bac
	 */
	public ICountyBAC getCountyBAC() {
		return countyBAC;
	}

	/**
	 * Sets the county bac.
	 *
	 * @param countyBAC
	 *            the new county bac
	 */
	@Resource
	public void setCountyBAC(ICountyBAC countyBAC) {
		this.countyBAC = countyBAC;
	}

	// /**
	// * Refresh counties.
	// *
	// * @param refreshInt the refresh int
	// * @param retList the ret list
	// * @param retPaged the ret paged
	// * @return the county response
	// */
	// @RequestMapping(value = "/refresh", method = RequestMethod.GET)
	// @ResponseBody
	// public CountyResponse refreshCounties(@RequestParam("refreshInt") Integer
	// refreshInt,
	// @RequestParam("retList") Boolean retList,
	// @RequestParam("retPaged") Boolean retPaged)
	// {
	// CountyResponse countyResponse = new CountyResponse();
	//
	// try
	// {
	// RefreshRequest request = new RefreshRequest(refreshInt, retList,
	// retPaged);
	// InternalResultsResponse<County> internalResponse =
	// getCountyBAC().refreshCounties(request);
	// ResponseHandler.handleOperationStatusAndMessages(countyResponse,
	// internalResponse, true);
	// }
	// catch (Exception ex)
	// {
	// ResponseHandler.handleException(LOG, countyResponse, ex,
	// DEFAULT_EXCEPTION_MSG,
	// new Object[] {ex.toString()});
	// }
	// return countyResponse;
	//
	// }

	/**
	 * Fetch counties paged.
	 *
	 * @param request
	 *            the request
	 * @return the county response
	 */
	@RequestMapping(value = "/fetchPage", method = RequestMethod.POST)
	@ResponseBody
	public CountyResponse fetchCountiesPaged(@RequestBody PagedInquiryRequest request) {
		CountyResponse countyResponse = new CountyResponse();
		try {
			InternalResultsResponse<County> internalResponse = getCountyBAC().fetchCountiesByRequest(request);
			ResponseHandler.handleOperationStatusAndMessages(countyResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, countyResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return countyResponse;
	}

	@RequestMapping(value = "/add-document", method = RequestMethod.POST)
	public String uploadDocument(FileBucket fileBucket, BindingResult result, ModelMap model, @PathVariable int userId)
			throws IOException {

		if (result.hasErrors()) {
			System.out.println("validation errors");

			return "managedocuments";
		} else {
			User user = new User(null, null, null);
			System.out.println("Fetching file");

			model.addAttribute("user", user);

			saveDocument(fileBucket, user);

			return "redirect:/add-document-" + userId;
		}
	}

	private void saveDocument(FileBucket fileBucket, User user) throws IOException {

		UserDocument document = new UserDocument();

		MultipartFile multipartFile = fileBucket.getFile();

		document.setName(multipartFile.getOriginalFilename());
		document.setDescription(fileBucket.getDescription());
		document.setType(multipartFile.getContentType());
		document.setContent(multipartFile.getBytes());
		document.setUser(user);
	}

	/**
	 * Insert county.
	 *
	 * @param request
	 *            the request
	 * @return the county response
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public CountyResponse insertCounty(@RequestBody CountyMaintenanceRequest request) {
		CountyResponse countyResponse = new CountyResponse();
		try {
			InternalResultsResponse<County> internalResponse = getCountyBAC().insertCounty(request);
			ResponseHandler.handleOperationStatusAndMessages(countyResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, countyResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return countyResponse;
	}

	/**
	 * Update county.
	 *
	 * @param request
	 *            the request
	 * @return the county response
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public CountyResponse updateCounty(@RequestBody CountyMaintenanceRequest request) {
		CountyResponse countyResponse = new CountyResponse();
		try {
			InternalResultsResponse<County> internalResponse = getCountyBAC().updateCounty(request);
			ResponseHandler.handleOperationStatusAndMessages(countyResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, countyResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return countyResponse;
	}

	/**
	 * Delete county.
	 *
	 * @param countyId
	 *            the county id
	 * @param retList
	 *            the ret list
	 * @param retPaged
	 *            the ret paged
	 * @return the county response
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public CountyResponse deleteCounty(@RequestParam("countyId") Integer countyId,
			@RequestParam("retList") Boolean retList, @RequestParam("retPaged") Boolean retPaged) {
		CountyResponse countyResponse = new CountyResponse();

		try {
			CountyMaintenanceRequest request = new CountyMaintenanceRequest(new County(countyId, null), retList,
					retPaged);
			InternalResultsResponse<County> internalResponse = getCountyBAC().deleteCounty(request);
			ResponseHandler.handleOperationStatusAndMessages(countyResponse, internalResponse, true);
		} catch (Exception ex) {
			ResponseHandler.handleException(LOG, countyResponse, ex, DEFAULT_EXCEPTION_MSG,
					new Object[] { ex.toString() });
		}
		return countyResponse;

	}
}
