package com.prosperitasglobal.sendsolv.arquivo.controller;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.ArquivoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.ArquivoResponse;

/**
 * The ArquivoAPIController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */
@Controller
@RequestMapping("/api/arquivo")
public class ArquivoAPIController extends ArquivoBaseController
{

	/** The Constant FETCH_SIC_NAICS. */
	private static final String FETCH_SIC_NAICS = "fetchSicNaics";
	/** The Constant FETCH_ORGANIZATION_BYLOCATION. */
	private static final String FETCH_ORGANIZATION_BYLOCATION = "fetchOrganizationByarquivo";
	/** The URL mapping constants. */
	private static final String DELETE_LOCATION = "/delete";

	/** The Constant EDIT_LOCATION. */
	private static final String EDIT_LOCATION = "/edit";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/fetchall";

	/** The Constant INSERT_LOCATION. */
	private static final String INSERT_LOCATION = "/add";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";
	/** The Constant APPLY. */
	private static final String APPLY = "/applyStatus";
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ArquivoAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "ArquivoAPIController";

	//
	// public Future<CustomizationResponse> loadFilters(DeviceCategoryEnum deviceCategoryEnum,
	// InquiryPaginationRequest inquiryPaginationRequest, AreaTypeEnum areaTypeEnum)
	// {
	// CustomizationRequest customSearchRequest =
	// new CustomizationRequest(new Customization(CustomizationTypeEnum.FILTER, deviceCategoryEnum));
	//
	// if (!ValidationUtil.isNull(areaTypeEnum))
	// {
	// customSearchRequest.getCustomization().setAreaType(areaTypeEnum);
	// }
	// addUserContextToRequest(customSearchRequest, inquiryPaginationRequest);
	//
	// return getAsyncDMFacade().<CustomizationResponse> callAsyncMethod(getCustomSearchBCF(), "fetchAllFilters",
	// customSearchRequest, null);
	//
	// }

	/**
	 * Fetch all Arquivos.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public ArquivoResponse fetchAll(@RequestBody PagedInquiryRequest pagedInquiryRequest)
	{

		return fetchArquivoByRequest(pagedInquiryRequest);

	}

	/**
	 * Fetch.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the arquivo response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public ArquivoResponse fetch(@RequestBody FetchByIdRequest fetchByIdRequest)
	{

		return fetchArquivoById(fetchByIdRequest);

	}

	/**
	 * Edit one arquivo.
	 *
	 * @param arquivoRequest the arquivo request
	 * @return the response
	 */
	@RequestMapping(value = EDIT_LOCATION, method = RequestMethod.POST)
	@ResponseBody
	public ArquivoResponse edit(@RequestBody ArquivoMaintenanceRequest arquivoRequest)
	{
		ArquivoResponse arquivoResponse = new ArquivoResponse();
		try
		{

			arquivoResponse = getArquivoBAI().updateArquivo(arquivoRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			arquivoResponse = null;
		}
		return arquivoResponse;

	}

	/**
	 * Delete one arquivo.
	 *
	 * @param arquivoRequest the arquivo request
	 * @return the response
	 */
	@RequestMapping(value = DELETE_LOCATION, method = RequestMethod.POST)
	@ResponseBody
	public ArquivoResponse delete(@RequestBody ArquivoMaintenanceRequest arquivoRequest)
	{
		ArquivoResponse arquivoResponse = new ArquivoResponse();
		try
		{

			arquivoResponse = getArquivoBAI().deleteArquivo(arquivoRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			arquivoResponse = null;
		}

		return arquivoResponse;

	}

	/**
	 * Insert one arquivo.
	 *
	 * @param arquivoRequest the arquivo request
	 * @return the response
	 */
	@RequestMapping(value = INSERT_LOCATION, method = RequestMethod.POST)
	@ResponseBody
	public ArquivoResponse insert(@RequestBody ArquivoMaintenanceRequest arquivoRequest)
	{
		ArquivoResponse arquivoResponse = new ArquivoResponse();

		try
		{

			arquivoRequest.getArquivo().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			// arquivoResponse = getArquivoBAI().insertArquivo(arquivoRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			arquivoResponse = null;
		}

		return arquivoResponse;

	}
}
