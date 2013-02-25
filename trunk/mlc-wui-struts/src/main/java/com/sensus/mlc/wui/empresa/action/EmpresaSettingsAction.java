package com.sensus.mlc.wui.empresa.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.mlc.wui.base.action.SettingsAction;



/**
 * Struts Action for injecting SmartPoint-related application properties into JavaScript via Freemarker.
 * 
 * @author Washington
 * 
 */

public class EmpresaSettingsAction extends SettingsAction
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1735439655152265099L;

	/** ************************ CONSTANTS ***********************. */

	/** The logger for this class. */
	private static transient Log LOG = LogFactory.getLog(EmpresaSettingsAction.class);

	/** ************************ PROPERTIES ***********************. */

	/** The callback url for the "addToGroup" action. */
	private String empresaTable;

	private String empresaFilial;
	
	private String emprEdit;
	private String emprDelete;
	private String emprInsert;
	private String empresaPorFilial;

	/**
	 * Reads the settings from the application settings and populates the response parameters.
	 * 
	 * @return always "SUCCESS"
	 */
	public String settings()
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Loading Smartpoint Page Settings");
		}
		super.initSettings();

		setEmpresaTable(getSettings().getProperty("empresa.url.empresaTable"));
		setEmpresaFilial(getSettings().getProperty("empresa.url.empresaFilial"));
		setEmprEdit(getSettings().getProperty("empresa.url.emprEdit"));
		setEmprDelete(getSettings().getProperty("empresa.url.emprDelete"));
		setEmprInsert(getSettings().getProperty("empresa.url.emprInsert"));
		setEmpresaPorFilial(getSettings().getProperty("empresa.url.empresaPorFilial"));
		return SUCCESS;
	}

	public String getEmpresaTable()
	{
		return empresaTable;
	}

	public void setEmpresaTable(String empresaTable)
	{
		this.empresaTable = empresaTable;
	}

	public String getEmpresaFilial() {
		return empresaFilial;
	}

	public void setEmpresaFilial(String empresaFilial) {
		this.empresaFilial = empresaFilial;
	}

	public String getEmprEdit() {
		return emprEdit;
	}

	public void setEmprEdit(String emprEdit) {
		this.emprEdit = emprEdit;
	}

	public String getEmprDelete() {
		return emprDelete;
	}

	public void setEmprDelete(String emprDelete) {
		this.emprDelete = emprDelete;
	}

	public String getEmprInsert() {
		return emprInsert;
	}

	public void setEmprInsert(String emprInsert) {
		this.emprInsert = emprInsert;
	}

	public String getEmpresaPorFilial() {
		return empresaPorFilial;
	}

	public void setEmpresaPorFilial(String empresaPorFilial) {
		this.empresaPorFilial = empresaPorFilial;
	}

}