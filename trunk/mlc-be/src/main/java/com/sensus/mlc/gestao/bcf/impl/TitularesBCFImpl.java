package com.sensus.mlc.titulares.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.titulares.bcf.ITitularesBCF;
import com.sensus.mlc.titulares.bcl.ITitularesBCL;
import com.sensus.mlc.titulares.model.Titulares;
import com.sensus.mlc.titulares.model.request.TitularesRequest;
import com.sensus.mlc.titulares.model.request.InquiryTitularesRequest;
import com.sensus.mlc.titulares.model.response.TitularesResponse;
import com.sensus.mlc.titulares.model.response.InquiryTitularesResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.titularesBCFImpl;

public class TitularesBCFImpl implements ITitularesBCF
{

	/** The Constant NAME_LENGTH. */
	private static final Integer NAME_LENGTH = 255;
 
	/** The Constant SENSUS_EPM_ACTIONVALIDATOR_NAME_INVALID. */ 
	private static final String SENSUS_EPM_ACTIONVALIDATOR_NAME_INVALID = 
			"sensus.epm.actionvalidator.name.invalid"; 

	/** The Constant DEFAULT_GROUP_BCF_EXCEPTION_MSG. */ 
	private static final String DEFAULT_EMPRESA_BCF_EXCEPTION_MSG = "sensus.mlc.groupbcfimpl.defaultexception"; 
 
	/** The Constant DEFAULT_GROUP_BCL_EXCEPTION_MSG. */ 
	private static final String DEFAULT_EMPRESA_BCL_EXCEPTION_MSG = "sensus.mlc.groupbclimpl.defaultexception";
 
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TitularesBCFImpl.class); 
 
	private ITitularesBCL titularesBCL; // injected by Spring through setter
 
	@Override 
	public TitularesResponse insertTitulares(TitularesRequest titularesRequest)
	{
		TitularesResponse response = new TitularesResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, titularesRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Titulares> internalResponse = getTitularesBCL().insertTitulares(titularesRequest); 
				response.setTitulares(internalResponse.getResultsList()); 
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}
		} 
		catch (Exception ex) 
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public TitularesResponse updateTitulares(TitularesRequest titularesRequest)
	{
		TitularesResponse response = new TitularesResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, titularesRequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Titulares> internalResponse = getTitularesBCL().updateTitulares(titularesRequest);
				response.setTitulares(internalResponse.getResultsList());
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCL_EXCEPTION_MSG);
			}
		} 
		catch (Exception ex) 
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		} 
		return response;  
	} 
   
	@Override  
	public TitularesResponse deleteTitulares(TitularesRequest titularesRequest) 
	{  
		TitularesResponse response = new TitularesResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, titularesRequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getTitularesBCL().deleteTitulares(titularesRequest); 
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		}  
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
  
	@Override 
	public InquiryTitularesResponse fetchAllTitulares(InquiryTitularesRequest inquirytitularesRequest) 
	{ 
		InquiryTitularesResponse response = new InquiryTitularesResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Titulares> internalResponse =  
						getTitularesBCL().fetchAllTitulares(inquirytitularesRequest); 
				response.setTitulares(internalResponse.getResultsList());
				response.setResultsSetInfo(internalResponse.getResultsSetInfo());
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
   
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		}  
		return response;
	} 
   
	@Override  
	public TitularesResponse fetchTitularesById(TitularesRequest titularesRequest) 
	{ 
		TitularesResponse response = new TitularesResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, titularesRequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Titulares> internalResponse = getTitularesBCL().fetchTitularesById(titularesRequest); 
				response.setTitulares(internalResponse.getResultsList());
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			} 
		}  
		catch (Exception ex)  
		{      
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);  
		}
		return response;  
	}
  
	@Override 
	public TitularesResponse fetchAllTitularesFilial(TitularesRequest titularesRequest) { 
		TitularesResponse response = new TitularesResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryTitularesRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Titulares> internalResponse =  
						getTitularesBCL().fetchAllTitularesFilial(titularesRequest);  
				response.setTitulares(internalResponse.getResultsList());   
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		} 
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
   
   
	public ITitularesBCL getTitularesBCL() 
	{       
		return titularesBCL; 
	}  
    
	public void setTitularesBCL(ITitularesBCL titularesBCL) 
	{ 
		this.titularesBCL = titularesBCL; 
	} 
}  
