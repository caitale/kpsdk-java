package kp.client.model;

import java.util.HashMap;

/**
 * 获取文件上传地址API，可选参数有source_ip
 * @author Tale
 *
 */
public class UploadLocate extends ApiRequest{

	public UploadLocate(){
		this.method = "upload_locate";
		this.requiredParameters = new HashMap<String, String>();
		this.optionalParameters = new HashMap<String, String>();
	}
	
	public void sourceIP(String ip){
		optionalParameters.put("source_ip", ip);
	}
}
