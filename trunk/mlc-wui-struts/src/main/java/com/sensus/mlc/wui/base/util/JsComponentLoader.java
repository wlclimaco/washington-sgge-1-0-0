package com.sensus.mlc.wui.base.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.sensus.mlc.wui.base.model.JsComponent;
import com.sensus.mlc.wui.base.model.JsComponentFile;
import com.sensus.mlc.wui.base.model.JsComponentFile.FileType;

/**
 * Factory for loading the JavaScript components from the configuration file. This was modified from the JavaScript
 * loading mechanism of sensus-site.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public final class JsComponentLoader
{
	/**
	 * The logger for this class.
	 */
	private static Log LOG = LogFactory.getLog(JsComponentLoader.class);

	/**
	 * The selector for required components.
	 */
	private static final String REQUIRES_COMPONENT = "/requires/component";

	/**
	 * Missing component message.
	 */
	private static final String MESSAGE_MISSING_COMPONENT = "Cannot find component: '%s'.";

	/**
	 * The name of the id attribute.
	 */
	private static final String ELEMENT_ID = "id";

	/**
	 * Private constructor to prevent instantiation.
	 */
	private JsComponentLoader()
	{
	}

	/**
	 * List of parsing settings. Internally used only.
	 */
	private static List<FileSettings> TYPES = Arrays.asList(
			new FileSettings(FileType.SETTINGS, "/files/settings", false, false),
			new FileSettings(FileType.SCRIPT_ABSOLUTE,
					"/files/script[@absolute='true']", true, false),
			new FileSettings(FileType.SCRIPT_LOCAL,
					"/files/script[@absolute='false']", false, false),
			new FileSettings(FileType.STYLESHEET_ABSOLUTE,
					"/files/stylesheet[@absolute='true']", true, false),
			new FileSettings(FileType.STYLESHEET_LOCAL,
					"/files/stylesheet[@absolute='false']", false, false),
			new FileSettings(FileType.SEC_LOCAL,
					"/files/script[@sec='true']", false, true));

	/**
	 * The factory method called by Spring. Loads the given file, parses it and returns a map of all loaded JavaScript
	 * components.
	 * 
	 * @param path the path to the configuration file (classpath resource)
	 * @return map of all loaded JavaScript components
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, JsComponent> loadComponents(String path)
	{

		Map<String, JsComponent> jsComponents = new LinkedHashMap<String, JsComponent>();
		try
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Loading JavaScript Component information from " + path);
			}
			Document xmlDoc = new SAXReader().read(Thread.currentThread().getContextClassLoader().getResource(path));
			for (Iterator i = xmlDoc.getRootElement().elementIterator(); i.hasNext();)
			{
				JsComponent component = createJSComponent((Element)i.next());
				jsComponents.put(component.getId(), component);
			}

			Set<JsComponentFile> set;
			for (JsComponent c : jsComponents.values())
			{
				set = new LinkedHashSet<JsComponentFile>();
				findDependencies(jsComponents, c, set);
				set.addAll(c.getFiles());
				c.setFiles(set);
			}

		}
		catch (Exception e)
		{
			LOG.error("Could not load JS Components", e);
			throw new RuntimeException("Could not load JavaScript Components", e);
		}
		return jsComponents;
	}

	/**
	 * Utility method that creates a JsComponent object from and XML element. It only populates the dependency names.
	 * 
	 * @param element the XML element to parse
	 * @return the resulting JsComponent object
	 */
	private static JsComponent createJSComponent(Element element)
	{
		JsComponent jsComponent = new JsComponent(element.attributeValue(ELEMENT_ID));
		parseDependencies(element, jsComponent);
		for (FileSettings settings : TYPES)
		{
			populateFiles(jsComponent, element, settings);
		}
		return jsComponent;
	}

	/**
	 * Utility method that populates the dependency names from and XML element into a JsComponent object.
	 * 
	 * @param element the XML element to parse
	 * @param jsComponent the JsComponent object to populate
	 */
	@SuppressWarnings("unchecked")
	private static void parseDependencies(Element element, JsComponent jsComponent)
	{
		String path = element.getUniquePath();
		List<Node> nodes = element.selectNodes(path + REQUIRES_COMPONENT);
		for (Node node : nodes)
		{
			if (node instanceof Element)
			{
				String dependency = ((Element)node).getText();
				if ((dependency != null) && (dependency.length() > 0))
				{
					jsComponent.getDependencyNames().add(dependency);
				}
			}
		}
	}

	/**
	 * Utility method that parses the XML configuration element for required files.
	 * 
	 * @param jsComponent the JsComponent object to populate
	 * @param element the XML element to parse
	 * @param settings the parsing configuration
	 */
	@SuppressWarnings("unchecked")
	private static void populateFiles(JsComponent jsComponent, Element element, FileSettings settings)
	{

		List<Node> nodes = element.selectNodes(element.getUniquePath() + settings.selector);
		for (Node node : nodes)
		{
			if (node instanceof Element)
			{
				if (LOG.isDebugEnabled())
				{
					LOG.debug("Adding files...." + ((Element)node).getText());
				}
				String filePath = ((Element)node).getText();
				if ((filePath != null) && (filePath.length() > 0))
				{
					jsComponent.getFiles()
							.add(new JsComponentFile(settings.getType(), filePath, settings.isAbsolutePath(),
									settings.getSec()));
				}
			}
		}
	}

	/**
	 * Utility method that fills in dependency files after all components are created. This method is called
	 * recursively. All results are stored in the provided set.
	 * 
	 * @param components the map of all components loaded from the configuration file
	 * @param component the component to resolve dependencies for
	 * @param set the set to store resolved dependencies in
	 */
	private static void findDependencies(Map<String, JsComponent> components, JsComponent component,
			Set<JsComponentFile> set)
	{

		for (String requiredId : component.getDependencyNames())
		{
			JsComponent requiredComponent = components.get(requiredId);
			if (requiredComponent == null)
			{
				String message = String.format(MESSAGE_MISSING_COMPONENT, requiredId);
				LOG.error(message);
				throw new RuntimeException(message);
			}

			findDependencies(components, requiredComponent, set);
		}
		set.addAll(component.getFiles());
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Adding Dependencies to Set ..." + component.getDependencyNames() + " \n\t\t" + set);
		}
	}

	/**
	 * Utility Class to describe the internal parsing configuration for the JsComponentLoader.
	 * 
	 * @author Anke Doerfel-Parker
	 * 
	 */
	public static class FileSettings
	{
		/**
		 * The file type to parse for.
		 */
		private FileType type;

		/**
		 * The selector to use.
		 */
		private String selector;

		/**
		 * Whether to look for absolute (true) or relative paths (false).
		 */
		private boolean absolutePath;

		private Boolean sec;

		/**
		 * Constructs a FileSettings object from the file type, selector and absolute path flag.
		 * 
		 * @param typeIn the file type
		 * @param selectorIn the selector
		 * @param absolutePathIn whether to look for absolute or relative paths
		 */
		public FileSettings(FileType typeIn, String selectorIn, boolean absolutePathIn, Boolean sec)
		{
			setType(typeIn);
			setSelector(selectorIn);
			setAbsolutePath(absolutePathIn);
			setSec(sec);
		}

		/**
		 * Get the file type.
		 * 
		 * @return the file type
		 */
		public FileType getType()
		{
			return type;
		}

		/**
		 * Set the file type.
		 * 
		 * @param typeIn the file type to set
		 */
		public void setType(FileType typeIn)
		{
			type = typeIn;
		}

		/**
		 * Get the selector.
		 * 
		 * @return the selector
		 */
		public String getSelector()
		{
			return selector;
		}

		/**
		 * Set the selector.
		 * 
		 * @param selectorIn the selector to set
		 */
		public void setSelector(String selectorIn)
		{
			selector = selectorIn;
		}

		/**
		 * Get the absolute path indicator.
		 * 
		 * @return the absolute path indicator
		 */
		public boolean isAbsolutePath()
		{
			return absolutePath;
		}

		/**
		 * Set the absolute path indicator.
		 * 
		 * @param absolutePathIn the absolute path indicator to set
		 */
		public void setAbsolutePath(boolean absolutePathIn)
		{
			absolutePath = absolutePathIn;
		}

		public Boolean getSec()
		{
			return sec;
		}

		public void setSec(Boolean sec)
		{
			this.sec = sec;
		}

	}

}
