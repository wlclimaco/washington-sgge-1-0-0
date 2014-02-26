package com.sensus.lc.comum.bcl.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.csv.CSVColumn;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.comentario.model.Comentario;
import com.sensus.lc.comentario.model.request.ComentarioRequest;
import com.sensus.lc.comentario.model.request.InquiryComentarioRequest;
import com.sensus.lc.comum.bcl.IComumBCL;
import com.sensus.lc.comum.dac.IComumDAC;
import com.sensus.lc.curtir.model.Curtir;
import com.sensus.lc.curtir.model.request.CurtirRequest;
import com.sensus.lc.curtir.model.request.InquiryCurtirRequest;
import com.sensus.lc.foto.model.Foto;
import com.sensus.lc.foto.model.request.FotoRequest;
import com.sensus.lc.foto.model.request.InquiryFotoRequest;
import com.sensus.lc.process.bcl.IProcessBCL;

// TODO: Auto-generated Javadoc
/**
 * The Class AcademiaBCLImpl.
 * 
 * @author Washington.
 */
public class ComumBCLImpl implements IComumBCL
{

	/** The log. */
	/** The log. */
	private static transient Logger LOG = LoggerFactory.getLogger(ComumBCLImpl.class);

	// Spring injection points:

	/** The temp upload file path. */
	private String tempUploadFilePath;

	/** The academia dac. */
	private IComumDAC comumDAC;

	/** The process bcl. */
	private IProcessBCL processBCL;

	/** The all possible columns. */
	private List<CSVColumn> allPossibleColumns;

	/**
	 * Gets the temp upload file path.
	 * 
	 * @return the temp upload file path
	 */
	public String getTempUploadFilePath()
	{
		return tempUploadFilePath;
	}

	/**
	 * Sets the temp upload file path.
	 * 
	 * @param tempUploadFilePath the new temp upload file path
	 */
	public void setTempUploadFilePath(String tempUploadFilePath)
	{
		this.tempUploadFilePath = tempUploadFilePath;
	}

	public IComumDAC getComumDAC()
	{
		return comumDAC;
	}

	public void setComumDAC(IComumDAC comumDAC)
	{
		this.comumDAC = comumDAC;
	}

	/**
	 * Gets the process bcl.
	 * 
	 * @return the process bcl
	 */
	public IProcessBCL getProcessBCL()
	{
		return processBCL;
	}

	/**
	 * Sets the process bcl.
	 * 
	 * @param processBCL the new process bcl
	 */
	public void setProcessBCL(IProcessBCL processBCL)
	{
		this.processBCL = processBCL;
	}

	/**
	 * Gets the all possible columns.
	 * 
	 * @return the all possible columns
	 */
	public List<CSVColumn> getAllPossibleColumns()
	{
		return allPossibleColumns;
	}

	/**
	 * Sets the all possible columns.
	 * 
	 * @param allPossibleColumns the new all possible columns
	 */
	public void setAllPossibleColumns(List<CSVColumn> allPossibleColumns)
	{
		this.allPossibleColumns = allPossibleColumns;
	}

	@Override
	public InternalResultsResponse<Comentario> fetchAllComentarios(InquiryComentarioRequest inquiryComentarioRequest)
	{
		return getComumDAC().fetchAllComentarios(inquiryComentarioRequest);
	}

	@Override
	public InternalResultsResponse<Comentario> fetchComentarioById(InquiryComentarioRequest inquiryComentarioRequest)
	{
		return getComumDAC().fetchComentarioById(inquiryComentarioRequest);
	}

	@Override
	public InternalResultsResponse<Comentario> insertComentario(ComentarioRequest dietaRequest)
	{
		InternalResultsResponse<Comentario> response = getComumDAC().insertComentario(dietaRequest);

		// if (!response.isInError())
		// {
		// // create a Process for the created group
		// InternalResponse processResponse = insertProcess(dietaRequest, LCActionTypeEnum.INSERT_GROUP);
		// response.addMessages(processResponse.getMessageInfoList());
		//
		// // insert default values to analytics summarized tables.
		// AnalyticsRequest analyticsRequest = new AnalyticsRequest();
		// analyticsRequest.setGroup(new AnalyticsGroup(response.getFirstResult().getId()));
		// analyticsRequest.setUserContext(groupRequest.getUserContext());
		//
		// InternalResponse internalResponse = getAnalyticsBCL().insertAnalyticsSummarized(analyticsRequest);
		//
		// if (internalResponse.isInError())
		// {
		// response.addMessages(response.getMessageInfoList());
		// }
		// }
		return response;
	}

	@Override
	public InternalResponse updateComentario(ComentarioRequest dietaRequest)
	{
		InternalResponse response = getComumDAC().updateComentario(dietaRequest);

		// if (!response.isInError())
		// {
		// InternalResponse processResponse = insertProcess(dietaRequest, LCActionTypeEnum.UPDATE_GROUP);
		//
		// response.addMessages(processResponse.getMessageInfoList());
		// }
		return response;
	}

	@Override
	public InternalResponse deleteComentario(ComentarioRequest dietaRequest)
	{

		return getComumDAC().deleteComentario(dietaRequest);

	}

	@Override
	public InternalResultsResponse<Curtir> fetchAllCurtirs(InquiryCurtirRequest inquiryCurtirRequest)
	{
		return getComumDAC().fetchAllCurtirs(inquiryCurtirRequest);
	}

	@Override
	public InternalResultsResponse<Curtir> fetchCurtirById(InquiryCurtirRequest inquiryCurtirRequest)
	{
		return getComumDAC().fetchCurtirById(inquiryCurtirRequest);
	}

	@Override
	public InternalResultsResponse<Curtir> insertCurtir(CurtirRequest dietaRequest)
	{
		InternalResultsResponse<Curtir> response = getComumDAC().insertCurtir(dietaRequest);

		// if (!response.isInError())
		// {
		// // create a Process for the created group
		// InternalResponse processResponse = insertProcess(dietaRequest, LCActionTypeEnum.INSERT_GROUP);
		// response.addMessages(processResponse.getMessageInfoList());
		//
		// // insert default values to analytics summarized tables.
		// AnalyticsRequest analyticsRequest = new AnalyticsRequest();
		// analyticsRequest.setGroup(new AnalyticsGroup(response.getFirstResult().getId()));
		// analyticsRequest.setUserContext(groupRequest.getUserContext());
		//
		// InternalResponse internalResponse = getAnalyticsBCL().insertAnalyticsSummarized(analyticsRequest);
		//
		// if (internalResponse.isInError())
		// {
		// response.addMessages(response.getMessageInfoList());
		// }
		// }
		return response;
	}

	@Override
	public InternalResponse updateCurtir(CurtirRequest dietaRequest)
	{
		InternalResponse response = getComumDAC().updateCurtir(dietaRequest);

		// if (!response.isInError())
		// {
		// InternalResponse processResponse = insertProcess(dietaRequest, LCActionTypeEnum.UPDATE_GROUP);
		//
		// response.addMessages(processResponse.getMessageInfoList());
		// }
		return response;
	}

	@Override
	public InternalResponse deleteCurtir(CurtirRequest dietaRequest)
	{
		return getComumDAC().deleteCurtir(dietaRequest);
	}

	@Override
	public InternalResultsResponse<Foto> fetchAllFotos(InquiryFotoRequest inquiryFotoRequest)
	{
		return getComumDAC().fetchAllFotos(inquiryFotoRequest);
	}

	@Override
	public InternalResultsResponse<Foto> fetchFotoById(InquiryFotoRequest inquiryFotoRequest)
	{
		return getComumDAC().fetchFotoById(inquiryFotoRequest);
	}

	@Override
	public InternalResultsResponse<Foto> insertFoto(FotoRequest dietaRequest)
	{
		InternalResultsResponse<Foto> response = getComumDAC().insertFoto(dietaRequest);

		// if (!response.isInError())
		// {
		// // create a Process for the created group
		// InternalResponse processResponse = insertProcess(groupRequest, LCActionTypeEnum.INSERT_GROUP);
		// response.addMessages(processResponse.getMessageInfoList());
		//
		// // insert default values to analytics summarized tables.
		// AnalyticsRequest analyticsRequest = new AnalyticsRequest();
		// analyticsRequest.setGroup(new AnalyticsGroup(response.getFirstResult().getId()));
		// analyticsRequest.setUserContext(groupRequest.getUserContext());
		//
		// InternalResponse internalResponse = getAnalyticsBCL().insertAnalyticsSummarized(analyticsRequest);
		//
		// if (internalResponse.isInError())
		// {
		// response.addMessages(response.getMessageInfoList());
		// }
		// }
		return response;
	}

	@Override
	public InternalResponse updateFoto(FotoRequest dietaRequest)
	{
		InternalResponse response = getComumDAC().updateFoto(dietaRequest);

		// if (!response.isInError())
		// {
		// InternalResponse processResponse = insertProcess(dietaRequest, LCActionTypeEnum.UPDATE_GROUP);
		//
		// response.addMessages(processResponse.getMessageInfoList());
		// }
		return response;
	}

	@Override
	public InternalResponse deleteFoto(FotoRequest dietaRequest)
	{
		return getComumDAC().deleteFoto(dietaRequest);
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

}
