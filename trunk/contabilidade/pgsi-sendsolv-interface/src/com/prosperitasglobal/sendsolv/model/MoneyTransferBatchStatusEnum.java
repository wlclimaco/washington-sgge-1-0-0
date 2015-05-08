package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * Enumeration representing the different stages a batch of money transfers goes through in the SendSolv system.
 */
public enum MoneyTransferBatchStatusEnum implements IIntegerEnum, II18nEnum
{
	/** Money transfer batch is being created. */
	BATCH_CREATION(1, "com.prosperitasglobal.sendsolv.model.moneytransferbatchstatusenum.batchcreation"),

	/** Money transfer batch is pending approval. */
	PENDING_APPROVAL(2, "com.prosperitasglobal.sendsolv.model.moneytransferbatchstatusenum.pendingapproval"),

	/** Money transfer batch spreads are being reviewed. */
	SPREAD_REVIEW(3, "com.prosperitasglobal.sendsolv.model.moneytransferbatchstatusenum.spreadreview"),

	/** Money transfer batch is ready for release to Automated Clearing House. */
	READY_FOR_RELEASE(4, "com.prosperitasglobal.sendsolv.model.moneytransferbatchstatusenum.readyforrelease"),

	/** Money transfer batch has been released to the ACH. */
	RELEASED(5, "com.prosperitasglobal.sendsolv.model.moneytransferbatchstatusenum.released"),

	/** Money transfer batch is being processed by ACH. */
	ACH_PROCESSING(6, "com.prosperitasglobal.sendsolv.model.moneytransferbatchstatusenum.achprocessing"),

	/** Money transfer batch has taken an exception. */
	ACH_EXCEPTIONS(7, "com.prosperitasglobal.sendsolv.model.moneytransferbatchstatusenum.achexceptions"),

	/** Money transfer batch is on hold. */
	ON_HOLD(8, "com.prosperitasglobal.sendsolv.model.moneytransferbatchstatusenum.onhold"),

	/** Money transfer batch has expired. */
	EXPIRED(9, "com.prosperitasglobal.sendsolv.model.moneytransferbatchstatusenum.expired"),

	/** Money transfer batch is cancelled. */
	CANCELLED(10, "com.prosperitasglobal.sendsolv.model.moneytransferbatchstatusenum.cancelled"),

	/** Money transfer batch has been processed. */
	BATCH_PROCESSED(11, "com.prosperitasglobal.sendsolv.model.moneytransferbatchstatusenum.batchprocessed");

	/** The code. */
	private Integer code;

	/** The label key. */
	private String labelKey;

	/**
	 * The Constructor.
	 *
	 * @param value The value of the money transfer batch status enumeration.
	 * @param labelKeyParam The label key parameter.
	 */
	private MoneyTransferBatchStatusEnum(Integer value, String labelKeyParam)
	{
		code = value;
		labelKey = labelKeyParam;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	@Override
	public Integer getValue()
	{
		return code;
	}

	/**
	 * Enumeration for value.
	 *
	 * @param value The value of the money transfer batch status enumeration.
	 * @return The money transfer batch status enumeration.
	 */
	public static MoneyTransferBatchStatusEnum enumForValue(Integer value)
	{
		for (MoneyTransferBatchStatusEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Gets the valid values for the money transfer batch status.
	 *
	 * @return The valid values.
	 */
	public static String getValidValues()
	{
		String comma = "";
		StringBuilder enumValue = new StringBuilder();

		for (MoneyTransferBatchStatusEnum i : MoneyTransferBatchStatusEnum.class.getEnumConstants())
		{
			enumValue.append(comma).append(i.getValue());
			comma = ", ";
		}

		return enumValue.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.II18nEnum#getLabelKey()
	 */
	@Override
	public String getLabelKey()
	{
		return labelKey;
	}
}