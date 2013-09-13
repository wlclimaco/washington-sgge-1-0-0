package com.sensus.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * The Class QATAppContext.
 */
public class SensusAppContext implements ApplicationContextAware  
{

	  /** The application context. */
  	private static ApplicationContext APPLICATION_CONTEXT;
		   
	  /* (non-Javadoc)
  	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
  	 */
  	public void setApplicationContext(ApplicationContext arg0) throws BeansException 
	  {
	      APPLICATION_CONTEXT = arg0;
	  }
		   
	  /**
  	 * Gets the application context.
  	 * 
  	 * @return the application context
  	 */
  	public static ApplicationContext getApplicationContext()
	  {
	      return APPLICATION_CONTEXT;
	  }
		   
	  /**
  	 * Gets the bean.
  	 * 
  	 * @param beanName the bean name
  	 * 
  	 * @return the bean
  	 */
  	public static Object getBean(String beanName)
	  {
	      if(beanName==null||beanName.trim().length()==0)
	      {
	         return null;
	      }
	      else
	      {
	         return APPLICATION_CONTEXT.getBean(beanName);
	      }
	  }
}
