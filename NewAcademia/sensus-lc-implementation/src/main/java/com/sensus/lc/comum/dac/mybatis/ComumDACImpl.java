package com.sensus.lc.comum.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.comentario.model.Comentario;
import com.sensus.lc.comentario.model.request.ComentarioRequest;
import com.sensus.lc.comentario.model.request.InquiryComentarioRequest;
import com.sensus.lc.comum.dac.IComumDAC;
import com.sensus.lc.curtir.model.Curtir;
import com.sensus.lc.curtir.model.request.CurtirRequest;
import com.sensus.lc.curtir.model.request.InquiryCurtirRequest;
import com.sensus.lc.foto.model.Foto;
import com.sensus.lc.foto.model.request.FotoRequest;
import com.sensus.lc.foto.model.request.InquiryFotoRequest;

/**
 * The Class AcademiaDACImpl.
 * 
 * @author Washington
 */
public class ComumDACImpl extends SqlSessionDaoSupport implements IComumDAC
{
	private static final String FETCH_ALL_ACADEMIA_BY_USER = "fetchAllAcademiaByUser";

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant ACADEMIA_TYPE_VALUE. */
	private static final Integer ACADEMIA_TYPE_VALUE = 1;

	/** The Constant PARAMSIZE2. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE3. */
	private static final Integer PARAMSIZE3 = 3;

	/** The Constant PARAMSIZE4. */
	private static final Integer PARAMSIZE4 = 4;

	/** The Constant PARAMSIZE5. */
	private static final int PARAMSIZE5 = 5;

	/** The Constant PARAMSIZE6. */
	private static final Integer PARAMSIZE6 = 6;

	/** The Constant PARAMSIZE10. */
	private static final Integer PARAMSIZE10 = 10;

	private static final Integer PARAMSIZE11 = 11;

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant TENANTID. */
	private static final String TENANTID = "tenantid";

	/** The Constant SELECTED_IDS. */
	private static final String PAGINATION_ALL_SELECTED = "paginationAllSelected";

	/** The Constant SELECTION_PAGINATION_IDS. */
	private static final String SELECTION_PAGINATION_IDS = "selectionPaginationIds";

	/** The Constant PAGE_SIZE. */
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */
	private static final String START_ROW = "startRow";

	/** The Constant ORDERBY. */
	private static final String ORDERBY = "orderBy";

	/** The Constant START_PAGE. */
	private static final String START_PAGE = "startPage";

	private static final String USER = "user";

	// -------------------------------------------------------------------------
	// MyBatis mapping IDs.

	/** The Constant ACADEMIA_MAP. */
	private static final String COMUM_MAP = "Comum.";

	private static final String DELETE_FOTO = COMUM_MAP + "deleteFoto";

	private static final String DELETE_CURTIR = COMUM_MAP + "deletCurtir";

	private static final String DELETE_COMENTAIO = COMUM_MAP + "deleteComentario";

	private static final String FETCH_ALL_COMENTARIO_BY_USER = COMUM_MAP + "comentarioFoto";

	private static final String FETCH_ALL_CURTIR_BY_USER = COMUM_MAP + "comentarioFoto";

	private static final String FETCH_ALL_FOTO_BY_USER = COMUM_MAP + "comentarioFoto";

	private static final String FETCH_COMENTARIO_BY_ID = COMUM_MAP + "comentarioFoto";

	private static final String FETCH_CURTIR_BY_ID = COMUM_MAP + "comentarioFoto";

	private static final String FETCH_FOTO_BY_ID = COMUM_MAP + "comentarioFoto";

	private static final String INSERT_COMENTARIO = COMUM_MAP + "comentarioFoto";

	private static final String INSERT_FOTO = COMUM_MAP + "comentarioFoto";

	private static final String UPDATE_COMENTARIO = COMUM_MAP + "comentarioFoto";

	private static final String UPDATE_CURTIR = COMUM_MAP + "comentarioFoto";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	@Override
	public InternalResultsResponse<Comentario> fetchAllComentarios(InquiryComentarioRequest inquiryComentarioRequest)
	{
		InternalResultsResponse<Comentario> response = new InternalResultsResponse<Comentario>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(SELECTION_PAGINATION_IDS, inquiryComentarioRequest.getSelectionPaginationIds());
		paramMap.put(PAGINATION_ALL_SELECTED, inquiryComentarioRequest.getPaginationAllSelected());
		paramMap.put(TENANTID, inquiryComentarioRequest.getUserContext().getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryComentarioRequest.getPageSize());
		paramMap.put(START_PAGE, inquiryComentarioRequest.getStartPage());
		paramMap.put(START_ROW, inquiryComentarioRequest.getStartRow());
		paramMap.put(USER, inquiryComentarioRequest.getUserContext().getUserId());
		paramMap.put(ORDERBY, "cdcomentario");

		if (!ValidationUtil.isNullOrEmpty(inquiryComentarioRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, "cdcomentario");
		}

		doQueryForList(getSqlSession(), FETCH_ALL_COMENTARIO_BY_USER, paramMap, response);
		Integer totalRows =
				(Integer)doQueryForObject(getSqlSession(), "ComentarioPaginationTotalRowsByUser", paramMap);
		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);

		return response;
	}

	@Override
	public InternalResultsResponse<Comentario> fetchComentarioById(InquiryComentarioRequest inquiryComentarioRequest)
	{
		InternalResultsResponse<Comentario> response = new InternalResultsResponse<Comentario>();
		response.addResults(
				SensusMyBatisDacHelper
						.doQueryForList(getSqlSession(), FETCH_COMENTARIO_BY_ID, inquiryComentarioRequest));

		return response;
	}

	@Override
	public InternalResultsResponse<Comentario> insertComentario(ComentarioRequest dietaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE10);

		paramMap.put("cdcomentario", dietaRequest.getFirstComentario().getCdcomentario());
		paramMap.put("dtpost", dietaRequest.getFirstComentario().getDtpost());
		paramMap.put("coment", dietaRequest.getFirstComentario().getComent());
		paramMap.put("acaoComentarioEnum", dietaRequest.getFirstComentario().getAcaoComentarioEnum());
		paramMap.put("curtis", dietaRequest.getFirstComentario().getCurtis());

		Integer academiaId =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_COMENTARIO, paramMap);

		InternalResultsResponse<Comentario> response = new InternalResultsResponse<Comentario>();

		if (!ValidationUtil.isNull(academiaId))
		{
			dietaRequest.getFirstComentario().setCdcomentario(academiaId);
			response.addResult(dietaRequest.getFirstComentario());
			return response;
		}

		response.setStatus(Status.NoRowsInsertedError);
		return response;
	}

	@Override
	public InternalResultsResponse<Comentario> updateComentario(ComentarioRequest comentarioRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE11);

		paramMap.put("cdcomentario", comentarioRequest.getFirstComentario().getCdcomentario());
		paramMap.put("dtpost", comentarioRequest.getFirstComentario().getDtpost());
		paramMap.put("coment", comentarioRequest.getFirstComentario().getComent());
		paramMap.put("acaoComentarioEnum", comentarioRequest.getFirstComentario().getAcaoComentarioEnum());
		paramMap.put("curtis", comentarioRequest.getFirstComentario().getCurtis());

		String result = (String)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), UPDATE_COMENTARIO, paramMap);

		InternalResultsResponse<Comentario> response = new InternalResultsResponse<Comentario>();

		if (ValidationUtil.isNull(result))
		{
			response.setStatus(Status.NoRowsUpdatedError);
		}
		else
		{
			response.getResultsList().add(comentarioRequest.getFirstComentario());
		}

		return response;
	}

	@Override
	public InternalResponse deleteComentario(ComentarioRequest dietaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put("Cdcomentario", dietaRequest.getFirstComentario().getCdcomentario());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_COMENTAIO, paramMap);

		InternalResponse response = new InternalResponse();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.setStatus(InternalResponse.Status.NoRowsRemovedError);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Curtir> fetchAllCurtirs(InquiryCurtirRequest inquiryCurtirRequest)
	{
		InternalResultsResponse<Curtir> response = new InternalResultsResponse<Curtir>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(SELECTION_PAGINATION_IDS, inquiryCurtirRequest.getSelectionPaginationIds());
		paramMap.put(PAGINATION_ALL_SELECTED, inquiryCurtirRequest.getPaginationAllSelected());
		paramMap.put(TENANTID, inquiryCurtirRequest.getUserContext().getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryCurtirRequest.getPageSize());
		paramMap.put(START_PAGE, inquiryCurtirRequest.getStartPage());
		paramMap.put(START_ROW, inquiryCurtirRequest.getStartRow());
		paramMap.put(USER, inquiryCurtirRequest.getUserContext().getUserId());
		paramMap.put(ORDERBY, "cdCurtir");

		if (!ValidationUtil.isNullOrEmpty(inquiryCurtirRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, "cdCurtir");
		}

		doQueryForList(getSqlSession(), FETCH_ALL_CURTIR_BY_USER, paramMap, response);
		Integer totalRows =
				(Integer)doQueryForObject(getSqlSession(), "CurtirPaginationTotalRowsByUser", paramMap);
		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);

		return response;

	}

	@Override
	public InternalResultsResponse<Curtir> fetchCurtirById(InquiryCurtirRequest inquiryCurtirRequest)
	{
		InternalResultsResponse<Curtir> response = new InternalResultsResponse<Curtir>();
		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_CURTIR_BY_ID, inquiryCurtirRequest));

		return response;
	}

	@Override
	public InternalResultsResponse<Curtir> insertCurtir(CurtirRequest dietaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE10);

		paramMap.put("cdCurtir", dietaRequest.getFirstCurtir().getCdCurtir());
		paramMap.put("curtirTypeEnum", dietaRequest.getFirstCurtir().getCurtirTypeEnum());
		paramMap.put("acaoEnum", dietaRequest.getFirstCurtir().getAcaoEnum());
		// paramMap.put("pessoa", dietaRequest.getFirstCurtir().getPessoa());
		// paramMap.put("cdEvento", dietaRequest.getFirstCurtir().getCdEvento());

		Integer academiaId =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_COMENTARIO, paramMap);

		InternalResultsResponse<Curtir> response = new InternalResultsResponse<Curtir>();

		if (!ValidationUtil.isNull(academiaId))
		{
			dietaRequest.getFirstCurtir().setCdCurtir(academiaId);
			response.addResult(dietaRequest.getFirstCurtir());
			return response;
		}

		response.setStatus(Status.NoRowsInsertedError);
		return response;
	}

	@Override
	public InternalResultsResponse<Curtir> updateCurtir(CurtirRequest curtirRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE11);

		paramMap.put("cdCurtir", curtirRequest.getFirstCurtir().getCdCurtir());
		paramMap.put("curtirTypeEnum", curtirRequest.getFirstCurtir().getCurtirTypeEnum());
		paramMap.put("acaoEnum", curtirRequest.getFirstCurtir().getAcaoEnum());
		// paramMap.put("pessoa", curtirRequest.getFirstCurtir().getPessoa());
		// paramMap.put("cdEvento", curtirRequest.getFirstCurtir().getCdEvento());

		String result = (String)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), UPDATE_CURTIR, paramMap);

		InternalResultsResponse<Curtir> response = new InternalResultsResponse<Curtir>();

		if (ValidationUtil.isNull(result))
		{
			response.setStatus(Status.NoRowsUpdatedError);
		}
		else
		{
			response.getResultsList().add(curtirRequest.getFirstCurtir());
		}

		return response;
	}

	@Override
	public InternalResponse deleteCurtir(CurtirRequest dietaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put("cdfoto", dietaRequest.getFirstCurtir().getCdCurtir());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_CURTIR, paramMap);

		InternalResponse response = new InternalResponse();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.setStatus(InternalResponse.Status.NoRowsRemovedError);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Foto> fetchAllFotos(InquiryFotoRequest inquiryFotoRequest)
	{
		InternalResultsResponse<Foto> response = new InternalResultsResponse<Foto>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(SELECTION_PAGINATION_IDS, inquiryFotoRequest.getSelectionPaginationIds());
		paramMap.put(PAGINATION_ALL_SELECTED, inquiryFotoRequest.getPaginationAllSelected());
		paramMap.put(TENANTID, inquiryFotoRequest.getUserContext().getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryFotoRequest.getPageSize());
		paramMap.put(START_PAGE, inquiryFotoRequest.getStartPage());
		paramMap.put(START_ROW, inquiryFotoRequest.getStartRow());
		paramMap.put(USER, inquiryFotoRequest.getUserContext().getUserId());
		paramMap.put(ORDERBY, "cdFoto");

		if (!ValidationUtil.isNullOrEmpty(inquiryFotoRequest.getSortExpressions()))
		{
			paramMap.put(ORDERBY, "cdFoto");
		}

		doQueryForList(getSqlSession(), FETCH_ALL_FOTO_BY_USER, paramMap, response);
		Integer totalRows =
				(Integer)doQueryForObject(getSqlSession(), "FotoPaginationTotalRowsByUser", paramMap);
		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);

		return response;
	}

	@Override
	public InternalResultsResponse<Foto> fetchFotoById(InquiryFotoRequest inquiryFotoRequest)
	{
		InternalResultsResponse<Foto> response = new InternalResultsResponse<Foto>();
		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_FOTO_BY_ID, inquiryFotoRequest));

		return response;
	}

	@Override
	public InternalResultsResponse<Foto> insertFoto(FotoRequest dietaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE10);

		paramMap.put("cdfoto", dietaRequest.getFirstFoto().getCdfoto());
		paramMap.put("foto", dietaRequest.getFirstFoto().getCdfoto());

		Integer academiaId =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_FOTO, paramMap);

		InternalResultsResponse<Foto> response = new InternalResultsResponse<Foto>();

		if (!ValidationUtil.isNull(academiaId))
		{
			dietaRequest.getFirstFoto().setCdfoto(academiaId);
			response.addResult(dietaRequest.getFirstFoto());
			return response;
		}

		response.setStatus(Status.NoRowsInsertedError);
		return response;
	}

	@Override
	public InternalResultsResponse<Foto> updateFoto(FotoRequest fotoRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE11);

		paramMap.put("cdfoto", fotoRequest.getFirstFoto().getCdfoto());
		paramMap.put("foto", fotoRequest.getFirstFoto().getCdfoto());

		String result = (String)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), UPDATE_CURTIR, paramMap);

		InternalResultsResponse<Foto> response = new InternalResultsResponse<Foto>();

		if (ValidationUtil.isNull(result))
		{
			response.setStatus(Status.NoRowsUpdatedError);
		}
		else
		{
			response.getResultsList().add(fotoRequest.getFirstFoto());
		}

		return response;
	}

	@Override
	public InternalResponse deleteFoto(FotoRequest dietaRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put("cdfoto", dietaRequest.getFirstFoto().getCdfoto());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_FOTO, paramMap);

		InternalResponse response = new InternalResponse();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.setStatus(InternalResponse.Status.NoRowsRemovedError);
		}

		return response;
	}

}
