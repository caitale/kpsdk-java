package kp.client.model;

import java.util.Map;

/**
 * API类, 不同的API都继承该类
 * @author Tale
 *
 */
public abstract class ApiRequest {
	protected String method;
	protected String urlPah;
	protected String filePath;
	protected Map<String, String> requiredParameters;
	protected Map<String, String> optionalParameters;
	
	public String getUrlPah() {
		return urlPah;
	}

	public void setUrlPah(String urlPah) {
		this.urlPah = urlPah;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getMethod(){
		return method;
	}
	
	public Map<String, String> getRequiredParams(){
		return requiredParameters;
	}
	
	public Map<String, String> getOptionalParams(){
		return optionalParameters;
	}
}
