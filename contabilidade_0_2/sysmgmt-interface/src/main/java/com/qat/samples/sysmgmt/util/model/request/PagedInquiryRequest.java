package com.qat.samples.sysmgmt.util.model.request;

import java.util.List;

import com.qat.framework.model.SortExpression.Direction;
import com.qat.framework.model.request.InquiryRequest;
import com.qat.samples.sysmgmt.util.model.PermissaoTypeEnum;

// TODO: Auto-generated Javadoc
/**
 * The Model Object PagedInquiryRequest.
 */
public class PagedInquiryRequest extends InquiryRequest {

	/** The business id. */
	private Integer emprId;

	/** The permissao type enum value. */
	private PermissaoTypeEnum permissaoTypeEnum;

	/** The empr ids. */
	private List<Integer> emprIds;

	/** The recipient id. */
	private Integer id;

	/** The parent id. */
	private Integer parentId;

	/** The string. */
	private String string;

	/** The user id. */
	private String userId;

	/** The select. */
	private Boolean select;

	/** The column name 1. */
	private Integer columnName1;

	/** The busca table. */
	private String buscaTable;

	/** The busca value. */
	private String buscaValue;

	/** The busca type. */
	private String buscaType;

	/** The direction. */
	private Direction direction1 = Direction.Ascending;


	public Integer getPermissaoTypeEnumValue()
	{
		if (permissaoTypeEnum != null)
		{
			return permissaoTypeEnum.getValue();
		}
		return null;
	}

	public void setPermissaoTypeEnumValue(Integer acaoTypeValue)
	{
		permissaoTypeEnum = PermissaoTypeEnum.enumForValue(acaoTypeValue);
	}

	/**
	 * Gets the string value for the direction for use in SQL.
	 *
	 * @return The direction string value
	 */
	public String getDirection1Value() {
		if (direction1 != Direction.Ascending) {
			return "DESC";
		} else {
			return "ASC";
		}
	}

	/**
	 * Gets the direction 1.
	 *
	 * @return the direction 1
	 */
	public Direction getDirection1() {
		return direction1;
	}

	/**
	 * Gets the column name.
	 *
	 * @return the column name
	 */
	public Integer getColumnName1() {
		return columnName1;
	}

	/**
	 * Sets the column name.
	 *
	 * @param columnName
	 *            the new column name
	 */
	public void setColumnName1(Integer columnName) {
		this.columnName1 = columnName;
	}

	/**
	 * Sets the direction.
	 *
	 * @param direction
	 *            the new direction
	 */
	public void setDirection1(Direction direction) {
		this.direction1 = direction;
	}

	/**
	 * Instantiates a new paged inquiry request.
	 */
	public PagedInquiryRequest() {

	}

	/**
	 * Gets the empr id.
	 *
	 * @return the empr id
	 */
	public Integer getEmprId() {
		return emprId;
	}

	/**
	 * Sets the empr id.
	 *
	 * @param emprId the new empr id
	 */
	public void setEmprId(Integer emprId) {
		this.emprId = emprId;
	}


	public PermissaoTypeEnum getPermissaoTypeEnum() {
		return permissaoTypeEnum;
	}

	public void setPermissaoTypeEnum(PermissaoTypeEnum permissaoTypeEnum) {
		this.permissaoTypeEnum = permissaoTypeEnum;
	}

	/**
	 * Gets the empr ids.
	 *
	 * @return the empr ids
	 */
	public List<Integer> getEmprIds() {
		return emprIds;
	}

	/**
	 * Sets the empr ids.
	 *
	 * @param emprIds the new empr ids
	 */
	public void setEmprIds(List<Integer> emprIds) {
		this.emprIds = emprIds;
	}

	/**
	 * Gets the parent id.
	 *
	 * @return the parent id
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * Sets the parent id.
	 *
	 * @param parentId the new parent id
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the string.
	 *
	 * @return the string
	 */
	public String getString() {
		return string;
	}

	/**
	 * Sets the string.
	 *
	 * @param string the new string
	 */
	public void setString(String string) {
		this.string = string;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the select.
	 *
	 * @return the select
	 */
	public Boolean getSelect() {
		return select;
	}

	/**
	 * Sets the select.
	 *
	 * @param select the new select
	 */
	public void setSelect(Boolean select) {
		this.select = select;
	}

	/**
	 * Gets the busca table.
	 *
	 * @return the busca table
	 */
	public String getBuscaTable() {
		return buscaTable;
	}

	/**
	 * Sets the busca table.
	 *
	 * @param buscaTable the new busca table
	 */
	public void setBuscaTable(String buscaTable) {
		this.buscaTable = buscaTable;
	}

	/**
	 * Gets the busca value.
	 *
	 * @return the busca value
	 */
	public String getBuscaValue() {
		return buscaValue;
	}

	/**
	 * Sets the busca value.
	 *
	 * @param buscaValue the new busca value
	 */
	public void setBuscaValue(String buscaValue) {
		this.buscaValue = buscaValue;
	}

	/**
	 * Gets the busca type.
	 *
	 * @return the busca type
	 */
	public String getBuscaType() {
		return buscaType;
	}

	/**
	 * Sets the busca type.
	 *
	 * @param buscaType the new busca type
	 */
	public void setBuscaType(String buscaType) {
		this.buscaType = buscaType;
	}

	@Override
	public String toString() {
		return "PagedInquiryRequest [getPermissaoTypeEnumValue()=" + getPermissaoTypeEnumValue()
				+ ", getDirection1Value()=" + getDirection1Value() + ", getDirection1()=" + getDirection1()
				+ ", getColumnName1()=" + getColumnName1() + ", getEmprId()=" + getEmprId()
				+ ", getPermissaoTypeEnum()=" + getPermissaoTypeEnum() + ", getEmprIds()=" + getEmprIds()
				+ ", getParentId()=" + getParentId() + ", getId()=" + getId() + ", getString()=" + getString()
				+ ", getUserId()=" + getUserId() + ", getSelect()=" + getSelect() + ", getBuscaTable()="
				+ getBuscaTable() + ", getBuscaValue()=" + getBuscaValue() + ", getBuscaType()=" + getBuscaType()
				+ ", toString()=" + super.toString() + "]";
	}


}
