package kp.client.core;

import java.util.HashMap;
import java.util.Map;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuth10aServiceImpl;
import org.scribe.oauth.OAuthService;

/**
 * API核心配置，需要设置appKey，apiSecret，即consumerKey,consumerSecret。
 * 若申请应用时选择的目录访问权限是 应用目录，则要将apiUrls中含root的地方修改为app_folder
 * @author Tale
 *
 */
public class KuaipanApi extends DefaultApi10a {

	public static final String apiKey = "yourConsumerKey";
	public static final String apiSecret = "yourConsumerSecret";

	private static final String AUTHORIZE_URL = "https://www.kuaipan.cn/api.php?ac=open&op=authorise&oauth_token=%s";
	private static final String REQUEST_TOKEN_RESOURCE = "https://openapi.kuaipan.cn/open/requestToken";
	private static final String ACCESS_TOKEN_RESOURCE = "https://openapi.kuaipan.cn/open/accessToken";

	private static Map<String, String> apiUrls = new HashMap<String, String>();
	static {
		apiUrls.put("account_info", "http://openapi.kuaipan.cn/1/account_info");
		apiUrls.put("metadata", "http://openapi.kuaipan.cn/1/metadata/app_folder%s");
		apiUrls.put("shares", "http://openapi.kuaipan.cn/1/shares/app_folder%s");
		apiUrls.put("delete", "http://openapi.kuaipan.cn/1/fileops/delete");
		apiUrls.put("move", "http://openapi.kuaipan.cn/1/fileops/move");
		apiUrls.put("copy", "http://openapi.kuaipan.cn/1/fileops/copy");
		apiUrls.put("upload_locate", "http://api-content.dfs.kuaipan.cn/1/fileops/upload_locate");
		apiUrls.put("upload_file", "%s1/fileops/upload_file");
		apiUrls.put("download_file", "http://api-content.dfs.kuaipan.cn/1/fileops/download_file");
		apiUrls.put("create_folder", "http://openapi.kuaipan.cn/1/fileops/create_folder");	
		apiUrls.put("thumbnail", "http://conv.kuaipan.cn/1/fileops/thumbnail");	
	}
	
	public String getApiKey(){
		return apiKey;
	}
	
	public String getApiSecret(){
		return apiSecret;
	}

	@Override
	public String getRequestTokenEndpoint() {
		return REQUEST_TOKEN_RESOURCE;
	}

	@Override
	public String getAccessTokenEndpoint() {
		return ACCESS_TOKEN_RESOURCE;
	}

	@Override
	public String getAuthorizationUrl(Token requestToken) {
		return String.format(AUTHORIZE_URL, requestToken.getToken());
	}

	@Override
	public OAuthService createService(OAuthConfig config) {
		return new OAuth10aServiceImpl(this, config);
	}

	@Override
	public String getApiUrl(String method){
		return apiUrls.get(method);
	}
	
	public KuaipanApiClient createApiService(OAuthConfig config){
		return new KuaipanApiClient(this, config);
	}

	@Override
	public Verb getApiVerb() {
		return Verb.GET;
	}
}
