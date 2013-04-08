package com.sensus.mlc.tabela.bcl.impl;

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
import com.sensus.mlc.tabela.bcl.IAtributosBCL;
import com.sensus.mlc.tabela.dac.IAtributosDAC;
import com.sensus.mlc.tabela.model.Atributos;
import com.sensus.mlc.tabela.model.request.AtributosRequest;
import com.sensus.mlc.tabela.model.request.InquiryAtributosRequest;
/**
 * Action BCL implementation class.
 *
 * @author Washington.
 */

/**
* Action BCL implementation class.
*
* @author Washington
*/
public class AtributosBCLImpl implements IAtributosBCL
{

/** The LOG. */
private static transient Log LOG = LogFactory.getLog(AtributosBCLImpl.class)

/** The atributos dac. */
private IAtributosDAC atributosDAC // injected by Spring through setter

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
public InternalResultsResponse<Atributos> insertAtributos(AtributosRequest atributosRequest)
{
InternalResultsResponse<Atributos> response = getAtributosDAC().insertAtributos(atributosRequest)

if (!response.isInError())
{
Atributos atributos = response.getFirstResult()
atributosRequest.setAtributos(atributos)

SearchParameter atributosParameter =
new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(atributos.getCodemp()))
atributosRequest.getSearchLight().addSearchParameter(atributosParameter)
InternalResultsResponse<Process> processResponse =
insertProcess(atributosRequest, LCActionTypeEnum.INSERT_EMPRESA, null)
atributosRequest.getSearchLight().getSearchParameters().remove(atributosParameter)

response.setStatus(processResponse.getStatus())
response.addMessages(processResponse.getMessageInfoList())
}
return response

}

@Override
public InternalResultsResponse<Atributos> updateAtributos(AtributosRequest atributosRequest)
{
InternalResponse groupResponse = new InternalResponse()

InternalResultsResponse<Atributos> internalResultsResponse = getAtributosDAC().updateAtributos(atributosRequest)

if (!ValidationUtil.isNull(groupResponse))
{
internalResultsResponse.addMessages(groupResponse.getMessageInfoList())
}

return internalResultsResponse
}

@Override
public InternalResponse deleteAtributos(AtributosRequest atributosRequest)
{

InternalResultsResponse<Atributos> tagResponse =
(InternalResultsResponse<Atributos>)getAtributosDAC().deleteAtributos(atributosRequest)
InternalResponse response = new InternalResponse()

if (tagResponse.isInError())
{
return response
}

response = getAtributosDAC().deleteAtributos(atributosRequest)

if (tagResponse.isInError())
{
return response
}

Atributos atributos = tagResponse.getFirstResult()
atributosRequest.setAtributos(atributos)

SearchParameter tagParameter =
new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(atributos.getCodemp()))
atributosRequest.getSearchLight().addSearchParameter(tagParameter)

InternalResultsResponse<Process> processResponse =
insertProcess(atributosRequest, LCActionTypeEnum.DELETE_TAG, null)
atributosRequest.getSearchLight().getSearchParameters().remove(tagParameter)

if (processResponse.isInError())
{
response.setStatus(processResponse.getStatus())
response.addMessages(processResponse.getMessageInfoList())
}

return response
}

@Override
public InternalResultsResponse<Atributos> fetchAllAtributos(InquiryAtributosRequest inquiryatributosRequest)
{
return getAtributosDAC().fetchAllAtributos(inquiryatributosRequest)
}

@Override
public InternalResultsResponse<Atributos> fetchAtributosById(AtributosRequest atributosRequest)
{
return getAtributosDAC().fetchAtributosById(atributosRequest)
}

public IAtributosDAC getAtributosDAC()
{
return atributosDAC
}

public void setAtributosDAC(IAtributosDAC atributosDAC)
{
this.atributosDAC = atributosDAC
}

@Override
public InternalResultsResponse<Atributos> fetchAllAtributosTypes(InquiryAtributosRequest inquiryatributosRequest)
{
// TODO Auto-generated method stub
return null
}

private InternalResultsResponse<Process> insertProcess(AtributosRequest atributosRequest,
LCActionTypeEnum lcActionType,
String processDescription)
{
return insertProcess(atributosRequest, lcActionType, processDescription, null)
}

/**
* Insert process.
*
* @param tagRequest the tag request
* @param lcActionType the lc action type
* @param processDescription the process description
* @return the internal results response
*/
private InternalResultsResponse<Process> insertProcess(AtributosRequest tagRequest, LCActionTypeEnum lcActionType,
String processDescription, List<Light> deactivatedLights)
{
Atributos atributos = tagRequest.getAtributos()

List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>()
actionParameters.add(new LCActionParameter(PropertyEnum.TAG_ID, String.valueOf(atributos.getCodemp())))
actionParameters.add(new LCActionParameter(PropertyEnum.TAG_NAME, atributos.getCnpjemp()))

LCAction action = new LCAction(lcActionType)
action.setActionParameters(actionParameters)
action.setDescription("INSERT EMPRESA")
Process process = LCHelp.generateProcess(false, action, 0)
process.setIsProcessComplete(true)
process.setIsSubmitted(true)
process.setDescription("insert Atributos")
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
public InternalResultsResponse<Atributos> fetchAllAtributosFilial(AtributosRequest atributosRequest)
{
// TODO Auto-generated method stub
return null
}
}

