Memo1
package com.sensus.mlc.UnidMed.bcl.impl; 
 
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
import com.sensus.mlc.UnidMed.bcl.IUnidmedBCL; 
import com.sensus.mlc.UnidMed.dac.IUnidmedDAC;  
import com.sensus.mlc.UnidMed.model.Unidmed;    
import com.sensus.mlc.UnidMed.model.request.UnidmedRequest; 
import com.sensus.mlc.UnidMed.model.request.InquiryUnidmedRequest;  
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
public class UnidmedBCLImpl implements IUnidmedBCL 
package com.sensus.mlc.UnidMed.bcl.impl
 
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
import com.sensus.mlc.UnidMed.bcl.IUnidmedBCL
import com.sensus.mlc.UnidMed.dac.IUnidmedDAC
import com.sensus.mlc.UnidMed.model.Unidmed
import com.sensus.mlc.UnidMed.model.request.UnidmedRequest
import com.sensus.mlc.UnidMed.model.request.InquiryUnidmedRequest
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
public class UnidmedBCLImpl implements IUnidmedBCL 
{ 
 
/** The LOG. */  
private static transient Log LOG = LogFactory.getLog(UnidmedBCLImpl.class)
 
/** The UnidMed dac. */ 
private IUnidmedDAC UnidMedDAC // injected by Spring through setter 
 
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
public InternalResultsResponse<Unidmed> insertUnidmed(UnidmedRequest UnidMedRequest)  
{  
InternalResultsResponse<Unidmed> response = getUnidmedDAC().insertUnidmed(UnidMedRequest)
 
if (!response.isInError())  
{    
Unidmed UnidMed = response.getFirstResult()
UnidMedRequest.setUnidmed(UnidMed)
 
SearchParameter UnidMedParameter =  
new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(UnidMed.getCodemp()))
UnidMedRequest.getSearchLight().addSearchParameter(UnidMedParameter)
InternalResultsResponse<Process> processResponse =   
insertProcess(UnidMedRequest, LCActionTypeEnum.INSERT_EMPRESA, null)
UnidMedRequest.getSearchLight().getSearchParameters().remove(UnidMedParameter)
 
response.setStatus(processResponse.getStatus())
response.addMessages(processResponse.getMessageInfoList())
}  
return response
 
}  
 
@Override 
public InternalResultsResponse<Unidmed> updateUnidmed(UnidmedRequest UnidMedRequest)
{ 
InternalResponse groupResponse = new InternalResponse()
 
InternalResultsResponse<Unidmed> internalResultsResponse = getUnidmedDAC().updateUnidmed(UnidMedRequest)
 
if (!ValidationUtil.isNull(groupResponse))  
{  
internalResultsResponse.addMessages(groupResponse.getMessageInfoList())
} 
 
return internalResultsResponse
} 
 
@Override  
public InternalResponse deleteUnidmed(UnidmedRequest UnidMedRequest)  
{ 
 
InternalResultsResponse<Unidmed> tagResponse =  
(InternalResultsResponse<Unidmed>)getUnidmedDAC().deleteUnidmed(UnidMedRequest)
InternalResponse response = new InternalResponse()
 
if (tagResponse.isInError())  
{   
return response
}  
 
response = getUnidmedDAC().deleteUnidmed(UnidMedRequest)
 
if (tagResponse.isInError()) 
{      
return response
}   
 
Unidmed UnidMed = tagResponse.getFirstResult()
UnidMedRequest.setUnidmed(UnidMed)
 
SearchParameter tagParameter =  
new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(UnidMed.getCodemp()))
UnidMedRequest.getSearchLight().addSearchParameter(tagParameter)
 
InternalResultsResponse<Process> processResponse = 
insertProcess(UnidMedRequest, LCActionTypeEnum.DELETE_TAG, null)
UnidMedRequest.getSearchLight().getSearchParameters().remove(tagParameter)
 
if (processResponse.isInError())  
{       
response.setStatus(processResponse.getStatus())
response.addMessages(processResponse.getMessageInfoList())
}
 
return response
} 
 
@Override 
public InternalResultsResponse<Unidmed> fetchAllUnidmed(InquiryUnidmedRequest inquiryUnidMedRequest) 
{   
return getUnidmedDAC().fetchAllUnidmed(inquiryUnidMedRequest)
}  
 
@Override  
public InternalResultsResponse<Unidmed> fetchUnidmedById(UnidmedRequest UnidMedRequest) 
{   
return getUnidmedDAC().fetchUnidmedById(UnidMedRequest)
} 
 
public IUnidmedDAC getUnidmedDAC() 
{
return UnidMedDAC
} 
 
public void setUnidmedDAC(IUnidmedDAC UnidMedDAC) 
{ 
this.UnidMedDAC = UnidMedDAC
}  
 
@Override  
public InternalResultsResponse<Unidmed> fetchAllUnidmedTypes(InquiryUnidmedRequest inquiryUnidMedRequest)  
{ 
// TODO Auto-generated method stub  
return null
}         
 
private InternalResultsResponse<Process> insertProcess(UnidmedRequest UnidMedRequest,  
LCActionTypeEnum lcActionType, 
String processDescription)   
{ 
return insertProcess(UnidMedRequest, lcActionType, processDescription, null)
} 
 
/**   
* Insert process. 
*   
* @param tagRequest the tag request 
* @param lcActionType the lc action type   
* @param processDescription the process description   
* @return the internal results response  
*/         
private InternalResultsResponse<Process> insertProcess(UnidmedRequest tagRequest, LCActionTypeEnum lcActionType,  
String processDescription, List<Light> deactivatedLights) 
{
Unidmed UnidMed = tagRequest.getUnidmed()
 
List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>()
actionParameters.add(new LCActionParameter(PropertyEnum.TAG_ID, String.valueOf(UnidMed.getCodemp())))
actionParameters.add(new LCActionParameter(PropertyEnum.TAG_NAME, UnidMed.getCnpjemp()))
 
LCAction action = new LCAction(lcActionType)
action.setActionParameters(actionParameters)
action.setDescription("INSERT EMPRESA")
Process process = LCHelp.generateProcess(false, action, 0)
process.setIsProcessComplete(true)
process.setIsSubmitted(true)
process.setDescription("insert Unidmed")
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
public InternalResultsResponse<Unidmed> fetchAllUnidmedFilial(UnidmedRequest UnidMedRequest) 
{  
// TODO Auto-generated method stub  
return null
} 
}  
 
package com.sensus.mlc.ClassTitulares.bcl.impl; 
 
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
import com.sensus.mlc.ClassTitulares.bcl.IClasstitularesBCL; 
import com.sensus.mlc.ClassTitulares.dac.IClasstitularesDAC;  
import com.sensus.mlc.ClassTitulares.model.Classtitulares;    
import com.sensus.mlc.ClassTitulares.model.request.ClasstitularesRequest; 
import com.sensus.mlc.ClassTitulares.model.request.InquiryClasstitularesRequest;  
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
public class ClasstitularesBCLImpl implements IClasstitularesBCL 
package com.sensus.mlc.ClassTitulares.bcl.impl
 
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
import com.sensus.mlc.ClassTitulares.bcl.IClasstitularesBCL
import com.sensus.mlc.ClassTitulares.dac.IClasstitularesDAC
import com.sensus.mlc.ClassTitulares.model.Classtitulares
import com.sensus.mlc.ClassTitulares.model.request.ClasstitularesRequest
import com.sensus.mlc.ClassTitulares.model.request.InquiryClasstitularesRequest
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
public class ClasstitularesBCLImpl implements IClasstitularesBCL 
{ 
 
/** The LOG. */  
private static transient Log LOG = LogFactory.getLog(ClasstitularesBCLImpl.class)
 
/** The ClassTitulares dac. */ 
private IClasstitularesDAC ClassTitularesDAC // injected by Spring through setter 
 
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
public InternalResultsResponse<Classtitulares> insertClasstitulares(ClasstitularesRequest ClassTitularesRequest)  
{  
InternalResultsResponse<Classtitulares> response = getClasstitularesDAC().insertClasstitulares(ClassTitularesRequest)
 
if (!response.isInError())  
{    
Classtitulares ClassTitulares = response.getFirstResult()
ClassTitularesRequest.setClasstitulares(ClassTitulares)
 
SearchParameter ClassTitularesParameter =  
new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(ClassTitulares.getCodemp()))
ClassTitularesRequest.getSearchLight().addSearchParameter(ClassTitularesParameter)
InternalResultsResponse<Process> processResponse =   
insertProcess(ClassTitularesRequest, LCActionTypeEnum.INSERT_EMPRESA, null)
ClassTitularesRequest.getSearchLight().getSearchParameters().remove(ClassTitularesParameter)
 
response.setStatus(processResponse.getStatus())
response.addMessages(processResponse.getMessageInfoList())
}  
return response
 
}  
 
@Override 
public InternalResultsResponse<Classtitulares> updateClasstitulares(ClasstitularesRequest ClassTitularesRequest)
{ 
InternalResponse groupResponse = new InternalResponse()
 
InternalResultsResponse<Classtitulares> internalResultsResponse = getClasstitularesDAC().updateClasstitulares(ClassTitularesRequest)
 
if (!ValidationUtil.isNull(groupResponse))  
{  
internalResultsResponse.addMessages(groupResponse.getMessageInfoList())
} 
 
return internalResultsResponse
} 
 
@Override  
public InternalResponse deleteClasstitulares(ClasstitularesRequest ClassTitularesRequest)  
{ 
 
InternalResultsResponse<Classtitulares> tagResponse =  
(InternalResultsResponse<Classtitulares>)getClasstitularesDAC().deleteClasstitulares(ClassTitularesRequest)
InternalResponse response = new InternalResponse()
 
if (tagResponse.isInError())  
{   
return response
}  
 
response = getClasstitularesDAC().deleteClasstitulares(ClassTitularesRequest)
 
if (tagResponse.isInError()) 
{      
return response
}   
 
Classtitulares ClassTitulares = tagResponse.getFirstResult()
ClassTitularesRequest.setClasstitulares(ClassTitulares)
 
SearchParameter tagParameter =  
new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(ClassTitulares.getCodemp()))
ClassTitularesRequest.getSearchLight().addSearchParameter(tagParameter)
 
InternalResultsResponse<Process> processResponse = 
insertProcess(ClassTitularesRequest, LCActionTypeEnum.DELETE_TAG, null)
ClassTitularesRequest.getSearchLight().getSearchParameters().remove(tagParameter)
 
if (processResponse.isInError())  
{       
response.setStatus(processResponse.getStatus())
response.addMessages(processResponse.getMessageInfoList())
}
 
return response
} 
 
@Override 
public InternalResultsResponse<Classtitulares> fetchAllClasstitulares(InquiryClasstitularesRequest inquiryClassTitularesRequest) 
{   
return getClasstitularesDAC().fetchAllClasstitulares(inquiryClassTitularesRequest)
}  
 
@Override  
public InternalResultsResponse<Classtitulares> fetchClasstitularesById(ClasstitularesRequest ClassTitularesRequest) 
{   
return getClasstitularesDAC().fetchClasstitularesById(ClassTitularesRequest)
} 
 
public IClasstitularesDAC getClasstitularesDAC() 
{
return ClassTitularesDAC
} 
 
public void setClasstitularesDAC(IClasstitularesDAC ClassTitularesDAC) 
{ 
this.ClassTitularesDAC = ClassTitularesDAC
}  
 
@Override  
public InternalResultsResponse<Classtitulares> fetchAllClasstitularesTypes(InquiryClasstitularesRequest inquiryClassTitularesRequest)  
{ 
// TODO Auto-generated method stub  
return null
}         
 
private InternalResultsResponse<Process> insertProcess(ClasstitularesRequest ClassTitularesRequest,  
LCActionTypeEnum lcActionType, 
String processDescription)   
{ 
return insertProcess(ClassTitularesRequest, lcActionType, processDescription, null)
} 
 
/**   
* Insert process. 
*   
* @param tagRequest the tag request 
* @param lcActionType the lc action type   
* @param processDescription the process description   
* @return the internal results response  
*/         
private InternalResultsResponse<Process> insertProcess(ClasstitularesRequest tagRequest, LCActionTypeEnum lcActionType,  
String processDescription, List<Light> deactivatedLights) 
{
Classtitulares ClassTitulares = tagRequest.getClasstitulares()
 
List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>()
actionParameters.add(new LCActionParameter(PropertyEnum.TAG_ID, String.valueOf(ClassTitulares.getCodemp())))
actionParameters.add(new LCActionParameter(PropertyEnum.TAG_NAME, ClassTitulares.getCnpjemp()))
 
LCAction action = new LCAction(lcActionType)
action.setActionParameters(actionParameters)
action.setDescription("INSERT EMPRESA")
Process process = LCHelp.generateProcess(false, action, 0)
process.setIsProcessComplete(true)
process.setIsSubmitted(true)
process.setDescription("insert Classtitulares")
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
public InternalResultsResponse<Classtitulares> fetchAllClasstitularesFilial(ClasstitularesRequest ClassTitularesRequest) 
{  
// TODO Auto-generated method stub  
return null
} 
}  
 
package com.sensus.mlc.TipoTitulares.bcl.impl; 
 
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
import com.sensus.mlc.TipoTitulares.bcl.ITipotitularesBCL; 
import com.sensus.mlc.TipoTitulares.dac.ITipotitularesDAC;  
import com.sensus.mlc.TipoTitulares.model.Tipotitulares;    
import com.sensus.mlc.TipoTitulares.model.request.TipotitularesRequest; 
import com.sensus.mlc.TipoTitulares.model.request.InquiryTipotitularesRequest;  
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
public class TipotitularesBCLImpl implements ITipotitularesBCL 
package com.sensus.mlc.TipoTitulares.bcl.impl
 
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
import com.sensus.mlc.TipoTitulares.bcl.ITipotitularesBCL
import com.sensus.mlc.TipoTitulares.dac.ITipotitularesDAC
import com.sensus.mlc.TipoTitulares.model.Tipotitulares
import com.sensus.mlc.TipoTitulares.model.request.TipotitularesRequest
import com.sensus.mlc.TipoTitulares.model.request.InquiryTipotitularesRequest
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
public class TipotitularesBCLImpl implements ITipotitularesBCL 
{ 
 
/** The LOG. */  
private static transient Log LOG = LogFactory.getLog(TipotitularesBCLImpl.class)
 
/** The TipoTitulares dac. */ 
private ITipotitularesDAC TipoTitularesDAC // injected by Spring through setter 
 
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
public InternalResultsResponse<Tipotitulares> insertTipotitulares(TipotitularesRequest TipoTitularesRequest)  
{  
InternalResultsResponse<Tipotitulares> response = getTipotitularesDAC().insertTipotitulares(TipoTitularesRequest)
 
if (!response.isInError())  
{    
Tipotitulares TipoTitulares = response.getFirstResult()
TipoTitularesRequest.setTipotitulares(TipoTitulares)
 
SearchParameter TipoTitularesParameter =  
new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(TipoTitulares.getCodemp()))
TipoTitularesRequest.getSearchLight().addSearchParameter(TipoTitularesParameter)
InternalResultsResponse<Process> processResponse =   
insertProcess(TipoTitularesRequest, LCActionTypeEnum.INSERT_EMPRESA, null)
TipoTitularesRequest.getSearchLight().getSearchParameters().remove(TipoTitularesParameter)
 
response.setStatus(processResponse.getStatus())
response.addMessages(processResponse.getMessageInfoList())
}  
return response
 
}  
 
@Override 
public InternalResultsResponse<Tipotitulares> updateTipotitulares(TipotitularesRequest TipoTitularesRequest)
{ 
InternalResponse groupResponse = new InternalResponse()
 
InternalResultsResponse<Tipotitulares> internalResultsResponse = getTipotitularesDAC().updateTipotitulares(TipoTitularesRequest)
 
if (!ValidationUtil.isNull(groupResponse))  
{  
internalResultsResponse.addMessages(groupResponse.getMessageInfoList())
} 
 
return internalResultsResponse
} 
 
@Override  
public InternalResponse deleteTipotitulares(TipotitularesRequest TipoTitularesRequest)  
{ 
 
InternalResultsResponse<Tipotitulares> tagResponse =  
(InternalResultsResponse<Tipotitulares>)getTipotitularesDAC().deleteTipotitulares(TipoTitularesRequest)
InternalResponse response = new InternalResponse()
 
if (tagResponse.isInError())  
{   
return response
}  
 
response = getTipotitularesDAC().deleteTipotitulares(TipoTitularesRequest)
 
if (tagResponse.isInError()) 
{      
return response
}   
 
Tipotitulares TipoTitulares = tagResponse.getFirstResult()
TipoTitularesRequest.setTipotitulares(TipoTitulares)
 
SearchParameter tagParameter =  
new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(TipoTitulares.getCodemp()))
TipoTitularesRequest.getSearchLight().addSearchParameter(tagParameter)
 
InternalResultsResponse<Process> processResponse = 
insertProcess(TipoTitularesRequest, LCActionTypeEnum.DELETE_TAG, null)
TipoTitularesRequest.getSearchLight().getSearchParameters().remove(tagParameter)
 
if (processResponse.isInError())  
{       
response.setStatus(processResponse.getStatus())
response.addMessages(processResponse.getMessageInfoList())
}
 
return response
} 
 
@Override 
public InternalResultsResponse<Tipotitulares> fetchAllTipotitulares(InquiryTipotitularesRequest inquiryTipoTitularesRequest) 
{   
return getTipotitularesDAC().fetchAllTipotitulares(inquiryTipoTitularesRequest)
}  
 
@Override  
public InternalResultsResponse<Tipotitulares> fetchTipotitularesById(TipotitularesRequest TipoTitularesRequest) 
{   
return getTipotitularesDAC().fetchTipotitularesById(TipoTitularesRequest)
} 
 
public ITipotitularesDAC getTipotitularesDAC() 
{
return TipoTitularesDAC
} 
 
public void setTipotitularesDAC(ITipotitularesDAC TipoTitularesDAC) 
{ 
this.TipoTitularesDAC = TipoTitularesDAC
}  
 
@Override  
public InternalResultsResponse<Tipotitulares> fetchAllTipotitularesTypes(InquiryTipotitularesRequest inquiryTipoTitularesRequest)  
{ 
// TODO Auto-generated method stub  
return null
}         
 
private InternalResultsResponse<Process> insertProcess(TipotitularesRequest TipoTitularesRequest,  
LCActionTypeEnum lcActionType, 
String processDescription)   
{ 
return insertProcess(TipoTitularesRequest, lcActionType, processDescription, null)
} 
 
/**   
* Insert process. 
*   
* @param tagRequest the tag request 
* @param lcActionType the lc action type   
* @param processDescription the process description   
* @return the internal results response  
*/         
private InternalResultsResponse<Process> insertProcess(TipotitularesRequest tagRequest, LCActionTypeEnum lcActionType,  
String processDescription, List<Light> deactivatedLights) 
{
Tipotitulares TipoTitulares = tagRequest.getTipotitulares()
 
List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>()
actionParameters.add(new LCActionParameter(PropertyEnum.TAG_ID, String.valueOf(TipoTitulares.getCodemp())))
actionParameters.add(new LCActionParameter(PropertyEnum.TAG_NAME, TipoTitulares.getCnpjemp()))
 
LCAction action = new LCAction(lcActionType)
action.setActionParameters(actionParameters)
action.setDescription("INSERT EMPRESA")
Process process = LCHelp.generateProcess(false, action, 0)
process.setIsProcessComplete(true)
process.setIsSubmitted(true)
process.setDescription("insert Tipotitulares")
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
public InternalResultsResponse<Tipotitulares> fetchAllTipotitularesFilial(TipotitularesRequest TipoTitularesRequest) 
{  
// TODO Auto-generated method stub  
return null
} 
}  
 
