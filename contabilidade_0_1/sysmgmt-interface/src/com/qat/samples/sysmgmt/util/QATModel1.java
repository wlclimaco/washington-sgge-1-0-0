package com.qat.samples.sysmgmt.util;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.QATModelOL;

/**
 * This class may be used as a base or super class for a Model object.<br/>
 * This class or the {@link QATModelOL} class should be used as the super class for ALL model objects. The difference
 * between these 2 types are as follows:
 * <ul>
 * <li>The QATModel class should be used for basic Model Objects that do not require any special support for optimistic
 * locking.
 * <li>The {@link QATModelOL} class should be used for Model Objects that do required special support for optimistic
 * locking.
 * </ul>
 * 
 */
@SuppressWarnings("serial")
public class QATModel1 extends QATModel
{

	/** The create date. */
	private Long createDataUTC;

	/** The modify date. */
	private Long modifyDataUTC;

	public Long getCreateDataUTC()
	{
		return createDataUTC;
	}

	public void setCreateDataUTC(Long createDataUTC)
	{
		this.createDataUTC = createDataUTC;
	}

	public Long getModifyDataUTC()
	{
		return modifyDataUTC;
	}

	public void setModifyDataUTC(Long modifyDataUTC)
	{
		this.modifyDataUTC = modifyDataUTC;
	}

	@Override
	public String toString()
	{
		return "QATModel1 [getCreateDataUTC()=" + getCreateDataUTC() + ", getModifyDataUTC()=" + getModifyDataUTC()
				+ ", toString()=" + super.toString() + "]";
	}

}
