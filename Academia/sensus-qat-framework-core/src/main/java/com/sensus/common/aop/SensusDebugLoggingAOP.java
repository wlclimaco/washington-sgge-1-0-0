package com.sensus.common.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.util.SensusStringUtil;

/**
 * Used to help with with the debugging of code by using an AOP to log information about method invocations. Following
 * is a example of the Spring Configuration required to use this AOP:<br/>
 * <code>  
 * <bean id="loggingInterceptor" class="com.sensus.common.logging.QATDebugLoggingAOP" />
 * <bean class="org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator">
 * 	<property name="beanNames"> 
 * 		<list> 
 * 			<value>*DAC*</value>
 * 			<value>*BAC*</value>
 * 		</list>
 * 	</property>
 * 	<property name="interceptorNames"> 
 * 		<list>
 * 			<value>loggingInterceptor</value>
 * 		</list>
 * 	</property>
 * </bean>
 * </code> Note the beanNames property list should be changed based on the beans you are using and wish to debug. See
 * the actual org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator in the Spring framework for more
 * information.
 */
public class SensusDebugLoggingAOP implements MethodInterceptor
{

	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(SensusDebugLoggingAOP.class);

	/*
	 * Used by the AOP and invoked prior to the "real" method being invoked.
	 */
	public Object invoke(MethodInvocation methodInvocation) throws Throwable
	{
		long startTime = System.currentTimeMillis();
		LOG.info("Beginning executing method: " + methodInvocation.getMethod().getDeclaringClass() + "::"
				+ methodInvocation.getMethod().getDeclaringClass() + "::"
				+ methodInvocation.getMethod().getName());
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Method parameters: " + methodInvocation.getArguments().length);

			for (Object object : methodInvocation.getArguments())
			{
				LOG.debug(SensusStringUtil.createToString(object));
			}
		}
		Object returnValue = new Object();
		try
		{
			returnValue = methodInvocation.proceed();
		}
		finally
		{
			LOG.info("Finished executing method: " + methodInvocation.getMethod().getDeclaringClass() + "::"
					+ methodInvocation.getMethod().getDeclaringClass() + "::"
					+ methodInvocation.getMethod().getName() + "::" + (System.currentTimeMillis() - startTime)
					+ " msecs.");
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Return value of method: " + SensusStringUtil.createToString(returnValue));
			}
		}
		return returnValue;
	}
}
