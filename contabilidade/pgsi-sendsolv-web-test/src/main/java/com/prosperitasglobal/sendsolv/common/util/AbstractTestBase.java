package com.prosperitasglobal.sendsolv.common.util;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collection;

import javax.annotation.Resource;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-wui-unittest-context-test.xml",
"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-integration-twilio-bac-context.xml"})
public abstract class AbstractTestBase extends AbstractJUnit4SpringContextTests
{
	private MockMvc mockMvc;

	private MockHttpSession session;

	private WebApplicationContext wac;

	/** The data. */
	private String data;

	@Autowired
	private FilterChainProxy springSecurityFilter;

	/*
	 * Note you may have to override that for testing other user scenarios
	 */
	@Before
	public void setup() throws Exception
	{
		setSessionDetails("admin", "password", "ROLE_DOMAIN_ADMIN");

		setData("");
		// TODO set user context
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(springSecurityFilter, "/*").build();
	}

	public MockHttpSession getSession()
	{
		return session;
	}

	public void setSession(MockHttpSession session)
	{
		this.session = session;
	}

	public String getData()
	{
		return data;
	}

	public void setData(String data)
	{
		this.data = data;
	}

	protected void setSessionDetails(final String username, final String password, final String role)
	{
		// Create a session with all content necessary to pass the basic security requirements and provide a security
		// context for use in the AOP
		@SuppressWarnings("serial")
		UserDetails user = new UserDetails()
		{
			@SuppressWarnings("serial")
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities()
			{
				return Arrays.asList(new GrantedAuthority()
				{
					@Override
					public String getAuthority()
					{
						return role;
					}
				});
			}

			@Override
			public String getPassword()
			{
				return password;
			}

			@Override
			public String getUsername()
			{
				return username;
			}

			@Override
			public boolean isAccountNonExpired()
			{
				return true;
			}

			@Override
			public boolean isAccountNonLocked()
			{
				return true;
			}

			@Override
			public boolean isCredentialsNonExpired()
			{
				return true;
			}

			@Override
			public boolean isEnabled()
			{
				return true;
			}
		};

		session = new MockHttpSession();
		session.setAttribute(
				HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
				new MockSecurityContext(new UsernamePasswordAuthenticationToken(
						user,
						user.getPassword(),
						user.getAuthorities())));
	}

	public MockMvc getMockMvc()
	{
		return mockMvc;
	}

	public WebApplicationContext getWac()
	{
		return wac;
	}

	@Resource
	public void setWac(WebApplicationContext wac)
	{
		this.wac = wac;
	}

	@SuppressWarnings("serial")
	public static class MockSecurityContext implements SecurityContext
	{

		private Authentication auth;

		public MockSecurityContext(Authentication authentication)
		{
			auth = authentication;
		}

		@Override
		public Authentication getAuthentication()
		{
			return auth;
		}

		@Override
		public void setAuthentication(Authentication auth)
		{
			this.auth = auth;

		}

	}

	/**
	 * Perform test.
	 *
	 * @param methodToExecute the method to execute
	 * @return the result actions
	 * @throws Exception the exception
	 */
	protected ResultActions performTest(String methodToExecute) throws Exception
	{

		return getMockMvc().perform(
				post(methodToExecute).contentType(MediaType.APPLICATION_JSON)
						.session(getSession()).content(getData().getBytes())).andExpect(
				status().isOk());
	}

	protected ResultActions performTestGet(String methodToExecute) throws Exception
	{
		return getMockMvc().perform(
				get(methodToExecute).contentType(MediaType.APPLICATION_JSON)
						.session(getSession()).content(getData().getBytes())).andExpect(
				status().isOk());
	}

	protected ResultActions performTestPost(String methodToExecute) throws Exception
	{
		return getMockMvc().perform(
				post(methodToExecute).contentType(MediaType.APPLICATION_JSON)
						.session(getSession()).content(getData().getBytes())).andExpect(
				status().isOk());
	}

}
