package org.scribe.model;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.scribe.utils.URLUtils;

/**
 * Represents an OAuth token (either request or access token) and its secret
 * 
 * @author Pablo Fernandez
 */
public class Token implements Serializable {
	private static final long serialVersionUID = 715000866082812683L;

	private final String token;
	private final String secret;
	private final String rawResponse;

	private static final Pattern CALLBACK_CONFIRMED_REGEX = Pattern.compile("\"oauth_callback_confirmed\":\\s*?\"(\\S*?)\"");
	private static final Pattern USER_ID_REGEX = Pattern.compile("\"user_id\":\\s*?\"(\\S*?)\"");
	private static final Pattern CHARGED_DIR_REGEX = Pattern.compile("\"charged_dir\":\\s*?\"(\\S*?)\"");

	/**
	 * Default constructor
	 * 
	 * @param token
	 *            token value
	 * @param secret
	 *            token secret
	 */
	public Token(String token, String secret) {
		this(token, secret, null);
	}

	public Token(String token, String secret, String rawResponse) {
		this.token = token;
		this.secret = secret;
		this.rawResponse = rawResponse;
	}

	public String getToken() {
		return token;
	}

	public String getSecret() {
		return secret;
	}

	public String getRawResponse() {
		if (rawResponse == null) {
			throw new IllegalStateException("This token object was not constructed by scribe and does not have a rawResponse");
		}
		return rawResponse;
	}

	public String getCallbackConfirmed() {
		if (rawResponse == null) {
			throw new IllegalStateException("This token object was not constructed by scribe and does not have a rawResponse");
		}
		Matcher matcher = CALLBACK_CONFIRMED_REGEX.matcher(rawResponse);
		if (matcher.find() && matcher.groupCount() >= 1) {
			return URLUtils.formURLDecode(matcher.group(1));
		}
		else 
			return null;
	}
	
	public String getUserId() {
		if (rawResponse == null) {
			throw new IllegalStateException("This token object was not constructed by scribe and does not have a rawResponse");
		}
		Matcher matcher = USER_ID_REGEX.matcher(rawResponse);
		if (matcher.find() && matcher.groupCount() >= 1) {
			return URLUtils.formURLDecode(matcher.group(1));
		}
		else 
			return null;
	}

	public String getChargedDir() {
		if (rawResponse == null) {
			throw new IllegalStateException("This token object was not constructed by scribe and does not have a rawResponse");
		}
		Matcher matcher = CHARGED_DIR_REGEX.matcher(rawResponse);
		if (matcher.find() && matcher.groupCount() >= 1) {
			return URLUtils.formURLDecode(matcher.group(1));
		}
		else 
			return null;
	}
	
	@Override
	public String toString() {
		return String.format("Token[%s , %s]", token, secret);
	}
}
