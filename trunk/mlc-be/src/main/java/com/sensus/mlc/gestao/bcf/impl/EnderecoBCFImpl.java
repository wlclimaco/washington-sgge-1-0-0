package com.sensus.mlc.endereco.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.endereco.bcf.IEnderecoBCF;
import com.sensus.mlc.endereco.bcl.IEnderecoBCL;
import com.sensus.mlc.endereco.model.Endereco;
import com.sensus.mlc.endereco.model.request.EnderecoRequest;
import com.sensus.mlc.endereco.model.request.InquiryEnderecoRequest;
import com.sensus.mlc.endereco.model.response.EnderecoResponse;
import com.sensus.mlc.endereco.model.response.InquiryEnderecoResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.enderecoBCFImpl;

public class EnderecoBCFImpl implements IEnderecoBCF
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
	private static final Logger LOG = LoggerFactory.getLogger(EnderecoBCFImpl.class); 
 
	private IEnderecoBCL enderecoBCL; // injected by Spring through setter
 
	@Override 
	public EnderecoResponse insertEndereco(EnderecoRequest enderecoRequest)
	{
		EnderecoResponse response = new EnderecoResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, enderecoRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Endereco> internalResponse = getEnderecoBCL().insertEndereco(enderecoRequest); 
				response.setEndereco(internalResponse.getResultsList()); 
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
	public EnderecoResponse updateEndereco(EnderecoRequest enderecoRequest)
	{
		EnderecoResponse response = new EnderecoResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, enderecoRequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Endereco> internalResponse = getEnderecoBCL().updateEndereco(enderecoRequest);
				response.setEndereco(internalResponse.getResultsList());
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
	public EnderecoResponse deleteEndereco(EnderecoRequest enderecoRequest) 
	{  
		EnderecoResponse response = new EnderecoResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, enderecoRequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getEnderecoBCL().deleteEndereco(enderecoRequest); 
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
	public InquiryEnderecoResponse fetchAllEndereco(InquiryEnderecoRequest inquiryenderecoRequest) 
	{ 
		InquiryEnderecoResponse response = new InquiryEnderecoResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Endereco> internalResponse =  
						getEnderecoBCL().fetchAllEndereco(inquiryenderecoRequest); 
				response.setEndereco(internalResponse.getResultsList());
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
	public EnderecoResponse fetchEnderecoById(EnderecoRequest enderecoRequest) 
	{ 
		EnderecoResponse response = new EnderecoResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, enderecoRequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Endereco> internalResponse = getEnderecoBCL().fetchEnderecoById(enderecoRequest); 
				response.setEndereco(internalResponse.getResultsList());
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
	public EnderecoResponse fetchAllEnderecoFilial(EnderecoRequest enderecoRequest) { 
		EnderecoResponse response = new EnderecoResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryEnderecoRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Endereco> internalResponse =  
						getEnderecoBCL().fetchAllEnderecoFilial(enderecoRequest);  
				response.setEndereco(internalResponse.getResultsList());   
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		} 
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
   
   
	public IEnderecoBCL getEnderecoBCL() 
	{       
		return enderecoBCL; 
	}  
    
	public void setEnderecoBCL(IEnderecoBCL enderecoBCL) 
	{ 
		this.enderecoBCL = enderecoBCL; 
	} 
}  
