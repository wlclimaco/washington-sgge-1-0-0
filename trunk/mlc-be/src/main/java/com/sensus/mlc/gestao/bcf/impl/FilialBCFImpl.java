package com.sensus.mlc.filial.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.filial.bcf.IFilialBCF;
import com.sensus.mlc.filial.bcl.IFilialBCL;
import com.sensus.mlc.filial.model.Filial;
import com.sensus.mlc.filial.model.request.FilialRequest;
import com.sensus.mlc.filial.model.request.InquiryFilialRequest;
import com.sensus.mlc.filial.model.response.FilialResponse;
import com.sensus.mlc.filial.model.response.InquiryFilialResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.filialBCFImpl;

public class FilialBCFImpl implements IFilialBCF
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
	private static final Logger LOG = LoggerFactory.getLogger(FilialBCFImpl.class); 
 
	private IFilialBCL filialBCL; // injected by Spring through setter
 
	@Override 
	public FilialResponse insertFilial(FilialRequest filialRequest)
	{
		FilialResponse response = new FilialResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, filialRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Filial> internalResponse = getFilialBCL().insertFilial(filialRequest); 
				response.setFilial(internalResponse.getResultsList()); 
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
	public FilialResponse updateFilial(FilialRequest filialRequest)
	{
		FilialResponse response = new FilialResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, filialRequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Filial> internalResponse = getFilialBCL().updateFilial(filialRequest);
				response.setFilial(internalResponse.getResultsList());
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
	public FilialResponse deleteFilial(FilialRequest filialRequest) 
	{  
		FilialResponse response = new FilialResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, filialRequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getFilialBCL().deleteFilial(filialRequest); 
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
	public InquiryFilialResponse fetchAllFilial(InquiryFilialRequest inquiryfilialRequest) 
	{ 
		InquiryFilialResponse response = new InquiryFilialResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Filial> internalResponse =  
						getFilialBCL().fetchAllFilial(inquiryfilialRequest); 
				response.setFilial(internalResponse.getResultsList());
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
	public FilialResponse fetchFilialById(FilialRequest filialRequest) 
	{ 
		FilialResponse response = new FilialResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, filialRequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Filial> internalResponse = getFilialBCL().fetchFilialById(filialRequest); 
				response.setFilial(internalResponse.getResultsList());
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
	public FilialResponse fetchAllFilialFilial(FilialRequest filialRequest) { 
		FilialResponse response = new FilialResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryFilialRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Filial> internalResponse =  
						getFilialBCL().fetchAllFilialFilial(filialRequest);  
				response.setFilial(internalResponse.getResultsList());   
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		} 
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
   
   
	public IFilialBCL getFilialBCL() 
	{       
		return filialBCL; 
	}  
    
	public void setFilialBCL(IFilialBCL filialBCL) 
	{ 
		this.filialBCL = filialBCL; 
	} 
}  
