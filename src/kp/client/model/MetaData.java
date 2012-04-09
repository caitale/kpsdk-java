package kp.client.model;

import java.util.HashMap;

import kp.client.constants.SortType;

/**
 * 获取文件（夹）信息API，必填参数是文件相对于root的路径
 * @author Tale
 *
 */
public class MetaData extends ApiRequest{
	public MetaData(String urlPath){
		this.urlPah = urlPath;
		this.method = "metadata";
		this.requiredParameters = new HashMap<String, String>();
		this.optionalParameters = new HashMap<String, String>();
	}

	public void list(boolean bool){
		optionalParameters.put("list", String.valueOf(bool));
	}
	
	public void fileLimit(int limit){
		optionalParameters.put("file_limit", String.valueOf(limit));
	}
	
	public void page(int page){
		optionalParameters.put("page", String.valueOf(page));
	}
	
	public void pageSize(int pageSize){
		optionalParameters.put("page_size", String.valueOf(pageSize));
	}
	
	public void filterExt(String fe){
		optionalParameters.put("filter_ext", fe);
	}
	
	public void SortBy(SortType type){
		optionalParameters.put("sort_by", type.toString());
	}
}
