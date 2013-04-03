Memo1

package com.sensus.mlc.Edit1.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.Edit1.bcf.IEdit1BCF;
import com.sensus.mlc.Edit1.bcl.IEdit1BCL;
import com.sensus.mlc.Edit1.model.Edit1;
import com.sensus.mlc.Edit1.model.request.Edit1Request;
import com.sensus.mlc.Edit1.model.request.InquiryEdit1Request;
import com.sensus.mlc.Edit1.model.response.Edit1Response;
import com.sensus.mlc.Edit1.model.response.InquiryEdit1Response;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.Edit1BCFImpl;

public class Edit1BCFImpl implements IEdit1BCF
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
	private static final Logger LOG = LoggerFactory.getLogger(Edit1BCFImpl.class); 
 
	private IEdit1BCL Edit1BCL; // injected by Spring through setter
 
	@Override 
	public Edit1Response insertEdit1(Edit1Request Edit1Request)
	{
		Edit1Response response = new Edit1Response();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, Edit1Request, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Edit1> internalResponse = getEdit1BCL().insertEdit1(Edit1Request); 
				response.setEdit1(internalResponse.getResultsList()); 
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
	public Edit1Response updateEdit1(Edit1Request Edit1Request)
	{
		Edit1Response response = new Edit1Response();
		try 
		{ 
			if (LCHelp.checkValidation(response, Edit1Request, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Edit1> internalResponse = getEdit1BCL().updateEdit1(Edit1Request);
				response.setEdit1(internalResponse.getResultsList());
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
	public Edit1Response deleteEdit1(Edit1Request Edit1Request) 
	{  
		Edit1Response response = new Edit1Response();
		try  
		{  
			if (LCHelp.checkValidation(response, Edit1Request, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getEdit1BCL().deleteEdit1(Edit1Request); 
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
	public InquiryEdit1Response fetchAllEdit1(InquiryEdit1Request inquiryEdit1Request) 
	{ 
		InquiryEdit1Response response = new InquiryEdit1Response(); 
		try 
		{ 
    
				InternalResultsResponse<Edit1> internalResponse =  
						getEdit1BCL().fetchAllEdit1(inquiryEdit1Request); 
				response.setEdit1(internalResponse.getResultsList());
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
	public Edit1Response fetchEdit1ById(Edit1Request Edit1Request) 
	{ 
		Edit1Response response = new Edit1Response(); 
		try 
		{  
			if (LCHelp.checkValidation(response, Edit1Request, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Edit1> internalResponse = getEdit1BCL().fetchEdit1ById(Edit1Request); 
				response.setEdit1(internalResponse.getResultsList());
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
	public Edit1Response fetchAllEdit1Filial(Edit1Request Edit1Request) { 
		Edit1Response response = new Edit1Response(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryEdit1Request, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Edit1> internalResponse =  
						getEdit1BCL().fetchAllEdit1Filial(Edit1Request);  
				response.setEdit1(internalResponse.getResultsList());   
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		} 
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
   
   
	public IEdit1BCL getEdit1BCL() 
	{       
		return Edit1BCL; 
	}  
    
	public void setEdit1BCL(IEdit1BCL Edit1BCL) 
	{ 
		this.Edit1BCL = Edit1BCL; 
	} 
}  
