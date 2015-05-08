package com.prosperitasglobal.sendsolv.payer.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.filter.model.response.FiltersResponse;
import com.prosperitasglobal.sendsolv.payer.model.PayerCountry;
import com.prosperitasglobal.sendsolv.payer.model.request.PayerCountryInquiryRequest;
import com.prosperitasglobal.sendsolv.payer.model.request.PayerStateProvinceRegionInquiryRequest;
import com.prosperitasglobal.sendsolv.payer.model.request.criteria.PayerCountryCriteria;
import com.prosperitasglobal.sendsolv.payer.model.request.criteria.PayerStateProvinceRegionCriteria;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;

@Controller
@RequestMapping("/payer")
public class PayerViewController extends PayerBaseController
{
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PayerViewController.class);
	/** The Constant START_PAGE_NUMBER. */
	private static final int START_PAGE_NUMBER = 0;

	/** The Constant INITIAL_PAGE_SIZE. */
	private static final int INITIAL_PAGE_SIZE = 25;

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "PayerViewController";

	@RequestMapping(value = "/find/countries", method = RequestMethod.GET)
	public ModelAndView loadCountries(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("/payer/payer_countries");

		FiltersResponse filtersResponse = new FiltersResponse();
		getFilterFactory().configureFilter("Country", null, filtersResponse);
		try
		{
			PayerCountryInquiryRequest payerCountryInquiryRequest = new PayerCountryInquiryRequest();
			payerCountryInquiryRequest.setStartPage(START_PAGE_NUMBER);
			payerCountryInquiryRequest.setPageSize(INITIAL_PAGE_SIZE);
			payerCountryInquiryRequest.setPreQueryCount(true);
			payerCountryInquiryRequest.addSortExpressions(new SortExpression("code",
					Direction.Ascending));
			PayerCountryCriteria criteria = new PayerCountryCriteria();
			criteria.setNoRelationship(Boolean.TRUE);
			payerCountryInquiryRequest.setCriteria(criteria);

			modelAndView.addObject("filters", getMapper().writeValueAsString(filtersResponse));
			modelAndView.addObject(RESPONSE, getMapper()
					.writeValueAsString(fetchPayerCountryByRequest(payerCountryInquiryRequest)));
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

	@RequestMapping(value = "/find/state", method = RequestMethod.GET)
	public ModelAndView loadStates(@RequestParam(value = "code", required = false) String code,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("/payer/payer_states");
		FiltersResponse filtersResponse = new FiltersResponse();
		getFilterFactory().configureFilter("state", null, filtersResponse);
		try
		{
			PayerStateProvinceRegionInquiryRequest payerRequest = new PayerStateProvinceRegionInquiryRequest();
			payerRequest.setStartPage(START_PAGE_NUMBER);
			payerRequest.setPageSize(INITIAL_PAGE_SIZE);

			payerRequest.setPreQueryCount(true);
			payerRequest.addSortExpressions(new SortExpression("code",
					Direction.Ascending));
			PayerStateProvinceRegionCriteria criteria = new PayerStateProvinceRegionCriteria();

			PayerCountry payerCountry = new PayerCountry();
			payerCountry.setCode(code);
			criteria.setCountry(payerCountry);
			criteria.setNoRelationship(Boolean.TRUE);

			payerRequest.setCriteria(criteria);
			modelAndView.addObject("filters", getMapper().writeValueAsString(filtersResponse));
			modelAndView.addObject(RESPONSE, getMapper()
					.writeValueAsString(fetchPayerStateProvinceRegionByRequest(payerRequest)));
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

	@RequestMapping(value = "/find/branches", method = RequestMethod.GET)
	public ModelAndView loadBranches(@RequestParam(value = "id", required = false) Integer id,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("/payer/payer_branches");
		try
		{
			modelAndView.addObject(RESPONSE, getMapper()
					.writeValueAsString(fetchPayerAddressesByCityId(new FetchByIdRequest(id))));
		}
		catch (IOException e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				modelAndView.addObject(RESPONSE, null);
			}
		}
		return modelAndView;
	}

	@RequestMapping(value = "/find/cities", method = RequestMethod.GET)
	public ModelAndView loadStates(@RequestParam(value = "id", required = false) Integer id,
			HttpServletRequest request)
	{

		ModelAndView modelAndView = new ModelAndView("/payer/payer_cities");
		try
		{

			PayerStateProvinceRegionInquiryRequest payerRequest = new PayerStateProvinceRegionInquiryRequest();
			payerRequest.setStartPage(START_PAGE_NUMBER);
			payerRequest.setPageSize(INITIAL_PAGE_SIZE);

			payerRequest.setPreQueryCount(true);
			payerRequest.addSortExpressions(new SortExpression("id",
					Direction.Ascending));

			PayerStateProvinceRegionCriteria criteria = new PayerStateProvinceRegionCriteria();
			criteria.setId(id);
			payerRequest.setCriteria(criteria);

			modelAndView
					.addObject(
							RESPONSE,
							getMapper()

									.writeValueAsString(
											fetchPayerCityByStateProvinceRegionId(payerRequest)));
		}
		catch (IOException e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				modelAndView.addObject(RESPONSE, null);
			}
		}
		return modelAndView;
	}
}