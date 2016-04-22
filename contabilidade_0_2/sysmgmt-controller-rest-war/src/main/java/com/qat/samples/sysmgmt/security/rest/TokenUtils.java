package com.qat.samples.sysmgmt.security.rest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class TokenUtils.
 */
public class TokenUtils
{
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TokenUtils.class);

	/** The Constant MAGIC_KEY. */
	private static String magicKey;

	/** The token minutes expire. */
	private static long minutesExpire;

	/** The Constant COLON. */
	private static final String COLON = ":";

	/** The Constant DIGEST. */
	private static final String DIGEST = "MD5";

	/** The Constant DIGEST_MISSING_EXCEPTION. */
	private static final String DIGEST_MISSING_EXCEPTION = "No MD5 algorithm available!";

	/**
	 * Sets the magic key.
	 *
	 * @param magicKey the new magic key
	 */
	public void setMagicKey(String magicKey)
	{
		TokenUtils.magicKey = magicKey;
	}

	/**
	 * Sets the minutes expire.
	 *
	 * @param minutesExpire the new minutes expire
	 */
	public void setMinutesExpire(long minutesExpire)
	{
		TokenUtils.minutesExpire = minutesExpire;
	}

	/**
	 * Creates the token.
	 *
	 * @param userDetails the user details
	 * @return the string
	 */
	public static String createToken(UserDetails userDetails)
	{
		/* Expires in one hour */
		long expires = System.currentTimeMillis() + (1000L * minutesExpire * 60);

		// for education purposes only never log
		if (LOG.isDebugEnabled())
		{
			LOG.debug("minutesExpire:" + minutesExpire + " token expires: " + expires);
		}

		StringBuilder tokenBuilder = new StringBuilder();
		tokenBuilder.append(userDetails.getUsername());
		tokenBuilder.append(COLON);
		tokenBuilder.append(expires);
		tokenBuilder.append(COLON);
		// adds special signature that is not easily breakable and repeatable
		// without access to the original box
		tokenBuilder.append(TokenUtils.computeSignature(userDetails, expires));

		// for education purposes only never log
		if (LOG.isDebugEnabled())
		{
			LOG.debug("token:" + tokenBuilder);
		}

		return tokenBuilder.toString();
	}

	/**
	 * Compute signature.
	 *
	 * @param userDetails the user details
	 * @param expires the expires
	 * @return the string
	 */
	public static String computeSignature(UserDetails userDetails, long expires)
	{
		// method creates and verifies token using expiration date, username/password and random md5 from custom
		// configurable seed
		StringBuilder signatureBuilder = new StringBuilder();
		signatureBuilder.append(userDetails.getUsername());
		signatureBuilder.append(COLON);
		signatureBuilder.append(expires);
		signatureBuilder.append(COLON);
		signatureBuilder.append(userDetails.getPassword());
		signatureBuilder.append(COLON);
		signatureBuilder.append(magicKey);

		MessageDigest digest;
		try
		{
			digest = MessageDigest.getInstance(DIGEST);
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new IllegalStateException(DIGEST_MISSING_EXCEPTION);
		}

		return new String(Hex.encode(digest.digest(signatureBuilder.toString().getBytes())));
	}

	/**
	 * Gets the user name from token.
	 *
	 * @param authToken the auth token
	 * @return the user name from token
	 */
	public static String getUserNameFromToken(String authToken)
	{
		if (null == authToken)
		{
			return null;
		}

		String[] parts = authToken.split(COLON);
		return parts[0];
	}

	/**
	 * Validate token.
	 *
	 * @param authToken the auth token
	 * @param userDetails the user details
	 * @return true, if successful
	 */
	public static boolean validateToken(String authToken, UserDetails userDetails)
	{
		String[] parts = authToken.split(COLON);
		long expires = Long.parseLong(parts[1]);
		String signature = parts[2];

		// validates token has not expired based on when it was created
		if (expires < System.currentTimeMillis())
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("token expired on: " + expires + " currentMilliSeconds: " + System.currentTimeMillis());
			}

			return false;
		}

		// verifies that the token sent matches what was produced by service
		// for the user originally. Prevents fake token or token replays
		return signature.equals(TokenUtils.computeSignature(userDetails, expires));
	}
}