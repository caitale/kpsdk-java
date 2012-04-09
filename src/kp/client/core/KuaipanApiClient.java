package kp.client.core;

import java.util.Map;

import kp.client.model.ApiRequest;
import kp.client.util.FileUtils;

import org.json.JSONObject;
import org.scribe.model.OAuthConfig;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Request;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuth10aServiceImpl;

/**
 * API调用的实现类，覆盖了scribe的OAuth实现类，可统一使用该类获取access_token和调用快盘API
 * @author Tale
 *
 */
public class KuaipanApiClient extends OAuth10aServiceImpl{

	public KuaipanApiClient(KuaipanApi api, OAuthConfig config) {
		super(api, config);
	}

	/**
	 * 调用快盘API,前提是已获得access_token
	 * @param token access_token
	 * @param apiRequest 相应的API请求类
	 * @return 调用结果
	 */
	public JSONObject doCall(Token token, ApiRequest apiRequest){
		String method = apiRequest.getMethod();
		String apiUrl = api.getApiUrl(method);
		
		/** 
		 * 部分api的调用URL需要添加root或者path，分开处理
		 */
		if( "download_file".equals(method) || "thumbnail".equals(method)){
			return downloadFile(token, apiRequest);
		}
		else if( "upload_file".equals(method) ){
			return uploadFile(token, apiRequest);
		}
		else if( "metadata".equals(method) || "shares".equals(method)){
			apiUrl = String.format(apiUrl, apiRequest.getUrlPah());
		}
		
		OAuthRequest request = new OAuthRequest(api.getApiVerb(), apiUrl);
		addApiParams(request, apiRequest);
		signRequest(token, request);	
		Response response = request.send();
		return api.getApiResponseExtractor().extract(response.getBody());	
	}
	
	/**
	 * 下载文件
	 * @param token
	 * @param apiRequest
	 * @return
	 */
	private JSONObject downloadFile(Token token, ApiRequest apiRequest){
		OAuthRequest request = new OAuthRequest(api.getApiVerb(), api.getApiUrl(apiRequest.getMethod()));
		addApiParams(request, apiRequest);
		signRequest(token, request);
		return FileUtils.downloadFile(request, apiRequest);
	}
	
	/**
	 * 上传文件
	 * @param token
	 * @param apiRequest
	 * @return
	 */
	private JSONObject uploadFile(Token token, ApiRequest apiRequest){
		String method = apiRequest.getMethod();
		String apiUrl = api.getApiUrl(method);
		apiUrl = String.format(apiUrl, apiRequest.getUrlPah());
		OAuthRequest request = new OAuthRequest(Verb.POST, apiUrl );
		addApiParams(request, apiRequest);
		signRequest(token, request);
		
		String response = FileUtils.uploadFile(request, apiRequest);
		return api.getApiResponseExtractor().extract(response);	
	}

	/**
	 * 添加api调用的参数
	 * @param request
	 * @param apiRequest
	 */
	private void addApiParams(Request request, ApiRequest apiRequest){
		Map<String, String> requiredParameters = apiRequest.getRequiredParams();
		if( !requiredParameters.isEmpty()){
			addParams(request, requiredParameters);
		}
		Map<String, String> optionalParameters = apiRequest.getOptionalParams();
		if( !optionalParameters.isEmpty()){
			addParams(request, optionalParameters);
		}
	}
	
	private void addParams(Request request, Map<String, String> parameters){
		for(Map.Entry<String, String> p : parameters.entrySet()){
			request.addQuerystringParameter(p.getKey(), p.getValue());
		}
	}
}
