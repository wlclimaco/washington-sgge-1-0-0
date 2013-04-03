Memo1
package com.sensus.mlc.Edit1.bcl.impl; 
 
import static com.sensus.mlc.base.util.LCHelp.createProcessItemWithFailure; 
import static com.sensus.mlc.base.util.LCHelp.createProcessRequest; 
  
import java.util.ArrayList; 
import java.util.List; 
 
import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory; 
  
import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse; 
import com.sensus.common.util.SensusAppContext; 
import com.sensus.common.validation.ValidationUtil; 
import com.sensus.mlc.base.util.LCDateUtil;   
import com.sensus.mlc.base.util.LCHelp;        
import com.sensus.mlc.Edit1.bcl.IEdit1BCL; 
import com.sensus.mlc.Edit1.dac.IEdit1DAC;  
import com.sensus.mlc.Edit1.model.Edit1;    
import com.sensus.mlc.Edit1.model.request.Edit1Request; 
import com.sensus.mlc.Edit1.model.request.InquiryEdit1Request;  
import com.sensus.mlc.process.bcl.IProcessBCL;    
import com.sensus.mlc.process.model.LCAction;  
import com.sensus.mlc.process.model.LCActionParameter;  
import com.sensus.mlc.process.model.LCActionTypeEnum;  
import com.sensus.mlc.process.model.Process;   
import com.sensus.mlc.process.model.ProcessItemStatusEnum; 
import com.sensus.mlc.process.model.ProcessStatusReasonEnum; 
import com.sensus.mlc.process.model.request.ProcessRequest; 
import com.sensus.mlc.schedule.bcl.IScheduleBCL;  
import com.sensus.mlc.smartpoint.model.Light; 
import com.sensus.mlc.smartpoint.model.PropertyEnum; 
import com.sensus.mlc.smartpoint.model.SearchParameter;
  
/** 
 * Action BCL implementation class. 
 * 
 * @author QAT. 
 */
public class Edit1BCLImpl implements IEdit1BCL 
package com.sensus.mlc.Edit1.bcl.impl
 
import static com.sensus.mlc.base.util.LCHelp.createProcessItemWithFailure
import static com.sensus.mlc.base.util.LCHelp.createProcessRequest
 
import java.util.ArrayList
import java.util.List
 
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
 
import com.sensus.common.model.response.InternalResponse
import com.sensus.common.model.response.InternalResultsResponse
import com.sensus.common.util.SensusAppContext
import com.sensus.common.validation.ValidationUtil
import com.sensus.mlc.base.util.LCDateUtil
import com.sensus.mlc.base.util.LCHelp
import com.sensus.mlc.Edit1.bcl.IEdit1BCL
import com.sensus.mlc.Edit1.dac.IEdit1DAC
import com.sensus.mlc.Edit1.model.Edit1
import com.sensus.mlc.Edit1.model.request.Edit1Request
import com.sensus.mlc.Edit1.model.request.InquiryEdit1Request
import com.sensus.mlc.process.bcl.IProcessBCL
import com.sensus.mlc.process.model.LCAction
import com.sensus.mlc.process.model.LCActionParameter
import com.sensus.mlc.process.model.LCActionTypeEnum
import com.sensus.mlc.process.model.Process
import com.sensus.mlc.process.model.ProcessItemStatusEnum
import com.sensus.mlc.process.model.ProcessStatusReasonEnum
import com.sensus.mlc.process.model.request.ProcessRequest
import com.sensus.mlc.schedule.bcl.IScheduleBCL
import com.sensus.mlc.smartpoint.model.Light
import com.sensus.mlc.smartpoint.model.PropertyEnum
import com.sensus.mlc.smartpoint.model.SearchParameter
 
/**
* Action BCL implementation class. 
*
* @author Washington 
*/
public class Edit1BCLImpl implements IEdit1BCL 
{ 
 
/** The LOG. */  
private static transient Log LOG = LogFactory.getLog(Edit1BCLImpl.class)
 
/** The Edit1 dac. */ 
private IEdit1DAC Edit1DAC // injected by Spring through setter 
 
/** The Constant PROCESS_BCL_BEAN. */ 
private static final String PROCESS_BCL_BEAN = "processBCLTarget"
 
/** The Constant SCHEDULE_BCL_BEAN. */ 
private static final String SCHEDULE_BCL_BEAN = "scheduleBCLTarget"
 
/** The Constant ACTION_PROVIDER_TYPE. */ 
private static final String ACTION_PROVIDER_TYPE = "EPM.TASK"
 
/** The Constant ADD_PROPERTY_TO_ACTION_FAILED. */ 
private static final String ADD_PROPERTY_TO_ACTION_FAILED =  
"sensus.epm.actionbclimpl.add.property.to.action.failed"
 
/** The Constant UPDATE_PROPERTY_TO_ACTION_FAILED. */  
private static final String UPDATE_PROPERTY_TO_ACTION_FAILED = 
"sensus.epm.actionbclimpl.update.property.to.action.failed"
 
/** The Constant DELETE_PROPERTY_FROM_ACTION_FAILED. */   
private static final String DELETE_PROPERTY_FROM_ACTION_FAILED = 
"sensus.epm.actionbclimpl.delete.property.from.action.failed"
 
/** The Constant PROPERTY_DAC_BEAN. */ 
private static final String PROPERTY_DAC_BEAN = "propertyDACTarget"
 
/** The Constant GET_ACTION_TO_CLONE_FAILED. */     
private static final String GET_ACTION_TO_CLONE_FAILED = "sensus.epm.actionbclimpl.get.action.to.clone.failed"
 
/** The Constant APPLY_ACTION_FAILED. */   
private static final String APPLY_ACTION_FAILED =  
"sensus.epm.actionbclimpl.apply.action.failed"
 
/** The Constant DATE_RAND_DIVISION. */  
private static final Integer DATE_RAND_DIVISION = 0xFFFFF
 
/**  
* Gets the process bcl. 
*   
* @return the process bcl 
*/
public IProcessBCL getProcessBCL() 
{  
return (IProcessBCL)SensusAppContext.getApplicationContext().getBean(PROCESS_BCL_BEAN)
} 
 
/**
* Gets the schedule bcl. 
* 
* @return the schedule bcl 
*/
public IScheduleBCL getScheduleBCL()
{  
return (IScheduleBCL)SensusAppContext.getApplicationContext().getBean(SCHEDULE_BCL_BEAN)
} 
 
@Override 
public InternalResultsResponse<Edit1> insertEdit1(Edit1Request Edit1Request)  
{  
InternalResultsResponse<Edit1> response = getEdit1DAC().insertEdit1(Edit1Request)
 
if (!response.isInError())  
{    
Edit1 Edit1 = response.getFirstResult()
Edit1Request.setEdit1(Edit1)
 
SearchParameter Edit1Parameter =  
new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(Edit1.getCodemp()))
Edit1Request.getSearchLight().addSearchParameter(Edit1Parameter)
InternalResultsResponse<Process> processResponse =   
insertProcess(Edit1Request, LCActionTypeEnum.INSERT_EMPRESA, null)
Edit1Request.getSearchLight().getSearchParameters().remove(Edit1Parameter)
 
response.setStatus(processResponse.getStatus())
response.addMessages(processResponse.getMessageInfoList())
}  
return response
 
}  
 
@Override 
public InternalResultsResponse<Edit1> updateEdit1(Edit1Request Edit1Request)
{ 
InternalResponse groupResponse = new InternalResponse()
 
InternalResultsResponse<Edit1> internalResultsResponse = getEdit1DAC().updateEdit1(Edit1Request)
 
if (!ValidationUtil.isNull(groupResponse))  
{  
internalResultsResponse.addMessages(groupResponse.getMessageInfoList())
} 
 
return internalResultsResponse
} 
 
@Override  
public InternalResponse deleteEdit1(Edit1Request Edit1Request)  
{ 
 
InternalResultsResponse<Edit1> tagResponse =  
(InternalResultsResponse<Edit1>)getEdit1DAC().deleteEdit1(Edit1Request)
InternalResponse response = new InternalResponse()
 
if (tagResponse.isInError())  
{   
return response
}  
 
response = getEdit1DAC().deleteEdit1(Edit1Request)
 
if (tagResponse.isInError()) 
{      
return response
}   
 
Edit1 Edit1 = tagResponse.getFirstResult()
Edit1Request.setEdit1(Edit1)
 
SearchParameter tagParameter =  
new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(Edit1.getCodemp()))
Edit1Request.getSearchLight().addSearchParameter(tagParameter)
 
InternalResultsResponse<Process> processResponse = 
insertProcess(Edit1Request, LCActionTypeEnum.DELETE_TAG, null)
Edit1Request.getSearchLight().getSearchParameters().remove(tagParameter)
 
if (processResponse.isInError())  
{       
response.setStatus(processResponse.getStatus())
response.addMessages(processResponse.getMessageInfoList())
}
 
return response
} 
 
@Override 
public InternalResultsResponse<Edit1> fetchAllEdit1(InquiryEdit1Request inquiryEdit1Request) 
{   
return getEdit1DAC().fetchAllEdit1(inquiryEdit1Request)
}  
 
@Override  
public InternalResultsResponse<Edit1> fetchEdit1ById(Edit1Request Edit1Request) 
{   
return getEdit1DAC().fetchEdit1ById(Edit1Request)
} 
 
public IEdit1DAC getEdit1DAC() 
{
return Edit1DAC
} 
 
public void setEdit1DAC(IEdit1DAC Edit1DAC) 
{ 
this.Edit1DAC = Edit1DAC
}  
 
@Override  
public InternalResultsResponse<Edit1> fetchAllEdit1Types(InquiryEdit1Request inquiryEdit1Request)  
{ 
// TODO Auto-generated method stub  
return null
}         
 
private InternalResultsResponse<Process> insertProcess(Edit1Request Edit1Request,  
LCActionTypeEnum lcActionType, 
String processDescription)   
{ 
return insertProcess(Edit1Request, lcActionType, processDescription, null)
} 
 
/**   
* Insert process. 
*   
* @param tagRequest the tag request 
* @param lcActionType the lc action type   
* @param processDescription the process description   
* @return the internal results response  
*/         
private InternalResultsResponse<Process> insertProcess(Edit1Request tagRequest, LCActionTypeEnum lcActionType,  
String processDescription, List<Light> deactivatedLights) 
{
Edit1 Edit1 = tagRequest.getEdit1()
 
List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>()
actionParameters.add(new LCActionParameter(PropertyEnum.TAG_ID, String.valueOf(Edit1.getCodemp())))
actionParameters.add(new LCActionParameter(PropertyEnum.TAG_NAME, Edit1.getCnpjemp()))
 
LCAction action = new LCAction(lcActionType)
action.setActionParameters(actionParameters)
action.setDescription("INSERT EMPRESA")
Process process = LCHelp.generateProcess(false, action, 0)
process.setIsProcessComplete(true)
process.setIsSubmitted(true)
process.setDescription("insert Edit1")
process.setRniCorrelationId("")
 
 
process.setEndTime(LCDateUtil.getNewDateUTC())
 
if (!ValidationUtil.isNullOrEmpty(processDescription))  
{ 
process.setDescription(processDescription)
} 
 
ProcessRequest processRequest = createProcessRequest(tagRequest, process)
processRequest.setProcessItemFailureList( 
createProcessItemWithFailure(  
	deactivatedLights,     
	ProcessItemStatusEnum.MLCFAILURE,  
	ProcessStatusReasonEnum.LIGHT_DEACTIVATED))
 
return getProcessBCL().insertProcess(processRequest)
} 
 
@Override  
public InternalResultsResponse<Edit1> fetchAllEdit1Filial(Edit1Request Edit1Request) 
{  
// TODO Auto-generated method stub  
return null
} 
}  
 
