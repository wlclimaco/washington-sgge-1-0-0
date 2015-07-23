package com.prosperitasglobal.sendsolv.cliente.controller;

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

	/** The URL mapping constants. */
	private static final String DELETE_CLIENTE = "/delete";

	/** The Constant EDIT_CLIENTE. */
	private static final String EDIT_CLIENTE = "/edit";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/fetchall";

	private static final String FETCH_ALL_AGENCIA = "/fetch/agencia";

	private static final String FETCH_ALL_BANCO = "/fetch/banco";

	private static final String FETCH_ALL_CONTA = "/fetch/conta";

	private static final String FETCH_ALL_ESTADO = "/fetch/estado";

	/** The Constant INSERT_CLIENTE. */
	private static final String INSERT_CLIENTE = "/add";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";

	/** The Constant APPLY. */

	/**
	 * Fetch all Clientes.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public ClienteResponse fetchAll(@RequestBody ClienteInquiryRequest pagedInquiryRequest)
	{

		return fetchClienteByRequest(pagedInquiryRequest);

	}

	@RequestMapping(value = FETCH_ALL_AGENCIA, method = RequestMethod.POST)
	@ResponseBody
	public AgenciaResponse fetchAll(@RequestBody AgenciaInquiryRequest pagedInquiryRequest)
	{

		return fetchAgenciaByRequest(pagedInquiryRequest);

	}

	@RequestMapping(value = FETCH_ALL_BANCO, method = RequestMethod.POST)
	@ResponseBody
	public BancoResponse fetchAll(@RequestBody BancoInquiryRequest pagedInquiryRequest)
	{

		return fetchBancoByRequest(pagedInquiryRequest);

	}

	@RequestMapping(value = FETCH_ALL_CONTA, method = RequestMethod.POST)
	@ResponseBody
	public ContaResponse fetchAll(@RequestBody ContaInquiryRequest pagedInquiryRequest)
	{

		return fetchContaByRequest(pagedInquiryRequest);

	}

	@RequestMapping(value = FETCH_ALL_ESTADO, method = RequestMethod.POST)
	@ResponseBody
	public EstadoResponse fetchAll(@RequestBody EstadoInquiryRequest pagedInquiryRequest)
	{

		return fetchEstadoByRequest(pagedInquiryRequest);

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
	public ClienteResponse editCliente(@RequestBody ClienteMaintenanceRequest clienteRequest)
	{

		return edit(clienteRequest);

	}

	/**
	 * Delete one cliente.
	 *
	 * @param clienteRequest the cliente request
	 * @return the response
	 */
	@RequestMapping(value = DELETE_CLIENTE, method = RequestMethod.POST)
	@ResponseBody
	public ClienteResponse deleteCliente(@RequestBody ClienteMaintenanceRequest clienteRequest)
	{

		return delete(clienteRequest);

	}

	/**
	 * Insert one cliente.
	 *
	 * @param clienteRequest the cliente request
	 * @return the response
	 */
	@RequestMapping(value = INSERT_CLIENTE, method = RequestMethod.POST)
	@ResponseBody
	public ClienteResponse insertCliente(@RequestBody ClienteMaintenanceRequest clienteRequest)
	{

		return insert(clienteRequest);

	}

}
