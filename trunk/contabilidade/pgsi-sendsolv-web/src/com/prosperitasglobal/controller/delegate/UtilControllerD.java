package com.prosperitasglobal.controller.delegate;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.bai.ICodeValueBAI;
import com.prosperitasglobal.cbof.bai.ICountryBAI;
import com.prosperitasglobal.cbof.bai.IDocumentTypeBAI;
import com.prosperitasglobal.cbof.bai.IIndustryClassificationBAI;
import com.prosperitasglobal.cbof.bai.ILanguageBAI;
import com.prosperitasglobal.cbof.bai.INameSupplementBAI;
import com.prosperitasglobal.cbof.bai.IRangeBAI;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.CodeValueEnum;
import com.prosperitasglobal.cbof.model.RangeEnum;
import com.prosperitasglobal.sendsolv.bai.IMemberBAI;
import com.prosperitasglobal.sendsolv.bai.IProductPlanBAI;
import com.prosperitasglobal.sendsolv.bai.ISecurityQuestionBAI;
import com.prosperitasglobal.sendsolv.common.controller.BaseController;
import com.prosperitasglobal.sendsolv.model.criteria.MemberCriteria;
import com.prosperitasglobal.sendsolv.model.request.MemberInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.MemberResponse;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;
import com.qat.framework.model.UserContext;

/**
 * The Class CountyBaseController.
 *
 * @author Flavio Tosta, Washington Costa
 */
public class UtilControllerD extends BaseController
{

	/** The Constant SECURITY_QUESTION. */
	private static final String SECURITY_QUESTION = "security_question";

	/** The Constant COUNTRY_BAI. */
	private static final String COUNTRY_BAI = "countryBAI";

	/** The Constant SUFFIX. */
	private static final String SUFFIX = "suffix";

	/** The Constant PREFIX. */
	private static final String PREFIX = "prefix";

	public static final String COUNTRIES = "countries";
	private static final String KNOWN_COUNTRIES = "known_countries";

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

	/** The Constant USA. */
	private static final String USA = "USA";

	/** The Constant START_PAGE_NUMBER. */
	private static final int START_PAGE_NUMBER = 0;

	/** The Constant INITIAL_PAGE_SIZE. */
	private static final int INITIAL_PAGE_SIZE = 0;

	/** The range. */
	private IRangeBAI range;

	/** The code value bai. */
	private ICodeValueBAI codeValueBAI;

	/** The product plan bai. */
	private IProductPlanBAI productPlanBAI;

	/** The industry classification bai. */
	private IIndustryClassificationBAI industryClassificationBAI;

	/** The country bai. */
	private ICountryBAI countryBAI;

	/** The name supplement bai. */
	private INameSupplementBAI nameSupplementBAI;

	/** The document type bai. */
	private IDocumentTypeBAI documentTypeBAI;

	/** The language bai. */
	private ILanguageBAI languageBAI;

	/** The member bai. */
	private IMemberBAI memberBAI;

	/** The security question. */
	private ISecurityQuestionBAI securityQuestion;

	/**
	 * @return the securityQuestion
	 */
	public ISecurityQuestionBAI getSecurityQuestion()
	{
		return securityQuestion;
	}

	/**
	 * @param securityQuestion the securityQuestion to set
	 */
	@Resource
	public void setSecurityQuestion(ISecurityQuestionBAI securityQuestion)
	{
		this.securityQuestion = securityQuestion;
	}

	/**
	 * Gets the member BAI.
	 *
	 * @return the member BAI
	 */
	public IMemberBAI getMemberBAI()
	{
		return memberBAI;
	}

	/**
	 * Sets the member bai.
	 *
	 * @param memberBAI the member bai
	 */
	@Resource
	public void setMemberBAI(IMemberBAI memberBAI)
	{
		this.memberBAI = memberBAI;
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
	 * Gets the code value bai.
	 *
	 * @return the code value bai
	 */
	public ICodeValueBAI getCodeValueBAI()
	{
		return codeValueBAI;
	}

	/**
	 * Sets the code value bai.
	 *
	 * @param codeValueBAI the code value bai
	 */
	@Resource
	public void setCodeValueBAI(ICodeValueBAI codeValueBAI)
	{
		this.codeValueBAI = codeValueBAI;
	}

	/**
	 * Gets the language bai.
	 *
	 * @return the language bai
	 */
	public ILanguageBAI getLanguageBAI()
	{
		return languageBAI;
	}

	/**
	 * Sets the language bai.
	 *
	 * @param languageBAI the language bai
	 */
	@Resource
	public void setLanguageBAI(ILanguageBAI languageBAI)
	{
		this.languageBAI = languageBAI;
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
	 * Gets the name supplement bai.
	 *
	 * @return the name supplement bai
	 */
	public INameSupplementBAI getNameSupplementBAI()
	{
		return nameSupplementBAI;
	}

	/**
	 * Sets the name supplement bai.
	 *
	 * @param nameSupplementBAI the name supplement bai
	 */
	@Resource
	public void setNameSupplementBAI(INameSupplementBAI nameSupplementBAI)
	{
		this.nameSupplementBAI = nameSupplementBAI;
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
	 * Sets the ranger.
	 *
	 * @param range the ranger
	 */
	@Resource
	public void setRanger(IRangeBAI range)
	{
		this.range = range;
	}

	/**
	 * Gets the range.
	 *
	 * @return the range
	 */
	public IRangeBAI getRange()
	{
		return range;
	}

	/**
	 * Gets the industry classification bai.
	 *
	 * @return the industry classification bai
	 */
	public IIndustryClassificationBAI getIndustryClassificationBAI()
	{
		return industryClassificationBAI;
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
	 * Sets the industry classification bai.
	 *
	 * @param industryClassificationBAI the industry classification bai
	 */
	@Resource
	public void setIndustryClassificationBAI(IIndustryClassificationBAI industryClassificationBAI)
	{
		this.industryClassificationBAI = industryClassificationBAI;
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
					COUNTRIES,
					getMapper().writeValueAsString(
							ListD.fetchAllCountries(countryBAI, fetchUserContext(request))));

			modelAndView
			.addObject(
					KNOWN_COUNTRIES,
					getMapper().writeValueAsString(
							ListD.fetchAllKnownCountries(countryBAI, fetchUserContext(request))));
			modelAndView
			.addObject(
					PHONE_CODES,
					getMapper().writeValueAsString(
							ListD.fetchAllPhoneCodes(countryBAI, fetchUserContext(request))));

			modelAndView
			.addObject(
					NUMBER_OF_EMPLOYEES,
					getMapper().writeValueAsString(
							ListD.fetchRange(RangeEnum.NUMBER_OF_EMPLOYEES, range,
									fetchUserContext(request))));

			modelAndView
			.addObject(
					NUMBER_OF_MIGRANT_WORKERS,
					getMapper().writeValueAsString(
							ListD.fetchRange(RangeEnum.NUMBER_OF_MIGRANT_WORKERS, range,
									fetchUserContext(request))));

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

		return modelAndView;
	}

	/**
	 * List select contact.
	 *
	 * @param modelAndView the model and view
	 * @return the model and view
	 */
	protected ModelAndView listSelectContact(ModelAndView modelAndView, HttpServletRequest request)
	{
		try
		{
			modelAndView
			.addObject(
					COUNTRIES,
					getMapper().writeValueAsString(
							ListD.fetchAllCountries(countryBAI, fetchUserContext(request))));
			modelAndView
			.addObject(
					KNOWN_COUNTRIES,
					getMapper().writeValueAsString(
							ListD.fetchAllKnownCountries(countryBAI, fetchUserContext(request))));

			modelAndView
			.addObject(
					PHONE_CODES,
					getMapper().writeValueAsString(
							ListD.fetchAllPhoneCodes(countryBAI, fetchUserContext(request))));

			modelAndView
			.addObject(
					PREFIX,
					getMapper().writeValueAsString(
							ListD.fetchPrefixSuffix(CodeValueEnum.PREFIX, nameSupplementBAI,
									fetchUserContext(request))));

			modelAndView
			.addObject(
					SUFFIX,
					getMapper().writeValueAsString(
							ListD.fetchPrefixSuffix(CodeValueEnum.SUFFIX, nameSupplementBAI,
									fetchUserContext(request))));

			modelAndView
			.addObject(
					"documentType",
					getMapper().writeValueAsString(
							ListD.fetchSDocumentTypeByCode(documentTypeBAI, fetchUserContext(request),
									BusinessTypeEnum.MEMBER.getValue())));

			modelAndView
			.addObject(
					COUNTRY_BAI,
					getMapper().writeValueAsString(
							ListD.fetchStateProvinceRegionByCountryCode(
									countryBAI, fetchUserContext(request), USA)));

			modelAndView
			.addObject(
					"languageResponse",
					getMapper().writeValueAsString(
							ListD.fetchSLanguageByCode(languageBAI, fetchUserContext(request))));

			modelAndView
					.addObject(
							SECURITY_QUESTION,
							getMapper().writeValueAsString(
									ListD.fetchAllSecurityQuestionBAI(securityQuestion)));

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
	 * List select transfer.
	 *
	 * @param modelAndView the model and view
	 * @return the model and view
	 */
	protected ModelAndView listSelectTransfer(ModelAndView modelAndView, HttpServletRequest request)
	{
		try
		{
			modelAndView
			.addObject(
					COUNTRIES,
					getMapper().writeValueAsString(
							ListD.fetchAllCountries(countryBAI, fetchUserContext(request))));
			modelAndView
			.addObject(
					COUNTRY_BAI,
					getMapper().writeValueAsString(
							ListD.fetchStateProvinceRegionByCountryCode(
									countryBAI, fetchUserContext(request), USA)));

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
	 * List select document type.
	 *
	 * @param businessType the business type
	 * @return the map< string, string>
	 */
	protected List<Map<String, String>> listSelectDocumentType(Integer businessType, UserContext userContext)
	{
		try
		{
			return ListD.fetchSDocumentTypeByCodeSSN(documentTypeBAI, userContext, businessType);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			return null;
		}
	}

	/**
	 * List sic naics.
	 *
	 * @param codeValueEnum the code value enum
	 * @return the map< string, string>
	 */
	protected List<Map<String, String>> listSicNaics(Integer codeValueEnum, String userId)
	{
		try
		{
			UserContext userContext = new UserContext();
			userContext.setUserId(userId);
			return ListD.fetchSicNaics(CodeValueEnum.enumForValue(codeValueEnum), codeValueBAI,
					userContext);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			return null;
		}
	}

	/**
	 * Checks for initial load.
	 *
	 * @param request the request
	 * @param modelAndView the model and view
	 * @return the boolean
	 */
	public Boolean isInitialLoad(HttpServletRequest request, ModelAndView modelAndView)
	{
		if (FALSE.equals(request.getParameter(INITIAL_LOAD)))
		{
			modelAndView.addObject(REFRESH, REFRESH);

			return false;
		}

		return true;
	}

	public MemberResponse fetchMembersEnrolledMember(Integer locationId, BusinessTypeEnum businessTypeEnum,
			HttpServletRequest request)
	{
		MemberResponse memberResponse = new MemberResponse();
		MemberInquiryRequest memberInquiryRequest = new MemberInquiryRequest();
		memberInquiryRequest.setParentId(locationId);
		memberInquiryRequest.setStartPage(START_PAGE_NUMBER);
		memberInquiryRequest.setPageSize(INITIAL_PAGE_SIZE);
		memberInquiryRequest.setPreQueryCount(true);
		memberInquiryRequest.addSortExpressions(new SortExpression("firstName", Direction.Ascending));

		MemberCriteria memberCriteria = new MemberCriteria();
		memberCriteria.setBusinessId(locationId);
		memberCriteria.setBusinessType(businessTypeEnum);

		memberInquiryRequest.setUserContext(fetchUserContext(request));

		memberInquiryRequest.setCriteria(memberCriteria);

		try
		{

			memberResponse = getMemberBAI().fetchMemberByRequest(memberInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return memberResponse;
	}
}
