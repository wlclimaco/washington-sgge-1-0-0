package com.sensus.mlc.ecomode.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleException;
import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.IMPORT_CSV_FILE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPSERT_ECOMODE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.CSV_FILE;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.END_DATE;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.INITIAL_DATE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.mlc.base.bcf.impl.AbstractBaseBCF;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.ecomode.bcf.IEcoModeBCF;
import com.sensus.mlc.ecomode.bcl.IEcoModeBCL;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.LightConsumption;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.mlc.ecomode.model.response.InquiryEcoModeResponse;
import com.sensus.mlc.process.bcf.IProcessBCF;
import com.sensus.mlc.process.model.response.ProcessResponse;

/**
 * The Class EcoModeBCFImpl.
 */
public class EcoModeBCFImpl extends AbstractBaseBCF implements IEcoModeBCF
{

	/** The Constant INQUIRY_ECOMODE_REQUEST_NAME. */
	private static final String INQUIRY_ECOMODE_REQUEST_NAME = InquiryEcoModeRequest.class.getSimpleName();

	/** The Constant ECOMODE_BASELINE_NAME. */
	private static final String ECOMODE_BASELINE_NAME = EcoModeBaseline.class.getSimpleName();

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

	/** The eco mode baseline controller. */
	private ValidationController ecoModeBaselineValidationController;

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
	 * Gets the eco mode baseline validation controller.
	 *
	 * @return the eco mode baseline validation controller
	 */
	public ValidationController getEcoModeBaselineValidationController()
	{
		return ecoModeBaselineValidationController;
	}

	/**
	 * Sets the eco mode baseline validation controller.
	 *
	 * @param ecoModeBaselineValidationController the new eco mode baseline validation controller
	 */
	public void setEcoModeBaselineValidationController(ValidationController ecoModeBaselineValidationController)
	{
		this.ecoModeBaselineValidationController = ecoModeBaselineValidationController;
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
	public InquiryEcoModeResponse upsertEcoMode(InquiryEcoModeRequest ecoModeRequest)
	{
		InquiryEcoModeResponse response = new InquiryEcoModeResponse();
		InternalResultsResponse<EcoModeBaseline> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPSERT_ECOMODE);
			context.putObjectToBeValidated(INQUIRY_ECOMODE_REQUEST_NAME, ecoModeRequest);
			context.putObjectToBeValidated(ECOMODE_BASELINE_NAME,
					ecoModeRequest.getFirstEcoModeBaseline());

			if (getLightingControlRequestValidationController().validate(context)
					&& getEcoModeBaselineValidationController().validate(context))
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
		InternalResultsResponse<LightConsumption> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), FETCH);
			context.putObjectToBeValidated(INQUIRY_ECOMODE_REQUEST_NAME, ecoModeRequest);
			context.putObjectToBeValidated(INITIAL_DATE.getValue(), ecoModeRequest.getInitialDate());
			context.putObjectToBeValidated(END_DATE.getValue(), ecoModeRequest.getEndDate());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context)
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
		InternalResultsResponse<LightConsumption> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), FETCH);
			context.putObjectToBeValidated(INQUIRY_ECOMODE_REQUEST_NAME, ecoModeRequest);
			context.putObjectToBeValidated(INITIAL_DATE.getValue(), ecoModeRequest.getInitialDate());
			context.putObjectToBeValidated(END_DATE.getValue(), ecoModeRequest.getEndDate());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context)
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
	public InquiryEcoModeResponse generateFileCSV(InquiryEcoModeRequest ecoModeRequest)
	{
		InquiryEcoModeResponse response = new InquiryEcoModeResponse();
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), FETCH);
			context.putObjectToBeValidated(INQUIRY_ECOMODE_REQUEST_NAME, ecoModeRequest);
			context.putObjectToBeValidated(INITIAL_DATE.getValue(), ecoModeRequest.getInitialDate());
			context.putObjectToBeValidated(END_DATE.getValue(), ecoModeRequest.getEndDate());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context)
					&& getLightSelectionRequestValidationController().validate(context)
					&& getRangeDateValidationController().validate(context))
			{
				return getEcoModeBCL().generateFileCSV(ecoModeRequest);
			}

			response.setOperationSuccess(false);
			response.addMessages(context.getMessages());
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
	public ProcessResponse insertCSVProcess(LightSelectionRequest lightSelectionRequest)
	{
		return getProcessBCF().insertCSVProcess(lightSelectionRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.ecomode.bcf.IEcoModeBCF#importEcoModeBaselineFromFileCSV(com.sensus.mlc.ecomode.model.request.
	 * InquiryEcoModeRequest)
	 */
	@Override
	public InquiryEcoModeResponse importEcoModeBaselineFromFileCSV(InquiryEcoModeRequest ecoModeRequest)
	{
		InquiryEcoModeResponse response = new InquiryEcoModeResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), IMPORT_CSV_FILE);
			context.putObjectToBeValidated(INQUIRY_ECOMODE_REQUEST_NAME, ecoModeRequest);
			context.putObjectToBeValidated(CSV_FILE.getValue(), ecoModeRequest.getEcoModeCSVImport());

			if (getLightingControlRequestValidationController().validate(context)
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
}
