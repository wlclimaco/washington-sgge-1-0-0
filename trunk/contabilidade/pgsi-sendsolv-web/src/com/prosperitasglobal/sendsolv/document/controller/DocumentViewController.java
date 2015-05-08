package com.prosperitasglobal.sendsolv.document.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.bai.ICountryBAI;
import com.prosperitasglobal.cbof.bai.IDocumentTypeBAI;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.controller.delegate.ListD;
import com.prosperitasglobal.sendsolv.common.controller.BaseViewController;

/**
 * The OrganizationViewController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */

@Controller
@RequestMapping("/document")
public class DocumentViewController extends BaseViewController
{
	/** The URL mapping constants. */
	private static final String FETCH_EDIT = "/edit";

	/** The view mapping constants . */
	private static final String VIEW_DOCUMENT_MAIN = "/document/document_create";

	/** The Constant VIEW_IDENTIFICATION_MAIN. */
	private static final String VIEW_IDENTIFICATION_MAIN = "/identification/identification_create";

	/** The Constant DOCUMENT_ID. */
	private static final String DOCUMENT_ID = "parentKeyType";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DocumentViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "DocumentViewController";

	/** The Response constants. */
	public static final String DOCUMENTTYPE = "documentType";

	/** The Constant ISSUE_COUNTRY. */
	public static final String KNOWN_COUNTRIES = "known_countries";

	/** The document type bai. */
	private IDocumentTypeBAI documentTypeBAI;

	/** The country bai. */
	private ICountryBAI countryBAI;

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
	 * Load add/edit view.
	 *
	 * @param parentKeyType the parent key type
	 * @return the model and view
	 */
	@RequestMapping(value = {FETCH_EDIT}, method = RequestMethod.GET)
	public ModelAndView loadUpdate(@RequestParam(value = DOCUMENT_ID, required = false) Integer parentKeyType,
			HttpServletRequest request)
	{

		ModelAndView modelAndView = new ModelAndView();

		try
		{

			if (parentKeyType == BusinessTypeEnum.ORGANIZATION.getValue())
			{
				modelAndView.setViewName(VIEW_DOCUMENT_MAIN);

				modelAndView
				.addObject(
						DOCUMENTTYPE,
						getMapper().writeValueAsString(
								ListD.fetchSDocumentTypeByCode(
										documentTypeBAI, fetchUserContext(request), parentKeyType)));
			}

			else if (parentKeyType == BusinessTypeEnum.MEMBER.getValue())
			{
				modelAndView.setViewName(VIEW_IDENTIFICATION_MAIN);
			}

			modelAndView
					.addObject(
							KNOWN_COUNTRIES,
							getMapper().writeValueAsString(
									ListD.fetchAllKnownCountries(countryBAI, fetchUserContext(request))));

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
}
