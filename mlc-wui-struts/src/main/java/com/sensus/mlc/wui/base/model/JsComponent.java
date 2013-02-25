package com.sensus.mlc.wui.base.model;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Model Object representing JavaScript component as configured in the JavaScript configuration.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class JsComponent
{
	/**
	 * The id of the component.
	 */
	private String id;

	/**
	 * The list of the associated files. Since this is implemented as LinkedHashSet, the order of components will be
	 * maintained while eliminating duplicates.
	 */
	private Set<JsComponentFile> files = new LinkedHashSet<JsComponentFile>();

	/**
	 * The names of the required dependencies.
	 */
	private List<String> dependencyNames = new LinkedList<String>();

	/**
	 * Constructs a JsComponent object from an id.
	 * 
	 * @param idIn the id.
	 */
	public JsComponent(String idIn)
	{
		setId(idIn);
	}

	/**
	 * Get the id.
	 * 
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Set the id.
	 * 
	 * @param idIn the id
	 */
	public void setId(String idIn)
	{
		id = idIn;
	}

	/**
	 * Get the list of all dependency names.
	 * 
	 * @return the list of all dependency names
	 */
	public List<String> getDependencyNames()
	{
		return dependencyNames;
	}

	/**
	 * Set the list of all dependency names.
	 * 
	 * @param dependencyNamesIn the list of all dependency names
	 */
	public void setDependencyNames(List<String> dependencyNamesIn)
	{
		dependencyNames = dependencyNamesIn;
	}

	/**
	 * Get the list of files associated with this component. Once fully loaded, this includes both the
	 * component-specific files and files belonging to resolved dependencies.
	 * 
	 * @return the list of files belonging to the component
	 */
	public Set<JsComponentFile> getFiles()
	{
		return files;
	}

	/**
	 * Set the list of files associated with this component.
	 * 
	 * @param filesIn the list of files belonging to the component
	 */
	public void setFiles(Set<JsComponentFile> filesIn)
	{
		files = filesIn;
	}

	/**
	 * Returns a String representation of ths object.
	 * 
	 * @return string representation of this object
	 */
	@Override
	public String toString()
	{
		return "JsComponent [ getId()=" + getId() + ", getDependencyNames()=" + getDependencyNames() + ", getFiles()="
				+ getFiles() + "]";
	}

}
