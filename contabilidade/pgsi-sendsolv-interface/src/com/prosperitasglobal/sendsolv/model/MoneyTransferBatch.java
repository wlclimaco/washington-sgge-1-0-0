package com.prosperitasglobal.sendsolv.model;

import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.cbof.model.Note;
import com.qat.framework.model.QATModelOL;
import com.qat.framework.validation.ValidationUtil;

/**
 * This class is a representation of a Money Transfer Batch. A batch contains a list of Money Transfers and
 * is used to manage the transfers.
 */
@SuppressWarnings("serial")
public class MoneyTransferBatch extends QATModelOL
{
	/** The SendSolv id for the money transfer batch. */
	private Integer id;

	/** The SendSolv key for the money transfer batch. */
	private String key;

	/** The location of the money transfers. */
	private Location location;

	/** The list of money transfer transactions. */
	private List<MoneyTransfer> moneyTransferList;

	/** The list of notes associated with the batch. */
	private List<Note> noteList;

	/** The amount of money being transferred in the batch. */
	private MoneyTransferAmount originAmount;

	/** The historical list of statuses for the batch. */
	private List<MoneyTransferBatchStatus> statusList;

	/** The date that the money transfer will occur. */
	private Long transferDate;

	/** The date the payroll was received. */
	private Long payrollReceivedDate;

	/** The payment preparation date that triggered the creation of the batch. */
	private Long payPreparationDate;

	/** The user that approved the batch. */
	private String releaseUser;

	/**
	 * Default constructor.
	 */
	public MoneyTransferBatch()
	{
		super();
	}

	/**
	 * Add a MoneyTransfer to the list of MoneyTransfers
	 *
	 * @param transfer The transfer to add.
	 */
	public void addMoneyTransfer(MoneyTransfer transfer)
	{
		if (getMoneyTransferList() == null)
		{
			setMoneyTransferList(new ArrayList<MoneyTransfer>());
		}
		getMoneyTransferList().add(transfer);
	}

	/**
	 * Add a MoneyTransferBatchStatus to the list of the same
	 *
	 * @param batchStatus The batch status to add.
	 */
	public void addMoneyTransferBatchStatus(MoneyTransferBatchStatus batchStatus)
	{
		if (getStatusList() == null)
		{
			setStatusList(new ArrayList<MoneyTransferBatchStatus>());
		}
		getStatusList().add(batchStatus);
	}

	/**
	 * Add a Note to the list of Notes
	 *
	 * @param note The note to add.
	 */
	public void addNote(Note note)
	{
		if (getNoteList() == null)
		{
			setNoteList(new ArrayList<Note>());
		}
		getNoteList().add(note);
	}

	/**
	 * Get the SendSolv id for the money transfer batch.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the SendSolv id for the money transfer batch.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the SendSolv key for the money transfer batch.
	 *
	 * @return The key.
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * Set the SendSolv key for the money transfer batch.
	 *
	 * @param key The key to set.
	 */
	public void setKey(String key)
	{
		this.key = key;
	}

	/**
	 * Get the current status of the money transfer batch. Convenience method for getting the most current status out of
	 * the status list.
	 *
	 * @return The current status.
	 */
	public MoneyTransferBatchStatus getCurrentStatus()
	{
		if (ValidationUtil.isNullOrEmpty(getStatusList()))
		{
			return null;
		}

		MoneyTransferBatchStatus currentStatus = null;

		for (MoneyTransferBatchStatus moneyTransferBatchStatus : getStatusList())
		{
			if (ValidationUtil.isNull(currentStatus)
					|| (moneyTransferBatchStatus.getCreateDateUTC() >= currentStatus.getCreateDateUTC()))
			{
				currentStatus = moneyTransferBatchStatus;
			}
		}

		return currentStatus;
	}

	/**
	 * Get the location.
	 *
	 * @return The location.
	 */
	public Location getLocation()
	{
		return location;
	}

	/**
	 * Set the location.
	 *
	 * @param location The location to set.
	 */
	public void setLocation(Location location)
	{
		this.location = location;
	}

	/**
	 * Get the list of {@link MoneyTransfer}'s.
	 *
	 * @return The list.
	 */
	public List<MoneyTransfer> getMoneyTransferList()
	{
		if (ValidationUtil.isNull(moneyTransferList))
		{
			setMoneyTransferList(new ArrayList<MoneyTransfer>());
		}

		return moneyTransferList;
	}

	/**
	 * Set the list of {@link MoneyTransfer}'s.
	 *
	 * @param moneyTransferList The list to set.
	 */
	public void setMoneyTransferList(List<MoneyTransfer> moneyTransferList)
	{
		this.moneyTransferList = moneyTransferList;
	}

	/**
	 * Get the list of {@link Note}'s.
	 *
	 * @return The list.
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
	 * Set the list of {@link Note}'s.
	 *
	 * @param noteList The list to set.
	 */
	public void setNoteList(List<Note> noteList)
	{
		this.noteList = noteList;
	}

	/**
	 * Get the amount of money being transferred.
	 *
	 * @return The amount.
	 */
	public MoneyTransferAmount getOriginAmount()
	{
		return originAmount;
	}

	/**
	 * Set the amount of money being transferred.
	 *
	 * @param originAmount The origin amount to set.
	 */
	public void setOriginAmount(MoneyTransferAmount originAmount)
	{
		this.originAmount = originAmount;
	}

	/**
	 * Get the payment preparation date. If this date has a time portion, it will be removed. This attribute is only a
	 * date.
	 *
	 * @return The date.
	 */
	public Long getPayPreparationDate()
	{
		return payPreparationDate;
	}

	/**
	 * Set the payment preparation date.
	 *
	 * @param payPreparationDate The date to set.
	 */
	public void setPayPreparationDate(Long payPreparationDate)
	{
		this.payPreparationDate = payPreparationDate;
	}

	/**
	 * Get the date the payroll was received. If this date has a time portion, it will be removed. This attribute is
	 * only a date.
	 *
	 * @return The date.
	 */
	public Long getPayrollReceivedDate()
	{
		return payrollReceivedDate;
	}

	/**
	 * Set the date the payroll was received.
	 *
	 * @param payrollReceivedDate The date to set.
	 */
	public void setPayrollReceivedDate(Long payrollReceivedDate)
	{
		this.payrollReceivedDate = payrollReceivedDate;
	}

	/**
	 * Get the release user.
	 *
	 * @return The release user.
	 */
	public String getReleaseUser()
	{
		return releaseUser;
	}

	/**
	 * Set the release user.
	 *
	 * @param releaseUser The release user.
	 */
	public void setReleaseUser(String releaseUser)
	{
		this.releaseUser = releaseUser;
	}

	/**
	 * Get the list of {@link MoneyTransferBatchStatus}.
	 *
	 * @return The list.
	 */
	public List<MoneyTransferBatchStatus> getStatusList()
	{
		if (ValidationUtil.isNull(statusList))
		{
			setStatusList(new ArrayList<MoneyTransferBatchStatus>());
		}

		return statusList;
	}

	/**
	 * Set the list of {@link MoneyTransferBatchStatus}.
	 *
	 * @param statusList The list to set.
	 */
	public void setStatusList(List<MoneyTransferBatchStatus> statusList)
	{
		this.statusList = statusList;
	}

	/**
	 * Get the transfer date. If this date has a time portion, it will be removed. This attribute is only a date.
	 *
	 * @return The date.
	 */
	public Long getTransferDate()
	{
		return transferDate;
	}

	/**
	 * Set the transfer date.
	 *
	 * @param transferDate The date.
	 */
	public void setTransferDate(Long transferDate)
	{
		this.transferDate = transferDate;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferBatch [getId()=" + getId() + ", getKey()=" + getKey() + ", getCurrentStatus()="
				+ getCurrentStatus() + ", getLocation()=" + getLocation() + ", getMoneyTransferList()="
				+ getMoneyTransferList() + ", getNoteList()=" + getNoteList() + ", getOriginAmount()="
				+ getOriginAmount() + ", getPayPreparationDate()=" + getPayPreparationDate()
				+ ", getPayrollReceivedDate()=" + getPayrollReceivedDate() + ", getReleaseUser()=" + getReleaseUser()
				+ ", getStatusList()=" + getStatusList() + ", getTransferDate()=" + getTransferDate() + ", toString()="
				+ super.toString() + "]";
	}
}
