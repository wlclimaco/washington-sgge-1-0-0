package com.sensus.common.util;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class QATCache. Is an utility class to programatically control a ehcache cache.
 */
public class SensusCache
{

	/** The cache. */
	private Ehcache cache;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SensusCache.class);

	/**
	 * Sets the Ehcache.
	 * 
	 * @param cache the name of the cache to manipulate.typically injected by Spring.
	 */
	public void setCache(Ehcache cache)
	{
		this.cache = cache;
	}

	/**
	 * Gets the ehcache.
	 * 
	 * @return the ehcache to be worked with.
	 */
	public Ehcache getCache()
	{
		return cache;
	}

	/**
	 * Puts a key/value pair into the ehcache.
	 * 
	 * @param key the key
	 * @param value the value
	 */
	public void put(final Object key, final Object value)
	{
		getCache().put(new Element(key, value));
		if (LOG.isDebugEnabled())
		{
			LOG.debug("QATCache: put: " + key + ",value:" + value);
		}
	}

	/**
	 * Gets the object from ehcache by the key.
	 * 
	 * @param key the key
	 * @return the object to be retrieved.
	 *         Will return null it does not exist in cache.
	 */
	public Object get(final Object key)
	{
		Element element = getCache().get(key);
		if (LOG.isDebugEnabled())
		{
			LOG.debug("QATCache: get: " + key + ",value:" + element);
		}
		if (element != null)
		{
			return element.getValue();
		}
		return null;
	}

	/**
	 * Removes an object in echache by key.
	 * 
	 * @param key the key
	 * @return true, if successful
	 */
	public boolean remove(final Object key)
	{
		boolean results = getCache().remove(key);
		if (LOG.isDebugEnabled())
		{
			LOG.debug("QATCache: remove: " + key + ",result of remove:" + results);
		}
		return results;
	}

	/**
	 * Flush cache. Removes all values from the ehcache.
	 */
	public void flushCache()
	{
		getCache().removeAll();
		if (LOG.isDebugEnabled())
		{
			LOG.debug("QATCache: flushCache(): cacheName:" + cache.getName());
		}
	}

}
