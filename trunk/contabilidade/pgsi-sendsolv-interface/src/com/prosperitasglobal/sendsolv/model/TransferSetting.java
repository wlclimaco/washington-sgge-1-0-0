package com.prosperitasglobal.sendsolv.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.cbof.model.Note;
import com.qat.framework.model.QATModelOL;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class represents all the associations needed to make a money transfer.
 */
@SuppressWarnings("serial")
public class TransferSetting extends QATModelOL
{
	/** The account to use for the transfer. */
	private Account account;

	/** The list of customer fees associated with the transfer. */
	private List<CustomFee> customFeeList;

	/** The number of cycles to skip before transferring. */
	private Integer cyclesToSkip;

	/** The date the transfer ends. */
	private Long effectiveEndDate;

	/** The date the transfer becomes available. */
	private Long effectiveStartDate;

	/** The employment associated with the transfer. */
	private EmploymentInfo employmentInfo;

	/** The SendSolv id of the transfer. */
	private Integer id;

	/** The SendSolv key of the transfer. */
	private String key;

	/** The SendSolv id of the member associated with the transfer. */
	private Integer memberId;

	/** The number of cycles that have been skipped in the processing. */
	private Integer numberOfCyclesSkipped;

	/** The plan category associated with the transfer. */
	private PlanCategory planCategory;

	/** The product plan applicability associated with the transfer. */
	private ProductPlanApplicability productPlanApplicability;

	/** The SendSolv id of the recipient associated with the transfer. */
	private Integer recipientId;

	/** The status of the transfer. */
	private StatusEnum status;

	/** The amount to transfer. */
	private BigDecimal transferAmount;

	/** The transfer type. */
	private TransferTypeEnum transferType;

	/** The note list. */
	private List<Note> noteList;

	/**
	 * The Constructor.
	 */
	public TransferSetting()
	{
		super();
	}

	/**
	 * Get the account to use for the transfer.
	 *
	 * @return The account.
	 */
	public Account getAccount()
	{
		return account;
	}

	/**
	 * Set the account to use for the transfer.
	 *
	 * @param account The account to set.
	 */
	public void setAccount(Account account)
	{
		this.account = account;
	}

	/**
	 * Get the list of customer fees associated with the transfer.
	 *
	 * @return The list.
	 */
	public List<CustomFee> getCustomFeeList()
	{
		if (ValidationUtil.isNull(customFeeList))
		{
			setCustomFeeList(new ArrayList<CustomFee>());
		}

		return customFeeList;
	}

	/**
	 * Set the list of customer fees associated with the transfer.
	 *
	 * @param customFeeList The list to set.
	 */
	public void setCustomFeeList(List<CustomFee> customFeeList)
	{
		this.customFeeList = customFeeList;
	}

	/**
	 * Get the number of cycles to skip before transferring.
	 *
	 * @return The number.
	 */
	public Integer getCyclesToSkip()
	{
		return cyclesToSkip;
	}

	/**
	 * Set the number of cycles to skip before transferring.
	 *
	 * @param cyclesToSkip The number to set.
	 */
	public void setCyclesToSkip(Integer cyclesToSkip)
	{
		this.cyclesToSkip = cyclesToSkip;
	}

	/**
	 * Get the date the transfer ends. If this date has a time portion, it will be removed. This
	 * attribute is only a date.
	 *
	 * @return The date.
	 */
	public Long getEffectiveEndDate()
	{
		return effectiveEndDate;
	}

	/**
	 * Set the date the transfer ends.
	 *
	 * @param effectiveEndDate The date to set.
	 */
	public void setEffectiveEndDate(Long effectiveEndDate)
	{
		this.effectiveEndDate = effectiveEndDate;
	}

	/**
	 * Get the date the transfer becomes available. If this date has a time portion, it will be removed. This
	 * attribute is only a date.
	 *
	 * @return The date.
	 */
	public Long getEffectiveStartDate()
	{
		return effectiveStartDate;
	}

	/**
	 * Set the date the transfer becomes available.
	 *
	 * @param effectiveStartDate The date to set.
	 */
	public void setEffectiveStartDate(Long effectiveStartDate)
	{
		this.effectiveStartDate = effectiveStartDate;
	}

	/**
	 * Get the employment associated with the transfer.
	 *
	 * @return The employment information.
	 */
	public EmploymentInfo getEmploymentInfo()
	{
		return employmentInfo;
	}

	/**
	 * Set the employment associated with the transfer.
	 *
	 * @param employmentInfo The employment information to set.
	 */
	public void setEmploymentInfo(EmploymentInfo employmentInfo)
	{
		this.employmentInfo = employmentInfo;
	}

	/**
	 * Get SendSolv id of the transfer.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set SendSolv id of the transfer.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get SendSolv key of the transfer.
	 *
	 * @return The key.
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * Set SendSolv key of the transfer.
	 *
	 * @param key The key to set.
	 */
	public void setKey(String key)
	{
		this.key = key;
	}

	/**
	 * Get the SendSolv id of the member associated with the transfer.
	 *
	 * @return The id.
	 */
	public Integer getMemberId()
	{
		return memberId;
	}

	/**
	 * Set the SendSolv id of the member associated with the transfer.
	 *
	 * @param memberId The id to set.
	 */
	public void setMemberId(Integer memberId)
	{
		this.memberId = memberId;
	}

	/**
	 * Get the number of cycles that have been skipped in the processing.
	 *
	 * @return The number.
	 */
	public Integer getNumberOfCyclesSkipped()
	{
		return numberOfCyclesSkipped;
	}

	/**
	 * Set the number of cycles that have been skipped in the processing.
	 *
	 * @param numberOfCyclesSkipped The number to set.
	 */
	public void setNumberOfCyclesSkipped(Integer numberOfCyclesSkipped)
	{
		this.numberOfCyclesSkipped = numberOfCyclesSkipped;
	}

	/**
	 * Get the plan category associated with the transfer.
	 *
	 * @return The plan category.
	 */
	public PlanCategory getPlanCategory()
	{
		return planCategory;
	}

	/**
	 * Set the plan category associated with the transfer.
	 *
	 * @param planCategory The plan category to set.
	 */
	public void setPlanCategory(PlanCategory planCategory)
	{
		this.planCategory = planCategory;
	}

	/**
	 * Get the product plan applicability associated with the transfer.
	 *
	 * @return The product plan applicability.
	 */
	public ProductPlanApplicability getProductPlanApplicability()
	{
		return productPlanApplicability;
	}

	/**
	 * Set the product plan applicability associated with the transfer.
	 *
	 * @param productPlanApplicability The product plan applicability to set.
	 */
	public void setProductPlanApplicability(ProductPlanApplicability productPlanApplicability)
	{
		this.productPlanApplicability = productPlanApplicability;
	}

	/**
	 * Gets the SendSolv id of the recipient associated with the transfer.
	 *
	 * @return The id.
	 */
	public Integer getRecipientId()
	{
		return recipientId;
	}

	/**
	 * Sets the SendSolv id of the recipient associated with the transfer.
	 *
	 * @param recipientId The id.
	 */
	public void setRecipientId(Integer recipientId)
	{
		this.recipientId = recipientId;
	}

	/**
	 * Get the status of the transfer.
	 *
	 * @return The status.
	 */
	public StatusEnum getStatus()
	{
		return status;
	}

	/**
	 * Get the enumeration value for the status.
	 *
	 * @return The value for the status.
	 */
	public Integer getStatusValue()
	{
		if (getStatus() == null)
		{
			return null;
		}

		return getStatus().getValue();
	}

	/**
	 * Set the status of the transfer.
	 *
	 * @param status The status to set.
	 */
	public void setStatus(StatusEnum status)
	{
		this.status = status;
	}

	/**
	 * Set the enumeration by the value of the status.
	 *
	 * @param statusValue The value of the status enumeration to set.
	 */
	public void setStatusValue(Integer statusValue)
	{
		setStatus(StatusEnum.enumForValue(statusValue));
	}

	/**
	 * Get the amount to transfer.
	 *
	 * @return The amount.
	 */
	public BigDecimal getTransferAmount()
	{
		return transferAmount;
	}

	/**
	 * Set the amount to transfer.
	 *
	 * @param transferAmount The amount to set.
	 */
	public void setTransferAmount(BigDecimal transferAmount)
	{
		this.transferAmount = transferAmount;
	}

	/**
	 * Gets the transfer type.
	 *
	 * @return the transfer type
	 */
	public TransferTypeEnum getTransferType()
	{
		return transferType;
	}

	/**
	 * Gets the transfer type value.
	 *
	 * @return the transfer type value
	 */
	public Integer getTransferTypeValue()
	{
		if (getTransferType() == null)
		{
			return null;
		}

		return getTransferType().getValue();
	}

	/**
	 * Sets the transfer type value.
	 *
	 * @param transferTypeValue the transfer type value
	 */
	public void setTransferTypeValue(Integer transferTypeValue)
	{
		setTransferType(TransferTypeEnum.enumForValue(transferTypeValue));
	}

	/**
	 * Sets the transfer type.
	 *
	 * @param transferType the transfer type
	 */
	public void setTransferType(TransferTypeEnum transferType)
	{
		this.transferType = transferType;
	}

	/**
	 * Gets the note list.
	 *
	 * @return the note list
	 */
	public List<Note> getNoteList()
	{
		if (ValidationUtil.isNull(noteList))
		{
			setNoteList(new ArrayList<Note>());
		}

		return noteList;
	}

	/**
	 * Sets the note list.
	 *
	 * @param noteList the note list
	 */
	public void setNoteList(List<Note> noteList)
	{
		this.noteList = noteList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TransferSetting [getAccount()=" + getAccount() + ", getCustomFeeList()=" + getCustomFeeList()
				+ ", getCyclesToSkip()=" + getCyclesToSkip() + ", getEffectiveEndDate()=" + getEffectiveEndDate()
				+ ", getEffectiveStartDate()=" + getEffectiveStartDate() + ", getEmploymentInfo()="
				+ getEmploymentInfo() + ", getId()=" + getId() + ", getKey()=" + getKey() + ", getMemberId()="
				+ getMemberId() + ", getNumberOfCyclesSkipped()=" + getNumberOfCyclesSkipped() + ", getPlanCategory()="
				+ getPlanCategory() + ", getProductPlanApplicability()=" + getProductPlanApplicability()
				+ ", getRecipientId()=" + getRecipientId() + ", getStatus()=" + getStatus() + ", getStatusValue()="
				+ getStatusValue() + ", getTransferAmount()=" + getTransferAmount() + ", getTransferType()="
				+ getTransferType() + ", getTransferTypeValue()=" + getTransferTypeValue() + ", getNoteList()="
				+ getNoteList() + ", toString()=" + super.toString() + "]";
	}

}