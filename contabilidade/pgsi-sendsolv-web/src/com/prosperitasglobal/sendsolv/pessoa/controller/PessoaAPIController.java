package com.prosperitasglobal.sendsolv.pessoa.controller;

import java.util.Calendar;
import java.util.logging.Logger;

/**
 * The EmpresaAPIController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */
@Controller
@RequestMapping("/api/pessoa")
public class PessoaAPIController extends PessoaBaseController
{

	/** The Constant FETCH_SIC_NAICS. */
	private static final String FETCH_SIC_NAICS = "fetchSicNaics";
	/** The Constant FETCH_ORGANIZATION_BYLOCATION. */
	private static final String FETCH_ORGANIZATION_BYLOCATION = "fetchOrganizationBylocation";
	/** The URL mapping constants. */
	private static final String DELETE_LOCATION = "/delete";

	/** The Constant EDIT_LOCATION. */
	private static final String EDIT_LOCATION = "/edit";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/fetchall";

	private static final String FETCH_ALL_CIDADE = "/fetchall/cidade";

	/** The Constant INSERT_LOCATION. */
	private static final String INSERT_LOCATION = "/add";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";
	/** The Constant APPLY. */
	private static final String APPLY = "/applyStatus";
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PessoaAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "EmpresaAPIController";

	/**
	 * Fetch all Empresas.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public EmpresaResponse fetchAll(@RequestBody PagedInquiryRequest pagedInquiryRequest)
	{

		return fetchEmpresaByRequest(pagedInquiryRequest);

	}

	@RequestMapping(value = FETCH_ALL_CIDADE, method = RequestMethod.POST)
	@ResponseBody
	public CidadeResponse fetchAll(@RequestBody CidadeInquiryRequest pagedInquiryRequest)
	{

		return fetchCidadeByRequest(pagedInquiryRequest);

	}

	/**
	 * Fetch.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the location response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public EmpresaResponse fetch(@RequestBody FetchByIdRequest fetchByIdRequest)
	{

		return fetchEmpresaById(fetchByIdRequest);

	}

	/**
	 * Edit one location.
	 *
	 * @param locationRequest the location request
	 * @return the response
	 */
	@RequestMapping(value = EDIT_LOCATION, method = RequestMethod.POST)
	@ResponseBody
	public EmpresaResponse edit(@RequestBody EmpresaMaintenanceRequest locationRequest)
	{
		EmpresaResponse locationResponse = new EmpresaResponse();
		try
		{

			locationResponse = getEmpresaBAI().updateEmpresa(locationRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			locationResponse = null;
		}
		return locationResponse;

	}

	/**
	 * Delete one location.
	 *
	 * @param locationRequest the location request
	 * @return the response
	 */
	@RequestMapping(value = DELETE_LOCATION, method = RequestMethod.POST)
	@ResponseBody
	public EmpresaResponse delete(@RequestBody EmpresaMaintenanceRequest locationRequest)
	{
		EmpresaResponse locationResponse = new EmpresaResponse();
		try
		{

			locationResponse = getEmpresaBAI().deleteEmpresa(locationRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			locationResponse = null;
		}

		return locationResponse;

	}

	/**
	 * Insert one location.
	 *
	 * @param locationRequest the location request
	 * @return the response
	 */
	@RequestMapping(value = INSERT_LOCATION, method = RequestMethod.POST)
	@ResponseBody
	public EmpresaResponse insert(@RequestBody EmpresaMaintenanceRequest locationRequest)
	{
		EmpresaResponse locationResponse = new EmpresaResponse();

		try
		{

			locationRequest.getEmpresa().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			locationResponse = getEmpresaBAI().insertEmpresa(locationRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			locationResponse = null;
		}

		return locationResponse;

	}

}
