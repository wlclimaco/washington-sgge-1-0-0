package com.sensus.dm.common.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.record.formula.functions.T;

import com.sensus.api.getlatestreading.messages.GetLatestReadingResponse;
import com.sensus.api.getmeterreadings.messages.GetMeterReadingsResponse;
import com.sensus.common.messagetypes.meter.MeterError;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.model.response.Response;
import com.sensus.dm.common.device.model.CustomSearch;

/**
 * The Class AbstractMockBase.
 */
public class AbstractMockBase
{
	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(AbstractMockBase.class);

	/** The Constant LOG_EXCEPTION_MESSAGE. */
	private static final Object LOG_EXCEPTION_MESSAGE = "Can not set area enum because an exception occurred";

	/** The Constant ERROR_CODE. */
	protected static final String ERROR_CODE = "error";

	/** The Constant TEST_EXCEPTION. */
	protected static final String TEST_EXCEPTION = "Test Exception";

	/** The Constant OTA_METER_FLEXNET2_ID. */
	protected static final BigInteger OTA_METER_FLEXNET2_ID = new BigInteger("46549476");

	/** The area enum. */
	private EPMAreaEnum areaEnum = EPMAreaEnum.DEFAULT;

	/** The situations enum. */
	private SituationsEnum situationsEnum = SituationsEnum.SUCCESS;

	/** The evaluable class. */
	private Class<?> evaluableClass;

	/** The methods. */
	private List<String> evaluableMethods = new ArrayList<String>();

	protected static final Integer ONE = 1;

	/**
	 * Gets the evaluable class.
	 * 
	 * @return the evaluable class
	 */
	public Class<?> getEvaluableClass()
	{
		return evaluableClass;
	}

	/**
	 * Sets the evaluable class.
	 * 
	 * @param evaluableClass the new evaluable class
	 */
	public void setEvaluableClass(Class<?> evaluableClass)
	{
		this.evaluableClass = evaluableClass;
	}

	/**
	 * Gets the methods.
	 * 
	 * @return the methods
	 */
	public List<String> getEvaluableMethods()
	{
		return evaluableMethods;
	}

	/**
	 * Sets the evaluable methods.
	 * 
	 * @param evaluableMethods the new evaluable methods
	 */
	public void setEvaluableMethods(List<String> evaluableMethods)
	{
		this.evaluableMethods = evaluableMethods;
	}

	/**
	 * Adds the evaluable method.
	 * 
	 * @param method the method
	 */
	public void addEvaluableMethod(String method)
	{
		getEvaluableMethods().add(method);
	}

	/**
	 * Checks if is valid situation.
	 * 
	 * @param className the class name
	 * @param methodName the method name
	 * @return true, if is valid situation
	 * @throws ClassNotFoundException the class not found exception
	 */
	private boolean isValidSituation(String className, String methodName) throws ClassNotFoundException
	{
		return getEvaluableClass().isAssignableFrom(Class.forName(className)) &&
				getEvaluableMethods().contains(methodName);

	}

	/**
	 * Gets the situations enum.
	 * 
	 * @return the situations enum
	 */
	public SituationsEnum getSituationsEnum()
	{
		try
		{
			if (getEvaluableMethods().isEmpty() || (getEvaluableClass() == null))
			{
				return situationsEnum;
			}

			StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
			for (int i = 0; i < (stackTrace.length - 1); i++)
			{
				if (isValidSituation(stackTrace[i].getClassName(), stackTrace[i].getMethodName())
						|| isValidSituation(stackTrace[i + 1].getClassName(), stackTrace[i + 1].getMethodName()))
				{
					return situationsEnum;
				}
			}
		}
		catch (Exception e)
		{
			LOG.error(LOG_EXCEPTION_MESSAGE, e);
		}

		// Situation default
		return SituationsEnum.SUCCESS;
	}

	/**
	 * Sets the situations enum.
	 * 
	 * @param situationsEnum the new situations enum
	 */
	public void setSituationsEnum(SituationsEnum situationsEnum)
	{
		this.situationsEnum = situationsEnum;
	}

	/**
	 * Gets the area enum.
	 * 
	 * @return the area enum
	 */
	public EPMAreaEnum getAreaEnum()
	{
		return areaEnum;
	}

	/**
	 * Sets the area enum.
	 * 
	 * @param areaEnum the new area enum
	 */
	public void setAreaEnum(EPMAreaEnum areaEnum)
	{
		this.areaEnum = areaEnum;
	}

	/**
	 * Result response custom search.
	 * 
	 * @return the internal results response
	 */
	protected InternalResultsResponse<CustomSearch> getResultResponseCustomSearch()
	{
		InternalResultsResponse<CustomSearch> response = new InternalResultsResponse<CustomSearch>();
		response.getResultsList().add(new CustomSearch(1));
		return response;
	}

	/**
	 * Gets the response default.
	 * 
	 * @return the response default
	 */
	protected Response getResponseDefault()
	{
		return new Response();
	}

	/**
	 * Gets the internal response default.
	 * 
	 * @return the internal response default
	 */
	protected InternalResponse getInternalResponseDefault()
	{
		return new InternalResponse();
	}

	/**
	 * Gets the internal results response error.
	 * 
	 * @return the internal results response error
	 */
	@SuppressWarnings("rawtypes")
	protected InternalResultsResponse getInternalResultsResponseError()
	{
		InternalResultsResponse response = new InternalResultsResponse();
		response.setStatus(Status.ExceptionError);
		response.addFieldErrorMessage(ERROR_CODE);
		return response;
	}

	/**
	 * Gets the internal response error.
	 * 
	 * @return the internal response error
	 */
	protected InternalResponse getInternalResponseError()
	{
		InternalResponse response = new InternalResponse();
		response.setStatus(Status.ExceptionError);
		response.addFieldErrorMessage(ERROR_CODE);
		return response;
	}

	/**
	 * Verify other situations.
	 * 
	 * @return the internal results response
	 */
	protected InternalResultsResponse<?> verifyOtherSituations()
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return new InternalResultsResponse<T>();
	}

	/**
	 * Verify other situations.
	 * 
	 * @return the internal results response
	 */
	protected GetLatestReadingResponse verifyLastMeterReadingSituations()
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			GetLatestReadingResponse errorResponse = new GetLatestReadingResponse();
			errorResponse.setError(new MeterError());
			return errorResponse;
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return new GetLatestReadingResponse();
	}

	/**
	 * Verify other situations.
	 * 
	 * @return the internal results response
	 */
	protected GetMeterReadingsResponse verifyMeterReadingSituations()
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			GetMeterReadingsResponse errorResponse = new GetMeterReadingsResponse();
			errorResponse.getErrors().add(new MeterError());
			return errorResponse;
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return new GetMeterReadingsResponse();
	}

}
