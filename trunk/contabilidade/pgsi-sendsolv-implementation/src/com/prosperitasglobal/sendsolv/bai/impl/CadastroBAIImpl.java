package com.prosperitasglobal.sendsolv.bai.impl;

import java.util.List;

import javax.xml.ws.Response;

import com.prosperitasglobal.sendsolv.bac.ICadastroBAC;

/**
 * The Class CadastroBAIImpl.
 */
public class CadastroBAIImpl implements ICadastroBAC
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
	public InternalResultsResponse<Cidade> insertCidade(CidadeMaintenanceRequest request)
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
	public InternalResultsResponse<Cidade> updateCidade(CidadeMaintenanceRequest request)
	{
		CidadeResponse response = new CidadeResponse();
		try
		{
			response = processCidade(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public InternalResponse deleteCidade(CidadeMaintenanceRequest request)
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
	public InternalResultsResponse<Cidade> fetchCidadeByRequest(CidadeInquiryRequest request)
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
	public InternalResultsResponse<Estado> insertEstado(EstadoMaintenanceRequest request)
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
	public InternalResultsResponse<Estado> updateEstado(EstadoMaintenanceRequest request)
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
	public InternalResponse deleteEstado(EstadoMaintenanceRequest request)
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
	public InternalResultsResponse<Estado> fetchEstadoByRequest(EstadoInquiryRequest request)
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
	public InternalResultsResponse<Cnae> insertCnae(CnaeMaintenanceRequest request)
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
	public InternalResultsResponse<Cnae> updateCnae(CnaeMaintenanceRequest request)
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
	public InternalResponse deleteCnae(CnaeMaintenanceRequest request)
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
	public InternalResultsResponse<Cnae> fetchCnaeByRequest(CnaeInquiryRequest request)
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
	public InternalResultsResponse<Regime> insertRegime(RegimeMaintenanceRequest request)
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
	public InternalResultsResponse<Regime> updateRegime(RegimeMaintenanceRequest request)
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
	public InternalResponse deleteRegime(RegimeMaintenanceRequest request)
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
	public InternalResultsResponse<Regime> fetchRegimeByRequest(RegimeInquiryRequest request)
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
	public InternalResultsResponse<Csosn> insertCsosn(CsosnMaintenanceRequest request)
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
	public InternalResultsResponse<Csosn> updateCsosn(CsosnMaintenanceRequest request)
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
	public InternalResponse deleteCsosn(CsosnMaintenanceRequest request)
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
	public InternalResultsResponse<Csosn> fetchCsosnByRequest(CsosnInquiryRequest request)
	{
		CsosnResponse response = new CsosnResponse();
		try
		{
			fetchPaged(request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public InternalResultsResponse<UniMed> insertCsosn(UniMedMaintenanceRequest request)
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
	public InternalResultsResponse<UniMed> updateCsosn(UniMedMaintenanceRequest request)
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
	public InternalResponse deleteCsosn(UniMedMaintenanceRequest request)
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
	public InternalResultsResponse<UniMed> fetchCsosnByRequest(UniMedInquiryRequest request)
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
	public InternalResultsResponse<Cfop> insertCfop(CfopMaintenanceRequest request)
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
	public InternalResultsResponse<Cfop> updateCfop(CfopMaintenanceRequest request)
	{
		CfopResponse response = new CfopResponse();
		try
		{
			response = processCfop(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDADE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Cfop> deleteCfop(CfopMaintenanceRequest request)
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
	public InternalResultsResponse<Cfop> fetchCfopByRequest(CfopInquiryRequest request)
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
	private Response handleReturn(Response response, InternalResponse internalResponse,
			List<MessageInfo> messages, boolean copyOver)
	{
		// In the case there was an Optimistic Locking error, add the specific message
		if (!ValidationUtil.isNull(internalResponse) && !ValidationUtil.isNull(internalResponse.getStatus())
				&& Status.OptimisticLockingError.equals(internalResponse.getStatus()))
		{
			messages.add(new MessageInfo(PROSPERITASGLOBAL_BASE_OL_ERROR, MessageSeverity.Error,
					MessageLevel.Object));
		}

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
		return (CidadeResponse)handleReturn(response, internalResponse, context.getMessages(), true);
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
		return (EstadoResponse)handleReturn(response, internalResponse, context.getMessages(), true);
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
		return (CnaeResponse)handleReturn(response, internalResponse, context.getMessages(), true);
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
		return (RegimeResponse)handleReturn(response, internalResponse, context.getMessages(), true);
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
		return (CsosnResponse)handleReturn(response, internalResponse, context.getMessages(), true);
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
		return (UniMedResponse)handleReturn(response, internalResponse, context.getMessages(), true);
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
		return (CfopResponse)handleReturn(response, internalResponse, context.getMessages(), true);
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
		InternalResultsResponse<Empresa> internalResponse = new InternalResultsResponse<Empresa>();

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
		InternalResultsResponse<Empresa> internalResponse = new InternalResultsResponse<Empresa>();

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
		InternalResultsResponse<Empresa> internalResponse = new InternalResultsResponse<Empresa>();

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
		InternalResultsResponse<Empresa> internalResponse = new InternalResultsResponse<Empresa>();

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
		InternalResultsResponse<Empresa> internalResponse = new InternalResultsResponse<Empresa>();

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
		InternalResultsResponse<Empresa> internalResponse = new InternalResultsResponse<Empresa>();

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
		InternalResultsResponse<Empresa> internalResponse = new InternalResultsResponse<Empresa>();

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
