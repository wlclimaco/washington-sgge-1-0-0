package com.sensus.lc.controller.comum;

import java.util.ArrayList;
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
import com.sensus.lc.academia.model.request.AcademiaRequest;
import com.sensus.lc.academia.model.response.AcademiaResponse;
import com.sensus.lc.base.model.TabelaEnum;
import com.sensus.lc.comentario.model.Comentario;
import com.sensus.lc.comentario.model.request.ComentarioRequest;
import com.sensus.lc.comentario.model.request.InquiryComentarioRequest;
import com.sensus.lc.comentario.model.response.ComentarioResponse;
import com.sensus.lc.comentario.model.response.InquiryComentarioResponse;
import com.sensus.lc.comum.bcf.IComumBCF;
import com.sensus.lc.controller.BaseController;
import com.sensus.lc.curtir.model.request.CurtirRequest;
import com.sensus.lc.curtir.model.response.CurtirResponse;
import com.sensus.lc.foto.model.request.FotoRequest;
import com.sensus.lc.foto.model.response.FotoResponse;

/**
 * The Class GroupAPIController.
 */
/**
 * @author QATEmployee
 * 
 */
@Controller
@RequestMapping("/api/comum")
public class ComumAPIController extends BaseController
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
	private static final Logger LOG = LoggerFactory.getLogger(ComumAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "GroupAPIController";

	/** The group bcf. */
	private IComumBCF comumBCF;

	public IComumBCF getComumBCF()
	{
		return comumBCF;
	}

	@Resource
	public void setComumBCF(IComumBCF comumBCF)
	{
		this.comumBCF = comumBCF;
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
	public InquiryComentarioResponse fetchAll(
			@RequestBody InquiryComentarioRequest inquiryComentarioRequest,
			HttpServletRequest request)
	{

		InquiryComentarioResponse inquiryComentarioResponse = new InquiryComentarioResponse();

		try
		{
			// ADD user context to request
			setUserContext(inquiryComentarioRequest, request);

			inquiryComentarioResponse = getComumBCF().fetchComentarioById(inquiryComentarioRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, inquiryComentarioResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return inquiryComentarioResponse;
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
			@RequestBody ComentarioRequest academiaRequest,
			HttpServletRequest request)
	{
		ComentarioResponse academiaResponse = new ComentarioResponse();
		try
		{
			// ADD user context to request
			setUserContext(academiaRequest, request);

			academiaResponse = getComumBCF().updateComentario(academiaRequest);

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
	@RequestMapping(value = "deleteFoto", method = RequestMethod.POST)
	@ResponseBody
	public Response delete(
			@RequestBody Map<String, Object> jsonRequest,
			HttpServletRequest request)
	{
		FotoResponse academiaResponse = new FotoResponse();
		try
		{

			FotoRequest academiaRequest = new FotoRequest();

			// ADD user context to request
			setUserContext(academiaRequest, request);

			// // Verify if exists ids selected
			// if (!ValidationUtil.isNull(jsonRequest.get(SELECTION_PAGINATION_IDS)))
			// {
			// academiaRequest.setSelectionPaginationIds((List<Integer>)jsonRequest.get(SELECTION_PAGINATION_IDS));
			//
			// // Verify if the checkbox "Select All" was marked
			// if (!ValidationUtil.isNull(jsonRequest.get(IS_ALL_ROWS)))
			// {
			// academiaRequest.setPaginationAllSelected(Boolean.valueOf(jsonRequest.get(IS_ALL_ROWS).toString()));
			// }

			academiaResponse = getComumBCF().deleteFoto(academiaRequest);
			// }

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
	@RequestMapping(value = "deleteComentario", method = RequestMethod.POST)
	@ResponseBody
	public Response deleteComentario(
			@RequestBody Map<String, Object> jsonRequest,
			HttpServletRequest request)
	{
		ComentarioResponse academiaResponse = new ComentarioResponse();
		try
		{

			ComentarioRequest academiaRequest = new ComentarioRequest();

			// ADD user context to request
			setUserContext(academiaRequest, request);

			academiaResponse = getComumBCF().deleteComentario(academiaRequest);

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
	public Response insertCurtir(
			@RequestBody Map<String, Object> jsonRequest,
			HttpServletRequest request)
	{
		CurtirResponse academiaResponse = new CurtirResponse();

		try
		{
			// ADD user context to request
			CurtirRequest curtirRequest = new CurtirRequest();

			setUserContext(curtirRequest, request);

			academiaResponse = getComumBCF().insertCurtir(curtirRequest);

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
	@RequestMapping(value = "insertComentario", method = RequestMethod.POST)
	@ResponseBody
	public Response insertComentario(
			@RequestBody Map<String, Object> jsonRequest,
			HttpServletRequest request)
	{
		ComentarioResponse academiaResponse = new ComentarioResponse();

		try
		{
			ComentarioRequest comentarioRequest = new ComentarioRequest();
			// ADD user context to request
			setUserContext(comentarioRequest, request);
			comentarioRequest.setComentarios(new ArrayList<Comentario>());
			comentarioRequest.getComentarios().add(
					new Comentario(Integer.valueOf(jsonRequest.get("id").toString()), jsonRequest.get("texto")
							.toString(), Integer.valueOf(jsonRequest.get("table").toString()), TabelaEnum.ACADEMIA));

			academiaResponse = getComumBCF().insertComentario(comentarioRequest);

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
	@RequestMapping(value = "insertFoto", method = RequestMethod.POST)
	@ResponseBody
	public Response insertFoto(
			@RequestBody Map<String, Object> jsonRequest,
			HttpServletRequest request)
	{
		FotoResponse academiaResponse = new FotoResponse();
		FotoRequest fotoRequest = new FotoRequest();

		try
		{
			// ADD user context to request
			setUserContext(fotoRequest, request);

			academiaResponse = getComumBCF().insertFoto(fotoRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, academiaResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return academiaResponse;

	}
}
