package com.qat.samples.sysmgmt.security.controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.qat.samples.sysmgmt.security.rest.TokenUtils;
import com.qat.samples.sysmgmt.security.model.TokenModel;

/**
 * The Class AuthenticationAPIController.
 */
@Controller
@RequestMapping("/auth/api")
public class AuthenticationAPIController {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationAPIController.class);

	/** The user service. */
	@Autowired
	private UserDetailsService userService;

	/** The auth manager. */
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;

	/**
	 * Authenticate.
	 *
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @return the token model
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	@ResponseBody
	public TokenModel authenticate(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		Authentication authentication = authManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		/*
		 * Reload user as password of authentication principal will be null
		 * after authorization and password is needed for token generation
		 */
		UserDetails userDetails = userService.loadUserByUsername(username);

		TokenModel tokenModel = new TokenModel(TokenUtils.createToken(userDetails), userDetails.getUsername(),
				createRoleMap(userDetails));

		if (LOG.isDebugEnabled()) {
			LOG.debug(tokenModel.toString());
		}

		return tokenModel;
	}

	@RequestMapping(value = "/authenticatee", method = RequestMethod.POST)
	@ResponseBody
	public TokenModel authenticatee(@RequestBody String username, String password) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		Authentication authentication = authManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		/*
		 * Reload user as password of authentication principal will be null
		 * after authorization and password is needed for token generation
		 */
		UserDetails userDetails = userService.loadUserByUsername(username);

		TokenModel tokenModel = new TokenModel(TokenUtils.createToken(userDetails), userDetails.getUsername(),
				createRoleMap(userDetails));

		if (LOG.isDebugEnabled()) {
			LOG.debug(tokenModel.toString());
		}

		return tokenModel;
	}

	/**
	 * Creates the role map.
	 *
	 * @param userDetails
	 *            the user details
	 * @return the map
	 */
	private Map<String, Boolean> createRoleMap(UserDetails userDetails) {
		Map<String, Boolean> roles = new HashMap<String, Boolean>();
		for (GrantedAuthority authority : userDetails.getAuthorities()) {
			roles.put(authority.getAuthority(), Boolean.TRUE);
		}

		return roles;
	}
}
