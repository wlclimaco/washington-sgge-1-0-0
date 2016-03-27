package com.qat.samples.sysmgmt.security.util;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 * The Class LCSecurityHandler.
 */
public final class LCSecurityHandler
{

	// We need an SHA encoder since our passwords in the database are SHA(256) encoded.
	/** The password encoder. */
	private static final ShaPasswordEncoder PASSWORDENCODER = new ShaPasswordEncoder(256);

	/**
	 * Instantiates a new lC security handler.
	 */
	private LCSecurityHandler()
	{
	}

	/**
	 * Encrypt password.
	 *
	 * @param encPass the credential
	 * @param salt the target
	 * @return the string
	 */
	public static String encryptPassword(String encPass, Object salt)
	{
		return PASSWORDENCODER.encodePassword(encPass, salt);
	}

	/**
	 * Valide password.
	 *
	 * @param encPass the enc pass
	 * @param rawPass the raw pass
	 * @param salt the salt
	 * @return true, if successful
	 */
	public static boolean validePassword(String encPass, String rawPass, Object salt)
	{
		return PASSWORDENCODER.isPasswordValid(encPass, rawPass, salt);
	}

}
