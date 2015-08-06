package com.prosperitasglobal.sendsolv.common.util;

import java.util.concurrent.Future;

import org.springframework.security.core.Authentication;

import com.sensus.common.model.request.Request;

public interface IAsyncDMFacade
{
	public <Response> Future<Response> callAsyncMethod(Object serviceBCF, String methodtoCall, Request request,
			Authentication authentication);
}
