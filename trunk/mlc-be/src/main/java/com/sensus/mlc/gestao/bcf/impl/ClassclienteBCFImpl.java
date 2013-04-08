package com.sensus.mlc.classcliente.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.classcliente.bcf.IClassclienteBCF;
import com.sensus.mlc.classcliente.bcl.IClassclienteBCL;
import com.sensus.mlc.classcliente.model.Classcliente;
import com.sensus.mlc.classcliente.model.request.ClassclienteRequest;
import com.sensus.mlc.classcliente.model.request.InquiryClassclienteRequest;
import com.sensus.mlc.classcliente.model.response.ClassclienteResponse;
import com.sensus.mlc.classcliente.model.response.InquiryClassclienteResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.classclienteBCFImpl;

public class ClassclienteBCFImpl implements IClassclienteBCF
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
	private static final Logger LOG = LoggerFactory.getLogger(ClassclienteBCFImpl.class); 
 
	private IClassclienteBCL classclienteBCL; // injected by Spring through setter
 
	@Override 
	public ClassclienteResponse insertClasscliente(ClassclienteRequest classclienteRequest)
	{
		ClassclienteResponse response = new ClassclienteResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, classclienteRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Classcliente> internalResponse = getClassclienteBCL().insertClasscliente(classclienteRequest); 
				response.setClasscliente(internalResponse.getResultsList()); 
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
	public ClassclienteResponse updateClasscliente(ClassclienteRequest classclienteRequest)
	{
		ClassclienteResponse response = new ClassclienteResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, classclienteRequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Classcliente> internalResponse = getClassclienteBCL().updateClasscliente(classclienteRequest);
				response.setClasscliente(internalResponse.getResultsList());
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
	public ClassclienteResponse deleteClasscliente(ClassclienteRequest classclienteRequest) 
	{  
		ClassclienteResponse response = new ClassclienteResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, classclienteRequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getClassclienteBCL().deleteClasscliente(classclienteRequest); 
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
	public InquiryClassclienteResponse fetchAllClasscliente(InquiryClassclienteRequest inquiryclassclienteRequest) 
	{ 
		InquiryClassclienteResponse response = new InquiryClassclienteResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Classcliente> internalResponse =  
						getClassclienteBCL().fetchAllClasscliente(inquiryclassclienteRequest); 
				response.setClasscliente(internalResponse.getResultsList());
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
	public ClassclienteResponse fetchClassclienteById(ClassclienteRequest classclienteRequest) 
	{ 
		ClassclienteResponse response = new ClassclienteResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, classclienteRequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Classcliente> internalResponse = getClassclienteBCL().fetchClassclienteById(classclienteRequest); 
				response.setClasscliente(internalResponse.getResultsList());
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
	public ClassclienteResponse fetchAllClassclienteFilial(ClassclienteRequest classclienteRequest) { 
		ClassclienteResponse response = new ClassclienteResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryClassclienteRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Classcliente> internalResponse =  
						getClassclienteBCL().fetchAllClassclienteFilial(classclienteRequest);  
				response.setClasscliente(internalResponse.getResultsList());   
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		} 
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
   
   
	public IClassclienteBCL getClassclienteBCL() 
	{       
		return classclienteBCL; 
	}  
    
	public void setClassclienteBCL(IClassclienteBCL classclienteBCL) 
	{ 
		this.classclienteBCL = classclienteBCL; 
	} 
}  
