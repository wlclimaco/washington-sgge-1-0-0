package com.prosperitasglobal.sendsolv.common.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.cbof.bai.ICountryBAI;
import com.prosperitasglobal.cbof.bai.IDocumentTypeBAI;
import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.controller.delegate.ListD;
import com.prosperitasglobal.sendsolv.bai.IPayerBAI;
import com.prosperitasglobal.sendsolv.bai.IProductPlanBAI;
import com.prosperitasglobal.sendsolv.model.criteria.BusinessProductPlanCriteria;
import com.prosperitasglobal.sendsolv.model.criteria.PayerCriteria;
import com.prosperitasglobal.sendsolv.model.request.BusinessProductPlanInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.BusinessProductPlanResponse;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;

@Controller
public class PGSIMainAPIController extends BaseController
{

	/** The Constant BUSINESS_ID. */
	private static final String BUSINESS_ID = "businessId";

	/** The Constant FETCH_PRODUCT_PLAN. */
	private static final String FETCH_PRODUCT_PLAN = "/fetchProductPlan";

	/** The Constant FETCH_CURRENCY. */
	private static final String FETCH_CURRENCY = "/fetchCurrency";

	/** The Constant FETCH_STATES. */
	public static final String FETCH_STATES = "/fetchstates";

	/** The Constant FETCH_PAYER. */
	public static final String FETCH_PAYER = "/fetchpayer";

	/** The Constant FETCH_DOCUMENT. */
	public static final String FETCH_DOCUMENT = "/fetchDocumentType";

	/** The Constant CODE. */
	public static final String CODE = "code";

	/** The Constant BUSINESSTYPEENUM. */
	public static final String BUSINESSTYPEENUM = "/businessTypeEnum";

	/** The country bai. */
	private ICountryBAI countryBAI;

	/** The document type bai. */
	private IDocumentTypeBAI documentTypeBAI;

	/** The product plan bai. */
	private IProductPlanBAI productPlanBAI;

	/** The payer bai. */
	private IPayerBAI payerBAI;

	/**
	 * Gets the payer bai.
	 *
	 * @return the payer bai
	 */
	public IPayerBAI getPayerBAI()
	{
		return payerBAI;
	}

	/**
	 * Sets the payer bai.
	 *
	 * @param payerBAI the payer bai
	 */
	@Resource
	public void setPayerBAI(IPayerBAI payerBAI)
	{
		this.payerBAI = payerBAI;
	}

	/**
	 * Gets the product plan bai.
	 *
	 * @return the product plan bai
	 */
	public IProductPlanBAI getProductPlanBAI()
	{
		return productPlanBAI;
	}

	/**
	 * Sets the product plan bai.
	 *
	 * @param productPlanBAI the product plan bai
	 */
	@Resource
	public void setProductPlanBAI(IProductPlanBAI productPlanBAI)
	{
		this.productPlanBAI = productPlanBAI;
	}

	/**
	 * Gets the document type bai.
	 *
	 * @return the document type bai
	 */
	public IDocumentTypeBAI getDocumentTypeBAI()
	{
		return documentTypeBAI;
	}

	/**
	 * Sets the document type bai.
	 *
	 * @param documentTypeBAI the document type bai
	 */
	@Resource
	public void setDocumentTypeBAI(IDocumentTypeBAI documentTypeBAI)
	{
		this.documentTypeBAI = documentTypeBAI;
	}

	/**
	 * Gets the country bai.
	 *
	 * @return the country bai
	 */
	public ICountryBAI getCountryBAI()
	{
		return countryBAI;
	}

	/**
	 * Sets the country bai.
	 *
	 * @param countryBAI the country bai
	 */
	@Resource
	public void setCountryBAI(ICountryBAI countryBAI)
	{
		this.countryBAI = countryBAI;
	}

	/**
	 * Fetches a collection of states for the StateProvinceRegion code provided.
	 *
	 * @param countryCode the country code
	 * @return Map<String, String>
	 */
	@RequestMapping(value = FETCH_STATES, method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, String>> fetchStates(@RequestParam(value = CODE, required = true) String countryCode,
			HttpServletRequest request)
	{
		return ListD
				.fetchStateProvinceRegionByCountryCode(countryBAI, fetchUserContext(request), countryCode);
	}

	/**
	 * Fetch currency.
	 *
	 * @param countryCode the country code
	 * @return the map< string, string>
	 * @throws Exception the exception
	 */
	@RequestMapping(value = FETCH_CURRENCY, method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, String>> fetchCurrency(@RequestParam(value = CODE, required = true) String countryCode,
			HttpServletRequest request)
			throws Exception
	{
		return ListD.fetchAllCountriesBAI(countryBAI, fetchUserContext(request), countryCode);

	}

	/**
	 * Fetch document type.
	 *
	 * @param businessTypeEnum the business type enum
	 * @return the map< string, string>
	 */
	@RequestMapping(value = FETCH_DOCUMENT, method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, String>> fetchDocumentType(
			@RequestParam(value = BUSINESSTYPEENUM, required = true) Integer businessTypeEnum,
			HttpServletRequest request)
	{
		return ListD.fetchSDocumentTypeByCode(documentTypeBAI, fetchUserContext(request), businessTypeEnum);

	}

	/**
	 * Fetch product plan.
	 *
	 * @param businessId the business id
	 * @return the map< integer, string>
	 */
	@RequestMapping(value = FETCH_PRODUCT_PLAN, method = RequestMethod.GET)
	@ResponseBody
	public BusinessProductPlanResponse fetchProductPlan(
			@RequestParam(value = BUSINESS_ID, required = true) Integer businessId, HttpServletRequest request)

	{
		BusinessProductPlanInquiryRequest productPlanInquiryRequest = new BusinessProductPlanInquiryRequest();

		productPlanInquiryRequest.setStartPage(0);
		productPlanInquiryRequest.setPageSize(999);
		productPlanInquiryRequest.setPreQueryCount(true);
		productPlanInquiryRequest.addSortExpressions(new SortExpression("name",
				Direction.Ascending));

		BusinessProductPlanCriteria criteria = new BusinessProductPlanCriteria();
		criteria.setLocationId(businessId);

		productPlanInquiryRequest.setCriteria(criteria);

		productPlanInquiryRequest.setUserContext(fetchUserContext(request));

		BusinessProductPlanResponse productPlanResponse =
				productPlanBAI.fetchBusinessProductPlanByRequest(productPlanInquiryRequest);

		return productPlanResponse;

	}

	/**
	 * Fetch payer.
	 *
	 * @param countryCode the country code
	 * @return the map< integer, string>
	 */
	@RequestMapping(value = FETCH_PAYER, method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, String>> fetchPayer(
			@RequestParam(value = CODE, required = true) String countryCode, HttpServletRequest request)
	{
		PayerCriteria criteria = new PayerCriteria();
		criteria.setCountry(new Country(countryCode));
		return ListD.fetchPayer(payerBAI, fetchUserContext(request), criteria);

	}

}
