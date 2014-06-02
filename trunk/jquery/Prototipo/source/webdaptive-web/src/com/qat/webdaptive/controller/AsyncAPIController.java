package com.qat.webdaptive.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.qat.webdaptive.bai.IAsyncRepositoryBAI;

@Controller
@RequestMapping("/async")
public class AsyncAPIController
{
	private static final Logger LOG = LoggerFactory.getLogger(AsyncAPIController.class);
	private static final String DEFAULT_EXCEPTION_MSG = "webdaptive.controller.async.defaultexception";
	private final Map<DeferredResult<List<String>>, Integer> asyncRequests = new ConcurrentHashMap<DeferredResult<List<String>>, Integer>();

	private IAsyncRepositoryBAI asyncRepository; // injected by Spring through setter

	public IAsyncRepositoryBAI getAsyncRepositoryBAI()
	{
		return asyncRepository;
	}

	@Resource
	public void setAsyncRepositoryBAI(IAsyncRepositoryBAI asyncRepository)
	{
		this.asyncRepository = asyncRepository;
	}

	@RequestMapping(value = "/api/fetchMessages/{id}", method = RequestMethod.GET)
	@ResponseBody
	public DeferredResult<List<String>> fetchMessages(final @PathVariable("id") int messageIndex)
	{
		final DeferredResult<List<String>> deferredResult = new DeferredResult<List<String>>(null, Collections.emptyList());
		asyncRequests.put(deferredResult, messageIndex);

		if (LOG.isInfoEnabled())
		{
			LOG.info("***entered fetchMessages***");
		}

		deferredResult.onCompletion(new Runnable()
		{
			@Override
			public void run()
			{
				asyncRequests.remove(deferredResult);
				if (LOG.isInfoEnabled())
				{
					LOG.info("***onCompletion ran***");
				}
			}
		});

		List<String> messages = getAsyncRepositoryBAI().fetchMessages(messageIndex);
		if (!messages.isEmpty())
		{
			deferredResult.setResult(messages);
		}

		if (LOG.isInfoEnabled())
		{
			LOG.info("***leaving fetchMessages***");
		}
		return deferredResult;
	}

	@RequestMapping(value = "/api/postMessage/{message}", method = RequestMethod.GET)
	@ResponseBody
	public void postMessage(@PathVariable("message") String message)
	{
		getAsyncRepositoryBAI().insertMessage(message);
	}

	// Wakes every minute to insert fake data to simulate BE process of filling messages
	@Scheduled(fixedRate = 60000)
	public void insertDataQueues()
	{
		// This whole method for demo purposes only
		// Clear Old Messages
		getAsyncRepositoryBAI().deleteMessages();
		// Make New messages
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i <= 3; i++)
		{
			getAsyncRepositoryBAI().insertMessage("Msg" + i + " @" + dateFormat.format(cal.getTime()));
		}

		if (LOG.isInfoEnabled())
		{
			LOG.info("***Scheduled Data Inserted Ran***");
		}
	}

	@ExceptionHandler
	@ResponseBody
	public String handleException(IllegalStateException ex)
	{
		return DEFAULT_EXCEPTION_MSG + ex.getMessage();
	}
}
