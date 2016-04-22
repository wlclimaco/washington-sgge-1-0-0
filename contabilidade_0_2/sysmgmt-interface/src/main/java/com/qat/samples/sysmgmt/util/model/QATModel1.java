package com.qat.samples.sysmgmt.util.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.BaseModel;

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
@XmlAccessorType(XmlAccessType.FIELD)
@SuppressWarnings("serial")
public class QATModel1 extends BaseModel
{


		/** The create user. */
		private String createUser;

		/** The modify user. */
		private String modifyUser;

		/** The create date. */
		private Long createDateUTC;

		/** The modify date. */
		private Long modifyDateUTC;


		/**
		 * Gets the creates the user.
		 *
		 * @return the creates the user
		 */
		public String getCreateUser()
		{
			return createUser;
		}

		/**
		 * Sets the creates the user.
		 *
		 * @param createUser the new creates the user
		 */
		public void setCreateUser(String createUser)
		{
			this.createUser = createUser;
		}

		/**
		 * Gets the creates the date.
		 *
		 * @return the create date which is always UTC.
		 */
		public Long getCreateDateUTC()
		{
			return createDateUTC;
		}

		/**
		 * Sets the UTC create date.
		 *
		 * @param createDateUTC the new creates the date utc
		 */
		public void setCreateDateUTC(Long createDateUTC)
		{
			this.createDateUTC = createDateUTC;
		}

		/**
		 * Gets the modify user.
		 *
		 * @return the modify user
		 */
		public String getModifyUser()
		{
			return modifyUser;
		}

		/**
		 * Sets the modify user.
		 *
		 * @param modifyUser the new modify user
		 */
		public void setModifyUser(String modifyUser)
		{
			this.modifyUser = modifyUser;
		}

		/**
		 * Gets the UTC modify date.
		 *
		 * @return the modify date
		 */
		public Long getModifyDateUTC()
		{
			return modifyDateUTC;
		}

		/**
		 * Sets the UTC modify date.
		 *
		 * @param modifyDateUTC the new modify date
		 */
		public void setModifyDateUTC(Long modifyDateUTC)
		{
			this.modifyDateUTC = modifyDateUTC;
		}

		@Override
		public String toString() {
			return "QATModel1 [getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()=" + getCreateDateUTC()
					+ ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()=" + getModifyDateUTC()
					+ ", toString()=" + super.toString() + "]";
		}

}
