package com.sensus.mlc.tabela.bcl.impl;

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
import com.sensus.mlc.tabela.bcl.ITabelaBCL;
import com.sensus.mlc.tabela.dac.ITabelaDAC;
import com.sensus.mlc.tabela.model.Tabela;
import com.sensus.mlc.tabela.model.request.TabelaRequest;
import com.sensus.mlc.tabela.model.request.InquiryTabelaRequest;
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
 * @author Washington
 */
public class TabelaBCLImpl implements ITabelaBCL {

	/** The LOG. */
	private static transient Log LOG = LogFactory.getLog(TabelaBCLImpl.class);

	/** The Tabela dac. */
	private ITabelaDAC TabelaDAC; // injected by Spring through setter

	/** The Constant PROCESS_BCL_BEAN. */
	private static final String PROCESS_BCL_BEAN = "processBCLTarget";

	/** The Constant SCHEDULE_BCL_BEAN. */
	private static final String SCHEDULE_BCL_BEAN = "scheduleBCLTarget";

	/** The Constant ACTION_PROVIDER_TYPE. */
	private static final String ACTION_PROVIDER_TYPE = "EPM.TASK";

	/** The Constant ADD_PROPERTY_TO_ACTION_FAILED. */
	private static final String ADD_PROPERTY_TO_ACTION_FAILED = "sensus.epm.actionbclimpl.add.property.to.action.failed";

	/** The Constant UPDATE_PROPERTY_TO_ACTION_FAILED. */
	private static final String UPDATE_PROPERTY_TO_ACTION_FAILED = "sensus.epm.actionbclimpl.update.property.to.action.failed";

	/** The Constant DELETE_PROPERTY_FROM_ACTION_FAILED. */
	private static final String DELETE_PROPERTY_FROM_ACTION_FAILED = "sensus.epm.actionbclimpl.delete.property.from.action.failed";

	/** The Constant PROPERTY_DAC_BEAN. */
	private static final String PROPERTY_DAC_BEAN = "propertyDACTarget";

	/** The Constant GET_ACTION_TO_CLONE_FAILED. */
	private static final String GET_ACTION_TO_CLONE_FAILED = "sensus.epm.actionbclimpl.get.action.to.clone.failed";

	/** The Constant APPLY_ACTION_FAILED. */
	private static final String APPLY_ACTION_FAILED = "sensus.epm.actionbclimpl.apply.action.failed";

	/** The Constant DATE_RAND_DIVISION. */
	private static final Integer DATE_RAND_DIVISION = 0xFFFFF;

	/**
	 * Gets the process bcl.
	 *
	 * @return the process bcl
	 */
public IProcessBCL getProcessBCL()
{
	return (IProcessBCL)SensusAppContext.getApplicationContext().getBean(PROCESS_BCL_BEAN);
}

	/**
	 * Gets the schedule bcl.
	 *
	 * @return the schedule bcl
	 */
	public IScheduleBCL getScheduleBCL() {
		return (IScheduleBCL) SensusAppContext.getApplicationContext().getBean(
				SCHEDULE_BCL_BEAN);
	}

	@Override
public InternalResultsResponse<Tabela> insertTabela(TabelaRequest TabelaRequest)
{
		InternalResultsResponse<Tabela> response = getTabelaDAC().insertTabela(TabelaRequest);

if (!response.isInError())
{
Tabela Tabela = response.getFirstResult();
TabelaRequest.setTabela(Tabela);

SearchParameter TabelaParameter =
new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(Tabela.getCodemp()));
TabelaRequest.getSearchLight().addSearchParameter(TabelaParameter);
InternalResultsResponse<Process> processResponse =
insertProcess(TabelaRequest, LCActionTypeEnum.INSERT_EMPRESA, null)
TabelaRequest.getSearchLight().getSearchParameters().remove(TabelaParameter);

response.setStatus(processResponse.getStatus());
response.addMessages(processResponse.getMessageInfoList());
}
return response;

}	@Override
public InternalResultsResponse<Tabela> updateTabela(TabelaRequest TabelaRequest)
{
InternalResponse groupResponse = new InternalResponse()

InternalResultsResponse<Tabela> internalResultsResponse = getTabelaDAC().updateTabela(TabelaRequest)

if (!ValidationUtil.isNull(groupResponse))
{
internalResultsResponse.addMessages(groupResponse.getMessageInfoList())
}

return internalResultsResponse
}	@Override
public InternalResponse deleteTabela(TabelaRequest TabelaRequest)
{

InternalResultsResponse<Tabela> tagResponse =
(InternalResultsResponse<Tabela>)getTabelaDAC().deleteTabela(TabelaRequest)
InternalResponse response = new InternalResponse()

if (tagResponse.isInError())
{
return response
}

response = getTabelaDAC().deleteTabela(TabelaRequest)

if (tagResponse.isInError())
{
return response
}

Tabela Tabela = tagResponse.getFirstResult()
TabelaRequest.setTabela(Tabela)

SearchParameter tagParameter =
new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(Tabela.getCodemp()))
TabelaRequest.getSearchLight().addSearchParameter(tagParameter)

InternalResultsResponse<Process> processResponse =
insertProcess(TabelaRequest, LCActionTypeEnum.DELETE_TAG, null)
TabelaRequest.getSearchLight().getSearchParameters().remove(tagParameter)

if (processResponse.isInError())
{
response.setStatus(processResponse.getStatus())
response.addMessages(processResponse.getMessageInfoList())
}

return response
}	@Override
public InternalResultsResponse<Tabela> fetchAllTabela(InquiryTabelaRequest inquiryTabelaRequest)
{
return getTabelaDAC().fetchAllTabela(inquiryTabelaRequest)
}	@Override
public InternalResultsResponse<Tabela> fetchTabelaById(TabelaRequest TabelaRequest)
{
return getTabelaDAC().fetchTabelaById(TabelaRequest)
}	public ITabelaDAC getTabelaDAC()
{
return TabelaDAC;
}	public void setTabelaDAC(ITabelaDAC TabelaDAC)
{
this.TabelaDAC = TabelaDAC;
}	@Override
public InternalResultsResponse<Tabela> fetchAllTabelaTypes(InquiryTabelaRequest inquiryTabelaRequest)
{
// TODO Auto-generated method stub
return null;
}	private InternalResultsResponse<Process> insertProcess(TabelaRequest TabelaRequest,
LCActionTypeEnum lcActionType,
String processDescription)
{
return insertProcess(TabelaRequest, lcActionType, processDescription, null);
}	/**
	 * Insert process.
	 *
	 * @param tagRequest
	 *            the tag request
	 * @param lcActionType
	 *            the lc action type
	 * @param processDescription
	 *            the process description
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(TabelaRequest tagRequest, LCActionTypeEnum lcActionType,
String processDescription, List<Light> deactivatedLights)
{
Tabela Tabela = tagRequest.getTabela();

List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
actionParameters.add(new LCActionParameter(PropertyEnum.TAG_ID, String.valueOf(Tabela.getCodemp())));
actionParameters.add(new LCActionParameter(PropertyEnum.TAG_NAME, Tabela.getCnpjemp()));

LCAction action = new LCAction(lcActionType);
action.setActionParameters(actionParameters);
action.setDescription("INSERT EMPRESA");
Process process = LCHelp.generateProcess(false, action, 0);
process.setIsProcessComplete(true);
process.setIsSubmitted(true);
process.setDescription("insert Tabela");
process.setRniCorrelationId("");


process.setEndTime(LCDateUtil.getNewDateUTC());

if (!ValidationUtil.isNullOrEmpty(processDescription))
{
	process.setDescription(processDescription);
}

ProcessRequest processRequest = createProcessRequest(tagRequest, process);
processRequest.setProcessItemFailureList(
createProcessItemWithFailure(
	deactivatedLights,
	ProcessItemStatusEnum.MLCFAILURE,
	ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

return getProcessBCL().insertProcess(processRequest);
}

	@Override
public InternalResultsResponse<Tabela> fetchAllTabelaFilial(TabelaRequest TabelaRequest)
{
	// TODO Auto-generated method stub
	return null;
}}
