Memo1

package com.sensus.mlc.Empresa.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.Empresa.bcf.IEmpresaBCF;
import com.sensus.mlc.Empresa.bcl.IEmpresaBCL;
import com.sensus.mlc.Empresa.model.Empresa;
import com.sensus.mlc.Empresa.model.request.EmpresaRequest;
import com.sensus.mlc.Empresa.model.request.InquiryEmpresaRequest;
import com.sensus.mlc.Empresa.model.response.EmpresaResponse;
import com.sensus.mlc.Empresa.model.response.InquiryEmpresaResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.EmpresaBCFImpl;

public class EmpresaBCFImpl implements IEmpresaBCF
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
	private static final Logger LOG = LoggerFactory.getLogger(EmpresaBCFImpl.class); 
 
	private IEmpresaBCL EmpresaBCL; // injected by Spring through setter
 
	@Override 
	public EmpresaResponse insertEmpresa(EmpresaRequest EmpresaRequest)
	{
		EmpresaResponse response = new EmpresaResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, EmpresaRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Empresa> internalResponse = getEmpresaBCL().insertEmpresa(EmpresaRequest); 
				response.setEmpresa(internalResponse.getResultsList()); 
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
	public EmpresaResponse updateEmpresa(EmpresaRequest EmpresaRequest)
	{
		EmpresaResponse response = new EmpresaResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, EmpresaRequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Empresa> internalResponse = getEmpresaBCL().updateEmpresa(EmpresaRequest);
				response.setEmpresa(internalResponse.getResultsList());
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
	public EmpresaResponse deleteEmpresa(EmpresaRequest EmpresaRequest) 
	{  
		EmpresaResponse response = new EmpresaResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, EmpresaRequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getEmpresaBCL().deleteEmpresa(EmpresaRequest); 
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
	public InquiryEmpresaResponse fetchAllEmpresa(InquiryEmpresaRequest inquiryEmpresaRequest) 
	{ 
		InquiryEmpresaResponse response = new InquiryEmpresaResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Empresa> internalResponse =  
						getEmpresaBCL().fetchAllEmpresa(inquiryEmpresaRequest); 
				response.setEmpresa(internalResponse.getResultsList());
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
	public EmpresaResponse fetchEmpresaById(EmpresaRequest EmpresaRequest) 
	{ 
		EmpresaResponse response = new EmpresaResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, EmpresaRequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Empresa> internalResponse = getEmpresaBCL().fetchEmpresaById(EmpresaRequest); 
				response.setEmpresa(internalResponse.getResultsList());
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
	public EmpresaResponse fetchAllEmpresaFilial(EmpresaRequest EmpresaRequest) { 
		EmpresaResponse response = new EmpresaResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryEmpresaRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Empresa> internalResponse =  
						getEmpresaBCL().fetchAllEmpresaFilial(EmpresaRequest);  
				response.setEmpresa(internalResponse.getResultsList());   
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		} 
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
   
   
	public IEmpresaBCL getEmpresaBCL() 
	{       
		return EmpresaBCL; 
	}  
    
	public void setEmpresaBCL(IEmpresaBCL EmpresaBCL) 
	{ 
		this.EmpresaBCL = EmpresaBCL; 
	} 
}  
