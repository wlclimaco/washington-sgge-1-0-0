package com.sensus.lc.controller.grupomuscular;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.controller.BaseController;
import com.sensus.lc.grupomuscular.bcf.IGrupomuscularBCF;
import com.sensus.lc.grupomuscular.model.request.GrupomuscularRequest;
import com.sensus.lc.grupomuscular.model.request.InquiryGrupomuscularRequest;
import com.sensus.lc.grupomuscular.model.response.GrupomuscularResponse;
import com.sensus.lc.grupomuscular.model.response.InquiryGrupomuscularResponse;

/**
 * The Class GroupAPIController.
 */
/**
 * @author QATEmployee
 * 
 */
@Controller
@RequestMapping("/api/grupoMuscular")
public class GrupoMuscularAPIController extends BaseController
{

	/** The Constant ACTION. */
	private static final String ACTION = "action";

	/** The Constant DELETE. */
	private static final String DELETE = "/delete";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/fetchall";

	/** The Constant FETCH_BY_ID. */
	private static final String FETCH_BY_ID = "fetchById";

	/** The Constant FETCH_COUNT. */
	private static final String FETCH_COUNT = "/fetchcount";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";

	/** The Constant GROUP_ID. */
	private static final String GROUP_ID = "groupId";

	/** The Constant INSERT. */
	private static final String INSERT = "/insert";

	/** The Constant IS_ALL_ROWS. */
	private static final String IS_ALL_ROWS = "isAllRows";

	/** The Constant SELECTION_PAGINATION_IDS. */
	private static final String SELECTION_PAGINATION_IDS = "selectionPaginationIds";

	/** The Constant UPDATE. */
	private static final String UPDATE = "/update";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(GrupoMuscularAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "GroupAPIController";

	/** The group bcf. */
	private IGrupomuscularBCF grupomuscularBCF;

	/**
	 * Gets the grupomuscular bcf.
	 * 
	 * @return the grupomuscular bcf
	 */
	public IGrupomuscularBCF getGrupomuscularBCF()
	{
		return grupomuscularBCF;
	}

	/**
	 * Sets the grupomuscular bcf.
	 * 
	 * @param grupomuscularBCF the new grupomuscular bcf
	 */
	@Resource
	public void setGrupomuscularBCF(IGrupomuscularBCF grupomuscularBCF)
	{
		this.grupomuscularBCF = grupomuscularBCF;
	}

	/**
	 * Fetch all.
	 * 
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @param request the request
	 * @return the inquiry group response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public InquiryGrupomuscularResponse fetchAll(
			@RequestBody InquiryGrupomuscularRequest inquiryGrupomuscularRequest,
			HttpServletRequest request)
	{

		InquiryGrupomuscularResponse inquiryGrupomuscularResponse = new InquiryGrupomuscularResponse();

		try
		{
			// ADD user context to request
			setUserContext(inquiryGrupomuscularRequest, request);

			inquiryGrupomuscularResponse =
					getGrupomuscularBCF().fetchAllGrupomuscularByUser(inquiryGrupomuscularRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, inquiryGrupomuscularResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return inquiryGrupomuscularResponse;
	}

	/**
	 * Fetch.
	 * 
	 * @param jsonRequest the json request
	 * @param request the request
	 * @return the group response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public Response fetch(
			@RequestBody Map<String, Object> jsonRequest,
			HttpServletRequest request)
	{

		GrupomuscularResponse grupomuscularResponse = new GrupomuscularResponse();
		try
		{

			GrupomuscularRequest grupomuscularRequest = new GrupomuscularRequest();

			// ADD user context to request
			setUserContext(grupomuscularRequest, request);

			// Chooses the proper BCF from Action String
			switch (String.valueOf(jsonRequest.get(ACTION)))
			{
				case FETCH_BY_ID:
					// grupomuscularRequest.setGrupomuscular(new
					// Grupomuscular(Integer.valueOf(jsonRequest.get(GROUP_ID).toString())));
					// grupomuscularResponse = getGroupBCF()
					// .fetchGroupById(groupRequest);
					break;
			}
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, grupomuscularResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return grupomuscularResponse;
	}

	/**
	 * Update.
	 * 
	 * @param grupomuscularRequest the group request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public Response update(
			@RequestBody GrupomuscularRequest grupomuscularRequest,
			HttpServletRequest request)
	{
		GrupomuscularResponse grupomuscularResponse = new GrupomuscularResponse();
		try
		{
			// ADD user context to request
			setUserContext(grupomuscularRequest, request);

			grupomuscularResponse = getGrupomuscularBCF().updateGrupomuscular(grupomuscularRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, grupomuscularResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return grupomuscularResponse;

	}

	/**
	 * Delete.
	 * 
	 * @param jsonRequest the json request
	 * @param request the request
	 * @return the response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = DELETE, method = RequestMethod.POST)
	@ResponseBody
	public Response delete(
			@RequestBody Map<String, Object> jsonRequest,
			HttpServletRequest request)
	{
		GrupomuscularResponse grupomuscularResponse = new GrupomuscularResponse();
		try
		{

			GrupomuscularRequest grupomuscularRequest = new GrupomuscularRequest();

			// ADD user context to request
			setUserContext(grupomuscularRequest, request);

			// Verify if exists ids selected
			if (!ValidationUtil.isNull(jsonRequest.get(SELECTION_PAGINATION_IDS)))
			{
				grupomuscularRequest
						.setSelectionPaginationIds((List<Integer>)jsonRequest.get(SELECTION_PAGINATION_IDS));

				// Verify if the checkbox "Select All" was marked
				if (!ValidationUtil.isNull(jsonRequest.get(IS_ALL_ROWS)))
				{
					grupomuscularRequest.setPaginationAllSelected(Boolean.valueOf(jsonRequest.get(IS_ALL_ROWS)
							.toString()));
				}

				grupomuscularResponse = getGrupomuscularBCF().deleteGrupomuscular(grupomuscularRequest);
			}

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, grupomuscularResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return grupomuscularResponse;

	}

	/**
	 * Insert.
	 * 
	 * @param grupomuscularRequest the group request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = INSERT, method = RequestMethod.POST)
	@ResponseBody
	public Response insert(
			@RequestBody GrupomuscularRequest grupomuscularRequest,
			HttpServletRequest request)
	{
		GrupomuscularResponse grupomuscularResponse = new GrupomuscularResponse();

		try
		{
			// ADD user context to request
			setUserContext(grupomuscularRequest, request);

			grupomuscularResponse = getGrupomuscularBCF().insertGrupomuscular(grupomuscularRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, grupomuscularResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return grupomuscularResponse;

	}
	//
	// /**
	// * Fetch count lights from groups.
	// *
	// * @param groupRequest the group request
	// * @param request the request
	// * @return the response
	// */
	// @RequestMapping(value = FETCH_COUNT, method = RequestMethod.POST)
	// @ResponseBody
	// public Response fetchCountLightsFromGroups(
	// @RequestBody GroupRequest groupRequest,
	// HttpServletRequest request)
	// {
	//
	// GroupResponse groupResponse = new GroupResponse();
	//
	// try
	// {
	//
	// setUserContext(groupRequest, request);
	//
	// groupResponse = getGroupBCF().fetchCountLightsFromGroups(groupRequest);
	//
	// }
	// catch (Exception e)
	// {
	// SensusInterfaceUtil.handleException(LOG, groupResponse, e, DEFAULT_EXCEPTION_MSG,
	// new String[] {CONTROLLER_EXCEPTION_MSG});
	// }
	//
	// return groupResponse;
	//
	// }

}
