Memo1

package com.sensus.mlc.Filial.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.Filial.bcf.IFilialBCF;
import com.sensus.mlc.Filial.bcl.IFilialBCL;
import com.sensus.mlc.Filial.model.Filial;
import com.sensus.mlc.Filial.model.request.FilialRequest;
import com.sensus.mlc.Filial.model.request.InquiryFilialRequest;
import com.sensus.mlc.Filial.model.response.FilialResponse;
import com.sensus.mlc.Filial.model.response.InquiryFilialResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.FilialBCFImpl;

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
 
	private IFilialBCL FilialBCL; // injected by Spring through setter
 
	@Override 
	public FilialResponse insertFilial(FilialRequest FilialRequest)
	{
		FilialResponse response = new FilialResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, FilialRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Filial> internalResponse = getFilialBCL().insertFilial(FilialRequest); 
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
	public FilialResponse updateFilial(FilialRequest FilialRequest)
	{
		FilialResponse response = new FilialResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, FilialRequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Filial> internalResponse = getFilialBCL().updateFilial(FilialRequest);
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
	public FilialResponse deleteFilial(FilialRequest FilialRequest) 
	{  
		FilialResponse response = new FilialResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, FilialRequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getFilialBCL().deleteFilial(FilialRequest); 
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
	public InquiryFilialResponse fetchAllFilial(InquiryFilialRequest inquiryFilialRequest) 
	{ 
		InquiryFilialResponse response = new InquiryFilialResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Filial> internalResponse =  
						getFilialBCL().fetchAllFilial(inquiryFilialRequest); 
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
	public FilialResponse fetchFilialById(FilialRequest FilialRequest) 
	{ 
		FilialResponse response = new FilialResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, FilialRequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Filial> internalResponse = getFilialBCL().fetchFilialById(FilialRequest); 
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
	public FilialResponse fetchAllFilialFilial(FilialRequest FilialRequest) { 
		FilialResponse response = new FilialResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryFilialRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Filial> internalResponse =  
						getFilialBCL().fetchAllFilialFilial(FilialRequest);  
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
		return FilialBCL; 
	}  
    
	public void setFilialBCL(IFilialBCL FilialBCL) 
	{ 
		this.FilialBCL = FilialBCL; 
	} 
}  
