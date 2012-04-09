package kp.client.model;

import java.util.HashMap;

/**
 * 删除文件（夹）API，必填参数有root，path，可选参数有to_recycle
 * @author Tale
 *
 */
public class Delete extends ApiRequest{

	public Delete(String root, String path){
		this.method = "delete";
		this.requiredParameters = new HashMap<String, String>();
		this.optionalParameters = new HashMap<String, String>();
		requiredParameters.put("root", root);
		requiredParameters.put("path", path);
	}
	
	public void toRecycle(boolean bool){
		optionalParameters.put("to_recycle", String.valueOf(bool));
	}
}
