package com.sensus.mlc.wui.base.action;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sensus.mlc.wui.base.model.JsComponent;
import com.sensus.mlc.wui.base.model.JsComponentFile;
import com.sensus.mlc.wui.base.model.JsComponentFile.FileType;

/**
 * Base Action for Struts actions that render pages within the main SiteMesh decorator. The JavaScript components are
 * injected by Spring and used in the main decorator to generate JavaScript and CSS imports.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
@SuppressWarnings("serial")
public class LayoutBase extends ActionBase
{

	/**
	 * The map of JavaScript and CSS components. Injected by Spring.
	 */
	private Map<String, JsComponent> jsComponentMap;

	/**
	 * The delimiter of JavaScript component names as defined in the struts-config.
	 */
	private static final String COMMA = ",";

	/**
	 * All resulting JavaScript components for the current action. Calculated from the component names configured in the
	 * struts configuration and the Spring-injected map of all loaded JavaScript components.
	 */
	private Set<JsComponentFile> jsComponents = new LinkedHashSet<JsComponentFile>();

	/**
	 * Explicit no-argument constructor.
	 */
	public LayoutBase()
	{
	}

	/**
	 * The default method for all page-rendering actions.
	 * 
	 * @return always "SUCCESS"
	 */
	@Override
	public String execute()
	{
		return SUCCESS;
	}

	/**
	 * Get all JavaScript components applicable to this page.
	 * 
	 * @return all JavaScript components applicable to this page
	 */
	public Set<JsComponentFile> getJsComponents()
	{
		return jsComponents;
	}

	/**
	 * Set the JavaScript components applicable to this page. The string comes from the struts configuration and is
	 * translated into a set of JsComponent objects using the Spring-injected jsComonents map.
	 * 
	 * @param jsComponents the new js components
	 */
	public void setJsComponents(String jsComponents)
	{
		for (String component : jsComponents.split(COMMA))
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Adding JavaScript/CSS Component %s", component);
			}
			addComponent(component);
		}
	}

	/**
	 * Utility method for translating a JavaScript component name into a JsComponentObject. The object is looked up in
	 * the Spring-injected jsComonents map and automatically
	 * added to the JavaScript component set for this action.
	 * 
	 * @param id the name of the JavaScript component to add.
	 */
	private void addComponent(String id)
	{
		JsComponent component = getJsComponentMap().get(id);
		if (component == null)
		{
			if (LOG.isDebugEnabled())
			{
				LOG.error("'%s' is not a valid JavaScript/CSS Component.", id);
			}
			return;
		}
		getJsComponents().addAll(component.getFiles());
	}

	/**
	 * Return a set of all JavaScript files to be imported at the end of the HTML body.
	 * 
	 * @return a set of all JavaScript files to be imported at the end of the HTML body
	 */
	public Set<JsComponentFile> getJsImports()
	{
		return getComponents(Arrays.asList(FileType.SCRIPT_ABSOLUTE, FileType.SCRIPT_LOCAL, FileType.SEC_LOCAL));
	}

	/**
	 * Return a set of all CSS files to be imported in the HTML Header.
	 * 
	 * @return a set of all CSS files to be imported in the HTML Header
	 */
	public Set<JsComponentFile> getCssImports()
	{
		return getComponents(Arrays.asList(FileType.STYLESHEET_ABSOLUTE, FileType.STYLESHEET_LOCAL));
	}

	/**
	 * Return a set of all JavaScript files to be imported in the HTML Header.
	 * 
	 * @return a set of all JavaScript files to be imported in the HTML Header
	 */
	public Set<JsComponentFile> getJsSettings()
	{
		return getComponents(Arrays.asList(FileType.SETTINGS));
	}

	/**
	 * Return a set of all required JavaScript components of one or more particular type(s).
	 * 
	 * @param fileTypes a list of file types to include in the returned set
	 * @return a set of all required JavaScript components of the specified types
	 */
	public Set<JsComponentFile> getComponents(List<FileType> fileTypes)
	{
		Set<JsComponentFile> resultingComponentList = new LinkedHashSet<JsComponentFile>();
		for (JsComponentFile c : getJsComponents())
		{
			if (fileTypes.contains(c.getType()))
			{
				resultingComponentList.add(c);
			}
		}
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Returning " + resultingComponentList);
		}
		return resultingComponentList;
	}

	/**
	 * Set the global JavaScript component map (injected by Spring).
	 * 
	 * @param jsComponentMapIn the global JavaScript component map
	 */
	public void setJsComponentMap(Map<String, JsComponent> jsComponentMapIn)
	{
		jsComponentMap = jsComponentMapIn;
	}

	/**
	 * Get the global JavaScript component map.
	 * 
	 * @return the global JavaScript component map
	 */
	public Map<String, JsComponent> getJsComponentMap()
	{
		return jsComponentMap;
	}

}
