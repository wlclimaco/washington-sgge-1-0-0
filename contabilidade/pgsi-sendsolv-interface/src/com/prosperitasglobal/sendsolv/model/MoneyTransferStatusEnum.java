package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.II18nEnum;
import com.qat.framework.model.IIntegerEnum;

/**
 * Enumeration representing the different stages of a money transfer in the SendSolv system.
 */
public enum MoneyTransferStatusEnum implements IIntegerEnum, II18nEnum
{
	/** Money transfer is pending approval. */
	PENDING_APPROVAL(1, "com.prosperitasglobal.sendsolv.model.moneytransfertransactionstatusenum.pendingapproval"),

	/** Money transfer is approved. */
	APPROVED(2, "com.prosperitasglobal.sendsolv.model.moneytransfertransactionstatusenum.approved"),

	/** Money transfer has been submitted. */
	ORDER_SUBMITTED(3, "com.prosperitasglobal.sendsolv.model.moneytransfertransactionstatusenum.ordersubmitted"),

	/** Money transfer is pending at ACH. */
	PENDING_ACH(4, "com.prosperitasglobal.sendsolv.model.moneytransfertransactionstatusenum.pendingach"),

	/** Money transfer has taken an exception. */
	EXCEPTION(5, "com.prosperitasglobal.sendsolv.model.moneytransfertransactionstatusenum.exception"),

	/** Money transfer is on hold. */
	ON_HOLD(6, "com.prosperitasglobal.sendsolv.model.moneytransfertransactionstatusenum.onhold"),

	/** Money transfer not paid by automated clearing house. */
	NOT_PAID(7, "com.prosperitasglobal.sendsolv.model.moneytransfertransactionstatusenum.notpaid"),

	/** Money transfer notification submitted to ACH. */
	MODIFICATION_SUBMITTED(8,
			"com.prosperitasglobal.sendsolv.model.moneytransfertransactionstatusenum.modificationsubmitted"),

			/** Money transfer cancellation submitted to ACH. */
			CANCELLATION_SUBMITTED(9,
			"com.prosperitasglobal.sendsolv.model.moneytransfertransactionstatusenum.cancellationsubmitted"),

					/** Money transfer rejected. */
					REJECTED(10, "com.prosperitasglobal.sendsolv.model.moneytransfertransactionstatusenum.rejected"),

					/** Money transfer has been seized. */
					SEIZED(11, "com.prosperitasglobal.sendsolv.model.moneytransfertransactionstatusenum.seized"),

					/** Money transfer has been cancelled. */
					CANCELLED(12, "com.prosperitasglobal.sendsolv.model.moneytransfertransactionstatusenum.cancelled"),

					/** Money transfer paid by automated clearing house. */
					PAID(13, "com.prosperitasglobal.sendsolv.model.moneytransfertransactionstatusenum.paid"),

					/** Cancellation is approved. */
					CANCELLATION_APPROVED(14,
			"com.prosperitasglobal.sendsolv.model.moneytransfertransactionstatusenum.cancellationapproved"),

							/** Modification is approved. */
							MODIFICATION_APPROVED(15,
			"com.prosperitasglobal.sendsolv.model.moneytransfertransactionstatusenum.modificationapproved"),

									/** Approved but the payer requires prefunding and not enough funds are available. */
									APPROVED_NOT_FUNDED(16,
			"com.prosperitasglobal.sendsolv.model.moneytransfertransactionstatusenum.approvednotfunded");

	/** The code. */
	private Integer code;

	/** The label key. */
	private String labelKey;

	/**
	 * The Constructor.
	 *
	 * @param value The value of the money transfer status enumeration.
	 * @param labelKeyParam The label key parameter.
	 */
	private MoneyTransferStatusEnum(Integer value, String labelKeyParam)
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
	 * @param value The value of the money transfer status enumeration.
	 * @return The money transfer status enumeration.
	 */
	public static MoneyTransferStatusEnum enumForValue(Integer value)
	{
		for (MoneyTransferStatusEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Gets the valid values for the money transfer status.
	 *
	 * @return The valid values.
	 */
	public static String getValidValues()
	{
		String comma = "";
		StringBuilder enumValue = new StringBuilder();

		for (MoneyTransferStatusEnum i : MoneyTransferStatusEnum.class.getEnumConstants())
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