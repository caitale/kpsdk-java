package kp.client.model;

import java.util.HashMap;

/**
 * 获取缩略图API，必填参数有width, height, root, path；filePath为保存文件的地址，这里设为必填
 * @author Tale
 *
 */
public class Thumbnail extends ApiRequest{

	public Thumbnail(int width, int height, String root, String path, String filePath){
		this.method = "thumbnail";
		this.filePath = filePath;
		this.requiredParameters = new HashMap<String, String>();
		this.optionalParameters = new HashMap<String, String>();
		
		requiredParameters.put("width", String.valueOf(width));
		requiredParameters.put("height", String.valueOf(height));
		requiredParameters.put("root", root);
		requiredParameters.put("path", path);
	}
}
