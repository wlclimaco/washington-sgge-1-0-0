package com.prosperitasglobal.sendsolv.bai.impl;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.bac.ICadastroBAC;
import com.prosperitasglobal.sendsolv.bai.ICadastrosBAI;
import com.prosperitasglobal.sendsolv.model.Cfop;
import com.prosperitasglobal.sendsolv.model.Cidade;
import com.prosperitasglobal.sendsolv.model.Cnae;
import com.prosperitasglobal.sendsolv.model.Csosn;
import com.prosperitasglobal.sendsolv.model.Estado;
import com.prosperitasglobal.sendsolv.model.Regime;
import com.prosperitasglobal.sendsolv.model.UniMed;
import com.prosperitasglobal.sendsolv.model.request.CfopInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CfopMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.CidadeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CidadeMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.CnaeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CnaeMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.CsosnInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CsosnMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.EstadoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.EstadoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.RegimeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RegimeMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.UniMedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.UniMedMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.CfopResponse;
import com.prosperitasglobal.sendsolv.model.response.CidadeResponse;
import com.prosperitasglobal.sendsolv.model.response.CnaeResponse;
import com.prosperitasglobal.sendsolv.model.response.CsosnResponse;
import com.prosperitasglobal.sendsolv.model.response.EstadoResponse;
import com.prosperitasglobal.sendsolv.model.response.RegimeResponse;
import com.prosperitasglobal.sendsolv.model.response.UniMedResponse;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.UserContext;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CadastroBAIImpl.
 */
public class CadastroBAIImpl implements ICadastrosBAI
{
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = CadastroBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CadastroBAIImpl.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED =
			"sendsolv.base.Cadastrovalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED =
			"sendsolv.base.validator.paging.parameters.required";

	/** The Constant PROSPERITASGLOBAL_BASE_OL_ERROR. */
	private static final String PROSPERITASGLOBAL_BASE_OL_ERROR =
			"sendsolv.base.optimistic.locking.error";

	/** The Cadastro bac. */
	private ICadastroBAC CadastroBAC; // injected by Spring through setter

	/** The validation controller. */
	private ValidationController validationController;

	/**
	 * Get Cadastro validation controller.
	 *
	 * @return the validation controller
	 */
	public ValidationController getValidationController()
	{
		return validationController;
	}

	/**
	 * Spring sets the Cadastro validation controller.
	 *
	 * @param CadastroValidationController the new validation controller
	 */
	public void setValidationController(ValidationController CadastroValidationController)
	{
		validationController = CadastroValidationController;
	}

	/**
	 * Spring Sets the Cadastro bac.
	 *
	 * @param CadastroBAC the new Cadastro bac
	 */
	public void setCadastroBAC(ICadastroBAC CadastroBAC)
	{
		this.CadastroBAC = CadastroBAC;
	}

	/**
	 * Gets the Cadastro bac.
	 *
	 * @return the Cadastro bac
	 */
	public ICadastroBAC getCadastroBAC()
	{
		return CadastroBAC;
	}

	@Override
	public CidadeResponse insertCidade(CidadeMaintenanceRequest request)
	{
		CidadeResponse response = new CidadeResponse();
		try
		{
			response = processCidade(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public CidadeResponse updateCidade(CidadeMaintenanceRequest request)
	{
		CidadeResponse response = new CidadeResponse();
		try
		{
			response = processCidade(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public CidadeResponse deleteCidade(CidadeMaintenanceRequest request)
	{
		CidadeResponse response = new CidadeResponse();
		try
		{
			response = processCidade(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public CidadeResponse fetchCidadeByRequest(CidadeInquiryRequest request)
	{
		CidadeResponse response = new CidadeResponse();
		try
		{
			fetchCidade(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public EstadoResponse insertEstado(EstadoMaintenanceRequest request)
	{
		EstadoResponse response = new EstadoResponse();
		try
		{
			response = processEstado(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public EstadoResponse updateEstado(EstadoMaintenanceRequest request)
	{
		EstadoResponse response = new EstadoResponse();
		try
		{
			response = processEstado(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public EstadoResponse deleteEstado(EstadoMaintenanceRequest request)
	{
		EstadoResponse response = new EstadoResponse();
		try
		{
			response = processEstado(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public EstadoResponse fetchEstadoByRequest(EstadoInquiryRequest request)
	{
		EstadoResponse response = new EstadoResponse();
		try
		{
			fetchEstado(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public CnaeResponse insertCnae(CnaeMaintenanceRequest request)
	{
		CnaeResponse response = new CnaeResponse();
		try
		{
			response = processCnae(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public CnaeResponse updateCnae(CnaeMaintenanceRequest request)
	{
		CnaeResponse response = new CnaeResponse();
		try
		{
			response = processCnae(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public CnaeResponse deleteCnae(CnaeMaintenanceRequest request)
	{
		CnaeResponse response = new CnaeResponse();
		try
		{
			response = processCnae(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public CnaeResponse fetchCnaeByRequest(CnaeInquiryRequest request)
	{
		CnaeResponse response = new CnaeResponse();
		try
		{
			fetchCnae(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public RegimeResponse insertRegime(RegimeMaintenanceRequest request)
	{
		RegimeResponse response = new RegimeResponse();
		try
		{
			response = processRegime(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public RegimeResponse updateRegime(RegimeMaintenanceRequest request)
	{
		RegimeResponse response = new RegimeResponse();
		try
		{
			response = processRegime(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public RegimeResponse deleteRegime(RegimeMaintenanceRequest request)
	{
		RegimeResponse response = new RegimeResponse();
		try
		{
			response = processRegime(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public RegimeResponse fetchRegimeByRequest(RegimeInquiryRequest request)
	{
		RegimeResponse response = new RegimeResponse();
		try
		{
			fetchRegime(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public CsosnResponse insertCsosn(CsosnMaintenanceRequest request)
	{
		CsosnResponse response = new CsosnResponse();
		try
		{
			response = processCsosn(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public CsosnResponse updateCsosn(CsosnMaintenanceRequest request)
	{
		CsosnResponse response = new CsosnResponse();
		try
		{
			response = processCsosn(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public CsosnResponse deleteCsosn(CsosnMaintenanceRequest request)
	{
		CsosnResponse response = new CsosnResponse();
		try
		{
			response = processCsosn(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public CsosnResponse fetchCsosnByRequest(CsosnInquiryRequest request)
	{
		CsosnResponse response = new CsosnResponse();
		try
		{
			fetchCsosn(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public UniMedResponse insertCsosn(UniMedMaintenanceRequest request)
	{
		UniMedResponse response = new UniMedResponse();
		try
		{
			response = processUniMed(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public UniMedResponse updateCsosn(UniMedMaintenanceRequest request)
	{
		UniMedResponse response = new UniMedResponse();
		try
		{
			response = processUniMed(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public UniMedResponse deleteCsosn(UniMedMaintenanceRequest request)
	{
		UniMedResponse response = new UniMedResponse();
		try
		{
			response = processUniMed(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public UniMedResponse fetchCsosnByRequest(UniMedInquiryRequest request)
	{
		UniMedResponse response = new UniMedResponse();
		try
		{
			fetchUniMed(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public CfopResponse insertCfop(CfopMaintenanceRequest request)
	{
		CfopResponse response = new CfopResponse();
		try
		{
			response = processCfop(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public CfopResponse updateCfop(CfopMaintenanceRequest request)
	{
		CfopResponse response = new CfopResponse();
		try
		{
			response = processCfop(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public CfopResponse deleteCfop(CfopMaintenanceRequest request)
	{
		CfopResponse response = new CfopResponse();
		try
		{
			response = processCfop(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public CfopResponse fetchCfopByRequest(CfopInquiryRequest request)
	{
		CfopResponse response = new CfopResponse();
		try
		{
			fetchCfop(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/**
	 * Handle return.
	 *
	 * @param response the response
	 * @param internalResponse the internal response
	 * @param messages the messages
	 * @param copyOver the copy over
	 * @return the response
	 */
	private CidadeResponse handleReturnCidade(CidadeResponse response, InternalResponse internalResponse,
			List<MessageInfo> messages, boolean copyOver)
	{

		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, messages, copyOver);
		return response;
	}

	private EstadoResponse handleReturnEstado(EstadoResponse response, InternalResponse internalResponse,
			List<MessageInfo> messages, boolean copyOver)
	{

		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, messages, copyOver);
		return response;
	}

	private CnaeResponse handleReturnCnae(CnaeResponse response, InternalResponse internalResponse,
			List<MessageInfo> messages, boolean copyOver)
	{

		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, messages, copyOver);
		return response;
	}

	private RegimeResponse handleReturnRegime(RegimeResponse response, InternalResponse internalResponse,
			List<MessageInfo> messages, boolean copyOver)
	{

		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, messages, copyOver);
		return response;
	}

	private CsosnResponse handleReturnCsosn(CsosnResponse response, InternalResponse internalResponse,
			List<MessageInfo> messages, boolean copyOver)
	{

		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, messages, copyOver);
		return response;
	}

	private UniMedResponse handleReturnUniMed(UniMedResponse response, InternalResponse internalResponse,
			List<MessageInfo> messages, boolean copyOver)
	{

		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, messages, copyOver);
		return response;
	}

	private CfopResponse handleReturnCfop(CfopResponse response, InternalResponse internalResponse,
			List<MessageInfo> messages, boolean copyOver)
	{

		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, messages, copyOver);
		return response;
	}

	private CidadeResponse processCidade(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			CidadeMaintenanceRequest request)
	{
		CidadeResponse response = new CidadeResponse();
		InternalResponse internalResponse = null;

		// Validate. Notice that BusinessValidator will in turn use additional validators depending on the type
		ValidationContext context =
				new ValidationContext(Cidade.class.getSimpleName(), request.getCidade(), indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getValidationController().validate(context))
		{
			// Persist
			internalResponse = doPersistanceCidade(request, persistType);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return handleReturnCidade(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Do persistance.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistanceCidade(CidadeMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getCadastroBAC().insertCidade(request);

			case UPDATE:
				return getCadastroBAC().updateCidade(request);

			case DELETE:
				return getCadastroBAC().deleteCidade(request);

			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}
		return null;
	}

	// ================================
	private EstadoResponse processEstado(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			EstadoMaintenanceRequest request)
	{
		EstadoResponse response = new EstadoResponse();
		InternalResponse internalResponse = null;

		// Validate. Notice that BusinessValidator will in turn use additional validators depending on the type
		ValidationContext context =
				new ValidationContext(Estado.class.getSimpleName(), request.getEstado(), indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getValidationController().validate(context))
		{
			// Persist
			internalResponse = doPersistanceEstado(request, persistType);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return handleReturnEstado(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Do persistance.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistanceEstado(EstadoMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getCadastroBAC().insertEstado(request);

			case UPDATE:
				return getCadastroBAC().updateEstado(request);

			case DELETE:
				return getCadastroBAC().deleteEstado(request);

			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}
		return null;
	}

	// ================================
	private CnaeResponse processCnae(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			CnaeMaintenanceRequest request)
	{
		CnaeResponse response = new CnaeResponse();
		InternalResponse internalResponse = null;

		// Validate. Notice that BusinessValidator will in turn use additional validators depending on the type
		ValidationContext context =
				new ValidationContext(Cnae.class.getSimpleName(), request.getCnae(), indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getValidationController().validate(context))
		{
			// Persist
			internalResponse = doPersistanceCnae(request, persistType);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return handleReturnCnae(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Do persistance.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistanceCnae(CnaeMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getCadastroBAC().insertCnae(request);

			case UPDATE:
				return getCadastroBAC().updateCnae(request);

			case DELETE:
				return getCadastroBAC().deleteCnae(request);

			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}
		return null;
	}

	// ================================
	private RegimeResponse processRegime(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			RegimeMaintenanceRequest request)
	{
		RegimeResponse response = new RegimeResponse();
		InternalResponse internalResponse = null;

		// Validate. Notice that BusinessValidator will in turn use additional validators depending on the type
		ValidationContext context =
				new ValidationContext(Regime.class.getSimpleName(), request.getRegime(), indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getValidationController().validate(context))
		{
			// Persist
			internalResponse = doPersistanceRegime(request, persistType);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return handleReturnRegime(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Do persistance.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistanceRegime(RegimeMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getCadastroBAC().insertRegime(request);

			case UPDATE:
				return getCadastroBAC().updateRegime(request);

			case DELETE:
				return getCadastroBAC().deleteRegime(request);

			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}
		return null;
	}

	// ================================
	private CsosnResponse processCsosn(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			CsosnMaintenanceRequest request)
	{
		CsosnResponse response = new CsosnResponse();
		InternalResponse internalResponse = null;

		// Validate. Notice that BusinessValidator will in turn use additional validators depending on the type
		ValidationContext context =
				new ValidationContext(Csosn.class.getSimpleName(), request.getCsosn(), indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getValidationController().validate(context))
		{
			// Persist
			internalResponse = doPersistanceCsosn(request, persistType);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return handleReturnCsosn(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Do persistance.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistanceCsosn(CsosnMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getCadastroBAC().insertCsosn(request);

			case UPDATE:
				return getCadastroBAC().updateCsosn(request);

			case DELETE:
				return getCadastroBAC().deleteCsosn(request);

			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}
		return null;
	}

	// ================================
	private UniMedResponse processUniMed(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			UniMedMaintenanceRequest request)
	{
		UniMedResponse response = new UniMedResponse();
		InternalResponse internalResponse = null;

		// Validate. Notice that BusinessValidator will in turn use additional validators depending on the type
		ValidationContext context =
				new ValidationContext(UniMed.class.getSimpleName(), request.getUniMed(), indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getValidationController().validate(context))
		{
			// Persist
			internalResponse = doPersistanceUniMed(request, persistType);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return handleReturnUniMed(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Do persistance.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistanceUniMed(UniMedMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getCadastroBAC().insertUniMed(request);

			case UPDATE:
				return getCadastroBAC().updateUniMed(request);

			case DELETE:
				return getCadastroBAC().deleteUniMed(request);

			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}
		return null;
	}

	// ================================
	private CfopResponse processCfop(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			CfopMaintenanceRequest request)
	{
		CfopResponse response = new CfopResponse();
		InternalResponse internalResponse = null;

		// Validate. Notice that BusinessValidator will in turn use additional validators depending on the type
		ValidationContext context =
				new ValidationContext(Cfop.class.getSimpleName(), request.getCfop(), indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getValidationController().validate(context))
		{
			// Persist
			internalResponse = doPersistanceCfop(request, persistType);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return handleReturnCfop(response, internalResponse, context.getMessages(), true);
	}

	/**
	 * Do persistance.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistanceCfop(CfopMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getCadastroBAC().insertCfop(request);

			case UPDATE:
				return getCadastroBAC().updateCfop(request);

			case DELETE:
				return getCadastroBAC().deleteCfop(request);

			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}
		return null;
	}

	// ******************************************
	private void fetchCidade(CidadeInquiryRequest request, CidadeResponse response)
	{
		InternalResultsResponse<Cidade> internalResponse = new InternalResultsResponse<Cidade>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getCadastroBAC().fetchCidadeByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	// ******************************************
	private void fetchEstado(EstadoInquiryRequest request, EstadoResponse response)
	{
		InternalResultsResponse<Estado> internalResponse = new InternalResultsResponse<Estado>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getCadastroBAC().fetchEstadoByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	// ******************************************
	private void fetchCnae(CnaeInquiryRequest request, CnaeResponse response)
	{
		InternalResultsResponse<Cnae> internalResponse = new InternalResultsResponse<Cnae>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getCadastroBAC().fetchCnaeByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	// ******************************************
	private void fetchRegime(RegimeInquiryRequest request, RegimeResponse response)
	{
		InternalResultsResponse<Regime> internalResponse = new InternalResultsResponse<Regime>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getCadastroBAC().fetchRegimeByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	// ******************************************
	private void fetchCsosn(CsosnInquiryRequest request, CsosnResponse response)
	{
		InternalResultsResponse<Csosn> internalResponse = new InternalResultsResponse<Csosn>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getCadastroBAC().fetchCsosnByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	// ******************************************
	private void fetchUniMed(UniMedInquiryRequest request, UniMedResponse response)
	{
		InternalResultsResponse<UniMed> internalResponse = new InternalResultsResponse<UniMed>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getCadastroBAC().fetchUniMedByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	// ******************************************
	private void fetchCfop(CfopInquiryRequest request, CfopResponse response)
	{
		InternalResultsResponse<Cfop> internalResponse = new InternalResultsResponse<Cfop>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getCadastroBAC().fetchCfopByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}
}
