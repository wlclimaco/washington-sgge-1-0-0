package com.sensus.mlc.wui.common.unittest;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.test.SeleniumBase;

/**
 * Base level class for all SLC Selenium tests.
 * Place common functionality in this class so it can be leveraged by sub-classes.
 */
public class SLCSeleniumBase extends SeleniumBase
{

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(SLCSeleniumBase.class);

	/**
	 * Instantiates a new sLC selenium base.
	 *
	 * @throws Exception the exception
	 */
	public SLCSeleniumBase() throws Exception
	{
		super.setUpSelenium() ;

		LOG.info("Here we go...") ;
	}

	/**
	 * Do slc login.
	 *
	 * @return true, if successful
	 */
	protected boolean doLogin()
	{

		getWebDriver().findElement(By.name("uname")).clear();
		getWebDriver().findElement(By.name("uname")).sendKeys( getTestProperty("userid"));
		getWebDriver().findElement(By.name("pword")).clear();
		getWebDriver().findElement(By.name("pword")).sendKeys( getTestProperty("password")) ;
		getWebDriver().findElement(By.name("OkBtn")).click();

		String title = getWebDriver().getTitle();
		// Make sure the user was found
		Assert.assertFalse("Login Error", title.toLowerCase().contains("unknown"));

		// Select the project
		getWebDriver().findElement(By.name("SelectProject")).click();

		// Verify the title is what we expect
		title = getWebDriver().getTitle();
		if (title.toLowerCase().contains("error"))
		{
			String source = getWebDriver().getPageSource().toLowerCase();
			if (source.contains("already logged in"))
			{
				getWebDriver().findElement(By.name("OkBtn")).click();
			}
			else
			{
				Assert.fail("Login Error");
			}
		}

		return true ;
	}
}
