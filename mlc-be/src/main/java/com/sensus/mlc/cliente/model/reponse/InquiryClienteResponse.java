package com.sensus.mlc.cliente.model.reponse;

import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.mlc.cliente.model.Cliente;
import com.sensus.mlc.smartpoint.model.Column;



/**
 * The Class InquiryAnalyticsResponse.
 */
public class InquiryClienteResponse extends InquiryResponse
{

	/** The actions. */
	private List<Cliente> clientes;
	
	/** The list column. */
	private List<Column> listColumn;

	/** The additional columns. */
	private List<Column> additionalColumns;


	/** The process id. */
	private Integer processId;

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
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
		return "InquiryClienteResponse [getClientes()=" + getClientes()
				+ ", getProcessId()=" + getProcessId() + ", getListColumn()="
				+ getListColumn() + ", getAdditionalColumns()="
				+ getAdditionalColumns() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()="
				+ getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()="
				+ getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + ", getResponseTime()="
				+ getResponseTime() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}




}
