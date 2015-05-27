package com.prosperitasglobal.sendsolv.common.controller;

import org.springframework.stereotype.Controller;

@Controller
public class PGSIMainAPIController extends BaseController
{

	// /** The Constant FETCH_CURRENCY. */
	// private static final String FETCH_CURRENCY = "/fetchCurrency";
	//
	// /** The Constant FETCH_STATES. */
	// public static final String FETCH_STATES = "/fetchstates";
	//
	// /** The Constant FETCH_PAYER. */
	// public static final String FETCH_PAYER = "/fetchpayer";
	//
	// /** The Constant FETCH_DOCUMENT. */
	// public static final String FETCH_DOCUMENT = "/fetchDocumentType";
	//
	// /** The Constant CODE. */
	// public static final String CODE = "code";
	//
	// /** The Constant BUSINESSTYPEENUM. */
	// public static final String BUSINESSTYPEENUM = "/businessTypeEnum";
	//
	// /** The country bai. */
	// private ICountryBAI countryBAI;
	//
	// /**
	// * Gets the country bai.
	// *
	// * @return the country bai
	// */
	// public ICountryBAI getCountryBAI()
	// {
	// return countryBAI;
	// }
	//
	// /**
	// * Sets the country bai.
	// *
	// * @param countryBAI the country bai
	// */
	// @Resource
	// public void setCountryBAI(ICountryBAI countryBAI)
	// {
	// this.countryBAI = countryBAI;
	// }
	//
	// /**
	// * Fetches a collection of states for the StateProvinceRegion code provided.
	// *
	// * @param countryCode the country code
	// * @return Map<String, String>
	// */
	// @RequestMapping(value = FETCH_STATES, method = RequestMethod.GET)
	// @ResponseBody
	// public List<Map<String, String>> fetchStates(@RequestParam(value = CODE, required = true) String countryCode,
	// HttpServletRequest request)
	// {
	// return ListD
	// .fetchStateProvinceRegionByCountryCode(countryBAI, fetchUserContext(request), countryCode);
	// }
	//
	// /**
	// * Fetch currency.
	// *
	// * @param countryCode the country code
	// * @return the map< string, string>
	// * @throws Exception the exception
	// */
	// @RequestMapping(value = FETCH_CURRENCY, method = RequestMethod.GET)
	// @ResponseBody
	// public List<Map<String, String>> fetchCurrency(@RequestParam(value = CODE, required = true) String countryCode,
	// HttpServletRequest request)
	// throws Exception
	// {
	// return ListD.fetchAllCountriesBAI(countryBAI, fetchUserContext(request), countryCode);
	//
	// }
	//
	// // /**
	// // * Fetch document type.
	// // *
	// // * @param businessTypeEnum the business type enum
	// // * @return the map< string, string>
	// // */
	// // @RequestMapping(value = FETCH_DOCUMENT, method = RequestMethod.GET)
	// // @ResponseBody
	// // public List<Map<String, String>> fetchDocumentType(
	// // @RequestParam(value = BUSINESSTYPEENUM, required = true) Integer businessTypeEnum,
	// // HttpServletRequest request)
	// // {
	// // // return ListD.fetchSDocumentTypeByCode(documentTypeBAI, fetchUserContext(request), businessTypeEnum);
	// //
	// // }

}
