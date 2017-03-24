package com.qat.samples.sysmgmt.util.model.request;

import com.qat.framework.model.SortExpression.Direction;
import com.qat.framework.model.request.InquiryRequest;

/**
 * The Model Object PagedInquiryRequest.
 */
public class PagedInquiryRequest extends InquiryRequest {

	/** The business id. */
	private Integer emprId;

	/** The recipient id. */
	private Integer id;

	private Integer parentId;

	private String string;

	private String userId;

	private Boolean select;

	private Integer columnName1;

	private String buscaTable;

	private String buscaValue;

	private String buscaType;

	/** The direction. */
	private Direction direction1 = Direction.Ascending;



	/**
	 * Gets the string value for the direction for use in SQL.
	 *
	 * @return The direction string value
	 */
	public String getDirection1Value()
	{
		if (direction1 != Direction.Ascending)
		{
			return "DESC";
		}
		else
		{
			return "ASC";
		}
	}

	public Direction getDirection1()
	{
		return direction1;
	}

	/**
	 * Gets the column name.
	 *
	 * @return the column name
	 */
	public Integer getColumnName1()
	{
		return columnName1;
	}

	/**
	 * Sets the column name.
	 *
	 * @param columnName the new column name
	 */
	public void setColumnName1(Integer columnName)
	{
		this.columnName1 = columnName;
	}
	/**
	 * Sets the direction.
	 *
	 * @param direction the new direction
	 */
	public void setDirection1(Direction direction)
	{
		this.direction1 = direction;
	}

	public PagedInquiryRequest() {

	}

	public Integer getEmprId() {
		return emprId;
	}

	public void setEmprId(Integer emprId) {
		this.emprId = emprId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getSelect() {
		return select;
	}

	public void setSelect(Boolean select) {
		this.select = select;
	}

	public String getBuscaTable() {
		return buscaTable;
	}

	public void setBuscaTable(String buscaTable) {
		this.buscaTable = buscaTable;
	}

	public String getBuscaValue() {
		return buscaValue;
	}

	public void setBuscaValue(String buscaValue) {
		this.buscaValue = buscaValue;
	}


	public String getBuscaType() {
		return buscaType;
	}

	public void setBuscaType(String buscaType) {
		this.buscaType = buscaType;
	}

	@Override
	public String toString() {
		return "PagedInquiryRequest [getDirection1Value()=" + getDirection1Value() + ", getDirection1()="
				+ getDirection1() + ", getColumnName1()=" + getColumnName1() + ", getEmprId()=" + getEmprId()
				+ ", getParentId()=" + getParentId() + ", getId()=" + getId() + ", getString()=" + getString()
				+ ", getUserId()=" + getUserId() + ", getSelect()=" + getSelect() + ", getBuscaTable()="
				+ getBuscaTable() + ", getBuscaValue()=" + getBuscaValue() + ", toString()=" + super.toString() + "]";
	}



}
