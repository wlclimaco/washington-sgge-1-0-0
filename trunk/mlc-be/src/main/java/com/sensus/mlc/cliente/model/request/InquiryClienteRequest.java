package com.sensus.mlc.cliente.model.request;

import java.util.List;

import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.cliente.model.Cliente;
import com.sensus.mlc.smartpoint.model.Column;



/**
 * The Class InquiryActionRequest.
 * 
 * @author QAT Brazil.
 */
public class InquiryClienteRequest extends InquiryPaginationRequest
{

	/** The base search. */
	private BaseSearch baseSearch;
	
	/** The file name. */
	private String fileName;

	/** The process id. */
	private Integer processId;

	/** The actions. */
	private List<Cliente> cliente;
	
	/** The list column. */
	private List<Column> listColumn;

	/** The additional columns. */
	private List<Column> additionalColumns;

	/**
	 * Gets the base search.
	 * 
	 * @return the base search
	 */
	public BaseSearch getBaseSearch()
	{
		return baseSearch;
	}

	/**
	 * Sets the base search.
	 * 
	 * @param baseSearch the new base search
	 */
	public void setBaseSearch(BaseSearch baseSearch)
	{
		this.baseSearch = baseSearch;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * Gets the process id.
	 * 
	 * @return the process id
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * Sets the process id.
	 * 
	 * @param processId the new process id
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}



	public List<Cliente> getCliente() {
		return cliente;
	}

	public void setCliente(List<Cliente> cliente) {
		this.cliente = cliente;
	}

	
	public List<Column> getListColumn() {
		return listColumn;
	}

	public void setListColumn(List<Column> listColumn) {
		this.listColumn = listColumn;
	}

	public List<Column> getAdditionalColumns() {
		return additionalColumns;
	}

	public void setAdditionalColumns(List<Column> additionalColumns) {
		this.additionalColumns = additionalColumns;
	}

	@Override
	public String toString() {
		return "InquiryClienteRequest [getBaseSearch()=" + getBaseSearch()
				+ ", getFileName()=" + getFileName() + ", getProcessId()="
				+ getProcessId() + ", getCliente()=" + getCliente()
				+ ", getListColumn()=" + getListColumn()
				+ ", getAdditionalColumns()=" + getAdditionalColumns()
				+ ", getInitialDate()=" + getInitialDate() + ", getEndDate()="
				+ getEndDate() + ", toString()=" + super.toString()
				+ ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize()
				+ ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions()
				+ ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount()
				+ ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", isMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()="
				+ getPaginationAllSelected() + ", getSelectionPaginationIds()="
				+ getSelectionPaginationIds()
				+ ", getUnselectionPaginationIds()="
				+ getUnselectionPaginationIds() + ", isCurrentLightStatus()="
				+ isCurrentLightStatus() + ", getBottomLeftLat()="
				+ getBottomLeftLat() + ", getBottomLeftLon()="
				+ getBottomLeftLon() + ", getTopRightLat()=" + getTopRightLat()
				+ ", getTopRightLon()=" + getTopRightLon()
				+ ", getMaxSmartpointCount()=" + getMaxSmartpointCount()
				+ ", getTenant()=" + getTenant() + ", getAllowedGroupIdList()="
				+ getAllowedGroupIdList() + ", getStringAllowedGroups()="
				+ getStringAllowedGroups() + ", getTimezone()=" + getTimezone()
				+ ", getDatePattern()=" + getDatePattern()
				+ ", getUserContext()=" + getUserContext() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

	



}
