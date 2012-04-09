package kp.client.model;

import java.util.HashMap;

/**
 * 下载文件API，API必填的参数有root，path；filePath为保存文件的地址，这里设为必填
 * @author Tale
 *
 */
public class DownloadFile extends ApiRequest {

	public DownloadFile(String root, String path, String filePath){
		this.method = "download_file";
		this.filePath = filePath;
		this.requiredParameters = new HashMap<String, String>();
		this.optionalParameters = new HashMap<String, String>();
		requiredParameters.put("root", root);
		requiredParameters.put("path", path);
	}
	
	public void fileRev(int rev){
		optionalParameters.put("rev", String.valueOf(rev));
	}
}
