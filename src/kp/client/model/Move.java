package kp.client.model;

import java.util.HashMap;

/**
 * 移动文件（夹）API，必填的参数有root，from_path, to_path
 * @author Tale
 *
 */
public class Move extends ApiRequest{

	public Move(String root, String fromPath, String toPath){
		this.method = "move";
		this.requiredParameters = new HashMap<String, String>();
		this.optionalParameters = new HashMap<String, String>();
		requiredParameters.put("root", root);
		requiredParameters.put("from_path", fromPath);
		requiredParameters.put("to_path", toPath);
	}
}
