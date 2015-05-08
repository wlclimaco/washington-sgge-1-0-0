package com.prosperitasglobal.sendsolv.organization.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.controller.delegate.UtilControllerD;
import com.prosperitasglobal.sendsolv.bai.ILocationBAI;
import com.prosperitasglobal.sendsolv.bai.IOrganizationBAI;
import com.prosperitasglobal.sendsolv.model.request.OrganizationMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.LocationResponse;
import com.prosperitasglobal.sendsolv.model.response.MemberResponse;
import com.prosperitasglobal.sendsolv.model.response.OrganizationResponse;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class OrganizationBaseController.
 *
 * @author Flavio Tosta, Washington Costa
 */
public class OrganizationBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String COUNTRIES = "countries";

	/** The Constant RESPONSE. */
	public static final String RESPONSE = "response";

	/** The Constant NUMBER_OF_EMPLOYEES. */
	public static final String NUMBER_OF_EMPLOYEES = "number_of_employees";

	/** The Constant START_PAGE_NUMBER. */
	private static final int START_PAGE_NUMBER = 0;

	/** The Constant INITIAL_PAGE_SIZE. */
	private static final int INITIAL_PAGE_SIZE = 0;

	/** The Constant NUMBER_OF_MIGRANT_WORKERS. */
	public static final String NUMBER_OF_MIGRANT_WORKERS = "number_of_migrant_workers";

	/** The Constant STATUS. */
	public static final String STATUS = "status";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(OrganizationBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "OrganizationBaseController";

	/** The Constant ENROLLED_LOCATIONS. */
	private static final String ENROLLED_ORGANIZATIONS = "enrolled_organization";

	/** The Constant ENROLLED_LOCATIONS. */
	private static final String ENROLLED_LOCATIONS = "enrolled_locations";

	/** The Organization BAI. */
	private IOrganizationBAI organizationBAI;

	/** The Location BAI. */
	private ILocationBAI locationBAI;

	/**
	 * Gets the location bai.
	 *
	 * @return the location bai
	 */
	public ILocationBAI getLocationBAI()
	{
		return locationBAI;
	}

	/**
	 * Sets the location bai.
	 *
	 * @param locationBAI the location bai
	 */
	@Resource
	public void setLocationBAI(ILocationBAI locationBAI)
	{
		this.locationBAI = locationBAI;
	}

	/**
	 * Gets the organization bai.
	 *
	 * @return the organization bai
	 */
	public IOrganizationBAI getOrganizationBAI()
	{
		return organizationBAI;
	}

	/**
	 * Sets the organization bai.
	 *
	 * @param organizationBAI the organization bai
	 */
	@Resource
	public void setOrganizationBAI(IOrganizationBAI organizationBAI)
	{
		this.organizationBAI = organizationBAI;
	}

	/**
	 * Organization edit mav.
	 *
	 * @param organizationId the organization id
	 * @param returnViewName the return view name
	 * @return the model and view
	 */
	protected ModelAndView organizationEditMAV(Integer organizationId, String returnViewName, HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			modelAndView = listSelectBusiness(modelAndView, request);

			if (!ValidationUtil.isNullOrZero(organizationId))
			{
				// Number of Enrolled Locations
				modelAndView.addObject(ENROLLED_ORGANIZATIONS, fetchEnrolledMembers(organizationId, request));
				modelAndView.addObject(ENROLLED_LOCATIONS, fetchEnrolledLocations(organizationId));

				FetchByIdRequest fetchByIdRequest = new FetchByIdRequest(organizationId);
				fetchByIdRequest.setUserContext(fetchUserContext(request));

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchOrganization(fetchByIdRequest)));

				return modelAndView;
			}

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
	 * Fetch all.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the organization response
	 */
	public OrganizationResponse fetchAllOrganization(PagedInquiryRequest pagedInquiryRequest)
	{

		OrganizationResponse organizationResponse = new OrganizationResponse();
		try
		{

			organizationResponse = getOrganizationBAI().fetchOrganizationByRequest(pagedInquiryRequest);
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				organizationResponse = null;
			}
		}

		return organizationResponse;
	}

	/**
	 * Fetch Organization.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the organization response
	 */
	public OrganizationResponse fetchOrganization(FetchByIdRequest fetchByIdRequest)
	{

		OrganizationResponse organizationResponse = new OrganizationResponse();
		try
		{

			organizationResponse = getOrganizationBAI().fetchOrganizationById(fetchByIdRequest);
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				organizationResponse = null;
			}
		}

		return organizationResponse;
	}

	/**
	 * Fetch location by organization.
	 *
	 * @param organizationId the organization id
	 * @return the location response
	 */
	public LocationResponse fetchLocationByOrganization(Integer organizationId)
	{

		LocationResponse locationResponse = new LocationResponse();
		PagedInquiryRequest pagedInquiryRequest = new PagedInquiryRequest();
		pagedInquiryRequest.setParentId(organizationId);
		pagedInquiryRequest.setStartPage(START_PAGE_NUMBER);
		pagedInquiryRequest.setPageSize(INITIAL_PAGE_SIZE);
		pagedInquiryRequest.setPreQueryCount(true);
		pagedInquiryRequest.addSortExpressions(new SortExpression("name", Direction.Ascending));

		try
		{

			locationResponse =
					getLocationBAI().fetchLocationByOrganization(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return locationResponse;
	}

	/**
	 * Fetch enrolled locations.
	 *
	 * @param organizationId the organization id
	 * @return the integer
	 */
	public Integer fetchEnrolledLocations(Integer organizationId)
	{
		LocationResponse locationResponse = fetchLocationByOrganization(organizationId);

		if (locationResponse.getLocationList() != null)
		{
			return locationResponse.getLocationList().size();
		}

		return 0;
	}

	/**
	 * Edits the.
	 *
	 * @param organizationRequest the organization request
	 * @return the organization response
	 */
	public OrganizationResponse editOrganization(OrganizationMaintenanceRequest organizationRequest)
	{
		OrganizationResponse organizationResponse = new OrganizationResponse();
		try
		{
			organizationResponse = getOrganizationBAI().updateOrganization(organizationRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			organizationResponse = null;
		}
		return organizationResponse;

	}

	/**
	 * Delete.
	 *
	 * @param organizationRequest the organization request
	 * @return the organization response
	 */
	public OrganizationResponse deleteOrganization(OrganizationMaintenanceRequest organizationRequest)
	{
		OrganizationResponse organizationResponse = new OrganizationResponse();
		try
		{

			organizationResponse = getOrganizationBAI().deleteOrganization(organizationRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			organizationResponse = null;
		}

		return organizationResponse;

	}

	/**
	 * Insert.
	 *
	 * @param organizationRequest the organization request
	 * @return the organization response
	 */
	public OrganizationResponse insertOrganization(OrganizationMaintenanceRequest organizationRequest)
	{
		OrganizationResponse organizationResponse = new OrganizationResponse();

		try
		{

			organizationResponse = getOrganizationBAI().insertOrganization(organizationRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			organizationResponse = null;
		}

		return organizationResponse;
	}

	/**
	 * Apply status organization.
	 *
	 * @param organizationRequest the organization request
	 * @return the organization response
	 */
	public OrganizationResponse applyStatusOrganization(OrganizationMaintenanceRequest organizationRequest)
	{
		OrganizationResponse organizationResponse = new OrganizationResponse();

		try
		{

			organizationResponse = getOrganizationBAI().applyStatus(organizationRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			organizationResponse = null;
		}

		return organizationResponse;
	}

	/**
	 * Fetch enrolled members.
	 *
	 * @param locationId the location id
	 * @return the integer
	 */
	private Integer fetchEnrolledMembers(Integer businessId, HttpServletRequest request)
	{
		MemberResponse memberResponse = fetchMembersEnrolledMember(businessId, BusinessTypeEnum.ORGANIZATION, request);

		if (memberResponse.getMemberList() != null)
		{
			return memberResponse.getMemberList().size();
		}

		return 0;
	}

}
