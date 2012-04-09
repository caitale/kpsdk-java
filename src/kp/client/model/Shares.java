package kp.client.model;

import java.util.HashMap;

/**
 * 获取文件分享链接API，必填参数是文件相对于root的路径
 * @author Tale
 *
 */
public class Shares extends ApiRequest{

	public Shares(String urlPah){
		this.urlPah = new String(urlPah);
		this.method = "shares";
		this.requiredParameters = new HashMap<String, String>();
		this.optionalParameters = new HashMap<String, String>();
	}
}
