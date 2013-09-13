package com.sensus.common.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Used to add timeout value to any method by using an AOP to create timeout thread for specified method invocations.
 * Following is a example of the Spring Configuration required to use this AOP:<br/>
 * <code>  
 * 	<!-- Define Timeout AOP and wired to desired Activities -->	
 *  <aop:config>
 *     <aop:aspect ref="timeOut">
 *       <aop:pointcut id="act9Timeout" expression="execution(* com.qat.samples.sasm.activity.ActivityImpl9.execute(..))" />
 *        <aop:around pointcut-ref="act9Timeout" method="doTimeout" />
 *     </aop:aspect>
 * 	</aop:config>
 * 	
 * 	<!-- define the timeout aspect -->
 * 	<bean id="timeOut" class="com.sensus.common.util.TimeoutAspect"> 
 * 		<property name="aspectTimeoutValue" value="100" />
 * 	</bean>	
 * </code>
 */
public class TimeoutAOP
{
	private long ltv = 0;

	private static class TimeoutThread extends Thread
	{
		private boolean completed = false;
		private ProceedingJoinPoint point;
		private Throwable throwable;
		private Object value;

		public ProceedingJoinPoint getPoint()
		{
			return point;
		}

		public Throwable getThrowable()
		{
			return throwable;
		}

		public Object getValue()
		{
			return value;
		}

		public boolean isCompleted()
		{
			return completed;
		}

		@Override
		public void run()
		{
			try
			{
				setValue(point.proceed());
			}
			catch (Throwable t)
			{
				setThrowable(t);
			}
			finally
			{
				setCompleted(true);
			}
		}

		public void setCompleted(boolean completed)
		{
			this.completed = completed;
		}

		public void setPoint(ProceedingJoinPoint point)
		{
			this.point = point;
		}

		public void setThrowable(Throwable throwable)
		{
			this.throwable = throwable;
		}

		public void setValue(Object value)
		{
			this.value = value;
		}

	}

	public void setAspectTimeoutValue(long ltvo)
	{
		ltv = ltvo;
	}

	public Object doTimeout(ProceedingJoinPoint point) throws Throwable
	{
		Method method = ((MethodSignature)point.getSignature()).getMethod();
		TimeoutThread thread = new TimeoutThread();
		thread.setDaemon(false);
		thread.setPoint(point);
		thread.start();
		thread.join(ltv);

		if (!thread.isCompleted())
		{
			throw new Exception("Method " + method + " exceeded timeout of " + ltv + " milliseconds");
		}
		else if (thread.getThrowable() != null)
		{
			throw thread.getThrowable();
		}
		else
		{
			return thread.getValue();
		}
	}
}