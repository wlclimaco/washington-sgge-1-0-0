package com.qat.webdaptive.controller.delegate;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.qat.samples.sysmgmt.entidade.bai.IEmpresaBAI;

public class UtilControllerD extends BaseController
{

	/** The Constant RESPONSE. */
	public static final String RESPONSE = "response";

	/** The Constant NUMBER_OF_EMPLOYEES. */
	public static final String NUMBER_OF_EMPLOYEES = "number_of_employees";

	/** The Constant NUMBER_OF_MIGRANT_WORKERS. */
	public static final String NUMBER_OF_MIGRANT_WORKERS = "number_of_migrant_workers";

	/** The Constant PHONE_CODES. */
	public static final String PHONE_CODES = "phone_codes";

	/** The Constant STATUS. */
	public static final String STATUS = "status";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(UtilControllerD.class);

	/** The Constant REFRESH. */
	private static final String REFRESH = "refresh";

	/** The Constant INITIAL_LOAD. */
	public static final String INITIAL_LOAD = "initialLoad";

	/** The Constant FALSE. */
	private static final String FALSE = "false";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "UtilControllerD";

	private IEmpresaBAI empresaBAI;

	private IPessoaBAI pessoaBAI;

	/**
	 * @return the empresaBAI
	 */
	public IEmpresaBAI getEmpresaBAI()
	{
		return empresaBAI;
	}

	/**
	 * @param empresaBAI the empresaBAI to set
	 */
	@Resource
	public void setEmpresaBAI(IEmpresaBAI empresaBAI)
	{
		this.empresaBAI = empresaBAI;
	}

	/**
	 * @return the pessoaBAI
	 */
	public IPessoaBAI getPessoaBAI()
	{
		return pessoaBAI;
	}

	/**
	 * @param pessoaBAI the pessoaBAI to set
	 */
	@Resource
	public void setPessoaBAI(IPessoaBAI pessoaBAI)
	{
		this.pessoaBAI = pessoaBAI;
	}

	/**
	 * County mav.
	 * 
	 * @param modelAndView the model and view
	 * @return the model and view
	 */
	protected ModelAndView listSelectBusiness(ModelAndView modelAndView, HttpServletRequest request)
	{

		try
		{
			modelAndView
					.addObject(
							"cnae",
							getMapper().writeValueAsString(
									ListD.fetchAllCnaes(getEmpresaBAI(), fetchUserContext(request))));

			modelAndView
					.addObject(
							"regime",
							getMapper().writeValueAsString(
									ListD.fetchAllRegime(getEmpresaBAI(), fetchUserContext(request))));

			return modelAndView;

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				modelAndView.addObject(RESPONSE, null);
			}
		}

		return new ModelAndView();
	}

	protected ModelAndView listSelectCidade(ModelAndView modelAndView, HttpServletRequest request)
	{

		try
		{
			modelAndView
					.addObject(
							"estado",
							getMapper().writeValueAsString(
									ListD.fetchAllEstado(getPessoaBAI(), fetchUserContext(request))));

			return modelAndView;

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				modelAndView.addObject(RESPONSE, null);
			}
		}

		return new ModelAndView();
	}

	//
	// /**
	// * List select contact.
	// *
	// * @param modelAndView the model and view
	// * @return the model and view
	// */
	protected ModelAndView listSelectContact(ModelAndView modelAndView, HttpServletRequest request)
	{
		// try
		// {
		// modelAndView
		// .addObject(
		// COUNTRIES,
		// getMapper().writeValueAsString(
		// ListD.fetchAllCountries(countryBAI, fetchUserContext(request))));
		// modelAndView
		// .addObject(
		// KNOWN_COUNTRIES,
		// getMapper().writeValueAsString(
		// ListD.fetchAllKnownCountries(countryBAI, fetchUserContext(request))));
		//
		// modelAndView
		// .addObject(
		// PHONE_CODES,
		// getMapper().writeValueAsString(
		// ListD.fetchAllPhoneCodes(countryBAI, fetchUserContext(request))));
		//
		// modelAndView
		// .addObject(
		// PREFIX,
		// getMapper().writeValueAsString(
		// ListD.fetchPrefixSuffix(CodeValueEnum.PREFIX, nameSupplementBAI,
		// fetchUserContext(request))));
		//
		// modelAndView
		// .addObject(
		// SUFFIX,
		// getMapper().writeValueAsString(
		// ListD.fetchPrefixSuffix(CodeValueEnum.SUFFIX, nameSupplementBAI,
		// fetchUserContext(request))));
		//
		// modelAndView
		// .addObject(
		// "documentType",
		// getMapper().writeValueAsString(
		// ListD.fetchSDocumentTypeByCode(documentTypeBAI, fetchUserContext(request),
		// BusinessTypeEnum.MEMBER.getValue())));
		//
		// modelAndView
		// .addObject(
		// COUNTRY_BAI,
		// getMapper().writeValueAsString(
		// ListD.fetchStateProvinceRegionByCountryCode(
		// countryBAI, fetchUserContext(request), USA)));
		//
		// modelAndView
		// .addObject(
		// "languageResponse",
		// getMapper().writeValueAsString(
		// ListD.fetchSLanguageByCode(languageBAI, fetchUserContext(request))));
		//
		// modelAndView
		// .addObject(
		// SECURITY_QUESTION,
		// getMapper().writeValueAsString(
		// ListD.fetchAllSecurityQuestionBAI(securityQuestion)));
		//
		// }
		// catch (Exception e)
		// {
		// if (LOG.isErrorEnabled())
		// {
		// LOG.error(CONTROLLER_EXCEPTION_MSG, e);
		// modelAndView.addObject(RESPONSE, null);
		// }
		// }
		//
		return modelAndView;
		//
	}

	//
	// /**
	// * List select transfer.
	// *
	// * @param modelAndView the model and view
	// * @return the model and view
	// */
	// protected ModelAndView listSelectTransfer(ModelAndView modelAndView, HttpServletRequest request)
	// {
	// try
	// {
	// modelAndView
	// .addObject(
	// COUNTRIES,
	// getMapper().writeValueAsString(
	// ListD.fetchAllCountries(countryBAI, fetchUserContext(request))));
	// modelAndView
	// .addObject(
	// COUNTRY_BAI,
	// getMapper().writeValueAsString(
	// ListD.fetchStateProvinceRegionByCountryCode(
	// countryBAI, fetchUserContext(request), USA)));
	//
	// }
	// catch (Exception e)
	// {
	// if (LOG.isErrorEnabled())
	// {
	// LOG.error(CONTROLLER_EXCEPTION_MSG, e);
	// modelAndView.addObject(RESPONSE, null);
	// }
	// }
	//
	// return modelAndView;
	//
	// }
	//
	// /**
	// * List select document type.
	// *
	// * @param businessType the business type
	// * @return the map< string, string>
	// */
	// protected List<Map<String, String>> listSelectDocumentType(Integer businessType, UserContext userContext)
	// {
	// try
	// {
	// return ListD.fetchSDocumentTypeByCodeSSN(documentTypeBAI, userContext, businessType);
	// }
	// catch (Exception e)
	// {
	// LOG.error(CONTROLLER_EXCEPTION_MSG, e);
	// return null;
	// }
	// }
	//
	// /**
	// * List sic naics.
	// *
	// * @param codeValueEnum the code value enum
	// * @return the map< string, string>
	// */
	// protected List<Map<String, String>> listSicNaics(Integer codeValueEnum, String userId)
	// {
	// try
	// {
	// UserContext userContext = new UserContext();
	// userContext.setUserId(userId);
	// return ListD.fetchSicNaics(CodeValueEnum.enumForValue(codeValueEnum), codeValueBAI,
	// userContext);
	// }
	// catch (Exception e)
	// {
	// LOG.error(CONTROLLER_EXCEPTION_MSG, e);
	// return null;
	// }
	// }
	//
	// /**
	// * Checks for initial load.
	// *
	// * @param request the request
	// * @param modelAndView the model and view
	// * @return the boolean
	// */
	public Boolean isInitialLoad(HttpServletRequest request, ModelAndView modelAndView)
	{
		if (FALSE.equals(request.getParameter(INITIAL_LOAD)))
		{
			modelAndView.addObject(REFRESH, REFRESH);

			return false;
		}

		return true;
	}
	//
	// public MemberResponse fetchMembersEnrolledMember(Integer locationId, BusinessTypeEnum businessTypeEnum,
	// HttpServletRequest request)
	// {
	// MemberResponse memberResponse = new MemberResponse();
	// MemberInquiryRequest memberInquiryRequest = new MemberInquiryRequest();
	// memberInquiryRequest.setParentId(locationId);
	// memberInquiryRequest.setStartPage(START_PAGE_NUMBER);
	// memberInquiryRequest.setPageSize(INITIAL_PAGE_SIZE);
	// memberInquiryRequest.setPreQueryCount(true);
	// memberInquiryRequest.addSortExpressions(new SortExpression("firstName", Direction.Ascending));
	//
	// MemberCriteria memberCriteria = new MemberCriteria();
	// memberCriteria.setBusinessId(locationId);
	// memberCriteria.setBusinessType(businessTypeEnum);
	//
	// memberInquiryRequest.setUserContext(fetchUserContext(request));
	//
	// memberInquiryRequest.setCriteria(memberCriteria);
	//
	// try
	// {
	//
	// memberResponse = getMemberBAI().fetchMemberByRequest(memberInquiryRequest);
	//
	// }
	// catch (Exception e)
	// {
	// if (LOG.isErrorEnabled())
	// {
	// LOG.error(CONTROLLER_EXCEPTION_MSG, e);
	// }
	// }
	//
	// return memberResponse;
	// }
}
