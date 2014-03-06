package com.sensus.lc.controller.foto;

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
import com.sensus.lc.academia.bcf.IAcademiaBCF;
import com.sensus.lc.academia.model.request.AcademiaRequest;
import com.sensus.lc.academia.model.request.InquiryAcademiaRequest;
import com.sensus.lc.academia.model.response.AcademiaResponse;
import com.sensus.lc.academia.model.response.InquiryAcademiaResponse;
import com.sensus.lc.controller.BaseController;

// TODO: Auto-generated Javadoc
/**
 * The Class GroupAPIController.
 */
/**
 * @author QATEmployee
 * 
 */
@Controller
@RequestMapping("/api/foto")
public class FotoAPIController extends BaseController
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
	private static final Logger LOG = LoggerFactory.getLogger(FotoAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "GroupAPIController";

	/** The group bcf. */
	private IAcademiaBCF academiaBCF;

	/**
	 * Gets the academia bcf.
	 * 
	 * @return the academia bcf
	 */
	public IAcademiaBCF getAcademiaBCF()
	{
		return academiaBCF;
	}

	/**
	 * Sets the academia bcf.
	 * 
	 * @param academiaBCF the new academia bcf
	 */
	@Resource
	public void setAcademiaBCF(IAcademiaBCF academiaBCF)
	{
		this.academiaBCF = academiaBCF;
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
	public InquiryAcademiaResponse fetchAll(
			@RequestBody InquiryAcademiaRequest inquiryAcademiaRequest,
			HttpServletRequest request)
	{

		InquiryAcademiaResponse inquiryAcademiaResponse = new InquiryAcademiaResponse();

		try
		{
			// ADD user context to request
			setUserContext(inquiryAcademiaRequest, request);

			inquiryAcademiaResponse = getAcademiaBCF().fetchAllAcademiaByUser(inquiryAcademiaRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, inquiryAcademiaResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return inquiryAcademiaResponse;
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

		AcademiaResponse academiaResponse = new AcademiaResponse();
		try
		{

			AcademiaRequest academiaRequest = new AcademiaRequest();

			// ADD user context to request
			setUserContext(academiaRequest, request);

			// Chooses the proper BCF from Action String
			switch (String.valueOf(jsonRequest.get(ACTION)))
			{
				case FETCH_BY_ID:
					// academiaRequest.setAcademia(new Academia(Integer.valueOf(jsonRequest.get(GROUP_ID).toString())));
					// academiaResponse = getGroupBCF()
					// .fetchGroupById(groupRequest);
					break;
			}
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, academiaResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return academiaResponse;
	}

	/**
	 * Update.
	 * 
	 * @param academiaRequest the group request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public Response update(
			@RequestBody AcademiaRequest academiaRequest,
			HttpServletRequest request)
	{
		AcademiaResponse academiaResponse = new AcademiaResponse();
		try
		{
			// ADD user context to request
			setUserContext(academiaRequest, request);

			academiaResponse = getAcademiaBCF().updateAcademia(academiaRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, academiaResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return academiaResponse;

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
		AcademiaResponse academiaResponse = new AcademiaResponse();
		try
		{

			AcademiaRequest academiaRequest = new AcademiaRequest();

			// ADD user context to request
			setUserContext(academiaRequest, request);

			// Verify if exists ids selected
			if (!ValidationUtil.isNull(jsonRequest.get(SELECTION_PAGINATION_IDS)))
			{
				academiaRequest.setSelectionPaginationIds((List<Integer>)jsonRequest.get(SELECTION_PAGINATION_IDS));

				// Verify if the checkbox "Select All" was marked
				if (!ValidationUtil.isNull(jsonRequest.get(IS_ALL_ROWS)))
				{
					academiaRequest.setPaginationAllSelected(Boolean.valueOf(jsonRequest.get(IS_ALL_ROWS).toString()));
				}

				academiaResponse = getAcademiaBCF().deleteAcademia(academiaRequest);
			}

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, academiaResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return academiaResponse;

	}

	/**
	 * Insert.
	 * 
	 * @param academiaRequest the group request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = INSERT, method = RequestMethod.POST)
	@ResponseBody
	public Response insert(
			@RequestBody AcademiaRequest academiaRequest,
			HttpServletRequest request)
	{
		AcademiaResponse academiaResponse = new AcademiaResponse();

		try
		{
			// ADD user context to request
			setUserContext(academiaRequest, request);

			academiaResponse = getAcademiaBCF().insertAcademia(academiaRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, academiaResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return academiaResponse;

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
