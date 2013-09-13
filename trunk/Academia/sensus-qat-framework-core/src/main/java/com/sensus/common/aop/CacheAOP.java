package com.sensus.common.aop;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * The Class CacheAOP.
 */
public class CacheAOP
{
	/** The cache. */
	private Ehcache cache;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CacheAOP.class);

	/*
	 *
	 */
	/**
	 * This proceeding join point will either return the cached value
	 * of a method or put the value into cache when invoked.
	 *
	 * @param point the proceeding join point
	 * @return the cached object
	 * @throws Throwable the throwable
	 */
	public Object cacheObject(ProceedingJoinPoint point) throws Throwable
	{
		Object result;
		String cacheKey = getCacheKey(point);
		Element element = cache.get(cacheKey);
		if (LOG.isDebugEnabled())
		{
			LOG.debug("CacheAOP: get: " + cacheKey + ",value:" + element);
		}
		if (element == null)
		{
			result = point.proceed();
			element = new Element(cacheKey, result);
			cache.put(element);
			if (LOG.isDebugEnabled())
			{
				LOG.debug("CacheAOP: put: " + cacheKey + ",value:" + result);
			}
		}
		return element.getValue();
	}

	/**
	 * Flushes designed named cache when the pointcut calls this method.
	 */
	public void flushCache()
	{
		cache.removeAll();
		if (LOG.isDebugEnabled())
		{
			LOG.debug("CacheAOP: flushCache(): cacheName:" + cache.getName());
		}
	}

	/**
	 * Gets the cache key.
	 *
	 * @param point the point
	 * @return the cache key
	 */
	private String getCacheKey(ProceedingJoinPoint point)
	{
		String targetName = point.getTarget().getClass().getSimpleName();
		String methodName = point.getSignature().getName();
		Object[] arguments = point.getArgs();
		StringBuilder cacheKey = new StringBuilder();
		cacheKey.append(targetName).append(".").append(methodName);
		if ((arguments != null) && (arguments.length != 0))
		{
			for (int i = 0; i < arguments.length; i++)
			{
				cacheKey.append(".").append(arguments[i]);
			}
		}
		return cacheKey.toString();
	}

	/**
	 * Sets the named EhCache cache.
	 *
	 * @param cache the new cache
	 */
	public void setCache(Ehcache cache)
	{
		this.cache = cache;
	}
}
