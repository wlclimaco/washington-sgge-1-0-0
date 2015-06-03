package com.prosperitasglobal.sendsolv.cliente.controller;

import java.util.Calendar;
import java.util.logging.Logger;

/**
 * The ClienteAPIController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */
@Controller
@RequestMapping("/api/cliente")
public class ClienteAPIController extends ClienteBaseController
{

	/** The Constant FETCH_SIC_NAICS. */
	private static final String FETCH_SIC_NAICS = "fetchSicNaics";
	/** The Constant FETCH_ORGANIZATION_BYCLIENTE. */
	private static final String FETCH_ORGANIZATION_BYCLIENTE = "fetchOrganizationBycliente";
	/** The URL mapping constants. */
	private static final String DELETE_CLIENTE = "/delete";

	/** The Constant EDIT_CLIENTE. */
	private static final String EDIT_CLIENTE = "/edit";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/fetchall";

	/** The Constant INSERT_CLIENTE. */
	private static final String INSERT_CLIENTE = "/add";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";
	/** The Constant APPLY. */
	private static final String APPLY = "/applyStatus";
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ClienteAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "ClienteAPIController";

	/**
	 * Fetch all Clientes.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public ClienteResponse fetchAll(@RequestBody PagedInquiryRequest pagedInquiryRequest)
	{

		return fetchClienteByRequest(pagedInquiryRequest);

	}

	/**
	 * Fetch.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the cliente response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public ClienteResponse fetch(@RequestBody FetchByIdRequest fetchByIdRequest)
	{

		return fetchClienteById(fetchByIdRequest);

	}

	/**
	 * Edit one cliente.
	 *
	 * @param clienteRequest the cliente request
	 * @return the response
	 */
	@RequestMapping(value = EDIT_CLIENTE, method = RequestMethod.POST)
	@ResponseBody
	public ClienteResponse edit(@RequestBody ClienteMaintenanceRequest clienteRequest)
	{
		ClienteResponse clienteResponse = new ClienteResponse();
		try
		{

			clienteResponse = getClienteBAI().updateCliente(clienteRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			clienteResponse = null;
		}
		return clienteResponse;

	}

	/**
	 * Delete one cliente.
	 *
	 * @param clienteRequest the cliente request
	 * @return the response
	 */
	@RequestMapping(value = DELETE_CLIENTE, method = RequestMethod.POST)
	@ResponseBody
	public ClienteResponse delete(@RequestBody ClienteMaintenanceRequest clienteRequest)
	{
		ClienteResponse clienteResponse = new ClienteResponse();
		try
		{

			clienteResponse = getClienteBAI().deleteCliente(clienteRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			clienteResponse = null;
		}

		return clienteResponse;

	}

	/**
	 * Insert one cliente.
	 *
	 * @param clienteRequest the cliente request
	 * @return the response
	 */
	@RequestMapping(value = INSERT_CLIENTE, method = RequestMethod.POST)
	@ResponseBody
	public ClienteResponse insert(@RequestBody ClienteMaintenanceRequest clienteRequest)
	{
		ClienteResponse clienteResponse = new ClienteResponse();

		try
		{

			clienteRequest.getCliente().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			clienteResponse = getClienteBAI().insertCliente(clienteRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			clienteResponse = null;
		}

		return clienteResponse;

	}

}
