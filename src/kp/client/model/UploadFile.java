package kp.client.model;

import java.util.HashMap;

import kp.client.constants.OverwriteType;

/**
 * 上传文件API，必填参数有overwrite, root, path；uploadUrl为上传地址，filePath为文件的路径，这里设为必填
 * @author Tale
 *
 */
public class UploadFile extends ApiRequest{

	public UploadFile(OverwriteType overwrite, String root, String path, String uploadUrl, String filePath){
		this.method = "upload_file";
		this.urlPah = uploadUrl;
		this.filePath = filePath;
		this.requiredParameters = new HashMap<String, String>();
		this.optionalParameters = new HashMap<String, String>();
		requiredParameters.put("root", root);
		requiredParameters.put("path", path);
		requiredParameters.put("overwrite", overwrite.name());
	}
}
