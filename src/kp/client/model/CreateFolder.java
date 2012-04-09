package kp.client.model;

import java.util.HashMap;

/**
 * 创建文件（夹）API，必填参数有root，path
 * @author Tale
 *
 */
public class CreateFolder extends ApiRequest{
	
	public CreateFolder(String root, String path){
		this.method = "create_folder";
		this.requiredParameters = new HashMap<String, String>();
		this.optionalParameters = new HashMap<String, String>();
		requiredParameters.put("root", root);
		requiredParameters.put("path", path);
	}
}
