package com.sensus.mlc.wui.base.unittest.action;

import java.util.Map;
import java.util.Set;

import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.sensus.mlc.wui.base.action.LayoutBase;
import com.sensus.mlc.wui.base.model.JsComponent;
import com.sensus.mlc.wui.base.model.JsComponentFile;
import com.sensus.mlc.wui.base.model.JsComponentFile.FileType;
import com.sensus.mlc.wui.base.util.JsComponentLoader;

/**
 * Unit Test for loading of JsComponents. It tests both JsComponentLoader and the related functionality of LayoutBase.
 * 
 * Developer Note: This technically doesn't need to be a StrutsSpringTestCase, but when running all JUnitTests in src
 * via
 * "Run As", "Coverage As" or "Debug As", the actual Struts test cases will not be able to resolve their contexts right
 * if other unit test types are mixed in (or even multiple spring contexts are loaded).
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class JsComponentLoaderTest extends StrutsSpringTestCase
{
	private static final String COMPONENT_DEPENDENCY1 = "dependency1";
	private static final String COMPONENT_DEPENDENCY2 = "dependency2";
	private static final String COMPONENT_INDEPENDENT = "independent";
	private static final String COMPONENT_BASE = "base";

	/**
	 * Point to the test Spring configuration.
	 */
	@Override
	public String getContextLocations()
	{
		return "classpath:context/sensus-mlc-wui-unittest-context-test.xml";
	}

	/**
	 * Test the loading of JavaScript Components.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testJsComponentLoader() throws Exception
	{
		Map<String, JsComponent> components = JsComponentLoader.loadComponents("test-javascript-components.xml");

		// base
		assertTrue("Contains " + COMPONENT_BASE, components.containsKey(COMPONENT_BASE));
		JsComponent c = components.get(COMPONENT_BASE);
		assertEquals("Number of dependencies", 0, c.getDependencyNames().size());
		int count = 0;
		// Note that settings are parsed first, then scripts and then stylesheets.
		FileType[] expectedTypes =
				new FileType[] {FileType.SETTINGS,
						FileType.SCRIPT_ABSOLUTE, FileType.SCRIPT_LOCAL, FileType.STYLESHEET_ABSOLUTE,
						FileType.STYLESHEET_LOCAL};
		for (JsComponentFile file : c.getFiles())
		{
			assertEquals("Type of file #" + count, expectedTypes[count++], file.getType());
		}

		// dependency 1
		assertTrue("Contains " + COMPONENT_DEPENDENCY1, components.containsKey(COMPONENT_DEPENDENCY1));
		c = components.get(COMPONENT_DEPENDENCY1);
		assertEquals("Number of dependencies", 1, c.getDependencyNames().size());
		assertEquals("Name of dependency for: " + COMPONENT_DEPENDENCY1, COMPONENT_BASE, c.getDependencyNames().get(
				0));
		assertEquals("Number of files", 10, c.getFiles().size());
		count = 0;
		// Note that settings are parsed first, then scripts and then stylesheets.
		expectedTypes =
				new FileType[] {FileType.SETTINGS,
						FileType.SCRIPT_ABSOLUTE, FileType.SCRIPT_LOCAL, FileType.STYLESHEET_ABSOLUTE,
						FileType.STYLESHEET_LOCAL, FileType.SETTINGS,
						FileType.SCRIPT_ABSOLUTE, FileType.SCRIPT_LOCAL, FileType.STYLESHEET_ABSOLUTE,
						FileType.STYLESHEET_LOCAL};
		for (JsComponentFile file : c.getFiles())
		{
			assertEquals("Type of file #" + count, expectedTypes[count++], file.getType());
		}

		// dependency 2
		assertTrue("Contains" + COMPONENT_DEPENDENCY2, components.containsKey(COMPONENT_DEPENDENCY2));
		c = components.get(COMPONENT_DEPENDENCY2);
		assertEquals("Number of dependencies", 1, c.getDependencyNames().size());
		assertEquals("Name of dependency for: " + COMPONENT_DEPENDENCY2, COMPONENT_DEPENDENCY1, c.getDependencyNames()
				.get(
						0));
		assertEquals("Number of files", 10, c.getFiles().size());
		count = 0;
		// Note that settings are parsed first, then scripts and then stylesheets.
		expectedTypes =
				new FileType[] {FileType.SETTINGS,
						FileType.SCRIPT_ABSOLUTE, FileType.SCRIPT_LOCAL, FileType.STYLESHEET_ABSOLUTE,
						FileType.STYLESHEET_LOCAL, FileType.SETTINGS,
						FileType.SCRIPT_ABSOLUTE, FileType.SCRIPT_LOCAL, FileType.STYLESHEET_ABSOLUTE,
						FileType.STYLESHEET_LOCAL};
		for (JsComponentFile file : c.getFiles())
		{
			assertEquals("Type of file #" + count, expectedTypes[count++], file.getType());
		}

		// independent
		assertTrue("Contains" + COMPONENT_INDEPENDENT, components.containsKey(COMPONENT_INDEPENDENT));
		c = components.get(COMPONENT_INDEPENDENT);
		assertEquals("Number of dependencies", 0, c.getDependencyNames().size());
		assertEquals("Number of files", 1, c.getFiles().size());
		count = 0;
		// Note that settings are parsed first, then scripts and then stylesheets.
		expectedTypes =
				new FileType[] {FileType.SETTINGS};
		for (JsComponentFile file : c.getFiles())
		{
			assertEquals("Type of file #" + count, expectedTypes[count++], file.getType());
		}

		LayoutBase action = new LayoutBase();
		action.setJsComponentMap(components);

		// No JS dependencies set for page
		Set<JsComponentFile> files = action.getJsImports();
		assertEquals("No JavaScript imports", 0, files.size());
		files = action.getCssImports();
		assertEquals("No CSS imports", 0, files.size());
		files = action.getJsSettings();
		assertEquals("No JavaScript Settings", 0, files.size());

		// Dependency2 set for page
		action.setJsComponents(COMPONENT_DEPENDENCY2);
		files = action.getJsImports();
		assertEquals("No JavaScript imports", 4, files.size());
		files = action.getCssImports();
		assertEquals("No CSS imports", 4, files.size());
		files = action.getJsSettings();
		assertEquals("No JavaScript Settings", 2, files.size());

		// Base and Dependency2 set for page (redundant settings - no duplication should occur)
		action.setJsComponents(COMPONENT_BASE + "," + COMPONENT_DEPENDENCY2);
		files = action.getJsImports();
		assertEquals("No JavaScript imports", 4, files.size());
		files = action.getCssImports();
		assertEquals("No CSS imports", 4, files.size());
		files = action.getJsSettings();
		assertEquals("No JavaScript Settings", 2, files.size());

		// Independent and Dependency2 set for page
		action.setJsComponents(COMPONENT_INDEPENDENT + "," + COMPONENT_DEPENDENCY2);
		files = action.getJsImports();
		assertEquals("No JavaScript imports", 4, files.size());
		files = action.getCssImports();
		assertEquals("No CSS imports", 4, files.size());
		files = action.getJsSettings();
		assertEquals("No JavaScript Settings", 3, files.size());

	}
}
