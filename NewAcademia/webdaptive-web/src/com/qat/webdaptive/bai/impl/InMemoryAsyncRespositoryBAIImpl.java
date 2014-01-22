package com.qat.webdaptive.bai.impl;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.qat.webdaptive.bai.IAsyncRepositoryBAI;

public class InMemoryAsyncRespositoryBAIImpl implements IAsyncRepositoryBAI
{
	private static final Logger LOG = LoggerFactory.getLogger(InMemoryAsyncRespositoryBAIImpl.class);
	private final List<String> messages = new CopyOnWriteArrayList<String>();

	public List<String> fetchMessages(Integer index)
	{
		try
		{
			if (messages.isEmpty())
			{
				return Collections.<String> emptyList();
			}
			Assert.isTrue((index >= 0) && (index <= messages.size()), "Invalid message index");
			return messages.subList(index, messages.size());
		}
		catch (Exception ex)
		{
			LOG.error(ex.toString());
			return Collections.<String> emptyList();
		}
	}

	public void insertMessage(String message)
	{
		try
		{
			messages.add(message);
		}
		catch (Exception ex)
		{
			LOG.error(ex.toString());
		}
	}

	public void deleteMessages()
	{
		try
		{
			messages.clear();
		}
		catch (Exception ex)
		{
			LOG.error(ex.toString());
		}
	}
}
