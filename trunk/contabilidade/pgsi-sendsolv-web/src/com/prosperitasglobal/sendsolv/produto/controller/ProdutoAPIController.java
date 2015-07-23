package com.prosperitasglobal.sendsolv.produto.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.CfopInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ClassificacaoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.GrupoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MarcaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ProdutoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ProdutoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.SubGrupoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.UniMedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.CfopResponse;
import com.prosperitasglobal.sendsolv.model.response.ClassificacaoResponse;
import com.prosperitasglobal.sendsolv.model.response.GrupoResponse;
import com.prosperitasglobal.sendsolv.model.response.MarcaResponse;
import com.prosperitasglobal.sendsolv.model.response.ProdutoResponse;
import com.prosperitasglobal.sendsolv.model.response.SubGrupoResponse;
import com.prosperitasglobal.sendsolv.model.response.UniMedResponse;

@Controller
@RequestMapping("/api/produto")
public class ProdutoAPIController extends ProdutoBaseController
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

	private static final String FETCH_UNIMED = "/fetch/unimed";

	private static final String FETCH_GRUPO = "/fetch/grupo";

	private static final String FETCH_SUBGRUPO = "/fetch/subgrupo";

	private static final String FETCH_MARCA = "/fetch/marca";

	private static final String FETCH_CLASSIFICACAO = "/fetch/classificacao";

	private static final String FETCH_CFOP = "/fetch/cfop";

	/** The Constant INSERT_LOCATION. */
	private static final String INSERT_LOCATION = "/add";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";
	/** The Constant APPLY. */
	private static final String APPLY = "/applyStatus";
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProdutoAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "ProdutoAPIController";

	/**
	 * Fetch all Produtos.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public ProdutoResponse fetchAll(@RequestBody ProdutoInquiryRequest pagedInquiryRequest)
	{

		return fetchProdutoByRequest(pagedInquiryRequest);

	}

	@RequestMapping(value = FETCH_UNIMED, method = RequestMethod.POST)
	@ResponseBody
	public UniMedResponse fetchAll(@RequestBody UniMedInquiryRequest pagedInquiryRequest)
	{

		return fetchUniMedByRequest(pagedInquiryRequest);

	}

	@RequestMapping(value = FETCH_GRUPO, method = RequestMethod.POST)
	@ResponseBody
	public GrupoResponse fetchAll(@RequestBody GrupoInquiryRequest pagedInquiryRequest)
	{

		return fetchGrupoByRequest(pagedInquiryRequest);

	}

	@RequestMapping(value = FETCH_SUBGRUPO, method = RequestMethod.POST)
	@ResponseBody
	public SubGrupoResponse fetchAll(@RequestBody SubGrupoInquiryRequest pagedInquiryRequest)
	{

		return fetchSubGrupoByRequest(pagedInquiryRequest);

	}

	@RequestMapping(value = FETCH_MARCA, method = RequestMethod.POST)
	@ResponseBody
	public MarcaResponse fetchAll(@RequestBody MarcaInquiryRequest pagedInquiryRequest)
	{

		return fetchMarcaByRequest(pagedInquiryRequest);

	}

	@RequestMapping(value = FETCH_CLASSIFICACAO, method = RequestMethod.POST)
	@ResponseBody
	public ClassificacaoResponse fetchAll(@RequestBody ClassificacaoInquiryRequest pagedInquiryRequest)
	{

		return fetchClassificacaoByRequest(pagedInquiryRequest);

	}

	@RequestMapping(value = FETCH_CFOP, method = RequestMethod.POST)
	@ResponseBody
	public CfopResponse fetchAll(@RequestBody CfopInquiryRequest pagedInquiryRequest)
	{

		return fetchCfopByRequest(pagedInquiryRequest);

	}

	/**
	 * Fetch.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the location response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public ProdutoResponse fetch(@RequestBody FetchByIdRequest fetchByIdRequest)
	{

		return fetchProdutoById(fetchByIdRequest);

	}

	/**
	 * Edit one location.
	 *
	 * @param locationRequest the location request
	 * @return the response
	 */
	@RequestMapping(value = EDIT_LOCATION, method = RequestMethod.POST)
	@ResponseBody
	public ProdutoResponse edit(@RequestBody ProdutoMaintenanceRequest locationRequest)
	{
		ProdutoResponse locationResponse = new ProdutoResponse();
		try
		{

			locationResponse = getProdutoBAI().updateProduto(locationRequest);

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
	public ProdutoResponse delete(@RequestBody ProdutoMaintenanceRequest locationRequest)
	{
		ProdutoResponse locationResponse = new ProdutoResponse();
		try
		{

			locationResponse = getProdutoBAI().deleteProduto(locationRequest);

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
	public ProdutoResponse insertApi(@RequestBody ProdutoMaintenanceRequest locationRequest)
	{

		return insert(locationRequest);

	}

}
