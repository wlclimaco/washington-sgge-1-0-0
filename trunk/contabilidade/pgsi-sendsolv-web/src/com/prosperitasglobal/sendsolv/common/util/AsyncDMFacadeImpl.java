package com.prosperitasglobal.sendsolv.common.util;

import java.lang.reflect.Method;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ReflectionUtils;

import com.prosperitasglobal.sendsolv.tenant.model.response.TenantResponse;
import com.qat.framework.model.request.Request;
import com.qat.framework.validation.ValidationUtil;

public class AsyncDMFacadeImpl implements IAsyncDMFacade
{

	private static final String DEFAULT_ASYNC_FACADE_EXCEPTION_MSG = "sensus.dm.elec.groupbcfimpl.defaultexception";

	private static final Logger LOG = LoggerFactory.getLogger(AsyncDMFacadeImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	@Async("dmExecutor")
	public <Response> Future<Response> callAsyncMethod(Object service, String methodtoCall, Request request,
			Authentication authentication)
			{

		try
		{
			if (!ValidationUtil.isNull(authentication))
			{
				SecurityContext ctx = SecurityContextHolder.createEmptyContext();
				ctx.setAuthentication(authentication);
				SecurityContextHolder.setContext(ctx);
			}

			Method mtd = ReflectionUtils.findMethod(service.getClass(), methodtoCall, request.getClass());
			return new AsyncResult<Response>((Response)mtd.invoke(service, request));
		}
		catch (Exception ex)
		{
			TenantResponse response = new TenantResponse();
			// SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ASYNC_FACADE_EXCEPTION_MSG);
			return new AsyncResult<Response>((Response)response);
		}

			}
}
