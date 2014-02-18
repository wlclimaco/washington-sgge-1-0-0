package com.sensus.lc.controller.exercicio;

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
import com.sensus.lc.exercicio.bcf.IExercicioBCF;
import com.sensus.lc.exercicio.model.request.ExercicioRequest;
import com.sensus.lc.exercicio.model.request.InquiryExercicioRequest;
import com.sensus.lc.exercicio.model.response.ExercicioResponse;
import com.sensus.lc.exercicio.model.response.InquiryExercicioResponse;

/**
 * The Class GroupAPIController.
 */
/**
 * @author QATEmployee
 * 
 */
@Controller
@RequestMapping("/api/exercicio")
public class ExercicioAPIController extends BaseController
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
	private static final Logger LOG = LoggerFactory.getLogger(ExercicioAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "GroupAPIController";

	/** The group bcf. */
	private IExercicioBCF exercicioBCF;

	/**
	 * Gets the exercicio bcf.
	 * 
	 * @return the exercicio bcf
	 */
	public IExercicioBCF getExercicioBCF()
	{
		return exercicioBCF;
	}

	/**
	 * Sets the exercicio bcf.
	 * 
	 * @param exercicioBCF the new exercicio bcf
	 */
	@Resource
	public void setExercicioBCF(IExercicioBCF exercicioBCF)
	{
		this.exercicioBCF = exercicioBCF;
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
	public InquiryExercicioResponse fetchAll(
			@RequestBody InquiryExercicioRequest inquiryExercicioRequest,
			HttpServletRequest request)
	{

		InquiryExercicioResponse inquiryExercicioResponse = new InquiryExercicioResponse();

		try
		{
			// ADD user context to request
			setUserContext(inquiryExercicioRequest, request);

			inquiryExercicioResponse =
					getExercicioBCF().fetchAllExercicioByUser(inquiryExercicioRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, inquiryExercicioResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return inquiryExercicioResponse;
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

		ExercicioResponse exercicioResponse = new ExercicioResponse();
		try
		{

			ExercicioRequest exercicioRequest = new ExercicioRequest();

			// ADD user context to request
			setUserContext(exercicioRequest, request);

			// Chooses the proper BCF from Action String
			switch (String.valueOf(jsonRequest.get(ACTION)))
			{
				case FETCH_BY_ID:
					// exercicioRequest.setExercicio(new
					// Exercicio(Integer.valueOf(jsonRequest.get(GROUP_ID).toString())));
					// exercicioResponse = getGroupBCF()
					// .fetchGroupById(groupRequest);
					break;
			}
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, exercicioResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return exercicioResponse;
	}

	/**
	 * Update.
	 * 
	 * @param exercicioRequest the group request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public Response update(
			@RequestBody ExercicioRequest exercicioRequest,
			HttpServletRequest request)
	{
		ExercicioResponse exercicioResponse = new ExercicioResponse();
		try
		{
			// ADD user context to request
			setUserContext(exercicioRequest, request);

			exercicioResponse = getExercicioBCF().updateExercicio(exercicioRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, exercicioResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return exercicioResponse;

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
		ExercicioResponse exercicioResponse = new ExercicioResponse();
		try
		{

			ExercicioRequest exercicioRequest = new ExercicioRequest();

			// ADD user context to request
			setUserContext(exercicioRequest, request);

			// Verify if exists ids selected
			if (!ValidationUtil.isNull(jsonRequest.get(SELECTION_PAGINATION_IDS)))
			{
				exercicioRequest
						.setSelectionPaginationIds((List<Integer>)jsonRequest.get(SELECTION_PAGINATION_IDS));

				// Verify if the checkbox "Select All" was marked
				if (!ValidationUtil.isNull(jsonRequest.get(IS_ALL_ROWS)))
				{
					exercicioRequest.setPaginationAllSelected(Boolean.valueOf(jsonRequest.get(IS_ALL_ROWS)
							.toString()));
				}

				exercicioResponse = getExercicioBCF().deleteExercicio(exercicioRequest);
			}

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, exercicioResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return exercicioResponse;

	}

	/**
	 * Insert.
	 * 
	 * @param exercicioRequest the group request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = INSERT, method = RequestMethod.POST)
	@ResponseBody
	public Response insert(
			@RequestBody ExercicioRequest exercicioRequest,
			HttpServletRequest request)
	{
		ExercicioResponse exercicioResponse = new ExercicioResponse();

		try
		{
			// ADD user context to request
			setUserContext(exercicioRequest, request);

			exercicioResponse = getExercicioBCF().insertExercicio(exercicioRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, exercicioResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return exercicioResponse;

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
