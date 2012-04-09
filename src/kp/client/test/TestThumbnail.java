package kp.client.test;

import kp.client.core.KuaipanApi;
import kp.client.core.KuaipanApiClient;
import kp.client.exception.KuaipanApiException;
import kp.client.model.Thumbnail;

import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;

/**
 * 
 * @author Tale
 *
 */
public class TestThumbnail {

	public static void main(String[] args){
		Token token = new Token("", "");
		String root = "app_folder";
		String path = "/testFolder/kp.png";
		String filePath = System.getProperty("user.dir") + "/" + root + path;

		int dot = filePath.lastIndexOf(".");
		String newFilePath = filePath.substring(0, dot) + "-thumbnail" + filePath.substring(dot);
		String separator = System.getProperty("file.separator");
		newFilePath = newFilePath.replace("/", separator);
		
		int width = 500;
		int height = 500;
		
		System.out.println(root);
		System.out.println(path);
		System.out.println(newFilePath);
		Thumbnail thumbnail = new Thumbnail(width, height, root, path, newFilePath);
		KuaipanApiClient client = new ServiceBuilder().provider(KuaipanApi.class).build();
		try{
			JSONObject obj = client.doCall(token, thumbnail);
			System.out.println(obj);
		}catch(KuaipanApiException e ){
			System.out.println(e.getStatusCode() + ": " + e.getMessage());
		}
	}
}
