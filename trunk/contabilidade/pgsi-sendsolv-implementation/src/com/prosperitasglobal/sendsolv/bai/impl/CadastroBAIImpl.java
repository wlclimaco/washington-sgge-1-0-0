package com.prosperitasglobal.sendsolv.bai.impl;

import java.util.List;

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

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ILocaationBAI#insertLocaation(com.prosperitasglobal.sendsolv.model
	 * .request.LocaationRequest)
	 * Leveraging the common process method to perform the "real" work.
	 * Wrapped in a try-catch to ensure we never return an exception from this operation.
	 */
	@Override
	public CadastroResponse insertCadastro(CadastroMaintenanceRequest request)
	{
		CadastroResponse response = new CadastroResponse();
		try
		{
			response = process(ValidationContextIndicator.INSERT, PersistanceActionEnum.INSERT, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ILocaationBAI#updateLocaation(com.prosperitasglobal.sendsolv.model
	 * .request.LocaationRequest)
	 * Leveraging the common process method to perform the "real" work.
	 * Wrapped in a try-catch to ensure we never return an exception from this operation.
	 */
	@Override
	public CadastroResponse updateCadastro(CadastroMaintenanceRequest request)
	{
		CadastroResponse response = new CadastroResponse();
		try
		{
			response = process(ValidationContextIndicator.UPDATE, PersistanceActionEnum.UPDATE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ILocaationBAI#deleteLocaation(com.prosperitasglobal.sendsolv.model
	 * .request.LocaationRequest)
	 * Leveraging the common process method to perform the "real" work.
	 * Wrapped in a try-catch to ensure we never return an exception from this operation.
	 */
	@Override
	public CadastroResponse deleteCadastro(CadastroMaintenanceRequest request)
	{
		CadastroResponse response = new CadastroResponse();
		try
		{
			response = process(ValidationContextIndicator.DELETE, PersistanceActionEnum.DELETE, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ILocaationBAI#fetchLocaationById(com.prosperitasglobal.sendsolv.model.
	 * request
	 * .CountyRequest)
	 */
	@Override
	public CadastroResponse fetchCadastroById(FetchByIdRequest request)
	{
		CadastroResponse response = new CadastroResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();
			// validate fetchId field
			if (ValidationUtil.isNull(request.getId()) && ValidationUtil.isNullOrEmpty(request.getStringId()))
			{
				internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_ID_REQUIRED);
			}
			else
			{
				internalResponse = getCadastroBAC().fetchCadastroById(request);
			}
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bai.ICadastroBAI#fetchCadastroByRequest(com.prosperitasglobal.sendsolv.model
	 * .request.PagedInquiryRequest)
	 */
	@Override
	public CadastroResponse fetchCadastroByRequest(PagedInquiryRequest request)
	{
		CadastroResponse response = new CadastroResponse();
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

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bai.ICadastroBAI#updateRisk(com.prosperitasglobal.sendsolv.model.request.
	 * RiskMaintenanceRequest)
	 */

	/**
	 * Fetch paged.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private void fetchPaged(PagedInquiryRequest request, CadastroResponse response)
	{
		InternalResultsResponse<Cadastro> internalResponse = new InternalResultsResponse<Cadastro>();

		if (ValidationUtil.isNull(request.getPageSize()) || ValidationUtil.isNull(request.getStartPage()))
		{
			internalResponse.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);
		}
		else
		{
			internalResponse = getCadastroBAC().fetchCadastroByRequest(request);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the Cadastro response
	 */
	private CadastroResponse process(ValidationContextIndicator indicator, PersistanceActionEnum persistType,
			CadastroMaintenanceRequest request)
	{
		CadastroResponse response = new CadastroResponse();
		InternalResponse internalResponse = null;

		// Validate. Notice that BusinessValidator will in turn use additional validators depending on the type
		ValidationContext context =
				new ValidationContext(Cadastro.class.getSimpleName(), request.getCadastro(), indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(), request.getUserContext());

		if (getValidationController().validate(context))
		{
			// Persist
			internalResponse = doPersistance(request, persistType);
		}

		// Handle the processing for all previous methods regardless of them failing or succeeding.
		return handleReturn(response, internalResponse, context.getMessages(), true);
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
	private CadastroResponse handleReturn(CadastroResponse response, InternalResponse internalResponse,
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

	/**
	 * Do persistance.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistance(CadastroMaintenanceRequest request, PersistanceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getCadastroBAC().insertCadastro(request);

			case UPDATE:
				return getCadastroBAC().updateCadastro(request);

			case DELETE:
				return getCadastroBAC().deleteCadastro(request);

			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}
		return null;
	}

	@Override
	public InternalResultsResponse<Cidade> insertCidade(CidadeMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Cidade> updateCidade(CidadeMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deleteCidade(CidadeMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Cidade> fetchCidadeByRequest(CidadeInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Estado> insertEstado(EstadoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Estado> updateEstado(EstadoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deleteEstado(EstadoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Estado> fetchEstadoByRequest(EstadoInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Cnae> insertCnae(CnaeMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Cnae> updateCnae(CnaeMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deleteCnae(CnaeMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Cnae> fetchCnaeByRequest(CnaeInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Regime> insertRegime(RegimeMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Regime> updateRegime(RegimeMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deleteRegime(RegimeMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Regime> fetchRegimeByRequest(RegimeInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Csosn> insertCsosn(CsosnMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Csosn> updateCsosn(CsosnMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deleteCsosn(CsosnMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Csosn> fetchCsosnByRequest(CsosnInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<UniMed> insertCsosn(UniMedMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<UniMed> updateCsosn(UniMedMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deleteCsosn(UniMedMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<UniMed> fetchCsosnByRequest(UniMedInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Cfop> insertCfop(CfopMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Cfop> updateCfop(CfopMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Cfop> deleteCfop(CfopMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Cfop> fetchCfopByRequest(CfopInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
