package kp.client.test;

import kp.client.core.KuaipanApi;
import kp.client.core.KuaipanApiClient;
import kp.client.exception.KuaipanApiException;
import kp.client.model.DownloadFile;

import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;

/**
 * 
 * @author Tale
 *
 */
public class TestDownloadFile {

	public static void main(String[] args){
		//获取到的access_token
		Token token = new Token("", "");
		String root = "app_folder";
		String path = "/testFolder/download.txt";
		String filePath = System.getProperty("user.dir") + "/" + root + path;
		DownloadFile downloadFile = new DownloadFile(root, path, filePath);
		KuaipanApiClient client = new ServiceBuilder().provider(KuaipanApi.class).build();
		try{
			JSONObject obj = client.doCall(token, downloadFile);
			System.out.println(obj);
		}catch(KuaipanApiException e ){
			System.out.println(e.getStatusCode() + ": " + e.getMessage());
		}
	}
}
