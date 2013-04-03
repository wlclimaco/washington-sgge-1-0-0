Memo1
package com.sensus.mlc.Empresa.bcl.impl; 
 
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
import com.sensus.mlc.Empresa.bcl.IEmpresaBCL; 
import com.sensus.mlc.Empresa.dac.IEmpresaDAC;  
import com.sensus.mlc.Empresa.model.Empresa;    
import com.sensus.mlc.Empresa.model.request.EmpresaRequest; 
import com.sensus.mlc.Empresa.model.request.InquiryEmpresaRequest;  
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
public class EmpresaBCLImpl implements IEmpresaBCL 
package com.sensus.mlc.Empresa.bcl.impl
 
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
import com.sensus.mlc.Empresa.bcl.IEmpresaBCL
import com.sensus.mlc.Empresa.dac.IEmpresaDAC
import com.sensus.mlc.Empresa.model.Empresa
import com.sensus.mlc.Empresa.model.request.EmpresaRequest
import com.sensus.mlc.Empresa.model.request.InquiryEmpresaRequest
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
public class EmpresaBCLImpl implements IEmpresaBCL 
{ 
 
/** The LOG. */  
private static transient Log LOG = LogFactory.getLog(EmpresaBCLImpl.class)
 
/** The Empresa dac. */ 
private IEmpresaDAC EmpresaDAC // injected by Spring through setter 
 
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
public InternalResultsResponse<Empresa> insertEmpresa(EmpresaRequest EmpresaRequest)  
{  
InternalResultsResponse<Empresa> response = getEmpresaDAC().insertEmpresa(EmpresaRequest)
 
if (!response.isInError())  
{    
Empresa Empresa = response.getFirstResult()
EmpresaRequest.setEmpresa(Empresa)
 
SearchParameter EmpresaParameter =  
new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(Empresa.getCodemp()))
EmpresaRequest.getSearchLight().addSearchParameter(EmpresaParameter)
InternalResultsResponse<Process> processResponse =   
insertProcess(EmpresaRequest, LCActionTypeEnum.INSERT_EMPRESA, null)
EmpresaRequest.getSearchLight().getSearchParameters().remove(EmpresaParameter)
 
response.setStatus(processResponse.getStatus())
response.addMessages(processResponse.getMessageInfoList())
}  
return response
 
}  
 
@Override 
public InternalResultsResponse<Empresa> updateEmpresa(EmpresaRequest EmpresaRequest)
{ 
InternalResponse groupResponse = new InternalResponse()
 
InternalResultsResponse<Empresa> internalResultsResponse = getEmpresaDAC().updateEmpresa(EmpresaRequest)
 
if (!ValidationUtil.isNull(groupResponse))  
{  
internalResultsResponse.addMessages(groupResponse.getMessageInfoList())
} 
 
return internalResultsResponse
} 
 
@Override  
public InternalResponse deleteEmpresa(EmpresaRequest EmpresaRequest)  
{ 
 
InternalResultsResponse<Empresa> tagResponse =  
(InternalResultsResponse<Empresa>)getEmpresaDAC().deleteEmpresa(EmpresaRequest)
InternalResponse response = new InternalResponse()
 
if (tagResponse.isInError())  
{   
return response
}  
 
response = getEmpresaDAC().deleteEmpresa(EmpresaRequest)
 
if (tagResponse.isInError()) 
{      
return response
}   
 
Empresa Empresa = tagResponse.getFirstResult()
EmpresaRequest.setEmpresa(Empresa)
 
SearchParameter tagParameter =  
new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(Empresa.getCodemp()))
EmpresaRequest.getSearchLight().addSearchParameter(tagParameter)
 
InternalResultsResponse<Process> processResponse = 
insertProcess(EmpresaRequest, LCActionTypeEnum.DELETE_TAG, null)
EmpresaRequest.getSearchLight().getSearchParameters().remove(tagParameter)
 
if (processResponse.isInError())  
{       
response.setStatus(processResponse.getStatus())
response.addMessages(processResponse.getMessageInfoList())
}
 
return response
} 
 
@Override 
public InternalResultsResponse<Empresa> fetchAllEmpresa(InquiryEmpresaRequest inquiryEmpresaRequest) 
{   
return getEmpresaDAC().fetchAllEmpresa(inquiryEmpresaRequest)
}  
 
@Override  
public InternalResultsResponse<Empresa> fetchEmpresaById(EmpresaRequest EmpresaRequest) 
{   
return getEmpresaDAC().fetchEmpresaById(EmpresaRequest)
} 
 
public IEmpresaDAC getEmpresaDAC() 
{
return EmpresaDAC
} 
 
public void setEmpresaDAC(IEmpresaDAC EmpresaDAC) 
{ 
this.EmpresaDAC = EmpresaDAC
}  
 
@Override  
public InternalResultsResponse<Empresa> fetchAllEmpresaTypes(InquiryEmpresaRequest inquiryEmpresaRequest)  
{ 
// TODO Auto-generated method stub  
return null
}         
 
private InternalResultsResponse<Process> insertProcess(EmpresaRequest EmpresaRequest,  
LCActionTypeEnum lcActionType, 
String processDescription)   
{ 
return insertProcess(EmpresaRequest, lcActionType, processDescription, null)
} 
 
/**   
* Insert process. 
*   
* @param tagRequest the tag request 
* @param lcActionType the lc action type   
* @param processDescription the process description   
* @return the internal results response  
*/         
private InternalResultsResponse<Process> insertProcess(EmpresaRequest tagRequest, LCActionTypeEnum lcActionType,  
String processDescription, List<Light> deactivatedLights) 
{
Empresa Empresa = tagRequest.getEmpresa()
 
List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>()
actionParameters.add(new LCActionParameter(PropertyEnum.TAG_ID, String.valueOf(Empresa.getCodemp())))
actionParameters.add(new LCActionParameter(PropertyEnum.TAG_NAME, Empresa.getCnpjemp()))
 
LCAction action = new LCAction(lcActionType)
action.setActionParameters(actionParameters)
action.setDescription("INSERT EMPRESA")
Process process = LCHelp.generateProcess(false, action, 0)
process.setIsProcessComplete(true)
process.setIsSubmitted(true)
process.setDescription("insert Empresa")
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
public InternalResultsResponse<Empresa> fetchAllEmpresaFilial(EmpresaRequest EmpresaRequest) 
{  
// TODO Auto-generated method stub  
return null
} 
}  
 
