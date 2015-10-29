package com.qat.webdaptive.controller.delegate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.qat.framework.model.UserContext;
import com.qat.samples.sysmgmt.cnae.Cnae;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeInquiryRequest;
import com.qat.samples.sysmgmt.cnae.model.response.CnaeResponse;
import com.qat.samples.sysmgmt.entidade.bai.IEmpresaBAI;
import com.qat.samples.sysmgmt.fiscal.Regime;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.response.RegimeResponse;

public final class ListD
{

	/**
	 * The Constructor.
	 */
	private ListD()
	{
	}

	public static List<Map<String, String>> fetchAllCnaes(IEmpresaBAI empresaBAI,
			UserContext userContext)
	{
		CnaeInquiryRequest cnaeRequest = new CnaeInquiryRequest();
		cnaeRequest.setUserContext(userContext);
		cnaeRequest.setPageSize(0);
		CnaeResponse cnaeResponse = empresaBAI.fetchCnaeByRequest(cnaeRequest);

		Map<String, String> mapObject;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		for (Cnae cnae : cnaeResponse.getCnaeList())
		{
			mapObject = new TreeMap<String, String>();
			mapObject.put("key", cnae.getId().toString());
			mapObject.put("value", cnae.getDescricao());
			list.add(mapObject);

		}

		return list;
	}

	// public static List<Map<String, String>> fetchAllEstado(IPessoaBAI pessoaBAI,
	// UserContext userContext)
	// {
	// EstadoInquiryRequest cnaeRequest = new EstadoInquiryRequest();
	// cnaeRequest.setUserContext(userContext);
	// cnaeRequest.setPageSize(0);
	// EstadoResponse cnaeResponse = pessoaBAI.fetchEstadoByRequest(cnaeRequest);
	//
	// Map<String, String> mapObject;
	// List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	//
	// for (Estado cnae : cnaeResponse.getEstadoList())
	// {
	// mapObject = new TreeMap<String, String>();
	// mapObject.put("key", cnae.getId().toString());
	// mapObject.put("value", cnae.getNome());
	// list.add(mapObject);
	//
	// }
	//
	// return list;
	// }

	public static List<Map<String, String>> fetchAllRegime(IEmpresaBAI empresaBAI,
			UserContext userContext)
	{
		RegimeInquiryRequest regimeInquiryRequest = new RegimeInquiryRequest();
		regimeInquiryRequest.setUserContext(userContext);
		RegimeResponse regimeResponse = empresaBAI.fetchRegimeByRequest(regimeInquiryRequest);

		Map<String, String> mapObject;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		for (Regime range : regimeResponse.getRegimeList())
		{
			mapObject = new TreeMap<String, String>();
			mapObject.put("key", range.getId().toString());
			mapObject.put("value", range.getNome());
			list.add(mapObject);

		}

		return list;
	}

	// /**
	// * Returns a collection of Range pair values for the rangeType provided.
	// *
	// * @param rangeType the range type
	// * @param rangeBAI the range bai
	// * @param userContext the user context
	// * @return Map<Integer, String>
	// */
	// public static List<Map<String, String>> fetchRange(RangeEnum rangeType, IRangeBAI rangeBAI,
	// UserContext userContext)
	// {
	// RangeRequest rangeRequest = new RangeRequest();
	// rangeRequest.setRangeType(rangeType);
	// rangeRequest.setUserContext(userContext);
	// RangeResponse rangeResponse = rangeBAI.fetchAllRange(rangeRequest);
	//
	// Map<String, String> mapObject;
	// List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	//
	// for (Range range : rangeResponse.getRangeList())
	// {
	// mapObject = new TreeMap<String, String>();
	// mapObject.put("key", range.getId().toString());
	// mapObject.put("value", range.getName());
	// list.add(mapObject);
	//
	// }
	//
	// return list;
	// }
	//
	// /**
	// * Returns a collection of Country pair values.
	// * This method includes the pair values "Unknown" and "Not Applicable"
	// *
	// * @param countryBAI the country bai
	// * @param userContext the user context
	// * @return Map<String, String>
	// * @throws Exception the exception
	// */
	// public static List<Map<String, String>> fetchAllCountries(ICountryBAI countryBAI,
	// UserContext userContext) throws Exception
	// {
	// FetchByCodeRequest fetchByCodeRequest = new FetchByCodeRequest();
	// fetchByCodeRequest.setUserContext(userContext);
	// CountryResponse countryResponse = countryBAI.fetchAllCountry(fetchByCodeRequest);
	//
	// Map<String, String> mapObject;
	// List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	//
	// for (Country country : countryResponse.getCountryList())
	// {
	// mapObject = new TreeMap<String, String>();
	// mapObject.put("key", country.getCode());
	// mapObject.put("value", country.getDescription());
	// list.add(mapObject);
	// }
	//
	// return list;
	// }
	//
	// /**
	// * Returns a collection of all known country pair values.
	// * This method does not include the pair values "Unknown" and "Not Applicable"
	// *
	// * @param countryBAI the country bai
	// * @param userContext the user context
	// * @return Map<String, String>
	// * @throws Exception the exception
	// */
	// public static List<Map<String, String>> fetchAllKnownCountries(ICountryBAI countryBAI,
	// UserContext userContext) throws Exception
	// {
	// FetchByCodeRequest fetchByCodeRequest = new FetchByCodeRequest();
	// fetchByCodeRequest.setUserContext(userContext);
	// CountryResponse countryResponse = countryBAI.fetchAllKnownCountry(fetchByCodeRequest);
	//
	// Map<String, String> mapObject;
	// List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	//
	// for (Country country : countryResponse.getCountryList())
	// {
	// mapObject = new TreeMap<String, String>();
	// mapObject.put("key", country.getCode());
	// mapObject.put("value", country.getDescription());
	// list.add(mapObject);
	// }
	//
	// return list;
	// }
	//
	// /**
	// * Returns a collection of Phone Codes pair values.
	// *
	// * @param countryBAI the country bai
	// * @param userContext the user context
	// * @return Map<String, String>
	// * @throws Exception the exception
	// */
	// public static List<Map<String, String>> fetchAllPhoneCodes(ICountryBAI countryBAI,
	// UserContext userContext) throws Exception
	// {
	// FetchByCodeRequest fetchByCodeRequest = new FetchByCodeRequest();
	// fetchByCodeRequest.setUserContext(userContext);
	// CountryResponse countryResponse = countryBAI.fetchAllKnownCountry(fetchByCodeRequest);
	//
	// Map<String, String> mapObject;
	// List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	//
	// for (Country country : countryResponse.getCountryList())
	// {
	// mapObject = new TreeMap<String, String>();
	// mapObject.put("key", country.getCode());
	// mapObject.put("value", "+" + country.getPhoneCode() + " (" + country.getCode() + ")");
	// list.add(mapObject);
	// }
	//
	// return list;
	// }
	//
	// /**
	// * Returns a collection of StateProvinceRegion pair values.
	// *
	// * @param countryBAI the country bai
	// * @param userContext the user context
	// * @param code the code
	// * @return Map<String, String>
	// */
	// public static List<Map<String, String>> fetchStateProvinceRegionByCountryCode(
	// ICountryBAI countryBAI,
	// UserContext userContext, String code)
	// {
	// FetchByCodeRequest fetchByCodeRequest = new FetchByCodeRequest();
	// fetchByCodeRequest.setCode(code);
	// fetchByCodeRequest.setUserContext(userContext);
	//
	// StateProvinceRegionResponse stateResponse =
	// countryBAI.fetchStateProvinceRegionByCountryCode(fetchByCodeRequest);
	//
	// Map<String, String> mapObject;
	// List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	//
	// for (StateProvinceRegion state : stateResponse.getStateProvinceRegionList())
	// {
	// mapObject = new TreeMap<String, String>();
	// mapObject.put("key", state.getId());
	// mapObject.put("value", state.getDescription());
	// list.add(mapObject);
	// }
	//
	// return list;
	//
	// }

	// /**
	// * Fetch s document type by code.
	// *
	// * @param documentTypeBAI the document type bai
	// * @param userContext the user context
	// * @param businessTypeEnum the business type enum
	// * @return the map< string, string>
	// */
	// public static List<Map<String, String>> fetchSDocumentTypeByCode(
	// IDocumentTypeBAI documentTypeBAI,
	// UserContext userContext, Integer businessTypeEnum)
	// {
	//
	// DocumentTypeResponse documentTypeResponse =
	// documentTypeBAI.fetchDocumentTypeByRequest(new DocumentTypeRequest(BusinessTypeEnum
	// .enumForValue(businessTypeEnum)));
	//
	// Map<String, String> mapObject;
	// List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	//
	// for (DocumentType documents : documentTypeResponse.getDocumentTypeList())
	// {
	// mapObject = new TreeMap<String, String>();
	// mapObject.put("key", documents.getId().toString());
	// mapObject.put("value", documents.getName());
	// list.add(mapObject);
	// }
	//
	// return list;
	//
	// }

	/**
	 * Fetch s document type by code ssn.
	 * 
	 * @param documentTypeBAI the document type bai
	 * @param userContext the user context
	 * @param businessTypeEnum the business type enum
	 * @return the map< string, string>
	 */
	// public static List<Map<String, String>> fetchSDocumentTypeByCodeSSN(
	// IDocumentTypeBAI documentTypeBAI,
	// UserContext userContext, Integer businessTypeEnum)
	// {
	//
	// DocumentTypeResponse documentTypeResponse =
	// documentTypeBAI.fetchDocumentTypeByRequest(new DocumentTypeRequest(BusinessTypeEnum
	// .enumForValue(businessTypeEnum)));
	//
	// Map<String, String> mapObject;
	// List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	//
	// for (DocumentType documents : documentTypeResponse.getDocumentTypeList())
	// {
	// if (documents.getName().trim().equalsIgnoreCase(SOCIAL_SECURITY_NUMBER))
	// {
	// mapObject = new TreeMap<String, String>();
	// mapObject.put("key", documents.getId().toString());
	// mapObject.put("value", documents.getName());
	// list.add(mapObject);
	// }
	// }
	//
	// return list;
	//
	// }

	// /**
	// * Fetch language by code.
	// *
	// * @param languageBAI the language bai
	// * @param userContext the user context
	// * @return the map< string, string>
	// */
	// public static List<Map<String, String>> fetchSLanguageByCode(
	// ILanguageBAI languageBAI,
	// UserContext userContext)
	// {
	//
	// LanguageResponse languageResponse =
	// languageBAI.fetchLanguageByRequest(new PagedInquiryRequest());
	//
	// Map<String, String> mapObject;
	// List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	//
	// for (Language language : languageResponse.getLanguageList())
	// {
	// mapObject = new TreeMap<String, String>();
	// mapObject.put("key", language.getId().toString());
	// mapObject.put("value", language.getName());
	// list.add(mapObject);
	// }
	//
	// return list;
	//
	// }
	//
	// /**
	// * Fetch prefix suffix.
	// *
	// * @param codeValueEnum the code value enum
	// * @param nameSupplementBAI the name supplement bai
	// * @param userContext the user context
	// * @return the map< integer, string>
	// */
	// public static List<Map<String, String>> fetchPrefixSuffix(CodeValueEnum codeValueEnum,
	// INameSupplementBAI nameSupplementBAI,
	// UserContext userContext)
	// {
	// CodeValueRequest codeValueRequest = new CodeValueRequest();
	// codeValueRequest.setUserContext(userContext);
	//
	// CodeValueResponse codeValueResponse = new CodeValueResponse();
	//
	// if (codeValueEnum.equals(CodeValueEnum.PREFIX))
	// {
	//
	// codeValueRequest.setCodeValueType(CodeValueEnum.PREFIX);
	// codeValueResponse = nameSupplementBAI.fetchAllPrefix(codeValueRequest);
	// }
	// else
	// {
	// codeValueRequest.setCodeValueType(CodeValueEnum.SUFFIX);
	// codeValueResponse = nameSupplementBAI.fetchAllSuffix(codeValueRequest);
	// }
	//
	// Map<String, String> mapObject;
	// List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	//
	// for (CodeValue codeValue : codeValueResponse.getCodeValueList())
	// {
	// mapObject = new TreeMap<String, String>();
	// mapObject.put("key", codeValue.getId().toString());
	// mapObject.put("value", codeValue.getCode());
	// list.add(mapObject);
	// }
	//
	// return list;
	// }
	//
	// /**
	// * Fetch sic naics.
	// *
	// * @param codeValueEnum the code value enum
	// * @param codeValueBAI the code value bai
	// * @param userContext the user context
	// * @return the map< string, string>
	// */
	// public static List<Map<String, String>> fetchSicNaics(CodeValueEnum codeValueEnum,
	// ICodeValueBAI codeValueBAI,
	// UserContext userContext)
	// {
	// CodeValueRequest codeValueRequest = new CodeValueRequest();
	// codeValueRequest.setUserContext(userContext);
	//
	// CodeValueResponse codeValueResponse = new CodeValueResponse();
	//
	// if (codeValueEnum.equals(CodeValueEnum.NAICS))
	// {
	//
	// codeValueRequest.setCodeValueType(CodeValueEnum.NAICS);
	// codeValueResponse = codeValueBAI.fetchAllCodeValueByType(codeValueRequest);
	// }
	// else
	// {
	// codeValueRequest.setCodeValueType(CodeValueEnum.SIC);
	// codeValueResponse = codeValueBAI.fetchAllCodeValueByType(codeValueRequest);
	// }
	//
	// Map<String, String> mapObject;
	// List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	//
	// for (CodeValue codeValue : codeValueResponse.getCodeValueList())
	// {
	// mapObject = new TreeMap<String, String>();
	// mapObject.put("key", codeValue.getCode());
	// mapObject.put("value", codeValue.getValue());
	// list.add(mapObject);
	// }
	//
	// return list;
	// }

	/**
	 * Fetch product plan.
	 * 
	 * @param productPlanBAI the product plan bai
	 * @param userContext the user context
	 * @return the map< integer, string>
	 */
	// public static List<Map<String, String>> fetchProductPlan(
	// IProductPlanBAI productPlanBAI,
	// UserContext userContext, Integer businessId)
	// {
	// BusinessProductPlanInquiryRequest productPlanInquiryRequest = new BusinessProductPlanInquiryRequest();
	//
	// productPlanInquiryRequest.setStartPage(0);
	// productPlanInquiryRequest.setPageSize(999);
	// productPlanInquiryRequest.setPreQueryCount(true);
	// productPlanInquiryRequest.addSortExpressions(new SortExpression("name",
	// Direction.Ascending));
	//
	// BusinessProductPlanCriteria criteria = new BusinessProductPlanCriteria();
	// criteria.setLocationId(businessId);
	//
	// productPlanInquiryRequest.setCriteria(criteria);
	//
	// productPlanInquiryRequest.setUserContext(userContext);
	//
	// BusinessProductPlanResponse productPlanResponse =
	// productPlanBAI.fetchBusinessProductPlanByRequest(productPlanInquiryRequest);
	//
	// Map<String, String> mapObject;
	// List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	//
	// for (BusinessProductPlan productPlan : productPlanResponse.getBusinessProductPlanList())
	// {
	//
	// mapObject = new TreeMap<String, String>();
	// mapObject.put("key", productPlan.getId().toString());
	// mapObject.put("value", productPlan.getName());
	// list.add(mapObject);
	// }
	//
	// return list;
	//
	// }

	/**
	 * Fetch all countries bai.
	 * 
	 * @param countryBAI the country bai
	 * @param userContext the user context
	 * @param code the code
	 * @return the map< string, string>
	 * @throws Exception the exception
	 */
	// public static List<Map<String, String>> fetchAllCountriesBAI(ICountryBAI countryBAI,
	// UserContext userContext, String code) throws Exception
	// {
	// FetchByCodeRequest fetchByCodeRequest = new FetchByCodeRequest();
	// fetchByCodeRequest.setCode(code);
	// fetchByCodeRequest.setUserContext(userContext);
	// CountryResponse countryResponse = countryBAI.fetchAllCountry(fetchByCodeRequest);
	//
	// Map<String, String> mapObject;
	// List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	//
	// if (!ValidationUtil.isNullOrEmpty(countryResponse.getCountryList()))
	// {
	// for (Currency country : countryResponse.getCountryList().get(0).getCurrencyList())
	// {
	// mapObject = new TreeMap<String, String>();
	// mapObject.put("key", country.getCode());
	// mapObject.put("value", country.getName());
	// list.add(mapObject);
	// }
	// }
	//
	// return list;
	// }

	/**
	 * Fetch payer.
	 * 
	 * @param payer the payer
	 * @param userContext the user context
	 * @param criteria the criteria
	 * @return the map< integer, string>
	 */
	// public static List<Map<String, String>> fetchPayer(
	// IPayerBAI payer,
	// UserContext userContext, PayerCriteria criteria)
	// {
	// PayerInquiryRequest payerInquiryRequest = new PayerInquiryRequest();
	//
	// payerInquiryRequest.setStartPage(0);
	// payerInquiryRequest.setPageSize(999);
	// payerInquiryRequest.setPreQueryCount(true);
	// payerInquiryRequest.addSortExpressions(new SortExpression("name",
	// Direction.Ascending));
	// payerInquiryRequest.setUserContext(userContext);
	//
	// payerInquiryRequest.setCriteria(criteria);
	//
	// PayerResponse payerResponse = payer.fetchPayerByRequest(payerInquiryRequest);
	//
	// Map<String, String> mapObject;
	// List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	//
	// for (Payer payers : payerResponse.getPayerList())
	// {
	// mapObject = new TreeMap<String, String>();
	// mapObject.put("key", payers.getId().toString());
	// mapObject.put("value", payers.getName());
	// list.add(mapObject);
	// }
	//
	// return list;
	//
	// }

	/**
	 * Fetch all the security questions.
	 * 
	 * @param securityQuestionBAI
	 * @return
	 * @throws Exception
	 */
	// public static List<Map<String, String>> fetchAllSecurityQuestionBAI(ISecurityQuestionBAI securityQuestionBAI)
	// throws Exception
	// {
	// SecurityQuestionResponse response = securityQuestionBAI.fetchAllSecurityQuestions();
	//
	// Map<String, String> mapObject;
	// List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	//
	// if (!ValidationUtil.isNullOrEmpty(response.getSecurityQuestionList()))
	// {
	// for (SecurityQuestion securityQuestion : response.getSecurityQuestionList())
	// {
	// mapObject = new TreeMap<String, String>();
	// mapObject.put("key", securityQuestion.getId().toString());
	// mapObject.put("value", securityQuestion.getSecurityQuestionKey());
	//
	// list.add(mapObject);
	// }
	// }
	//
	// return list;
	// }

}
