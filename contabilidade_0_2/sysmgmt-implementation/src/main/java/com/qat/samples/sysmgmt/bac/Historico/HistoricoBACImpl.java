/** create by system gera-java version 1.0.0 28/04/2016 20:29 : 56*/
package com.qat.samples.sysmgmt.bac.Historico;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.model.MessageLevel;
import com.qat.framework.model.MessageSeverity;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.SystemErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.advocacia.Advogado;
import com.qat.samples.sysmgmt.advocacia.Audiencia;
import com.qat.samples.sysmgmt.advocacia.Processo;
import com.qat.samples.sysmgmt.advocacia.request.AdvogadoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.AdvogadoMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.request.AudienciaInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.AudienciaMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoMaintenanceRequest;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.historico.model.Historico;
import com.qat.samples.sysmgmt.historico.model.request.HistoricoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * Standards based implementation of a BAC for Advogado leveraging the injected IAdvogadoBAR.
 */
@Component
public class HistoricoBACImpl implements IHistoricoBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_ADVOGADO_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_ADVOGADO_BAC_EXCEPTION_MSG = "sysmgmt.base.Advogadobacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(HistoricoBACImpl.class);

	/** The Advogado BAR. */
	private IHistoricoBAR historicoBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter



	public IHistoricoBAR getHistoricoBAR() {
		return historicoBAR;
	}

	public void setHistoricoBAR(IHistoricoBAR historicoBAR) {
		this.historicoBAR = historicoBAR;
	}

	/**
	 * Gets the validation controller
	 *
	 * @return ValidationController
	 */
	public ValidationController getValidationController()
	{
		return validationController;
	}

	/**
	 * Sets the validation Controller
	 *
	 * @param validationController
	 */
	public void setValidationController(ValidationController validationController)
	{
		this.validationController = validationController;
	}

	@Override
	public InternalResultsResponse<Historico> fetchHistoricoById(FetchByIdRequest request) {
		InternalResultsResponse<Historico> response = new InternalResultsResponse<Historico>();
		// validate fetchId field
		if (ValidationUtil.isNull(request.getFetchId()))
		{
			response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
			response.setStatus(SystemErrorCategory.SystemValidation);
		}
		else
		{
			response.getResultsList().add(getHistoricoBAR().fetchHistoricoById(request));
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Historico> fetchAllHistoricos(Historico historico) {

		InternalResultsResponse<Historico> response = new InternalResultsResponse<Historico>();
		response.getResultsList().addAll(getHistoricoBAR().fetchAllHistoricos(historico).getResultsList());
		return response;
	}

	@Override
	public InternalResultsResponse<Historico> fetchHistoricosByRequest(HistoricoInquiryRequest request) {
		return getHistoricoBAR().fetchHistoricosByRequest(request);
	}


}
