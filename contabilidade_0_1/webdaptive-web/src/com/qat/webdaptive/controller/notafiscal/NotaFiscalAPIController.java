package com.qat.webdaptive.controller.notafiscal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.CaixaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CondPgInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ContasInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalEntradaMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalSaidaMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.OrcamentoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.OrcamentoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PedidoComprasInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.PedidoComprasMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.CaixaResponse;
import com.prosperitasglobal.sendsolv.model.response.CondPgResponse;
import com.prosperitasglobal.sendsolv.model.response.ContasResponse;
import com.prosperitasglobal.sendsolv.model.response.NotaFiscalEntradaResponse;
import com.prosperitasglobal.sendsolv.model.response.NotaFiscalResponse;
import com.prosperitasglobal.sendsolv.model.response.NotaFiscalSaidaResponse;
import com.prosperitasglobal.sendsolv.model.response.OrcamentoResponse;
import com.prosperitasglobal.sendsolv.model.response.PedidoComprasResponse;

@Controller
@RequestMapping("/api/notafiscal")
public class NotaFiscalAPIController extends NotaFiscalBaseController
{

	/** The Constant FETCH_SIC_NAICS. */
	private static final String FETCH_SIC_NAICS = "fetchSicNaics";
	/** The Constant FETCH_ORGANIZATION_BYNOTAFISCAL. */
	private static final String FETCH_ORGANIZATION_BYNOTAFISCAL = "fetchOrganizationBylocation";
	/** The URL mapping constants. */
	private static final String DELETE_NOTAFISCAL = "/delete";

	/** The Constant EDIT_NOTAFISCAL. */
	private static final String EDIT_NOTAFISCAL = "/edit";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL_ENTRADA = "/fetchall/entrada";

	private static final String FETCH_ALL_SAIDA = "/fetchall/saida";

	private static final String FETCH_ALL_ORCAMENTO = "/fetchall/orcamento";

	private static final String FETCH_ALL_COMPRAS = "/fetchall/compras";

	private static final String FETCH_ALL_CAIXA = "/fetchall/caixa";

	private static final String FETCH_ALL_CONTAS_PG = "/fetchall/contaspg";

	private static final String FETCH_ALL_CONTAS_RB = "/fetchall/contasrb";

	private static final String FETCH_ALL_CONDPG = "/fetchall/condpg";

	/** The Constant INSERT_NOTAFISCAL. */
	private static final String INSERT_NOTAFISCAL_ENTRADA = "/add/entrada";

	private static final String INSERT_NOTAFISCAL_SAIDA = "/add/saida";

	private static final String INSERT_PD_COMPRAS = "/add/compras";

	private static final String INSERT_ORCAMENTO = "/add/orcamento";

	private static final String INSERT_CONTAS_PG = "/add/contaspg";

	private static final String INSERT_CONTAS_RB = "/add/contasrb";

	private static final String INSERT_CAIXA = "/add/caixa";

	private static final String INSERT_CONG_PG = "/add/confpg";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";
	/** The Constant APPLY. */
	private static final String APPLY = "/applyStatus";
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "NotaFiscalAPIController";

	/**
	 * Fetch all NotaFiscals.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ALL_ENTRADA, method = RequestMethod.POST)
	@ResponseBody
	public NotaFiscalEntradaResponse fetchAllEntrada(@RequestBody NotaFiscalInquiryRequest pagedInquiryRequest)
	{

		return fetchNotaFiscalEntradaByRequest(pagedInquiryRequest);

	}

	@RequestMapping(value = FETCH_ALL_SAIDA, method = RequestMethod.POST)
	@ResponseBody
	public NotaFiscalSaidaResponse fetchAllSaida(@RequestBody NotaFiscalInquiryRequest pagedInquiryRequest)
	{

		return fetchNotaFiscalSaidaByRequest(pagedInquiryRequest);

	}

	@RequestMapping(value = FETCH_ALL_COMPRAS, method = RequestMethod.POST)
	@ResponseBody
	public PedidoComprasResponse fetchAllCompras(@RequestBody PedidoComprasInquiryRequest pagedInquiryRequest)
	{

		return fetchPedidoComprasByRequest(pagedInquiryRequest);

	}

	@RequestMapping(value = FETCH_ALL_ORCAMENTO, method = RequestMethod.POST)
	@ResponseBody
	public OrcamentoResponse fetchAllOrcamento(@RequestBody OrcamentoInquiryRequest pagedInquiryRequest)
	{

		return fetchOrcamentoByRequest(pagedInquiryRequest);

	}

	@RequestMapping(value = FETCH_ALL_CAIXA, method = RequestMethod.POST)
	@ResponseBody
	public CaixaResponse fetchAllCaixa(@RequestBody CaixaInquiryRequest pagedInquiryRequest)
	{

		return fetchCaixaByRequest(pagedInquiryRequest);

	}

	@RequestMapping(value = FETCH_ALL_CONDPG, method = RequestMethod.POST)
	@ResponseBody
	public CondPgResponse fetchAllCondPag(@RequestBody CondPgInquiryRequest pagedInquiryRequest)
	{

		return fetchCondPgByRequest(pagedInquiryRequest);

	}

	@RequestMapping(value = FETCH_ALL_CONTAS_PG, method = RequestMethod.POST)
	@ResponseBody
	public ContasResponse fetchAllContas(@RequestBody ContasInquiryRequest pagedInquiryRequest)
	{

		return fetchContasByRequest(pagedInquiryRequest);

	}

	@RequestMapping(value = FETCH_ALL_CONTAS_RB, method = RequestMethod.POST)
	@ResponseBody
	public ContasResponse fetchAll(@RequestBody ContasInquiryRequest pagedInquiryRequest)
	{

		return fetchContasByRequest(pagedInquiryRequest);

	}

	/**
	 * Fetch.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the location response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public NotaFiscalResponse fetch(@RequestBody FetchByIdRequest fetchByIdRequest)
	{

		return fetchNotaFiscalById(fetchByIdRequest);

	}

	/**
	 * Edit one location.
	 *
	 * @param locationRequest the location request
	 * @return the response
	 */
	// @RequestMapping(value = EDIT_NOTAFISCAL, method = RequestMethod.POST)
	// @ResponseBody
	// public NotaFiscalResponse edit(@RequestBody NotaFiscalMaintenanceRequest locationRequest)
	// {
	// NotaFiscalResponse locationResponse = new NotaFiscalResponse();
	// try
	// {
	//
	// locationResponse = getNotaFiscalBAI().updateNotaFiscal(locationRequest);
	//
	// }
	// catch (Exception e)
	// {
	// LOG.error(CONTROLLER_EXCEPTION_MSG, e);
	// locationResponse = null;
	// }
	// return locationResponse;
	//
	// }

	/**
	 * Delete one location.
	 *
	 * @param locationRequest the location request
	 * @return the response
	 */
	// @RequestMapping(value = DELETE_NOTAFISCAL, method = RequestMethod.POST)
	// @ResponseBody
	// public NotaFiscalResponse delete(@RequestBody NotaFiscalMaintenanceRequest locationRequest)
	// {
	// NotaFiscalResponse locationResponse = new NotaFiscalResponse();
	// try
	// {
	//
	// locationResponse = getNotaFiscalBAI().deleteNotaFiscal(locationRequest);
	//
	// }
	// catch (Exception e)
	// {
	// LOG.error(CONTROLLER_EXCEPTION_MSG, e);
	// locationResponse = null;
	// }
	//
	// return locationResponse;
	//
	// }

	/**
	 * Insert one location.
	 *
	 * @param locationRequest the location request
	 * @return the response
	 */
	@RequestMapping(value = INSERT_NOTAFISCAL_ENTRADA, method = RequestMethod.POST)
	@ResponseBody
	public NotaFiscalEntradaResponse insertApi(@RequestBody NotaFiscalEntradaMaintenanceRequest locationRequest)
	{

		return insertEntrada(locationRequest);

	}

	@RequestMapping(value = INSERT_NOTAFISCAL_SAIDA, method = RequestMethod.POST)
	@ResponseBody
	public NotaFiscalSaidaResponse insertApii(@RequestBody NotaFiscalSaidaMaintenanceRequest locationRequest)
	{

		return insertSaida(locationRequest);

	}

	@RequestMapping(value = INSERT_ORCAMENTO, method = RequestMethod.POST)
	@ResponseBody
	public OrcamentoResponse insertApiii(@RequestBody OrcamentoMaintenanceRequest locationRequest)
	{

		return insertOrcamento(locationRequest);

	}

	@RequestMapping(value = INSERT_PD_COMPRAS, method = RequestMethod.POST)
	@ResponseBody
	public PedidoComprasResponse insertApiiii(@RequestBody PedidoComprasMaintenanceRequest locationRequest)
	{

		return insertCompras(locationRequest);

	}

}
