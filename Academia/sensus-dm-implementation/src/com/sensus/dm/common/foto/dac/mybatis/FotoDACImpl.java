package com.sensus.dm.common.foto.dac.mybatis;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.foto.dac.IFotoDAC;
import com.sensus.dm.common.foto.model.Foto;
import com.sensus.dm.common.foto.model.request.FotoRequest;
import com.sensus.dm.common.foto.model.request.InquiryFotoRequest;

/**
 * The Class FotoDACImpl.
 * 
 * @author Washington
 */
public class FotoDACImpl extends SqlSessionDaoSupport implements IFotoDAC
{
	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant FOTO_TYPE_VALUE. */
	private static final Integer FOTO_TYPE_VALUE = 1;

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

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant CDFOTO. */
	private static final String CDFOTO = "cdfoto";
	/** The Constant DTPOST. */
	private static final String DTPOST = "dtpost";
	/** The Constant COMENT. */
	private static final String COMENT = "coment";
	/** The Constant FOTO. */
	private static final String FOTO = "foto";

	// -------------------------------------------------------------------------
	// MyBatis mapping IDs.

	/** The Constant FOTO_MAP. */
	private static final String FOTO_MAP = "FotoMap.";

	/** The Constant DELETE_FOTO. */
	private static final String DELETE_FOTO = FOTO_MAP + "deleteFoto";

	/** The Constant FETCH_ALL_FOTOS. */
	private static final String FETCH_ALL_FOTOS = FOTO_MAP + "fetchAllFotos";

	/** The Constant FETCH_DEVICES_BY_FOTOS. */
	private static final String FETCH_DEVICES_BY_FOTOS = FOTO_MAP + "fetchDevicesByName";

	/** The Constant INSERT_FOTO. */
	private static final String INSERT_FOTO = FOTO_MAP + "insertFoto";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = FOTO_MAP + "paginationTotalRows";

	/** The Constant UPDATE_FOTO. */
	private static final String UPDATE_FOTO = FOTO_MAP + "updateFoto";

	/** The Constant FETCH_SCHEDULE_BY_ID. */
	private static final String FETCH_SCHEDULE_BY_ID = FOTO_MAP + "fetchDevicesByFotos";

	private static final String CUSTOMER_ID = null;

	private static final String MODIFY_USER = null;

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.foto.dac.IFotoDAC#deleteFoto(com.sensus.dm.common.foto.model.request.FotoRequest)
	 */
	@Override
	public InternalResponse deleteFoto(FotoRequest fotoRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(CDFOTO, fotoRequest.getFirstFoto().getCdfoto());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_FOTO, paramMap);

		InternalResponse response = new InternalResponse();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.setStatus(InternalResponse.Status.NoRowsRemovedError);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.foto.dac.IFotoDAC#insertFoto(com.sensus.dm.common.foto.model.request.FotoRequest)
	 */
	@Override
	public InternalResultsResponse<Foto> insertFoto(FotoRequest fotoRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE10);

		paramMap.put(CDFOTO, fotoRequest.getFirstFoto().getCdfoto());
		paramMap.put(DTPOST, fotoRequest.getFirstFoto().getDtpost());
		paramMap.put(COMENT, fotoRequest.getFirstFoto().getComent());
		paramMap.put(FOTO, fotoRequest.getFirstFoto().getFoto());
		paramMap.put(CUSTOMER_ID, fotoRequest.getTenant().getKey());

		Integer fotoId =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_FOTO, paramMap);

		InternalResultsResponse<Foto> response = new InternalResultsResponse<Foto>();

		if (!ValidationUtil.isNull(fotoId))
		{
			fotoRequest.getFirstFoto().setCdfoto(fotoId);
			response.addResult(fotoRequest.getFirstFoto());
			return response;
		}

		response.setStatus(Status.NoRowsInsertedError);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.foto.dac.IFotoDAC#updateFoto(com.sensus.dm.common.foto.model.request.FotoRequest)
	 */
	@Override
	public InternalResultsResponse<Foto> updateFoto(FotoRequest fotoRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		paramMap.put(CDFOTO, fotoRequest.getFirstFoto().getCdfoto());
		paramMap.put(DTPOST, fotoRequest.getFirstFoto().getDtpost());
		paramMap.put(COMENT, fotoRequest.getFirstFoto().getComent());
		paramMap.put(FOTO, fotoRequest.getFirstFoto().getFoto());
		paramMap.put(MODIFY_USER, fotoRequest.getUserContext().getUserId());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), UPDATE_FOTO, paramMap);

		InternalResultsResponse<Foto> response = new InternalResultsResponse<Foto>();

		if (ValidationUtil.isNullOrZero(result))
		{
			response.setStatus(Status.NoRowsUpdatedError);
		}
		else
		{
			response.getResultsList().add(fotoRequest.getFirstFoto());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.foto.dac.IFotoDAC#fetchAllFotos(com.sensus.dm.common.foto.model.request.InquiryFotoRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Foto> fetchAllFotos(InquiryFotoRequest inquiryFotoRequest)
	{
		InternalResultsResponse<Foto> response = new InternalResultsResponse<Foto>();

		if (inquiryFotoRequest.isPreQueryCount())
		{
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)SensusMyBatisDacHelper.doQueryForObject(
							getSqlSession(), PAGINATION_TOTAL_ROWS, inquiryFotoRequest));

			if (ValidationUtil.isNullOrZero(response.getResultsSetInfo().getTotalRowsAvailable()))
			{
				return response;
			}
		}

		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_FOTOS, inquiryFotoRequest));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.foto.dac.IFotoDAC#fetchDevicesByFoto(com.sensus.dm.common.foto.model.request.FotoRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Foto> fetchFotosByName(InquiryFotoRequest fotoRequest)
	{
		InternalResultsResponse<Foto> response = new InternalResultsResponse<Foto>();

		response.getResultsList().addAll(SensusMyBatisDacHelper.doQueryForList(getSqlSession(),
				FETCH_DEVICES_BY_FOTOS, fotoRequest));

		return response;

	}

	@Override
	public InternalResultsResponse<Foto> fetchFotosById(InquiryFotoRequest fotoRequest)
	{
		InternalResultsResponse<Foto> response = new InternalResultsResponse<Foto>();

		SensusMyBatisDacHelper.doQueryForList(
				getSqlSession(), FETCH_SCHEDULE_BY_ID, fotoRequest.getFirstFoto(), response);

		DMConvertUtil.checkResult(response);

		return response;
	}

}
