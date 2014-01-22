package com.sensus.lc.ecomode.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleException;
import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.FETCH_LIGHT_CONSUMPTION;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.IMPORT_CSV_FILE;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.UPSERT_ECOMODE;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum.CSV_FILE;
import static com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum.END_DATE;
import static com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum.INITIAL_DATE;
import static com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.bcf.impl.AbstractBaseBCF;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.ecomode.bcf.IEcoModeBCF;
import com.sensus.lc.ecomode.bcl.IEcoModeBCL;
import com.sensus.lc.ecomode.model.EcoModeOrderByEnum;
import com.sensus.lc.ecomode.model.request.EcoModeCSVRequest;
import com.sensus.lc.ecomode.model.request.EcoModeRequest;
import com.sensus.lc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.lc.ecomode.model.response.EcoModeResponse;
import com.sensus.lc.ecomode.model.response.InquiryEcoModeResponse;
import com.sensus.lc.light.model.Consumption;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.response.CSVInternalResponse;
import com.sensus.lc.light.model.response.CSVResponse;
import com.sensus.lc.process.bcf.IProcessBCF;
import com.sensus.lc.process.model.response.ProcessResponse;

/**
 * The Class EcoModeBCFImpl.
 */
public class EcoModeBCFImpl extends AbstractBaseBCF implements IEcoModeBCF
{

	/** The Constant TWENTY. */
	private static final Integer TWENTY = 20;

	/** The Constant INQUIRY_ECOMODE_REQUEST_NAME. */
	private static final String INQUIRY_ECOMODE_REQUEST_NAME = InquiryEcoModeRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */

	/** The Constant ECOMODE_REQUEST_NAME. */
	private static final String ECOMODE_REQUEST_NAME = EcoModeRequest.class.getSimpleName();

	/** The Constant ECOMODE_CSV_REQUEST_NAME. */
	private static final String ECOMODE_CSV_REQUEST_NAME = EcoModeCSVRequest.class.getSimpleName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(EcoModeBCFImpl.class);

	/** The Constant SENSUS_MLC_PROCESSBCLIMPL_DEFAULTEXCEPTION. */
	private static final String SENSUS_MLC_ECOMODEBCLIMPL_DEFAULTEXCEPTION =
			"sensus.mlc.ecomodebclimpl.defaultexception";

	/** The eco mode bcl. */
	private IEcoModeBCL ecoModeBCL;// injected by Spring through setter

	/** The process bcf. */
	private IProcessBCF processBCF;// injected by Spring through setter

	/** The csv file controller. */
	private ValidationController csvFileValidationController;

	/** The light list validation controller. */
	private ValidationController lightListValidationController;

	/** The request validation controller. */
	private ValidationController requestValidationController;

	/** The inquiry request validation controller. */
	private ValidationController inquiryRequestValidationController;

	/**
	 * Gets the csv file validation controller.
	 * 
	 * @return the csv file validation controller
	 */
	public ValidationController getCsvFileValidationController()
	{
		return csvFileValidationController;
	}

	/**
	 * Sets the csv file validation controller.
	 * 
	 * @param csvFileValidationController the new csv file validation controller
	 */
	public void setCsvFileValidationController(ValidationController csvFileValidationController)
	{
		this.csvFileValidationController = csvFileValidationController;
	}

	/**
	 * Gets the light list validation controller.
	 * 
	 * @return the light list validation controller
	 */
	public ValidationController getLightListValidationController()
	{
		return lightListValidationController;
	}

	/**
	 * Sets the light list validation controller.
	 * 
	 * @param lightListValidationController the new light list validation controller
	 */
	public void setLightListValidationController(ValidationController lightListValidationController)
	{
		this.lightListValidationController = lightListValidationController;
	}

	/**
	 * Gets the request validation controller.
	 * 
	 * @return the request validation controller
	 */
	@Override
	public ValidationController getRequestValidationController()
	{
		return requestValidationController;
	}

	/**
	 * Sets the request validation controller.
	 * 
	 * @param requestValidationController
	 *            the new request validation controller
	 */
	@Override
	public void setRequestValidationController(
			ValidationController requestValidationController)
	{
		this.requestValidationController = requestValidationController;
	}

	/**
	 * Gets the inquiry request validation controller.
	 * 
	 * @return the inquiry request validation controller
	 */
	@Override
	public ValidationController getInquiryRequestValidationController()
	{
		return inquiryRequestValidationController;
	}

	/**
	 * Sets the inquiry request validation controller.
	 * 
	 * @param inquiryRequestValidationController
	 *            the new inquiry request validation controller
	 */
	@Override
	public void setInquiryRequestValidationController(
			ValidationController inquiryRequestValidationController)
	{
		this.inquiryRequestValidationController = inquiryRequestValidationController;
	}

	/**
	 * Gets the eco mode bcl.
	 * 
	 * @return the eco mode bcl
	 */
	public IEcoModeBCL getEcoModeBCL()
	{
		return ecoModeBCL;
	}

	/**
	 * Sets the eco mode bcl.
	 * 
	 * @param ecoModeBCL the new eco mode bcl
	 */
	public void setEcoModeBCL(IEcoModeBCL ecoModeBCL)
	{
		this.ecoModeBCL = ecoModeBCL;
	}

	/**
	 * Gets the process bcf.
	 * 
	 * @return the process bcf
	 */
	public IProcessBCF getProcessBCF()
	{
		return processBCF;
	}

	/**
	 * Sets the process bcf.
	 * 
	 * @param processBCF the new process bcf
	 */
	public void setProcessBCF(IProcessBCF processBCF)
	{
		this.processBCF = processBCF;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.ecomode.bcf.IEcoModeBCF#upsertEcoMode(com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest)
	 */
	@Override
	public EcoModeResponse upsertEcoMode(EcoModeRequest ecoModeRequest)
	{
		EcoModeResponse response = new EcoModeResponse();
		InternalResultsResponse<Light> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPSERT_ECOMODE);
			context.putObjectToBeValidated(ECOMODE_REQUEST_NAME, ecoModeRequest);
			context.putObjectToBeValidated(LIGHT_LIST.getValue(), ecoModeRequest.getLights());

			if (getRequestValidationController().validate(context)
					&& getLightListValidationController().validate(context))
			{
				// upsert EcoMode
				internalResponse = getEcoModeBCL().upsertEcoMode(ecoModeRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, SENSUS_MLC_ECOMODEBCLIMPL_DEFAULTEXCEPTION);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.ecomode.bcf.IEcoModeBCF#fetchLightConsumptionsToChart(com.sensus.mlc.ecomode.model.request.
	 * InquiryEcoModeRequest)
	 */
	@Override
	public InquiryEcoModeResponse fetchLightConsumptionsToChart(InquiryEcoModeRequest ecoModeRequest)
	{
		InquiryEcoModeResponse response = new InquiryEcoModeResponse();
		InternalResultsResponse<Consumption> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), FETCH_LIGHT_CONSUMPTION);
			context.putObjectToBeValidated(INQUIRY_ECOMODE_REQUEST_NAME, ecoModeRequest);
			context.putObjectToBeValidated(INITIAL_DATE.getValue(), ecoModeRequest.getInitialDate());
			context.putObjectToBeValidated(END_DATE.getValue(), ecoModeRequest.getEndDate());

			checkDefaultsFetchAll(ecoModeRequest);
			if (getRequestValidationController().validate(context)
					&& getInquiryRequestValidationController().validate(context)
					&& getLightSelectionRequestValidationController().validate(context)
					&& getRangeDateValidationController().validate(context))
			{
				// fetch light consumptions
				internalResponse = getEcoModeBCL().fetchLightConsumptionsToChart(ecoModeRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, SENSUS_MLC_ECOMODEBCLIMPL_DEFAULTEXCEPTION);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.ecomode.bcf.IEcoModeBCF#fetchLightConsumptionsByLightId(com.sensus.mlc.ecomode.model.request.
	 * InquiryEcoModeRequest)
	 */
	@Override
	public InquiryEcoModeResponse fetchLightConsumptionsByLightId(InquiryEcoModeRequest ecoModeRequest)
	{
		InquiryEcoModeResponse response = new InquiryEcoModeResponse();
		InternalResultsResponse<Consumption> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), FETCH_LIGHT_CONSUMPTION);
			context.putObjectToBeValidated(INQUIRY_ECOMODE_REQUEST_NAME, ecoModeRequest);
			context.putObjectToBeValidated(INITIAL_DATE.getValue(), ecoModeRequest.getInitialDate());
			context.putObjectToBeValidated(END_DATE.getValue(), ecoModeRequest.getEndDate());

			checkDefaultsFetchAll(ecoModeRequest);
			if (getRequestValidationController().validate(context)
					&& getInquiryRequestValidationController().validate(context)
					&& getLightSelectionRequestValidationController().validate(context)
					&& getRangeDateValidationController().validate(context))
			{
				// fetch light consumptions
				internalResponse = getEcoModeBCL().fetchLightConsumptionsByLightId(ecoModeRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, SENSUS_MLC_ECOMODEBCLIMPL_DEFAULTEXCEPTION);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.ecomode.bcf.IEcoModeBCF#generateFileCSV(com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest
	 * )
	 */
	@Override
	public CSVResponse generateFileCSV(EcoModeCSVRequest ecoModeRequest)
	{
		CSVResponse response = new CSVResponse();
		CSVInternalResponse internalResponse = null;
		try
		{
			ValidationContext requestContext = new ValidationContext();
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_ECOMODE_REQUEST_NAME,
					ecoModeRequest.getInquiryEcoModeRequest());
			context.getValidationArguments().put(getSlcActionName(), FETCH_LIGHT_CONSUMPTION);
			requestContext.putObjectToBeValidated(ECOMODE_CSV_REQUEST_NAME, ecoModeRequest);
			context.putObjectToBeValidated(INITIAL_DATE.getValue(), ecoModeRequest.getInquiryEcoModeRequest()
					.getInitialDate());
			context.putObjectToBeValidated(END_DATE.getValue(), ecoModeRequest.getInquiryEcoModeRequest().getEndDate());

			if (!getRequestValidationController().validate(requestContext)) // Validate Tenant and UserContext
			{
				handleOperationStatusAndMessages(response, internalResponse, requestContext.getMessages(), false);
				return response;
			}

			if (getInquiryRequestValidationController().validate(context)
					&& getLightSelectionRequestValidationController().validate(context)
					&& getRangeDateValidationController().validate(context))
			{
				internalResponse = getEcoModeBCL().generateFileCSV(ecoModeRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, SENSUS_MLC_ECOMODEBCLIMPL_DEFAULTEXCEPTION);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.ecomode.bcf.IEcoModeBCF#insertCSVProcess(com.sensus.mlc.base.model.request.LightSelectionRequest)
	 */
	@Override
	public ProcessResponse insertCSVProcess(InquiryPaginationRequest request)
	{
		return getProcessBCF().insertCSVProcess(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.ecomode.bcf.IEcoModeBCF#importEcoModeBaselineFromFileCSV(com.sensus.mlc.ecomode.model.request.
	 * InquiryEcoModeRequest)
	 */
	@Override
	public EcoModeResponse importEcoModeBaselineFromFileCSV(EcoModeRequest ecoModeRequest)
	{
		EcoModeResponse response = new EcoModeResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), IMPORT_CSV_FILE);
			context.putObjectToBeValidated(ECOMODE_REQUEST_NAME, ecoModeRequest);
			context.putObjectToBeValidated(CSV_FILE.getValue(), ecoModeRequest.getEcoModeCSVImport());

			if (getRequestValidationController().validate(context)
					&& getCsvFileValidationController().validate(context))
			{
				// Import eco mode baseline
				internalResponse = getEcoModeBCL().importEcoModeBaselineFromFileCSV(ecoModeRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, SENSUS_MLC_ECOMODEBCLIMPL_DEFAULTEXCEPTION);
		}
		return response;
	}

	/**
	 * Check defaults fetch all.
	 * 
	 * @param inquiryPaginationRequest the inquiry pagination request
	 */
	private void checkDefaultsFetchAll(InquiryPaginationRequest inquiryPaginationRequest)
	{
		if (ValidationUtil.isNull(inquiryPaginationRequest.getPageSize())
				|| inquiryPaginationRequest.getPageSize().equals(TWENTY))
		{
			inquiryPaginationRequest.setPageSize(new Integer(0));
		}

		if (!ValidationUtil.isNullOrEmpty(inquiryPaginationRequest.getSortExpressions()))
		{
			return;
		}

		SortExpression sortExpression = new SortExpression();
		sortExpression.setField(EcoModeOrderByEnum.DATE.getValue());
		sortExpression.setDirection(Direction.Descending);
		inquiryPaginationRequest.addSortExpressions(sortExpression);
	}
}
