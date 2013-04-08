package com.sensus.mlc.tipcliente.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.tipcliente.bcf.ITipclienteBCF;
import com.sensus.mlc.tipcliente.bcl.ITipclienteBCL;
import com.sensus.mlc.tipcliente.model.Tipcliente;
import com.sensus.mlc.tipcliente.model.request.TipclienteRequest;
import com.sensus.mlc.tipcliente.model.request.InquiryTipclienteRequest;
import com.sensus.mlc.tipcliente.model.response.TipclienteResponse;
import com.sensus.mlc.tipcliente.model.response.InquiryTipclienteResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.tipclienteBCFImpl;

public class TipclienteBCFImpl implements ITipclienteBCF
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
	private static final Logger LOG = LoggerFactory.getLogger(TipclienteBCFImpl.class); 
 
	private ITipclienteBCL tipclienteBCL; // injected by Spring through setter
 
	@Override 
	public TipclienteResponse insertTipcliente(TipclienteRequest tipclienteRequest)
	{
		TipclienteResponse response = new TipclienteResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, tipclienteRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Tipcliente> internalResponse = getTipclienteBCL().insertTipcliente(tipclienteRequest); 
				response.setTipcliente(internalResponse.getResultsList()); 
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
	public TipclienteResponse updateTipcliente(TipclienteRequest tipclienteRequest)
	{
		TipclienteResponse response = new TipclienteResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, tipclienteRequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Tipcliente> internalResponse = getTipclienteBCL().updateTipcliente(tipclienteRequest);
				response.setTipcliente(internalResponse.getResultsList());
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
	public TipclienteResponse deleteTipcliente(TipclienteRequest tipclienteRequest) 
	{  
		TipclienteResponse response = new TipclienteResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, tipclienteRequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getTipclienteBCL().deleteTipcliente(tipclienteRequest); 
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
	public InquiryTipclienteResponse fetchAllTipcliente(InquiryTipclienteRequest inquirytipclienteRequest) 
	{ 
		InquiryTipclienteResponse response = new InquiryTipclienteResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Tipcliente> internalResponse =  
						getTipclienteBCL().fetchAllTipcliente(inquirytipclienteRequest); 
				response.setTipcliente(internalResponse.getResultsList());
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
	public TipclienteResponse fetchTipclienteById(TipclienteRequest tipclienteRequest) 
	{ 
		TipclienteResponse response = new TipclienteResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, tipclienteRequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Tipcliente> internalResponse = getTipclienteBCL().fetchTipclienteById(tipclienteRequest); 
				response.setTipcliente(internalResponse.getResultsList());
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
	public TipclienteResponse fetchAllTipclienteFilial(TipclienteRequest tipclienteRequest) { 
		TipclienteResponse response = new TipclienteResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryTipclienteRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Tipcliente> internalResponse =  
						getTipclienteBCL().fetchAllTipclienteFilial(tipclienteRequest);  
				response.setTipcliente(internalResponse.getResultsList());   
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		} 
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
   
   
	public ITipclienteBCL getTipclienteBCL() 
	{       
		return tipclienteBCL; 
	}  
    
	public void setTipclienteBCL(ITipclienteBCL tipclienteBCL) 
	{ 
		this.tipclienteBCL = tipclienteBCL; 
	} 
}  
