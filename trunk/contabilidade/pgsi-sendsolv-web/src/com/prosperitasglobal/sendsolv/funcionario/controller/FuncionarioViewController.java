package com.prosperitasglobal.sendsolv.funcionario.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.sendsolv.filter.FilterFactory;
import com.prosperitasglobal.sendsolv.filter.model.response.FiltersResponse;
import com.prosperitasglobal.sendsolv.model.criteria.BeneficiosCriteria;
import com.prosperitasglobal.sendsolv.model.criteria.EventoCriteria;
import com.prosperitasglobal.sendsolv.model.criteria.FuncionarioCriteria;
import com.prosperitasglobal.sendsolv.model.criteria.HoraFuncCriteria;
import com.prosperitasglobal.sendsolv.model.request.BeneficiosInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.EventoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FuncionarioInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.HoraFuncInquiryRequest;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioViewController extends FuncionarioBaseController
{
	/** The URL mapping constants. */
	private static final String FETCH_LIST = "";

	private static final String FETCH_LIST_EVENTO = "/eventos";

	private static final String FETCH_LIST_BENEFICIOS = "/beneficios";

	private static final String FETCH_LIST_CONVENIO = "/convenio";

	private static final String FETCH_LIST_FUNCPONTO = "/funcPonto";

	/** The Constant FETCH_ADD. */
	private static final String FETCH_ADD = "/add";

	/** The Constant FETCH_EDIT. */
	private static final String FETCH_EDIT = "/edit";

	/** The Constant FETCH_VIEW. */
	private static final String FETCH_VIEW_TABS = "/view";

	/** The Constant FETCH_VIEW_INFO. */
	private static final String FETCH_VIEW = "/view/info";

	/** The Constant EDIT_VIEW. */
	private static final String EDIT_VIEW = "/editView";

	/** The Constant FETCH_ORGANIZATION_BYFUNCIONARIO. */
	private static final String FETCH_ORGANIZATION_BYFUNCIONARIO = "fetchOrganizationBylocation";

	/** The view mapping constants . */
	private static final String VIEW_FUNCIONARIO_MAIN = "/funcionario/funcionario_main";

	/** The Constant VIEW_FUNCIONARIO_ADD. */
	private static final String VIEW_FUNCIONARIO_ADD = "/funcionario/funcionario_create";

	/** The Constant VIEW_FUNCIONARIO_VIEW. */
	private static final String VIEW_FUNCIONARIO_VIEW = "/funcionario/funcionario_view";

	private static final String VIEW_FUNCIONARIO_TABS = "/funcionario/funcionario_tabs";

	/** The Constant VIEW_FUNCIONARIO_DIALOG_ADD. */
	private static final String VIEW_FUNCIONARIO_DIALOG_ADD = "/funcionario/funcionario_dialog_create";

	/** The PagedInquiryRequest Constants. */
	private static final int START_PAGE_NUMBER = 0;

	/** The Constant INITIAL_PAGE_SIZE. */
	private static final int INITIAL_PAGE_SIZE = 25;

	/** The Constant FUNCIONARIO_ID. */
	private static final String FUNCIONARIO_ID = "locationId";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FuncionarioViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "LocationViewController";

	private static final String BUSINESS = "BUSINESS";
	private static final String FILTERS = "filters";

	/** The filter factory. */
	private FilterFactory filterFactory;

	/**
	 * @return the filterFactory
	 */
	public FilterFactory getFilterFactory()
	{
		return filterFactory;
	}

	/**
	 * @param filterFactory the filterFactory to set
	 */
	@Resource
	public void setFilterFactory(FilterFactory filterFactory)
	{
		this.filterFactory = filterFactory;
	}

	/**
	 * Load list views.
	 *
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FETCH_LIST, method = RequestMethod.GET)
	public ModelAndView loadList(@RequestParam(value = "locationId", required = false) Integer locationId,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_FUNCIONARIO_MAIN);

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}
		FuncionarioCriteria criteria = new FuncionarioCriteria();
		criteria.setEmpresa(locationId);
		FuncionarioInquiryRequest pagedInquiryRequest = new FuncionarioInquiryRequest();
		pagedInquiryRequest.setStartPage(START_PAGE_NUMBER);
		pagedInquiryRequest.setPageSize(INITIAL_PAGE_SIZE);
		pagedInquiryRequest.setPreQueryCount(true);
		pagedInquiryRequest.addSortExpressions(new SortExpression("ID",
				Direction.Ascending));
		pagedInquiryRequest.setCriteria(criteria);

		try
		{

			modelAndView.addObject(RESPONSE, getMapper()
					.writeValueAsString(fetchFuncionarioByRequest(pagedInquiryRequest)));

			FiltersResponse filtersResponse = new FiltersResponse();
			getFilterFactory().configureFilter(BUSINESS, null, filtersResponse);

			modelAndView.addObject(FILTERS, getMapper().writeValueAsString(filtersResponse));
		}

		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				modelAndView.addObject(RESPONSE, null);
			}
		}

		return modelAndView;
	}

	// Convenio
	@RequestMapping(value = FETCH_LIST_CONVENIO, method = RequestMethod.GET)
	public ModelAndView loadListConvenio(@RequestParam(value = "locationId", required = false) Integer locationId,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("convenio/convenio_main");

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}
		BeneficiosInquiryRequest pagedInquiryRequest = new BeneficiosInquiryRequest();
		pagedInquiryRequest.setStartPage(START_PAGE_NUMBER);
		pagedInquiryRequest.setPageSize(INITIAL_PAGE_SIZE);
		pagedInquiryRequest.setPreQueryCount(true);
		pagedInquiryRequest.addSortExpressions(new SortExpression("ID",
				Direction.Ascending));

		try
		{

			modelAndView.addObject(RESPONSE, getMapper()
					.writeValueAsString(fetchBeneficiosByRequest(pagedInquiryRequest)));

			FiltersResponse filtersResponse = new FiltersResponse();
			getFilterFactory().configureFilter(BUSINESS, null, filtersResponse);

			modelAndView.addObject(FILTERS, getMapper().writeValueAsString(filtersResponse));
		}

		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				modelAndView.addObject(RESPONSE, null);
			}
		}

		return modelAndView;
	}

	// Eventos
	@RequestMapping(value = FETCH_LIST_EVENTO, method = RequestMethod.GET)
	public ModelAndView loadListEvento(@RequestParam(value = "locationId", required = false) Integer locationId,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("eventos/eventos_main");

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}
		EventoCriteria criteria = new EventoCriteria();
		criteria.setEmpresa(locationId);
		EventoInquiryRequest pagedInquiryRequest = new EventoInquiryRequest();
		pagedInquiryRequest.setStartPage(START_PAGE_NUMBER);
		pagedInquiryRequest.setPageSize(INITIAL_PAGE_SIZE);
		pagedInquiryRequest.setPreQueryCount(true);
		pagedInquiryRequest.addSortExpressions(new SortExpression("ID",
				Direction.Ascending));
		pagedInquiryRequest.setCriteria(criteria);

		try
		{

			modelAndView.addObject(RESPONSE, getMapper()
					.writeValueAsString(fetchEventoByRequest(pagedInquiryRequest)));

			FiltersResponse filtersResponse = new FiltersResponse();
			getFilterFactory().configureFilter(BUSINESS, null, filtersResponse);

			modelAndView.addObject(FILTERS, getMapper().writeValueAsString(filtersResponse));
		}

		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				modelAndView.addObject(RESPONSE, null);
			}
		}

		return modelAndView;
	}

	// pontoFunc
	@RequestMapping(value = FETCH_LIST_FUNCPONTO, method = RequestMethod.GET)
	public ModelAndView loadListFunc(@RequestParam(value = "locationId", required = false) Integer locationId,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("pontoFunc/pontoFunc_main");

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}
		HoraFuncCriteria criteria = new HoraFuncCriteria();
		criteria.setEmpresa(locationId);
		HoraFuncInquiryRequest pagedInquiryRequest = new HoraFuncInquiryRequest();
		pagedInquiryRequest.setStartPage(START_PAGE_NUMBER);
		pagedInquiryRequest.setPageSize(INITIAL_PAGE_SIZE);
		pagedInquiryRequest.setPreQueryCount(true);
		pagedInquiryRequest.addSortExpressions(new SortExpression("ID",
				Direction.Ascending));
		pagedInquiryRequest.setCriteria(criteria);

		try
		{

			modelAndView.addObject(RESPONSE, getMapper()
					.writeValueAsString(fetchHorarioFuncByRequest(pagedInquiryRequest)));

			FiltersResponse filtersResponse = new FiltersResponse();
			getFilterFactory().configureFilter(BUSINESS, null, filtersResponse);

			modelAndView.addObject(FILTERS, getMapper().writeValueAsString(filtersResponse));
		}

		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				modelAndView.addObject(RESPONSE, null);
			}
		}

		return modelAndView;
	}

	// beneficios
	@RequestMapping(value = FETCH_LIST_BENEFICIOS, method = RequestMethod.GET)
	public ModelAndView loadListBene(@RequestParam(value = "locationId", required = false) Integer locationId,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("beneficios/beneficios_main");

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}
		BeneficiosCriteria criteria = new BeneficiosCriteria();
		criteria.setEmpresa(locationId);
		BeneficiosInquiryRequest pagedInquiryRequest = new BeneficiosInquiryRequest();
		pagedInquiryRequest.setStartPage(START_PAGE_NUMBER);
		pagedInquiryRequest.setPageSize(INITIAL_PAGE_SIZE);
		pagedInquiryRequest.setPreQueryCount(true);
		pagedInquiryRequest.addSortExpressions(new SortExpression("ID",
				Direction.Ascending));
		pagedInquiryRequest.setCriteria(criteria);

		try
		{

			modelAndView.addObject(RESPONSE, getMapper()
					.writeValueAsString(fetchBeneficiosByRequest(pagedInquiryRequest)));

			FiltersResponse filtersResponse = new FiltersResponse();
			getFilterFactory().configureFilter(BUSINESS, null, filtersResponse);

			modelAndView.addObject(FILTERS, getMapper().writeValueAsString(filtersResponse));
		}

		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				modelAndView.addObject(RESPONSE, null);
			}
		}

		return modelAndView;
	}

	/**
	 * Load add/edit view.
	 *
	 * @param locationId the location id
	 * @return the model and view
	 */
	@RequestMapping(value = {FETCH_ADD, FETCH_EDIT}, method = RequestMethod.GET)
	public ModelAndView loadUpdate(@RequestParam(value = FUNCIONARIO_ID, required = false) Integer locationId,
			HttpServletRequest request)
	{

		return locationEditMAV(locationId, VIEW_FUNCIONARIO_ADD, true, request);
	}

	@RequestMapping(value = {FETCH_VIEW_TABS}, method = RequestMethod.GET)
	public ModelAndView loadTabs(@RequestParam(value = FUNCIONARIO_ID, required = true) Integer locationId,
			HttpServletRequest request)
	{
		return new ModelAndView(VIEW_FUNCIONARIO_TABS);
	}

	/**
	 * Load location view.
	 *
	 * @param locationId the location id
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = {FETCH_VIEW}, method = RequestMethod.GET)
	public ModelAndView loadView(@RequestParam(value = FUNCIONARIO_ID, required = true) Integer locationId,
			HttpServletRequest request)
	{
		return locationEditMAV(locationId, VIEW_FUNCIONARIO_VIEW, true, request);
	}

	/**
	 * Load view update.
	 *
	 * @param locationId the location id
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = {EDIT_VIEW}, method = RequestMethod.GET)
	public ModelAndView loadViewUpdate(@RequestParam(value = FUNCIONARIO_ID, required = false) Integer locationId,
			HttpServletRequest request)
	{

		return locationEditMAV(locationId, VIEW_FUNCIONARIO_DIALOG_ADD, true, request);
	}

}
